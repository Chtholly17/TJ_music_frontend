// 本文件用于管理登录/注册/找回密码的三个弹窗的显示。
// 由于el-dialog v-model是双向绑定，而vuex数据只读，故新建ts文件专门管理。
// 三个visible对应三个弹窗的显示与否；三个函数控制三个弹窗的显示操作。

import {ref} from "vue";

export const registerDialogVisible = ref<boolean>(false)
export const loginDialogVisible = ref<boolean>(false)
export const retrieveDialogVisible = ref<boolean>(false)
export const showRegisterDialog = ()=> {
    registerDialogVisible.value = true
    loginDialogVisible.value = false
    retrieveDialogVisible.value = false
}
export const showLoginDialog = ()=> {
    registerDialogVisible.value = false
    loginDialogVisible.value = true
    retrieveDialogVisible.value = false
}
export const showRetrieveDialog = ()=> {
    registerDialogVisible.value = false
    loginDialogVisible.value = false
    retrieveDialogVisible.value = true
}
export const closeAllDialogs = () => {
    registerDialogVisible.value = false
    loginDialogVisible.value = false
    retrieveDialogVisible.value = false
}

//用于管理显示修改密码页面
export const showUpdatePassword=ref(false)
