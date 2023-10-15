import axios from "axios";
import authService from "../../../service/AuthService";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";


const MemberApi = {
    getMemberInfo: () => {
        return axios
            .get(ADMIN_SERVER_URL+'/member/me',
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': 'Bearer ' + authService.getToken()
                    }
                }
            );
    },
    getAllMembers: (page, size) => {
        return axios
            .get(ADMIN_SERVER_URL + '/member' + '?page=' + page + '&size=' + size,
                {
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': 'Bearer ' + authService.getToken()
                    }
                }
            );
    },
    changePassword: (prevPassword, newPassword) => {
        const data = {
            prevPassword: prevPassword,
            newPassword: newPassword
        }
        return axios.patch(ADMIN_SERVER_URL + '/member/me/change-password', data, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                maxBodyLength: Infinity,
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    }
}

export default MemberApi;