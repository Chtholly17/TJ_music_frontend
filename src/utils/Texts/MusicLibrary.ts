import {computed, reactive, ref, unref} from "vue";
import api from "@/service";
import {useStore} from "vuex";

export const fetchMusicLibrary=async ()=>{
    //console.log("fetchFanList")
    const store = useStore()
    const count = computed(() => store.getters.getUserID)   //获取用户id

    const response = await api.getMusicLibrary(count.value)
    console.log(response.data.data.workList);
    return response.data.data.workList
}
