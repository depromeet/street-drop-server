import React from "react";
import {Avatar, Col, List, Row, Segmented, Space, Statistic, theme, Typography} from "antd";
import {Content} from "antd/es/layout/layout";
import DefaultLayout from "./DefaultLayout";
import CountUp from "react-countup";


const {Title} = Typography;

function DashboardLayout({ children, children2, children3, children4 }) {
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    const quickAccessPanel = () => (
        <>
            <div>{children}</div>
        </>
    );

    const formatter = (value) => <CountUp end={value} separator=","/>;

    const indexContent = () => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 4', // 3쿼터 콘텐츠
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 1', // 2행 차지 (높이 조절)
                    display: 'flex', // 요소 내부를 가로로 정렬하기 위해 flex 사용
                    justifyContent: 'center', // 가로 가운데 정렬
                    alignItems: 'center', // 세로 가운데 정렬
                    flexDirection: 'column', // 세로로 정렬
                }}
            >
            </div>
        </>
    );

    const indexContents = () =>(
        <>
            {indexContent()}
            {indexContent()}
            {indexContent()}
            {indexContent()}
            {indexContent()}
            {indexContent()}
        </>
    )

    const treeQuarterGraphContent = () => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 18', // 3쿼터 콘텐츠
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 6', // 2행 차지 (높이 조절)
                }}
            >
            </div>
        </>
    );

    const oneQuarterContent = () => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 6', // 1쿼터 콘텐츠
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 6', // 2행 차지 (높이 조절)
                }}
            ></div>
        </>
    );

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
                <Segmented options={['3Days', 'Weekly', 'Month']} />
            </Space>
            {titleValueList(data)}
        </div>
    )

    const titleValueList = (data) => (
        <div style={{padding:16}}>
            {data.map((item, index) => (
                <div key={index} style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <div style={{ display: 'flex', alignItems: 'center' }}>
                        <div style={{
                            width: `14px`,  // 가로로 긴 막대기 크기 조절 (예: value * 10)
                            backgroundColor: `${item.color}`,      // 막대기 색상 설정
                            height: '14px',                // 막대기 높이 설정
                            marginRight: '10px',             // 막대기와 텍스트 사이 여백 설정
                            borderRadius: '50%',         // 동그라미 모양 설정
                        }}></div>
                        <p>{item.title}</p>
                    </div>
                    <p>{item.value}</p>
                </div>
            ))}
        </div>
    );


    return (
        <>
            <DefaultLayout
                content={
                    <>
                        <Content style={{
                            margin: '20px 16px',
                            display: 'grid',
                            gridTemplateColumns: 'repeat(24, 1fr)' ,
                            gridGap: '20px'
                        }}>
                            <div
                                style={{
                                    gridColumn: 'span 18',
                                    display: 'grid',
                                    gridTemplateColumns: 'repeat(24, 1fr)' ,
                                    gridRow: "auto",
                                    gridGap: '20px'
                                }}
                            >
                                {indexContents()}
                                {treeQuarterGraphContent()}
                                {oneQuarterContent()}
                                {oneQuarterContent()}
                                {treeQuarterGraphContent()}
                            </div>
                            <div
                                style={{
                                    gridColumn: 'span 6',
                                    background: colorBgContainer,
                                    borderRadius: '0.6rem',
                                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                                }}
                            >
                                {longContent()}
                            </div>



                        </Content>
                    </>
                }
            />
        </>
    );
}

export default DashboardLayout;