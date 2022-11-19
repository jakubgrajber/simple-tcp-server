CREATE SEQUENCE app_user_seq;
CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT NOT NULL DEFAULT NEXTVAL ('app_user_seq'),
    nick VARCHAR(40) NOT NULL,
    login VARCHAR(40) NOT NULL,
    password VARCHAR(40) NOT NULL,
    insert_time TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id));
ALTER SEQUENCE app_user_seq RESTART WITH 1;

CREATE SEQUENCE vehicle_seq;
CREATE TABLE IF NOT EXISTS vehicle (
    id BIGINT NOT NULL DEFAULT NEXTVAL ('vehicle_seq'),
    user_id BIGINT NOT NULL,
    brand VARCHAR(40) NOT NULL,
    model VARCHAR(40) NOT NULL,
    insert_time TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    CONSTRAINT fk_app_user FOREIGN KEY (user_id) REFERENCES app_user (id));
ALTER SEQUENCE vehicle_seq RESTART WITH 1;

CREATE SEQUENCE insurance_offer_seq;
CREATE TABLE IF NOT EXISTS insurance_offer (
    id BIGINT NOT NULL DEFAULT NEXTVAL ('insurance_offer_seq'),
    vehicle_id BIGINT NOT NULL,
    insurer VARCHAR(40) NOT NULL,
    price DECIMAL(13,2) NOT NULL,
    insert_time TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle (id));
ALTER SEQUENCE insurance_offer_seq RESTART WITH 1;

