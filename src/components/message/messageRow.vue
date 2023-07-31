<template>
    <div class="rowBox">
        <div class="profileColumn">
            <el-avatar :size="50" :src="profilePath" fit="cover"></el-avatar>
        </div>
        <div class="bubbleColumn">
            <div class="bubbleBox">
                <div class="bubbleText">{{props.content}}</div>
            </div>
        </div>
        <div class="marginColumn">
        </div>
    </div>
</template>

<script setup lang="ts">
import {computed, defineProps} from 'vue';
import {chatStudentNumber} from "@/utils/chatParams";
import store from "@/store";

const props = defineProps(['senderProfile', 'senderId', 'content']) // 发送者头像，ID（判断样式），内容
const profilePath = props.senderId === chatStudentNumber.value?
    props.senderProfile: computed(()=>store.getters.getUserPhoto).value;
const type = props.senderId === chatStudentNumber.value? "row": "row-reverse";    // 根据发送者的不同来设置这些样式
const bubbleColor = props.senderId === chatStudentNumber.value? "#ffffff": "#409eff";
const textColor = props.senderId === chatStudentNumber.value? "#222222": "#fafafa";
</script>

<style lang="scss" scoped>
.profileColumn{
    // 对齐方式：顶端对齐
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    margin: 5px;
    width: 10%;
    height: 100%;
}
.bubbleColumn{
    display: flex;
    flex-direction: v-bind(type);
    width: 60%;
}
.bubbleBox{
    margin: 5px;
    max-width: 100%;
    display: flex;
    flex-direction: v-bind(type);
    border-radius: 10px;
    background-color: v-bind(bubbleColor);
    color: v-bind(textColor);
}
.bubbleText{
    width: 100%;
    padding: 15px 10px; // 使用text内容将高度撑起来
    text-align: left;
    word-break:break-all;
}
.marginColumn{
    width: 30%;
    height: 100%;
    margin: 5px;
}
.rowBox{
    display: flex;
    flex-direction: v-bind(type);
}
</style>
