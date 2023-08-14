import api from "@/service";
import {ElMessage} from "element-plus";

export const fetchComment=async (work_id:any)=>{
    try{
        const response=await api.getComment(work_id)
        // console.log(response.data)
        if(response.data.data==null) {
            return []
        }
        return response.data.data
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}

export const commitComment=async(comment_target:any,comment_owner:any,comment_content:any)=>
{
    return await api.postComment(comment_target, comment_owner, comment_content)
}
