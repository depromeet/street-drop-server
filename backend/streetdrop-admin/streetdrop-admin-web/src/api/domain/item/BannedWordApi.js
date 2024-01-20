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
    createBannedWord: (bannedWord) => {
        return axiosAuthInstance.post('/banned-words', {
            word: bannedWord
        })
    },
    deleteBannedWord: (id) => {
        return axiosAuthInstance.delete('/banned-words/' + id)
    }
}

export default BannedWordApi;