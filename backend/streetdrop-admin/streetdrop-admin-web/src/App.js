import React from 'react';
import {Layout} from 'antd';
import SearchDropMusic from './components/music/drop/SearchDropMusic';
import DropMusicInMap from './components/music/drop/DropMusicInMap';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import DropMusicSuccess from './components/music/drop/DropMusicSuccess';
import DropMusicFail from './components/music/drop/DropMusicFail';
import Login from './pages/Login';
import MusicRecommend from './components/music/recommend/MusicRecommend';
import MusicList from './components/music/near/MusicList';
import MusicListPage from "./components/music/MusicListPage";
import UserListPage from "./components/user/UserListPage";
import CreateNotification from "./components/notification/CreateNotification";

const App = () => {
    return (
        <Layout className="layout">
            <Router>
                <Routes>
                    <Route exact path="/login" element={<Login/>}/>
                    <Route exact path="/" element={<Dashboard/>}/>
                    <Route path="/drop-music" element={<SearchDropMusic/>}/>
                    <Route path="/drop-music/details" element={<DropMusicInMap/>}/>
                    <Route path='/drop-music/result/success' element={<DropMusicSuccess/>}/>
                    <Route path='/drop-music/result/fail' element={<DropMusicFail/>}/>
                    <Route path='/music/recommend' element={<MusicRecommend/>}/>
                    <Route path='/music/map' element={<MusicList/>}/>
                    <Route path='/music/list' element={<MusicListPage/>}/>
                    <Route path='/user/list' element={<UserListPage/>}/>
                    <Route path='/notification/create' element={<CreateNotification/>}/>
                </Routes>
            </Router>
        </Layout>
    );
};


export default App;

