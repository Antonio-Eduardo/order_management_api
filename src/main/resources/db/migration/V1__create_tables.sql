CREATE TABLE tb_user(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL ,
    email VARCHAR(30) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);
CREATE TABLE tb_order(
    id BIGSERIAL PRIMARY KEY ,
    moment TIMESTAMP NOT NULL,
    order_status INTEGER NOT NULL,
    client_id BIGINT REFERENCES tb_user(id) ON DELETE CASCADE
);
CREATE TABLE tb_product(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(30) NOT NULL ,
    description VARCHAR(50) NOT NULL ,
    price NUMERIC(19,2) NOT NULL,
    img_url VARCHAR(255)
);
CREATE TABLE tb_category(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(30) NOT NULL
);
CREATE TABLE tb_product_category(
    product_id BIGINT REFERENCES  tb_product(id),
    category_id BIGINT REFERENCES  tb_category(id),
    PRIMARY KEY (product_id,category_id)
);
CREATE TABLE tb_order_item(
    product_id BIGINT REFERENCES tb_product(id),
    order_id BIGINT REFERENCES tb_order(id),
    PRIMARY KEY (product_id,order_id),
    quantity INTEGER NOT NULL,
    price NUMERIC(19,2) NOT NULL
);
