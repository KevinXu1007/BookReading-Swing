-- MySQL dump 10.11
--
-- Host: localhost    Database: bookdb
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  `book_name` varchar(50) default NULL,
  `ISBN` varchar(20) default NULL,
  `author` varchar(50) default NULL,
  `pages` int(11) default NULL,
  `publisher` varchar(50) default NULL,
  `language` varchar(20) default NULL,
  `publish_date` date default NULL,
  `description` varchar(500) default NULL,
  `content` varchar(1000) default NULL,
  `file_name` varchar(50) default NULL,
  `category_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'BIG JAVA','123-456-788','Cay',1267,'campany','English','2015-05-06','Many thanks to Beth Lang Golub, Don Fowley, Elizabeth Mills, Katherine Willis, Jenny Welter, Wendy Ashenberg, Lisa Gee, Kevin Holm, and Tim Lindner at John Wiley & Sons, and Vickie Piercey at Publishing Services for their help with this project. An especially deep acknowledgment and thanks goes to Cindy Johnson for her hard work, sound judgment, and amazing attention to detail.','Many thanks to Beth Lang Golub, Don Fowley, Elizabeth Mills, Katherine Willis, Jenny Welter, Wendy Ashenberg, Lisa Gee, Kevin Holm, and Tim Lindner at John Wiley & Sons, and Vickie Piercey at Publishing Services for their help with this project. An especially deep acknowledgment and thanks goes to Cindy Johnson for her hard work, sound judgment, and amazing attention to detail.','123-456-788.pdf',4),(2,'Wild Nature','567-896-5598','Gray',125,'ABC','English','2013-06-08','','','567-896-5598.pdf',5),(4,'World War II','98653-23876-000','Max M',123,'vv','13','2000-08-09','','','98653-23876-000.pdf',6),(5,'Tiger','569568-5960','Bob',99,'MNC','English','1988-10-26','','','569568-5960.pdf',5);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_category`
--

DROP TABLE IF EXISTS `book_category`;
CREATE TABLE `book_category` (
  `id` int(11) NOT NULL auto_increment,
  `category_id` int(11) default NULL,
  `book_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `book_category`
--

LOCK TABLES `book_category` WRITE;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL auto_increment,
  `category_name` varchar(50) default NULL,
  `parent_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Book Category',0),(2,'Science',1),(3,'History',1),(4,'Computer',2),(5,'Nature',2),(6,'World',3),(7,'America',3),(10,'Communication',2),(11,'Chemical',2),(12,'France',3);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL auto_increment,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) default NULL,
  `isAdmin` varchar(1) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','123','admin@gmail.com','1'),(2,'jack','123456','jack','1'),(3,'max','123456','max@yahoo.com','0'),(4,'kevin','123456','kevin@gmail.com','1'),(5,'andy','123456','andy@yahoo.com','0'),(6,'jenny','123456','jenny@gmail.com','0'),(7,'aaron','123456','aaron@apple.com','1'),(8,'bb','123456','bb@bb.com','1'),(9,'cc','123456','cc@cc.com','1'),(10,'dd','123456','dd@d.com','1'),(11,'ee','123456','ee@ee.com','1'),(13,'ggg','123456','gg@gg.com','0'),(15,'jj','123456','jj@jj.com','0');
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

-- Dump completed on 2015-12-17  1:59:15
