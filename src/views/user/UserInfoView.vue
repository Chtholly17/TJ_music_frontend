<template>
   <el-dialog class="user_info" v-model="user_info_show" title="修改个人信息" width="50%" :lock-scroll="false">
      <el-form ref="baseForm"  :model="userinfoData.userinfoForm" label-width="120px" :rules="userinfoRules">
         <el-form-item label="昵称" prop="newNickname">
            <el-input v-model="userinfoData.userinfoForm.newNickname" />
         </el-form-item>
<!--         <el-form-item label="学院">-->
<!--            <el-input v-model="userinfoData.userinfoForm.newCollege" />-->
<!--         </el-form-item>-->
<!--         <el-form-item label="专业">-->
<!--            <el-input v-model="userinfoData.userinfoForm.newMajor" />-->
<!--         </el-form-item>-->
         <el-form-item label="学院和专业">
            <el-cascader style="width: 80%" v-model="colma" :options="college_major" :props="colma_props" @change="colma_change"></el-cascader>
         </el-form-item>
         <el-form-item label="地区">
            <el-cascader  size='mid' :options='options' v-model='area'></el-cascader>
         </el-form-item>

         <el-form-item label="生日">
            <el-col :span="11">
               <el-date-picker
                   v-model="userinfoData.userinfoForm.newBirthday"
                   type="date"
                   placeholder="选择你的生日"
                   style="width: 100%"
               />
            </el-col>
         </el-form-item>

         <el-form-item label="性别">
            <el-radio-group v-model="userinfoData.userinfoForm.newGender">
               <el-radio label="男" />
               <el-radio label="女" />
            </el-radio-group>

         </el-form-item>
         <el-form-item label="个性签名">
            <el-input v-model="userinfoData.userinfoForm.newSignature" type="textarea" />
         </el-form-item>
         <el-form-item>
            <el-button type="primary" @click="onSubmit" >提交</el-button>
         </el-form-item>
      </el-form>
   </el-dialog>
</template>

<script>
import {computed, inject, onBeforeMount, ref} from "vue";
import { provinceAndCityData } from 'element-china-area-data'
import {baseForm, userinfoData, userinfoRules, commitUserInfo, fetchUserInfo,college_major} from "@/utils/Texts/userinfoText";
import {mapGetters} from "vuex";
import store from "@/store";
import { useStore } from 'vuex'
import {getCookie} from "@/service/cookie";
import {user_nickname} from "@/utils/Texts/userinfoText";

export default {
   name: "UserInfoView",
   computed: {
      store() {
         return store
      },
      userinfoData() {
         return userinfoData
      },
      ...mapGetters(['getUserID'])
   },

   setup(props,cxt)
   {
      const store = useStore()
      const options=provinceAndCityData
      const area=ref([])
      const count = computed(() => store.getters.getUserID)

      const colma=ref([]);
      const colma_props = {
         expandTrigger: 'hover',
      }
      function colma_change(){
         userinfoData.userinfoForm.newCollege=colma.value[0]
         userinfoData.userinfoForm.newMajor=colma.value[1]
      }

      onBeforeMount(()=>{
         //userinfoData.userinfoForm.userStudentNumber= count.value;
         userinfoData.userinfoForm.userStudentNumber=getCookie("userNumber");
         const userId=getCookie("userNumber");
         fetchUserInfo(userId).then(res=>{
            area.value.push(res.userArea1)
            area.value.push(res.userArea2)
            colma.value.push(res.userCollege)
            colma.value.push(res.userMajor)
         })
      })

      const user_info_show=inject("user_info_show");

      const onSubmit = () => {
         userinfoData.userinfoForm.newArea1=area.value[0]
         userinfoData.userinfoForm.newArea2=area.value[1]
         //console.log(userinfoData.userinfoForm.newBirthday)

         user_nickname.value=userinfoData.userinfoForm.newNickname;

         if(userinfoData.userinfoForm.newBirthday===null)
            userinfoData.userinfoForm.newBirthday=''
         commitUserInfo();
         cxt.emit("pass_nickname",userinfoData.userinfoForm.newNickname);  //向父组件传递昵称参数
         cxt.emit("pass_signature",userinfoData.userinfoForm.newSignature);  //向父组件传递签名参数
         user_info_show.value=false;
      }
      return{
         user_info_show,
         // form,
         onSubmit,
         userinfoRules,
         baseForm,
         area,
         onBeforeMount,
         options,college_major,colma,colma_props,colma_change
      }
   }
}

</script>

<style scoped>
.user_info{
   overflow: hidden;
}
</style>
