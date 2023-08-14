import api from "@/service";
import {ElMessage} from "element-plus";
export const fetchRankList=async (tag:any,order:any)=>{
    try{
        const response = await api.getRankList(tag,order)
        // console.log(response.data.data);
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}
