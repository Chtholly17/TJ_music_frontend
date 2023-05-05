<template>
    <div id="app">
        <el-affix>
            <navigation-menu></navigation-menu>
        </el-affix>
        <router-view></router-view>
    </div>
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

/*.log_reg_dialog{*/
/*    padding-left: 1em;*/
/*    padding-right: 1em;*/
/*    padding-bottom: 0;*/
/*}*/

*{
    font-family: "微软雅黑 Light", sans-serif;
}
</style>

<script setup lang="ts">
import NavigationMenu from "@/components/NavigationMenu.vue";
</script>
