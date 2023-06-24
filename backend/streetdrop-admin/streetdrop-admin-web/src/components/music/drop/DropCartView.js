import {Empty, Table} from "antd";
import React from "react";


function DropCartView({songs}) {
    const songsColumn = [
        {
            title: '앨범',
            dataIndex: 'albumThumbnailImage',
            render: (text) => <img src={text} alt="album" style={{width: '60px', height: '60px'}}/>,
        },
        {
            title: '노래 제목',
            dataIndex: 'songName',
        },
        {
            title: '아티스트',
            dataIndex: 'artistName',
        }
    ]
    return (
        <>
            {
                songs.length > 0 ? (
                    <div>
                        <Table dataSource={songs}
                               columns={songsColumn}
                               pagination={{hideOnSinglePage: true}}/>;
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

export default DropCartView;