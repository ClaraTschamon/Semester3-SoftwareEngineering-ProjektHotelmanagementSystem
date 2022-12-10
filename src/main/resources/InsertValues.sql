DELETE FROM RESERVED_ROOM
WHERE TRUE;
DELETE FROM RESERVED_ROOM_CATEGORY
WHERE TRUE;
DELETE FROM RESERVATION
WHERE TRUE;
DELETE FROM INVOICED_ROOM_CATEGORY
WHERE TRUE;
DELETE FROM INVOICE
WHERE TRUE;
DELETE FROM BOOKED_ROOM
WHERE TRUE;
DELETE FROM BOOKED_ROOM_CATEGORY
WHERE TRUE;
DELETE FROM BOOKING
WHERE TRUE;
DELETE FROM CUSTOMER
WHERE TRUE;
DELETE FROM BOARD
WHERE TRUE;
DELETE FROM ROOM
WHERE TRUE;
DELETE FROM ROOM_CATEGORY
WHERE TRUE;

INSERT INTO room_category(room_category_name, price_per_night, max_amount_guests)
VALUES('Single room', 80, 1);

INSERT INTO room_category(room_category_name, price_per_night, MAX_AMOUNT_GUESTS)
VALUES('Double room', 95, 2);

INSERT INTO room_category(room_category_name, price_per_night, MAX_AMOUNT_GUESTS)
VALUES('Family room', 120, 4);

INSERT INTO room_category(room_category_name, price_per_night, MAX_AMOUNT_GUESTS)
VALUES('Suite', 180, 4);


INSERT INTO board(board_name, price_per_night)
VALUES('Full Board', 30);

INSERT INTO board(board_name, price_per_night)
VALUES('Half Board', 20);

INSERT INTO board(board_name, price_per_night)
VALUES('Just Breakfast', 10);

INSERT INTO board(board_name, price_per_night)
VALUES('No Package', 0);


INSERT INTO room(room_number, room_category_name)
VALUES(11, 'Single room');

INSERT INTO room(room_number, room_category_name)
VALUES(12, 'Single room');

INSERT INTO room(room_number, room_category_name)
VALUES(13, 'Single room');

INSERT INTO room(room_number, room_category_name)
VALUES(14, 'Family room');

INSERT INTO room(room_number, room_category_name)
VALUES(15, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(16, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(17, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(18, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(21, 'Single room');

INSERT INTO room(room_number, room_category_name)
VALUES(22, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(23, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(24, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(25, 'Family room');

INSERT INTO room(room_number, room_category_name)
VALUES(26, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(27, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(28, 'Family room');

INSERT INTO room(room_number, room_category_name)
VALUES(31, 'Family room');

INSERT INTO room(room_number, room_category_name)
VALUES(32, 'Double room');

INSERT INTO room(room_number, room_category_name)
VALUES(33, 'Suite');

INSERT INTO room(room_number, room_category_name)
VALUES(34, 'Suite');

INSERT INTO room(room_number, room_category_name)
VALUES(35, 'Suite');

//nur bis hierher!!

INSERT INTO customer(customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES('Angelina', 'Pfeiffer', DATE '1980-06-07', 'Österreich', '+43178/9074374', 'apfeiffer@live-mail.none', 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', true);

INSERT INTO customer(customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES('Dennis', 'Boto', DATE '1978-10-17', 'Deutschland', '+49151/8759788', 'boto_dennis@xyz.none', 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', true);

INSERT INTO customer(customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES('Linda', 'Jackson', DATE '2001-01-06', 'Schweiz', '+41132/64805846', 'linda_jackson@net-mail.none', 'Iserstraße', '87', '38114', 'Amelinghausen', 'Schweiz', true);


INSERT INTO reservation(customer_number, ARRIVAL_DATE,departure_date, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
VALUES (1,CURRENT_DATE, CURRENT_DATE + 3, 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', 'isst Vegan', 'Kreditkarte','AT88 3888 2222 3333', '01/23', 'AVA2 AJ44 3285 8891', 'Half Board', 20, 2);

INSERT INTO reservation(customer_number,ARRIVAL_DATE,  departure_date, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
VALUES (2,CURRENT_DATE, CURRENT_DATE + 5,'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', 'hat seinen Hund dabei', 'Rechnung', null, '10/25', null, 'Full Board', 30, 1);

-- INSERT INTO reservation(customer_number,ARRIVAL_DATE, , departure_date, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
-- VALUES (3,CURRENT_DATE, CURRENT_DATE + 7,  'Iserstraße', '87', '38114', 'Amelighausen', 'Schweiz', null, 'Kreditkarte','CH22 4324 6665 3256', '07/33', 'EXI 3849 SHH 3898', 'Full Board', 30, 1);


INSERT INTO booking(reservation_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
VALUES (1, 1,CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 3, NULL, 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', 'isst Vegan', 'Kreditkarte','AT88 3888 2222 3333', '01/23', 'AVA2 AJ44 3285 8891', 'Half Board', 20, 2);

INSERT INTO booking(reservation_number, customer_number,ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
VALUES (2,2,CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 5, NULL, 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', 'hat seinen Hund dabei', 'Rechnung', null, '10/25', null, 'Full Board', 30, 1);

-- INSERT INTO booking(customer_number,ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board, amount_guests)
-- VALUES (3,CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 7, NULL, 'Iserstraße', '87', '38114', 'Amelighausen', 'Schweiz', null, 'Kreditkarte','CH22 4324 6665 3256', '07/33', 'EXI 3849 SHH 3898', 'Full Board', 30, 1);


INSERT INTO BOOKED_ROOM(booking_number,room_number, from_date, to_date)
VALUES (1,12,DATE '2022-11-11',CURRENT_DATE);

INSERT INTO BOOKED_ROOM(booking_number,room_number, from_date, to_date)
VALUES (2,16,DATE '2022-11-10',CURRENT_DATE);

-- INSERT INTO BOOKED_ROOM(booking_number,room_number, from_date, to_date)
-- VALUES (3,21, DATE '2022-11-10',  DATE '2023-06-07');

INSERT INTO reserved_room(reservation_number, room_number, from_date, to_date)
VALUES (1,15,DATE '2022-11-11',CURRENT_DATE);

INSERT INTO reserved_room(reservation_number, room_number, from_date, to_date)
VALUES (2,26,DATE '2022-11-10',CURRENT_DATE);


INSERT INTO reserved_room_category(reservation_number, room_category_name, booking_price_per_night, amount_room_category)
VALUES (2,'Single room',30,100);

INSERT INTO reserved_room_category(reservation_number, room_category_name, booking_price_per_night, amount_room_category)
VALUES (2,'Single room',30,100);



