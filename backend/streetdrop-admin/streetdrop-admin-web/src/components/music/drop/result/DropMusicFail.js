import React from 'react';
import {Button, Result} from 'antd';
import {useNavigate} from "react-router-dom";
import BasicLayout from "../../../layout/BasicLayout";

function DropMusicFail() {
    const navigate = useNavigate();
    const clickGoBack = () => {
        navigate('/drop-music');
    }
    return (<>
        <BasicLayout>
            <Result
                status="error"
                title="드랍에 실패했습니다."
                extra={[
                    <Button type="primary" key="console" onClick={clickGoBack}>
                        Go Back
                    </Button>,
                ]}
            />
        </BasicLayout>
    </>);

}

export default DropMusicFail;
