DROP SCHEMA IF EXISTS users;
CREATE SCHEMA users;
USE users;

CREATE TABLE user (
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY(id)
);

/*CREATE TABLE role (
	id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    user_id BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);*/