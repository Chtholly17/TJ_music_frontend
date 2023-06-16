import api from "@/service";

export const fetchRankList=async (tag:any,order:any)=>{

    const response = await api.getRankList(tag,order)
    // console.log(response.data.data);
    return response.data.data
}
