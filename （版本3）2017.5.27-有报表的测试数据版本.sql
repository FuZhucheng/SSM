-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.51-log - MySQL Community Server (GPL)
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

-- 导出  表 ssm.commodity_classification 结构
DROP TABLE IF EXISTS `commodity_classification`;
CREATE TABLE IF NOT EXISTS `commodity_classification` (
  `Classify_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Good_kinds_Name` varchar(50) DEFAULT '',
  `Upper_ID` int(11) DEFAULT '0',
  `Upper_Name` varchar(50) DEFAULT '',
  `Description` varchar(100) DEFAULT '',
  PRIMARY KEY (`Classify_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.commodity_classification 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `commodity_classification` DISABLE KEYS */;
INSERT INTO `commodity_classification` (`Classify_ID`, `Good_kinds_Name`, `Upper_ID`, `Upper_Name`, `Description`) VALUES
	(1, 'Furniture', 0, '', 'TopFloor'),
	(2, 'HomeTextiles', 0, '', 'TopFloor'),
	(3, 'HomeFurniture', 1, 'Furniture', 'MiddleLayer:Furniture>HomeFurniture'),
	(15, 'High-lowBed', 3, 'HomeFurniture', 'GroundFloor:Furniture>HomeFurniture>High-lowBed'),
	(16, 'LeatherBed', 3, 'HomeFurniture', 'GroundFloor:Furniture>HomeFurniture>LeatherBed');
/*!40000 ALTER TABLE `commodity_classification` ENABLE KEYS */;

-- 导出  表 ssm.commodity_list 结构
DROP TABLE IF EXISTS `commodity_list`;
CREATE TABLE IF NOT EXISTS `commodity_list` (
  `Good_ID` varchar(50) NOT NULL DEFAULT '',
  `Classify_ID` int(11) DEFAULT '0',
  `Classify_Description` varchar(150) DEFAULT '',
  `Good_Brand` varchar(50) DEFAULT '',
  `Good_Name` varchar(200) DEFAULT '',
  `Good_Price` varchar(50) DEFAULT '',
  `Store_Add` varchar(100) DEFAULT '',
  `Monthsale_Num` int(11) DEFAULT '0',
  `Color_Classification` varchar(500) DEFAULT '',
  `Comment_Num` int(11) DEFAULT '0',
  `Good_AfterRate` varchar(50) DEFAULT '',
  `IndustryCompare` varchar(50) DEFAULT '',
  `Good_Link` varchar(100) DEFAULT '',
  `Store_Name` varchar(100) DEFAULT '',
  `Store_Link` varchar(100) DEFAULT '',
  `Good_Hot` int(11) DEFAULT '0',
  `Store_Age` varchar(50) DEFAULT '',
  `Seller_Credit` varchar(50) DEFAULT '',
  `Ishas_License` int(11) DEFAULT '0',
  `DescriptionSituation` double DEFAULT '0',
  `ServiceAttitude` double DEFAULT '0',
  `LogisticsService` double DEFAULT '0',
  `Size` varchar(200) DEFAULT '',
  PRIMARY KEY (`Good_ID`),
  KEY `FK_Commodity_list_Commodity_classification` (`Classify_ID`),
  KEY `classify_index` (`Classify_Description`),
  CONSTRAINT `commodity_list_ibfk_1` FOREIGN KEY (`Classify_ID`) REFERENCES `commodity_classification` (`Classify_ID`),
  CONSTRAINT `commodity_list_ibfk_2` FOREIGN KEY (`Classify_ID`) REFERENCES `commodity_classification` (`Classify_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.commodity_list 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `commodity_list` DISABLE KEYS */;
INSERT INTO `commodity_list` (`Good_ID`, `Classify_ID`, `Classify_Description`, `Good_Brand`, `Good_Name`, `Good_Price`, `Store_Add`, `Monthsale_Num`, `Color_Classification`, `Comment_Num`, `Good_AfterRate`, `IndustryCompare`, `Good_Link`, `Store_Name`, `Store_Link`, `Good_Hot`, `Store_Age`, `Seller_Credit`, `Ishas_License`, `DescriptionSituation`, `ServiceAttitude`, `LogisticsService`, `Size`) VALUES
	('tb10025584930', 15, 'GroundFloor:Furniture>HomeFurniture>High-lowBed', '一品木舍', '广州榉木家具-进口榉木-双层床-上下床-子母床-高低床-', '2600.00 - 3228.00', '广东省广州市', 0, '上1.0+下1.2+无抽屉、上1.2+下1.5+无抽屉、上1.0+下1.2+抽屉、1.2+下1.5+有抽屉、', 0, '0.00%', '-4.25%', 'http://item.taobao.com/item.html?id=10025584930', '一品木舍家具', 'https://shop65772677.taobao.com', 22, '无', '2个蓝钻', 0, 3.6, 4.3, 4.3, '无'),
	('tb10025819846', 16, 'LeatherBed', '无', '商品床-进口', '', '', 0, '', 0, '', '', '', '', '', 0, '', '', 0, 0, 0, 0, ''),
	('tb10085076695', 16, 'LeatherBed', '无', '商品床', '', '', 0, '', 0, '', '', '', '', '', 0, '', '', 0, 0, 0, 0, ''),
	('tb10085076696', 15, 'GroundFloor:Furniture>HomeFurniture>High-lowBed', '无', '特价促销实木上下床松木床双层床员工床宿舍床公寓床子母床上下铺', '750.00 - 1200.00', '北京北京市', 0, '上下90*1.9、上90 下1.2、上90 下1.5、上1.2下1.5、上下1.2、', 0, '0.00%', '-4.23%', 'http://item.taobao.com/item.html?id=10085076696', '欣隆家具', 'https://xinlongjiaj.taobao.com', 1, '无', '3个红心', 0, 4.8, 4.8, 5, '无'),
	('tb10165274856', 15, 'GroundFloor:Furniture>HomeFurniture>TVCabinet', '红甲天', '可伸缩液晶电视机柜 dsg电视柜组合现代 时尚简约机顶盒架视听柜', '50.00-159.00', '江苏省徐州市', 14, '白色800*300、黑胡桃色800*300、白枫木色800*300、黑色800*300、白色800*400、黑胡桃色800*400、白枫木色800*400、黑色800*400、白色1000*400、黑胡桃色1000*400、白枫木色1000*400、黑色1000*400、白色1200*400、黑胡桃色1200*400、白枫木色1200*400、黑色1200*400、浅胡桃色800*300、浅胡桃色800*400、浅胡桃色1000*400、浅胡桃色1200*400、', 172, '无', '无', 'https://detail.tmall.com/item.htm?id=10165274856', '红甲天旗舰店', 'https://hongjiatian.tmall.com', 3933, '天猫7年店', '无', 1, 4.7, 4.7, 4.6, '无'),
	('tb10187076912', 16, 'GroundFloor:Furniture>HomeFurniture>LeatherBed', '泊宁馨品', '真皮床双人床1.8米简约时尚软床田园床1米8皮艺床榻榻米婚床N42', '2660.00 - 2720.00', '广东省佛山市', 1, '真皮、', 1, '0.00%', '-4.25%', 'http://item.taobao.com/item.html?id=10187076912', '百合家具店', 'https://baihejiajudian.taobao.com', 47, '无', '3个蓝钻', 0, 4.9, 4.9, 4.9, '无'),
	('tb10341993306', 1, 'GroundFloor:Furniture>HomeFurniture>TeaTable', '5i57.CN/吾爱吾妻', '高档 布艺绣花桌旗 时尚简约现代 欧式田园茶几桌布-电视柜盖布', '11.00', '北京北京市', 1461, '6732#浅粉（首图款）、6850#、729新#（推荐款）、1011新款#绣花、511#暗红', 9866, '5.10%', '+0.76%', 'http://item.taobao.com/item.html?id=10341993306', '吾爱吾妻品牌家居 金牌卖家', 'https://5i57.taobao.com', 48846, '无', '1个黄冠', 0, 4.8, 4.8, 4.8, '无'),
	('tb10345192027', 15, 'GroundFloor:Furniture>HomeFurniture>Sofa', '居色', '布艺沙发北欧宜家亚麻棉麻布艺沙发组合可拆洗简约现代日式贵妃', '3780.00', '广东省佛山市', 8, '组合1（4米长，1米8宽）、组合2（3米3长，1米8宽）、三位+贵妃+双护手双位、双位2米1、', 62, '12.35%', '+8.10%', 'http://item.taobao.com/item.html?id=10345192027', '居色家具', 'https://jusejiaju.taobao.com', 6362, '无', '4个蓝钻', 0, 4.9, 4.9, 4.8, '无'),
	('tb10368549832', 16, 'GroundFloor:Furniture>HomeFurniture>Table', '鸿兴', '特价 实木餐桌 宜家桌子 折叠餐桌 小户型伸缩 餐桌组合橡木桌椅', '148.00 - 1270.00', '广东省广州市', 21, '长方形餐桌115#春茶色（无椅、长方形餐桌115#棕色（无椅、椭圆餐台112#春茶色（无椅、椭圆餐桌112#棕色（无椅、餐椅012#春茶色、餐椅012#棕色、椭圆形餐桌春茶色（一桌四椅）、椭圆形餐桌棕色（一桌四椅）、长方形餐桌春茶色（一桌四椅）、长方形餐桌棕色（一桌四椅）、', 59, '0.00%', '-4.25%', 'http://item.taobao.com/item.html?id=10368549832', '顺联家私', 'https://shunlian.taobao.com', 15893, '无', '4个蓝钻', 0, 4.9, 4.8, 4.9, '无'),
	('tb10415641616', 1, 'Furniture', '无', '测试内连接', '', '', 0, '', 0, '', '', '', '', '', 0, '', '', 0, 0, 0, 0, ''),
	('tb10469849352', 15, 'GroundFloor:Furniture>HomeFurniture>Desk', '无', '包邮现货简约现代小户型宜家折叠伸缩餐桌餐桌书桌特价板式餐台', '499.00 - 580.00', '北京北京市', 0, '小 珍珠白现生产、小 白枫色【现生产】、大 红樱桃色【现货】、大 珍珠白现生产、小 黑橡色【现货】、大 胡桃木色【现货】、小 浅胡桃木色现生产、小 红樱桃色【现货】、大 浮雕白色【现货】、大 浅胡桃木色现生产、小 胡桃木色 现生产、小 浮雕白色【现货】、小 波士顿黑胡桃木色现生产、大 白枫色【现货】、大 波士顿黑胡桃木色现生产、大 黑橡色【现货】、', 3, '16.67%', '+12.42%', 'http://item.taobao.com/item.html?id=10469849352', '简约时尚家居家具', 'https://knsyg.taobao.com', 53651, '无', '2个蓝冠', 0, 4.6, 4.6, 4.5, '无'),
	('tb10470693068', 16, 'GroundFloor:Furniture>HomeFurniture>LeatherBed', '泊宁馨品', '时尚榻榻米真皮床皮艺双人床1.8米田园床软床软体床婚床头层牛皮', '2793.60', '广东省佛山市', 0, '头层牛皮、', 0, '0.00%', '-4.25%', 'http://item.taobao.com/item.html?id=10470693068', '百合家具店', 'https://baihejiajudian.taobao.com', 4, '无', '3个蓝钻', 0, 4.9, 4.9, 4.9, '无'),
	('tb10569001077', 15, 'GroundFloor:Furniture>HomeFurniture>ShoeBox', '皇海家具', '定制 特价鞋柜实木简约现代烤漆换鞋凳门厅玄关柜简易多层超薄鞋架包邮', '319.00', '河北省', 4, '两抽屉印花、一抽屉印花、一抽屉无花、两抽屉无花、', 90, '7.04%', '+2.80%', 'http://item.taobao.com/item.html?id=10569001077', '河北金河家具基地', 'https://jinhejiaju.taobao.com', 3648, '无', '2个蓝冠', 0, 4.8, 4.8, 4.7, '无'),
	('tb10574008722', 15, 'GroundFloor:Furniture>HomeFurniture>ComputerDesk', '三升', '烤漆彩绘电脑桌台式家用台式机特价简易简约书桌办公桌写字台桌子', '98.00', '江苏省其他/other', 27, '水墨荷竹（1.2米）、格林童话（1米长）、静心（1米长）、爱心树（1.2米长）、玫瑰之约（1.2米长）、荷塘美景（1米长）、爱心树（1米长）、白紫荆花（1.2米长）、黑紫荆花（1米长）、白紫荆花（1米长）、紫水晶（1米长）、幽香清远（1米长）、亚光白紫荆花（90厘米长）、黑紫荆花（1.2米长）、夏日沙滩（1米长）、玫瑰之约（1米长）、蒲公英（1米长）、动感蘑菇（1.2米长）、山水如画（1.2米长）、青花瓷（1.2米长）、品味人生（1.2米长）、', 2446, '4.65%', '+0.40%', 'http://item.taobao.com/item.html?id=10574008722', '三升电脑桌批发', 'https://sanson.taobao.com', 34281, '无', '4个蓝冠', 0, 4.6, 4.7, 4.6, '无'),
	('tb10584007989', 16, 'GroundFloor:Furniture>HomeFurniture>ShoeBox', '那帕', '威廉时期康弗尔小镇家具 美式乡村实木家具 全实木三门鞋柜K801B', '3126.00', '广东省东莞市', 0, '金丝楸木色、胡桃色、', 7, '4.35%', '+0.12%', 'http://item.taobao.com/item.html?id=10584007989', '那帕家居', 'https://k1860.taobao.com', 191, '无', '1个蓝冠', 0, 4.9, 4.9, 4.9, '无'),
	('tb10614456461', 3, 'HomeFurniture', '无', '家居', '', '', 0, '', 0, '', '', '', '', '', 0, '', '', 0, 0, 0, 0, '');
/*!40000 ALTER TABLE `commodity_list` ENABLE KEYS */;

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

-- 正在导出表  ssm.gag 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `gag` DISABLE KEYS */;
INSERT INTO `gag` (`id`, `create_time`, `gag_time`, `user_id`) VALUES
	(1, '2017-05-05 16:08:47', '2017-05-05', 5);
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
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.score 的数据：~60 rows (大约)
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` (`id`, `change_type`, `create_time`, `score`, `user_id`) VALUES
	(1, 'value1', '2017-05-05 20:59:46', 1, 1),
	(2, 'value2', '2017-03-31 17:03:30', 2, NULL),
	(3, 'value3', '2017-03-31 17:05:06', 3, NULL),
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
	(17, '充钱钱', '2017-04-01 16:32:09', 10, 1),
	(18, 'bb', '2017-04-02 13:39:29', 20, 1),
	(19, 'bb', '2017-04-02 13:41:15', 20, 1),
	(20, 'bb', '2017-04-02 13:41:28', 20, 1),
	(21, 'bb', '2017-04-03 23:22:19', 20, 1),
	(22, 'bb', '2017-04-03 23:23:11', 20, 1),
	(23, 'bb', '2017-04-03 23:24:07', 20, 2),
	(24, 'bb', '2017-04-03 23:26:04', 20, 2),
	(25, 'bb', '2017-04-03 23:28:02', 20, 2),
	(26, 'bb', '2017-04-03 23:30:09', 20, 2),
	(27, 'bb', '2017-04-03 23:31:23', 20, 2),
	(28, 'bb', '2017-04-03 23:32:45', 20, 2),
	(29, 'bb', '2017-04-03 23:34:38', 20, 3),
	(31, 'bb', '2017-04-03 23:42:00', 20, 3),
	(35, 'bb', '2017-04-03 23:56:32', 20, 3),
	(38, 'bb', '2017-04-05 15:26:36', 20, 2),
	(39, 'bb', '2017-04-05 15:45:05', 20, 3),
	(41, 'bb', '2017-04-05 15:57:53', 20, 3),
	(44, 'bb', '2017-04-05 16:22:22', 20, 5),
	(46, 'bb', '2017-04-05 19:53:17', 20, 3),
	(49, 'bb', '2017-04-05 20:04:26', 20, 3),
	(50, 'bb', '2017-04-05 20:06:38', 20, 3),
	(53, 'bb', '2017-04-05 20:32:37', 20, 3),
	(55, 'bb', '2017-04-05 20:41:33', 20, 3),
	(56, 'bb', '2017-04-05 20:46:05', 20, 3),
	(57, 'bb', '2017-04-05 20:48:31', 20, 3),
	(58, 'bb', '2017-04-05 20:51:45', 20, 6),
	(59, 'bb', '2017-04-05 20:54:09', 20, 6),
	(60, 'bb', '2017-04-05 20:54:56', 20, 6),
	(61, '充钱钱', '2017-04-06 19:33:10', 10, 1),
	(62, 'bb', '2017-04-08 17:17:53', 20, 3),
	(63, 'bb', '2017-04-08 17:18:18', 20, 5),
	(64, 'bb', '2017-04-08 17:19:49', 20, 5),
	(65, '吃饭', '2017-05-04 21:37:40', 10, 1),
	(66, '吃饭', '2017-05-04 21:54:48', 10, 1),
	(67, '喝茶', '2017-05-04 21:54:48', 10, 1),
	(68, '吃饭', '2017-05-05 10:23:01', 10, 1),
	(69, '喝茶', '2017-05-05 10:23:01', 10, 1),
	(70, '喝茶', '2017-05-05 10:23:01', 10, 1),
	(71, '吃饭', '2017-05-05 10:39:58', 10, 1),
	(72, '吃饭', '2017-05-05 19:05:38', 10, 1),
	(73, '喝茶', '2017-05-05 19:05:38', 10, 1),
	(74, '喝茶', '2017-05-05 19:05:38', 10, 1),
	(75, '喝茶', '2017-05-10 22:27:30', 10, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  ssm.user 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `avatar`, `pay_money`, `country`, `create_time`, `score`, `latitude`, `longitude`, `province`, `sex`, `password`) VALUES
	(1, '89564', NULL, 0, NULL, '2017-04-01 16:25:36', 80, NULL, NULL, '广东省', NULL, NULL),
	(2, '123456', NULL, 0, 'China', '2017-04-02 11:37:09', 140, NULL, NULL, '北京市', NULL, NULL),
	(3, '489486', NULL, 0, '中国', '2017-04-02 11:38:56', 200, NULL, NULL, '北京市', NULL, NULL),
	(5, '18945198', NULL, 0, '中国', '2017-04-02 17:24:49', 140, NULL, NULL, '广东省', NULL, NULL),
	(6, '489156', NULL, 0, NULL, '2017-04-02 21:07:16', 120, NULL, NULL, '广东省', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
