DROP TABLE IF EXISTS author CASCADE;
DROP TABLE IF EXISTS book CASCADE;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS t_user CASCADE;
DROP TABLE IF EXISTS authority CASCADE;
DROP TABLE IF EXISTS users_authorities;

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
    updated_at TIMESTAMP  DEFAULT current_timestamp
);

CREATE TABLE t_user
(
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL
);

CREATE TABLE authority
(
    authority_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users_authorities
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES t_user(user_id),
    authority_id BIGINT REFERENCES authority(authority_id)
);
