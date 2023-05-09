<template>
    <div   v-for="item in follow_list" :key="item.userId">
        <div class="fan_block">
            <div class="user_photo">
                <!--            <el-image :src="require(item.userProfileImageFilename)" class="photo" fit="scale-down"> </el-image>-->
                <img class="photo" :src="item.userProfileImageFilename">
            </div>
            <div class="name_signature">
                <p>{{item.userNickname}}</p>
                <div style="height: 20px"></div>
                <p>{{item.userSignature}}</p>
            </div>
            <div class="delete_fan">
                <el-button @click="delete_follow(item.userStudentNumber)">取消关注</el-button>
            </div>
        </div>
        <el-divider></el-divider>

    </div>

</template>

<script>
import {computed, onBeforeMount, ref, inject, onBeforeUpdate} from "vue";
import {fetchFollowList} from "@/utils/Texts/FollowList";
import {deleteFollow} from "@/utils/Texts/FollowList";
import {useStore} from "vuex";

export default {
    name:'FollowList',
    setup()
    {
        const reload=inject('reload')
        const store = useStore()

        let user_id;
        let follow_list;
        follow_list=ref()
        onBeforeMount(()=>{
            const count = computed(() => store.getters.getUserID)

            user_id=count.value
            fetchFollowList(user_id).then(res=>{
                follow_list.value=res
                console.log(res)
            })

        })
        function delete_follow(follow_id){
            deleteFollow(user_id,follow_id).then(res=>{
                reload()
            })
        }
        return{
            onBeforeMount,
            follow_list,
            delete_follow,
            user_id
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
