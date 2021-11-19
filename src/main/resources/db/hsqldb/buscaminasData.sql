-- Creating users and authorities
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin','admin');

INSERT INTO jugadores(id,nombre,last_name,password) VALUES (1,'jugador1','lastnamejugador1','jugador1');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (2,'jugador2','lastnamejugador2','jugador2');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (3,'jugador3','lastnamejugador3','jugador3');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (4,'jugador4','lastnamejugador4','jugador4');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (5,'jugador5','lastnamejugador5','jugador5');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (6,'jugador6','lastnamejugador6','jugador6');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (7,'jugador7','lastnamejugador7','jugador7');
INSERT INTO jugadores(id,nombre,last_name,password) VALUES (8,'jugador1','lastnamejugador8','jugador8');