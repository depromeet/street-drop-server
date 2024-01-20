import React from 'react';
import {Layout} from 'antd';
import SearchDropMusic from './components/items/drop/SearchDropMusic';
import DropSingleMusic from './components/items/drop/DropSingleMusic';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import DropMusicSuccess from './components/items/drop/result/DropMusicSuccess';
import DropMusicFail from './components/items/drop/result/DropMusicFail';
import Login from './pages/Login';
import MusicRecommend from './components/items/music/recommend/MusicRecommend';
import UserListPage from "./components/user/UserListPage";
import CreateNotification from "./components/notification/CreateNotification";
import ItemListPage from "./components/items/ItemListPage";
import UserSignUpGraph from "./components/user/UserSignUpGraph";
import LocationAnalysis from "./components/location/LocationAnalysis";
import PrivateRoute from "./route/PrivateRoute";
import UserBlockPage from "./components/user/UserBlockPage";
import MemberListPage from "./components/member/MemberListPage";
import MemberLoginLogPage from "./components/member/MemberLoginLogPage";
import MemberSecuritySettingPage from "./components/member/MemberSecuritySettingPage";
import ItemReportPage from "./components/items/ItemReportPage";
import BannedWordPage from "./components/items/BannedWordPage";

const App = () => {
    return (
        <Layout className="layout">
            <Router>
                <Routes>
                    <Route exact path="/login" element={<Login/>}/>
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
                        <Route path='/user/signup-graph' element={<UserSignUpGraph/>}/>
                        <Route path='/notification/create' element={<CreateNotification/>}/>
                        <Route path='/members/list' element={<MemberListPage/>}/>
                        <Route path='/members/login-log' element={<MemberLoginLogPage/>}/>
                        <Route path='/members/security' element={<MemberSecuritySettingPage/>}/>
                    </Route>
                </Routes>
            </Router>
        </Layout>
    );
};


export default App;

