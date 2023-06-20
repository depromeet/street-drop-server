import React from 'react';
import {Button, Result} from 'antd';
import {useNavigate} from "react-router-dom";
import BasicLayout from "../../layout/BasicLayout";

function DropMusicSuccess() {
    const navigate = useNavigate();
    const clickGoBack = () => {
        navigate('/drop-music');
    }

    return (<>
        <BasicLayout>
            <Result
                status="success"
                title="성공적으로 드랍했습니다"
                extra={[
                    <Button type="primary" key="console" onClick={clickGoBack}>
                        Go Back
                    </Button>,
                ]}
            />
        </BasicLayout>
    </>);

}

export default DropMusicSuccess;
