<template>
    <el-form ref="baseForm"  :model="updatePasswordData.updatePasswordForm" label-width="120px" :rules="updatePasswordRule">
        <el-form-item label="请输入旧密码">
            <el-input v-model="updatePasswordData.updatePasswordForm.old_password"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码">
            <el-input v-model="updatePasswordData.updatePasswordForm.new_password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" >提交</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import {baseForm,updatePasswordData,updatePasswordRule,commitUpdatePassword} from "@/utils/Texts/UpdatePassword";

export default {
    name: "UpdatePassword",
    setup()
    {

        return{
            baseForm,
            updatePasswordData,
            updatePasswordRule,
            commitUpdatePassword
        }
    }
}
</script>

<style scoped>

</style>
