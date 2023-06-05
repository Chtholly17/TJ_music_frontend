<template>
    <div class="accompanimentItem"
         :class="isHover? 'hoverItem': (props.index % 2) ? 'oddItem' : 'evenItem'"
         @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler">
        <div class="itemIndexBox">
            {{props.index + 1}}
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.userStudentNumber}} </el-text>
        </div>
        <div class="singerBox">
            <el-text truncated size="large"> {{props.appealContent}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.appealStatus}} </el-text>
        </div>
        <div class="buttonBox">
            <el-button type="success" @click="allow()">允许</el-button>
            <el-button type="warning" @click="reject()">拒绝</el-button>
        </div>
    </div>
</template>

<script setup>
import {defineProps, ref} from 'vue'
import axios from "axios";
import path from "@/service/path";
const props = defineProps(['userStudentNumber', 'appealContent', 'appealStatus', 'index'])
let isHover = ref(false)
const allow = () => {
    let form = new FormData();
    form.append("userStudentNumber", props.userStudentNumber);
    form.append("appealStatus", "pass");
    axios.post(path.baseUrl + path.allowAppealById, form).then((res) => {
        console.log(res);
    }).catch((err) => {
        console.log(err);
    })
}
const reject = () => {
    let form = new FormData();
    form.append("userStudentNumber", props.userStudentNumber);
    form.append("appealStatus", "reject");
    axios.post(path.baseUrl + path.rejectAppealById, form).then((res) => {
        console.log(res);
    }).catch((err) => {
        console.log(err);
    })
}
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
    width: 10%;
}
.nameBox{
    width: 10%;
    text-align: left;
}
.singerBox{
    width: 50%;
    text-align: left;
}
.introductionBox{
    width: 10%
}
.genderBox{
    width: 5%;
    text-align: left;
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
    width: 15%;
    text-align: left;
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
