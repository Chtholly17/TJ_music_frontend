<template>
    <div id="music">
        <div id="top">
            <span>歌曲详情</span>
            <div id="search">
                <!--                <input type="text" placeholder="请输入内容" v-on:keyup.enter = "search" v-model = "name"/>-->
                <el-input
                        style="width:250px"
                        class="s"
                        placeholder="在我的曲库中搜素："
                        v-model="input"
                >
                </el-input>
            </div>
        </div>
        <div id="cont">
            <div id="left">
                <!--                <img :src ="currentsong.img" >-->
                <!-- musicImgRotate与pause为CSS样式，ifPlaying与ifPaused为布尔值 -->
                <img  :src=currentsong.img
                      :class="{'musicImgRotate':ifPlaying,'pause':ifPaused}">
<!--                      :class="{'musicImgRotate':ifPlaying,'pause':ifPaused}">-->

                <!--                <p>{{currentsong.name}}{{"."}}{{currentsong.singer}}</p>-->
            </div>
            <div id="line"></div>
            <div id="right">
                <!--                <ul>-->
                <!--                    <li v-for="(item,i) in items" :key="i">-->
                <!--                        <img src="../assets/2.png" @click="playIt(i)"/>-->
                <!--                        <p>{{item.name}}{{"      ——"}}{{item.singer}}</p>-->
                <!--                    </li>-->

                <!--                </ul>-->
                <el-scrollbar height="80%">
                    <p v-for="(item,i) in items" :key="i" class="scrollbar-demo-item">{{item.name}}{{"      ——"}}{{item.singer}}</p>
                </el-scrollbar>
            </div>
        </div>
        <div id="bottom">
            <!--            <audio :src="currentsong.url" controls="controls" style="width: 100%;"></audio>-->
            <audio @play="musicPlay" @pause="musicPause" controls :src="currentsong.url"  style="width:100%;"></audio>
        </div>
    </div>
</template>



<script>
import {Search} from '@element-plus/icons-vue'
import {ref} from "vue";
export default {
    name: "music_player",
    functional: true,
    // data() {
    //     return {
    //         input:"",
    //         items: [
    //             { name: "青花瓷" ,singer:"周杰伦"},
    //             { name: "东风破" ,singer:"周杰伦"},
    //             { name: "花海" ,singer:"周杰伦"},
    //             { name: "千里之外" ,singer:"周杰伦/费玉清"},
    //             { name: "园游会" ,singer:"周杰伦"},
    //             { name: "晴天" ,singer:"周杰伦"},
    //             { name: "稻香" ,singer:"周杰伦"},
    //             { name: "夜的第七章" ,singer:"周杰伦"},
    //             { name: "说好的幸福呢" ,singer:"周杰伦"},
    //             { name: "一路向北" ,singer:"周杰伦"},
    //             { name: "红尘客栈" ,singer:"周杰伦"},
    //             { name: "爱情废柴" ,singer:"周杰伦"},
    //             { name: "粉色海洋" ,singer:"周杰伦"}
    //         ],
    //         currentsong: {
    //             name: "锦鲤抄",
    //             singer: "银临",
    //             img:require("../assets/1.jpg"),
    //             url:require("../assets/1.mp3")
    //         },
    //         ifPlaying:false,
    //         ifPaused:true
    //     }
    // },
    setup(){
        const input="";
        const items=[
            { name: "青花瓷" ,singer:"周杰伦"},
            { name: "东风破" ,singer:"周杰伦"},
            { name: "花海" ,singer:"周杰伦"},
            { name: "千里之外" ,singer:"周杰伦/费玉清"},
            { name: "园游会" ,singer:"周杰伦"},
            { name: "晴天" ,singer:"周杰伦"},
            { name: "稻香" ,singer:"周杰伦"},
            { name: "夜的第七章" ,singer:"周杰伦"},
            { name: "说好的幸福呢" ,singer:"周杰伦"},
            { name: "一路向北" ,singer:"周杰伦"},
            { name: "红尘客栈" ,singer:"周杰伦"},
            { name: "爱情废柴" ,singer:"周杰伦"},
            { name: "粉色海洋" ,singer:"周杰伦"}
        ];
        const currentsong={
            name: "锦鲤抄",
            singer: "银临",
            img:require("../assets/1.jpg"),
            url:require("../assets/1.mp3")
        };
        let ifPlaying=ref(false);
        let ifPaused=ref(true);
        const musicPlay =()=> {
            //console.log("musicPlay");
            ifPlaying = true;
            ifPaused = false;
            console.log(ifPlaying);
        }
        const musicPause =()=>{
            //console.log("musicPause");
            // this.ifPlaying = false;
            ifPaused = true;
            ifPlaying = false;
            console.log(ifPlaying);
        }
        return {
            musicPlay,
            musicPause,
            Search,
            input,
            items,
            currentsong,
            ifPlaying,
            ifPaused
        }
    }
    // method()
    // {
    //     this.$refs.myAudio.play()
    // }
}</script>



