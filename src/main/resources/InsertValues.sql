//Testdaten so schreiben, dass man kein Hartkodiertes Datum
//hat, sondern immer das aktuelle Datum eingefügt wird.
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

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Einzelzimmer', 80);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Doppelzimmer', 95);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Familienzimmer', 120);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Suite', 180);


INSERT INTO board(board_name, price_per_night)
VALUES('Vollpension', 30);

INSERT INTO board(board_name, price_per_night)
VALUES('Halbpension', 20);

INSERT INTO board(board_name, price_per_night)
VALUES('Nur Frühstück', 10);

INSERT INTO board(board_name, price_per_night)
VALUES('Ohne Package', 0);


INSERT INTO room(room_number, room_category_name)
VALUES(11, 'Einzelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(12, 'Einzelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(13, 'Einzelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(14, 'Familienzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(15, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(16, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(17, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(18, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(21, 'Einzelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(22, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(23, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(24, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(25, 'Familienzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(26, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(27, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(28, 'Familienzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(31, 'Familienzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(32, 'Doppelzimmer');

INSERT INTO room(room_number, room_category_name)
VALUES(33, 'Suite');

INSERT INTO room(room_number, room_category_name)
VALUES(34, 'Suite');

INSERT INTO room(room_number, room_category_name)
VALUES(35, 'Suite');


INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(1, 'Angelina', 'Pfeiffer', DATE '1980-06-07', 'Österreich', '+43178/9074374', 'apfeiffer@live-mail.none', 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', true);

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(2, 'Dennis', 'Boto', DATE '1978-10-17', 'Deutschland', '+49151/8759788', 'boto_dennis@xyz.none', 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', true);

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(3, 'Linda', 'Jackson', DATE '2001-01-06', 'Schweiz', '+41132/64805846', 'linda_jackson@net-mail.none', 'Iserstraße', '87', '38114', 'Amelinghausen', 'Schweiz', true);


INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board)
VALUES (1, 1, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 3, NULL, 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', 'isst Vegan', 'Kreditkarte','AT88 3888 2222 3333', '01/23', 'AVA2 AJ44 3285 8891', 'Halbpension', 20);

INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board)
VALUES (2, 2, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 5, NULL, 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', 'hat seinen Hund dabei', 'Rechnung', null, '10/25', null, 'Vollpension', 30);

INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number, board_name, price_per_night_for_board)
VALUES (3, 3, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 7, NULL, 'Iserstraße', '87', '38114', 'Amelighausen', 'Schweiz', null, 'Kreditkarte','CH22 4324 6665 3256', '07/33', 'EXI 3849 SHH 3898', 'Vollpension', 30);


INSERT INTO BOOKED_ROOM(booking_number, room_number, from_date, to_date)
VALUES (1, 12, DATE '2022-11-11',  CURRENT_DATE);

INSERT INTO BOOKED_ROOM(booking_number, room_number, from_date, to_date)
VALUES (1, 16, DATE '2022-11-10',  CURRENT_DATE);

INSERT INTO BOOKED_ROOM(booking_number, room_number, from_date, to_date)
VALUES (1, 21, DATE '2022-11-10',  DATE '2023-06-07');





