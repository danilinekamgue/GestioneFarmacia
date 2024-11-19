CREATE DATABASE IF NOT EXISTS FARMACIA;

use FARMACIA;

CREATE TABLE IF NOT EXISTS users (
 email varchar(100) PRIMARY KEY NOT NULL,
 nome varchar(100),
 password varchar(100),
 role varchar(100)
);

CREATE TABLE IF NOT EXISTS farmaci (
 id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
 nome varchar(100) ,
 descrizione varchar(100),
 prezzo double,
 quantita int
);

CREATE TABLE IF NOT EXISTS ordine (
 id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
 email_user varchar(100),
 prezzo double,
 consegnato boolean,
 FOREIGN KEY (email_user) REFERENCES users(email)
);

CREATE TABLE IF NOT EXISTS ordine_farmaci (
 id_ordine int NOT NULL,
 id_famaco int NOT NULL,
 quantita int,
 FOREIGN KEY (id_famaco) REFERENCES farmaci(id),
 FOREIGN KEY (id_ordine) REFERENCES ordine(id),
 CONSTRAINT pk_farmaco_ordine PRIMARY KEY (id_famaco, id_ordine)
);
