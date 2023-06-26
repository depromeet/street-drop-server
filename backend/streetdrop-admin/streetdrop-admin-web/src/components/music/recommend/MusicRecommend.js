import React, {useState} from "react"
import {Button, Modal, Table, Tag} from 'antd';
import MusicRecommendEventCreate from "./MusicRecommendEventCreate";
import BasicLayout from "../../layout/BasicLayout";


function MusicRecommend() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const showModal = () => {
        setIsModalOpen(true);
    };


    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const columns = [

        {
            title: '이벤트 명',
            dataIndex: 'eventName',
        },
        {
            title: '이벤트 문구',
            dataIndex: 'eventText',
        },
        {
            title: '범위',
            dataIndex: 'eventRange',
            redner: (text) => <Tag color="gray">{text}</Tag>
        },
        {
            title: '타입',
            dataIndex: 'eventType',
            redner: (text) => <Tag color="gray">{text}</Tag>
        },
        {
            title: '작성자',
            dataIndex: 'writer',
        },
        {
            title: '이벤트 태그',
            dataIndex: 'eventTag',
            render: (text) => text.map((genre) => <Tag color="blue" key={genre}>{genre}</Tag>)
        },
        {
            title: '상세보기',
            render: () => <Button type="primary" size='small'>상세보기</Button>
        }
    ]

    const songs = [
        {
            key: '1',
            eventName: '날씨 관련 이벤트',
            eventText: '날씨가 청량하고 맑다면?',
            writer: '성훈',
            eventRange: '전체',
            eventType: '기본',
            eventTag: [
                'Aespa',
                '(여자)아이들',
                'Spicy',
                '벚꽃 엔딩',
                '...'
            ]
        },
        {
            key: '2',
            eventName: '한강 이벤트 이벤트',
            eventText: '지금 한강에 있다면?',
            writer: '성훈',
            eventRange: '그룹',
            eventType: '위치',
            eventTag: [
                'Beautiful',
                '한강',
                '봄날',
                '빈지노',
                '...'
            ]
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
                <Table columns={columns} dataSource={songs}/>

                <Modal title="음악 추천 이벤트 등록" open={isModalOpen} onOk={handleOk} onCancel={handleCancel} okText='등록'
                       cancelText='취소'>
                    <MusicRecommendEventCreate/>
                </Modal>
            </BasicLayout>
        </>

    )
}

export default MusicRecommend;