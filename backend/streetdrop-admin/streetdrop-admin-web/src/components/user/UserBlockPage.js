import React, {useEffect, useState} from "react"
import BasicLayout from "../../layout/BasicLayout";
import {Table} from "antd";
import UserBlockApi from "../../api/domain/user/UserBlockApi";

function UserBlockPage() {
    const [data, setData] = useState([]);

    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams)]);


    const fetchData = async () => {
        const response = await UserBlockApi.getAllBlockList(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
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
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: '차단 요청 유저 ID',
            dataIndex: 'userId',
        },
        {
            title: '차단 요청 유저 닉네임',
            dataIndex: 'userNickname',
        },
        {
            title: '차단 대상 유저 ID',
            dataIndex: 'blockUserId',
        },
        {
            title: '차단 대상 유저 닉네임',
            dataIndex: 'blockUserNickname',
        },
        {
            title: '차단 일자',
            dataIndex: 'createdAt',
        }
    ];

    const handleTableChange = (
        pagination,
    ) => {
        setTableParams({
            pagination,
        });


        if (pagination.pageSize !== tableParams.pagination?.pageSize) {
            setData([]);
        }
    };


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>유저 차단 내역</h3>
                <p style={{color: 'gray'}}>유저의 차단 내역을 확인할 수 있습니다.</p>
                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={data}
                    onChange={handleTableChange}
                />
            </BasicLayout>
        </>
    )
}

export default UserBlockPage;