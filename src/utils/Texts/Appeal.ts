import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";

export const fetchAppeal=async ()=>{
    const response = await api.getAppeal()
    console.log(response.data);
    return response.data;
}
