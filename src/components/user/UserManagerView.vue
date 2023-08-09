<template>
  <h1 style="margin-bottom: 5vh;margin-top: 5vh">用户列表
  <el-button type="primary" @click="groupSend()" style="padding: auto;">群发</el-button>
</h1>
<div>
    <div class="accompanimentItem">
        <div class="itemIndexBox">
            <el-text truncated size="large">
            <h3>序号</h3>
            </el-text>
        </div>
        <div class="imageBox">
            <el-text truncated size="large"> <h3> 头像 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 学号 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 昵称 </h3>  </el-text>
        </div>
        <div class="genderBox">
            <el-text truncated size="large"> <h3> 性别 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 创建时间 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 用户状态 </h3>  </el-text>
        </div>
        <div class="buttonBox">
            <el-text truncated size="large"> <h3> 操作 </h3>  </el-text>
        </div>
    </div>
    <user_manager_item v-for="(item,index) in userManager" :key="item"
                        :userStudentNumber="item.userStudentNumber" :userNickname="item.userNickname" :userGender="item.userGender"
                        :userProfileImageFilename = "item.userProfileImageFilename" :userStatus="item.userStatus"
                        :index="index" :createTime="item.createTime.substr(0,10)"></user_manager_item>
</div>
</template>

<script>
import {onBeforeMount, ref, getCurrentInstance} from "vue";
import {fetchUserManager} from "@/utils/Texts/UserManager";
import user_manager_item from "@/components/user/UserManagerItem.vue"

export default {
  name: "UserManager",
  components:{user_manager_item},
  setup(){

    const { proxy } = getCurrentInstance();

    const userManager=ref();
    onBeforeMount(()=>{
        fetchUserManager().then(res=>{
            userManager.value=res.data
            console.log(res)
        })
    })

    const groupSend=()=>{
        proxy.$EventBus.emit('postUser', {userStudentNumber: "all"});
    }

      return {
        userManager: userManager,
        groupSend
      }
  }
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
.genderBox{
    width: 5%;
    text-align: left;
}
.imageBox{
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
.buttonBox{
    width: 20%;
    text-align: left;
}
</style>
