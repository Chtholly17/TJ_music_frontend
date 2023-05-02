<template>
    <div class="PrefaceDiv">
        <div class="preface-box">
            <div>
                <el-image :src="require('@/assets/logo/logo_rec.png')"></el-image>
            </div>
            <div>
                <login-form v-if="LoginDialogVisible"> </login-form>
                <register-form v-if="RegisterDialogVisible"> </register-form>
                <retrieve-form v-if="RetrieveDialogVisible"> </retrieve-form>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import LoginForm from "@/components/LoginForm.vue";
import {defineComponent} from "vue";
import RegisterForm from "@/components/RegisterForm.vue";
import RetrieveForm from "@/components/RetrieveForm.vue";
import {LoginDialogVisible, RegisterDialogVisible, RetrieveDialogVisible, showLoginDialog} from "@/utils/DialogVisible";
import {onMounted} from "vue";
export default defineComponent({
    name: "LoginView",
    components: {RetrieveForm, RegisterForm, LoginForm},
    setup() {
        onMounted(showLoginDialog)
        return {
            LoginDialogVisible,
            RegisterDialogVisible,
            RetrieveDialogVisible
        }
    }
})
</script>

<style src="@/assets/CSS/preface.scss" lang="scss" scoped>
//用这种方式引入不会报错，也不会污染全局样式
</style>
