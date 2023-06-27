import React, { useState, useEffect } from "react"
import { Button, Space, Table, message } from 'antd';
import { DownloadOutlined } from '@ant-design/icons';
import axios from "axios";

import BasicLayout from "../layout/BasicLayout";

function UserListPage() {
    const [ messageApi, contextHolder ] = message.useMessage();
    const [ users, setUsers ] = useState([]);
    const [ loading, setLoading ] = useState(false);

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

    useEffect(() => {
        setUsers([]);
        setLoading(true);
        fetchUser();
    }, []);

    const fetchUser = () => {
        axios.get('/admin/users')
            .then(response => {
                messageApi.open({
                    type: 'success',
                    content: "유저 전체 조회에 성공했습니다"
                });
                const data = response.data.users;
                const fetchData = data.map((user, index) => {
                    const key = index + 1;
                    return {...user, key};
                });
                setUsers(fetchData);
            }).catch(error => {
                console.error("Error fetching data:", error);
                messageApi.open({
                    type: 'error',
                    content:"사용자 검색에 실패했습니다. 다시 시도해주세요.",
                });
        });
        setLoading(false);
    }

    if (loading)
        return
            <>
                <BasicLayout>
                    <h3 style={{marginBottom: '10px'}}>사용자 전체 조회</h3>
                    <p style={{color: 'gray'}}>전체 사용자를 조회할 수 있습니다.</p>
                    <div> 로딩중 ...</div>
                </BasicLayout>
            </>
    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>사용자 전체 조회</h3>
                <p style={{color: 'gray'}}>전체 사용자를 조회할 수 있습니다.</p>
                <div>
                    <Space wrap style={{marginTop: '20px', marginBottom: '10px', float: 'right'}}>
                        <Button size='default'>최신순</Button>
                        <Button size='default'>오래된 순</Button>
                        <Button
                            icon={<DownloadOutlined/>}
                            size='default'>
                            Download
                        </Button>
                    </Space>
                </div>
                <Table
                    columns={columns}
                    dataSource={users}
                />
            </BasicLayout>
        </>
    )
}

export default UserListPage;