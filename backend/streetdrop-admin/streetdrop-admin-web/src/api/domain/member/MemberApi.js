import {axiosAuthInstance} from "../../AxiosInstance";


const MemberApi = {
    getMemberInfo: () => {
        return axiosAuthInstance.get('/member/me');
    },
    getAllMembers: (page, size) => {
        return axiosAuthInstance.get('/member', {
            params: {
                page: page,
                size: size
            }
        });
    },
    changePassword: (prevPassword, newPassword) => {
        const data = {
            prevPassword: prevPassword,
            newPassword: newPassword
        }
        return axiosAuthInstance.patch('/member/me/change-password', data);
    }
}

export default MemberApi;