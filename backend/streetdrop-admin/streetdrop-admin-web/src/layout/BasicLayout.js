import {theme} from "antd";
import React from "react";
import {Content} from "antd/es/layout/layout";


function BasicLayout({children}) {
    const {token: {colorBgContainer},} = theme.useToken();

    return (
        <>
                <Content
                    style={{
                        margin: '20px 16px',
                        padding: 24,
                        background: colorBgContainer,
                        borderRadius: '0.3rem',
                        boxShadow: '0 1px 2px rgba(0, 0, 0, 0.1)',
                    }}
                >
                    <div>{children}</div>
                </Content>
        </>
    )
}

export default BasicLayout;