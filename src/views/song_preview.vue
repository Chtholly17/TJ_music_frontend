<template>
    <div class="wrapper">

        <div class="cont">
            <div class="left"><!--歌曲图片、歌曲名称、歌手、翻唱-->
                <el-image class="song-pic" fit="fill" :src="current_song.img"/>
                <br/>
                <div class="song-info">
                    <p>歌手：{{current_song.singer }}</p>
                    <p>歌曲：{{current_song.name }}</p>
                    <p>翻唱：{{current_song.cover }}</p>
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
                <audio id="audio" @timeupdate="audioTime" autoplay controls preload="auto" :src="current_song.vocal_url"  style="width:100%;"></audio>
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
export default {
    name: "song_preview",
    functional: true,
    setup(){
        const current_song={
            name: "千千阕歌",
            singer: "陈慧娴",
            cover:"刘安民",
            img:require('@/assets/material/image.jpg'),
            vocal_url:require('@/assets/material/翻唱.mp3'),
        };
        const LRC="[00:00.00]千千阕歌 \n" +
            "[00:02.00]作词 : 林振强\n" +
            "[00:06.00]作曲 : 馬飼野康二\n" +
            "[00:10.00]歌手 : 陈慧娴\n" +
            "[00:14.00]徐徐回望, 曾属于彼此的晚上\n" +
            "[00:22.00]红红仍是你, 赠我的心中艳阳\n" +
            "[00:29.00]如流傻泪, 祈望可体恤兼见谅\n" +
            "[00:37.00]明晨离别你, 路也许孤单得漫长\n" +
            "[00:43.00]一瞬间, 太多东西要讲\n" +
            "[00:47.00]可惜即将在各一方\n" +
            "[00:50.00]只好深深把这刻尽凝望\n" +
            "[00:57.00]来日纵是千千阕歌\n" +
            "[01:00.00]飘于远方我路上\n" +
            "[01:04.00]来日纵是千千晚星\n" +
            "[01:07.00]亮过今晚月亮\n" +
            "[01:11.00]都比不起这宵美丽\n" +
            "[01:15.00]亦绝不可使我更欣赏\n" +
            "[01:18.00]AH..因你今晚共我唱\n" +
            "[01:56.00]临行临别, 才顿感哀伤的漂亮\n" +
            "[02:04.00]原来全是你, 令我的思忆漫长\n" +
            "[02:11.00]何年何月, 才又可今宵一样\n" +
            "[02:18.00]停留凝望里, 让眼睛讲彼此立场\n" +
            "[02:25.00]当某天, 雨点轻敲你窗\n" +
            "[02:29.00]当风声吹乱你构想\n" +
            "[02:32.00]可否抽空想这张旧模样\n" +
            "[02:39.00]来日纵是千千阕歌\n" +
            "[02:43.00]飘于远方我路上\n" +
            "[02:46.00]来日纵是千千晚星\n" +
            "[02:50.00]亮过今晚月亮\n" +
            "[02:54.00]都比不起这宵美丽\n" +
            "[02:57.00]亦绝不可使我更欣赏\n" +
            "[03:01.00]AH..因你今晚共我唱\n" +
            "[03:21.00]AH..怎都比不起这宵美丽\n" +
            "[03:26.00]亦绝不可使我更欣赏\n" +
            "[03:30.00]因今晚的我可共你唱\n" +
            "[03:36.00]来日纵是千千阕歌\n" +
            "[03:39.00]飘于远方我路上\n" +
            "[03:43.00]来日纵是千千晚星\n" +
            "[03:46.00]亮过今晚月亮\n" +
            "[03:50.00]都比不起这宵美丽\n" +
            "[03:54.00]亦绝不可使我更欣赏\n" +
            "[03:57.00]AH..因你今晚共我唱\n" +
            "[04:04.00]来日纵是千千阙歌\n" +
            "[04:08.00]飘于远方我路上\n" +
            "[04:11.00]来日纵是千千晚星\n" +
            "[04:14.00]亮过今晚月亮\n" +
            "[04:18.00]都比不起这宵美丽\n" +
            "[04:22.00]都洗不清今晚我所思\n" +
            "[04:26.00]因不知哪天再共你唱\n";
        const lrcData=ref([]);//歌词数据数组
        const dataWords=ref("");//当前歌词
        const data_index=ref(0);//当前歌词索引
        const evaluation="你的歌唱表现非常优秀，尤其是情感方面，达到了满分的分数。你的音准表现也非常出色，"+
            "达到了90分的高分。此外，你的音色也非常好，获得了80分的高分。虽然你的节奏分数较低，但这并不影响你的整体表现。"+
            "建议你可以多加练习节奏感，并且注重口腔肌肉的控制，以提高唱歌技巧。继续保持！";//评分评价


        //歌词数据转化为数组
        const formatLrc = () => {
            const strLrc = LRC.split("\n");
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
        };

        //时间转换（秒）
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
            alert("发表成功！")
            router.replace({path: '/user/music_library'})
        }

        const cancel =() => {
            router.replace({path: '/user/music_library'})
        }

        onBeforeMount(() => {
            formatLrc();
        })

        return {
            current_song,
            LRC,
            lrcData,
            dataWords,
            data_index,
            evaluation,
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