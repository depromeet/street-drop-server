import {Col, Progress, Row, Typography} from "antd";
import React, {useEffect} from "react";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";

const {Title} = Typography;

function VersionAnalysisDashboard() {

    const [ contentList, setContentList ] = React.useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await DashboardApi.getVersions()
        setContentList(response.data);
    }


    const data = [
        {
            version: '1.0.8',
            count: 124,
            percent: 60,
        },
        {
            version: '1.0.7',
            count: 30,
            percent: 45,
        },
        {
            version: '1.0.6',
            count: 42,
            percent: 20,
        },
        {
            version: '1.0.5',
            count: 8,
            percent: 10,
        },
        {
            version: '1.0.4',
            count: 2,
            percent: 2,
        },
    ];

    const renderContent = (version, count, percentage) => {
        return (
            <>
                <Row style={{color: '#8c8c8c'}}>
                    <Col span={8} style={{textAlign: 'left'}}>{version}</Col>
                    <Col span={8} style={{textAlign: 'right'}}>{count}</Col>
                    <Col span={8} style={{textAlign: 'right'}}>{percentage}%</Col>
                </Row>
                <Progress percent={percentage} size="small" showInfo={false}/>
            </>
        );

    };


    const renderTitle = () => {
        return (
            <Row style={{
                marginBottom: 12,
                fontWeight: 'bold',
            }}>
                <Col span={8} style={{textAlign: 'left'}}>버전</Col>
                <Col span={8} style={{textAlign: 'right'}}></Col>
                <Col span={8} style={{textAlign: 'right'}}>비율</Col>
            </Row>
        );
    };

    return (
        <>
            <Title level={5} style={{marginLeft: 12}}>버전분석</Title>
            <div style={{
                padding: 12,
            }}>
                {renderTitle()}
                {contentList.map((item, index) => (
                    <Row key={index} style={{marginBottom: 12}}>
                        <Col span={24}>
                            {renderContent(item.version, item.count, item.percentage)}
                        </Col>
                    </Row>
                ))}
            </div>
        </>
    );


}

export default VersionAnalysisDashboard;