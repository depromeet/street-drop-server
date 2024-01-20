import {axiosAuthInstance} from "../../AxiosInstance";

const ItemClaimApi = {
    getItemClaim: (page, size) => {
        return axiosAuthInstance.get('/items/claim', {
            params: {
                page: page,
                size: size
            }
        })
    },
    updateItemStatus: (id, status) => {
        return axiosAuthInstance.patch('/items/claim', {
            itemClaimId: id,
            itemClaimStatus: status
        })
    },
}
export default ItemClaimApi;