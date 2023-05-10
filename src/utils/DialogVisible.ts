// 本文件用于管理登录/注册/找回密码的三个弹窗的显示。
// 由于el-dialog v-model是双向绑定，而vuex数据只读，故新建ts文件专门管理。
// 三个visible对应三个弹窗的显示与否；三个函数控制三个弹窗的显示操作。

import {ref} from "vue";

export const RegisterDialogVisible = ref<boolean>(false)
export const LoginDialogVisible = ref<boolean>(false)
export const RetrieveDialogVisible = ref<boolean>(false)
export const showRegisterDialog = ()=> {
    RegisterDialogVisible.value = true
    LoginDialogVisible.value = false
    RetrieveDialogVisible.value = false
}
export const showLoginDialog = ()=> {
    RegisterDialogVisible.value = false
    LoginDialogVisible.value = true
    RetrieveDialogVisible.value = false
}
export const showRetrieveDialog = ()=> {
    RegisterDialogVisible.value = false
    LoginDialogVisible.value = false
    RetrieveDialogVisible.value = true
}
export const closeAllDialogs = () => {
    RegisterDialogVisible.value = false
    LoginDialogVisible.value = false
    RetrieveDialogVisible.value = false
}

//用于管理显示修改密码页面
export const show_update_password=ref(false)
