import {axiosAuthInstance} from "../../AxiosInstance";


const NicknameApi = {
    getNicknames() {
        return axiosAuthInstance.get("/users/nickname")
    },
    createNickname(preNickname, postNickname) {
        return axiosAuthInstance.post("/users/nickname", {
            preNickname: preNickname,
            postNickname: postNickname
        })
    }
}

export default NicknameApi;