import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";

export const fetchRankList=async (tag:any,order:any)=>{

    const store = useStore()
    const count = computed(() => store.getters.getUserID)   //获取用户id

    const response = await api.getRankList(tag,order)
    console.log(response.data.data);
    return response.data.data
}
