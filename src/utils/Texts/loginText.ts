// 包含Login表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref, unref} from "vue";
import {FormInstance} from "element-plus";
import api from "@/service";

export const baseForm = ref<FormInstance>();
export const loginData = reactive({
    loginForm: {
        username: "",
        password: ""
    }
})
export const loginRules = reactive ({
    username: [
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
                console.log(response.data);
            } catch (error) {
                console.error(error);
            }
        } else {
            console.log("验证失败");
        }
    })
}
