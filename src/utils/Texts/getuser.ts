import {computed} from "vue";
import api from "@/service";
import {ElMessage} from "element-plus";

export const fetchUserById=async (userId:any)=>{
    const response=await api.selectUserById(userId)


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
