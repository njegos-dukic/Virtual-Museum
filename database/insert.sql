USE VirtualMuseum;


INSERT INTO MUSEUM VALUES (0, 'Louvre', 'Rue de Rivoli', '+33 1 40 20 51 77', 'Paris', 'France', 'Art museum', 48.860294, 2.338629),
						  (0, 'Tate Modern', 'Bankside London SE1 9TG', '+44 20 7887 8888', 'London', 'United Kingdom', 'Art gallery', 51.507595,  -0.099356),
                          (0, 'Vatican Museums', '00120 Vatican City', '+39 06 6988 4676', 'Vatican City', 'Vatican', 'Art museum', 41.906487, 12.453641),
                          (0, 'Museo Reina Sofia', 'C. de Sta. Isabel, 52', '+34 917 74 10 00', 'Madrid', 'Spain', 'Non-movable', 40.407913, -3.694557),
                          (0, 'State Russian Museum', 'Inzhenernaya St, 4', '+7 812 595 42 48', 'Saint Petersburg', 'Russia', 'Art museum', 59.938742, 30.332385),
                          (0, 'The Pompidou Centre', 'Place Georges-Pompidou', '+33 1 44 78 12 33', 'Paris', 'France', 'Modern and contemporary art', 48.860653, 2.352411),
                          (0, 'The British Museum', 'Great Russell St, London WC1B 3DG', '+44 20 7323 8299', 'London', 'United Kingdon', 'Public museum', 51.518757, -0.126168),
                          (0, 'The Rijksmuseum', ' Museumstraat 1', '+31 20 674 7000', 'Amsterdam', 'Netherlands', 'Art and history museum', 52.360001, 4.885278),
                          (0, 'Prado', 'C. de Ruiz de Alarcón, 23', '+34 913 30 28 00', 'Madrid', 'Spain', 'National art museum', 40.413780, -3.692127),
                          (0, 'The National Gallery', 'Trafalgar Square', '+44 20 7747 2885', 'London', 'United Kingdom', 'Art museum', 51.5086, -0.1283),
                          (0, 'The Anne Frank House', 'Westermarkt 20', '+31 20 556 7105', 'Amsterdam', 'Netherlands', 'Biographical museum', 52.375191, 4.883928),
                          (0, 'Centre Pompidou Metz', '1 Parv. des Droits de lHomme', '+33 3 87 15 39 39', 'Metz', 'France', 'Museum of modern and contemporary art', 49.104832914, 6.174665968),
                          (0, 'The Serralves Museum', 'R. Dom João de Castro 210', '+351 22 615 6500', 'Porto', 'Portugal', 'Major modern art collection', 41.15853, -8.66036),
                          (0, 'The Museum of Broken Relationships', 'Ćirilometodska ul. 2', '+385 1 4851 021', 'Zagreb', 'Croatia', 'Baroque place', 45.81497, 15.97353),
                          (0, 'Museu de la Miniatura', 'AD300 Ordino', '+376 838 338', 'Andorra', 'Andorra', 'Miniatures museum', 42.54072, 1.57320),
                          (0, 'Belvedere Museum',  'rinz Eugen-Straße 27', '+43 1 795570', 'Wien', 'Austria', 'Summer residence and museum', 48.20716, 16.36934),
                          (0, 'Magritte Museum', 'Pl. Royale 1', '+32 2 508 32 11', 'Bruxelles', 'Belgium', 'Surrealism museum', 50.8424057, 4.3586407),
                          (0, 'Oldmasters Museum', 'Rue de la Régence 3', '+32 2 508 32 11', 'Bruxelles', 'Belgium', 'Painters museum', 50.84161, 4.35809),
                          (0, 'Museum of Communism', 'V Celnici 1031/4', '+420 224 212 966', 'Nove Mesto', 'Czechia', 'Post WW2 museum', 50.08785, 14.43001),
                          (0, 'Thorvaldsens Museum', 'Bertel Thorvaldsens Plads 2', '+45 33 32 15 32', 'Copenhagen', 'Denmark', 'Single artist museum', 55.67675, 12.57879),
                          (0, 'Victoria & Albert Museum', ' Cromwell Rd', '+44 20 7942 2000', 'London', 'United Kingdom', 'Applied arts museum', 51.49688, -0.17164),
                          (0, 'The National Archeological Museum', '28is Oktovriou 44', '+30 21 3214 4800', 'Athina', 'Greece', 'Archeological museum', 37.98901, 23.73296),
                          (0, 'Royal Museums of Fine Arts', 'Rue de la Régence 3', '+32 2 508 32 11', 'Bruxelles', 'Belgium', 'Group of museums', 50.84168, 4.35824),
                          (0, 'Capodimonte Museum', 'Via Miano, 2', '+39 081 749 9111', 'Napoli', 'Italy', 'Art museum', 40.87064, 14.25005),
                          (0, 'Borghese Gallery', 'Piazzale Scipione Borghese, 5', '+39 06 841 3979', 'Roma', 'Italy', 'Art gallery', 41.91412, 12.49206),
                          (0, 'Venice Academy Gallery', 'Campo della Carità, 1050', '+39 041 522 2247', 'Venezia', 'Italy', 'Museum gallery', 45.43124, 12.32830);                          
                          
INSERT INTO VirtualMuseum.USER VALUES (0, 'admin', 'admin', 'Administrator', '1', 'njegos.dukic.998@gmail.com', 0, 1, 'admintoken', 1, 0, 0),
									  (0, 'user', 'user', 'User', '1', 'njegos.dukic.998@gmail.com', 0, 0, null, 1, 0, 0);

INSERT INTO CARD VALUES ('1111222233334444', 'njegos', 'dukic', 'VISA', '1222', '123', 10000.00, 1),
						('2222333344445555', 'vanja', 'novakovic', 'MASTERCARD', '1122', '123', 30.00, 1);
			