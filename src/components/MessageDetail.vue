<template>
  <div class="messages">
      <chat-messages></chat-messages>
  </div>
  <div class="messageBuffer">
      <chat-message-buffer :id=props.id></chat-message-buffer>
  </div>
</template>

<script lang="ts" setup>
import ChatMessageBuffer from "@/components/ChatMessageBuffer.vue";
import {defineProps} from "vue";
import ChatMessages from "@/components/ChatMessages.vue";
const props = defineProps(['id'])
</script>

<style scoped>
.messages{
    height: 68%;
}
.messageBuffer{
    height: 32%;
}
</style>
