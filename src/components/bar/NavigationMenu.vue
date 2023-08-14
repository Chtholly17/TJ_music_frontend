<template>
    <div class="header">
        <el-menu :router="true" mode="horizontal"  :default-active=defaultIndex class="top_bar">
            <div style="display: flex">
                <img src="../../assets/logo/logo_rec.png" class="logo">
                <el-menu-item index="/music_square">首页</el-menu-item>
                <el-menu-item index="/rank">榜单</el-menu-item>
            </div>
            <search-bar></search-bar>
            <el-dropdown trigger="hover" style="margin-right: 5%">
                <el-avatar :src="realImgUrl " :size="50" fit="cover" class="user_photo"></el-avatar>
<!--                <el-image :src=realImgUrl class="user_photo" />-->
                <template #dropdown>
                    <el-dropdown-menu >
                        <el-dropdown-item @click="userRouter" >个人主页</el-dropdown-item>
                        <el-dropdown-item @click="showUpdate">修改密码</el-dropdown-item>
                        <el-dropdown-item @click="userMessage">我的消息</el-dropdown-item>
                        <el-dropdown-item @click="userLogOut">登出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </el-menu>
    </div>
</template>

<script>
import {showLoginDialog} from "@/utils/DialogVisible";
import SearchBar from "@/components/bar/searchBar.vue";
import {computed, onBeforeMount, onBeforeUpdate, ref, watch} from "vue";
import store from "@/store";
<<<<<<< HEAD
import {showUpdatePassword} from "@/utils/DialogVisible";
import {user_fetchUserImage} from "@/utils/Texts/userinfoText";
=======
import {show_update_password} from "@/utils/DialogVisible";
import {userFetchUserImage} from "@/utils/Texts/userinfoText";
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
import api from "@/service";
import {delCookie} from "@/service/cookie";
import router from "@/router";
import {isLoggedIn} from "@/utils/BarVisible";

export default {
    name: "NavigationMenu",
    components: {SearchBar},
    methods:{
        //跳转到个人主页
<<<<<<< HEAD
        user_router(){
            const userId=computed(()=>store.getters.getUserID)
            if (userId.value== 'admin')
=======
        userRouter(){
            const userID =computed(()=>store.getters.getUserID)
            if (userID.value === 'admin')
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                router.push('/admin')
            else
                router.push('/user')
        },
        userMessage(){
            router.push({path:'/message',query:{target_id:''}})
        }
    },
    setup() {
        const userPhotoUrl=ref()   //用户头像
<<<<<<< HEAD
        const default_index=ref()
        const real_img_url=ref('')
=======
        const defaultIndex=ref()
        const realImgUrl=ref('')
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419

        watch(
            ()=>store.state.bar_pic_change,
            ()=>{
<<<<<<< HEAD
                const userId=computed(()=>store.getters.getUserID)
                user_fetchUserImage(userId.value).then(res=>{
                    userPhotoUrl.value=res
                    const random_num=Math.random()*100+1;
                    real_img_url.value=`${userPhotoUrl.value}?timestamp=${random_num}`;
=======
                const userID = computed(()=>store.getters.getUserID)
                userFetchUserImage(userID.value).then(res=>{
                    userPhotoUrl.value=res
                    const randomNumber=Math.random()*100+1;
                    realImgUrl.value=`${userPhotoUrl.value}?timestamp=${randomNumber}`;
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                })
            }
        )

        //const userId=ref()
        onBeforeMount(()=>{
            const rootPath = 'http://localhost:8080';   //用这个消去href的前一段
            const href = window.location.href;
<<<<<<< HEAD
            default_index.value=href.substring(root_path.length)
            const userId=computed(()=>store.getters.getUserID)
            if (default_index.value==='/hello')
                default_index.value='/music_square'

            user_fetchUserImage(userId.value).then(res=>{
                userPhotoUrl.value=res
                const random_num=Math.random()*100+1;
                real_img_url.value=`${userPhotoUrl.value}?timestamp=${random_num}`;
                // store.commit('setUserPhoto',real_img_url)
=======
            defaultIndex.value=href.substring(rootPath.length)
            const userID=computed(()=>store.getters.getUserID)
            if (defaultIndex.value==='/hello')
                defaultIndex.value='/music_square'

            userFetchUserImage(userID.value).then(res=>{
                userPhotoUrl.value=res
                const randomNumber=Math.random()*100+1;
                realImgUrl.value=`${userPhotoUrl.value}?timestamp=${randomNumber}`;
                // store.commit('setUserPhoto',realImgUrl)
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                // user_photo.value=store.getters.getUserPhoto

            })

            // console.log("bar初始渲染")
            // console.log(defaultIndex.value)

        })
        onBeforeUpdate(()=>{
            // console.log("bar更新")
        })
        function showUpdate()
        {
            showUpdatePassword.value=true
        }

<<<<<<< HEAD
        function user_logout(){
=======
        function userLogOut(){
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            const userId=computed(()=>store.getters.getUserID)
            api.userLogout(userId.value)
            delCookie("userNumber")
            delCookie("password")
            isLoggedIn.value=false;
            router.push('/')
        }
        return {
            showLoginDialog,
            userPhotoUrl: userPhotoUrl,
<<<<<<< HEAD
            show_update,
            default_index,
            user_logout,
            real_img_url,
=======
            showUpdate: showUpdate,
            defaultIndex: defaultIndex,
            userLogOut: userLogOut,
            realImgUrl: realImgUrl,
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419

        }
    }
}
</script>

<style lang="scss" scoped>
//@import "@/assets/css/var.scss";
//@import "@/assets/css/global.scss";
.user{
    float:right
}
.header{

}
.logo{
    //transform:scale(30%);
    height: 65px;
    text-align: center;
    margin-left: 20px;
}
.el-menu{
    //display: flex;
    //justify-content: center;
    height: 65px;
}
.user_photo{
    margin-top: 7px;
    margin-left: 15px;
    border-radius: 50%;
    height: 50px;
    width: 50px;
    align-items:center;
    cursor: pointer;
}
.top_bar{
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>
