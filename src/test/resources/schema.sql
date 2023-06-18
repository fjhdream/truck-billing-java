CREATE TABLE "user"
(
    id         VARCHAR(128) PRIMARY KEY,
    user_name  VARCHAR(128) NOT NULL,
    avatar_url text
);

CREATE TYPE role_type AS ENUM ('OWNER', 'ADMIN', 'DRIVER');
CREATE TABLE role
(
    id      uuid PRIMARY KEY,
    user_id VARCHAR(128) NOT NULL REFERENCES "user" (id),
    type    role_type    NOT NULL,
    UNIQUE (user_id, "type")
);

CREATE TABLE team
(
    id        uuid PRIMARY KEY,
    team_name VARCHAR(128) NOT NULL,
    user_id   VARCHAR(128) NOT NULL REFERENCES "user" (id)
);

CREATE TYPE use_status AS ENUM ('INUSE', 'DELETED');

CREATE TABLE team_car
(
    id               uuid PRIMARY KEY,
    team_id          uuid         NOT NULL REFERENCES team (id),
    car_plate_number VARCHAR(128) NOT NULL,
    status           use_status   NOT NULL
);

CREATE TABLE team_driver
(
    id      uuid PRIMARY KEY,
    user_id VARCHAR(128) NOT NULL REFERENCES "user" (id),
    team_id uuid         NOT NULL REFERENCES team (id),
    status  use_status   NOT NULL,
    UNIQUE (user_id, team_id)
);

CREATE TYPE item_type as ENUM ('BASIC', 'COSTOM', 'DEFAULT');
CREATE TABLE item
(
    id       uuid PRIMARY KEY,
    type     item_type    NOT NULL,
    name     VARCHAR(128) NOT NULL,
    team_id  uuid REFERENCES team (id),
    icon_url text
);

CREATE TYPE billing_status as ENUM ('INITIALIZED', 'STARTED', 'ENDED', 'CLOSED');

CREATE TABLE billing
(
    id             uuid PRIMARY KEY,
    name           VARCHAR(128)   NOT NULL,
    team_id        uuid REFERENCES team (id),
    start_time     TIMESTAMP,
    end_time       TIMESTAMP,
    status         billing_status NOT NULL,
    commodity      VARCHAR(128),
    start_location VARCHAR(128),
    end_location   VARCHAR(128),
    start_money    money,
    end_money      money,
    team_car_id    uuid REFERENCES team_car (id)
);

CREATE TABLE billing_item
(
    id         uuid PRIMARY KEY,
    billing_id uuid REFERENCES billing (id),
    cost       money CHECK (cost > 0 :: money) NOT NULL,
    item_id    uuid REFERENCES item (id),
    time       TIMESTAMP                       NOT NULL
);

CREATE CAST (varchar AS billing_status) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS item_type) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS role_type) WITH INOUT AS IMPLICIT;
CREATE CAST (varchar AS use_status) WITH INOUT AS IMPLICIT;