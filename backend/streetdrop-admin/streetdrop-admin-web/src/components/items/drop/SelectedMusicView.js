import React from "react"
import {Empty, Table, Tag} from 'antd';

function SelectedMusicView({musicInfo}) {
    const columns = [
        {
            title: '앨범 이미지',
            dataIndex: 'albumImage',
            render: (text) => <img src={text} alt="album" style={{width: '100px', height: '100px'}}/>,
        },
        {
            title: '노래 제목',
            dataIndex: 'title',
            render: (text) => <a href={"https://www.youtube.com/results?search_query=" + text} target='_blank'
                                 rel="noreferrer"> {text}</a>
        },
        {
            title: '아티스트',
            dataIndex: 'artist',
        },
        {
            title: "앨범명",
            dataIndex: 'albumName',
        },
        {
            title: '장르',
            dataIndex: 'genre',
            render: (text) => text.map((genre) => <Tag color="blue" key={genre}>{genre}</Tag>)
        }
    ]
    const musicDataList = [musicInfo]
    return (
        <>
            {
                musicDataList.length > 0 ? (
                    <div>
                        <Table dataSource={musicDataList} columns={columns} pagination={{hideOnSinglePage: true}}>
                        </Table>
                    </div>
                ) : (
                    <div>
                        <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} style={{marginTop: '100px'}}/>
                    </div>
                )
            }

        </>
    );

}

export default SelectedMusicView;
