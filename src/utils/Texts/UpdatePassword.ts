import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import {userinfoData} from "@/utils/Texts/userinfoText";
import {registerData} from "@/utils/Texts/registerText";
import {pwdCheck} from "@/utils/Texts/retrieveText";

export const baseForm = ref<FormInstance>();

export const updatePasswordData=reactive({
    updatePasswordForm:{
        user_id:"", //用户id
        old_password:"",    //旧密码
        new_password:"",    //新密码
        check_password:""   //确认密码
    }
})
const pwdAgainCheck = async(rule: any, value: any, callback: any) => {
    if (updatePasswordData.updatePasswordForm.check_password != updatePasswordData.updatePasswordForm.new_password)
        callback(Error("两次密码不一致，请检查"))
    else
        callback()
}
export const updatePasswordRule=reactive({
    old_password:[
        {
            required:true,
            trigger:"blur",
            message:"原密码不能为空"
        }
    ],
    new_password:[
        {
            required:true,
            trigger:"blur",
            message:"新密码不能为空"
        }
    ],
    check_password:[
        {
            required: true,
            trigger: 'blur',
            message: '请确认密码'
        },
        {
            validator: pwdAgainCheck,
            trigger: 'blur'
        }
    ]
})

export const commitUpdatePassword=async ()=>{

    const submitForm = unref(baseForm)
    if (!submitForm)
    {
        return
    }
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postUpdatePassword(updatePasswordData.updatePasswordForm.user_id,updatePasswordData.updatePasswordForm.new_password); // 不能传入submitForm！
                console.log(response.data);
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
