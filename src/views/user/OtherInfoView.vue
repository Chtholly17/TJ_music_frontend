<template>
    <div class="common-layout" v-loading="loading">
        <el-container style="height: 100%">
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <div >
                        <el-avatar :src="realImgUrl " :size="280" fit="cover"></el-avatar>
                    </div>
                    <div >
                        <div style="height: 30px"></div>
                        <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{otherNickname}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{otherId}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{otherSignature}}</p>
                    </div>
                    <div >
                        <div style="height: 70px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            关注: {{ userFollow }}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            粉丝: {{userFans}}
                        </div>
                        <div style="height: 30px"></div>
                    </div>
                    <div style="float: right">
                        <div style="height: 10px"></div>
                        <el-button type="info" :icon="Edit"  class="right_buttons" @click="showInfo=true">详细信息</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View" @click="followChange" class="right_buttons">
                            <span v-if="isFollow">取关</span>
                            <span v-else>关注</span>
                        </el-button>
                        <div style="height: 30px"></div>

                        <el-button type="primary" :icon="Share" @click="toChat" class="right_buttons">发起会话</el-button>
                    </div>
                </div>
                <h1 style="margin-bottom: 5vh;margin-top: 5vh">
                    TA的曲库
                </h1>
                <div>
                    <div class="accompanimentItem">
                        <div class="itemIndexBox">
                            <el-text truncated size="large">
                                <h3>序号</h3>
                            </el-text>
                        </div>
                        <div class="nameBox">
                            <el-text truncated size="large"><h3> 歌曲名</h3> </el-text>
                        </div>
                        <div class="nameBox">
                            <el-text truncated size="large"> <h3> 评分</h3>  </el-text>
                        </div>
                        <div class="singerBox">
                            <el-text truncated size="large"> <h3> 点赞数</h3>  </el-text>
                        </div>
                        <div class="durationBox">
                            <el-text truncated size="large"> <h3> 创建时间</h3>  </el-text>
                        </div>
                    </div>
                    <music_library_item v-for="(item,index) in otherMusicLibrary" :key="item"
                                        :name="item.workName" :score="item.score_ave"
                                        :index="index" :like="item.workLike" :time="item.createTime.substr(0,10)" ></music_library_item>

                </div>

                <el-dialog class="user_info" title="用户信息" width="30%" :lock-scroll="false" v-model="showInfo">
                    <el-form>
                        <el-form-item label="学院：" >
                            <el-text>{{otherCollege}}</el-text>
                        </el-form-item>
                    </el-form>
                    <el-form-item label="专业：">
                        <el-text>{{otherMajor}}</el-text>
                    </el-form-item>
                    <el-form-item label="地区：">
                        <el-text>{{otherArea1+"-"+otherArea2}}</el-text>
                    </el-form-item>
                    <el-form-item label="生日：">
                        <el-text>{{otherBirthday}}</el-text>
                    </el-form-item>
                    <el-form-item label="性别：">
                        <el-text>{{otherGender}}</el-text>
                    </el-form-item>
                </el-dialog>

            </el-main>
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
        </el-container>
    </div>
</template>

<script>
import { Edit, Search, Share, Upload,View } from '@element-plus/icons-vue';
import {computed, onBeforeMount, provide, ref} from "vue";
import { useStore } from 'vuex'
import {userinfoData} from "@/utils/Texts/userinfoText";
import {userFetchUserImage,fetchOtherInfo} from "@/utils/Texts/userinfoText";
import { provinceAndCityData } from 'element-china-area-data'
import music_library_item from "@/components/user/musicLibraryItem.vue"
import {fetchMusicLibrary} from "@/utils/Texts/MusicLibrary";
import api from "@/service/index"
import {getCookie} from "@/service/cookie";
import {ElMessage} from "element-plus";
import router from "@/router";
import {chatStudentNumber,chatNickname} from "@/utils/chatParams";

