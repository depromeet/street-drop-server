import BasicLayout from "../../layout/BasicLayout";
import React from "react";
import {Button, Divider, Form, Input, List, message} from "antd";
import MemberApi from "../../api/domain/member/MemberApi";


function MemberSecuritySettingPage() {
    const [messageApi, contextHolder] = message.useMessage();
    const onLoginClick = async ({prevPassword, newPassword, newPasswordCheck}) => {
        if (newPassword !== newPasswordCheck) {
            messageApi.open({
                type: 'error',
                content: '신규 비밀번호가 일치하지 않습니다.',
            });
        } else {
            const response = await MemberApi.changePassword(prevPassword, newPassword);
            if (response === 204) {
                messageApi.open({
                    type: 'success',
                    content: '비밀번호 변경 성공',
                });
            } else {
                messageApi.open({
                    type: 'error',
                    content: '잘못된 비밀번호 입니다.',
                });
            }
        }
    };

    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>계정 보안 관리</h3>
                <p style={{color: 'gray'}}>계정 보안 관련 설정을 변경할수 있습니다.</p>
                <div style={{
                    display: 'grid',
                    gridTemplateColumns: 'repeat(2, 1fr)',
                    gridGap: '40px',
                }}>
                    <div>
                        <Divider/>
                        <h4 style={{marginBottom: '20px', marginTop: '10px'}}>내 계정정보</h4>
                    </div>
                    <div>
                        <Divider/>
                        <h4 style={{marginBottom: '20px', marginTop: '10px'}}>비밀번호 변경</h4>
                        <Form name="normal_login"
                              initialValues={{remember: true}}
                              onFinish={onLoginClick}
                              style={{maxWidth: '400px'}}
                        >
                            <Form.Item name="prevPassword" rules={[{required: true, message: '이전 비밀번호를 입력해주세요.'}]}>
                                <Input className="prev-password-field" type="password" placeholder="이전 비밀번호"/>
                            </Form.Item>
                            <Form.Item name="newPassword" rules={[{required: true, message: '비밀번호를 입력해주세요'}]}>
                                <Input className="new-password-field" type="password" placeholder="신규 비밀번호"/>
                            </Form.Item>
                            <Form.Item name="newPasswordCheck" rules={[{required: true, message: '비밀번호를 입력해주세요'}]}>
                                <Input className="new-password-check-field" type="password" placeholder="신규 비밀번호 확인"/>
                            </Form.Item>
                            <Form.Item>
                                <Button htmlType="submit">비밀번호 변경</Button>
                            </Form.Item>
                        </Form>
                    </div>
                    <div>
                        <Divider/>
                        <h4 style={{marginBottom: '20px', marginTop: '10px'}}>2단계 인증</h4>
                    </div>

                    <div>
                        <Divider/>
                        <h4 style={{marginBottom: '20px', marginTop: '10px'}}>소셜 로그인 연동</h4>
                        <List>

                        </List>
                    </div>
                </div>
            </BasicLayout>
        </>
    )
}

export default MemberSecuritySettingPage;