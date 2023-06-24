import {Table} from "antd";

function ItemDashboard() {

    const columns = [
        {
            title: '순위',
            dataIndex: 'key',
            width: 30,
        },
        {
            title: '지역명',
            dataIndex: 'name',
            width: 100,
        },
        {
            title: '드랍된 개수',
            dataIndex: 'count',
            width: 40,
        },
        {
            title: '총 개수',
            dataIndex: 'total',
            width: 40,
        }
    ];

    const data = [
        {
            key: 1,
            name: "서울시 성수동",
            count: 60,
            total: 100,
        },
        {
            key: 2,
            name: '강남구 강남동',
            count: 50,
            total: 300,
        },
        {
            key: 3,
            name: '강남구 역삼동',
            count: 40,
            total: 200,
        }
    ];

    return (
        <div>
            <Table columns={columns} dataSource={data} size={"small"}/>
        </div>
    )
}

export default ItemDashboard;