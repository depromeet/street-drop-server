import {axiosAuthInstance} from "../../AxiosInstance";


const DashboardApi = {
    getIndicators: () => {
        return axiosAuthInstance.get('/dashboard/indicators');
    },
    getRecentReports: () => {
        return axiosAuthInstance.get('/dashboard/reports');
    },
    getVersions: () => {
        return axiosAuthInstance.get('/dashboard/versions');
    },
    getAppStoreIndicators: () => {
        return axiosAuthInstance.get('/dashboard/appstore-indicators');
    },
    getRegionalAnalysis: () => {
        return axiosAuthInstance.get('/dashboard/regional-analysis');
    },
};

export default DashboardApi;