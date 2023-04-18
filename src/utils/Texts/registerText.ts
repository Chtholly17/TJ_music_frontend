// 包含Register表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();
export const registerData = reactive({
    registerForm: {
        userNumber: "",
        password: "",
        checkPassword: "",
        verificationCode: ""
    }
})
export const registerRules = reactive ({
    userNumber: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入用户名'
        }
    ],
    password: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入密码'
        }
    ],
    checkPassword: [ // TODO: 确认密码是否与先前一致
        {
            required: true,
            trigger: 'blur',
            message: '请确认密码'
        }
    ],
    verificationCode: [
        {
            required: true,
            trigger: 'blur',
            message: '请输入验证码'
        },
        {
            type: 'string',
            message: '验证码格式错误',
            trigger: 'blur'
        }
    ]
})
export const commitRegister = async () => {
    const submitForm = unref(baseForm)
    if (!submitForm)
        return
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try { // TODO: 剥离checkPassword，使用新的对象进行传送
                const response = await api.postRegister(registerData.registerForm); // 不能传入submitForm！
                console.log(response.data); // TODO：注册成功与失败的后处理
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
export const sendRegisterVRCode = async (userInfo: any) => {
    if (!userInfo)
        return
    if (!userInfo.userNumber) {
        ElMessage.error("请输入学号")
        return
    }
    // TODO：验证学号合法
    try {
        const response = await api.post_sendRegisterVRCode(userInfo);
        console.log(response.data); // TODO：注册成功与失败的后处理
    } catch (error: any) {
        ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
    }
}
