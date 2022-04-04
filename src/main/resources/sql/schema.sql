CREATE SEQUENCE movie_sequence
  start 2
  increment 2;

CREATE TABLE IF NOT EXISTS movie(
    id SERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    rating NUMERIC(2,1) NOT NULL
);