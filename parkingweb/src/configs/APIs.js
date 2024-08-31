import axios from "axios"
import cookie from "react-cookies"

const SERVER_CONTEXT = "/MyParkingApp";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "parkinglots": `${SERVER_CONTEXT}/api/parkinglots/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER,
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
})