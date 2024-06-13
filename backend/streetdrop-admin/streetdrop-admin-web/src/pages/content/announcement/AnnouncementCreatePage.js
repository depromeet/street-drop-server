import BasicLayout from "../../../layout/BasicLayout";
import {Button, Col, Input, Row, Space} from "antd";
import MDEditor from "@uiw/react-md-editor";
import React from "react";
import AnnouncementApi from "../../../api/domain/announcement/AnnouncementApi";
import {useNavigate} from "react-router-dom";

function AnnouncementCreatePage() {
    const navigate = useNavigate();

    const [content, setContent] = React.useState("");
    const [title, setTitle] = React.useState("");

    const createAnnouncement = async () => {
        const response = await AnnouncementApi.createAnnouncement(title, content);
        if (response.status === 200) {
            navigate("/announcement")
        }


    }

    return (

        <>
            <BasicLayout>
                <h3 style={{marginBottom: '10px'}}>공지사항 등록</h3>
                <p style={{color: 'gray'}}>공지사항을 추가하거나 수정할 수 있습니다.</p>
                <h3>제목</h3>

                <Input
                    placeholder="제목을 입력해주세요"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <br/>

                <h3>내용</h3>

                <MDEditor
                    value={content}
                    onChange={setContent}
                    textareaProps={{
                        placeholder: '내용을 입력해주세요',
                    }}
                />



                <br/>
                <br/>
                <Row>
                    <Col span={8}/>
                    <Col span={4}>
                        <Button type="primary" block
                                onClick={() => {
                                    createAnnouncement()
                                }}
                        >등록</Button>
                    </Col>
                    <Space/>
                    <Col span={4}>
                        <Button block
                                onClick={() => {
                                    navigate("/announcement")
                                }}
                        >취소</Button>
                    </Col>
                    <Col span={8}/>
                </Row>


                <Space/>

            </BasicLayout>
        </>
    )
}

export default AnnouncementCreatePage;