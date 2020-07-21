BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price numeric(10,2));
INSERT INTO products (name, price) VALUES
('coffe', 100),
('tea', 80),
('ice tea', 90);

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO buyers (name) VALUES
('Василий'),
('Михаил'),
('Елена');

DROP TABLE IF EXISTS buyers_products CASCADE;
CREATE TABLE buyers_products (buyers_id bigint, products_id bigint, FOREIGN KEY (buyers_id) REFERENCES buyers (id), FOREIGN KEY (products_id) REFERENCES products (id));
INSERT INTO buyers_products VALUES
(1,1),
(1,2),
(2,2),
(3,1),
(3,3);
COMMIT;