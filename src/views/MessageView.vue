<template>
    <div class="user_message">
        <div class="message_list">
           <message-item  v-if="new_chat" :nickname="target_nickname" :user-image="target_img" last_message=""
                          index="0" :user_id="now_chat_id"></message-item>
           <message-item v-for="(item,index) in chat_object" :key="item"
                        :nickname="item.nickname" :userImage="item.profile_image_filename"
                        :last_message="item.last_message_content" :index="index"
                        :user_id="item.student_number">

           </message-item>
        </div>
        <div class="message_body">
            <message-detail v-if="chatStudentNumber" :key="chatStudentNumber"></message-detail>
        </div>
    </div>
</template>

<script lang="ts">
import MessageItem from "@/components/MessageItem.vue";
import MessageDetail from "@/components/MessageDetail.vue";
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
        interface chat_object_type{
            last_message_content:string,
            nickname:string,
            profile_image_filename:string,
            student_number:string
        }

        console.log(chatStudentNumber.value)
        const chat_object:chat_object_type[]=reactive([])
        //const chat_object=ref()
        const user_id=ref()
        const new_chat=ref(true)
        const now_chat_id=ref(router.currentRoute.value.query.target_id)
        const target_nickname=ref()
        const target_img=ref()

        onBeforeMount(()=>{
            user_id.value=store.getters.getUserID
            api.getMessageObject(user_id.value).then(res=>{
                //chat_object.value=res.data.data
                // console.log("获取数据")
                // console.log(res.data.data)

                while (chat_object.length>0){
                    chat_object.pop()
                }
                for (let i=0;i<res.data.data.length;i++){
                    const iter=res.data.data[i];
                    chat_object.push(iter)
                }


                if(now_chat_id.value==='')
                    new_chat.value=false
                if(new_chat.value===true) {
                    for (const item of chat_object) {
                        if (item.student_number === now_chat_id.value) {
                            new_chat.value = false
                            break
                        }
                    }
                }
                // console.log("获取完消息队列")
            })
            api.getUserInfo(now_chat_id.value).then(res=>{
                target_nickname.value=res.data.data.userNickname;
                target_img.value=res.data.data.userProfileImageFilename;
            })
            // console.log("onBeforeMount执行完毕")
        })
        setInterval(()=>{
            user_id.value=store.getters.getUserID
            api.getMessageObject(user_id.value).then(res=>{
                while (chat_object.length>0){
                    chat_object.pop()
                }
                for (let i=0;i<res.data.data.length;i++){
                    const iter=res.data.data[i];
                    chat_object.push(iter)
                }

                if(now_chat_id.value==='')
                    new_chat.value=false
                // console.log("获取聊天用户列表")
                // console.log(chat_object.value)
                if(new_chat.value===true) {
                    for (const item of chat_object) {
                        if (item.student_number === now_chat_id.value) {
                            new_chat.value = false
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
            chat_object,
            chatStudentNumber,
            new_chat,
            target_nickname,target_img,now_chat_id
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
