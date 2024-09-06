import axios from "axios"
import cookie from "react-cookies"

const SERVER_CONTEXT = "/MyParkingApp";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "parkinglots": `${SERVER_CONTEXT}/api/parkinglots/`,
    "login": `${SERVER_CONTEXT}/api/login/`,
    "current-user": `${SERVER_CONTEXT}/api/current-user/`,
    "register": `${SERVER_CONTEXT}/api/accounts/`,
    "parkingspots": (parkingLotID) => `${SERVER_CONTEXT}/api/parkinglots/${parkingLotID}/parkingspots/`,
    "getparkinglot": (parkingLotID) => `${SERVER_CONTEXT}/api/parkinglots/${parkingLotID}`,
    "pay": `${SERVER_CONTEXT}/api/pay/`,
    "reservations": (id) => `${SERVER_CONTEXT}/api/reservations/${id}/`,
    "updateEmpty": (id) => `${SERVER_CONTEXT}/api/parkingspot/update-empty/${id}/`,
    "updateInUse": (id) => `${SERVER_CONTEXT}/api/parkingspot/update-inuse/${id}/`,
    "update_reservation": (id) => `${SERVER_CONTEXT}/api/reservation/${id}/`
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