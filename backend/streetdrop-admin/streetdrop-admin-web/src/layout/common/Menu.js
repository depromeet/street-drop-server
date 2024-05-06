import React from 'react';
import {useNavigate} from 'react-router-dom';
import {Menu} from 'antd';
import MENU_ITEMS from "../../constant/MenuItem";
import SubMenu from "antd/es/menu/SubMenu";

const MenuComponent = () => {
    const navigate = useNavigate();

    return (
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['dashboard']}>
            {MENU_ITEMS.map(item =>
                item.children ? (
                    <SubMenu key={item.key} icon={item.icon} title={item.title}>
                        {item.children.map(childItem => (
                            <Menu.Item key={childItem.key} onClick={() => navigate(childItem.link)}>
                                {childItem.title}
                            </Menu.Item>
                        ))}
                    </SubMenu>
                ) : (
                    <Menu.Item key={item.key} icon={item.icon} onClick={() => navigate(item.link)}>
                        {item.title}
                    </Menu.Item>
                )
            )}
        </Menu>
    );
};

export default MenuComponent;