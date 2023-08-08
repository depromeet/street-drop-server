import React, {useEffect, useState} from "react";
import BasicLayout from "../../layout/BasicLayout";
import {Form, Radio, Table} from "antd";
import VillageApi from "../../api/domain/village/VillageApi";


function LocationAnalysis() {
    const [form] = Form.useForm();
    const [range, setRange] = useState('all')
    const [data, setData] = useState([]);

    const onRangeChange = (changedValues, allValues) => {
        if (allValues.rangeValue === 'all') {
            fetchAllData();
        } else {
            fetchRecentData();
        }
        setRange(allValues.rangeValue);
    }

    useEffect(() => {
        fetchAllData();
    }, [])

    const fetchAllData = async () => {
        const response = await VillageApi.getVillageItemCountAll()
        setData(response.data)
    }

    const fetchRecentData = async () => {
        const response = await VillageApi.getVillageItemCountRecent()
        setData(response.data)
    }


    const columns = [
        {
            title: '지역명',
            dataIndex: 'villageName',
        },
        {
            title: '드랍 개수',
            dataIndex: 'itemCount',
        }
    ];

    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>지역별 아이템 개수 조회</h3>
                <p style={{color: 'gray'}}>지역별로 드랍된 아이템 개수를 확인할 수 있습니다. - 현재 서울, 경기도 지역만 제공됩니다.</p>
                <br/>
                <Form
                    form={form}
                    onValuesChange={onRangeChange}
                    initialValues={{rangeValue: range}}
                >
                    <Form.Item name="rangeValue">
                        <Radio.Group>
                            <Radio.Button value="all">전체</Radio.Button>
                            <Radio.Button value="recent">최근</Radio.Button>
                        </Radio.Group>
                    </Form.Item>
                </Form>
                <Table
                    columns={columns}
                    dataSource={data}
                />
            </BasicLayout>
        </>
    )
}

export default LocationAnalysis;