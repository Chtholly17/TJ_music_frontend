<template>
    <div class="wrapper">
        <br/>
        <div><span style="font-size:25px;font-weight: 200">热门歌手</span></div>
        <div style="height: 30px"></div>
            <div class="carousel"><!--轮播图-->
                <el-carousel height="300px" type="card">
                    <el-carousel-item v-for="(item, index) in carouselImg" :key="index">
                        <h3 class="small">
                            <img :src="item.url" alt />
                        </h3>
                    </el-carousel-item>
                </el-carousel>
            </div>

        <div class="list"><!--歌曲列表-->
            <el-divider />
            <div><span style="font-size:25px;">动态广场</span></div>
            <el-divider />
            <el-row :gutter="10" v-for="item in 1" :key="item">
                <el-col :span="200" v-for="(item, index) in workMessage" :key="index">
                    <el-card shadow="hover" style="background:rgba(255,255,255,0.5);">
                        <img
                            :src="item.workPrefaceFilename"
                            class="image"

                            @click="playerRouter(item.workId)"

                        />
                        <div><span>{{item.workName}}</span></div>
                    </el-card>
                    <br/>
                </el-col>

            </el-row>
        </div>
    </div>
</template>

<script>
import {computed,onBeforeMount, ref} from 'vue'
import {fetchMusicSquare} from "@/utils/Texts/musicSquare";
import {store} from "@/store";
import router from "@/router";
export default {
    name: 'music_square',
    functional: true,
    methods:{
        playerRouter(id){
            // console.log(id)
            router.push({path: '/music_player',query: {id: id}});
        }
    },
    setup() {
        const workMessage=ref();

        onBeforeMount(()=>{
            fetchMusicSquare(100).then(res=>{
                workMessage.value=res
                // console.log(workMessage.value)
                })
        })

        //轮播图图片
        const carouselImg= [
            { url: require('../assets/material/img1.jpg') },
            { url: require('../assets/material/img2.jpg') },
            { url: require('../assets/material/img3.jpg') },
            { url: require('../assets/material/img4.jpg') },
            { url: require('../assets/material/img5.jpg') },
            { url: require('../assets/material/img6.jpg') },
        ];
        return{
            carouselImg: carouselImg,
            workMessage: workMessage
        }
    }
}
</script>

<style scoped>
.wrapper
{
    padding:0;
    margin:0;
    background: linear-gradient(to right bottom, rgba(255, 133, 234, 0.5), rgba(0, 255, 236, 0.5));
}

.carousel {
    margin: 0 auto;
    background: #ffffff66;
    padding: 30px 20px;
    width: 1100px;
    border-radius: 10px;
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 1;
    margin: 0;
    text-align: center;
}

.small img {
    width: 100%;
    height: 100%;
}

.list
{
    margin: 0 auto;
    padding-top:20px;
    width: 1000px;
}
.image {
    margin: 0 auto;
    width: 200px;
    height: 200px;
}
</style>
