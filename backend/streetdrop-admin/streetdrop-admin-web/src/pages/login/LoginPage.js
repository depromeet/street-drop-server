import {Button, Form, Input, Tooltip} from "antd";
import '../../styles/Login.css';
import React from 'react';
import LoginApi from "../../api/domain/auth/LoginApi";
import authService from "../../service/AuthService";

function LoginPage() {
    const onLoginClick = async ({username, password}) => {
        try {
            const result = await LoginApi(username, password);
            await authService.saveToken(result.data.accessToken, result.data.refreshToken)
            window.location.href = '/';
        } catch (error) {
            const {status} = error.response;
            if (status === 400) {
                alert('이메일 또는 비밀번호를 확인하세요.');
            } else if (status === 401) {
                alert('이메일 또는 비밀번호를 확인하세요.');
            } else {
                alert('로그인 실패');
            }
        }
    };


    return (
        <div className="back-ground-color full-screen">
            <div className="login-box-container">
                <div className="street-drop-login-img-container">
                    <img className="street-drop-img" src="image/login_background.png" alt="img"/>
                    <img className="street-drop-app" src="image/login_app.png" alt="img"/>
                </div>
                <div className="login-from-box-container">
                    <Tooltip title="스트릿 드랍 관리자 페이지 입니다.">
                        <p className="login-title">관리자 로그인</p>
                    </Tooltip>

                    <Form
                        name="normal_login"
                        className="form-container"
                        initialValues={{remember: true}}
                        onFinish={onLoginClick}
                    >
                        <Form.Item
                            name="username"
                            rules={[{required: true, message: '아이디를 입력해주세요'}]}
                        >
                            <Input
                                className="id-field"
                                placeholder="아이디"/>
                        </Form.Item>
                        <Form.Item
                            name="password"
                            rules={[{required: true, message: '비밀번호를 입력해주세요'}]}
                        >
                            <Input
                                className="password-field"
                                type="password"
                                placeholder="패스워드"
                            />
                        </Form.Item>
                        <Form.Item>
                            <Button htmlType="submit" className="button-style">
                                <div
                                    style={{
                                        fontSize: '16px',
                                        fontWeight: 'bold',
                                    }}
                                >
                                    로그인
                                </div>

                            </Button>
                        </Form.Item>
                    </Form>
                </div>

            </div>

        </div>
    )
}

export default LoginPage;