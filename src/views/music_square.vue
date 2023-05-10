<template>
    <div class="wrapper">

        <div class="carousel"><!--轮播图-->
            <el-carousel height="300px" type="card">
                <el-carousel-item v-for="(item, index) in carousel_img" :key="index">
                    <h3 class="small">
                        <img :src="item.url" alt />
                    </h3>
                </el-carousel-item>
            </el-carousel>
        </div>

        <div class="list"><!--歌曲列表-->
            <div><span style="font-size:25px;">动态广场</span></div>
            <br/>
            <el-row :gutter="10" v-for="item in 1" :key="item">
                <el-col :span="200" v-for="(item, index) in work_message" :key="index">
                    <el-card shadow="hover">
                        <img
                            :src="item.workPrefaceFilename"
                            class="image"
                            @click="player_router"
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
import {onBeforeMount, ref} from 'vue'
import {fetchMusicSquare} from "@/utils/Texts/musicSquare";
export default {
    name: 'music_square',
    functional: true,
    methods:{
        player_router(){
            this.$router.push('/music_player')
        }
    },
    setup() {
        const work_message=ref();

        onBeforeMount(()=>{
            fetchMusicSquare(100).then(res=>{
                work_message.value=res
                })
        })

        //轮播图图片
        const carousel_img= [
            { url: require('../assets/cxk1.jpg') },
            { url: require('../assets/cxk2.jpeg') },
            { url: require('../assets/cxk3.jpeg') },
            { url: require('../assets/cxk1.jpg') },
            { url: require('../assets/cxk2.jpeg') },
            { url: require('../assets/cxk3.jpeg') },
        ];
        //作品信息（图片、歌曲名）
        //（该说不说这里我有点不理解，是不是要后端给什么东西我才决定跳转到哪个歌曲详情页嘞）
        // const work_message=[
        //     { name: "只因你太美",url: require('../assets/cxk4.png')},
        //     { name: "情人",url: require('../assets/cxk5.jpg')},
        //     { name: "HUG ME",url: require('../assets/cxk6.jpg')},
        //     { name: "young",url: require('../assets/cxk7.jpg')},
        // ]
        return{
            carousel_img,
            work_message
        }
    }
}
</script>

<style>
.wrapper
{
    padding:0;
    margin:0;
}

.carousel {
    margin: 0 auto;
    padding-top:30px;
    width: 1100px;
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
