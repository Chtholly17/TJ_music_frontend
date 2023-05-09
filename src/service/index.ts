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
        return axios.get(path.baseUrl + path.getuserinfo,user_id)
    },
    postSearchAccompaniment(keyword: any)
    {
        console.log(keyword)
        // return axios.get(path.baseUrl + path.postSearch, keyword)
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
}

export default api
