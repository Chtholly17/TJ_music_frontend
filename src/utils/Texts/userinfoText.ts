//定义了个人信息管理部分需要使用到的数据结构
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();

export const userinfoData=reactive({
    userinfoForm:{
        user_id:0,
        nickname:'日本天皇',   //昵称
        //select_area:['120000', '120100'],  //选择的地区
        area1:'120000',
        area2:'120100',
        date1: '2001-06-25',    //生日
        gender: '男',   //性别
        signature: '我是天皇，我最强！',   //签名
        colledg:"电子与信息工程学院",  //学院
        major:"计算机科学与技术",  //专业
    }
})

export const userinfoRules=reactive({
    nickname:[
        {
            required:true,
            trigger:"blur",
            message:"昵称不能为空"
        },
    ],
})

export const commitUserInfo=async ()=>{

    const submitForm = unref(baseForm)
    if (!submitForm)
    {
        return
    }
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postUserInfo(userinfoData.userinfoForm); // 不能传入submitForm！
                console.log(response.data); // TODO：登陆成功与失败的后处理
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
