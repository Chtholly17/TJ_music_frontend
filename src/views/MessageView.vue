<template>
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
            <message-detail id="20170101" nickName="JL子"></message-detail>
        </div>
    </div>
</template>

<script setup lang="ts">
import MessageItem from "@/components/MessageItem.vue";
import MessageDetail from "@/components/MessageDetail.vue";
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
    height: 100%;
    margin: 0 1%;
}
.message_body{
    width: 73%;
    height: 100%;
    margin: 0 1%;
}
</style>
