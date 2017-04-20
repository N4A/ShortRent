/*
Navicat MySQL Data Transfer

Source Server         : pj2
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : easyrent

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-04-19 22:31:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `house_id` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT '3' COMMENT '评分：0-5星',
  `content` text,
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `house_id` (`house_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '2', '1', '3', '我很满意', '0');
INSERT INTO `comment` VALUES ('2', '1', '1', '3', 'ghgdfdgda', '0');
INSERT INTO `comment` VALUES ('3', '1', '1', '3', 'hahahahahahahahahhahahaha\r\n', '0');
INSERT INTO `comment` VALUES ('4', '3', '4', '3', '房东态度很差', '0');
INSERT INTO `comment` VALUES ('5', '3', '4', '3', '棒极了，积极点赞', '0');
INSERT INTO `comment` VALUES ('6', '1', '17', '3', 'nihao', '0');
INSERT INTO `comment` VALUES ('7', '5', '18', '3', '江', '0');
INSERT INTO `comment` VALUES ('8', '6', '18', '3', '很好', '0');
INSERT INTO `comment` VALUES ('9', '7', '18', '3', '好房子', '0');

-- ----------------------------
-- Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `bill` tinyint(4) DEFAULT NULL COMMENT '1：提供发票，0：不提供',
  `rent_type` tinyint(4) DEFAULT NULL COMMENT '1：整租，2：单间，3：床位',
  `kind` tinyint(4) DEFAULT NULL COMMENT '1：酒店，2：旅店，3：客栈，4：民居',
  `pic_num` int(11) DEFAULT NULL,
  `area` float DEFAULT NULL,
  `guest_num` int(11) DEFAULT NULL,
  `bed_num` int(11) DEFAULT NULL,
  `bedroom_num` int(11) DEFAULT NULL,
  `room_num` int(11) DEFAULT NULL,
  `bed_type` tinyint(4) DEFAULT NULL COMMENT '1：双人，2：单人，3：高低',
  `toilet_num` int(11) DEFAULT NULL,
  `room_desc` text,
  `use_rule` text,
  `facility` text,
  `address` text,
  `min_day` int(11) DEFAULT NULL,
  `max_day` int(11) DEFAULT NULL,
  `refund_day` int(11) DEFAULT NULL,
  `pay_rule` text,
  `day_price` decimal(5,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：待审核，1：已发布，2：已拒绝',
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `house_ibfk_1` (`user_id`),
  CONSTRAINT `house_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', '1', '测试房源', '1', '1', '3', '1', '100', '5', '3', '1', '5', '1', '1', '欢迎入住', '禁止吸烟', '修改后的设施', '上海市杨浦区国定路440号', '1', '7', '2', 'paypal', '25.20', '2015-07-22 08:07:14', '1', '0');
INSERT INTO `house` VALUES ('2', '1', '测试房源2', '0', '1', '1', '3', '200', '1', '1', '1', '1', '0', '1', '', '', '', '123465', '1', '1', '1', '', '5.50', '2015-07-31 20:03:03', '1', '0');
INSERT INTO `house` VALUES ('4', '1', '测试房源3', '1', '1', '3', '0', '300', '5', '3', '1', '5', '1', '1', '欢迎入住', '禁止吸烟', 'tanxin', '上海市浦东新区复旦大学张江校区', '1', '7', '2', 'paypal', '124.00', '2015-07-23 08:35:10', '1', '0');
INSERT INTO `house` VALUES ('11', '4', '没看', '1', '1', '1', '0', '400', '1', '1', '1', '1', '1', '1', '好房', '跪着', '好东西', '大街', '1', '1', '1', '支付', '50.00', '2015-08-04 10:07:00', '1', '1');
INSERT INTO `house` VALUES ('12', '3', '21', '1', '1', '1', '0', '500', '1', '1', '1', '1', '1', '1', '', '', '', '21', '1', '1', '1', '', '200.00', '2015-08-04 10:17:28', '0', '1');
INSERT INTO `house` VALUES ('13', '2', '444', '1', '1', '1', '0', '550', '1', '1', '1', '1', '1', '1', '', '', '', '12', '1', '1', '1', '', '12.00', '2015-08-04 10:49:09', '1', '1');
INSERT INTO `house` VALUES ('14', '3', '同济大学', '1', '1', '1', '7', '560', '1', '1', '1', '1', '1', '1', '', '', '', '上海市杨浦区同济大学', '1', '1', '1', '', '500.00', '2015-08-04 10:54:36', '1', '0');
INSERT INTO `house` VALUES ('15', '1', '同济大学', '1', '1', '1', '0', '570', '1', '1', '1', '1', '1', '4', '', '', '', '上海市杨浦区同济大学', '1', '1', '1', '', '22.00', '2015-08-04 21:01:05', '2', '1');
INSERT INTO `house` VALUES ('16', '3', '清华园宾馆', '1', '1', '1', '2', '580', '8', '4', '3', '4', '2', '2', '', '', '', '北京市海淀区清华大学', '1', '15', '7', '', '600.00', '2015-08-05 08:30:58', '1', '0');
INSERT INTO `house` VALUES ('17', '1', '西南某高校', '1', '1', '1', '3', '1000', '1', '1', '1', '1', '1', '1', '', '', '', '上海市闵行区上海交通大学', '1', '1', '1', '', '200.00', '2015-08-05 08:52:37', '1', '0');
INSERT INTO `house` VALUES ('18', '1', '谈心之家', '1', '1', '4', '2', '2000', '4', '1', '1', '1', '3', '1', '', '', '', '上海市浦东新区高科苑4号楼', '1', '7', '1', '', '30.00', '2015-08-05 09:00:35', '1', '0');
INSERT INTO `house` VALUES ('19', '1', '上海科技大学', '1', '1', '1', '2', '500', '1', '1', '1', '1', '1', '1', '', '', '', '上海市浦东新区海科路100号', '1', '1', '1', '', '20.00', '2015-08-05 09:01:52', '1', '0');
INSERT INTO `house` VALUES ('20', '5', '交通大学', '0', '1', '1', '1', '200', '1', '1', '1', '5', '1', '1', '', '', '', '上海市徐汇区', '1', '1', '4', '', '23.00', '2015-08-05 10:33:08', '0', '0');
INSERT INTO `house` VALUES ('21', '6', '登云', '1', '1', '1', '1', '78', '1', '5', '1', '1', '1', '1', '', '', '', '张江', '1', '13', '1', '', '45.00', '2015-08-06 11:00:18', '1', '0');
INSERT INTO `house` VALUES ('22', '7', '邯郸路', '1', '1', '1', '1', '2332', '1', '1', '1', '5', '1', '1', '', '', '', '邯郸路440号', '1', '1', '1', '', '234.00', '2015-08-07 08:25:05', '1', '0');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `target_id` int(11) DEFAULT NULL,
  `content` text,
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未读，1：已读',
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `message_ibfk_1` (`user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '2', 'hello admin', '0', '0');
INSERT INTO `message` VALUES ('2', '2', '1', 'xxxxxxxx', '1', '0');
INSERT INTO `message` VALUES ('3', '1', '2', '1111111111111111111111111111111111', '0', '1');
INSERT INTO `message` VALUES ('4', '3', '1', 'hello', '1', '0');
INSERT INTO `message` VALUES ('5', '3', '3', 'hello，bayby', '0', '1');
INSERT INTO `message` VALUES ('6', '3', '3', '你好', '0', '0');
INSERT INTO `message` VALUES ('7', '5', '1', '土豪你好', '0', '0');
INSERT INTO `message` VALUES ('8', '6', '1', '就离开了回复', '1', '1');
INSERT INTO `message` VALUES ('9', '7', '1', '房子很好', '0', '0');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_num` varchar(50) DEFAULT NULL,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL,
  `day_price` decimal(5,2) DEFAULT NULL,
  `state` tinyint(4) DEFAULT '0' COMMENT '0：待受理，1：已受理，2：已取消，3：已拒绝，4：完成',
  `order_date` date DEFAULT NULL,
  `check_date` date DEFAULT NULL,
  `comment_state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：未评论，1：已评论',
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `house_id` (`house_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`house_id`) REFERENCES `house` (`id`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('4', '2', '2', '143761179809412', '2015-07-24', '2015-07-26', '200.02', '3', '2015-07-23', '2015-07-23', '0', '0');
INSERT INTO `order` VALUES ('5', '1', '1', '143864857974611', '2015-08-05', '2015-08-07', '0.00', '4', '2015-08-04', null, '1', '0');
INSERT INTO `order` VALUES ('11', '4', '3', '143865924655343', '2015-08-05', '2015-08-07', '0.00', '4', '2015-08-04', null, '1', '0');
INSERT INTO `order` VALUES ('12', '4', '3', '143865925773943', '2015-08-06', '2015-08-07', '0.00', '3', '2015-08-04', null, '0', '0');
INSERT INTO `order` VALUES ('13', '17', '1', '1438736806218171', '2015-08-06', '2015-08-07', '200.00', '3', '2015-08-05', null, '0', '0');
INSERT INTO `order` VALUES ('14', '17', '1', '1438736937065171', '2015-08-13', '2015-08-14', '200.00', '4', '2015-08-05', null, '1', '0');
INSERT INTO `order` VALUES ('15', '14', '3', '1438739660558143', '2015-08-05', '2015-08-06', '500.00', '3', '2015-08-05', null, '0', '0');
INSERT INTO `order` VALUES ('16', '14', '3', '1438739671855143', '2015-08-07', '2015-08-08', '500.00', '3', '2015-08-05', null, '0', '0');
INSERT INTO `order` VALUES ('17', '14', '3', '1438740040834143', '2015-08-05', '2015-08-06', '500.00', '3', '2015-08-05', null, '0', '0');
INSERT INTO `order` VALUES ('18', '17', '3', '1438740576590173', '2015-08-07', '2015-08-08', '200.00', '3', '2015-08-05', null, '0', '0');
INSERT INTO `order` VALUES ('19', '18', '5', '1438742174169185', '2015-08-06', '2015-08-07', '30.00', '4', '2015-08-05', null, '1', '0');
INSERT INTO `order` VALUES ('20', '19', '5', '1438820615429195', '2015-08-13', '2015-08-14', '20.00', '2', '2015-08-06', null, '0', '0');
INSERT INTO `order` VALUES ('21', '18', '6', '1438830335164186', '2015-08-13', '2015-08-14', '30.00', '4', '2015-08-06', null, '1', '0');
INSERT INTO `order` VALUES ('22', '18', '7', '1438907228730187', '2015-08-08', '2015-08-09', '30.00', '4', '2015-08-07', null, '1', '0');

-- ----------------------------
-- Table structure for `reply`
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `content` text,
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `reply_ibfk_2` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '1', '1', '谢谢！', '0');
INSERT INTO `reply` VALUES ('2', '1', '2', 'lalala', '0');
INSERT INTO `reply` VALUES ('9', '1', '3', '1111111111111112222222222', '0');
INSERT INTO `reply` VALUES ('10', '1', '4', '造谣是不好的行为', '0');
INSERT INTO `reply` VALUES ('11', '1', '6', 'haha', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `salt` varchar(8) NOT NULL,
  `gender` tinyint(4) DEFAULT '1' COMMENT '1：男，2：女',
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` text,
  `avatar` varchar(50) DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：普通，1：管理员',
  `del` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', '70875e9b66e0fb5ce9a1b8d740cd1d40', '333b600c', '1', '13333333333', 'd@d.d', '', null, '1', '0');
INSERT INTO `user` VALUES ('2', 'admin', '25921ca2d2bdf7df868c0cfdeb33a990', '77d77659', null, '123456', null, null, null, '1', '0');
INSERT INTO `user` VALUES ('3', 'duocai', '5c123ef0f988ac4959c8a3221e8f9590', 'cb3a6178', '1', '15124864545', 'saas@dfsfd.csdf', '上海', null, '0', '0');
INSERT INTO `user` VALUES ('4', '解决', '05dd0436317e720f4bb1f63dc3d9c3f2', 'a3bbe9fb', '1', '13666666666', null, null, null, '0', '0');
INSERT INTO `user` VALUES ('5', 'tanxin', '5a38a081d96d5a46ebce525063775e87', '73d258a4', '1', '15345678999', null, 'xxxx', null, '0', '0');
INSERT INTO `user` VALUES ('6', 'liu', '7693e28dc0ba6b565d1590be7d036619', 'c2ce02e6', '1', '15888888888', null, null, null, '0', '0');
INSERT INTO `user` VALUES ('7', 'wuduo', '98322e5e749b01a7cb8da83648d7cd70', '4cfe21a5', '1', '13456789056', null, null, null, '0', '0');
