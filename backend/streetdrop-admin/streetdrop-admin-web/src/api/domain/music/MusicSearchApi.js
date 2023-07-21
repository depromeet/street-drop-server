import axios from "axios";
import {SEARCH_SERVER_URL} from "../../DefaultSetUp";


const MusicSearchApi = {
    searchMusic: (searchText) => {
        return axios.get(SEARCH_SERVER_URL + '/music', {
            params: {
                keyword: searchText
            }
        });
    }
}

export default MusicSearchApi;