<template v-slot:default="props">
    <div class="accompanimentItem"
         :class="isHover? 'hoverItem': (props.index % 2) ? 'oddItem' : 'evenItem'"
         @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler">
        <div class="itemIndexBox">
            {{props.index + 1}}
        </div>
        <div class="imageBox">
            <img :src=props.originPrefaceFilename alt="图片">
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.originName}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.originAuthor}} </el-text>
        </div>
        <div class="singerBox">
            <audio controls style="width: 150px;">
                <source :src=props.originVoiceFilename type="audio/mpeg">
            </audio>
        </div>
        <div class="singerBox">
            <audio controls style="width: 150px;">
                <source :src=props.originBgmusicFilename type="audio/mpeg">
            </audio>
        </div>
        <div class="introductionBox">
            <el-button type="success" @click="showDetails()">详细信息</el-button>
        </div>
        <div class="buttonBox">
            <el-button type="primary" @click="editMusic()">编辑</el-button>
            <el-button type="primary" @click="deleteMusic()">删除</el-button>
        </div>

    </div>
</template>

<script setup>
import {defineProps, ref, getCurrentInstance} from 'vue'
import axios from "axios";
import path from "@/service/path";
const props = defineProps(['originName', 'originAuthor', 'originVoiceFilename', 'originBgmusicFilename', 'originIntroduction', 'originPrefaceFilename', 'originId', 'index'])
console.log(props)
let isHover = ref(false)
const { proxy } = getCurrentInstance()
const itemMouseOverHandler = () => {
    isHover.value = true
}
const itemMouseLeaveHandler = () => {
    isHover.value = false
}
const editMusic = () => {
    //index转换为string
    proxy.$EventBus.emit('editMusic', {originId : props.originId.toString()});
}
const deleteMusic = () => {
    //调用api
    axios.delete(path.baseUrl + path.deleteOriginMusic, {params: {originId : props.originId.toString()}}).then((res) => {
        console.log(res);
    }).catch((err) => {
        console.log(err);
    })
}
const showDetails = () => {
    proxy.$EventBus.emit('showDetails', {originId : props.originId.toString()});
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
    width: 10%;
    text-align: left;
}
.singerBox{
    width: 15%;
    text-align: left;
}
.introductionBox{
    width: 10%
}
.imageBox{
    width: 10%;
    text-align: left;
}
.imageBox img{
    width: 50%;
    height: 50%;
}
.buttonBox{
    width: 15%
}
.durationBox button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
