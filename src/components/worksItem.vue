<template>
    <div class="worksItem"
         :class="isHover? 'hoverItem': (props.index % 2) ? 'oddItem' : 'evenItem'"
         @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler" @click="workClickHandler">
        <div class="itemIndexBox">
            {{props.index + 1}}
        </div>
        <div class="profileBox">
            <el-image :src="props.profile" class="profileImg"
                      fit="scale-down"></el-image>
        </div>
        <!-- 注意require不支持直接引入变量，需要通过ES6字符串模板将变量转换为字符串 `${props.cover}` -->
        <!--      如果使用本地图片则需要:src="require('@/assets/'+props.cover)"-->
        <div class="nicknameBox">
            <el-text truncated> {{props.nickname}} </el-text>
        </div>
        <div class="scoreBox">
            <el-text truncated tag="b"> {{props.score}} </el-text>
        </div>
        <div class="likesBox">
            <div class="thumbBox">
                <icon-thumb-up :stroke-width="3"></icon-thumb-up>
            </div>
            &nbsp;
            <el-text truncated>{{props.likes}}</el-text>
        </div>
        <div class="dateBox">
            <el-text truncated> {{props.date}} </el-text>
        </div>
    </div>
</template>

<script setup>
import {defineProps, ref} from 'vue'
import router from "@/router";
const props = defineProps(['profile', 'nickname', 'score', 'likes', 'date', 'index', 'workId'])
let isHover = ref(false)
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    isHover.value = false
}
const workClickHandler = () => {
    router.push({path: '/music_player',query: {id: props.workId}});
}
</script>

<style scoped>
.worksItem{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 5px;
    height: 50px;
    width: 100%
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
.itemIndexBox{
    min-width: 8%;
}
.profileBox{
    display: flex;
    align-items: center;
    min-width: 8%;
}
.profileImg{
    width: 45px;
    height: 45px;
    border-radius: 50%;
}
.nicknameBox{
    display: flex;
    align-items: center;
    height: 100%;
    width: 30%;
    text-align: left;
}
.scoreBox{
    display: flex;
    align-items: center;
    height: 100%;
    width: 8%;
}
.likesBox{
    display: flex;
    align-items: center;
    height: 100%;
    min-width: 14%;
    text-align: left;
}
.thumbBox{
    height: 100%;
    min-width: 20px;
    display: flex;
    align-items: center;
}
.dateBox{
    display: flex;
    align-items: center;
    height: 100%;
    min-width: 14%;
}
</style>
