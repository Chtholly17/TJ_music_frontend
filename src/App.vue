<template>
    <navigationMenu></navigationMenu>
    <router-view></router-view>
    <login-dialogue></login-dialogue>
    <register-dialog></register-dialog>
    <retrieve-dialog></retrieve-dialog>
</template>

<style>
#app {
    font-family: sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
}
/*<<<<<<< HEAD*/

/*nav {*/
/*  padding: 30px;*/
/*}*/

/*a{*/
/*    text-decoration: none;*/
/*    color: #000;*/
/*}*/
/*.router-link-active {*/
/*    color: red;*/
/*}*/
/*.login_register_button{*/
/*    margin-top: 10px;*/
/*}*/

.log_reg_dialog{
    padding-left: 1em;
    padding-right: 1em;
    padding-bottom: 0;
}
<<<<<<< HEAD
.nav_bar{
    position:fixed;
    top:0;
    width: 100%;
=======

*{
    font-family: "微软雅黑 Light", sans-serif;
}
.el-input .el-input__inner{
    height: 35px;
    letter-spacing: 1px;
    padding: 0 4px;
>>>>>>> front-end
}
</style>

<script setup lang="ts">
import NavigationMenu from "@/components/NavigationMenu.vue";
import LoginDialogue from "@/components/LoginDialogue.vue";
import RegisterDialog from "@/components/RegisterDialog.vue";
import RetrieveDialog from "@/components/RetrieveDialog.vue";

</script>
