-- пароль admin
INSERT INTO USER (id, username, password, role)
VALUES (-1, 'admin', '$2a$10$FJtA52u7PmJ1yG8Tdp2idOVhvSG1I7NSbc5yyf61Q5J2ACj./TyZ2', 'ROLE_ADMIN');
-- пароль user для всех пользователей
INSERT INTO USER (id, username, password, role)
VALUES (1, 'tim', '$2a$10$KLWCS.kNO5JzkhQQHatQTeE7Zrz/uQP4SuzHojNbklovHvBuXaiAm', 'ROLE_PRESENTER');
INSERT INTO USER (id, username, password, role)
VALUES (2, 'alice', '$2a$10$KLWCS.kNO5JzkhQQHatQTeE7Zrz/uQP4SuzHojNbklovHvBuXaiAm', 'ROLE_PRESENTER');
INSERT INTO USER (id, username, password, role)
VALUES (3, 'tom1', '$2a$10$KLWCS.kNO5JzkhQQHatQTeE7Zrz/uQP4SuzHojNbklovHvBuXaiAm', 'ROLE_LISTENER');
INSERT INTO USER (id, username, password, role)
VALUES (4, 'jack1', '$2a$10$KLWCS.kNO5JzkhQQHatQTeE7Zrz/uQP4SuzHojNbklovHvBuXaiAm', 'ROLE_LISTENER');

INSERT INTO PRESENTATION (TITLE)
VALUES
  ('title1'),
  ('title2'),
  ('title3'),
  ('title4'),
  ('title5');

INSERT INTO USER_PRESENTATION (USER_ID, PRESENTATION_ID)
VALUES
  (1, 1),
  (1, 2),
  (1, 5),
  (2, 1),
  (2, 3),
  (2, 4);

INSERT INTO ROOM (ROOM_NUMBER) VALUES (201), (202), (203), (204);

INSERT INTO SCHEDULE (PRESENTATION_ID, ROOM_ID, EVENT_TIME, EVENT_DURATION_MIN) VALUES
  (1, 1, '2017-09-17 10:00:00.00', 59),
  (2, 2, '2017-09-17 10:00:00.00', 59),
  (3, 1, '2017-09-17 11:00:00.00', 59),
  (4, 2, '2017-09-17 11:00:00.00', 59);

INSERT INTO SCHEDULE
(PRESENTATION_ID, ROOM_ID, EVENT_TIME, EVENT_DURATION_MIN)
VALUES
  (5, 1, '2017-09-17 12:00:00.00', 40);

UPDATE PRESENTATION
SET SCHEDULE_ID = 1
WHERE id = 1;
UPDATE PRESENTATION
SET SCHEDULE_ID = 2
WHERE id = 2;
UPDATE PRESENTATION
SET SCHEDULE_ID = 3
WHERE id = 3;
UPDATE PRESENTATION
SET SCHEDULE_ID = 4
WHERE id = 4;

-- '2017-09-17 10:00:00.00',
-- '2017-09-17 10:00:00.00',
-- '2017-09-17 11:00:00.00',
-- '2017-09-17 11:00:00.00',