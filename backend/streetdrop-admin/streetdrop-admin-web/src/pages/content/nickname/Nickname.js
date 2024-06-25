import BasicLayout from "../../../layout/BasicLayout";
import React, {useEffect, useState} from "react";
import AnnouncementApi from "../../../api/domain/announcement/AnnouncementApi";
import {Table} from "antd";
import NicknameApi from "../../../api/domain/nickname/NicknameApi";


function NicknamePage() {

    const [data, setData] = React.useState([]);
    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await NicknameApi.getNicknames(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data['data']);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }



    const columns = [
        {
            title: "ID",
            dataIndex: "id",
        }, {
            title: '닉네임 앞',
            dataIndex: 'preNickName',
        }, {
            title: '닉네임 뒤',
            dataIndex: 'postNickName',
        }
    ];

    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>닉네임 관리</h3>
                <p style={{color: 'gray'}}>닉네임을 추가하거나 수정할 수 있습니다.</p>
                <Table
                    dataSource={data}
                    columns={columns}
                />

            </BasicLayout>
        </>
    )

}

export default NicknamePage;