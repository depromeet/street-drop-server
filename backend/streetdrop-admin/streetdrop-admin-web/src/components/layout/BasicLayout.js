import MenuComponent from "./common/Menu";
import {Layout, theme} from "antd";
import React, {useState} from "react";
import {Content} from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import CommonHeader from "./common/CommonHeader";


function BasicLayout({children}) {
    const {token: {colorBgContainer},} = theme.useToken();
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
                    <Content
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            minHeight: 1000,
                            background: colorBgContainer,
                        }}
                    >
                        <div>
                            {children}
                        </div>
                    </Content>
                </Layout>
            </Layout>
        </>
    )
}

export default BasicLayout;