import React from 'react';
import {Navigate, Outlet} from 'react-router-dom';
import authService from "../service/AuthService";

const PrivateRoute = () => {
    const isUserLoggedIn = authService.isLogin();
    return isUserLoggedIn ? <Outlet/> : <Navigate to="/login"/>
}

export default PrivateRoute;