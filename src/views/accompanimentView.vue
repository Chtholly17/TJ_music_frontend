<template>
    <div id="accompanimentFrame">
        <div id="accompanimentBox">
            <div style="height: 10px"></div>
            <div id="searchResult">搜索结果</div>
            <div id="searchResultNumber">
                <el-divider content-position="left">共找到&nbsp;{{itemsLength}}&nbsp;条结果</el-divider>
            </div>
<!--            <div class="accompanimentItem" id="accompanimentHeader">-->
<!--                <div class="itemIndexBox"></div>-->
<!--                <div class="coverBox"></div>-->
<!--                <div class="nameBox"><el-text truncated size="large"> 歌名 </el-text></div>-->
<!--                <div class="singerBox"><el-text truncated size="large"> 歌手 </el-text></div>-->
<!--                <div class="durationBox"><el-text truncated size="large"> 时长 </el-text></div>-->
<!--            </div>-->
            <accompaniment-item v-for="(item, index) in accompanimentInfoList" :key="item.originId"
                                :cover="item.originPrefaceFilename" :name="item.originName" :singer="item.originAuthor"
                                :index="index" :duration="'4:30'" :id="item.originId"></accompaniment-item>
            <div style="height: 10px"></div>
        </div>
    </div>
</template>

<script>
import AccompanimentItem from "@/components/accompanimentItem.vue";
import {onBeforeRouteUpdate, useRoute} from 'vue-router'
import api from "@/service";
import {ElMessage} from "element-plus";
import {onBeforeMount, ref} from "vue";
import {accompanimentInfoList} from "@/utils/Texts/accompanimentText";

export default {
    name: "accompanimentView",
    components: {AccompanimentItem},
    setup() {
        // let accompanimentInfoList = [{
        //     originPrefaceFilename: 'profile.jpg',
        //     originName: 'test222222',
        //     originAuthor: 'author',
        //     originDuration: '0'
        // }];
        const itemsLength = ref(0);
        //首先在setup中定义
        const getAccompanimentInfo = async () => {
            try {
                const response = await api.postSearchAccompanimentByKeyword(keyWord)
                if (response.data.code === 1) {
                    ElMessage.success("查找成功！");

                    while (accompanimentInfoList.length > 0) {
                        accompanimentInfoList.pop()
                    }
                    for (let i = 0; i < response.data.data.length; ++i) {
                        const iter = response.data.data[i];
                        accompanimentInfoList.push(iter)
                    }
                    itemsLength.value = response.data.data.length;
                } else {
                    ElMessage.error(response.data.msg)
                }
            } catch (error) {
                console.log(error)
                ElMessage.error(error.code + ': 提交失败，请检查网络或联系管理员')
            }
        }
        const route = useRoute();
        let keyWord = route.query;
        onBeforeMount(getAccompanimentInfo)
        onBeforeRouteUpdate((to, from, next) => { // 当路由在本界面进行跳转时，需要调用此钩子函数进行重新渲染
            if(to.name === from.name) {
                next()
                keyWord = to.query;
                getAccompanimentInfo()
            }
            else {
                next()
            }
        })
        return {
            keyWord,
            accompanimentInfoList,
            itemsLength,
        }
    }
}
</script>

<style lang="scss" scoped>
#accompanimentFrame{
    //background: url("@/assets/Login/LoginBackground.jpg");
    //width: 100%;
    //background-size: 100% 100%;
}
#accompanimentBox{
    width: 60%;
    min-width: 350px;
    height: 100%;
    text-align: center;
    margin: 0 auto;
    padding: 0 5px 0;
    background-color: white;
}
#searchResult{
    letter-spacing: 2em;
    font-size: 25px;
    margin: 10px auto 10px
}
#searchResultNumber{
    //color: #aaaaaa;
    //text-align: left;
    margin: 20px 10px 0;
}
#accompanimentHeader{
    font-weight: bold;
    height: 30px;
}
</style>

<style src="@/assets/CSS/accompaniment.scss" lang="scss" scoped>
</style>
