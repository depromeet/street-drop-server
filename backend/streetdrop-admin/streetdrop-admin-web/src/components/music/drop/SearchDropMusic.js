import React, {useState} from "react";
import {Badge, Button, Drawer, Empty, Input, message, Space, Table, Tag} from 'antd';
import axios from "axios";
import {useNavigate} from 'react-router-dom';
import BasicLayout from "../../layout/BasicLayout";
import {ShoppingCartOutlined} from '@ant-design/icons';
import DropCartView from "./DropCartView";


const {Search} = Input;

function SearchDropMusic() {
    const navigate = useNavigate();
    const [messageApi, contextHolder] = message.useMessage();
    const [search, setSearch] = useState("");
    const [songs, setSongs] = useState([]);
    const [selectedRowKeys, setSelectedRowKeys] = useState([]);
    const [selectedSongList, setSelectedSongList] = useState([]);
    const [drawerOpen, setDrawerOpen] = useState(false);

    const onChange = (e) => {
        setSearch(e.target.value)
    }

    /*
    * Function for Single Drop Music
    */
    const onClickSingleDrop = () => {
        if (selectedRowKeys.length > 0) {
            setSelectedSongList([...selectedSongList, songs[selectedRowKeys[0] - 1]]);
            if (selectedSongList.length > 0) {
                // 마지막 요소 선택
                navigate('/drop-music/details', {state: {selectedSong: selectedSongList[selectedSongList.length - 1]}});
            }
        }
    };


    /*
    * Function for Drop Music To Cart
    */
    const onClickDropToCart = () => {
        if (selectedRowKeys.length > 0) {
            messageApi.open({
                type: 'success',
                content: "장바구니에 추가되었습니다.",
            })
            setSelectedSongList([...selectedSongList, songs[selectedRowKeys[0] - 1]]);
        }
    }


    const onClickSearch = () => {
        fetchData();
    }

    const columns = [
        {
            title: '앨범 이미지',
            dataIndex: 'albumThumbnailImage',
            render: (text) => <img src={text} alt="album" style={{width: '100px', height: '100px'}}/>,
        },
        {
            title: '노래 제목',
            dataIndex: 'songName',
            render: (text) => <a href={"https://www.youtube.com/results?search_query=" + text} target='_blank'
                                 rel="noreferrer"> {text}</a>
        },
        {
            title: '아티스트',
            dataIndex: 'artistName',
        },
        {
            title: 'duration',
            dataIndex: 'durationTime',
        },
        {
            title: '장르',
            dataIndex: 'genre',
            render: (text) => text.map((genre) => <Tag color="blue" key={genre}>{genre}</Tag>)
        }
    ]


    /*
     * Get Data From Search Server
     */
    const fetchData = () => {
        setSongs([]);
        axios.get('/search/music', {
            params: {
                keyword: search
            }
        })
            .then(response => {
                messageApi.open({
                    type: 'success',
                    content: "음악 검색에 성공했습니다.",
                })
                const data = response.data.data;
                const newData = data.map((item, index) => {
                    const key = index + 1;
                    return {...item, key};
                });

                setSongs(newData);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                messageApi.open({
                    type: 'error',
                    content: "음악 검색에 실패했습니다. 다시 시도해주세요.",
                });
            });
    }

    const onSelectChange = (newSelectedRowKeys) => {
        setSelectedRowKeys(newSelectedRowKeys);
    };

    const rowSelection = {
        selectedRowKeys,
        onChange: onSelectChange,
    };

    const hasSelected = selectedRowKeys.length > 0;


    return (

        <div>
            <BasicLayout>
                {contextHolder}
                <h1 style={{marginBottom: '5px'}}>Drop Music Admin</h1>
                <p style={{color: 'gray'}}>스트릿 드랍 아이템 관리자 페이지입니다. 드랍 아이템을 추가하거나 삭제할 수 있습니다.</p>
                <div style={{
                    float: 'right',
                }}>
                    <Badge count={selectedSongList.length}
                           showZero={true}
                           size={"small"}
                           offset={[-10, 10]}
                           style={{
                               backgroundColor: '#1777ff',
                               fontSize: '6px',
                           }}
                    >
                        <Button
                            type="text"
                            icon={<ShoppingCartOutlined/>}
                            style={{
                                fontSize: '24px',
                                width: 70,
                                height: 50,
                                display: 'inline-block',
                                float: 'right'
                            }}
                            onClick={() => setDrawerOpen(true)}
                        />
                    </Badge>
                </div>


                <h3 style={{marginTop: '20px', marginBottom: '10px'}}>음악 검색</h3>
                <Search placeholder="Music" value={search} onChange={onChange} onSearch={onClickSearch} enterButton/>
                {songs.length > 0 ? (
                    <div>
                        <Space style={{marginTop: '20px', marginBottom: '10px', float: 'right'}}>
                            <Button size='default' disabled={!hasSelected} onClick={onClickDropToCart}>
                                장바구니 담기</Button>
                            <Button type="primary" size='default' disabled={!hasSelected}
                                    onClick={onClickSingleDrop}>드랍하기</Button>
                        </Space>
                        <Table rowSelection={{type: "radio", ...rowSelection}} columns={columns} dataSource={songs}/>
                    </div>
                ) : (
                    <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} style={{marginTop: '100px'}}/>
                )
                }
                <Drawer
                    title="드랍음악 카트"
                    placement="right"
                    width={500}
                    onClose={() => setDrawerOpen(false)}
                    open={drawerOpen}
                    extra={
                        <Space>
                            <Button onClick={() => setSelectedSongList([])}>지우기</Button>
                            <Button type="primary" onClick={console.log("clicked")}>모두 드랍하기</Button>
                        </Space>
                    }
                >
                    <DropCartView songs={selectedSongList}/>
                </Drawer>
            </BasicLayout>
        </div>

    )
}


export default SearchDropMusic;
