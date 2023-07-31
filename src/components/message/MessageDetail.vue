<template>
  <div class="messages">
      <chat-messages></chat-messages>
  </div>
  <div class="messageBuffer">
      <chat-message-buffer></chat-message-buffer>
  </div>
</template>

<script lang="ts" setup>
import ChatMessageBuffer from "@/components/message/ChatMessageBuffer.vue";
import ChatMessages from "@/components/message/ChatMessages.vue";

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
