CREATE DATABASE  IF NOT EXISTS `researchspecialty` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `researchspecialty`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: researchspecialty
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `abstract`
--

DROP TABLE IF EXISTS `abstract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abstract` (
  `abstract_ID` int NOT NULL AUTO_INCREMENT,
  `abstractTitle` varchar(300) DEFAULT NULL,
  `abstractContent` mediumtext,
  PRIMARY KEY (`abstract_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abstract`
--

LOCK TABLES `abstract` WRITE;
/*!40000 ALTER TABLE `abstract` DISABLE KEYS */;
INSERT INTO `abstract` VALUES (1,'A Modular Architecture for Scalable Inter-Domain Routing','Border Gateway Protocol (BGP) is the de facto standard for inter-domain routing. BGP faces challenges such as increases in routing table size proportional to increases in the number of networks, high convergence times, and high churn rates, among others. Modularity in routing can address several of these challenges. In this article, we discuss a modular routing architecture, its application to the current Internet, and evaluate its scalability in terms of churn rate and routing table size. Optimization opportunities offered by the modular routing architecture are discussed. Briefly, a transition approach to deploy such an architecture, through a Layer 2.5 protocol, is also presented.'),(2,'Producing and evaluating crowdsourced computer security attack trees','We describe the recent developments of an open-source project called RATCHET that can be used by groups of users to collectively construct attack trees. We present the RATCHET framework as well as a model for testing and evaluation of the produced attack trees. RATCHET has been tested in classroom settings with positive results and this paper presents the plans for expanding its outreach to the community at large and building attack trees through crowdsourcing. This paper gives an overview of RATCHET and an introduction to its use.'),(3,'Advancing Ubiquitous Collaboration for Telehealth - A Framework to Evaluate Technology-mediated Collaborative Workflow for Telehealth, Hypertension Exam Workflow Study','Healthcare systems are under siege globally regarding technology adoption; the recent pandemic has only magnified the issues. Providers and patients alike look to new enabling technologies to establish real-time connectivity and capability for a growing range of remote telehealth solutions. The migration to new technology is not as seamless as clinicians and patients would like since the new workflows pose new responsibilities and barriers to adoption across the telehealth ecosystem. Technology-mediated workflows (integrated software and personal medical devices) are increasingly important in patient-centered healthcare; software-intense systems will become integral in prescribed treatment plans [1]. My research explored the path to ubiquitous adoption of technology-mediated workflows from historic roots in the CSCW domain to arrive at an expanded method for evaluating collaborative workflows. This new approach for workflow evaluation, the Collaborative Space – Analysis Framework (CS-AF), was then deployed in a telehealth empirical study of a hypertension exam workflow to evaluate the gains and gaps associated with a technology-mediated workflow enhancements. My findings indicate that technology alone is not the solution; rather, it is an integrated approach that establishes “relative advantage” for patients’ in their personal healthcare plans. Results suggest wider use of the CS-AF for future technology-mediated workflow evaluations in telehealth and other technology-rich domains.'),(4,'Emotional Empathy and Facial Mimicry of Avatar Faces','We explore the extent to which empathetic reactions are elicited when subjects view 3D motion-capture driven avatar faces compared to viewing human faces. Through a remote study, we captured subjects\' facial reactions when viewing avatar and humans faces, and elicited self reported feedback regarding empathy. Avatar faces varied by gender and realism. Results show no sign of facial mimicry; only mimicking of slight facial movements with no solid consistency. Participants tended to empathize with avatars when they could adequately identify the stimulus\' emotion. As avatar realism increased, it negatively impacted the subjects\' feelings towards the stimuli.'),(5,'Assessing the Modular-based Digital Forensics Game for Entry Level Students','Cybersecurity and forensics are among the most critical areas of national importance in growing need of knowledgeable professionals. In an effort to identify and attract more students to forensics programs, RIT faculty have been working with Onondaga Community College and Corning Community College to develop a sequence of entertaining, engaging, and educational forensic games, suitable for first year students in college. Following narrative and/or storylines of the game via interactive dialogs and visualized abstract concepts, we expected that students will be motivated and engaged to obtain the necessary knowledge, and to develop their problem-solving skills while playing the game. This approach will potentially shorten pre-requisite chains of advanced courses, thereby reducing the time and cost for obtaining cybersecurity knowledge and skills for students. This project is funded in part by the National Science Foundation under Award DUE-1400567. This paper will primary focus on assessing the major project goals and objectives after briefly introducing our modular-based educational game framework and our pilot project applying this game framework in digital forensics courses. Based on three years’ experiments, the game modules appear to be effective in teaching the processes of digital forensics. This paper will share our evaluation strategy and results of assessing the effectiveness of the games-based course modules via a comprehensive evaluation plan.'),(6,'On the viability of data collection using Google Rapid Response for enterprise-level malware research','With the increasing number of attacks on enterprises, which often involves the deployment of some form of malware, an automated method for performing large-scale triage has become essential to the timely resolution of an incident. The purpose of this project is to combine the data collection capabilities of Google Rapid Response (GRR) with the flexible automation of Cuckoo Sandbox, to collect data for training machine learning models that perform triage on enterprise machines. To evaluate the viability of this approach, we investigate the artifacts that can be collected using GRR and whether they provide salient features for triage automation. Furthermore, we consider the speed of data collection and the consistency of the collected data when scaling the analysis environment to include more machines. Moreover, we develop multiple simulations of benign computer usage for both generating the benign dataset and as real-world background activities when injecting malware samples. Examples of the simulations include surfing the web, using a word editor, and python coding using an IDE. We investigated a total of 39 Windows artifacts that can be remotely collected using GRR\'s StartFlowAndWait API. StartFlowAndWait blocks execution until the artifacts are collected or until an error message is received. Collecting all 39 artifacts required over 1 h on a dedicated network connection between the analysis VM and the GRR server. However, handpicking only 11 artifacts reduces the average data collection time to 4 min. We also found that increasing the number of analysis machines caused less artifacts to be successfully collected. This drop in reliability is due to network congestion and the waste of other computing resources from the blocking mechanism of StartFlowAndWait. Although GRR is designed for large-scale deployment, we found that the default configuration of GRR is not sufficient for malware research data collection when using StartFlowAndWait instead of StartFlow.');
/*!40000 ALTER TABLE `abstract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `faculty_ID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `buildingNumber` int DEFAULT NULL,
  `officeNumber` int DEFAULT NULL,
  `work_num` varchar(10) DEFAULT NULL,
  `cell_num` varchar(10) DEFAULT NULL,
  `office_hours` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`faculty_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Christopher','Bondy',NULL,NULL,'cxbppr@rit.edu',70,2323,NULL,NULL,NULL),(2,'Joe','Geigel',NULL,NULL,'joe.geigel@rit.edu',70,3527,NULL,NULL,NULL),(3,'Yin','Pan',NULL,NULL,'yin.pan@rit.edu',NULL,NULL,NULL,NULL,NULL),(4,'Daniel','Bogaard','dsbics','61a7508ed1b04e9ada836fcd14d4d8ef5687c7dd','dsbics@rit.edu',70,2111,NULL,NULL,NULL),(5,'Erik','Golen','efgics','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','efgics@rit.edu',70,2671,NULL,NULL,NULL);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_abstract`
--

DROP TABLE IF EXISTS `faculty_abstract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_abstract` (
  `faculty_ID` int NOT NULL AUTO_INCREMENT,
  `abstract_ID` int NOT NULL,
  PRIMARY KEY (`faculty_ID`,`abstract_ID`),
  KEY `faculty_abstract_fk2` (`abstract_ID`),
  CONSTRAINT `faculty_abstract_fk1` FOREIGN KEY (`faculty_ID`) REFERENCES `faculty` (`faculty_ID`),
  CONSTRAINT `faculty_abstract_fk2` FOREIGN KEY (`abstract_ID`) REFERENCES `abstract` (`abstract_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_abstract`
--

LOCK TABLES `faculty_abstract` WRITE;
/*!40000 ALTER TABLE `faculty_abstract` DISABLE KEYS */;
INSERT INTO `faculty_abstract` VALUES (1,1),(1,2),(2,2),(3,3),(4,4),(5,5),(5,6);
/*!40000 ALTER TABLE `faculty_abstract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_specialty`
--

DROP TABLE IF EXISTS `faculty_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty_specialty` (
  `faculty_ID` int NOT NULL,
  `specialty_ID` int NOT NULL,
  PRIMARY KEY (`faculty_ID`,`specialty_ID`),
  KEY `faculty_specialty_fk2` (`specialty_ID`),
  CONSTRAINT `faculty_specialty_fk1` FOREIGN KEY (`faculty_ID`) REFERENCES `faculty` (`faculty_ID`),
  CONSTRAINT `faculty_specialty_fk2` FOREIGN KEY (`specialty_ID`) REFERENCES `specialty` (`specialty_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_specialty`
--

LOCK TABLES `faculty_specialty` WRITE;
/*!40000 ALTER TABLE `faculty_specialty` DISABLE KEYS */;
INSERT INTO `faculty_specialty` VALUES (4,1),(1,2),(3,2),(2,7),(5,7),(5,9),(5,11);
/*!40000 ALTER TABLE `faculty_specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organization` (
  `organization_ID` int NOT NULL AUTO_INCREMENT,
  `organization` varchar(60) DEFAULT NULL,
  `keyword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`organization_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'Henrietta Public Library','Trait1'),(2,'Urgent Care Center','Trait2'),(3,'Goldman Sachs','Trait3');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialty`
--

DROP TABLE IF EXISTS `specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialty` (
  `specialty_ID` int NOT NULL AUTO_INCREMENT,
  `specialty` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`specialty_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialty`
--

LOCK TABLES `specialty` WRITE;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` VALUES (1,'Facial Mimicry'),(2,'Web-Development'),(3,'Database'),(4,'Robotics'),(5,'AI'),(6,'Vision and Graphics'),(7,'Security and Privacy'),(8,'Gene Therapy'),(9,'Digital Forensics'),(10,'Modern Architecture'),(11,'Malware'),(12,'Systems and Networking');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_ID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `portfolio_link` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`student_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Chloe','Calderon','cjc3239@rit.edu','cjc3239','204036a1ef6e7360e536300ea78c6aeb4a9333dd','http://chloesportfolio.com'),(2,'Jason','Chen','jc3496@g.rit.edu','jc3496','2439e0457579ab4fd962cbd80b9206aca794cc38','http://jasonsportfolio.com'),(3,'Renee','Bogdany','rkb4472@rit.edu','rkb4472','5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8','http://reneeportfolio.com');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_specialty`
--

DROP TABLE IF EXISTS `student_specialty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_specialty` (
  `student_ID` int NOT NULL,
  `specialty_ID` int NOT NULL,
  PRIMARY KEY (`student_ID`,`specialty_ID`),
  KEY `student_specialty_fk2` (`specialty_ID`),
  CONSTRAINT `student_specialty_fk1` FOREIGN KEY (`student_ID`) REFERENCES `student` (`student_ID`),
  CONSTRAINT `student_specialty_fk2` FOREIGN KEY (`specialty_ID`) REFERENCES `specialty` (`specialty_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_specialty`
--

LOCK TABLES `student_specialty` WRITE;
/*!40000 ALTER TABLE `student_specialty` DISABLE KEYS */;
INSERT INTO `student_specialty` VALUES (3,2),(1,7),(2,11);
/*!40000 ALTER TABLE `student_specialty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'researchspecialty'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-11 19:35:29
