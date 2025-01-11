CREATE TABLE IF NOT EXISTS orders (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
product_name VARCHAR(255),
brand VARCHAR(255),
article VARCHAR,
price FLOAT,
quantity INTEGER,
dealer VARCHAR(255),
info VARCHAR(255),
manager VARCHAR(255),
delivery_time DATE,
create_time DATE
);