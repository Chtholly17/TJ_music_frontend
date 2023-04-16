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
<!--                        <p style="text-align: left"> 姓名: &nbsp; &nbsp;{{user_name}}</p>-->
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{user_student_num}}</p>
<!--                        <p style="text-align: left"> 生日: &nbsp; &nbsp;{{user_brithday}}</p>-->
<!--                        <p style="text-align: left"> 家乡: &nbsp; &nbsp;{{user_home}}</p>-->
                        <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{user_signature}}</p>

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
                        <el-button type="info" :icon="Edit" @click="show_info">个人资料</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View">我的关注</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Search">我的粉丝</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Share" @click="post_router" >我的动态</el-button>
                    </el-col>
                </el-row>

                <router-view class="child_page"></router-view>

            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>

        <user-info-view></user-info-view>

    </div>
</template>

<script>
import { Delete, Edit, Search, Share, Upload,View } from '@element-plus/icons-vue';
import UserInfoView from "@/views/user/UserInfoView.vue";
import {provide, ref} from "vue";
export default {
    name: "UserManagementView",
    components: {UserInfoView},
    //components: {UserInfoView},
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
            user_signature:"我是天皇，我最强！",
            // user_info_show_control:0    //控制是否展示个人信息界面
        }
    },
    methods:{
        post_router(){
            this.$router.push('/user/post')
        },

    }
    ,
    setup(){
        const user_info_control=ref(false)
        provide("user_info_show",user_info_control);
        function show_info(){
            user_info_control.value=user_info_control.value==true?false:true;

        }
        return{
            Search,
            Share,
            Upload,
            View,
            Edit,
            show_info
        }
    }
}
</script>

<style scoped>
.user_main{
    //background-color: cornflowerblue;
    //height: 90vh;
    min-height: 740px;
}
.user_aside{
    background-color: rgb(245,245,245);
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
.common-layout{
    overflow-y: auto;
    //background-image: url("../../assets/backgroud_test.jpg");
}

.child_page{
    //min-height: 800px;
}


</style>
