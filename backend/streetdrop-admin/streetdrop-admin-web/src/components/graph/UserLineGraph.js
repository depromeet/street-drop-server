import React from 'react';
import { ResponsiveLine } from '@nivo/line'

function UserLineGraph({data}) {
    return (
        <div style={{height: '330px'}}>
            <ResponsiveLine
                data={[
                    {
                      id: '일자별 가입 유저',
                      color: 'hsl(210,98%,37%)',
                      data: data,
                    }
                ]}
                margin={{top: 50, right: 50, bottom: 50, left: 50}}
                xScale={{type: 'point'}}
                yScale={{
                    type: 'linear',
                    min: 'auto',
                    max: 'auto',
                }}
                yFormat=" >-.2f"
                axisTop={null}
                axisRight={null}
                axisBottom={{
                    tickSize: 5,
                    tickPadding: 5,
                    tickRotation: 0,
                    legend: '일자별',
                    legendOffset: 36,
                    legendPosition: 'middle'
                }}
                axisLeft={{
                    tickSize: 5,
                    tickPadding: 5,
                    tickRotation: 0,
                    legend: '유저수',
                    legendOffset: -40,
                    legendPosition: 'middle'
                }}
                pointSize={6}
                pointColor={{theme: 'background'}}
                pointBorderWidth={1}
                pointBorderColor={{from: 'serieColor'}}
                pointLabelYOffset={-12}
                legends={[
                    {
                        anchor: 'bottom',
                        direction: 'row',
                        justify: false,
                        translateX: 100,
                        translateY: 55,
                        itemsSpacing: 0,
                        itemDirection: 'left-to-right',
                        itemWidth: 80,
                        itemHeight: 20,
                        itemOpacity: 0.75,
                        symbolSize: 12,
                        symbolShape: 'circle',
                        symbolBorderColor: 'rgba(0, 0, 0, .5)',
                        effects: [
                            {
                                on: 'hover',
                                style: {
                                    itemBackground: 'rgba(0, 0, 0, .03)',
                                    itemOpacity: 1
                                }
                            }
                        ]
                    }
                ]}
            />
        </div>

    );

}

export default UserLineGraph;