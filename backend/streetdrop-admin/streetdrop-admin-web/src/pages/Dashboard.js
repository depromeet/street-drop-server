import React from "react";
import {Col, Row, Statistic, Typography} from 'antd';
import UserLineGraph from "../components/dashboard/UserLineGraph";
import DashboardLayout from "../components/layout/DashboardLayout";
import CountUp from 'react-countup';

const formatter = (value) => <CountUp end={value} separator=","/>;
const {Title} = Typography;

const data = [
    {
        "id": "전체 유저",
        "color": "hsl(210,98%,37%)",
        "data": [
            {
                "x": "06.19",
                "y": 5
            },
            {
                "x": "06.20",
                "y": 15
            },
            {
                "x": "06.21",
                "y": 30
            },
            {
                "x": "06.22",
                "y": 60
            },
            {
                "x": "06.23",
                "y": 100
            },
            {
                "x": "06.24",
                "y": 180
            },
            {
                "x": "06.25",
                "y": 190
            },
            {
                "x": "06.26",
                "y": 200
            }
        ]
    },
    {
        "id": "일자별 가입 유저",
        "color": "hsl(210,98%,37%)",
        "data": [
            {
                "x": "06.19",
                "y": 5
            },
            {
                "x": "06.20",
                "y": 10
            },
            {
                "x": "06.21",
                "y": 15
            },
            {
                "x": "06.22",
                "y": 30
            },
            {
                "x": "06.23",
                "y": 40
            },
            {
                "x": "06.24",
                "y": 80
            },
            {
                "x": "06.25",
                "y": 10
            },
            {
                "x": "06.26",
                "y": 10
            }
        ]
    }
]

function Dashboard() {
    const dashboard1 = (
        <>
            <Title level={5}>KPI 지표</Title>
            <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
            <Row gutter={24}
                 style={{marginBottom: '24px'}}>
                <Col span={8}>
                    <Statistic title="Active Users" value={112893} formatter={formatter}/>
                </Col>
                <Col span={8}>
                    <Statistic title="New User" value={4563} formatter={formatter}/>
                </Col>
                <Col span={8}>
                    <Statistic title="User Retention" value={4563} formatter={formatter}/>
                </Col>
            </Row>
        </>
    );

    const dashboard2 = (
        <>
            <Title level={5}>가입유저</Title>
            <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
            <UserLineGraph data={data}/>
        </>);
    return (
        <>
            <DashboardLayout children={dashboard1} children2={dashboard2}>
            </DashboardLayout>
        </>
    );
}

export default Dashboard;
