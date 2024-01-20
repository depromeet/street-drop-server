import {axiosAuthInstance} from "../../AxiosInstance";

const UserBlockApi = {
    getAllBlockList: (page, size) => {
        return axiosAuthInstance.get('/users/blocks', {
            params: {
                page: page,
                size: size
            }
        });
    }
}
export default UserBlockApi;