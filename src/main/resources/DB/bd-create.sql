drop table if exists application;
drop table if exists users;
drop table if exists user_role;


CREATE TABLE IF NOT EXISTS user_role
(
    user_role_id    SERIAL PRIMARY KEY,
    user_role_title VARCHAR(31) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    user_id       SERIAL PRIMARY KEY,
    user_role_id  INTEGER,
    user_name     VARCHAR(50),
    user_surname  VARCHAR(50),
    user_email    VARCHAR(100),
    user_password VARCHAR(256),
    account       DECIMAL(8, 2) NOT NULL DEFAULT 0,
    FOREIGN KEY (user_role_id) REFERENCES user_role (user_role_id)
);

CREATE TABLE IF NOT EXISTS application
(
    application_id     SERIAL PRIMARY KEY,
    client_id          INTEGER NOT NULL,
    master_id          INTEGER,
    date TIMESTAMP NOT NULL ,
    completion_status VARCHAR(63) DEFAULT 'NOT_STARTED',
    payment_status VARCHAR(63) DEFAULT 'WAITING_FOR_PAYMENT',
    application_description VARCHAR(255),
    application_price  DECIMAL(8, 2),
    application_review VARCHAR(511),
    FOREIGN KEY (client_id) REFERENCES users (user_id),
    FOREIGN KEY (master_id) REFERENCES users (user_id)
);


INSERT INTO user_role
VALUES (DEFAULT, 'ADMIN'),
       (DEFAULT, 'MANAGER'),
       (DEFAULT, 'MASTER'),
       (DEFAULT, 'CLIENT');

INSERT INTO users
VALUES (DEFAULT, 1, 'admin', 'admin', 'admin@gmail.com', 'd+sYTsy7/EPLsyaYCsD8QKxSfrJQhU4hk7N14rc2A7I=$pFeKrhPXvX8xyVpQ6hhw8ACiAgdooCGneWUx0Ivb1QY=', DEFAULT);




