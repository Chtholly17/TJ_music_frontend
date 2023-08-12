<template>
    <div class="wrapper">

        <div class="cont">
            <div class="left"><!--歌曲图片、歌曲名称、歌手、翻唱-->
                <el-image class="song-pic" fit="fill" :src="currenWork.workPrefaceFilename"/>
                <br/>
                <div class="song-info">
                    <p>歌手：{{currentSong.originAuthor }}</p>
                    <p>歌曲：{{currentSong.originName }}</p>
                    <p @click="toWorkOwnerPage">翻唱：{{currentWorkUser.userNickname}}</p>
                </div>
            </div>


            <div class="right"><!--歌词、评论区-->
                <div class="lyric">
                    <div v-for="(item, index) in lrcData" :key="index">
                        <!--大于当前索引的歌词才能被展示；当前播放的歌词才能被高亮-->
                        <p v-if="index>=dataIndex" style="color:black">{{item.words}}</p>
                    </div>
                </div>

                <div class="btn">
                    <el-button class="sub-btn" type="primary" @click="addPresent">
                        <el-icon style="vertical-align: middle">
                            <Present />
                        </el-icon>
                        <span style="vertical-align: middle">{{presentNum}}</span>
                    </el-button>
                    <el-button class="sub-btn" type="primary" @click="enterKSong">我也要唱</el-button>
                </div>
                <div class="comment">
                    <h2 class="comment-title">
                        <span>评论</span>
                        <span class="comment-desc">共 {{ commentList.length }} 条评论</span>
                    </h2>
                    <el-input class="comment-input" type="textarea" placeholder="期待您的精彩评论..." :rows="2" v-model="newComment" clearable/>
                    <div class="btn"><el-button class="sub-btn" type="primary" @click="handlerComment">发表评论</el-button></div>
                </div>

                <div class="popular">
                    <div class="popular_comment" v-for="(item, index) in commentList" :key="index">
                        <el-avatar class="popular-img" shape="square" fit="cover" :src="item.workCommentUser.userProfileImageFilename" />
                        <div class="popular-msg">
                            <span class="name">{{ item.workCommentUser.userNickname }}&nbsp;&nbsp;</span>
                            <span class="time">{{ item.workComment.createTime}}</span>
                            <p class="content">{{ item.workComment.workCommentContent }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <el-affix position="bottom">
            <div class="bottom"><!--进度条-->
                <audio id = "audio" @timeupdate="audioTime" autoplay controls loop :src = "currenWork.workVoiceFilename" style="width:100%;"></audio>
            </div>
        </el-affix>

    </div>

</template>



<script>
import {computed,onBeforeMount, onBeforeUpdate, onMounted, ref, watch,nextTick} from "vue";
import {commitComment, fetchComment} from "@/utils/Texts/commentText";

import {fetchWork} from "@/utils/Texts/work";
import {fetchUserById} from "@/utils/Texts/getuser";

import api from "@/service";
import {ElMessage} from "element-plus";
import {showLoginDialog} from "@/utils/DialogVisible";
import {baseForm, registerData} from "@/utils/Texts/registerText";
import router from "@/router";
import path from "@/service/path";
import axios from "axios";
import {store} from "@/store";
import {getCookie} from "@/service/cookie";

export default {
    name: "music_player",
    functional: true,
    setup(){
        const commentList=ref([]);
        const lrcData=ref([]);//歌词数据数组
        const dataWords=ref("");//当前歌词
        const dataIndex=ref(0);//当前歌词索引
        const audio=ref();//audio对象
        const newComment=ref("");
        const lrc=ref("");
        // console.log(router.currentRoute.value.query.id);
        const currentWorkId = ref(router.currentRoute.value.query.id);
        const currenWork=ref();
        const currentWorkUser=ref();
        const lrcFile = ref();
        const currentSong=ref([]);
        const presentNum=ref(0);//礼物数量


        //歌词数据转化为数组
        const formatLrc = () => {
            if (currentSong.value.originLrcFilename) {
                //在props.originPrefaceFilename去掉前面的path.baseUrl
                // console.log(currentSong.value.originLrcFilename);
                let url = currentSong.value.originLrcFilename.replace(path.serverUrl, "");
                axios.get(path.baseUrl+ url, {
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
                    // console.log(lrcData.value)
                });
            }
        };
        //时间转换（秒）
        const formatTime=(time)=>
        {
            const parts = time.split(":"); //[03:00.000]==>[03,00.00]
            return +parts[0] * 60 + +parts[1]; //计算秒
        };

        const addPresent = () =>{
            presentNum.value = presentNum.value + 1
            //console.log(currentWorkId.value)
            let data = new FormData();
            data.append("workId", currentWorkId.value)
            axios.post(path.baseUrl + path.postPresent,data).then((res) =>{
                console.log(res)
            })
        }

        const toWorkOwnerPage=()=>
        {
            const userId =getCookie("userNumber");
            if (userId == currentWorkUser.value.userStudentNumber)
            {
                router.push({
                    path: "/user",
                });
            }
            else {
                router.push({
                    path: "/otherinfo",
                    query: {
                        id: currentWorkUser.value.userStudentNumber,
                    },
                });
            }
            // console.log(currentWorkUser);
        };
        //获取当前播放时间
        const audioTime=(e)=>
        {
            let time = e.target.currentTime; //当前播放器时间

            // console.log(time)
            // console.log(dataIndex.value)
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

        const handlerComment = async () => {
            // console.log(newComment.value);
            // console.log("1234");
            const userId =getCookie("userNumber");
            // console.log(userId)
            // console.log(currentWorkId.value)
            await commitComment(currentWorkId.value, userId, newComment.value).then(res => {
                fetchComment(currentWorkId.value).then(res => {
                    commentList.value = res;
                    for (let i = 0; i < commentList.value.length; i++) {
                        commentList.value[i].workComment.createTime = commentList.value[i].workComment.createTime.split("T")[0];
                    }
                })
                if(newComment.value)
                    newComment.value = "";
            }).catch(err => {
                console.log(err)
            })
        }

        const enterKSong = () => {
            router.replace({path: '/k_song',query:{id:currentSong.value.originId}})
        }


        onBeforeUpdate(()=> {
            //  fetchComment(1).then(res => {
            //     commentList.value = res;
            //     for (let i = 0; i < commentList.value.length; i++) {
            //         commentList.value[i].workComment.createTime = commentList.value[i].workComment.createTime.split("T")[0];
            //     }
            // })
        })

        onMounted(()=>{
          let form = new FormData();
          form.append("workId", currentWorkId.value);
          axios.post(path.baseUrl+path.getOriginByWorkId,form).then((res) => {
            // console.log(res)
            currentSong.value = res.data.data;

            lrcFile.value = res.data.data.originLrcFilename
            formatLrc();
            nextTick().then(()=>{
              //console.log("nextTick")
            })
          }).catch(err => {
            console.log(err)
          })

        })


        onBeforeMount(() => {
            fetchComment(currentWorkId.value).then(res => {
                // console.log(res)
                commentList.value = res;
                for (let i = 0; i < commentList.value.length; i++) {
                    commentList.value[i].workComment.createTime = commentList.value[i].workComment.createTime.split("T")[0];
                }
            })
            fetchWork(currentWorkId.value).then(res => {
                // console.log(currentWorkId.value)
                console.log(res)
                // console.log("fetchWork")
                currenWork.value = res;
                presentNum.value = currenWork.value.workLike
                console.log(currenWork.value.workVoiceFilename)
                // get user by Id
                let userForm = new FormData();
                // console.log(currenWork.value.workOwner)
                userForm.append("userId", currenWork.value.workOwner);
                // convert workOwner to string

                // console.log(userForm)
                axios.get(path.baseUrl+ path.selectUserById, {params:{userId : currenWork.value.workOwner}}).then((res) => {
                    // console.log(res)
                    currentWorkUser.value = res.data.data;
                    // console.log(currentWorkUser.value)
                }).catch(err => {
                    console.log(err)
                })
                // //formatLrc();
                // audio.value = document.getElementById("audio");
                console.log(res.workVoiceFilename)
                // audio.value = document.getElementById("audio");
                console.log(currenWork.value)
                // audio.value.src = currenWork.value.workVoiceFilename;
                // get lyric

            }).catch(err => {
                console.log(err)
            })
        })


        return {
            currentSong,
            commentList: commentList,
            lrc,
            lrcData,
            dataWords,
            dataIndex,
            newComment: newComment,
            currenWork: currenWork,
            currentWorkUser: currentWorkUser,
            presentNum: presentNum,
            formatLrc,
            formatTime,
            audioTime,
            handlerComment,
            enterKSong: enterKSong,
            toWorkOwnerPage,
            addPresent: addPresent
        }

    }
}

</script>



<style scoped>
.wrapper{
    padding:0;
    margin:0 auto;
    width: 100%;
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
    height: 300px;
    background-color: rgba(0,0,0,0.05);
    overflow: hidden;
}

.right .btn
{
    width:100%;
    height: 30px;
    top:30px;
    margin-top:30px;
    text-align:right;
}


/*评论*/
.right .comment {
    margin: 20px auto;
    width:100%;
    .comment-title {
        height: 50px;
        line-height: 50px;
        text-align:left;
        .comment-desc {
            font-size: 14px;
            font-weight: 400;
            color: $color-grey;
            margin-left: 10px;
        }
    }
    .comment-input {
        display: flex;
        margin-bottom: 20px;
    }
    .btn{
        text-align: right;
    }
}


/*热门评论*/
.right .popular {
    width: 100%;

    .popular_comment {
        border-bottom: solid 1px rgba(0, 0, 0, 0.1);
        padding: 10px 0 0;
        display: flex;

        .popular-img {
            width: 50px;
            height:50px;
            border-radius:5px;
        }

        .popular-msg {
            padding: 0 15px;
            text-align:left;

            .time {
                font-size: 10px;
                color: rgba(0, 0, 0, 0.5);
            }

            .name {
                color: rgba(0, 0, 0, 0.5);
            }

            .content {
                font-size: 15px;
            }
        }
    }




    .bottom {
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 7%;
    }
}
</style>
