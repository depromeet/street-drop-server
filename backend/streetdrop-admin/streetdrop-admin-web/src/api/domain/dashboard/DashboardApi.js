import axios from "axios";
import {ADMIN_SERVER_URL} from "../../DefaultSetUp";
import authService from "../../../service/AuthService";


const DashboardApi = {
  getIndicators: () => {
      return axios.get(ADMIN_SERVER_URL + '/dashboard/indicators', {
          headers: {
              'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': '*',
              'Authorization': 'Bearer ' + authService.getToken()
          }
      });
  },

    getRecentReports: () => {
        return axios.get(ADMIN_SERVER_URL + '/dashboard/reports', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    },

    getVersions: () => {
        return axios.get(ADMIN_SERVER_URL + '/dashboard/versions', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    },

    getAppStoreIndicators: () => {
        return axios.get(ADMIN_SERVER_URL + '/dashboard/appstore-indicators', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    },

    getRegionalAnalysis: () => {
        return axios.get(ADMIN_SERVER_URL + '/dashboard/regional-analysis', {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + authService.getToken()
            }
        });
    },
};

export default DashboardApi;