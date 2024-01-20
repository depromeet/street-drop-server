import {axiosAuthInstance} from "../../AxiosInstance";

const UserApi = {
    getUser: (id) => {
        return axiosAuthInstance.get('/users/' + id)
    },

    getAllUser: (page, size) => {
        return axiosAuthInstance.get('/users', {
            params: {
                page: page,
                size: size
            }
        })
    }
}
export default UserApi;