-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: caravan
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caravans`
--

DROP TABLE IF EXISTS `caravans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `caravans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `toGoal` int(11) NOT NULL DEFAULT '1000',
  `money` int(11) NOT NULL DEFAULT '1000',
  `food` int(11) NOT NULL DEFAULT '50',
  `medicine` int(11) NOT NULL DEFAULT '2',
  `ammo` int(11) NOT NULL DEFAULT '20',
  `ox` int(11) NOT NULL DEFAULT '4',
  `canCarry` int(11) NOT NULL DEFAULT '400',
  `lastSaved` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `caravans_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caravans`
--

LOCK TABLES `caravans` WRITE;
/*!40000 ALTER TABLE `caravans` DISABLE KEYS */;
/*!40000 ALTER TABLE `caravans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `caravanId` int(11) NOT NULL,
  `memberFirst` varchar(30) NOT NULL DEFAULT 'Cvetkovic',
  `isAliveFirst` tinyint(1) NOT NULL DEFAULT '1',
  `sickLevelFirst` int(11) NOT NULL DEFAULT '0',
  `memberSecond` varchar(30) NOT NULL DEFAULT 'Savic',
  `isAliveSecond` tinyint(1) NOT NULL DEFAULT '1',
  `sickLevelSecond` int(11) NOT NULL DEFAULT '0',
  `memberThird` varchar(30) NOT NULL DEFAULT 'Georgijevski',
  `isAliveThird` tinyint(1) NOT NULL DEFAULT '1',
  `sickLevelThird` int(11) NOT NULL DEFAULT '0',
  `memberFourth` varchar(30) NOT NULL DEFAULT 'Bogdanovic',
  `isAliveFourth` tinyint(1) NOT NULL DEFAULT '1',
  `sickLevelFourth` int(11) NOT NULL DEFAULT '0',
  `memberFifth` varchar(30) NOT NULL DEFAULT 'Vitkovic',
  `isAliveFifth` tinyint(1) NOT NULL DEFAULT '1',
  `sickLevelFifth` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `caravanId` (`caravanId`),
  CONSTRAINT `members_ibfk_1` FOREIGN KEY (`caravanId`) REFERENCES `caravans` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `displayName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `isValid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin','Admin','admin@mail',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-14 13:34:08
