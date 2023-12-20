import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/travel_web"
const SERVER = "http://localhost:8080";
export const endpoints ={
    "categories": `${SERVER_CONTEXT}/api/categories/`,
    "products":  `${SERVER_CONTEXT}/api/products/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "username-exist": `${SERVER_CONTEXT}/api/username-exist/`,
    "details": (productId) => `${SERVER_CONTEXT}/api/products/${productId}/`,
    "news":  `${SERVER_CONTEXT}/api/news/`,
    "imagetours": (productId) => `${SERVER_CONTEXT}/api/products/image/${productId}/`,
    "comments": (productId) => `${SERVER_CONTEXT}/api/products/${productId}/comments/`,
    "add-comment": `${SERVER_CONTEXT}/api/comments/`,
    "rating":(productId) => `${SERVER_CONTEXT}/api/products/${productId}/rating/`,
    "add-rating":`${SERVER_CONTEXT}/api/rating/`,
    "comments-new":(productId) => `${SERVER_CONTEXT}/api/news/${productId}/comments/`,
    "add-comments-new":`${SERVER_CONTEXT}/api/commentsNews/`,
    "info-cus":(productId) => `${SERVER_CONTEXT}/api/customer/${productId}/`,
    "addReceipts":`${SERVER_CONTEXT}/api/addReceipts/`,
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization":  cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})