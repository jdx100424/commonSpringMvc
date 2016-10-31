/*
db
*/
CREATE DATABASE IF NOT EXISTS jdxTestMaster DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE DATABASE IF NOT EXISTS jdxTestSlave DEFAULT CHARSET utf8 COLLATE utf8_general_ci;



/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50715
 Source Host           : 127.0.0.1
 Source Database       : jdxTest

 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 10/30/2016 19:08:59 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `echo`
-- ----------------------------
use jdxTestMaster;
DROP TABLE IF EXISTS `echo`;
CREATE TABLE `echo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NULL default '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `echo`
-- ----------------------------
BEGIN;
INSERT INTO `echo` VALUES ('1','echo1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

use jdxTestSlave;
DROP TABLE IF EXISTS `echo`;
CREATE TABLE `echo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NULL default '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `echo`
-- ----------------------------
BEGIN;
INSERT INTO `echo` VALUES ('2','echo2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