<style>
*{
    padding:0;
    margin:0;
}

body{
    height: 100%;
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
}

#music{
    position: fixed;
    background-color: #fff;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    /*box-shadow: 2px 2px 4px #5e72e4;*/
}
#top{
    width: 100%;
    height: 15%;
    background-color:#5e72e4;
}
#top span{
    position: absolute;
    color: #fff;
    top:25px;
    left:80px;
    font-size:40px;

}
#search{
    position: absolute;
    right: 80px;
    top: 40px;
}

/*#search input{*/
/*    width: 180px;*/
/*    height: 24px;*/
/*    padding-left: 8px;*/
/*    border-radius: 12px;*/
/*    border: 1px solid #5e72e4;*/
/*    box-shadow: 1px 1px 2px #5c5c5f;*/
/*}*/

#cont{
    position: absolute;
    margin: 0;
    width: 100%;
    height: 78%;
}
#left{
    position: absolute;
    width: 60%;
    height: 100%;
}
#left img{
    position: absolute;
    left: 20%;
    top: 20%;
    height: 60%;
    /*width: auto;*/
}

.musicImgRotate {animation: rotate 6s linear infinite;}
@keyframes rotate {/* 这里从来没接触过 直接抄的。。。 */
    0% {
        transform: rotateZ(0deg);
        /*从0度开始*/
    }
    100% {
        transform: rotateZ(360deg);
        /*360度结束*/
    }
}

.pause{
    animation-play-state:paused;
}


/*#left p{*/
/*    position: absolute;*/
/*    font-size: 24px;*/
/*    font-weight: 500;*/
/*    color: #5e72e4;*/
/*    bottom:10%;*/
/*    left: 35%;*/
/*}*/
#line{
    position: absolute;
    top: 10%;
    left: 62%;
    width: 0;
    height: 80%;
    border-left:1px solid #59666d;
    opacity: 0.4;
}

#right{
    position: absolute;
    left:65%;
    width: 35%;
    top:10%;
    bottom:10%;
    height: 100%;
}

.scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-primary-light-9);
    color: var(--el-color-primary);
}

/*ul{*/
/*    position: absolute;*/
/*    width: 80%;*/
/*    height: 80%;*/
/*    left:10%;*/
/*    top:10%;*/
/*}*/
/*li{*/
/*    width: 100%;*/
/*    height: 30px;*/
/*    list-style: none;*/

/*}*/
/*li img{*/
/*    !* position: absolute; *!*/
/*    float: left;*/
/*    width: 25px;*/
/*    height: auto;*/
/*    cursor: pointer;*/
/*}*/
/*li p{*/
/*    font-size: 20px;*/
/*    float: left;*/
/*    width: 85%;*/
/*    !* 强制不换行 *!*/
/*    white-space:nowrap;*/
/*}*/

#bottom{
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 7%;
    background-color: #f4f5f7;
}


</style>

