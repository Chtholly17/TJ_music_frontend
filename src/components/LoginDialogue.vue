<template>
    <el-dialog class="log_reg_dialog" v-model="LoginDialogVisible" title="登 录 账 号" width="30%">
        <div class="login-form" >
            <el-form ref="baseForm" :model="loginData.loginForm" :rules="loginRules">
                <el-form-item prop="username">
                    <el-input v-model="loginData.loginForm.username"
                              placeholder="学号"
                              prefix-icon="User"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginData.loginForm.password"
                              placeholder="密码"
                              prefix-icon="Lock"
                              show-password></el-input>
                </el-form-item>
            </el-form>
            <el-button class=LoginButton type="primary"
                       @click="commitLogin" round>登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
        </div>
        <el-row>
            <el-col :span="10">
                <el-button class="login_register_button"
                           @click="showRegisterDialog"
                           link>没有账号，去注册</el-button>
            </el-col>
            <el-col :span="4">
            </el-col>
            <el-col :span="10">
                <el-button class="login_register_button"
                           @click="showRetrieveDialog"
                           link>忘记密码</el-button>
            </el-col>
        </el-row>
    </el-dialog>
</template>

<script lang="ts">
import {User, Lock} from "@element-plus/icons-vue";
import {defineComponent} from "vue";
import {baseForm, loginData, loginRules, commitLogin} from "@/utils/Texts/loginText";
import {showRegisterDialog, showRetrieveDialog, LoginDialogVisible} from "@/utils/DialogVisible";

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
            commitLogin,
            LoginDialogVisible,
            showRegisterDialog,
            showRetrieveDialog
        }
    }
})
</script>

<style scoped>
.LoginButton{
    display: flex;
    flex-direction: row;
    height: 35px;
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
    width: 40%;
}
</style>
