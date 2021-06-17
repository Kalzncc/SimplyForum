/*
 Navicat MySQL Data Transfer

 Source Server         : Mysql_server
 Source Server Type    : MySQL
 Source Server Version : 50649
 Source Host           : 154.8.152.140:3306
 Source Schema         : java_web_db_task8

 Target Server Type    : MySQL
 Target Server Version : 50649
 File Encoding         : 65001

 Date: 19/12/2020 17:27:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminname` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for badword
-- ----------------------------
DROP TABLE IF EXISTS `badword`;
CREATE TABLE `badword`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime(0) NULL DEFAULT NULL,
  `pulisher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `replynum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `messageid` int(11) NULL DEFAULT NULL,
  `pulisher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `messageid`(`messageid`) USING BTREE,
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`messageid`) REFERENCES `message` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Triggers structure for table reply
-- ----------------------------
DROP TRIGGER IF EXISTS `add_reply_num`;
delimiter ;;
CREATE TRIGGER `add_reply_num` AFTER INSERT ON `reply` FOR EACH ROW begin
	update message set replynum = replynum+1 where id =new.messageid;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table reply
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_reply_num`;
delimiter ;;
CREATE TRIGGER `delete_reply_num` AFTER DELETE ON `reply` FOR EACH ROW begin
    update message set replynum = replynum-1 where id = old.messageid;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
