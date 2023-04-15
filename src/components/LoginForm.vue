<template>
    <div class="login-form" >
        <el-form ref="baseForm" :model="loginForm" :rules="loginRules">
            <el-form-item prop="username">
                <el-input v-model="loginForm.username"
                          placeholder="用户名 / 邮箱地址"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginForm.password"
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
import {defineComponent, reactive, ref} from "vue";
import api from '@/service/index'
import { FormInstance } from "element-plus";

// TODO：尝试将form, rules和处理函数封装到单独的文件
const loginForm = reactive({
    username: "",
    password: ""
})
const loginRules = ref ({
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

export default defineComponent({
    name: "LoginForm",
    setup(){
        return {
            User,
            Lock,
            loginForm,
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
