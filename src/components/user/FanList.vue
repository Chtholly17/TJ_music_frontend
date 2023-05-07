<template>
    <div class="fan_block"  v-for="item in fan_list" :key="item.userId">
        <div class="user_photo">
<!--            <el-image :src="require(item.userProfileImageFilename)" class="photo" fit="scale-down"> </el-image>-->
            <img class="photo" :src="item.userProfileImageFilename">
        </div>
        <div class="name_signature">
            <p>{{item.userNickname}}</p>
            <p>{{item.userSignature}}</p>
        </div>
        <div class="delete_fan">
            <el-button>删除粉丝</el-button>
        </div>
    </div>

</template>

<script>
import {onBeforeMount, ref} from "vue";
import {fetchFanList} from "@/utils/Texts/FanList";

export default {
    name:'FanList',
    setup()
    {
        let fan_list;
        fan_list=ref()
        onBeforeMount(()=>{
            fetchFanList().then(res=>{
                fan_list.value=res
                console.log(res)
                })
        })
        return{
            onBeforeMount,
            fan_list
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
    height: 95%;
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
