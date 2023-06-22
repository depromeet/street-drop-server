import React, {useState} from "react"
import axios from "axios";
import {Table} from "antd";
import {Map, MapMarker} from 'react-kakao-maps-sdk'
import BasicLayout from "../../layout/BasicLayout";


function MusicList() {
    const [poi, setPoi] = useState([]);
    const [items, setItems] = useState([]);
    const [latitude, setLatitude] = useState(37.566826);
    const [longitude, setLongitude] = useState(126.9786567);

    const fetchPoiData = () => {
        setPoi([]);
        axios.get('/api/items/points',
            {
                params: {
                    latitude: latitude,
                    longitude: longitude
                }
            }).then(
            (response) => {
                console.log(response.data['poi']);
                setPoi(response.data['poi']);
            }
        )
            .catch((error) => {
                console.log(error);
            });
        axios.get('/api/items',
            {
                params: {
                    latitude: latitude,
                    longitude: longitude,
                }
            }).then(
            (response) => {
                console.log(response.data);
                setItems(response.data['items']);
            }
        )
            .catch((error) => {
                console.log(error);
            });

    }

    const columns = [
        {
            title: '앨범 이미지',
            dataIndex: 'music.albumImage',
            render: (text, record) => <img src={record.music.albumImage} alt="album"
                                           style={{width: '100px', height: '100px'}}/>,
        },
        {
            title: '노래 제목 - 노래 가수',
            dataIndex: 'music',
            render: (text, record) => <p>{record.music.title} - {record.music.artist}</p>,
        }, {
            title: '유저',
            dataIndex: 'user',
            render: (text, record) => <p>{record.user.nickname}</p>,
        },
        {
            title: '내용',
            dataIndex: 'content',
        },
        {
            title: '장르',
            dataIndex: 'music.genre',
        },

        {
            title: '등록일',
            dataIndex: 'createdAt',
        },

    ]
    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>주변음악 조회</h3>
                <p style={{color: 'gray', marginBottom: '20px'}}> 주변에 있는 음악을 조회하고 관리할 수 있습니다. 지도를 움직이면 주변의 음악들을 조회할 수
                    있습니다.</p>
                <Map
                    center={{
                        lat: 37.566826,
                        lng: 126.9786567
                    }}
                    style={{
                        width: "100%",
                        height: "450px",
                    }}
                    level={3} // 지도의 확대 레벨

                    onDragEnd={(map) => {
                        setLatitude(map.getCenter().getLat());
                        setLongitude(map.getCenter().getLng());
                        fetchPoiData();
                    }}
                >
                    {poi.map((position, index) => (
                        <MapMarker
                            key={`${position.id}`}
                            position={
                                {
                                    lat: position.latitude,
                                    lng: position.longitude
                                }
                            }
                        />
                    ))}
                </Map>
                <Table columns={columns} dataSource={items}
                       style={{marginTop: '20px'}}
                />
            </BasicLayout>
        </>
    )
}

export default MusicList;