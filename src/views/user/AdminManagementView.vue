<template>
    <div class="common-layout" v-disable-shortcut>
        <el-container style="height: 100%">
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
                           <p style="text-align: left;font-size=20px;">Admin</p>

                        </el-col>
                        <el-col :span="7">
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="View" @click="orign_music_router">曲库信息</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Search" @click="user_inform_router">用户信息</el-button>
                            <div style="height: 30px"></div>
                            <el-button type="primary" :icon="Share" @click="user_list_router" >申诉信息</el-button>
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

        <el-dialog v-model="show_edit_music" title="修改音乐信息">
            <edit-music
            :originId="music.originId"
            :originName="music.originName"
            :originAuthor="music.originAuthor"
            :originBgmusicFilename="music.originBgmusicFilename"
            :originVoiceFilename="music.originVoiceFilename"
            :originPrefaceFilename="music.originPrefaceFilename"
            :originDescription="music.originDescription"
            :key="show_edit_music" 
            ></edit-music>
        </el-dialog>

        <el-dialog v-model="show_music_introduction" title="音乐介绍">
            <music-introduction
            :originName="music.originName"
            :originAuthor="music.originAuthor"
            :originPrefaceFilename="music.originPrefaceFilename"
            :originDescription="music.originDescription"
            :key="show_music_introduction"
            ></music-introduction>
        </el-dialog>

        <el-dialog v-model="show_post_user" title="添加用户">
            <post-user
            :originName="user.username"
            key="show_post_user"
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
import {user_fetchUserImage,fetchUserInfo} from "@/utils/Texts/userinfoText";
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
        orign_music_router(){
            this.$router.push({path:'/admin/orign_music'})
        },
        user_inform_router(){
            this.$router.push({path:'/admin/manager'})
        },
        user_list_router(){
            this.$router.push({path:'/admin/inform'})
        },
        get_nickname(data){
            this.nickname=data;
        },
        get_signature(data){
            this.user_signature=data;
        }
    },
    setup(){
        const { proxy } = getCurrentInstance()
        const user_photo_url=ref()   //用户头像
        const show_router=ref(true)
        let show_upload=ref(false)
        let show_edit_music=ref(false)
        let show_music_introduction=ref(false)
        let show_post_user=ref(false)
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
                console.log(music.value);
            }).catch((err) => {
                console.log(err);
            })
        }
        async function editMusic(data){
            await getMusic(data);
            show_edit_music.value=true;
        }
        async function showMusicIntroduction(data){
            await getMusic(data);
            show_music_introduction.value=true;
        }
        async function showPostUser(data){
            user.value.username = data.userStudentNumber;
            show_post_user.value=true;
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
            // 先获取用户学号
            userinfoData.userinfoForm.user_student_number = userNumber.value;

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
            user_photo_url,
            show_upload,
            show_edit_music,
            show_music_introduction,
            show_post_user,
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
