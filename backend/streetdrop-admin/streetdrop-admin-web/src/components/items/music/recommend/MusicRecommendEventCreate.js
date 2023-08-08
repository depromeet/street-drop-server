import React, {useState} from "react"
import {DatePicker, Divider, Form, Input, Radio, Select, TimePicker,} from 'antd';

const options = [];

const {RangePicker} = DatePicker;
const {TextArea} = Input;


function MusicRecommendEventCreate() {
    const [form] = Form.useForm();
    const [showDurationFields, setShowDurationFields] = useState(false);

    const onFormEventTypeChange = (changedValues) => {
        if (changedValues.eventTypeValue === 'duration') {
            setShowDurationFields(true);
        } else {
            setShowDurationFields(false);
        }
    };

    return (
        <>
            <Form
                form={form}
                labelCol={{span: 4}}
                wrapperCol={{span: 16}}
                layout="horizontal"
                onValuesChange={onFormEventTypeChange}
                style={{maxWidth: 800}}
            >
                <Form.Item label="진행 범위">
                    <Radio.Group>
                        <Radio.Button value="all">전체</Radio.Button>
                        <Radio.Button value="group">그룹</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="타입">
                    <Radio.Group name="eventTypeValue">
                        <Radio.Button value="basic">기본</Radio.Button>
                        <Radio.Button value="duration">기간</Radio.Button>
                        <Radio.Button value="location">장소</Radio.Button>
                    </Radio.Group>
                </Form.Item>
                <Form.Item label="이벤트 명">
                    <Input/>
                </Form.Item>
                <Form.Item label="이벤트 설명">
                    <TextArea rows={1}/>
                </Form.Item>

                <Divider/>
                <Form.Item label="이벤트 문구">
                    <Input/>
                </Form.Item>
                <Form.Item label="이벤트 태그">
                    <Select mode="tags" placeholder="Search Tag" options={options}/>
                </Form.Item>

                {showDurationFields && (
                    <>
                        <Divider/>
                        <Form.Item label="진행 일자">
                            <RangePicker/>
                        </Form.Item>
                        <Form.Item label="진행 시간">
                            <TimePicker.RangePicker/>
                        </Form.Item>
                    </>
                )}
            </Form>
        </>
    );
}

export default MusicRecommendEventCreate;