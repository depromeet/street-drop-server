import UserLineGraph from "../graph/UserLineGraph";
import {useEffect, useState} from "react";

function UserSignUpGraphDashboard() {

    const [data, setData] = useState();

    useEffect(() => {
        fetchData();

    }, []);

    const fetchData = async () => {
        const response = [
            {"date": "10.05", "count": 5},
            {"date": "10.06", "count": 20},
            {"date": "10.07", "count": 34},
            {"date": "10.08", "count": 56},
            {"date": "10.09", "count": 28},
            {"date": "10.10", "count": 67},
            {"date": "10.11", "count": 13}
        ];
        const formattedData = response.map(data => ({
            x: data.date,
            y: data.count
        }));
        setData(formattedData);
    };


    return (
        <>
            <UserLineGraph data={data}/>
        </>
    )
}

export default UserSignUpGraphDashboard;