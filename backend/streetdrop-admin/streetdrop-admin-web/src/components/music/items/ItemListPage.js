import React, {useEffect, useState} from "react"
import {Drawer, Table} from 'antd';
import axios from "axios";
import BasicLayout from "../../layout/BasicLayout";


function ItemListPage() {
    const [data, setData] = useState([]);
    const [openDrawer, setOpenDrawer] = useState(false);

    const showDrawer = () => {
        setOpenDrawer(true);
    };

    const onClose = () => {
        setOpenDrawer(false);
    };

    const deleteItem = (itemId) => {

        axios.delete(`/admin/items/${itemId}`)
            .then(res => {
                fetchData();
            })
            .catch(
                err => {
                    console.log(err);
                }
            )

    }

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
            title: '노래',
            dataIndex: 'songName',
            render: (text, record) => (
                <>
                    <span>{record.songName}</span><br/>
                    <span style={{color: 'gray'}}>{record.artistName}</span>
                </>

            )
        },
        {
            title: '유저',
            dataIndex: 'userNickname',
        }, {
            title: '드랍 지역',
            dataIndex: 'dropLocationName',
        },
        {
            title: '코멘트',
            dataIndex: 'comment',
        }, {
            title: '등록일',
            dataIndex: 'createdAt',
            render: (text, record) => (
                <span>{new Date(record.createdAt).toLocaleDateString()}</span>
            )
        }, {
            title: '상세보기',
            dataIndex: 'id',
            render: (text, record) => (
                <>
                    <a onClick={showDrawer}>More</a>
                    <br/>
                    <a onClick={() => {deleteItem(record.id)}}>Delete</a>
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
            setData([]);
        }
    };

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams)]);

    const fetchData = () => {

        axios.get('/admin/items' + '?page=' + (tableParams.pagination.current - 1) + '&size=' + tableParams.pagination.pageSize)
            .then(response => {
                setData(response.data['items']);
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


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>아이템 전체 조회</h3>
                <p style={{color: 'gray'}}>아이템을 전체 조회할 수 있습니다.</p>
                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={data}
                    onChange={handleTableChange}
                />
                <Drawer title="아이템 상세조회" placement="right" onClose={onClose} open={openDrawer}>
                </Drawer>
            </BasicLayout>
        </>
    )
}

export default ItemListPage;