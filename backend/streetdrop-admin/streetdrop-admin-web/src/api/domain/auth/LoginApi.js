import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";


function LoginApi(username, password) {
    return axios.post(ADMIN_SERVER_URL + '/login', {
        username: username,
        password: password
    });
}

export default LoginApi;