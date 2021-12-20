CREATE TABLE genre
(
    genre_id SERIAL PRIMARY KEY,
    genre_name    VARCHAR(50) NOT NULL
);

INSERT INTO genre(genre_name)
VALUES ('Русская классика'),
       ('Фантастика');