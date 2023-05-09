<template>
    <div class="wrapper">

        <div class="carousel"><!--轮播图-->
            <el-carousel height="300px" type="card">
                <el-carousel-item v-for="(item, index) in list_img" :key="index">
                    <h3 class="small">
                        <img :src="item.url" alt />
                    </h3>
                </el-carousel-item>
            </el-carousel>
        </div>

        <div class="list"><!--歌曲列表-->
            <el-row :span="200" v-for="item in 5" :key="item">
                <el-col :span="200" v-for="item in 5" :key="item">
                        <el-card :body-style="{ padding:'0px'}">
                            <img
                                src='../assets/cxk4.png'
                                class="image"
                            />
                        </el-card>
                </el-col>

            </el-row>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
export default {
    name: 'music_square',
    functional: true,
    setup() {
        const list_img= [
            { url: require('../assets/cxk1.jpg') },
            { url: require('../assets/cxk2.jpeg') },
            { url: require('../assets/cxk3.jpeg') },
            { url: require('../assets/cxk1.jpg') },
            { url: require('../assets/cxk2.jpeg') },
            { url: require('../assets/cxk3.jpeg') },
        ];
        return{
            list_img
        }
    }
}
</script>

<style>
.wrapper
{
    padding:0;
    margin:0;
    overflow-y:scroll;
}

.carousel {
    margin: 0 auto;
    width: 1200px;
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
    width: 1100px;
}

.list el-row {
    margin: 50px;
}

.image {
    margin: 0 auto;
    width: 200px;
    height:200px;
}
</style>
