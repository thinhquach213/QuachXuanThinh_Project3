-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: qxtmerrychristmas
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `qxt_category`
--

DROP TABLE IF EXISTS `qxt_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_category` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_name` varchar(255) NOT NULL,
  PRIMARY KEY (`qxt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_category`
--

LOCK TABLES `qxt_category` WRITE;
/*!40000 ALTER TABLE `qxt_category` DISABLE KEYS */;
INSERT INTO `qxt_category` VALUES (1,'Đồ trang trí Noel'),(2,'Đèn LED'),(3,'Vòng nguyệt quế'),(4,'Ông già Noel & Elf'),(5,'Hộp quà'),(6,'Phụ kiện Giáng Sinh');
/*!40000 ALTER TABLE `qxt_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qxt_order_items`
--

DROP TABLE IF EXISTS `qxt_order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_order_items` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_order_id` bigint(20) NOT NULL,
  `qxt_product_id` bigint(20) NOT NULL,
  `qxt_product_name` varchar(255) NOT NULL,
  `qxt_product_price` double NOT NULL,
  `qxt_quantity` int(11) NOT NULL,
  PRIMARY KEY (`qxt_id`),
  KEY `fk_qxt_orderitems_order` (`qxt_order_id`),
  CONSTRAINT `fk_qxt_orderitems_order` FOREIGN KEY (`qxt_order_id`) REFERENCES `qxt_orders` (`qxt_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_order_items`
--

LOCK TABLES `qxt_order_items` WRITE;
/*!40000 ALTER TABLE `qxt_order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `qxt_order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qxt_orders`
--

DROP TABLE IF EXISTS `qxt_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_orders` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_customer_name` varchar(150) NOT NULL,
  `qxt_phone` varchar(20) NOT NULL,
  `qxt_address` varchar(255) NOT NULL,
  `qxt_note` varchar(500) DEFAULT NULL,
  `qxt_total_amount` double NOT NULL,
  `qxt_status` varchar(30) NOT NULL,
  `qxt_created_at` datetime NOT NULL,
  `qxt_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`qxt_id`),
  KEY `fk_qxt_order_user` (`qxt_user_id`),
  CONSTRAINT `fk_qxt_order_user` FOREIGN KEY (`qxt_user_id`) REFERENCES `qxt_users` (`qxt_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_orders`
--

LOCK TABLES `qxt_orders` WRITE;
/*!40000 ALTER TABLE `qxt_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `qxt_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qxt_product`
--

DROP TABLE IF EXISTS `qxt_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_product` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_name` varchar(255) NOT NULL,
  `qxt_image` varchar(500) DEFAULT NULL,
  `qxt_price` double DEFAULT NULL,
  `qxt_description` text DEFAULT NULL,
  `qxt_category_id` bigint(20) DEFAULT NULL,
  `qxt_tag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`qxt_id`),
  KEY `fk_qxt_product_category` (`qxt_category_id`),
  CONSTRAINT `fk_qxt_product_category` FOREIGN KEY (`qxt_category_id`) REFERENCES `qxt_category` (`qxt_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_product`
--

LOCK TABLES `qxt_product` WRITE;
/*!40000 ALTER TABLE `qxt_product` DISABLE KEYS */;
INSERT INTO `qxt_product` VALUES (1,'Cây thông Noel mini 50cm','tree50.jpg',199000,'Cây thông Noel mini trang trí bàn',1,NULL),(2,'Đèn LED dây 5m','led5m.jpg',159000,'Đèn LED nhiều màu sắc cho Noel',2,NULL),(3,'Vòng nguyệt quế đỏ','wreath_red.jpg',249000,'Vòng nguyệt quế trang trí cửa',3,NULL),(4,'Ông già Noel bông 30cm','santa30.jpg',89000,'Ông già Noel bằng bông dễ thương',4,NULL),(5,'Hộp quà Noel nhỏ','giftbox.jpg',59000,'Hộp quà nhỏ xinh trang trí',5,NULL);
/*!40000 ALTER TABLE `qxt_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qxt_product_comment`
--

DROP TABLE IF EXISTS `qxt_product_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_product_comment` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_content` text NOT NULL,
  `qxt_star` int(11) NOT NULL CHECK (`qxt_star` between 1 and 5),
  `qxt_created_at` datetime DEFAULT current_timestamp(),
  `qxt_product_id` bigint(20) NOT NULL,
  `qxt_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`qxt_id`),
  KEY `fk_qxt_comment_product` (`qxt_product_id`),
  KEY `fk_qxt_comment_user` (`qxt_user_id`),
  CONSTRAINT `fk_qxt_comment_product` FOREIGN KEY (`qxt_product_id`) REFERENCES `qxt_product` (`qxt_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_qxt_comment_user` FOREIGN KEY (`qxt_user_id`) REFERENCES `qxt_users` (`qxt_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_product_comment`
--

LOCK TABLES `qxt_product_comment` WRITE;
/*!40000 ALTER TABLE `qxt_product_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `qxt_product_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qxt_users`
--

DROP TABLE IF EXISTS `qxt_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qxt_users` (
  `qxt_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `qxt_full_name` varchar(150) NOT NULL,
  `qxt_email` varchar(150) NOT NULL,
  `qxt_password` varchar(255) NOT NULL,
  `qxt_phone` varchar(20) DEFAULT NULL,
  `qxt_role` varchar(20) NOT NULL,
  PRIMARY KEY (`qxt_id`),
  UNIQUE KEY `qxt_email` (`qxt_email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qxt_users`
--

LOCK TABLES `qxt_users` WRITE;
/*!40000 ALTER TABLE `qxt_users` DISABLE KEYS */;
INSERT INTO `qxt_users` VALUES (1,'Admin Test','admin@test.com','123','0900000000','ADMIN'),(2,'Shipper Test','ship@test.com','123','0911111111','SHIPPER');
/*!40000 ALTER TABLE `qxt_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-15 22:41:32
