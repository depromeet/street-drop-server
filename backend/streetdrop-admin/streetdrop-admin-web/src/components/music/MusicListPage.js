import React, {useState} from "react"
import {Button, Space, Table} from 'antd';
import {DownloadOutlined} from '@ant-design/icons';
import BasicLayout from "../layout/BasicLayout";


function MusicListPage() {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const columns = [
        {
            title: 'ID',
            dataIndex: 'itemId',
        },
        {
            title: '곡',
            dataIndex: 'song',
        },
        {
            title: '아티스트',
            dataIndex: 'artist',
        },
        {
            title: '드랍 지역',
            dataIndex: 'dropArea',
        },
        {
            title: '코멘트',
            dataIndex: 'dropContent',
        },
        {
            title: '유저',
            dataIndex: 'user',
        },
        {
            title: '드랍일시',
            dataIndex: 'dropDate',
        },
        {
            title: '상세보기',
            render: () => <Button size='small'>상세보기</Button>
        }
    ]

    const songs = [
        {
            key: '1',
            itemId: '1',
            song: 'Next Level',
            dropArea: '광진구 화양동',
            dropContent: '너무 더운데',
            artist: 'aespa',
            dropDate: '2021-04-01',
            user: '핑크 햄버거',
        }, {
            key: '2',
            itemId: '2',
            song: 'butter',
            dropArea: '강남구 강남동',
            dropContent: '집가고 싶어',
            artist: '방탄소년단',
            dropDate: '2021-04-01',
            user: '도라에몽',
        }
    ]

    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>드랍 음악 전체 조회</h3>
                <p style={{color: 'gray'}}>드랍된 전체 음악을 조회할 수 있습니다.</p>
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
                <Table columns={columns} dataSource={songs}/>

            </BasicLayout>
        </>

    )
}

export default MusicListPage;