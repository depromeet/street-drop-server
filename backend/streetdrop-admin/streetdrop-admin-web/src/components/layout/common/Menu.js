import {
    BellOutlined,
    DingtalkOutlined,
    ExperimentOutlined,
    FundProjectionScreenOutlined,
    PieChartOutlined,
    UserOutlined
} from '@ant-design/icons';
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

            <Menu.Item key="1" icon={<PieChartOutlined/>}>
                <Link to="/">대시보드</Link>
            </Menu.Item>
            <Menu.SubMenu key="sub1" icon={<DingtalkOutlined/>} title="드랍 아이템">
                <Menu.Item key="2">
                    <Link to="/music/list">• 음악조회</Link>
                </Menu.Item>
                <Menu.Item key="3">
                    <Link to="/music/map">• 음악조회-지도</Link>
                </Menu.Item>
                <Menu.Item key="4">
                    <Link to="/drop-music">• 음악드랍</Link>
                </Menu.Item>
                <Menu.Item key="5">
                    <Link to="/music/recommend">• 음악검색</Link>
                </Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="sub2" icon={<UserOutlined/>} title="사용자 관리">
                <Menu.Item key="6">
                    <Link to="/user/list">• 유저조회</Link>
                </Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="sub3" icon={<FundProjectionScreenOutlined/>} title="마케팅 관리">
                <Menu.Item key="7">• 캠페인 생성</Menu.Item>
                <Menu.Item key="8">• 캠페인 분석</Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="sub4" icon={<BellOutlined/>} title="푸시알림 관리">
                <Menu.Item key="7">• 푸시알림 생성</Menu.Item>
                <Menu.Item key="8">• 푸시알림 분석</Menu.Item>
            </Menu.SubMenu>
            <Menu.SubMenu key="sub5" icon={<ExperimentOutlined/>} title="실험 플랫폼">
                <Menu.Item key="9">• A/B 테스트</Menu.Item>
            </Menu.SubMenu>
        </Menu>
    );
};

export default MenuComponent;