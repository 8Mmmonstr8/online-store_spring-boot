-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: online_store
-- ------------------------------------------------------
-- Server version	8.0.22

CREATE DATABASE IF NOT EXISTS `online_store` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `online_store`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1),(5);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(25) NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Books'),(2,'Groceries'),(3,'Gadgets'),(4,'Toys'),(5,'Furniture');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (39);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_cart_product`
--

DROP TABLE IF EXISTS `in_cart_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `in_cart_product` (
                                   `id` bigint NOT NULL AUTO_INCREMENT,
                                   `cart_id` bigint NOT NULL,
                                   `product_id` bigint NOT NULL,
                                   `needed_quantity` int DEFAULT NULL,
                                   PRIMARY KEY (`id`),
                                   KEY `FKdy6hpgje9prru1o5tbkjyvqur` (`cart_id`),
                                   KEY `FK35s7nlsq4hyliry7u9c996gkw` (`product_id`),
                                   CONSTRAINT `FK35s7nlsq4hyliry7u9c996gkw` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                   CONSTRAINT `FKdy6hpgje9prru1o5tbkjyvqur` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_cart_product`
--

LOCK TABLES `in_cart_product` WRITE;
/*!40000 ALTER TABLE `in_cart_product` DISABLE KEYS */;
INSERT INTO `in_cart_product` VALUES (78,5,4,5),(79,5,3,1);
/*!40000 ALTER TABLE `in_cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered_products`
--

DROP TABLE IF EXISTS `ordered_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered_products` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `description` varchar(255) DEFAULT NULL,
                                    `name` varchar(255) NOT NULL,
                                    `price` double NOT NULL,
                                    `quantity` int NOT NULL,
                                    `category_id` bigint DEFAULT NULL,
                                    `order_id` bigint DEFAULT NULL,
                                    `product_id` bigint DEFAULT NULL,
                                    PRIMARY KEY (`id`),
                                    KEY `FKefi1wk7pu6allaytkbmc01fyy` (`category_id`),
                                    KEY `FKi7isf670mbq331v0muqqry1cd` (`order_id`),
                                    KEY `FKn042ir6sf41qikoy2c07gt87i` (`product_id`),
                                    CONSTRAINT `FKefi1wk7pu6allaytkbmc01fyy` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
                                    CONSTRAINT `FKi7isf670mbq331v0muqqry1cd` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
                                    CONSTRAINT `FKn042ir6sf41qikoy2c07gt87i` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered_products`
--

LOCK TABLES `ordered_products` WRITE;
/*!40000 ALTER TABLE `ordered_products` DISABLE KEYS */;
INSERT INTO `ordered_products` VALUES (43,'A lot of interesting stories','Creapy Stories',100,5,1,22,1),(44,'Toy for kids','Funny bear',20,17,4,22,3),(45,'A lot of interesting stories','Creapy Stories',100,5,1,24,1),(69,'fresh cucumbers','Cucumber',0.2,10,2,36,13),(70,'Sofa for office','Sofa',300,11,5,36,11),(72,'new Samsung phone','Samsung S21',1100,1,3,37,8);
/*!40000 ALTER TABLE `ordered_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `date` date NOT NULL,
                          `user_id` bigint DEFAULT NULL,
                          `cart_id` bigint DEFAULT NULL,
                          `is_approved` bit(1) DEFAULT NULL,
                          `order_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKhm3lo0y0k014dmwxqdrmqiaxd` (`cart_id`),
                          KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
                          CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                          CONSTRAINT `FKhm3lo0y0k014dmwxqdrmqiaxd` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (22,'2020-12-20',35,5,_binary '',NULL),(24,'2020-12-20',35,5,_binary '',NULL),(36,'2021-01-04',35,5,_binary '\0',NULL),(37,'2021-01-05',35,5,_binary '\0',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `description` varchar(255) DEFAULT NULL,
                            `name` varchar(255) NOT NULL,
                            `price` double NOT NULL,
                            `date` date NOT NULL,
                            `quantity` int NOT NULL,
                            `category_id` bigint DEFAULT NULL,
                            `order_id` bigint DEFAULT NULL,
                            `product_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
                            KEY `FKmncpppnsne0g6xpunqetj2x2x` (`order_id`),
                            KEY `FK68ow544t97t14bptcr0h1s7n` (`product_id`),
                            CONSTRAINT `FK68ow544t97t14bptcr0h1s7n` FOREIGN KEY (`product_id`) REFERENCES `carts` (`id`),
                            CONSTRAINT `FKmncpppnsne0g6xpunqetj2x2x` FOREIGN KEY (`order_id`) REFERENCES `carts` (`id`),
                            CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'A lot of interesting stories','Creapy Stories',100,'2020-12-02',20,1,NULL,NULL),(2,'new iPhone 11','iPhone 11',1000,'2020-11-18',20,3,NULL,NULL),(3,'Toy for kids','Funny bear',20,'2020-08-12',133,4,NULL,NULL),(4,'new iPhone 12','iPhone 12',1500,'2021-01-02',10,1,NULL,NULL),(5,'Mark Twain\'s book','Tom Sawyer',10,'2020-12-01',30,1,NULL,NULL),(6,'','Sherlock Holmes',5,'2020-12-10',23,1,NULL,NULL),(7,'new Xiaomi','Xiaomi mi 10',800,'2020-12-02',20,3,NULL,NULL),(8,'new Samsung phone','Samsung S21',1100,'2020-12-02',10,3,NULL,NULL),(9,'fresh bananas','Banana',0.5,'2020-12-02',1000,2,NULL,NULL),(10,'table for ofice','Table',50,'2020-12-01',30,5,NULL,NULL),(11,'Sofa for office','Sofa',300,'2020-12-01',20,5,NULL,NULL),(12,'old nokia','Nokia 3310',100,'2014-01-01',1,3,NULL,NULL),(13,'fresh cucumbers','Cucumber',0.2,'2020-12-02',100,2,NULL,NULL),(17,'old chinese phone','Oppo',300,'2020-12-01',10,3,NULL,NULL),(20,'samsung planche','Samsung Tab 10.1',500,'2021-01-22',50,3,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) NOT NULL,
                        `first_name` varchar(30) NOT NULL,
                        `last_name` varchar(30) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `role` varchar(255) DEFAULT NULL,
                        `is_non_locked` bit(1) DEFAULT NULL,
                        `cart_id` bigint NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
                        UNIQUE KEY `UK_47dq8urpj337d3o65l3fsjph3` (`cart_id`),
                        CONSTRAINT `FKg10rs1a4lo74hp7oaa1ahuqrf` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (30,'admin','admin','admin','$2a$10$4VJoDyuMcx4OF8QtfrAY1eQes5R6VPAGht4BRndfY8Bhd1WQvi9/G','ADMIN',_binary '',1),(35,'user','user','user','$2a$10$IAqRNXX9orWTwAIOdSbBeOPUHaUwRzigwg8YeTY6Zqe2lfGctO082','USER',_binary '',5);
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

-- Dump completed on 2021-01-07 21:13:14
