-- artist table sample data
INSERT INTO artist (created_at, modified_at, name)
VALUES
    ('2021-01-01 12:00:00', '2023-05-16 12:00:00', 'Beyonce'),
    ('2022-03-15 09:30:00', '2023-05-16 12:00:00', 'Ed Sheeran'),
    ('2023-04-20 16:45:00', '2023-05-16 12:00:00', 'Taylor Swift');

-- album_cover table sample data
INSERT INTO album_cover (created_at, modified_at, album_image, album_thumbnail)
VALUES
    ('2021-02-10 14:20:00', '2023-05-16 12:00:00', 'beyonce_album.jpg', 'beyonce_album_thumb.jpg'),
    ('2022-06-05 18:10:00', '2023-05-16 12:00:00', 'edsheeran_album.jpg', 'edsheeran_album_thumb.jpg'),
    ('2023-03-25 11:30:00', '2023-05-16 12:00:00', 'taylorswift_album.jpg', 'taylorswift_album_thumb.jpg');

-- album table sample data
INSERT INTO album (created_at, modified_at, name, album_cover_id, artist_id)
VALUES
    ('2021-03-20 09:45:00', '2023-05-16 12:00:00', 'Lemonade', 1, 1),
    ('2022-05-12 16:55:00', '2023-05-16 12:00:00', 'Divide', 2, 2),
    ('2023-04-15 13:20:00', '2023-05-16 12:00:00', '1989', 3, 3);

-- song table sample data
INSERT INTO song (created_at, modified_at, name, album_id)
VALUES
    ('2021-05-30 14:40:00', '2023-05-16 12:00:00', 'Formation', 1),
    ('2022-08-22 17:25:00', '2023-05-16 12:00:00', 'Shape of You', 1),
    ('2023-03-10 10:55:00', '2023-05-16 12:00:00', 'Blank Space', 2);

-- genre table sample data
INSERT INTO genre (created_at, modified_at, name)
VALUES
    ('2021-05-30 14:40:00', '2023-05-16 12:00:00', 'Pop'),
    ('2022-08-22 17:25:00', '2023-05-16 12:00:00', 'Rock'),
    ('2023-03-10 10:55:00', '2023-05-16 12:00:00', 'R&B');

-- song_genre table sample data
INSERT INTO song_genre (created_at, modified_at, genre_id, song_id)
VALUES
    ('2021-05-30 14:40:00', '2023-05-16 12:00:00', 1, 1),
    ('2022-08-22 17:25:00', '2023-05-16 12:00:00', 2, 1),
    ('2023-03-10 10:55:00', '2023-05-16 12:00:00', 2, 2);

-- users table sample data
INSERT INTO users (created_at, modified_at, nickname)
VALUES
    ('2021-04-25 10:30:00', '2023-05-16 12:00:00', 'John Doe'),
    ('2022-07-18 15:50:00', '2023-05-16 12:00:00', 'Jane Smith'),
    ('2023-02-05 11:15:00', '2023-05-16 12:00:00', 'David Johnson');

-- item table sample data
INSERT INTO item (created_at, modified_at, content, album_cover_id, song_id, user_id)
VALUES
    ('2021-06-15 13:15:00', '2023-05-16 12:00:00', 'Item 1', 1, 1, 1),
    ('2022-09-08 11:05:00', '2023-05-16 12:00:00', 'Item 2', 2, 1, 2),
    ('2023-02-12 14:50:00', '2023-05-16 12:00:00', 'Item 3', 3, 2, 3);

-- item_location table sample data
INSERT INTO item_location (created_at, modified_at, name, point, item_id, village_id) VALUES
                                                                                          ('2021-06-15 13:15:00', '2023-05-16 12:00:00', 'Location 1', ST_GeomFromText('POINT(37.5747389 126.9718807)', 4326), 1, 1),
                                                                                          ('2022-09-08 11:05:00', '2023-05-16 12:00:00', 'Location 2', ST_GeomFromText('POINT(37.5892704 126.981028)', 4326), 2, 2),
                                                                                          ('2023-02-12 14:50:00', '2023-05-16 12:00:00', 'Location 3', ST_GeomFromText('POINT(3 3)', 4326), 3, 3);