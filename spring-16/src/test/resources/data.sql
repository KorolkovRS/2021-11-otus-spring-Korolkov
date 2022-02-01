INSERT INTO author(name)
VALUES ('Л.Н. Толстой'),
       ('Роджер Желязны'),
       ('Герберт Уэлс');

INSERT INTO genre(genre_name)
VALUES ('Русская классика'),
       ('Фантастика');

INSERT INTO book(title, author_id, genre_id)
VALUES ('Война и мир', 1, 1),
       ('Хроники Амбера', 2, 2),
       ('война миров', 3, 2);

INSERT INTO comment(text, book_id)
VALUES ('Описание природы', 1),
       ('Затянуто', 1);


