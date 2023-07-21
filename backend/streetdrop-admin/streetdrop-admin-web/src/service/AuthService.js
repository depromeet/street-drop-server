import browserStorage from "../utils/BrowserStorage";


const authService = {
    isLogin: () => !!browserStorage.get('token'),
    saveToken: (token) => browserStorage.set('token', token),
    getToken: () => browserStorage.get('token'),
    logout: () => browserStorage.remove('token'),
};

export default authService;