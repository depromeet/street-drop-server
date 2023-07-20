import {BellOutlined, DingtalkOutlined, PieChartOutlined, UserOutlined, EnvironmentOutlined} from '@ant-design/icons';
import React from 'react';
import {Link} from 'react-router-dom';
import {Menu} from 'antd';

const MenuComponent = () => {
    return (
        <Menu
            theme="dark"
            mode="inline"
            defaultSelectedKeys={['1']}
        >

            <Menu.Item key="dashboard" icon={<PieChartOutlined/>}>
                <Link to="/">대시보드</Link>
            </Menu.Item>
            <Menu.SubMenu key="dropitem-menu" icon={<DingtalkOutlined/>} title="드랍 아이템">
                <Menu.Item key="dropitem-menu-1">
                    <Link to="/items">• 아이템 전체조회</Link>
                </Menu.Item>
                <Menu.Item key="dropitem-menu-2">
                    <Link to="/drop-music">• 아이템 드랍</Link>
                </Menu.Item>
                <Menu.Item key="dropitem-menu-3">
                    <Link to="/music/recommend">• 음악 검색어 추천</Link>
                </Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="location-menu" icon={<EnvironmentOutlined />} title="지역 관리">
                <Menu.Item key="location-menu-1">
                    <Link to="/location/analysis">• 지역별 분석</Link>
                </Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="user-menu" icon={<UserOutlined/>} title="사용자 관리">
                <Menu.Item key="user-menu-1">
                    <Link to="/user/list">• 유저조회</Link>
                </Menu.Item>
                <Menu.Item key="user-menu-2">
                    <Link to="/user/signup-graph">• 유저가입 분석</Link>
                </Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="sub4" icon={<BellOutlined/>} title="푸시알림 관리">
                <Menu.Item key="9">
                    <Link to="/notification/create">• 푸시알림 생성</Link></Menu.Item>
                <Menu.Item key="10">• 푸시알림 기록</Menu.Item>
            </Menu.SubMenu>
        </Menu>
    );
};

export default MenuComponent;