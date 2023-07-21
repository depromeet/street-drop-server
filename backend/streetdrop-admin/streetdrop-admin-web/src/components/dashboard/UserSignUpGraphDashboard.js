import UserLineGraph from "../graph/UserLineGraph";
import {useEffect, useState} from "react";
import UserSignUpStaticApi from "../../api/domain/dashboard/UserSignUpStaticApi";

function UserSignUpGraphDashboard() {

    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await UserSignUpStaticApi.getUserSignUpStaticCount();
        const formattedData = response.data.map(data => ({
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