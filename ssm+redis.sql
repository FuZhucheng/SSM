-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.51 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 ssm 的数据库结构
DROP DATABASE IF EXISTS `ssm`;
CREATE DATABASE IF NOT EXISTS `ssm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ssm`;

-- 导出  表 ssm.black_list 结构
DROP TABLE IF EXISTS `black_list`;
CREATE TABLE IF NOT EXISTS `black_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `black_user_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.black_list 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `black_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `black_list` ENABLE KEYS */;

-- 导出  表 ssm.gag 结构
DROP TABLE IF EXISTS `gag`;
CREATE TABLE IF NOT EXISTS `gag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gag_time` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `gag_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.gag 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `gag` DISABLE KEYS */;
INSERT INTO `gag` (`id`, `create_time`, `gag_time`, `user_id`) VALUES
	(1, '2017-04-01 16:41:27', '2017-04-01', 1);
/*!40000 ALTER TABLE `gag` ENABLE KEYS */;

-- 导出  表 ssm.score 结构
DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `change_type` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `score` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.score 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` (`id`, `change_type`, `create_time`, `score`, `user_id`) VALUES
	(1, '??', '2017-03-31 17:02:33', 10, NULL),
	(2, 'asf', '2017-03-31 17:03:30', 10, NULL),
	(3, '充钱', '2017-03-31 17:05:06', 10, NULL),
	(4, '充钱', '2017-03-31 17:05:59', 10, NULL),
	(5, '充钱', '2017-03-31 17:06:10', 10, NULL),
	(6, '充钱钱', '2017-03-31 17:06:24', 10, NULL),
	(7, '充钱钱', '2017-03-31 17:30:24', 10, NULL),
	(8, '充钱钱', '2017-03-31 17:31:25', 10, NULL),
	(9, '充钱钱', '2017-03-31 18:40:58', 10, NULL),
	(10, '充钱钱', '2017-03-31 19:22:04', 10, NULL),
	(11, '玩游戏', '2017-03-31 20:01:34', 10, NULL),
	(12, '玩游戏', '2017-03-31 20:13:45', 10, NULL),
	(13, '玩游戏', '2017-03-31 20:13:53', 10, NULL),
	(14, '玩游戏', '2017-03-31 20:17:17', 10, NULL),
	(15, '玩游戏', '2017-03-31 20:36:52', 10, NULL),
	(16, '玩游戏', '2017-03-31 20:36:56', 10, NULL),
	(17, '充钱钱', '2017-04-01 16:32:09', 10, 1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;

-- 导出  表 ssm.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `pay_money` bigint(20) NOT NULL DEFAULT '0',
  `country` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `score` bigint(20) NOT NULL DEFAULT '0',
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `avatar`, `pay_money`, `country`, `create_time`, `score`, `latitude`, `longitude`, `province`, `sex`, `password`) VALUES
	(1, '89564', NULL, 0, NULL, '2017-04-01 16:25:36', 0, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
