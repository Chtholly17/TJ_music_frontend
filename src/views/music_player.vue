<template>
    <div class="wrapper">

        <div class="cont">
            <div class="left"><!--歌曲图片、歌曲名称、歌手、翻唱-->
                <el-image class="song-pic" fit="fill" :src="current_song.originPrefaceFilename"/>
                <br/>
                <div class="song-info">
                    <p>歌手：{{current_song.originAuthor }}</p>
                    <p>歌曲：{{current_song.originName }}</p>
                    <p>翻唱：{{current_work_user.userNickname }}</p>
                </div>
            </div>


            <div class="right"><!--歌词、评论区-->
                <div class="lyric">
                    <div v-for="(item, index) in lrcData" :key="index">
                        <!--大于当前索引的歌词才能被展示；当前播放的歌词才能被高亮-->
                        <p v-if="index>=data_index" style="color:black">{{item.words}}</p>
                    </div>
                </div>

                <div class="btn"><el-button class="sub-btn" type="primary" @click="enter_k_song">我也要唱</el-button></div>
                <div class="comment">
                    <h2 class="comment-title">
                        <span>评论</span>
                        <span class="comment-desc">共 {{ comment_list.length }} 条评论</span>
                    </h2>
                    <el-input class="comment-input" type="textarea" placeholder="期待您的精彩评论..." :rows="2" v-model="new_comment" clearable/>
                    <div class="btn"><el-button class="sub-btn" type="primary" @click="handlerComment">发表评论</el-button></div>
                </div>

                <div class="popular">
                    <div class="popular_comment" v-for="(item, index) in comment_list" :key="index">
                        <el-image class="popular-img" fit="fill" :src="item.workCommentUser.userProfileImageFilename" />
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
                <audio id = "audio" @timeupdate="audioTime" autoplay controls loop  style="width:100%;"></audio>
            </div>
        </el-affix>

    </div>

</template>



<script>
import {computed,onBeforeMount, onBeforeUpdate, onMounted, ref, watch} from "vue";
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
        const comment_list=ref([]);
        const lrcData=ref([]);//歌词数据数组
        const dataWords=ref("");//当前歌词
        const data_index=ref(0);//当前歌词索引
        const audio=ref();//audio对象
        const new_comment=ref("");
        const LRC=ref("");
        console.log(router.currentRoute.value.query.id);
        const current_work_id = ref(router.currentRoute.value.query.id);
        const current_work=ref();
        const current_work_user=ref();
        const LrcFile = ref();
        const current_song=ref([]);


        //歌词数据转化为数组
        const formatLrc = () => {
            if (current_song.value.originLrcFilename) {
                //在props.originPrefaceFilename去掉前面的path.baseUrl
                let url = current_song.value.originLrcFilename.replace(path.baseUrl, "");
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

            // console.log(time)
            // console.log(data_index.value)
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

        const handlerComment = async () => {
            console.log(new_comment.value);
            console.log("1234");
            const userId =getCookie("userNumber");
            console.log(userId)
            console.log(current_work_id.value)
            await commitComment(current_work_id.value, userId, new_comment.value).then(res => {
                fetchComment(current_work_id.value).then(res => {
                        comment_list.value = res;
                        for (let i = 0; i < comment_list.value.length; i++) {
                            comment_list.value[i].workComment.createTime = comment_list.value[i].workComment.createTime.split("T")[0];
                        }
                    })
                if(new_comment.value)
                  new_comment.value = "";
            }).catch(err => {
                console.log(err)
            })
        }

        const enter_k_song = () => {
            router.replace({path: '/k_song'})
        }


        onBeforeUpdate(()=> {
            //  fetchComment(1).then(res => {
            //     comment_list.value = res;
            //     for (let i = 0; i < comment_list.value.length; i++) {
            //         comment_list.value[i].workComment.createTime = comment_list.value[i].workComment.createTime.split("T")[0];
            //     }
            // })
        })


        onBeforeMount(() => {
            fetchComment(current_work_id.value).then(res => {
                console.log(res)
                comment_list.value = res;
                for (let i = 0; i < comment_list.value.length; i++) {
                    comment_list.value[i].workComment.createTime = comment_list.value[i].workComment.createTime.split("T")[0];
                }
            })
            fetchWork(current_work_id.value).then(res => {
                console.log(current_work_id.value)
                console.log(res)
                console.log("fetchWork")
                current_work.value = res;
                console.log(current_work.value.workVoiceFilename)
                // get user by Id
                let userForm = new FormData();
                console.log(current_work.value.workOwner)
                userForm.append("userId", current_work.value.workOwner);
                // convert workOwner to string

                console.log(userForm)
                axios.get(path.baseUrl+ path.selectUserById, {params:{userId : current_work.value.workOwner}}).then((res) => {
                    console.log(res)
                    current_work_user.value = res.data.data;
                    console.log(current_work_user.value)
                    
                }).catch(err => {
                    console.log(err)
                })
                //formatLrc();
                audio.value = document.getElementById("audio");
                audio.value.src = current_work.value.workVoiceFilename;
                // get lyric
                let form = new FormData();
                form.append("workId", current_work_id.value);
                axios.post(path.baseUrl+path.getOriginByWorkId,form).then((res) => {
                    console.log(res)
                    current_song.value = res.data.data;
                    console.log(current_song.value)
                    LrcFile.value = res.data.data.originLrcFilename
                    console.log(LrcFile.value)
                    formatLrc();
                }).catch(err => {
                    console.log(err)
                })
            }).catch(err => {
                console.log(err)
            })
        })


        return {
            current_song,
            comment_list,
            LRC,
            lrcData,
            dataWords,
            data_index,
            new_comment,
            current_work,
            current_work_user,
            formatLrc,
            formatTime,
            audioTime,
            handlerComment,
            enter_k_song
        }

    }
}

</script>



<style scoped>
.wrapper{
    padding:0;
    margin:0;
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
    padding-right:40px;
    width: 800px;
    display:flex;
    flex-direction:column;
}

.right .lyric
{
    margin: 0 auto;
    width: 800px;
    height: 300px;
    background-color: rgba(0,0,0,0.05);
    overflow: hidden;
}

.right .btn
{
    width: 800px;
    height: 30px;
    top:30px;
    margin-top:30px;
    text-align:right;
}


/*评论*/
.right .comment {
    margin: 20px auto;
    width: 800px;
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
