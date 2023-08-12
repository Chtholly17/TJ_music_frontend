<template>
  <div class="rank_body">
      <div class="aside">
          <h1>曲风榜</h1>
          <el-menu>
              <el-menu-item  class="rank_item" @click="changeList(1)">
                  <h3>民谣榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="changeList(2)">
                  <h3>摇滚榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="changeList(3)">
                  <h3>流行榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="changeList(5)">
                  <h3>电子榜</h3>
              </el-menu-item>
          </el-menu>
          <h1>精选榜</h1>
          <el-menu>
              <el-menu-item class="rank_item" @click="changeList(6)">
                  <h3>热歌榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="changeList(7)">
                  <h3>新歌榜</h3>
              </el-menu-item>
              <el-menu-item class="rank_item" @click="changeList(8)">
                  <h3>飙升榜</h3>
              </el-menu-item>
          </el-menu>
      </div>
      <div class="rank_list">
<!--          <h2 style="text-align: left;margin-bottom: 1vh">{{rankName}}</h2>-->
          <h2 style="text-align: left">{{rankName}}</h2>
<!--          <p style="text-align: left;margin-bottom: 1vh">排序方式</p>-->
          <p style="text-align: left">排序方式</p>
          <div style="display: flex">
              <el-radio-group v-model="rankType" size="medium ">
                  <el-radio-button label="点赞数" @click="changeType(1)" />
                  <el-radio-button label="评论数" @click="changeType(2)"/>
                  <el-radio-button label="粉丝数" @click="changeType(3)"/>
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
        <rank-item style="cursor: pointer" v-for="(item,index) in allRank" :key="item" :cover="item.workPrefaceFilename" :name="item.workName"
                   :score="item.score_ave" :index="index" :like="item.like" :nickname="item.workAuthorNickname"
                   :comments_num="item.workCommentCnt" :fans_num="item.workAuthorFans" @click="playRouter(item.workId)"
                    ></rank-item>

      </div>
  </div>
</template>

<script lang="ts">
import {onBeforeMount, ref} from "vue";
import {fetchRankList} from "@/utils/Texts/rankList";
import RankItem from "@/components/item/rankItem.vue";
import router from "@/router";

export default {
    name: "rankView",
    components: {RankItem},
    setup(){
        const rankName=ref();  //排行榜名
        const rankType=ref( );  //排序类型
        const allRank=ref();   //具体的排行榜
        const realType=ref();  //真实的type，将"点赞数"这种改成"like"
        const tpyeSend=ref(true);  //每次点击change_type都出触发两次，用这个变量过滤一次
        //const rank_radio=ref(); //
        onBeforeMount(()=>{
            rankName.value="民谣榜"
            rankType.value='点赞数'
            realType.value="like"

            fetchRankList(rankName.value.substring(0,2),realType.value).then(res=>{
               allRank.value=res
                for(const  item of allRank.value){
                    console.log(item)
                    item.score_ave=(item.work_quality_score+item.work_precise_score+item.work_pitch_score)/3;
                    item.score_ave=item.score_ave.toFixed(2)
                }

            })
        })

        function realChange(name:any, type:any)
        {
            fetchRankList(name.substring(0,2),type).then(res=>{
                allRank.value=res
                for(const  item of allRank.value){
                    item.score_ave=(item.work_quality_score+item.work_precise_score+item.work_pitch_score)/3;
                    item.score_ave=item.score_ave.toFixed(2)
                }
            })
        }

        function changeList(id:any)    //改变榜单
        {
            if(id===1){
                rankName.value="民谣榜"
            }
            else if(id===2){
                rankName.value="摇滚榜"
            }
            else if(id===3){
                rankName.value="流行榜"
            }
            else if(id===5){
                rankName.value="电子榜"
            }
            else if(id===6){
                rankName.value="热歌榜"
            }
            else if(id===7){
                rankName.value="新歌榜"
            }
            else if(id===8){
                rankName.value="飙升榜"
            }
            realChange(rankName.value,realType.value)
        }

        function changeType(id:any)
        {
           if(id==1)
           {
               realType.value="like"
           }
           else if(id==2)
           {
               realType.value="comment"
           }
           else if(id==3)
           {
               realType.value="fans"
           }
           if(tpyeSend.value==true) {
               //console.log("change_type发送")
               //console.log(realType.value)
               realChange(rankName.value, realType.value)
               tpyeSend.value=false
           }
           else {
               tpyeSend.value=true
           }
        }

        function playRouter(id:any){
            router.push({path: '/music_player',query: {id: id}});
        }

        return{
            rankName: rankName,
            changeList: changeList,
            rankType: rankType,
            allRank: allRank,
            changeType: changeType,
            playRouter: playRouter

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
