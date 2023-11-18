import React from "react";
import {Button, Cascader, Segmented, Space, Typography} from 'antd';
import DashboardLayout from "../layout/DashboardLayout";
import UserSignUpGraphDashboard from "../components/dashboard/UserSignUpGraphDashboard";


const {Title} = Typography;

function Dashboard() {

    const appStoreReviewData = [{
        author :'ParkSeungWoo',
        rating : 5,
        vote : 0,
        title : '아이디어 좋아요',
        content : '아이디어 정말 좋은데 해외도 추가해주시면 좋을거같고, 줌 아웃했을때 마커들이 클러스터링 돼도 좋을것같네요. 또 반경 밖의 음악은 확인할 수 없는것 같은데 다른 지역에 드랍된 음악들도 확인할 수 있는 방안이 있으면 좋을거같아요!'
    }]

    const indexItemData = [{
        title: '전체 가입 유저',
        value: "2,546",
        color: '#0062c1'
    },
        {
            title: '전체 드랍 개수',
            value: "1,453",
            color: '#0062c1'
        },
        {
            title: 'WAU',
            value: "446",
            color: '#0062c1'
        },
        {
            title: 'MAU',
            value: "2546",
            color: '#0062c1'
        }]


    const data = [
        {
            title: '남구 송암동',
            value: 3,
            color: '#0062c1'
        },
        {
            title: '영등포구 여의동',
            value: 3,
            color: '#d56464'
        },
        {
            title: '동작구 상도1동',
            value: 1,
            color: '#0062c1'
        },
        {
            title: '강남구 역삼1동',
            value: 2,
            color: '#3c4f30'
        },
        {
            title: '광진구 광장동',
            value: 1,
            color: '#3c4f30'
        },
    ];

    const longContent = () => (
        <div
            style={{
                padding: '20px'
            }}
        >
            <Space>
                <Title level={5}>지역분석</Title>
                <Segmented options={['3Days', 'Weekly', 'Month']}/>
            </Space>
            {titleValueList(data)}
        </div>
    )

    const titleValueList = (data) => (
        <div style={{padding: 16}}>
            {data.map((item, index) => (
                <div key={index} style={{display: 'flex', justifyContent: 'space-between'}}>
                    <div style={{display: 'flex', alignItems: 'center'}}>
                        <div style={{
                            width: `14px`,
                            backgroundColor: `${item.color}`,
                            height: '14px',
                            marginRight: '10px',
                            borderRadius: '50%',
                        }}></div>
                        <p>{item.title}</p>
                    </div>
                    <p>{item.value}</p>
                </div>
            ))}
        </div>
    );


    const indexContentList = (indexItemContentDataList) => (
        indexItemContentDataList.map(indexItemContentData => {
            return (
                <div style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'center',
                    height: '100%',
                    padding: '0.8rem 1rem'
                }}>
                    <div style={{
                        width: '50px',
                        height: '50px',
                        background: '#D9D9D9',
                        borderRadius: '50px',
                        display: 'flex',
                        alignItems: 'flex-start'
                    }}></div>
                    <div style={{
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'flex-end',
                        padding: '3px',
                        marginLeft: 'auto'
                    }}>
                        <p style={{
                            color: '#5B5B5B',
                            fontSize: '14px',
                            fontWeight: 600,
                            lineHeight: 'normal',
                            margin: '0',
                            marginBottom: '8px'
                        }}>{indexItemContentData.title}</p>
                        <p style={{
                            color: '#333',
                            fontSize: '24px',
                            fontWeight: 700,
                            lineHeight: 'normal',
                            margin: '0'
                        }}>{indexItemContentData.value}</p>
                    </div>
                </div>
            )
        })
    );


    const selectOptions: Option[] = [
        {
            value: '이번 주',
            label: '이번 주',
        },
        {
            value: '지난 주',
            label: '지난 주',
        },
        {
            value: '최근 월별',
            label: '최근 월별',
        },
    ];
    const onChange = (value: string[]) => {
        console.log(value);
    };


    const graphContent = () => (
        <div
            style={{
                padding: '20px',
                flexDirection: 'column', // 수직 정렬을 위해 추가
                justifyContent: 'center', // 수직 가운데 정렬을 위해 추가
                height: '100%',
            }}
        >
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                marginLeft: '20px',
                marginRight: '20px',
            }}>
                <p style={{
                    color: '#5B5B5B',
                    fontSize: '16px',
                    fontStyle: 'normal',
                    fontWeight: 700,
                }}>신규 가입 유저</p>
                <Cascader
                    size="small"
                    style={{width: '100px'}}
                    defaultValue={['이번 주']} options={selectOptions} onChange={onChange} changeOnSelect
                    bordered={false}/>
            </div>
            <UserSignUpGraphDashboard/>
        </div>
    )

    const reportListContent = () => (
        <div>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                marginLeft: '20px',
                marginRight: '20px',
            }}>
                <p style={{
                    color: '#5B5B5B',
                    fontSize: '16px',
                    fontStyle: 'normal',
                    fontWeight: 700,
                }}>신고 관리</p>
                <button>바로가기</button>

            </div>

        </div>
    )

    const appStoreReviewContent = () => (
        <div>
            <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'space-between',
                marginLeft: '20px',
                marginRight: '20px',
            }}>
                <p style={{
                    color: '#5B5B5B',
                    fontSize: '16px',
                    fontStyle: 'normal',
                    fontWeight: 700,
                }}>앱스토어 리뷰</p>
                <button>바로가기</button>

            </div>

        </div>
    )


    return (
        <>
            <DashboardLayout longContent={longContent}
                             indexContentList={indexContentList(indexItemData)}
                             graphContent={graphContent()}
                             firstHalfContent={appStoreReviewContent()}
                             secondHalfContent={reportListContent()}
            />
        </>
    );

}

export default Dashboard;
