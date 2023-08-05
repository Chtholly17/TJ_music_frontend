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
      <i v-else class="avatar-uploader-icon" style="border: 2px solid black;" ></i>
    </el-upload>
    <div class="container">
      <div class="sidebar">

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
            :on-change="onChangeBg"
            :show-file-list="false"
            multiple
            :auto-upload='false'>
            <audio v-if="bgMusicUrl" :src="bgMusicUrl" controls style = "width: 250px; height: 35px"></audio>
            <i v-else></i>
            <div class="el-upload__text">Click to upload</div>
        </el-upload>

        <el-upload label="原唱音乐"
            class="avatar-uploader"
            limit="1"
            action="#"
            :on-change="onChangeVoice"
            :show-file-list="false"
            multiple
            :auto-upload='false'
            style="margin-top: 10px;"
            >
            <audio v-if="voiceMusicUrl" :src="voiceMusicUrl" controls style = "width: 250px; height: 35px"></audio>
            <i v-else></i>
            <div class="el-upload__text">Click to upload</div>
        </el-upload>

        <el-upload label="音乐歌词"
            class="avatar-uploader"
            limit="1"
            action="#"
            :on-change="onChangeLyric"
            :show-file-list="false"
            multiple
            :auto-upload='false'
            style="margin-top: 10px;"
            >
            <h>{{form.lyricFilename}}</h>
            <div class="el-upload__text">Click to upload</div>
        </el-upload>

        <el-form-item label="音乐描述" prop="description" style="margin-top: 10px;">
          <el-input
            v-model="form.description"
            type="textarea"
            placeholder="请输入描述"
            :rows="3"
            style="width: 300px;"
          ></el-input>
        </el-form-item>
      </div>
      <div class="main-content">
        <div style="margin-left: 30px">
          <h2 style="font-size: 20px;">选择音乐Tag</h2>
          <div v-for="option in options" :key="option.id" class="checkbox-container">
            <input
              type="checkbox"
              :id="option.id"
              :value="option.value"
              v-model="selectedOptions"
              class="checkbox"
            />
            <label :for="option.id" class="checkbox-label" style="margin-left: 10px;">{{ option.label }}</label>
          </div>
          <!-- <p>已选择的选项: {{ selectedOptions }}</p> -->
        </div>
      </div>
    </div>
    <el-form-item style="margin-top: 15px;">
      <el-button type="primary" @click="submit">提交</el-button>
    </el-form-item>
  </div>
</template>

