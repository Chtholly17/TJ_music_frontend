import {ElMessage} from "element-plus";
import api from "@/service";


// 获取用户的粉丝列表
export const fetchFollowList=async (user_id:string)=>{
    //console.log("fetchFanList")
    // const store = useStore()
    // const count = computed(() => store.getters.getUserID)   //获取用户id
    try{
        const response = await api.getFollowList(user_id)
        //console.log(response.data.data);
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}

export const deleteFollow=async (user_id:string,follow_id:string)=>{
    // console.log(user_id,follow_id)
    try {
        const response = await api.deleteFollow(user_id, follow_id)
        // console.log(response)
    }catch (error: any) {
        ElMessage.error(error.code+': 提交失败，请检查网络或联系管理员')
    }
    //console.log("删除完毕");
}
