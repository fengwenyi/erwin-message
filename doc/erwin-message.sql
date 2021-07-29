-- ----------------------------
-- 创建数据库
-- ----------------------------
DROP DATABASE IF EXISTS `erwin-message`; -- 删除数据库
CREATE DATABASE `erwin-message` CHARACTER SET utf8mb4 COLLATE utf8mb4_bin; -- 创建数据库并指定编码

USE `erwin-message`;

-- ----------------------------
-- 用户表
-- 如果用户是采用第三方登录，那么将昵称、头像等用户信息保存到用户第三方信息表中
-- 使用时，如果用户表中用户信息为空，则使用第三方的用户信息
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                          `create_user_id` BIGINT (20) COMMENT '创建者ID',
                          `update_time` DATETIME COMMENT '修改时间',
                          `update_user_id` BIGINT (20) COMMENT '修改者ID',
                          `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                          `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                          `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                          `erwin_id` VARCHAR (255) NOT NULL COMMENT 'Erwin ID（邮箱）',
                          `password` VARCHAR (255) NOT NULL COMMENT '密码',
                          `nickname` VARCHAR (255) NOT NULL COMMENT '昵称',
                          `sex` TINYINT (1) DEFAULT 0 COMMENT '性别；0：未知，默认；1：男；2：女',
                          `city` VARCHAR (255) COMMENT '城市',
                          `head_url` VARCHAR (255) COMMENT '头像',
                          `lock_status` TINYINT (1) DEFAULT 0 COMMENT '锁定状态，0：正常；1：锁定',

                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uni_erwin_id` (`erwin_id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '用户表';

-- ----------------------------
-- 第三方用户表
-- 如果用户表中用户信息为空，则每次第三方登录时，
-- 都将用户信息同步一次，以保证用户信息与第三方一致
-- ----------------------------
DROP TABLE IF EXISTS `t_user_third`;
CREATE TABLE `t_user_third` (
                                `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                                `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                `create_user_id` BIGINT (20) COMMENT '创建者ID',
                                `update_time` DATETIME COMMENT '修改时间',
                                `update_user_id` BIGINT (20) COMMENT '修改者ID',
                                `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                                `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                                `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                                `user_id` BIGINT (20) NOT NULL COMMENT 'User ID',
                                `third_id` VARCHAR (255) NOT NULL COMMENT 'Third ID',
                                `third_type` INT (5) NOT NULL COMMENT '第三方。1：QQ；2：微信；3：Github；4：Gitee；5：支付宝；6：Google；7：',
                                `nickname` VARCHAR (255) COMMENT '昵称',
                                `sex` TINYINT (1) DEFAULT 0 COMMENT '性别；0：未知，默认；1：男；2：女',
                                `city` VARCHAR (255) COMMENT '城市',
                                `head_url` VARCHAR (255) COMMENT '头像',
                                `lock_status` TINYINT (1) DEFAULT 0 COMMENT '锁定状态，0：正常；1：锁定',

                                PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '第三方用户表';


-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
                          `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                          `create_user_id` BIGINT (20) COMMENT '创建者ID',
                          `update_time` DATETIME COMMENT '修改时间',
                          `update_user_id` BIGINT (20) COMMENT '修改者ID',
                          `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                          `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                          `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                          `role` VARCHAR (255) NOT NULL COMMENT '角色',
                          `remarks` VARCHAR (255) NOT NULL COMMENT '说明',

                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uni_role` (`role`) -- 角色名称不重复
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '角色表';

-- ----------------------------
-- 用户-角色表
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
                               `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                               `create_time` DATETIME NOT NULL COMMENT '创建时间',
                               `create_user_id` BIGINT (20) COMMENT '创建者ID',
                               `update_time` DATETIME COMMENT '修改时间',
                               `update_user_id` BIGINT (20) COMMENT '修改者ID',
                               `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                               `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                               `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                               `user_id` BIGINT (20) NOT NULL COMMENT 'User ID',
                               `role_id` BIGINT (20) NOT NULL COMMENT '角色 ID',

                               PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '用户-角色表';


-- ----------------------------
-- 权限表
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
                                `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                                `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                `create_user_id` BIGINT (20) COMMENT '创建者ID',
                                `update_time` DATETIME COMMENT '修改时间',
                                `update_user_id` BIGINT (20) COMMENT '修改者ID',
                                `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                                `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                                `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                                `permission` VARCHAR (255) NOT NULL COMMENT '权限',
                                `url` VARCHAR (255) NOT NULL COMMENT 'URL',
                                `remarks` VARCHAR (255) NOT NULL COMMENT '说明',

                                PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '权限表';

-- ----------------------------
-- 角色-权限表
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
                                     `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                                     `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                     `create_user_id` BIGINT (20) COMMENT '创建者ID',
                                     `update_time` DATETIME COMMENT '修改时间',
                                     `update_user_id` BIGINT (20) COMMENT '修改者ID',
                                     `version` INT (11) DEFAULT 0 COMMENT '乐观锁',
                                     `release_status` TINYINT (1) DEFAULT 0 COMMENT '启用状态，0：不启用；1：启用',
                                     `delete_status` TINYINT (1) DEFAULT 0 COMMENT '删除状态，0：正常；1：删除',

                                     `role_id` BIGINT (20) NOT NULL COMMENT 'User ID',
                                     `permission_id` BIGINT (20) NOT NULL COMMENT '角色 ID',

                                     PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '角色-权限表';

-- ----------------------------
-- 权限系统数据初始化
-- 初始密码：123456
-- ----------------------------
INSERT INTO `t_user` (id, erwin_id, nickname, password, release_status, create_time) VALUE (1, 'xfsy_2015@163.com', 'root', '$2a$10$0LRtdXG3yw/QD/t3wZvW.OXsex.p56T/8VZFzIN1K68mDdhPAHP1.', 1, '2021-07-30 00:51:00');
INSERT INTO `t_role` (id, role, remarks, release_status, create_time) VALUE (1, 'ROLE_ADMIN', '管理员', 1, '2021-07-30 00:51:00');
INSERT INTO `t_role` (id, role, remarks, release_status, create_time) VALUE (2, 'ROLE_USER', '用户', 1, '2021-07-30 00:51:00');
INSERT INTO `t_user_role` (id, user_id, role_id, release_status, create_time) VALUE (1, 1, 1, 1, '2021-07-30 00:51:00');
INSERT INTO `t_user_role` (id, user_id, role_id, release_status, create_time) VALUE (2, 1, 2, 1, '2021-07-30 00:51:00');


-- ----------------------------
-- 用户登录记录表
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
                               `id` BIGINT (20) NOT NULL COMMENT '主键ID',
                               `create_time` DATETIME NOT NULL COMMENT '创建时间',
                               `erwin_id` VARCHAR (255) NOT NULL COMMENT 'Erwin ID',
                               `ip` VARCHAR (255) NOT NULL COMMENT '登录IP',
                               `position` VARCHAR (255) NOT NULL COMMENT '登录位置（如果与注册城市不一致，则需要验证）',
                               `os` VARCHAR (255) NOT NULL COMMENT '使用的系统',
                               `browser` VARCHAR (255) NOT NULL COMMENT '使用的浏览器',
                               `login_result` VARCHAR (255) NOT NULL COMMENT '登录结果',
                               `error_message` VARCHAR (255) NOT NULL COMMENT '错误信息',
                               `user_id` BIGINT (20) NOT NULL COMMENT '用户ID',

                               PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_bin COMMENT '用户登录记录表';