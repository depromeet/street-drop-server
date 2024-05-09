import React, {useEffect, useState} from "react";
import {Button, Input, Modal, Table} from "antd";
import BasicLayout from "../../layout/BasicLayout";
import BannedWordApi from "../../api/domain/item/BannedWordApi";

function BannedWordPage() {
    const [data, setData] = useState([]);
    const [inputValue, setInputValue] = useState('');
    const [isModalOpen, setIsModalOpen] = useState(false);

    const showModal = () => {
        setIsModalOpen(true);
    };

    const createBannedWord = async () => {
        if (inputValue !== '') {
            BannedWordApi.createBannedWord(inputValue);
            await fetchData();
        }
        setIsModalOpen(false);
    }


    const deleteBannedWord = async (id) => {
        await BannedWordApi.deleteBannedWord(id);
        Modal.success({
            content: '삭제되었습니다.',
        });
        await fetchData();
    }

    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams), isModalOpen, inputValue]);


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
        },
        {
            title: '삭제',
            dataIndex: 'id',
            render: (id) => (
                <Button onClick={() => deleteBannedWord(id)}>
                    삭제
                </Button>
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
                <h3 style={{marginBottom: '10px'}}>금칙어 관리</h3>
                <p style={{color: 'gray'}}>아이템 콘텐츠의 금칙어를 관리할 수 있습니다.</p>
                <Button onClick={showModal} type="primary">금칙어 추가</Button>
                <Modal
                    title="금칙어 추가"
                    open={isModalOpen}
                    onOk={() => createBannedWord()}
                    onCancel={() => setIsModalOpen(false)}
                >
                    <Input
                        placeholder="금칙어를 입력하세요"
                        value={inputValue}
                        onChange={(e) => setInputValue(e.target.value)}
                    />
                </Modal>
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