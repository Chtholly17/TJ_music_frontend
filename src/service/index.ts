import axios from "@/utils/request";
import path from "@/service/path";

// 此处集成各种api函数，通过调用这些函数实现网络请求
const api = {
    getTest() {
        return axios.get(path.baseUrl + path.getTest)
    },
    postTest(data: any)
    {
        // const data_ = new URLSearchParams();
        // data_.append('test_string', 'hahahaha')
        // console.log(data_)
        // data = JSON.stringify(data)
        return axios.post(path.baseUrl + path.postTest, data)
    },
    postLogin(loginForm: any) {
        return axios.post(path.baseUrl + path.login, loginForm)
    },
    postRegister(registerForm: any) {
        return axios.post(path.baseUrl + path.register, registerForm)
    }
}

export default api
