DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS genre;

CREATE TABLE author
(
    author_id SERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL
);

CREATE TABLE genre
(
    genre_id SERIAL PRIMARY KEY,
    genre_name    VARCHAR(50) NOT NULL
);

CREATE TABLE book
(
    book_id   BIGSERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id INTEGER REFERENCES author (author_id),
    genre_id  INTEGER REFERENCES genre (genre_id)
);