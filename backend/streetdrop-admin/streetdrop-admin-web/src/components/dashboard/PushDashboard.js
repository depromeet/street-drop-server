import {Table} from "antd";


function PushDashboard() {

    const data = [
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        },
        {
            title: '푸시 발송',
            content: '푸시 발송',
            createdAt: '2021-01-01 00:00:00'
        }

    ];

    const columns = [
        {
            title: "제목",
            dataIndex: 'title',
        }, {
            title: "내용",
            dataIndex: 'content',
        }, {
            title: "발송일시",
            dataIndex: 'createdAt',
        }
    ];

    return (
        <>
            <Table
                columns={columns}
                dataSource={data}
                size = 'small'
                pagination={false}
            />
        </>
    )
}

export default PushDashboard;