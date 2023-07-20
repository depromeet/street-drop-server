import React, {useState} from "react";
import {Col, Layout, Row, theme} from "antd";
import Sider from "antd/es/layout/Sider";
import MenuComponent from "./common/Menu";
import {Content} from "antd/es/layout/layout";
import CommonHeader from "./common/CommonHeader";

function DashboardLayout({children, children2, children3, children4}) {
    const {
        token: {colorBgContainer},
    } = theme.useToken();
    const [collapsed, setCollapsed] = useState(false);

    return (
        <>
            <Layout className="view-container">
                <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                    <div className="demo-logo-vertical"/>
                    <MenuComponent/>
                </Sider>
                <Layout>
                    <CommonHeader/>
                    <Row>
                        <Col span={12}>
                            <Content
                                style={{
                                    marginTop: '24px',
                                    marginBottom: '8px',
                                    marginLeft: '16px',
                                    marginRight: '8px',
                                    padding: 24,
                                    minHeight: 350,
                                    background: colorBgContainer,
                                }}
                            >
                                <div>
                                    {children}
                                </div>
                            </Content>
                        </Col>
                        <Col span={12}>
                            <Content
                                style={{
                                    marginTop: '24px',
                                    marginBottom: '8px',
                                    marginLeft: '8px',
                                    marginRight: '16px',
                                    padding: 24,
                                    minHeight: 350,
                                    background: colorBgContainer,
                                }}
                            >
                                <div>
                                    {children2}
                                </div>
                            </Content>
                        </Col>
                    </Row>
                    <Row>
                        <Col span={12}>
                            <Content
                                style={{
                                    marginTop: '8px',
                                    marginBottom: '16px',
                                    marginLeft: '16px',
                                    marginRight: '8px',
                                    padding: 24,
                                    minHeight: 400,
                                    background: colorBgContainer,
                                }}
                            >
                                <div>
                                    {children3}
                                </div>
                            </Content>
                        </Col>
                        <Col span={12}>
                            <Content
                                style={{
                                    marginTop: '8px',
                                    marginBottom: '16px',
                                    marginLeft: '8px',
                                    marginRight: '16px',
                                    padding: 24,
                                    minHeight: 400,
                                    background: colorBgContainer,
                                }}
                            >
                                <div>
                                    {children4}
                                </div>
                            </Content>
                        </Col>
                    </Row>
                </Layout>
            </Layout>
        </>
    )
}

export default DashboardLayout;