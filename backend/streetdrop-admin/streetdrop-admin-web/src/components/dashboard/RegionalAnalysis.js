import LocationGraph from "../graph/LocationGraph";
import React, {useEffect, useState} from "react";
import DashboardApi from "../../api/domain/dashboard/DashboardApi";


function RegionalAnalysisDashboard() {
    const [contentList, setContentList] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await DashboardApi.getRegionalAnalysis();
        response.data.map((item) => {
            item.id = item.name;
            item.value = item.count;
        })
        setContentList(response.data);
    }


    return (
        <div
            style={{
                padding: '20px',
                flexDirection: 'column',
                justifyContent: 'center',
                height: '100%',
            }}
        >
            <p style={{
                color: '#5B5B5B',
                fontSize: '16px',
                fontStyle: 'normal',
                fontWeight: 700,
            }}>최근 드랍지역 분석</p>
            <div style={{
                width: '100%',
                height: '300px',
            }}>
                <LocationGraph data={contentList}/>
            </div>

        </div>
    )


}

export default RegionalAnalysisDashboard;