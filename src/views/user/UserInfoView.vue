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

         <el-form-item label="地区">
            <el-cascader size='mid' :options='options' v-model='selectedOptions' @change='handleChange()'></el-cascader>
         </el-form-item>

         <el-form-item label="生日">
            <el-col :span="11">
               <el-date-picker
                   v-model="form.date1"
                   type="date"
                   placeholder="选择你的生日"
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
            <el-button type="primary" @click="onSubmit" >提交</el-button>
         </el-form-item>
      </el-form>
   </el-dialog>
</template>

<script>
import {inject, reactive} from "vue";
import { provinceAndCityData, CodeToText } from 'element-china-area-data'

export default {
    name: "UserInfoView",
    data(){
        return{
            options:provinceAndCityData,
            selectedOptions: ['120000', '120100']
        }
    },
    methods: {
        handleChange () {
            console.log(this.selectedOptions)
        }
    },
    setup(props,cxt)
    {
        const user_info_show=inject("user_info_show");
        const form = reactive({
          nickname: '日本天皇',   //昵称
          province: '四川',
           city:'成都',
          date1: '2001-06-25',
          date2: '',
          delivery: false,
          type: [],
          gender: '男',   //性别
          signature: '我是天皇，我最强！',   //签名
           colledg:"电子与信息工程学院",  //学院
           major:"计算机科学与技术",  //专业

        })
       const onSubmit = () => {
          cxt.emit("pass_nickname",form.nickname);  //向父组件传递昵称参数
          cxt.emit("pass_signature",form.signature);  //向父组件传递签名参数
          user_info_show.value=false;
       }
        return{
           user_info_show,
           form,
           onSubmit,
        }
    }
}

</script>

<style scoped>
.user_info{
    overflow: hidden;
}
</style>
