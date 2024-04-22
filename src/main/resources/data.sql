INSERT INTO category (name) VALUES
        ('adventure'),
        ('science fiction'),
        ('fantasy'),
        ('detectives'),
        ('historical literature'),
        ('books for children'),
        ('modern poetry'),
        ('thriller');

INSERT INTO author (first_name, last_name) VALUES
        ('Андрій', 'Сем’янків');

INSERT INTO book (description, name, num_of_pages) VALUES
        ('Книга "Танці з кістками" розповідає просту, на перший погляд, історію про Северина - патологоанатома, якому не пощастило ні на роботі, ні в особистому житті. З самооцінкою на нулі та мізерною зарплатою, він швидко піддається спокусі заробити гроші, яка змінить його життя. Хоча пропозиція не зовсім законна, а ринок трансплантації органів не є морально чистим, це стає для Северина надзвичайно спокусливим шансом на забезпечене майбутнє.',
         'Танці з кістками',
         368
        );

INSERT INTO book_author(book_id, author_id) VALUES
        (1, 1);

INSERT INTO book_category (book_id, category_id) VALUES
        (1, 4),
        (1, 8);

INSERT INTO _user (date_of_birth, email, first_name, last_name, password, role) VALUES
        ('2004-10-05', 'admin@gmail.com', 'admin', 'admin', '$2a$12$LZKyTIxMUzTpCyXYGcYx3uMzJrAAJHvRuisCZ1fkEH6NzMoJS3bVG', 'ROLE_ADMIN'),
        ('2004-10-05', 'dima.kazmiruk.05@gmail.com', 'Dmytro', 'Kazmiruk', '$2a$12$aal78acqPKPOwyxcoEN6/.TetGIjltrPcKA7dGeBJLouM4krveK5K', 'ROLE_READER'),
        ('2004-04-03', 'vlad.tkachuk@gmail.com', 'Vladyslav', 'Tkachuk', '$2a$12$2amw9YFhX9Ffq/bnCtzvvu.W73JA1W.4BlXN5iA4EyEOCjWanpjku', 'ROLE_READER')