<template>
    <div>
        <el-form ref="baseForm" :model="registerData.registerForm" :rules="registerRules"
                 label-width="auto" :hide-required-asterisk="true">
            <el-form-item prop="userNumber">
                <el-input v-model="registerData.registerForm.userNumber"
                          placeholder="学号"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="verificationCode">
                <el-col :span="12">
                    <el-input v-model="registerData.registerForm.verificationCode"
                              prefix-icon="Message"
                              placeholder="验证码"></el-input>
                </el-col>
                <el-col :span="10">
                    <el-button class="VRCodeButton" :disabled="sendVRCodeButtonDisabled"
                               @click="getVRCodeHandler">{{sendVRCodeButtonMessage}}</el-button>
                </el-col>
                <el-col :span="2">
                    <div class="tip_icon">
                        <el-tooltip content="向同济邮箱（学号@tongji.edu.cn）发送验证码">
                            <el-icon :size="20">
                                <icon-question-circle :stroke-width="2" style="color: #505050"/>
                            </el-icon>
                        </el-tooltip>
                    </div>
                </el-col>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="registerData.registerForm.password"
                          placeholder="密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
            <el-form-item prop="checkPassword">
                <el-input v-model="registerData.registerForm.checkPassword"
                          placeholder="确认密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=AccountDialogButton type="primary"
                   @click="commitRegister" round>注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</el-button>
    </div>
    <div>
        <el-button class="AccountChangeDialogButton"
                   @click="showLoginDialog"
                   link>已有账号，去登录</el-button>
    </div>
</template>

<script lang="ts">
import {registerData, baseForm, sendRegisterVRCode, commitRegister} from "@/utils/Texts/registerText";
import {registerRules} from "@/utils/Texts/registerText";
import {defineComponent, ref} from "vue";
import {registerDialogVisible, showLoginDialog} from "@/utils/DialogVisible";
import {ElMessage} from "element-plus";
import {User, Lock, Message} from "@element-plus/icons-vue";

export default defineComponent({
    name: "RegisterForm",
    setup() {
        let sendVRCodeButtonCount = 0;
        const sendVRCodeButtonMessage = ref("获取验证码"); // 必须要ref才能实现按钮的更改！！
        const sendVRCodeButtonDisabled = ref(false);
        let sendVRCodeButtonTimer: any = null;
        const getVRCodeHandler = async () => { // 从Promise中获取数据的方法：添加await关键字，代价是所在的函数需要是async
            const TIME_COUNT = 60;
            if (sendVRCodeButtonCount > 0) {
                ElMessage.error("请在" + sendVRCodeButtonCount + "s后再发送验证码")
                return
            }
            const result = await sendRegisterVRCode({userNumber: registerData.registerForm.userNumber})
            if (result)
            {
                if (sendVRCodeButtonTimer == null) {
                    sendVRCodeButtonCount = TIME_COUNT;
                    sendVRCodeButtonDisabled.value = true;
                    sendVRCodeButtonTimer = setInterval(() => {
                        if (sendVRCodeButtonCount > 0 && sendVRCodeButtonCount <= TIME_COUNT) {
                            sendVRCodeButtonCount--;
                            sendVRCodeButtonMessage.value = sendVRCodeButtonCount + "s后重试";
                        } else {
                            sendVRCodeButtonDisabled.value = false;
                            sendVRCodeButtonMessage.value = "获取验证码"
                            clearInterval(sendVRCodeButtonTimer); // 清除定时器
                            sendVRCodeButtonTimer = null;
                        }
                    }, 1000) // 延时1000ms，每一轮执行一次setInterval函数体内容
                }
            }
        }
        return {
            User,
            Lock,
            Message,
            registerData,
            registerRules,
            baseForm,
            commitRegister,
            RegisterDialogVisible: registerDialogVisible,
            showLoginDialog,
            sendRegisterVRCode,
            getVRCodeHandler,
            sendVRCodeButtonMessage,
            sendVRCodeButtonDisabled
        }
    }
})
</script>

<style src="@/assets/CSS/preface.scss" lang="scss" scoped>
</style>
