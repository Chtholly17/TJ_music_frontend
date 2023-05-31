// 本文件封装了一系列api请求
import axios from "@/utils/request";
import path from "@/service/path";

// 此处集成各种api函数，通过调用这些函数实现网络请求
const api = {
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
    getUserImage(user_id: any){
        return axios.get(path.baseUrl + path.getUserImage, {params: user_id})
    },
    getUserInfo(user_id:any)  //获取用户信息
    {
        return axios.get(path.baseUrl + path.getuserinfo,{
            params:{
                user_student_number:user_id
            }
            })
    },

    postSearchAccompanimentByKeyword(keyword: any)
    {
        return axios.post(path.baseUrl + path.postSearch_keyword, keyword)
    },

    //获取首页作品
    getMainPageWorks(worknum:any)
    {
        return axios.get(path.baseUrl+path.getMainPageWorks,{
            params:{
                workNumber:worknum
            }
        })
    },
    //获取作品评论信息
    //获取首页作品
    getComment(work_id:any)
    {
        return axios.get(path.baseUrl+path.getComment,{
            params:{
                workId:work_id
            }
        })
    },
    postComment(comment_target:any,comment_owner:any,comment_content:any){
        console.log("target:"+typeof(comment_target))
        console.log("owner:"+comment_owner)
        console.log("content:"+comment_content)
        return axios.post(path.baseUrl+path.postComment,
            {
                workCommentTarget:comment_target,
                workCommentOwner:comment_owner,
                workCommentContent:comment_content
            })
    },
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
        // for(const value of file.values()){
        //     console.log("jl2")
        //     console.log(value)
        // }
        //console.log(file)
        //axios.defaults.headers.post['Content-Type'] = 'multipart/form-data';

        return axios.post(path.baseUrl+path.update_user_image, file
        );
    },
    //修改密码
    postUpdatePassword(user_id:any,password:any) {
        return axios.post(path.baseUrl + path.update_password, {
                userNumber: user_id,
                password: password
        })
    },
    // postSearchAccompanimentByKeyword(keyword: any)
    // {
    //     return axios.post(path.baseUrl + path.postSearch_keyword, keyword)
    // },
    getWorksById(id: any)
    {
        return axios.get(path.baseUrl + path.getWorksById, {params: id})
    },
    //获取榜单
    getRankList(tag:any,order:any)
    {
        return axios.get(path.baseUrl+path.getRank,{
            params:{
                tag:tag,
                order:order
            }
        })
    },
    //用户登出
    userLogout(user_id:any)
    {
        return axios.post(path.baseUrl+path.logout,{
            userNumber:user_id,
        })
    },
    //获取当前的聊天对象
    getMessageObject(user_id:any)
    {
        return axios.get(path.baseUrl+path.getMessageBrief,{
            params:{
                user_student_number:user_id
            }
        })
    },
    postMessage(message: any)
    {
        console.log(message)
        return axios.post(path.baseUrl + path.sendMessage, message)
    },
    getDetailMessage(id1: any, id2: any)
    {
        return axios.get(path.baseUrl + path.getDetailMessage, {
            params:{
                user1_student_number: id1,
                user2_student_number: id2
            }
        })
    }

}

export default api
