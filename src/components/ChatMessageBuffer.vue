<template>
    <div>
        <el-input v-model="messageContent" @input=inputHandler id="inputArea"
                  maxlength="255" placeholder="输入点什么……" rows="6"
                  show-word-limit type="textarea" resize="none"></el-input>
        <el-button id="sendButton" type="primary" bg @click="sendMessage()"
                   :disabled="sendDisable" :icon="Position"></el-button>
    </div>
</template>

<script lang="ts" setup>
import {computed, ref} from "vue";
import {Position} from "@element-plus/icons-vue";
import {message} from "@/utils/Texts/sendMessageText";
import store from "@/store";
import api from "@/service";
import {ElMessage} from "element-plus";
import {chatStudentNumber} from "@/utils/chatParams";
import {getDetailMessage} from "@/utils/chatParams";

const sendDisable = ref(true)
const messageContent = ref("")
const inputHandler = () => {
    sendDisable.value = (messageContent.value == "");
}
const sendMessage = async () => {
    message.receiver_student_number = chatStudentNumber.value;
    // message.sender_student_number = computed(()=>store.getters.getUserID).value;
    message.sender_student_number = computed(()=>store.getters.getUserID).value;
    message.content = messageContent.value;
    messageContent.value = "";  // 清空消息
    const response = await api.postMessage(message);
    if (response.data.code == 0) {
        ElMessage.error("发送消息失败，请检查网络或联系管理员")
    }
    else {
        await getDetailMessage(); // 重新获取新的消息
    }
}
</script>

<style lang="scss" scoped>
#sendButton{
    margin: 3px;
    width: 30%;
}
#inputArea{
}
</style>
