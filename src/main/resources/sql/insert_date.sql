insert into role  values (1,'Роль пользователя','USER'), (2,'Роль Режжисёра', 'REZ');

/*Фильмы*/
INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', 'Ультра мега крутой фильм', 2, '2022-11-15 13:54:28.115079 ','ыы' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', 'Супер крйто фильм', 1, '2022-9-15 13:54:28.115079 ','Крутой фильм' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', 'Супер  фильм', 4, '2022-10-17 13:54:28.115079 ','Супер фильм' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', '  фильм', 3, '2022-10-12 13:54:28.115079 ',' фильм' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', '  крутой', 4, '2022-8-12 13:54:28.115079 ',' крйто' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', '  супер', 4, '2022-8-17 13:54:28.115079 ',' супер' );

INSERT INTO film (id,country,description,genre,premier_year,title)
VALUES (nextval('films_sequence'), 'Russia', '  ульраф', 4, '2022-8-20 13:54:28.115079 ',' ульраф' );

/*Участники*/
INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Кристофер Нолан', 'Описание', 'Режиссёр', '2022-8-20 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Рамзес Кушнарёв', 'Описание', 'Режиссёр', '2022-8-21 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Киотака Глеб', 'Описание', 'Режиссёр', '2021-8-20 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Даниил ГПК', 'Описание', 'Режиссёр', '2020-8-20 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Элька Пуаро', 'Описание', 'Режиссёр', '2001-8-20 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Алексей СОЛО', 'Описание', 'Режиссёр', '1999-8-20 13:54:28.115079 ' );

INSERT INTO directors (id,dirfio,description,position,birth_date)
VALUES (nextval('directors_sequence'), 'Евген Мафиози', 'Описание', 'Режиссёр', '2001-8-20 13:54:28.115079 ' );


