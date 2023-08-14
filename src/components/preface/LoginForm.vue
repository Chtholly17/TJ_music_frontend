<template>
    <div>
        <el-form ref="baseForm" :model="loginData.loginForm" :rules="loginRules">
            <el-form-item prop="userNumber">
                <el-input v-model="loginData.loginForm.userNumber"
                          placeholder="学号" class="preface_input"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginData.loginForm.password"
                          placeholder="密码" class="preface_input"
                          prefix-icon="Lock" @keyup.enter="commitLogin()"
                          show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=AccountDialogButton type="primary"
                   @click="commitLogin" round :disabled="loginDisabled">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
    </div>
    <el-row>
        <el-col :span="10">
            <el-button class="AccountChangeDialogButton"
                       @click="showRegisterDialog"
                       link>没有账号，去注册</el-button>
        </el-col>
        <el-col :span="4">
        </el-col>
        <el-col :span="10">
            <el-button class="AccountChangeDialogButton"
                       @click="showRetrieveDialog"
                       link>忘记密码</el-button>
        </el-col>
    </el-row>
</template>

<script lang="ts">
import {User, Lock} from "@element-plus/icons-vue";
import {defineComponent, ref} from "vue";
import {baseForm, loginData, loginRules, commitLogin} from "@/utils/Texts/loginText";
import {showRegisterDialog, showRetrieveDialog, loginDialogVisible} from "@/utils/DialogVisible";

export default defineComponent({
    name: "LoginForm",
    setup(){
        const loginDisabled = ref(false);
        return {
            User,
            Lock,
            // loginForm,
            loginDisabled,
            loginData,
            loginRules,
            baseForm,
            commitLogin,
            LoginDialogVisible: loginDialogVisible,
            showRegisterDialog,
            showRetrieveDialog
        }
    }
})
</script>

<style src="@/assets/CSS/preface.scss" lang="scss" scoped>
</style>
