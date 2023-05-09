<template>
    <div class="retrieve-form">
        <el-form ref="baseForm" :model="retrieveData.retrieveForm"
                 :rules="retrieveRules" label-width="auto"
                 :hide-required-asterisk="true">
            <el-form-item prop="userNumber">
                <el-input v-model="retrieveData.retrieveForm.userNumber"
                          placeholder="学号"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="verificationCode">
                <el-col :span="12">
                    <el-input v-model="retrieveData.retrieveForm.verificationCode"
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
                            <el-icon :size="15"><QuestionFilled /></el-icon>
                        </el-tooltip>
                    </div>
                </el-col>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="retrieveData.retrieveForm.password"
                          placeholder="新密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
            <el-form-item prop="checkPassword">
                <el-input v-model="retrieveData.retrieveForm.checkPassword"
                          placeholder="确认新密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=AccountDialogButton type="primary"
                   @click="commitRetrieve" round>找&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</el-button>
    </div>
    <div>
        <el-button class="AccountChangeDialogButton"
                   @click="showLoginDialog"
                   link>去登录</el-button>
    </div>
</template>

<script lang="ts">
import { QuestionFilled } from "@element-plus/icons";
import {retrieveData, commitRetrieve, sendRetrieveVRCode, baseForm} from "@/utils/Texts/retrieveText";
import {retrieveRules} from "@/utils/Texts/retrieveText";
import {RetrieveDialogVisible, showLoginDialog} from "@/utils/DialogVisible";
import {ElMessage} from "element-plus";
import {ref} from "vue";
import {Lock, Message, User} from "@element-plus/icons-vue";

export default {
    name: "RetrieveForm",
    components: {QuestionFilled},
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
            const result = await sendRetrieveVRCode({userNumber: retrieveData.retrieveForm.userNumber})
            // console.log('result: ' + result)
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
            retrieveData,
            retrieveRules,
            baseForm,
            commitRetrieve,
            RetrieveDialogVisible,
            showLoginDialog,
            getVRCodeHandler,
            sendVRCodeButtonMessage,
            sendVRCodeButtonDisabled
        }
    }
} // TODO：固定验证码按钮宽度
</script>

<style src="@/assets/CSS/preface.scss" lang="scss" scoped>
</style>
