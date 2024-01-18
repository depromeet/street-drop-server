import React, {useEffect, useState} from "react";
import {List, Tag, Typography} from "antd";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";

const {Title} = Typography;

function ReportListDashboard () {
    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await DashboardApi.getRecentReports()
        setContentList(response.data);
    }


        return (
            <>
                <Title level={5} style={{marginLeft: 12}}>신고관리</Title>
                <List
                    style={{padding: 12}}
                    itemLayout="horizontal"
                    dataSource={contentList}
                    renderItem={(item, index) => (
                        <List.Item>
                            <List.Item.Meta
                                style={{
                                    color: item.status === "Open" ? "black" : "gray",
                                }}
                                title={item.title}
                                description={item.content}
                            />
                            <Tag color={item.status === "Open" ? "blue" : "gray"}>{item.status}</Tag>

                        </List.Item>
                    )}
                />
            </>
        )

}
export default ReportListDashboard;