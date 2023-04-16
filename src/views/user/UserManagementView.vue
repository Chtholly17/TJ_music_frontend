<template>
    <div class="common-layout">
        <el-container>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main">
                <el-row :gutter="20">
                    <el-col :span="8">
                       <img :src=user_photo_url class="user_photo" >
                    </el-col>
                    <el-col :span="6">
                        <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{nickname}}</p>
                        <p style="text-align: left"> 姓名: &nbsp; &nbsp;{{user_name}}</p>
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{user_student_num}}</p>
                        <p style="text-align: left"> 生日: &nbsp; &nbsp;{{user_brithday}}</p>
                        <p style="text-align: left"> 家乡: &nbsp; &nbsp;{{user_home}}</p>
                    </el-col>
                    <el-col :span="3">
                        <div style="height: 70px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            关注: {{ user_follow }}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            粉丝: {{user_fans}}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            动态: {{user_post}}
                        </div>
                    </el-col>
                    <el-col :span="7">
                        <div style="height: 10px"></div>
                        <el-button type="info" :icon="Edit">个人资料</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View">我的关注</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Search">我的粉丝</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Share">我的动态</el-button>
                    </el-col>
                </el-row>

                <div class="user_music_library">
                    <p>我的曲库</p>
                    <el-divider></el-divider>
                    <el-scrollbar height="400px" >
                        <p v-for="(item,index) in user_music" :key="index" class="scrollbar-demo-item">
                            <span style="justify-content: flex-start;display: flex">&nbsp;&nbsp;{{index+1}}.{{item.music_name}}&nbsp;&nbsp; -- {{item.singer}}</span>
                            <span style="justify-content: flex-end;display: flex">{{item.date}}</span>
                        </p>
                    </el-scrollbar>
                </div>

            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>
    </div>
</template>

<script>
import { Delete, Edit, Search, Share, Upload,View } from '@element-plus/icons-vue';
export default {

    name: "UserManagementView",
   data(){
        return{
            aside_width:35,
            user_photo_url:require("../../assets/profile.jpg"),
            nickname:"日本天皇",
            user_name:"孙笑川",
            user_student_num:"2050000",
            user_brithday:"1945-8-15",
            user_home:"上海市",
            user_follow:20,
            user_fans:10,
            user_post:15,
            user_music:[
                {
                    music_name:"青花瓷",
                    singer:"周杰伦",
                    date:"2023-4-10"
                },
                {
                    music_name:"东风破",
                    singer:"周杰伦",
                    date:"2023-4-9"
                },
                {
                    music_name:"花海",
                    singer:"周杰伦",
                    date:"2023-4-8"
                },
                {
                    music_name:"千里之外",
                    singer:"周杰伦/费玉清",
                    date:"2023-4-7"
                },
                {
                    music_name:"园游会",
                    singer:"周杰伦",
                    date:"2023-4-6"
                },
                {
                    music_name:"晴天",
                    singer:"周杰伦",
                    date:"2023-4-5"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                }
            ]
        }
    },
    setup(){
        return{
            Search,
            Share,
            Upload,
            View,
            Edit
        }
    }
}
</script>

<style scoped>
.user_main{
    //background-color: cornflowerblue;
    height: 90vh;
}
.user_aside{
    background-color: rgb(245,245,245);
}
.user_info{
    height: 250px;
    background-color: cornflowerblue;
}
.user_music_library{
    //background-color: aquamarine;
    position: absolute;
    bottom: 0;
}

.user_photo{
    height: 250px;
    /*内边框*/
    /*border: 1px solid black;*/
    /*padding: 10px;*/
    /*boxShadow: '0px 0px 0px 4px white, 0px 0px 0px 5px black';*/

    /*圆角边框*/
    /*border: 2px solid black;*/
    /*border-radius: 10px;*/
    /*padding: 10px;*/

    /*阴影边框*/
    padding: 10px;
    border-radius:50%;
    box-shadow: 0px 0px 5px 10px rgba(0,0,0,0.5);
}

.scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    width: 900px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-primary-light-9);
    color: var(--el-color-primary);
    padding-left: 30px;
    padding-right: 30px;
}

</style>