<template>
  <div class="rank_body">
      <div class="aside">
          <h1>曲风榜</h1>
          <el-menu>
              <el-menu-item  class="rank_item" @click="change_list(1)">
                  <h3>民谣榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(2)">
                  <h3>摇滚榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(3)">
                  <h3>金属榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(4)">
                  <h3>古典榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(5)">
                  <h3>电子榜</h3>
              </el-menu-item>
          </el-menu>
          <h1>精选榜</h1>
          <el-menu>
              <el-menu-item class="rank_item" @click="change_list(6)">
                  <h3>热歌榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(7)">
                  <h3>新歌榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="change_list(8)">
                  <h3>飙升榜</h3>
              </el-menu-item>
          </el-menu>
      </div>
      <div class="rank_list">
          <h2 style="text-align: left;margin-bottom: 1vh">{{rank_name}}</h2>
          <p style="text-align: left;margin-bottom: 1vh">排序方式</p>
          <div style="display: flex">
              <el-radio-group v-model="rank_type" size="medium ">
                  <el-radio-button label="点赞数" />
                  <el-radio-button label="评论数" />
                  <el-radio-button label="作者粉丝数" />
              </el-radio-group>
          </div>

      </div>
  </div>
</template>

<script lang="ts">
import {onBeforeMount, ref} from "vue";

export default {
    name: "rankView",
    setup(){
        const rank_name=ref();  //排行榜名
        const rank_type=ref( );  //排序类型
        //const rank_radio=ref(); //
        onBeforeMount(()=>{
            rank_name.value="民谣榜"
            rank_type.value='点赞数'
        })
        function change_list(id:any)    //改变榜单
        {
            if(id===1){
                rank_name.value="民谣榜"
            }
            else if(id===2){
                rank_name.value="摇滚榜"
            }
            else if(id===3){
                rank_name.value="金属榜"
            }
            else if(id===4){
                rank_name.value="古典榜"
            }
            else if(id===5){
                rank_name.value="电子榜"
            }
            else if(id===6){
                rank_name.value="热歌榜"
            }
            else if(id===7){
                rank_name.value="新歌榜"
            }
            else if(id===8){
                rank_name.value="飙升榜"
            }
        }
        return{
            rank_name,
            change_list,
            rank_type

        }
    }
}
</script>

<style scoped>

.rank_body{
    display: flex;
    margin-top: 10vh;
}
.rank_body>.aside{
    margin-left: 20vh;
    width: 20vh;
}
.rank_body>.rank_list{
    margin-left: 5vh;
}
.rank_item{
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 7vh;
}
</style>
