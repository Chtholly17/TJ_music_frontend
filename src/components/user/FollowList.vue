<template>
    <h1 style="margin-bottom: 5vh;margin-top: 5vh">我的关注</h1>
    <div   v-for="item in followList" :key="item.userId">
        <div class="fan_block">
            <div class="user_photo">
                <!--            <el-image :src="require(item.userProfileImageFilename)" class="photo" fit="scale-down"> </el-image>-->
<!--                <img class="photo" :src="item.userProfileImageFilename">-->
                <el-avatar class="photo" :src="item.userProfileImageFilename " @click="toOther(item.userStudentNumber)"></el-avatar>
            </div>
            <div class="name_signature">
                <p>{{item.userNickname}}</p>
                <div style="height: 20px"></div>
                <p>{{item.userSignature}}</p>
            </div>
            <div class="deleteFan">
                <el-button @click="deleteFollows(item.userStudentNumber)">取消关注</el-button>
            </div>
        </div>
        <el-divider></el-divider>

    </div>

</template>

<script>
import {computed, onBeforeMount, ref, inject} from "vue";
import {fetchFollowList} from "@/utils/Texts/FollowList";
import {deleteFollow} from "@/utils/Texts/FollowList";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";
import router from "@/router";

export default {
    name:'FollowList',
    setup(_,{emit})
    {
        const reload=inject('reload')
        const store = useStore()

        let userId;
<<<<<<< HEAD
        let follow_list;
        follow_list=ref()
=======
        let followList;
        followList=ref()
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
        onBeforeMount(()=>{
            // const count = computed(() => store.getters.getUserID)
            // userId=count.value
            userId=getCookie("userNumber");

            fetchFollowList(userId).then(res=>{
<<<<<<< HEAD
                follow_list.value=res
=======
                followList.value=res
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
                //console.log(res)
            })

        })
<<<<<<< HEAD
        function deleteFollow(followId){
            emit('de_follow')
=======
        function deleteFollows(followId){
            emit('deFollow')
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            deleteFollow(userId,followId).then(()=>{
                reload()
            })
        }
<<<<<<< HEAD
        function to_other(userId){
=======
        function toOther(userId){
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
            router.push({path:'/otherinfo',query:{id:userId}})
        }
        return{
            onBeforeMount,
<<<<<<< HEAD
            follow_list,
            delete_follow,
            userId,to_other
=======
            followList: followList,
            deleteFollows: deleteFollows,
            userId: userId,toOther
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419
        }
    }
}
</script>

<style scoped>
.fan_block{
    display: flex;
    margin: 3%;
}
.fan_block > .user_photo{
    width:10%;
}

.fan_block > .user_photo>.photo{
    height: 100px;
    width: 100px;
    border-radius: 50%;
    cursor: pointer;
}

.fan_block>.name_signature{
    width: 80%;
    text-align: left;
    margin-left: 5%;
}
.fan_block>.deleteFan{
    width: 10%;
}

.fan_block>.deleteFan>.el-button:focus{
    background: #CC2288 ;
    border-color: #CC2288 ;
    color: #ffffff ;
}
.fan_block>.deleteFan>.el-button:hover{
    background: #c965a2 ;
    border-color: #c965a2 ;
    color: #ffffff;
}
.fan_block>.deleteFan>.el-button{
    background: #CC2288 ;
    border-color: #CC2288 ;
    color: #ffffff ;
}

</style>
