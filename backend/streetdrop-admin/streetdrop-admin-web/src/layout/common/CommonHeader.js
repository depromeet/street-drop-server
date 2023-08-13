import {Header} from "antd/es/layout/layout";
import {Button, Dropdown, theme} from "antd";
import {LogoutOutlined, UserOutlined} from "@ant-design/icons";
import React, {useEffect} from "react";
import authService from "../../service/AuthService";
import {useNavigate} from "react-router-dom";
import MemberApi from "../../api/domain/member/MemberApi";


function CommonHeader() {
    const {token: {colorBgContainer},} = theme.useToken();
    const navigate = useNavigate();
    const [memberName, setMemberName] = React.useState('');
    const [memberPart, setMemberPart] = React.useState('');

    useEffect(() => {
        fetchMemberName();
    });
    const fetchMemberName = async () => {
        try {
            const response = await MemberApi.getMemberInfo();
            setMemberName(response.data.name);
            setMemberPart(response.data.part);
        } catch (e) {
            console.log(e);
        }

    }


    const items = [
        {
            label: 'Logout',
            icon: <LogoutOutlined/>,
            onClick: () => {
                authService.logout();
                navigate('/login');
            }
        },
    ];

    return (
        <>
            <Header style={{padding: 0, background: colorBgContainer}}>
                <span style={{marginLeft: 30, fontWeight: "bold"}}>Street Drop Admin</span>

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
                <div
                    style={{
                        float: 'right',
                        flexDirection: 'row',
                    }}>
                    <span>{memberName}</span>
                    <span style={{color: 'gray', marginLeft: 5}}>
                        <small>{memberPart}</small>
                    </span>
                </div>
            </Header>
        </>
    );
}

export default CommonHeader;