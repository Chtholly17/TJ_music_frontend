import {computed} from "vue";
import api from "@/service";


export const fetchWork=async (work_id:any)=>{
    const response=await api.getWorkById(work_id)

    console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}
