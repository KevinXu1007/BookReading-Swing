create database bookdb;
use bookdb;
create table users(id int PRIMARY KEY AUTO_INCREMENT,user_name varchar(20) not null, password varchar(20) not null, email varchar(20), isAdmin char);
create table category(id int PRIMARY KEY AUTO_INCREMENT, category_name varchar(50), parent_id int);
create table book(id int PRIMARY KEY AUTO_INCREMENT, category_id int, book_name varchar(50), ISBN varchar(20), author varchar(50), pages int, publisher varchar(50), language varchar(20), publish_date date, description varchar(500), content varchar(1000), file_name varchar(50));