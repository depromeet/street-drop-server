import React, {useEffect, useState} from "react";
import BasicLayout from "../../layout/BasicLayout";
import {Button, Drawer, Table} from "antd";
import MemberApi from "../../api/domain/member/MemberApi";
import {PlusOutlined} from '@ant-design/icons';
import CreateMemberPage from "./CreateMemberPage";
import {InitialPagination} from "../../constant/PaginationConstant";

function MemberListPage() {
    const [data, setData] = useState([]);
    const [tableParams, setTableParams] = useState({pagination: InitialPagination});
    const [openDrawer, setOpenDrawer] = useState(false);

    const showDrawer = () => {
        setOpenDrawer(true);
    };

    const onClose = () => {
        setOpenDrawer(false);
    };

    useEffect(() => {
        fetchData();
    }, [JSON.stringify(tableParams)]);


    const fetchData = async () => {
        const response = await MemberApi.getAllMembers(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data.members);
        setTableParams({
            ...tableParams,
            pagination: {
                ...tableParams.pagination,
                total: response.data['meta']['totalElements'],
            },
        });
    }

    const columns = [
{
            title: '유저 아이디',
            dataIndex: 'userId'
        },
        {
            title: '멤버명',
            dataIndex: 'name',
        },
        {
            title: '파트',
            dataIndex: 'part',
        },
        {
            title: '이메일',
            dataIndex: 'email',
        }
    ];

    const handleTableChange = (
        pagination,
    ) => {
        setTableParams({
            pagination,
        });


        if (pagination.pageSize !== tableParams.pagination?.pageSize) {
            setData([]);
        }
    };


    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>어드민 유저 관리</h3>
                <p style={{color: 'gray'}}>유저를 조회하고 관리할 수 있습니다.</p>
                <div style={{textAlign: 'right'}}>
                    <Button type="primary" onClick={showDrawer} icon={<PlusOutlined/>}>신규계정</Button>
                </div>
                <Table
                    style={{marginTop: '20px'}}
                    columns={columns}
                    rowKey={record => record.id}
                    pagination={tableParams.pagination}
                    dataSource={data}
                    onChange={handleTableChange}
                />
                <Drawer
                    title="신규 계정생성"
                    onClose={onClose}
                    open={openDrawer}
                    bodyStyle={{paddingBottom: 80}}
                >
                    <CreateMemberPage refreshFunction={fetchData} closeFunction={onClose}/>
                </Drawer>
            </BasicLayout>
        </>
    )


}

export default MemberListPage;