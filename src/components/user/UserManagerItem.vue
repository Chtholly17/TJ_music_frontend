<template>
    <div class="accompanimentItem"
         :class="isHover? 'hoverItem': (props.index % 2) ? 'oddItem' : 'evenItem'"
         @mouseover="itemMouseOverHandler" @mouseleave="itemMouseLeaveHandler">
        <div class="itemIndexBox">
            {{props.index + 1}}
        </div>
        <div class="imageBox">
            <img :src=props.userProfileImageFilename alt="图片">
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.userStudentNumber}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.userNickname}} </el-text>
        </div>
        <div class="genderBox">
            <el-text truncated size="large"> {{props.userGender}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.createTime}} </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> {{props.userStatus}} </el-text>
        </div>
        <div class="buttonBox">
            <!-- <el-button v-if="props.userStatus==1" type="warning" @click="updateUserStatus()">禁用</el-button>
            <el-button v-if="props.userStatus==0" type="primary" @click="updateUserStatus()">启用</el-button> -->
            <el-button type="info" @click="sendMsg()">发送信息</el-button>
            <el-button type="warning" @click="deleteUser()">删除</el-button>
        </div>
    </div>
</template>

<script setup>
import {defineProps, ref, getCurrentInstance} from 'vue'
import axios from "axios";
import path from "@/service/path";
const props = defineProps(['userProfileImageFilename', 'userStudentNumber', 'userNickname', 'userCollege', 'createTime', 'userStatus', 'userGender', 'index'])
const { proxy } = getCurrentInstance()
let isHover = ref(false)
const deleteUser = () => {
    let form = new FormData();
    form.append("studentNumber", props.userStudentNumber);
    axios.post(path.baseUrl + path.deleteUserById, form).then((res) => {
        console.log(res);
    }).catch((err) => {
        console.log(err);
    })
}
const sendMsg = () => {
    proxy.$EventBus.emit('postUser', {userStudentNumber: props.userStudentNumber});
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
    width: 15%;
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
    width: 90px;
    height: 70px;
}
.buttonBox{
    width: 20%;
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
