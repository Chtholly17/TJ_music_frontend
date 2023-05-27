<template>
  <div class="a_message">
      <div class="user_image_box">
          <el-image :src="'http://49.4.115.48:8888/20170101/images/avatar.jpg'" class="user_image"
                    ></el-image>
      </div>
      <div class="name_last_box">
          <div class="nicknamebox">
              <el-text>114514114514114514114514114</el-text>
          </div>
          <div class="last_message_box">
              <el-text>{{56898}}</el-text>
          </div>
      </div>
  </div>
</template>

<script setup>
import {defineProps} from "vue";
const props = defineProps(['nickname','userImage','last_message'])  //昵称，头像，最后一条消息

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
    /*margin-top: 7px;*/
    /*margin-left: 15px;*/
    border-radius: 50%;
    height: 50px;
    width: 50px;
    align-items: center;
    //cursor: pointer;
    /*position: absolute;*/
    /*top: 50%;*/
    /*left: 50%;*/
    /*!* 将图片移到容器中央 *!*/
    /*transform: translate(-50%, -50%);*/
    /*!* 设置圆形裁剪 *!*/
    /*clip-path: circle(50% at center);*/
}

.last_message_box{
    text-align: left;
}

</style>
