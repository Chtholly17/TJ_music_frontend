<template>
    <div id="detailFrame">
        <div style="height:20px"></div>
        <div class="frameBox detailDiv">
            <div class="songDetailBox">
                <el-image :src="songDetails.originPrefaceFilename" class="coverBox" fit="scale-down"></el-image>
                <div style="height: 100%; width: 10%"></div>
                <div class="detailBox">
                    <div class="nameBox"><el-text truncated class="nameText">{{songDetails.originName}}</el-text></div>
                    <div class="singerBox">
                        <el-icon :size="17" class="userIcon"><User /></el-icon>
                        <el-text truncated class="singerText">{{songDetails.originAuthor}}</el-text></div>
                    <div class="introBox">{{songDetails.originIntroduction}}</div>
                    <div style="height: 100%"></div>
                    <div class="singBox"><el-button type="primary">我 也 要 唱</el-button></div>
                </div>
            </div>
        </div>
        <div style="height:20px"></div>
    </div>
    <div class="frameBox">
        <div class="worksItemBox">
            <el-divider></el-divider>
            <div class="worksItemTitleBox">
                <el-text class="worksItemTitleText">翻唱作品</el-text>
            </div>
            <el-divider></el-divider>
            <works-item v-for="(works, index) in songWorksInfoList" :key="works"
                        :profile="works.work.workPrefaceFilename"
                        :nickname="works.userNickname" :score="works.work.workScore"
                        :likes="works.work.workLike" :date="works.work.createTime"
                        :index="index"></works-item>
        </div>
    </div>
</template>

<script>
import {User} from "@element-plus/icons";
import WorksItem from "@/components/worksItem.vue";
import {onBeforeRouteLeave, useRoute} from "vue-router";
import api from "@/service";
import {ElMessage} from "element-plus";
import {accompanimentInfoList, songWorksInfoList} from "@/utils/Texts/accompanimentText";
import {onBeforeMount} from "vue";

export default {
    name: "detailView",
    components: {WorksItem, User},
    setup() {
        const queryParams = useRoute().query;
        const getSongDetail = () => {
            for(let i = 0; i < accompanimentInfoList.length; ++i) {
                if (String(accompanimentInfoList[i].originId) === queryParams.originId){
                    return accompanimentInfoList[i]
                }
            }
        }
        const getSongWorks = () => {
            api.getWorksById(queryParams).then((response) => {
                if (response.data.code === 1) {
                    while (songWorksInfoList.length > 0) {
                        songWorksInfoList.pop()
                    }
                    // console.log(response.data.data.length)
                    for (let i = 0; i < response.data.data.length; ++i) {
                        const iter = response.data.data[i];
                        songWorksInfoList.push(iter)
                    }
                    for (let i = 0; i < songWorksInfoList.length; ++i) {
                        const iter = songWorksInfoList[i];
                        iter.work.createTime = iter.work.createTime.substring(0,10)
                    }
                } else {
                    ElMessage.error(response.data.msg)
                }
            })
            // return ref(
            //     {
            //         name: '雪distance',
            //         cover: 'profile.jpg',
            //         singer: 'capper',
            //         intro: '太好听了呜呜呜呜呜呜呜呜，你听：在这么冷的天别离我那么远再靠近我一点可是雪 飘进双眼看不见你桥牌的谎言心甘情愿囚禁在深渊舔舐着伤口和刀尖可是雪 飘进双眼看不见你桥牌的谎言心甘情愿囚禁在深渊烧一捧雪花做硝烟',
            //         works: [
            //             {
            //                 profile: 'profile.jpg',
            //                 nickname: '孙笑川',
            //                 date: '2023-02-03',
            //                 score: '5.28',
            //                 likes: '114'
            //             },
            //             {
            //                 profile: 'profile.jpg',
            //                 nickname: '孙笑川不爱吃香菜',
            //                 date: '2020-02-03',
            //                 score: '99.90',
            //                 likes: '1145999'
            //             },
            //             {
            //                 profile: 'profile.jpg',
            //                 nickname: '孙笑川已黑化已有闺hhhhhh哈哈哈哈哈哈',
            //                 date: '2022-02-03',
            //                 score: '88.90',
            //                 likes: '1145'
            //             }
            //         ]
            //     }
            // )
        }
        let songDetails = getSongDetail()
        onBeforeMount(getSongWorks)
        onBeforeRouteLeave(() => {
            // 离开时，清空数组
            for(let i = 0; i < songWorksInfoList.length; ++i)
                songWorksInfoList.pop()
        })
        // onBeforeUpdate(()=>{
        //     getSongWorks();
        //     songDetails = getSongDetail()
        // })
        return {
            songWorksInfoList,
            songDetails
        }
    }
}
</script>

<style lang="scss" scoped>
@import "@/assets/fonts/font.scss";
#detailFrame{
    background: linear-gradient(#DD3399, #ffffff);
    background-size: 100% 100%;
}

.frameBox{
    width: 60%;
    min-width: 350px;
    height: 100%;
    text-align: center;
    margin: 0 auto;
    padding: 0 10px 0;
    background-color: #ffffff77;
}
.detailDiv{
    border-radius: 20px;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.15);
}
.songDetailBox{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 85%;
    height: 300px;
    padding: 10px;
    margin: 0 auto;
    *{
        font-family: "Poppins", '微软雅黑 Light', sans-serif !important;
    }
    .coverBox{
        width: 35%;
        height: 100%;
    }
    .detailBox{
        display: flex;
        flex-direction: column;
        width: 55%;
        height: 100%;
        .nameBox{
            height: 35px;
            display: flex;
            align-items: center;
            width: 100%;
            margin: 5% 0 0;
            .nameText{
                font-size: 30px !important;
                display: flex;
                align-items: center;
            }
        }
        .singerBox{
            height: 20px;
            display: flex;
            align-items: center;
            width: 100%;
            margin: 10px 0 20px;
            .singerText{
                font-size: 17px !important;
                color: #888888;
                margin: 0 10px 0;
            }
        }
        .introBox{
            height: 100px;
            text-align: left;
            font-size: 14px;
        }
        .singBox{
            height: 20%;
            padding: 0 0 5%;
            text-align: right;
        }
    }
}
.userIcon{
    color: #888888;
}
.worksItemBox{
    width: 80%;
    margin: 0 auto;
}
.worksItemTitleBox{
    margin: 20px auto;
}
.worksItemTitleText{
    font-size: 20px;
    letter-spacing: 2em;
}
</style>
