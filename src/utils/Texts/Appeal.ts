import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {ElMessage} from "element-plus";

export const fetchAppeal=async ()=>{
    try{
        const response = await api.getAppeal()
        return response.data;
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
    // console.log(response.data);
}
