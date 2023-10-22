import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";
import authService from "../../../service/AuthService";

const MemberLoginLogApi = {
    getAllLoginLog: (page, size) => {
        return axios
            .get(ADMIN_SERVER_URL + '/member/login-log' + '?page=' + page + '&size=' + size,
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

export default MemberLoginLogApi;