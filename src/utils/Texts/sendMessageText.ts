import {reactive} from "vue";

interface sendMessageType{
    sender_student_number: string,
    receiver_student_number: string,
    content: string
}

export const message:sendMessageType = reactive({
    sender_student_number: "",
    receiver_student_number:"",
    content:""
})
