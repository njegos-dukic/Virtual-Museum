USE VirtualMuseum;

INSERT INTO MUSEUM VALUES (0, 'Louvre', 'Rue de Rivoli', '+33 1 40 20 51 77', 'Paris', 'France', 'Art museum', 48.860294, 2.338629),
						  (0, 'Tate Modern', 'Bankside London SE1 9TG', '+44 20 7887 8888', 'London', 'United Kingdom', 'Art gallery', 51.507595,  -0.099356),
                          (0, 'Vatican Museums', '00120 Vatican City', '+39 06 6988 4676', 'Vatican City', 'Vatican', 'Art museum', 41.906487, 12.453641),
                          (0, 'Museo Reina Sofia', 'C. de Sta. Isabel, 52', '+34 917 74 10 00', 'Madrid', 'Spain', 'Non-movable', 40.407913, -3.694557),
                          (0, 'State Russian Museum', 'Inzhenernaya St, 4', '+7 812 595 42 48', 'Saint Petersburg', 'Russia', 'Art museum', 59.938742, 30.332385),
                          (0, 'Metropolitan Museum of Art', '1000 5th Ave', '+1 212 535 7710', 'New York', 'United States', 'Art museum', 40.779434, -73.963402),
                          (0, 'Museum of New Zaeland Te Papa Tongarewa', '55 Cable Street, Te Aro', '+64 4 381 7000', 'Wellington', 'New Zaeland', 'National museum', -41.2997, 174.7767);
                          
INSERT INTO VirtualMuseum.USER VALUES (0, 'admin', 'admin', 'Administrator', '1', 'admin@admin.com', 1, 'admintoken', 1, 0, 0),
									  (0, 'user', 'user', 'User', '1', 'user@user.com', 0, null, 1, 0, 0);
							
INSERT INTO TOUR VALUES (0, 'Tour 1', '2022-01-05 12:30:00', 2.5, 40, 1),
						(0, 'Tour 2', '2021-12-28 15:00:00', 4, 25, 1),
                        (0, 'Tour 3', '2022-01-08 08:30:00', 1.5, 70, 1),
                        (0, 'Tour 4', '2021-11-25 11:30:00', 5, 10, 2),
                        (0, 'Tour 5', '2022-01-02 17:30:00', 3.5, 30, 2),
                        (0, 'Tour 6', '2021-11-15 19:00:00', 3, 35, 3),
                        (0, 'Tour 7', '2022-01-03 16:00:00', 6.5, 70, 3),
                        (0, 'Tour 8', '2021-12-12 12:00:00', 2, 15, 4),
                        (0, 'Tour 9', '2021-10-17 10:30:00', 4.5, 45, 5),
                        (0, 'Tour 10', '2022-01-06 14:30:00', 2.5, 60, 5),
                        (0, 'Tour 11', '2021-11-23 09:30:00', 1.5, 55, 6),
                        (0, 'Tour 12', '2022-01-07 18:00:00', 4, 20, 6),
                        (0, 'Tour 13', '2021-12-17 19:30:00', 2.5, 25, 7),
                        (0, 'Tour 14', '2022-01-06 12:00:00', 5.5, 40, 7),
                        (0, 'Tour 15', '2021-12-16 08:30:00', 6.5, 30, 7);
                        
# INSERT INTO ARTIFACT VALUES (0, 'Artifact 1', 'img', 2);
                            
## INSERT INTO VirtaulMuseum.TRANSACTION VALUES (0, 3, 1, '124215');
			