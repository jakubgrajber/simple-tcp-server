CREATE SEQUENCE app_user_seq;
CREATE TABLE IF NOT EXISTS app_user
(
    id          BIGINT      NOT NULL DEFAULT NEXTVAL('app_user_seq'),
    nick        VARCHAR(40) NOT NULL,
    login       VARCHAR(40) NOT NULL,
    password    VARCHAR(40) NOT NULL,
    insert_time TIMESTAMP   NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);
ALTER SEQUENCE app_user_seq RESTART WITH 1;

CREATE SEQUENCE vehicle_seq;
CREATE TABLE IF NOT EXISTS vehicle
(
    id          BIGINT      NOT NULL DEFAULT NEXTVAL('vehicle_seq'),
    user_id     BIGINT      NOT NULL,
    brand       VARCHAR(40) NOT NULL,
    model       VARCHAR(40) NOT NULL,
    insert_time TIMESTAMP   NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    CONSTRAINT fk_app_user FOREIGN KEY (user_id) REFERENCES app_user (id)
);
ALTER SEQUENCE vehicle_seq RESTART WITH 1;

CREATE SEQUENCE insurance_offer_seq;
CREATE TABLE IF NOT EXISTS insurance_offer
(
    id          BIGINT         NOT NULL DEFAULT NEXTVAL('insurance_offer_seq'),
    vehicle_id  BIGINT         NOT NULL,
    insurer     VARCHAR(40)    NOT NULL,
    price       DECIMAL(13, 2) NOT NULL,
    insert_time TIMESTAMP      NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);
ALTER SEQUENCE insurance_offer_seq RESTART WITH 1;

-- -- -----------------------------------------------------
-- Users
-- --------------------------------------------------------
INSERT INTO app_user(nick, login, password)
VALUES ('James', 'user1', 'test');
INSERT INTO app_user(nick, login, password)
VALUES ('Kirk', 'user2', 'test');
INSERT INTO app_user(nick, login, password)
VALUES ('Robert', 'user3', 'test');
INSERT INTO app_user(nick, login, password)
VALUES ('Lars', 'user4', 'test');

-- -- -----------------------------------------------------
-- Vehicles
-- --------------------------------------------------------
INSERT INTO vehicle(user_id, brand, model)
VALUES (1, 'Ford', 'F100');
INSERT INTO vehicle(user_id, brand, model)
VALUES (1, 'Lincoln', 'Continental');
INSERT INTO vehicle(user_id, brand, model)
VALUES (2, 'Porsche', '911 Carrera 4');
INSERT INTO vehicle(user_id, brand, model)
VALUES (2, 'Jaguar', 'XKE');
INSERT INTO vehicle(user_id, brand, model)
VALUES (3, 'Buick', 'Riviera');
INSERT INTO vehicle(user_id, brand, model)
VALUES (3, 'Chevrolet', 'Camaro SS');
INSERT INTO vehicle(user_id, brand, model)
VALUES (3, 'Audi', 'R8');
INSERT INTO vehicle(user_id, brand, model)
VALUES (4, 'Lamborghini', 'Aventador');

-- -- -----------------------------------------------------
-- Insurance offers
-- --------------------------------------------------------
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (1, 'Alianz', 35043.14);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (1, 'Benefia', 43200.67);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (1, 'Ergo Hestia', 38395.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (2, 'Alianz', 23562.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (2, 'Benefia', 23534.35);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (2, 'Ergo Hestia', 25462.64);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (3, 'Alianz', 27546.46);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (3, 'Benefia', 30351.23);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (3, 'Ergo Hestia', 25643.93);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (4, 'Alianz', 19352.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (4, 'Benefia', 22003.12);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (4, 'Ergo Hestia', 17345.53);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (5, 'Alianz', 12003.56);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (5, 'Benefia', 10353.54);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (5, 'Ergo Hestia', 9465.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (6, 'Alianz', 6543.64);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (6, 'Benefia', 8093.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (6, 'Ergo Hestia', 6432.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (7, 'Alianz', 15235.60);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (7, 'Benefia', 17453.11);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (7, 'Ergo Hestia', 14325.99);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (8, 'Alianz', 76323.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (8, 'Benefia', 5743.13);
INSERT INTO insurance_offer(vehicle_id, insurer, price)
VALUES (8, 'Ergo Hestia', 3146.13);


