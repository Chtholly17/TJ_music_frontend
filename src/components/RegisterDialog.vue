<template>
    <el-dialog class="log_reg_dialog" v-model="RegisterDialogVisible" title="注 册 账 号" width="30%" :lock-scroll="false">
        <div class="register-form">
            <el-form ref="baseForm" :model="registerData.registerForm"
                     :rules="registerRules" label-width="auto"
                     :hide-required-asterisk="true">
                <el-form-item label="学号" prop="userNumber">
                    <el-input v-model="registerData.registerForm.userNumber"></el-input>
                </el-form-item>
                <el-form-item label="验证码" prop="verificationCode">
                    <el-col :span="10">
                        <el-input v-model="registerData.registerForm.verificationCode"></el-input>
                    </el-col>
                    <el-col :span="12">
                        <el-button @click="sendRegisterVRCode(
                            {userNumber: registerData.registerForm.userNumber})">发送验证码</el-button>
                    </el-col>
                    <el-col :span="2">
                        <div class="tip_icon">
                            <el-tooltip content="向同济邮箱（学号@tongji.edu.cn）发送验证码">
                                <el-icon :size="15"><QuestionFilled /></el-icon>
                            </el-tooltip>
                        </div>
                    </el-col>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="registerData.registerForm.password" show-password></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPassword">
                    <el-input v-model="registerData.registerForm.checkPassword" show-password></el-input>
                </el-form-item>
            </el-form>
            <el-button class=AccountDialogButton type="primary"
                       @click="commitRegister" round>注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</el-button>
        </div>
        <el-button class="AccountChangeDialogButton"
                   @click="showLoginDialog"
                   link>已有账号，去登录</el-button>
    </el-dialog>
</template>

<script lang="ts">
import {QuestionFilled} from "@element-plus/icons";
import {registerData, registerRules, baseForm, sendRegisterVRCode, commitRegister} from "@/utils/Texts/registerText";
import {defineComponent} from "vue";
import {RegisterDialogVisible, showLoginDialog} from "@/utils/DialogVisible";

export default defineComponent({
    name: "RegisterForm",
    components: {QuestionFilled},
    setup() {
        return {
            registerData,
            registerRules,
            baseForm,
            commitRegister,
            RegisterDialogVisible,
            showLoginDialog,
            sendRegisterVRCode
        }
    }
})
</script>

<style>
.AccountDialogButton{
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    margin-left: auto;
    margin-right: auto;
    width: 40%;
    font-size: 15px !important;
    height: 40px !important;
}
.tip_icon{
    display: flex;
    align-items: center;
}
.AccountChangeDialogButton{
    margin-top: 20px;
    font-size: 10px !important;
}
</style>
