CREATE TABLE Client (
                        client_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        name TEXT NOT NULL,
                        phone CHAR(11) NOT NULL UNIQUE,
                        address TEXT NOT NULL
);

CREATE TABLE Film (
                      film_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      title TEXT NOT NULL,
                      company TEXT NOT NULL,
                      director TEXT NOT NULL,
                      year_of_release CHAR(4),
                      description TEXT NOT NULL
);

CREATE TABLE Copy (
                      copy_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      film_id INT,
                      type TEXT NOT NULL,
                      status TEXT NOT NULL,
                      price INT,
                      FOREIGN KEY (film_id) REFERENCES Film(film_id)
);

CREATE TABLE ClientHistoryRecord (
                                     history_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                     client_id INT,
                                     copy_id INT,
                                     date_of_transfer DATE NOT NULL,
                                     date_of_receipt DATE NOT NULL,
                                     actual_date_of_receipt DATE NOT NULL,
                                     transfer_amount INT NOT NULL,

                                     FOREIGN KEY (client_id) REFERENCES Client(client_id),
                                     FOREIGN KEY (copy_id) REFERENCES Copy(copy_id)
);

/* INTO Client table */
INSERT INTO Client (name, phone, address) VALUES('Иванов Алексей Михайлович', '89261900909', 'г. Москва, ул. 2-я Владимирская, д. 37');
INSERT INTO Client (name, phone, address) VALUES('Петров Иван Алексеевич', '89161448984', 'г. Москва, ул. Тверская, д. 4' );
INSERT INTO Client (name, phone, address) VALUES('Алексеев Петр Иванович', '89148969666', 'г. Москва, ул. 1-я Владимирская, д. 45');
INSERT INTO Client (name, phone, address) VALUES('Абрамов Дмитрий Евгеньевич', '89166768558', 'г. Москва, ул. Первомайская, д 19');
INSERT INTO Client (name, phone, address) VALUES('Лузик Дмитрий Евгеньевич', '77777777777', 'г. Москва');

/* INTO Film table */
INSERT INTO Film(title, company, director, year_of_release, description)
    VALUES('Брат', 'СТВ', 'Алексей Балабанов', '1997', 'Демобилизованный из армии Данила Багров возвращается в родной город. Скучная, однообразная жизнь провинции не может устроить крепкого русского парня, и он решает поехать в Питер, испытать себя. Тем более, что там, по слухам уже давно процветает его старший брат. Но «новая русская» жизнь северной столицы оказывается слишком неожиданной, а родной брат Данилы зарабатывает на жизнь заказными убийствами. Даниле предстоит многое узнать… и со многими разобраться.');
INSERT INTO Film(title, company, director, year_of_release, description)
    VALUES('Брат 2', 'СТВ', 'Алексей Балабанов', '2000', 'Второй фильм о культовом русском герое 90-х – Даниле Багрове. Участвуя в программе на телевидении, Данила Багров встречает своих друзей по Чечне. Одного из них внезапно убивают. Данила знает, что у друга были неприятности из-за брата-хоккеиста в Америке. Данила должен разобраться. Теперь его фотография ходит по рукам русской и американской мафии. Новоявленные «Аль Капоне» понимают, что нарвались на профессионала…');
INSERT INTO Film(title, company, director, year_of_release, description)
    VALUES('Карты, деньги, два ствола', 'SKA Films', 'Гай Ричи', '1998', 'Четверо молодых парней накопили каждый по 25 тысяч фунтов, чтобы один из них мог сыграть в карты с опытным шулером и матерым преступником, известным по кличке Гарри-Топор. Парень в итоге проиграл 500 тысяч, на уплату долга ему дали неделю.');
INSERT INTO Film(title, company, director, year_of_release, description)
    VALUES('Большой Куш', 'Columbia Pictures', 'Гай Ричи', '2000', 'Фрэнки Четыре Пальца должен был переправить краденый алмаз из Англии в США своему боссу Эви, но, сделав ставку на подпольный боксерский поединок, он попал в круговорот весьма нежелательных событий. Вокруг него и его груза разворачивается сложная интрига с участием множества колоритных персонажей лондонского дна — русского гангстера, троих незадачливых грабителей, хитрого боксера и угрюмого громилы грозного мафиози. Каждый норовит в одиночку сорвать большой куш.');

/* INTO Copy table */
INSERT INTO Copy(film_id, type, status, price) VALUES(1, 'Диск', 'Свободный', 550);
INSERT INTO Copy(film_id, type, status, price) VALUES(1, 'Кассета', 'Свободный', 450);
INSERT INTO Copy(film_id, type, status, price) VALUES(1, 'Диск', 'Используется', 550);
INSERT INTO Copy(film_id, type, status, price) VALUES(1, 'Кассета', 'Свободный', 450);
INSERT INTO Copy(film_id, type, status, price) VALUES(2, 'Диск', 'Свободный', 650);
INSERT INTO Copy(film_id, type, status, price) VALUES(2, 'Диск', 'Свободный', 650);
INSERT INTO Copy(film_id, type, status, price) VALUES(2, 'Кассета', 'Свободный', 500);
INSERT INTO Copy(film_id, type, status, price) VALUES(2, 'Диск', 'Используется', 650);
INSERT INTO Copy(film_id, type, status, price) VALUES(3, 'Кассета', 'Свободный', 400);
INSERT INTO Copy(film_id, type, status, price) VALUES(3, 'Диск', 'Свободный', 500);
INSERT INTO Copy(film_id, type, status, price) VALUES(3, 'Диск', 'Свободный', 500);
INSERT INTO Copy(film_id, type, status, price) VALUES(3, 'Кассета', 'Свободный', 400);
INSERT INTO Copy(film_id, type, status, price) VALUES(4, 'Диск', 'Используется', 700);
INSERT INTO Copy(film_id, type, status, price) VALUES(4, 'Кассета', 'Свободный', 700);
INSERT INTO Copy(film_id, type, status, price) VALUES(4, 'Диск', 'Свободный', 700);
INSERT INTO Copy(film_id, type, status, price) VALUES(4, 'Диск', 'Свободный', 700);

/* INTO ClientHistoryTable */
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(1, 1, '2022.02.01', '2022.02.10', '2022.02.09', 550);
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(1, 5, '2022.02.15', '2022.02.22', '2022.02.22', 650);
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(2, 10, '2022.02.10', '2022.02.20', '2022.02.17', 500);
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(3, 11, '2022.02.10', '2022.02.20', '2022.02.20', 500);
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(3, 15, '2022.02.24', '2022.03.01', '2022.03.01', 700);
INSERT INTO ClientHistoryRecord(client_id, copy_id, date_of_transfer, date_of_receipt, actual_date_of_receipt, transfer_amount)
    VALUES(4, 2, '2022.03.01', '2022.03.03', '2022.03.07', 450);

/* Test by SELECT */

SELECT * FROM Client;
SELECT * FROM Film;
SELECT * FROM Copy;
SELECT * FROM ClientHistoryRecord;
