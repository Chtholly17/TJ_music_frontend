<template>
    <div class="common-layout" v-loading="loading">
        <el-container style="height: 100%">
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <div >
                        <el-avatar :src="realImgUrl " :size="280" fit="cover"></el-avatar>
                        <div style="height: 10px"></div>
                        <el-button type="primary" @click="showUpload=true">更换头像</el-button>
                    </div>
                    <div >
                        <div style="height: 30px"></div>
                        <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{user_nickname}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{userId}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{userInfoData.userinfoForm.new_signature}}</p>
                    </div>
                    <div >
                        <div style="height: 70px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            关注: {{ userFollow }}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            粉丝: {{userFans}}
                        </div>
                        <div style="height: 30px"></div>
                    </div>
                    <div style="float: right">
                        <div style="height: 10px"></div>
                        <el-button type="info" :icon="Edit" @click="showInfo">个人资料</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View" @click="followRouter">我的关注</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Search" @click="fanRouter">我的粉丝</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="Share" @click="musicRouter" >我的曲库</el-button>
                    </div>
                </div>
                <router-view @deFan="deFan" @deFollow="deFollow" class="child_page" v-if="showRouter"></router-view>
<!--                <SongList></SongList>-->
            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>

        <user-info-view @pass_nickname="getNickname" @pass_signature="getSignature" ></user-info-view>

        <el-dialog v-model="showUpload" title="修改头像">
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
import {userFetchUserImage,fetchUserInfo} from "@/utils/Texts/userinfoText";
import {getCookie} from "@/service/cookie";
import {user_nickname} from "@/utils/Texts/userinfoText";

export default {
    name: "UserManagementView",
    components: {
        UserInfoView,
        uploadPic
    },
   data(){
        return{
            aside_width:13,
            //userPhotoUrl:require("../../assets/profile.jpg"),
            user_info_show_control:0    //控制是否展示个人信息界面
        }
    },
    methods:{
        musicRouter(){
            this.$router.push('/user/musicLibrary')
        },
        fanRouter(){
            this.$router.push('/user/fanList')
        },
        followRouter(){
            this.$router.push('/user/followList')
        },
        getNickname(data){
            this.nickname=data;
        },
        getSignature(data){
            this.user_signature=data;
        }
    }
    ,
    setup(){
        const loading=ref(true);

        const userPhotoUrl=ref()   //用户头像
        const showRouter=ref(true)
        const showUpload=ref(false) //展示上传头像框

        const userFollow=ref(0)    //用户关注数
        const userFans=ref(0)  //用户粉丝数
        const userId=ref("")   //用户学号

        watch(userPhotoUrl,()=>{
            showUpload.value=false;
            })
        const reload=()=>{
            showRouter.value=false
            nextTick(()=>{
                showRouter.value=true
            })
        }
        provide('reload',reload)
        const store = useStore()
        const count = computed(() => store.getters.getUserID)
        const userInfoControl=ref(false)
        provide("userInfoShow",userInfoControl);

        const realImgUrl=ref('');



        watch(
            ()=>store.state.bar_pic_change,
            ()=>{
                //const userId=computed(()=>store.getters.getUserID)
                const userNumber = getCookie("userNumber")
                userFetchUserImage( userNumber).then(res=>{
                    userPhotoUrl.value=res
                    const random_num=Math.random()*100+1;
                    realImgUrl.value=`${userPhotoUrl.value}?timestamp=${random_num}`;
                })
            }
        )



        onBeforeMount(()=>{
            loading.value=true
            const userNumber=ref();
            userNumber.value = getCookie("userNumber")
            // 先获取用户学号
            userinfoData.userinfoForm.user_student_number = userNumber.value;
            userId.value=userNumber.value;

            setTimeout(()=>{
                fetchUserInfo().then(res=>{
                    userinfoData.userinfoForm.new_nickname=res.userNickname
                    userinfoData.userinfoForm.new_college=res.userCollege
                    userinfoData.userinfoForm.new_major=res.userMajor
                    userinfoData.userinfoForm.new_area1=res.userArea1
                    userinfoData.userinfoForm.new_area2=res.userArea2
                    userinfoData.userinfoForm.new_birthday=res.userBirthday
                    userinfoData.userinfoForm.new_gender=res.userGender
                    userinfoData.userinfoForm.new_signature=res.userSignature
                    user_nickname.value=res.userNickname;
                    userFollow.value=res.userFollowCnt;
                    userFans.value=res.userFansCnt;
                })

                userFetchUserImage( userNumber.value).then(res=>{
                    userPhotoUrl.value=res
                    const random_num=Math.random()*100+1;
                    realImgUrl.value= `${userPhotoUrl.value}?timestamp=${random_num}`;
                })
                loading.value=false
            },1000)

        })

        function deFollow(){
            userFollow.value--;
        }

        function deFan(){
            userFans.value--;
        }


        function showInfo(){
            userInfoControl.value=userInfoControl.value==true?false:true;
        }

        return{
            Search,
            Share,
            Upload,
            View,
            Edit,
            showInfo,
            userinfoData,
            onBeforeMount,
            showRouter,
            reload,userPhotoUrl,showUpload,
            realImgUrl,userFollow,userFans,userId,
            loading,deFollow: deFollow,deFan: deFan,user_nickname
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


</style>
