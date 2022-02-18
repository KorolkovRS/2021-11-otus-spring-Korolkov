DROP TABLE IF EXISTS author CASCADE;
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS comment;

CREATE TABLE author
(
    author_id BIGSERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL
);

CREATE TABLE genre
(
    genre_id BIGSERIAL PRIMARY KEY,
    genre_name    VARCHAR(50) NOT NULL
);

CREATE TABLE book
(
    book_id   BIGSERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id BIGINT REFERENCES author (author_id),
    genre_id  BIGINT REFERENCES genre (genre_id)
);

CREATE TABLE comment
(
    comment_id BIGSERIAL PRIMARY KEY,
    text VARCHAR(1024) NOT NULL,
    book_id BIGINT REFERENCES book(book_id),
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP DEFAULT current_timestamp
);