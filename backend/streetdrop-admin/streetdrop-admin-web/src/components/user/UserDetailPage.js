import React, {useEffect, useState} from "react"
import {Divider, List, Skeleton} from "antd";
import axios from "axios";

function UserDetailPage({userId}) {
    const [userBasicInfo, setUserBasicInfo] = useState(null);
    const [userDetailInfo, setUserDetailInfo] = useState(null);
    const [userActivity, setUserActivity] = useState(null);

    useEffect(() => {
        fetchUserDetail(userId);
    }, [userId]);

    const fetchUserDetail = (id) => {
        axios.get('/admin/users/' + id)
            .then(response => {
                const data = response.data;
                setUserBasicInfo(data.userBasicInfo);
                setUserDetailInfo(data.userDetailInfo);
                setUserActivity(data.userActivity);
            }).catch(error => {
            console.error("Error fetching data:", error);
        });
    }

    const DescriptionItem = ({title, content}) => (
        <div>
            <h4 style={{marginBottom: 8}}>{title}</h4>
            <p style={{color: 'gray', marginBottom: 8}}>{content}</p>
        </div>
    );

    const UserBasicInfo = ({id, nickname, idfv, createdAt}) => (
        <>
            <h4 style={{marginBottom: 12}}>유저 기본정보</h4>
            <DescriptionItem title="ID" content={id}/>
            <DescriptionItem title="닉네임" content={nickname}/>
            <DescriptionItem title="IDFV" content={idfv}/>
            <DescriptionItem title="가입일" content={createdAt}/>
        </>
    );

    const UserDetailInfo = ({allDropCount, mainDropLocation, dropLocations, interestGenre}) => (
        <>
            <h4 style={{marginBottom: 12}}>유저 상세정보</h4>
            <DescriptionItem title="총 드랍 개수" content={allDropCount}/>
            <DescriptionItem title="주요 활동 지역" content={mainDropLocation}/>
            <DescriptionItem title="활동 지역" content={dropLocations}/>
            <DescriptionItem title="관심 장르" content={interestGenre}/>
        </>
    );

    const UserRecentActivity = ({data}) => (
        <>
            <h4>최근 활동</h4>
            <p style={{marginTop: '5px', color: 'gray', fontSize: '10px'}}>(최근 5건 활동 내역)</p>
            <List dataSource={data}
                  renderItem={data => (
                      <List.Item>
                          <List.Item.Meta title={data.title} description={data.content}/>
                      </List.Item>
                  )}
            >
            </List>
        </>)

    if (userDetailInfo == null || userBasicInfo == null || userActivity == null) {
        return (
            <>
                <Skeleton active={true} paragraph={{rows: 12}}/>
            </>
        );
    }

    return (
        <>
            <UserBasicInfo
                id={userBasicInfo.id}
                nickname={userBasicInfo.nickname}
                idfv={userBasicInfo.idfv}
                createdAt={userBasicInfo.createdAt}
            />
            <Divider/>
            <UserDetailInfo
                allDropCount={userDetailInfo.allDropCount}
                mainDropLocation={userDetailInfo.mainDropLocation}
                dropLocations={userDetailInfo.dropLocations}
                interestGenre={userDetailInfo.interestGenre}
            />
            <Divider/>
            <UserRecentActivity
                data={userActivity}
            />
        </>
    )
}

export default UserDetailPage;