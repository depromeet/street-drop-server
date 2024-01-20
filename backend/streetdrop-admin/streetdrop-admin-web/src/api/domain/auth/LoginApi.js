import {axiosInstance} from "../../AxiosInstance";


function LoginApi(username, password) {
    return axiosInstance.post('/login', {
        username: username,
        password: password
    });
}

export default LoginApi;