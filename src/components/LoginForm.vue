<template>
    <div class="login-form" >
        <el-form ref="baseForm" :model="loginData.loginForm" :rules="loginRules">
            <el-form-item prop="username">
                <el-input v-model="loginData.loginForm.username"
                          placeholder="用户名 / 邮箱地址"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginData.loginForm.password"
                          placeholder="密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=LoginButton type="primary" @click="commitLogin"> 登 录 </el-button>
    </div>
</template>

<script lang="ts">
import {User, Lock} from "@element-plus/icons-vue";
import {defineComponent} from "vue";
import {baseForm, loginData, loginRules, commitLogin} from "@/utils/loginText";

export default defineComponent({
    name: "LoginForm",
    setup(){
        return {
            User,
            Lock,
            // loginForm,
            loginData,
            loginRules,
            baseForm,
            commitLogin
        }
    }
})
</script>

<style scoped>
.LoginButton{
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    margin-left: 35%;
    margin-right: 35%;
    width: 30%;
}
</style>
