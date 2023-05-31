import {ref, reactive, computed} from "vue";
import api from "@/service";
import store from "@/store";
import {ElMessage} from "element-plus";

export const chatNickname = ref("")
export const chatProfile = ref("")
export const chatStudentNumber = ref("")

export const getDetailMessage = async () => {
    const response = await api.getDetailMessage(chatStudentNumber.value,
        computed(() => store.getters.getUserID).value)
    if(response.data.code != 1){
        ElMessage.error("获取消息失败，请检查网络或联系管理员")
    }
    else {
        const length1 = messageList.length;
        for(let i = 0; i < length1; ++i)
            messageList.pop()
        const length2 = response.data.data.length;
        for(let i = 0; i < length2; ++i)
            messageList.push(response.data.data.shift())
    }
}

interface messageType {
    sender_student_number: string,
    message_content: string,
    message_time: string,
}
export const messageList: messageType[] = reactive([])
