-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `id` bigint(0) NOT NULL COMMENT '主键ID',
                           `erwin_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'Erwin ID（邮箱）',
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
                           `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
                           `sex` int(0) DEFAULT NULL COMMENT '性别；0：未知，默认；1：男；2：女',
                           `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
                           `head_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
                           `lock_status` int(0) DEFAULT 0 COMMENT '锁定状态，0：正常；-1：锁定',
                           `release_status` int(0) DEFAULT 0 COMMENT '生效状态，0：不生效；1：生效',
                           `delete_status` int(0) DEFAULT 0 COMMENT '删除状态，0：正常；-1：删除',
                           `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                           `update_user_id` bigint(0) DEFAULT NULL COMMENT '修改者ID',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE INDEX `user_erwin_id`(`erwin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1243871976518864898, 'xfsy_2015@163.com', '$2a$10$bLFCnxGskhZQInWAXkPoc.v1aJVBi2ockuhVRy/IHs10T/b2uNhZa', 'root', 1, '未知位置', 'https://images.fengwenyi.com/1193158348289167361', 0, 1, 0, '2020-03-28 20:06:03', '2020-03-28 22:06:27', NULL);
INSERT INTO `t_user` VALUES (1246102807815741441, '1', '1', '1', 1, '1', NULL, 0, 1, 0, '2020-04-03 23:50:36', '2020-04-03 23:50:36', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `id` bigint(0) NOT NULL COMMENT '主键ID',
                                `user_id` bigint(0) NOT NULL COMMENT 'User ID',
                                `role_id` bigint(0) NOT NULL COMMENT '角色 ID',
                                `release_status` int(0) DEFAULT 0 COMMENT '生效状态，0：不生效；1：生效',
                                `delete_status` int(0) DEFAULT 0 COMMENT '删除状态，0：正常；-1：删除',
                                `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                `update_user_id` bigint(0) DEFAULT NULL COMMENT '修改者ID',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户-角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (2, 1243871976518864898, 1, 1, 0, '2020-03-29 04:38:11', '2020-03-29 04:38:11', NULL);
INSERT INTO `t_user_role` VALUES (1243871976665665538, 1243871976518864898, 2, 1, 0, '2020-03-28 20:06:03', '2020-03-28 20:06:03', NULL);

-- ----------------------------
-- Table structure for t_user_third
-- ----------------------------
DROP TABLE IF EXISTS `t_user_third`;
CREATE TABLE `t_user_third`  (
                                 `id` bigint(0) NOT NULL COMMENT '主键ID',
                                 `user_id` bigint(0) NOT NULL COMMENT 'User ID',
                                 `third_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'Third ID',
                                 `third_type` int(0) NOT NULL COMMENT '第三方。1：QQ；2：微信；3：Github；4：Gitee；5：支付宝；6：Google；7：',
                                 `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
                                 `sex` int(0) DEFAULT NULL COMMENT '性别；0：未知，默认；1：男；2：女',
                                 `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '城市',
                                 `head_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
                                 `lock_status` int(0) DEFAULT 0 COMMENT '锁定状态，0：正常；-1：锁定',
                                 `release_status` int(0) DEFAULT 0 COMMENT '生效状态，0：不生效；1：生效',
                                 `delete_status` int(0) DEFAULT 0 COMMENT '删除状态，0：正常；-1：删除',
                                 `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                 `update_user_id` bigint(0) DEFAULT NULL COMMENT '修改者ID',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '第三方用户表' ROW_FORMAT = Dynamic;
