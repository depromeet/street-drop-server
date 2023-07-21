export const ADMIN_SERVER_URL =
    process.env.REACT_APP_NODE_ENV === 'dev' ? '/admin' : process.env.REACT_APP_BASE_URL;
export const API_SERVER_URL =
    process.env.REACT_APP_NODE_ENV === 'dev' ? '/api' : `${process.env.REACT_APP_API_URL}`;
export const SEARCH_SERVER_URL =
    process.env.REACT_APP_NODE_ENV === 'dev' ? '/search' : `${process.env.REACT_APP_SEARCH_URL}`;
export const NOTIFICATION_SERVER_URL =
    process.env.REACT_APP_NODE_ENV === 'dev' ? '/notification' : `${process.env.REACT_APP_NOTIFICATION_URL}`;
