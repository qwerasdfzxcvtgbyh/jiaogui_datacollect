/*
Navicat MySQL Data Transfer

Source Server         : 192.168.15.200
Source Server Version : 50640
Source Host           : 192.168.15.200:3307
Source Database       : bi_task

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-08-16 19:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for system_api_user
-- ----------------------------
DROP TABLE IF EXISTS `system_api_user`;
CREATE TABLE `system_api_user` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(50) NOT NULL,
  `api_key` varchar(50) NOT NULL,
  `api_secret` varchar(100) NOT NULL,
  `api_create_time` datetime NOT NULL,
  `api_last_login` datetime NOT NULL,
  `api_last_login_ip` varchar(50) NOT NULL,
  `prefix` varchar(255) DEFAULT '' COMMENT '前缀',
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of system_api_user
-- ----------------------------
INSERT INTO `system_api_user` VALUES ('6', 'boshi', '5523165006', '6wwCK0WRfB', '2019-06-03 18:38:47', '2019-06-03 18:39:17', '127.0.0.1', '');
INSERT INTO `system_api_user` VALUES ('7', 'feinuojia', '3976877650', 'MYKqeMlQJn', '2019-06-03 18:38:47', '2019-06-03 18:39:17', '127.0.0.1', '');
INSERT INTO `system_api_user` VALUES ('8', 'zulijian', '5523165006', '8i19fSbKaD', '2019-06-03 18:38:47', '2019-06-03 18:39:17', '127.0.0.1', '');
INSERT INTO `system_api_user` VALUES ('9', 'bershka', '9994601371', 'Hd52kqHMhK', '2019-06-03 18:38:47', '2019-06-03 18:39:17', '127.0.0.1', '');

-- ----------------------------
-- Table structure for t_brand_info
-- ----------------------------
DROP TABLE IF EXISTS `t_brand_info`;
CREATE TABLE `t_brand_info` (
  `brand_id` int(6) NOT NULL AUTO_INCREMENT,
  `out_id` int(6) NOT NULL,
  `brand_code` varchar(30) NOT NULL,
  `brand_name` varchar(30) NOT NULL,
  `status` int(4) DEFAULT '1' COMMENT '任务状态[common(1):正常, forbid(2):禁用, deleted(3):删除]',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`brand_id`),
  UNIQUE KEY `brand_code` (`brand_code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='品牌数据';

-- ----------------------------
-- Records of t_brand_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_category_message
-- ----------------------------
DROP TABLE IF EXISTS `t_category_message`;
CREATE TABLE `t_category_message` (
  `id` int(20) NOT NULL,
  `parent_id` int(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_at` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `updated_at` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category_message
-- ----------------------------
INSERT INTO `t_category_message` VALUES ('1', '42190', 'Tops', 'kids_newborn_tops', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('2', '28149', 'Shorts', 'men_shorts', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('3', '18999', 'Short sleeve', 'men_tshirtstanks_shortsleeve', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('4', '11502', 'Vests', 'ladies_tops_vests', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('5', '0', 'Duvet cover sets', 'home_bedlinen_duvetscoversets', '2019-05-30 10:25:20.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('6', '23149', 'Hand', 'home_towels_hand', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('7', '23149', 'Bath', 'home_towels_bath', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('8', '5399', 'Tops & T-shirts', 'kids_babygirl_topstshirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('9', '28149', 'Swimwear', 'men_swimweear', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('10', '0', 'Tops', 'ladies_basics_tops', '2019-05-30 10:25:33.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('11', '2003', 'Bikini bottoms', 'ladies_swimwear_bikinibottoms', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('12', '26399', 'Bags & Storage', 'home_storage_bagsstorage', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('13', '2901', 'Belts', 'ladies_accessories_belts', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('14', '2052', 'Accessories & Storage', 'home_kidsroom_accessoriesstorage', '2019-05-30 10:25:31.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('15', '23149', 'Guest', 'home_towels_guests', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('16', '20850', 'Short skirts', 'ladies_skirts_shortskirts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('17', '18999', 'Long sleeve', 'men_tshirtstanks_longsleeve', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('18', '507', 'Dressed', 'men_shoes_dressed', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('19', '0', 'Ballerinas & Flats', 'ladies_shoes_ballerinas_flats', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('20', '34099', 'Maxi Dresses', 'ladies_dresses_maxidresses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('21', '0', 'Sneakers', 'ladies_shoes_sneakers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('22', '13549', 'Balconette bra', 'ladies_lingerie_bras_balconette', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('23', '20599', 'Tops & T-shirts', 'kids_girl14y_topstshirts', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('24', '10699', 'Socks', 'ladies_sockstights_socks', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('25', '11502', 'Short sleeve', 'ladies_tops_shortsleeve', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('26', '2102', 'Joggers', 'ladies_trousers_joggers', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('27', '20599', 'Underwear', 'kids_girl14y_underwear', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('28', '11100', 'Curtains', 'home_curtains', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('29', '19099', 'Cushion cover', 'home_cushions_cushioncover', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('30', '59', 'Skinny regular', 'ladies_jeans_skinny_skinnyregular', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('31', '23149', 'Showers', 'home_towels_showers', '2019-05-30 10:26:07.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('32', '11502', 'Long sleeve', 'ladies_tops_longsleeve', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('33', '0', 'Pillowcases', 'home_bedlinen_pillowcases', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('51', '11100', 'Tea towel', 'home_teatowel', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('52', '38195', 'Shirts', 'ladies_shirtsblouses_shirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('53', '26750', 'Jackets', 'men_sport_jackets', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('54', '2152', 'Dresses', 'kids_girl8y_dressesskirts_dresses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('55', '38195', 'Blouses', 'ladies_shirtsblouses_blouses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('56', '0', 'Socks', 'kids_boy8y_socks', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('57', '0', 'Jumpers', 'ladies_cardigansjumpers_jumpers', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('58', '0', 'Shorts', 'kids_boy8y_shorts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('59', '6949', 'Skinny', 'ladies_jeans_skinny', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('60', '2152', 'Skirts', 'kids_girl8y_dressesskirts_skirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('61', '8099', 'Jackets', 'men_jacketscoats_jackets', '2019-05-30 10:26:48.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('62', '21949', 'Thong', 'ladies_lingerie_briefsknickers_thong', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('63', '5', 'Single', 'home_bedlinen_duvetscoversets_single', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('64', '42190', 'Accessories', 'kids_newborn_accessories', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('65', '0', 'Bottoms', 'ladies_sport_bottoms', '2019-05-30 10:26:30.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('66', '8099', 'Bomber Jackets', 'men_jacketscoats_bomberjackets', '2019-05-30 10:26:44.000000', '2019-07-12 02:14:37.000000');
INSERT INTO `t_category_message` VALUES ('67', '1001', 'Joggers', 'men_trousers_joggers', '2019-05-30 10:26:48.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('68', '22099', 'Jumpers', 'men_cardigansjumpers_jumpers', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('69', '20599', 'Socks & Tights', 'kids_girl14y_socktights', '2019-05-30 10:26:43.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('70', '19099', 'Seat cushions', 'home_cushions_seatcushions', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('71', '26750', 'Bottoms', 'men_sport_bottoms', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('72', '10249', 'Kitchen ware', 'home_kitchen_ware', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('73', '20849', 'Slim', 'men_jeans_slim', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('74', '0', 'Outdoor clothing', 'kids_boy8y_outdoor', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('75', '4151', 'Accessories', 'kids_boys14y_accessories', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('76', '171', 'Leggings', 'kids_girl8y_trousersleggings_leggings', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('77', '0', 'Shirts', 'kids_babyboy_shirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('78', '0', 'Tops & T-shirts', 'kids_babyboy_topstshirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('79', '0', 'Accessories', 'kids_boy8y_accessories', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('80', '14099', 'Tops & T-shirts', 'kids_girl8y_topstshirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('81', '4151', 'Jumpers & Cardigans', 'kids_boys14y_jumperscardigans', '2019-05-30 10:26:17.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('82', '5399', 'Bodies', 'kids_babygirl_bodies', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('83', '0', 'Ballerinas & Flats', 'kids_girl8y_shoes_ballerians_flats', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('101', '0', 'Outdoor clothing', 'kids_babyboy_outdoor', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('102', '4151', 'Trousers', 'kids_boys14y_trousers', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('103', '74', 'Bomber Jackets', 'kids_boy8y_outdoor_bomberjackets', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('104', '3053', 'Jackets', 'ladies_jacketscoats_jackets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('105', '0', 'Shoes', 'kids_babyboy_shoes', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('106', '13549', 'Push-up bra', 'ladies_lingerie_bras_pushup', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('107', '11201', 'Slim', 'kids_girl8y_jeans_slim', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('108', '39494', 'Boots', 'kids_boy8y_shoes_boots', '2019-05-30 10:26:24.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('109', '169', 'Bomber Jackets', 'kids_girl8y_outdoor_bomberjackets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('110', '0', 'Function', 'kids_boy8y_function', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('111', '28149', 'Hoodies & Sweatshirts', 'men_hoodiessweatshirts', '2019-05-30 10:26:06.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('112', '2501', 'Earrings', 'ladies_accessories_jewellery_earrings', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('113', '0', 'Tops & T-shirts', 'kids_boy8y_topstshirts', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('114', '215', 'Vest', 'ladies_sport_tops_vest', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('115', '803', 'Pull On', 'kids_boy8y_trousers_pullon', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('116', '21949', 'Multipack', 'ladies_lingerie_briefsknickers_multipack', '2019-05-30 10:26:18.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('117', '2102', 'Slim', 'ladies_trousers_slim', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('118', '0', 'Jumpers & Cardigans', 'kids_boy8y_jumperscardigans', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('119', '0', 'Sport bras', 'ladies_sport_sportbras', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('120', '22099', 'Cardigans', 'men_cardigansjumpers_cardigans', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('121', '0', 'Yoga', 'ladies_sportswear_yoga', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('122', '4201', 'Dresses', 'kids_girl14y_dressesskirts_dresses', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('123', '18999', 'Polo', 'MEN_TSHIRTSTANKS_POLO', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('124', '5399', 'Trousers & Leggings', 'kids_babygirl_trousersleggings', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('125', '4151', 'Underwear', 'kids_boys14y_underwear', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('126', '0', 'Sportswear', 'kids_boy8y_sportswear', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('127', '5399', 'Sets and all-in-ones', 'kids_babygirl_sets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('128', '59', 'Skinny low', 'ladies_jeans_skinny_skinnylow', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('129', '265', 'Bomber Jackets', 'kids_girl14y_outdoor_bomberjackets', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('130', '14099', 'Swimwear', 'kids_girl8y_swimwear', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('151', '20599', 'Jumpers & Cardigans', 'kids_girl14y_jumperscardigans', '2019-05-30 10:26:32.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('152', '11201', 'Skinny', 'kids_girl8y_jeans_skinny', '2019-05-30 10:26:39.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('153', '42190', 'Bottoms', 'kids_newborn_bottoms', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('154', '14099', 'Underwear', 'kids_girl8y_underwear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('155', '0', 'Nightwear', 'kids_boy8y_nightwear', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('156', '35796', 'Loose', 'kids_boy8y_jeans_loose', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('157', '171', 'Pull On', 'kids_girl8y_trousersleggings_pullon', '2019-05-30 10:26:40.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('158', '14099', 'Accessories', 'kids_girl8y_accessories', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('159', '39494', 'Sneakers', 'kids_boy8y_shoes_Sneakers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('160', '18999', 'Best Basics', 'men_tshirtstanks_bestbasics', '2019-05-30 10:25:19.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('161', '39992', 'Blankets', 'home_blankets_blankets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('162', '20849', 'Skinny', 'men_jeans_skinny', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('163', '14099', 'Dancewear', 'kids_girl8y_dancewear', '2019-05-30 10:26:26.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('164', '0', 'Shirts', 'kids_boy8y_shirts', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('165', '4301', 'Chinos skinny', 'men_trousers_chinos_skinny_all', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('166', '14099', 'Jumpers & Cardigans', 'kids_girl8y_jumperscardigans', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('167', '5399', 'Shoes', 'kids_babygirl_shoes', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('168', '0', 'Sneakers', 'kids_girl8y_shoes_sneakers', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('169', '14099', 'Outdoor clothing', 'kids_girl8y_outdoor', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('170', '0', 'Sheets', 'home_bedlinen_sheets', '2019-05-30 10:26:10.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('171', '14099', 'Trousers & Leggings', 'kids_girl8y_trousersleggings', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('172', '607', 'Blazers', 'men_blazerssuits_blazers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('173', '11100', 'Bathmats', 'home_bathmats', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('174', '14099', 'Socks & Tights', 'kids_girl8y_sockstights', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('175', '2901', 'Hats/scarves/gloves', 'ladies_accessories_hatscarvesgloves', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('201', '20599', 'Trousers & Leggings', 'kids_girl14y_trousersleggings', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('202', '20850', 'Midi skirts', 'ladies_skirts_midiskirts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('203', '0', 'Regular fit', 'men_shirt_dressed_regularfit', '2019-05-30 10:26:27.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('204', '20599', 'Sportswear', 'kids_girl14y_sportswear', '2019-05-30 10:26:27.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('205', '26749', 'Denim', 'men_shirts_denim', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('206', '26750', 'Tops', 'men_sport_tops', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('207', '4151', 'Shirts', 'kids_boys14y_shirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('208', '0', 'Bed spreads', 'home_bedlinen_bedspreads', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('209', '26749', 'Casual', 'men_shirts_casual', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('210', '0', 'Pumps & High heels', 'ladies_shoes_pumps_highheels', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('211', '2901', 'Other', 'ladies_accessories_other', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('212', '0', 'Swimwear', 'kids_boy8y_swimwear', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('213', '34099', 'Short dresses', 'ladies_dresses_shortdresses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('214', '28149', 'Accessories', 'men_accessories', '2019-05-30 10:25:23.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('215', '0', 'Tops', 'ladies_sport_tops', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('216', '4151', 'Tops & T-shirts', 'kids_boys14y_topstshirts', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('217', '42190', 'Nightwear', 'kids_newborn_nightwear', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('218', '0', 'Blazers & Waistcoats', 'kids_boy8y_balszerswaistcoats', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('219', '14099', 'Sportswear', 'kids_girl8y_sportswear', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('220', '11100', 'Porcelain', 'home_porcelain', '2019-05-30 10:26:26.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('221', '0', 'Sandals & Flip Flops', 'kids_girl8y_shoes_sandals_flipflops', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('222', '0', 'Hoodies & Sweatshirts', 'ladies_cardigansjumpers_hoodiessweatshirts', '2019-05-30 10:25:56.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('251', '35796', 'Pull On', 'kids_boy8y_jeans_pullon', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('252', '0', 'Boots', 'ladies_shoes_boots', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('253', '28149', 'Underwear', 'men_underwear', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('254', '20599', 'Shorts', 'kids_girl14y_shorts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('255', '11100', 'Napkins', 'home_napkins', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('256', '13549', 'Padded bra', 'ladies_lingerie_bras_padded', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('257', '4151', 'Sportswear', 'kids_boys14y_sportswear', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('258', '19599', 'Jumpers', 'ladies_knitwear_jumpers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('259', '42190', 'Sets and all-in-ones', 'kids_newborn_sets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('260', '2102', 'Chinos & Slacks', 'ladies_trousers_chinosslacks', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('261', '11100', 'Candles and candleholders', 'home_candlescandleholders', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('262', '607', 'Trousers', 'men_blazerssuits_trousers', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('263', '65', 'Shorts', 'ladies_sport_bottoms_shorts', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('264', '215', 'Short sleeve', 'ladies_sport_tops_shortsleeve', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('265', '20599', 'Outdoor clothing', 'kids_girl14y_outdoor', '2019-05-30 10:26:43.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('266', '5399', 'Baby fleece', 'Kids_babygirl_fleece', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('301', '0', 'Turtlenecks', 'ladies_cardigansjumpers_turtlenecks', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('302', '14099', 'Shirts & Blouses', 'kids_girl8y_shirtsblouses', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('303', '20599', 'Function', 'kids_girl14y_function', '2019-05-30 10:26:10.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('304', '6949', 'Straight', 'ladies_jeans_straight', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('305', '2501', 'Bracelets', 'ladies_accessories_jewellery_bracelets', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('306', '21949', 'Thongs', 'ladies_lingerie_briefsknickers_thongs', '2019-05-30 10:25:23.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('307', '0', 'Bed skirts', 'home_bedlinen_bedskirts', '2019-05-30 10:25:07.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('308', '20599', 'Jumpsuits', 'kids_girl14y_jumpsuits', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('309', '10799', 'Skinny', 'kids_girl14y_jeans_skinny', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('310', '0', 'Trousers', 'kids_babyboy_trousers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('311', '34099', 'Midi Dresses', 'ladies_dresses_mididresses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('312', '2052', 'Towels', 'home_kidsroom_towels', '2019-05-30 10:25:26.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('313', '38445', 'Sneakers', 'kids_boys14y_shoes_sneakers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('314', '20599', 'Generous fit', 'kids_girl14y_generousfit', '2019-05-30 10:26:37.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('351', '507', 'Sneakers', 'men_shoes_sneakers', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('352', '11100', 'Glasswear', 'home_glasswear', '2019-05-30 10:26:36.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('353', '0', 'Cardigans', 'ladies_cardigansjumpers_cardigans', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('354', '42190', 'Sock and tights', 'kids_newborn_sockstights', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('355', '28149', 'Socks', 'men_socks', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('356', '5399', 'Socks & Tights', 'kids_babygirl_sockstights', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('357', '2003', 'Bikini tops', 'ladies_swimwear_bikinitops', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('358', '4151', 'Shorts', 'kids_boys14y_shorts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('359', '3801', 'Night Slips & robes', 'ladies_nightwear_nightslipsrobes', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('360', '11100', 'Carpets', 'home_carpets', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('361', '5399', 'Dresses & Skirts', 'kids_babygirl_dressesskirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('362', '21949', 'Hipster', 'ladies_lingerie_briefsknickers_hipster', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('363', '214', 'Belts and braces', 'men_accessories_beltsandbraces', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('364', '20599', 'Accessories', 'kids_girl14y_accessories', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('365', '26750', 'Sport accessories', 'men_sport_sportaccessories', '2019-05-30 10:26:20.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('366', '0', 'Premium Quality', 'ladies_shoes_premium_quality', '2019-05-30 10:24:54.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('367', '0', 'Slim fit', 'men_shirt_dressed_slimfit', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('368', '0', 'Jeans', 'kids_babyboy_jeans', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('401', '65', 'Tights & Trousers', 'ladies_sport_bottoms_trousers', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:38.000000');
INSERT INTO `t_category_message` VALUES ('402', '35796', 'Slim', 'kids_boy8y_jeans_slim', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('403', '214', 'Hats and gloves', 'men_accessories_hatsandgloves', '2019-05-30 10:26:43.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('404', '0', 'Ankle Boots', 'ladies_shoes_ankle_boots', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('405', '0', 'Bodies', 'kids_babyboy_bodies', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('406', '2901', 'Hair accessories', 'ladies_accessories_hairaccessories', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('407', '507', 'Boots', 'men_shoes_boots', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('408', '0', 'Premium Quality', 'men_shoes_premium_quality', '2019-05-30 10:26:24.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('409', '3801', 'Accessories', 'ladies_nightwear_accessories', '2019-05-30 10:26:29.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('410', '0', 'Jumper&Cardigans', 'kids_babyexclusive_jumperscardigans', '2019-05-30 10:25:05.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('411', '7700', 'Tops', 'ladies_plus_tops', '2019-05-30 10:26:40.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('412', '11502', 'Cropped tops', 'ladies_tops_croppedtops', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('451', '5', 'Double', 'home_bedlinen_duvetscoversets_double', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('452', '803', 'Trousers', 'kids_boy8y_trousers_trousers', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('453', '0', 'Accessories', 'kids_babyboy_accessories', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('454', '0', 'baby/kids', 'home_bedlinen_babykids', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('455', '605', 'Bomber Jackets', 'kids_boys14y_outdoor_bomberjackets', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('456', '0', 'Nightwear', 'kids_babyboy_nightwear', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('501', '4201', 'Skirts', 'kids_girl14y_dressesskirts_skirts', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('502', '5399', 'Accessories', 'kids_babygirl_accessories', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('503', '0', 'Underwear', 'kids_boy8y_underwear', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('504', '0', 'Tops', 'ladies_divided_tops', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('505', '4151', 'Socks', 'kids_boys14y_socks', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('506', '11100', 'Decorations', 'home_decorations', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('507', '28149', 'Shoes', 'men_shoes', '2019-05-30 10:25:29.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('508', '2901', 'Sunglasses', 'ladies_accessories_sunglasses', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('509', '0', 'Skinny', 'kids_boys14y_jeans_skinny', '2019-05-30 10:26:07.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('551', '2102', 'Leggings', 'ladies_trousers_leggings', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('552', '2901', 'Bags', 'ladies_accessories_bags', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('553', '3053', 'Bomber Jackets', 'ladies_jacketscoats_bomber', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('554', '12949', 'Kimono', 'ladies_blazerswaistcoats_kimono', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('555', '20599', 'Shirts & Blouses', 'kids_girl14y_shirtsblouses', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('556', '14099', 'Jumpsuits', 'kids_girl8y_jumpsuits', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('557', '11201', 'Pull On', 'kids_girl8y_jeans_pullon', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('558', '0', 'Baby Fleece', 'Kids_babyboy_fleece', '2019-05-30 10:25:02.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('601', '3053', 'Coats', 'ladies_jacketscoats_coats', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('602', '8099', 'Coats', 'men_jacketscoats_coats', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('603', '214', 'Jewellery', 'men_accessories_jewellery', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('604', '5399', 'Outdoor clothing', 'kids_babygirl_outdoor', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('605', '4151', 'Outdoor clothing', 'kids_boys14y_outdoor', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('606', '20849', 'Straight', 'men_jeans_straight', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('607', '28149', 'Blazers & Suits', 'men_blazerssuits', '2019-05-30 10:25:59.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('608', '171', 'Trousers', 'kids_girl8y_trousersleggings_trousers', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('609', '0', 'Ballerinas & Flats', 'kids_girl14y_shoes_ballerians_flats', '2019-05-30 10:26:36.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('651', '38995', 'Tops', 'ladies_maternity_tops', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('652', '13549', 'Soft bra', 'ladies_lingerie_bras_soft', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('653', '35796', 'Skinny', 'kids_boy8y_jeans_skinny', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('654', '12949', 'Blazers', 'ladies_blazerswaistcoats_blazers', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('655', '0', 'Cardigans & Jumpers', 'ladies_basics_cardigansjumpers', '2019-05-30 10:25:19.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('701', '0', 'Loose', 'kids_boys14y_jeans_loose', '2019-05-30 10:26:17.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('702', '6949', 'Slim', 'ladies_jeans_slim', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('703', '4151', 'Generous fit', 'kids_boys14y_generousfit', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('704', '2052', 'Cushions', 'home_kidsroom_cushions', '2019-05-30 10:25:40.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('751', '20849', 'Slim Tapered', 'men_jeans_slimtapered', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('753', '20849', 'Super Skinny', 'men_jeans_superskinny', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('754', '214', 'Scarves', 'men_accessories_scarves', '2019-05-30 10:26:36.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('755', '0', 'LADIES', 'ladies', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('756', '4151', 'Blazers & Waistcoats', 'kids_boys14y_blazerswaistcoats', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('757', '14099', 'Shorts', 'kids_girl8y_shorts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('758', '21949', 'Hipsters', 'ladies_lingerie_briefsknickers_hipsters', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('759', '214', 'Bags', 'men_accessories_bags', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('760', '42190', 'Bodies', 'kids_newborn_bodies', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('801', '2052', 'Others', 'home_kidsroom_others', '2019-05-30 10:25:54.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('802', '4701', 'Trousers skinny', 'men_trousers_trousers_skinny_all', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('803', '0', 'Trousers', 'kids_boy8y_trousers', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('804', '7700', 'Swimwear', 'ladies_plus_swimwear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:39.000000');
INSERT INTO `t_category_message` VALUES ('805', '2752', 'Pyjamas', 'men_underwearloungewear_pyjamas', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('851', '20599', 'Swimwear', 'kids_girl14y_swimwear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('852', '4701', 'Trousers slim', 'men_trousers_trousers_slim_all', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('853', '215', 'Long sleeve', 'ladies_sport_tops_longsleeve', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('854', '6949', 'Loose', 'ladies_jeans_loose', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('901', '11100', 'Table cloth', 'home_tablecloth', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('902', '0', 'Basic', 'ladies_divided_basic', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('903', '10', 'Long Sleeve', 'ladies_basics_tops_longsleeve', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('904', '74', 'Parkas', 'kids_boy8y_outdoor_parkasjackets', '2019-05-30 10:26:27.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('905', '214', 'Sunglasses', 'men_accessories_sunglasses', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('951', '605', 'Parkas', 'kids_boys14y_outdoor_parkasjackets', '2019-05-30 10:25:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('952', '0', 'Sandals & Espandrillos', 'men_shoes_sandals_espandrillos', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1001', '28149', 'Trousers', 'men_trousers', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1002', '0', 'Boots', 'kids_girl8y_shoes_boots', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1003', '35946', 'Other', 'kids_girl14y_shoes_other', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1004', '30350', 'Shape Lingerie', 'ladies_lingerie_shapelingerie', '2019-05-30 10:25:23.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1051', '14099', 'Nightwear', 'kids_girl8y_nightwear', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1052', '35946', 'Sneakers', 'kids_girl14y_shoes_sneakers', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1053', '14099', 'Function', 'kids_girl8y_function', '2019-05-30 10:26:29.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1054', '0', 'Tshirts & shirts', 'men_divided_tshirtandshirts', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1101', '2102', 'Flare', 'ladies_trousers_flare', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1102', '0', 'Sandals & Espandrillos', 'ladies_shoes_sandals_espandrillos', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1103', '11100', 'Aprons', 'home_aprons', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1151', '0', 'Dresses & skirts', 'ladies_basics_dressesskirts', '2019-05-30 10:25:14.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1152', '265', 'Parkas', 'kids_girl14y_outdoor_parkasjackets', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1201', '7700', 'Dresses', 'ladies_plus_dresses', '2019-05-30 10:26:44.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1202', '0', 'Tops', 'men_trend_tops', '2019-05-30 10:25:12.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1203', '11100', 'Christmas Decorations', 'home_christmasdecorations', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1204', '7700', 'Bottoms', 'ladies_plus_bottoms', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1205', '0', 'Jackets', 'ladies_sport_jackets', '2019-05-30 10:26:40.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1251', '10', 'Vests', 'ladies_basics_tops_vests', '2019-05-30 10:25:38.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1252', '3801', 'Pyjamas', 'ladies_nightwear_pyjamas', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1253', '4151', 'Function', 'kids_boys14y_function', '2019-05-30 10:25:25.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1301', '13549', 'Super push-up bra', 'ladies_lingerie_bras_superpushup', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1302', '0', 'Sport accessories', 'ladies_sport_sportaccessories', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1303', '35946', 'Boots', 'kids_girl14y_shoes_boots', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1351', '755', 'Shorts', 'ladies_shorts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1352', '26399', 'baby/kids', 'home_storage_babykids', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1353', '0', 'Slim', 'kids_boys14y_jeans_slim', '2019-05-30 10:26:17.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1354', '18999', 'Tanks', 'men_tshirtstanks_tanks', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1401', '4151', 'Nightwear', 'kids_boys14y_nightwear', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1451', '38995', 'Bottoms', 'ladies_maternity_bottoms', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1452', '11502', 'Bodys', 'ladies_tops_bodys', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1453', '8099', 'Parkas', 'men_jacketscoats_parkas', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1454', '0', 'Shorts', 'kids_babyboy_shorts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1501', '59', 'Skinny high', 'ladies_jeans_skinny_skinnyhigh', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1502', '6949', 'Shaping', 'ladies_jeans_shaping', '2019-05-30 10:25:22.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1503', '20599', 'Nightwear', 'kids_girl14y_nightwear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1504', '21949', 'Briefs', 'ladies_lingerie_briefsknickers_briefs', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1505', '2901', 'Mobile Accessories', 'ladies_accessories_mobileaccessories', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1506', '0', 'Swimwear', 'kids_babyboy_swimwear', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1551', '4701', 'Trousers regular', 'men_trousers_trousers_regular_all', '2019-05-30 10:26:22.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1552', '2501', 'Necklaces', 'ladies_accessories_jewellery_necklaces', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1553', '4301', 'Chinos slim', 'men_trousers_chinos_slim_all', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1554', '0', 'Sandals & Flip Flops', 'kids_girl14y_shoes_sandals_flipflops', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1555', '2003', 'Bikini', 'ladies_swimwear_bikinisets', '2019-05-30 10:25:45.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1601', '11100', 'Table runners', 'home_tablerunners', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1602', '14099', 'Cartoons & Comics', 'kids_girl8y_cartoonscomics', '2019-05-30 10:24:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1603', '507', 'Other', 'men_shoes_other', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1651', '0', 'Sets and all-in-ones', 'kids_babyboy_sets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1652', '7700', 'Jeans', 'ladies_plus_jeans', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1653', '0', 'Jackets & Blazers', 'ladies_divided_jacketsblazers', '2019-05-30 10:24:55.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1701', '38195', 'Tunics', 'ladies_shirtsblouses_tunics', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1702', '4151', 'Swimwear', 'kids_boys14y_swimwear', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1703', '755', 'Jumpsuits', 'ladies_jumpsuits', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1751', '5399', 'Swimwear', 'kids_babygirl_swimwear', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1801', '21549', 'Picnic blankets', 'home_outdoor_picnicblankets', '2019-05-30 10:25:26.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1802', '5399', 'Shorts', 'kids_babygirl_shorts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1851', '38995', 'Nursing', 'ladies_maternity_nursing', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1901', '0', 'Sandals & Flip Flops', 'kids_boy8y_shoes_sandals_flipflops', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('1902', '1351', 'Jeans shorts', 'ladies_shorts_jeans', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2001', '0', 'Other', 'kids_girl8y_shoes_other', '2019-05-30 10:26:39.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2002', '0', 'Pull On', 'kids_boys14y_jeans_pullon', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2003', '755', 'Swimwear', 'ladies_swimwear', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2051', '0', 'T-shirts', 'men_trend_tshirts', '2019-05-30 10:25:27.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2052', '11100', 'View all', 'home_kidsroom_all', '2019-05-30 10:26:03.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2053', '2003', 'Swimsuits', 'ladies_swimwear_swimsuits', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2101', '2', 'Denim', 'men_shorts_denim', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2102', '755', 'Trousers', 'ladies_trousers', '2019-05-30 10:26:10.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2151', '2102', 'Culottes', 'ladies_trousers_culottes', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2152', '14099', 'Dresses & Skirts', 'kids_girl8y_dressesskirts', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2153', '2', 'Jeans Shorts', 'men_shorts_jeansshorts', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2201', '7700', 'Jackets & Blazers', 'ladies_plus_jacketsblazers', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2251', '253', 'Pyjamas', 'men_underwear_pyjamas', '2019-05-30 10:25:04.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2351', '3053', 'Parkas', 'ladies_jacketscoats_parkas', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2401', '10', 'Short Sleeve', 'ladies_basics_tops_shortsleeve', '2019-05-30 10:25:29.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2451', '0', 'Tops', 'ladies_partywear_tops', '2019-05-30 10:25:13.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2452', '19099', 'baby/kids', 'home_cushions_babykids', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2453', '2052', 'Carpets', 'home_kidsroom_carpets', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2454', '0', 'Ties, bowties, handkerchiefs', 'men_accessories_ties_bowties_handkerchiefs', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2501', '2901', 'Jewellery', 'ladies_accessories_jewellery', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2502', '38445', 'Boots', 'kids_boys14y_shoes_boots', '2019-05-30 10:26:19.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2503', '38995', 'Jeans', 'ladies_maternity_jeans', '2019-05-30 10:26:43.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2551', '0', 'Dresses & Jumpsuits', 'ladies_partywear_dressesjumpsuits', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2552', '0', 'Hoodies', 'ladies_hoodiesswetshirts_hoodies', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2601', '39494', 'Other', 'kids_boy8y_shoes_other', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2602', '7700', 'Lingerie & Tights', 'ladies_plus_lingerietights', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2603', '0', 'Sweatshirts', 'ladies_hoodiesswetshirts_sweatshirts', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2651', '0', 'Accessories', 'ladies_divided_accessories', '2019-05-30 10:26:28.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2701', '21949', 'Bikini & Briefs', 'ladies_lingerie_briefsknickers_bikinibriefs', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2702', '19599', 'Tops', 'ladies_knitwear_tops', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2751', '0', 'Bottoms', 'ladies_trend_bottoms', '2019-05-30 10:25:22.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2752', '28149', 'Underwear & Loungewear', 'men_underwearloungewear', '2019-05-30 10:26:12.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2801', '7700', 'Tunics', 'ladies_plus_tunics', '2019-05-30 10:26:22.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2851', '20599', 'Blazers & Waistcoats', 'kids_girl14y_blazerswaistcoats', '2019-05-30 10:25:00.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2852', '11100', 'Shower curtains', 'home_showercurtains', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2853', '4301', 'Chinos regular', 'men_trousers_chinos_regular_all', '2019-05-30 10:26:44.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2901', '755', 'Accessories', 'ladies_accessories', '2019-05-30 10:25:20.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('2951', '5399', 'Nightwear', 'kids_babygirl_nightwear', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3051', '2501', 'Rings', 'ladies_accessories_jewellery_rings', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3052', '30350', 'Accessories', 'ladies_lingerie_accessories', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3053', '755', 'Jackets & Coats', 'ladies_jacketscoats', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3101', '0', 'Jackets & Blazers', 'men_divided_jacketsandblazers', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3201', '0', 'Blazers', 'ladies_modernclassic_blazers', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3301', '10699', 'Tight & Leggings', 'ladies_sockstights_tightleggings', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3451', '11100', 'Holiday room', 'home_holidayroom', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3452', '5399', 'Shirts & Blouses', 'kids_babygirl_shirtsblouses', '2019-05-30 10:26:48.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3701', '0', 'Cartoons & Comics', 'kids_babyboy_cartoonscomics', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3702', '0', 'Jumpers & Sweatshirts', 'men_divided_jumpersandsweatshirts', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3751', '6949', 'Flare', 'ladies_jeans_flare', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3801', '755', 'Nightwear', 'ladies_nightwear', '2019-05-30 10:25:20.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3851', '169', 'Parkas', 'kids_girl8y_outdoor_parkasjackets', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3852', '6949', 'Boot cut', 'ladies_jeans_bootcut', '2019-05-30 10:26:18.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('3901', '0', 'Socks & Tights', 'kids_babyboy_sockstights', '2019-05-30 10:25:48.000000', '2019-07-12 02:14:40.000000');
INSERT INTO `t_category_message` VALUES ('4101', '38995', 'Dresses', 'ladies_maternity_dresses', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4151', '30548', 'Boys Size 8y-14+', 'kids_boys14y', '2019-05-30 10:24:52.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4201', '20599', 'Dresses & Skirts', 'kids_girl14y_dressesskirts', '2019-05-30 10:25:48.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4301', '1001', 'Chinos', 'men_trousers_chinos', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4351', '0', 'Trousers & Leggings', 'ladies_basics_trousersleggings', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4401', '20850', 'Maxi skirts', 'ladies_skirts_longskirts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4402', '38995', 'Lingerie & Tights', 'ladies_maternity_lingerietights', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4451', '0', 'Dresses & Jumpsuits', 'ladies_divided_dressesjumpsuits', '2019-05-30 10:25:22.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4501', '7700', 'Basics', 'ladies_plus_basics', '2019-05-30 10:25:21.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4701', '1001', 'Trousers', 'men_trousers_trousers', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4851', '0', 'Fancy dress', 'kids_boy8y_fancydress', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4852', '4151', 'School uniforms', 'kids_boys14y_schooluniforms', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('4901', '26749', 'Dressed', 'men_shirts_dressed', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5001', '0', 'Best Basics', 'kids_babyboy_bestbasics', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5051', '0', 'Cartoon & Comics', 'kids_boy8y_cartoonscomics', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5151', '38995', 'Jackets & Blazers', 'ladies_maternity_jacketsblazers', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5249', '12949', 'Waistcoats', 'ladies_blazerswaistcoats_waistcoats', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5399', '30548', 'Baby Girl Size 4-24m', 'kids_babygirl', '2019-05-30 10:24:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5499', '14099', 'Blazers & Waistcoats', 'kids_girl8y_blazers', '2019-05-30 10:26:10.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5549', '11100', 'View all', 'home_bathroom_all', '2019-05-30 10:26:03.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5599', '2052', 'Duvet cover sets', 'home_kidsroom_duvetcoversets', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5749', '0', 'Multipack & Sets', 'Kids_boy8y_multipack_sets', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5849', '0', 'Pots & Vases', 'home_pots_vases', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('5949', '19599', 'Turtlenecks', 'ladies_knitwear_turtlenecks', '2019-05-30 10:26:30.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6049', '2052', 'Toys', 'home_kidsroom_toys', '2019-05-30 10:25:08.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6149', '5399', 'Jumpers and cardigans', 'kids_babygirl_jumperscardigans', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6150', '21949', 'Brazilian', 'ladies_lingerie_briefsknickers_brazilian', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6151', '755', 'Extended sizes', 'ladies_extendedsizes', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6349', '81', 'Hoodies & Sweatshirts', 'kids_boys14y_jumperscardigans_hoodiessweatshirts', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6399', '38445', 'Other', 'kids_boys14y_shoes_other', '2019-05-30 10:26:38.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6449', '2052', 'Blankets', 'home_kidsroom_blankets', '2019-05-30 10:25:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6699', '13549', 'Soft bra & Wireless bras', 'ladies_lingerie_bras_softwireless', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6849', '2052', 'Sheets', 'home_kidsroom_sheets', '2019-05-30 10:24:54.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6850', '0', 'Trousers', 'ladies_modernclassic_trousers', '2019-05-30 10:25:39.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6949', '755', 'Jeans', 'ladies_jeans', '2019-05-30 10:25:11.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('6950', '7700', 'Sport', 'ladies_plus_sport', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7049', '11100', 'Dressing gown', 'home_dressinggown', '2019-05-30 10:26:07.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7099', '0', 'Bottoms', 'ladies_divided_bottoms', '2019-05-30 10:25:23.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7149', '13549', 'Wire bra', 'ladies_lingerie_bras_wire', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7150', '0', 'Jackets and Blazers', 'ladies_logg_jacketsblazers', '2019-05-30 10:24:54.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7249', '0', 'Tops', 'ladies_modernclassic_tops', '2019-05-30 10:24:56.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7649', '10799', 'Loose', 'kids_girl14y_jeans_loose', '2019-05-30 10:25:11.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7699', '19099', 'Inner cushions', 'home_cushions_innercushions', '2019-05-30 10:26:26.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7700', '755', 'H&M+ Plus sizes', 'ladies_plus', '2019-05-30 10:24:54.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7749', '166', 'Hoodies & Sweatshirts', 'kids_girl8y_jumperscardigans_hoodiessweatshirts', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7750', '2102', 'Dressed', 'ladies_trousers_dressed', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7751', '0', 'Tops', 'Kids_babygirl_basics_tops', '2019-05-30 10:24:54.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7752', '171', 'Leggings & Slim', 'kids_girl8y_trousersleggings_leggingsslim', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7753', '2901', 'Premium Quality', 'ladies_accessories_premiumquality', '2019-05-30 10:25:56.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7754', '151', 'Hoodies & Sweatshirts', 'kids_girl14y_jumperscardigans_hoodiessweatshirts', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7849', '0', 'Tops', 'ladies_logg_tops', '2019-05-30 10:26:05.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7850', '30350', 'Shape & Functional Lingerie', 'ladies_lingerie_shapefunctional', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7851', '30350', 'Bodies & Corsets', 'ladies_lingerie_bodiescorsets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('7949', '10699', 'Shape', 'ladies_sockstights_shape', '2019-05-30 10:26:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8049', '19599', 'Cardigans', 'ladies_knitwear_cardigans', '2019-05-30 10:26:43.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8099', '28149', 'Jackets & Coats', 'men_jacketscoats', '2019-05-30 10:24:55.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8199', '13549', 'D-F cups extra support bras', 'ladies_lingerie_bras_dfcups', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8200', '10799', 'Slim', 'kids_girl14y_jeans_slim', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8249', '2052', 'Pilllow cases', 'home_kidsroom_pillowcases', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8349', '0', 'Shirts and Blouses', 'ladies_logg_shirtsblouses', '2019-05-30 10:24:55.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8399', '38995', 'Basics', 'ladies_maternity_basics', '2019-05-30 10:24:55.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8499', '0', 'Mutipack & Sets', 'Kids_girl8y_multipack_sets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8549', '10799', 'Straight', 'kids_girl14y_jeans_straight', '2019-05-30 10:24:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8550', '11100', 'View all', 'home_bedroom_all', '2019-05-30 10:25:11.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8599', '0', 'Bottoms', 'men_divided_bottoms', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8600', '3801', 'Slippers', 'ladies_nightwear_slippers', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8949', '0', 'Jumpers and cardigans', 'Kids_babyboy_jumperscardigans', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('8950', '0', 'Function', 'kids_babyboy_function', '2019-05-30 10:24:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9599', '28149', 'Underwear & Loungewear', 'men_underwear&loungewear', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9600', '0', 'Premium Quality', 'men_accessories_premium_quality', '2019-05-30 10:26:13.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9699', '19599', 'Ponchos', 'ladies_knitwear_ponchos', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9799', '20849', 'Loose', 'men_jeans_loose', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9800', '2003', 'Other', 'ladies_swimwear_other', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('9899', '23149', 'Washcloths', 'home_towels_washcloths', '2019-05-30 10:26:07.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10199', '1001', 'Casual', 'men_trousers_casual', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10249', '11100', 'View all', 'home_kitchen_all', '2019-05-30 10:24:56.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10599', '0', 'Straight', 'kids_boys14y_jeans_straight', '2019-05-30 10:26:30.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10649', '0', 'Dresses & Skirts', 'ladies_knitwear_dresses_skirts', '2019-05-30 10:24:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10699', '755', 'Socks & Tights', 'ladies_sockstights', '2019-05-30 10:26:07.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10749', '0', 'Dresses', 'ladies_modernclassic_dresses', '2019-05-30 10:25:08.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10799', '20599', 'Jeans', 'kids_girl14y_jeans', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10849', '5399', 'Multipacks and sets', 'Kids_babygirl_multipacks', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10949', '113', 'Short sleeves', 'kids_boy8y_topstshirts_shortsleeves', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10950', '23', 'Long sleeves', 'kids_girl14y_topstshirts_longsleeves', '2019-05-30 10:26:29.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10951', '23', 'Short sleeves', 'kids_girl14y_topstshirts_shortsleeves', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10952', '0', 'Multipack & Sets', 'Kids_boys14y_multipack_sets', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('10999', '2102', 'Loose', 'ladies_trousers_loose', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11000', '113', 'Long sleeves', 'kids_boy8y_topstshirts_longsleeves', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11001', '29548', 'Shoppers & Totes', 'ladies_bags_shopperstotes', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11002', '29548', 'Shoulder & Cross bags', 'ladies_bags_shouldercross', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11003', '2901', 'Watches', 'ladies_accessories_watches', '2019-05-30 10:26:28.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11049', '0', 'Hats', 'ladies_accessories_hatsscarvesgloves_hats', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11050', '1555', 'Bikini Tops', 'ladies_swimwear_bikinisets_tops', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11051', '1555', 'Bikini Bottoms', 'ladies_swimwear_bikinisets_bottoms', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11099', '0', 'Tops & T-shirts', 'kids_babyexclusive_topstshirts', '2019-05-30 10:25:15.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11100', '0', 'Home All', 'home_all', '2019-05-30 10:24:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11101', '216', 'Short sleeves', 'kids_boys14y_topstshirts_shortsleeves', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11149', '0', 'Outdoor clothing', 'kids_babyexclusive_outdoor', '2019-05-30 10:24:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11150', '166', 'Knitwear', 'kids_girl8y_jumperscardigans_knitwear', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11151', '0', 'Dresses', 'kids_babyexclusive_dresses', '2019-05-30 10:25:25.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11152', '151', 'Cardigans & Jackets', 'kids_girl14y_jumperscardigans_cardigansjackets', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11199', '0', 'Caps/Hats/Scarves', 'kids_boy8y_accessories_caps_hats_scarves', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11200', '13549', 'Multipack bras', 'ladies_lingerie_bras_multipack', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11201', '14099', 'Jeans', 'kids_girl8y_jeans', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11249', '158', 'Caps/Hats/Scarves', 'kids_girl8y_accessories_capshatsscarves', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11250', '803', 'Loose', 'kids_boy8y_trousers_loose', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11299', '3053', 'Biker Jackets', 'ladies_jacketscoats_biker', '2019-05-30 10:26:40.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11300', '151', 'Knitwear', 'kids_girl14y_jumperscardigans_knitwear', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11301', '29548', 'Pouches & Wallets', 'ladies_bags_poucheswallets', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11302', '0', 'Hairaccessories & Jewelry', 'kids_girl14y_accessories_hairaccessories_jewelry', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11303', '166', 'Cardigans & Jackets', 'kids_girl8y_jumperscardigans_cardigansjackets', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11304', '0', 'Set and all-in-ones', 'kids_babyexclusive_sets', '2019-05-30 10:25:25.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11305', '0', 'Tops', 'Kids_boys14y_basics_tops', '2019-05-30 10:24:59.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11349', '216', 'Long sleeves', 'kids_boys14y_topstshirts_longsleeves', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11350', '118', 'Hoodies & Sweatshirt', 'kids_boy8y_jumperscardigans_Hoodiessweatshirt', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11399', '42190', 'Dresses', 'kids_newborn_dresses', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11400', '80', 'Longsleeves', 'kids_girl8y_topstshirts_longsleeves', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11401', '10799', 'Leggings', 'Kids_girl14y_jeans_leggings', '2019-05-30 10:26:22.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('11449', '34099', 'Party Dresses', 'ladies_dresses_party', '2019-05-30 10:26:04.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11450', '29548', 'Small bags & Clutch', 'ladies_bags_smallclutch', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11499', '171', 'Loose', 'kids_girl8y_trousersleggings_loose', '2019-05-30 10:26:25.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11500', '158', 'Hairaccessories', 'kids_girl8y_accessories_hairaccessoriesjewelry', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11501', '11201', 'Leggings & Slim', 'kids_girl8y_jeans_leggingsslim', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11502', '755', 'Tops', 'ladies_tops', '2019-05-30 10:25:27.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11503', '1703', 'Jumpsuits Short', 'ladies_jumpsuits_short', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:41.000000');
INSERT INTO `t_category_message` VALUES ('11549', '118', 'Knitwear', 'kids_boy8y_jumperscardigans_knitwear', '2019-05-30 10:26:32.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11550', '0', 'Blazer & Suits', 'kids_boy8y_blazersuits', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11599', '0', 'Multipack & Sets', 'Kids_girl14y_multipack_sets', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11649', '21949', 'Shortie & High waist', 'ladies_lingerie_briefsknickers_shortiehighwaist', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11650', '0', 'Bottoms', 'kids_babyexclusive_bottoms', '2019-05-30 10:25:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11651', '30350', 'Corsets', 'ladies_lingerie_corsets', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11652', '158', 'Bags', 'kids_girl8y_accessories_bags', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11653', '0', 'Shoes & Accessories', 'ladies_newarrivals_shoesacc', '2019-05-30 10:24:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11699', '0', 'Tops', 'Kids_babyboy_basics_tops', '2019-05-30 10:24:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11700', '0', 'Bottoms', 'Kids_babygirl_basics_bottoms', '2019-05-30 10:24:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11701', '80', 'Short sleeves', 'kids_girl8y_topstshirts_shortsleeves', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11799', '0', 'Tops', 'ladies_premiumquality_tops', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11800', '42190', 'Multipack and sets', 'kids_newborn_multipack', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11899', '0', 'Trousers & Leggings', 'kids_babyboy_trousersleggings', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11900', '23149', 'baby/kids', 'home_towels_babykids', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11949', '75', 'Caps/Hats/Scarves', 'kids_boys14y_accessories_capshatsscarves', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('11999', '0', 'Hats/Gloves/Scarves', 'kids_girl14y_accessories_hats_gloves_scarves', '2019-05-30 10:26:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12000', '158', 'Hats/Gloves/Scarves', 'kids_girl8y_accessories_hatsglovesscarves', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12001', '0', 'Accessories', 'kids_babyexclusive_accessories', '2019-05-30 10:25:14.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12099', '5399', 'All in ones', 'Kids_babygirl_allinones', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12149', '3801', 'Loungewear', 'ladies_nightwear_loungewear', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12199', '3053', 'Denim Jackets', 'ladies_jacketscoats_denim', '2019-05-30 10:26:48.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12299', '214', 'Other', 'men_accessories_other', '2019-05-30 10:26:37.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12300', '0', 'Scarves', 'ladies_accessories_hatsscarvesgloves_scarves', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12549', '42190', 'All in ones', 'kids_newborn_allinones', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12599', '6949', 'High waisted', 'ladies_jeans_highwaisted', '2019-05-30 10:26:34.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12600', '11201', 'Straight', 'kids_girl8y_jeans_straight', '2019-05-30 10:25:02.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12601', '5399', 'Jeans', 'kids_babygirl_jeans', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12849', '0', 'Hats/Gloves/Scarves', 'kids_boy8y_accessories_hats_gloves_scarves', '2019-05-30 10:26:41.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('12949', '755', 'Blazers & Waistcoats', 'ladies_blazerswaistcoats', '2019-05-30 10:26:40.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13249', '3053', 'Vests', 'ladies_jacketscoats_vests', '2019-05-30 10:26:26.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13349', '0', 'ARBETSKATALOG_PE_BEAUTY', 'ARBETSKATALOG_PE_BEAUTY', '2019-05-30 10:25:03.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13449', '0', 'Tops', 'Kids_girl8y_basics_tops', '2019-05-30 10:25:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13549', '30350', 'Bras', 'ladies_lingerie_bras', '2019-05-30 10:25:04.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13749', '42190', 'Basic', 'Kids_newborn_basic', '2019-05-30 10:25:04.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13799', '7700', 'Shirts & Blouses', 'ladies_plus_shirtsblouses', '2019-05-30 10:26:29.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13849', '0', 'Trousers & Leggings', 'kids_babyexclusive_trousersleggings', '2019-05-30 10:25:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13899', '0', 'Tops', 'ladies_trend_tops', '2019-05-30 10:25:21.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('13949', '2003', 'Beach wear', 'ladies_swimwear_beachwear', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('14099', '30548', 'View all', 'kids_girl8y_all', '2019-05-30 10:25:05.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('14349', '1703', 'Jumpsuits Long', 'ladies_jumpsuits_long', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('14399', '170', '160x200', 'home_bedlinen_sheets_160x200', '2019-05-30 10:25:06.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('14549', '0', 'Soft Slippers', 'kids_girl8y_shoes_softslippers', '2019-05-30 10:26:29.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('14899', '14099', 'Costume', 'kids_girl8y_costume', '2019-05-30 10:25:07.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('15999', '0', 'Bottoms', 'Kids_boys14y_basics_bottoms', '2019-05-30 10:25:10.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('16249', '0', 'Caps/Hats/Scarves', 'kids_girl14y_accessories_caps_hats_scarves', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('16749', '118', 'Cardigans & Jacket', 'kids_boy8y_jumperscardigans_cardigansjackets', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('16750', '1601', 'Table runners 45x150', 'home_tablerunners_45x150', '2019-05-30 10:25:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('16799', '0', 'Bodies', 'kids_babyexclusive_bodies', '2019-05-30 10:25:12.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17099', '13549', 'Strapless & Bandeau bras', 'ladies_lingerie_bras_straplessbandeaubras', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17149', '901', 'Table cloth 145x200', 'home_tablecloth_145x200', '2019-05-30 10:25:26.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17249', '34099', 'Bodycon Dresses', 'ladies_dresses_bodycon', '2019-05-30 10:26:19.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17299', '214', 'Sportsaccessories', 'men_accessories_sportsaccessories', '2019-05-30 10:26:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17749', '0', 'Multipacks and sets', 'Kids_babyboy_multipacks', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17899', '4151', 'Blazer & Suits', 'kids_boys14y_blazerssuits', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('17999', '81', 'Cardigans & Jackets', 'kids_boys14y_jumperscardigans_cardigansjackets', '2019-05-30 10:25:47.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18000', '0', 'All in ones', 'Kids_babyboy_allinones', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18149', '39992', 'baby/kids', 'home_blankets_babykids', '2019-05-30 10:26:23.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18249', '0', 'Sandals & Flip Flops', 'kids_boys14y_shoes_sandals_flipflops', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18349', '214', 'Mobile Accessories', 'men_accessories_mobileaccessories', '2019-05-30 10:26:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18549', '4151', 'Cartoons & Comics', 'kids_boys14y_cartoonscomics', '2019-05-30 10:25:17.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18799', '0', 'Trousers', 'men_modernclassic_trousers', '2019-05-30 10:25:18.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18849', '0', 'Shoes', 'ladies_divided_shoes', '2019-05-30 10:25:18.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('18999', '28149', 'T-shirts & Tanks', 'men_tshirtstanks', '2019-05-30 10:26:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19099', '11100', 'Cushions', 'home_cushions', '2019-05-30 10:25:19.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19149', '0', 'Shirts', 'men_modernclassic_shirts', '2019-05-30 10:25:19.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19599', '755', 'Knitwear', 'ladies_knitwear', '2019-05-30 10:25:20.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19649', '35796', 'Straight', 'kids_boy8y_jeans_straight', '2019-05-30 10:26:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19699', '0', 'Shirts & Blouses', 'ladies_modernclassic_shirtsblouses', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19749', '38995', 'Swimwear', 'ladies_maternity_swimwear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('19750', '0', 'Skirts', 'ladies_modernclassic_skirts', '2019-05-30 10:25:21.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20099', '0', 'Bottoms', 'men_trend_bottoms', '2019-05-30 10:25:36.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20149', '5399', 'Function', 'kids_babygirl_function', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20299', '0', 'Bottoms', 'ladies_logg_bottoms', '2019-05-30 10:25:22.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20599', '30548', 'View all', 'kids_girl14y_all', '2019-05-30 10:25:23.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20749', '11100', 'Picnic', 'home_picnic', '2019-05-30 10:26:44.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20849', '28149', 'Jeans', 'men_jeans', '2019-05-30 10:25:24.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('20850', '755', 'Skirts', 'ladies_skirts', '2019-05-30 10:25:27.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21049', '81', 'Knitwear', 'kids_boys14y_jumperscardigans_knitwear', '2019-05-30 10:26:26.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21399', '20599', 'Basics', 'Kids_girl14y_basics', '2019-05-30 10:25:25.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21449', '0', 'Tops', 'ladies_everydayfashion_tops', '2019-05-30 10:25:26.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21549', '11100', 'View all', 'home_outdoor_all', '2019-05-30 10:25:26.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21599', '364', 'Bags', 'kids_girl14y_accessories_bags', '2019-05-30 10:25:53.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('21949', '30350', 'Briefs & Knickers', 'ladies_lingerie_briefsknickers', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22049', '607', 'Suits', 'men_blazerssuits_suits', '2019-05-30 10:25:34.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22099', '28149', 'Cardigans & Jumpers', 'men_cardigansjumpers', '2019-05-30 10:25:38.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22249', '0', 'Jackets & Blazers', 'ladies_partywear_jacketsblazers', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22399', '23253', 'Jugs & Carafs', 'home_kitchen_servingware_jugscarafs', '2019-05-30 10:25:28.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22499', '75', 'Hats/Gloves/Scarves', 'kids_boys14y_accessories_hatsglovesscarves', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22500', '0', 'Trousers', 'ladies_divided_trousers', '2019-05-30 10:25:29.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22699', '803', 'Slim', 'kids_boy8y_trousers_slim', '2019-05-30 10:26:17.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('22849', '39494', 'Shoes', 'kids_boy8y_shoes_shoes', '2019-05-30 10:26:39.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23099', '29548', 'Premium Quality', 'ladies_bags_premium', '2019-05-30 10:25:33.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23100', '0', 'Shirts & Blouses', 'ladies_divided_shirtsblouses', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23149', '11100', 'View all', 'home_towels_all', '2019-05-30 10:25:54.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23199', '8099', 'Denim Jackets', 'men_jacketscoats_denimjackets', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23200', '23253', 'Bowls & Platters', 'home_kitchen_servingware_bowlsplatters', '2019-05-30 10:26:03.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23201', '1001', 'Dressed', 'men_trousers_dressed', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23202', '23253', 'Plates', 'home_kitchen_servingware_plates', '2019-05-30 10:26:08.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23203', '111', 'Sweatshirts', 'men_hoodiessweatshirts_sweatshirts', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23249', '23253', 'Cutleries & Table Utencils', 'home_kitchen_servingware_cutleriestableutencils', '2019-05-30 10:26:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23250', '0', 'Cooking Utencils', 'home_kitchen_cookware_cookingutencils', '2019-05-30 10:25:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23251', '0', 'Cutting Boards', 'home_kitchen_cookware_cuttingboards', '2019-05-30 10:25:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23252', '28149', 'Bags', 'men_bags', '2019-05-30 10:26:37.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23253', '10249', 'Servingware', 'home_kitchen_servingware', '2019-05-30 10:25:31.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23254', '23253', 'Glasses & Mugs', 'home_kitchen_servingware_glassesmugs', '2019-05-30 10:25:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23349', '5549', 'Laundry Bags', 'home_bathroom_laundrybags', '2019-05-30 10:26:30.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23549', '2', 'Basics', 'men_shorts_basics', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23599', '2752', 'Multipack', 'men_underwearloungewear_multipack', '2019-05-30 10:26:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23600', '18999', 'Printed', 'men_tshirtstanks_printed', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23699', '2752', 'Underwear', 'men_underwearloungewear_underwear', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23799', '0', 'Rugs', 'home_kidsroom_textile_rugs', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23800', '2', 'Chinos', 'men_shorts_chinos', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23949', '26749', 'Short Sleeved', 'men_shirts_shortsleeved', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23950', '0', 'Kitchen Towels', 'home_kitchen_kitchentextile_kitchentowels', '2019-05-30 10:25:33.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('23999', '111', 'Hoodies', 'men_hoodiessweatshirts_hoodies', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('24149', '39494', 'Soft Slippers', 'kids_boy8y_shoes_softslippers', '2019-05-30 10:26:21.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('24349', '0', 'Bottoms', 'ladies_premiumquality_bottoms', '2019-05-30 10:25:34.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('24399', '214', 'Hats & Caps', 'men_accessories_hatscaps', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('24449', '28149', 'T-shirts', 'men_tshirts', '2019-05-30 10:25:35.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('24450', '0', 'Outdoor', 'ladies_sport_outdoor', '2019-05-30 10:26:15.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('25549', '2901', 'Sarongs & Ponchos', 'ladies_accessories_sarongsponchos', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('25649', '214', 'Wallets', 'men_accessories_wallets', '2019-05-30 10:26:12.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('25999', '0', 'Aprons & Potholders', 'home_kitchen_kitchentextile_apronspotholders', '2019-05-30 10:25:40.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26149', '2', 'Cargo', 'men_shorts_cargo', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26249', '8099', 'Biker Jackets', 'men_jacketscoats_bikerjackets', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26399', '11100', 'Storage', 'home_storage', '2019-05-30 10:25:46.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26449', '10249', 'Placemats', 'home_kitchen_placemats', '2019-05-30 10:25:46.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26699', '0', 'T-shirts', 'men_edition_tshirts', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26700', '10249', 'Storage', 'home_kitchen_storage', '2019-05-30 10:25:44.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26749', '28149', 'Shirts', 'men_shirts', '2019-05-30 10:26:49.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26750', '28149', 'Sport', 'men_sport', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('26799', '0', 'Duvet Cover Sets', 'home_kidsroom_textile_duvetcoversets', '2019-05-30 10:25:42.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('27449', '1001', 'Cargo', 'men_trousers_cargo', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('27450', '0', 'Basics T-shirts', 'men_basics_tshirts', '2019-05-30 10:26:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('27799', '0', 'Running', 'ladies_sport_running', '2019-05-30 10:26:05.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('27949', '11201', 'Loose', 'kids_girl8y_jeans_loose', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('28099', '20599', 'Fancy dress', 'kids_girl14y_fancydress', '2019-05-30 10:25:47.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('28149', '0', 'MEN', 'men', '2019-05-30 10:25:47.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('28199', '42190', 'Shoes and Slippers', 'kids_newborn_shoes', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('28499', '79', 'Bags', 'kids_boy8y_accessories_bags', '2019-05-30 10:26:18.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('28799', '10249', 'Aprons & Potholders', 'home_kitchen_apronspotholders', '2019-05-30 10:25:49.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29049', '30750', 'Posters', 'home_livingroom_decorations_posters', '2019-05-30 10:26:03.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29098', '0', 'Basics Bottoms', 'men_basics_bottoms', '2019-05-30 10:25:50.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29498', '20849', 'Skinny Carrot', 'men_jeans_skinnycarrot', '2019-05-30 10:26:27.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29548', '755', 'Bags', 'ladies_bags', '2019-05-30 10:26:37.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29648', '8099', 'Leather Jackets', 'men_jacketscoats_leatherjackets', '2019-05-30 10:26:36.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29698', '22099', 'Premium Quality', 'men_cardigansjumpers_premiumquality', '2019-05-30 10:25:53.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('29998', '75', 'Bags', 'kids_boys14y_accessories_bags', '2019-05-30 10:25:54.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30298', '360', '70x140', 'home_carpets_70x140', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30348', '360', '140x200', 'home_carpets_140x200', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30349', '360', '70x200', 'home_carpets_70x200', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30350', '755', 'Lingerie', 'ladies_lingerie', '2019-05-30 10:25:55.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30448', '42190', 'Outdoor and  Fleece', 'kids_newborn_outdoorclothing', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30548', '0', 'KIDS', 'kids', '2019-05-30 10:25:56.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30598', '0', 'Tablecloths & Runner', 'home_kitchen_kitchentextile_tableclothsrunner', '2019-05-30 10:25:56.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30648', '0', 'Gloves', 'ladies_accessories_hatsscarvesgloves_gloves', '2019-05-30 10:26:20.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30748', '755', '&Denim', 'ladies_denim', '2019-05-30 10:25:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30749', '0', 'Napkins', 'home_kitchen_kitchentextile_napkins', '2019-05-30 10:25:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30750', '0', 'Decorations', 'home_livingroom_decorations', '2019-05-30 10:26:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30751', '0', 'Placemats', 'home_kitchen_kitchentextile_placemats', '2019-05-30 10:26:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30898', '5549', 'Accessories', 'home_bathroom_accessories', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('30948', '0', 'Cushions', 'home_kidsroom_textile_cushions', '2019-05-30 10:25:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('31048', '0', 'Basics Polo', 'men_basics_polo', '2019-05-30 10:25:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('31148', '11100', 'Homewear', 'home_homewear', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('31198', '2052', 'Storage', 'home_kidsroom_storage', '2019-05-30 10:25:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('31748', '11100', 'Place mats', 'home_placemats', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('31998', '11100', 'Giftwraps', 'home_giftwraps', '2019-05-30 10:26:28.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('32497', '502', 'Scarves', 'kids_babygirl_accessories_scarves', '2019-05-30 10:26:12.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('32747', '453', 'Scarves', 'kids_babyboy_accessories_scarves', '2019-05-30 10:26:04.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('33647', '0', 'Single', 'home_bedroom_duvetcoversets_single', '2019-05-30 10:26:08.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('33797', '20849', 'Tech Stretch Skinny', 'men_jeans_techstretchskinny', '2019-05-30 10:26:08.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('33947', '20849', 'Slim Straight', 'men_jeans_slimstraight', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('34047', '507', 'Slippers', 'men_shoes_slippers', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('34097', '2052', 'Decorations & Toys', 'home_kidsroom_decorationstoys', '2019-05-30 10:26:54.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('34098', '26750', 'Running', 'men_sport_running', '2019-05-30 10:26:37.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('34099', '755', 'Dresses', 'ladies_dresses', '2019-05-30 10:26:10.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35146', '14099', 'Fancy Dress', 'kids_girl8y_fancydress', '2019-05-30 10:26:20.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35246', '20849', 'Tapered', 'men_jeans_tapered', '2019-05-30 10:26:52.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35646', '0', 'Hoodies & Sweatshirts', 'ladies_logg_hoodiessweatshirts', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35696', '0', 'Bottoms', 'ladies_partywear_bottoms', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35796', '0', 'Jeans', 'kids_boy8y_jeans', '2019-05-30 10:26:16.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35896', '42190', 'Baby fleece', 'Kids_newborn_fleece', '2019-05-30 10:26:20.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('35946', '20599', 'Shoes', 'kids_girl14y_shoes', '2019-05-30 10:26:44.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('36046', '38445', 'Shoes', 'kids_boys14y_shoes_shoes', '2019-05-30 10:26:17.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('36246', '506', 'Vases', 'home_decorations_vases', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('36895', '755', 'Hoodies & Sweatshirts', 'ladies_hoodiessweatshirts', '2019-05-30 10:26:21.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('37345', '2052', 'Homewear', 'home_kidsroom_homewear', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('37495', '506', 'Knobs & Hooks', 'home_decorations_knobshooks', '2019-05-30 10:26:50.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('37795', '23253', 'Trays', 'home_kitchen_servingware_trays', '2019-05-30 10:26:25.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('37845', '11502', 'Off-shoulder tops', 'ladies_tops_offshouldertops', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38146', '506', 'Pots', 'home_decorations_pots', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38195', '755', 'Shirts & Blouses', 'ladies_shirtsblouses', '2019-05-30 10:26:27.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38295', '506', 'Candleholders', 'home_decorations_candleholders', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38445', '4151', 'Shoes', 'kids_boys14y_shoes', '2019-05-30 10:26:28.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38895', '506', 'Decor Accessories', 'home_decorations_decoraccessories', '2019-05-30 10:26:30.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38896', '506', 'Posters', 'home_decorations_posters', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('38995', '755', 'Maternity Wear', 'ladies_maternity', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('39244', '506', 'Mirror & Frames', 'home_decorations_mirrorframes', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('39245', '506', 'Trays', 'home_decorations_trays', '2019-05-30 10:27:01.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('39494', '0', 'Shoes', 'kids_boy8y_shoes', '2019-05-30 10:26:32.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('39694', '20849', 'Skinny Jogger', 'men_jeans_skinnyjogger', '2019-05-30 10:26:33.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('39992', '11100', 'Blankets', 'home_blankets', '2019-05-30 10:26:39.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('40092', '10799', 'Pull On', 'kids_girl14y_jeans_pullon', '2019-05-30 10:26:35.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('40292', '26749', 'Long Sleeved', 'men_shirts_longsleeved', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('41542', '11100', 'Furnitures', 'home_furnitures', '2019-05-30 10:26:42.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('41742', '0', 'Off-shoulder blouses', 'ladies_shirtblouses_offshoulderblouses', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('41942', '21549', 'Watering can', 'home_outdoor_wateringcan', '2019-05-30 10:26:47.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('42190', '30548', 'View all', 'kids_newborn_all', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('42240', '34099', 'Shirt Dresses', 'ladies_dresses_shirt', '2019-05-30 10:26:45.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('42539', '2', 'Sport', 'men_shorts_sport', '2019-05-30 10:26:46.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('43381', '2752', 'Loungewear', 'men_underwearloungewear_loungewear', '2019-05-30 10:26:51.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('43678', '507', 'Loafers', 'men_shoes_loafers', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('43829', '0', 'Party & Occasional wear', 'kids_babyboy_partandoccasionalwear', '2019-05-30 10:26:53.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('43830', '2', 'Casual', 'men_shorts_casual', '2019-05-30 10:26:59.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44424', '0', 'Limited Edition', 'ladies_beauty_limitededition', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44573', '0', 'GENERAL OFFERS', 'GENERAL OFFERS', '2019-05-30 10:26:56.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44723', '0', 'Slip-on', 'Ladies_shoes_slipon', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44724', '1351', 'High waisted', 'Ladies_shorts_highwaisted', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44773', '552', 'Shopper & Totes', 'ladies_accessories_bags_shoppertotes', '2019-05-30 10:26:57.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('44823', '552', 'Handbags', 'ladies_accessories_bags_handbags', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:42.000000');
INSERT INTO `t_category_message` VALUES ('44922', '552', 'Shoulder & Cross bags', 'ladies_accessories_bags_shouldercrossbags', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('44971', '0', 'Make-up Bags & Travel', 'ladies_beauty_makeupbagstravel', '2019-05-30 10:26:58.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('45319', '39992', 'Wool Blankets', 'home_blankets_woolblankets', '2019-05-30 10:27:00.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521546', '0', 'Small bags & Clutch', 'ladies_accessories_bags_smallbagsclutch', '2019-07-04 02:03:05.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521547', '0', 'Laundry Basket', 'home_storage_laundrybasket', '2019-07-04 02:03:05.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521596', '0', 'Weekend bag', 'men_accessories_bags_weekendbag', '2019-07-04 02:03:05.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521646', '0', 'Backpack', 'men_accessories_bags_backpack', '2019-07-04 02:03:05.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521647', '0', 'Loafers', 'Ladies_shoes_loafers', '2019-07-04 02:03:05.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521696', '0', 'Printed T-shirts', 'ladies_tops_printed_tshirts', '2019-07-04 02:03:06.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521746', '0', 'T-shirts', 'ladies_tops_tshirts', '2019-07-04 02:03:06.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('521996', '0', 'Backpack', 'ladies_accessories_bags_backpack', '2019-07-04 02:03:08.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522146', '0', 'Wallets', 'ladies_accessories_wallets', '2019-07-04 02:03:09.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522147', '0', 'View all', 'kids_boy8y_shoes_all', '2019-07-04 02:03:09.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522148', '0', 'Make-up Brushes', 'ladies_beauty_brushestools_makeupbrushes', '2019-07-04 02:03:09.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522149', '0', 'Brushes & Tools', 'ladies_beauty_brushestools', '2019-07-04 02:03:09.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522578', '0', 'Faux Fur Blankets', 'home_blankets_fauxfurblankets', '2019-07-04 02:03:12.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522676', '0', 'Shoes', 'kids_girl8y_shoes', '2019-07-04 02:03:13.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('522677', '0', 'Double', 'home_bedroom_duvetcoversets_double', '2019-07-04 02:03:13.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('2126394', '0', 'Wrap Dresses', 'Ladies_dresses_wrap', '2019-07-07 02:03:38.000000', '2019-07-12 02:14:43.000000');
INSERT INTO `t_category_message` VALUES ('2666909', '0', 'View all', 'kids_girl8y_shoes_all', '2019-07-08 02:03:36.000000', '2019-07-12 02:14:43.000000');

-- ----------------------------
-- Table structure for t_datax_config
-- ----------------------------
DROP TABLE IF EXISTS `t_datax_config`;
CREATE TABLE `t_datax_config` (
  `id` varchar(32) NOT NULL COMMENT '任务id',
  `name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `code` varchar(255) DEFAULT NULL COMMENT '任务编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `startup_time` datetime DEFAULT NULL COMMENT '启动时间',
  `json_file_path` varchar(255) DEFAULT NULL COMMENT 'Json文件路径',
  `json_file_name` varchar(255) DEFAULT NULL COMMENT 'Json文件名',
  `json_file_content` text COMMENT 'Json文件内容',
  `pythonscript_path` varchar(255) DEFAULT NULL COMMENT 'python脚本路径',
  `pythonscript__file_name` varchar(255) DEFAULT NULL COMMENT 'python脚本文件名',
  `pythonscript_content` text COMMENT 'python脚本内容',
  `server_ip` varchar(255) DEFAULT NULL COMMENT '服务器Ip',
  `server_port` varchar(255) DEFAULT NULL COMMENT '服务端的运行端口',
  `runState` int(4) DEFAULT '2' COMMENT '运行状态[RUNING(1):已开启, UNOPENED(2):未开启,PUSHED(3):已接收请求,STARTING(4):正在启动,CREATEPROFILE(5):创建配置文件,CREATESCRIPTS(6):创建脚本,EXECSCRIPTS(7):执行脚本,FAIL(8):失败,PUSHING(9):开始推送,EXCEPTION(10):启动异常,RESTART(11):重启,RESTARTING(12):正在重启]',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='DataX信息表';

-- ----------------------------
-- Records of t_datax_config
-- ----------------------------
INSERT INTO `t_datax_config` VALUES ('0905c09aca3f47f9b4baf90f4e0000d2', '21122', '312', '2019-08-07 15:27:17', '2019-08-07 15:27:17', null, '1', '1', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '1', '1', 'qweqweqw', '127.0.01.1', '1', '2', '1');

-- ----------------------------
-- Table structure for t_etl_jar
-- ----------------------------
DROP TABLE IF EXISTS `t_etl_jar`;
CREATE TABLE `t_etl_jar` (
  `etl_jar_id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL COMMENT 'jar文件名',
  `class_name` varchar(200) DEFAULT NULL COMMENT '类名',
  `resource_dir` varchar(200) NOT NULL COMMENT '存储的文件夹名字',
  `resource_path` varchar(200) NOT NULL COMMENT '存储路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`etl_jar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='jar包列表';

-- ----------------------------
-- Records of t_etl_jar
-- ----------------------------
INSERT INTO `t_etl_jar` VALUES ('2', '店铺领卡交叉分析', 'com.qmtec.peony.crm.stcrm.stcrm003.process', '/data/tools', '/data/tools/qmtec-st-crm-1.0.jar', '2019-05-21 00:21:00', null);
INSERT INTO `t_etl_jar` VALUES ('3', '品类销售分析', 'com.qmtec.peony.crm.stcrm.stcrm005.process', '/data/tools', '/data/tools/qmtec-st-crm-1.0.jar', '2019-06-04 12:16:37', null);

-- ----------------------------
-- Table structure for t_etl_task
-- ----------------------------
DROP TABLE IF EXISTS `t_etl_task`;
CREATE TABLE `t_etl_task` (
  `etl_task_id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '任务命名',
  `status` int(4) DEFAULT '1' COMMENT 'etl任务状态[common(1):正常, forbid(2):禁用, deleted(3):删除]',
  `etl_jar_id` int(6) DEFAULT NULL COMMENT '主类jar的ID',
  `resource_path` varchar(200) DEFAULT NULL COMMENT '主类jar存放路径',
  `main_class` varchar(200) DEFAULT NULL COMMENT 'jar主类',
  `master` varchar(20) DEFAULT 'yarn-cluster' COMMENT 'spark的运行模式，如，yarn-cluster',
  `deploy_mode` varchar(20) DEFAULT 'cluster' COMMENT 'spark的发布模式，如cluster',
  `config` varchar(3000) DEFAULT NULL COMMENT 'spark任务所需参数json',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`etl_task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='etl任务列表';

-- ----------------------------
-- Records of t_etl_task
-- ----------------------------
INSERT INTO `t_etl_task` VALUES ('2', '店铺领卡交叉分析', '1', '2', '/data/tools/qmtec-st-crm-1.0.jar', 'com.qmtec.peony.crm.stcrm.stcrm003.process', 'yarn', 'cluster', null, '2019-05-21 00:19:26', null);
INSERT INTO `t_etl_task` VALUES ('3', '品类销售分析', '1', '3', '/data/tools/qmtec-st-crm-1.0.jar', 'com.qmtec.peony.crm.stcrm.stcrm005.process', 'yarn', 'cluster', null, '2019-06-04 12:17:46', null);

-- ----------------------------
-- Table structure for t_file_template
-- ----------------------------
DROP TABLE IF EXISTS `t_file_template`;
CREATE TABLE `t_file_template` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL COMMENT '编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `file_config_content` text COMMENT '配置文件模板内容',
  `fileType` int(4) DEFAULT '2' COMMENT '模板类型[FLUME(1):类型FLUME, DATAXJSON(2):类型DATAXJSON,DATAXSCRIPT(3):类型PYTHONSCRIPT]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='配置文件模板';

-- ----------------------------
-- Records of t_file_template
-- ----------------------------
INSERT INTO `t_file_template` VALUES ('277b64a6b36c4a70b65776c92f9249c3', 'qweqweqw', 'qweq', '2019-08-07 11:29:21', '2019-08-07 11:29:21', '', 'qweqweqw', '3');
INSERT INTO `t_file_template` VALUES ('6df30b601c0744a8a8e1950c45e9e274', 'DATAXJSON', 'J001', '2019-08-06 18:47:13', '2019-08-06 18:47:13', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2');
INSERT INTO `t_file_template` VALUES ('d9251373103c4531a89b577ef6405261', 'wqeqw', 'eqweq', '2019-08-06 18:29:11', '2019-08-06 18:46:15', 'qe23123123', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '1');

-- ----------------------------
-- Table structure for t_flume_channelInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flume_channelInfo`;
CREATE TABLE `t_flume_channelInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `ip_addr` varchar(255) DEFAULT NULL COMMENT 'ip地址',
  `put_success_count` varchar(255) DEFAULT NULL COMMENT '成功放入数量',
  `put_attempt_count` varchar(255) DEFAULT NULL COMMENT '正在放入数量',
  `percentage` varchar(255) DEFAULT NULL COMMENT '百分比',
  `channel_size` varchar(255) DEFAULT NULL COMMENT '通道总量',
  `take_success_count` varchar(255) DEFAULT NULL COMMENT '成功取走的数量',
  `take_attempt_count` varchar(255) DEFAULT NULL COMMENT '正在取走的数量',
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭, PORTMONITORINGEXCEPTION(3):端口监控异常]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `channel_capacity` varchar(255) DEFAULT NULL COMMENT '通道容量',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的通道内容';

-- ----------------------------
-- Records of t_flume_channelInfo
-- ----------------------------
INSERT INTO `t_flume_channelInfo` VALUES ('13ace78bbf2e4cf38ae13b2ceb844315', 'c1', '2019-08-12 17:30:26', '192.168.15.60', '184900', '184900', '4.399', '4399', '180500', '180520', '2', '0', '100000', '2019-08-12 17:30:35', '2019-08-12 17:30:55');
INSERT INTO `t_flume_channelInfo` VALUES ('1f504ce3c58b4eb69ca5e525b11b88aa', 'c1', '2019-08-12 17:50:51', '192.168.15.60', '562500', '562500', '4.36', '4360', '558140', '558160', '2', '0', '100000', '2019-08-12 17:51:20', '2019-08-12 17:52:08');
INSERT INTO `t_flume_channelInfo` VALUES ('21014331753d4b18a79710ae6ded182a', 'c1', '2019-08-12 17:34:03', '192.168.15.60', '1042700', '1042800', '4.32', '4320', '1038380', '1038400', '2', '0', '100000', '2019-08-12 17:34:19', '2019-08-12 17:35:55');
INSERT INTO `t_flume_channelInfo` VALUES ('26c6e2fcc7e5401e8a486d1ab01c51db', 'c1', '2019-08-12 17:34:12', '192.168.15.60', '2600', '2600', '2.5989999999999998', '2599', '0', '1', '2', '0', '100000', '2019-08-12 17:34:20', '2019-08-12 17:34:49');
INSERT INTO `t_flume_channelInfo` VALUES ('4274064af12b4cbd9a515d719a7cd024', 'c1', '2019-08-12 18:11:04', '192.168.15.60', '1697400', '1697400', '4.36', '4360', '1693040', '1693060', '3', '0', '100000', '2019-08-12 18:11:11', '2019-08-12 18:14:09');
INSERT INTO `t_flume_channelInfo` VALUES ('44cde3f7733c46379e70170cfea115e8', 'c1', '2019-08-12 17:30:16', '192.168.15.60', '599200', '599300', '4.32', '4320', '594880', '594900', '2', '0', '100000', '2019-08-12 17:30:35', '2019-08-12 17:31:21');
INSERT INTO `t_flume_channelInfo` VALUES ('4a084f8c58c5471d82da4648f995b893', 'c1', '2019-08-12 17:31:49', '192.168.15.60', '1129000', '1129000', '4.38', '4380', '1124620', '1124640', '2', '0', '100000', '2019-08-12 17:31:56', '2019-08-12 17:33:36');
INSERT INTO `t_flume_channelInfo` VALUES ('6fc715bfbf4f4ce1978929e71d184f40', 'c1', '2019-08-12 17:31:30', '192.168.15.60', '1207700', '1207700', '4.34', '4380', '1203340', '1203360', '2', '0', '100000', '2019-08-12 17:31:38', '2019-08-12 17:33:29');
INSERT INTO `t_flume_channelInfo` VALUES ('7ced97a922c74058be097e45d503fc43', 'c1', '2019-08-12 17:30:19', '192.168.15.60', '542700', '542800', '4.36', '4360', '538340', '538360', '2', '0', '100000', '2019-08-12 17:30:35', '2019-08-12 17:31:23');
INSERT INTO `t_flume_channelInfo` VALUES ('7f7cf8a06383486cbe004a99d6c1c1a6', 'c1', '2019-08-12 17:34:46', '192.168.15.60', '576300', '576400', '4.34', '4340', '571960', '571980', '2', '0', '100000', '2019-08-12 17:34:55', '2019-08-12 17:35:56');
INSERT INTO `t_flume_channelInfo` VALUES ('beb58989ccf34e98b4988057d7b4bc76', 'c1', '2019-08-12 17:31:26', '192.168.15.60', '1414100', '1414100', '4.38', '4380', '1409700', '1409720', '2', '0', '100000', '2019-08-12 17:31:39', '2019-08-12 17:33:34');
INSERT INTO `t_flume_channelInfo` VALUES ('c0afab9487f84c2e9f8bfc7e83dad34a', 'c1', '2019-08-12 17:34:08', '192.168.15.60', '953600', '953700', '4.32', '4320', '949280', '949300', '2', '0', '100000', '2019-08-12 17:34:18', '2019-08-12 17:35:57');
INSERT INTO `t_flume_channelInfo` VALUES ('c457f685faac4d808d02daa690a59ac1', 'c1', '2019-08-12 17:50:57', '192.168.15.60', '490300', '490300', '4.32', '4320', '485980', '486000', '2', '0', '100000', '2019-08-12 17:51:20', '2019-08-12 17:52:11');
INSERT INTO `t_flume_channelInfo` VALUES ('c49c0f6f243f47c4a4ce19c4926aa7cd', 'c1', '2019-08-12 17:50:55', '192.168.15.60', '491200', '491200', '4.399', '4399', '486800', '486820', '2', '0', '100000', '2019-08-12 17:51:20', '2019-08-12 17:52:09');
INSERT INTO `t_flume_channelInfo` VALUES ('c6263759e2d549f2a79f3ad65e18a97b', 'c1', '2019-08-12 18:11:08', '192.168.15.60', '1614000', '1614100', '4.34', '4340', '1609660', '1609680', '3', '0', '100000', '2019-08-12 18:11:10', '2019-08-12 18:14:09');
INSERT INTO `t_flume_channelInfo` VALUES ('d1d2e8feb2fd4def9d4fa197400f6254', 'c1', '2019-08-16 16:01:55', '192.168.15.60', '11271700', '11271800', '4.34', '4340', '11267360', '11267380', '2', '0', '100000', '2019-08-16 16:02:12', '2019-08-16 16:15:19');
INSERT INTO `t_flume_channelInfo` VALUES ('e701b96bdebf4fe78c4a308459493d6d', 'c1', '2019-08-12 17:30:54', '192.168.15.60', '249500', '249600', '4.3', '4320', '245200', '245200', '2', '0', '100000', '2019-08-12 17:31:05', '2019-08-12 17:31:25');
INSERT INTO `t_flume_channelInfo` VALUES ('f289a818da25480388e638f23cf40a72', 'c1', '2019-08-12 18:12:05', '192.168.15.60', '1024400', '1024400', '4.38', '4380', '1020020', '1020040', '3', '0', '100000', '2019-08-12 18:12:08', '2019-08-12 18:14:09');

-- ----------------------------
-- Table structure for t_flume_config
-- ----------------------------
DROP TABLE IF EXISTS `t_flume_config`;
CREATE TABLE `t_flume_config` (
  `context_id` bigint(20) NOT NULL COMMENT '任务id',
  `monitor_port` int(6) DEFAULT NULL COMMENT 'flume监控端口',
  `name` varchar(255) DEFAULT NULL COMMENT '任务名',
  `code` varchar(255) DEFAULT NULL COMMENT '任务编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `startup_time` datetime DEFAULT NULL COMMENT '启动时间',
  `flume_home` varchar(255) DEFAULT NULL COMMENT 'flume根目录',
  `process_pid` int(18) DEFAULT NULL COMMENT '进程pid',
  `profile_name` varchar(255) DEFAULT NULL COMMENT '配置文件名',
  `agent_name` varchar(255) DEFAULT NULL COMMENT 'flume配置文件的agent名',
  `server_ip` varchar(255) DEFAULT NULL COMMENT '服务器Ip',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `file_config_content` text COMMENT '配置文件内容',
  `runState` int(4) DEFAULT '2' COMMENT '运行状态[RUNING(1):已开启, UNOPENED(2):未开启,PUSHED(3):已接收请求,STARTING(4):正在启动,CREATEPROFILE(5):创建配置文件,CREATESCRIPTS(6):创建脚本,EXECSCRIPTS(7):执行脚本,FAIL(8):失败,PUSHING(9):开始推送,EXCEPTION(10):启动异常,RESTART(11):重启,RESTARTING(12):正在重启,PortMonitoringException(13):端口监控异常]',
  `server_port` varchar(255) DEFAULT NULL COMMENT '服务端的运行端口',
  `flume_monitor_content` text COMMENT 'Flume监控端口返回的内容',
  `flume_config_type` int(4) DEFAULT NULL,
  PRIMARY KEY (`context_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='flume信息表';

-- ----------------------------
-- Records of t_flume_config
-- ----------------------------
INSERT INTO `t_flume_config` VALUES ('4596106507945', '29861', 'flume_test002', 'test002', '2019-08-12 14:19:12', '2019-08-12 18:14:47', '2019-08-12 18:11:02', '/home/flume/flume190', '16928', 'flume_test002', 'mk', '192.168.15.60', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2', '10060', '{\"SINK.k1\":{\"ConnectionCreatedCount\":\"0\",\"BatchCompleteCount\":\"0\",\"BatchEmptyCount\":\"0\",\"EventDrainAttemptCount\":\"122320\",\"StartTime\":\"1565590763584\",\"BatchUnderflowCount\":\"0\",\"ConnectionFailedCount\":\"0\",\"ConnectionClosedCount\":\"0\",\"Type\":\"SINK\",\"RollbackCount\":\"0\",\"EventDrainSuccessCount\":\"122300\",\"KafkaEventSendTimer\":\"13582\",\"StopTime\":\"0\"},\"SOURCESQL.s1\":{\"CurrentThroughput\":\"0\",\"MaxThroughput\":\"0\",\"EventCount\":\"126700\",\"AverageThroughput\":\"7452\"},\"CHANNEL.c1\":{\"ChannelCapacity\":\"100000\",\"ChannelFillPercentage\":\"4.38\",\"Type\":\"CHANNEL\",\"ChannelSize\":\"4380\",\"EventTakeSuccessCount\":\"122300\",\"EventTakeAttemptCount\":\"122320\",\"StartTime\":\"1565590763213\",\"EventPutAttemptCount\":\"126700\",\"EventPutSuccessCount\":\"126700\",\"StopTime\":\"0\"}}', '1');
INSERT INTO `t_flume_config` VALUES ('6472019080514', '29187', '192.168.15.61', '61', '2019-08-05 11:15:02', '2019-08-12 11:17:30', '2019-08-12 11:17:21', '/home/flume/flume191', '24296', 'flume_test', 'mk', '192.168.15.61', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2', '10060', null, '1');
INSERT INTO `t_flume_config` VALUES ('6602019080514', '29724', '192.168.15.62', '62', '2019-08-05 11:18:13', '2019-08-12 11:15:49', '2019-08-12 11:15:26', '/home/flume/flume192', '3058', 'flume_test', 'mk', '192.168.15.62', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2', '10060', null, '1');
INSERT INTO `t_flume_config` VALUES ('7019469830973', '20202', '192.168.15.60', '60', '2019-08-05 14:34:43', '2019-08-16 16:15:12', '2019-08-16 16:01:53', '/home/flume/flume190', '1515', 'flume_test', 'mk', '192.168.15.60', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2', '10060', '{\"SINK.k1\":{\"ConnectionCreatedCount\":\"0\",\"BatchCompleteCount\":\"0\",\"BatchEmptyCount\":\"0\",\"EventDrainAttemptCount\":\"12600\",\"StartTime\":\"1565942514731\",\"BatchUnderflowCount\":\"0\",\"ConnectionFailedCount\":\"0\",\"ConnectionClosedCount\":\"0\",\"Type\":\"SINK\",\"RollbackCount\":\"0\",\"EventDrainSuccessCount\":\"12580\",\"KafkaEventSendTimer\":\"1336\",\"StopTime\":\"0\"},\"SOURCESQL.s1\":{\"CurrentThroughput\":\"0\",\"MaxThroughput\":\"0\",\"EventCount\":\"16900\",\"AverageThroughput\":\"16900\"},\"CHANNEL.c1\":{\"ChannelCapacity\":\"100000\",\"ChannelFillPercentage\":\"4.32\",\"Type\":\"CHANNEL\",\"ChannelSize\":\"4320\",\"EventTakeSuccessCount\":\"12580\",\"EventTakeAttemptCount\":\"12600\",\"StartTime\":\"1565942514646\",\"EventPutAttemptCount\":\"17000\",\"EventPutSuccessCount\":\"16900\",\"StopTime\":\"0\"}}', '1');
INSERT INTO `t_flume_config` VALUES ('9312019080514', '22172', '192.168.15.63', '63', '2019-08-05 11:19:37', '2019-08-12 11:15:36', '2019-08-12 11:15:24', '/home/flume/flime193', '11828', 'flume_test', 'mk', '192.168.15.63', '', 'mk.channels = c1\nmk.sources = s1\nmk.sinks = k1\n###########sql source#################\n# For each one of the sources, the type is defined\nmk.sources.s1.type = org.keedio.flume.source.SQLSource\nmk.sources.s1.hibernate.connection.url = jdbc:mysql://192.168.0.65:3306/full_crm\nmk.sources.s1.hibernate.connection.user = bidb_admin\nmk.sources.s1.hibernate.connection.password = mypassword\nmk.sources.s1.hibernate.connection.autocommit = true\nmk.sources.s1.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect\nmk.sources.s1.hibernate.connection.driver_class = com.mysql.jdbc.Driver\nmk.sources.s1.run.query.delay=5000\nmk.sources.s1.status.file.path = /home/flume/flume190/status\nmk.sources.s1.status.file.name = sqlSource.status\n\n# Custom query\nmk.sources.s1.start.from = 0\nmk.sources.s1.custom.query = SELECT `brand_id`, `buyer_email`, `buyer_message`, `channel_type` FROM vz_order\nmk.sources.s1.batch.size = 100\nmk.sources.s1.max.rows = 100\nmk.sources.s1.hibernate.connection.provider_class = org.hibernate.connection.C3P0ConnectionProvider\nmk.sources.s1.hibernate.c3p0.min_size=10\nmk.sources.s1.hibernate.c3p0.max_size=30\n\n################################################################\nmk.channels.c1.type = memory\nmk.channels.c1.capacity = 100000\nmk.channels.c1.transactionCapacity = 10000\nmk.channels.c1.byteCapacityBufferPercentage = 20\nmk.channels.c1.byteCapacity = 800000\n\n################################################################\nmk.sinks.k1.type = org.apache.flume.sink.kafka.KafkaSink\nmk.sinks.k1.topic = category_tp\nmk.sinks.k1.brokerList = 192.168.15.40:9092\nmk.sinks.k1.requiredAcks = 1\nmk.sinks.k1.batchSize = 20\nmk.sinks.k1.channel = c1\nmk.sources.s1.channels=c1', '2', '10060', null, '1');

-- ----------------------------
-- Table structure for t_flume_sinkInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flume_sinkInfo`;
CREATE TABLE `t_flume_sinkInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `ip_addr` varchar(255) DEFAULT NULL COMMENT 'Ip地址',
  `start_time` datetime DEFAULT NULL COMMENT '启动时间',
  `connection_created_count` varchar(255) DEFAULT NULL COMMENT '存储系统创建连接数量',
  `batch_complete_count` varchar(255) DEFAULT NULL COMMENT '批处理完成计数',
  `batch_empty_count` varchar(255) DEFAULT NULL COMMENT '空的批量的数量',
  `event_drainAttempt_count` varchar(255) DEFAULT NULL COMMENT '写出到存储的事件总数量',
  `batch_underflow_count` varchar(255) DEFAULT NULL COMMENT '批量下溢计数',
  `connection_failed_count` varchar(255) DEFAULT NULL COMMENT '存储系统由于错误关闭的连接数量',
  `connection_closed_count` varchar(255) DEFAULT NULL COMMENT '存储系统关闭的连接数量',
  `rollback_count` varchar(255) DEFAULT NULL COMMENT '回滚计数',
  `event_drain_success_count` varchar(255) DEFAULT NULL COMMENT '成功写出到存储的事件总数量',
  `kafka_event_sendTimer` varchar(255) DEFAULT NULL COMMENT 'Kafka事件发送计时器',
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭, PORTMONITORINGEXCEPTION(3):端口监控异常]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的下沉内容';

-- ----------------------------
-- Records of t_flume_sinkInfo
-- ----------------------------
INSERT INTO `t_flume_sinkInfo` VALUES ('17825bbd981941f09b6a541cae64c2c4', 'k1', '192.168.15.60', '2019-08-12 17:30:19', '0', '0', '0', '538360', '0', '0', '0', '0', '538340', '36412', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:31:23');
INSERT INTO `t_flume_sinkInfo` VALUES ('29d8b5457ba44f46955dfa6eeceeb31b', 'k1', '192.168.15.60', '2019-08-12 17:34:08', '0', '0', '0', '949300', '0', '0', '0', '0', '949280', '73175', '2', '0', '2019-08-12 17:34:18', '2019-08-12 17:35:57');
INSERT INTO `t_flume_sinkInfo` VALUES ('3a4a2f7d7db246f1834aa9c9db1cddfb', 'k1', '192.168.15.60', '2019-08-12 17:31:49', '0', '0', '0', '1124640', '0', '0', '0', '0', '1124620', '66361', '2', '0', '2019-08-12 17:31:56', '2019-08-12 17:33:36');
INSERT INTO `t_flume_sinkInfo` VALUES ('418b8cc6ca19488ba1058fbb83398996', 'k1', '192.168.15.60', '2019-08-12 17:50:51', '0', '0', '0', '558160', '0', '0', '0', '0', '558140', '36490', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:08');
INSERT INTO `t_flume_sinkInfo` VALUES ('5707f37936474b74935574d788587856', 'k1', '192.168.15.60', '2019-08-12 18:11:04', '0', '0', '0', '1693060', '0', '0', '0', '0', '1693040', '108290', '3', '0', '2019-08-12 18:11:11', '2019-08-12 18:14:09');
INSERT INTO `t_flume_sinkInfo` VALUES ('6f100acc44bc45e7ba07ff8f1482cfcd', 'k1', '192.168.15.60', '2019-08-12 17:31:26', '0', '0', '0', '1409720', '0', '0', '0', '0', '1409700', '81139', '2', '0', '2019-08-12 17:31:39', '2019-08-12 17:33:34');
INSERT INTO `t_flume_sinkInfo` VALUES ('727c94b66d104ab8bde132a14330b8c5', 'k1', '192.168.15.60', '2019-08-12 17:34:47', '0', '0', '0', '571980', '0', '0', '0', '0', '571960', '44776', '2', '0', '2019-08-12 17:34:55', '2019-08-12 17:35:56');
INSERT INTO `t_flume_sinkInfo` VALUES ('772e9c1b9fb140bcb978cadbbc648ba4', 'k1', '192.168.15.60', '2019-08-12 18:11:09', '0', '0', '0', '1609680', '0', '0', '0', '0', '1609660', '105427', '3', '0', '2019-08-12 18:11:10', '2019-08-12 18:14:09');
INSERT INTO `t_flume_sinkInfo` VALUES ('7decb3ed90244312b94dba374e473afd', 'k1', '192.168.15.60', '2019-08-12 17:34:12', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '2', '0', '2019-08-12 17:34:20', '2019-08-12 17:34:49');
INSERT INTO `t_flume_sinkInfo` VALUES ('98c61ede014b42cd8db815dbd957e561', 'k1', '192.168.15.60', '2019-08-16 16:01:55', '0', '0', '0', '11267380', '0', '0', '0', '0', '11267360', '446336', '2', '0', '2019-08-16 16:02:13', '2019-08-16 16:15:19');
INSERT INTO `t_flume_sinkInfo` VALUES ('ad7bfc598b6e4427a159208c804c04e8', 'k1', '192.168.15.60', '2019-08-12 17:50:57', '0', '0', '0', '486000', '0', '0', '0', '0', '485980', '32228', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:11');
INSERT INTO `t_flume_sinkInfo` VALUES ('b3a670940c264dad85653230f6dbf815', 'k1', '192.168.15.60', '2019-08-12 17:50:55', '0', '0', '0', '486820', '0', '0', '0', '0', '486800', '34097', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:09');
INSERT INTO `t_flume_sinkInfo` VALUES ('b7f2e75fc4f04c99962f36042312bbc5', 'k1', '192.168.15.60', '2019-08-12 18:12:05', '0', '0', '0', '1020040', '0', '0', '0', '0', '1020020', '65553', '3', '0', '2019-08-12 18:12:08', '2019-08-12 18:14:10');
INSERT INTO `t_flume_sinkInfo` VALUES ('bfc697a51f024b55804e3bf0e7561c7c', 'k1', '192.168.15.60', '2019-08-12 17:30:17', '0', '0', '0', '594900', '0', '0', '0', '0', '594880', '37199', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:31:22');
INSERT INTO `t_flume_sinkInfo` VALUES ('d7a97811d0f84da38a02ac87b2bb0e24', 'k1', '192.168.15.60', '2019-08-12 17:34:04', '0', '0', '0', '1038400', '0', '0', '0', '0', '1038380', '76039', '2', '0', '2019-08-12 17:34:19', '2019-08-12 17:35:55');
INSERT INTO `t_flume_sinkInfo` VALUES ('e040835f3a3f4b4991db44f7ef865313', 'k1', '192.168.15.60', '2019-08-12 17:30:26', '0', '0', '0', '180520', '0', '0', '0', '0', '180500', '16551', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:30:55');
INSERT INTO `t_flume_sinkInfo` VALUES ('f0ddda3982e54c5cb32ea5fde116f687', 'k1', '192.168.15.60', '2019-08-12 17:30:54', '0', '0', '0', '245200', '0', '0', '0', '0', '245180', '16342', '2', '0', '2019-08-12 17:31:05', '2019-08-12 17:31:25');
INSERT INTO `t_flume_sinkInfo` VALUES ('fa29a6d731af42f4a979544ae6e403a1', 'k1', '192.168.15.60', '2019-08-12 17:31:30', '0', '0', '0', '1203280', '0', '0', '0', '0', '1203260', '72068', '2', '0', '2019-08-12 17:31:38', '2019-08-12 17:33:29');

-- ----------------------------
-- Table structure for t_flume_sourcesInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flume_sourcesInfo`;
CREATE TABLE `t_flume_sourcesInfo` (
  `id` varchar(32) NOT NULL,
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名',
  `ip_addr` varchar(255) DEFAULT NULL COMMENT 'Ip地址',
  `current_throughput` varchar(255) DEFAULT NULL COMMENT '当前吞吐量',
  `max_throughput` varchar(255) DEFAULT NULL COMMENT '最大吞吐量',
  `event_count` varchar(255) DEFAULT NULL COMMENT '事件总数',
  `average_throughput` varchar(255) DEFAULT NULL COMMENT '平均吞吐量',
  `run_state` int(4) DEFAULT NULL COMMENT '运行状态[RUNING(1):已开启, CLOSE(2):关闭, PORTMONITORINGEXCEPTION(3):端口监控异常]',
  `delete_flag` int(2) DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='flume监控端口的来源内容';

-- ----------------------------
-- Records of t_flume_sourcesInfo
-- ----------------------------
INSERT INTO `t_flume_sourcesInfo` VALUES ('0e24a6fcd37a460297cf43a65c163d57', 's1', '192.168.15.60', '0', '0', '1614000', '10760', '3', '0', '2019-08-12 18:11:10', '2019-08-12 18:14:09');
INSERT INTO `t_flume_sourcesInfo` VALUES ('1d688326be4640e79dd3aca71b47dc3b', 's1', '192.168.15.60', '0', '0', '953600', '9632', '2', '0', '2019-08-12 17:34:18', '2019-08-12 17:35:57');
INSERT INTO `t_flume_sourcesInfo` VALUES ('1d74a952a85640ed892011854eb5eceb', 's1', '192.168.15.60', '0', '0', '249600', '10847', '2', '0', '2019-08-12 17:31:05', '2019-08-12 17:31:25');
INSERT INTO `t_flume_sourcesInfo` VALUES ('1ec9d5fd09f540838ee1a35e5ef91e86', 's1', '192.168.15.60', '0', '0', '562500', '10817', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:08');
INSERT INTO `t_flume_sourcesInfo` VALUES ('1fc294049c104455a170d7bb7c121634', 's1', '192.168.15.60', '0', '0', '491200', '10233', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:09');
INSERT INTO `t_flume_sourcesInfo` VALUES ('254d81ce06da41cc8cdb909f80ef509b', 's1', '192.168.15.60', '0', '0', '184900', '8804', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:30:55');
INSERT INTO `t_flume_sourcesInfo` VALUES ('2fc7631871f1420988db5cd20a77af53', 's1', '192.168.15.60', '0', '0', '11271700', '14160', '2', '0', '2019-08-16 16:02:12', '2019-08-16 16:15:19');
INSERT INTO `t_flume_sourcesInfo` VALUES ('36d8397f691843a481520dedf4313290', 's1', '192.168.15.60', '0', '0', '1414100', '11590', '2', '0', '2019-08-12 17:31:39', '2019-08-12 17:33:34');
INSERT INTO `t_flume_sourcesInfo` VALUES ('48548f1ce200462c83f470c996477fa3', 's1', '192.168.15.60', '0', '0', '1129000', '11520', '2', '0', '2019-08-12 17:31:56', '2019-08-12 17:33:36');
INSERT INTO `t_flume_sourcesInfo` VALUES ('6f7ad261f03f4b8086b3e42cace8bc5f', 's1', '192.168.15.60', '0', '0', '490300', '10658', '2', '0', '2019-08-12 17:51:20', '2019-08-12 17:52:11');
INSERT INTO `t_flume_sourcesInfo` VALUES ('87b2f63d92ca4d81b2ab869a3dfb73ca', 's1', '192.168.15.60', '0', '0', '2600', '2600', '2', '0', '2019-08-12 17:34:20', '2019-08-12 17:34:49');
INSERT INTO `t_flume_sourcesInfo` VALUES ('ab27ec5567f94dbc8074e3ae52bcfeca', 's1', '192.168.15.60', '0', '0', '1042700', '10025', '2', '0', '2019-08-12 17:34:19', '2019-08-12 17:35:55');
INSERT INTO `t_flume_sourcesInfo` VALUES ('c7c918168d75499fa9f02985ae07c903', 's1', '192.168.15.60', '0', '0', '1697400', '10950', '3', '0', '2019-08-12 18:11:10', '2019-08-12 18:14:09');
INSERT INTO `t_flume_sourcesInfo` VALUES ('d240189ecbcf453085dccc3a717fe9d2', 's1', '192.168.15.60', '0', '0', '576300', '9447', '2', '0', '2019-08-12 17:34:55', '2019-08-12 17:35:56');
INSERT INTO `t_flume_sourcesInfo` VALUES ('dbfc7aae2a9b47c386d32ac1b23ce0a6', 's1', '192.168.15.60', '0', '0', '599200', '11096', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:31:21');
INSERT INTO `t_flume_sourcesInfo` VALUES ('e14df2c2512c47199b4842c081c2a253', 's1', '192.168.15.60', '0', '0', '542700', '10641', '2', '0', '2019-08-12 17:30:35', '2019-08-12 17:31:23');
INSERT INTO `t_flume_sourcesInfo` VALUES ('e8ba5c1cb9054fbebc49002c95d611af', 's1', '192.168.15.60', '0', '0', '1024400', '10897', '3', '0', '2019-08-12 18:12:08', '2019-08-12 18:14:09');
INSERT INTO `t_flume_sourcesInfo` VALUES ('ec451baf37e543c09f4e6ea56685381e', 's1', '192.168.15.60', '0', '0', '1207700', '11286', '2', '0', '2019-08-12 17:31:38', '2019-08-12 17:33:29');

-- ----------------------------
-- Table structure for t_flumeCofig_ChannelInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flumeCofig_ChannelInfo`;
CREATE TABLE `t_flumeCofig_ChannelInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `channelID` (`id`),
  CONSTRAINT `channelID` FOREIGN KEY (`id`) REFERENCES `t_flume_channelInfo` (`id`) ON DELETE CASCADE,
  CONSTRAINT `flumeID` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flumeCofig_ChannelInfo
-- ----------------------------
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('4596106507945', '1f504ce3c58b4eb69ca5e525b11b88aa');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('4596106507945', '21014331753d4b18a79710ae6ded182a');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('4596106507945', '4274064af12b4cbd9a515d719a7cd024');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('4596106507945', '44cde3f7733c46379e70170cfea115e8');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('4596106507945', 'beb58989ccf34e98b4988057d7b4bc76');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', '13ace78bbf2e4cf38ae13b2ceb844315');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', '26c6e2fcc7e5401e8a486d1ab01c51db');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', '4a084f8c58c5471d82da4648f995b893');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', '7f7cf8a06383486cbe004a99d6c1c1a6');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', 'c457f685faac4d808d02daa690a59ac1');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', 'd1d2e8feb2fd4def9d4fa197400f6254');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', 'e701b96bdebf4fe78c4a308459493d6d');
INSERT INTO `t_flumeCofig_ChannelInfo` VALUES ('7019469830973', 'f289a818da25480388e638f23cf40a72');

-- ----------------------------
-- Table structure for t_flumeCofig_sinkInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flumeCofig_sinkInfo`;
CREATE TABLE `t_flumeCofig_sinkInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `sinkID` (`id`),
  CONSTRAINT `flumeID3` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE,
  CONSTRAINT `sinkID` FOREIGN KEY (`id`) REFERENCES `t_flume_sinkInfo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flumeCofig_sinkInfo
-- ----------------------------
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('4596106507945', '418b8cc6ca19488ba1058fbb83398996');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('4596106507945', '5707f37936474b74935574d788587856');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('4596106507945', '6f100acc44bc45e7ba07ff8f1482cfcd');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('4596106507945', 'bfc697a51f024b55804e3bf0e7561c7c');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('4596106507945', 'd7a97811d0f84da38a02ac87b2bb0e24');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', '3a4a2f7d7db246f1834aa9c9db1cddfb');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', '727c94b66d104ab8bde132a14330b8c5');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', '7decb3ed90244312b94dba374e473afd');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', '98c61ede014b42cd8db815dbd957e561');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', 'ad7bfc598b6e4427a159208c804c04e8');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', 'b7f2e75fc4f04c99962f36042312bbc5');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', 'e040835f3a3f4b4991db44f7ef865313');
INSERT INTO `t_flumeCofig_sinkInfo` VALUES ('7019469830973', 'f0ddda3982e54c5cb32ea5fde116f687');

-- ----------------------------
-- Table structure for t_flumeCofig_sourcesInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_flumeCofig_sourcesInfo`;
CREATE TABLE `t_flumeCofig_sourcesInfo` (
  `context_id` bigint(20) DEFAULT NULL,
  `id` varchar(32) DEFAULT NULL,
  UNIQUE KEY `flune_channel` (`context_id`,`id`),
  KEY `sourcesID` (`id`),
  CONSTRAINT `flumeID2` FOREIGN KEY (`context_id`) REFERENCES `t_flume_config` (`context_id`) ON DELETE CASCADE,
  CONSTRAINT `sourcesID` FOREIGN KEY (`id`) REFERENCES `t_flume_sourcesInfo` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_flumeCofig_sourcesInfo
-- ----------------------------
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('4596106507945', '1ec9d5fd09f540838ee1a35e5ef91e86');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('4596106507945', '36d8397f691843a481520dedf4313290');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('4596106507945', 'ab27ec5567f94dbc8074e3ae52bcfeca');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('4596106507945', 'c7c918168d75499fa9f02985ae07c903');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('4596106507945', 'dbfc7aae2a9b47c386d32ac1b23ce0a6');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '1d74a952a85640ed892011854eb5eceb');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '254d81ce06da41cc8cdb909f80ef509b');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '2fc7631871f1420988db5cd20a77af53');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '48548f1ce200462c83f470c996477fa3');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '6f7ad261f03f4b8086b3e42cace8bc5f');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', '87b2f63d92ca4d81b2ab869a3dfb73ca');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', 'd240189ecbcf453085dccc3a717fe9d2');
INSERT INTO `t_flumeCofig_sourcesInfo` VALUES ('7019469830973', 'e8ba5c1cb9054fbebc49002c95d611af');

-- ----------------------------
-- Table structure for t_hm_category
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_category`;
CREATE TABLE `t_hm_category` (
  `product_id` varchar(64) DEFAULT NULL,
  `article_id` varchar(64) DEFAULT NULL,
  `variant_id` varchar(64) DEFAULT NULL,
  `cate_cod` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hm_category
-- ----------------------------

-- ----------------------------
-- Table structure for t_hm_send_record
-- ----------------------------
DROP TABLE IF EXISTS `t_hm_send_record`;
CREATE TABLE `t_hm_send_record` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `end_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_hm_send_record
-- ----------------------------
INSERT INTO `t_hm_send_record` VALUES ('1', 'bi.zip', 'e://test01/2019_08_01/bi.zip', '2019-08-01 19:34:25.534000', '2019-08-01 19:34:26.369000');
INSERT INTO `t_hm_send_record` VALUES ('2', 'bi.zip', 'e://test01/2019_08_01/bi.zip', '2019-08-01 19:54:02.400000', '2019-08-01 19:54:03.213000');
INSERT INTO `t_hm_send_record` VALUES ('3', 'bi.zip', 'e://test01/2019_08_01/bi.zip', '2019-08-01 20:00:27.172000', '2019-08-01 20:00:28.215000');
INSERT INTO `t_hm_send_record` VALUES ('4', 'bi.zip', 'e://test01/2019_08_01/bi.zip', '2019-08-01 20:12:30.001000', '2019-08-01 20:12:30.910000');
INSERT INTO `t_hm_send_record` VALUES ('5', 'bi.zip', 'E:/copy2019_08_05/bi.zip', '2019-08-05 10:03:13.968000', '2019-08-05 10:03:14.779000');
INSERT INTO `t_hm_send_record` VALUES ('6', 'bi.zip', 'E:/copy2019_08_05/bi.zip', '2019-08-05 10:03:51.175000', '2019-08-05 10:03:51.732000');
INSERT INTO `t_hm_send_record` VALUES ('7', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:04:57.902000', '2019-08-05 10:04:58.686000');
INSERT INTO `t_hm_send_record` VALUES ('8', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:05:30.404000', '2019-08-05 10:05:31.173000');
INSERT INTO `t_hm_send_record` VALUES ('9', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:09:49.285000', '2019-08-05 10:09:50.117000');
INSERT INTO `t_hm_send_record` VALUES ('10', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:10:16.645000', '2019-08-05 10:10:17.168000');
INSERT INTO `t_hm_send_record` VALUES ('11', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:10:27.854000', '2019-08-05 10:10:28.368000');
INSERT INTO `t_hm_send_record` VALUES ('12', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:11:26.493000', '2019-08-05 10:11:27.208000');
INSERT INTO `t_hm_send_record` VALUES ('13', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:12:05.166000', '2019-08-05 10:12:05.705000');
INSERT INTO `t_hm_send_record` VALUES ('14', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:16:08.133000', '2019-08-05 10:16:08.905000');
INSERT INTO `t_hm_send_record` VALUES ('15', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:16:14.306000', '2019-08-05 10:16:14.792000');
INSERT INTO `t_hm_send_record` VALUES ('16', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:18:58.808000', '2019-08-05 10:18:59.527000');
INSERT INTO `t_hm_send_record` VALUES ('17', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:19:41.305000', '2019-08-05 10:19:41.985000');
INSERT INTO `t_hm_send_record` VALUES ('18', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:19:50.893000', '2019-08-05 10:19:51.403000');
INSERT INTO `t_hm_send_record` VALUES ('19', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:27:19.905000', '2019-08-05 10:27:20.650000');
INSERT INTO `t_hm_send_record` VALUES ('20', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:28:02.293000', '2019-08-05 10:28:02.802000');
INSERT INTO `t_hm_send_record` VALUES ('21', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:32:36.108000', '2019-08-05 10:32:36.820000');
INSERT INTO `t_hm_send_record` VALUES ('22', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:32:50.301000', '2019-08-05 10:32:50.838000');
INSERT INTO `t_hm_send_record` VALUES ('23', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:34:42.585000', '2019-08-05 10:34:43.372000');
INSERT INTO `t_hm_send_record` VALUES ('24', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:34:51.104000', '2019-08-05 10:34:51.709000');
INSERT INTO `t_hm_send_record` VALUES ('25', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:35:36.828000', '2019-08-05 10:35:37.631000');
INSERT INTO `t_hm_send_record` VALUES ('26', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:35:47.807000', '2019-08-05 10:35:48.424000');
INSERT INTO `t_hm_send_record` VALUES ('27', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:36:43.461000', '2019-08-05 10:36:44.139000');
INSERT INTO `t_hm_send_record` VALUES ('28', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:37:54.018000', '2019-08-05 10:37:54.696000');
INSERT INTO `t_hm_send_record` VALUES ('29', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:41:21.564000', '2019-08-05 10:41:22.380000');
INSERT INTO `t_hm_send_record` VALUES ('30', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:41:36.831000', '2019-08-05 10:41:37.478000');
INSERT INTO `t_hm_send_record` VALUES ('31', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:42:31.656000', '2019-08-05 10:42:32.338000');
INSERT INTO `t_hm_send_record` VALUES ('32', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:45:01.999000', '2019-08-05 10:45:03.804000');
INSERT INTO `t_hm_send_record` VALUES ('33', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:45:32.265000', '2019-08-05 10:45:33.052000');
INSERT INTO `t_hm_send_record` VALUES ('34', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:46:30.141000', '2019-08-05 10:46:30.958000');
INSERT INTO `t_hm_send_record` VALUES ('35', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:47:29.342000', '2019-08-05 10:47:29.962000');
INSERT INTO `t_hm_send_record` VALUES ('36', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:48:08.960000', '2019-08-05 10:48:09.657000');
INSERT INTO `t_hm_send_record` VALUES ('37', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:51:20.865000', '2019-08-05 10:51:21.653000');
INSERT INTO `t_hm_send_record` VALUES ('38', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:51:38.438000', '2019-08-05 10:51:39.075000');
INSERT INTO `t_hm_send_record` VALUES ('39', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:52:14.417000', '2019-08-05 10:52:15.326000');
INSERT INTO `t_hm_send_record` VALUES ('40', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:56:52.749000', '2019-08-05 10:56:53.596000');
INSERT INTO `t_hm_send_record` VALUES ('41', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 10:57:10.087000', '2019-08-05 10:57:10.702000');
INSERT INTO `t_hm_send_record` VALUES ('42', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:13:58.214000', '2019-08-05 11:13:59.025000');
INSERT INTO `t_hm_send_record` VALUES ('43', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:14:04.819000', '2019-08-05 11:14:05.385000');
INSERT INTO `t_hm_send_record` VALUES ('44', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:14:38.560000', '2019-08-05 11:14:39.166000');
INSERT INTO `t_hm_send_record` VALUES ('45', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:16:54.595000', '2019-08-05 11:16:55.387000');
INSERT INTO `t_hm_send_record` VALUES ('46', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:17:12.796000', '2019-08-05 11:17:13.435000');
INSERT INTO `t_hm_send_record` VALUES ('47', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:27:15.113000', '2019-08-05 11:27:15.963000');
INSERT INTO `t_hm_send_record` VALUES ('48', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:27:25.510000', '2019-08-05 11:27:26.126000');
INSERT INTO `t_hm_send_record` VALUES ('49', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:30:02.992000', '2019-08-05 11:30:03.880000');
INSERT INTO `t_hm_send_record` VALUES ('50', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:30:13.343000', '2019-08-05 11:30:13.958000');
INSERT INTO `t_hm_send_record` VALUES ('51', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:31:22.559000', '2019-08-05 11:31:23.384000');
INSERT INTO `t_hm_send_record` VALUES ('52', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:31:31.855000', '2019-08-05 11:31:32.495000');
INSERT INTO `t_hm_send_record` VALUES ('53', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:32:22.946000', '2019-08-05 11:32:23.634000');
INSERT INTO `t_hm_send_record` VALUES ('54', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:35:03.202000', '2019-08-05 11:35:03.964000');
INSERT INTO `t_hm_send_record` VALUES ('55', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:35:25.215000', '2019-08-05 11:35:25.816000');
INSERT INTO `t_hm_send_record` VALUES ('56', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:35:35.879000', '2019-08-05 11:35:36.471000');
INSERT INTO `t_hm_send_record` VALUES ('57', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:36:38.788000', '2019-08-05 11:36:39.589000');
INSERT INTO `t_hm_send_record` VALUES ('58', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:36:42.687000', '2019-08-05 11:36:43.161000');
INSERT INTO `t_hm_send_record` VALUES ('59', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:46:37.068000', '2019-08-05 11:46:37.895000');
INSERT INTO `t_hm_send_record` VALUES ('60', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:46:46.384000', '2019-08-05 11:46:47.079000');
INSERT INTO `t_hm_send_record` VALUES ('61', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:47:19.684000', '2019-08-05 11:47:20.295000');
INSERT INTO `t_hm_send_record` VALUES ('62', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:47:50.615000', '2019-08-05 11:47:51.487000');
INSERT INTO `t_hm_send_record` VALUES ('63', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:48:05.698000', '2019-08-05 11:48:06.344000');
INSERT INTO `t_hm_send_record` VALUES ('64', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:50:18.680000', '2019-08-05 11:50:19.522000');
INSERT INTO `t_hm_send_record` VALUES ('65', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:50:22.576000', '2019-08-05 11:50:23.027000');
INSERT INTO `t_hm_send_record` VALUES ('66', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:51:47.727000', '2019-08-05 11:51:48.519000');
INSERT INTO `t_hm_send_record` VALUES ('67', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:53:05.167000', '2019-08-05 11:53:06.001000');
INSERT INTO `t_hm_send_record` VALUES ('68', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:56:23.453000', '2019-08-05 11:56:24.292000');
INSERT INTO `t_hm_send_record` VALUES ('69', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 11:56:31.946000', '2019-08-05 11:56:32.485000');
INSERT INTO `t_hm_send_record` VALUES ('70', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:00:17.069000', '2019-08-05 12:00:17.938000');
INSERT INTO `t_hm_send_record` VALUES ('71', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:01:29.408000', '2019-08-05 12:01:30.074000');
INSERT INTO `t_hm_send_record` VALUES ('72', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:02:03.629000', '2019-08-05 12:02:04.273000');
INSERT INTO `t_hm_send_record` VALUES ('73', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:02:51.547000', '2019-08-05 12:02:52.426000');
INSERT INTO `t_hm_send_record` VALUES ('74', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:03:05.010000', '2019-08-05 12:03:05.607000');
INSERT INTO `t_hm_send_record` VALUES ('75', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:05:19.034000', '2019-08-05 12:05:19.815000');
INSERT INTO `t_hm_send_record` VALUES ('76', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:05:26.580000', '2019-08-05 12:05:27.224000');
INSERT INTO `t_hm_send_record` VALUES ('77', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 12:05:33.132000', '2019-08-05 12:05:33.724000');
INSERT INTO `t_hm_send_record` VALUES ('78', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:47:03.569000', '2019-08-05 13:47:04.186000');
INSERT INTO `t_hm_send_record` VALUES ('79', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:50:24.099000', '2019-08-05 13:50:32.788000');
INSERT INTO `t_hm_send_record` VALUES ('80', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:52:31.623000', '2019-08-05 13:52:32.428000');
INSERT INTO `t_hm_send_record` VALUES ('81', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:53:41.280000', '2019-08-05 13:53:42.102000');
INSERT INTO `t_hm_send_record` VALUES ('82', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:56:34.984000', '2019-08-05 13:56:35.927000');
INSERT INTO `t_hm_send_record` VALUES ('83', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:58:05.564000', '2019-08-05 13:58:06.344000');
INSERT INTO `t_hm_send_record` VALUES ('84', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 13:59:50.207000', '2019-08-05 13:59:50.877000');
INSERT INTO `t_hm_send_record` VALUES ('85', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:03:11.749000', '2019-08-05 14:03:18.465000');
INSERT INTO `t_hm_send_record` VALUES ('86', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:04:38.408000', '2019-08-05 14:04:39.074000');
INSERT INTO `t_hm_send_record` VALUES ('87', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:06:54.746000', '2019-08-05 14:06:55.542000');
INSERT INTO `t_hm_send_record` VALUES ('88', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:07:00.063000', '2019-08-05 14:07:00.554000');
INSERT INTO `t_hm_send_record` VALUES ('89', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:14:07.927000', '2019-08-05 14:14:08.752000');
INSERT INTO `t_hm_send_record` VALUES ('90', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:26:20.310000', '2019-08-05 14:26:21.098000');
INSERT INTO `t_hm_send_record` VALUES ('91', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:32:37.720000', '2019-08-05 14:32:38.532000');
INSERT INTO `t_hm_send_record` VALUES ('92', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:48:18.420000', '2019-08-05 14:48:19.278000');
INSERT INTO `t_hm_send_record` VALUES ('93', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:52:45.490000', '2019-08-05 14:52:46.367000');
INSERT INTO `t_hm_send_record` VALUES ('94', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:56:34.084000', '2019-08-05 14:56:34.908000');
INSERT INTO `t_hm_send_record` VALUES ('95', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:56:38.055000', '2019-08-05 14:56:38.538000');
INSERT INTO `t_hm_send_record` VALUES ('96', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:56:40.874000', '2019-08-05 14:56:41.369000');
INSERT INTO `t_hm_send_record` VALUES ('97', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:57:10.536000', '2019-08-05 14:57:11.161000');
INSERT INTO `t_hm_send_record` VALUES ('98', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 14:58:24.243000', '2019-08-05 14:58:25.048000');
INSERT INTO `t_hm_send_record` VALUES ('99', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:00:00.118000', '2019-08-05 15:00:00.938000');
INSERT INTO `t_hm_send_record` VALUES ('100', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:00:14.943000', '2019-08-05 15:00:15.580000');
INSERT INTO `t_hm_send_record` VALUES ('101', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:01:17.050000', '2019-08-05 15:01:17.718000');
INSERT INTO `t_hm_send_record` VALUES ('102', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:05:28.359000', '2019-08-05 15:05:29.427000');
INSERT INTO `t_hm_send_record` VALUES ('103', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:06:15.125000', '2019-08-05 15:06:16.065000');
INSERT INTO `t_hm_send_record` VALUES ('104', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:07:13.867000', '2019-08-05 15:07:14.849000');
INSERT INTO `t_hm_send_record` VALUES ('105', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:11:33.393000', '2019-08-05 15:11:34.431000');
INSERT INTO `t_hm_send_record` VALUES ('106', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:24:49.897000', '2019-08-05 15:24:50.719000');
INSERT INTO `t_hm_send_record` VALUES ('107', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:25:02.851000', '2019-08-05 15:25:03.499000');
INSERT INTO `t_hm_send_record` VALUES ('108', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:37:15.259000', '2019-08-05 15:37:16.081000');
INSERT INTO `t_hm_send_record` VALUES ('109', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:37:58.400000', '2019-08-05 15:37:59.066000');
INSERT INTO `t_hm_send_record` VALUES ('110', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:38:52.290000', '2019-08-05 15:38:53.055000');
INSERT INTO `t_hm_send_record` VALUES ('111', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:47:00.113000', '2019-08-05 15:47:00.924000');
INSERT INTO `t_hm_send_record` VALUES ('112', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:47:11.756000', '2019-08-05 15:47:12.404000');
INSERT INTO `t_hm_send_record` VALUES ('113', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:48:03.661000', '2019-08-05 15:48:04.333000');
INSERT INTO `t_hm_send_record` VALUES ('114', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:49:59.707000', '2019-08-05 15:50:00.525000');
INSERT INTO `t_hm_send_record` VALUES ('115', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:51:10.708000', '2019-08-05 15:51:11.419000');
INSERT INTO `t_hm_send_record` VALUES ('116', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:53:27.686000', '2019-08-05 15:53:28.527000');
INSERT INTO `t_hm_send_record` VALUES ('117', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:53:43.326000', '2019-08-05 15:53:43.991000');
INSERT INTO `t_hm_send_record` VALUES ('118', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:56:26.495000', '2019-08-05 15:56:27.384000');
INSERT INTO `t_hm_send_record` VALUES ('119', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 15:56:32.557000', '2019-08-05 15:56:33.209000');
INSERT INTO `t_hm_send_record` VALUES ('120', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:08:38.162000', '2019-08-05 16:08:39.005000');
INSERT INTO `t_hm_send_record` VALUES ('121', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:09:12.224000', '2019-08-05 16:09:12.947000');
INSERT INTO `t_hm_send_record` VALUES ('122', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:11:13.532000', '2019-08-05 16:11:14.353000');
INSERT INTO `t_hm_send_record` VALUES ('123', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:11:37.010000', '2019-08-05 16:11:37.624000');
INSERT INTO `t_hm_send_record` VALUES ('124', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:13:00.506000', '2019-08-05 16:13:01.319000');
INSERT INTO `t_hm_send_record` VALUES ('125', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:14:07.033000', '2019-08-05 16:14:07.757000');
INSERT INTO `t_hm_send_record` VALUES ('126', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 16:53:39.887000', '2019-08-05 16:53:40.594000');
INSERT INTO `t_hm_send_record` VALUES ('127', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:15:23.063000', '2019-08-05 17:15:23.929000');
INSERT INTO `t_hm_send_record` VALUES ('128', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:15:58.257000', '2019-08-05 17:15:58.895000');
INSERT INTO `t_hm_send_record` VALUES ('129', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:18:48.540000', '2019-08-05 17:18:49.307000');
INSERT INTO `t_hm_send_record` VALUES ('130', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:22:03.842000', '2019-08-05 17:22:04.462000');
INSERT INTO `t_hm_send_record` VALUES ('131', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:22:40.241000', '2019-08-05 17:22:40.864000');
INSERT INTO `t_hm_send_record` VALUES ('132', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:25:27.711000', '2019-08-05 17:25:28.642000');
INSERT INTO `t_hm_send_record` VALUES ('133', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:26:40.641000', '2019-08-05 17:26:41.329000');
INSERT INTO `t_hm_send_record` VALUES ('134', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:26:59.427000', '2019-08-05 17:27:00.119000');
INSERT INTO `t_hm_send_record` VALUES ('135', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:30:09.731000', '2019-08-05 17:30:10.698000');
INSERT INTO `t_hm_send_record` VALUES ('136', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:31:43.510000', '2019-08-05 17:31:44.310000');
INSERT INTO `t_hm_send_record` VALUES ('137', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:31:50.078000', '2019-08-05 17:31:50.779000');
INSERT INTO `t_hm_send_record` VALUES ('138', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:35:23.022000', '2019-08-05 17:35:24.058000');
INSERT INTO `t_hm_send_record` VALUES ('139', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:36:16.644000', '2019-08-05 17:36:17.307000');
INSERT INTO `t_hm_send_record` VALUES ('140', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:36:41.512000', '2019-08-05 17:36:42.222000');
INSERT INTO `t_hm_send_record` VALUES ('141', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:36:59.925000', '2019-08-05 17:37:00.638000');
INSERT INTO `t_hm_send_record` VALUES ('142', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:37:21.106000', '2019-08-05 17:37:21.760000');
INSERT INTO `t_hm_send_record` VALUES ('143', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:37:32.465000', '2019-08-05 17:37:33.161000');
INSERT INTO `t_hm_send_record` VALUES ('144', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:37:38.170000', '2019-08-05 17:37:38.811000');
INSERT INTO `t_hm_send_record` VALUES ('145', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:37:43.540000', '2019-08-05 17:37:44.155000');
INSERT INTO `t_hm_send_record` VALUES ('146', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:37:53.540000', '2019-08-05 17:37:54.184000');
INSERT INTO `t_hm_send_record` VALUES ('147', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:38:01.250000', '2019-08-05 17:38:01.924000');
INSERT INTO `t_hm_send_record` VALUES ('148', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:39:24.773000', '2019-08-05 17:39:25.833000');
INSERT INTO `t_hm_send_record` VALUES ('149', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:39:32.572000', '2019-08-05 17:39:33.207000');
INSERT INTO `t_hm_send_record` VALUES ('150', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:39:57.218000', '2019-08-05 17:39:57.836000');
INSERT INTO `t_hm_send_record` VALUES ('151', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:40:08.659000', '2019-08-05 17:40:09.351000');
INSERT INTO `t_hm_send_record` VALUES ('152', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:40:23.353000', '2019-08-05 17:40:23.982000');
INSERT INTO `t_hm_send_record` VALUES ('153', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:49:44.165000', '2019-08-05 17:49:45.057000');
INSERT INTO `t_hm_send_record` VALUES ('154', 'bi.zip', 'E:/copy/2019_08_05/bi.zip', '2019-08-05 17:49:54.477000', '2019-08-05 17:49:55.167000');
INSERT INTO `t_hm_send_record` VALUES ('155', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:52:34.693000', '2019-08-06 09:52:35.796000');
INSERT INTO `t_hm_send_record` VALUES ('156', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:53:30.321000', '2019-08-06 09:53:30.973000');
INSERT INTO `t_hm_send_record` VALUES ('157', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:54:11.585000', '2019-08-06 09:54:12.371000');
INSERT INTO `t_hm_send_record` VALUES ('158', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:56:10.311000', '2019-08-06 09:56:11.426000');
INSERT INTO `t_hm_send_record` VALUES ('159', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:56:43.880000', '2019-08-06 09:56:44.613000');
INSERT INTO `t_hm_send_record` VALUES ('160', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:58:05.335000', '2019-08-06 09:58:06.118000');
INSERT INTO `t_hm_send_record` VALUES ('161', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 09:59:32.497000', '2019-08-06 09:59:33.274000');
INSERT INTO `t_hm_send_record` VALUES ('162', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:00:09.206000', '2019-08-06 10:00:09.838000');
INSERT INTO `t_hm_send_record` VALUES ('163', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:11:11.811000', '2019-08-06 10:11:12.577000');
INSERT INTO `t_hm_send_record` VALUES ('164', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:11:53.712000', '2019-08-06 10:11:54.358000');
INSERT INTO `t_hm_send_record` VALUES ('165', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:12:21.202000', '2019-08-06 10:12:21.958000');
INSERT INTO `t_hm_send_record` VALUES ('166', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:26:35.968000', '2019-08-06 10:26:36.746000');
INSERT INTO `t_hm_send_record` VALUES ('167', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:33:41.043000', '2019-08-06 10:33:41.800000');
INSERT INTO `t_hm_send_record` VALUES ('168', 'bi.zip', 'E:/copy/2019_08_06/bi.zip', '2019-08-06 10:34:47.552000', '2019-08-06 10:34:48.309000');

-- ----------------------------
-- Table structure for t_mission
-- ----------------------------
DROP TABLE IF EXISTS `t_mission`;
CREATE TABLE `t_mission` (
  `mission_id` bigint(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '计划命名',
  `status` int(4) DEFAULT '1' COMMENT '任务状态[common(1):正常, forbid(2):禁用, deleted(3):删除]',
  `mission_type` int(4) DEFAULT '0' COMMENT '执行计划类型,[TIMING(101):定时, REALTIME(102):实时, DELAY(103):延时]',
  `app_id` varchar(50) DEFAULT NULL COMMENT '对接应用程序标识',
  `outer_id` varchar(30) DEFAULT NULL COMMENT '对接应用程序计划标识ID',
  `etl_task_id` int(6) DEFAULT NULL COMMENT 'etl任务ID',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `outer_id` (`outer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='执行计划';

-- ----------------------------
-- Records of t_mission
-- ----------------------------
INSERT INTO `t_mission` VALUES ('6548874870707453955', '5523147270-3', '1', '101', '5523147270', '5523147270-3', '3', '2019-06-24 18:50:41', '2019-06-24 18:50:41');
INSERT INTO `t_mission` VALUES ('6548882406093357059', '5523147270-14', '1', '101', '5523147270', '5523147270-14', '3', '2019-06-24 19:20:38', '2019-06-24 19:20:38');
INSERT INTO `t_mission` VALUES ('6548897995528601603', '5523147270-12', '1', '101', '5523147270', '5523147270-12', '2', '2019-06-24 20:22:35', '2019-06-24 20:22:35');
INSERT INTO `t_mission` VALUES ('6548898116861427715', '5523147270-16', '1', '101', '5523147270', '5523147270-16', '2', '2019-06-24 20:23:04', '2019-06-24 20:23:04');
INSERT INTO `t_mission` VALUES ('6549468932196007939', '5523147270-17', '1', '101', '5523147270', '5523147270-17', '3', '2019-06-26 10:11:16', '2019-06-26 10:11:16');
INSERT INTO `t_mission` VALUES ('6549471252195573763', '5523147270-18', '1', '101', '5523147270', '5523147270-18', '2', '2019-06-26 10:20:30', '2019-06-26 10:20:30');
INSERT INTO `t_mission` VALUES ('6549547325289660419', '5523147270-19', '1', '101', '5523147270', '5523147270-19', '2', '2019-06-26 15:22:47', '2019-06-26 15:22:47');
INSERT INTO `t_mission` VALUES ('6549548568762384387', '5523147270-20', '1', '101', '5523147270', '5523147270-20', '3', '2019-06-26 15:27:44', '2019-06-26 15:27:44');
INSERT INTO `t_mission` VALUES ('6549561214890934275', '5523147270-21', '1', '101', '5523147270', '5523147270-21', '2', '2019-06-26 16:17:59', '2019-06-26 16:17:59');
INSERT INTO `t_mission` VALUES ('6549561257681223683', '5523147270-22', '1', '101', '5523147270', '5523147270-22', '3', '2019-06-26 16:18:09', '2019-06-26 16:18:09');

-- ----------------------------
-- Table structure for t_mission_context
-- ----------------------------
DROP TABLE IF EXISTS `t_mission_context`;
CREATE TABLE `t_mission_context` (
  `context_id` bigint(20) NOT NULL,
  `mission_id` bigint(20) NOT NULL,
  `spark_app_id` varchar(200) DEFAULT NULL COMMENT 'spark resource application id',
  `context_state` int(11) NOT NULL DEFAULT '1' COMMENT '任务上下文状态[NEW(1):新增, SUBMITED(2):已提交, DELETE(100):删除, FAIL(200):失败]',
  `schedule_state` int(4) NOT NULL DEFAULT '1' COMMENT '调度状态[NEW(1):新增, FAIL(2):失败, DELETE(3):删除]',
  `spark_app_state` varchar(20) DEFAULT NULL COMMENT 'NEW, NEW_SAVING, SUBMITTED, ACCEPTED, RUNNING, FINISHED, FAILED, KILLED',
  `etl_task_id` int(6) DEFAULT NULL COMMENT 'etl任务ID',
  `class_name` varchar(200) DEFAULT NULL COMMENT 'job类',
  `cron_expression` varchar(50) DEFAULT NULL COMMENT '执行时间描述',
  `job_name` varchar(100) DEFAULT NULL COMMENT '调度任务名称',
  `job_group` varchar(100) DEFAULT NULL COMMENT '调度任务组别',
  `job_status` varchar(30) DEFAULT '' COMMENT '任务TriggerState状态',
  `trigger_name` varchar(100) DEFAULT NULL COMMENT '任务触发器名称',
  `trigger_group` varchar(100) DEFAULT NULL COMMENT '任务触发器组别',
  `email` varchar(200) DEFAULT NULL COMMENT '告警邮箱',
  `send` int(6) DEFAULT NULL COMMENT '是否开启告警功能1-开启，2-关闭',
  `restart` int(6) DEFAULT '2' COMMENT '是否自动重启1-开启，2-关闭',
  `retries` int(6) DEFAULT '1' COMMENT '尝试重启次数',
  `pause` tinyint(1) DEFAULT '0' COMMENT '是否暂停[CONFIRM(1):暂停, DENY(0):不暂停]',
  `enable` tinyint(1) DEFAULT '1' COMMENT '可用[Y(1):可用, N(0):不能用]',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `target_table` varchar(100) DEFAULT NULL COMMENT '存储目标表',
  `report_code` varchar(100) DEFAULT NULL COMMENT '报表编码',
  `priority` int(4) DEFAULT NULL COMMENT '优先级',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`context_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='执行计划上下文';

-- ----------------------------
-- Records of t_mission_context
-- ----------------------------
INSERT INTO `t_mission_context` VALUES ('6548874870736814083', '6548874870707453955', 'application_1555225485595_2269', '2', '1', 'FAILED', '3', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-3_6548874870707453955', 'ETL_GROUP_5523147270-3_6548874870707453955', null, 'ETL_TRIGGER_NAME_5523147270-3_6548874870707453955', 'ETL_TRIGGER_GROUP_5523147270-3_6548874870707453955', null, null, null, '3', '0', '1', '', 'category_sales_5523147270_3_6548874870707453955_2019_06_24_18_50_41', 'qbi_category_sales', null, '2019-06-24 18:50:41', '2019-06-24 19:44:52');
INSERT INTO `t_mission_context` VALUES ('6548882406114328579', '6548882406093357059', 'application_1555225485595_2267', '2', '1', 'FAILED', '3', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-14_6548882406093357059', 'ETL_GROUP_5523147270-14_6548882406093357059', null, 'ETL_TRIGGER_NAME_5523147270-14_6548882406093357059', 'ETL_TRIGGER_GROUP_5523147270-14_6548882406093357059', null, null, null, '3', '0', '1', '', 'category_sales_5523147270_14_6548882406093357059_2019_06_24_19_20_37', 'qbi_category_sales', null, '2019-06-24 19:20:38', '2019-06-24 19:20:52');
INSERT INTO `t_mission_context` VALUES ('6548897995587321859', '6548897995528601603', 'application_1555225485595_2270', '2', '1', 'FINISHED', '2', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-12_6548897995528601603', 'ETL_GROUP_5523147270-12_6548897995528601603', null, 'ETL_TRIGGER_NAME_5523147270-12_6548897995528601603', 'ETL_TRIGGER_GROUP_5523147270-12_6548897995528601603', null, null, null, '3', '0', '1', '', 'shop_card_intersect_5523147270_12_6548897995528601603_2019_06_24_20_22_34', 'qbi_shop_card_intersect', null, '2019-06-24 20:22:35', '2019-06-24 20:22:52');
INSERT INTO `t_mission_context` VALUES ('6548898116886593539', '6548898116861427715', 'application_1555225485595_2271', '2', '1', 'FINISHED', '2', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-16_6548898116861427715', 'ETL_GROUP_5523147270-16_6548898116861427715', null, 'ETL_TRIGGER_NAME_5523147270-16_6548898116861427715', 'ETL_TRIGGER_GROUP_5523147270-16_6548898116861427715', null, null, null, '3', '0', '1', '', 'shop_card_intersect_5523147270_16_6548898116861427715_2019_06_24_20_23_03', 'qbi_shop_card_intersect', null, '2019-06-24 20:23:04', '2019-06-24 20:23:52');
INSERT INTO `t_mission_context` VALUES ('6549468932246339587', '6549468932196007939', 'application_1555225485595_2396', '2', '1', 'FAILED', '3', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-17_6549468932196007939', 'ETL_GROUP_5523147270-17_6549468932196007939', null, 'ETL_TRIGGER_NAME_5523147270-17_6549468932196007939', 'ETL_TRIGGER_GROUP_5523147270-17_6549468932196007939', null, null, null, '3', '0', '1', '', 'category_sales_5523147270_17_6549468932196007939_2019_06_26_10_11_16', 'qbi_category_sales', null, '2019-06-26 10:11:16', '2019-06-26 10:11:37');
INSERT INTO `t_mission_context` VALUES ('6549471252233322499', '6549471252195573763', 'application_1555225485595_2397', '2', '1', 'FINISHED', '2', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-18_6549471252195573763', 'ETL_GROUP_5523147270-18_6549471252195573763', null, 'ETL_TRIGGER_NAME_5523147270-18_6549471252195573763', 'ETL_TRIGGER_GROUP_5523147270-18_6549471252195573763', null, null, null, '3', '0', '1', '', 'shop_card_intersect_5523147270_18_6549471252195573763_2019_06_26_10_20_29', 'qbi_shop_card_intersect', null, '2019-06-26 10:20:30', '2019-06-26 10:20:37');
INSERT INTO `t_mission_context` VALUES ('6549547325323214851', '6549547325289660419', 'application_1555225485595_2401', '2', '1', 'NONE', '2', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-19_6549547325289660419', 'ETL_GROUP_5523147270-19_6549547325289660419', null, 'ETL_TRIGGER_NAME_5523147270-19_6549547325289660419', 'ETL_TRIGGER_GROUP_5523147270-19_6549547325289660419', null, null, null, '3', '0', '1', '', 'shop_card_intersect_5523147270_19_6549547325289660419_2019_06_26_15_22_47', 'qbi_shop_card_intersect', null, '2019-06-26 15:22:47', '2019-06-26 15:32:58');
INSERT INTO `t_mission_context` VALUES ('6549548568779161603', '6549548568762384387', 'application_1555225485595_2400', '2', '1', 'FAILED', '3', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-20_6549548568762384387', 'ETL_GROUP_5523147270-20_6549548568762384387', null, 'ETL_TRIGGER_NAME_5523147270-20_6549548568762384387', 'ETL_TRIGGER_GROUP_5523147270-20_6549548568762384387', null, null, null, '3', '0', '1', '', 'category_sales_5523147270_20_6549548568762384387_2019_06_26_15_27_43', 'qbi_category_sales', null, '2019-06-26 15:27:44', '2019-06-26 15:31:28');
INSERT INTO `t_mission_context` VALUES ('6549561214937071619', '6549561214890934275', 'application_1555225485595_2403', '2', '1', 'FINISHED', '2', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-21_6549561214890934275', 'ETL_GROUP_5523147270-21_6549561214890934275', null, 'ETL_TRIGGER_NAME_5523147270-21_6549561214890934275', 'ETL_TRIGGER_GROUP_5523147270-21_6549561214890934275', null, null, null, '3', '0', '1', '', 'shop_card_intersect_5523147270_21_6549561214890934275_2019_06_26_16_17_58', 'qbi_shop_card_intersect', null, '2019-06-26 16:17:59', '2019-06-26 16:19:58');
INSERT INTO `t_mission_context` VALUES ('6549561257698000899', '6549561257681223683', 'application_1555225485595_2402', '2', '1', 'FAILED', '3', 'com.qmtec.stem.job.SparkJob', '', 'ETL_JOB_5523147270-22_6549561257681223683', 'ETL_GROUP_5523147270-22_6549561257681223683', null, 'ETL_TRIGGER_NAME_5523147270-22_6549561257681223683', 'ETL_TRIGGER_GROUP_5523147270-22_6549561257681223683', null, null, null, '3', '0', '1', '', 'category_sales_5523147270_22_6549561257681223683_2019_06_26_16_18_08', 'qbi_category_sales', null, '2019-06-26 16:18:09', '2019-06-26 16:18:28');

-- ----------------------------
-- Table structure for t_report_type
-- ----------------------------
DROP TABLE IF EXISTS `t_report_type`;
CREATE TABLE `t_report_type` (
  `report_id` int(6) NOT NULL AUTO_INCREMENT,
  `report_code` varchar(100) NOT NULL,
  `report_name` varchar(200) DEFAULT NULL COMMENT '计划命名',
  `status` int(4) DEFAULT '1' COMMENT '任务状态[common(1):正常, forbid(2):禁用, deleted(3):删除]',
  `etl_task_id` int(6) DEFAULT NULL COMMENT 'etl任务ID',
  `task_args_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '运行参数集ID',
  `report_template` varchar(100) DEFAULT NULL COMMENT '存储命名模板',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `report_class` varchar(100) DEFAULT NULL COMMENT '存储运行的类名',
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `type_code` (`report_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='报表类型';

-- ----------------------------
-- Records of t_report_type
-- ----------------------------
INSERT INTO `t_report_type` VALUES ('1', 'qbi_shop_card_intersect', '店铺领卡交叉分析', '1', '2', '1003', 'shop_card_intersect_<flag>_<time>', '2019-05-21 00:14:41', '2019-06-04 12:24:09', 'QBI_CategoryBuyIntersect');
INSERT INTO `t_report_type` VALUES ('2', 'qbi_category_sales', '获取品类销售分析', '1', '3', '1004', 'category_sales_<flag>_<time>', '2019-05-30 18:36:53', '2019-06-04 12:24:11', 'QBI_MemberTransform');

-- ----------------------------
-- Table structure for t_schema_task_args
-- ----------------------------
DROP TABLE IF EXISTS `t_schema_task_args`;
CREATE TABLE `t_schema_task_args` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `args_name` varchar(50) NOT NULL COMMENT '参数集合名字',
  `args_alias` varchar(50) NOT NULL DEFAULT '' COMMENT '参数集名字中文',
  `args_template` varchar(2000) DEFAULT '' COMMENT '参数模板',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务参数集合表';

-- ----------------------------
-- Records of t_schema_task_args
-- ----------------------------
INSERT INTO `t_schema_task_args` VALUES ('1003', '店铺领卡交叉分析', 'shop_card_intersect', 'ArgsTem(db_name,channel_ype,start_ime,end_ime,table_name) ::= \\\"<db_name> <channel_ype> <start_ime> <end_ime> <table_name>\\\"', '2019-05-23 19:35:21', '2019-05-23 19:35:23');
INSERT INTO `t_schema_task_args` VALUES ('1004', '品类销售分析', 'cate_sale', 'ArgsTem(db_name,channel_ype,shop_no,classificate_type,start_ime,end_ime,table_name) ::= \\\"<db_name> <channel_ype> <shop_no> <classificate_type> <start_ime> <end_ime> <table_name>\\\"', '2019-06-04 11:06:33', '2019-06-04 11:06:36');

-- ----------------------------
-- Table structure for t_schema_task_param
-- ----------------------------
DROP TABLE IF EXISTS `t_schema_task_param`;
CREATE TABLE `t_schema_task_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_args_id` int(6) NOT NULL,
  `param_name` varchar(50) NOT NULL COMMENT '参数名',
  `param_alias` varchar(20) NOT NULL DEFAULT '' COMMENT '参数名中文',
  `field_type` varchar(10) NOT NULL COMMENT '数据类型',
  `sorder` int(4) NOT NULL COMMENT '顺序',
  `individual_deal` int(4) NOT NULL DEFAULT '1' COMMENT '个别处理标识,用于统一填充参数数据时字段特殊处理[NO(1):不特殊处理, YES(2):要求特殊处理]',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10025 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务参数字段表';

-- ----------------------------
-- Records of t_schema_task_param
-- ----------------------------
INSERT INTO `t_schema_task_param` VALUES ('10013', '1003', 'start_time', '开始时间', 'date', '2', '1', '2019-05-24 11:30:25', null);
INSERT INTO `t_schema_task_param` VALUES ('10014', '1003', 'end_time', '结束时间', 'date', '3', '1', '2019-05-24 11:30:22', null);
INSERT INTO `t_schema_task_param` VALUES ('10015', '1003', 'channel_type', '渠道类型', 'string', '1', '1', '2019-05-24 11:31:24', null);
INSERT INTO `t_schema_task_param` VALUES ('10016', '1003', 'db_name', '数据库名字', 'string', '0', '2', '2019-05-24 11:31:21', null);
INSERT INTO `t_schema_task_param` VALUES ('10017', '1003', 'target_table', '存储数据表标识', 'string', '100', '2', '2019-05-24 11:32:00', null);
INSERT INTO `t_schema_task_param` VALUES ('10018', '1004', 'start_time', '开始时间', 'date', '4', '1', '2019-05-24 11:30:25', null);
INSERT INTO `t_schema_task_param` VALUES ('10019', '1004', 'end_time', '结束时间', 'date', '5', '1', '2019-05-24 11:30:22', null);
INSERT INTO `t_schema_task_param` VALUES ('10020', '1004', 'channel_type', '渠道类型', 'string', '1', '1', '2019-05-24 11:31:24', null);
INSERT INTO `t_schema_task_param` VALUES ('10021', '1004', 'shop_no', '店铺id', 'string', '2', '1', '2019-05-24 11:31:24', null);
INSERT INTO `t_schema_task_param` VALUES ('10022', '1004', 'classificate_type', '计算类型', 'string', '3', '1', '2019-05-24 11:31:24', null);
INSERT INTO `t_schema_task_param` VALUES ('10023', '1004', 'db_name', '数据库名字', 'string', '0', '2', '2019-05-24 11:31:21', null);
INSERT INTO `t_schema_task_param` VALUES ('10024', '1004', 'target_table', '存储数据表标识', 'string', '100', '2', '2019-05-24 11:32:00', null);

-- ----------------------------
-- Table structure for t_schema_task_param_value
-- ----------------------------
DROP TABLE IF EXISTS `t_schema_task_param_value`;
CREATE TABLE `t_schema_task_param_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_args_id` int(6) NOT NULL DEFAULT '0',
  `context_id` bigint(20) NOT NULL DEFAULT '0',
  `param_name` varchar(50) NOT NULL COMMENT '参数名',
  `param_value` varchar(100) NOT NULL COMMENT '参数值',
  `sorder` int(4) NOT NULL DEFAULT '1' COMMENT '顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10132 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务参数值表';

-- ----------------------------
-- Records of t_schema_task_param_value
-- ----------------------------
INSERT INTO `t_schema_task_param_value` VALUES ('10068', '1004', '6548874870736814083', 'start_time', '2019-05-01', '4', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10069', '1004', '6548874870736814083', 'end_time', '2019-06-01', '5', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10070', '1004', '6548874870736814083', 'channel_type', 'tm', '1', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10071', '1004', '6548874870736814083', 'shop_no', '100000000451', '2', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10072', '1004', '6548874870736814083', 'classificate_type', '1', '3', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10073', '1004', '6548874870736814083', 'db_name', 'edw_monki', '0', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10074', '1004', '6548874870736814083', 'target_table', 'category_sales_5523147270_3_6548874870707453955_2019_06_24_18_50_41', '200', '2019-06-24 18:50:41', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10079', '1004', '6548882406114328579', 'start_time', '2019-05-01', '4', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10080', '1004', '6548882406114328579', 'end_time', '2019-06-01', '5', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10081', '1004', '6548882406114328579', 'channel_type', 'tm', '1', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10082', '1004', '6548882406114328579', 'shop_no', '100000000451', '2', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10083', '1004', '6548882406114328579', 'classificate_type', '2', '3', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10084', '1004', '6548882406114328579', 'db_name', 'edw_monki', '0', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10085', '1004', '6548882406114328579', 'target_table', 'category_sales_5523147270_14_6548882406093357059_2019_06_24_19_20_37', '200', '2019-06-24 19:20:38', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10086', '1003', '6548897995587321859', 'start_time', '2019-05-01', '2', '2019-06-24 20:22:35', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10087', '1003', '6548897995587321859', 'end_time', '2019-06-01', '3', '2019-06-24 20:22:35', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10088', '1003', '6548897995587321859', 'channel_type', 'full', '1', '2019-06-24 20:22:35', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10089', '1003', '6548897995587321859', 'db_name', 'edw_monki', '0', '2019-06-24 20:22:35', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10090', '1003', '6548897995587321859', 'target_table', 'shop_card_intersect_5523147270_12_6548897995528601603_2019_06_24_20_22_34', '200', '2019-06-24 20:22:35', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10091', '1003', '6548898116886593539', 'start_time', '2019-05-01', '2', '2019-06-24 20:23:04', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10092', '1003', '6548898116886593539', 'end_time', '2019-06-01', '3', '2019-06-24 20:23:04', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10093', '1003', '6548898116886593539', 'channel_type', 'tm', '1', '2019-06-24 20:23:04', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10094', '1003', '6548898116886593539', 'db_name', 'edw_monki', '0', '2019-06-24 20:23:04', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10095', '1003', '6548898116886593539', 'target_table', 'shop_card_intersect_5523147270_16_6548898116861427715_2019_06_24_20_23_03', '200', '2019-06-24 20:23:04', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10096', '1004', '6549468932246339587', 'start_time', '2019-05-01', '4', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10097', '1004', '6549468932246339587', 'end_time', '2019-06-01', '5', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10098', '1004', '6549468932246339587', 'channel_type', 'tm', '1', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10099', '1004', '6549468932246339587', 'shop_no', '100000000451', '2', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10100', '1004', '6549468932246339587', 'classificate_type', '2', '3', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10101', '1004', '6549468932246339587', 'db_name', 'edw_monki', '0', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10102', '1004', '6549468932246339587', 'target_table', 'category_sales_5523147270_17_6549468932196007939_2019_06_26_10_11_16', '200', '2019-06-26 10:11:17', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10103', '1003', '6549471252233322499', 'start_time', '2019-05-01', '2', '2019-06-26 10:20:30', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10104', '1003', '6549471252233322499', 'end_time', '2019-06-01', '3', '2019-06-26 10:20:30', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10105', '1003', '6549471252233322499', 'channel_type', 'tm', '1', '2019-06-26 10:20:30', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10106', '1003', '6549471252233322499', 'db_name', 'edw_monki', '0', '2019-06-26 10:20:30', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10107', '1003', '6549471252233322499', 'target_table', 'shop_card_intersect_5523147270_18_6549471252195573763_2019_06_26_10_20_29', '200', '2019-06-26 10:20:30', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10108', '1003', '6549547325323214851', 'start_time', '2019-05-01', '2', '2019-06-26 15:22:47', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10109', '1003', '6549547325323214851', 'end_time', '2019-06-01', '3', '2019-06-26 15:22:47', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10110', '1003', '6549547325323214851', 'channel_type', 'tm', '1', '2019-06-26 15:22:47', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10111', '1003', '6549547325323214851', 'db_name', 'edw_monki', '0', '2019-06-26 15:22:47', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10112', '1003', '6549547325323214851', 'target_table', 'shop_card_intersect_5523147270_19_6549547325289660419_2019_06_26_15_22_47', '200', '2019-06-26 15:22:47', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10113', '1004', '6549548568779161603', 'start_time', '2019-05-01', '4', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10114', '1004', '6549548568779161603', 'end_time', '2019-06-01', '5', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10115', '1004', '6549548568779161603', 'channel_type', 'tm', '1', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10116', '1004', '6549548568779161603', 'shop_no', '100000000451', '2', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10117', '1004', '6549548568779161603', 'classificate_type', '2', '3', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10118', '1004', '6549548568779161603', 'db_name', 'edw_monki', '0', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10119', '1004', '6549548568779161603', 'target_table', 'category_sales_5523147270_20_6549548568762384387_2019_06_26_15_27_43', '200', '2019-06-26 15:27:44', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10120', '1003', '6549561214937071619', 'start_time', '2019-05-01', '2', '2019-06-26 16:17:59', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10121', '1003', '6549561214937071619', 'end_time', '2019-06-01', '3', '2019-06-26 16:17:59', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10122', '1003', '6549561214937071619', 'channel_type', 'tm', '1', '2019-06-26 16:17:59', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10123', '1003', '6549561214937071619', 'db_name', 'edw_monki', '0', '2019-06-26 16:17:59', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10124', '1003', '6549561214937071619', 'target_table', 'shop_card_intersect_5523147270_21_6549561214890934275_2019_06_26_16_17_58', '200', '2019-06-26 16:17:59', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10125', '1004', '6549561257698000899', 'start_time', '2019-05-01', '4', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10126', '1004', '6549561257698000899', 'end_time', '2019-06-01', '5', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10127', '1004', '6549561257698000899', 'channel_type', 'tm', '1', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10128', '1004', '6549561257698000899', 'shop_no', '100000000451', '2', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10129', '1004', '6549561257698000899', 'classificate_type', '0', '3', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10130', '1004', '6549561257698000899', 'db_name', 'edw_monki', '0', '2019-06-26 16:18:09', null);
INSERT INTO `t_schema_task_param_value` VALUES ('10131', '1004', '6549561257698000899', 'target_table', 'category_sales_5523147270_22_6549561257681223683_2019_06_26_16_18_08', '200', '2019-06-26 16:18:09', null);

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(18) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `sex` int(4) DEFAULT NULL COMMENT '性别',
  `user_code` varchar(255) DEFAULT NULL COMMENT '用户编码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('123', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', null);
