CREATE DATABASE  IF NOT EXISTS `microblog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `microblog`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: microblog
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `content` varchar(3000) NOT NULL,
                        `date` timestamp NOT NULL,
                        `rating` int DEFAULT '0',
                        `user_id` int NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK_USER_06` (`user_id`),
                        CONSTRAINT `FK_USER_06` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES
(1,'Vestibulum faucibus varius scelerisque. Curabitur nibh neque, tristique id vehicula sed, feugiat vitae tellus. Quisque in neque dui. Cras congue urna id elit vehicula tincidunt. Vivamus nec viverra elit, vitae lacinia odio. Integer vestibulum finibus diam lobortis lobortis. Sed id luctus augue. Morbi eget mollis lorem, vitae varius tellus. Vestibulum sed venenatis tortor. Morbi sagittis pulvinar velit, sed aliquam metus ullamcorper nec. Nunc facilisis eros ante, ac tincidunt tortor facilisis eu. ','2020-02-07 09:10:10',0,4),
(2,'Drugi testowy post, dokladnie o niczym','2018-05-05 10:10:10',0,3),
(3,'Trzeci post, dokladnie o niczym','2019-12-07 09:10:10',0,2),
(4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras diam leo, sodales non interdum vel, dignissim at augue. Pellentesque cursus, urna sit amet fringilla semper, eros ex malesuada sem, quis iaculis lectus enim vitae odio. Vestibulum at lobortis nulla. Aliquam condimentum laoreet nisi in egestas. Nam sit amet risus eu ex consequat tincidunt. Proin sed viverra libero, eu luctus quam. Quisque dignissim dignissim ullamcorper. Nam euismod metus justo, sit amet suscipit quam faucibus eu. Sed tempor tristique urna, a gravida libero mattis ac. Sed sollicitudin mauris eget augue efficitur, sed fringilla ipsum aliquam. Nunc sed faucibus quam. Ut hendrerit quis lacus sit amet laoreet. Duis hendrerit id elit vitae bibendum. Pellentesque imperdiet ipsum sed ex posuere, sed posuere lacus fringilla. Aenean iaculis, lorem et efficitur condimentum, ligula tellus fermentum lorem, non pellentesque arcu odio quis massa. ','2020-02-18 12:58:00',0,4);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `username` varchar(50) NOT NULL,
                        `password` char(80) NOT NULL,
                        `email` varchar(50) NOT NULL,
                        `enabled` tinyint(1) DEFAULT '1',
                        `avatar` varchar(30) DEFAULT 'default.png',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe','admin@admin.com',1,'default.png'),(2,'marek','$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe','marek.aureliusz@example.com',1,'default.png'),(3,'agataMeble','$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe','agata@example.com',1,'agata.png'),(4,'jan','$2a$10$2UklC5TcPfzEy3jEm0NhhuAjs87mBEYyxeJbArdyJrcWcMlDpwXXe','jan@example.com',1,'jan.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
                               `user_id` int NOT NULL,
                               `role_id` int NOT NULL,
                               PRIMARY KEY (`user_id`,`role_id`),
                               KEY `FK_ROLE_idx` (`role_id`),
                               CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                               CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (2,1),(3,1),(4,1),(1,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-21 12:45:44
