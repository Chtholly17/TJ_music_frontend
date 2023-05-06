<template>
  <div class="searchBar">
      <el-input prefix-icon="Search" v-model="searchKeyword.keyWord" style="margin: 10px 5px 10px"></el-input>
      <el-button @click="searchHandler" style="margin: 10px 5px 10px">查找</el-button>
  </div>
</template>

<script>
import {Search} from "@element-plus/icons-vue";
import {reactive} from "vue";
import router from "@/router";
import {ElMessage} from "element-plus";
export default {
    name: "searchBar",
    setup() {
        const searchKeyword = reactive({
            keyWord: ""
        })
        const searchHandler = () => {
            if (searchKeyword.keyWord)
                router.push({ path: '/accompaniment', query: { keyWord: searchKeyword.keyWord }})
            else
                ElMessage("想好要查找什么再点我吧~")
        }
        return {
            Search,
            searchKeyword,
            searchHandler
        }
    }
}
</script>

<style lang="scss" scoped>
.searchBar{
    display: flex;
    align-items: center;
    width: 30%;
    height: 100%;
}
:deep(.el-input__inner){
    height: 35px;
    letter-spacing: 3px;
    font-size: 14px;
    padding: 0 4px;
} // 使用样式穿透的方法，可以改变不在引入文件中编写的组件样式
</style>
