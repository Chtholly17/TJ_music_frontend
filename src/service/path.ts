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
    postuserinfo:'/updateUserinfo',//提交用户信息
    postSearch: '',
    getMainPageWorks:'/mainPageWorks',  //获取首页作品
    getComment:'/getWorkCommentByWorkId',  //获取作品评论
    postComment:'/addWorkComment'//提交评论
}

export default base;
