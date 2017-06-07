DROP ALL OBJECTS;

CREATE TABLE USER (
  id         INT          NOT NULL IDENTITY,
  username   VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(100),
  last_name  VARCHAR(100),
  role       VARCHAR(50)           DEFAULT 'LISTENER',

  CONSTRAINT user_unique_1 UNIQUE (username),
  PRIMARY KEY (id)
);


CREATE TABLE ROOM (
  id          INT  NOT NULL IDENTITY,
  room_number INT2 NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE PRESENTATION (
  id          INT           NOT NULL IDENTITY,
  title       VARCHAR(2000) NOT NULL,
  schedule_id INT,
  PRIMARY KEY (id)
);


CREATE TABLE USER_PRESENTATION (
  user_id         INT NOT NULL,
  presentation_id INT,
  CONSTRAINT user_presentation_fk_1 FOREIGN KEY (user_id) REFERENCES USER,
  CONSTRAINT user_presentation_fk_2 FOREIGN KEY (presentation_id) REFERENCES PRESENTATION,
  PRIMARY KEY (user_id, presentation_id)
);


CREATE TABLE SCHEDULE (
  id                 INT NOT NULL IDENTITY,
  presentation_id    INT,
  room_id            INT,
  event_time         DATETIME,
  event_duration_min INT,
  CONSTRAINT schedule_presentation_fk_1
  FOREIGN KEY (presentation_id) REFERENCES PRESENTATION
  ON DELETE SET NULL,
  CONSTRAINT schedule_presentation_unique UNIQUE (presentation_id),
  CONSTRAINT schedule_room_fk FOREIGN KEY (room_id) REFERENCES ROOM,
  PRIMARY KEY (id)
);

ALTER TABLE PRESENTATION
  ADD FOREIGN KEY (schedule_id) REFERENCES SCHEDULE;
