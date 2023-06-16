import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";

export const fetchUserManager=async ()=>{
    const response = await api.getUserManager()
    console.log(response.data);
    return response.data;
}
