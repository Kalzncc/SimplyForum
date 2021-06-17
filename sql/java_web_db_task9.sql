/*
 Navicat MySQL Data Transfer

 Source Server         : Mysql_server
 Source Server Type    : MySQL
 Source Server Version : 50649
 Source Host           : 154.8.152.140:3306
 Source Schema         : java_web_db_task9

 Target Server Type    : MySQL
 Target Server Version : 50649
 File Encoding         : 65001

 Date: 19/12/2020 15:55:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lottery
-- ----------------------------
DROP TABLE IF EXISTS `lottery`;
CREATE TABLE `lottery`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `good_num` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isoverdue` tinyint(4) NULL DEFAULT NULL,
  `jackpot` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_lottery
-- ----------------------------
DROP TABLE IF EXISTS `user_lottery`;
CREATE TABLE `user_lottery`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lottery_id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `good_num` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `lottery_id`(`lottery_id`) USING BTREE,
  CONSTRAINT `user_lottery_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_lottery_ibfk_2` FOREIGN KEY (`lottery_id`) REFERENCES `lottery` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Triggers structure for table user_lottery
-- ----------------------------
DROP TRIGGER IF EXISTS `buy_lottery`;
delimiter ;;
CREATE TRIGGER `buy_lottery` BEFORE INSERT ON `user_lottery` FOR EACH ROW begin
	declare isover tinyint;
	declare msg varchar(128);
	select isoverdue into isover from lottery where id = new.lottery_id;
	if isover <> 0 then
		set msg = "the lottery has been overdued.";
		SIGNAL SQLSTATE 'HY000' SET MESSAGE_TEXT = msg;
	else 
		update lottery set jackpot=jackpot+new.num where id = new.lottery_id;
	end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
