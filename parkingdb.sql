-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: parkingdb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `AccountID` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `Role` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AccountID`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Lê','Tiến Hưng','letienhung','$2a$12$QMpyspm7AM355h5WM80Lh.fsA5zFXBO2ToiDaBgQnVQYAw/zJ8gIG','admin1@example.com','1234567890','ROLE_ADMIN',NULL),(2,'zxc','zxccc','2','2','user1@example.com','2345678901','ROLE_USER',NULL),(3,'zxczxczcx','sadasdasd','admin2','password123','admin2@example.com','3456789012','ROLE_ADMIN',NULL),(4,'adasdasd','fsadasdasd','user2','password123','user2@example.com','4567890123','ROLE_USER',NULL),(5,NULL,NULL,'admin3','password123','admin3@example.com','5678901234','ROLE_ADMIN',NULL),(6,NULL,NULL,'user3','password123','user3@example.com','6789012345','ROLE_USER',NULL),(7,NULL,NULL,'admin4','password123','admin4@example.com','7890123456','ROLE_ADMIN',NULL),(8,NULL,NULL,'user4','password123','user4@example.com','8901234567','ROLE_USER',NULL),(9,NULL,NULL,'admin5','password123','admin5@example.com','9012345678','ROLE_ADMIN',NULL),(10,NULL,NULL,'user5','password123','user5@example.com','0123456789','ROLE_USER',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_detail`
--

