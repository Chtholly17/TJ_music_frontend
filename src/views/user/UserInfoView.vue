<template>
   <el-dialog class="user_info" v-model="user_info_show" title="修改个人信息" width="50%" :lock-scroll="false">
      <el-form :model="form" label-width="120px">
         <el-form-item label="昵称">
            <el-input v-model="form.nickname" />
         </el-form-item>
         <el-form-item label="学院">
            <el-input v-model="form.colledg" />
         </el-form-item>
         <el-form-item label="专业">
            <el-input v-model="form.major" />
         </el-form-item>
         <el-form-item label="Activity zone">
            <el-select v-model="form.region" placeholder="please select your zone">
               <el-option label="Zone one" value="shanghai" />
               <el-option label="Zone two" value="beijing" />
            </el-select>
         </el-form-item>
         <el-form-item label="Activity time">
            <el-col :span="11">
               <el-date-picker
                   v-model="form.date1"
                   type="date"
                   placeholder="Pick a date"
                   style="width: 100%"
               />
            </el-col>
            <el-col :span="2" class="text-center">
               <span class="text-gray-500">-</span>
            </el-col>
            <el-col :span="11">
               <el-time-picker
                   v-model="form.date2"
                   placeholder="Pick a time"
                   style="width: 100%"
               />
            </el-col>
         </el-form-item>

         <el-form-item label="性别">
            <el-radio-group v-model="form.gender">
               <el-radio label="男" />
               <el-radio label="女" />
            </el-radio-group>
         </el-form-item>
         <el-form-item label="个性签名">
            <el-input v-model="form.signature" type="textarea" />
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button>修改</el-button>
         </el-form-item>
      </el-form>
   </el-dialog>
</template>

<script lang="ts">
import {inject, reactive} from "vue";

export default {
    name: "UserInfoView",
    setup()
    {
        const user_info_show=inject("user_info_show");
        const form = reactive({
          nickname: '日本天皇',   //昵称
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          gender: '',   //性别
          signature: '我是天皇，我最强！',   //签名
           colledg:"电子与信息工程学院",  //学院
           major:"计算机科学与技术",  //专业

        })
       const onSubmit = () => {
          console.log('submit!')
       }
        return{
           user_info_show,
           form,
           onSubmit
        }
    }
}

</script>

<style scoped>
.user_info{
    overflow: hidden;
}
</style>
