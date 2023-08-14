<template>
    <div class="common-layout" v-disable-shortcut>
        <el-container style="height: 100%">
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <el-row :gutter="20">
                        <el-col :span="8">
                           <img :src=userPhotoUrl class="user_photo" >
    <!--                        <el-image class="personal-img" fit="contain" :src=userPhotoUrl @click="dialogTableVisible = true" />-->
                            <div style="height: 10px"></div>
                            <el-button type="primary" @click="showUpload=true">更换头像</el-button>
                        </el-col>
                        <el-col :span="6">
                            <div style="height: 30px"></div>
<<<<<<< HEAD
                            <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{ userinfoData.userinfoForm.newNickname }}</p>
                           <p style="text-align: left;font-size=20px;">Admin</p>
=======
                            <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{userInfoData.userinfoForm.new_nickname}}</p>
                           <p style="text-align: left;font-size:20px;">Admin</p>
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419

                        </el-col>
                        <el-col :span="7">
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="View" @click="orignMusicRouter">曲库信息</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Search" @click="userInformRouter">用户信息</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Share" @click="userListRouter" >申诉信息</el-button>
                        </el-col>
                    </el-row>
                </div>

                <router-view class="child_page" v-if="showRouter"></router-view>
<!--                <SongList></SongList>-->
            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>

        <user-info-view @pass_nickname="getNickname" @pass_signature="getSignature" ></user-info-view>

        <el-dialog v-model="showUpload" title="修改头像">
            <upload-pic></upload-pic>
        </el-dialog>

        <el-dialog v-model="showEditMusic" title="修改音乐信息">
            <edit-music
            :originId="music.originId"
            :originName="music.originName"
            :originAuthor="music.originAuthor"
            :originBgmusicFilename="music.originBgmusicFilename"
            :originVoiceFilename="music.originVoiceFilename"
            :originPrefaceFilename="music.originPrefaceFilename"
            :originDescription="music.originDescription"
<<<<<<< HEAD
            :key="show_edit_music"
=======
            :key="showEditMusic"
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            ></edit-music>
        </el-dialog>

        <el-dialog v-model="showMusicIntro" title="音乐介绍">
            <music-introduction
            :originName="music.originName"
            :originAuthor="music.originAuthor"
            :originPrefaceFilename="music.originPrefaceFilename"
            :originDescription="music.originDescription"
            :key="showMusicIntro"
            ></music-introduction>
        </el-dialog>

        <el-dialog v-model="showPostUserFlag" title="添加用户">
            <post-user
            :originName="user.username"
            key="showPostUserFlag"
            ></post-user>
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
import postUser from "@/components/user/postUser.vue";
import editMusic from "@/components/user/editMusic.vue";
import musicIntroduction from "@/components/user/musicIntroduction.vue";
import { getCurrentInstance } from 'vue'
import axios from "axios";
import path from "@/service/path";
import {userFetchUserImage,fetchUserInfo} from "@/utils/Texts/userinfoText";
import {getCookie} from "@/service/cookie";

