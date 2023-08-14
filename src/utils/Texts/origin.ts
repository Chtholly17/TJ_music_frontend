import {computed} from "vue";
import api from "@/service";
import {ElMessage} from "element-plus";

<<<<<<< HEAD
export const fetchOriginByOriginId=async (originId:any)=>{
    const response=await api.getOriginByOriginId(originId)
=======
export const fetchOriginByOriginId=async (origin_id:any)=>{
    try{
        const response=await api.getOriginByOriginId(origin_id)
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419

        // console.log("获取数据")
        // console.log(response.data)
        if(response.data.data==null)
        {
            return []
        }
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}

<<<<<<< HEAD
export const fetchOriginByWorkId=async (workId:any)=>{
    const response=await api.getOriginByOriginId(workId)
=======
export const fetchOriginByWorkId=async (work_id:any)=>{
    try{
        const response=await api.getOriginByOriginId(work_id)
>>>>>>> e5d9cb31ec52f3b470277d410365bcac437bf419

        // console.log("获取数据")
        // console.log(response.data)
        if(response.data.data==null)
        {
            return []
        }
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}
