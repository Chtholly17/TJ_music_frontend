<template>
    <div id="chatMessageBox">
        <div id="nickNameBox">
            <div id="nickNameText">
                {{props.nickName}}
            </div>
        </div>
        <div id="chatBox">
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
          <h1>haha</h1>
        </div>
    </div>
</template>

<script setup lang="ts">
import {defineProps} from "vue";

const props = defineProps(['nickName'])
</script>

<style scoped>
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
}
#nickNameText{
    font-family: "Poppins", '微软雅黑 Light', sans-serif;
    font-size: 18px;
}
</style>
