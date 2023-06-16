<template>
  <div class="upload">    
    <img :src="imageUrl" class="avatar" style="width: 150px; height: 150px; border: 2px solid black;" />
  
    <div style="width: 300px; padding: 10px;">{{ name }}</div>

    <div style="width: 300px; padding: 10px;">{{ author }}</div>

    <pre style="width: 300px; padding: 10px; white-space: pre-wrap;">{{ description }}</pre>
  </div>
</template>

<script lang="ts">
import { defineComponent,ref } from "vue";
export default defineComponent({
  props: {
        originName: String,
        originAuthor: String,
        originPrefaceFilename: String,
        originDescription: String,
    },
  setup(props) {
    let imageUrl = ref(props.originPrefaceFilename);
    let name = ref(props.originName);
    let author = ref(props.originAuthor);
    let description = ref(props.originDescription);
    return {
      imageUrl,
      name,
      author,
      description,
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
