<template>
  <div class="upload">   

    <el-form-item label="To User">{{name}}</el-form-item>
  
    <el-form-item label="通知信息" prop="description" style="margin-top: 10px;">
      <el-input
        v-model="description"
        type="textarea"
        placeholder="请输入描述"
        :rows="3"
        style="width: 300px;"
      ></el-input>
    </el-form-item>

    <el-form-item style="margin-top: 15px;">
      <el-button type="primary" @click="submit">提交</el-button>
    </el-form-item>
    
  </div>
</template>

<script lang="ts">
import axios from "axios";
import path from "@/service/path";
import { defineComponent,ref } from "vue";
export default defineComponent({
  props: {
        originName: String,
    },
  setup(props) {
    let name = ref(props.originName);
    console.log(name.value);
    let description = ref("");
    const submit = () => {
      if(name.value=="all"){
        let form = new FormData();
        form.append("content", description.value);
        axios.post(path.baseUrl + path.sendMessageAll, form).then((res) => {
          console.log(res);
          alert("发送成功");
        }).catch((err) => {
          console.log(err);
        })
        return;
      }
      if (name.value == "") {
        alert("请输入用户名");
        return;
      }
      if (name.value) {
        let form = new FormData();
        form.append("sender_student_number", "admin");
        form.append("receiver_student_number", name.value);
        form.append("content", description.value);
        axios.post(path.baseUrl + path.sendService, form).then((res) => {
          console.log(res);
          alert("发送成功");
        }).catch((err) => {
          console.log(err);
        })
      }
    };
    return {
      name,
      description,
      submit,
    };
  },
});
</script>

<style scoped>
.upload {
  width: 100%;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.avatar {
  border-radius: 50%;
  overflow: hidden;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  overflow: hidden;
  position: relative;
  transition: border-color 0.3s ease-in-out;
  width: 200px;
  height: 200px;
  padding: 50px;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #373b3f;
  width: 150px;
  height: 150px;
  line-height: 200px;
  text-align: center;
  border-radius: 50%;
  overflow: hidden;
}

.avatar-uploader-icon i {
  font-size: 28px;
  color: #111112;
  width: 150px;
  height: 150px;
  line-height: 200px;
  text-align: center;
}

.avatar-uploader .el-upload .el-upload__text {
  padding: 0 10px;
  font-size: 12px;
  color: #8c939d;
}

.avatar-uploader .el-upload:hover .el-upload__text {
  color: #409eff;
}

</style>