DROP TABLE IF EXISTS `admin_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_detail` (
  `AdminID` int NOT NULL AUTO_INCREMENT,
  `AccountID` int NOT NULL,
  PRIMARY KEY (`AdminID`),
  KEY `AccountID` (`AccountID`),
  CONSTRAINT `admin_detail_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `account` (`AccountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_detail`
--

LOCK TABLES `admin_detail` WRITE;
/*!40000 ALTER TABLE `admin_detail` DISABLE KEYS */;
INSERT INTO `admin_detail` VALUES (1,1),(2,3),(3,5);
/*!40000 ALTER TABLE `admin_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `NotificationID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `Message` text NOT NULL,
  `NotificationDate` datetime NOT NULL,
  `IsRead` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`NotificationID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user_detail` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,'Your reservation has been confirmed.','2024-08-15 10:00:00',1),(2,2,'Your reservation is still waiting for confirmation.','2024-08-15 11:00:00',0),(3,3,'Your reservation has been cancelled.','2024-08-15 12:00:00',1),(4,4,'Your reservation has been confirmed.','2024-08-15 13:00:00',1),(5,5,'Your reservation has been confirmed.','2024-08-15 14:00:00',1),(6,1,'Payment successful for your recent reservation.','2024-08-15 15:00:00',1),(7,2,'Payment failed for your recent reservation.','2024-08-15 16:00:00',1),(8,3,'Your reservation status has been updated.','2024-08-15 17:00:00',0),(9,4,'New parking lot added in your area.','2024-08-15 18:00:00',0),(10,5,'Reminder: Your reservation is about to start.','2024-08-15 19:00:00',1);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_lot`
--

DROP TABLE IF EXISTS `parking_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_lot` (
  `ParkingLotID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(255) NOT NULL,
  `TotalSpots` int NOT NULL,
  `PricePerHour` decimal(10,2) NOT NULL,
  `Facilities` text,
  `AdminID` int DEFAULT NULL,
  `ImageURL` varchar(255) DEFAULT NULL,
  `Latitude` decimal(9,6) DEFAULT NULL,
  `Longitude` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`ParkingLotID`),
  KEY `AdminID` (`AdminID`),
  CONSTRAINT `parking_lot_ibfk_1` FOREIGN KEY (`AdminID`) REFERENCES `admin_detail` (`AdminID`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_lot`
--

LOCK TABLES `parking_lot` WRITE;
/*!40000 ALTER TABLE `parking_lot` DISABLE KEYS */;
INSERT INTO `parking_lot` VALUES (31,'Central Parking','123 Main St',50,5.00,'Restrooms, Security',1,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(32,'hung hahahahah','456 Elm St',100,4.50,'Security',1,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(33,'North Lot','789 Oak St',75,6.00,'Restrooms, Security, EV Charging',3,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(34,'South Parking','101 Pine St',60,4.00,'Security',1,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(35,'West End Lot','202 Maple St',80,5.50,'Restrooms',2,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(36,'East Side Parking','303 Birch St',90,4.75,'Security, EV Charging',3,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(37,'Green Park','404 Cedar St',55,5.25,'Restrooms',1,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(38,'Parkside Garage','505 Walnut St',85,6.00,'Security, EV Charging',2,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(39,'Metro Lot','606 Spruce St',95,4.25,'Restrooms',3,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(40,'City Center Parking','707 Fir St',70,5.75,'Security, Restrooms',3,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724342270/t15tokffilt6pz7bbzwj.jpg',NULL,NULL),(44,'hung an tao','ádsadsadasd',12,13.00,'zxczxczxczxc',1,'https://res.cloudinary.com/dqyjpgsx5/image/upload/v1724781466/hbopxbstyqyttutggswv.jpg',NULL,NULL);
/*!40000 ALTER TABLE `parking_lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_spot`
--

DROP TABLE IF EXISTS `parking_spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_spot` (
  `SpotID` int NOT NULL AUTO_INCREMENT,
  `ParkingLotID` int NOT NULL,
  `Status` enum('Empty','Booked','In Use','Fixing') NOT NULL,
  `Level` int DEFAULT NULL,
  `SpotNumber` int NOT NULL,
  PRIMARY KEY (`SpotID`),
  KEY `ParkingLotID` (`ParkingLotID`),
  CONSTRAINT `parking_spot_ibfk_1` FOREIGN KEY (`ParkingLotID`) REFERENCES `parking_lot` (`ParkingLotID`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_spot`
--

LOCK TABLES `parking_spot` WRITE;
/*!40000 ALTER TABLE `parking_spot` DISABLE KEYS */;
INSERT INTO `parking_spot` VALUES (11,31,'Empty',1,1),(12,32,'Booked',1,2),(13,33,'In Use',1,3),(14,34,'Empty',2,1),(15,35,'In Use',2,2),(16,36,'Empty',1,1),(17,37,'Fixing',1,2),(18,38,'Empty',2,1),(19,39,'Booked',2,2),(20,40,'In Use',3,1),(144,40,'Empty',1,1),(145,40,'Empty',1,2),(146,40,'Empty',1,3),(147,40,'Empty',1,4),(148,40,'Empty',1,5),(149,40,'Empty',1,6),(150,40,'Empty',1,7),(151,40,'Empty',1,8),(152,40,'Empty',1,9),(153,40,'Empty',1,10),(154,40,'Empty',1,11),(155,40,'Empty',1,12),(156,40,'Empty',2,1),(157,40,'Empty',2,2),(158,40,'Empty',2,3),(159,40,'Empty',2,4),(160,40,'Empty',2,5),(161,40,'Empty',2,6),(162,40,'Empty',2,7),(163,40,'Empty',2,8),(164,40,'Empty',2,9),(165,40,'Empty',2,10),(166,40,'Empty',2,11),(167,40,'Empty',2,12),(168,44,'Empty',1,1),(169,44,'Empty',1,2),(170,44,'Empty',1,3),(171,44,'Empty',1,4),(172,44,'Empty',1,5),(173,44,'Empty',1,6),(174,44,'Empty',1,7),(175,44,'Empty',1,8),(176,44,'Empty',1,9),(177,44,'Empty',1,10),(178,44,'Empty',1,11),(179,44,'Empty',1,12),(180,44,'Empty',2,1),(181,44,'Empty',2,2),(182,44,'Empty',2,3),(183,44,'Empty',2,4),(184,44,'Empty',2,5),(185,44,'Empty',2,6),(186,44,'Empty',2,7),(187,44,'Empty',2,8),(188,44,'Empty',2,9),(189,44,'Empty',2,10),(190,44,'Empty',2,11),(191,44,'Empty',2,12),(192,44,'Empty',3,1),(193,44,'Empty',3,2),(194,44,'Empty',3,3),(195,44,'Empty',3,4),(196,44,'Empty',3,5),(197,44,'Empty',3,6),(198,44,'Empty',3,7),(199,44,'Empty',3,8),(200,44,'Empty',3,9),(201,44,'Empty',3,10),(202,44,'Empty',3,11),(203,44,'Empty',3,12);
/*!40000 ALTER TABLE `parking_spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `ReservationID` int NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  `PaymentMethod` enum('Credit Cards','Debit Cards','E-wallets') NOT NULL,
  `PaymentStatus` enum('successful','fail') NOT NULL,
  `PaymentDate` datetime NOT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `ReservationID` (`ReservationID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`ReservationID`) REFERENCES `reservation` (`ReservationID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,11,10.00,'Credit Cards','successful','2024-08-15 12:00:00'),(2,12,13.50,'Debit Cards','successful','2024-08-15 13:00:00'),(3,13,6.00,'E-wallets','fail','2024-08-15 14:00:00'),(4,14,11.50,'Credit Cards','successful','2024-08-15 15:00:00'),(5,15,12.00,'Debit Cards','successful','2024-08-15 16:00:00'),(6,16,9.00,'E-wallets','successful','2024-08-15 17:00:00'),(7,17,8.75,'Credit Cards','successful','2024-08-15 18:00:00'),(8,18,7.50,'Debit Cards','fail','2024-08-15 19:00:00'),(9,19,10.25,'E-wallets','successful','2024-08-15 20:00:00'),(10,20,11.00,'Credit Cards','successful','2024-08-15 21:00:00');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `ReservationID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `SpotID` int NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  `ReservationStatus` enum('Waiting','Confirmed','Cancelled') NOT NULL,
  `TotalPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ReservationID`),
  KEY `UserID` (`UserID`),
  KEY `SpotID` (`SpotID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user_detail` (`UserID`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`SpotID`) REFERENCES `parking_spot` (`SpotID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (11,1,11,'2024-08-16 08:00:00','2024-08-16 10:00:00','Confirmed',10.00),(12,2,12,'2024-08-16 09:00:00','2024-08-16 12:00:00','Waiting',13.50),(13,3,13,'2024-08-16 10:00:00','2024-08-16 11:00:00','Cancelled',6.00),(14,4,14,'2024-08-16 11:00:00','2024-08-16 13:00:00','Confirmed',11.50),(15,5,15,'2024-08-16 12:00:00','2024-08-16 14:00:00','Confirmed',12.00),(16,1,16,'2024-08-16 13:00:00','2024-08-16 15:00:00','Waiting',9.00),(17,2,17,'2024-08-16 14:00:00','2024-08-16 16:00:00','Confirmed',8.75),(18,3,18,'2024-08-16 15:00:00','2024-08-16 17:00:00','Cancelled',7.50),(19,4,19,'2024-08-16 16:00:00','2024-08-16 18:00:00','Confirmed',10.25),(20,5,20,'2024-08-16 17:00:00','2024-08-16 19:00:00','Confirmed',11.00);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `ParkingLotID` int NOT NULL,
  `Rating` int DEFAULT NULL,
  `Comment` text,
  `ReviewDate` datetime NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `UserID` (`UserID`),
  KEY `ParkingLotID` (`ParkingLotID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user_detail` (`UserID`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`ParkingLotID`) REFERENCES `parking_lot` (`ParkingLotID`),
  CONSTRAINT `review_chk_1` CHECK ((`Rating` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,31,5,'Great parking lot, very convenient!','2024-08-15 10:00:00'),(2,2,31,4,'Good experience, but a bit expensive.','2024-08-15 11:00:00'),(3,3,32,3,'Average service, could use some improvements.','2024-08-15 12:00:00'),(4,4,33,5,'Excellent facilities and security.','2024-08-15 13:00:00'),(5,5,34,4,'Nice spot, but the restrooms could be cleaner.','2024-08-15 14:00:00'),(6,1,35,5,'Very clean and well-managed parking lot.','2024-08-15 15:00:00'),(7,2,36,4,'Good place, though it gets busy.','2024-08-15 16:00:00'),(8,3,37,3,'Decent, but the parking spots are small.','2024-08-15 17:00:00'),(9,4,38,5,'Perfect for my needs, highly recommend.','2024-08-15 18:00:00'),(10,5,40,4,'Good overall, just a bit pricey.','2024-08-15 19:00:00');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_detail` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `AccountID` int NOT NULL,
  `TwoFactorEnabled` tinyint(1) DEFAULT '0',
  `Avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `AccountID` (`AccountID`),
  CONSTRAINT `user_detail_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `account` (`AccountID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (1,2,0,'user1_avatar.png'),(2,4,1,'user2_avatar.png'),(3,6,0,'user3_avatar.png'),(4,8,1,'user4_avatar.png'),(5,10,0,'user5_avatar.png');
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `VehicleID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `Make` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `Year` int DEFAULT NULL,
  `LicensePlate` varchar(20) DEFAULT NULL,
  `Color` varchar(20) DEFAULT NULL,
  `AdditionalInfo` text,
  PRIMARY KEY (`VehicleID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user_detail` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,1,'Toyota','Camry',2020,'ABC1234','Blue','Sedan'),(2,2,'Honda','Civic',2019,'XYZ5678','Red','Coupe'),(3,3,'Ford','Focus',2018,'LMN9101','Green','Hatchback'),(4,4,'Chevrolet','Malibu',2021,'OPQ2345','Black','Sedan'),(5,5,'Nissan','Altima',2022,'RST6789','White','Sedan'),(6,1,'Hyundai','Elantra',2020,'UVW1234','Silver','Sedan'),(7,2,'Kia','Soul',2019,'GHI5678','Blue','SUV'),(8,3,'Subaru','Impreza',2018,'JKL9101','Gray','Sedan'),(9,4,'Volkswagen','Golf',2021,'MNO2345','Black','Hatchback'),(10,5,'Mazda','3',2022,'PQR6789','Red','Sedan');
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-31 16:04:14
