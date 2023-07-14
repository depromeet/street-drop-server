import {Col, Row, Statistic} from "antd";
import React, {useEffect, useState} from "react";
import CountUp from "react-countup";
import axios from "axios";

const formatter = (value) => <CountUp end={value} separator=","/>;


function KpiDashboard() {
    const [userKpiData, setUserKpiData] = useState(null);

    useEffect(() => {
        fetchUserKpi();
    }, []);

    const fetchUserKpi = () => {
        axios.get('/admin/users/statical/all/count')
            .then(response => {
                const data = response.data;
                setUserKpiData(data);
            }).catch(error => {
            console.error("Error fetching data:", error);
        });
    }

    const UserKpi = ({allUserCount, todayUserCount, dropUserCount}) => (
        <>
            <Col span={8}>
                <Statistic title="전체 유저" value={allUserCount} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="신규 가입" value={todayUserCount} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="가입후 드랍한 유저" value={dropUserCount} formatter={formatter}/>
            </Col>
        </>
    );

    const UserRetention = () => (
        <>
            <Col span={8}>
                <Statistic title="DAU(Daily)" value={0} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="WAU(Weekly)" value={0} formatter={formatter}/>
            </Col>
            <Col span={8}>
                <Statistic title="MAU(Monthly)" value={0} formatter={formatter}/>
            </Col>
        </>
    )

    return (
        <>
            <Row gutter={24}
                 style={{marginBottom: '24px', marginTop: '20px'}}>
                {
                    userKpiData === null ?
                        <UserKpi
                            allUserCount={0}
                            todayUserCount={0}
                            dropUserCount={0}
                        />
                        :
                        <UserKpi
                            allUserCount={userKpiData.allUserCount}
                            todayUserCount={userKpiData.todayUserCount}
                            dropUserCount={userKpiData.dropUserCount}
                        />
                }
            </Row>
            <Row gutter={24}
                 style={{marginBottom: '24px'}}>
                <UserRetention/>
            </Row>
        </>
    )
}

export default KpiDashboard;