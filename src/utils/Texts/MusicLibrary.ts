import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";

export const fetchMusicLibrary=async ()=>{

    const userNumber = getCookie("userNumber")

    const response=await api.getMusicLibrary(userNumber)

    return response.data.data.workList
}

export const fetchOtherMusicLibrary=async (user_id:any)=>{
    const response=await api.getMusicLibrary(user_id)
    return response.data.data.workList
}
