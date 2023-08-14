import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {ElMessage} from "element-plus";
export const fetchOriginMusic=async ()=>{
    try{
        const response = await api.getOriginMusic()
        console.log(response.data);
        return response.data;
    }
    catch (error:any){
        ElMessage.error(error.code + ': 获取失败，请检查网络或联系管理员')
        return false
    }
}

// export const fetchOriginMusicById=async (Id : any)=>{
//     //string2int
//     Id = parseInt(Id);
//     const response = await api.getOriginMusicById(Id)
//     console.log(response.data);
//     return response.data;
// }
