import authService from "../../../service/AuthService";
import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";

const UserApi = {
    getUser: (id) => {
        return axios.get(ADMIN_SERVER_URL+'/users/' + id, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        })
    },

    getAllUser: (page, size) => {
        return axios.get(ADMIN_SERVER_URL+'/users' + '?page=' + page + '&size=' + size,
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': 'Bearer ' + authService.getToken()
                }
            });
    }
}
export default UserApi;