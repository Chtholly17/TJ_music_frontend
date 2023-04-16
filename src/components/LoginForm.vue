<template>
    <div class="login-form" >
        <el-form ref="baseForm" :model="loginData.loginForm" :rules="loginRules">
            <el-form-item prop="username">
                <el-input v-model="loginData.loginForm.username"
                          placeholder="用户名 / 邮箱地址"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginData.loginForm.password"
                          placeholder="密码"
                          prefix-icon="Lock"
                          show-password></el-input>
            </el-form-item>
        </el-form>
        <el-button class=LoginButton type="primary" @click="commitLogin"> 登 录 </el-button>
    </div>
</template>

<script lang="ts">
import { User, Lock } from "@element-plus/icons-vue";
import {defineComponent, reactive, ref, unref} from "vue";
import api from '@/service/index'
import { FormInstance } from "element-plus";

// TODO：尝试将form, rules和处理函数等封装到单独的文件
// interface loginFormat {
//     username: string
//     password: string
// }
// const loginForm = ref<loginFormat>({
//     username: "",
//     password: ""
// })
const loginData = reactive({
    loginForm: {
        username: "",
        password: ""
    }
})
const loginRules = reactive ({
    username: [
        {
            required: true,
            trigger: "blur",
            message: "请输入用户名",
        },
    ],
    password: [
        {
            required: true,
            trigger: "blur",
            message: "请输入密码",
        },
    ],
})
const baseForm = ref<FormInstance>();
const commitLogin = async () => {
    const submitForm = unref(baseForm)
    if (!submitForm)
        return
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postLogin(loginData.loginForm); // 不能传入submitForm！
                console.log(response.data);
            } catch (error) {
                console.error(error);
            }
        } else {
            console.log("验证失败");
        }
    })
}

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
            commitLogin
        }
    }
})
</script>

<style scoped>
.LoginButton{
    display: flex;
    flex-direction: row;
    margin-top: 10px;
    margin-left: 35%;
    margin-right: 35%;
    width: 30%;
}
</style>
