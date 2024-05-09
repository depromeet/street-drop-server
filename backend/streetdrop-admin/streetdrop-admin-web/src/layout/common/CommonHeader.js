import {Header} from "antd/es/layout/layout";
import {Avatar, Button, Dropdown} from "antd";
import {LogoutOutlined, SettingOutlined} from "@ant-design/icons";
import React, {useEffect} from "react";
import authService from "../../service/AuthService";
import {useNavigate} from "react-router-dom";
import MemberApi from "../../api/domain/member/MemberApi";


function CommonHeader(props) {
    const navigate = useNavigate();
    const [memberName, setMemberName] = React.useState('');
    const [memberPart, setMemberPart] = React.useState('');

    useEffect(() => {
        fetchMemberName();
    });
    const fetchMemberName = async () => {
        try {
            if (window.location.pathname === '/login') {
                return;
            }

            const response = await MemberApi.getMemberInfo();
            setMemberName(response.data.name);
            setMemberPart(response.data.part);
        } catch (e) {
            console.log(e);
        }

    }


    const items = [
        {
            label: memberName + " \n" + memberPart,
            style: {
                fontWeight: 'bold',
                fontSize: '16px',
            },
        },
        {
            label: '보안설정',
            icon: <SettingOutlined/>,
            onClick: () => {
                navigate('/members/security');
            }
        },
        {
            label: '로그아웃',
            icon: <LogoutOutlined/>,
            onClick: () => {
                authService.logout();
                navigate('/login');
            },
            style: {
                color: 'red'
            }
        },
    ];

    if (window.location.pathname === '/login') {
        return null;
    } else {
        return (

            <>
                <Header
                    style={{
                        padding: 0,
                    }}>
                    <a
                        href="/"
                        style={{marginLeft: 30, fontWeight: "bold", color: "white"}}>Street Drop Admin</a>

                    <Dropdown menu={{items}} placement="bottomRight" arrow>
                        <Button
                            type="text"
                            icon={<Avatar shape="square" style={{
                                backgroundColor: '#0055a7',
                                color: '#f6f3f3'
                            }}>{memberName.split("").at(0)}</Avatar>}
                            style={{
                                fontSize: '16px',
                                width: 64,
                                height: 64,
                                display: 'inline-block',
                                float: 'right',
                                color: 'whitesmoke'
                            }}/>
                    </Dropdown>

                </Header>
            </>
        );
    }


}

export default CommonHeader;