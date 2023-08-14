import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import {userinfoData} from "@/utils/Texts/userinfoText";

export const baseForm = ref<FormInstance>();

export const SongListData=reactive({
    SongListForm:{
        userStudentNumber:0,  //用户id
        songName:"",   //歌名
        songSinger:"", //歌手
        songGrade:"",  //评分
        songDate:"",   //日期
    }
})

//获取用户曲库
// export const fetchSongList=async ()=>{
//
//     const submitForm = unref(baseForm)
//     if (!submitForm)
//     {
//         return
//     }
//     await submitForm.validate( async (valid: any) => {
//         if (valid) {
//             try {
//                 const response = await api.getSongList(SongListData.SongListForm.user_student_number) // 不能传入submitForm！
//                 console.log(response.data);
//             } catch (error: any) {
//                 ElMessage.error(error.code+': 获取失败，请检查网络或联系管理员')
//             }
//         } else {
//             ElMessage.error('验证失败，请检查数据是否完整且正确')
//         }
//     })
// }
