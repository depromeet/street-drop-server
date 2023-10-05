import React from "react";
import {Divider, Image} from "antd";
import ItemApi from "../../api/domain/item/ItemApi";


function ItemDetailPage({itemId}) {
    const [basicInfo, setBasicInfo] = React.useState({});
    const [userInfo, setUserInfo] = React.useState({});
    const [musicInfo, setMusicInfo] = React.useState({});
    const [locationInfo, setLocationInfo] = React.useState({});


    const fetchData = async () => {
        const response = await ItemApi.getItem(itemId);
        setBasicInfo(response.data.basicInfo);
        setUserInfo(response.data.userInfo);
        setMusicInfo(response.data.musicInfo);
        setLocationInfo(response.data.locationInfo);
    }

    React.useEffect(() => {
        fetchData();
    }, [itemId]);

    const DescriptionItem = ({title, content}) => (
        <div>
            <h4 style={{marginBottom: 8}}>{title}</h4>
            <p style={{color: 'gray', marginBottom: 8}}>{content}</p>
        </div>
    );


    const ItemBasicInfo = ({id, likeCount, createdAt}) => (
        <>
            <h4 style={{marginBottom: 12}}>아이템 기본 정보</h4>
            <div style={{display: 'flex', flexDirection: 'row'}}>
                <div style={{flex: 1, marginRight: '20px'}}>
                    <DescriptionItem title="ID" content={id}/>
                    <DescriptionItem title="드랍 일자" content={createdAt}/>
                </div>
                <div style={{flex: 1}}>
                    <DescriptionItem title="좋아요 개수" content={likeCount}/>
                </div>
            </div>
        </>
    );


    const ItemUserInfo = ({userId, nickname, idfv}) => {
        return (
            <>
                <h4 style={{marginBottom: 12}}>아이템 소유유저 정보</h4>
                <DescriptionItem title="유저 ID" content={userId}/>
                <DescriptionItem title="닉네임" content={nickname}/>
                <DescriptionItem title="IDFV" content={idfv}/>
            </>
        )
    }

    const ItemMusicInfo = ({title, artist, album, genre, image}) => {
        return (
            <>
                <h4 style={{marginBottom: 12}}>음악 정보</h4>
                <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center'}}>
                    <Image
                        src={image}
                        style={{width: 200, height: 200}}/>
                </div>
                <DescriptionItem title="음악 이름" content={title}/>
                <DescriptionItem title="아티스트" content={artist}/>
                <DescriptionItem title="앨범" content={album}/>
                <DescriptionItem title="장르" content={genre}/>
            </>
        )
    }

    const ItemLocationInfo = ({latitude, longitude, addresss}) => {
        return (
            <>
                <h4 style={{marginBottom: 12}}>아이템 위치 정보</h4>
                <div style={{display: 'flex', flexDirection: 'row'}}>
                    <div style={{flex: 1, marginRight: '20px'}}>
                        <DescriptionItem title="위도" content={latitude}/>
                    </div>
                    <div style={{flex: 1}}>
                        <DescriptionItem title="경도" content={longitude}/>
                    </div>
                </div>
                <DescriptionItem title="주소" content={addresss}/>
            </>
        )
    }


    return (

        <div>
            <ItemBasicInfo id={basicInfo.id} likeCount={basicInfo.likeCount} createdAt={basicInfo.createdAt}/>
            <Divider/>
            <ItemUserInfo nickname={userInfo.nickname} idfv={userInfo.idfv} userId={userInfo.id}/>
            <Divider/>
            <ItemMusicInfo title={musicInfo.title} artist={musicInfo.artist} album={musicInfo.album}
                           genre={musicInfo.genre} image={musicInfo.albumImageUrl}/>
            <Divider/>
            <ItemLocationInfo latitude={locationInfo.latitude} longitude={locationInfo.longitude}
                              addresss={locationInfo.address}/>
        </div>
    )
}

export default ItemDetailPage;