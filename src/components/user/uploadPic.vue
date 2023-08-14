<template>
    <div class="upload">
        <el-upload drag :action="'https://jsonplaceholder.typicode.com/posts/'"  :show-file-list="false"  :before-upload="beforeAvatarUpload">
            <el-icon class="el-icon--upload"><upload-filled /></el-icon>
            <div class="el-upload__text">将文件拖到此处或点击上传</div>
            <template #tip>
                <p class="el-upload__tip">只能上传 {{ uploadTypes.join("、") }} 文件, 且不超过10M</p>
            </template>
        </el-upload>
<!--        <a-upload class="avatar-upload" :show-upload-list="false"-->
<!--                  :before-upload="beforeAvatarUpload">-->
<!--            <a-button>更换头像</a-button>-->
<!--        </a-upload>-->
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import axios from "axios";
import path from "@/service/path";
import {ElMessage} from "element-plus";

export default defineComponent({
    components: {
        //UploadFilled,
    },
    setup() {
        const proxy  = getCurrentInstance();
        const store = useStore();

        // 声明一个异步函数 delay，使用 setTimeout 实现延迟操作
        const delay = async () => {
            await new Promise(resolve => setTimeout(resolve, 1000));
            //console.log("睡眠完成")
        };

        const uploadTypes = ref(["jpg", "jpeg", "png", "gif"]);
        const userId = computed(() => store.getters.getUserID);
        function beforeAvatarUpload(file:any) {
            //(proxy as any).$message.error("图片提交成功");
            const ltCode = 2;
            const isLt10M = file.size / 1024 / 1024;
            const isExistFileType = uploadTypes.value.includes(file.type.replace(/image\//, ""));

            if (isLt10M > ltCode || isLt10M <= 0) {
                ElMessage.error(`图片大小范围是 0~${ltCode}MB!`)
            }
            if (!isExistFileType) {
                ElMessage.error(`图片只支持 ${uploadTypes.value.join("、")} 格式!`)
            }

            let pic_form=new FormData();

            pic_form.append("user_student_number",userId.value);
            pic_form.append("file",file);

            axios.post(path.baseUrl+path.updateUserImage,pic_form).then(res=>{

               delay();
               // console.log("用户头像修改")
               // console.log(res);
               store.state.bar_pic_change=!store.state.bar_pic_change;
            })



            return isLt10M && isExistFileType;
        }

        function handleAvatarSuccess() {
            // console.log("上传成功！")
        }

        return {
            uploadTypes,
            beforeAvatarUpload,
            handleAvatarSuccess,
        };
    },
});
</script>

<style scoped>
.upload {
    width: 100%;
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.avatar-upload {
    margin-top: 12px;
}
</style>
