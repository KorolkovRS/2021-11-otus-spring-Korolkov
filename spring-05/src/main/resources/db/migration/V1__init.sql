CREATE TABLE book
(
    book_id   BIGSERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    author_id INTEGER REFERENCES book (book_id)
);

CREATE TABLE author
(
    author_id SERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL
);