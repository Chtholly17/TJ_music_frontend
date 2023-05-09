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
        <div class="singerBox">
            <el-icon :size="20" class="userIcon"><User /></el-icon>&nbsp;&nbsp;
            <el-text truncated size="large"> {{props.singer}} </el-text>
        </div>
        <div class="durationBox">
            <el-text truncated size="large"> {{props.duration}} </el-text>
        </div>
    </div>
</template>

<script setup>
import {defineProps, ref} from 'vue'
import {User} from "@element-plus/icons";
const props = defineProps(['cover', 'name', 'singer', 'index', 'duration'])
let isHover = ref(false)
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    isHover.value = false
}
</script>

<style src="@/assets/CSS/accompaniment.scss" lang="scss" scoped>
</style>