export default {
    name: "OtherInfo",
    components:{music_library_item},
    data() {
        return {
            aside_width: 13,
            user_info_show_control: 0    //控制是否展示个人信息界面
        }
    },
    setup() {
        const loading=ref(false)
        const otherId = ref(router.currentRoute.value.query.id)
        const otherNickname = ref("")    //其他人的昵称
        const otherSignature = ref("")   //其他人的签名
        const otherCollege = ref("")    //学院
        const otherMajor = ref("")   //专业
        const otherArea1 = ref("")   //地区1
        const otherArea2 = ref("")   //地区2
        const otherBirthday = ref("")    //生日
        const otherGender = ref("")  //性别
        const otherMusicLibrary = ref();//曲库

        const showInfo = ref(false)
        const isFollow=ref()   //是否关注


        const options = provinceAndCityData   //所有地区的可选项
        const userPhotoUrl = ref("")
        const userFollow = ref(0)    //用户关注数
        const userFans = ref(0)  //用户粉丝数
        const userId = ref("")   //用户学号



        const store = useStore()
        const realImgUrl = ref('');

        onBeforeMount(() => {
            //获取头像
            userFetchUserImage(otherId.value).then(res => {
                userPhotoUrl.value = res
                const random_num = Math.random() * 100 + 1;
                realImgUrl.value = `${userPhotoUrl.value}?timestamp=${random_num}`;
            })

            //获取个人信息
            fetchOtherInfo(otherId.value).then((res => {
                otherNickname.value = res.userNickname
                otherSignature.value = res.userSignature
                userFollow.value = res.userFollowCnt
                userFans.value = res.userFansCnt
                otherCollege.value = res.userCollege
                otherMajor.value = res.userMajor
                otherBirthday.value = res.userBirthday
                if (res.userGender == "男")
                    otherGender.value = "男"
                else if (res.userGender == "女")
                    otherGender.value = "女"

                const area1 = options.find((dict) => dict.value === res.userArea1)
                otherArea1.value = area1.label
                const area2 = area1.children.find((dict) => dict.value === res.userArea2)
                otherArea2.value = area2.label

            }))

            //获取曲库
            fetchMusicLibrary(otherId.value).then(res=>{
                otherMusicLibrary.value=res
                for (const item of otherMusicLibrary.value) {
                    item.score_ave=(item.workQualityScore+item.workPreciseScore+item.workPitchScore)/3;
                    item.score_ave=item.score_ave.toFixed(2)
            }})

            //判断是否关注
            userId.value = getCookie("userNumber")
            api.checkFollow(userId.value,otherId.value).then(res=>{

                if(res.data.code===0)    //未关注
                {
                    isFollow.value=false
                }
                else {  //关注
                    isFollow.value=true
                }
            })

        })



        function followChange(){
            loading.value=true

            setTimeout(()=>{
                if(isFollow.value===true)
                {
                    userFans.value--;
                    api.deleteFollow(userId.value,otherId.value).then(res=>{
                        if(res.status===200){
                            isFollow.value=false
                            ElMessage.success("已取消关注")
                        }
                        else{
                            ElMessage.error("出现错误")
                        }

                    })

                }
                else{
                    userFans.value++;
                    api.follow(userId.value,otherId.value).then(res=>{
                        if(res.status===200){
                            isFollow.value=true
                            ElMessage.success("已关注")
                        }
                        else{
                            ElMessage.error("出现错误")
                        }

                    })
                }
                loading.value=false
            },500)

        }

        function toChat(){
            chatStudentNumber.value=otherId.value
            chatNickname.value=otherNickname.value
            router.push({path:'/message',query:{target_id:otherId.value}})
        }

        return {
            Search,
            Share,
            Upload,
            View,
            Edit,
            userinfoData,
            onBeforeMount,
            realImgUrl, userFollow: userFollow, userFans: userFans, otherId: otherId,
            otherNickname: otherNickname, otherSignature: otherSignature, otherCollege: otherCollege,
            otherMajor: otherMajor, otherArea1: otherArea1, otherArea2: otherArea2, otherBirthday: otherBirthday, otherGender: otherGender,
            showInfo: showInfo, otherMusicLibrary: otherMusicLibrary,isFollow: isFollow,followChange: followChange,toChat: toChat,
            loading
        }
    }
}
</script>

<style scoped>
.user_main{
    height: 100%;
}
.user_aside{
    background-color: rgb(245,245,245);
    height: 100%;
}

.user_photo{
    height: 250px;
    /*圆角边框*/
    border: 2px solid white;
    border-radius: 50%;
    padding: 10px;
    height: 280px;
    width: 280px;


}
.common-layout{
    overflow: auto;
    min-height: 90vh;
}

.user_top{
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;

}

.right_buttons{
    width: 100%;
}
.itemIndexBox{
    width: 10%
}
.nameBox{
    width: 25%;
    text-align: left;
}
.singerBox{
    width: 25%;
    text-align: left;
}
.durationBox{
    width: 15%
}

.accompanimentItem{
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    height: 60px;
}



</style>
