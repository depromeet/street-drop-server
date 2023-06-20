import React from "react"
import {Descriptions, Row, Tag} from 'antd';

function SelectedMusicDetail(musicInfo) {

    musicInfo = musicInfo.musicInfo
    return (
        <>
            <Row gutter={[16, 16]}>
                <img
                    src={musicInfo.albumImage}
                    alt="album"
                    style={{width: "100%", maxWidth: "150px", height: "auto"}}
                />
                <Descriptions title="선택한 음악 정보">
                    <Descriptions.Item label="노래 제목">{musicInfo.title}</Descriptions.Item>
                    <Descriptions.Item label="아티스트">{musicInfo.artist}</Descriptions.Item>
                    <Descriptions.Item label="앨범 이름">{musicInfo.albumName}</Descriptions.Item>
                    <Descriptions.Item label="장르">
                        {musicInfo.genre.map((genre) => (
                            <Tag color="blue" key={genre}>
                                {genre}
                            </Tag>
                        ))}
                    </Descriptions.Item>
                </Descriptions>

            </Row>
        </>
    );

}

export default SelectedMusicDetail;
