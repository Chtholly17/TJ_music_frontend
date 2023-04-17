<template>
    <div class="user_music_library">
        <p>我的曲库</p>
        <el-divider></el-divider>
        <el-scrollbar height="400px" >
            <p v-for="(item,index) in user_music" :key="index" class="scrollbar-demo-item">
                <span style="justify-content: flex-start;display: flex">&nbsp;&nbsp;{{index+1}}.{{item.music_name}}&nbsp;&nbsp; -- {{item.singer}}</span>
                <span style="justify-content: flex-end;display: flex">{{item.date}}</span>
            </p>
        </el-scrollbar>
    </div>
</template>

<script>
export default {
    name: "UserMusicLibrary",
    data(){
        return{
            user_music:[
                {
                    music_name:"青花瓷",
                    singer:"周杰伦",
                    date:"2023-4-10"
                },
                {
                    music_name:"东风破",
                    singer:"周杰伦",
                    date:"2023-4-9"
                },
                {
                    music_name:"花海",
                    singer:"周杰伦",
                    date:"2023-4-8"
                },
                {
                    music_name:"千里之外",
                    singer:"周杰伦/费玉清",
                    date:"2023-4-7"
                },
                {
                    music_name:"园游会",
                    singer:"周杰伦",
                    date:"2023-4-6"
                },
                {
                    music_name:"晴天",
                    singer:"周杰伦",
                    date:"2023-4-5"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                },
                {
                    music_name:"稻香",
                    singer:"周杰伦",
                    date:"2023-4-4"
                }
            ]
        }
    }
}
</script>

<style scoped>
.scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 50px;
    width: 850px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-primary-light-9);
    color: var(--el-color-primary);
    padding-left: 30px;
    padding-right: 30px;
}
.user_music_library{
//background-color: aquamarine;
    /*position: absolute;*/
    /*bottom: 0;*/
}
</style>