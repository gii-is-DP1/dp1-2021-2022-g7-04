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

-- MINESWEEPER BOARDS
-- Jugador 1
INSERT INTO minesweeper_board(id,player_name,background,width,height)
VALUES (1,'jugador1',null,800,400);

-- Jugador 5
INSERT INTO minesweeper_board(id,player_name,background,width,height)
VALUES (2,'jugador5',null,800,400);

-- Jugador 7
INSERT INTO minesweeper_board(id,player_name,background,width,height)
VALUES (3,'jugador7',null,800,400);

-- Jugador 8
INSERT INTO minesweeper_board(id,player_name,background,width,height)
VALUES (4,'jugador8',null,800,400);

-- AUDITS
-- Jugador 1
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (1,'2022-06-27 10:11:37','2022-06-27 10:11:47','jugador1','CANCELLED','BEGINNER',TRUE,1);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (2,'2022-06-28 11:11:48','2022-06-28 11:12:15','jugador1','LOST','BEGINNER',TRUE,1);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (3,'2022-06-28 11:11:59','2022-06-28 11:14:57','jugador1','WON','BEGINNER',TRUE,1);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (4,'2022-06-28 11:14:57','2022-06-28 11:15:06','jugador1','CANCELLED','BEGINNER',TRUE,1);

-- Jugador 5
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (5,'2022-06-27 10:11:37','2022-06-27 10:11:47','jugador5','CANCELLED','MEDIUM',TRUE,2);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (6,'2022-06-28 11:11:48','2022-06-28 11:12:15','jugador5','LOST','BEGINNER',TRUE,2);

-- Jugador 7
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (7,'2022-06-27 10:11:37','2022-06-27 10:11:47','jugador7','CANCELLED','ACE',TRUE,3);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (8,'2022-06-28 11:11:48','2022-06-28 11:12:15','jugador7','LOST','BEGINNER',TRUE,3);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (9,'2022-06-28 11:12:21','2022-06-28 11:14:57','jugador7','WON','BEGINNER',TRUE,3);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (10,'2022-06-28 11:14:59','2022-06-28 11:19:09','jugador7','WON','BEGINNER',TRUE,3);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (11,'2022-06-28 11:19:19','2022-06-28 11:21:47','jugador7','WON','BEGINNER',TRUE,3);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (12,'2022-06-28 11:21:57','2022-06-28 11:33:04','jugador7','WON','MEDIUM',TRUE,3);

-- Jugador 8
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (13,'2022-06-27 14:11:37','2022-06-27 14:11:56','jugador8','CANCELLED','BEGINNER',TRUE,4);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (14,'2022-06-28 15:11:48','2022-06-28 15:12:15','jugador8','LOST','BEGINNER',TRUE,4);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (15,'2022-06-28 16:11:59','2022-06-28 16:23:57','jugador8','WON','ACE',TRUE,4);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (16,'2022-06-28 20:14:57','2022-06-28 20:26:10','jugador8','WON','MEDIUM',TRUE,4);
INSERT INTO audits(id,start_date,end_date,player,game_status,difficulty,is_finished,minesweeper_board_id)
VALUES (17,'2022-06-28 20:19:27','2022-06-28 20:19:30','jugador8','CANCELLED','MEDIUM',TRUE,4);

-- ACHIEVEMENTS
INSERT INTO achievements(id,level_achievement,games) 
VALUES (1,'BRONZE',10);
INSERT INTO achievements(id,level_achievement,games)
VALUES (2,'SILVER',25);
INSERT INTO achievements(id,level_achievement,games)
VALUES (3,'GOLD',50);

-- PLAYER STATS
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (1,'jugador1',1,11,18,46);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (2,'jugador2',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (3,'jugador3',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (4,'jugador4',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (5,'jugador5',1,4,8,22);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (6,'jugador6',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (7,'jugador7',1,25,33,79);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (8,'jugador8',1,23,30,93);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (9,'jugador9',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (10,'jugador10',0,0,0,0);
INSERT INTO player_stats(id,player,activated_mines,guessed_mines,total_flags,cells_clicked) 
VALUES (11,'player',0,0,0,0);
