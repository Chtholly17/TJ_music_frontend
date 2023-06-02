<template>
    <div class="common-layout">
        <el-container style="height: 100%">
            <el-aside class="user_aside" :style="{width: aside_width + 'vh'}"></el-aside>
            <el-main class="user_main" >
                <div class="user_top">
                    <div >
                        <el-avatar :src="real_img_url " :size="280" fit="cover"></el-avatar>
                    </div>
                    <div >
                        <div style="height: 30px"></div>
                        <p style="font-size: 30px;text-align: left;font-family:SimHei;font-style: italic">{{other_nickname}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 学号: &nbsp; &nbsp;{{other_id}}</p>
                        <div style="height: 30px"></div>
                        <p style="text-align: left"> 个性签名: &nbsp; &nbsp;{{other_signature}}</p>
                    </div>
                    <div >
                        <div style="height: 70px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            关注: {{ user_follow }}
                        </div>
                        <div style="height: 30px"></div>
                        <div style="color: darkgray;font-size: 25px;text-align: left">
                            粉丝: {{user_fans}}
                        </div>
                        <div style="height: 30px"></div>
                    </div>
                    <div style="float: right">
                        <div style="height: 10px"></div>
                        <el-button type="info" :icon="Edit"  class="right_buttons" @click="show_info=true">详细信息</el-button>
                        <div style="height: 30px"></div>
                        <el-button type="primary" :icon="View" @click="follow_change" class="right_buttons">
                            <span v-if="is_follow">取关</span>
                            <span v-else>关注</span>
                        </el-button>
                        <div style="height: 30px"></div>
<!--                        <el-button type="primary" :icon="Search" @click="fan_router" class="right_buttons">取消关注</el-button>-->
<!--                        <div style="height: 30px"></div>-->
                        <el-button type="primary" :icon="Share" @click="to_chat" class="right_buttons">发起会话</el-button>
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
                    <music_library_item v-for="(item,index) in other_music_library" :key="item"
                                        :name="item.workName" :score="item.score_ave"
                                        :index="index" :like="item.workLike" :time="item.createTime.substr(0,10)" ></music_library_item>

                </div>

                <el-dialog class="user_info" title="用户信息" width="30%" :lock-scroll="false" v-model="show_info">
                    <el-form>
                        <el-form-item label="学院：" >
                            <el-text>{{other_colledge}}</el-text>
                        </el-form-item>
                    </el-form>
                    <el-form-item label="专业：">
                        <el-text>{{other_major}}</el-text>
                    </el-form-item>
                    <el-form-item label="地区：">
                        <el-text>{{other_area1+"-"+other_area2}}</el-text>
                    </el-form-item>
                    <el-form-item label="生日：">
                        <el-text>{{other_birthday}}</el-text>
                    </el-form-item>
                    <el-form-item label="性别：">
                        <el-text>{{other_gender}}</el-text>
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
import {user_fetchUserImage,fetchOtherInfo} from "@/utils/Texts/userinfoText";
import { provinceAndCityData } from 'element-china-area-data'
import music_library_item from "@/components/user/musicLibraryItem.vue"
import {fetchOtherMusicLibrary} from "@/utils/Texts/MusicLibrary";
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
        const other_id = ref(router.currentRoute.value.query.id)
        const other_nickname = ref("")    //其他人的昵称
        const other_signature = ref("")   //其他人的签名
        const other_colledge = ref("")    //学院
        const other_major = ref("")   //专业
        const other_area1 = ref("")   //地区1
        const other_area2 = ref("")   //地区2
        const other_birthday = ref("")    //生日
        const other_gender = ref("")  //性别
        const other_music_library = ref();//曲库

        const show_info = ref(false)
        const is_follow=ref()   //是否关注


        const options = provinceAndCityData   //所有地区的可选项
        const user_photo_url = ref("")
        const user_follow = ref(0)    //用户关注数
        const user_fans = ref(0)  //用户粉丝数
        const user_id = ref("")   //用户学号



        const store = useStore()
        const real_img_url = ref('');

        onBeforeMount(() => {
            //获取头像
            user_fetchUserImage(other_id.value).then(res => {
                user_photo_url.value = res
                const random_num = Math.random() * 100 + 1;
                real_img_url.value = `${user_photo_url.value}?timestamp=${random_num}`;
            })

            //获取个人信息
            fetchOtherInfo(other_id.value).then((res => {
                other_nickname.value = res.userNickname
                other_signature.value = res.userSignature
                user_follow.value = res.userFollowCnt
                user_fans.value = res.userFansCnt
                other_colledge.value = res.userCollege
                other_major.value = res.userMajor
                other_birthday.value = res.userBirthday
                if (res.userGender == "男")
                    other_gender.value = "男"
                else if (res.userGender == "女")
                    other_gender.value = "女"

                const area1 = options.find((dict) => dict.value === res.userArea1)
                other_area1.value = area1.label
                const area2 = area1.children.find((dict) => dict.value === res.userArea2)
                other_area2.value = area2.label

            }))

            //获取曲库
            fetchOtherMusicLibrary(other_id.value).then(res=>{
                other_music_library.value=res
                for (const item of other_music_library.value) {
                    item.score_ave=(item.workQualityScore+item.workPreciseScore+item.workPitchScore)/3;
                    item.score_ave=item.score_ave.toFixed(2)
            }})

            //判断是否关注
            user_id.value = getCookie("userNumber")
            api.checkFollow(user_id.value,other_id.value).then(res=>{

                if(res.data.code===0)    //未关注
                {
                    is_follow.value=false
                }
                else {  //关注
                    is_follow.value=true
                }
            })

        })

        function follow_change(){
            if(is_follow.value===true)
            {
                api.deleteFollow(user_id.value,other_id.value).then(res=>{
                    if(res.status===200){
                        is_follow.value=false
                        ElMessage.success("已取消关注")
                    }
                    else{
                        ElMessage.error("出现错误")
                    }

                })

            }
            else{
                api.follow(user_id.value,other_id.value).then(res=>{
                    if(res.status===200){
                        is_follow.value=true
                        ElMessage.success("已关注")
                    }
                    else{
                        ElMessage.error("出现错误")
                    }

                })
            }
        }

        function to_chat(){
            chatStudentNumber.value=other_id.value
            chatNickname.value=other_nickname.value
            router.push({path:'/message',query:{target_id:other_id.value}})
        }

        return {
            Search,
            Share,
            Upload,
            View,
            Edit,
            userinfoData,
            onBeforeMount,
            real_img_url, user_follow, user_fans, other_id,
            other_nickname, other_signature, other_colledge,
            other_major, other_area1, other_area2, other_birthday, other_gender,
            show_info, other_music_library,is_follow,follow_change,to_chat
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
