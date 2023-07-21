import {Table} from "antd";
import axios from "axios";
import {useEffect, useState} from "react";

function ItemDashboard() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    },[])
    const fetchData = () => {
        axios.get('/admin/villages/items/count/recent')
            .then(response => {
                const dataResult = response.data.slice(0, 5);
                setData(dataResult);
            })
            .catch(error => {
                console.error('fail when fetching data', error);
            });
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