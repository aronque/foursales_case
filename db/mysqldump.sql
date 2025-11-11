-- MySQL dump 10.13  Distrib 8.4.7, for Linux (x86_64)
--
-- Host: localhost    Database: db_case
-- ------------------------------------------------------
-- Server version	8.4.7

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
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` varchar(255) NOT NULL,
  `qty` int DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  KEY `FKc5uhmwioq5kscilyuchp4w49o` (`product_id`),
  CONSTRAINT `FKc5uhmwioq5kscilyuchp4w49o` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('058965d1-78dc-4677-819f-a95c9c77ff5c',1,'439bedce-d971-4600-b77e-5dcac5a6b923','4516c45d-0434-4fc3-b050-1119a896d562'),('064a7906-9aad-4b1c-a9aa-47c9545baa87',2,'3632cbe4-6baf-4f6d-8766-783629b92913','1b40dbae-6be1-436f-b323-580260f3274c'),('0be92930-1037-4426-920e-869c69661c7c',5,'22ab764c-c9ac-467f-bf22-c4dbb5bfc37a','19c46941-fa16-4113-92a2-8599ba7e407e'),('0e304b7c-e59e-41e6-91b6-fa1c81063974',5,'326caf63-49c9-4f0c-bfe0-26a3e95cf8a5','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('27475967-0673-4ae6-9a74-3dfd803854a5',3,'04c97d7b-5896-4f0f-905e-b5977c78bd38','6e041618-9c43-4a1b-9c8e-436b24148f70'),('3dcf5655-c136-4cc0-aa7e-e4336ab11808',3,'439bedce-d971-4600-b77e-5dcac5a6b923','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('5223ce1a-8e86-4a70-8c76-b8410a7f0b40',5,'0d32f58c-1efa-4068-a3e5-9e3f5c732fb3','1b40dbae-6be1-436f-b323-580260f3274c'),('582fc889-20ba-4ecc-9ba7-6c13d919255f',5,'ca93de03-79b8-43ad-90e1-8495afe45618','1b40dbae-6be1-436f-b323-580260f3274c'),('6a58aedd-375b-4edc-827a-e40f70d8bfac',2,'eccab140-711d-4847-b1b8-8d8ea6ea3a56','1b40dbae-6be1-436f-b323-580260f3274c'),('6db6e3b1-1ac7-45bc-8288-55e11ef0b45a',2,'ca93de03-79b8-43ad-90e1-8495afe45618','6e041618-9c43-4a1b-9c8e-436b24148f70'),('73baf8b8-4ae3-41ae-9c23-f109e6c3a08e',10,'0d32f58c-1efa-4068-a3e5-9e3f5c732fb3','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('7ebab4e8-e888-42d1-ab05-e7c60a41abb0',3,'9fca9d2b-2096-479e-88f1-4b1c028c3daa','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('836958df-5703-4347-aa17-6f174ef00245',3,'4fd5265a-e4b7-4cbf-9870-34b17f6da0ec','1b40dbae-6be1-436f-b323-580260f3274c'),('8aa1600f-42d9-4dbb-9254-ce4d632e3fdb',1,'58c41c50-5644-4e0a-97d4-ca29fae4b951','4516c45d-0434-4fc3-b050-1119a896d562'),('9a3297be-85fd-469b-a3c7-b3a1dec7ed21',3,'ca93de03-79b8-43ad-90e1-8495afe45618','19c46941-fa16-4113-92a2-8599ba7e407e'),('9b6273c0-9ba2-4d35-9929-4b08cc551ba0',3,'3632cbe4-6baf-4f6d-8766-783629b92913','19c46941-fa16-4113-92a2-8599ba7e407e'),('a659a316-7b06-4983-aab8-299a92275992',2,'51d6165e-3e72-43c9-ad01-61bee2d7429d','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('a8fb20e4-544f-4e9c-931b-a90513b137be',3,'58c41c50-5644-4e0a-97d4-ca29fae4b951','6e041618-9c43-4a1b-9c8e-436b24148f70'),('b2b4b297-8962-4b2a-add3-0500a0f71918',1,'9fca9d2b-2096-479e-88f1-4b1c028c3daa','4516c45d-0434-4fc3-b050-1119a896d562'),('b439c638-7875-41d0-a402-7a9926f571f2',1,'be379bf7-9240-4316-a6e0-a1f67eda653d','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('bf33c6c7-7c03-4d21-b42f-4ad6cf2afa5b',1,'439bedce-d971-4600-b77e-5dcac5a6b923','19c46941-fa16-4113-92a2-8599ba7e407e'),('c38201dc-2214-4fbe-9659-ca6dbb64274a',5,'eccab140-711d-4847-b1b8-8d8ea6ea3a56','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('d9c9a0bf-f3e3-4e4e-a203-c0d03d6039c8',1,'3632cbe4-6baf-4f6d-8766-783629b92913','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('dc69662c-9574-479a-93ae-3f94af3fe9d2',1,'e3b8e0c0-8dd1-46cb-b412-b12a4a045f21','4516c45d-0434-4fc3-b050-1119a896d562'),('e0e5d8df-6a3b-4dea-9d7d-9365ff230cbf',4,'be379bf7-9240-4316-a6e0-a1f67eda653d','19c46941-fa16-4113-92a2-8599ba7e407e'),('e1e94d9e-c3fb-4f48-87a1-882ad6fa1a75',30,'d59d8e62-1a76-4425-8967-f7fb0857058c','db267c71-11e6-42d9-a098-e9a2fd9fee77'),('ee9b05c4-be57-4d8c-b780-17cb1d8fd89e',1,'9fca9d2b-2096-479e-88f1-4b1c028c3daa','19c46941-fa16-4113-92a2-8599ba7e407e');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(255) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `final_payment` double DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `order_user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp1maxb2lt3d3y2yajbw6yqwck` (`order_user_id`),
  CONSTRAINT `FKp1maxb2lt3d3y2yajbw6yqwck` FOREIGN KEY (`order_user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `orders_chk_1` CHECK ((`status` between 0 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('04c97d7b-5896-4f0f-905e-b5977c78bd38','2025-11-11 02:14:19.790764',NULL,NULL,0,'dae29cd1-9a3d-4827-99b2-7a621917e4ed'),('0d32f58c-1efa-4068-a3e5-9e3f5c732fb3','2025-11-11 02:15:47.701352',NULL,NULL,0,'5a20f889-6903-4448-b2c6-494dbd15e31c'),('22ab764c-c9ac-467f-bf22-c4dbb5bfc37a','2025-11-10 19:56:51.954061',6000,'2025-11-10 20:14:20.361516',1,'e14e2b19-c8d5-494e-92ad-b6e0268002db'),('326caf63-49c9-4f0c-bfe0-26a3e95cf8a5','2025-11-11 02:14:57.034734',NULL,NULL,0,'dae29cd1-9a3d-4827-99b2-7a621917e4ed'),('3632cbe4-6baf-4f6d-8766-783629b92913','2025-11-11 02:04:17.010065',NULL,NULL,0,'1705ba4a-85e3-44fa-ae77-f966884625f1'),('439bedce-d971-4600-b77e-5dcac5a6b923','2025-11-11 02:02:35.304610',22347,'2025-11-11 02:11:50.029565',1,'e14e2b19-c8d5-494e-92ad-b6e0268002db'),('4fd5265a-e4b7-4cbf-9870-34b17f6da0ec','2025-11-11 02:05:50.863630',5250,'2025-11-11 02:09:01.631843',1,'a643dbe4-847b-43c2-8fa5-1a6691998abd'),('51d6165e-3e72-43c9-ad01-61bee2d7429d','2025-11-11 02:05:38.368719',13198,'2025-11-11 02:10:21.315409',1,'a643dbe4-847b-43c2-8fa5-1a6691998abd'),('58c41c50-5644-4e0a-97d4-ca29fae4b951','2025-11-11 02:06:36.094886',15149.34,'2025-11-11 02:09:34.095029',1,'a643dbe4-847b-43c2-8fa5-1a6691998abd'),('9fca9d2b-2096-479e-88f1-4b1c028c3daa','2025-11-10 20:55:17.259656',20998,'2025-11-10 20:58:43.100545',1,'e14e2b19-c8d5-494e-92ad-b6e0268002db'),('be379bf7-9240-4316-a6e0-a1f67eda653d','2025-11-11 02:16:08.181239',NULL,NULL,0,'5a20f889-6903-4448-b2c6-494dbd15e31c'),('ca93de03-79b8-43ad-90e1-8495afe45618','2025-11-11 02:03:18.210739',NULL,NULL,0,'e14e2b19-c8d5-494e-92ad-b6e0268002db'),('d59d8e62-1a76-4425-8967-f7fb0857058c','2025-11-11 02:04:51.520730',NULL,NULL,0,'1705ba4a-85e3-44fa-ae77-f966884625f1'),('e3b8e0c0-8dd1-46cb-b412-b12a4a045f21','2025-11-11 02:06:11.628921',NULL,NULL,0,'a643dbe4-847b-43c2-8fa5-1a6691998abd'),('eccab140-711d-4847-b1b8-8d8ea6ea3a56','2025-11-11 02:15:15.216518',NULL,NULL,0,'dae29cd1-9a3d-4827-99b2-7a621917e4ed');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` varchar(255) NOT NULL,
  `att_date` datetime(6) DEFAULT NULL,
  `category` tinyint DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock_qty` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `products_chk_1` CHECK ((`category` between 0 and 3)),
  CONSTRAINT `products_chk_2` CHECK ((`stock_qty` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('19c46941-fa16-4113-92a2-8599ba7e407e','2025-11-10 11:25:54.045847',2,'2025-11-10 11:22:56.366979','Balcão de pia de banheiro 2 portas','Balcão',1200,5),('1b40dbae-6be1-436f-b323-580260f3274c',NULL,0,'2025-11-10 20:48:44.022586','Bancada de aço inoxidável para cozinha','Bancada de Inox',1750,43),('4516c45d-0434-4fc3-b050-1119a896d562','2025-11-11 02:01:57.978710',3,'2025-11-10 20:47:06.268334','Quadro de arte','Quadro',1350,0),('6e041618-9c43-4a1b-9c8e-436b24148f70',NULL,1,'2025-11-10 20:47:54.224237',NULL,'Sofá',4599.78,124),('db267c71-11e6-42d9-a098-e9a2fd9fee77',NULL,0,'2025-11-10 20:45:12.138804','Geladeira French Door','Geladeira',6599,202);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `users_chk_1` CHECK ((`role` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1705ba4a-85e3-44fa-ae77-f966884625f1','$2a$10$0vO7WQZlM/Vk5kRT4n/JtOdYef0x4BDEJDaylrUeksj/qqOcPWO5K',1,'gonDaSilva'),('57378e0f-5ec5-420b-ac51-4b5fc484d8cf','$2a$10$KQjs6/6a34gtSfaNytUK5uy/SUa0QxC1sZ2qsiOZOLZF4OOjrcHym',1,'abbacchio'),('5a20f889-6903-4448-b2c6-494dbd15e31c','$2a$10$pur3.2.mdppmkGhNtXOxzedYHq.1mUHxAICVT6uTuXvY45HUm9HRO',1,'josephjoestar'),('611032e3-c8c6-420d-a187-c61735854312','$2a$10$qICK76m.z3xBiEJiD8sd7Ogvv6q1SoaYQGg0CBJCpi1nGWB5lCL9S',1,'lievtolstoi'),('8d8f390e-02ac-4b94-8e02-367c65151bf9','$2a$10$F.3l7wUA.0PKc/4kCGrwkOli4KSuhJT60PvnrquZloPat63y2T8ca',0,'admin'),('a643dbe4-847b-43c2-8fa5-1a6691998abd','$2a$10$HbCnbM7GwjztAG8xnIeQj.ikRPDG1YyfyZHiLPn5nQ6avIKzgBjTa',1,'yoshikagekira'),('ad239e31-a4ee-4baf-9f55-456a4e2dcd13','$2a$10$Uc9Z2Yd6abBdWaDJve1aCuWM5367e9D6Xr5qAfhoRGklil39ia3lO',1,'paulriedl'),('dae29cd1-9a3d-4827-99b2-7a621917e4ed','$2a$10$pcIdCWWU1uio3jTdBWX9GeAfwa4lP0Lf1lQwSEqFZDbpLbMoxDg7C',1,'robertojr'),('e14e2b19-c8d5-494e-92ad-b6e0268002db','$2a$10$msO3ScgR4i5B7/4as5.vJuCLyd0S/XNycC3Feia9ZVT9KYCTTmDIi',1,'varonque');
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

-- Dump completed on 2025-11-11  2:23:12
