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
                </div>
<!--                <div class="wave">-->
<!--                    <video loop muted width="250" height="200" id="video">-->
<!--                        <source src="@/assets/material/wave.mp4" type="video/mp4">-->
<!--                    </video>-->
<!--                </div>-->
            </div>


            <div class="right"><!--歌词、按钮-->
                <div class="lyric">
                    <div v-for="(item, index) in lrcData" :key="index">
                        <!--大于当前索引的歌词才能被展示；当前播放的歌词才能被高亮-->
                        <p v-if="index >=dataIndex" style="color:black">{{item.words}}</p>

                    </div>
                </div>

                <div class="demo-progress">
                    <el-progress :percentage="percentage" striped >
                        <span>{{timeToString(duration)}}</span>
                    </el-progress>
                </div>

                <div class="option">
                    <div class="btn">
                        <el-button class="sub-btn" type="primary" @click="chmod">
                            <el-icon style="vertical-align: middle">
                                <User />
                            </el-icon>
                            <span style="vertical-align: middle">{{curModeText}}</span>
                        </el-button>
                    </div>
                    <div class="btn">
                        <el-button class="sub-btn" type="primary" :disabled=!startPlaying @click="again">
                            <el-icon style="vertical-align: middle">
                                <Refresh />
                            </el-icon>
                            <span style="vertical-align: middle">重唱</span>
                        </el-button>
                    </div>
                    <div class="btn">
                        <el-button class="sub-btn" type="primary" :disabled=startIsDisabled @click="start">
                            <el-icon style="vertical-align: middle">
                                <Microphone />
                            </el-icon>
                            <span style="vertical-align: middle">开始</span>
                        </el-button>
                    </div>
                    <div class="btn">
                        <el-button class="sub-btn" type="primary" :disabled=pauseIsDisabled||!startPlaying @click="pause">
                            <el-icon style="vertical-align: middle">
                                <VideoPause />
                            </el-icon>
                            <span style="vertical-align: middle">暂停</span>
                        </el-button>
                    </div>
                    <div class="btn">
                        <el-button class="sub-btn" type="primary" @click="enterSongPreview" :disabled=!startPlaying>
                            <el-icon style="vertical-align: middle">
                                <Headset />
                            </el-icon>
                            <span style="vertical-align: middle">完成</span>
                        </el-button>
                    </div>
                </div>
            </div>
        </div>

        <el-affix position="bottom">
            <div class="bottom"><!--进度条-->
                <audio id="audio" @timeupdate="audioTime"  preload="auto" style="width:100%;"></audio>
            </div>
        </el-affix>

    </div>

</template>



<script>
import {onBeforeMount, onBeforeUpdate, onMounted,ref, watch} from "vue";
import api from "@/service";
import path from "@/service/path"
import axios from "axios"
import {ElMessage} from "element-plus";
import {showLoginDialog} from "@/utils/DialogVisible";
import {baseForm, registerData} from "@/utils/Texts/registerText";
import router from "@/router";
import {Headset, Microphone, Refresh} from "@element-plus/icons-vue";
import {fetchComment} from "@/utils/Texts/commentText";
import {fetchOriginByOriginId} from "@/utils/Texts/origin";
import Recoder from 'js-audio-recorder'
import { ElLoading } from 'element-plus'
import {getCookie} from "@/service/cookie";


