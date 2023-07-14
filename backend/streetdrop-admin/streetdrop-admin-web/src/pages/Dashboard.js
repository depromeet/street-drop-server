import React, {useEffect, useState} from "react";
import {Typography} from 'antd';

import axios from "axios";

import UserLineGraph from "../components/dashboard/UserLineGraph";
import DashboardLayout from "../components/layout/DashboardLayout";
import ItemDashboard from "../components/dashboard/ItemDashboard";
import KpiDashboard from "../components/dashboard/KpiDashboard";


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
        <p style={{color: 'gray', marginBottom: '15px'}}>갱신시간 : 2023.06.19 오전 3:06:49</p>
        <KpiDashboard/>
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
        {data && <UserLineGraph data={data}/>}
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
