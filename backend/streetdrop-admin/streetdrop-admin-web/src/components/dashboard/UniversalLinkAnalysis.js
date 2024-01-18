import {List, Typography} from "antd";
import React, {useEffect, useState} from "react";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";

const {Title} = Typography;

function AppStoreIndicatorAnalysis () {

    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await DashboardApi.getAppStoreIndicators()
        setContentList(response.data);
    }


        return (
            <>
                <Title level={5} style={{marginLeft: 12}}>지표정보</Title>
                <List
                    style={{padding: 12}}
                    itemLayout="horizontal"
                    dataSource={contentList}
                    renderItem={(item) => (
                        <List.Item>
                            <List.Item.Meta
                                title={item.title}
                                description={item.value}
                            />
                        </List.Item>
                    )}
                />
            </>
        )



}

export default AppStoreIndicatorAnalysis;