-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 07, 2023 at 10:00 AM
-- Server version: 8.0.35-0ubuntu0.22.04.1
-- PHP Version: 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hk-sar`
--

-- --------------------------------------------------------

--
-- Table structure for table `minor_sector`
--

CREATE TABLE `minor_sector` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sub_sector_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `minor_sector`
--

INSERT INTO `minor_sector` (`id`, `created_at`, `updated_at`, `updated_by`, `name`, `sub_sector_id`) VALUES
(1, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Bakery & confectionery products', 3),
(2, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Beverages', 3),
(3, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Fish & fish products', 3),
(4, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Meat & meat products', 3),
(5, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Milk & dairy products', 3),
(6, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Other', 3),
(7, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Sweets & snack food', 3),
(8, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Bathroom/sauna', 4),
(9, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Bedroom', 4),
(10, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Childrenâ€™s room', 4),
(11, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Kitchen', 4),
(12, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Living room', 4),
(13, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Office', 4),
(14, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Other (Furniture)', 4),
(15, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Outdoor', 4),
(16, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Project furniture', 4),
(24, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Machinery components', 5),
(25, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Machinery equipment/tools', 5),
(26, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Manufacture of machinery', 5),
(27, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Maritime', 5),
(28, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Metal structures', 5),
(29, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Other', 5),
(30, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Repair and maintenance service', 5),
(31, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Construction of metal structures', 6),
(32, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Houses and buildings', 6),
(33, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Metal products', 6),
(34, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Metal works', 6),
(35, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Packaging', 7),
(36, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Plastic goods', 7),
(37, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Plastic processing technology', 7),
(38, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Plastic profiles', 7),
(39, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Advertising', 8),
(40, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Book/Periodicals printing', 8),
(41, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Labelling and packaging printing', 8),
(42, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Clothing', 9),
(43, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Textile', 9),
(44, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Other (Wood)', 10),
(45, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Wooden building materials', 10),
(46, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Wooden houses', 10),
(47, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Data processing, Web portals, E-marketing', 16),
(48, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Programming, Consultancy', 16),
(49, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Software, Hardware', 16),
(50, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Telecommunications', 16),
(51, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Air', 19),
(52, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Rail', 19),
(53, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Road', 19),
(54, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Water', 19);

-- --------------------------------------------------------

--
-- Table structure for table `sector`
--

CREATE TABLE `sector` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `sector_name` varchar(255) DEFAULT NULL,
  `minor_sector_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sector`
--

INSERT INTO `sector` (`id`, `created_at`, `updated_at`, `updated_by`, `sector_name`, `minor_sector_id`) VALUES
(1, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Aluminium and steel workboats', 27),
(2, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Boat/Yacht building', 27),
(3, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Ship repair and conversion', 27),
(4, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'CNC-machining', 34),
(5, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Forgings, Fasteners', 34),
(6, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Gas, Plasma, Laser cutting', 34),
(7, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'MIG, TIG, Aluminum welding', 34),
(8, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Blowing', 37),
(9, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Moulding', 37),
(10, '2023-12-03 23:11:27.000000', '2023-12-03 23:11:27.000000', NULL, 'Plastics welding and processing', 37);

-- --------------------------------------------------------

--
-- Table structure for table `sector_category`
--

CREATE TABLE `sector_category` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sector_category`
--

INSERT INTO `sector_category` (`id`, `name`, `created_at`, `updated_at`, `updated_by`) VALUES
(1, 'Manufacturing', '2023-12-03 21:27:20.000000', '2023-12-03 00:00:00.000000', NULL),
(2, 'Other', '2023-12-03 21:27:20.000000', '2023-12-03 21:27:20.000000', NULL),
(3, 'Service', '2023-12-03 21:27:20.000000', '2023-12-03 21:27:20.000000', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `sub_sector`
--

CREATE TABLE `sub_sector` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sector_category_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sub_sector`
--

INSERT INTO `sub_sector` (`id`, `name`, `sector_category_id`, `created_at`, `updated_at`, `updated_by`) VALUES
(1, 'Construction materials', 1, '2023-12-03 21:52:40.000000', '2023-12-03 00:00:00.000000', NULL),
(2, 'Electronics and Optics', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(3, 'Food and Beverage', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(4, 'Furniture', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(5, 'Machinery', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(6, 'Metalworking', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(7, 'Plastic and Rubber', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(8, 'Printing', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(9, 'Textile and Clothing', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(10, 'Wood', 1, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(11, 'Creative industries', 2, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(12, 'Energy technology', 2, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(13, 'Environment', 2, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(14, 'Business services', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(15, 'Engineering', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(16, 'Information Technology and Telecommunications', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(17, 'Tourism', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(18, 'Translation services', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL),
(19, 'Transport and Logistics', 3, '2023-12-03 21:52:40.000000', '2023-12-03 21:52:40.000000', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `agree_terms` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('USER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_sector`
--

CREATE TABLE `user_sector` (
  `id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `sector_id` bigint DEFAULT NULL,
  `sector_type` enum('MINOR_SECTOR','SECTOR','SECTOR_CATEGORY','SUB_SECTOR') NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `minor_sector`
--
ALTER TABLE `minor_sector`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqrw8ouvisc3be0klg35rutpsk` (`sub_sector_id`);

--
-- Indexes for table `sector`
--
ALTER TABLE `sector`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nc7o546ndeutkx3gs2tup04mr` (`sector_name`),
  ADD KEY `FK8yrk72qhto12s81ikmgfs2mm5` (`minor_sector_id`);

--
-- Indexes for table `sector_category`
--
ALTER TABLE `sector_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4jakjlrkweh1exlha4jb2lrlb` (`name`);

--
-- Indexes for table `sub_sector`
--
ALTER TABLE `sub_sector`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t64kc6oo3etfqdx6xkrebkl43` (`name`),
  ADD KEY `FKgi5en0migyp8n0y2pw6ilsfuj` (`sector_category_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  ADD UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`);

--
-- Indexes for table `user_sector`
--
ALTER TABLE `user_sector`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `minor_sector`
--
ALTER TABLE `minor_sector`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `sector`
--
ALTER TABLE `sector`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `sector_category`
--
ALTER TABLE `sector_category`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `sub_sector`
--
ALTER TABLE `sub_sector`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_sector`
--
ALTER TABLE `user_sector`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `minor_sector`
--
ALTER TABLE `minor_sector`
  ADD CONSTRAINT `FKqrw8ouvisc3be0klg35rutpsk` FOREIGN KEY (`sub_sector_id`) REFERENCES `sub_sector` (`id`);

--
-- Constraints for table `sector`
--
ALTER TABLE `sector`
  ADD CONSTRAINT `FK8yrk72qhto12s81ikmgfs2mm5` FOREIGN KEY (`minor_sector_id`) REFERENCES `minor_sector` (`id`);

--
-- Constraints for table `sub_sector`
--
ALTER TABLE `sub_sector`
  ADD CONSTRAINT `FKgi5en0migyp8n0y2pw6ilsfuj` FOREIGN KEY (`sector_category_id`) REFERENCES `sector_category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
