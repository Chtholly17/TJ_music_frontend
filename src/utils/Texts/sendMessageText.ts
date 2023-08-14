import {reactive} from "vue";

interface sendMessageType{
    senderStudentNumber: string,
    receiverStudentNumber: string,
    content: string
}

export const message:sendMessageType = reactive({
    senderStudentNumber: "",
    receiverStudentNumber:"",
    content:""
})
