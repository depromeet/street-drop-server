import React, {useEffect, useState} from "react";
import UserBlockApi from "../../api/domain/user/UserBlockApi";
import BasicLayout from "../../layout/BasicLayout";
import {Table} from "antd";

function ItemReportPage() {
    const [data, setData] = useState([]);

    const exampleData  ={

    }

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
            title: '아이템 정보',
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
                <h3 style={{marginBottom: '10px'}}>아이템 신고 관리</h3>
                <p style={{color: 'gray'}}>아이템의 신고내역과 신고 처리를 관리할 수 있습니다.</p>
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

export default ItemReportPage;