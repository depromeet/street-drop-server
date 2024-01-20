import {axiosAuthInstance} from "../../AxiosInstance";


const UserSignUpStaticApi = {
    getUserSignUpStaticCount: (startDate, endDate) => {
        return axiosAuthInstance.get('/users/statical/signup/count', {
            params: {
                startDate: startDate,
                endDate: endDate
            }
        });
    }
}

export default UserSignUpStaticApi;