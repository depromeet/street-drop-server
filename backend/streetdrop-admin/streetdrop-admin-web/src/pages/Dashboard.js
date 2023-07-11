import React, { useEffect, useState } from "react";
import { Col, Row, Statistic, Typography } from 'antd';

import axios from "axios";

import UserLineGraph from "../components/dashboard/UserLineGraph";
import DashboardLayout from "../components/layout/DashboardLayout";
import CountUp from 'react-countup';
import ItemDashboard from "../components/dashboard/ItemDashboard";
import MarketingDashboard from "../components/dashboard/MarketingDashboard";

const formatter = (value) => <CountUp end={value} separator=","/>;
const { Title } = Typography;

function Dashboard() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('/admin/users/count');
            const apiData = response.data;
            const formattedData = apiData.map(data => ({
                x: data.date,
                y: data.value
            }));

            setData(formattedData);
        } catch (error) {
            console.error('Error fetching data: ', error);
        }
    };

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
        {data && <UserLineGraph data={data} />}
    </>);


    const dashboard4 = (<>
        <Title level={5}>마케팅 분석</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>

    </>)
    return (<>
        <DashboardLayout children={dashboard1} children2={dashboard2} children3={dashboard3} children4={dashboard4}>
        </DashboardLayout>
    </>);

}

export default Dashboard;
