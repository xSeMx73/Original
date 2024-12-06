CREATE TABLE IF NOT EXISTS clients (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name  VARCHAR(255) NOT NULL,
last_name  VARCHAR(255),
nick_name  VARCHAR(255),
phone  BIGINT,
email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS transport (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
brand_name VARCHAR(255),
model VARCHAR(255),
add_inform VARCHAR(510),
vin VARCHAR(17),
owner_id BIGINT REFERENCES clients (id) NOT NULL,
gos_number VARCHAR(10),
year int
);
