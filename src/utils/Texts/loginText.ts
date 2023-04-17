// 包含Login表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref, unref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();
export const loginData = reactive({
    loginForm: {
        userNumber: "",
        password: ""
    }
})
export const loginRules = reactive ({
    userNumber: [
        {
            required: true,
            trigger: "blur",
            message: "请输入学号",
        },
    ],
    password: [
        {
            required: true,
            trigger: "blur",
            message: "请输入密码",
        },
    ],
})
export const commitLogin = async () => {
    const submitForm = unref(baseForm)
    if (!submitForm)
        return
    await submitForm.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postLogin(loginData.loginForm); // 不能传入submitForm！
                console.log(response.data); // TODO：登陆成功与失败的后处理
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
