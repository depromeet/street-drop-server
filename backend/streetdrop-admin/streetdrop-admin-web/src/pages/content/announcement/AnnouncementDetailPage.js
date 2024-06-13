import AnnouncementApi from "../../../api/domain/announcement/AnnouncementApi";
import React, {useEffect} from "react";
import MDEditor from '@uiw/react-md-editor';
import {Space} from "antd";

function AnnouncementDetailPage({announcementId}) {

    const [value, setValue] = React.useState("");
    const [title, setTitle] = React.useState("");

    useEffect(() => {
        fetchAnnouncementDetail(announcementId);
    }, [announcementId]);

    const fetchAnnouncementDetail = async (id) => {
        const response = await AnnouncementApi.getAnnouncement(id);
        setTitle(response.data["title"])
        setValue(response.data["content"]);
    }

    return (
        <div>

            <h2>제목</h2>
            <div style={{border: '1px solid #d9d9d9', padding: '10px', borderRadius: '4px'}}>
                {title}
            </div>

            <Space/>
            <h2>내용</h2>
            <div style={{border: '1px solid #d9d9d9', padding: '10px', borderRadius: '4px'}}>
                <MDEditor.Markdown source={value}
                                   style={{whiteSpace: 'pre-wrap'}}/>
            </div>
        </div>
    );
}

export default AnnouncementDetailPage;