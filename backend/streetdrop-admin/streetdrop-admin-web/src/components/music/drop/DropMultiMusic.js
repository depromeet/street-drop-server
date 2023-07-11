import React from 'react';
import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons';
import {Button, Form, Input, Space} from 'antd';


const onFinish = (values) => {
    console.log('Received values of form:', values);
};

function DropMultiMusic() {
    return (
        <div>
            <Form
                name="dynamic_form_nest_item"
                onFinish={onFinish}
                style={{maxWidth: 600}}
                autoComplete="off"
            >
                <Form.List name="items">
                    {(fields, {add, remove}) => (
                        <>
                            {fields.map(({key, name, ...restField}) => (
                                <Space key={key} style={{display: 'flex', marginBottom: 8}} align="baseline">
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'first']}
                                        rules={[{required: true, message: 'Missing Latitude'}]}
                                    >
                                        <Input placeholder="Latitude"/>
                                    </Form.Item>
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'last']}
                                        rules={[{required: true, message: 'Missing Longtitude'}]}
                                    >
                                        <Input placeholder="Longtitude"/>
                                    </Form.Item>
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'last']}
                                        rules={[{required: true, message: 'Missing Comment'}]}
                                    >
                                        <Input placeholder="Comment"/>
                                    </Form.Item>
                                    <MinusCircleOutlined onClick={() => remove(name)}/>
                                </Space>
                            ))}
                            <Form.Item>
                                <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined/>}>
                                    Add field
                                </Button>
                            </Form.Item>
                        </>
                    )}
                </Form.List>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );

}

export default DropMultiMusic;