CREATE TABLE ROOM(
  ROOM_ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(16) NOT NULL,
  ROOM_NUMBER CHAR(2) NOT NULL UNIQUE,
  BED_INFO CHAR(2) NOT NULL
);

CREATE TABLE GUEST(
  GUEST_ID BIGSERIAL PRIMARY KEY,
  FIRST_NAME VARCHAR(64),
  LAST_NAME VARCHAR(64),
  EMAIL_ADDRESS VARCHAR(64),
  ADDRESS VARCHAR(64),
  COUNTRY VARCHAR(32),
  STATE VARCHAR(12),
  PHONE_NUMBER VARCHAR(24)
);

CREATE TABLE RESERVATION(
  RESERVATION_ID BIGSERIAL PRIMARY KEY,
  ROOM_ID BIGINT NOT NULL,
  GUEST_ID BIGINT NOT NULL,
  RES_DATE DATE
);

CREATE SCHEMA IF NOT EXISTS tdd;

CREATE TABLE IF NOT EXISTS tdd.SERVICES (
                                            SERVICE_ID  UUID PRIMARY KEY,
                                            NAME VARCHAR UNIQUE,
                                            PRICE NUMERIC(12,2)
    );

CREATE TABLE IF NOT EXISTS tdd.CUSTOMERS (
                                             CUSTOMER_ID  UUID PRIMARY KEY,
                                             FIRST_NAME VARCHAR,
                                             LAST_NAME VARCHAR,
                                             EMAIL VARCHAR UNIQUE,
                                             PHONE VARCHAR,
                                             ADDRESS VARCHAR
);

CREATE TABLE IF NOT EXISTS tdd.VENDORS (
                                           VENDOR_ID  UUID PRIMARY KEY,
                                           NAME VARCHAR NOT NULL,
                                           CONTACT VARCHAR,
                                           PHONE VARCHAR,
                                           EMAIL VARCHAR,
                                           ADDRESS VARCHAR
);

CREATE TABLE tdd.PRODUCTS (
                              PRODUCT_ID UUID PRIMARY KEY,
                              NAME VARCHAR UNIQUE,
                              PRICE NUMERIC(12,2),
                              VENDOR_ID UUID NOT NULL,
                              FOREIGN KEY (VENDOR_ID) references tdd.VENDORS(VENDOR_ID)
);


ALTER TABLE RESERVATION ADD FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID);
ALTER TABLE RESERVATION ADD FOREIGN KEY (GUEST_ID) REFERENCES GUEST(GUEST_ID);
CREATE INDEX IDX_RES_DATE_ ON RESERVATION(RES_DATE);



-- ####################################################non working Tables


-- CREATE TABLE tour_package(
--                              code CHAR(2) NOT NULL UNIQUE,
--                              name VARCHAR(50) NOT NULL
-- );
--
-- CREATE TABLE tour (
--                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
--                       tour_package_code CHAR(2) NOT NULL,
--                       title VARCHAR(100) NOT NULL,
--                       description VARCHAR(2000) NOT NULL,
--                       blurb VARCHAR(2000) NOT NULL,
--                       bullets VARCHAR(2000) NOT NULL,
--                       price VARCHAR(10) not null,
--                       duration VARCHAR(32) NOT NULL,
--                       difficulty VARCHAR(16) NOT NULL,
--                       region VARCHAR(20) NOT NULL,
--                       keywords VARCHAR(100)
-- );
-- ALTER TABLE tour ADD FOREIGN KEY (tour_package_code) REFERENCES tour_package(code);
--
--
-- CREATE TABLE tour_rating (
--                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
--                              tour_id BIGINT,
--                              customer_id BIGINT,
--                              score INT,
--                              comment VARCHAR(100));
--
-- ALTER TABLE tour_rating ADD FOREIGN KEY (tour_id) REFERENCES tour(id);
-- ALTER TABLE tour_rating ADD UNIQUE MyConstraint (tour_id, customer_id);

