import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";
import authService from "../../../service/AuthService";

const SignUpApi = {
    signUp: (username, email, part, name, password) => {
        const data = {
            username: username,
            email: email,
            part: part,
            name: name,
            password: password
        }
        console.log(data);

        return axios
            .post(ADMIN_SERVER_URL + '/sign-up', data,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': 'Bearer ' + authService.getToken()
                    }
                }
            );
    }
}

export default SignUpApi;