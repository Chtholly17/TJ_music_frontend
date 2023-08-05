import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";
import {ElMessage} from "element-plus";
// 获取用户的粉丝列表
export const fetchFanList=async ()=>{
    //console.log("fetchFanList")
    // const store = useStore()
    // const count = computed(() => store.getters.getUserID)   //获取用户id

    try{
        const user_id=getCookie("userNumber");
        const response = await api.getFanList(user_id)
        //console.log(response.data.data);
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }

}
