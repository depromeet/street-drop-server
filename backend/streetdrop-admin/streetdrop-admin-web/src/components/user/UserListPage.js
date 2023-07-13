import React, {useEffect, useState} from "react"
import {Table} from 'antd';
import axios from "axios";

import BasicLayout from "../layout/BasicLayout";

function UserListPage() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
        },
        {
            title: '닉네임',
            dataIndex: 'nickname',
        },
        {
            title: 'IDFV',
            dataIndex: 'idfv',
        }
    ]

    const handleTableChange = (
        pagination,
    ) => {
        setTableParams({
            pagination,
        });


        if (pagination.pageSize !== tableParams.pagination?.pageSize) {
            setUsers([]);
        }
    };

    useEffect(() => {
        fetchUser();
    }, [JSON.stringify(tableParams)]);

    const fetchUser = () => {
        setLoading(true);

        axios.get('/admin/users' + '?page=' + (tableParams.pagination.current - 1) + '&size=' + tableParams.pagination.pageSize)
            .then(response => {
                setLoading(false)
                console.log(response.data);
                setUsers(response.data['users']);
                setTableParams({
                    ...tableParams,
                    pagination: {
                        ...tableParams.pagination,
                        total: response.data['meta']['totalElements'],
                    },
                });
            }).catch(error => {
            console.error("Error fetching data:", error);
        });
    }

    if (loading)
        return
    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>사용자 전체 조회</h3>
                <p style={{color: 'gray'}}>전체 사용자를 조회할 수 있습니다.</p>
                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={users}
                    loading={loading}
                    onChange={handleTableChange}
                />
            </BasicLayout>
        </>
    )
}

export default UserListPage;