import axios from "axios";
import authService from "../../../service/AuthService";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";

const UserStaticApi = {

    getUserKpiData: () => {
        return axios.get(ADMIN_SERVER_URL+'/users/statical/all/count', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    }

}

export default UserStaticApi;