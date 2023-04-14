<template>
    <div class="login-form" >
        <el-form form="user" :model="form">
            <el-form-item>
                <el-input v-model="form.username"
                          placeholder="用户名 / 邮箱地址"
                          prefix-icon="User"></el-input>
            </el-form-item>
            <el-form-item>
                <el-input v-model="form.password"
                          placeholder="密码"
                          prefix-icon="Lock"></el-input>
            </el-form-item>
        </el-form>
        <el-button class=LoginButton type="primary">登 录</el-button>
    </div>
</template>

<script>
import { User, Lock } from "@element-plus/icons-vue";
export default {
    name: "LoginForm",
    data() {
        return {
            form:{
                username: "",
                password: ""
            }
        }
    },
    setup(){
        return {
            User,
            Lock
        }
    }
}
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
