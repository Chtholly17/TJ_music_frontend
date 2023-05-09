// 所有地址相关都放在这里，其中/api是根目录，在vue.config.js中将会被替换为后端的IP地址

const base = {
    baseUrl: '/api',
    // postTest: '/test_return_string',
    // getTest: '/get_hello_world',
    login: '/login',
    register_sendVRCode: '/register',
    register: '/registerCheck',
    retrieve_sendVRCode: '/forgetPassword',
    retrieve: '/forgetPasswordCheck',
    getuserinfo:'/getUserInfo',    //获取用户信息
    postuserinfo:'/updateUserinfo',  //提交用户信息
    get_songList:'/songList',   //获取用户曲库
    get_fanList:"/display_user_follower",   //获取用户粉丝
    get_followList:"/display_user_following",    //获取用户关注
    delete_follow:"/unfollow",  //取消关注某人
    postSearch_keyword: '/searchOriginByTag',
    getWorksById: '/relatedWork',
    get_musicLibrary:"/UserProfile",    //获取用户曲库
    update_user_image:"/updateUserImage",   //上传用户头像
    update_password:"/updatePassword", //
}

export default base;
