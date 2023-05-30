<template>
  <div class="a_message" :class="isHover? 'hoverItem':  'oddItem'"
       @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler" @click="itemClickHandler">
<!--  <div class="a_message">-->
      <div class="user_image_box">
          <el-image :src="props.userImage" class="user_image"
                    ></el-image>
      </div>
      <div class="name_last_box">
          <div class="nicknamebox">
              <el-text>{{ string_control(props.nickname) }}</el-text>
          </div>
          <div class="last_message_box">
              <el-text>{{string_control(props.last_message)}}</el-text>
          </div>
      </div>
  </div>
</template>

<script setup>
import {defineProps, ref} from "vue";
import router from "@/router";
import {ElMessage} from "element-plus";
const props = defineProps(['nickname','userImage','last_message','index','user_id'])  //昵称，头像，最后一条消息,下标
function string_control(str) {
    if (str.length > 20) {
        return str.slice(0, 20) + "..."
    }
    return str
}

const isHover = ref(false)
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    isHover.value = false
}
// const itemClickHandler = () => {
//     if (props.user_id)
//         router.push({ path: '/detail', query: { originId: props.user_id }})
//     else
//         ElMessage.error( "表项无跳转，请联系管理员")
// }

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
    width: 80px;
    height:80px;
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

</style>
