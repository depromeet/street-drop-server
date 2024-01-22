import {axiosAuthInstance} from "../../AxiosInstance";


const RecommendApi = {
    getMusicKeywordRecommend: (page, size) => {
        return axiosAuthInstance.get('/search-term/recommend', {
            params: {
                page: page,
                size: size
            }
        })
    }
}

export default RecommendApi;