DROP DATABASE IF EXISTS `microblog`;

CREATE DATABASE IF NOT EXISTS `microblog`;
USE `microblog`;



DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`       int(11)     NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL,
    `password` char(80)    NOT NULL,
    `email`    varchar(50) NOT NULL,
    `enabled`  tinyint(1)  DEFAULT 1,
    `avatar`   varchar(30) DEFAULT 'default.png',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1;


-- Encrypted using BCrypt
-- Hash generator: https://www.bcryptcalculator.com/
-- Default passwords: admin


INSERT INTO `user` (username, password, email,avatar)
VALUES ('admin', '$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe', 'admin@admin.com','default.png'),
       ('marek', '$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe', 'marek.aureliusz@example.com','default.png'),
       ('agata', '$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe', 'agata@example.com','agata.png'),
       ('jan', '$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe', 'jan@example.com','jan.jpg');




DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = latin1;

INSERT INTO `role` (name)
VALUES ('ROLE_USER'),
       ('ROLE_MODERATOR'),
       ('ROLE_ADMIN');




DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,

    PRIMARY KEY (`user_id`, `role_id`),

    KEY `FK_ROLE_idx` (`role_id`),

    CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
        REFERENCES `role` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id, role_id)
VALUES (1, 3),
       (2, 1),
       (3, 1),
       (4, 1);




DROP TABLE IF EXISTS `post`;

CREATE TABLE `post`
(
    `id`      int(11)       NOT NULL AUTO_INCREMENT,
    `content` varchar(3000) NOT NULL,
    `date`    TIMESTAMP     NOT NULL,
    `rating`  int(5) DEFAULT 0,
    `user_id` int(11)       NOT NULL,
    PRIMARY KEY (`id`),

    CONSTRAINT `FK_USER_06` FOREIGN KEY (`user_id`)
        REFERENCES `user` (`id`)
    -- ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO `post` (content, date, user_id)
VALUES ('Testowy post, dokladnie o niczym', '2020-02-07 10:10:10', 4),
       ('Drugi testowy post, dokladnie o niczym', '2018-05-05 12:10:10', 3),
       ('Trzeci post, dokladnie o niczym', '2019-12-07 10:10:10', 2)



