-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 20, 2025 at 05:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sys_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `dailytimerecords`
--

CREATE TABLE `dailytimerecords` (
  `record_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `entry_date` date NOT NULL,
  `time_in` time NOT NULL,
  `time_out` time NOT NULL,
  `month` varchar(20) NOT NULL,
  `hours_worked` decimal(10,0) NOT NULL,
  `overtime_hrs` decimal(10,0) NOT NULL,
  `absent` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `emp_fname` varchar(50) NOT NULL,
  `emp_middle` int(50) DEFAULT NULL,
  `emp_lname` varchar(50) NOT NULL,
  `emp_age` int(11) NOT NULL,
  `emp_sex` int(11) NOT NULL,
  `emp_add` varchar(50) NOT NULL,
  `emp_email` varchar(50) NOT NULL,
  `emp_contact` varchar(50) NOT NULL,
  `emp_hdate` date NOT NULL,
  `emp_dept` varchar(50) NOT NULL,
  `emp_position` varchar(50) NOT NULL,
  `emp_rate` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `m_reports`
--

CREATE TABLE `m_reports` (
  `m_report_id` int(11) NOT NULL,
  `report_type` varchar(255) NOT NULL,
  `month` varchar(20) NOT NULL,
  `year` int(11) NOT NULL,
  `total_salary_cost` decimal(15,2) NOT NULL,
  `total_contri_employee` decimal(15,2) NOT NULL,
  `total_contri_employer` decimal(15,2) NOT NULL,
  `date_generated` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` int(11) NOT NULL,
  `total_hours` decimal(10,0) NOT NULL,
  `total_overtime` decimal(10,0) NOT NULL,
  `sss` decimal(10,0) NOT NULL,
  `phil_health` decimal(10,0) NOT NULL,
  `pag_ibig` decimal(10,0) NOT NULL,
  `t_deductions` decimal(10,0) NOT NULL,
  `overtime_pay` decimal(10,0) NOT NULL,
  `net_pay` decimal(10,0) NOT NULL,
  `status` varchar(20) NOT NULL,
  `date_generated` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Super_Admin'),
(2, 'HR_Admin'),
(3, 'Employee'),
(4, 'Management');

-- --------------------------------------------------------

--
-- Table structure for table `sex`
--

CREATE TABLE `sex` (
  `sex_id` int(11) NOT NULL,
  `sex_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sex`
--

INSERT INTO `sex` (`sex_id`, `sex_name`) VALUES
(1, 'Male'),
(2, 'Female');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_email` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_pass` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  `emp_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_email`, `user_name`, `user_pass`, `status`, `role_id`, `emp_id`) VALUES
(2, 'admin@gmail.com', 'admin', 'qwe123!@#', 'Active', 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  ADD PRIMARY KEY (`record_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `emp_sex` (`emp_sex`);

--
-- Indexes for table `m_reports`
--
ALTER TABLE `m_reports`
  ADD PRIMARY KEY (`m_report_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `sex`
--
ALTER TABLE `sex`
  ADD PRIMARY KEY (`sex_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `emp_id` (`emp_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  MODIFY `record_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `m_reports`
--
ALTER TABLE `m_reports`
  MODIFY `m_report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `sex`
--
ALTER TABLE `sex`
  MODIFY `sex_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dailytimerecords`
--
ALTER TABLE `dailytimerecords`
  ADD CONSTRAINT `dailytimerecords_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`emp_sex`) REFERENCES `sex` (`sex_id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
