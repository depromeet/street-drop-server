import authService from "../../../service/AuthService";
import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";

const UserBlockApi = {
    getAllBlockList: (page, size) => {
        return axios.get(ADMIN_SERVER_URL+'/users/blocks' + '?page=' + page + '&size=' + size,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': 'Bearer ' + authService.getToken()
                }
            });
    }
}
export default UserBlockApi;