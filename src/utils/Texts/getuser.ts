import {computed} from "vue";
import api from "@/service";


export const fetchUserById=async (user_id:any)=>{
    const response=await api.selectUserById(user_id)

    console.log(response.data)
    if(response.data.data==null)
    {
        return []
    }
    return response.data.data
}
