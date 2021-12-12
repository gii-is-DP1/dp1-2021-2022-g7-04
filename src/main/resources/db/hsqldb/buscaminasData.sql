-- Creating users and authorities
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin','admin');

INSERT INTO users(username,password,enabled) VALUES ('jugador1','jugador1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'jugador1','jugador');

INSERT INTO users(username,password,enabled) VALUES ('jugador2','jugador2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'jugador2','jugador');

INSERT INTO users(username,password,enabled) VALUES ('jugador3','jugador3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jugador3','jugador');

INSERT INTO users(username,password,enabled) VALUES ('player','player',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'player','player');
INSERT INTO players(id,first_name,last_name,username,city,address,telephone,email) VALUES (6,'Nombre','Apellido','player','Seville','Reina Mercedes','12345678','player@player.com');
