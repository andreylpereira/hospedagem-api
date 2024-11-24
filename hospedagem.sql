CREATE DATABASE  IF NOT EXISTS `hospedagem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hospedagem`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospedagem
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `acomodacao_amenidade`
--

DROP TABLE IF EXISTS `acomodacao_amenidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acomodacao_amenidade` (
  `acomodacao_id` int NOT NULL,
  `amenidade_id` int NOT NULL,
  PRIMARY KEY (`acomodacao_id`,`amenidade_id`),
  KEY `FKbt82r1kmai988rh5n74wtb00a` (`amenidade_id`),
  CONSTRAINT `FKbt82r1kmai988rh5n74wtb00a` FOREIGN KEY (`amenidade_id`) REFERENCES `amenidades` (`id`),
  CONSTRAINT `FKe0nvr6lyc1cpovknx1lu96efe` FOREIGN KEY (`acomodacao_id`) REFERENCES `acomodacoes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acomodacao_amenidade`
--

LOCK TABLES `acomodacao_amenidade` WRITE;
/*!40000 ALTER TABLE `acomodacao_amenidade` DISABLE KEYS */;
INSERT INTO `acomodacao_amenidade` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(1,2),(2,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(1,3),(2,3),(3,3),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(11,3),(12,3),(1,4),(2,4),(3,4),(4,4),(5,4),(6,4),(7,4),(8,4),(9,4),(11,4),(12,4),(1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5),(9,5),(10,5),(11,5),(12,5),(13,5),(1,6),(3,6),(4,6),(9,6),(11,6),(12,6),(13,6),(1,7),(6,7),(9,7),(11,7),(1,8),(13,8),(1,9);
/*!40000 ALTER TABLE `acomodacao_amenidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `acomodacoes`
--

DROP TABLE IF EXISTS `acomodacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acomodacoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `capacidade` int NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double NOT NULL,
  `funcionario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4uf9ektnmf28ved5h5f8cnnnh` (`funcionario_id`),
  CONSTRAINT `FK4uf9ektnmf28ved5h5f8cnnnh` FOREIGN KEY (`funcionario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acomodacoes`
--

LOCK TABLES `acomodacoes` WRITE;
/*!40000 ALTER TABLE `acomodacoes` DISABLE KEYS */;
INSERT INTO `acomodacoes` VALUES (1,5,'Uma suíte luxuosa com vista para o mar.',_binary '','Suíte Deluxe nº 342',229.99,7),(2,3,'Uma suíte luxuosa com vista para montanhas.',_binary '','Suíte Deluxe nº 341',109.99,3),(3,5,'Chalé confortável para toda família.',_binary '','Chalé Premium nº 134',99.99,3),(4,6,'Chalé confortável para toda família.',_binary '','Chalé Premium nº 130',299.9,3),(5,8,'Casa',_binary '','Casa Familiar nº  101',309.9,3),(6,10,'Casa familiar com churrasqueira para eventos de 10 pessoas.',_binary '','Casa Familiar nº 102',400,3),(7,2,'Suíte presidencial com toda privacidade e amenidades.',_binary '','Suíte Presidencial nº 1',599.9,3),(8,2,'Suíte presidencial com toda privacidade e amenidades.',_binary '','Suíte Presidencial nº 2',599.9,3),(9,5,'',_binary '','Casa Familiar nº 104',199.99,1),(10,4,'Casa padrão',_binary '','Casa Familiar º 105',100.99,1),(11,5,'Completa',_binary '','Suíte Platinum nº 3',560.99,1),(12,6,'Completissima.',_binary '','Suíte Premium nº 8',666.66,1),(13,4,'Descricao basica',_binary '','Nova Suite 1',99,7);
/*!40000 ALTER TABLE `acomodacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amenidades`
--

DROP TABLE IF EXISTS `amenidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amenidades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `funcionario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkvt1mv1r1uy259g6q4ivhao57` (`funcionario_id`),
  CONSTRAINT `FKkvt1mv1r1uy259g6q4ivhao57` FOREIGN KEY (`funcionario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenidades`
--

LOCK TABLES `amenidades` WRITE;
/*!40000 ALTER TABLE `amenidades` DISABLE KEYS */;
INSERT INTO `amenidades` VALUES (1,'Wi-fi',1),(2,'Frigobar',3),(3,'Micro-ondas',1),(4,'Ar condicionado',1),(5,'Televisão',1),(6,'Lareira',3),(7,'Churrasqueira',3),(8,'Velha amenidade',1),(9,'Outra amenidade',1);
/*!40000 ALTER TABLE `amenidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `funcionario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK83wo6qjfp6y3ewndhxja8gagh` (`funcionario_id`),
  CONSTRAINT `FK83wo6qjfp6y3ewndhxja8gagh` FOREIGN KEY (`funcionario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'07433440909','joao.silva@example.com','Rua Exemplo, 123, São Paulo, SP','João da Silva','(11) 11111-1111',1),(2,'04407726059','julio@gmail.com','Brasil','Julio','(48) 99999-9999',1),(3,'21353633080','jose@jose.com','Florianopolis','Jose','(99) 99999-9999',2),(4,'09942320040','joana@gmail.com','Florianopolis','Joana','(99) 99999-9999',7);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data_fim` datetime(6) DEFAULT NULL,
  `data_inicio` datetime(6) DEFAULT NULL,
  `status` enum('CANCELADO','CONCLUIDO','CONFIRMADO','EM_ANDAMENTO','PENDENTE') DEFAULT NULL,
  `acomodacao_id` int DEFAULT NULL,
  `cliente_id` int DEFAULT NULL,
  `funcionario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKu2ywkaqgadhcgljvhx3b24e6` (`acomodacao_id`),
  KEY `FKpays115gahfu3ffuywded1xqt` (`cliente_id`),
  KEY `FKra0bptj5xgx9sthhek7jr0s0n` (`funcionario_id`),
  CONSTRAINT `FKpays115gahfu3ffuywded1xqt` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKra0bptj5xgx9sthhek7jr0s0n` FOREIGN KEY (`funcionario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKu2ywkaqgadhcgljvhx3b24e6` FOREIGN KEY (`acomodacao_id`) REFERENCES `acomodacoes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'2024-09-03 14:00:00.000000','2024-08-29 11:00:00.000000','EM_ANDAMENTO',1,1,1),(5,'2024-11-19 00:00:00.000000','2024-11-18 00:00:00.000000','EM_ANDAMENTO',7,1,3),(7,'2024-11-30 00:00:00.000000','2024-11-27 00:00:00.000000','EM_ANDAMENTO',1,1,1),(8,'2024-11-21 00:00:00.000000','2024-11-17 00:00:00.000000','EM_ANDAMENTO',5,1,1),(9,'2024-11-24 00:00:00.000000','2024-11-22 00:00:00.000000','EM_ANDAMENTO',6,1,5),(10,'2024-11-24 00:00:00.000000','2024-11-21 00:00:00.000000','EM_ANDAMENTO',4,2,5),(11,'2024-11-23 00:00:00.000000','2024-11-20 00:00:00.000000','EM_ANDAMENTO',2,1,5),(12,'2024-11-23 00:00:00.000000','2024-11-22 00:00:00.000000','PENDENTE',1,2,1),(13,'2024-11-05 00:00:00.000000','2024-11-03 00:00:00.000000','EM_ANDAMENTO',1,2,1),(14,'2024-11-23 00:00:00.000000','2024-11-23 00:00:00.000000','CANCELADO',1,3,2),(15,'2024-11-07 00:00:00.000000','2024-11-06 00:00:00.000000','EM_ANDAMENTO',1,3,2),(16,'2024-11-26 00:00:00.000000','2024-11-25 00:00:00.000000','EM_ANDAMENTO',1,1,2),(17,'2024-11-04 00:00:00.000000','2024-11-03 00:00:00.000000','EM_ANDAMENTO',4,1,2),(18,'2024-11-15 00:00:00.000000','2024-11-14 00:00:00.000000','EM_ANDAMENTO',4,1,2),(19,'2024-11-02 00:00:00.000000','2024-11-01 00:00:00.000000','EM_ANDAMENTO',4,1,2),(20,'2024-11-06 00:00:00.000000','2024-11-05 00:00:00.000000','CANCELADO',4,1,2),(21,'2024-11-08 00:00:00.000000','2024-11-07 00:00:00.000000','EM_ANDAMENTO',4,3,2),(22,'2024-11-11 00:00:00.000000','2024-11-10 00:00:00.000000','EM_ANDAMENTO',4,2,2),(23,'2024-11-09 00:00:00.000000','2024-11-08 00:00:00.000000','CONFIRMADO',1,4,7),(24,'2024-11-23 00:00:00.000000','2024-11-23 00:00:00.000000','EM_ANDAMENTO',3,4,7),(25,'2024-11-20 00:00:00.000000','2024-11-25 00:00:00.000000','EM_ANDAMENTO',7,4,7);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `habilitado` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'7343c3062ee89fd8b6e368d80d47273dfdf82589c59d05a67bc10c27ea8b9981',NULL,_binary '','Admin','ADMINISTRADOR','$2a$10$mo9PBt66TvZkmN6AXiO07eKfrDIaAI.Qk/lZpyHIAVEGhsMaJ1Ema'),(2,'2e2c24aa5ac9aaf0edeea01fc98759d91373b1f380f790e4fb4fd94508da6ba5','ronaldooo@gmail.com',_binary '','Ronald McDonalds','FUNCIONARIO','$2a$10$tyDGng97WHzdIls6575Mou6pumips9cnBFvWkgZ316/19K6ZGTRu6'),(3,'d5f0cd4f5b7a2eff4f88d6893a209bec82d81c7c714ebc1b2425309d4ee5810c','md@md.com',_binary '','Michael Douglas','FUNCIONARIO','$2a$10$RmdiBc0iYKHQyiehfGeOE.cHBxNTO0yPE3NsIbn3Kn3kR9fQ7p2SO'),(4,'2e56a1e5c78dcca7757c4c7445cb43a637c84f5e18b43dbd611b0c6455241257','funcionario@funcionario.com',_binary '','Funcionario','FUNCIONARIO','$2a$10$Ci0ZwWiq7qIbctIKoYzPj.wzHepyJu/JvpC4.G8VnQG.QZZx3bjlm'),(5,'5d3f123c90a4cedd918b676bd59b955791344e7ab1fb2e67719c143842510923','joao@gmail.com',_binary '','João Pedro','FUNCIONARIO','$2a$10$faIu09IHoZ5CArVBTN8xLeJaftnA5TgB/x3H9KObY2b/Fo63h1V5W'),(6,'6d7dbe50cee829dcaafd4f37466ed54a5f0bc660f09cbae853cf432009b1ebcc','asdasd@gmail.com',_binary '','Jose','ADMINISTRADOR','$2a$10$QXXt3Z2FpJiEbhl34BJQGuBIX2kXBZCbYO6xg7YkmL7V2PZh2sR3m'),(7,'3a9da84e7508d4e00121344f2ab325f5cc788ba78ed0a247979838852e14eed8','joana@gmail.com',_binary '','Joana','FUNCIONARIO','$2a$10$Q.BiGzksBccd0iDvc4VWoeKRW8jmtj4ZDEryGmDxozgjVb2it8qO2');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hospedagem'
--

--
-- Dumping routines for database 'hospedagem'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-24  9:04:31
