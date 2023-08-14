import {computed} from "vue";
import api from "@/service";


export const fetchOriginByOriginId=async (originId:any)=>{
    const response=await api.getOriginByOriginId(originId)

    // console.log("获取数据")
    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}

export const fetchOriginByWorkId=async (workId:any)=>{
    const response=await api.getOriginByOriginId(workId)

    // console.log("获取数据")
    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}
