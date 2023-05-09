<template>
    <div class="common-layout">
        <el-container>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <el-row :gutter="20">
                        <el-col :span="8">
                           <img :src=user_photo_url class="user_photo" >
    <!--                        <el-image class="personal-img" fit="contain" :src=user_photo_url @click="dialogTableVisible = true" />-->
                            <div style="height: 10px"></div>
                            <el-button type="primary" @click="show_upload=true">更换头像</el-button>
                        </el-col>
                        <el-col :span="6">
                            <div style="height: 30px"></div>
                            <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{userinfoData.userinfoForm.new_nickname}}</p>
    <!--                        <p style="text-align: left"> 姓名: &nbsp; &nbsp;{{user_name}}</p>-->
                            <div style="height: 30px"></div>
                            <p style="text-align: left"> 学号: &nbsp; &nbsp;{{userinfoData.userinfoForm.user_student_number}}</p>
    <!--                        <p style="text-align: left"> 生日: &nbsp; &nbsp;{{user_brithday}}</p>-->
    <!--                        <p style="text-align: left"> 家乡: &nbsp; &nbsp;{{user_home}}</p>-->
                            <div style="height: 30px"></div>
                            <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{userinfoData.userinfoForm.new_signature}}</p>

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
                            <el-button type="primary" :icon="View" @click="follow_router">我的关注</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Search" @click="fan_router">我的粉丝</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Share" @click="music_router" >我的曲库</el-button>
                        </el-col>
                    </el-row>
                </div>

                <router-view class="child_page" v-if="show_router"></router-view>
<!--                <SongList></SongList>-->
            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>

        <user-info-view @pass_nickname="get_nickname" @pass_signature="get_signature" ></user-info-view>

        <el-dialog v-model="show_upload" title="修改头像">
            <upload-pic></upload-pic>
        </el-dialog>

    </div>
</template>

<script>
import { Edit, Search, Share, Upload,View } from '@element-plus/icons-vue';
import UserInfoView from "@/views/user/UserInfoView.vue";
import {computed, onBeforeMount, provide, ref, nextTick, watch} from "vue";
import { useStore } from 'vuex'
import {userinfoData} from "@/utils/Texts/userinfoText";
import uploadPic from "@/components/user/uploadPic.vue";

// import {ElMessage} from "element-plus";
export default {
    name: "UserManagementView",
    components: {
        UserInfoView,
        uploadPic
    },
   data(){
        return{
            aside_width:13,
            //user_photo_url:require("../../assets/profile.jpg"),
            nickname:"日本天皇",
            user_name:"孙笑川",
            user_brithday:"1945-8-15",
            user_home:"上海市",
            user_follow:20,
            user_fans:10,
            user_post:15,
            user_signature:"我是天皇，我最强！",
            user_info_show_control:0    //控制是否展示个人信息界面
        }
    },
    methods:{
        music_router(){
            this.$router.push('/user/music_library')
        },
        fan_router(){
            this.$router.push('/user/fan_list')
        },
        follow_router(){
            this.$router.push('/user/follow_list')
        },
        get_nickname(data){
            this.nickname=data;
        },
        get_signature(data){
            this.user_signature=data;
        }
    }
    ,
    setup(){
        const user_photo_url=ref()   //用户头像
        const show_router=ref(true)
        const show_upload=ref(false) //展示上传头像框
        watch(user_photo_url,()=>{
            show_upload.value=false;
            })
        const reload=()=>{
            show_router.value=false
            nextTick(()=>{
                show_router.value=true
            })
            //console.log("重新加载")
        }
        provide('reload',reload)
        const store = useStore()
        const count = computed(() => store.getters.getUserID)
        const user_info_control=ref(false)
        provide("user_info_show",user_info_control);
        onBeforeMount(()=>{
            //这里其实不写也没关系，因为渲染完这个组件之后就会紧接着渲染下一�?
            userinfoData.userinfoForm.user_student_number = count.value;
            const user_photo=computed(() => store.getters.getUserPhoto)
            user_photo_url.value=user_photo.value;
        })
        function show_info(){
            user_info_control.value=user_info_control.value==true?false:true;
        }

        return{
            Search,
            Share,
            Upload,
            View,
            Edit,
            show_info,
            userinfoData,
            onBeforeMount,
            show_router,
            reload,user_photo_url,show_upload
        }
    }
}
</script>

<style scoped>
.user_main{
    //background-color: cornflowerblue;
    //height: 90vh;
    //overflow-y: hidden;
    min-height: 100vh;
}
.user_aside{
    background-color: rgb(245,245,245);
}

.user_photo{
    height: 250px;
    /*内边�?*/
    /*border: 1px solid black;*/
    /*padding: 10px;*/
    /*boxShadow: '0px 0px 0px 4px white, 0px 0px 0px 5px black';*/

    /*圆角边框*/
    border: 2px solid white;
    border-radius: 50%;
    padding: 10px;
    height: 280px;
    width: 280px;

    /*阴影边框*/
    /*padding: 10px;*/
    /*border-radius:50%;*/
    /*box-shadow: 0px 0px 5px 10px rgba(0,0,0,0.5);*/
}
.common-layout{
    //overflow-y: auto;
    //background-image: url("../../assets/backgroud_test.jpg");
    overflow: auto;
    height: 100vh;
}

.child_page{
    //min-height: 800px;
}


</style>
