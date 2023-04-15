<template>
    <el-menu :router="true" mode="horizontal">
        <el-menu-item @click="LoginFormVisible=true; RegisterFormVisible=false">登录 / 注册</el-menu-item>
        <el-sub-menu>
            <template #title>我的</template>
            <el-menu-item index="/">个人主页</el-menu-item>
        </el-sub-menu>
    </el-menu>
    <router-view></router-view>

    <el-dialog class="log_reg_dialog" v-model="RegisterFormVisible" title="注 册 账 号" width="30%">
        <register-form> </register-form>
        <el-button class="login_register_button"
                   @click="LoginFormVisible=true; RegisterFormVisible=false"
                   text>已有账号，去登录</el-button>
    </el-dialog>
    <el-dialog class="log_reg_dialog" v-model="LoginFormVisible" title="登 录 账 号" width="30%">
        <login-form> </login-form>
        <el-button class="login_register_button"
                   @click="LoginFormVisible=false; RegisterFormVisible=true"
                   text>没有账号，去注册</el-button>
    </el-dialog>
</template>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
}
.login_register_button{
    margin-top: 10px;
}
.log_reg_dialog{
    padding-left: 1em;
    padding-right: 1em;
    padding-bottom: 0;
}
</style>

<script setup lang="ts">
import { ref } from 'vue'
import RegisterForm from "@/components/RegisterForm.vue";
import LoginForm from "@/components/LoginForm.vue";
const RegisterFormVisible = ref(false)
const LoginFormVisible = ref(false)
</script>
