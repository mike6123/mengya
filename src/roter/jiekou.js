import axios from 'axios';

// 创建 axios 实例
const request = axios.create({
  baseURL: 'http://localhost:8081', // 后端 Spring Boot 服务的基础地址
  timeout: 5000 // 超时时间
});

// 请求拦截器（可选，用于添加 token 等）
request.interceptors.request.use(
  config => {
    // 登录前通常不需要 token，可省略
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

// 响应拦截器（处理后端返回的结果）
request.interceptors.response.use(
  response => {
    return response; 
  },
  error => {
    return Promise.reject(error);
  }
);
export default request;