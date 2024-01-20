// yarn add @nivo/pie
import {ResponsivePie} from '@nivo/pie'

const MyResponsivePie = ({data /* see data tab */}) => (


    <ResponsivePie
        data={data}
        margin={{top: 20, right: 100, bottom: 20, left: 100}}
        innerRadius={0.5}
        padAngle={0.5}
        activeOuterRadiusOffset={8}
        borderWidth={1}
        colors={{scheme: 'purple_blue'}}
        borderColor={{
            from: 'color',
            modifiers: [
                [
                    'darker',
                    0.2
                ]
            ]
        }}
    />
)

export default MyResponsivePie;