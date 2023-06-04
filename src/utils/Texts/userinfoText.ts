//定义了个人信息管理部分需要使用到的数据结构
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();

export const userinfoData=reactive({
    userinfoForm:{
        user_student_number:"",
        new_nickname:'',   //昵称
        new_college:"",  //学院
        new_major:"",
        new_area1:'',
        new_area2:'',
        new_birthday: '',    //生日
        new_gender: '',   //性别
        new_signature: '',   //签名
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

    // console.log("获取用户学号2")
    // console.log( userinfoData.userinfoForm.user_student_number)
    const submitForm = unref(baseForm)
    if (!submitForm)
    {
        return
    }
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                // console.log("提交用户信息")
                // console.log(userinfoData.userinfoForm)
                const response = await api.postUserInfo(userinfoData.userinfoForm); // 不能传入submitForm！
                // console.log(response.data);
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}

//获取用户信息
export const fetchUserInfo=async ()=>{

    try {
        const response = await api.getUserInfo(userinfoData.userinfoForm.user_student_number); // 不能传入submitForm！
        userinfoData.userinfoForm.new_nickname=response.data.data.userNickname
        userinfoData.userinfoForm.new_college=response.data.data.userCollege
        userinfoData.userinfoForm.new_major=response.data.data.userMajor
        userinfoData.userinfoForm.new_area1=response.data.data.userArea1
        userinfoData.userinfoForm.new_area2=response.data.data.userArea2
        userinfoData.userinfoForm.new_birthday=response.data.data.userBirthday
        userinfoData.userinfoForm.new_gender=response.data.data.userGender
        userinfoData.userinfoForm.new_signature=response.data.data.userSignature
        return response.data.data
    } catch (error: any) {
        ElMessage.error(error.code+': 获取失败，请检查网络或联系管理员')
    }
}

export const user_fetchUserImage=async (user_id:any)=>{
    const  response=await api.getUserImage({user_student_number:user_id})
    return response.data.data
}

//获取其他人的信息
export const fetchOtherInfo=async (user_id:any)=>{
    try {
        const response = await api.getUserInfo(user_id);
        return response.data.data
    } catch (error:any){
        ElMessage.error(error.code+': 获取失败，请检查网络或联系管理员')
    }

}
