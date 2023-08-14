import {computed} from "vue";
import api from "@/service";


export const fetchUserById=async (userId:any)=>{
    const response=await api.selectUserById(userId)

    // console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}
