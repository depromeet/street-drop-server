import BasicLayout from "../../layout/BasicLayout";
import React, {useState} from "react";
import {Table} from "antd";
import {InitialPagination} from "../../constant/PaginationConstant";

function NotificationListPage() {


    const data = [
        {
            id : 1,
            title : '푸시 발송 테스트',
            content : '푸시 발송 테스트입니다.',
            isViewed : '확인'
        }
    ]

    const [tableParams, setTableParams] = useState({pagination: InitialPagination});

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
        },
        {
            title: '제목',
            dataIndex: 'title',
        },
        {
            title: '내용',
            dataIndex: 'content',
        }, {
            title: '확인 여부',
            dataIndex: 'isViewed',
        }
    ]

    return (
        <>
            <BasicLayout>
                <h1>푸시 발송 조회</h1>
                <p style={{color: 'gray'}}>푸시 발송 현황을 조회할 수 있습니다.</p>
                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={data}
                />
            </BasicLayout>
        </>
    )
}

export default NotificationListPage