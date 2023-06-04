<template>
    <div class="header">
        <el-menu :router="true" mode="horizontal"  :default-active=default_index class="top_bar">
            <div style="display: flex">
                <img src="@/assets/logo/logo_rec.png" class="logo">
                <el-menu-item index="/music_square">首页</el-menu-item>
                <el-menu-item index="/rank">榜单</el-menu-item>
            </div>
            <search-bar></search-bar>
            <el-dropdown trigger="hover" style="margin-right: 5%">
                <el-avatar :src="real_img_url " :size="50" fit="cover" class="user_photo"></el-avatar>
<!--                <el-image :src=real_img_url class="user_photo" />-->
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
import {computed, onBeforeMount, onBeforeUpdate, ref, watch} from "vue";
import store from "@/store";
import {show_update_password} from "@/utils/DialogVisible";
import {user_fetchUserImage} from "@/utils/Texts/userinfoText";
import api from "@/service";
import {delCookie} from "@/service/cookie";
import router from "@/router";
import {isLoggedIn} from "@/utils/BarVisible";

export default {
    name: "NavigationMenu",
    components: {SearchBar},
    methods:{
        //跳转到个人主页
        user_router(){
            router.push('/user')
        },
        user_message(){
            router.push({path:'/message',query:{target_id:''}})
        }
    },
    setup() {
        const user_photo_url=ref()   //用户头像
        const default_index=ref()
        const real_img_url=ref('')

        watch(
            ()=>store.state.bar_pic_change,
            ()=>{
                const user_id=computed(()=>store.getters.getUserID)
                user_fetchUserImage(user_id.value).then(res=>{
                    user_photo_url.value=res
                    const random_num=Math.random()*100+1;
                    real_img_url.value=`${user_photo_url.value}?timestamp=${random_num}`;
                })
            }
        )

        //const user_id=ref()
        onBeforeMount(()=>{

            const root_path = 'http://localhost:8080';   //用这个消去href的前一段
            const href = window.location.href;
            default_index.value=href.substring(root_path.length)
            const user_id=computed(()=>store.getters.getUserID)
            if (default_index.value==='/hello')
                default_index.value='/music_square'

            user_fetchUserImage(user_id.value).then(res=>{
                user_photo_url.value=res
                const random_num=Math.random()*100+1;
                real_img_url.value=`${user_photo_url.value}?timestamp=${random_num}`;
                // store.commit('setUserPhoto',real_img_url)
                // user_photo.value=store.getters.getUserPhoto

            })

            // console.log("bar初始渲染")
            // console.log(default_index.value)

        })
        onBeforeUpdate(()=>{
            // console.log("bar更新")
        })
        function show_update()
        {
            show_update_password.value=true
        }

        function user_logout(){
            const user_id=computed(()=>store.getters.getUserID)
            api.userLogout(user_id.value)
            delCookie("userNumber")
            delCookie("password")
            isLoggedIn.value=false;
            router.push('/')
        }
        return {
            showLoginDialog,
            user_photo_url,
            show_update,
            default_index,
            user_logout,
            real_img_url,

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
