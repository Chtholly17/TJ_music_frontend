<template>
  <div style="height: 25px"></div>
  <h3 style="text-align: left">我的动态({{postnum}})</h3>
  <el-divider></el-divider>
  <div v-for="item in post_body" :key="item.post_id">
      <div class="common-layout">
          <el-container>
              <el-aside width="200px">Aside</el-aside>
              <el-main>Main</el-main>
          </el-container>
      </div>
  </div>
</template>

<script>
export default {
    name: "UserPostView",
    data(){
        return{
            postnum:1, //动态的数量
            user_photo:require("../../assets/profile.jpg"), //用户头像
            nickname:"日本天皇",
            post_body:[
                {
                    post_id:0,  //帖子的id
                    date:"2023-4-16", //发布日期
                    time:"9:29",  //发布时间
                    favorite_num:20,//收藏数量
                    comments_num:3, //评论数量
                    star_num:10,  //赞的个数
                }
            ]
        }
    }
}
</script>

<style scoped>

</style>
