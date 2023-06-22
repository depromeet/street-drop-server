import React, {useState} from 'react'
import {Map, MapMarker} from 'react-kakao-maps-sdk'

const KakaoMap = ({onMapClick, height}) => {
    const [position, setPosition] = useState()

    return (
        <>
            <Map
                center={{
                    lat: 37.5698567,
                    lng: 126.98310,
                }}
                style={{
                    width: "100%",
                    height: "500px",
                }}
                level={3} // 지도의 확대 레벨
                onClick={(_t, mouseEvent) => {
                    setPosition({lat: mouseEvent.latLng.getLat(), lng: mouseEvent.latLng.getLng()})
                    onMapClick && onMapClick({lat: mouseEvent.latLng.getLat(), lng: mouseEvent.latLng.getLng()})
                }}
            >
                {position && <MapMarker position={position}/>}
            </Map>
        </>
    )

}

export default KakaoMap
