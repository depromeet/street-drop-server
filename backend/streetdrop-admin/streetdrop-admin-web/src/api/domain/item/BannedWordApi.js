import {axiosAuthInstance} from "../../AxiosInstance";


const BannedWordApi = {
    getBannedWords: (page, size) => {
        return axiosAuthInstance.get('/banned-words', {
            params: {
                page: page,
                size: size
            }
        })
    },
}

export default BannedWordApi;