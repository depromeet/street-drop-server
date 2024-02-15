import React, {useEffect, useState} from "react"
import {Button, Form, Modal, Table, Tag} from 'antd';
import MusicRecommendEventCreate from "./MusicRecommendEventCreate";
import BasicLayout from "../../../../layout/BasicLayout";
import RecommendApi from "../../../../api/domain/music/RecommendApi";


function MusicRecommend() {
    const [data, setData] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });
    const [form, setForm] = Form.useForm();

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams)]);

    const fetchData = async () => {
        const response = await RecommendApi.getMusicKeywordRecommend(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data['data']);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }

    const descriptionRender = (text) => {
        return text.map((item, index) => {
            const textColor = item.color === 'RecommendTitleHighLight' ? '#1677ff' : 'black';
            const textBold = item.color === 'RecommendTitleHighLight' ? 'bold' : 'normal';
            return (
                <span key={index} style={{color: textColor, fontWeight: textBold,}}>
        {item.text}
      </span>
            );
        });
    }

    const termsRender = (text) => {
        return text.map((item, index) => {
            const textColor = item.color === 'RecommendKeywordHighLight' ? 'blue' : 'default';
            return (
                <Tag key={index} bordered={false} color={textColor}>{item.text}</Tag>
            );
        });
    }

    const showModal = () => {
        setIsModalOpen(true);
    };

    const createMusicRecommend = async () => {
        const formValue = form.getFieldsValue();
        const title = formValue.title;
        const description = formValue.description;
        const terms = formValue.terms;
        if (title && description && terms) {
            await RecommendApi.createMusicKeywordRecommend(title, description, terms);
            await fetchData();
        }
    }

    const handleOk = () => {
        createMusicRecommend()
        setIsModalOpen(false);
    };


    const handleCancel = () => {
        setIsModalOpen(false);
    };

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
            title: '노출되는 설명',
            dataIndex: 'description',
            render: (text) => descriptionRender(text)
        },
        {
            title: '추천 검색어',
            dataIndex: 'terms',
            render: (text) => termsRender(text)
        },
        {
            title: '활성화',
            dataIndex: 'active',
            render: (text) => {
                if (text === true) {
                    return <Tag color="green">활성화</Tag>
                } else {
                    return <Tag color="red">비활성화</Tag>
                }

            }
        }
    ]


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>음악 추천 이벤트</h3>
                <p style={{color: 'gray'}}>검색창에서의 음악 추천 이벤트를 등록하거나 관리할 수 있습니다.</p>
                <div>
                    <Button type="primary" size='default'
                            style={{marginTop: '20px', marginBottom: '10px', float: 'right'}}
                            onClick={showModal}>생성하기</Button>
                </div>
                <Table columns={columns} dataSource={data}/>

                <Modal title="음악 추천 이벤트 등록"
                       open={isModalOpen}
                       onOk={handleOk}
                       onCancel={handleCancel}
                       okText='등록'
                       cancelText='취소'
                       bodyStyle={{overflowY: 'auto', maxHeight: 'calc(70vh)'}}
                >
                    <MusicRecommendEventCreate form={form}/>
                </Modal>
            </BasicLayout>
        </>

    )
}

export default MusicRecommend;