// import {ElMessage} from "element-plus";
export default{
    name: "UserManagementView",
    components: {
        UserInfoView,
        uploadPic,
        editMusic,
        musicIntroduction,
        postUser,
    },
    data(){
        return{
            nickname:"管理员",
        }
    },
    methods:{
        orignMusicRouter(){
            this.$router.push({path:'/admin/orign_music'})
        },
        userInformRouter(){
            this.$router.push({path:'/admin/manager'})
        },
        userListRouter(){
            this.$router.push({path:'/admin/inform'})
        },
        getNickname(data){
            this.nickname=data;
        },
        getSignature(data){
            this.user_signature=data;
        }
    },
    setup(){
        const { proxy } = getCurrentInstance()
        const userPhotoUrl=ref()   //用户头像
<<<<<<< HEAD
        const show_router=ref(true)
        let show_upload=ref(false)
        let show_edit_music=ref(false)
        let show_music_introduction=ref(false)
        let show_post_user=ref(false)
=======
        const showRouter=ref(true)
        let showUpload=ref(false)
        let showEditMusic=ref(false)
        let showMusicIntro=ref(false)
        let showPostUserFlag=ref(false)
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
        let user = ref({
            username: "",
        });
        let music=ref({
            originId:"",
            originName:"",
            originAuthor:"",
            originBgmusicFilename:"",
            originVoiceFilename:"",
            originPrefaceFilename:"",
            originDescription:"",
        });

        async function getMusic(data) {
            music.value.originId=data.originId;
            music.value.originName="";
            music.value.originAuthor="";
            music.value.originBgmusicFilename="";
            music.value.originVoiceFilename="";
            music.value.originPrefaceFilename="";
            music.value.originDescription="";
            if(data.originId=="-1"){
                return;
            }
            let form = new FormData();
            form.append("originId", data.originId);
            await axios.post(path.baseUrl + path.getOriginMusicById, form).then((res) => {
                music.value.originName=res.data.data.originName;
                music.value.originAuthor=res.data.data.originAuthor;
                music.value.originBgmusicFilename=res.data.data.originBgmusicFilename;
                music.value.originVoiceFilename=res.data.data.originVoiceFilename;
                music.value.originPrefaceFilename=res.data.data.originPrefaceFilename;
                music.value.originDescription=res.data.data.originIntroduction;
                //console.log(music.value);
            }).catch((err) => {
                console.log(err);
            })
        }
        async function editMusic(data){
            await getMusic(data);
            showEditMusic.value=true;
        }
        async function showMusicIntroduction(data){
            await getMusic(data);
            showMusicIntro.value=true;
        }
        async function showPostUser(data){
            user.value.username = data.userStudentNumber;
            showPostUserFlag.value=true;
        }
        proxy.$EventBus.on('showDetails', data => {
            showMusicIntroduction(data);
        });

        proxy.$EventBus.on('postUser', data => {
            showPostUser(data);
        });

        proxy.$EventBus.on('editMusic', data => {
            editMusic(data);
        });

        watch(userPhotoUrl,()=>{
<<<<<<< HEAD
            show_upload.value=false;
=======
            showUpload.value=false;
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
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
<<<<<<< HEAD
                user_fetchUserImage( userNumber).then(res=>{
                    userPhotoUrl.value=res
                    const random_num=Math.random()*100+1;
                    real_img_url.value=`${userPhotoUrl.value}?timestamp=${random_num}`;
=======
                userFetchUserImage( userNumber).then(res=>{
                    userPhotoUrl.value=res
                    const random_num=Math.random()*100+1;
                    realImgUrl.value=`${userPhotoUrl.value}?timestamp=${random_num}`;
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                })
            }
        )

        onBeforeMount(()=>{
            const userNumber=ref();
            userNumber.value = getCookie("userNumber")
            // 先获取用户学号
            userinfoData.userinfoForm.userStudentNumber = userNumber.value;

<<<<<<< HEAD
            user_fetchUserImage( userNumber.value).then(res=>{
                userPhotoUrl.value=res
                const random_num=Math.random()*100+1;
                real_img_url.value= `${userPhotoUrl.value}?timestamp=${random_num}`;
=======
            userFetchUserImage( userNumber.value).then(res=>{
                userPhotoUrl.value=res
                const random_num=Math.random()*100+1;
                realImgUrl.value= `${userPhotoUrl.value}?timestamp=${random_num}`;
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            })
        })


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
<<<<<<< HEAD
            show_router,
            userPhotoUrl,
            show_upload,
            show_edit_music,
            show_music_introduction,
            show_post_user,
=======
            showRouter: showRouter,
            userPhotoUrl,
            showUpload: showUpload,
            showEditMusic: showEditMusic,
            showMusicIntro: showMusicIntro,
            showPostUserFlag: showPostUserFlag,
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            music,
            user,
        }
    }
}
</script>

<style scoped>
.user_aside{
    background-color: rgb(245,245,245);
    height: 100%;
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
    overflow: auto;
    min-height: 90vh;
}
</style>
