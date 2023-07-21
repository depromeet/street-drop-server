import axios from "axios";
import {ADMIN_SERVER_URL, API_SERVER_URL} from "../../DefaultSetUp";


function DropItemApi(data) {
    return axios.post(ADMIN_SERVER_URL + '/login', {
        method: 'post',
        maxBodyLength: Infinity,
        url: API_SERVER_URL + '/items',
        headers: {
            'x-sdp-idfv': 'admin',
            'Content-Type': 'application/json'
        },
        data: data
    });
}

export default DropItemApi;