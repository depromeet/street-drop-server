import {axiosAuthInstance} from "../../AxiosInstance";


const RecommendApi = {
    getMusicKeywordRecommend: (page, size) => {
        return axiosAuthInstance.get('/search-term/recommend', {
            params: {
                page: page,
                size: size
            }
        })
    },
    createMusicKeywordRecommend: (
        title, description, terms
    ) => {
        return axiosAuthInstance.post('/search-term/recommend', {
            title: title,
            description: description,
            terms: terms
        })
    }
}

export default RecommendApi;