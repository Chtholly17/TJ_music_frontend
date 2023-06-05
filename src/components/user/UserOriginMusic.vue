<template>
  <h1 style="margin-bottom: 5vh;margin-top: 5vh">曲库列表
  <el-button type="primary" @click="createMusic()" style="padding: auto;">新建音乐</el-button>
</h1>
  <div>
      <div class="accompanimentItem">
          <div class="itemIndexBox">
              <el-text truncated size="large">
              <h3>序号</h3>
              </el-text>
          </div>
          <div class="imageBox">
              <el-text truncated size="large"> <h3> 背景图片 </h3>  </el-text>
          </div>
          <div class="nameBox">
              <el-text truncated size="large"><h3> 歌曲名</h3> </el-text>
          </div>
          <div class="nameBox">
              <el-text truncated size="large"> <h3> 作者</h3>  </el-text>
          </div>
          <div class="singerBox">
              <el-text truncated size="large"> <h3> 歌曲音乐</h3>  </el-text>
          </div>
          <div class="singerBox">
              <el-text truncated size="large"> <h3> 歌曲背景音乐</h3>  </el-text>
          </div>
          <div class="introductionBox">
              <el-text truncated size="large"> <h3> 音乐介绍 </h3>  </el-text>
          </div>
          <div class="buttonBox">
              <el-text truncated size="large"> <h3> 操作 </h3> </el-text>
          </div>
      </div>
      <music_library_item v-for="(item,index) in music_library" :key="item"
                          :originId = "item.originId"
                          :originName = "item.originName" :originAuthor = "item.originAuthor" 
                          :originVoiceFilename = "item.originVoiceFilename" :originBgmusicFilename = "item.originBgmusicFilename" 
                          :originIntroduction = "item.originIntroduction" :originPrefaceFilename = "item.originPrefaceFilename" :index="index"></music_library_item>
  </div>
</template>

<script>
import {onBeforeMount, ref, getCurrentInstance} from "vue";
import {fetchOriginMusic} from "@/utils/Texts/OriginMusic";
import music_library_item from "@/components/user/UserOriginItem.vue";
export default {
    name: "MusicLibrary",
    components:{music_library_item},
    setup(){
        const music_library=ref();

        const { proxy } = getCurrentInstance();
        onBeforeMount(()=>{
            fetchOriginMusic().then(res=>{
                music_library.value=res.data
                print(res)
                console.log(res)
            })
        })

        const createMusic=()=>{
            proxy.$EventBus.emit('editMusic',{originId:"-1"})
        }
        return {
            music_library,
            createMusic
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
    width: 10%;
    text-align: left;
}
.singerBox{
    width: 15%;
    text-align: left;
}
.imageBox{
    width: 10%;
    text-align: left;
}
.introductionBox{
    width: 10%
}
.buttonBox{
    width: 15%
}
</style>
