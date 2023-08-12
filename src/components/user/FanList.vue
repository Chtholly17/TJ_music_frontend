<template>
    <h1 style="margin-bottom: 5vh;margin-top: 5vh">我的粉丝</h1>
    <div  v-for="item in fanList" :key="item.userId">
        <div class="fan_block">
          <div class="user_photo">
<!--              <vue-avatar :name="item.userNickname" :src="item.userProfileImageFilename"></vue-avatar>-->
<!--              <img class="photo" :src="item.userProfileImageFilename">-->
              <el-avatar class="photo" :src="item.userProfileImageFilename" @click="toOther(item.userStudentNumber)"></el-avatar>
          </div>
          <div class="name_signature">
              <p>{{item.userNickname}}</p>
              <p>{{item.userSignature}}</p>
          </div>
          <div class="deleteFan">
              <el-button @click="deleteFan(item.userStudentNumber)">删除粉丝</el-button>
          </div>
        </div>
        <el-divider></el-divider>
    </div>

</template>

<script>
import {computed, inject, onBeforeMount, ref} from "vue";
import {fetchFanList} from "@/utils/Texts/FanList";
import {deleteFollow} from "@/utils/Texts/FollowList";
import {useStore} from "vuex";
import router from "@/router";
// import VueAvatar from 'vue-avatar'

export default {
    name:'FanList',

    setup(_,{emit})
    {

        const reload=inject('reload')
        const store = useStore()

        let userId;
        let fanList;
        fanList=ref()
        onBeforeMount(()=>{
            const count = computed(() => store.getters.getUserID)
            userId = count.value
            fetchFanList().then(res=>{
                fanList.value=res
                //console.log(res)
                })
        })
        function deleteFan(fanId){
            emit('deFan')
            deleteFollow(fanId,userId).then(()=>{
                reload()
            })
        }
        function toOther(userId){
            router.push({path:'/otherinfo',query:{id:userId}})
        }
        return{
            onBeforeMount,
            fanList: fanList,
            deleteFan: deleteFan,
            toOther: toOther
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
