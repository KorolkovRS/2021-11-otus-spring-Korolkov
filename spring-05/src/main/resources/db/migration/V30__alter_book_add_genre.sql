ALTER TABLE book
ADD genre_id INTEGER REFERENCES genre (genre_id);