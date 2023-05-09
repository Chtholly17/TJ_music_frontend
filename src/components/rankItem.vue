<template>
    <div class="accompanimentItem"
         :class="isHover? 'hoverItem': (props.index % 2) ? 'oddItem' : 'evenItem'"
         @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler">
        <div class="itemIndexBox">
            {{props.index + 1}}
        </div>
        <el-image :src="require('@/assets/'+props.cover)" class="coverBox"
                  fit="scale-down"></el-image>
        <!-- 注意require不支持直接引入变量，需要通过ES6字符串模板将变量转换为字符串 `${props.cover}` -->
        <!--      如果使用本地图片则需要:src="require('@/assets/'+props.cover)"-->
        <div class="nameBox">
            <el-text truncated size="large"> {{props.name}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.score}} </el-text>
        </div>
        <div class="singerBox">
            <el-text truncated size="large"> {{props.like}} </el-text>
        </div>
        <div class="durationBox">
            <el-text truncated size="large"> {{props.time}} </el-text>
        </div>
    </div>
</template>

<script setup>
import {defineProps, ref} from 'vue'
const props = defineProps(['cover','name', 'score', 'index', 'time','like','nickname'])
let isHover = ref(false)
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    isHover.value = false
}
</script>

<style  lang="scss" scoped>
.accompanimentItem{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  height: 60px;
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
.coverBox{
  padding: 2%;
  width: 10%;
  height: 100%
}
.itemIndexBox{
  width: 10%
}
.nameBox{
  width: 25%;
  text-align: left;
}
.singerBox{
  width: 25%;
  text-align: left;
}
.durationBox{
  width: 15%
}
</style>
