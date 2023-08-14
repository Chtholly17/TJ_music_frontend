import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";

export const fetchMusicLibrary=async ()=>{

    const userNumber = getCookie("userNumber")

    const response=await api.getMusicLibrary(userNumber)

    return response.data.data.workList
}

export const fetchOtherMusicLibrary=async (userId:any)=>{
    const response=await api.getMusicLibrary(userId)
    return response.data.data.workList
}
