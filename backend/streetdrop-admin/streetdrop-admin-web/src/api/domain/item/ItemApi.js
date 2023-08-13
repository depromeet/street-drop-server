import axios from "axios";
import authService from "../../../service/AuthService";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";

const ItemApi = {
    getItems: (page, size) => {
        return axios.get(ADMIN_SERVER_URL+'/items', {
            params: {
                page: page,
                size: size
            },
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        })
    },

    deleteItem: (itemId) => {
        return axios.delete(ADMIN_SERVER_URL + '/items/' + itemId, {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    }
}
export default ItemApi;