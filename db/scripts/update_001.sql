  --Задания
  CREATE TABLE IF NOT EXISTS items (
   id           SERIAL PRIMARY KEY ,
   description  VARCHAR(255) NOT NULL,
   created      TIMESTAMP NOT NULL,
   done         boolean DEFAULT TRUE
);

