<template>
    <div class="wrapper">

        <div class="cont">
            <div class="left"><!--歌曲图片、歌曲名称、歌手、翻唱-->
                <el-image class="song-pic" fit="fill" :src="currentSong.originPrefaceFilename"/>
                <br/>
                <div class="song-info">
                    <p>歌手：{{currentSong.originAuthor }}</p>
                    <p>歌曲：{{currentSong.originName }}</p>
<!--                    <p>翻唱：{{currentSong.cover }}</p>-->
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
                        <p v-if="index>=dataIndex" style="color:black">{{item.words}}</p>
                    </div>
                </div>
                <div class="evaluation">
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
        const dataIndex=ref(0);//当前歌词索引
        const evaluation=ref(router.currentRoute.value.query.comments);
        const currentSong=ref([]);
        const lrc=ref("");
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
            if (currentSong.value.originLrcFilename) {
                //在props.originPrefaceFilename去掉前面的path.baseUrl
                let url = currentSong.value.originLrcFilename.replace(path.serverUrl, "");
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
                    dataIndex.value=i-1;
                    // //保存当前歌词动画执行事件
                    // lrcTime = lrcData.value[i].time - lrcData.value[i - 1].time;
                    return i - 1;
                }
            }
        };


        const publish =() => {
            const userId=getCookie("userNumber");
            // console.log(userId)
            const formData = new FormData();
            // console.log(currentSong.value.originName)
            formData.append('workName', currentSong.value.originName);
            formData.append('workComment', "");
            formData.append('workOwner', userId);
<<<<<<< HEAD
            formData.append('workOriginVersion', current_song.value.originId);
=======
            formData.append('workOriginVersion', currentSong.value.originId);
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            formData.append('workVoiceFilename', fullUrl.value);
            formData.append('workPreciseScore', precise.value);
            formData.append('workQualityScore', quality.value);
            formData.append('workPitchScore', pitch.value);

<<<<<<< HEAD
            commitWork(current_song.value.originName,"",userId,current_song.value.originId,fullUrl.value,precise.value,quality.value,pitch.value).then(res=>{
=======
            commitWork(currentSong.value.originName,"",userId,currentSong.value.originId,fullUrl.value,precise.value,quality.value,pitch.value).then(res=>{
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                // console.log("debug1")
                // console.log(res)
                router.replace({path: '/user/musicLibrary'})
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
                currentSong.value = res.data.data;
                formatLrc();
            })
        })

        return {
            fullUrl,
            currentSong,
            lrc: lrc,
            lrcData,
            dataWords,
            dataIndex,
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
    width: 100%;
    position:fixed;
    background: linear-gradient(to right bottom, rgba(255, 133, 234, 0.5), rgba(0, 255, 236, 0.5));
}

.cont{
    margin: 0 auto;
    width: 1200px;
    width: 100%;
    display:flex;
}

.left{
    margin: 40px auto;
    /*padding-top:40px;*/
    padding-left:5%;
    padding-right:5%;
    /*width: 250px;*/
    width:25%;
    height:800px;
    display:flex;
    flex-direction:column;

    .song-pic
    {
        border-radius:5px;
        width:80%;
    }

    .song-info {
        font-size:17px;
        line-height:15px;
        text-align:left;
        /*margin-left:30px;*/
    }
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
    /*padding-right:10vh;*/
    /*width: 800px;*/
    padding-right:5%;
    width:60%;
    display:flex;
    flex-direction:column;
}

.right .option
{
    width:100%;
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
    width: 100%;
    height: 350px;
    background-color: rgba(0,0,0,0.05);
    overflow: hidden;
}

.evaluation
{
    margin-top:20px;
    width: 90%;
    height: 110px;
    padding-left:5%;
    padding-right:5%;
    background-color: rgba(0,0,0,0.05);
    text-align: left;
    overflow:auto;
}

.bottom {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 7%;
}

</style>
