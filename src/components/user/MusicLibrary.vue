<template>
    <h1 style="margin-bottom: 5vh;margin-top: 5vh">我的曲库</h1>
  <div>
      <div class="accompanimentItem">
          <div class="itemIndexBox">
              <el-text truncated size="large">
              <h3>序号</h3>
              </el-text>
          </div>
          <div class="nameBox">
              <el-text truncated size="large"><h3> 歌曲名</h3> </el-text>
          </div>
          <div class="nameBox">
              <el-text truncated size="large"> <h3> 评分</h3>  </el-text>
          </div>
          <div class="singerBox">
              <el-text truncated size="large"> <h3> 点赞数</h3>  </el-text>
          </div>
          <div class="durationBox">
              <el-text truncated size="large"> <h3> 创建时间</h3>  </el-text>
          </div>
      </div>
      <music_library_item v-for="(item,index) in music_library" :key="item"
                          :name="item.workName" :score="item.workScore"
                          :index="index" :like="item.workLike" :time="item.createTime.substr(0,10)" @click="player_router"></music_library_item>
  </div>
</template>

<script>
import {onBeforeMount, ref} from "vue";
import {fetchMusicLibrary} from "@/utils/Texts/MusicLibrary";
import music_library_item from "@/components/user/musicLibraryItem.vue"

export default {
    name: "MusicLibrary",
    components:{music_library_item},
    methods:{
        player_router(){
                this.$router.push('/music_player')
        }
    },
    setup(){
        const music_library=ref();
        onBeforeMount(()=>{
            fetchMusicLibrary().then(res=>{
                music_library.value=res
            })
        })

        return {
            music_library
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
.durationBox{
    width: 15%
}
</style>
