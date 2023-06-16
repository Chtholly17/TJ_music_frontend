import {computed} from "vue";
import api from "@/service";


export const fetchOriginByOriginId=async (origin_id:any)=>{
    const response=await api.getOriginByOriginId(origin_id)

    // console.log("获取数据")
    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}

export const fetchOriginByWorkId=async (work_id:any)=>{
    const response=await api.getOriginByOriginId(work_id)

    // console.log("获取数据")
    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}
