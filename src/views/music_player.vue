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


            <div class="right"><!--歌词、评论区-->
                <div class="lyric">
                    <div v-for="(item, index) in lrcData" :key="index">
                        <!--                        <p>{{item.words}}</p>-->
                        <!--大于当前索引的歌词才能被展示；当前播放的歌词才能被高亮-->
                        <p v-if="index>=data_index" style="color:black">{{item.words}}</p>
                        <!--                        <p v-if="index>data_index" style="color:black">{{item.words}}</p>-->
                    </div>
                </div>


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
                <audio @timeupdate="audioTime" controls :src="current_song.vocal_url"  style="width:100%;"></audio>
            </div>
        </el-affix>

    </div>

</template>



<script>
import {onBeforeMount, onBeforeUpdate, ref, watch} from "vue";
import {commitComment, fetchComment} from "@/utils/Texts/commentText";
import api from "@/service";
import {ElMessage} from "element-plus";
import {showLoginDialog} from "@/utils/DialogVisible";
import {baseForm, registerData} from "@/utils/Texts/registerText";
export default {
    name: "music_player",
    functional: true,
    setup(){
        const current_song={
            name: "千千阕歌",
            singer: "陈慧娴",
            cover:"刘安民",
            img:require("../assets/material/image.jpg"),
            vocal_url:require("../assets/material/原唱_bgm.mp3"),
            bgm_url:require("../assets/material/bgm.mp3"),
        };
        // const comment_list=[
        //     {username:"刘安民",content:"不愧是我",create_time:"2023-05-08",url:require("../assets/cxk4.png")},
        //     {username:"姜 垒",content:"哎哎哟！",create_time:"2023-05-07",url:require("../assets/cxk5.jpg")},
        //     {username:"王子安",content:"哈哈哈哈哈",create_time:"2023-05-06",url:require("../assets/cxk6.jpg")},
        //     {username:"吴俊成",content:"呵呵呵呵呵呵呵",create_time:"2023-05-05",url:require("../assets/cxk7.jpg")},
        // ];
        const comment_list=ref([]);
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
        const new_comment=ref("");
        let lrcTime=0;//当前时间
        let className="gray";



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
                    //保存当前歌词动画执行事件
                    lrcTime = lrcData.value[i].time - lrcData.value[i - 1].time;
                    return i - 1;
                }
            }
        };

        const handlerComment = async () => {
            console.log(new_comment.value);
            commitComment(1,16,new_comment.value);
            if(new_comment.value)
                new_comment.value.clear()
        }

        watch(dataWords,()=> {
            console.log(data_index.value);
        })

        onBeforeUpdate(()=> {
            fetchComment(1).then(res => {
                comment_list.value = res;

                for (let i = 0; i < comment_list.value.length; i++) {
                    comment_list.value[i].workComment.createTime = comment_list.value[i].workComment.createTime.split("T")[0];
                }
            })
        })


        onBeforeMount(() => {
            fetchComment(1).then(res => {
                comment_list.value = res;

                for (let i = 0; i < comment_list.value.length; i++) {
                    comment_list.value[i].workComment.createTime = comment_list.value[i].workComment.createTime.split("T")[0];
                }
            })
            formatLrc();
        })

        return {
            current_song,
            comment_list,
            LRC,
            lrcData,
            dataWords,
            data_index,
            lrcTime,
            className,
            new_comment,
            formatLrc,
            formatTime,
            audioTime,
            handlerComment
        }

    }
}

</script>



<style>
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
            padding: 0px 15px;
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