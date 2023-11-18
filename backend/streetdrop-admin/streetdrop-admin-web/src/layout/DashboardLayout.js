import React from "react";
import {theme} from "antd";
import {Content} from "antd/es/layout/layout";
import DefaultLayout from "./DefaultLayout";

function DashboardLayout({indexContentList, longContent, graphContent, firstHalfContent, secondHalfContent}) {
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    const indexContents = (indexContentList) => (
        <>
            {indexContentList.slice(0, 4).map(content => (
                <React.Fragment key={content.id}>
                    <div
                        style={{
                            background: colorBgContainer,
                            gridColumn: 'span 6', // 3쿼터 콘텐츠
                            borderRadius: '0.6rem',
                            boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                            gridRow: 'span 1', // 2행 차지 (높이 조절)
                        }}
                    >
                        {content}
                    </div>
                </React.Fragment>
            ))}
        </>
    );


    const fifteenSizeContent = (content) => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 15',
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 6',
                }}
            >
                {content}
            </div>
        </>
    );

    const nineSizeHalfContent = (content) => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 9',
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 3',
                }}
            >
                {content}
            </div>
        </>
    );

    const twelveSizeContent = (content)  => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 12', // 1쿼터 콘텐츠
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 4', // 2행 차지 (높이 조절)
                }}
            >
                {content}
            </div>
        </>
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
                                {indexContents(indexContentList)}
                                {fifteenSizeContent(graphContent)}
                                {nineSizeHalfContent(firstHalfContent)}
                                {nineSizeHalfContent(firstHalfContent)}
                                {twelveSizeContent(firstHalfContent)}
                                {twelveSizeContent(secondHalfContent)}
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