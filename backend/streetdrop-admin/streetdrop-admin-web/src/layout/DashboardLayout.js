import React from "react";
import {theme} from "antd";
import {Content} from "antd/es/layout/layout";
import DefaultLayout from "./DefaultLayout";

function DashboardLayout({
                             indicatorContentList,
                             graphContent,
                             graphContent2,
                             basicContent1,
                             basicContent2,
                             basicContent3,
                         }) {
    const {
        token: { colorBgContainer },
    } = theme.useToken();

    const indicatorContentsLayout = (contentList) => (
        <>
            {contentList.map((content, index) => (
                <React.Fragment key={index}>
                    <div
                        style={{
                            background: colorBgContainer,
                            gridColumn: 'span 4',
                            borderRadius: '0.6rem',
                            boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                            gridRow: 'span 1',
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
                    gridRow: 'span 6',
                }}
            >
                {content}
            </div>
        </>
    );

    const eightSizeContent = (content) => (
        <>
            <div
                style={{
                    background: colorBgContainer,
                    gridColumn: 'span 8',
                    borderRadius: '0.6rem',
                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    gridRow: 'span 6',
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
                            {indicatorContentsLayout(indicatorContentList)}
                            {fifteenSizeContent(graphContent)}
                            {nineSizeHalfContent(graphContent2)}
                            {eightSizeContent(basicContent1)}
                            {eightSizeContent(basicContent2)}
                            {eightSizeContent(basicContent3)}
                            <div
                                style={{
                                    gridColumn: 'span 6',
                                    background: colorBgContainer,
                                    borderRadius: '0.6rem',
                                    boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                                }}
                            >
                            </div>

                        </Content>
                    </>
                }
            />
        </>
    );
}

export default DashboardLayout;