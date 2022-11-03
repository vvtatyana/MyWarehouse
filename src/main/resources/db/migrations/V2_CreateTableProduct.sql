CREATE TABLE IF NOT EXISTS product
(
    id         BIGSERIAL NOT NULL,
    warehouse  BIGINT NOT NULL,
    article    VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    price_last_purchase VARCHAR(255) NOT NULL,
    price_last_sale VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT fk_product_warehouse FOREIGN KEY (warehouse) REFERENCES warehouse;