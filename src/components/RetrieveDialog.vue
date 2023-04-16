<template>
    <el-dialog class="log_reg_dialog" v-model="RetrieveDialogVisible" title="找 回 账 号" width="30%">
        <div class="retrieve-form">
            <el-form ref="baseForm" :model="retrieveData.retrieveForm"
                     :rules="retrieveRules" label-width="auto"
                     :hide-required-asterisk="true">
                <el-form-item label="学号" prop="username">
                    <el-input v-model="retrieveData.retrieveForm.username"></el-input>
                </el-form-item>
                <el-form-item label="验证码" prop="verification_code">
                    <el-col :span="10">
                        <el-input v-model="retrieveData.retrieveForm.verificationCode"></el-input>
                    </el-col>
                    <el-col :span="12">
                        <el-button>发送验证码</el-button>
                    </el-col>
                    <el-col :span="2">
                        <div class="tip_icon">
                            <el-tooltip content="向同济邮箱（学号@tongji.edu.cn）发送验证码">
                                <el-icon :size="15"><QuestionFilled /></el-icon>
                            </el-tooltip>
                        </div>
                    </el-col>
                </el-form-item>
                <el-form-item label="新密码" prop="password">
                    <el-input v-model="retrieveData.retrieveForm.password" show-password></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="check_pass">
                    <el-input v-model="retrieveData.retrieveForm.checkPassword" show-password></el-input>
                </el-form-item>
            </el-form>
            <el-button class=retrieveButton type="primary"
                       @click="commitRetrieve" round>找&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</el-button>
        </div>
        <el-button class="login_register_button"
                   @click="showLoginDialog"
                   link>去登录</el-button>
    </el-dialog>
</template>

<script lang="ts">
import { QuestionFilled } from "@element-plus/icons";
import {retrieveData, retrieveRules, commitRetrieve, baseForm} from "@/utils/Texts/retrieveText";
import {RetrieveDialogVisible, showLoginDialog} from "@/utils/DialogVisible";

export default {
    name: "RetrieveForm",
    components: {QuestionFilled},
    setup() {
        return {
            retrieveData,
            retrieveRules,
            baseForm,
            commitRetrieve,
            RetrieveDialogVisible,
            showLoginDialog
        }
    }
}
</script>

<style scoped>

</style>
