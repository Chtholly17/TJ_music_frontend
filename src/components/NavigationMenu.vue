<template>
    <el-menu :router="true" mode="horizontal">
        <el-menu-item @click="showLoginDialog">登录 / 注册</el-menu-item>
        <el-sub-menu>
            <template #title>我的</template>
            <el-menu-item index="/user">个人主页</el-menu-item>
        </el-sub-menu>
    </el-menu>
</template>

<script>
import {showLoginDialog} from "@/utils/DialogVisible";

export default {
    name: "NavigationMenu",
    setup() {
        return {
            showLoginDialog
        }
    }
}
</script>

<style scoped>

</style>
