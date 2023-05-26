<template>
    <div class="header">
        <el-menu :router="true" mode="horizontal"  :default-active=default_index>
            <img src="@/assets/logo/logo_rec.png" class="logo">
            <el-menu-item index="/hello">登录</el-menu-item>
            <el-menu-item index="/music_square">首页</el-menu-item>
            <el-menu-item index="/rank">榜单</el-menu-item>
            <search-bar></search-bar>
            <el-dropdown trigger="hover">
                <el-image :src=user_photo_url class="user_photo" />
                <template #dropdown>
                    <el-dropdown-menu >
                        <el-dropdown-item @click="user_router" >个人主页</el-dropdown-item>
                        <el-dropdown-item @click="show_update">修改密码</el-dropdown-item>
                        <el-dropdown-item >登出</el-dropdown-item>
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
import {userinfoData} from "@/utils/Texts/userinfoText";
import store from "@/store";
import {show_update_password} from "@/utils/DialogVisible";
export default {
    name: "NavigationMenu",
    components: {SearchBar},
    methods:{
        //跳转到个人主页
        user_router(){
            this.$router.push('/user')
        }
    },
    setup() {
        const user_photo_url=ref()   //用户头像
        const default_index=ref()
        onBeforeMount(()=>{
            //这里其实不写也没关系，因为渲染完这个组件之后就会紧接着渲染下一�?
            //console.log("刷新bar")
            var root_path='http://localhost:8080'   //用这个消去href的前一段

            var href=window.location.href
            //console.log(href.substring(root_path.length))
            //console.log("渲染bar")
            default_index.value=href.substring(root_path.length)

            const user_photo=computed(() => store.getters.getUserPhoto)
            user_photo_url.value=user_photo.value;
        })
        function show_update()
        {
            //console.log(show_update_password.value)
            show_update_password.value=true
            //console.log(show_update_password.value)
        }
        return {
            showLoginDialog,
            user_photo_url,
            show_update,
            default_index
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
