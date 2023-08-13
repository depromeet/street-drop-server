import {Table} from "antd";
import {useEffect, useState} from "react";
import VillageApi from "../../api/domain/village/VillageApi";

function ItemDashboard() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, [])

    const fetchData = async () => {
        const response = await VillageApi.getVillageItemCountRecent();
        setData(response.data.slice(0, 5));
    }

    const columns = [
        {
            title: '지역명',
            dataIndex: 'villageName',
            width: 100,
        },
        {
            title: '드랍 개수',
            dataIndex: 'itemCount',
            width: 40,
        }
    ];


    return (
        <div>
            <Table columns={columns} dataSource={data} size={"small"} pagination={false}/>
        </div>
    )
}

export default ItemDashboard;