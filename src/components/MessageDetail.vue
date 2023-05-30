<template>
  <div class="messages">
      <chat-messages :nickName=props.nickName></chat-messages>
  </div>
  <div class="messageBuffer">
      <chat-message-buffer :id=props.id></chat-message-buffer>
  </div>
</template>

<script lang="ts" setup>
import ChatMessageBuffer from "@/components/ChatMessageBuffer.vue";
import {defineProps} from "vue";
import ChatMessages from "@/components/ChatMessages.vue";
const props = defineProps(['id', 'nickName'])
</script>

<style scoped>
.messages{
    margin: 1% 0;
    height: 66%;
}
.messageBuffer{
    margin: 1%;
    height: 32%;
}
</style>
