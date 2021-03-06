DROP SCHEMA IF EXISTS groups;
CREATE SCHEMA groups;
USE groups;

CREATE TABLE groups (
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    group_admin BIGINT,
    PRIMARY KEY(id)
);

CREATE TABLE groups_members (
	id BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT,
    group_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(group_id) REFERENCES groups(id)
);