-- phpMyAdmin SQL Dump
-- version 3.2.2.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2010 at 10:27 AM
-- Server version: 5.1.37
-- PHP Version: 5.2.10-2ubuntu6.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pizza_C11E4_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `ingredients`
--

CREATE TABLE IF NOT EXISTS `ingredients` (
  `name` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ingredients`
--

INSERT INTO `ingredients` (`name`, `id`, `price`) VALUES
('blat', 1, 2.5),
('sunca', 2, 3),
('mozzarella', 3, 1.5),
('parmezan', 4, 1),
('ciuperci', 5, 1),
('porumb', 6, 2),
('bacon', 7, 2.7),
('sos tomate', 8, 0.8);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `delivered` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `address`, `delivered`) VALUES
(1, 'Allea Studentilor, 11C, 409', 0),
(2, 'Aleea Studentilor, 11C, 432', 1),
(3, 'Orastie, 54', 0);

-- --------------------------------------------------------

--
-- Table structure for table `orders_pizzas`
--

CREATE TABLE IF NOT EXISTS `orders_pizzas` (
  `id_order` int(11) DEFAULT NULL,
  `id_pizza` int(11) DEFAULT NULL,
  KEY `fk_orders_pizzas_1` (`id_order`),
  KEY `fk_orders_pizzas_2` (`id_pizza`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders_pizzas`
--

INSERT INTO `orders_pizzas` (`id_order`, `id_pizza`) VALUES
(1, 1),
(1, 4),
(1, 1),
(2, 3),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `pizzas`
--

CREATE TABLE IF NOT EXISTS `pizzas` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `permanent` tinyint(4) DEFAULT '0',
  `link` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pizzas`
--

INSERT INTO `pizzas` (`id`, `name`, `description`, `permanent`, `link`, `date`) VALUES
(1, 'prosciuto', 'pizza prosciuto', 0, './photos/prosciuto.jpg', '10.05.2010.13.30'),
(2, 'milaneza', 'pizza milaneza', 0, './photos/milaneza.jpg', '13.05.2010.18.56'),
(3, 'capriciosa', 'pizza capriciosa', 1, './photos/capriciosa.jpg', '13.05.2010.19.16'),
(4, 'prosciuto funghi', 'prosciuto cu ciuperci', 1, './photos/prosciutoFunghi.jpg', '13.05.2010.19.30');

-- --------------------------------------------------------

--
-- Table structure for table `pizzas_ingredients`
--

CREATE TABLE IF NOT EXISTS `pizzas_ingredients` (
  `id_pizza` int(11) DEFAULT NULL,
  `id_ingredient` int(11) DEFAULT NULL,
  KEY `fk_pizzas_ingredients_1` (`id_pizza`),
  KEY `fk_pizzas_ingredients_2` (`id_ingredient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pizzas_ingredients`
--

INSERT INTO `pizzas_ingredients` (`id_pizza`, `id_ingredient`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 8),
(4, 1),
(4, 2),
(4, 3),
(4, 5),
(4, 8);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders_pizzas`
--
ALTER TABLE `orders_pizzas`
  ADD CONSTRAINT `fk_orders_pizzas_1` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_orders_pizzas_2` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pizzas_ingredients`
--
ALTER TABLE `pizzas_ingredients`
  ADD CONSTRAINT `fk_pizzas_ingredients_1` FOREIGN KEY (`id_pizza`) REFERENCES `pizzas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pizzas_ingredients_2` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredients` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
