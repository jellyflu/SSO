/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.16 : Database - sso
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sso` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sso`;

/*Table structure for table `t_sessioninfo` */

DROP TABLE IF EXISTS `t_sessioninfo`;

CREATE TABLE `t_sessioninfo` (
  `sessionId` varchar(50) NOT NULL COMMENT '会话id',
  `sessionType` smallint(6) DEFAULT NULL COMMENT '会话类型 1全局SSO会话 2局部site会话',
  `parentSessionId` varchar(50) DEFAULT NULL COMMENT '父会话id',
  `domain` varchar(256) DEFAULT NULL COMMENT '站点域,如www.site1.com',
  `browserIp` varchar(32) DEFAULT NULL COMMENT '浏览器ip',
  `browserName` varchar(32) DEFAULT NULL COMMENT '浏览器名称',
  `loginTime` varchar(24) DEFAULT NULL COMMENT '登录时间',
  `logoutTime` varchar(24) DEFAULT NULL COMMENT '退出时间',
  `isOnline` smallint(6) DEFAULT '1' COMMENT '是否在线 1在线 0离线',
  `userId` varchar(32) DEFAULT NULL COMMENT '关联用户id',
  `username` varchar(32) DEFAULT NULL COMMENT '关联用户名',
  PRIMARY KEY (`sessionId`),
  KEY `parentSessionId` (`parentSessionId`),
  CONSTRAINT `t_sessioninfo_ibfk_1` FOREIGN KEY (`parentSessionId`) REFERENCES `t_sessioninfo` (`sessionId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sessioninfo` */


/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` varchar(32) NOT NULL COMMENT '主键id uuid',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `birthday` varchar(24) DEFAULT NULL COMMENT '生日',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `createtime` varchar(24) DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`userId`,`username`,`password`,`birthday`,`city`,`email`,`createtime`,`remark`) values 
('9F6111DDFDE311E8A35C507B9D60CD8C','zhangsan','9ae0147d65724f72f74804af4aac6f13','1993-03-23','北京','zhangsan@123.com','2018-12-12 15:57:48',''),
('C9D74719FDE311E8A35C507B9D60CD8C','wangwu','9ae0147d65724f72f74804af4aac6f13','1993-05-23','广州','wangwu@123.com','2018-12-12 15:58:59',''),
('D162BA4BFDE311E8A35C507B9D60CD8C','lisi','9ae0147d65724f72f74804af4aac6f13','1993-04-23','南京','lisi@123.com','2018-12-12 15:59:12','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
