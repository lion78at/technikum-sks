INSERT INTO t_category (name) VALUES ('Allgemein');

INSERT INTO t_news (title, text, publicationdate, topnews, categoryid) VALUES ('Hello World!', 'Herzlich willkommen am Planeten Erde.', '2019-01-01', false, 1);
INSERT INTO t_news (title, text, publicationdate, topnews, categoryid) VALUES ('News-Portal online!', 'Unser neues News-Portal ist online.', '2019-01-02', true, 1);

INSERT INTO t_author (sex, firstname, lastname) VALUES ('MALE', 'Markus', 'Mustermann');
INSERT INTO t_author (sex, firstname, lastname) VALUES ('FEMALE', 'Martina', 'Musterfrau');

INSERT INTO t_news_author (newsid, authorid) VALUES (1, 1);
INSERT INTO t_news_author (newsid, authorid) VALUES (1, 2);