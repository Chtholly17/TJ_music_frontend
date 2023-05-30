<template>
    <div id="app">
        <el-affix v-if="visible">
            <navigation-menu></navigation-menu>
        </el-affix>
        <router-view></router-view>
    </div>
    <el-dialog v-model="show_update_password" title="修改密码" width="30%" :lock-scroll="false">
        <update-password></update-password>
    </el-dialog>
</template>

<style lang="scss">
#app {
    font-family: sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;

    overflow: auto;
    height: 100%
}

//.el-button--primary:focus{
//    background: #CC2288 !important;
//    border-color: #CC2288 !important;
//    color: #ffffff !important;
//}
//.el-button--primary:hover{
//    background: #c965a2 !important;
//    border-color: #c965a2 !important;
//    color: #ffffff !important;
//}
//.el-button--primary{
//    background: #CC2288 !important;
//    border-color: #CC2288 !important;
//    color: #ffffff !important;
//}

*{
    font-family: "微软雅黑 Light", sans-serif;
}
</style>

<script setup lang="ts">
import NavigationMenu from "@/components/NavigationMenu.vue";
import UpdatePassword from "@/components/user/UpdatePassword.vue";
import {show_update_password} from "@/utils/DialogVisible";
import {onBeforeMount} from "vue";
import {visible} from "@/utils/BarVisible";
import {getCookie} from "@/service/cookie";
import {loginData, commitLogin_cookie} from "@/utils/Texts/loginText";

onBeforeMount(() => {
    // 05.26 work list：
    // 1. 首先读取store中是否有userID，若没有，先返回登录页面
    // 2. 在登录页面，beforeMounted时读取cookie，如果有cookie则用其登录
    // 3. 每次登出，需要清除cookie与store内容，并且发送登出请求
    // 4. 登陆成功后，预读取用户头像与ID（ID暂时不需要）

    // 在输入路由时，会重新渲染页面，因此会触发这个函数
    const userNumber = getCookie("userNumber")
    const password = getCookie("password")
    console.log(userNumber)
    console.log(password)
    if(userNumber != false && password != false) {
        loginData.loginForm.userNumber = userNumber
        loginData.loginForm.password = password
        commitLogin_cookie()
    }
})

</script>
