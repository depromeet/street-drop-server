import React, {useEffect, useState} from "react"
import {Drawer, Table} from 'antd';

import BasicLayout from "../../layout/BasicLayout";
import UserDetailPage from "../location/user/UserDetailPage";
import UserApi from "../../api/domain/user/UserApi";
import {InitialPagination} from "../../constant/PaginationConstant";

function UserListPage() {
    const [users, setUsers] = useState([]);
    const [openDrawer, setOpenDrawer] = useState(false);
    const [clickedUserId, setClickedUserId] = useState(1);
    const showDrawer = (id) => {
        setClickedUserId(id);
        setOpenDrawer(true);
    };

    const onClose = () => {
        setOpenDrawer(false);
    };

    const [tableParams, setTableParams] = useState({pagination: InitialPagination});

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
        },
        {
            title: '가입일',
            dataIndex: 'createdAt',
            render: (text, record) => (
                <span>{new Date(record.createdAt).toLocaleString()}</span>
            )
        }, {
            title: '상세보기',
            dataIndex: 'id',
            render: (text, record) => (
                <>
                    <a onClick={() => {showDrawer(record.id)}}>More</a>
                    <br/>
                </>
            )
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

    const fetchUser = async () => {
        const response = await UserApi.getAllUser(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setUsers(response.data.data);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }


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
                    onChange={handleTableChange}
                />
                <Drawer title="유저 상세조회" placement="right" onClose={onClose} open={openDrawer}>
                    <UserDetailPage userId={clickedUserId}/>
                </Drawer>
            </BasicLayout>
        </>
    )
}

export default UserListPage;