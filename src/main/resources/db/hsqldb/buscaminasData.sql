-- Creating users and authorities
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin','admin');

INSERT INTO jugadores(id,nombre,last_name,password) VALUES (1,'jugador1','lastnamejugador12','jugador1');