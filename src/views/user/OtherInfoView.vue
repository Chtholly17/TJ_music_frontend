<template>
    <div class="common-layout">
        <el-container style="height: 100%">
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <div >
                        <img :src=real_img_url class="user_photo" >
                    </div>
                    <div >
                        <div style="height: 30px"></div>
                        <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{userinfoData.userinfoForm.new_nickname}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{user_id}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{userinfoData.userinfoForm.new_signature}}</p>
                    </div>
                    <div >
                        <div style="height: 70px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            关注: {{ user_follow }}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            粉丝: {{user_fans}}
                        </div>
                        <div style="height: 30px"></div>
                    </div>
                    <div style="float: right">
                        <div style="height: 10px"></div>
                        <el-button type="info" :icon="Edit" @click="show_info" class="right_buttons">个人资料</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View" @click="follow_router" class="right_buttons">关注</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Search" @click="fan_router" class="right_buttons">取消关注</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Share" @click="music_router" class="right_buttons">发起会话</el-button>
                    </div>
                </div>
                <router-view class="child_page" v-if="show_router"></router-view>
                <!--                <SongList></SongList>-->
            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>
    </div>
</template>

<script>
import { Edit, Search, Share, Upload,View } from '@element-plus/icons-vue';
import UserInfoView from "@/views/user/UserInfoView.vue";
import {computed, onBeforeMount, provide, ref, nextTick, watch} from "vue";
import { useStore } from 'vuex'
import {userinfoData} from "@/utils/Texts/userinfoText";
import uploadPic from "@/components/user/uploadPic.vue";
import {user_fetchUserImage,fetchUserInfo} from "@/utils/Texts/userinfoText";
import {getCookie} from "@/service/cookie";


export default {
    name: "UserManagementView",
    components: {
    },
    data(){
        return{
            aside_width:13,
            //user_photo_url:require("../../assets/profile.jpg"),
            user_info_show_control:0    //控制是否展示个人信息界面
        }
    },
    methods:{
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
        const user_follow=ref(0)    //用户关注数
        const user_fans=ref(0)  //用户粉丝数
        const user_id=ref("")   //用户学号

        watch(user_photo_url,()=>{
            show_upload.value=false;
        })
        const reload=()=>{
            show_router.value=false
            nextTick(()=>{
                show_router.value=true
            })
        }
        provide('reload',reload)
        const store = useStore()
        const count = computed(() => store.getters.getUserID)
        const user_info_control=ref(false)
        provide("user_info_show",user_info_control);

        const real_img_url=ref('');



        watch(
            ()=>store.state.bar_pic_change,
            ()=>{
                //const user_id=computed(()=>store.getters.getUserID)
                const userNumber = getCookie("userNumber")
                user_fetchUserImage( userNumber).then(res=>{
                    user_photo_url.value=res
                    const random_num=Math.random()*100+1;
                    real_img_url.value=`${user_photo_url.value}?timestamp=${random_num}`;
                })
            }
        )

        onBeforeMount(()=>{
            const userNumber=ref();
            userNumber.value = getCookie("userNumber")
            // const user_id=computed(() => store.getters.getUserID)
            // 先获取用户学号
            userinfoData.userinfoForm.user_student_number = userNumber.value;
            user_id.value=userNumber.value;

            console.log("获取用户学号1")
            console.log( userinfoData.userinfoForm.user_student_number)

            fetchUserInfo().then(res=>{
                user_follow.value=res.userFollowCnt;
                user_fans.value=res.userFansCnt;

            })

            user_fetchUserImage( userNumber.value).then(res=>{
                user_photo_url.value=res
                const random_num=Math.random()*100+1;
                real_img_url.value= `${user_photo_url.value}?timestamp=${random_num}`;
            })
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
            reload,user_photo_url,show_upload,
            real_img_url,user_follow,user_fans,user_id
        }
    }
}
</script>

<style scoped>
.user_main{
    height: 100%;
}
.user_aside{
    background-color: rgb(245,245,245);
    height: 100%;
}

.user_photo{
    height: 250px;
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
    overflow: auto;
    min-height: 90vh;
}

.child_page{

}
.user_top{
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;

}

.right_buttons{
    width: 100%;
}


</style>
