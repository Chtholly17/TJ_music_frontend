import api from "@/service";


export const fetchComment=async (work_id:any)=>{
    const response=await api.getComment(work_id)

    console.log(response.data)
    return response.data.data
}

export const commitComment=async(comment_target:any,comment_owner:any,comment_content:any)=>
{
    return await api.postComment(comment_target, comment_owner, comment_content)
}
