<template>
    <el-menu :router="true" mode="horizontal">
        <el-menu-item index="/hello">登录</el-menu-item>
        <el-sub-menu>
            <template #title>我的</template>
            <el-menu-item index="/user">个人主页</el-menu-item>
        </el-sub-menu>
        <search-bar></search-bar>
    </el-menu>
</template>

<script>
import {showLoginDialog} from "@/utils/DialogVisible";
import SearchBar from "@/components/searchBar.vue";

export default {
    name: "NavigationMenu",
    components: {SearchBar},
    setup() {
        return {
            showLoginDialog
        }
    }
}
</script>

<style scoped>

</style>
