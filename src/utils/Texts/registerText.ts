// 包含Register表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import {pwdCheck, userNumberCheck, VRCodeCheck} from "@/utils/Texts/retrieveText";
import {showLoginDialog} from "@/utils/DialogVisible";

export const baseForm = ref<FormInstance>();
export const registerData = reactive({
    registerForm: {
        userNumber: "",
        password: "",
        checkPassword: "",
        verificationCode: ""
    }
})
const pwdAgainCheck = async(rule: any, value: any, callback: any) => {
    if (registerData.registerForm.checkPassword != registerData.registerForm.password)
        callback(Error("两次密码不一致，请检查"))
    else
        callback()
}
export const registerRules = reactive ({
    userNumber: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入学号'
        },
        {
            validator: userNumberCheck,
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入密码'
        },
        {   min: 6,
            max: 16,
            message: '长度在 6 到 16 个字符',
            trigger: 'blur'
        },
        {
            validator: pwdCheck, // 合法字符检验
            trigger: 'blur'
        }
    ],
    checkPassword: [
        {
            required: true,
            trigger: 'blur',
            message: '请确认密码'
        },
        {
            validator: pwdAgainCheck,
            trigger: 'blur'
        }
    ],
    verificationCode: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入验证码'
        },
        {
            validator: VRCodeCheck,
            trigger: 'blur'
        }
    ]
})
export const commitRegister = async () => {
    if (!baseForm.value)
        return
    await baseForm.value.validate( async (valid: any) => {
        if (valid) {
            try { // TODO: 剥离checkPassword，使用新的对象进行传送
                const response = await api.postRegister(registerData.registerForm); // 不能传入submitForm！
                console.log(response.data);
                if (response.data.code == 1) {
                    ElMessage.success("注册成功！")
                    showLoginDialog()
                    if(baseForm.value)
                        baseForm.value.resetFields() // 清空表单，打开登录弹窗
                }
                else if (response.data.code == 0) {
                    ElMessage.error("验证码错误！")
                    return false
                }
                else if (response.data.code == 2) {
                    ElMessage.error("两次密码不相同！")
                    return false
                }
                else {
                    ElMessage.error("未知错误！错误码："+response.data.code)
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

export const sendRegisterVRCode = async (userInfo: any): Promise<boolean> => {
    // 由于是异步函数，因此只能使用Promise返回
    if (!baseForm.value)
        return Promise.resolve(false)
    let FormValid = false
    await baseForm.value.validateField('userNumber', (valid)  => {
        FormValid = valid
    })
    // 不要在回调函数里声明异步……
    if (FormValid) {
        try {
            const response = await api.post_sendRegisterVRCode(userInfo);
            if (response.data.code == 1) {
                ElMessage.success("验证码发送成功，请注意查收")
                return true
            }
            else if (response.data.code == 0) {
                ElMessage.error("验证码发送失败，账号已存在！")
                return false
            }
            else {
                ElMessage.error("未知错误！错误码："+response.data.code)
                return false
            }
        } catch (error: any) {
            ElMessage.error(error.code + ': 提交失败，请检查网络或联系管理员')
            return false
        }
    } else {
        ElMessage.error('验证失败，请检查数据是否完整且正确')
        return false
    }
}
