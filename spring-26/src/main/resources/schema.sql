DROP TABLE IF EXISTS authorJpa CASCADE;
DROP TABLE IF EXISTS bookJpa CASCADE;
DROP TABLE IF EXISTS genreJpa;
DROP TABLE IF EXISTS commentJpa;

CREATE TABLE authorJpa
(
    author_id BIGSERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL
);

CREATE TABLE genreJpa
(
    genre_id BIGSERIAL PRIMARY KEY,
    genre_name    VARCHAR(50) NOT NULL
);

CREATE TABLE bookJpa
(
    book_id   BIGSERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id BIGINT REFERENCES authorJpa (author_id),
    genre_id  BIGINT REFERENCES genreJpa (genre_id)
);

CREATE TABLE commentJpa
(
    comment_id BIGSERIAL PRIMARY KEY,
    text VARCHAR(1024) NOT NULL,
    book_id BIGINT REFERENCES bookJpa(book_id),
    created_at TIMESTAMP DEFAULT current_timestamp,
    updated_at TIMESTAMP  DEFAULT current_timestamp
);
