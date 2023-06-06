<template>
    <div class="wrapper">

        <div class="cont">
            <div class="left"><!--歌曲图片、歌曲名称、歌手、翻唱-->
                <el-image class="song-pic" fit="fill" :src="current_song.originPrefaceFilename"/>
                <br/>
                <div class="song-info">
                    <p>歌手：{{current_song.originAuthor }}</p>
                    <p>歌曲：{{current_song.originName }}</p>
<!--                    <p>翻唱：{{current_song.cover }}</p>-->
<!--                    这个后面改一下变成当前用户-->
                </div>
                <div class="scores">
                    <p class="score_message" >评分详情：</p>
                    <el-progress :stroke-width="10" :percentage=precise  class="score">
                        音准：{{precise}}分
                    </el-progress>
                    <el-progress :stroke-width="10" :percentage=quality  class="score">
                        音质：{{quality}}分
                    </el-progress>
                    <el-progress :stroke-width="10" :percentage=pitch  class="score">
                        音高：{{pitch}}分
                    </el-progress>
                </div>
            </div>


            <div class="right"><!--歌词、按钮-->
                <div class="lyric">
                    <div v-for="(item, index) in lrcData" :key="index">
                        <!--大于当前索引的歌词才能被展示；当前播放的歌词才能被高亮-->
                        <p v-if="index>=data_index" style="color:black">{{item.words}}</p>
                    </div>
                </div>
                <div class="score">
                    <p>{{evaluation}}</p>
                </div>
                <div class="option">
                    <div class="btn"><el-button class="sub-btn" type="primary" @click="cancel">取消发布</el-button></div>
                    <div class="btn"><el-button class="sub-btn" type="primary" @click="publish">发表作品</el-button></div>
                </div>
            </div>
        </div>
        <el-affix position="bottom">
            <div class="bottom"><!--进度条-->
                <audio id="audio" @timeupdate="audioTime" autoplay controls preload="auto" :src=fullUrl   style="width:100%;"></audio>
            </div>
        </el-affix>

    </div>

</template>



