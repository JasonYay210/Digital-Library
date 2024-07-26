-- Lily Chau, Nick Criss, Renee Bogdany, Chloe Calderon, Natnail Tolossa, Jason Chen
-- ISTE 330 - Group 1
-- SQL Database

/*      CREATES DATABASE        */
DROP DATABASE IF exists researchSpecialty; 

CREATE DATABASE researchSpecialty;
USE researchSpecialty;

/*      FACULTY TABLE       */
-- if faculty table alr exists drop
DROP TABLE IF EXISTS faculty;

-- builds faculty table
    CREATE TABLE faculty (
        faculty_ID INT NOT NULL AUTO_INCREMENT,
        firstName VARCHAR(45),
        lastName VARCHAR(45),
        username VARCHAR(45),
        password VARCHAR(45),
        email VARCHAR(45),
        buildingNumber INT,
        officeNumber INT,
        work_num VARCHAR(10),
        cell_num VARCHAR(10),
        office_hours VARCHAR(150),
        CONSTRAINT faculty_pk PRIMARY KEY (faculty_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

/*      FACULTY ABSTRACT TABLE       */
-- if faculty_abstract table alr exists drop
DROP TABLE IF EXISTS faculty_abstract;

/*      ABSTRACT TABLE       */
-- if abstract table alr exists drop
DROP TABLE IF EXISTS abstract;

-- builds abstract table
    CREATE TABLE abstract(
        abstract_ID INT NOT NULL AUTO_INCREMENT,
        abstractTitle VARCHAR(300),
        abstractContent MEDIUMTEXT,
        CONSTRAINT abstract_pk PRIMARY KEY (abstract_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;
    
-- builds faculty_abstract table
    CREATE TABLE faculty_abstract (
        faculty_ID INT NOT NULL AUTO_INCREMENT,
        abstract_ID INT NOT NULL,
        CONSTRAINT faculty_abstract_pk PRIMARY KEY (faculty_ID, abstract_ID),
        CONSTRAINT faculty_abstract_fk1 FOREIGN KEY (faculty_ID) REFERENCES faculty(faculty_ID),
        CONSTRAINT faculty_abstract_fk2 FOREIGN KEY (abstract_ID) REFERENCES abstract(abstract_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

/*      FACULTY SPECIALTY TABLE       */
-- if faculty specialty table alr exists drop
DROP TABLE IF EXISTS faculty_specialty;

/*      SPECIALTY TABLE       */
-- if specialty table alr exists drop
DROP TABLE IF EXISTS specialty;

-- builds specialty table
    CREATE TABLE specialty (
        specialty_ID INT NOT NULL AUTO_INCREMENT,
        specialty VARCHAR(45),    
        CONSTRAINT specialty_pk PRIMARY KEY (specialty_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- builds faculty specialty table
    CREATE TABLE faculty_specialty (
        faculty_ID INT NOT NULL,
        specialty_ID INT NOT NULL,
        CONSTRAINT faculty_specialty_pk PRIMARY KEY (faculty_ID, specialty_ID),
        CONSTRAINT faculty_specialty_fk1 FOREIGN KEY (faculty_ID) references faculty(faculty_ID),
        CONSTRAINT faculty_specialty_fk2 FOREIGN KEY (specialty_ID) references specialty(specialty_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;


/*      STUDENT TABLE       */
-- if student table alr exists drop
DROP TABLE IF EXISTS student;

-- builds student table
    CREATE TABLE student (
        student_ID INT NOT NULL AUTO_INCREMENT,
        firstName VARCHAR(45),
        lastName VARCHAR(45),
        email VARCHAR(45),
        userName VARCHAR(45),
        password VARCHAR(45),
        portfolio_link VARCHAR(120),
        CONSTRAINT student_pk PRIMARY KEY (student_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

/*      STUDENT specialty TABLE       */
-- if student_specialty table alr exists drop
DROP TABLE IF EXISTS student_specialty;

-- builds student_specialty table
    CREATE TABLE student_specialty (
        student_ID INT NOT NULL,
        specialty_ID INT NOT NULL,
        CONSTRAINT student_specialty_pk PRIMARY KEY (student_ID, specialty_ID),
        CONSTRAINT student_specialty_fk1 FOREIGN KEY (student_ID) references student(student_ID),
        CONSTRAINT student_specialty_fk2 FOREIGN KEY (specialty_ID) references specialty(specialty_ID)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;

/*      ORGANIZATION TABLE       */
-- if organization table alr exists drop
DROP TABLE IF EXISTS organization;

-- builds organization table
    CREATE TABLE organization (
        organization_ID INT PRIMARY KEY AUTO_INCREMENT,
        organization VARCHAR(60),
        keyword VARCHAR(45)
    ) ENGINE = InnoDB DEFAULT CHARSET=utf8;


/*  INSERT STATEMENTS */

/*      INSERT STATEMENTS      */


-- Insert data into the faculty table
INSERT INTO faculty (firstName, lastName, email, buildingNumber, officeNumber ) VALUES
('Christopher', 'Bondy', 'cxbppr@rit.edu', 070, 2323),
('Joe', 'Geigel', 'joe.geigel@rit.edu', 070, 3527),
('Yin', 'Pan', 'yin.pan@rit.edu', null, null);

INSERT INTO faculty (firstName, lastName, email, username, password, buildingNumber, officeNumber ) VALUES
('Daniel', 'Bogaard', 'dsbics@rit.edu', 'dsbics', '61a7508ed1b04e9ada836fcd14d4d8ef5687c7dd', 070, 2111),
('Erik', 'Golen', 'efgics@rit.edu', 'efgics', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 070, 2671);

-- Insert data into the abstract table
INSERT INTO abstract (abstractTitle, abstractContent) VALUES
('A Modular Architecture for Scalable Inter-Domain Routing', 'Border Gateway Protocol (BGP) is the de facto standard for inter-domain routing. BGP faces challenges such as increases in routing table size proportional to increases in the number of networks, high convergence times, and high churn rates, among others. Modularity in routing can address several of these challenges. In this article, we discuss a modular routing architecture, its application to the current Internet, and evaluate its scalability in terms of churn rate and routing table size. Optimization opportunities offered by the modular routing architecture are discussed. Briefly, a transition approach to deploy such an architecture, through a Layer 2.5 protocol, is also presented.'),
('Producing and evaluating crowdsourced computer security attack trees', 'We describe the recent developments of an open-source project called RATCHET that can be used by groups of users to collectively construct attack trees. We present the RATCHET framework as well as a model for testing and evaluation of the produced attack trees. RATCHET has been tested in classroom settings with positive results and this paper presents the plans for expanding its outreach to the community at large and building attack trees through crowdsourcing. This paper gives an overview of RATCHET and an introduction to its use.'),
('Advancing Ubiquitous Collaboration for Telehealth - A Framework to Evaluate Technology-mediated Collaborative Workflow for Telehealth, Hypertension Exam Workflow Study', 'Healthcare systems are under siege globally regarding technology adoption; the recent pandemic has only magnified the issues. Providers and patients alike look to new enabling technologies to establish real-time connectivity and capability for a growing range of remote telehealth solutions. The migration to new technology is not as seamless as clinicians and patients would like since the new workflows pose new responsibilities and barriers to adoption across the telehealth ecosystem. Technology-mediated workflows (integrated software and personal medical devices) are increasingly important in patient-centered healthcare; software-intense systems will become integral in prescribed treatment plans [1]. My research explored the path to ubiquitous adoption of technology-mediated workflows from historic roots in the CSCW domain to arrive at an expanded method for evaluating collaborative workflows. This new approach for workflow evaluation, the Collaborative Space – Analysis Framework (CS-AF), was then deployed in a telehealth empirical study of a hypertension exam workflow to evaluate the gains and gaps associated with a technology-mediated workflow enhancements. My findings indicate that technology alone is not the solution; rather, it is an integrated approach that establishes “relative advantage” for patients’ in their personal healthcare plans. Results suggest wider use of the CS-AF for future technology-mediated workflow evaluations in telehealth and other technology-rich domains.'),
('Emotional Empathy and Facial Mimicry of Avatar Faces', "We explore the extent to which empathetic reactions are elicited when subjects view 3D motion-capture driven avatar faces compared to viewing human faces. Through a remote study, we captured subjects' facial reactions when viewing avatar and humans faces, and elicited self reported feedback regarding empathy. Avatar faces varied by gender and realism. Results show no sign of facial mimicry; only mimicking of slight facial movements with no solid consistency. Participants tended to empathize with avatars when they could adequately identify the stimulus' emotion. As avatar realism increased, it negatively impacted the subjects' feelings towards the stimuli."),
('Assessing the Modular-based Digital Forensics Game for Entry Level Students', 'Cybersecurity and forensics are among the most critical areas of national importance in growing need of knowledgeable professionals. In an effort to identify and attract more students to forensics programs, RIT faculty have been working with Onondaga Community College and Corning Community College to develop a sequence of entertaining, engaging, and educational forensic games, suitable for first year students in college. Following narrative and/or storylines of the game via interactive dialogs and visualized abstract concepts, we expected that students will be motivated and engaged to obtain the necessary knowledge, and to develop their problem-solving skills while playing the game. This approach will potentially shorten pre-requisite chains of advanced courses, thereby reducing the time and cost for obtaining cybersecurity knowledge and skills for students. This project is funded in part by the National Science Foundation under Award DUE-1400567. This paper will primary focus on assessing the major project goals and objectives after briefly introducing our modular-based educational game framework and our pilot project applying this game framework in digital forensics courses. Based on three years’ experiments, the game modules appear to be effective in teaching the processes of digital forensics. This paper will share our evaluation strategy and results of assessing the effectiveness of the games-based course modules via a comprehensive evaluation plan.'),
('On the viability of data collection using Google Rapid Response for enterprise-level malware research', "With the increasing number of attacks on enterprises, which often involves the deployment of some form of malware, an automated method for performing large-scale triage has become essential to the timely resolution of an incident. The purpose of this project is to combine the data collection capabilities of Google Rapid Response (GRR) with the flexible automation of Cuckoo Sandbox, to collect data for training machine learning models that perform triage on enterprise machines. To evaluate the viability of this approach, we investigate the artifacts that can be collected using GRR and whether they provide salient features for triage automation. Furthermore, we consider the speed of data collection and the consistency of the collected data when scaling the analysis environment to include more machines. Moreover, we develop multiple simulations of benign computer usage for both generating the benign dataset and as real-world background activities when injecting malware samples. Examples of the simulations include surfing the web, using a word editor, and python coding using an IDE. We investigated a total of 39 Windows artifacts that can be remotely collected using GRR's StartFlowAndWait API. StartFlowAndWait blocks execution until the artifacts are collected or until an error message is received. Collecting all 39 artifacts required over 1 h on a dedicated network connection between the analysis VM and the GRR server. However, handpicking only 11 artifacts reduces the average data collection time to 4 min. We also found that increasing the number of analysis machines caused less artifacts to be successfully collected. This drop in reliability is due to network congestion and the waste of other computing resources from the blocking mechanism of StartFlowAndWait. Although GRR is designed for large-scale deployment, we found that the default configuration of GRR is not sufficient for malware research data collection when using StartFlowAndWait instead of StartFlow.");

-- Insert data into faculty_abstract table
INSERT INTO faculty_abstract (faculty_ID, abstract_ID) VALUES
(1, 1),
(1, 2),
(2, 2),
(4, 4),
(3, 3),
(5, 5),
(5, 6);


-- Insert data into the specialty table
INSERT INTO specialty (specialty) VALUES
('Facial Mimicry'),
('Web-Development'),
('Database'),
('Robotics'),
('AI'),
('Vision and Graphics'),
('Security and Privacy'),
('Gene Therapy'),
('Digital Forensics'),
('Modern Architecture'),
('Malware'),
('Systems and Networking');

-- Insert data into the faculty_specialty table
INSERT INTO faculty_specialty (faculty_ID, specialty_ID) VALUES
(1, 2),
(2, 7),
(3, 2),
(4, 1),
(5, 7),
(5, 11),
(5, 9);

-- Insert data into the student table
INSERT INTO student (firstName, lastName, email, userName, password, portfolio_link) VALUES
('Chloe', 'Calderon', 'cjc3239@rit.edu', 'cjc3239', '204036a1ef6e7360e536300ea78c6aeb4a9333dd', 'http://chloesportfolio.com'),
('Jason', 'Chen', 'jc3496@g.rit.edu', 'jc3496', '2439e0457579ab4fd962cbd80b9206aca794cc38', 'http://jasonsportfolio.com'),
('Renee', 'Bogdany', 'rkb4472@rit.edu', 'rkb4472', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'http://reneeportfolio.com');

-- Insert data into the student_specialty table
INSERT INTO student_specialty (student_ID, specialty_ID) VALUES
(1, 7),
(2, 11),
(3, 2);

-- Insert data into the organization table
INSERT INTO organization (organization_ID, organization, keyword) VALUES
(1, 'Henrietta Public Library', 'Web-Development'),
(2, 'Urgent Care Center', 'Malware'),
(3, 'Goldman Sachs', 'Database');
