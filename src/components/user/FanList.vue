<template>
    <div  v-for="item in fan_list" :key="item.userId">
        <div class="fan_block">
          <div class="user_photo">
              <vue-avatar :name="item.userNickname" :src="item.userProfileImageFilename"></vue-avatar>
              <img class="photo" :src="item.userProfileImageFilename">
          </div>
          <div class="name_signature">
              <p>{{item.userNickname}}</p>
              <p>{{item.userSignature}}</p>
          </div>
          <div class="delete_fan">
              <el-button @click="delete_fan(item.userStudentNumber)">删除粉丝</el-button>
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
// import VueAvatar from 'vue-avatar'

export default {
    name:'FanList',

    setup()
    {

        const reload=inject('reload')
        const store = useStore()

        let user_id;
        let fan_list;
        fan_list=ref()
        onBeforeMount(()=>{
            const count = computed(() => store.getters.getUserID)

            user_id=count.value
            fetchFanList().then(res=>{
                fan_list.value=res
                console.log(res)
                })
        })
        function delete_fan(fan_id){
            deleteFollow(fan_id,user_id).then(()=>{
                reload()
            })
        }
        return{
            onBeforeMount,
            fan_list,
            delete_fan
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
}

.fan_block>.name_signature{
    width: 80%;
    text-align: left;
    margin-left: 5%;
}
.fan_block>.delete_fan{
    width: 10%;
}

.fan_block>.delete_fan>.el-button:focus{
    background: #CC2288 ;
    border-color: #CC2288 ;
    color: #ffffff ;
}
.fan_block>.delete_fan>.el-button:hover{
    background: #c965a2 ;
    border-color: #c965a2 ;
    color: #ffffff;
}
.fan_block>.delete_fan>.el-button{
    background: #CC2288 ;
    border-color: #CC2288 ;
    color: #ffffff ;
}

</style>
