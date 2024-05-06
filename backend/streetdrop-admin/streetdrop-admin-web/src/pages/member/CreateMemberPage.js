import {Button, Form, Input, Select} from "antd";
import React from "react"
import SignUpApi from "../../api/domain/auth/SignUpApi";

const {Option} = Select;

function CreateMemberPage({refreshFunction, closeFunction}) {
    const [form] = Form.useForm();
    const formRef = React.useRef();


    const createMember = async () => {
        const id = await form.getFieldValue("id");
        const password = await form.getFieldValue("password");
        const email = await form.getFieldValue("email");
        const name = await form.getFieldValue("name");
        const part = await form.getFieldValue("part");
        const response = await SignUpApi.signUp(id, password, email, name, part);
        if (response.status === 200) {
            alert("계정 생성에 성공했습니다")
            refreshFunction();
            closeFunction();
            formRef.current.resetFields();
        } else {
            alert("계정 생성에 실패했습니다")
        }
    }


    return (<>
        <Form ref={formRef} form={form} layout="vertical" hideRequiredMark>
            <Form.Item
                name="name"
                label="이름"
                rules={[{required: true, message: '유저 이름을 입력해주세요'}]}
            >
                <Input placeholder="이름을 입력해주세요"/>
            </Form.Item>
            <Form.Item
                name="email"
                label="Email"
                rules={[{required: true, message: '유저 이메일을 입력해주세요'}]}
            >
                <Input placeholder="유저 이메일을 입력해주세요"/>
            </Form.Item>
            <Form.Item
                name="part"
                label="파트"
                rules={[{required: true, message: '파트를 필수로 선택해주세요.'}]}
            >
                <Select placeholder="파트를 선택해주세요">
                    <Option value="backend">Backend</Option>
                    <Option value="ios">iOS</Option>
                    <Option value="design">Design</Option>
                </Select>
            </Form.Item>
            <Form.Item
                name="id"
                label="유저 Id"
                rules={[{required: true, message: '유저 아이디를 입력해주세요'}]}
            >
                <Input placeholder="유저 아이디를 입력해주세요"/>
            </Form.Item>
            <Form.Item
                name="password"
                label="유저 패스워드"
                rules={[{required: true, message: '유저 아이디를 입력해주세요'}]}
            >
                <Input.Password placeholder="유저 패스워드를 입력해주세요"/>
            </Form.Item>

        </Form>
        <Button type="primary" onClick={createMember}>생성하기</Button>
    </>)
}

export default CreateMemberPage;