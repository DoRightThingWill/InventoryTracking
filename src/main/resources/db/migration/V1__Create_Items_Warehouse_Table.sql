CREATE TABLE IF NOT EXISTS items
(
    id                 BigInt AUTO_INCREMENT PRIMARY KEY,
    item_name          VARCHAR(500) NOT NULL,
    available_quantity BigInt       NOT NULL,
    max_quantity       BigInt       NOT NULL,
    cost               DOUBLE       NOT NULL,
    price              DOUBLE       NOT NULL,
    vendor             VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS warehouses
(
    id           BigInt AUTO_INCREMENT PRIMARY KEY,
    address      VARCHAR(500) NOT NULL,
    phone_number VARCHAR(30)  NOT NULL
);

CREATE TABLE IF NOT EXISTS items_warehouses
(
    item_id      BigInt NOT NULL,
    warehouse_id BigInt NOT NULL,
    FOREIGN KEY (item_id) references items (id),
    FOREIGN KEY (warehouse_id) references warehouses (id)
);

INSERT INTO warehouses (address, phone_number)
VALUES ('10201 Tanner Bridge Rd Henley, Missouri(MO), 65040', '(573) 496-3106'),
       ('1013 S 1040th E Springville, Utah(UT), 84663', '(801) 491-7665');

INSERT INTO items (item_name, available_quantity, max_quantity, cost, price, vendor)
VALUES ('Tire', 200, 2000, 215.2, 310.2, 'Blue Tire'),
       ('Table', 100, 2000, 25.2, 100.2, 'IKEA'),
       ('Truck hood', 150, 3000, 568.5, 700.5, 'VOLVO');

INSERT INTO items_warehouses (item_id, warehouse_id)
VALUES (1, 1),
       (2, 1),
       (3, 1);

