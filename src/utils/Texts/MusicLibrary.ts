import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";
import {getCookie} from "@/service/cookie";

export const fetchMusicLibrary=async ()=>{
    //console.log("fetchFanList")
    // const store = useStore()
    // const count = computed(() => store.getters.getUserID)   //获取用户id

    const userNumber = getCookie("userNumber")

    const response=await api.getMusicLibrary(userNumber)

    console.log("获取我的曲库")
    console.log(response);
    return response.data.data.workList


}
