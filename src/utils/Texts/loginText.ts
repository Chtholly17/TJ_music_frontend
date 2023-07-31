// 包含Login表单所需的数据类型form，约束rules以及处理函数commit
import {computed, reactive, ref} from "vue";
import {ElMessage, FormInstance} from "element-plus";
import api from "@/service";
import store from "@/store";
import {closeAllDialogs} from "@/utils/DialogVisible";
import router from "@/router";
import {setCookie} from "@/service/cookie";
import {isLoggedIn} from "@/utils/BarVisible";
import loginDisabled from "@/components/preface/LoginForm.vue";

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
        loginDisabled.value = true;
        if (valid) {
            try {
                const response = await api.postLogin(loginData.loginForm); // 不能传入submitForm！
                if (response.data.code == 1){
                    store.commit('setUserID', loginData.loginForm.userNumber)
                    // ElMessage.success("登陆成功！")
                    await getUserProfile()
                    isLoggedIn.value = true;
                    closeAllDialogs()
                    // 存储cookie
                    setCookie("userNumber", loginData.loginForm.userNumber, 7)
                    setCookie("password", loginData.loginForm.password, 7)
                    if(baseForm.value)
                        baseForm.value.resetFields() // 清空表单，关闭所有弹窗
                    await router.replace({path: '/music_square'})
                }
                else if(response.data.code == 0){
                    ElMessage.error("用户不存在，请检查是否已经注册~")
                }
                else if(response.data.code == 2){
                    ElMessage.error("密码错误！")
                }
                else if(response.data.code == 3){
                    ElMessage.error("登陆失败，账号被封禁！")
                }
                else {
                    ElMessage.error("未知错误！错误码："+response.data.code)
                }
            } catch (error: any) {
                ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
            }
        } else {
            ElMessage.error('验证失败，请检查数据是否完整且正确')
        }
    })
}

export const commitLogin_cookie = async () => {
    const response = await api.postLogin(loginData.loginForm);
    if (response.data.code == 1){
        store.commit('setUserID', loginData.loginForm.userNumber)
        isLoggedIn.value = true;
        closeAllDialogs()
        await getUserProfile()
        // 手动清空表单
        loginData.loginForm.userNumber = "";
        loginData.loginForm.password = "";
        if(router.currentRoute.value.path == "/hello")
            await router.replace("/music_square")
    }
    else {
        await router.replace("/hello")
    }
}

const getUserProfile = async () => {
    const userID = computed(() => store.getters.getUserID).value
    const response = await api.getUserImage({user_student_number: userID})
    if (response.data.code == 1){
        store.commit('setUserPhoto', response.data.data)
        // console.log("获取用户头像")
        // console.log(response.data.data)
    }
}
