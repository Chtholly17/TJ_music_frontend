<template>
    <div class="PrefaceDiv">
        <div class="video-background">
            <video autoplay muted loop>
                <source src="@/assets/backgroud.mp4" type="video/mp4">
            </video>
        </div>
        <div class="preface-box">
            <div>
                <el-image :src="require('@/assets/logo/logo_rec.png')"></el-image>
            </div>
            <div>
                <login-form v-if="LoginDialogVisible"> </login-form>
                <register-form v-else-if="RegisterDialogVisible"> </register-form>
                <retrieve-form v-else-if="RetrieveDialogVisible"> </retrieve-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import LoginForm from "@/components/preface/LoginForm.vue";
import {defineComponent} from "vue";
import RegisterForm from "@/components/preface/RegisterForm.vue";
import RetrieveForm from "@/components/preface/RetrieveForm.vue";
import {loginDialogVisible, registerDialogVisible, retrieveDialogVisible, showLoginDialog} from "@/utils/DialogVisible";
import {onMounted} from "vue";

export default defineComponent({
    name: "LoginView",
    components: {RetrieveForm, RegisterForm, LoginForm},
    setup() {
        onMounted(showLoginDialog)
        // 将导航栏删掉后，这一部分似乎不再需要了…
        // onBeforeRouteLeave(() => {
        //     const userID = computed(() => store.getters.getUserID).value
        //     if (userID === "") {
        //         ElMessage.warning("您还没有登录，请先登录")
        //         router.replace('/hello')
        //     }
        // })
        return {
            LoginDialogVisible: loginDialogVisible,
            RegisterDialogVisible: registerDialogVisible,
            RetrieveDialogVisible: retrieveDialogVisible
        }
    }
})
</script>

<style src="@/assets/CSS/preface.scss" lang="scss" scoped>
//用这种方式引入不会报错，也不会污染全局样式
</style>
