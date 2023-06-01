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
<!--          <h2 style="text-align: left;margin-bottom: 1vh">{{rank_name}}</h2>-->
          <h2 style="text-align: left">{{rank_name}}</h2>
<!--          <p style="text-align: left;margin-bottom: 1vh">排序方式</p>-->
          <p style="text-align: left">排序方式</p>
          <div style="display: flex">
              <el-radio-group v-model="rank_type" size="medium ">
                  <el-radio-button label="点赞数" @click="change_type(1)" />
                  <el-radio-button label="评论数" @click="change_type(2)"/>
                  <el-radio-button label="粉丝数" @click="change_type(3)"/>
              </el-radio-group>
          </div>

          <div class="myItem">
              <div class="itemIndexBox"></div>
              <div class="coverBox"></div>
              <div class="nameBox">
                  <el-text truncated size="large"> 歌名 </el-text>
              </div>
              <div class="nameBox">
                  <el-text truncated size="large"> 作者 </el-text>
              </div>
              <div class="nameBox">
                  <el-text truncated size="large"> 评分 </el-text>
              </div>

              <div class="singerBox">
                  <el-text truncated size="large"> 点赞数 </el-text>
              </div>
              <div class="nameBox">
                  <el-text truncated size="large"> 评论数 </el-text>
              </div>
              <div class="nameBox">
                  <el-text truncated size="large"> 粉丝数 </el-text>
              </div>
          </div>
        <rank-item v-for="(item,index) in all_rank" :key="item" :cover="item.workPrefaceFilename" :name="item.workName"
                   :score="item.score_ave" :index="index" :like="item.like" :nickname="item.workAuthorNickname"
                   :comments_num="item.workCommentCnt" :fans_num="item.workAuthorFans"
                    ></rank-item>

      </div>
  </div>
</template>

<script lang="ts">
import {onBeforeMount, ref} from "vue";
import {fetchRankList} from "@/utils/Texts/rankList";
import RankItem from "@/components/rankItem.vue";

export default {
    name: "rankView",
    components: {RankItem},
    setup(){
        const rank_name=ref();  //排行榜名
        const rank_type=ref( );  //排序类型
        const all_rank=ref();   //具体的排行榜
        const real_type=ref();  //真实的type，将"点赞数"这种改成"like"
        const tpye_send=ref(true);  //每次点击change_type都出触发两次，用这个变量过滤一次
        //const rank_radio=ref(); //
        onBeforeMount(()=>{
            rank_name.value="民谣榜"
            rank_type.value='点赞数'
            real_type.value="like"

            fetchRankList(rank_name.value.substring(0,2),real_type.value).then(res=>{
               all_rank.value=res
                for(const  item of all_rank.value){
                    item.score_ave=(item.workQualityScore+item.workPreciseScore+item.workPitchScore)/3;
                    item.score_ave=item.score_ave.toFixed(2)
                }

            })
        })

        function real_change(name:any,type:any)
        {
            fetchRankList(name.substring(0,2),type).then(res=>{
                all_rank.value=res
                //console.log(all_rank.value)
            })
        }

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
            real_change(rank_name.value,real_type.value)
        }

        function change_type(id:any)
        {
           if(id==1)
           {
               real_type.value="like"
           }
           else if(id==2)
           {
               real_type.value="comment"
           }
           else if(id==3)
           {
               rank_type.value="fans"
           }
           if(tpye_send.value==true) {
               //console.log("change_type发送")
               //console.log(real_type.value)
               real_change(rank_name.value, real_type.value)
               tpye_send.value=false
           }
           else {
               tpye_send.value=true
           }
        }

        return{
            rank_name,
            change_list,
            rank_type,
            all_rank,
            change_type

        }
    }
}
</script>

<style scoped>

.rank_body{
    display: flex;
    margin-top: 10vh;
    height: 100%;
    overflow: hidden;
}
.rank_body>.aside{
    margin-left: 20vh;
    width: 20vh;
}
.rank_body>.rank_list{
    margin-left: 5vh;
    width: 100%;
    height: 100%;
}
.rank_item{
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 7vh;
}
.myItem{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    height: 60px;
    //overflow: auto;
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

</style>
