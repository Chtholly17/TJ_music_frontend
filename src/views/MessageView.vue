<template>
    <div class="user_message">
        <div class="message_list">
           <message-item v-for="(item,index) in chat_object" :key="item.last_message_content"
                        :nickname="item.nickname" :userImage="item.profile_image_filename"
                        :last_message="item.last_message_content" :index="index"
                        :user_id="item.student_number"></message-item>
        </div>
        <div class="message_body">
            <message-detail v-if="chatStudentNumber" :key="chatStudentNumber"></message-detail>
        </div>
    </div>
</template>

<script lang="ts">
import MessageItem from "@/components/MessageItem.vue";
import MessageDetail from "@/components/MessageDetail.vue";
import {onBeforeMount, ref} from "vue";
import api from "@/service";
import store from "@/store";
import {onBeforeRouteLeave} from "vue-router";
import {chatNickname, chatStudentNumber, chatProfile} from "@/utils/chatParams";

export default {
    name: "MessageView",
    components: {MessageDetail, MessageItem},
    setup()
    {
        console.log(chatStudentNumber.value)
        const chat_object=ref()
        const user_id=ref()
        onBeforeMount(()=>{
            user_id.value=store.getters.getUserID
            api.getMessageObject(user_id.value).then(res=>{
                chat_object.value=res.data.data
                // console.log("获取聊天用户列表")
                // console.log(chat_object.value)
            })
        })
        setInterval(()=>{
            user_id.value=store.getters.getUserID
            api.getMessageObject(user_id.value).then(res=>{
                // chat_object.value[1]=res.data.data[1]
                chat_object.value=res.data.data
                // console.log("获取聊天用户列表")
                // console.log(chat_object.value)
            })
        },1000)
        onBeforeRouteLeave(()=>{
            chatNickname.value = "";
            chatProfile.value = "";
            chatStudentNumber.value = "";
        })
        return{
            chat_object,
            chatStudentNumber
        }
    }
}
</script>

<style scoped>

.user_message{
    display: flex;
    width: 70%;
    margin: 0 auto;
    padding: 3vh 0 0;
    height: 88vh;
}
.message_list{
    width: 23%;
    min-width: 200px;
    height: 100%;
    margin: 0 1%;
    overflow-y: scroll;
}
.message_body{
    width: 73%;
    min-width: 700px;
    height: 100%;
}
</style>
