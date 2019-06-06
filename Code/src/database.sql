-- MySQL dump 10.13  Distrib 8.0.16, for osx10.13 (x86_64)
--
-- Host: localhost    Database: OOD
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cashier`
--

DROP TABLE IF EXISTS `cashier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cashier` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `is_manager` tinyint(1) NOT NULL,
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cashier_id_uindex` (`id`),
  UNIQUE KEY `cashier_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cashier`
--

LOCK TABLES `cashier` WRITE;
/*!40000 ALTER TABLE `cashier` DISABLE KEYS */;
INSERT INTO `cashier` VALUES (1,'Admin','Admin',1,'',''),(2,'Peyman','Kiasari',0,'peyman','12345');
/*!40000 ALTER TABLE `cashier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `parent` int(11) NOT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`parent`),
  UNIQUE KEY `customer_parent_uindex` (`parent`),
  CONSTRAINT `customer_customer_salesman_id_fk` FOREIGN KEY (`parent`) REFERENCES `customer_salesman` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'Dorna','Abdolazimi',0),(5,'testname','testlastname',0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_salesman`
--

DROP TABLE IF EXISTS `customer_salesman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer_salesman` (
  `id` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone_number` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_customer` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_salesman_id_uindex` (`id`),
  UNIQUE KEY `customer_salesman_email_uindex` (`email`),
  UNIQUE KEY `customer_salesman_phone_number_uindex` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_salesman`
--

LOCK TABLES `customer_salesman` WRITE;
/*!40000 ALTER TABLE `customer_salesman` DISABLE KEYS */;
INSERT INTO `customer_salesman` VALUES (1,'salesman1@sales.org','0911111111',0),(2,'customer1@gmail.com','09100000000',1),(3,'test@test.com','09133333333',1),(4,'dorna.abdolazimi@gmail.com','0913',1),(5,'dadfadfgsf@test.com','0.7268112225573633',1),(6,'dadfadfgsf2@test.com','0.0450691949564912',0),(7,'email@email.com','0.37702226892924984',0);
/*!40000 ALTER TABLE `customer_salesman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_rel`
--

DROP TABLE IF EXISTS `group_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `group_rel` (
  `super_group` int(11) NOT NULL,
  `sub_group` int(11) NOT NULL,
  PRIMARY KEY (`sub_group`),
  UNIQUE KEY `group_rel_sub_group_uindex` (`sub_group`),
  KEY `group_rel_group_id_fk` (`super_group`),
  CONSTRAINT `group_rel_group_id_fk` FOREIGN KEY (`super_group`) REFERENCES `item_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `group_rel_group_id_fk_2` FOREIGN KEY (`sub_group`) REFERENCES `item_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_rel`
--

LOCK TABLES `group_rel` WRITE;
/*!40000 ALTER TABLE `group_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `current_price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id_uindex` (`id`),
  UNIQUE KEY `item_name_uindex` (`name`),
  KEY `item_item_group_id_fk` (`group_id`),
  CONSTRAINT `item_item_group_id_fk` FOREIGN KEY (`group_id`) REFERENCES `item_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'San Star Banana Juice',2,90000,0),(2,'Sahar Bread',1,30000,0),(3,'KitKat',1,30000,0),(4,'Dorna Item',1,2000,0),(5,'test item',1,24234,0),(6,'ssdfa',1,242,0),(7,'fdsgsg',1,243,3423),(8,'Dorna itm',1,24234,324),(9,'fasfa',1,234,233),(10,'test',1,30000,2000),(11,'testItem',1,2343,45),(12,'testt',1,35324,500),(13,'an item',1,40000,9),(14,'test10',1,234,224),(15,'item100',1,234,184),(16,'item200',1,243,2343),(17,'item2000',1,234,314);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_group`
--

DROP TABLE IF EXISTS `item_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item_group` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_group_id_uindex` (`id`),
  UNIQUE KEY `item_group_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_group`
--

LOCK TABLES `item_group` WRITE;
/*!40000 ALTER TABLE `item_group` DISABLE KEYS */;
INSERT INTO `item_group` VALUES (2,'drinks'),(1,'general');
/*!40000 ALTER TABLE `item_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_quantity`
--

DROP TABLE IF EXISTS `item_quantity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `item_quantity` (
  `item_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `end_time` time DEFAULT NULL,
  `start_time` time NOT NULL,
  UNIQUE KEY `item_id_3` (`item_id`,`start_date`,`start_time`),
  CONSTRAINT `item_quantity_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_quantity`
--

LOCK TABLES `item_quantity` WRITE;
/*!40000 ALTER TABLE `item_quantity` DISABLE KEYS */;
INSERT INTO `item_quantity` VALUES (4,'2019-06-06',NULL,0,NULL,'21:10:11'),(9,'2019-06-06',NULL,233,NULL,'23:38:47'),(13,'2019-06-06',NULL,0,NULL,'21:00:00'),(14,'2019-06-06',NULL,234,NULL,'23:39:23'),(15,'2019-06-06',NULL,234,NULL,'23:41:15'),(15,'2019-06-06',NULL,194,NULL,'23:51:39'),(15,'2019-06-06',NULL,184,NULL,'23:54:57'),(16,'2019-06-06',NULL,2353,NULL,'23:43:24'),(17,'2019-06-06',NULL,324,NULL,'23:47:11');
/*!40000 ALTER TABLE `item_quantity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `off`
--

DROP TABLE IF EXISTS `off`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `off` (
  `endDate` date NOT NULL,
  `startDate` date NOT NULL,
  `item_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `off_id_uindex` (`id`),
  KEY `off_item_id_fk` (`item_id`),
  CONSTRAINT `off_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `off`
--

LOCK TABLES `off` WRITE;
/*!40000 ALTER TABLE `off` DISABLE KEYS */;
/*!40000 ALTER TABLE `off` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `off_customer_rank`
--

DROP TABLE IF EXISTS `off_customer_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `off_customer_rank` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `off_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `off_customer_rank_id_uindex` (`id`),
  UNIQUE KEY `off_customer_rank_name_uindex` (`name`),
  UNIQUE KEY `off_customer_rank_off_id_uindex` (`off_id`),
  CONSTRAINT `off_customer_rank_off_id_fk` FOREIGN KEY (`off_id`) REFERENCES `off` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `off_customer_rank`
--

LOCK TABLES `off_customer_rank` WRITE;
/*!40000 ALTER TABLE `off_customer_rank` DISABLE KEYS */;
/*!40000 ALTER TABLE `off_customer_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `order_item` (
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`item_id`),
  KEY `order_item_item_id_fk` (`item_id`),
  CONSTRAINT `order-item_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_item_item_id_fk` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,1000,80000000),(11,2,1,30000),(12,2,1,30000),(12,3,2,60000),(13,3,5,150000),(14,3,10,300000),(15,3,10,300000),(16,3,10,300000),(17,2,10,300000),(18,1,10,900000),(19,6,10,2420),(20,6,10,2420),(21,10,10,300000),(22,13,10,400000),(23,13,10,400000),(24,13,10,400000),(25,13,10,400000),(26,13,10,400000),(27,13,10,400000),(28,3,10,300000),(29,3,10,300000),(30,3,10,300000),(31,13,10,400000),(32,13,10,400000),(33,13,10,400000),(34,13,10,400000),(35,13,10,400000),(36,13,10,400000),(37,13,10,400000),(38,13,10,400000),(39,13,10,400000),(40,3,10,300000),(41,3,10,300000),(42,2,10,300000),(45,13,10,400000),(46,13,10,400000),(47,13,10,400000),(48,4,10,20000),(49,13,10,400000),(50,13,1,40000),(51,9,1,234),(52,14,10,2340),(53,15,10,2340),(55,16,10,2430),(56,17,10,2340),(57,15,10,2340),(58,15,10,2340),(59,15,10,2340),(60,15,10,2340);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `is_selling` tinyint(1) NOT NULL,
  `other_side` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `final_price` int(11) NOT NULL,
  `done_price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id_uindex` (`id`),
  KEY `order_customer_salesman_id_fk` (`other_side`),
  CONSTRAINT `order_customer_salesman_id_fk` FOREIGN KEY (`other_side`) REFERENCES `customer_salesman` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,0,1,'2019-05-23 23:50:48',0,0),(3,1,3,'1970-01-01 00:00:00',0,0),(4,1,3,'1970-01-01 00:00:00',0,0),(5,1,3,'1970-01-01 00:00:00',0,0),(6,1,3,'1970-01-01 00:00:00',0,0),(7,1,3,'1970-01-01 00:00:00',0,0),(8,0,1,'2019-06-06 00:00:00',0,0),(9,0,1,'2019-06-06 00:00:00',0,0),(10,0,1,'2019-06-06 00:00:00',0,0),(11,0,1,'2019-06-06 00:00:00',0,0),(12,0,1,'2019-06-06 00:00:00',0,0),(13,0,1,'2019-06-06 00:00:00',150000,0),(14,0,1,'2019-06-06 00:00:00',300000,0),(15,0,1,'2019-06-06 00:00:00',300000,0),(16,0,1,'2019-06-06 00:00:00',300000,0),(17,0,1,'2019-06-06 00:00:00',300000,0),(18,0,1,'2019-06-06 00:00:00',900000,0),(19,0,1,'2019-06-06 00:00:00',2420,0),(20,0,1,'2019-06-06 00:00:00',2420,0),(21,0,1,'2019-06-06 00:00:00',300000,0),(22,0,1,'2019-06-06 00:00:00',400000,0),(23,0,1,'2019-06-06 00:00:00',400000,0),(24,0,1,'2019-06-06 00:00:00',400000,0),(25,0,1,'2019-06-06 00:00:00',400000,0),(26,0,1,'2019-06-06 00:00:00',400000,0),(27,0,1,'2019-06-06 00:00:00',400000,0),(28,0,1,'2019-06-06 00:00:00',300000,0),(29,0,1,'2019-06-06 00:00:00',300000,0),(30,0,1,'2019-06-06 00:00:00',300000,0),(31,0,1,'2019-06-06 00:00:00',400000,0),(32,1,1,'2019-06-06 00:00:00',400000,0),(33,1,1,'2019-06-06 00:00:00',400000,0),(34,1,1,'2019-06-06 00:00:00',400000,0),(35,0,1,'2019-06-06 00:00:00',400000,0),(36,0,1,'2019-06-06 00:00:00',400000,0),(37,0,1,'2019-06-06 00:00:00',400000,0),(38,0,1,'2019-06-06 00:00:00',400000,0),(39,0,1,'2019-06-06 00:00:00',400000,0),(40,0,1,'2019-06-06 00:00:00',300000,0),(41,0,1,'2019-06-06 00:00:00',300000,0),(42,0,1,'2019-06-06 00:00:00',300000,0),(43,1,1,'2019-06-06 00:00:00',0,0),(44,1,1,'2019-06-06 00:00:00',0,0),(45,1,1,'2019-06-06 00:00:00',400000,0),(46,1,1,'2019-06-06 00:00:00',400000,0),(47,1,1,'2019-06-06 00:00:00',400000,0),(48,0,1,'2019-06-06 00:00:00',20000,0),(49,0,1,'2019-06-06 00:00:00',400000,0),(50,1,1,'2019-06-06 00:00:00',40000,0),(51,1,1,'2019-06-06 00:00:00',234,0),(52,0,1,'2019-06-06 00:00:00',2340,0),(53,0,1,'2019-06-06 00:00:00',2340,0),(54,0,1,'2019-06-06 00:00:00',0,0),(55,0,1,'2019-06-06 00:00:00',2430,0),(56,0,1,'2019-06-06 00:00:00',2340,0),(57,0,1,'2019-06-06 00:00:00',2340,0),(58,0,1,'2019-06-06 00:00:00',2340,0),(59,0,1,'2019-06-06 00:00:00',2340,0),(60,0,1,'2019-06-06 00:00:00',2340,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `price` (
  `item` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `date` date NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `price_id_uindex` (`id`),
  UNIQUE KEY `date` (`date`,`item`),
  KEY `price_item_id_fk` (`item`),
  CONSTRAINT `price_item_id_fk` FOREIGN KEY (`item`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesman`
--

DROP TABLE IF EXISTS `salesman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `salesman` (
  `parent` int(11) NOT NULL,
  `official_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`parent`),
  UNIQUE KEY `salesman_official_name_uindex` (`official_name`),
  UNIQUE KEY `salesman_parent_uindex` (`parent`),
  CONSTRAINT `salesman_customer_salesman_id_fk` FOREIGN KEY (`parent`) REFERENCES `customer_salesman` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesman`
--

LOCK TABLES `salesman` WRITE;
/*!40000 ALTER TABLE `salesman` DISABLE KEYS */;
INSERT INTO `salesman` VALUES (1,' San Star Company'),(6,'nameofficial'),(7,'salesmantest');
/*!40000 ALTER TABLE `salesman` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-07  0:00:04
