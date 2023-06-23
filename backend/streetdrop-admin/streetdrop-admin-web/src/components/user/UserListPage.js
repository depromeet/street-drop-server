import React, {useState} from "react"
import {Button, Space, Table} from 'antd';
import {DownloadOutlined} from '@ant-design/icons';
import BasicLayout from "../layout/BasicLayout";

function UserListPage() {
    const columns = [
        {
            title: 'ID',
            dataIndex: 'userId',
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

    const users = [
        {
            key: '1',
            userId: '1',
            nickname: '친절한 모찌씨',
            idfv: '12345'
        },
        {
            key: '2',
            userId: '2',
            nickname: '도라에몽',
            idfv: '12345678'
        },
    ]
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