CREATE TABLE IF NOT EXISTS orders
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_name  VARCHAR(255),
    brand         VARCHAR(255),
    article       VARCHAR(128),
    price         FLOAT,
    quantity      INTEGER,
    dealer        VARCHAR(255),
    info          VARCHAR(255),
    manager       VARCHAR(255),
    delivery_time DATE,
    create_time   TIMESTAMP(0),
    is_delivered   BOOLEAN
);

CREATE TABLE IF NOT EXISTS pend_orders
(
    id           BIGINT PRIMARY KEY,
    article      VARCHAR(32),
    product_name VARCHAR(255),
    brand        VARCHAR(32),
    dealer       VARCHAR(32),
    price        FLOAT,
    input_data   DATE,
    return_data  DATE,
    quantity     INTEGER,
    manager      VARCHAR(255),
    info         VARCHAR(255),
    reason       VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users_chat_id
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    chat_id   bigint unique ,
    user_login VARCHAR(32) unique ,
    user_name VARCHAR(32)
);

