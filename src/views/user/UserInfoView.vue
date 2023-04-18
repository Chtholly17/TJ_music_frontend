<template>
   <el-dialog class="user_info" v-model="user_info_show" title="修改个人信息" width="50%" :lock-scroll="false">
      <el-form ref="baseForm"  :model="userinfoData.userinfoForm" label-width="120px" :rules="userinfoRules">
         <el-form-item label="昵称" prop="nickname">
            <el-input v-model="userinfoData.userinfoForm.nickname" />
         </el-form-item>
         <el-form-item label="学院">
            <el-input v-model="userinfoData.userinfoForm.colledg" />
         </el-form-item>
         <el-form-item label="专业">
            <el-input v-model="userinfoData.userinfoForm.major" />
         </el-form-item>

         <el-form-item label="地区">
            <el-cascader size='mid' :options='options' v-model='area'></el-cascader>
         </el-form-item>

         <el-form-item label="生日">
            <el-col :span="11">
               <el-date-picker
                   v-model="userinfoData.userinfoForm.date1"
                   type="date"
                   placeholder="选择你的生日"
                   style="width: 100%"
               />
            </el-col>
         </el-form-item>

         <el-form-item label="性别">
            <el-radio-group v-model="userinfoData.userinfoForm.gender">
               <el-radio label="男" />
               <el-radio label="女" />
            </el-radio-group>

         </el-form-item>
         <el-form-item label="个性签名">
            <el-input v-model="userinfoData.userinfoForm.signature" type="textarea" />
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="onSubmit" >提交</el-button>
         </el-form-item>
         {{area}}
      </el-form>
   </el-dialog>
</template>

<script>
import {inject, onBeforeMount, onMounted, reactive, ref} from "vue";
import { provinceAndCityData, CodeToText } from 'element-china-area-data'
import {baseForm,userinfoData,userinfoRules,commitUserInfo} from "@/utils/Texts/userinfoText";

export default {
    name: "UserInfoView",
   computed: {
      userinfoData() {
         return userinfoData
      }
   },
    data(){
        return{
            options:provinceAndCityData,
            // area:ref(['120100'])
        }
    },
    methods: {
    },

   setup(props,cxt)
    {
       const area=ref([])
       onBeforeMount(()=>{
          //area=[]
          area.value.push(userinfoData.userinfoForm.area1)
          area.value.push(userinfoData.userinfoForm.area2)
          console.log("开始渲染")
       })
        const user_info_show=inject("user_info_show");
        // const form = reactive({
        //   nickname: '日本天皇',   //昵称
        //   province: '四川',
        //    city:'成都',
        //   date1: '2001-06-25',
        //   date2: '',
        //   delivery: false,
        //   type: [],
        //   gender: '男',   //性别
        //   signature: '我是天皇，我最强！',   //签名
        //    colledg:"电子与信息工程学院",  //学院
        //    major:"计算机科学与技术",  //专业
        //    head_photo_url:"", //头像图片的url
        // })
       const onSubmit = () => {
          commitUserInfo();
          cxt.emit("pass_nickname",userinfoData.userinfoForm.nickname);  //向父组件传递昵称参数
          cxt.emit("pass_signature",userinfoData.userinfoForm.signature);  //向父组件传递签名参数
          user_info_show.value=false;
       }
        return{
           user_info_show,
           // form,
           onSubmit,
           userinfoRules,
           baseForm,
           area,
           onBeforeMount
        }
    }
}

</script>

<style scoped>
.user_info{
    overflow: hidden;
}
</style>