<script>
import {onBeforeMount, onBeforeUpdate, onMounted,ref, watch} from "vue";
import api from "@/service";
import {ElMessage} from "element-plus";
import {showLoginDialog} from "@/utils/DialogVisible";
import {baseForm, registerData} from "@/utils/Texts/registerText";
import router from "@/router";
import path from "@/service/path";
import axios from "axios";
import {commitWork} from "@/utils/Texts/work";
import {getCookie} from "@/service/cookie";
export default {
    name: "song_preview",
    functional: true,
    setup(){
        const lrcData=ref([]);//歌词数据数组
        const dataWords=ref("");//当前歌词
        const data_index=ref(0);//当前歌词索引
        // const evaluation="你的歌唱表现非常优秀，尤其是情感方面，达到了满分的分数。你的音准表现也非常出色，"+
        //     "达到了90分的高分。此外，你的音色也非常好，获得了80分的高分。虽然你的节奏分数较低，但这并不影响你的整体表现。"+
        //     "建议你可以多加练习节奏感，并且注重口腔肌肉的控制，以提高唱歌技巧。继续保持！";//评分评价
        const evaluation=ref(router.currentRoute.value.query.comments);
        const current_song=ref([]);
        const LRC=ref("");
        // console.log(router.currentRoute.value.query.url);
        const fullUrl = ref(router.currentRoute.value.query.url);
        // console.log(router.currentRoute.value.query.precise);
        const precise=ref(router.currentRoute.value.query.precise);
        const quality=ref(router.currentRoute.value.query.quality);
        const pitch=ref(router.currentRoute.value.query.pitch);
        // transfer precise to int
        precise.value=parseInt(precise.value);
        quality.value=parseInt(quality.value);
        pitch.value=parseInt(pitch.value);
        // console.log(precise.value);
        // console.log(quality.value);
        // console.log(pitch.value);


        // const param = router.currentRoute.value.params
        // console.log(router.currentRoute.value)

        //歌词数据转化为数组
        const formatLrc = () => {
            if (current_song.value.originLrcFilename) {
                //在props.originPrefaceFilename去掉前面的path.baseUrl
                let url = current_song.value.originLrcFilename.replace(path.serverUrl, "");
                axios.get(path.baseUrl + url, {
                }).then((res) => {
                    const strLrc = res.data.split("\n");
                    let arr = [];
                    for (let i = 0; i < strLrc.length; i++) {
                        const str = strLrc[i];
                        const parts = str.split("]");
                        const timeStr = parts[0].substring(1);
                        const obj = {
                            time: formatTime(timeStr),
                            words: parts[1],
                        };
                        arr.push(obj);
                    }
                    lrcData.value = arr;
                });
            }
        };

        //歌词中字符串时间转换为秒数时间
        const formatTime=(time)=>
        {
            const parts = time.split(":"); //[03:00.000]==>[03,00.00]
            return +parts[0] * 60 + +parts[1]; //计算秒
        };

        //获取当前播放时间
        const audioTime=(e)=>
        {
            let time = e.target.currentTime; //当前播放器时间
            for (let i = 0; i < lrcData.value.length; i++)
            {
                if (time < lrcData.value[i].time)
                {
                    //循环歌词数组，当播放器当前时间第一次小于歌词时间时当前数组下标减一即为当前时间数组所对应歌词下标
                    dataWords.value = lrcData.value[i - 1].words;
                    data_index.value=i-1;
                    // //保存当前歌词动画执行事件
                    // lrcTime = lrcData.value[i].time - lrcData.value[i - 1].time;
                    return i - 1;
                }
            }
        };


        const publish =() => {
            const user_id=getCookie("userNumber");
            // console.log(user_id)
            const formData = new FormData();
            // console.log(current_song.value.originName)
            formData.append('workName', current_song.value.originName);
            formData.append('workComment', "");
            formData.append('workOwner', user_id);
            formData.append('workOriginVersion', current_song.value.originId);
            formData.append('workVoiceFilename', fullUrl.value);
            formData.append('workPreciseScore', precise.value);
            formData.append('workQualityScore', quality.value);
            formData.append('workPitchScore', pitch.value);

            commitWork(current_song.value.originName,"",user_id,current_song.value.originId,fullUrl.value,precise.value,quality.value,pitch.value).then(res=>{
                // console.log("debug1")
                // console.log(res)
                router.replace({path: '/user/music_library'})
            }).then(res=>{
                alert("发表成功！")
            }).catch(err=>{
                alert("发表失败！")
            })
        }

        const cancel =() => {
            router.replace({path: '/music_square'})
        }

        onBeforeMount(() => {
            let form=new FormData()
            form.append("originId",router.currentRoute.value.query.id)
            // audio.value = document.getElementById("audio");
            // audio.value.url = router.currentRoute.value.query.url
            axios.post(path.baseUrl + path.getOriginByOriginId, form).then((res) => {
                current_song.value = res.data.data;
                formatLrc();
            })
        })

        return {
            fullUrl,
            current_song,
            LRC,
            lrcData,
            dataWords,
            data_index,
            evaluation,
            precise,
            quality,
            pitch,
            formatLrc,
            formatTime,
            audioTime,
            publish,
            cancel
        }

    }
}

</script>


<style scoped>
.wrapper{
    padding:0;
    margin:0;
    position:fixed;
}

.cont{
    margin: 0 auto;
    width: 1200px;
    display:flex;
}

.left{
    margin: 0 auto;
    padding-top:40px;
    padding-left:40px;
    width: 250px;
    height:800px;
    display:flex;
    flex-direction:column;
}


.song-pic
{
    border-radius:5px;
}

.song-info {
    font-size:17px;
    line-height:15px;
    text-align:left;
    margin-left:30px;
}

.score_message
{
    text-align: left;
    font-size:20px;
}

.scores{
    margin-top:20px;
}

.score{
    margin-top:20px;
}

.right{
    margin: 0 auto;
    padding-top:40px;
    padding-right:20px;
    width: 800px;
    display:flex;
    flex-direction:column;
}

.right .option
{
    width:800px;
    height:30px;
    margin-top:30px;
    display:flex;
    flex-direction: row-reverse;
    .btn
    {
        margin-left:50px;
    }
}

.right .lyric
{
    margin: 0 auto;
    width: 800px;
    height: 350px;
    background-color: rgba(0,0,0,0.05);
    overflow: hidden;
}

.right .score
{
    margin-top:20px;
    width: 780px;
    height: 80px;
    padding:10px;
    background-color: rgba(0,0,0,0.05);
    text-align: left;
}

.bottom {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 7%;
}

</style>
