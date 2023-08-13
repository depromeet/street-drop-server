create table album_cover
(
    album_cover_id  bigint auto_increment
        primary key,
    album_image     varchar(255) not null,
    album_thumbnail varchar(255) not null
);

create table artist
(
    artist_id bigint auto_increment
        primary key,
    name      varchar(255) not null
);

create table album
(
    album_id       bigint auto_increment
        primary key,
    name           varchar(255) not null,
    album_cover_id bigint       not null,
    artist_id      bigint       not null,
    constraint fk_album_album_cover_id
        foreign key (album_cover_id) references album_cover (album_cover_id),
    constraint fk_album_artist_id
        foreign key (artist_id) references artist (artist_id)
);

create table block_user
(
    block_id    bigint auto_increment
        primary key,
    created_at  datetime(6) not null,
    modified_at datetime(6) not null,
    blocked_id  bigint      not null,
    blocker_id  bigint      not null
);

create table default_nick_name
(
    id             bigint auto_increment
        primary key,
    post_nick_name varchar(5) not null,
    pre_nick_name  varchar(5) not null
);

create table genre
(
    genre_id bigint auto_increment
        primary key,
    name     varchar(255) not null
);

create table item_claim
(
    item_claim_id bigint auto_increment
        primary key,
    created_at    datetime(6)  not null,
    modified_at   datetime(6)  not null,
    item_id       bigint       not null,
    reason        varchar(255) not null,
    status        varchar(255) not null,
    user_id       bigint       not null
);

create table item_like
(
    item_like_id bigint auto_increment
        primary key,
    created_at   datetime(6) not null,
    modified_at  datetime(6) not null,
    item_id      bigint      null,
    user_id      bigint      null
);

create table song
(
    song_id  bigint auto_increment
        primary key,
    name     varchar(255) not null,
    album_id bigint       not null,
    constraint fk_song_album_id
        foreign key (album_id) references album (album_id)
);

create table song_genre
(
    song_genre_id bigint auto_increment
        primary key,
    genre_id      bigint not null,
    song_id       bigint not null,
    constraint fk_song_genre_song_id
        foreign key (song_id) references song (song_id),
    constraint fk_song_genre_genre_id
        foreign key (genre_id) references genre (genre_id)
);

create table state_area
(
    state_id   bigint auto_increment
        primary key,
    state_code int          not null,
    state_name varchar(255) not null,
    version    int          not null
);

create table city_area
(
    city_id   bigint auto_increment
        primary key,
    city_code int          not null,
    city_name varchar(255) not null,
    version   int          not null,
    state_id  bigint       not null,
    constraint fk_city_area_state_id
        foreign key (state_id) references state_area (state_id)
);

create table users_level
(
    user_level_id bigint auto_increment
        primary key,
    description   varchar(255) not null,
    image         varchar(255) not null,
    name          varchar(255) not null
);

create table users
(
    user_id       bigint auto_increment
        primary key,
    created_at    datetime(6)  not null,
    modified_at   datetime(6)  not null,
    idfv          varchar(100) not null,
    music_app     varchar(255) not null,
    nickname      varchar(20)  not null,
    user_level_id bigint       not null,
    constraint fk_users_user_level_id
        foreign key (user_level_id) references users_level (user_level_id)
);

create table item
(
    item_id        bigint auto_increment
        primary key,
    created_at     datetime(6)  not null,
    modified_at    datetime(6)  not null,
    content        varchar(500) not null,
    album_cover_id bigint       not null,
    song_id        bigint       not null,
    user_id        bigint       not null,
    constraint fk_item_user_id
        foreign key (user_id) references users (user_id),
    constraint fk_item_song_id
        foreign key (song_id) references song (song_id),
    constraint fk_item_album_cover_id
        foreign key (album_cover_id) references album_cover (album_cover_id)
);

create table village_area
(
    village_id           bigint auto_increment
        primary key,
    version              int          not null,
    village_center_point point        null,
    village_code         int          not null,
    village_name         varchar(255) not null,
    village_polygon      multipolygon not null,
    city_id              bigint       not null,
    constraint fk_village_area_city_id
        foreign key (city_id) references city_area (city_id)
);

create table item_location
(
    item_location_id bigint auto_increment
        primary key,
    created_at       datetime(6)  not null,
    modified_at      datetime(6)  not null,
    name             varchar(255) null,
    point            point        null,
    item_id          bigint       not null,
    village_id       bigint       not null,
    constraint fk_item_location_item_id
        foreign key (item_id) references item (item_id),
    constraint fk_item_location_village_id
        foreign key (village_id) references village_area (village_id)
);