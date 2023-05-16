DROP TABLE IF EXISTS village_area;
DROP TABLE IF EXISTS city_area;
DROP TABLE IF EXISTS state_area;
CREATE TABLE state_area
(
    state_id   BIGINT       NOT NULL,
    state_name VARCHAR(255) NOT NULL,
    state_code INT          NOT NULL,
    version    INT          NOT NULL,
    PRIMARY KEY (state_id)
);
CREATE TABLE city_area
(
    city_id   BIGINT       NOT NULL,
    city_name VARCHAR(255) NOT NULL,
    city_code INT          NOT NULL,
    version   INT          NOT NULL,
    state_id  BIGINT       NOT NULL,
    PRIMARY KEY (city_id),
    FOREIGN KEY (state_id) REFERENCES state_area (state_id)
);
CREATE TABLE village_area
(
    village_id           BIGINT       NOT NULL,
    village_name         VARCHAR(255) NOT NULL,
    village_code         INT          NOT NULL,
    version              INT          NOT NULL,
    city_id              BIGINT       NOT NULL,
    village_polygon      MULTIPOLYGON NOT NULL,
    village_center_point POINT NULL,
    PRIMARY KEY (village_id),
    FOREIGN KEY (city_id) REFERENCES city_area (city_id),
    SPATIAL              INDEX village_polygon_idx (village_polygon)
);