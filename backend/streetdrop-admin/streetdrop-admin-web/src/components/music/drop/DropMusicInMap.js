import React, {useState} from "react";
import KakaoMap from "../../map/KakaoMap";
import {Button, Col, Form, Input, Radio, Row} from 'antd';
import {useLocation, useNavigate} from "react-router-dom";
import SelectedMusicDetail from "./SelectMusic";
import axios from "axios";
import MapLayout from "../../layout/MapLayout";


function DropMusicInMap() {
    const navigate = useNavigate();
    const location = useLocation();
    const songInfo = {...location.state};
    const songDetail = songInfo.selectedSong;
    const musicInfo = {
        title: songDetail.songName,
        artist: songDetail.artistName,
        albumName: songDetail.albumName,
        albumImage: songDetail.albumImage,
        genre: songDetail.genre
    }
    const [size, setSize] = useState('');


    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');
    const [form] = Form.useForm();
    const formRef = React.useRef();
    const [isModalOpen, setIsModalOpen] = useState(false);

    function truncateDecimal(num) {
        const decimalPlaces = 7;
        const multiplier = Math.pow(10, decimalPlaces);
        return Math.floor(num * multiplier) / multiplier;
    }

    const showModal = () => {
        setIsModalOpen(true);
    };

    const handleOk = () => {
        setIsModalOpen(false);
        formRef.current?.setFieldsValue({latitude: truncateDecimal(latitude), longitude: truncateDecimal(longitude)});
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const handleLatitudeChange = (e) => {
        setLatitude(e.target.value);
    };

    const handleLongitudeChange = (e) => {
        setLongitude(e.target.value);

    };


    const handleMapClick = (mouseEvent) => {
        setLatitude(mouseEvent.lat);
        setLongitude(mouseEvent.lng);
        formRef.current?.setFieldsValue({
            latitude: truncateDecimal(mouseEvent.lat),
            longitude: truncateDecimal(mouseEvent.lng)
        });
    };

    const clickDropMusic = () => {
        const data = JSON.stringify({
            "location": {
                "latitude": latitude,
                "longitude": longitude,
                "address": "서울시 강남구 강남동"
            },
            "music": {
                "title": musicInfo.title,
                "artist": musicInfo.artist,
                "albumName": musicInfo.albumName,
                "albumImage": musicInfo.albumImage,
                "genre": musicInfo.genre
            },
            "content": form.getFieldValue('comment')
        });
        postData(data);

    }

    const postData = (data) => {

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: '/api/items',
            headers: {
                'x-sdp-idfv': 'admin',
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then(
                (response) => {
                    console.log(response);
                    navigate('/drop-music/result/success');
                }
            )
            .catch(
                (error) => {
                    console.log(error);
                    navigate('/drop-music/result/fail', {state: {error}});
                }
            )
    };

    return (
        <>

            <MapLayout>
                <KakaoMap onMapClick={handleMapClick}/>
                <div style={{margin: '30px 30px 30px 30px'}}>
                </div>
                <h1>음악 드랍하기</h1>
                <Radio.Group value={size}>
                    <Radio.Button value="large">Large</Radio.Button>
                    <Radio.Button value="middle">Default</Radio.Button>
                    <Radio.Button value="small">Small</Radio.Button>
                </Radio.Group>
                <Row>
                    <Col span={12}>
                        <SelectedMusicDetail musicInfo={musicInfo}/>
                    </Col>
                    <Col span={12}>
                        <Form ref={formRef} form={form} style={{marginTop: '30px'}}>
                            <Form.Item style={{marginBottom: '10px'}}>
                                <Form.Item
                                    label="위도"
                                    name="latitude"
                                    rules={[{required: true}]}
                                    style={{maxWidth: 'calc(50% - 8px)', display: 'inline-block', margin: '0 8px'}}
                                >
                                    <Input placeholder="Latitude" value={latitude} onChange={handleLatitudeChange}/>
                                </Form.Item>
                                <Form.Item
                                    label="경도"
                                    name="longitude"
                                    rules={[{required: true}]}
                                    style={{maxWidth: 'calc(50% - 8px)', display: 'inline-block', margin: '0 8px'}}
                                >
                                    <Input placeholder="Longitude" value={longitude} onChange={handleLongitudeChange}/>
                                </Form.Item>
                            </Form.Item>
                            <Form.Item label="코멘트" name="comment" style={{}}>
                                <Input placeholder="Comment"/>
                            </Form.Item>
                            <Button type="primary" size='default' onClick={clickDropMusic}
                                    style={{float: 'right'}}>드랍하기</Button>
                        </Form>
                    </Col>
                </Row>

            </MapLayout>
        </>
    );
}

export default DropMusicInMap;
