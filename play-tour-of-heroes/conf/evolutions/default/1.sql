# User schema

# --- !Ups
CREATE TABLE HEROES (
  ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NAME TEXT NOT NULL
);

# --- !Downs
DROP TABLE HEROES;