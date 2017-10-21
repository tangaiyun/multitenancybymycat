DROP DATABASE `jg1`;
CREATE DATABASE `jg1` default character set utf8 COLLATE utf8_bin;
USE `jg1`;
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户顾客ID',
  `itemName` varchar(50) NOT NULL DEFAULT '' COMMENT '品项标题',
  `itemPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '单价',
  `itemNum` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数量',
  `createDatetime` datetime NOT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;