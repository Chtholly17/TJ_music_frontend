<template>
  <h1 style="margin-bottom: 5vh;margin-top: 5vh">用户申诉</h1>
<div>
    <div class="accompanimentItem">
        <div class="nameBox">
            <el-text truncated size="large">
            <h3>序号</h3>
            </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 学号 </h3>  </el-text>
        </div>
        <div class="singerBox">
            <el-text truncated size="large"> <h3> 申诉原因 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 申诉状态 </h3>  </el-text>
        </div>
        <div class="nameBox">
            <el-text truncated size="large"> <h3> 操作 </h3>  </el-text>
        </div>
    </div>
    <user_info_item v-for="(item,index) in appeal" :key="item"
                        :userStudentNumber="item.userStudentNumber" 
                        :appealContent = "item.appealContent" :appealStatus="item.appealStatus" 
                        :index="index"></user_info_item>
</div>
</template>

<script>
import {onBeforeMount, ref} from "vue";
import {fetchAppeal} from "@/utils/Texts/Appeal";
import user_info_item from "@/components/user/UserInformItem.vue"

export default {
  name: "UserInform",
  components:{user_info_item},
  setup(){
      const appeal=ref();
      onBeforeMount(()=>{
        fetchAppeal().then(res=>{
          appeal.value=res.data
          console.log(res)
        })
      })

      return {
        appeal
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
    width: 50%;
    text-align: left;
}
.introductionBox{
    width: 10%
}
.buttonBox{
    width: 15%;
    text-align: left;
}
</style>
