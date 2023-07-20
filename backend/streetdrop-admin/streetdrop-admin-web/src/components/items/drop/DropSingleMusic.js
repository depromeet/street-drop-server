import React, {useState} from "react";
import DropKakaoMap from "./DropKakaoMap";
import {Button, Form, Input} from 'antd';
import {useLocation, useNavigate} from "react-router-dom";
import SelectedMusicView from "./SelectedMusicView";
import axios from "axios";
import MapLayout from "../../../layout/MapLayout";


function DropSingleMusic() {
    const navigate = useNavigate();
    const location = useLocation();
    const songInfo = {...location.state};
    const songDetail = songInfo.selectedSong;

    console.log(songInfo);
    const musicInfo = {
        title: songDetail.songName,
        artist: songDetail.artistName,
        albumName: songDetail.albumName,
        albumImage: songDetail.albumImage,
        genre: songDetail.genre
    }

    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');
    const [form] = Form.useForm();
    const formRef = React.useRef();

    function truncateDecimal(num) {
        const decimalPlaces = 7;
        const multiplier = Math.pow(10, decimalPlaces);
        return Math.floor(num * multiplier) / multiplier;
    }

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
                    navigate('/drop-music/result/success');
                }
            )
            .catch(
                (error) => {
                    navigate('/drop-music/result/fail', {state: {error}});
                }
            )
    };


    return (
        <>
            <MapLayout>
                <DropKakaoMap onMapClick={handleMapClick}/>
                <div style={{margin: '10px 10px 10px 10px'}}>
                </div>
                <div style={{padding: 48}}>
                    <h2>음악 드랍</h2>
                    <SelectedMusicView musicInfo={musicInfo}/>
                    <Form ref={formRef} form={form} style={{marginTop: '30px'}}>
                        <Form.Item style={{marginBottom: '10px'}}>
                            <Form.Item label="위도" name="latitude" rules={[{required: true}]}>
                                <Input placeholder="Latitude" value={latitude}
                                       onChange={handleLatitudeChange}/>
                            </Form.Item>
                            <Form.Item label="경도" name="longitude" rules={[{required: true}]}>
                                <Input placeholder="Longitude" value={longitude}
                                       onChange={handleLongitudeChange}/>
                            </Form.Item>
                        </Form.Item>
                        <Form.Item label="코멘트" name="comment" style={{}} rules={[{required: true}]}>
                            <Input placeholder="Comment"/>
                        </Form.Item>
                        <Button type="primary" size='default' onClick={clickDropMusic}
                                style={{float: 'right'}}>드랍하기</Button>
                    </Form>
                </div>

            </MapLayout>
        </>
    );
}

export default DropSingleMusic;
