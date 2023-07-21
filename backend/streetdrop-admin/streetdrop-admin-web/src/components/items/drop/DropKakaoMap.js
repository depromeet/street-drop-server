import React, {useState} from 'react'
import {Map, MapMarker} from 'react-kakao-maps-sdk'

const DropKakaoMap = ({onMapClick, height}) => {
    const [position, setPosition] = useState()
    const [poi, setPoi] = useState([]);
    const [latitude, setLatitude] = useState(37.566826);
    const [longitude, setLongitude] = useState(126.9786567);


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

                level={3}

                onDragEnd={(map) => {
                    setLatitude(map.getCenter().getLat());
                    setLongitude(map.getCenter().getLng());
                }}

                onClick={(_t, mouseEvent) => {
                    setPosition({lat: mouseEvent.latLng.getLat(), lng: mouseEvent.latLng.getLng()})
                    onMapClick && onMapClick({lat: mouseEvent.latLng.getLat(), lng: mouseEvent.latLng.getLng()})
                }}
            >
                {position
                    &&
                    poi.map((poiInfo, index) => (
                        <MapMarker
                            key={`${poiInfo.id}`}
                            position={
                                {
                                    lat: poiInfo.latitude,
                                    lng: poiInfo.longitude
                                }
                            }
                            image={{
                                src: "/image/poi.png",
                                size: {
                                    width: 60,
                                    height: 60
                                }
                            }}
                        />
                    ))
                    &&
                    <MapMarker position={position}/>
                }
            </Map>
        </>
    )

}

export default DropKakaoMap
