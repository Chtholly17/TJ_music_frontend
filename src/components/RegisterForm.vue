<template>
    <div class="register-form">
        <el-form ref="baseForm" :model="registerForm" :rules="registerRules" label-width="80px">
            <el-form-item label="学号" prop="username">
                <el-input v-model="registerForm.username"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verification_code">
                <el-col :span="12">
                    <el-input v-model="registerForm.verification_code"></el-input>
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
                <el-input v-model="registerForm.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="check_pass">
                <el-input v-model="registerForm.check_pass" show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=RegisterButton type="primary" @click="commitRegister"> 注 册 </el-button>
    </div>
</template>

<script lang="ts">
import { QuestionFilled } from "@element-plus/icons";
import { FormInstance } from "element-plus";
import api from "@/service";
import { reactive, ref} from "vue";

// TODO：尝试将form, rules和处理函数封装到单独的文件
const registerForm = reactive({
    username: "",
    email: "",
    password: "",
    check_pass: "",
    verification_code: ""
})
const registerRules = ref ({
    username: [
        {
            required: true,
            message: '请输入用户名',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '请输入密码',
            trigger: 'blur'
        }
    ],
    check_pass: [
        {
            required: true,
            message: '请确认密码',
            trigger: 'blur'
        }
    ],
    verification_code: [
        {
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
        },
        {
            type: 'number',
            message: '验证码格式错误',
            trigger: 'blur',
            transform: (value: string) => Number(value)
            // TODO: 考虑到可能有以0开头的验证码，此处需要添加验证函数而不是用number检验
        }
    ]
})

const baseForm = ref<FormInstance>();
const commitRegister = async () => {
    if (!baseForm.value)
        return
    await baseForm.value.validate( async (valid: any) => {
        if (valid) {
            try {
                // const response = await api.getTest();
                const response = await api.postLogin(baseForm);
                console.log(response.data);
            } catch (error) {
                console.error(error);
            }
        } else {
            console.log("验证失败");
        }
    })
}

export default {
    name: "RegisterForm",
    components: {QuestionFilled},
    setup() {
        return {
            registerForm,
            registerRules,
            baseForm,
            commitRegister
        }
    }
}
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
