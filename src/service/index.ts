// 本文件封装了一系列api请求
import axios from "@/utils/request";
import path from "@/service/path";

// 此处集成各种api函数，通过调用这些函数实现网络请求
const api = {
    postLogin(loginForm: any) {
        return axios.post(path.baseUrl + path.login, loginForm)
    },
    postSendRegisterVRCode(userInfo: any) {
        return axios.post(path.baseUrl + path.registerSendVRCode, userInfo)
    },
    postRegister(registerForm: any) {
        return axios.post(path.baseUrl + path.register, registerForm)
    },
    postSendRetrieveVRCode(userInfo: any) {
        // console.log("SEND!")
        return axios.post(path.baseUrl + path.retrieveSendVRCode, userInfo)
    },
    postRetrieve(retrieveForm: any)
    {
        return axios.post(path.baseUrl + path.retrieve, retrieveForm)
    },
    postUserInfo(userInfo:any){
        return axios.post(path.baseUrl+path.postUserInfo,userInfo)
    },
    getUserImage(user_id: any){
        return axios.get(path.baseUrl + path.getUserImage, {params: user_id})
    },

    getOriginMusic() {
        return axios.get(path.baseUrl + path.getOriginMusic)
    },
    getUserManager() {
        return axios.get(path.baseUrl + path.getUserManager)
    },
    getAppeal() {
        return axios.get(path.baseUrl + path.getAppeal)
    },

    getUserInfo(user_id:any)  //获取用户信息
    {
        return axios.get(path.baseUrl + path.getUserInfo,{
            params:{
                userStudentNumber:user_id
            }
            })
    },

    postSearchAccompanimentByKeyword(keyword: any)
    {
        return axios.post(path.baseUrl + path.postSearchKeyword, keyword)
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
    getComment(work_id:any)
    {
        return axios.get(path.baseUrl+path.getComment,{
            params:{
                workId:work_id
            }
        })
    },
    //根据原唱ID获取原唱信息
    getOriginByOriginId(origin_id:any)
    {
        return axios.post(path.baseUrl+path.getOriginByOriginId,origin_id)
    },
    searchOriginByWorkId(work_id:any)
    {
        return axios.post(path.baseUrl+path.getOriginByOriginId,work_id)
    },
    //根据work_id获取work信息
    getWorkById(work_id:any)
    {
        return axios.get(path.baseUrl+path.getWorkById,{
            params:{
                workId:work_id
            }
        })
    },
    selectUserById(user_id:any)
    {
        return axios.get(path.baseUrl+path.selectUserById,{
            params:{
                userId:user_id
            }
        })
    },
    postComment(comment_target:any,comment_owner:any,comment_content:any){
        // console.log("target:"+typeof(comment_target))
        // console.log("owner:"+comment_owner)
        // console.log("content:"+comment_content)
        return axios.post(path.baseUrl+path.postComment,
            {
                workCommentTarget:comment_target,
                workCommentOwner:comment_owner,
                workCommentContent:comment_content
            })
    },
    postWork(work_name:any,work_comment:any,work_owner:any,work_origin_version:any,work_voice_file_name:any,work_precise_score:any,work_quality_score:any,work_pitch_score:any){
        // console.log("work_name:"+work_name)
        // console.log("work_owner:"+work_owner)
        // console.log("work_origin_version："+work_origin_version)
        return axios.post(path.baseUrl+path.postWork,
            {
                workName:work_name,
                workComment:work_comment,
                workOwner:work_owner,
                workOriginVersion:work_origin_version,
                workVoiceFilename:work_voice_file_name,
                workPreciseScore:work_precise_score,
                workQualityScore:work_quality_score,
                workPitchScore:work_pitch_score
            })
    },
    getSongList(user_id:any)//获取用户曲库
    {
        return axios.get(path.baseUrl+path.getSongList,{
            params:{
                userStudentNumber:user_id
            }
        })
    },
    //获取粉丝列表
    getFanList(user_id:any)//获取用户粉丝列表
    {
        return axios.get(path.baseUrl+path.getFanList,{
            params:{
                userStudentNumber:user_id
            }
        })
    },
    //获取关注列表
    getFollowList(user_id:any)
    {
        return axios.get(path.baseUrl+path.getFollowList,{
            params:{
                userStudentNumber:user_id
            }
        })
    },
    //关注某用户
    follow(user_id:any,follow_id:any)
    {
        return axios.post(path.baseUrl+path.follow,
            {
                userStudentNumber:user_id,
                target_student_number:follow_id
            })
    },
    //取消关注某用户
    deleteFollow(user_id:string,follow_id:string)
    {
        return axios.delete(path.baseUrl+path.deleteFollow,{
            params:{    //这里必须用params，不能用data，否则无法正确删除
                userStudentNumber:user_id,
                target_student_number:follow_id
            }
        })
    },
    //获取用户曲库
    getMusicLibrary(user_id:any)
    {
        return axios.get(path.baseUrl+path.getMusicLibrary,{
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

        return axios.post(path.baseUrl+path.updateUserImage, file
        );
    },
    //修改密码
    postUpdatePassword(user_id:any,password:any) {
        return axios.post(path.baseUrl + path.updatePassword, {
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
                userStudentNumber:user_id
            }
        })
    },
    postMessage(message: any)
    {
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
    },
    //查看是否关注某人
    checkFollow(user_id:any,target_id:any){
        return axios.get(path.baseUrl+path.checkFollow,{
            params:{
                userStudentNumber:user_id,
                target_student_number:target_id
            }
        })
    }

}

export default api
