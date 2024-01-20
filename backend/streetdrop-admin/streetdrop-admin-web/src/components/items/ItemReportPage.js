import React, {useEffect, useState} from "react";
import BasicLayout from "../../layout/BasicLayout";
import {Button, Modal, Select, Table} from "antd";
import ItemClaimApi from "../../api/domain/item/ItemClaimApi";

function ItemReportPage() {
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
        const response = await ItemClaimApi.getItemClaim(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data['data']);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }

    const handleClaimStatusChange = (itemId, status) => {
        ItemClaimApi.updateItemStatus(itemId, status);
        if (status === 'ACCEPTED') {
            Modal.success({
                content: '신고가 처리되었습니다.',
            });
        } else if (status === 'REJECTED') {
            Modal.success({
                content: '신고가 거절되었습니다.',
            });
        }
    }

    const columns = [
        {
            title: '신고 ID',
            dataIndex: 'itemClaimId',
            key: 'itemClaimId',
        },
        {
            title: '아이템 ID',
            dataIndex: 'itemId',
        },
        {
            title: '신고 사유',
            dataIndex: 'reason',
        },
        {
            title: '아이템 내용',
            dataIndex: 'itemContent',
        },
        {
            title: '신고 일자',
            dataIndex: 'reportAt',
        },
        {
            title: '처리 상태',
            dataIndex: 'status',
            render: (status, record) => (
                <>
                    <Select
                        defaultValue={status}
                        disabled={status !== 'WAITING'}
                        style={{ width: 120 }}
                        onChange={(selectedStatus) => handleClaimStatusChange(record.itemClaimId, selectedStatus)}
                        options={[
                            { value: 'WAITING', label: '대기중' },
                            { value: 'ACCEPTED', label: '처리완료' },
                            { value: 'REJECTED', label: '신고거절' },
                        ]}
                    />
                </>
            ),
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