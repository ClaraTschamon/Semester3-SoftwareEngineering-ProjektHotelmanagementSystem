DROP TABLE IF EXISTS room_category CASCADE;
DROP TABLE IF EXISTS room CASCADE;
DROP TABLE IF EXISTS booked_room CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS booking CASCADE;
DROP TABLE IF EXISTS booked_room_category CASCADE;


CREATE TABLE room_category(
    room_category_name VARCHAR(255) PRIMARY KEY ,
    price_per_night INTEGER
);

CREATE TABLE room(
    room_number INTEGER PRIMARY KEY,
    room_category_name VARCHAR(255),
    FOREIGN KEY (room_category_name) REFERENCES room_category(room_category_name)
);

CREATE TABLE customer(
    customer_number INTEGER PRIMARY KEY,
    customer_first_name VARCHAR(255),
    customer_last_name VARCHAR(255),
    customer_date_of_birth DATE,
    customer_nationality VARCHAR(255),
    customer_phone_number VARCHAR(255),
    customer_email_address VARCHAR(255),
    customer_street VARCHAR(255),
    customer_house_number VARCHAR(255),
    customer_postal_code VARCHAR(255),
    customer_city VARCHAR(255),
    customer_country VARCHAR(255),
    customer_credit_card_number VARCHAR(255),
    saved BOOLEAN
);

CREATE TABLE booking(
    booking_number INTEGER PRIMARY KEY,
    customer_number INTEGER,
    FOREIGN KEY (customer_number) REFERENCES customer(customer_number),
    arrival_date DATE NOT NULL,
    check_in_datetime DATETIME, //Zeitstempel wann man eingecheckt hat
    departure_date DATE,
    check_out_datetime DATETIME,
    billing_street VARCHAR(255),
    billing_house_number VARCHAR(255),
    billing_postal_code VARCHAR(255),
    billing_city VARCHAR(255),
    billing_country VARCHAR(255)
);

CREATE TABLE booked_room(
    booking_number INTEGER,
    FOREIGN KEY(booking_number) REFERENCES booking(booking_number),
    room_number INTEGER NOT NULL,
    FOREIGN KEY(room_number) REFERENCES room(room_number),
    from_date DATE,
    to_date DATE
);

CREATE TABLE booked_room_category(
    booking_number INTEGER,
    FOREIGN KEY (booking_number) REFERENCES booking(booking_number),
    room_category_name VARCHAR(255),
    FOREIGN KEY (room_category_name) REFERENCES room_category(room_category_name),
    booking_price_per_night INTEGER,
    amount_room_category INTEGER
);

