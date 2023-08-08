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
    }
}

export default MemberApi;