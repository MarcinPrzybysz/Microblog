DROP DATABASE  IF EXISTS `microblog`;

CREATE DATABASE  IF NOT EXISTS `microblog`;
USE `microblog`;

--
-- Table structure for table `user`


DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- Encrypted using BCrypt

-- Hash generator: https://www.bcryptcalculator.com/

-- Default passwords: test, for admin: admin
--

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('jan','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','Jan','Karski','jan@example.com'),
('marek','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','Marek','Aureliusz','marek.aureliusz@example.com'),
('agata','$2a$10$RJrtv3jpUF3uduoSxWQnNOENWFLaLRbqCTEZ4ukwvs..xsZLGYFo2','Agata','Meble','agata@example.com'),
('admin','$2a$10$qQCJ6l5WidSTSxSIHOsyJuw/1dRXIvgBLJ7WHdKGdFLcZPNoR97/i','admin','admin','admin@example.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_MODERATOR'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(4, 1),
(4, 2),
(4, 3);


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
("Testowy post, dokladnie o niczym", '2020-02-07 10:10:10',1),
("Drugi testowy post, dokladnie o niczym", '2018-05-05 12:10:10',1),
("Trzeci post, dokladnie o niczym", '2019-12-07 10:10:10',2)



