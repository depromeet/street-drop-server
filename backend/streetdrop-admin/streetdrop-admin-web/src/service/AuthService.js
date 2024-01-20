import browserStorage from "../utils/BrowserStorage";
import cookieStorage from "../utils/CookieStorage";


const authService = {
    isLogin: () => !!browserStorage.get('accessToken'),
    getAccessToken: () => browserStorage.get('accessToken'),
    getRefreshToken: () => cookieStorage.get('refreshToken'),
    saveToken: (accessToken, refreshToken) => {
        browserStorage.set('accessToken', accessToken)
        cookieStorage.set('refreshToken', refreshToken)
    },
    logout: () => {
        browserStorage.remove('accessToken')
        cookieStorage.remove('refreshToken')
    },

};

export default authService;