<script lang="ts">
import { defineComponent, h, ref, onMounted } from "vue";
import {} from '@element-plus/icons'
import axios from "axios";
import path from "@/service/path";
export default defineComponent({
  props: {
      originId: String,
      originName: String,
      originAuthor: String,
      originVoiceFilename: String,
      originBgmusicFilename: String,
      originPrefaceFilename: String,
      originLyricFilename: String,
      originDescription: String,
  },
  setup(props) {
      const form = (props.originId == "-1") ? ref({
          name: "",
          author: "",
          prefaceFilename: "",
          voiceFilename: "",
          bgmusicFilename: "",
          lyricFilename: "",
          description: "",
        }) : ref({
          name: props.originName,
          author: props.originAuthor,
          prefaceFilename: props.originPrefaceFilename,
          voiceFilename: props.originVoiceFilename,
          bgmusicFilename: props.originBgmusicFilename,
          lyricFilename: props.originLyricFilename,
          description: props.originDescription,
      });
      const rules = {
          name: [
              { required: true, message: "请输入名称", trigger: "blur" },
          ],
          author: [
              { required: true, message: "请输入作者", trigger: "blur" },
          ],
          prefaceFilename: [
              { required: true, message: "请上传封面图片", trigger: "blur" },
          ],
          voiceFilename: [
              { required: true, message: "请上传音频文件", trigger: "blur" },
          ],
          bgmusicFilename: [
              { required: true, message: "请上传背景音乐", trigger: "blur" },
          ],
          duration: [
              { required: true, message: "请输入时长", trigger: "blur" },
          ],
      };


      // const imageUrl = ref('https://th.bing.com/th/id/R.466bb61cd7cf4e8b7d9cdf645add1d6e?rik=YRZKRLNWLutoZA&riu=http://222.186.12.239:10010/wmxs_161205/002.jpg&ehk=WEy01YhyfNzzQNe1oIqxwgbTnzY7dMfmZZHkqpZB5WI=&risl=&pid=ImgRaw&r=0');
      // const voiceMusicUrl = ref('https://www.runoob.com/try/demo_source/horse.mp3')
      // const bgMusicUrl = ref('https://www.runoob.com/try/demo_source/horse.mp3')
      const imageUrl = ref(props.originPrefaceFilename);
      const voiceMusicUrl = ref(props.originVoiceFilename);
      const bgMusicUrl = ref(props.originBgmusicFilename);
      let imageFile: any = null;
      let voiceFile: any = null;
      let bgFile: any = null;
      let lyricFile: any = null;
      onMounted(() => {
          if (props.originId != "-1") {
            if (props.originPrefaceFilename) {
              //在props.originPrefaceFilename去掉前面的path.baseUrl
              let url = props.originPrefaceFilename.replace(path.serverUrl, "");
              axios.get(path.baseUrl + url, {
              }).then((res) => {
                imageFile = res.data
              });
            }
            if (props.originVoiceFilename) {
              let url = props.originVoiceFilename.replace(path.serverUrl, "");
              axios.get(path.baseUrl + url, {
              }).then((res) => {
                voiceFile = res.data;
              });
            }
            if (props.originBgmusicFilename) {
              let url = props.originBgmusicFilename.replace(path.serverUrl, "");
              axios.get(path.baseUrl + url, {
              }).then((res) => {
                bgFile = res.data;
              });
            }
            if (props.originLyricFilename) {
              let url = props.originLyricFilename.replace(path.serverUrl, "");
              axios.get(path.baseUrl + url, {
              }).then((res) => {
                lyricFile = res.data;
              });
            }
          }
      });
      const onChangeImage = (file: any) => {
          // console.log(file);
          imageFile = file.raw;
          const reader = new FileReader();
          reader.readAsDataURL(file.raw);
          reader.onload = () => {
              imageUrl.value = reader.result as string;
          };
      };
      const onChangeVoice = (file: any) => {
          //console.log(file);
          voiceFile = file.raw;
          const reader = new FileReader();
          reader.readAsDataURL(file.raw);
          reader.onload = () => {
              voiceMusicUrl.value = reader.result as string;
          };
      };
      const onChangeBg = (file: any) => {
          //console.log(file);
          bgFile = file.raw;
          const reader = new FileReader();
          reader.readAsDataURL(file.raw);
          reader.onload = () => {
              bgMusicUrl.value = reader.result as string;
          };
      };
      const onChangeLyric = (file: any) => {
          //console.log(file);
          lyricFile = file.raw;
          form.value.lyricFilename = file.name;
      };
      const submit = () => {
          //console.log(props.originId);
          let pack = new FormData();
          pack.append("originId", "-1");
          if (form.value.name) {
              pack.append("originName", form.value.name);
          }
          if (form.value.author) {
              pack.append("originAuthor", form.value.author);
          }
          if (imageFile) {
              pack.append("originPrefaceFile", imageFile);
          }
          if (voiceFile) {
              pack.append("originVoiceFile", voiceFile);
          }
          if (bgFile) {
              pack.append("originBgmusicFile", bgFile);
          }
          if (lyricFile) {
              pack.append("originLrcFile", lyricFile);
          }
          if (form.value.description) {
              pack.append("originIntroduction", form.value.description);
          }
          let type = "";
          //console.log(selectedOptions.value);
          selectedOptions.value.forEach((item: any) => {
              type += item + "/";
          });
          //console.log(type);
          if (type == "") {
            alert("请选择类型");
            return;
          } else {
            pack.append("originMusicTag", type);
          }
          //console.log(pack);
          axios.post(path.baseUrl + path.createOriginMusic, pack).then((res) => {
              console.log(res);
          }).catch((err) => {
              console.log(err);
          });
      };

      let options =ref([
        { id: '1', value: '民谣', label: '民谣' },
        { id: '2', value: '摇滚', label: '摇滚' },
        { id: '3', value: '金属', label: '金属' },
        { id: '4', value: '古典', label: '古典' },
        { id: '5', value: '热歌', label: '热歌' },
        { id: '6', value: '新歌', label: '新歌' },
        { id: '7', value: '飙升', label: '飙升' },
      ]);

      let selectedOptions = ref<{
          id: string;
          value: string;
          label: string;
      }[]>([]);

      return {
          form,
          rules,
          onChangeImage,
          onChangeBg,
          onChangeVoice,
          onChangeLyric,
          imageUrl,
          bgMusicUrl,
          voiceMusicUrl,
          submit,
          options,
          selectedOptions
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
.container {
  display: flex;
}

.sidebar {
  flex: 1;
  /* 侧边栏样式 */
}

.main-content {
  flex: 2;
  /* 主内容区域样式 */
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
.checkbox-list-container {
  border: 1px solid #ccc;
  padding: 10px;
}

.checkbox-container {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.checkbox {
  opacity: 0;
  position: absolute;
  cursor: pointer;
}

.checkbox-label {
  padding-left: 10px;
  font-size: 16px;
  color: #333;
  cursor: pointer;
}

.checkbox:checked + .checkbox-label {
  font-weight: bold;
  color: #ff0000;
}

.checkbox-container:hover {
  background-color: #f5f5f5;
}

.checkbox-container:last-child {
  margin-bottom: 0;
}
</style>
