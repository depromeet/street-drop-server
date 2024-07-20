import React, {useEffect, useState} from "react"
import {Button, Col, Drawer, Modal, Row, Table} from 'antd';
import BasicLayout from "../../layout/BasicLayout";
import ItemApi from "../../api/domain/item/ItemApi";
import ItemDetailPage from "../../components/items/ItemDetailPage";
import {InitialPagination} from "../../constant/PaginationConstant";
import Search from "antd/es/input/Search";


function ItemListPage() {
    const [data, setData] = useState([]);
    const [openDrawer, setOpenDrawer] = useState(false);
    const [clickedItemId, setClickedItemId] = useState(1);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);

    const showDeleteModal = () => {
        setIsDeleteModalOpen(true);
    };

    const handleDeleteOk = async () => {
        await ItemApi.deleteItem(clickedItemId);
        setIsDeleteModalOpen(false);
        setOpenDrawer(false);
        await fetchData()
    };


    const onSearch = async (value) => {
        const response = await ItemApi.getItemsByKeyword(tableParams.pagination.current - 1, tableParams.pagination.pageSize, value);
        setData(response.data['data']);
    }


    const showDrawer = (itemId) => {
        setClickedItemId(itemId)
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
                    <a onClick={() => {
                        showDrawer(record.id)
                    }}>More</a>
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

    const fetchData = async () => {
        const response = await ItemApi.getItems(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data['data']);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }

    const DeleteModal = () => {
        return (
            <Modal title="아이템을 삭제하시겠습니까?"
                   open={isDeleteModalOpen}
                   onOk={handleDeleteOk}
                   onCancel={() => {
                       setIsDeleteModalOpen(false)
                   }}>
                <p>아이템을 삭제합니다. 복구할 수 없습니다.</p>
            </Modal>
        )
    }


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>아이템 전체 조회</h3>
                <p style={{color: 'gray'}}>아이템을 전체 조회할 수 있습니다.</p>


                <Row>
                    <Col span={14}>
                    </Col>

                    <Col span={10}>
                        <Search placeholder="검색어를 입력하세요" onSearch={onSearch} enterButton/>
                    </Col>

                </Row>

                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={data}
                    onChange={handleTableChange}
                />
                <Drawer title="아이템 상세조회"
                        placement="right"
                        onClose={onClose}
                        open={openDrawer}
                        extra={
                            <Button onClick={showDeleteModal}>삭제하기</Button>
                        }
                >
                    <ItemDetailPage itemId={clickedItemId}/>
                </Drawer>
                <DeleteModal/>
            </BasicLayout>
        </>
    )
}

export default ItemListPage;