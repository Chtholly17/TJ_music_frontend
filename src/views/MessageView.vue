<template>
    <h3>
        用户消息
    </h3>
  <div class="user_message">
      <div class="aside">

      </div>
      <div class="message_list">
            <message-item></message-item>
          <message-item></message-item>
          <message-item></message-item>
          <message-item></message-item>
          <message-item></message-item>
          <message-item></message-item>
          <message-item></message-item>
      </div>
      <div class="message_body">

      </div>
      <div class="aside">

      </div>
  </div>
</template>

<script>
import MessageItem from "@/components/MessageItem.vue";

export default {
    name: "MessageView",
    components: {MessageItem}
}
</script>


<style scoped>

.user_message{
  display: flex;
}
.message_list{

}
.aside{
    width: 15%;
}
.message_list{
    width: 18%;
}

</style>
