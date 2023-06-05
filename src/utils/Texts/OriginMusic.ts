import {computed} from "vue";
import api from "@/service";
import {useStore} from "vuex";

export const fetchOriginMusic=async ()=>{
    const response = await api.getOriginMusic()
    console.log(response.data);
    return response.data;
}

// export const fetchOriginMusicById=async (Id : any)=>{
//     //string2int
//     Id = parseInt(Id);
//     const response = await api.getOriginMusicById(Id)
//     console.log(response.data);
//     return response.data;
// }
