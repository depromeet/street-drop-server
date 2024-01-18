import IndicatorLayout from "./IndicatorLayout";
import {useEffect, useState} from "react";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";

function IndicatorDashboard() {
    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await DashboardApi.getIndicators();
        setContentList(response.data);
    }


    return (
        contentList.map((content, index) => {
            return IndicatorLayout({content})
        })
    )

}

export default IndicatorDashboard;