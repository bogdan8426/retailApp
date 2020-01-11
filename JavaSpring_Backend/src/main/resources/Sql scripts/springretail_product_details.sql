-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: springretail
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_details` (
  `product_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(600) DEFAULT NULL,
  `memorie_interna` varchar(255) DEFAULT NULL,
  `memorie_ram` varchar(255) DEFAULT NULL,
  `procesor` varchar(255) DEFAULT NULL,
  `rezolutie` varchar(255) DEFAULT NULL,
  `rezolutie_camera` varchar(255) DEFAULT NULL,
  `sistem_de_operare` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_details_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (11,'Lenovo 14\" Touch-Screen Laptop - Intel Pentium - 4GB Memory - 500GB Hard Drive Black ','4 GB','4 GB','I7','1500x1200','12','Intel'),(10,'Best camera, procesor dual core','8 GB','4 GB','intel','1200x1000','14','Android'),(12,'Iphone 7 Plus','64 GB','8 GB','SnapDragon','1270x920','12 Mp','iOS'),(13,'a','a','a','a','a','a','a'),(5,'Modelul Y520 utilizeaza cea de-a 7-a generatie de procesoare Intel Core i5/i7. Cu o performanta crescuta cu pana la 12% fata de generatia precedenta si cu 17% mai mult in ceea ce priveste performanta web, va puteti bucura de o experienta fluida, indiferent de task. Cea mai noua solutie grafica Y520 vine cu una dintre placile grafice NVIDIA GTX 1050/1050GTi/1060, oferind o experienta de gaming fluida si un numar mare de cadre pe minut.','9 GB','2 GB','i4','1400x1200','12','DOS');
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-24  9:24:12
