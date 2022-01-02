INSERT INTO author(name)
VALUES ('Ф.М. Достоевский'),
       ('Л.Н. Толстой'),
       ('Роджер Желязны');

INSERT INTO genre(genre_name)
VALUES ('Русская классика'),
       ('Фантастика');

INSERT INTO book(title, author_id, genre_id)
VALUES ('Преступление и наказание', 1, 1),
       ('Война и мир', 2, 1),
       ('Хроники Амбера', 3, 2);