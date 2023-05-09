// 本文件封装了一系列api请求
import axios from "@/utils/request";
import path from "@/service/path";

// 此处集成各种api函数，通过调用这些函数实现网络请求
const api = {
    // getTest() {
    //     return axios.get(path.baseUrl + path.getTest)
    // },
    // postTest(data: any)
    // {
    //     // const data_ = new URLSearchParams();
    //     // data_.append('test_string', 'hahahaha')
    //     // console.log(data_)
    //     // data = JSON.stringify(data)
    //     return axios.post(path.baseUrl + path.postTest, data)
    // },
    postLogin(loginForm: any) {
        return axios.post(path.baseUrl + path.login, loginForm)
    },
    post_sendRegisterVRCode(userInfo: any) {
        return axios.post(path.baseUrl + path.register_sendVRCode, userInfo)
    },
    postRegister(registerForm: any) {
        return axios.post(path.baseUrl + path.register, registerForm)
    },
    post_sendRetrieveVRCode(userInfo: any) {
        // console.log("SEND!")
        return axios.post(path.baseUrl + path.retrieve_sendVRCode, userInfo)
    },
    postRetrieve(retrieveForm: any)
    {
        return axios.post(path.baseUrl + path.retrieve, retrieveForm)
    },
    postUserInfo(userInfo:any){
        return axios.post(path.baseUrl+path.postuserinfo,userInfo)
    },
    getUserInfo(user_id:any)  //获取用户信息
    {
        return axios.get(path.baseUrl + path.getuserinfo,{
            params:{
                user_student_number:user_id
            }
            })
    },
    //获取歌单
    getSongList(user_id:any)//获取用户曲库
    {
        return axios.get(path.baseUrl+path.get_songList,{
            params:{
                user_student_number:user_id
            }
        })
    },
    //获取粉丝列表
    getFanList(user_id:any)//获取用户粉丝列表
    {
        return axios.get(path.baseUrl+path.get_fanList,{
            params:{
                user_student_number:user_id
            }
        })
    },
    //获取关注列表
    getFollowList(user_id:any)
    {
        return axios.get(path.baseUrl+path.get_followList,{
            params:{
                user_student_number:user_id
            }
        })
    },
    //取消关注某用户
    deleteFollow(user_id:string,follow_id:string)
    {
        return axios.delete(path.baseUrl+path.delete_follow,{
            params:{    //这里必须用params，不能用data，否则无法正确删除
                user_student_number:user_id,
                target_student_number:follow_id
            }
        })
    },
    //获取用户曲库
    getMusicLibrary(user_id:any)
    {
        return axios.get(path.baseUrl+path.get_musicLibrary,{
            params:{
                userNumber:user_id
            }
        })
    },
    //修改用户头像
    postUserImage(file:any)
    {
        //console.log(path.baseUrl+path.update_user_image)
        return axios.post(path.baseUrl+path.update_user_image,file)
    }
}

export default api
