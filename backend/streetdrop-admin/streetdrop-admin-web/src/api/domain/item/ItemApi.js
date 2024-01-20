import {axiosAuthInstance} from "../../AxiosInstance";

const ItemApi = {
    getItems: (page, size) => {
        return axiosAuthInstance.get('/items', {
            params: {
                page: page,
                size: size
            }
        })
    },
    getItem: (itemId) => {
        return axiosAuthInstance.get('/items/' + itemId)
    },
    deleteItem: (itemId) => {
        return axiosAuthInstance.delete('/items/' + itemId)
    }
}
export default ItemApi;