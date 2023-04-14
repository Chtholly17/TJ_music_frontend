<template>
    <div class="register-form">
        <el-form form="user" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="用户名" prop="username">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
                <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verification_code">
                <el-col :span="14">
                    <el-input v-model="form.verification_code"></el-input>
                </el-col>
                <el-col :span="10">
                    <el-button>发送验证码</el-button>
                </el-col>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="check_pass">
                <el-input v-model="form.check_pass" show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=RegisterButton type="primary" @click="submitForm"> 注 册 </el-button>
    </div>
</template>

<script>
import {reactive, ref, unref} from "vue";

export default {
    name: "RegisterForm",
    setup() {
        const ruleForm = ref(null)
        const form = reactive({
            username: "",
            email: "",
            password: "",
            check_pass: "",
            verification_code: ""
        })
        const rules = {
            username: [
                { required: true, message: '请输入用户名', trigger: 'blur' }
            ],
            email: [
                { required: true, message: '请输入邮箱', trigger: 'blur' }
            ],
            password: [
                { required: true, message: '请输入密码', trigger: 'blur' }
            ],
            check_pass: [
                { required: true, message: '请确认密码', trigger: 'blur' }
            ],
            verification_code: [
                { required: true, message: '请输入验证码', trigger: 'blur'},
                { type: 'number', message: '验证码格式错误', trigger: 'blur', transform: (value) => Number(value)}
            ],
        }
        const submitForm = async () => {
            const form = unref(ruleForm);
            if (!form) return
            try {
                await form.validate()
            } catch (error) {
                console.error("ERROR!")
            }
        }
        return {
            ruleForm,
            form,
            rules,
            submitForm
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
</style>
