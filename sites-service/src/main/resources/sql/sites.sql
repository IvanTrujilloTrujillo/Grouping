DROP SCHEMA IF EXISTS sites;
CREATE SCHEMA sites;
USE sites;

CREATE TABLE site (
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    group_id BIGINT,
    map_url VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE review (
	id BIGINT AUTO_INCREMENT NOT NULL,
    site_id BIGINT,
    user_id BIGINT,
    rating INT,
    comment VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY(site_id) REFERENCES site(id)
);