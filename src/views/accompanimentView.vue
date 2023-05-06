<template>
    <div id="accompanimentFrame" :key="keyWord">
        <div id="accompanimentBox">
            <div id="searchResult">搜索结果</div>
            <div id="searchResultNumber"> 共找到{{accompanimentInfo.length}}条结果 >></div>
            <div class="accompanimentItem" id="accompanimentHeader">
                <div class="itemIndexBox"></div>
                <div class="coverBox"></div>
                <div class="nameBox"><el-text truncated size="large"> 歌名 </el-text></div>
                <div class="singerBox"><el-text truncated size="large"> 歌手 </el-text></div>
                <div class="durationBox"><el-text truncated size="large"> 时长 </el-text></div>
            </div>
            <accompaniment-item v-for="(item, index) in accompanimentInfo" :key="item"
                                :cover="item.cover" :name="item.name" :singer="item.singer"
                                :index="index" :duration="item.duration"></accompaniment-item>
        </div>
    </div>
</template>

<script>
import AccompanimentItem from "@/components/accompanimentItem.vue";
import {ref} from "vue";
import {onBeforeRouteUpdate, useRoute} from 'vue-router'
import api from "@/service";

export default {
    name: "accompanimentView",
    components: {AccompanimentItem},
    setup() {
        //首先在setup中定义
        const getAccompanimentInfo = () => {
            api.postSearchAccompaniment(keyWord)
            return ref([
                {
                    name: '雪distance',
                    cover: 'profile.jpg',
                    singer: 'capper',
                    duration: '03:28'
                },
                {
                    name: '只因你太美',
                    cover: 'profile.jpg',
                    singer: 'SWINS',
                    duration: '03:28'
                },
                {
                    name: '夜に駆ける',
                    cover: 'profile.jpg',
                    singer: 'YASOBI',
                    duration: '03:28'
                },
            ])
        }
        const route = useRoute()
        let keyWord = route.query;
        let accompanimentInfo = getAccompanimentInfo()
        onBeforeRouteUpdate((to, from, next) => { // 当路由在本界面进行跳转时，需要调用此钩子函数进行重新渲染
            if(to.name === from.name) {
                next()
                keyWord = to.query;
                accompanimentInfo = getAccompanimentInfo()
            }
            else {
                next()
            }
        })
        return {
            keyWord,
            accompanimentInfo
        }
    }
}
</script>

<style lang="scss" scoped>
#accompanimentFrame{
    //background: url("@/assets/Login/LoginBackground.jpg");
    //width: 100%;
    //height: 100%;
    //position: fixed;
    //background-size: 100% 100%;
}
#accompanimentBox{
    width: 60%;
    text-align: center;
    margin: 0 auto;
}
#searchResult{
    font-size: 25px;
    margin: 10px auto 10px
}
#searchResultNumber{
    color: #aaaaaa;
    text-align: left;
    margin: 10px auto 10px
}
#accompanimentHeader{
    font-weight: bold;
    font-family: "宋体", sans-serif !important; // 好像不能覆盖？
    height: 30px;
}
</style>

<style src="@/assets/CSS/accompaniment.scss" lang="scss" scoped>
</style>
