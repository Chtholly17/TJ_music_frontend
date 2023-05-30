import api from "@/service";


export const fetchMusicSquare=async (worknum:any)=>{
    const response=await api.getMainPageWorks(worknum)

    console.log(response.data.data)
    return response.data.data
}
