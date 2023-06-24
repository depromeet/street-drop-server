import React from 'react';
import {Layout} from 'antd';
import SearchDropMusic from './components/music/drop/SearchDropMusic';
import DropSingleMusic from './components/music/drop/DropSingleMusic';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import DropMusicSuccess from './components/music/drop/result/DropMusicSuccess';
import DropMusicFail from './components/music/drop/result/DropMusicFail';
import Login from './pages/Login';
import MusicRecommend from './components/music/recommend/MusicRecommend';
import MusicList from './components/music/near/MusicList';
import MusicListPage from "./components/music/MusicListPage";
import UserListPage from "./components/user/UserListPage";

const App = () => {
    return (
        <Layout className="layout">
            <Router>
                <Routes>
                    <Route exact path="/login" element={<Login/>}/>
                    <Route exact path="/" element={<Dashboard/>}/>
                    <Route path="/drop-music" element={<SearchDropMusic/>}/>
                    <Route path="/drop-music/details" element={<DropSingleMusic/>}/>
                    <Route path='/drop-music/result/success' element={<DropMusicSuccess/>}/>
                    <Route path='/drop-music/result/fail' element={<DropMusicFail/>}/>
                    <Route path='/music/recommend' element={<MusicRecommend/>}/>
                    <Route path='/music/map' element={<MusicList/>}/>
                    <Route path='/music/list' element={<MusicListPage/>}/>
                    <Route path='/user/list' element={<UserListPage/>}/>
                </Routes>
            </Router>
        </Layout>
    );
};


export default App;

