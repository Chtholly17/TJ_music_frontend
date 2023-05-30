<template>
    <div>
        <el-input v-model="message.content" @input=inputHandler id="inputArea"
                  maxlength="500" placeholder="输入点什么……" rows="6"
                  show-word-limit type="textarea" resize="none"></el-input>
        <el-button id="sendButton" type="primary" bg @click="sendMessage"
                   :disabled="sendDisable" :icon="Position"></el-button>
    </div>
</template>

<script lang="ts" setup>
import {computed, defineProps, ref} from "vue";
import {Position} from "@element-plus/icons-vue";
import {message} from "@/utils/Texts/sendMessageText";
import store from "@/store";
import api from "@/service";

const props = defineProps(['id'])

const sendDisable = ref(true)
const inputHandler = () => {
    sendDisable.value = (message.content == "");
}
const sendMessage = async () => {
    message.receiver_student_number = props.id;
    message.sender_student_number = computed(()=>store.getters.getUserID).value;
    await api.postMessage(message);
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
