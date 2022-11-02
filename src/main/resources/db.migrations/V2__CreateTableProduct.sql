CREATE TABLE product
(
    id         BIGSERIAL NOT NULL,
    warehouse  BIGINT NOT NULL,
    article    VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    priceLastPurchase VARCHAR(255) NOT NULL,
    priceLastSale VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT fk_product_warehouse FOREIGN KEY (warehouse) REFERENCES warehouse;