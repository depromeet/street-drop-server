import React, {useEffect, useState} from "react";
import {Table} from "antd";
import BasicLayout from "../../layout/BasicLayout";
import BannedWordApi from "../../api/domain/item/BannedWordApi";

function BannedWordPage() {
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
        const response = await BannedWordApi.getBannedWords(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
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
            title: '금칙어',
            dataIndex: 'word',
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
                <h3 style={{marginBottom: '10px'}}>금칙어 관리</h3>
                <p style={{color: 'gray'}}>아이템 콘텐츠의 금칙어를 관리할 수 있습니다.</p>
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

export default BannedWordPage;