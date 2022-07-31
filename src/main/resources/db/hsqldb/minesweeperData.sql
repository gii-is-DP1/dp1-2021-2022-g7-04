-- Creating users and authorities
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin','admin');

INSERT INTO users(username,password,enabled) VALUES ('jugador1','jugador1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'jugador1','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador2','jugador2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'jugador2','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador3','jugador3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jugador3','player');


INSERT INTO users(username,password,enabled) VALUES ('jugador4','jugador4',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'jugador4','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador5','jugador5',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (9,'jugador5','player');

INSERT INTO users(username,password,enabled) VALUES ('player','player',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'player','player');


INSERT INTO users(username,password,enabled) VALUES ('jugador6','jugador6',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (10,'jugador6','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador7','jugador7',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'jugador7','player');


INSERT INTO users(username,password,enabled) VALUES ('jugador8','jugador8',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'jugador8','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador9','jugador9',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (13,'jugador9','player');

INSERT INTO users(username,password,enabled) VALUES ('jugador10','jugador10',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'jugador10','player');

INSERT INTO users(username,password,enabled) VALUES ('luis','luis',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'luis','player');

INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (6,'Nombre','Apellido','Seville','Reina Mercedes','12345678','player@player.com','player');


INSERT INTO administrator(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (1,'Administrator','Administrator','Seville','Reina Mercedes','666666666','admin@alum.es','admin');


INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (8,'Luis','Cerrato','Seville','Reina Mercedes','666666666','luicersan@alum.es','luis');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (9,'Nombre1','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador1');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (10,'Nombre2','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador2');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (11,'Nombre3','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador3');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (12,'Nombre4','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador4');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (13,'Nombre5','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador5');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (14,'Nombre6','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador6');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (15,'Nombre7','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador7');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (16,'Nombre8','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador8');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (17,'Nombre9','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador9');
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (18,'Nombre10','Apellido','Seville','Reina Mercedes','12345678','player@player.com','jugador10');
