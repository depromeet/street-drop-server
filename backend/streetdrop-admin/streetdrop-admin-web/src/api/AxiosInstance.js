import axios from 'axios';
import authService from "../service/AuthService";
import {ADMIN_SERVER_URL} from "./DefaultSetUp";


const axiosResultSuccessInterceptor = (response) => {
    const validStatusCodes = [200, 201, 204, 400, 404];

    if (!validStatusCodes.includes(response.status)) {
        throw new Error();
    }

    if (response.data.errors) {
        throw new Error(response.data.errors);
    }

    return response;
};

const axiosResultErrorInterceptor = async (error) => {
    if (error.response?.status === 401) {
        authService.logout();
        window.location.href = '/login';
    }

    return Promise.reject(error);
};


const axiosAuthAPI = (url, options) => {
    const token = authService.getAccessToken();
    const axiosResult = axios.create({
        baseURL: url,
        headers: {
            Authorization: `Bearer ${token}`,
            ContentType: 'application/json',
            AccessControlAllowOrigin: '*',
        },
        ...options,
    })

    axiosResult.interceptors.response.use(
        axiosResultSuccessInterceptor,
        axiosResultErrorInterceptor
    );

    return axiosResult;

}

const baseAxiosAPI = (url, options) => {
    return axios.create({
        baseURL: url,
        headers: {
            ContentType: 'application/json',
            AccessControlAllowOrigin: '*',
        },
        ...options,
    })
}


export const axiosAuthInstance = axiosAuthAPI(ADMIN_SERVER_URL);
export const axiosInstance = baseAxiosAPI(ADMIN_SERVER_URL);
