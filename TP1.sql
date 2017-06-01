-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: TP1_BD
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1

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
-- Table structure for table `adicionais`
--

DROP TABLE IF EXISTS `adicionais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adicionais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `imovel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_adicionais_imovel1_idx` (`imovel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adicionais`
--

LOCK TABLES `adicionais` WRITE;
/*!40000 ALTER TABLE `adicionais` DISABLE KEYS */;
INSERT INTO `adicionais` VALUES (22,'Piscina',8),(23,'Churrasqueira',8),(24,'Playground',8);
/*!40000 ALTER TABLE `adicionais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Casa Padrão','Casa simples localizada em rua.'),(2,'Casa Condomínio','Casa localizada dentro das dependêcias de um condomínio.'),(3,'Apartamento','Moradia localizada dentro de um edifício.'),(4,'Kitchenette','Tipo de apartamento que apresenta quarto, sala, cozinha e banheiro geralmente conjugados.');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (9,'GIlberto das Dores'),(12,'Roberto Durán'),(13,'Oseas Filho'),(14,'Rocky Balboa');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imoveis_alugados`
--

DROP TABLE IF EXISTS `imoveis_alugados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imoveis_alugados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_inicio` date DEFAULT NULL,
  `data_fim` date DEFAULT NULL,
  `imovel_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`cliente_id`),
  UNIQUE KEY `imovel_id` (`imovel_id`),
  KEY `fk_imoveis_alugados_imovel1_idx` (`imovel_id`),
  KEY `fk_imoveis_alugados_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_imoveis_alugados_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_imoveis_alugados_imovel1` FOREIGN KEY (`imovel_id`) REFERENCES `imovel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imoveis_alugados`
--

LOCK TABLES `imoveis_alugados` WRITE;
/*!40000 ALTER TABLE `imoveis_alugados` DISABLE KEYS */;
/*!40000 ALTER TABLE `imoveis_alugados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel`
--

DROP TABLE IF EXISTS `imovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imovel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endereco` varchar(150) DEFAULT NULL,
  `area_total` float DEFAULT NULL,
  `qtd_quartos` int(11) DEFAULT NULL,
  `qtd_suites` int(11) DEFAULT NULL,
  `qtd_vagas` int(11) DEFAULT NULL,
  `custo` float DEFAULT NULL,
  `categoria_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`categoria_id`),
  KEY `fk_imovel_categoria_idx` (`categoria_id`),
  CONSTRAINT `fk_imovel_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel`
--

LOCK TABLES `imovel` WRITE;
/*!40000 ALTER TABLE `imovel` DISABLE KEYS */;
INSERT INTO `imovel` VALUES (1,'Rua dos Bobos',250,3,1,1,200,2),(4,'Av. Luanny Rocha',500,1,1,3,1800,1),(5,'Av. Humberto Calderaro',220,3,2,4,1500,1),(6,'Av. das Torres',430,5,2,4,2500,2),(8,'Av. Tabajara',300,5,2,4,2600,1),(9,'Av. das Bandeiras',200,2,2,1,1340,3),(11,'Rua dos Maracujás',345,2,2,3,1569,2),(12,'Rua Ponta Negra',500,4,4,3,12000,3);
/*!40000 ALTER TABLE `imovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcionais`
--

DROP TABLE IF EXISTS `opcionais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `opcionais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `servico` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_opcionais_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_opcionais_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcionais`
--

LOCK TABLES `opcionais` WRITE;
/*!40000 ALTER TABLE `opcionais` DISABLE KEYS */;
INSERT INTO `opcionais` VALUES (11,12,'Seguro'),(12,12,'Chave extra'),(13,13,'Chave extra'),(14,13,'Imóvel mobiliado'),(15,14,'Seguro'),(16,14,'Chave extra'),(17,14,'Imóvel mobiliado');
/*!40000 ALTER TABLE `opcionais` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-24 19:42:26
