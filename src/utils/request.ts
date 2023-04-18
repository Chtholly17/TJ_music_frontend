// 网络请求相关文件，包括请求拦截器和相应拦截器

import axios, {InternalAxiosRequestConfig, AxiosInstance, AxiosResponse} from 'axios';
import qs from 'qs';

const axiosInstance: AxiosInstance = axios.create({
    timeout: 10000,
});

const errorHandle = (status: number, info: any) => {
    switch (status) {
        case 400:
            console.log("语义有误");
            break;
        case 401:
            console.log("服务器认证失败");
            break;
        case 403:
            console.log("服务器拒绝访问");
            break;
        case 404:
            console.log("地址错误");
            break;
        case 500:
            console.log("服务器遇到意外")
            break;
        case 502:
            console.log("服务器无响应")
            break;
        default:
            console.log(info);
            break;
    }
}

// 请求拦截器，所有的请求在发送前会在此处被处理
axiosInstance.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        if (config.method === 'post') {
            // console.log(config.data)
            config.data = qs.stringify(config.data) // THIS IS A MUST!!!
        }
        return config;
    },
    (error: any) => {
        return Promise.reject(error);
    },
);

// 响应拦截器，所有的后端响应在收到后会先在此处被处理
axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => {
        return response.status === 200 ? Promise.resolve(response) : Promise.reject(response);
    },
    (error: any) => {
        console.error(error)
        const {response} = error;
        errorHandle(response.status, response.info);
        return Promise.reject(error);
    },
);

export default axiosInstance;
