import axios from "axios";
import authService from "../../../service/AuthService";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";


const UserSignUpStaticApi = {
    getUserSignUpStaticCount: (startDate, endDate) => {
        return axios.get(ADMIN_SERVER_URL + '/users/statical/signup/count', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            },
            params: {
                startDate: startDate,
                endDate: endDate
            }
        });
    }
}

export default UserSignUpStaticApi;