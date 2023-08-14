<template>
    <el-form ref="baseForm"  :model="updatePasswordData.updatePasswordForm" label-width="120px" :rules="updatePasswordRule">
        <el-form-item label="请输入新密码" prop="newPassword">
            <el-input v-model="updatePasswordData.updatePasswordForm.newPassword"
                      placeholder="新密码"
                      prefix-icon="Lock"
                      show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPassword">
            <el-input v-model="updatePasswordData.updatePasswordForm.checkPassword"
                      placeholder="确认密码"
                      prefix-icon="Lock"
                      show-password></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="changePassword">提交</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import {baseForm,updatePasswordData,updatePasswordRule,commitUpdatePassword} from "@/utils/Texts/UpdatePassword";
import {computed, onBeforeMount} from "vue";
import {useStore} from "vuex";
import {showUpdatePassword} from "@/utils/DialogVisible";

export default {
    name: "UpdatePassword",
    setup()
    {
        function changePassword()
        {
            commitUpdatePassword()
        }

        onBeforeMount(()=>{
            const store = useStore()
            const count = computed(() => store.getters.getUserID)
<<<<<<< HEAD
            updatePasswordData.updatePasswordForm.userId=count.value
=======
            updatePasswordData.updatePasswordForm.userId = count.value
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
        })

        return{
            baseForm,
            updatePasswordData,
            updatePasswordRule,
            commitUpdatePassword,
            changePassword
        }
    }
}
</script>

<style scoped>

</style>
