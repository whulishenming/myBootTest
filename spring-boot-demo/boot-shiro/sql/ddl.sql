
CREATE DATABASE IF NOT EXISTS `shiro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shiro`;

-- 导出  表 shiro.t_permission 结构
CREATE TABLE IF NOT EXISTS `t_permission` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`url` varchar(256) DEFAULT NULL COMMENT 'url地址',
	`desc` varchar(64) DEFAULT NULL COMMENT 'url描述',
	`is_deleted` tinyint(4) NOT NULL,
	`create_user_id` bigint(20) NOT NULL,
	`create_time` datetime NOT NULL,
	`update_user_id` bigint(20) NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 shiro.t_role 结构
CREATE TABLE IF NOT EXISTS `t_role` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
	`role_type` varchar(10) DEFAULT NULL COMMENT '角色类型',
	`is_deleted` tinyint(4) NOT NULL,
	`create_user_id` bigint(20) NOT NULL,
	`create_time` datetime NOT NULL,
	`update_user_id` bigint(20) NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 shiro.t_role_permission 结构
CREATE TABLE IF NOT EXISTS `t_role_permission` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
	`permission_id` bigint(20) DEFAULT NULL COMMENT '权限ID',
	`is_deleted` tinyint(4) NOT NULL,
	`create_user_id` bigint(20) NOT NULL,
	`create_time` datetime NOT NULL,
	`update_user_id` bigint(20) NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 shiro.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`nick` varchar(20) DEFAULT NULL COMMENT '用户昵称',
	`user_name` varchar(128) DEFAULT NULL COMMENT '邮箱|登录帐号',
	`password` varchar(128) DEFAULT NULL COMMENT '密码',
	`phone_num` varchar(20) DEFAULT NULL COMMENT '手机号',
	`last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
	`status` tinyint(4) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
	`is_deleted` tinyint(4) NOT NULL,
	`create_user_id` bigint(20) NOT NULL,
	`create_time` datetime NOT NULL,
	`update_user_id` bigint(20) NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 shiro.t_user_role 结构
CREATE TABLE IF NOT EXISTS `t_user_role` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
	`role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
	`is_deleted` tinyint(4) NOT NULL,
	`create_user_id` bigint(20) NOT NULL,
	`create_time` datetime NOT NULL,
	`update_user_id` bigint(20) NOT NULL,
	`update_time` datetime NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

