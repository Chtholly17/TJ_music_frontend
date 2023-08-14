import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";

export const fetchMusicLibrary=async (user_id:any)=>{
    const UserNumber = (user_id == null) ? getCookie("userNumber") : user_id
    const response=await api.getMusicLibrary(UserNumber)
    return response.data.data.workList
}

