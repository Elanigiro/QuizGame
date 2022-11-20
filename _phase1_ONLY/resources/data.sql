
INSERT INTO Difficulty(id_diff, diff) 
VALUES (1, 'Easy'), (2, 'Medium'), (3, 'Hard');

INSERT INTO gameversion(launch_date) 
VALUES (CURRENT_TIMESTAMP);

INSERT INTO diffversion(id_diff, id_version, score_per_quest) 
VALUES (1, 1, 100), (2, 1, 150), (3, 1, 200);

INSERT INTO Topic(id_topic, topic) 
VALUES (1, 'History'), (2, 'Movies'), (3, 'Latin');

INSERT INTO Question(id_quest, topic, question, source) 
VALUES (1, 1, 'Il tempo', 'Albert Einstein'), 	(2, 1, 'E pur', 'Galileo Galilei'), 					(3, 1, 'Siate affamati', 'Steve Jobs'), 	(4, 1, 'Sii il cambiamento', 'Mahatma Gandhi'), 				(5, 1, 'Tutti i sogni possono diventare realta`', 'Walt Disney'), 	(6, 1, 'Un piccolo passo per un uomo', 'Neil Armstrong'), 	(7, 1, 'La moda', 'Coco Chanel'), 					(8, 1, 'La miglior Ferrari che sia mai stata costruita', 'Enzo Ferrari'), 	(9, 1, 'La religione e`', 'Karl Marx'), 			(10, 1, 'Il fine', 'Niccolo` Machiavelli'),  (11, 1, 'La nazione che distrugge il suo suolo', 'Franklin Delano Roosevelt'), (12, 1, 'Qui si fa l''Italia', 'Giuseppe Garibaldi'), (13, 1, 'Prendete in mano la vostra vita', 'Papa Giovanni Paolo II'), (14, 1, 'Un''ingiustizia commessa in un solo luogo', 'Martin Luther King'), (15, 1, 'E` meglio morire in piedi', 'Emiliano Zapata');

INSERT INTO Question(id_quest, topic, question, source) 
VALUES (16, 2,  'Elementare', 'Sherlock Holmes - Il ritorno di Sherlock Holmes'), 	(17, 2, 'Tu mi hai dato la pace', 'Achille - Troy'), 	(18, 2, 'E` leviosa,', 'Hermione Granger - Harry Potter'), 	(19, 2, 'Prima regola del Fight Club:', 'Tyler Durden - Fight Club'), 	(20, 2, 'Chi sei tu?', 'Sylvester Stallone - Rambo 3'), 									(21, 2, 'Io ne ho viste cose', 'Roy Batty - Blade Runner'), 				(22, 2, 'Gli faro` un''offerta', 'Don Vito Corleone - Il Padrino'), 	(23, 2, 'E` la bacchetta a scegliere il mago,', 'Garrick Olivander - Harry Potter'), 			(24, 2, 'Che la Forza', 'Yoda - Star Wars'), 				(25, 2, 'Uno stregone', 'Gandalf - Il Signore degli Anelli'), (26, 2, 'Francamente', 'Rhett Butler - Via col vento'), (27, 2, 'Alla tua salute', 'Rick Blaine - Casablanca'), (28, 2, 'Prendete il salvagente', 'Margo Channing - Eva contro Eva'), (29, 2, 'Quello che abbiamo qui', 'Il capitano - Nick Mano Fredda'), (30, 2, 'Tu non puoi reggere', 'Nathan Jessup - Codice d''onore');

INSERT INTO Question(id_quest, topic, question, source) 
VALUES (31, 3, 'Alea iacta', 'Gaio Giulio Cesare'), 	(32, 3, 'Infandum, reginae,', 'Publio Virgilio Marone'), 		(33, 3, 'Timeo Danaos', 'Publio Virgilio Marone'), 	(34, 3, 'Cartago', 'Marco Porcio Catone'), 							(35, 3, 'Veni, vidi,', 'Gaio Giulio Cesare'), 									(36, 3, 'Audentes', 'Publio Virgilio Marone'), 						(37, 3, 'Panem', 'Giovenale'), 					(38, 3, 'Omnia munda', 'Paolo di Tarso'), 										(39, 3, 'Melius est abundare quam', 'Autore Sconosciuto'), 	(40, 3, 'Per Aspera', 'Autore Sconosciuto'), (41, 3, 'Vitam regit fortuna', 'Cicerone'), (42, 3, 'Amor', 'Friedrich Nietzsche'), (43, 3, 'Ars longa', 'Ippocrate di Coo'), (44, 3, 'Dum vivimus', 'Philip Doddridge'), (45, 3, 'Vita incerta', 'Philip K. Dick');

