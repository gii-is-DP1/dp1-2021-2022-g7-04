-- Creating users and authorities
INSERT INTO users(username,password,enabled) VALUES ('admin','admin',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin','admin');

INSERT INTO users(username,password,enabled) VALUES ('player1','player1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'player1','player');

INSERT INTO users(username,password,enabled) VALUES ('player2','player2',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'player2','player');

INSERT INTO users(username,password,enabled) VALUES ('player3','player3',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'player3','player');

