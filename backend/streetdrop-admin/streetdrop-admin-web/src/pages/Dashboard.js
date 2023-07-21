import React, {useEffect, useState} from "react";
import {Typography} from 'antd';

import axios from "axios";

import UserLineGraph from "../components/dashboard/UserLineGraph";
import DashboardLayout from "../layout/DashboardLayout";
import ItemDashboard from "../components/dashboard/ItemDashboard";
import KpiDashboard from "../components/dashboard/KpiDashboard";
import PushDashboard from "../components/dashboard/PushDashboard";


const {Title} = Typography;

function Dashboard() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = () => {
        axios.get('/admin/users/statical/signup/count')
            .then(response => {
                const formattedData = response.data.map(data => ({
                    x: data.date,
                    y: data.count
                }));
                console.log(formattedData);
                setData(formattedData);
            })
            .catch(error => {
                console.error('fail when fetching data', error);
            });
    };


    const dashboard1 = (<>
        <Title level={5}>KPI 지표</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>서비스와 관련된 KPI 지표를 확인해볼 수 있습니다.</p>
        <KpiDashboard/>
    </>);

    const dashboard2 = (<>
            <Title level={5}>지역분석</Title>
            <p style={{color: 'gray', marginBottom: '15px'}}>최근(3일이내) 가장 많이 드랍된 지역입니다.</p>
            <ItemDashboard/>
        </>)
    ;

    const dashboard3 = (<>
        <Title level={5}>가입유저</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>일별 가입유저 그래프입니다.</p>
        {data && <UserLineGraph data={data}/>}
    </>);


    const dashboard4 = (<>
        <Title level={5}>푸시 분석</Title>
        <p style={{color: 'gray', marginBottom: '15px'}}>푸시 발송내역 및 푸시 기록입니다.</p>
        <PushDashboard/>
    </>)

    return (<>
        <DashboardLayout children={dashboard1} children2={dashboard2} children3={dashboard3} children4={dashboard4}>
        </DashboardLayout>
    </>);

}

export default Dashboard;
