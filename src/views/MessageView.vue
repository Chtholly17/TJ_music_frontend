<template>
    <div class="user_message">
        <div class="message_list">
           <message-item  v-if="newChat" :nickname="targetNickname" :user-image="targetImg" last_message=""
                          index="0" :userId="nowChatId"></message-item>
           <message-item v-for="(item,index) in chatObject" :key="item"
                        :nickname="item.nickname" :userImage="item.profile_image_filename"
                        :last_message="item.last_message_content" :index="index"
                        :userId="item.student_number">

           </message-item>
        </div>
        <div class="message_body">
            <message-detail v-if="chatStudentNumber" :key="chatStudentNumber"></message-detail>
        </div>
    </div>
</template>

<script lang="ts">
import MessageItem from "@/components/message/MessageItem.vue";
import MessageDetail from "@/components/message/MessageDetail.vue";
import {onBeforeMount, reactive, ref} from "vue";
import api from "@/service";
import store from "@/store";
import {onBeforeRouteLeave} from "vue-router";
import {chatNickname, chatStudentNumber, chatProfile} from "@/utils/chatParams";
import router from "@/router";

export default {
    name: "MessageView",
    components: {MessageDetail, MessageItem},
    setup()
    {
        interface chatObjectType {
            last_message_content:string,
            nickname:string,
            profile_image_filename:string,
            student_number:string
        }

        //console.log(chatStudentNumber.value)
        const chatObject:chatObjectType[]=reactive([])
        //const chatObject=ref()
        const userId=ref()
        const newChat=ref(true)
        const nowChatId=ref(router.currentRoute.value.query.target_id)
        const targetNickname=ref()
        const targetImg=ref()

        onBeforeMount(()=>{
            userId.value=store.getters.getUserID
            api.getMessageObject(userId.value).then(res=>{
                //chatObject.value=res.data.data
                // console.log("获取数据")
                // console.log(res.data.data)

                while (chatObject.length>0){
                    chatObject.pop()
                }
                for (let i=0;i<res.data.data.length;i++){
                    const iter=res.data.data[i];
                    chatObject.push(iter)
                }


                if(nowChatId.value==='')
                    newChat.value=false
                if(newChat.value===true) {
                    for (const item of chatObject) {
                        if (item.student_number === nowChatId.value) {
                            newChat.value = false
                            break
                        }
                    }
                }
                // console.log("获取完消息队列")
            })
            api.getUserInfo(nowChatId.value).then(res=>{
                targetNickname.value=res.data.data.userNickname;
                targetImg.value=res.data.data.userProfileImageFilename;
            })
            // console.log("onBeforeMount执行完毕")
        })
        setInterval(()=>{
            userId.value=store.getters.getUserID
            api.getMessageObject(userId.value).then(res=>{
                while (chatObject.length>0){
                    chatObject.pop()
                }
                for (let i=0;i<res.data.data.length;i++){
                    const iter=res.data.data[i];
                    chatObject.push(iter)
                }

                if(nowChatId.value==='')
                    newChat.value=false
                // console.log("获取聊天用户列表")
                // console.log(chatObject.value)
                if(newChat.value===true) {
                    for (const item of chatObject) {
                        if (item.student_number === nowChatId.value) {
                            newChat.value = false
                            break
                        }
                    }
                }
            })
        },1000)
        onBeforeRouteLeave(()=>{
            chatNickname.value = "";
            chatProfile.value = "";
            chatStudentNumber.value = "";
        })
        return{
            chatObject: chatObject,
            chatStudentNumber,
            newChat: newChat,
            targetNickname: targetNickname,targetImg: targetImg,nowChatId: nowChatId
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

    min-width: 330px;
    height: 80vh;
    margin: 0 1%;
    overflow-y: scroll;
}
.message_body{
    min-width: 800px;
    height: 100%;
}
</style>
