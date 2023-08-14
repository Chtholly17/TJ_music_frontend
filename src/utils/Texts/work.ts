import {computed} from "vue";
import api from "@/service";


export const fetchWork=async (workId:any)=>{
    const response=await api.getWorkById(workId)

    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}

export const commitWork=async(workName:any, workComment:any, workOwner:any, workOriginVersion:any, workVoiceFileName:any, workPreciseScore:any, workQualityScore:any, workPitchScore:any)=>
{
    // console.log("work_name:"+work_name)
    // console.log("work_owner:"+work_owner)
    // console.log("work_origin_version:"+work_origin_version)
    // console.log("work_voice_file_name:"+work_voice_file_name)
    // console.log("work_precise_score:"+work_precise_score)
    // console.log("work_quality_score:"+work_quality_score)
    // console.log("work_pitch_score:"+work_pitch_score)

    return await api.postWork(workName,workComment,workOwner,workOriginVersion,workVoiceFileName,workPreciseScore,workQualityScore,workPitchScore)
}