INSERT INTO Answer(id_answer, question, answer, correct) VALUES
(1, 1, 'e` un''illusione.', true), (2, 1, 'e` oro.', false), (3, 1, 'da'' consiglio.', false), (4, 1, 'consuma ogni cosa.', false),
(5, 2, 'si muove.', true), (6, 2, 'si illumina.', false), (7, 2, 'e` tonda.', false), (8, 2, 'e` facile.', false),
(9, 3, 'siate folli.', true), (10, 3, 'siate forti.', false), (11, 3, 'arrivera` il nostro momento.', false), (12, 3, 'ce la faremo.', false),
(13, 4, 'che vuoi vedere nel mondo.', true), (14, 4, 'di cui hai bisogno.', false), (15, 4, 'che ci salvera`.', false), (16, 4, 'che vogliamo vedere.', false),
(17, 5, 'se abbiamo il coraggio di seguirli.', true), (18, 5, 'se in quei sogni ci crediamo veramente.', false), (19, 5, 'anche quelli piu` lontani.', false), (20, 5, 'anche quelli piu` difficili da realizzare.', false),
(21, 6, 'un grande balzo per l''umanita`.', true), (22, 6, 'e` solo l''inizio.', false), (23, 6, 'un grande passo per la Terra.', false), (24, 6, 'poteva andare peggio.', false),
(25, 7, 'e` fatta per andare fuori moda.', true), (26, 7, 'a volte e` strana.', false), (27, 7, 'e` difficile da seguire.', false), (28, 7, 'e` passeggera.', false),
(29, 8, 'e` la prossima.', true), (30, 8, 'e` la piu` veloce.', false), (31, 8, 'e` la Ferrari Roma.', false), (32, 8, 'e` la prima.', false),
(33, 9, 'l''oppio dei popoli.', true), (34, 9, 'la speranza del popolo.', false), (35, 9, 'un costrutto sociale.', false), (36, 9, 'la causa principale di molti conflitti.', false),
(37, 10, 'giustifica i mezzi.', true), (38, 10, 'e` quello a cui pensavamo.', false), (39, 10, 'non e` mai lo stesso.', false), (40, 10, 'e` molto lontano.', false), 
(41, 11, 'distrugge se stessa.', true), (42, 11, 'distruggera` il pianeta.', false), (43, 11, 'e` capace di distruggerne altri.', false), (44, 11, 'costruisce il suo baratro.', false), 
(45, 12, 'o si muore.', true), (46, 12, 'o ve ne pentirete.', false), (47, 12, 'o perderemo tutto.', false), (48, 12, 'o ci uccideranno.', false), 
(49, 13, 'e fatene un capolavoro.', true), (50, 13, 'e capite cosa volete farne.', false), (51, 13, 'e potrete arrivare ovunque.', false), (52, 13, 'ne capirete il vero valore.', false), 
(53, 14, 'e` una minaccia per la giustizia in ogni luogo.', true), (54, 14, 'e` un male che va condannato in ogni luogo.', false), (55, 14, 'porta ingiustizie anche altrove.', false), (56, 14, 'e` una sconfitta di molti.', false), 
(57, 15, 'che vivere in ginocchio.', true), (58, 15, 'che vivere seduti.', false), (59, 15, 'senza guardarsi indietro.', false), (60, 15, 'che morire in ginocchio.', false), 
(61, 16, 'Watson!', true), (62, 16, 'Potter!', false), (63, 16, 'era cosi` scontato!', false), (64, 16, 'era cosi` facile!', false),
(65, 17, 'in una vita di guerra.', true), (66, 17, 'di cui avevo bisogno.', false), (67, 17, 'in questo mondo di guerre.', false), (68, 17, 'che stavo cercando.', false),
(69, 18, 'non leviosa`!', true), (70, 18, 'devi ascoltare di piu`!', false), (71, 18, 'stai attento!', false), (72, 18, 'non come lo dici tu!', false), 
(73, 19, 'non si parla del Fight Club.', true), (74, 19, 'si combatte solo due per volta.', false), (75, 19, 'un combattimento alla volta.', false), (76, 19, 'se e` la vostra prima sera al Fight Club, dovete combattere.', false),
(77, 20, 'Il tuo incubo peggiore!', true), (78, 20, 'Pensa bene...in realta` lo sai.', false), (79, 20, 'Colui che non puo` essere nominato.', false), (80, 20, 'Dovresti saperlo.', false),
(81, 21, 'che voi umani non potreste immaginarvi.', true), (82, 21, 'che non potete immaginare.', false), (83, 21, 'che non credevo esistessero.', false), (84, 21, 'che neanche riuscivo ad immaginare.', false),
(85, 22, 'che non potra` rifiutare.', true), (86, 22, 'che accettera` subito.', false), (87, 22, 'che difficilmente rifiutera`.', false), (88, 22, 'che non rifiutera`.', false),
(89, 23, 'signor Potter.', true), (90, 23, 'non il mago a scegliere la bacchetta.', false), (91, 23, 'signorina Granger.', false), (92, 23, 'signor Weasley.', false),
(93, 24, 'sia con te!', true), (94, 24, 'con te sia!', false), (95, 24, 'interiore sia la protezione piu` potente che tu possa avere!', false), (96, 24, 'non ti abbandoni mai!', false),
(97, 25, 'non e` mai in ritardo.', true), (98, 25, 'e` sempre in orario.', false), (99, 25, 'non e` un mago.', false), (100, 25, 'non e` una strega.', false),
(101, 26, 'me ne infischio.', true), (102, 26, 'non ne ho voglia.', false), (103, 26, 'ho voglia di ucciderti.', false), (104, 26, 'vorrei trasformarmi in una libellula.', false),
(105, 27, 'bambina.', true), (106, 27, 'nonna.', false), (107, 27, 'mamma.', false), (108, 27, 'papa`.', false),
(109, 28, 'questa sera c''e` aria da burrasca.', true), (110, 28, 'questa notte potremmo morire.', false), (111, 28, 'altrimenti affogherete.', false), (112, 28, 'e andate a fare il vostro lavoro.', false),
(113, 29, 'e` un fallimento della comunicazione.', true), (114, 29, 'e` una scoperta interessante.', false), (115, 29, 'e` decisamente una delusione.', false), (116, 29, 'potremmo non vederlo mai piu`.', false),
(117, 30, 'la verita`.', true), (118, 30, 'tutto questo peso.', false), (119, 30, 'la tua vergogna.', false), (120, 30, 'le mie emozioni.', false),
(121, 31, 'est.', true), (122, 31, 'sud.', false), (123, 31, 'nord.', false), (124, 31, 'ovest.', false),
(125, 32, 'iubes renovare dolorem.', true), (126, 32, 'ut detrahere mihi.', false), (127, 32, 'ut interficias me', false), (128, 32, 'ad amare te.', false),
(129, 33, 'et dona ferentes.', true), (130, 33, 'plus quam Persarum.', false), (131, 33, 'cum autem loquimur.', false), (132, 33, 'mors.', false),
(133, 34, 'delenda est.', true), (134, 34, 'oportet putrescet.', false), (135, 34, 'illa splendidis.', false), (136, 34, 'non existat.', false),
(137, 35, 'vici.', true), (138, 35, 'perdidi.', false), (139, 35, 'cecidi.', false), (140, 35, 'risi.', false),
(141, 36, 'fortuna iuvat.', true), (142, 36, 'crudelis.', false), (143, 36, 'est arcanum.', false), (144, 36, 'semper venit.', false),
(145, 37, 'et circenses.', true), (146, 37, 'et vinum.', false), (147, 37, 'et callidus.', false), (148, 37, 'et aqua.', false),
(149, 38, 'mundis.', true), (150, 38, 'universus.', false), (151, 38, 'terram.', false), (152, 38, 'stellae.', false),
(153, 39, 'deficere.', true), (154, 39, 'perdere.', false), (155, 39, 'comedere.', false), (156, 39, 'mori.', false),
(157, 40, 'ad astra.', true), (158, 40, 'usque in finem.', false), (159, 40, 'ad mane.', false), (160, 40, 'et spes.', false),
(161, 41, 'non sapientia.', true), (162, 41, 'non pecunia.', false), (163, 41, 'et fatales.', false), (164, 41, 'et morte.', false),
(165, 42, 'fati.', true), (166, 42, 'et sacrificium.', false), (167, 42, 'pulvis.', false), (168, 42, 'vitae.', false),
(169, 43, 'vita brevis.', true), (170, 43, 'amicitiam cernimus.', false), (171, 43, 'immodica ira.', false), (172, 43, 'incohare longam.', false),
(173, 44, 'vivamus.', true), (174, 44, 'caritas.', false), (175, 44, 'magistra.', false), (176, 44, 'mors.', false),
(177, 45, 'mors certissima.', true), (178, 45, 'non tollitur.', false), (179, 45, 'vita excolatur.', false), (180, 45, 'perdidit tempore.', false);

ALTER TABLE Answer ALTER COLUMN id_answer RESTART WITH 121;