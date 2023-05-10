<template>
    <div id="app">
        <el-affix>
            <navigation-menu></navigation-menu>
        </el-affix>
        <router-view></router-view>
    </div>
    <el-dialog v-model="show_update_password" title="修改密码" width="30%" :lock-scroll="false">
        <update-password></update-password>
    </el-dialog>
</template>

<style lang="scss">
#app {
    font-family: sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;

    overflow: auto;
    height: 100%
}

.el-button--primary:focus{
    background: #CC2288 !important;
    border-color: #CC2288 !important;
    color: #ffffff !important;
}
.el-button--primary:hover{
    background: #c965a2 !important;
    border-color: #c965a2 !important;
    color: #ffffff !important;
}
.el-button--primary{
    background: #CC2288 !important;
    border-color: #CC2288 !important;
    color: #ffffff !important;
}

*{
    font-family: "微软雅黑 Light", sans-serif;
}
</style>

<script setup lang="ts">
import NavigationMenu from "@/components/NavigationMenu.vue";
import UpdatePassword from "@/components/user/UpdatePassword.vue";
import {show_update_password} from "@/utils/DialogVisible";
import {ref} from "vue";
</script>
