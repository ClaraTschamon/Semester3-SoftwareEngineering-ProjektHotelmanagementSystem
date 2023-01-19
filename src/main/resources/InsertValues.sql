DELETE FROM RESERVED_ROOM
WHERE TRUE;
DELETE FROM RESERVED_ROOM_CATEGORY
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
DELETE FROM RESERVATION
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