export default {
    name: "k_song",
    components: {Headset, Microphone, Refresh},
    functional: true,
    setup(){
        const lrcData=ref([]);//歌词数据数组
        const dataWords=ref("");//当前歌词
        const dataIndex=ref(0);//当前歌词索引
        const curMode=ref(0);//当前模式（0为伴唱，1为原唱）
        const curModeText=ref("原唱");//当前模式按钮展示的文字
        const curTime=ref(0);//当前时间
        const audio=ref();//audio对象
        const startIsDisabled=ref(false);//播放按钮是否禁用
        const pauseIsDisabled=ref(false);//暂停按钮是否禁用
        const percentage=ref(0);//进度条属性
        const duration=ref(0);//当前歌曲时长
        const wave=ref();//波形图属性
        const currentSong=ref([]);
        const lrc=ref("");
        const recoder = ref();
        const originId = ref(router.currentRoute.value.query.id);
        // console.log("originId",originId.value);
        const startPlaying=ref(false);
        const isPausing = ref(false);
        const isPause = ref(false);
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
        //秒数时间转换为字符串时间（分+秒）
        const timeToString=(time)=>
        {
            const minute=Math.floor(time/60);
            const second=Math.floor(time%60);
            return minute.toString()+":"+second.toString();
        };
        //获取当前播放时间
        const audioTime=(e)=>
        {
            let time = e.target.currentTime; //当前播放器时间
            percentage.value=time/audio.value.duration*100;
            for (let i = 0; i < lrcData.value.length; i++)
            {
                if (time < lrcData.value[i].time)
                {
                    //循环歌词数组，当播放器当前时间第一次小于歌词时间时当前数组下标减一即为当前时间数组所对应歌词下标
                    dataWords.value = lrcData.value[i - 1].words;
                    dataIndex.value=i-1;
                    return i - 1;
                }
            }
        };



        const chmod = async () => {
            if(curMode.value) {
                const temp=ref(audio.value.currentTime);
                curMode.value = 0;//切换为伴唱
                curModeText.value="原唱";
                audio.value.src = currentSong.value.originBgmusicFilename;
                audio.value.currentTime=temp.value;
                if(startPlaying.value)
                    audio.value.play()
            }
            else {
                const temp=ref(audio.value.currentTime);
                curMode.value = 1;//切换为原唱
                curModeText.value="伴唱";
                audio.value.src = currentSong.value.originVoiceFilename;
                audio.value.currentTime=temp.value;
                if(startPlaying.value)
                    audio.value.play()
            }
        };


        const start = async () => {
            // console.log(startIsDisabled.value);
            startIsDisabled.value=true;
            pauseIsDisabled.value=false;
            startPlaying.value=true;
            if(isPausing.value === false)
            {
                recoder.value.start().then(() => {
                    // console.log('start recording')
                    duration.value = audio.value.duration
                    audio.value.play();
                    wave.value.play();
                }, (err) =>{
                    console.log(err)
                });
            }
            else
            {
                audio.value.play();
                recoder.value.resume();
                isPausing.value=false;
            }
        }

        const pause = async () => {
            isPausing.value = true
            // recoder.value.pause();
            audio.value.pause();
            recoder.value.pause();
            isPausing.value = true;
            startIsDisabled.value=false;
            pauseIsDisabled.value=true;
            wave.value.currentTime=0;
            wave.value.pause();

        }

        const again = async () => {
            isPausing.value = false

            recoder.value.stop()
            recoder.value.start().then(() => {
                // console.log('start recording')
                startPlaying.value=true;
                audio.value.currentTime=0;
            }, (err) =>{
                console.log(err)
            });
        }

        const enterSongPreview = () => {
            //在等待时加载动画
            const loading = ElLoading.service({
                lock: true,
                text: '作品生成中，请稍后哟......',
                background: 'rgba(0, 0, 0, 0.7)',
            })
            // setTimeout(() => {
            //     loading.close()
            // }, 10000)
            audio.value.pause()
            const blob = recoder.value.getWAVBlob()
            let formData = new FormData()
            formData.append("file", blob,'test.wav');
            formData.append("originId",originId.value);
            const userId =getCookie("userNumber");
            formData.append("userStudentNumber",userId)
            axios.post(path.baseUrl+path.postMusic,formData).then(res=>{
                // console.log("上传成功");
                // console.log(res);
                // console.log(res.data.data.scores)
                // console.log(res.data.data.url)
                let url = res.data.data.url
                let scores = res.data.data.scores
                // path.getComments,scores.preciseScore,scores.qualityScore,scores.pitchScore
                // console.log(scores)
                loading.close()
                let scoreForm = new FormData()
                scoreForm.append("preciseScore",scores.preciseScore)
                scoreForm.append("qualityScore",scores.qualityScore)
                scoreForm.append("pitchScore",scores.pitchScore)
                const loading_comment = ElLoading.service({
                  lock: true,
                  text: '生成评价中......',
                  background: 'rgba(0, 0, 0, 0.7)',
                })
                axios.post(path.baseUrl+path.getAiComment,scoreForm).then(res=>{
                    loading_comment.close()
                    // console.log(res)
                    let comments = res.data.data
                    // console.log(comments)
                    router.push({path: '/song_preview',query:{precise: scores.preciseScore, quality: scores.qualityScore , pitch: scores.pitchScore, url: url, id:originId.value, comments: comments}})
                }).catch(err=>{
                    console.log(err);
                  router.push({path: '/song_preview',query:{precise: scores.preciseScore, quality: scores.qualityScore , pitch: scores.pitchScore, url: url, id:originId.value, comments: "获取评价失败"}})
                })

                // router.push({path: '/song_preview',query:{score: scores, url: res.data.data.url, id:router.currentRoute.value.query.id.toString()}})
            }).catch(err=>{
                console.log(err);
            })
        }

        onBeforeMount(() => {
            let form=new FormData()
            //form.append("originId","8")
            form.append("originId",router.currentRoute.value.query.id.toString())//获取从music_player来的原唱ID编号
            axios.post(path.baseUrl + path.getOriginByOriginId, form).then((res) => {
                currentSong.value = res.data.data;
                formatLrc();
                audio.value = document.getElementById("audio");
                wave.value=document.getElementById("video");
                audio.value.src = currentSong.value.originBgmusicFilename;
                originId.value = currentSong.value.originId;
                // console.log(currentSong.value.originId)
                recoder.value = new Recoder();
            })
        })

        return {
            currentSong: currentSong,
            lrc,
            lrcData,
            dataWords,
            dataIndex: dataIndex,
            curMode: curMode,
            curModeText: curModeText,
            curTime: curTime,
            startIsDisabled: startIsDisabled,
            pauseIsDisabled: pauseIsDisabled,
            percentage,
            duration,
            startPlaying,
            chmod,
            again,
            formatLrc,
            formatTime,
            audioTime,
            enterSongPreview: enterSongPreview,
            start,
            pause,
            timeToString: timeToString
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

.right .lyric
{
    margin: 0 auto;
    width:100%;
    height: 400px;
    background-color: rgba(0,0,0,0.05);
    overflow: hidden;
}

.right .option
{
    width:100%;
    height:30px;
    margin-top:10px;
    margin-left:10%;
    display:flex;
    .btn
    {
        margin-right:50px;
    }
}

.bottom {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 7%;
}

.demo-progress
{
    margin-left:10%;
    /*position: fixed;*/
    /*bottom: 10px;*/
    margin-top:80px;
    width: 90%;
    height: 7%;
}

</style>
