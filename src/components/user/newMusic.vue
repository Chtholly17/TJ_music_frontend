<template>
  <div class="upload">    
    <el-upload
        class="avatar-uploader"
        limit="1"   
        action="#"
        :on-change="onChangeImage"
        :show-file-list="false"
        multiple
        :auto-upload='false'>
      <img v-if="imageUrl" :src="imageUrl" class="avatar" style="width: 150px; height: 150px; border: 2px solid black;" />
      <i v-else class="el-icon-plus avatar-uploader-icon" style="border: 2px solid black;" ></i>
    </el-upload>
    
    <el-form-item label="名称" prop="name" style="margin-top: 20px;">
      <el-input v-model="form.name" placeholder="请输入名称" style="width: 300px;"></el-input>
    </el-form-item>
    
    <el-form-item label="作者" prop="author">
      <el-input v-model="form.author" placeholder="请输入作者" style="width: 300px;"></el-input>
    </el-form-item>
    
    <el-upload label="背景音乐"
        class="avatar-uploader"
        limit="1"   
        action="#"
        :on-change="onChangeMusic"
        :show-file-list="false"
        multiple
        :auto-upload='false'>
        <audio v-if="audioUrl" :src="audioUrl" controls style = "width: 250px; height: 35px"></audio>
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        <div class="el-upload__text">Click to upload</div>
    </el-upload>
    
    <el-upload label="原唱音乐"
        class="avatar-uploader"
        limit="1"   
        action="#"
        :on-change="onChangeMusic"
        :show-file-list="false"
        multiple
        :auto-upload='false'
        style="margin-top: 10px;"
        >
        <audio v-if="audioUrl" :src="audioUrl" controls style = "width: 250px; height: 35px"></audio>
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        <div class="el-upload__text">Click to upload</div>
    </el-upload>
    
    <el-form-item label="音乐描述" prop="dis" style="margin-top: 10px;">
      <el-input
        v-model="form.author"
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
import { defineComponent, ref } from "vue";
import {} from '@element-plus/icons'
export default defineComponent({
  props: {
    originPrefaceFilename: String,
    originName: String,
    originAuthor: String,
    originVoiceFilename: String,
    originBgmusicFilename: String,
    originDuration: String,
  },

  setup(props) {
    const form = ref({
      name: props.originName,
      author: props.originAuthor,
      prefaceFilename: props.originPrefaceFilename,
      voiceFilename: props.originVoiceFilename,
      bgmusicFilename: props.originBgmusicFilename,
      duration: props.originDuration,
    });

    const rules = {
      name: [
        { required: true, message: '请输入名称', trigger: 'blur' },
      ],
      author: [
        { required: true, message: '请输入作者', trigger: 'blur' },
      ],
      prefaceFilename: [
        { required: true, message: '请上传封面图片', trigger: 'blur' },
      ],
      voiceFilename: [
        { required: true, message: '请上传音频文件', trigger: 'blur' },
      ],
      bgmusicFilename: [
        { required: true, message: '请上传背景音乐', trigger: 'blur' },
      ],
      duration: [
        { required: true, message: '请输入时长', trigger: 'blur' },
      ],
    };
    
    const imageUrl = ref('https://th.bing.com/th/id/R.466bb61cd7cf4e8b7d9cdf645add1d6e?rik=YRZKRLNWLutoZA&riu=http%3a%2f%2f222.186.12.239%3a10010%2fwmxs_161205%2f002.jpg&ehk=WEy01YhyfNzzQNe1oIqxwgbTnzY7dMfmZZHkqpZB5WI%3d&risl=&pid=ImgRaw&r=0');
    const audioUrl = ref('https://www.runoob.com/try/demo_source/horse.mp3')
    const onChangeImage = (file: any) => {
      console.log(file);
      const reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = () => {
        imageUrl.value = reader.result as string;
      };
    }; 
    const onChangeMusic = (file: any) => {
      console.log(file);
      const reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = () => {
        audioUrl.value = reader.result as string;
      };
    };

    const submit = () => {
      console.log(form.value);
    };
 
    return {
      form,
      rules,
      onChangeImage,
      onChangeMusic,
      imageUrl,
      audioUrl,
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
