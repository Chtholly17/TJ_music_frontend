<template>
    <div id="chatMessageBox">
        <div id="nickNameBox">
            <div id="nickNameText">
                {{chatNickname}}
            </div>
        </div>
        <div id="chatBox">
          <message-row v-for="item in messageList" :key="item"
              :sender-profile="chatProfile" :sender-id="item.sender_student_number" :content="item.message_content"></message-row>
        </div>
    </div>
</template>

<script setup lang="ts">
import {chatNickname, messageList, chatProfile, getDetailMessage} from "@/utils/chatParams";
import MessageRow from "@/components/messageRow.vue";
import {onBeforeMount} from "vue";

onBeforeMount(getDetailMessage);
</script>

<style lang="scss" scoped>
#chatMessageBox{
    display: flex;
    flex-direction: column;
    height: 100%;
}
#nickNameBox{
    background: #ffffff;
    height: 13%;
    margin: 0 1% 1%;
    border-color: #eeeeee;
    border-style: solid;
    border-width: 1px;
    display: flex;
    align-items: center;
    justify-content: space-around;
}
#chatBox{
    background: #f8f8f8;
    height: 85%;
    margin: 0 1%;
    overflow-y: auto;
    display: flex;
    flex-direction: column-reverse;
}
#nickNameText{
    font-family: "Poppins", '微软雅黑 Light', sans-serif;
    font-size: 18px;
}
</style>
