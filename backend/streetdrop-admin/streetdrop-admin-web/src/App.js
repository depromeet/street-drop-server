import React from 'react';
import {Layout} from 'antd';
import SearchDropMusic from './components/items/drop/SearchDropMusic';
import DropSingleMusic from './components/items/drop/DropSingleMusic';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import DropMusicSuccess from './components/items/drop/result/DropMusicSuccess';
import DropMusicFail from './components/items/drop/result/DropMusicFail';

import MusicRecommend from './pages/content/recommend/MusicRecommend';
import UserListPage from "./pages/user/UserListPage";
import CreateNotificationPage from "./pages/notification/CreateNotificationPage";
import ItemListPage from "./pages/items/ItemListPage";
import LocationAnalysis from "./components/location/LocationAnalysis";
import PrivateRoute from "./route/PrivateRoute";
import UserBlockPage from "./pages/user/UserBlockPage";
import MemberListPage from "./pages/member/MemberListPage";
import MemberLoginLogPage from "./pages/member/MemberLoginLogPage";
import MemberSecuritySettingPage from "./pages/member/MemberSecuritySettingPage";
import ItemReportPage from "./pages/items/ItemReportPage";
import BannedWordPage from "./pages/content/BannedWordPage";
import NotitificationListPage from "./pages/notification/NotificationListPage";
import LoginPage from "./pages/login/LoginPage";
import UserSignUpGraphPage from "./pages/user/UserSignUpGraphPage";
import CommonHeader from "./layout/common/CommonHeader";
import Sider from "antd/es/layout/Sider";
import MenuComponent from "./layout/common/Menu";

const App = () => {
    return (
        <Layout className="layout">
            <Router>
                <CommonHeader/>
                <Layout style={{minHeight: '100vh'}}>
                    <Sider>
                        <MenuComponent/>
                    </Sider>
                <Routes>
                    <Route exact path="/login" element={<LoginPage/>}/>
                    <Route element={<PrivateRoute/>}>
                        <Route exact path="/" element={<Dashboard/>}/>
                        <Route path="/items" element={<ItemListPage/>}/>
                        <Route path="/items/report" element={<ItemReportPage/>}/>
                        <Route path='/banned-words' element={<BannedWordPage/>}/>
                        <Route path="/drop-music" element={<SearchDropMusic/>}/>
                        <Route path="/drop-music/details" element={<DropSingleMusic/>}/>
                        <Route path='/drop-music/result/success' element={<DropMusicSuccess/>}/>
                        <Route path='/drop-music/result/fail' element={<DropMusicFail/>}/>
                        <Route path='/music/recommend' element={<MusicRecommend/>}/>
                        <Route path='/location/analysis' element={<LocationAnalysis/>}/>
                        <Route path='/user/list' element={<UserListPage/>}/>
                        <Route path='/user/block' element={<UserBlockPage/>}/>
                        <Route path='/user/signup-graph' element={<UserSignUpGraphPage/>}/>
                        <Route path='/notification/create' element={<CreateNotificationPage/>}/>
                        <Route path='/notification/list' element={<NotitificationListPage/>}/>
                        <Route path='/members/list' element={<MemberListPage/>}/>
                        <Route path='/members/login-log' element={<MemberLoginLogPage/>}/>
                        <Route path='/members/security' element={<MemberSecuritySettingPage/>}/>
                    </Route>
                </Routes>
                </Layout>
            </Router>
        </Layout>
    );
};


export default App;

