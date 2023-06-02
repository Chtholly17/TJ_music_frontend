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

export const commitWork=async(work_name:any,work_comment:any,work_owner:any,work_origin_version:any,work_voice_file_name:any,work_precise_score:any,work_quality_score:any,work_pitch_score:any)=>
{
    console.log("work_name:"+work_name)
    console.log("work_owner:"+work_owner)
    console.log("work_origin_version:"+work_origin_version)
    console.log("work_voice_file_name:"+work_voice_file_name)
    console.log("work_precise_score:"+work_precise_score)
    console.log("work_quality_score:"+work_quality_score)
    console.log("work_pitch_score:"+work_pitch_score)

    return await api.postWork(work_name,work_comment,work_owner,work_origin_version,work_voice_file_name,work_precise_score,work_quality_score,work_pitch_score)
}
