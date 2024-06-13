import {
    BellOutlined,
    DingtalkOutlined,
    EnvironmentOutlined,
    PartitionOutlined,
    PieChartOutlined,
    SketchOutlined,
    UserOutlined
} from "@ant-design/icons";
import Dashboard from "../pages/Dashboard";
import ItemListPage from "../pages/items/ItemListPage";
import ItemReportPage from "../pages/items/ItemReportPage";
import React from "react";

const MENU_ITEMS = [
    {
        key: 'dashboard',
        icon: <PieChartOutlined/>,
        title: '대시보드',
        link: '/',
        element: <Dashboard/>
    },
    {
        key: 'dropitem-menu',
        icon: <DingtalkOutlined/>,
        title: '아이템 관리',
        children: [
            {key: 'dropitem-menu-1', title: '• 아이템 전체조회', link: '/items', element: <ItemListPage/>},
            {key: 'dropitem-menu-2', title: '• 아이템 드랍', link: '/drop-music'},
            {key: 'dropitem-menu-3', title: '• 아이템 신고', link: '/items/report', element: <ItemReportPage/>},
        ]
    }, {
        key: 'content-menu',
        icon: <SketchOutlined/>,
        title: '콘텐츠 관리',
        children: [
            {key: 'content-menu-1', title: '• 음악 검색어 추천', link: '/music/recommend'},
            {key: 'content-menu-2', title: '• 금칙어 관리', link: '/banned-words', element: <ItemListPage/>},
            {key: 'content-menu-3', title: '• 공지사항 관리', link: '/announcement'}
        ]
    },
    {
        key: 'location-menu',
        icon: <EnvironmentOutlined/>,
        title: '지역 관리',
        children: [{key: 'location-analysis', title: '• 지역별 분석', link: '/location/analysis'}]
    },
    {
        key: 'user-menu',
        icon: <UserOutlined/>,
        title: '사용자 관리',
        children: [
            {key: 'user-list', title: '• 유저조회', link: '/user/list'},
            {key: 'user-signup-graph', title: '• 유저가입 분석', link: '/user/signup-graph'},
            {key: 'user-block', title: '• 유저 차단내역', link: '/user/block'}
        ]
    },
    {
        key: 'notification-menu',
        icon: <BellOutlined/>,
        title: '푸시알림 관리',
        children: [
            {key: 'notification-create', title: '• 푸시알림 생성', link: '/notification/create'},
            {key: 'notification-list', title: '• 푸시알림 기록', link: '/notification/list'}
        ]
    },
    {
        key: 'admin-setting',
        icon: <PartitionOutlined/>,
        title: '어드민 관리',
        children: [
            {key: 'admin-setting-user', title: '• 어드민 유저 관리', link: '/members/list'},
            {key: 'admin-setting-login-log', title: '• 어드민 로그인 기록', link: '/members/login-log'},
            {key: 'admin-setting-security', title: '• 게정 보안 관리', link: '/members/security'}
        ]
    }
];

export default MENU_ITEMS;