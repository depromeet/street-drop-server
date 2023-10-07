import browserStorage from "../utils/BrowserStorage";


const userInfoService = {
    isInfoSaved : () => !!browserStorage.get('user-'),
    saveToken: (token) => browserStorage.set('token', token),
    getToken: () => browserStorage.get('token'),
    logout: () => browserStorage.remove('token'),
};

export default userInfoService;