//Testdaten so schreiben, dass man kein Hartkodiertes Datum
//hat, sondern immer das aktuelle Datum eingefügt wird.
DELETE FROM ROOM
WHERE TRUE;
DELETE FROM ROOM_CATEGORY
WHERE TRUE;
DELETE FROM BOOKING
WHERE TRUE;
DELETE FROM CUSTOMER
WHERE TRUE;
DELETE FROM BOOKED_ROOM
WHERE TRUE;
DELETE FROM BOOKED_ROOM_CATEGORY
WHERE TRUE;


INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Einzelzimmer', 80);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Doppelzimmer', 95);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Familienzimmer', 120);

INSERT INTO room_category(room_category_name, price_per_night)
VALUES('Suite', 180);


INSERT INTO package(package_name, price_per_night)
VALUES('Vollpension', 30);

INSERT INTO package(package_name, price_per_night)
VALUES('Halbpension', 20);

INSERT INTO package(package_name, price_per_night)
VALUES('Nur Frühstück', 10);

INSERT INTO package(package_name, price_per_night)
VALUES('Ohne Package', 0);


INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(11, true, true, 'Einzelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(12, false, false, 'Einzelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(13, true, false, 'Einzelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(14, true, true, 'Familienzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(15, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(16, false, false, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(17, true, false, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(18, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(21, true, true, 'Einzelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(22, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(23, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(24, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(25, true, true, 'Familienzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(26, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(27, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(28, true, true, 'Familienzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(31, true, true, 'Familienzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(32, true, true, 'Doppelzimmer');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(33, true, true, 'Suite');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(34, true, true, 'Suite');

INSERT INTO room(room_number, is_free, is_clean, room_category_name)
VALUES(35, true, true, 'Suite');


INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(1, 'Angelina', 'Pfeiffer', DATE '1980-06-07', 'Österreich', '+43178/9074374', 'apfeiffer@live-mail.none', 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', true);

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(2, 'Dennis', 'Boto', DATE '1978-10-17', 'Deutschland', '+49151/8759788', 'boto_dennis@xyz.none', 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', true);

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, saved)
VALUES(3, 'Linda', 'Jackson', DATE '2001-01-06', 'Schweiz', '+41132/64805846', 'linda_jackson@net-mail.none', 'Iserstraße', '87', '38114', 'Amelinghausen', 'Schweiz', true);


INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number)
VALUES (1, 1, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 3, NULL, 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', 'isst Vegan', 'Kreditkarte','AT88 3888 2222 3333', DATE '2023-06-01', 'AVA2 AJ44 3285 8891');

INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number)
VALUES (2, 2, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 5, NULL, 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', 'hat seinen Hund dabei', 'Rechnung', null, null, null);

INSERT INTO booking(booking_number, customer_number, ARRIVAL_DATE, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country, comment, payment_method, credit_card_number, expiration_date, authorisation_number)
VALUES (3, 3, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 7, NULL, 'Iserstraße', '87', '38114', 'Amelighausen', 'Schweiz', null, 'Kreditkarte','CH22 4324 6665 3256', DATE '2024-12-01', 'EXI 3849 SHH 3898');


INSERT INTO BOOKED_ROOM(booking_number, room_number, from_date, to_date)
VALUES (1, 11, DATE '2022-11-10',  DATE '2022-11-12')





