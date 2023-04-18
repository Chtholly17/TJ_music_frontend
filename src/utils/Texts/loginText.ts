// 包含Login表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import store from "@/store";

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
    if (!baseForm.value)
        return
    await baseForm.value.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postLogin(loginData.loginForm); // 不能传入submitForm！
                console.log(response.data);
                if (response.data.code == 1)
                {
                    store.commit('setUserID', loginData.loginForm.userNumber)
                    ElMessage.success("登陆成功！")
                    location.reload()
                }
                else
                {
                    ElMessage.error("登陆失败！请检查密码是否正确及是否已注册！")
                }
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
