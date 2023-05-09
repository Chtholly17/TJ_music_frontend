import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";

// 获取用户的粉丝列表
export const fetchFanList=async ()=>{
    //console.log("fetchFanList")
    const store = useStore()
    const count = computed(() => store.getters.getUserID)   //获取用户id

    const response = await api.getFanList(count.value)
    //console.log(response.data.data);
    return response.data.data
}
