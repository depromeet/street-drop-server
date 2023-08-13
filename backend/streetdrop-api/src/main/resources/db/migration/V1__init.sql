create table if not exists album_cover
(
    album_cover_id  bigint auto_increment
        primary key,
    created_at      datetime(6)  null,
    modified_at     datetime(6)  null,
    album_image     varchar(255) null,
    album_thumbnail varchar(255) null
)
    charset = utf8mb3;

create table if not exists artist
(
    artist_id   bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    name        varchar(255) null
)
    charset = utf8mb3;

create table if not exists album
(
    album_id       bigint auto_increment
        primary key,
    created_at     datetime(6)  null,
    modified_at    datetime(6)  null,
    name           varchar(255) null,
    album_cover_id bigint       null,
    artist_id      bigint       null,
    constraint FK7otyksnaj781l0b2r8570vefc
        foreign key (album_cover_id) references streetdrop.album_cover (album_cover_id),
    constraint FKmwc4fyyxb6tfi0qba26gcf8s1
        foreign key (artist_id) references streetdrop.artist (artist_id)
)
    charset = utf8mb3;

create table if not exists block_user
(
    block_id    bigint auto_increment
        primary key,
    blocked_id  bigint      null,
    blocker_id  bigint      null,
    created_at  datetime(6) not null,
    modified_at datetime(6) not null
);

create table if not exists default_nick_name
(
    id             bigint auto_increment
        primary key,
    post_nick_name varchar(5) null,
    pre_nick_name  varchar(5) null
);

create table if not exists genre
(
    genre_id    bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    name        varchar(255) null
)
    charset = utf8mb3;

create table if not exists item_claim
(
    item_claim_id bigint auto_increment
        primary key,
    created_at    datetime(6)  null,
    modified_at   datetime(6)  null,
    item_id       bigint       null,
    user_id       bigint       null,
    reason        varchar(255) not null,
    status        varchar(255) null
);

create table if not exists member
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) not null,
    name     varchar(255) not null,
    part     varchar(255) not null,
    password varchar(255) not null,
    username varchar(255) not null
);

create table if not exists song
(
    song_id     bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    name        varchar(255) null,
    album_id    bigint       null,
    constraint FKrcjmk41yqj3pl3iyii40niab0
        foreign key (album_id) references streetdrop.album (album_id)
)
    charset = utf8mb3;

create table if not exists song_genre
(
    song_genre_id bigint auto_increment
        primary key,
    created_at    datetime(6) null,
    modified_at   datetime(6) null,
    genre_id      bigint      null,
    song_id       bigint      null,
    constraint FK1ssu87dg5vsdxpmyjqqc42if3
        foreign key (song_id) references streetdrop.song (song_id),
    constraint FKmpuht870e976moxtxywrfngcr
        foreign key (genre_id) references streetdrop.genre (genre_id)
)
    charset = utf8mb3;

create table if not exists state_area
(
    state_id   bigint auto_increment
        primary key,
    state_code int          not null,
    state_name varchar(255) not null,
    version    int          not null
)
    charset = utf8mb3;

create table if not exists city_area
(
    city_id   bigint auto_increment
        primary key,
    city_code int          not null,
    city_name varchar(255) not null,
    version   int          not null,
    state_id  bigint       null,
    constraint FKrf563rtlt9al5bw1lekn18du1
        foreign key (state_id) references streetdrop.state_area (state_id)
)
    charset = utf8mb3;

create table if not exists users
(
    user_id       bigint auto_increment
        primary key,
    created_at    datetime(6)  null,
    modified_at   datetime(6)  null,
    idfv          varchar(100) null,
    nickname      varchar(20)  null,
    music_app     varchar(255) null,
    user_level_id bigint       null
)
    charset = utf8mb3;

create table if not exists item
(
    item_id        bigint auto_increment
        primary key,
    created_at     datetime(6)                             null,
    modified_at    datetime(6)                             null,
    content        varchar(500) collate utf8mb4_general_ci null,
    album_cover_id bigint                                  null,
    song_id        bigint                                  null,
    user_id        bigint                                  null,
    constraint FKc4s174l330le17rblwgyjawev
        foreign key (user_id) references streetdrop.users (user_id),
    constraint FKf9j8ocdanjs7a48djb3aqd0en
        foreign key (song_id) references streetdrop.song (song_id),
    constraint FKip7vjlqnw0rt125paig31ni3
        foreign key (album_cover_id) references streetdrop.album_cover (album_cover_id)
)
    charset = utf8mb3;

create table if not exists item_like
(
    item_like_id bigint auto_increment
        primary key,
    created_at   datetime(6) null,
    modified_at  datetime(6) null,
    item_id      bigint      null,
    user_id      bigint      null,
    constraint FK943xouml6m8ikrxn862iq9qhj
        foreign key (item_id) references streetdrop.item (item_id),
    constraint FKIc81jr021caudusp3hcfjr
        foreign key (user_id) references streetdrop.users (user_id)
)
    charset = utf8mb3;

create index FK943xouml6m8ikrxn862iq9qhj_idx
    on streetdrop.item_like (item_id);

create index FKIc81jr021caudusp3hcfjr_idx
    on streetdrop.item_like (user_id);

create table streetdrop.users_level
(
    level_id      bigint auto_increment
        primary key,
    description   varchar(255) not null,
    image         varchar(255) not null,
    name          varchar(255) not null,
    user_level_id bigint       not null
);

create table if not exists village_area
(
    village_id           bigint auto_increment
        primary key,
    version              int          not null,
    village_center_point point        null,
    village_code         int          not null,
    village_name         varchar(255) not null,
    village_polygon      multipolygon not null,
    city_id              bigint       null,
    constraint FKadtch2u4brlxykg1e03xdkyiw
        foreign key (city_id) references streetdrop.city_area (city_id)
);

create table if not exists item_location
(
    item_location_id bigint auto_increment
        primary key,
    created_at       datetime(6)  null,
    modified_at      datetime(6)  null,
    name             varchar(255) null,
    point            point        null,
    item_id          bigint       null,
    village_id       bigint       null,
    constraint FK50oxdirrbr9uk55y7xlrq6jmv
        foreign key (item_id) references streetdrop.item (item_id),
    constraint FKlnnflryk5f1y35ismb0qpnepy
        foreign key (village_id) references streetdrop.village_area (village_id)
)
    charset = utf8mb3;

create spatial index idx_coordinates
    on streetdrop.village_area (village_polygon);