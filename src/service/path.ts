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
    follow:'/follow',    //关注某用户
    delete_follow:"/unfollow",  //取消关注某人
    postSearch_keyword: '/searchOriginByTag',
    getWorksById: '/relatedWork',
    get_musicLibrary:"/UserProfile",    //获取用户曲库
    update_user_image:"/updateUserImage",   //上传用户头像
    update_password:"/updatePassword", //
    getMainPageWorks:'/mainPageWorks',  //获取首页作品
    getComment:'/getWorkCommentByWorkId',  //获取作品评论
    postComment:'/addWorkComment',//提交评论
    getRank:'/getWorkList', //获取榜单
    getUserImage:'/getUserImage',  //获取用户头像
    sendMessage: '/sendMessage',
    logout:'/logout',   //登出
    getMessageBrief:'/getMessageBrief', //获取消息列表
    getDetailMessage: '/getMessageList', // 获取两个用户间的详细信息
    getOriginByOriginId:'/getOriginByOriginId',//根据原唱ID获取原唱信息
    getOriginByWorkId:'/searchOriginByWorkId',//根据作品ID获取原唱信息
    getWorkById:'/getWorkById',//根据work_id获取work信息
    selectUserById:'/selectUserById',
    postMusic:'/getScores',
    postWork:'/insertWork',//提交作品,
    getAiComment:'/getComments',
    check_follow:'/check_follow',   //查看是否已经关注某人
}

export default base;
