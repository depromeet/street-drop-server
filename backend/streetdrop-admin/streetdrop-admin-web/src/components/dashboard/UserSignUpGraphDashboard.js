import React, {useEffect, useState} from "react";
import {ResponsiveLine} from "@nivo/line";
import UserSignUpStaticApi from "../../api/domain/dashboard/UserSignUpStaticApi";

function UserSignUpGraphDashboard() {

    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        const response = await UserSignUpStaticApi.getUserSignUpStaticCount();
        response.data.forEach((item) => {
            item.x = item.date;
            item.y = item.count;
        });
        setData(response.data);

    };


    return (
        <>
            <div
                style={{
                    padding: '20px',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    height: '100%',
                }}
            >
                <div style={{
                    display: 'flex',
                    alignItems: 'center',
                    justifyContent: 'space-between',
                    marginLeft: '20px',
                    marginRight: '20px',
                }}>
                    <p style={{
                        color: '#5B5B5B',
                        fontSize: '16px',
                        fontStyle: 'normal',
                        fontWeight: 700,
                    }}>신규 가입 유저</p>
                </div>
                <div style={{height: '300px'}}>
                    <ResponsiveLine
                        data={[
                            {
                                id: '일자별 가입 유저',
                                color: 'hsl(208,100%,26%)',
                                data: data,
                            }
                        ]}
                        margin={{top: 10, right: 30, bottom: 30, left: 30}}
                        xScale={{type: 'point'}}
                        yScale={{
                            type: 'linear',
                            min: '0',
                            max: '100',
                            reverse: false
                        }}
                        curve="natural"
                        axisTop={null}
                        axisRight={null}
                        axisBottom={{
                            tickSize: 5,
                            tickPadding: 6,
                            tickRotation: 0,
                        }}
                        axisLeft={null}
                        enableGridY={false}
                        colors={{scheme: 'category10'}}
                        pointColor={{from: 'color', modifiers: []}}
                        pointBorderWidth={2}
                        pointBorderColor={{from: 'serieColor'}}
                        pointLabelYOffset={-12}
                        enablePointLabel={true}
                        pointLabel="y"
                        enableArea={true}
                        enableSlices="x"
                        crosshairType="top-left"
                        areaBaselineValue={0}
                        useMesh={true}
                        legends={[]}
                        motionConfig="default"
                    />
                </div>
            </div>
        </>
    )
}

export default UserSignUpGraphDashboard;