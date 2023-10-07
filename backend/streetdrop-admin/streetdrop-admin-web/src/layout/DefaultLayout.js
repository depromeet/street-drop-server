import {Layout} from "antd";
import CommonHeader from "./common/CommonHeader";
import Sider from "antd/es/layout/Sider";
import MenuComponent from "./common/Menu";
import React from "react";

function DefaultLayout({content}) {

    return (
        <>
            <CommonHeader/>
            <Layout style={{minHeight: '100vh'}}>
                <Sider>
                    <MenuComponent/>
                </Sider>
                {content}
            </Layout>
        </>
    )
}

export default DefaultLayout;