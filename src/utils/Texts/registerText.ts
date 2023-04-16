// 包含Register表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref, unref} from "vue";
import {FormInstance} from "element-plus";
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
    checkPassword: [
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
            try {
                const response = await api.postRegister(registerData.registerForm); // 不能传入submitForm！
                console.log(response.data);
            } catch (error) {
                console.error(error);
            }
        } else {
            console.log("验证失败");
        }
    })
}
