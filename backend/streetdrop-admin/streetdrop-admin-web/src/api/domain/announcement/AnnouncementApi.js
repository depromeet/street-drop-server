import {axiosAuthInstance} from "../../AxiosInstance";

const AnnouncementApi = {
    getAnnouncements: (page, size) => {
        return axiosAuthInstance.get('/announcements', {
            params: {
                page: page,
                size: size
            }
        });
    },
    getAnnouncement: (announcementId) => {
        return axiosAuthInstance.get('/announcements/' + announcementId);
    },
    createAnnouncement: (title, content) => {
        return axiosAuthInstance.post('/announcements', {
            title: title,
            content: content,
        });
    },
    deleteAnnouncement: (announcementId) => {
        return axiosAuthInstance.delete('/announcements/' + announcementId);
    },
}

export default AnnouncementApi;