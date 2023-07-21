import React, {useEffect, useState} from "react"

import BasicLayout from "../../layout/BasicLayout";
import UserLineGraph from "../dashboard/UserLineGraph";
import axios from "axios";
import {Button, Col, DatePicker, Form, Row, Space, Table} from "antd";
import * as PropTypes from "prop-types";

import dayjs from 'dayjs';

const {RangePicker} = DatePicker;


RangePicker.propTypes = {
    presets: PropTypes.any,
    onChange: PropTypes.any
};

function UserSignUpGraph() {

    const [data, setData] = useState([]);
    const [dayRange, setDayRange] = useState([dayjs().add(-30, 'day'), dayjs()]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = () => {
        const startDate = dayRange[0].startOf('day').format('YYYY-MM-DDTHH:mm:ss');
        const endDate = dayRange[1].format('YYYY-MM-DDTHH:mm:ss');
        axios.get(`/admin/users/statical/signup/count?startDate=${startDate}&endDate=${endDate}`)
            .then(response => {
                const formattedData = response.data.map(data => ({
                    x: data.date,
                    y: data.count
                }));
                setData(formattedData);
            })
            .catch(error => {
                console.error('fail when fetching data', error);
            });
    };

    const onRangeChange = (dates, dateStrings) => {
        setDayRange(dates);
    };

    const rangePresets = [
        {label: 'Last 7 Days', value: [dayjs().add(-7, 'd'), dayjs()]},
        {label: 'Last 14 Days', value: [dayjs().add(-14, 'd'), dayjs()]},
        {label: 'Last 30 Days', value: [dayjs().add(-30, 'd'), dayjs()]},
        {label: 'Last 90 Days', value: [dayjs().add(-90, 'd'), dayjs()]},
    ];

    const DataTable = ({data}) => {
        const halfLength = Math.floor(data.length / 2);
        const firstHalf = data.slice(0, halfLength);
        const secondHalf = data.slice(halfLength);

        return (
            <>
                <Row>
                    <Col span={12}>
                        <h1>
                            <Table
                                size="small"
                                style={{marginTop: '30px'}}
                                dataSource={firstHalf}
                                pagination={false}
                                columns={[
                                    {
                                        title: '일자',
                                        dataIndex: 'x',
                                    },
                                    {
                                        title: '가입 유저수',
                                        dataIndex: 'y',
                                    }
                                ]}
                            />
                        </h1>
                    </Col>
                    <Col span={12}>
                        <h1>
                            <Table
                                size="small"
                                style={{marginTop: '30px'}}
                                dataSource={secondHalf}
                                pagination={false}
                                columns={[
                                    {
                                        title: '일자',
                                        dataIndex: 'x',
                                    },
                                    {
                                        title: '가입 유저수',
                                        dataIndex: 'y',
                                    }
                                ]}
                            />
                        </h1>
                    </Col>
                </Row>
            </>
        )

    }


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>유저가입 조회</h3>
                <p style={{color: 'gray'}}>일자별, 주별, 기간별 유저 가입을 확인할 수 있습니다.</p>
                <Space direction="vertical" size={12} style={{marginTop: '20px'}}>
                    <Form
                        layout="inline"
                    >
                        <Form.Item label="조회기간">
                            <RangePicker presets={rangePresets} onChange={onRangeChange} value={dayRange}/>
                        </Form.Item>
                        <Form.Item>
                            <Button onClick={() => {
                                fetchData()
                            }}>조회</Button>
                        </Form.Item>

                    </Form>
                </Space>
                {data && <UserLineGraph data={data}/>}
                <DataTable data={data}/>
            </BasicLayout>
        </>
    )
}

export default UserSignUpGraph;