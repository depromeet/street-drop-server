import React from 'react';
import { ResponsiveLine } from '@nivo/line'

function UserLineGraph({data}) {
    return (
        <div style={{height: '300px'}}>
            <ResponsiveLine
                data={[
                    {
                        id: '일자별 가입 유저',
                        color: 'hsl(208,100%,26%)',
                        data: data,
                    }
                ]}
                margin={{ top: 10, right: 30, bottom: 30, left: 30 }}
                xScale={{ type: 'point' }}
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
                colors={{ scheme: 'category10' }}
                pointColor={{ from: 'color', modifiers: [] }}
                pointBorderWidth={2}
                pointBorderColor={{ from: 'serieColor' }}
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

    );

}

export default UserLineGraph;