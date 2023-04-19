// 包含Login表单所需的数据类型form，约束rules以及处理函数commit
import {reactive, ref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import store from "@/store";
import {closeAllDialogs} from "@/utils/DialogVisible";

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
    // 注意：这个await很可能并没有处于等待状态！
    await baseForm.value.validate( async (valid: any) => {
        if (valid) {
            try {
                const response = await api.postLogin(loginData.loginForm); // 不能传入submitForm！
                console.log(response.data);
                if (response.data.code == 1){
                    store.commit('setUserID', loginData.loginForm.userNumber)
                    ElMessage.success("登陆成功！")
                    closeAllDialogs()
                    if(baseForm.value)
                        baseForm.value.resetFields() // 清空表单，关闭所有弹窗
                    // TODO：进行路由跳转
                }
                else{
                    ElMessage.error(response.data.msg)
                }
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}
