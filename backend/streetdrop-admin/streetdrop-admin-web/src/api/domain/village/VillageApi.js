import {axiosAuthInstance} from "../../AxiosInstance";


const VillageApi = {
    getVillageItemCountRecent: () => {
        return axiosAuthInstance.get('/villages/items/count/recent');
    },

    getVillageItemCountAll: () => {
        return axiosAuthInstance.get('/villages/items/count');
    }
}

export default VillageApi;