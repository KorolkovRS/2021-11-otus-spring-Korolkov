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
       ('Хроники Амбера', 3, 2),
       ('Звездный спидвей', 3, 2),
       ('Анна Каренина', 1, 1),
       ('Кавказский пленник', 1, 1);


INSERT INTO comment(text, book_id)
VALUES ('Русская классика это страдание. Страдает главный герой, автор или читатель. ' ||
        'Если страдают все трое, то это шедевр русской классики.', 1),
       ('Описание природы', 1),
       ('Золотая коллекция зарубежной фантазии', 3),
       ('Гонки на космических кораблях', 4),
       ('Девушка и поезд', 5),
       ('В плену у горцев', 6),
       ('c1', 1),
       ('c2', 1),
       ('c3', 1),
       ('c4', 1),
       ('c5', 1),
       ('c6', 1),
       ('c7', 1),
       ('c8', 1),
       ('c9', 1),
       ('c10', 1),
       ('c11', 1),
       ('c12', 1);

INSERT INTO t_user(username, password)
VALUES ('boba', '$2a$12$jYwbEn/1TZWNt3K9/njSnOJU6g5m8VzxHHjhv5YIBb65GDuaPvNXG'),
       ('biba', '$2a$12$7dcXuKlx5HaZ9hatV3CDZeBEbllKUf1XVYQKXENB9fvCP8HfB4zY.');

INSERT INTO t_role(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);
