<template>
    <h3>
        用户消息
    </h3>
    <div class="user_message">
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
            <message-detail id="20170101"></message-detail>
        </div>
    </div>
</template>

<script>
import MessageItem from "@/components/MessageItem.vue";
import MessageDetail from "@/components/MessageDetail.vue";

export default {
    name: "MessageView",
    components: {MessageDetail, MessageItem}
}
</script>

<style scoped>

.user_message{
    display: flex;
    width: 70%;
    margin: 0 auto;
}
.message_list{
    width: 23%;
    margin: 0 1%;
}
.message_body{
    width: 73%;
    margin: 0 1%;
}
</style>
