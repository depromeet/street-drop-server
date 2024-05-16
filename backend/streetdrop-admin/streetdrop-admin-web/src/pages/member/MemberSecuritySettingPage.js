import BasicLayout from "../../layout/BasicLayout";
import React, {useState} from "react";
import {Button, Card, Col, Descriptions, Form, Input, message, Modal, Row, Switch} from "antd";
import MemberApi from "../../api/domain/member/MemberApi";

import {RightOutlined} from "@ant-design/icons";
import {Link, useNavigate} from "react-router-dom";


function MemberSecuritySettingPage() {
    const [messageApi, contextHolder] = message.useMessage();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const navigate = useNavigate();

    const showModal = () => {
        setIsModalOpen(true);
    };

    const handleOk = () => {
        setIsModalOpen(false);
    };

    const handleCancel = () => {
        setIsModalOpen(false);
    };

    const items = [
        {
            key: '1',
            label: 'UserName',
            children: 'Zhou Maomao',
        },
        {
            key: '2',
            label: 'Telephone',
            children: '1810000000',
        },
        {
            key: '3',
            label: 'Live',
            children: 'Hangzhou, Zhejiang',
        },
        {
            key: '4',
            label: 'Remark',
            children: 'empty',
        },
        {
            key: '5',
            label: 'Address',
            children: 'No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China',
        },
    ];


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
                <div style={{margin: 10}}>

                    <Card style={{marginTop: 15, marginBottom: 15, paddingLeft: 15, paddingRight: 15}} size={"small"}>
                        <Row>
                            <Col span={8}><h4>비밀번호 변경</h4></Col>
                            <Col span={8} offset={8} style={{
                                display: 'flex',
                                justifyContent: 'flex-end', /* 버튼을 오른쪽 끝으로 정렬 */
                                alignItems: 'center'
                            }}>
                                <Button onClick={showModal}>변경하기</Button>
                                <Modal title="비밀번호 변경"
                                       centered
                                       open={isModalOpen}
                                       onOk={handleOk}
                                       onCancel={handleCancel}>
                                    <Form name="normal_login"
                                          initialValues={{remember: true}}
                                          onFinish={onLoginClick}
                                          style={{maxWidth: '400px'}}
                                    >
                                        <Form.Item name="prevPassword"
                                                   label={"이전 비밀번호"}
                                                   rules={[{required: true, message: '이전 비밀번호를 입력해주세요.'}]}>
                                            <Input className="prev-password-field" type="password"
                                                   placeholder="*******"/>
                                        </Form.Item>
                                        <Form.Item name="newPassword"
                                                   label={"신규 비밀번호"}
                                                   rules={[{required: true, message: '비밀번호를 입력해주세요'}]}>
                                            <Input className="new-password-field" type="password"
                                                   placeholder="*******"/>
                                        </Form.Item>
                                        <Form.Item name="newPasswordCheck"
                                                   label={"비밀번호 확인"}
                                                   rules={[{required: true, message: '비밀번호를 입력해주세요'}]}>
                                            <Input className="new-password-check-field" type="password"
                                                   placeholder="*******"/>
                                        </Form.Item>
                                    </Form>
                                </Modal>
                            </Col>
                        </Row>

                    </Card>
                    <Card style={{marginTop: 15, marginBottom: 15, paddingLeft: 15, paddingRight: 15}} size={"small"}>
                        <Row>
                            <Col span={8}><h4>2단계 인증설정</h4></Col>
                            <Col span={8} offset={8} style={{
                                display: 'flex',
                                justifyContent: 'flex-end', /* 버튼을 오른쪽 끝으로 정렬 */
                                alignItems: 'center'
                            }}>
                                <Switch disabled={true}></Switch>
                            </Col>
                        </Row>
                    </Card>
                    <Card style={{marginTop: 15, marginBottom: 15, paddingLeft: 15, paddingRight: 15}} size={"small"}>

                        <Row>
                            <Col span={8}><h4>소셜로그인 연동</h4></Col>
                            <Col span={8} offset={8} style={{
                                display: 'flex',
                                justifyContent: 'flex-end', /* 버튼을 오른쪽 끝으로 정렬 */
                                alignItems: 'center'
                            }}>
                                <Switch disabled={true}></Switch>
                            </Col>
                        </Row>
                    </Card>
                </div>


            </BasicLayout>
        </>
    )
}

export default MemberSecuritySettingPage;