-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 21, 2020 at 04:16 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e-jawi`
--

-- --------------------------------------------------------

--
-- Table structure for table `note`
--

CREATE TABLE `note` (
  `notesID` int(11) NOT NULL,
  `notesTitle` varchar(255) NOT NULL,
  `notesContent` longtext NOT NULL,
  `teacherId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `note`
--

INSERT INTO `note` (`notesID`, `notesTitle`, `notesContent`, `teacherId`) VALUES
(1, 'chapter 3', '  hhh\r\n', 3),
(6, 'chapter 2', 'eee', 3),
(7, 'chapter 2', 'Chips or \"kerepek\" in Asia especially Malaysia is a part of culture and lifestyle. In a wider sense, it may reflect various realms of life and character in people according to their origins. Many chips are closely associated with snacks and junk food. It creates and represents culture and the environment, characterized by its local flavor. The unique flavor and affordability in the country are several factors contributing to its success and demand. Their popularity resulted in a wider variety of their kinds which includes bananas, potatoes, ginger and onions. Thus, this topic leads to a case study for the project.', 3),
(8, 'chapter 1', '  ttttttttttttttttttttttttt\r\n', 3),
(10, 'test 2', '<div data-oembed-url=\"https://www.youtube.com/watch?v=tB48sKGi5ag\">\r\n<div style=\"height:0; left:0; padding-bottom:56.25%; position:relative; width:100%\"><iframe allow=\"encrypted-media; accelerometer; gyroscope; picture-in-picture\" allowfullscreen=\"\" scrolling=\"no\" src=\"https://www.youtube.com/embed/tB48sKGi5ag?rel=0\" style=\"border: 0; top: 0; left: 0; width: 100%; height: 100%; position: absolute;\" tabindex=\"-1\"></iframe></div>\r\n</div>\r\n\r\n<p>&nbsp;</p>\r\n', 3);

-- --------------------------------------------------------

--
-- Table structure for table `parents`
--

CREATE TABLE `parents` (
  `id` int(11) NOT NULL,
  `parentEmail` varchar(255) NOT NULL,
  `parentPassword` varchar(255) NOT NULL,
  `parentName` varchar(255) NOT NULL,
  `parentAddress` varchar(255) NOT NULL,
  `parentPhone` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parents`
--

INSERT INTO `parents` (`id`, `parentEmail`, `parentPassword`, `parentName`, `parentAddress`, `parentPhone`) VALUES
(1, 'anim@yahoo.com', '81b073de9370ea873f548e31b8adc081', 'anim', 'jasin', '0234567891'),
(2, 'zaini@yahoo.com', '81dc9bdb52d04dc20036dbd8313ed055', 'zaini', 'jasin', '0234567891'),
(4, 'afifahkamaludin98@gmail.com', '202cb962ac59075b964b07152d234b70', 'imran ', 'jasin 123', '0234567891'),
(5, 'imranparent@gmail.com', '202cb962ac59075b964b07152d234b70', 'imran 123', 'jasin', '0234567891');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `questionID` int(11) NOT NULL,
  `questionName` varchar(255) NOT NULL,
  `questionNumber` int(11) NOT NULL,
  `option1` varchar(255) NOT NULL,
  `option2` varchar(255) NOT NULL,
  `option3` varchar(255) NOT NULL,
  `option4` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `quizID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`questionID`, `questionName`, `questionNumber`, `option1`, `option2`, `option3`, `option4`, `answer`, `quizID`) VALUES
