<template>
    <div class="header">
        <el-menu :router="true" mode="horizontal"  :default-active=default_index>
            <img src="@/assets/logo/logo_rec.png" class="logo">
            <el-menu-item index="/music_square">首页</el-menu-item>
            <el-menu-item index="/rank">榜单</el-menu-item>
            <search-bar></search-bar>
            <el-dropdown trigger="hover">
                <el-image :src=user_photo_url class="user_photo" />
                <template #dropdown>
                    <el-dropdown-menu >
                        <el-dropdown-item @click="user_router" >个人主页</el-dropdown-item>
                        <el-dropdown-item @click="show_update">修改密码</el-dropdown-item>
                        <el-dropdown-item @click="user_message">我的消息</el-dropdown-item>
                        <el-dropdown-item @click="user_logout">登出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </el-menu>
    </div>
</template>

<script>
import {showLoginDialog} from "@/utils/DialogVisible";
import SearchBar from "@/components/searchBar.vue";
import {computed, onBeforeMount, ref} from "vue";
import store from "@/store";
import {show_update_password} from "@/utils/DialogVisible";
import {user_fetchUserImage} from "@/utils/Texts/userinfoText";
import api from "@/service";
import {delCookie} from "@/service/cookie";
import router from "@/router";
import path from "@/service/path";
export default {
    name: "NavigationMenu",
    components: {SearchBar},
    methods:{
        //跳转到个人主页
        user_router(){
            this.$router.push('/user')
        },
        user_message(){
            this.$router.push('/message')
        }
    },
    setup() {
        const user_photo_url=ref()   //用户头像
        const default_index=ref()
        //const user_id=ref()
        onBeforeMount(()=>{

            var root_path='http://localhost:8080'   //用这个消去href的前一段
            var href=window.location.href
            default_index.value=href.substring(root_path.length)
            const user_id=computed(()=>store.getters.getUserID)
            user_fetchUserImage(user_id.value).then(res=>{
                user_photo_url.value=res
            })

        })
        function show_update()
        {
            //console.log(show_update_password.value)
            show_update_password.value=true
            //console.log(show_update_password.value)
        }

        function user_logout(){
            const user_id=computed(()=>store.getters.getUserID)
            api.userLogout(user_id.value)
            delCookie("userNumber")
            delCookie("password")
            router.push('/')
        }
        return {
            showLoginDialog,
            user_photo_url,
            show_update,
            default_index,
            user_logout
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
    align-items: center;
    cursor: pointer;
}
</style>
