import React, {useEffect, useState} from "react";
import {List, Tag, Typography} from "antd";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";
import ItemApi from "../../api/domain/item/ItemApi";
import ItemClaimApi from "../../api/domain/item/ItemClaimApi";

const {Title} = Typography;

function ReportListDashboard () {
    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await ItemClaimApi.getItemClaim(0,5)
        setContentList(response.data['data']);
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
                                    color: item.status === "WAITING" ? "black" : "gray",
                                }}
                                title={item.reason}
                                description={item.itemContent}
                            />
                            <Tag color={item.status === "WAITING" ? "blue" : "gray"}>{item.status}</Tag>

                        </List.Item>
                    )}
                />
            </>
        )

}
export default ReportListDashboard;