(1, 'Pilih jwpan betul', 1, 'ayam', 'itik', 'kura kura', 'biri biri', 'itik', 1),
(2, 'test soalan 2', 2, 'jwpan 1', 'jwpan 2', 'jwpan 3', 'jwpan 4', 'jwpan 3', 1),
(3, 'soalan tiga', 3, 'jwpan A', 'jwpan B', 'jwpan C', 'jwpan D', 'jwpan B', 1),
(5, 'soalan 4', 1, 'jwpan A', 'jwpan B', 'jwpan C', 'jwpan D', 'jwpan B', 3),
(6, 'soalan 4', 4, 'jwpan A', 'jwpan B', 'jwpan C', 'jwpan D', 'jwpan C', 1);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `QuizId` int(11) NOT NULL,
  `QuizName` varchar(255) NOT NULL,
  `teacherId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`QuizId`, `QuizName`, `teacherId`) VALUES
(1, 'test 1', 3),
(3, 'test 2', 3),
(4, '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `quizID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `correctAnswer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`quizID`, `studentID`, `correctAnswer`) VALUES
(1, 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `studentanswer`
--

CREATE TABLE `studentanswer` (
  `studentid` int(11) NOT NULL,
  `questionid` int(11) NOT NULL,
  `choosenanswer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentanswer`
--

INSERT INTO `studentanswer` (`studentid`, `questionid`, `choosenanswer`) VALUES
(5, 1, 'ayam'),
(5, 2, 'jwpan 4'),
(5, 3, 'jwpan C'),
(5, 6, 'jwpan B');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `studentEmail` varchar(255) NOT NULL,
  `studentPassword` varchar(255) NOT NULL,
  `studentName` varchar(255) NOT NULL,
  `studentAddress` varchar(255) NOT NULL,
  `studentPhone` varchar(255) NOT NULL,
  `parentid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `studentEmail`, `studentPassword`, `studentName`, `studentAddress`, `studentPhone`, `parentid`) VALUES
(4, 'test@gmail.com', '202cb962ac59075b964b07152d234b70', 'test', 'dsa', '123', 2),
(5, 'abu@gmail.com', '202cb962ac59075b964b07152d234b70', 'abu', 'asdas 123', '0234567891', 4);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `teacherEmail` varchar(255) NOT NULL,
  `teacherPassword` varchar(255) NOT NULL,
  `teacherName` varchar(255) NOT NULL,
  `teacherAddress` varchar(255) NOT NULL,
  `teacherPhone` varchar(255) NOT NULL,
  `teacherRole` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `teacherEmail`, `teacherPassword`, `teacherName`, `teacherAddress`, `teacherPhone`, `teacherRole`) VALUES
(1, 'affinoor007@gmail.com', '81dc9bdb52d04dc20036dbd8313ed055', 'Mariam', 'Taman Ria', '0137098979', 'Teacher'),
(2, 'pah@yahoo.com', '202cb962ac59075b964b07152d234b70', 'Auni', 'Taman Bunga', '0137098979', 'Admin'),
(3, 'imran@gmail.com', '202cb962ac59075b964b07152d234b70', 'imrann@gmail.com', 'asdasd', '1233232', 'Teacher'),
(4, 'imransasudin@gmail.com', '202cb962ac59075b964b07152d234b70', 'imran 123', 'jasin', '123', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`notesID`),
  ADD KEY `note_fk1` (`teacherId`);

--
-- Indexes for table `parents`
--
ALTER TABLE `parents`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`questionID`),
  ADD KEY `questions_fk1` (`quizID`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`QuizId`),
  ADD KEY `quiz_fk1` (`teacherId`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`quizID`,`studentID`),
  ADD KEY `result_fk2` (`studentID`);

--
-- Indexes for table `studentanswer`
--
ALTER TABLE `studentanswer`
  ADD PRIMARY KEY (`studentid`,`questionid`),
  ADD KEY `studentanswer_fk2` (`questionid`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_fk1` (`parentid`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `note`
--
ALTER TABLE `note`
  MODIFY `notesID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `parents`
--
ALTER TABLE `parents`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `questionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `QuizId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_fk1` FOREIGN KEY (`teacherId`) REFERENCES `teachers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_fk1` FOREIGN KEY (`quizID`) REFERENCES `quiz` (`QuizId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `quiz`
--
ALTER TABLE `quiz`
  ADD CONSTRAINT `quiz_fk1` FOREIGN KEY (`teacherId`) REFERENCES `teachers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_fk1` FOREIGN KEY (`quizID`) REFERENCES `quiz` (`QuizId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `result_fk2` FOREIGN KEY (`studentID`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `studentanswer`
--
ALTER TABLE `studentanswer`
  ADD CONSTRAINT `studentanswer_fk1` FOREIGN KEY (`studentid`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `studentanswer_fk2` FOREIGN KEY (`questionid`) REFERENCES `questions` (`questionID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `student_fk1` FOREIGN KEY (`parentid`) REFERENCES `parents` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
