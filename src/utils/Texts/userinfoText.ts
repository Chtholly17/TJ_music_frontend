//定义了个人信息管理部分需要使用到的数据结构
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();

export const userinfoData=reactive({
    userinfoForm:{
        user_student_number:0,
        new_nickname:'日本天皇',   //昵称
        new_college:"电子与信息工程学院",  //学院
        new_major:"计算机科学与技术",
        new_area1:'120000',
        new_area2:'120100',
        new_birthday: '2001/6/30',    //生日
        new_gender: '男',   //性别
        new_signature: '我是天皇，我最强！',   //签名
    }
})

export const userinfoRules=reactive({
    new_nickname:[
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

export const getUserInfo=async ()=>{

    const submitForm = unref(baseForm)
    if (!submitForm)
    {
        return
    }
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.getUserInfo(userinfoData.userinfoForm.user_student_number); // 不能传入submitForm！
                console.log(response.data); // TODO：登陆成功与失败的后处理
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
