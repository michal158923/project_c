INSERT INTO my_user (name,password,code,join_date) VALUES
    ('admin', 'admin','42UmLLz7LLKpb5TYUzVV','2022-11-28 12:50:29.785967'),
    ('user1', 'user1','XzcC7sl3fP8U7m2b2ZF8','2022-11-28 12:50:29.785967'),
    ('user2', 'user2','63HYAwp4NNFY6c8PUC9r','2022-11-28 12:50:29.785967'),
    ('user3', 'user3','2T47538O916e0Pdcl9Kt','2022-12-09 11:26:30.407382');

INSERT INTO question (id, content) VALUES
    (1, 'Z iloma państwami graniczy Ruminia?'),
    (2,'Paella to:'),
    (3,'Wiklina to ścięte pędy'),
    (4,'jak nazywa się najgłębsze jezioro w polsce'),
    (5,'Z którego miasta pochodzi raper Peja?'),
    (6,'Jesionka to:'),
    (7,'Jak ma na imię konik polny, przyjaciel pszczółki Mai?'),
    (8,'Floret to nie jest'),
    (9,'Cardamiane to nazwa naukowa: '),
    (10,'Co jest stolica Jordani'),
    (11,'Ile dzieci ma królowa Elżbieta II ?'),
    (12,'Szmizjerka to: '),
    (13,'Która figura geometryczna moze być pitagorejska, wymierna, egipska, ostrokątna:'),
    (14,'Co oznacza angielskie słowo "regret"'),
    (15,'Które państwo ma w swojej fladze czerwone koło na białym tle?'),
    (16,'W którym mieście odbywa się akcja serialu "Ojciec Mateusz"'),
    (17,'Tutus, Romek i Atomek - który z nich jest szympansem?'),
    (18,'W którym miesiący Irlandczycy świętują Dzień Świętego Patryka'),
    (19,'W którym polskim mieście leży pałac Krzystofory'),
    (20,'Króry z tych krajów ma największą powierzchnię');

INSERT INTO answer (question_id, content, correct_flag) VALUES
    (1, 'z trzema', FALSE),
    (1, 'z czteroma', FALSE),
    (1, 'z pięcioma', TRUE),
    (1, 'z sześcioma', FALSE),
    (2,'Hiszpańska potrawa',TRUE),
    (2, 'Hiszpański taniec',FALSE),
    (2, 'misto w Hiszpanii',FALSE),
    (2, 'nic wspólnego z Hiszpanią',FALSE),
    (3,'Wierzby',TRUE),
    (3,'sosny', FALSE),
    (3,'topoli',FALSE),
    (3,'brzozy',FALSE),
    (4,'Drawsko', FALSE),
    (4,'Hańcza',TRUE),
    (4,'Wigry', FALSE),
    (4,'Śniardwy',FALSE),
    (5, 'z Warszawy',FALSE),
    (5, 'z Kielc', FALSE),
    (5, 'z Katowic',FALSE),
    (5, 'z Poznania', TRUE),
    (6, 'nakrycie głowy',FALSE),
    (6, 'wies w woj. Wielkop.',FALSE),
    (6, 'ciepły płaszcz', TRUE),
    (6, 'zimowe buty', FALSE),
    (7, 'Gustaw',FALSE),
    (7, 'Gucio',FALSE),
    (7, 'Filip', TRUE),
    (7, 'Aleksander', FALSE),
    (8, 'broń biała sportowa',FALSE),
    (8, 'gąbka florystyczna',FALSE),
    (8, 'elemet garderoby', TRUE),
    (8, 'żadna z powyższych odpowiedzi', FALSE),
    (9, 'kardamonu',FALSE),
    (9, 'klendry',FALSE),
    (9, 'bazylii', FALSE),
    (9, 'rzeżuchy', TRUE),
    (10, 'Amman',TRUE),
    (10, 'Teheran',FALSE),
    (10, 'Beirut', FALSE),
    (10, 'damaszek', FALSE),
    (11, '2',FALSE),
    (11, '3',FALSE),
    (11, '4', TRUE),
    (11, '5', FALSE),
    (12, 'zawód',FALSE),
    (12, 'elemen garderoby',TRUE),
    (12, 'narzędzie budowale', FALSE),
    (12, 'żadna z powyższych', FALSE),
    (13, 'trójkąt',TRUE),
    (13, 'trapez',FALSE),
    (13, 'romb', FALSE),
    (13, 'pięcikąt', FALSE),
    (14, 'żałować',TRUE),
    (14, 'odmawiać',FALSE),
    (14, 'wycofywać się', FALSE),
    (14, 'przysięgać', FALSE),
    (15, 'Laos',FALSE),
    (15, 'Somalia',FALSE),
    (15, 'Wyspy Owcze', FALSE),
    (15, 'Japońa', TRUE),
    (16, 'W Legnicy',FALSE),
    (16, 'W Sandomierzu',TRUE),
    (16, 'W Lublinie', FALSE),
    (16, 'W Radomiu', FALSE),
    (17, 'Tutus',TRUE),
    (17, 'Romek',FALSE),
    (17, 'Atomek', FALSE),
    (17, 'żaden', FALSE),
    (18, 'w marcu',TRUE),
    (18, 'w sierpniu',FALSE),
    (18, 'we wrześniu ', FALSE),
    (18, 'w lutym', FALSE),
    (19, 'W Gdańsku',FALSE),
    (19, 'W Malborku',FALSE),
    (19, 'W Lublinie', FALSE),
    (19, 'W Krakowie', TRUE),
    (20, 'Madagaskar',FALSE),
    (20, 'Indonezja',TRUE),
    (20, 'Kolumbia', FALSE),
    (20, 'Egipt', FALSE);

INSERT INTO user_answer (correct_choice_flag, given_answer_id, lot_question_id, user_id) VALUES
    ('TRUE' , 78, 20, 2),
    ('FALSE', 6 , 2 , 2),
    ('TRUE' , 3 , 1 , 2),
    ('FALSE', 33, 9 , 2),
    ('TRUE' , 23, 6 , 2),
    ('TRUE' , 27, 7 , 2),
    ('TRUE' , 46, 12, 2),
    ('FALSE', 44, 11, 2),

    ('TRUE' , 76, 19, 3),
    ('FALSE', 80, 20, 3),
    ('FALSE', 38, 10, 3),
    ('FALSE', 26, 7 , 3),
    ('FALSE', 19, 5 , 3),
    ('TRUE' , 60, 15, 3),
    ('TRUE' , 3 , 1 , 3),
    ('TRUE' , 23, 5 , 3),

    ('TRUE' , 62, 16, 4),
    ('TRUE' , 60, 15, 4),
    ('TRUE' , 9 , 3 , 4),
    ('TRUE' , 65, 17, 4),
    ('FALSE', 74, 19, 4),
    ('TRUE' , 14, 4 , 4),
    ('TRUE' , 69, 18, 4),
    ('FALSE', 38, 10, 4);