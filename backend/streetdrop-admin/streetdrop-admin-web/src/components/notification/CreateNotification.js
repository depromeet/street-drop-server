import BasicLayout from "../layout/BasicLayout";
import {Button, Form, Input, message, Radio} from "antd";
import {useState} from "react";
import axios from "axios";

const {TextArea} = Input;


function CreateNotification() {
    const [form] = Form.useForm();
    const [pushType, setPushType] = useState('all');
    const [messageApi, contextHolder] = message.useMessage();
    const onPushTypeChange = (changedValues, allValues) => {
        setPushType(allValues.pushTypeValue);
    }

    const IndivdualPush = (userIds, title, content) => {
        // userId int로
        const userIdList = userIds.split(',').map(
            (userId) => {
                return parseInt(userId);
            }
        );

        return JSON.stringify(
            {
                "userIds": userIdList,
                "title": title,
                "content": content
            }
        );
    }

    const AllPush = (title, content) => {
        return JSON.stringify(
            {
                "title": title,
                "content": content
            }
        );
    }


    const requestAllPush = (data) => {
        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: '/noti/push/all/send',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }

        axios.request(config)
            .then(
                (response) => {
                    console.log(JSON.stringify(response.data));
                    messageApi.open(
                        {
                            type: 'success',
                            content: "푸시 발송에 성공했습니다"
                        }
                    )
                }
            )
            .catch(
                (error) => {
                    console.log(error);
                    messageApi.open(
                        {
                            type: 'error',
                            content: "푸시 발송에 실패했습니다"
                        }
                    )
                })
    }

    const requsetIndividaulPush = (data) => {

        let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: '/noti/push/send',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        };

        axios.request(config)
            .then(
                (response) => {
                    messageApi.open(
                        {
                            type: 'success',
                            content: "푸시 발송에 성공했습니다"
                        }
                    )
                }
            )
            .catch(
                (error) => {
                    messageApi.open(
                        {
                            type: 'error',
                            content: "푸시 발송에 실패했습니다"
                        }
                    )
                }
            )
    };


    const clickPushSend = () => {
        const title = form.getFieldValue('title');
        const content = form.getFieldValue('content');
        const userIds = form.getFieldValue('userIds');

        console.log("clicked")

        if (pushType === 'all') {
            console.log("all")
            const data = AllPush(title, content);
            requestAllPush(data);
        } else {
            console.log("individual")
            const data = IndivdualPush(userIds, title, content);
            requsetIndividaulPush(data);
        }
    }

    return (
        <>
            {contextHolder}
            <BasicLayout>
                <h1>푸시 발송</h1>
                <br/>
                <Form
                    form={form}
                    onValuesChange={onPushTypeChange}
                    initialValues={{pushTypeValue: pushType}}
                >
                    <Form.Item name="pushTypeValue">
                        <Radio.Group>
                            <Radio.Button value="all">전체</Radio.Button>
                            <Radio.Button value="individual">개인</Radio.Button>
                        </Radio.Group>
                    </Form.Item>
                    <Form.Item label="푸시 대상" hidden={pushType === 'all'} name="userIds">
                        <Input placeholder="푸시 대상을 입력하세요 - 유저의 Id"/>
                    </Form.Item>
                    <Form.Item label="푸시 제목" name="title">
                        <Input
                            placeholder="푸시 제목을 입력하세요."
                            showCount maxLength={20}/>
                    </Form.Item>
                    <Form.Item label="푸시 내용" name="content">
                        <TextArea
                            placeholder="푸시 내용을 입력하세요."
                            showCount maxLength={50}/>
                    </Form.Item>
                    <Form.Item style={{
                        marginTop: '30px',
                        textAlign: 'right'
                    }}>
                        <Button type="primary" htmlType="submit" onClick={clickPushSend}>푸시 발송</Button>
                    </Form.Item>
                </Form>
            </BasicLayout>
        </>
    )
}

export default CreateNotification;