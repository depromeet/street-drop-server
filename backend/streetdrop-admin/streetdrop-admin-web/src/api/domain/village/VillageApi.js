import axios from "axios";
import authService from "../../../service/AuthService";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";


const VillageApi = {
    getVillageItemCountRecent: () => {
        return axios.get(ADMIN_SERVER_URL + '/villages/items/count/recent',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': 'Bearer ' + authService.getToken()
                }
            })
    },

    getVillageItemCountAll: () => {
        return axios.get(ADMIN_SERVER_URL + '/villages/items/count',
            {
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': 'Bearer ' + authService.getToken()
                }
            });
    }
}

export default VillageApi;