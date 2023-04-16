<template>
    <div class="register-form">
        <el-form ref="baseForm" :model="registerData.registerForm" :rules="registerRules" label-width="80px">
            <el-form-item label="学号" prop="username">
                <el-input v-model="registerData.registerForm.username"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verification_code">
                <el-col :span="12">
                    <el-input v-model="registerData.registerForm.verification_code"></el-input>
                </el-col>
                <el-col :span="10">
                    <el-button>发送验证码</el-button>
                </el-col>
                <el-col :span="2">
                    <div class="tip_icon">
                        <el-tooltip content="向同济邮箱（学号@tongji.edu.cn）发送验证码">
                            <el-icon :size="18"><QuestionFilled /></el-icon>
                        </el-tooltip>
                    </div>
                </el-col>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="registerData.registerForm.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="check_pass">
                <el-input v-model="registerData.registerForm.check_pass" show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=RegisterButton type="primary" @click="commitRegister"> 注 册 </el-button>
    </div>
</template>

<script lang="ts">
import { QuestionFilled } from "@element-plus/icons";
import {registerData, registerRules, baseForm, commitRegister} from "@/utils/registerText";
import {defineComponent} from "vue";

export default defineComponent({
    name: "RegisterForm",
    components: {QuestionFilled},
    setup() {
        return {
            registerData,
            registerRules,
            baseForm,
            commitRegister
        }
    }
})
</script>

<style scoped>
.RegisterButton{
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    margin-left: 35%;
    margin-right: 35%;
    width: 30%;
}
.tip_icon{
    display: flex;
    align-items: center;
}
</style>
