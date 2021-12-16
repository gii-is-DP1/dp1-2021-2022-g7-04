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
INSERT INTO players(id,first_name,last_name,city,address,telephone,email,username) 
VALUES (6,'Nombre','Apellido','Seville','Reina Mercedes','12345678','player@player.com','player');

INSERT INTO minesweeper_board(id,background,height,width) VALUES (1,null,450,800);

INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(7,null,null,null,0,'UNPRESSED',0,0,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(9,null,null,null,0,'UNPRESSED',2,0,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(10,null,null,null,0,'UNPRESSED',3,0,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(15,null,null,null,0,'UNPRESSED',0,1,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(16,null,null,null,0,'UNPRESSED',0,2,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(17,null,null,null,0,'UNPRESSED',0,3,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(18,null,null,null,0,'UNPRESSED',2,1,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(19,null,null,null,0,'UNPRESSED',2,2,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(20,null,null,null,0,'UNPRESSED',2,3,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(21,null,null,null,0,'UNPRESSED',3,1,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(22,null,null,null,0,'UNPRESSED',3,2,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(23,null,null,null,0,'UNPRESSED',3,3,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(24,null,null,null,0,'UNPRESSED',2,3,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(8,null,null,null,0,'FLAG',1,0,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(25,null,null,null,0,'UNPRESSED',1,1,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(26,null,null,null,0,'UNPRESSED',1,2,1);
INSERT INTO cells(id,covered,flagged,has_mine,mines_around,type,x_position,y_position,minesweeper_board_id) VALUES
(27,null,null,null,0,'UNPRESSED',1,3,1);

