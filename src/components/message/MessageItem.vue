<template>
  <div class="a_message" :class="isFouse?'hoverItem':isHover? 'hoverItem':  'oddItem'"
       @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler" @click="itemClickHandler">
<!--  <div class="a_message">-->
      <div class="user_image_box">
          <el-avatar :src="props.userImage" class="user_image"></el-avatar>
      </div>
      <div class="name_last_box">
          <div class="nicknamebox">
              <el-text truncated class="nickname">{{ props.nickname}}</el-text>
          </div>
          <div class="last_message_box">
              <el-text truncated class="last_message">{{props.last_message}}</el-text>
          </div>
      </div>
  </div>

</template>

<script setup>
import {defineProps, onBeforeMount, ref} from "vue";
import {chatNickname, chatProfile, chatStudentNumber} from "@/utils/chatParams";

const props = defineProps(['nickname','userImage','last_message','index','user_id'])  //昵称，头像，最后一条消息,下标


const isHover = ref(false)
const isFouse=ref(false)
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    if(chatStudentNumber.value!==props.user_id)
        isHover.value = false
}
const itemClickHandler = () => {
    chatNickname.value = props.nickname;
    chatProfile.value = props.userImage;
    chatStudentNumber.value = props.user_id;
    isFouse.value=true
}

onBeforeMount(()=>{
    if(chatStudentNumber.value===props.user_id)
        isFouse.value=true
    // console.log(props.nickname)
    // console.log(isFouse.value)
})

</script>

<style scoped>
.a_message{
    display: flex;
    width: 100%;
    align-items: center;
    //justify-content: center;
    cursor: pointer;

}

.user_image_box{
    position: relative;
    display: flex;
    overflow: hidden;
    /* 设置容器宽高 */
    min-width: 80px;
    min-height:80px;
    align-items: center;
    justify-content: center;
}

.user_image{
    border-radius: 50%;
    height: 50px;
    width: 50px;
    align-items: center;
}

.last_message_box{
    text-align: left;
}

.nicknamebox{
    text-align: left;
}

.oddItem{
    background-color: white;
}
.evenItem{
    background-color: #fafafa;
}
.hoverItem{
    transition: background-color 0.15s;
    background-color: #f1f1f1;
}

.nickname{
    font-size: 16px;
    width: 200px; /* 容器宽度 */
    font-weight: bold

}

.last_message{
    font-size: 14px;
    width: 200px; /* 容器宽度 */
    color: #888888;
}

</style>
