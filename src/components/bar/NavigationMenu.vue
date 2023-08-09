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
import {show_update_password} from "@/utils/DialogVisible";
import {userFetchUserImage} from "@/utils/Texts/userinfoText";
import api from "@/service";
import {delCookie} from "@/service/cookie";
import router from "@/router";
import {isLoggedIn} from "@/utils/BarVisible";

export default {
    name: "NavigationMenu",
    components: {SearchBar},
    methods:{
        //跳转到个人主页
        userRouter(){
            const userID =computed(()=>store.getters.getUserID)
            if (userID.value === 'admin')
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
        const defaultIndex=ref()
        const realImgUrl=ref('')

        watch(
            ()=>store.state.bar_pic_change,
            ()=>{
                const userID = computed(()=>store.getters.getUserID)
                userFetchUserImage(userID.value).then(res=>{
                    userPhotoUrl.value=res
                    const randomNumber=Math.random()*100+1;
                    realImgUrl.value=`${userPhotoUrl.value}?timestamp=${randomNumber}`;
                })
            }
        )

        //const userId=ref()
        onBeforeMount(()=>{
            const rootPath = 'http://localhost:8080';   //用这个消去href的前一段
            const href = window.location.href;
            defaultIndex.value=href.substring(rootPath.length)
            const userID=computed(()=>store.getters.getUserID)
            if (defaultIndex.value==='/hello')
                defaultIndex.value='/music_square'

            userFetchUserImage(userID.value).then(res=>{
                userPhotoUrl.value=res
                const randomNumber=Math.random()*100+1;
                realImgUrl.value=`${userPhotoUrl.value}?timestamp=${randomNumber}`;
                // store.commit('setUserPhoto',realImgUrl)
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
            show_update_password.value=true
        }

        function userLogOut(){
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
            showUpdate: showUpdate,
            defaultIndex: defaultIndex,
            userLogOut: userLogOut,
            realImgUrl: realImgUrl,

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
