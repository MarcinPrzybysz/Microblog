DROP DATABASE  IF EXISTS `microblog`;

CREATE DATABASE  IF NOT EXISTS `microblog`;
USE `microblog`;

--
-- Table structure for table `user`


DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `email` varchar(50) NOT NULL,
  `enabled`tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


-- Encrypted using BCrypt

-- Hash generator: https://www.bcryptcalculator.com/

-- Default passwords: test, for admin: admin
--

INSERT INTO `user` (user_name,password,email)
VALUES 
('jan','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','jan@example.com'),
('marek','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','marek.aureliusz@example.com'),
('agata','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','agata@example.com'),
('admin','$2a$10$qQCJ6l5WidSTSxSIHOsyJuw/1dRXIvgBLJ7WHdKGdFLcZPNoR97/i','admin@example.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role` varchar(50) DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`),
  constraint fk_authorities_users foreign key(user_id) references user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--


INSERT INTO `authorities` (user_id,role)
VALUES 
(1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_USER');



DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(3000) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `rating` int(5) DEFAULT 0,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
    
  CONSTRAINT `FK_USER_06` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
 -- ON DELETE NO ACTION ON UPDATE NO ACTION
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `post` (content,date,user_id)
VALUES 
('Testowy post, dokladnie o niczym', '2020-02-07 10:10:10',1),
('Drugi testowy post, dokladnie o niczym', '2018-05-05 12:10:10',3),
('Trzeci post, dokladnie o niczym', '2019-12-07 10:10:10',2)



