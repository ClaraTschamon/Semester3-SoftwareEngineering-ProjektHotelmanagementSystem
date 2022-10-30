//Testdaten so schreiben, dass man kein Hartkodiertes Datum
//hat, sondern immer das aktuelle Datum eingefügt wird.
//z.B. mit Date (?)
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


INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, customer_credit_card_number, saved)
VALUES(1, 'Angelina', 'Pfeiffer', DATE '1980-06-07', 'Österreich', '+43178/9074374', 'apfeiffer@live-mail.none', 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich', 'AT88 3888 2222 3333', 'ja');

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, customer_credit_card_number, saved)
VALUES(2, 'Dennis', 'Boto', DATE '1978-10-17', 'Deutschland', '+49151/8759788', 'boto_dennis@xyz.none', 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland', 'DE83 4567 4567 4567', 'ja');

INSERT INTO customer(customer_number, customer_first_name, customer_last_name, customer_date_of_birth, customer_nationality, customer_phone_number, customer_email_address, customer_street, customer_house_number, customer_postal_code, customer_city, customer_country, customer_credit_card_number, saved)
VALUES(3, 'Linda', 'Jackson', DATE '2001-01-06', 'Schweiz', '+41132/64805846', 'linda_jackson@net-mail.none', 'Iserstraße', '87', '38114', 'Amelinghausen', 'Schweiz', 'CH22 4324 6665 3256', 'ja');


INSERT INTO booking(booking_number, customer_number, arrival_dat, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country)
VALUES (1, 1, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 3, NULL, 'Orsoyer Straße', '72', '38114', 'Braunschweig', 'Österreich');

INSERT INTO booking(booking_number, customer_number, arrival_dat, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country)
VALUES (2, 2, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 5, NULL, 'Wuppermannstraße', '163a', '26901', 'Lorup', 'Deutschland');

INSERT INTO booking(booking_number, customer_number, arrival_dat, check_in_datetime, departure_date, check_out_datetime, billing_street, billing_house_number, billing_postal_code, billing_city, billing_country)
VALUES (3, 3, CURRENT_DATE, CURRENT_TIME(), CURRENT_DATE + 7, NULL, 'Iserstraße', '87', '38114', 'Amelighausen', 'Schweiz');




