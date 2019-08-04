CREATE DATABASE programming_school
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT,
    email    VARCHAR(255) UNIQUE,
    username VARCHAR(255),
    password VARCHAR(255),

    PRIMARY KEY (id)
);


CREATE TABLE user_group
(
    id   INT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE exercise
(
    id          INT AUTO_INCREMENT,
    title       VARCHAR(255),
    description TEXT,
    PRIMARY KEY (id)
);


CREATE TABLE solution
(
    id           INT AUTO_INCREMENT,
    created      DATETIME,
    updated      DATETIME,
    description  TEXT,
    exercise_id INT(11),
    users_id     INT(11),
    PRIMARY KEY (id),
    FOREIGN KEY (exercise_id) REFERENCES exercise (id),
    FOREIGN KEY (users_id) REFERENCES users (id)
);

ALTER TABLE users
    ADD COLUMN user_group_id INT(11);
ALTER TABLE users
    ADD FOREIGN KEY (user_group_id) REFERENCES user_group (id);