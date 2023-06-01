import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import {show_update_password} from "@/utils/DialogVisible";


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

export const pwdCheck =  async(rule: any, value: any, callback: any) => {
    // const reg = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[~@#$%*-+=:,\\?[\]{}]).{6,16}$/ // TODO：修改这个正则表达式
    const reg = /\d/
    if (!reg.test(value))
        callback(Error("密码不合法"))
    else
        callback() // callback回调函数得到的信息将会输出，类似rules里面的message
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
        },
        {   min: 6,
            max: 16,
            message: '长度在 6 到 16 个字符',
            trigger: 'blur'
        },
        {
            validator: pwdCheck,
            trigger: 'blur'
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
                if(response.data.code==1) {
                    ElMessage.success("修改成功")
                    show_update_password.value=false
                }
                else {
                    ElMessage.error("修改失败")
                    return false
                }
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
