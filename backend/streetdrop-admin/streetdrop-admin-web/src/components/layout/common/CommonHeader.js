import {Header} from "antd/es/layout/layout";
import {Button, Dropdown, theme} from "antd";
import {LogoutOutlined, UserOutlined} from "@ant-design/icons";
import React from "react";


function CommonHeader() {
    const {token: {colorBgContainer},} = theme.useToken();


    const items = [
        {
            label: (
                <div>
                    <span>정성훈</span>
                    <span style={{color: 'gray', marginLeft: 5}}>
                        <small>백엔드</small>
                    </span>
                </div>
            ),
        },
        {
            label: 'Logout',
            icon: <LogoutOutlined/>,
        },
    ];

    return (
        <>
            <Header style={{padding: 0, background: colorBgContainer}}>
                <span style={{marginLeft: 30, font: "bold"}}>Street Drop Admin</span>
                <Dropdown menu={{items}} placement="bottomRight" arrow>
                    <Button
                        type="text"
                        icon={<UserOutlined/>}
                        style={{
                            fontSize: '16px',
                            width: 64,
                            height: 64,
                            display: 'inline-block',
                            float: 'right'
                        }}/>
                </Dropdown>
            </Header>
        </>
    );
}

export default CommonHeader;