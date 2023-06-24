import React from "react";
import {Col, Row, Statistic, Typography} from 'antd';
import UserLineGraph from "../components/dashboard/UserLineGraph";
import DashboardLayout from "../components/layout/DashboardLayout";
import CountUp from 'react-countup';
import ItemDashboard from "../components/dashboard/ItemDashboard";
import MarketingDashboard from "../components/dashboard/MarketingDashboard";

const formatter = (value) => <CountUp end={value} separator=","/>;
const {Title} = Typography;

const data = [{
    "id": "전체 유저", "color": "hsl(210,98%,37%)", "data": [{
        "x": "06.19", "y": 5
    }, {
        "x": "06.20", "y": 15
    }, {
        "x": "06.21", "y": 30
    }, {
        "x": "06.22", "y": 60
    }, {
        "x": "06.23", "y": 100
    }, {
        "x": "06.24", "y": 180
    }, {
        "x": "06.25", "y": 190
    }, {
        "x": "06.26", "y": 200
    }]
}, {
    "id": "일자별 가입 유저", "color": "hsl(210,98%,37%)", "data": [{
        "x": "06.19", "y": 5
    }, {
        "x": "06.20", "y": 10
    }, {
        "x": "06.21", "y": 15
    }, {
        "x": "06.22", "y": 30
    }, {
        "x": "06.23", "y": 40
    }, {
        "x": "06.24", "y": 80
    }, {
        "x": "06.25", "y": 10
    }, {
        "x": "06.26", "y": 10
    }]
}]
const data2 = [{
    "country": "06.18",
    "Instagram": 121,
    "Youtube": 196,
    "Facebook": 184,
}, {
    "country": "06.19",
    "Instagram": 163,
    "Youtube": 3,
    "Facebook": 147,
}, {
    "country": "06.20",
    "Instagram": 19,
    "Youtube": 168,
    "Facebook": 4,
}, {
    "country": "06.21",
    "Instagram": 194,
    "Youtube": 149,
    "Facebook": 182,
}, {
    "country": "06.22",
    "Instagram": 143,
    "Youtube": 2,
    "Facebook": 163,
}, {
    "country": "06.23",
    "Instagram": 5,
    "Youtube": 137,
    "Facebook": 188,
}]

function Dashboard() {
    const dashboard1 = (<>
        <Title level={5}>KPI 지표</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
        <Row gutter={24}
             style={{marginBottom: '24px', marginTop: '20px'}}>
            <Col span={8}>
                <Statistic title="전체 유저" value={112893} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="신규 가입" value={4563} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="유저 리텐션" value={4563} formatter={formatter}/>
            </Col>
        </Row>
        <Row gutter={24}
             style={{marginBottom: '24px'}}>
            <Col span={8}>
                <Statistic title="DAU(Daily)" value={504} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="WAU(Weekly)" value={4563} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="MAU(Monthly)" value={4563} formatter={formatter}/>
            </Col>
        </Row>
    </>);

    const dashboard2 = (<>
            <Title level={5}>지역분석</Title>
            <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
            <ItemDashboard/>
        </>)
    ;

    const dashboard3 = (<>
        <Title level={5}>가입유저</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
        <UserLineGraph data={data}/>
    </>);


    const dashboard4 = (<>
        <Title level={5}>마케팅 분석</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
        <MarketingDashboard data={data2}/>
    </>)
    return (<>
        <DashboardLayout children={dashboard1} children2={dashboard2} children3={dashboard3} children4={dashboard4}>
        </DashboardLayout>
    </>);

}

export default Dashboard;
