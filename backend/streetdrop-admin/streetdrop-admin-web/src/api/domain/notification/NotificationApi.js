import axios from "axios";
import {NOTIFICATION_SERVER_URL} from "../../DefaultSetUp";


const NotificationApi = {

    sendNotification: (data) => {
        const config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: NOTIFICATION_SERVER_URL+'/push/all/send',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }

        return axios.request(config);
    },


    sendNotificationAll: (data) => {
        const config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: '/noti/push/send',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };
        return axios.request(config);
    }


}

export default NotificationApi;