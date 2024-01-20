import {axiosAuthInstance} from "../../AxiosInstance";

const MemberLoginLogApi = {
    getAllLoginLog: (page, size) => {
        return axiosAuthInstance.get('/member/login-log', {
            params: {
                page: page,
                size: size
            }
        });
    }
}

export default MemberLoginLogApi;