import React, {useState} from "react";
import {Button, Empty, Input, message, Table, Tag} from 'antd';
import axios from "axios";
import {useNavigate} from 'react-router-dom';
import BasicLayout from "../../layout/BasicLayout";


const {Search} = Input;

function SearchDropMusic() {
    const navigate = useNavigate();
    const [messageApi, contextHolder] = message.useMessage();
    const [search, setSearch] = useState("");
    const [songs, setSongs] = useState([]);
    const [selectedRowKeys, setSelectedRowKeys] = useState([]);
    const [selectedSong, setSelectedSong] = useState(null);

    const onChange = (e) => {
        setSearch(e.target.value)
    }

    const onClickDrop = () => {
        if (selectedRowKeys.length > 0) {
            console.log('선택한 음악 정보:', selectedSong);
            navigate('/drop-music/details', {state: {selectedSong}});
        }
    };


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
        console.log('selectedRowKeys changed: ', newSelectedRowKeys);
        setSelectedRowKeys(newSelectedRowKeys);
        setSelectedSong(songs[newSelectedRowKeys - 1]);
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
                <h3 style={{marginTop: '20px', marginBottom: '10px'}}>음악 검색</h3>
                <Search placeholder="Music" value={search} onChange={onChange} onSearch={onClickSearch} enterButton/>
                {songs.length > 0 ? (
                    <div>
                        <Button type="primary" size='default' disabled={!hasSelected} onClick={onClickDrop}
                                style={{marginTop: '20px', marginBottom: '10px', float: 'right'}}>드랍하기</Button>
                        <Table rowSelection={{type: "radio", ...rowSelection}} columns={columns} dataSource={songs}/>
                    </div>
                ) : (
                    <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} style={{marginTop: '100px'}}/>
                )
                }
            </BasicLayout>
        </div>

    )
}


export default SearchDropMusic;
