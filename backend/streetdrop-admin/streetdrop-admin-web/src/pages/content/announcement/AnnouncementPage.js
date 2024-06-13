import BasicLayout from "../../../layout/BasicLayout";
import React, {useEffect, useState} from "react";

import {Button, Drawer, Table} from "antd";
import {useNavigate} from "react-router-dom";
import AnnouncementApi from "../../../api/domain/announcement/AnnouncementApi";
import AnnouncementDetailPage from "./AnnouncementDetailPage";


function AnnouncementPage() {

    const navigate = useNavigate();
    const [data, setData] = React.useState([]);
    const [tableParams, setTableParams] = useState({
        pagination: {
            current: 1,
            pageSize: 30
        },
    });

    const [openDrawer, setOpenDrawer] = useState(false);
    const [clickedAnnouncementId, setClickedAnnouncementId] = useState(1);

    useEffect(() => {
        fetchData();
    }, []);

    const showDrawer = (id) => {
        setClickedAnnouncementId(id);
        setOpenDrawer(true);
    };


    const onClose = () => {
        setOpenDrawer(false);
    };

    const fetchData = async () => {
        const response = await AnnouncementApi.getAnnouncements(tableParams.pagination.current - 1, tableParams.pagination.pageSize);
        setData(response.data['data']);
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
            title: "ID",
            dataIndex: "id",
        }, {
            title: "제목",
            dataIndex: "title",
        }, {
            title: '작성일',
            dataIndex: 'createdAt',
            render: (text, record) => (
                <span>{new Date(record.createdAt).toLocaleString()}</span>
            )
        }, {
            title: '상세보기',
            dataIndex: 'id',
            render: (text, record) => (
                <>
                    <a onClick={() => {
                        showDrawer(record.id)
                    }}>More</a>
                    <br/>
                </>
            )
        }
    ]

    return (
        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>공지사항 관리</h3>
                <p style={{color: 'gray'}}>공지사항을 추가하거나 수정할 수 있습니다.</p>
                <div>
                    <Button type="primary" size='default'
                            style={{marginTop: '20px', marginBottom: '10px', float: 'right'}}
                            onClick={() => {
                                navigate("/announcement/create")
                            }}>작성하기</Button>
                </div>

                <Table
                    dataSource={data}
                    columns={columns}
                />

                <Drawer title="공지사항 콘텐츠" placement="right" onClose={onClose} open={openDrawer}>
                    <AnnouncementDetailPage announcementId={clickedAnnouncementId}/>
                </Drawer>

            </BasicLayout>
        </>
    )
}


export default AnnouncementPage;