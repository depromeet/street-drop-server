import React from "react"
import {Button, Checkbox, Divider, Form, Input, Select, Space,} from 'antd';
import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons';

function MusicRecommendEventCreate({form}) {


    return (
        <>
            <Form
                form={form}
                layout="horizontal"
                style={{maxWidth: 1800}}
            >
                <h4>제목</h4>
                <Form.Item
                    name="title"
                >
                    <Input/>
                </Form.Item>

                <h4>노출되는 설명 (필드를 추가하면 하나의 문장으로 연결됩니다.)</h4>
                <Form.List name="description">
                    {(fields, {add, remove}) => (
                        <>
                            {fields.map(({key, name, ...restField}) => (
                                <Space key={key} style={{display: 'flex'}} align="baseline">
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'text']}
                                        label="설명"
                                        rules={[{required: true}]}
                                    >
                                        <Input placeholder="설명"/>
                                    </Form.Item>
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'color']}
                                        label="색상"
                                        initialValue={"RecommendTitleBasic"}
                                        rules={[{required: true}]}
                                    >
                                        <Select
                                            defaultValue="RecommendTitleBasic"
                                            style={{width: 120}}
                                            options={[
                                                {value: 'RecommendTitleBasic', label: '기본'},
                                                {value: 'RecommendTitleHighLight', label: '강조'},
                                            ]}
                                        />
                                    </Form.Item>
                                    <MinusCircleOutlined onClick={() => remove(name)}/>
                                </Space>
                            ))}
                            <Form.Item>
                                <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined/>}>추가하기
                                </Button>
                            </Form.Item>
                        </>
                    )}
                </Form.List>

                <h4>추천 검색어</h4>
                <Form.List name="terms">
                    {(fields, {add, remove}) => (
                        <>
                            {fields.map(({key, name, ...restField}) => (
                                <Space key={key} style={{display: 'flex'}} align="baseline">
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'text']}
                                        label="검색어"
                                        rules={[{required: true, message: 'Missing first name'}]}
                                    >
                                        <Input placeholder="검색어"/>
                                    </Form.Item>
                                    <Form.Item
                                        {...restField}
                                        name={[name, 'color']}
                                        label="색상"
                                        initialValue={"RecommendKeywordBasic"}
                                        rules={[{required: true}]}
                                    >
                                        <Select
                                            defaultValue="RecommendKeywordBasic"
                                            style={{width: 100}}
                                            options={[
                                                {value: 'RecommendKeywordBasic', label: '기본'},
                                                {value: 'RecommendKeywordHighLight', label: '강조'},
                                            ]}
                                        />
                                    </Form.Item>
                                    <MinusCircleOutlined onClick={() => remove(name)}/>
                                </Space>
                            ))}
                            <Form.Item>
                                <Button type="dashed" onClick={() => add()} block icon={<PlusOutlined/>}>추가하기
                                </Button>
                            </Form.Item>
                        </>
                    )}
                </Form.List>

                <Divider/>
                <Form.Item
                    name="active"
                    initialValue={true}
                >
                    <Checkbox
                        defaultChecked={false}
                    >비활성화하려면 체크하세요</Checkbox>
                </Form.Item>


            </Form>
        </>
    );
}

export default MusicRecommendEventCreate;