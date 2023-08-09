<template>
    <el-form ref="baseForm"  :model="updatePasswordData.updatePasswordForm" label-width="120px" :rules="updatePasswordRule">
        <el-form-item label="请输入新密码" prop="new_password">
            <el-input v-model="updatePasswordData.updatePasswordForm.new_password"
                      placeholder="新密码"
                      prefix-icon="Lock"
                      show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="check_password">
            <el-input v-model="updatePasswordData.updatePasswordForm.check_password"
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
import {show_update_password} from "@/utils/DialogVisible";

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
            updatePasswordData.updatePasswordForm.userId = count.value
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
