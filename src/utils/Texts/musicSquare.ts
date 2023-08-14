import api from "@/service";


export const fetchMusicSquare=async (workNum:any)=>{
    const response=await api.getMainPageWorks(workNum)

    // console.log(response.data.data)
    return response.data.data
}
