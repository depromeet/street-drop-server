import React, {useEffect, useState} from "react";
import BasicLayout from "../../layout/BasicLayout";
import {Table} from "antd";
import MemberLoginLogApi from "../../api/domain/member/MemberLoginLogApi";
import {InitialPagination} from "../../constant/PaginationConstant";

function MemberLoginLogPage() {
    const [data, setData] = useState([]);
    const [tableParams, setTableParams] = useState({ pagination: InitialPagination })

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams)]);


    const fetchData = async () => {
        const response = await MemberLoginLogApi.getAllLoginLog(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data.data)
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
            title: '유저 이름',
            dataIndex: 'name',
        }, {
            title: '유저 아이디',
            dataIndex: 'userId'
        },
        {
            title: '유저 Agent',
            dataIndex: 'userAgent',
        },
        {
            title: '로그인 IP',
            dataIndex: 'loginIp',
        }, {
            title: '로그인 일시',
            dataIndex: 'loginAt'
        }, {
            title: '로그인 결과',
            dataIndex: 'loginResult'
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
                <h3 style={{marginBottom: '10px'}}>어드민 유저 관리</h3>
                <p style={{color: 'gray'}}>유저를 조회하고 관리할 수 있습니다.</p>
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

export default MemberLoginLogPage;