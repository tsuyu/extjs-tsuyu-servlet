-- MySQL dump 10.13  Distrib 5.1.41, for Win32 (ia32)
--
-- Host: localhost    Database: tsuyu
-- ------------------------------------------------------
-- Server version	5.1.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accordian`
--

DROP TABLE IF EXISTS `accordian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accordian` (
  `accordian_id` int(11) NOT NULL AUTO_INCREMENT,
  `accordian_sequence` int(11) NOT NULL,
  `accordian_name` varchar(50) NOT NULL,
  `accordian_description` varchar(50) DEFAULT NULL,
  `accordian_icon` varchar(50) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`accordian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accordian`
--

LOCK TABLES `accordian` WRITE;
/*!40000 ALTER TABLE `accordian` DISABLE KEYS */;
INSERT INTO `accordian` VALUES (7,1,'Profile',NULL,NULL,NULL,NULL,NULL,NULL),(8,2,'Setting',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `accordian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `signin_id` int(11) NOT NULL,
  `address1` varchar(225) NOT NULL,
  `address2` varchar(225) NOT NULL,
  PRIMARY KEY (`address_id`),
  KEY `signin_id` (`signin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog_model`
--

DROP TABLE IF EXISTS `catalog_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog_model` (
  `cm_id` int(10) NOT NULL AUTO_INCREMENT,
  `id` varchar(50) DEFAULT '0',
  `text` varchar(50) DEFAULT '0',
  `leaf` varchar(1) DEFAULT NULL,
  `node` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog_model`
--

LOCK TABLES `catalog_model` WRITE;
/*!40000 ALTER TABLE `catalog_model` DISABLE KEYS */;
INSERT INTO `catalog_model` VALUES (1,'newq',' Profile','1','2'),(2,'folder/new','Edit  Profile','1','2'),(3,'2','Profil','0','root'),(4,'3','Setting','0','root'),(5,'leaf','Leaf  Menu','1','3');
/*!40000 ALTER TABLE `catalog_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `children`
--

DROP TABLE IF EXISTS `children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `children` (
  `children_id` int(11) NOT NULL AUTO_INCREMENT,
  `accordian_id` int(11) NOT NULL,
  `children_name` varchar(50) NOT NULL,
  `children_sequence` int(11) NOT NULL,
  `children_description` varchar(200) DEFAULT NULL,
  `children_path` varchar(50) NOT NULL,
  `children_icon` varchar(50) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`children_id`),
  KEY `accordian_uniqueId` (`accordian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children`
--

LOCK TABLES `children` WRITE;
/*!40000 ALTER TABLE `children` DISABLE KEYS */;
INSERT INTO `children` VALUES (5,7,'Chilll',1,NULL,'lor.jsp',NULL,NULL,NULL,NULL,NULL),(6,8,'No',1,NULL,'33',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaf`
--

DROP TABLE IF EXISTS `leaf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `leaf` (
  `leaf_id` int(11) NOT NULL AUTO_INCREMENT,
  `children_id` int(11) NOT NULL,
  `leaf_sequence` int(11) NOT NULL,
  `leaf_name` varchar(50) NOT NULL,
  `leaf_description` varchar(200) DEFAULT NULL,
  `leaf_mapper` varchar(100) NOT NULL,
  `leaf_icon` varchar(100) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`leaf_id`),
  KEY `accordian_id` (`children_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaf`
--

LOCK TABLES `leaf` WRITE;
/*!40000 ALTER TABLE `leaf` DISABLE KEYS */;
INSERT INTO `leaf` VALUES (1,5,1,'naruto',NULL,'ko',NULL,NULL,NULL,NULL,NULL),(2,6,1,'sasuke',NULL,'jk',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `leaf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_in`
--

DROP TABLE IF EXISTS `sign_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sign_in` (
  `signin_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT '0',
  `access_level` varchar(50) DEFAULT '0',
  PRIMARY KEY (`signin_id`),
  KEY `user_id` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_in`
--

LOCK TABLES `sign_in` WRITE;
/*!40000 ALTER TABLE `sign_in` DISABLE KEYS */;
INSERT INTO `sign_in` VALUES (1,'root','48690','root');
/*!40000 ALTER TABLE `sign_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `signin_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`signin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@tsuyu.org');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-03-17 17:30:06
