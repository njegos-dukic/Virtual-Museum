USE VirtualMuseum;

INSERT INTO MUSEUM VALUES (0, 'Louvre', 'Rue de Rivoli', '+33 1 40 20 51 77', 'Paris', 'France', 'Art museum', 'https://goo.gl/maps/1vJzX2y25z5iEFw49'),
						  (0, 'Tate Modern', 'Bankside London SE1 9TG', '+44 20 7887 8888', 'London', 'United Kingdom', 'Art gallery', 'https://goo.gl/maps/BpKT7Xq14WbXzwP56'),
                          (0, 'Vatican Museums', '00120 Vatican City', '+39 06 6988 4676', 'Vatican City', 'Vatican', 'Art museum', 'https://g.page/musei-vaticani-roma?share'),
                          (0, 'Museo Reina Sofia', 'C. de Sta. Isabel, 52', '+34 917 74 10 00', 'Madrid', 'Spain', 'Non-movable', 'https://goo.gl/maps/hZaiZbRwQzhYPVdg7'),
                          (0, 'State Russian Museum', 'Inzhenernaya St, 4', '+7 812 595 42 48', 'Saint Petersburg', 'Russia', 'Art museum', 'https://goo.gl/maps/8bthKCjBfh6tuzaq6'),
                          (0, 'Metropolitan Museum of Art', '1000 5th Ave', '+1 212 535 7710', 'New York', 'United States', 'Art museum', 'https://goo.gl/maps/pcdiJcdp3XUdBg9Z8'),
                          (0, 'Museum of New Zaeland Te Papa Tongarewa', '55 Cable Street, Te Aro', '+64 4 381 7000', 'Wellington', 'New Zaeland', 'National museum', 'https://goo.gl/maps/YJ4fxr42tL2D1zrF7');
                          
INSERT INTO USER VALUES (0, 'admin', 'admin', 'Administrator', '1', 'admin@admin.com', 1, 'admintoken', 1, 0, 0),
						(0, 'user', 'user', 'User', '1', 'user@user.com', 0, null, 1, 0, 0);