/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : ebe_base

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 01/07/2020 14:57:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business_stock
-- ----------------------------
DROP TABLE IF EXISTS `business_stock`;
CREATE TABLE `business_stock`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sku_id` bigint(20) NOT NULL COMMENT '关联sku表的主键sku_id',
  `seckill_stock` int(10) NULL DEFAULT 0 COMMENT '秒杀库存',
  `stock` int(10) NOT NULL DEFAULT 0 COMMENT '商品库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business_stock
-- ----------------------------

-- ----------------------------
-- Table structure for category_goods
-- ----------------------------
DROP TABLE IF EXISTS `category_goods`;
CREATE TABLE `category_goods`  (
  `category_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '上级分类id,父类别id=0时说明是根节点,一级类别',
  `category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `category_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类图片',
  `sort_order` int(4) NULL DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态；0：禁用，1：启用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_goods
-- ----------------------------
INSERT INTO `category_goods` VALUES (1, 0, '水果', 'daf3cf88703a495c9aa5ad5ddf55abca.png', NULL, NULL, 1, '2020-03-08 23:18:37', '2020-04-02 23:00:50');
INSERT INTO `category_goods` VALUES (2, 0, '蔬菜', '6432fdd010f649ff843bd445b689f9cc.jpeg', NULL, NULL, 1, '2020-03-08 23:18:49', '2020-04-02 23:09:57');
INSERT INTO `category_goods` VALUES (3, 0, '肉禽', '85dd987b67bc4b25b0513902d80f668c.png', 1, NULL, 1, '2020-03-08 23:18:57', '2020-04-02 23:04:07');
INSERT INTO `category_goods` VALUES (4, 0, '速食', '1f8529bd834e4f03881f466826f9b6e5.jpeg', 1, '', 1, '2020-03-08 23:19:02', '2020-06-24 18:01:30');
INSERT INTO `category_goods` VALUES (5, 0, '粮油调味', '9fe3603ff8aa487dbbc548a918d709fa.png', 2, NULL, 1, '2020-03-08 23:19:04', '2020-04-02 23:03:53');
INSERT INTO `category_goods` VALUES (6, 0, '厨卫百货', '2f885c46fdb34fd88ec43d165b883ea5.png', 12, '223', 1, '2020-03-09 00:26:44', '2020-04-02 23:10:08');
INSERT INTO `category_goods` VALUES (7, 1, '热带水果', 'dbef074cab85491585ea9e5f7c9518ee.jpeg', 23, '', 1, '2020-03-09 00:27:29', '2020-06-13 00:02:49');
INSERT INTO `category_goods` VALUES (8, 0, '饮料酒水', '3404661405b94b3b902feb444bf25a5e.jpeg', 0, '', 1, '2020-03-09 17:31:12', '2020-06-24 18:01:07');
INSERT INTO `category_goods` VALUES (9, 1, '橘柑橙柚', '60365b1d1f604938bc412e1b8aca335b.jpeg', 5, '', 1, '2020-03-09 17:42:18', '2020-06-13 00:05:14');
INSERT INTO `category_goods` VALUES (10, 1, '苹果香蕉', 'b64553fda05147728626b55e356ffec3.jpeg', 1, '', 1, '2020-03-09 23:55:20', '2020-06-13 00:05:49');
INSERT INTO `category_goods` VALUES (12, 2, '有机蔬菜', '25fc2c435c644f27b69ba3fd881b0feb.jpeg', NULL, '', 1, '2020-04-01 23:12:06', '2020-06-13 00:08:56');
INSERT INTO `category_goods` VALUES (13, 2, '叶菜', 'd7b4760bfce94f6286501716315cf55c.jpeg', NULL, '', 1, '2020-04-01 23:12:19', '2020-06-13 00:09:03');
INSERT INTO `category_goods` VALUES (14, 2, '茄果/瓜果', NULL, NULL, NULL, 1, '2020-04-01 23:12:40', '2020-04-01 23:12:40');
INSERT INTO `category_goods` VALUES (15, 2, '根茎类', NULL, NULL, NULL, 1, '2020-04-01 23:13:09', '2020-04-01 23:13:09');
INSERT INTO `category_goods` VALUES (16, 3, '猪肉', 'ce2e791705bf46dcb8270c5d245e4396.jpeg', NULL, '', 1, '2020-04-01 23:13:38', '2020-06-13 00:12:57');
INSERT INTO `category_goods` VALUES (17, 3, '鸡/鸭/鸽', '88e256fd247c4c5c808b98c0fd841005.jpeg', NULL, '', 1, '2020-04-01 23:13:54', '2020-06-13 00:12:28');
INSERT INTO `category_goods` VALUES (18, 3, '鸡蛋/蛋类', 'bbbcda74461248a1b2784749b68ac81a.jpeg', NULL, '', 1, '2020-04-01 23:14:18', '2020-06-13 00:11:17');
INSERT INTO `category_goods` VALUES (19, 3, '牛肉', '29f186005d32487e9967e5cdb0be5f0a.jpeg', NULL, '', 1, '2020-04-01 23:14:25', '2020-06-13 00:12:41');
INSERT INTO `category_goods` VALUES (20, 4, '饺子', NULL, NULL, NULL, 1, '2020-04-01 23:14:56', '2020-04-01 23:14:56');
INSERT INTO `category_goods` VALUES (21, 4, '包子馒头', NULL, NULL, NULL, 1, '2020-04-01 23:15:05', '2020-04-01 23:15:05');
INSERT INTO `category_goods` VALUES (22, 4, '面条/拉面', NULL, NULL, NULL, 1, '2020-04-01 23:15:24', '2020-04-01 23:15:24');
INSERT INTO `category_goods` VALUES (23, 5, '大米', '', NULL, '', 1, '2020-04-01 23:15:46', '2020-05-20 13:53:00');
INSERT INTO `category_goods` VALUES (24, 5, '食用油', NULL, NULL, NULL, 1, '2020-04-01 23:15:53', '2020-04-01 23:15:53');
INSERT INTO `category_goods` VALUES (25, 5, '面粉面条', NULL, NULL, NULL, 1, '2020-04-01 23:16:05', '2020-04-01 23:16:05');
INSERT INTO `category_goods` VALUES (26, 5, '调味酱汁', NULL, NULL, NULL, 1, '2020-04-01 23:16:33', '2020-04-01 23:16:33');
INSERT INTO `category_goods` VALUES (27, 6, '纸巾面巾', NULL, NULL, NULL, 1, '2020-04-01 23:17:02', '2020-04-01 23:17:02');
INSERT INTO `category_goods` VALUES (28, 6, '衣服清洁', NULL, NULL, NULL, 1, '2020-04-01 23:17:16', '2020-04-01 23:17:16');
INSERT INTO `category_goods` VALUES (29, 6, '家具清洁', NULL, NULL, NULL, 1, '2020-04-01 23:17:36', '2020-04-01 23:17:36');
INSERT INTO `category_goods` VALUES (30, 8, '碳酸/运动', NULL, NULL, NULL, 1, '2020-04-01 23:18:13', '2020-04-01 23:18:13');
INSERT INTO `category_goods` VALUES (31, 8, '饮用水', NULL, NULL, NULL, 1, '2020-04-01 23:18:36', '2020-04-01 23:18:36');
INSERT INTO `category_goods` VALUES (32, 8, '乳制品', NULL, NULL, NULL, 1, '2020-04-01 23:19:02', '2020-04-01 23:19:02');
INSERT INTO `category_goods` VALUES (33, 11, '薯片膨化', NULL, NULL, NULL, 1, '2020-04-01 23:19:30', '2020-04-01 23:19:30');
INSERT INTO `category_goods` VALUES (34, 11, '饼干曲奇', NULL, NULL, NULL, 1, '2020-04-01 23:20:16', '2020-04-01 23:20:16');
INSERT INTO `category_goods` VALUES (35, 11, '中西糕点', NULL, NULL, NULL, 1, '2020-04-01 23:20:33', '2020-04-01 23:20:33');
INSERT INTO `category_goods` VALUES (36, 8, '啤酒', NULL, NULL, NULL, 1, '2020-04-02 00:34:16', '2020-04-02 00:34:16');
INSERT INTO `category_goods` VALUES (37, 8, '预调酒', NULL, NULL, NULL, 1, '2020-04-02 00:34:32', '2020-04-02 00:34:32');
INSERT INTO `category_goods` VALUES (38, 3, '海鲜', NULL, NULL, NULL, 1, '2020-04-02 14:08:29', '2020-04-02 14:08:29');
INSERT INTO `category_goods` VALUES (40, 39, '饼干', NULL, 20, NULL, 1, '2020-04-25 18:41:13', '2020-04-25 18:41:13');
INSERT INTO `category_goods` VALUES (42, 41, '饼干', NULL, 20, NULL, 1, '2020-04-25 19:10:05', '2020-04-25 19:10:05');
INSERT INTO `category_goods` VALUES (44, 43, '饼干', NULL, 20, NULL, 1, '2020-04-25 20:28:35', '2020-04-25 20:28:35');
INSERT INTO `category_goods` VALUES (46, 45, '饼干', NULL, 10, NULL, 1, '2020-04-25 20:44:09', '2020-04-25 20:44:09');
INSERT INTO `category_goods` VALUES (48, 47, '饼干', NULL, NULL, NULL, 1, '2020-04-25 21:08:43', '2020-04-25 21:08:43');
INSERT INTO `category_goods` VALUES (50, 49, '饼干', NULL, 20, NULL, 1, '2020-05-01 14:23:19', '2020-05-01 14:23:19');
INSERT INTO `category_goods` VALUES (53, 52, '饼干', NULL, 20, NULL, 1, '2020-05-01 15:32:38', '2020-05-01 15:32:38');
INSERT INTO `category_goods` VALUES (54, 0, '休闲零食', '20f3a1d8b8c24575841d8f0bdd874779.png', 20, NULL, 1, '2020-05-01 15:43:57', '2020-05-01 15:43:57');
INSERT INTO `category_goods` VALUES (55, 54, '饼干', NULL, 1, NULL, 1, '2020-05-01 15:44:10', '2020-05-01 15:44:10');
INSERT INTO `category_goods` VALUES (61, 2, '西红柿/黄瓜', '793cb3d2b5b546918f2a8031361b241f.jpeg', NULL, NULL, 1, '2020-06-13 00:09:29', '2020-06-13 00:09:29');

-- ----------------------------
-- Table structure for category_goods_spu
-- ----------------------------
DROP TABLE IF EXISTS `category_goods_spu`;
CREATE TABLE `category_goods_spu`  (
  `spu_id` bigint(20) UNSIGNED NOT NULL COMMENT 'spuid',
  `cg_id1` bigint(20) UNSIGNED NOT NULL COMMENT '商品一级类目id',
  `cg_id2` bigint(20) UNSIGNED NOT NULL COMMENT '商品二级类目id',
  `cg_id3` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '商品三级类目id（未使用）'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'SPU与商品分类中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_goods_spu
-- ----------------------------
INSERT INTO `category_goods_spu` VALUES (1, 1, 7, NULL);
INSERT INTO `category_goods_spu` VALUES (2, 1, 7, NULL);
INSERT INTO `category_goods_spu` VALUES (7, 8, 37, NULL);
INSERT INTO `category_goods_spu` VALUES (8, 5, 26, NULL);
INSERT INTO `category_goods_spu` VALUES (9, 5, 23, NULL);
INSERT INTO `category_goods_spu` VALUES (10, 3, 38, NULL);
INSERT INTO `category_goods_spu` VALUES (11, 1, 7, NULL);
INSERT INTO `category_goods_spu` VALUES (20, 2, 12, NULL);
INSERT INTO `category_goods_spu` VALUES (21, 3, 16, NULL);
INSERT INTO `category_goods_spu` VALUES (22, 1, 7, NULL);

-- ----------------------------
-- Table structure for content_banner
-- ----------------------------
DROP TABLE IF EXISTS `content_banner`;
CREATE TABLE `content_banner`  (
  `banner_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `banner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'banner内容管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content_banner
-- ----------------------------
INSERT INTO `content_banner` VALUES (1, 'b-1', '', '', '首页顶部主banner', '2020-06-27 17:30:09', '2020-06-27 21:26:40');
INSERT INTO `content_banner` VALUES (2, 'b-2', '', '96f90911a4b949918a092eb0e8c0edf6.jpeg', '', '2020-06-27 19:02:29', '2020-06-27 21:28:10');

-- ----------------------------
-- Table structure for content_banner_item
-- ----------------------------
DROP TABLE IF EXISTS `content_banner_item`;
CREATE TABLE `content_banner_item`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `banner_id` bigint(20) UNSIGNED NOT NULL COMMENT 'banner表外键',
  `banner_item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'banner_item内容管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content_banner_item
-- ----------------------------
INSERT INTO `content_banner_item` VALUES (1, 2, '', '1f15e10bee7546d59469b55b8cbb711b.jpeg', '2020-06-28 13:27:19', '2020-06-28 13:27:19');
INSERT INTO `content_banner_item` VALUES (2, 1, '', 'bfe5714801994748a102fe88c1e91c62.jpeg', '2020-06-28 13:33:39', '2020-06-28 13:33:39');
INSERT INTO `content_banner_item` VALUES (3, 1, '', 'a7dbafdce4c0474183e7c06227642583.jpeg', '2020-06-28 13:33:53', '2020-06-28 13:33:53');
INSERT INTO `content_banner_item` VALUES (4, 1, '', '97b7df27d64744c1a7f3f5fccb6671d6.jpeg', '2020-06-28 13:36:02', '2020-06-28 13:36:02');
INSERT INTO `content_banner_item` VALUES (5, 1, '', 'aa9e2fe2e9354495a4bd9369aaf105ed.jpeg', '2020-06-28 13:42:13', '2020-06-28 13:42:13');
INSERT INTO `content_banner_item` VALUES (13, 1, '', '06abe618298649bb9ebcbda62befc7a2.jpeg', '2020-06-28 14:17:49', '2020-06-28 14:19:57');

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delivery_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机',
  `delivery_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `delivery_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `delivery_sex` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别;0：未知，1:男，2：女',
  `delivery_idcard` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态：1-可用，0-为不可用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配送人员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (1, '13800138000', NULL, '王某某', 1, NULL, 1, NULL, '2020-06-22 00:22:39', '2020-06-22 00:22:39');

-- ----------------------------
-- Table structure for generator_config
-- ----------------------------
DROP TABLE IF EXISTS `generator_config`;
CREATE TABLE `generator_config`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `base_package` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '基础包名',
  `module_package` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名',
  `entity_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'entity文件存放路径',
  `mapper_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper文件存放路径',
  `mapper_xml_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'mapper xml文件存放路径',
  `service_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'servcie文件存放路径',
  `service_impl_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'serviceImpl文件存放路径',
  `controller_package` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'controller文件存放路径',
  `is_trim` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否去除前缀 1是 0否',
  `trim_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前缀内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of generator_config
-- ----------------------------
INSERT INTO `generator_config` VALUES (1, 'sxquan', 'com.sxquan.manage', 'content', 'pojo', 'mapper', 'mapper', 'service', 'service.impl', 'controller', '1', 'content_');

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `login_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '登录时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `systems` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_login
-- ----------------------------
INSERT INTO `log_login` VALUES (3, 'admin', '2020-05-25 23:22:26', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 8');
INSERT INTO `log_login` VALUES (4, 'admin', '2020-05-26 00:57:49', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (5, 'admin', '2020-05-26 13:32:33', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (6, 'admin', '2020-05-26 14:28:35', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (7, 'admin', '2020-05-27 00:00:36', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 70');
INSERT INTO `log_login` VALUES (8, 'admin', '2020-05-27 00:48:42', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (9, 'admin', '2020-05-28 00:57:27', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (10, 'admin', '2020-05-28 14:29:27', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (11, 'admin', '2020-05-28 17:00:37', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (12, 'admin', '2020-05-28 21:50:15', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (13, 'admin', '2020-05-28 21:50:31', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (14, 'admin', '2020-05-28 21:51:06', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (15, 'admin', '2020-06-05 14:03:08', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (16, 'admin', '2020-06-08 15:43:19', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (17, 'admin', '2020-06-08 16:43:29', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (18, 'admin', '2020-06-08 20:43:48', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (19, 'janc', '2020-06-11 19:11:38', '0|0|0|内网IP|内网IP', '192.168.1.107', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (20, 'admin', '2020-06-11 19:11:53', '0|0|0|内网IP|内网IP', '192.168.1.107', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (21, 'admin', '2020-06-11 19:12:22', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (22, 'admin', '2020-06-12 23:56:24', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (23, 'admin', '2020-06-19 16:46:27', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (24, 'admin', '2020-06-22 00:29:06', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (25, 'admin', '2020-06-22 16:30:20', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (26, 'admin', '2020-06-24 17:59:39', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (27, 'admin', '2020-06-27 12:59:10', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (28, 'admin', '2020-06-27 16:05:34', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (29, 'admin', '2020-06-27 16:45:16', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (30, 'admin', '2020-06-27 21:11:49', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (31, 'admin', '2020-06-28 00:25:05', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (32, 'admin', '2020-06-28 13:05:44', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');
INSERT INTO `log_login` VALUES (33, 'admin', '2020-06-30 00:31:33', '0|0|0|内网IP|内网IP', '127.0.0.1', 'Windows 10', 'Chrome 80');

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_user_id` bigint(20) NULL DEFAULT NULL COMMENT '管理员ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理者名字',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '方法参数',
  `run_time` int(11) UNSIGNED NOT NULL COMMENT '耗时',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作地点',
  `result` tinyint(1) NULL DEFAULT 1 COMMENT '操作结果:0-失败，1-成功',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 546 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员操作日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
INSERT INTO `operation_log` VALUES (382, 1, 'admin', '新增改SPU', 'com.sxquan.manage.business.controller.SpuController.addSpu', 'spu{\"categoryId\":17,\"mergerCategoryId\":\"3,17\",\"specGroup\":\"3\",\"spuId\":21,\"status\":1,\"subhead\":\"234324\",\"title\":\"23434\",\"unit\":\"瓶\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 22:20:24');
INSERT INTO `operation_log` VALUES (383, 1, 'admin', '新增SKU', 'com.sxquan.manage.business.controller.SkuController.addSku', 'sku{\"code\":\"21$3-6\",\"originPrice\":234,\"paramIds\":\"6\",\"skuId\":11,\"spuId\":\"21\",\"status\":1,\"title\":\"234\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 22:20:38');
INSERT INTO `operation_log` VALUES (384, 1, 'admin', '新增SKU', 'com.sxquan.manage.business.controller.SkuController.addSku', 'sku{\"code\":\"21$3-10\",\"inventory\":213,\"originPrice\":12,\"paramIds\":\"10\",\"skuId\":12,\"spuId\":\"21\",\"status\":1,\"title\":\"243\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 22:20:56');
INSERT INTO `operation_log` VALUES (385, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryProductController.categoryProductAdd', 'categoryProduct{\"categoryId\":56,\"categoryName\":\"234234\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 22:21:10');
INSERT INTO `operation_log` VALUES (386, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryProductController.categoryProductAdd', 'categoryProduct{\"categoryId\":57,\"categoryName\":\"123\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 22:21:19');
INSERT INTO `operation_log` VALUES (387, 1, 'admin', '添加用户', 'com.sxquan.manage.system.controller.SystemUserController.addUser', 'systemUser{\"avatar\":\"default.jpg\",\"password\":\"858e27b742fa51082f49b2c2f95a10bf\",\"roleIds\":\"1\",\"sex\":0,\"status\":1,\"systemUserId\":106,\"username\":\"2313\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 23:04:34');
INSERT INTO `operation_log` VALUES (388, 1, 'admin', '添加用户', 'com.sxquan.manage.system.controller.SystemUserController.addUser', 'systemUser{\"avatar\":\"default.jpg\",\"password\":\"0ad93e1d478b4509933fc9619b909794\",\"roleIds\":\"3\",\"sex\":0,\"status\":1,\"systemUserId\":107,\"username\":\"123354\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-20 23:04:45');
INSERT INTO `operation_log` VALUES (389, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-verticalright\",\"menuId\":10132,\"menuName\":\"代码生成\",\"parentId\":0,\"sortOrder\":4,\"type\":0} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 00:33:58');
INSERT INTO `operation_log` VALUES (390, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-setting\",\"menuId\":10133,\"menuName\":\"生成配置\",\"parentId\":10132,\"perms\":\"generator:configure:view\",\"sortOrder\":1,\"type\":1,\"url\":\"/generator/configure\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 00:37:21');
INSERT INTO `operation_log` VALUES (391, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 00:37:34');
INSERT INTO `operation_log` VALUES (392, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-codepen\",\"menuId\":10132,\"menuName\":\"代码生成\",\"parentId\":0,\"sortOrder\":4,\"type\":0} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 16:46:08');
INSERT INTO `operation_log` VALUES (393, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-wrench\",\"menuId\":10133,\"menuName\":\"生成配置\",\"parentId\":10132,\"perms\":\"generator:configure:view\",\"sortOrder\":1,\"type\":1,\"url\":\"/generator/configure\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 16:58:27');
INSERT INTO `operation_log` VALUES (394, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-align-center\",\"menuId\":10014,\"menuName\":\"分类管理\",\"parentId\":0,\"sortOrder\":4,\"type\":0} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:09:25');
INSERT INTO `operation_log` VALUES (395, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-piechart\",\"menuId\":10015,\"menuName\":\"商品分类\",\"parentId\":10014,\"perms\":\"productCategory:view\",\"sortOrder\":1,\"type\":1,\"url\":\"/category/product\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:11:47');
INSERT INTO `operation_log` VALUES (396, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-interspace\",\"menuId\":10132,\"menuName\":\"代码生成\",\"parentId\":0,\"sortOrder\":4,\"type\":0} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:15:25');
INSERT INTO `operation_log` VALUES (397, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-similar-product\",\"menuId\":10134,\"menuName\":\"生成代码\",\"parentId\":10132,\"perms\":\"generator:view\",\"sortOrder\":2,\"type\":1,\"url\":\"/generator\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:26:16');
INSERT INTO `operation_log` VALUES (398, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:26:31');
INSERT INTO `operation_log` VALUES (399, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-similar-product\",\"menuId\":10134,\"menuName\":\"生成代码\",\"parentId\":10132,\"perms\":\"generator:view\",\"sortOrder\":2,\"type\":1,\"url\":\"/generator/generate\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 17:29:45');
INSERT INTO `operation_log` VALUES (400, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10135,\"menuName\":\"更新生成配置\",\"parentId\":10133,\"perms\":\"generator:configure:update\",\"type\":2} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 22:50:16');
INSERT INTO `operation_log` VALUES (401, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 22:50:29');
INSERT INTO `operation_log` VALUES (402, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxquan.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 22:52:30');
INSERT INTO `operation_log` VALUES (403, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 22:55:41');
INSERT INTO `operation_log` VALUES (404, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 22:57:09');
INSERT INTO `operation_log` VALUES (405, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 22:57:23');
INSERT INTO `operation_log` VALUES (406, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 22:58:03');
INSERT INTO `operation_log` VALUES (407, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:01:59');
INSERT INTO `operation_log` VALUES (408, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:05:37');
INSERT INTO `operation_log` VALUES (409, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"status\":1,\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:15:28');
INSERT INTO `operation_log` VALUES (410, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"status\":1,\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:17:21');
INSERT INTO `operation_log` VALUES (411, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"status\":1,\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:17:51');
INSERT INTO `operation_log` VALUES (412, 1, 'admin', '修改GeneratorConfig失败', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"status\":1,\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-05-22 23:19:02');
INSERT INTO `operation_log` VALUES (413, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"MrBird\",\"basePackage\":\"cc.mrbird.febs.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"entity\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"status\":1,\"trimValue\":\"\"} ', 1600, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 23:22:04');
INSERT INTO `operation_log` VALUES (414, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxuqan.gen\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-22 23:22:58');
INSERT INTO `operation_log` VALUES (415, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10136,\"menuName\":\"生成代码\",\"parentId\":10134,\"perms\":\"generator:generate\",\"type\":2} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-23 00:14:47');
INSERT INTO `operation_log` VALUES (416, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-similar-product\",\"menuId\":10134,\"menuName\":\"代码生成\",\"parentId\":10132,\"perms\":\"generator:view\",\"sortOrder\":2,\"type\":1,\"url\":\"/generator/generate\"} ', 1200, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-23 00:15:02');
INSERT INTO `operation_log` VALUES (417, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-23 00:15:21');
INSERT INTO `operation_log` VALUES (418, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxuqan.manage.monitor\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-24 00:19:33');
INSERT INTO `operation_log` VALUES (419, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxquan.manage.monitor\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 520, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-24 00:21:42');
INSERT INTO `operation_log` VALUES (420, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxquan.manage\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"modulePackage\":\"monitor\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-24 01:38:53');
INSERT INTO `operation_log` VALUES (421, 1, 'admin', '添加用户', 'com.sxquan.manage.system.controller.SystemUserController.addUser', 'systemUser{\"avatar\":\"default.jpg\",\"password\":\"0bbd8dca7120d2d3bbf03e62db2a8274\",\"roleIds\":\"3\",\"sex\":0,\"status\":1,\"systemUserId\":108,\"username\":\"12335\"} ', 88, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 16:55:44');
INSERT INTO `operation_log` VALUES (422, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"c191fc5a94c64aaaaa5636f47b6f5657.jpeg\",\"categoryId\":38,\"cover\":\"e168c117b1914ca8bce4f2eb58b3a59c.jpeg\",\"description\":\"\",\"detailImg\":\"\",\"mergerCategoryId\":\"3,38\",\"originPrice\":127,\"specGroup\":\"1\",\"spuId\":10,\"status\":2,\"subhead\":\"蓝鳍金枪鱼大脂\",\"title\":\"蓝鳍金枪鱼大脂\",\"unit\":\"盒\"} ', 67, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 18:08:50');
INSERT INTO `operation_log` VALUES (423, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"\",\"categoryId\":7,\"cover\":\"5231f20fb8554fbf890a3c284d008e8d.jpeg\",\"description\":\"\",\"detailImg\":\"\",\"mergerCategoryId\":\"1,7\",\"originPrice\":12,\"specGroup\":\"1\",\"spuId\":11,\"status\":0,\"subhead\":\"越南火龙果\",\"title\":\"越南火龙果\",\"unit\":\"份\"} ', 105, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 18:49:19');
INSERT INTO `operation_log` VALUES (424, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-solution\",\"menuId\":10137,\"menuName\":\"登录日志\",\"parentId\":0,\"sortOrder\":2,\"type\":0} ', 322, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:04:42');
INSERT INTO `operation_log` VALUES (425, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-solution\",\"menuId\":10137,\"menuName\":\"登录日志\",\"parentId\":10001,\"perms\":\"logLogin:view\",\"sortOrder\":2,\"type\":1,\"url\":\"/monitor/logLogin\"} ', 74, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:05:40');
INSERT INTO `operation_log` VALUES (426, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1503, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:05:52');
INSERT INTO `operation_log` VALUES (427, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10138,\"menuName\":\"删除登录日志\",\"parentId\":10137,\"perms\":\"logLogin:delete\",\"type\":2} ', 340, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:14:25');
INSERT INTO `operation_log` VALUES (428, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1511, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:14:42');
INSERT INTO `operation_log` VALUES (429, 1, 'admin', '删除登录日志信息', 'com.sxquan.manage.monitor.controller.LogLoginController.deleteLogLogin', 'ids\"1\" ', 44, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:15:35');
INSERT INTO `operation_log` VALUES (430, 1, 'admin', '删除角色', 'com.sxquan.manage.system.controller.SystemRoleController.deleteRole', 'roleIds\"36,37,38,39,40\" ', 132, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-25 22:25:19');
INSERT INTO `operation_log` VALUES (431, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-commodity\",\"menuId\":10139,\"menuName\":\"swagger文档\",\"parentId\":10001,\"perms\":\"swagger:view\",\"sortOrder\":4,\"type\":1,\"url\":\"/swagger-ui.html\"} ', 51, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-26 13:33:46');
INSERT INTO `operation_log` VALUES (432, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1523, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-26 13:33:58');
INSERT INTO `operation_log` VALUES (433, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryProductController.categoryProductAdd', 'categoryProduct{\"categoryId\":58,\"categoryImg\":\"\",\"categoryName\":\"\",\"description\":\"\"} ', 311, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-26 14:30:25');
INSERT INTO `operation_log` VALUES (434, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-commodity\",\"menuId\":10139,\"menuName\":\"swagger文档\",\"parentId\":10001,\"perms\":\"swagger:view\",\"sortOrder\":4,\"type\":1,\"url\":\"/doc.html\"} ', 309, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-26 14:32:20');
INSERT INTO `operation_log` VALUES (435, 1, 'admin', '删除登录日志信息', 'com.sxquan.manage.monitor.controller.LogLoginController.deleteLogLogin', 'ids\"2\" ', 71, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-27 01:01:21');
INSERT INTO `operation_log` VALUES (436, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxquan.manage\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"0\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"modulePackage\":\"content\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"\"} ', 57, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 14:39:26');
INSERT INTO `operation_log` VALUES (437, 1, 'admin', '修改GeneratorConfig', 'com.sxquan.manage.generator.controller.GeneratorConfigController.updateGeneratorConfig', 'generatorConfig{\"author\":\"sxquan\",\"basePackage\":\"com.sxquan.manage\",\"controllerPackage\":\"controller\",\"entityPackage\":\"pojo\",\"id\":1,\"isTrim\":\"1\",\"mapperPackage\":\"mapper\",\"mapperXmlPackage\":\"mapper\",\"modulePackage\":\"content\",\"serviceImplPackage\":\"service.impl\",\"servicePackage\":\"service\",\"trimValue\":\"content_\"} ', 72, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 14:39:55');
INSERT INTO `operation_log` VALUES (438, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-read\",\"menuId\":10140,\"menuName\":\"CMS管理\",\"parentId\":0,\"sortOrder\":5,\"type\":0} ', 316, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 17:02:48');
INSERT INTO `operation_log` VALUES (439, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-desktop\",\"menuId\":10141,\"menuName\":\"Banner管理\",\"parentId\":10140,\"perms\":\"banner:view\",\"sortOrder\":1,\"type\":1,\"url\":\"/content/banner\"} ', 305, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 17:04:39');
INSERT INTO `operation_log` VALUES (440, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1626, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 17:04:50');
INSERT INTO `operation_log` VALUES (441, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10142,\"menuName\":\"新增Banner\",\"parentId\":10141,\"perms\":\"banner:add\",\"type\":2} ', 348, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 21:37:11');
INSERT INTO `operation_log` VALUES (442, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10143,\"menuName\":\"删除Banner\",\"parentId\":10141,\"perms\":\"banner:delete\",\"type\":2} ', 78, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 21:37:58');
INSERT INTO `operation_log` VALUES (443, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10144,\"menuName\":\"修改banner\",\"parentId\":10141,\"perms\":\"banner:update\",\"type\":2} ', 45, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 21:38:26');
INSERT INTO `operation_log` VALUES (444, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1734, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-05-28 21:38:50');
INSERT INTO `operation_log` VALUES (445, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"6aff2e7016ce4ae7a82314de691dc68b.jpeg,c558f3913e4e40629ab02bd96bbce67e.jpeg,7b313003fb1e449fabe4dc91133b98cb.jpeg,ec1ce23e8f874c86b16481308a3d399f.jpeg\",\"cover\":\"0f6dff5c307c4354ac71a3863657b57e.jpeg\",\"description\":\"\",\"detailImg\":\"df26fe6dd3c34ea1a80494b4a7959b5c.jpeg\",\"mergerCategoryId\":\"1,7\",\"originPrice\":68.8,\"specGroup\":\"1\",\"spuId\":1,\"status\":1,\"subhead\":\"啊克苏冰糖心苹果\",\"title\":\"啊克苏冰糖心苹果\",\"unit\":\"份\"} ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:28:54');
INSERT INTO `operation_log` VALUES (446, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"c9496407020841798b517d9adf641411.jpeg\",\"cover\":\"fafba0e84850440aa54de8337e4673cc.jpeg\",\"description\":\"\",\"detailImg\":\"df26fe6dd3c34ea1a80494b4a7959b5c.jpeg\",\"mergerCategoryId\":\"1,7\",\"originPrice\":20,\"specGroup\":\"1\",\"spuId\":2,\"status\":1,\"subhead\":\"冰淇淋西瓜\",\"title\":\"冰淇淋西瓜\",\"unit\":\"份\"} ', 102, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:29:25');
INSERT INTO `operation_log` VALUES (447, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"icon\":\"layui-icon-piechart\",\"menuId\":10015,\"menuName\":\"商品分类\",\"parentId\":10014,\"perms\":\"goodsCategory:view\",\"sortOrder\":1,\"type\":1,\"url\":\"/category/goods\"} ', 39, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:32:19');
INSERT INTO `operation_log` VALUES (448, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10035,\"menuName\":\"新增分类\",\"parentId\":10015,\"perms\":\"goodsCategory:add\",\"type\":2} ', 102, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:32:27');
INSERT INTO `operation_log` VALUES (449, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10036,\"menuName\":\"修改分类\",\"parentId\":10015,\"perms\":\"goodsCategory:update\",\"type\":2} ', 62, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:32:40');
INSERT INTO `operation_log` VALUES (450, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10037,\"menuName\":\"删除分类\",\"parentId\":10015,\"perms\":\"goodsCategory:delete\",\"type\":2} ', 62, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:32:51');
INSERT INTO `operation_log` VALUES (451, 1, 'admin', '删除商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductDelete', 'categoryIds\"57\" ', 46, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:43:46');
INSERT INTO `operation_log` VALUES (452, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":8,\"categoryImg\":\"\",\"categoryName\":\"饮料酒水\",\"description\":\"\",\"sortOrder\":0,\"status\":1} ', 66, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:43:58');
INSERT INTO `operation_log` VALUES (453, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":7,\"categoryImg\":\"\",\"categoryName\":\"热带水果\",\"description\":\"\",\"sortOrder\":23,\"status\":1} ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:48:49');
INSERT INTO `operation_log` VALUES (454, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductAdd', 'categoryGoods{\"categoryId\":59,\"categoryName\":\"23213\",\"status\":1} ', 64, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:50:38');
INSERT INTO `operation_log` VALUES (455, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductAdd', 'categoryGoods{\"categoryId\":60,\"categoryName\":\"123\",\"parentId\":59,\"sortOrder\":123,\"status\":1} ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:53:20');
INSERT INTO `operation_log` VALUES (456, 1, 'admin', '删除商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductDelete', 'categoryIds\"60\" ', 68, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:53:23');
INSERT INTO `operation_log` VALUES (457, 1, 'admin', '新增改SPU', 'com.sxquan.manage.business.controller.SpuController.addSpu', 'spu{\"mergerCategoryId\":\"1,7\",\"specGroup\":\"2\",\"spuId\":22,\"status\":1,\"subhead\":\"123\",\"title\":\"123\",\"unit\":\"盒\"} ', 78, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 16:58:43');
INSERT INTO `operation_log` VALUES (458, 1, 'admin', '修改店铺', 'com.sxquan.manage.business.controller.ShopInfoController.updateShopInfo', 'shopInfo{\"address\":\"123\",\"beginTime\":\"00:00\",\"businessHours\":\"00:00:00 - 00:00:00\",\"contactMan\":\"\",\"contactMobile\":\"\",\"endTime\":\"00:00\",\"latitude\":\"\",\"longitude\":\"\",\"shopInfoId\":10002,\"shopName\":\"123\",\"status\":1,\"storeImg\":\"2d395179845948b7b34858448ffe5582.png\"} ', 79, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:02:12');
INSERT INTO `operation_log` VALUES (459, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"85b3678034d54b2b86fae18a11454a6e.jpeg,9436e886fcbb49d69e8561d5a1b2bcf9.png\",\"cover\":\"4f405d762195425f835c6de08cc11349.png\",\"description\":\"\",\"detailImg\":\"df26fe6dd3c34ea1a80494b4a7959b5c.jpeg\",\"mergerCategoryId\":\"2,12\",\"originPrice\":0,\"specGroup\":\"1\",\"spuId\":20,\"status\":1,\"subhead\":\"饼干\",\"title\":\"我以为天理，何必了？你觉得我应该怎么办，我要杀戮！\",\"unit\":\"瓶\"} ', 79, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:03:30');
INSERT INTO `operation_log` VALUES (460, 1, 'admin', '修改SKU', 'com.sxquan.manage.business.controller.SkuController.updateSku', 'sku{\"code\":\"1$1-7\",\"image\":\"2abd4dc05a1e44d99a5b312761e83cf2.jpeg\",\"inventory\":2001,\"originPrice\":12.8,\"paramIds\":\"7\",\"skuId\":1,\"spuId\":\"1\",\"status\":1,\"title\":\"阿克苏冰糖心苹果 3个装 （900g+）\"} ', 133, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:04:00');
INSERT INTO `operation_log` VALUES (461, 1, 'admin', '修改规格组', 'com.sxquan.manage.spec.controller.SpecGroupController.specGroupUpdate', 'specGroup{\"description\":\"\",\"groupName\":\"包装\",\"specGroupId\":3,\"standard\":false} ', 62, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:06:23');
INSERT INTO `operation_log` VALUES (462, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"\",\"cover\":\"93299f7978264598881553460a38c0a4.jpeg\",\"description\":\"\",\"detailImg\":\"df26fe6dd3c34ea1a80494b4a7959b5c.jpeg\",\"mergerCategoryId\":\"8,37\",\"originPrice\":239,\"specGroup\":\"2,3\",\"spuId\":7,\"status\":1,\"subhead\":\"锐澳鸡尾酒人气套装\",\"title\":\"锐澳鸡尾酒人气套装\",\"unit\":\"份\"} ', 109, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:20:48');
INSERT INTO `operation_log` VALUES (463, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"c191fc5a94c64aaaaa5636f47b6f5657.jpeg\",\"cover\":\"e168c117b1914ca8bce4f2eb58b3a59c.jpeg\",\"description\":\"\",\"detailImg\":\"\",\"mergerCategoryId\":\"3,38\",\"originPrice\":127,\"specGroup\":\"1\",\"spuId\":10,\"status\":0,\"subhead\":\"蓝鳍金枪鱼大脂\",\"title\":\"蓝鳍金枪鱼大脂\",\"unit\":\"盒\"} ', 96, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:21:13');
INSERT INTO `operation_log` VALUES (464, 1, 'admin', '修改SPU', 'com.sxquan.manage.business.controller.SpuController.updateSpu', 'spu{\"bannerImages\":\"467103ec8e2c46c794f9cc8e6af6915f.jpeg,eaf7beb591de4629b537082327ca3e50.jpeg\",\"cover\":\"a7a444bcc0fe4475bfa814942a07717c.jpeg\",\"description\":\"\",\"detailImg\":\"df26fe6dd3c34ea1a80494b4a7959b5c.jpeg\",\"mergerCategoryId\":\"5,26\",\"originPrice\":18,\"specGroup\":\"1\",\"spuId\":8,\"status\":1,\"subhead\":\"香辣牛肉酱\",\"title\":\"香辣牛肉酱\",\"unit\":\"瓶\"} ', 75, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 17:21:34');
INSERT INTO `operation_log` VALUES (465, 1, 'admin', '修改规格组', 'com.sxquan.manage.spec.controller.SpecGroupController.specGroupUpdate', 'specGroup{\"description\":\"\",\"groupName\":\"包装\",\"specGroupId\":3,\"standard\":true} ', 320, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 20:52:50');
INSERT INTO `operation_log` VALUES (466, 1, 'admin', '修改规格组', 'com.sxquan.manage.spec.controller.SpecGroupController.specGroupUpdate', 'specGroup{\"description\":\"\",\"groupName\":\"包装\",\"specGroupId\":3,\"standard\":false} ', 73, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 20:52:54');
INSERT INTO `operation_log` VALUES (467, 1, 'admin', '新增规格属性', 'com.sxquan.manage.spec.controller.SpecParamController.addSpecParam', 'specParam{\"paramName\":\"2132\",\"specGroupId\":2,\"specParamId\":12} ', 84, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 21:40:19');
INSERT INTO `operation_log` VALUES (468, 1, 'admin', '删除规格属性', 'com.sxquan.manage.spec.controller.SpecParamController.deleteSpecParam', 'specParamIds\"12\" ', 80, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 21:40:27');
INSERT INTO `operation_log` VALUES (469, 1, 'admin', '修改SKU', 'com.sxquan.manage.business.controller.SkuController.updateSku', 'sku{\"code\":\"20$1-4\",\"image\":\"93085426c3b24866a4c86d0eb446b5d2.png\",\"inventory\":20,\"originPrice\":20,\"paramIds\":\"4\",\"skuId\":10,\"spuId\":\"20\",\"status\":1,\"title\":\"饼干\"} ', 163, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:14:26');
INSERT INTO `operation_log` VALUES (470, 1, 'admin', '新增规格属性', 'com.sxquan.manage.spec.controller.SpecParamController.addSpecParam', 'specParam{\"paramName\":\"312\",\"specGroupId\":3,\"specParamId\":13} ', 90, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:18:01');
INSERT INTO `operation_log` VALUES (471, 1, 'admin', '修改规格组', 'com.sxquan.manage.spec.controller.SpecGroupController.specGroupUpdate', 'specGroup{\"description\":\"\",\"groupName\":\"包装\",\"specGroupId\":3,\"standard\":true} ', 303, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:19:58');
INSERT INTO `operation_log` VALUES (472, 1, 'admin', '修改规格属性', 'com.sxquan.manage.spec.controller.SpecParamController.updateSpecParam', 'specParam{\"paramName\":\"312e\",\"specParamId\":13} ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:20:05');
INSERT INTO `operation_log` VALUES (473, 1, 'admin', '修改SKU', 'com.sxquan.manage.business.controller.SkuController.updateSku', 'sku{\"code\":\"21$3-13\",\"inventory\":0,\"originPrice\":234,\"paramIds\":\"13\",\"skuId\":11,\"spuId\":\"21\",\"status\":1,\"title\":\"234\"} ', 137, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:20:21');
INSERT INTO `operation_log` VALUES (474, 1, 'admin', '修改SKU', 'com.sxquan.manage.business.controller.SkuController.updateSku', 'sku{\"code\":\"9$1-11\",\"image\":\"7051b1620b124b8fbf870ff75d39ec52.png\",\"inventory\":100,\"originPrice\":12.8,\"paramIds\":\"11\",\"skuId\":5,\"spuId\":\"9\",\"status\":1,\"title\":\"新鲜现摘蓝莓 （380g）\"} ', 124, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-08 22:28:58');
INSERT INTO `operation_log` VALUES (475, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":7,\"categoryImg\":\"dbef074cab85491585ea9e5f7c9518ee.jpeg\",\"categoryName\":\"热带水果\",\"description\":\"\",\"sortOrder\":23,\"status\":1} ', 49, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:02:49');
INSERT INTO `operation_log` VALUES (476, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":9,\"categoryImg\":\"60365b1d1f604938bc412e1b8aca335b.jpeg\",\"categoryName\":\"橘柑橙柚\",\"description\":\"\",\"sortOrder\":5,\"status\":1} ', 75, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:05:14');
INSERT INTO `operation_log` VALUES (477, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":10,\"categoryImg\":\"b64553fda05147728626b55e356ffec3.jpeg\",\"categoryName\":\"苹果香蕉\",\"description\":\"\",\"sortOrder\":1,\"status\":1} ', 48, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:05:49');
INSERT INTO `operation_log` VALUES (478, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":12,\"categoryImg\":\"25fc2c435c644f27b69ba3fd881b0feb.jpeg\",\"categoryName\":\"有机蔬菜\",\"description\":\"\",\"status\":1} ', 74, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:08:56');
INSERT INTO `operation_log` VALUES (479, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":13,\"categoryImg\":\"d7b4760bfce94f6286501716315cf55c.jpeg\",\"categoryName\":\"叶菜\",\"description\":\"\",\"status\":1} ', 67, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:09:03');
INSERT INTO `operation_log` VALUES (480, 1, 'admin', '添加商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductAdd', 'categoryGoods{\"categoryId\":61,\"categoryImg\":\"793cb3d2b5b546918f2a8031361b241f.jpeg\",\"categoryName\":\"西红柿/黄瓜\",\"parentId\":2,\"status\":1} ', 62, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:09:29');
INSERT INTO `operation_log` VALUES (481, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":18,\"categoryImg\":\"bbbcda74461248a1b2784749b68ac81a.jpeg\",\"categoryName\":\"鸡蛋/蛋类\",\"description\":\"\",\"status\":1} ', 44, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:11:17');
INSERT INTO `operation_log` VALUES (482, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":17,\"categoryImg\":\"88e256fd247c4c5c808b98c0fd841005.jpeg\",\"categoryName\":\"鸡/鸭/鸽\",\"description\":\"\",\"status\":1} ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:12:29');
INSERT INTO `operation_log` VALUES (483, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":19,\"categoryImg\":\"29f186005d32487e9967e5cdb0be5f0a.jpeg\",\"categoryName\":\"牛肉\",\"description\":\"\",\"status\":1} ', 64, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:12:41');
INSERT INTO `operation_log` VALUES (484, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":16,\"categoryImg\":\"ce2e791705bf46dcb8270c5d245e4396.jpeg\",\"categoryName\":\"猪肉\",\"description\":\"\",\"status\":1} ', 39, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-13 00:12:57');
INSERT INTO `operation_log` VALUES (485, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":8,\"categoryImg\":\"3404661405b94b3b902feb444bf25a5e.jpeg\",\"categoryName\":\"饮料酒水\",\"description\":\"\",\"sortOrder\":0,\"status\":1} ', 50, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-24 18:01:07');
INSERT INTO `operation_log` VALUES (486, 1, 'admin', '删除商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductDelete', 'categoryIds\"58\" ', 72, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-24 18:01:12');
INSERT INTO `operation_log` VALUES (487, 1, 'admin', '删除商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductDelete', 'categoryIds\"59\" ', 65, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-24 18:01:14');
INSERT INTO `operation_log` VALUES (488, 1, 'admin', '删除商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductDelete', 'categoryIds\"56\" ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-24 18:01:16');
INSERT INTO `operation_log` VALUES (489, 1, 'admin', '修改商品分类/子分类', 'com.sxquan.manage.category.controller.CategoryGoodsController.categoryProductUpdate', 'categoryGoods{\"categoryId\":4,\"categoryImg\":\"1f8529bd834e4f03881f466826f9b6e5.jpeg\",\"categoryName\":\"速食\",\"description\":\"\",\"sortOrder\":1,\"status\":1} ', 71, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-24 18:01:30');
INSERT INTO `operation_log` VALUES (490, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":1,\"bannerName\":\"b-1\",\"description\":\"\",\"img\":\"dc8e62acfebd4702b8131bccea5aa1dc.jpeg\",\"title\":\"\"} ', 71, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 17:30:09');
INSERT INTO `operation_log` VALUES (491, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":2,\"bannerName\":\"t-2\",\"description\":\"\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"\"} ', 85, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 19:02:30');
INSERT INTO `operation_log` VALUES (492, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":3,\"bannerName\":\"t-2\",\"description\":\"首页顶部主banner\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"\"} ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:12:26');
INSERT INTO `operation_log` VALUES (493, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":4,\"bannerName\":\"t-2\",\"description\":\"bannerId\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"\"} ', 93, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:13:26');
INSERT INTO `operation_log` VALUES (494, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":5,\"bannerName\":\"t-2\",\"description\":\"首页顶部主banner\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"12\"} ', 77, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:13:38');
INSERT INTO `operation_log` VALUES (495, 1, 'admin', '删除Banner', 'com.sxquan.manage.content.controller.BannerController.deleteBanner', 'ids\"5\" ', 99, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:16:25');
INSERT INTO `operation_log` VALUES (496, 1, 'admin', '删除Banner', 'com.sxquan.manage.content.controller.BannerController.deleteBanner', 'ids\"4\" ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:16:27');
INSERT INTO `operation_log` VALUES (497, 1, 'admin', '删除Banner', 'com.sxquan.manage.content.controller.BannerController.deleteBanner', 'ids\"3\" ', 35, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:16:29');
INSERT INTO `operation_log` VALUES (498, 1, 'admin', '新增Banner失败', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":1,\"bannerName\":\"b-1\",\"description\":\"\",\"title\":\"\"} ', 193, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 21:18:48');
INSERT INTO `operation_log` VALUES (499, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":6,\"bannerName\":\"b-1\",\"description\":\"12\",\"img\":\"Untitled\",\"title\":\"\"} ', 68, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:19:30');
INSERT INTO `operation_log` VALUES (500, 1, 'admin', '删除Banner', 'com.sxquan.manage.content.controller.BannerController.deleteBanner', 'ids\"6\" ', 90, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:19:39');
INSERT INTO `operation_log` VALUES (501, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":7,\"bannerName\":\"b-1\",\"description\":\"12\",\"img\":\"Untitled\",\"title\":\"\"} ', 85, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:21:26');
INSERT INTO `operation_log` VALUES (502, 1, 'admin', '新增Banner', 'com.sxquan.manage.content.controller.BannerController.addBanner', 'banner{\"bannerId\":8,\"bannerName\":\"b-1\",\"description\":\"121\",\"img\":\"Untitled\",\"title\":\"\"} ', 97, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:22:03');
INSERT INTO `operation_log` VALUES (503, 1, 'admin', '删除Banner', 'com.sxquan.manage.content.controller.BannerController.deleteBanner', 'ids\"7,8\" ', 98, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:23:37');
INSERT INTO `operation_log` VALUES (504, 1, 'admin', '修改Banner', 'com.sxquan.manage.content.controller.BannerController.updateBanner', 'banner{\"bannerId\":1,\"bannerName\":\"b-1\",\"description\":\"\",\"title\":\"\"} ', 7, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:23:42');
INSERT INTO `operation_log` VALUES (505, 1, 'admin', '修改Banner', 'com.sxquan.manage.content.controller.BannerController.updateBanner', 'banner{\"bannerId\":1,\"bannerName\":\"b-1\",\"description\":\"首页顶部主banner\",\"title\":\"\"} ', 56, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:26:40');
INSERT INTO `operation_log` VALUES (506, 1, 'admin', '修改Banner', 'com.sxquan.manage.content.controller.BannerController.updateBanner', 'banner{\"bannerId\":2,\"bannerName\":\"b-2\",\"description\":\"\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"\"} ', 67, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 21:28:10');
INSERT INTO `operation_log` VALUES (507, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10145,\"menuName\":\"新增BannerItem\",\"parentId\":10141,\"perms\":\"bannerItem:add\",\"type\":2} ', 82, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 22:32:53');
INSERT INTO `operation_log` VALUES (508, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10146,\"menuName\":\"修改BannerItem\",\"parentId\":10141,\"perms\":\"bannerItem\",\"type\":2} ', 303, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 22:33:18');
INSERT INTO `operation_log` VALUES (509, 1, 'admin', '修改菜单', 'com.sxquan.manage.system.controller.SystemMenuController.updateMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10146,\"menuName\":\"修改BannerItem\",\"parentId\":10141,\"perms\":\"bannerItem:update\",\"type\":2} ', 68, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 22:33:31');
INSERT INTO `operation_log` VALUES (510, 1, 'admin', '新增菜单', 'com.sxquan.manage.system.controller.SystemMenuController.addMenu', 'systemMenu{\"descpt\":\"\",\"menuId\":10147,\"menuName\":\"删除BannerItem\",\"parentId\":10141,\"perms\":\"bannerItem:delete\",\"type\":2} ', 298, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-27 22:33:53');
INSERT INTO `operation_log` VALUES (511, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1760, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:34:14');
INSERT INTO `operation_log` VALUES (512, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1692, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:34:20');
INSERT INTO `operation_log` VALUES (513, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1944, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:35:11');
INSERT INTO `operation_log` VALUES (514, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1743, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:38:27');
INSERT INTO `operation_log` VALUES (515, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1958, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:40:21');
INSERT INTO `operation_log` VALUES (516, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1980, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:40:53');
INSERT INTO `operation_log` VALUES (517, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1753, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:47:30');
INSERT INTO `operation_log` VALUES (518, 1, 'admin', '修改角色失败', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1959, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-27 22:53:53');
INSERT INTO `operation_log` VALUES (519, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1736, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 00:25:41');
INSERT INTO `operation_log` VALUES (520, 1, 'admin', '修改角色', 'com.sxquan.manage.system.controller.SystemRoleController.updateRole', 'systemRole{\"roleDesc\":\"系统管理员，拥有所有操作权限 ^_^\",\"roleId\":1,\"roleName\":\"超级管理员\",\"status\":1} ', 1699, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 00:25:49');
INSERT INTO `operation_log` VALUES (521, 1, 'admin', '修改Banner', 'com.sxquan.manage.content.controller.BannerController.updateBanner', 'banner{\"bannerId\":2,\"bannerName\":\"b-2\",\"description\":\"\",\"img\":\"96f90911a4b949918a092eb0e8c0edf6.jpeg\",\"title\":\"\"} ', 21, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 01:47:35');
INSERT INTO `operation_log` VALUES (522, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":2,\"bannerItemName\":\"\",\"id\":1,\"img\":\"1f15e10bee7546d59469b55b8cbb711b.jpeg\"} ', 75, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:27:19');
INSERT INTO `operation_log` VALUES (523, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":2,\"img\":\"bfe5714801994748a102fe88c1e91c62.jpeg\"} ', 312, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:33:40');
INSERT INTO `operation_log` VALUES (524, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":3,\"img\":\"a7dbafdce4c0474183e7c06227642583.jpeg\"} ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:33:53');
INSERT INTO `operation_log` VALUES (525, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":4,\"img\":\"97b7df27d64744c1a7f3f5fccb6671d6.jpeg\"} ', 116, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:36:02');
INSERT INTO `operation_log` VALUES (526, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":5,\"img\":\"aa9e2fe2e9354495a4bd9369aaf105ed.jpeg\"} ', 36, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:42:13');
INSERT INTO `operation_log` VALUES (527, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":6,\"img\":\"038c121eb9834da1b45ee275ebb19a84.jpeg\"} ', 118, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:44:34');
INSERT INTO `operation_log` VALUES (528, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"6\" ', 72, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:45:21');
INSERT INTO `operation_log` VALUES (529, 1, 'admin', '新增BannerItem失败', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\"} ', 140, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-28 13:47:17');
INSERT INTO `operation_log` VALUES (530, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":7,\"img\":\"b875894da0444926819968611798e721.jpeg\"} ', 97, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:47:45');
INSERT INTO `operation_log` VALUES (531, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":8,\"img\":\"3e87535c922642128386c711ae6c04ae.jpeg\"} ', 64, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:48:37');
INSERT INTO `operation_log` VALUES (532, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"8\" ', 87, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:49:02');
INSERT INTO `operation_log` VALUES (533, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"7\" ', 25, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:49:04');
INSERT INTO `operation_log` VALUES (534, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":9,\"img\":\"8364bcc6650a431eac982b2d957b118e.jpeg\"} ', 67, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:49:09');
INSERT INTO `operation_log` VALUES (535, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":10,\"img\":\"de5ebbb0c4ec4da78b28030926224465.jpeg\"} ', 90, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 13:56:28');
INSERT INTO `operation_log` VALUES (536, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"10\" ', 87, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:09');
INSERT INTO `operation_log` VALUES (537, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"9\" ', 28, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:10');
INSERT INTO `operation_log` VALUES (538, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":11,\"img\":\"3134f849d1d9459182a89535bba3c711.jpeg\"} ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:18');
INSERT INTO `operation_log` VALUES (539, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":12,\"img\":\"760144b0d3034c08aa9b1b1dfd87932b.jpeg\"} ', 70, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:31');
INSERT INTO `operation_log` VALUES (540, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"12\" ', 66, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:35');
INSERT INTO `operation_log` VALUES (541, 1, 'admin', '删除BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.deleteBannerItem', 'ids\"11\" ', 68, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:01:45');
INSERT INTO `operation_log` VALUES (542, 1, 'admin', '新增BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerId\":1,\"bannerItemName\":\"\",\"id\":13,\"img\":\"acad54af806e49f1962a3b6f4af1a16f.jpeg\"} ', 324, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:17:50');
INSERT INTO `operation_log` VALUES (543, 1, 'admin', '新增BannerItem失败', 'com.sxquan.manage.content.controller.BannerItemController.addBannerItem', 'bannerItem{\"bannerItemName\":\"\",\"img\":\"b5d7b4134ec4432b906e6c8908d09600.jpeg\"} ', 65, '127.0.0.1', '0|0|0|内网IP|内网IP', 0, '2020-06-28 14:18:00');
INSERT INTO `operation_log` VALUES (544, 1, 'admin', '修改BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.updateBannerItem', 'bannerItem{\"bannerItemName\":\"\",\"img\":\"0549b048c98246a8911cceea2b21f3c1.jpeg\"} ', 9, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:18:34');
INSERT INTO `operation_log` VALUES (545, 1, 'admin', '修改BannerItem', 'com.sxquan.manage.content.controller.BannerItemController.updateBannerItem', 'bannerItem{\"bannerItemName\":\"\",\"id\":13,\"img\":\"06abe618298649bb9ebcbda62befc7a2.jpeg\"} ', 69, '127.0.0.1', '0|0|0|内网IP|内网IP', 1, '2020-06-28 14:19:57');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `sku_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品sku_id',
  `spec_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规格信息',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标题',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品封面',
  `price` decimal(10, 2) NOT NULL COMMENT '原价',
  `total_price` decimal(10, 2) NOT NULL COMMENT '总价',
  `amount` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '下单数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单商品详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (14, '1200622015823693', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (21, '1200622763933700', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (22, '1200622763933700', 4, '500g', '香辣牛肉酱 （500g）', '11beb1263b084febbc7802f2873c1aaf.jpeg', 18.00, 18.00, 1);
INSERT INTO `order_item` VALUES (23, '1200622764703701', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (24, '1200622764703701', 4, '500g', '香辣牛肉酱 （500g）', '11beb1263b084febbc7802f2873c1aaf.jpeg', 18.00, 18.00, 1);
INSERT INTO `order_item` VALUES (25, '1200622767803702', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (26, '1200622767803702', 4, '500g', '香辣牛肉酱 （500g）', '11beb1263b084febbc7802f2873c1aaf.jpeg', 18.00, 18.00, 1);
INSERT INTO `order_item` VALUES (27, '1200622768673703', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (28, '1200622768673703', 4, '500g', '香辣牛肉酱 （500g）', '11beb1263b084febbc7802f2873c1aaf.jpeg', 18.00, 18.00, 1);
INSERT INTO `order_item` VALUES (29, '1200622847643704', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (30, '1200622849283705', 2, '500g', '冰淇淋西瓜 （1.7kg+）', '1f5b4188745143e498aea971f88efee9.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (31, '1200622850203706', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (32, '1200622854003707', 5, '380g', '新鲜现摘蓝莓 （380g）', '7051b1620b124b8fbf870ff75d39ec52.png', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (33, '1200623041033709', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (34, '1200623041033708', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (35, '1200623510143708', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (36, '1200623510313709', 3, '500ml*6，组合装', '锐澳鸡尾酒人气套装', 'ebf25d583d504260a5a3721c45e96610.jpeg', 128.00, 128.00, 1);
INSERT INTO `order_item` VALUES (37, '1200623510323710', 3, '500ml*6，组合装', '锐澳鸡尾酒人气套装', 'ebf25d583d504260a5a3721c45e96610.jpeg', 128.00, 128.00, 1);
INSERT INTO `order_item` VALUES (38, '1200623510763711', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (39, '1200623510823712', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (40, '1200623510893713', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (41, '1200623526843714', 2, '500g', '冰淇淋西瓜 （1.7kg+）', '1f5b4188745143e498aea971f88efee9.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (42, '1200624028033715', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (43, '1200624507973716', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (44, '1200627045933718', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (45, '1200627049233719', 2, '500g', '冰淇淋西瓜 （1.7kg+）', '1f5b4188745143e498aea971f88efee9.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (46, '1200629617763718', 2, '500g', '冰淇淋西瓜 （1.7kg+）', '1f5b4188745143e498aea971f88efee9.jpeg', 12.80, 12.80, 1);
INSERT INTO `order_item` VALUES (47, '1200629617763718', 1, '900g', '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 12.80, 1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `pay_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '支付类型，1、在线支付，2、货到付款',
  `delivery_mode` tinyint(4) NULL DEFAULT NULL COMMENT '配送方式：1-自提，2-商品配送',
  `user_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `user_true_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机号码',
  `province_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址第一级地址',
  `city_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址第二级地址',
  `county_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址第三级地址',
  `user_address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户详细地址',
  `shop_info_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商铺ID',
  `send_cost` decimal(10, 2) NOT NULL COMMENT '配送费',
  `total_money` decimal(12, 2) NOT NULL COMMENT '总价',
  `discount_money` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `coupon_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '优惠劵ID',
  `pay_money` decimal(12, 2) NULL COMMENT '实付金额',
  `delivery_id` bigint(20) UNSIGNED NOT NULL COMMENT '送货员ID',
  `delivery_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '送货员姓名',
  `delivery_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '送货员联系电话',
  `is_expired` tinyint(1) NULL DEFAULT NULL COMMENT '是否过期：0-未过期，1-已过期',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态;0：取消订单,1:等待买家付款,2:买家已付款,3:等待商家送货,4:商家已送达，5：确定收货，6：订单完成',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `reserve_date` date NULL DEFAULT NULL COMMENT '预约配送日期',
  `reserve_over_time` time(0) NULL DEFAULT NULL COMMENT '预约配送的时间（结束）时间点',
  `reserve_start_time` time(0) NULL DEFAULT NULL COMMENT '预约配送的时间（开始）时间点',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (14, '1200622015823693', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-22', '09:30:00', '09:00:00', '2020-06-22 00:26:22', '2020-06-22 21:15:45');
INSERT INTO `orders` VALUES (15, '1200622763933700', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 30.80, NULL, '0', 30.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '09:30:00', '09:00:00', '2020-06-22 21:13:13', '2020-06-22 21:13:13');
INSERT INTO `orders` VALUES (16, '1200622764703701', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 30.80, NULL, '0', 30.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '09:30:00', '09:00:00', '2020-06-22 21:14:30', '2020-06-22 21:14:30');
INSERT INTO `orders` VALUES (17, '1200622767803702', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 30.80, NULL, '0', 30.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '11:00:00', '10:30:00', '2020-06-22 21:19:40', '2020-06-22 21:19:40');
INSERT INTO `orders` VALUES (18, '1200622768673703', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 30.80, NULL, '0', 30.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '09:30:00', '09:00:00', '2020-06-22 21:21:07', '2020-06-22 21:21:07');
INSERT INTO `orders` VALUES (19, '1200622847643704', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '10:30:00', '10:00:00', '2020-06-22 23:32:44', '2020-06-22 23:32:44');
INSERT INTO `orders` VALUES (20, '1200622849283705', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '09:30:00', '09:00:00', '2020-06-22 23:35:28', '2020-06-22 23:35:28');
INSERT INTO `orders` VALUES (21, '1200622850203706', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '09:30:00', '09:00:00', '2020-06-22 23:37:01', '2020-06-22 23:37:00');
INSERT INTO `orders` VALUES (22, '1200622854003707', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '10:30:00', '10:00:00', '2020-06-22 23:43:20', '2020-06-22 23:43:20');
INSERT INTO `orders` VALUES (23, '1200623041033709', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '11:00:00', '10:30:00', '2020-06-23 01:08:23', '2020-06-23 01:08:23');
INSERT INTO `orders` VALUES (24, '1200623041033708', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '11:00:00', '10:30:00', '2020-06-23 01:08:23', '2020-06-23 01:08:23');
INSERT INTO `orders` VALUES (25, '1200623510143708', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:10:15', '2020-06-23 14:10:14');
INSERT INTO `orders` VALUES (26, '1200623510313709', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 128.00, NULL, '0', 128.00, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:10:32', '2020-06-23 14:10:31');
INSERT INTO `orders` VALUES (27, '1200623510323710', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 128.00, NULL, '0', 128.00, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:10:32', '2020-06-23 14:10:32');
INSERT INTO `orders` VALUES (28, '1200623510763711', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:11:17', '2020-06-23 14:11:16');
INSERT INTO `orders` VALUES (29, '1200623510823712', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:11:22', '2020-06-23 14:11:22');
INSERT INTO `orders` VALUES (30, '1200623510893713', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 3, NULL, NULL, '2020-06-23', '15:00:00', '14:30:00', '2020-06-23 14:11:29', '2020-06-27 01:35:12');
INSERT INTO `orders` VALUES (31, '1200623526843714', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-23', '15:30:00', '15:00:00', '2020-06-23 14:38:05', '2020-06-23 14:38:04');
INSERT INTO `orders` VALUES (32, '1200624028033715', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 6, NULL, NULL, '2020-06-24', '09:30:00', '09:00:00', '2020-06-24 00:46:44', '2020-06-27 01:35:16');
INSERT INTO `orders` VALUES (33, '1200624507973716', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-24', '15:00:00', '14:30:00', '2020-06-24 14:06:38', '2020-06-24 14:06:37');
INSERT INTO `orders` VALUES (34, '1200627045933718', 1, NULL, 1, '老王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-27', '09:30:00', '09:00:00', '2020-06-27 01:16:33', '2020-06-27 01:16:33');
INSERT INTO `orders` VALUES (35, '1200627049233719', 1, NULL, 1, '老王王', '17876777777', '广东省', '广州市', '海珠区', '某某某', 0, 0.00, 12.80, NULL, '0', 12.80, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-27', '09:30:00', '09:00:00', '2020-06-27 01:22:03', '2020-06-27 01:22:03');
INSERT INTO `orders` VALUES (36, '1200629617763718', 1, NULL, 1, '张三', '020-81167888', '广东省', '广州市', '海珠区', '新港中路397号', 0, 0.00, 25.60, NULL, '0', 25.60, 1, '王某某', '13800138000', NULL, NULL, 1, NULL, NULL, '2020-06-29', '18:00:00', '17:30:00', '2020-06-29 17:09:36', '2020-06-29 17:09:36');

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log`  (
  `pay_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `transaction_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交易号码',
  `total_fee` decimal(12, 2) NULL DEFAULT NULL COMMENT '支付金额（分）',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `trade_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易状态',
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `pay_type` tinyint(4) NULL DEFAULT NULL COMMENT '支付类型：1-微信,2-支付宝',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付完成时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`pay_id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单支付日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay_log
-- ----------------------------

-- ----------------------------
-- Table structure for shop_info
-- ----------------------------
DROP TABLE IF EXISTS `shop_info`;
CREATE TABLE `shop_info`  (
  `shop_info_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shop_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商店名称',
  `contact_man` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `cate_id` tinyint(4) NULL DEFAULT NULL COMMENT '门店类型',
  `begin_time` time(0) NULL DEFAULT NULL COMMENT '营业开始时间',
  `end_time` time(0) NULL DEFAULT NULL COMMENT '营业结束时间',
  `store_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '门店图片',
  `instore_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '店内图片',
  `logo_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo图片',
  `longitude` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `province` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区',
  `street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '街道/城镇',
  `region` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联动路径',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他图片',
  `notice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商家公告',
  `send_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '配送时间',
  `send_cost` decimal(10, 2) NULL COMMENT '配送费用',
  `floor_send_cost` decimal(10, 2) NOT NULL COMMENT '起送消费',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态:0-休息，1-营业',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`shop_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10003 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商铺信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop_info
-- ----------------------------
INSERT INTO `shop_info` VALUES (10000, '华南店', '某某', '13800000000', NULL, '08:00:00', '20:00:00', '06b5d2accf6e4523ba9e563d9b1da567.jpeg', NULL, NULL, NULL, NULL, '江苏省', '九江市', '修水县', '上奉镇', '320000,360400,360424,29605', '213', NULL, NULL, NULL, '2020-04-25 21:09:41', 0.00, 0.00, 1, '2020-02-28 15:43:48', '2020-04-25 21:09:41');
INSERT INTO `shop_info` VALUES (10001, '东北店', NULL, NULL, NULL, '09:00:00', '21:00:00', '963f7cb059ff4ae5adb448079f5bb873.jpeg', NULL, NULL, NULL, NULL, '山东省', '青岛市', '城阳区', '上马街道', '370000,370200,370214,13053', '略略', NULL, NULL, NULL, '2020-04-03 00:12:24', 0.00, 0.00, 1, '2020-03-06 19:09:59', '2020-04-03 00:12:24');
INSERT INTO `shop_info` VALUES (10002, '123', '', '', NULL, '00:00:00', '00:00:00', '2d395179845948b7b34858448ffe5582.png', NULL, NULL, '', '', '云南省', '丽江市', '古城区', '大东乡', '530000,530700,530702,43497', '123', NULL, NULL, NULL, '2020-06-08 17:02:12', 0.00, 0.00, 1, '2020-05-18 00:31:59', '2020-06-08 17:02:12');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `sku_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编码',
  `spu_id` bigint(20) UNSIGNED NOT NULL COMMENT 'spu_id关联',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `origin_price` decimal(10, 2) NOT NULL COMMENT '原价',
  `sell_price` decimal(10, 2) NULL COMMENT '售价',
  `discount` decimal(10, 2) NULL COMMENT '折扣',
  `own_spec` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'sku的特有规格参数，json格式',
  `inventory` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除；0:未删除，1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, '1$1-7', 1, '阿克苏冰糖心苹果 3个装 （900g+）', '2abd4dc05a1e44d99a5b312761e83cf2.jpeg', 12.80, 0.00, 0.00, NULL, 2001, 1, 0, '2020-03-17 12:59:55', '2020-06-08 17:04:00');
INSERT INTO `sku` VALUES (2, '2$1-1', 2, '冰淇淋西瓜 （1.7kg+）', '1f5b4188745143e498aea971f88efee9.jpeg', 12.80, 0.00, 0.00, NULL, 200, 1, 0, '2020-03-17 13:10:53', '2020-04-03 00:17:25');
INSERT INTO `sku` VALUES (3, '7$2-9#3-10', 7, '锐澳鸡尾酒人气套装', 'ebf25d583d504260a5a3721c45e96610.jpeg', 128.00, 0.00, 0.00, NULL, 200, 1, 0, '2020-03-17 13:13:15', '2020-04-03 00:18:23');
INSERT INTO `sku` VALUES (4, '8$1-1', 8, '香辣牛肉酱 （500g）', '11beb1263b084febbc7802f2873c1aaf.jpeg', 18.00, 0.00, 0.00, NULL, 200, 1, 0, '2020-03-17 13:13:38', '2020-04-13 00:32:21');
INSERT INTO `sku` VALUES (5, '9$1-11', 9, '新鲜现摘蓝莓 （380g）', '7051b1620b124b8fbf870ff75d39ec52.png', 12.80, 0.00, 0.00, NULL, 100, 1, 0, '2020-03-17 21:08:45', '2020-06-08 22:28:58');
INSERT INTO `sku` VALUES (10, '20$1-4', 20, '饼干', '93085426c3b24866a4c86d0eb446b5d2.png', 20.00, 0.00, 0.00, NULL, 20, 1, 0, '2020-05-01 15:47:50', '2020-06-08 22:14:26');
INSERT INTO `sku` VALUES (11, '21$3-13', 21, '234', NULL, 234.00, 0.00, 0.00, NULL, 0, 1, 0, '2020-05-20 22:20:38', '2020-06-08 22:20:21');
INSERT INTO `sku` VALUES (12, '21$3-10', 21, '243', NULL, 12.00, 0.00, 0.00, NULL, 213, 1, 0, '2020-05-20 22:20:56', '2020-05-20 22:20:56');

-- ----------------------------
-- Table structure for spec_group
-- ----------------------------
DROP TABLE IF EXISTS `spec_group`;
CREATE TABLE `spec_group`  (
  `spec_group_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cg_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规格组的名称',
  `is_standard` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否为通用属性组：0-不是，1-是',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`spec_group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '规格组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spec_group
-- ----------------------------
INSERT INTO `spec_group` VALUES (1, NULL, '规格', 1, '10', '2020-03-11 14:05:29', '2020-04-25 18:42:59');
INSERT INTO `spec_group` VALUES (2, NULL, '净含量', 1, NULL, '2020-03-11 14:08:26', '2020-03-16 00:56:27');
INSERT INTO `spec_group` VALUES (3, NULL, '包装', 1, '', '2020-03-11 14:10:41', '2020-06-08 22:19:58');

-- ----------------------------
-- Table structure for spec_group_spu
-- ----------------------------
DROP TABLE IF EXISTS `spec_group_spu`;
CREATE TABLE `spec_group_spu`  (
  `spec_group_id` bigint(20) UNSIGNED NOT NULL COMMENT '规格组id',
  `spu_id` bigint(20) UNSIGNED NOT NULL COMMENT 'spuid'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'SPU与规格组中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spec_group_spu
-- ----------------------------
INSERT INTO `spec_group_spu` VALUES (1, 9);
INSERT INTO `spec_group_spu` VALUES (1, 12);
INSERT INTO `spec_group_spu` VALUES (1, 13);
INSERT INTO `spec_group_spu` VALUES (1, 14);
INSERT INTO `spec_group_spu` VALUES (1, 15);
INSERT INTO `spec_group_spu` VALUES (3, 15);
INSERT INTO `spec_group_spu` VALUES (1, 16);
INSERT INTO `spec_group_spu` VALUES (2, 16);
INSERT INTO `spec_group_spu` VALUES (1, 17);
INSERT INTO `spec_group_spu` VALUES (2, 17);
INSERT INTO `spec_group_spu` VALUES (1, 18);
INSERT INTO `spec_group_spu` VALUES (1, 19);
INSERT INTO `spec_group_spu` VALUES (3, 21);
INSERT INTO `spec_group_spu` VALUES (1, 11);
INSERT INTO `spec_group_spu` VALUES (1, 1);
INSERT INTO `spec_group_spu` VALUES (1, 2);
INSERT INTO `spec_group_spu` VALUES (2, 22);
INSERT INTO `spec_group_spu` VALUES (1, 20);
INSERT INTO `spec_group_spu` VALUES (2, 7);
INSERT INTO `spec_group_spu` VALUES (3, 7);
INSERT INTO `spec_group_spu` VALUES (1, 10);
INSERT INTO `spec_group_spu` VALUES (1, 8);

-- ----------------------------
-- Table structure for spec_param
-- ----------------------------
DROP TABLE IF EXISTS `spec_param`;
CREATE TABLE `spec_param`  (
  `spec_param_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `spec_group_id` bigint(20) NOT NULL COMMENT '关联groip表id',
  `param_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名',
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数字类型参数的单位，非数字类型可以为空',
  `is_number` tinyint(1) NULL DEFAULT NULL COMMENT '是否是数字类型参数，0-false,1-true',
  `generic` tinyint(1) NULL DEFAULT NULL COMMENT '是否是sku通用属性，0-false,1-true',
  `searching` tinyint(1) NULL DEFAULT NULL COMMENT '是否用于搜索过滤，0-false,1-true',
  `segments` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`spec_param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '规格组下的参数表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spec_param
-- ----------------------------
INSERT INTO `spec_param` VALUES (1, 1, '500g', NULL, NULL, NULL, NULL, NULL, '2020-03-11 23:23:13', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (2, 1, '350g', NULL, NULL, NULL, NULL, NULL, '2020-03-11 23:44:07', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (3, 1, '1kg', NULL, NULL, NULL, NULL, NULL, '2020-03-11 23:47:28', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (4, 1, '300g', NULL, NULL, NULL, NULL, NULL, '2020-03-11 23:47:42', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (5, 2, '500g', NULL, NULL, NULL, NULL, NULL, '2020-03-16 00:57:00', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (6, 3, '6枚', NULL, NULL, NULL, NULL, NULL, '2020-03-16 01:00:35', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (7, 1, '900g', NULL, NULL, NULL, NULL, NULL, '2020-04-02 14:12:28', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (8, 1, '1.5kg', NULL, NULL, NULL, NULL, NULL, '2020-04-02 14:16:04', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (9, 2, '500ml*6', NULL, NULL, NULL, NULL, NULL, '2020-04-02 14:18:19', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (10, 3, '组合装', NULL, NULL, NULL, NULL, NULL, '2020-04-02 14:18:34', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (11, 1, '380g', NULL, NULL, NULL, NULL, NULL, '2020-04-02 14:24:04', '2020-06-08 20:44:20');
INSERT INTO `spec_param` VALUES (13, 3, '312e', NULL, NULL, NULL, NULL, NULL, '2020-06-08 22:18:01', '2020-06-08 22:20:05');

-- ----------------------------
-- Table structure for spec_param_sku
-- ----------------------------
DROP TABLE IF EXISTS `spec_param_sku`;
CREATE TABLE `spec_param_sku`  (
  `spec_param_id` bigint(20) UNSIGNED NOT NULL COMMENT '规格参数id',
  `sku_id` bigint(20) UNSIGNED NOT NULL COMMENT 'skuId'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'SKU与规格参数中间表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spec_param_sku
-- ----------------------------
INSERT INTO `spec_param_sku` VALUES (1, 2);
INSERT INTO `spec_param_sku` VALUES (9, 3);
INSERT INTO `spec_param_sku` VALUES (10, 3);
INSERT INTO `spec_param_sku` VALUES (1, 4);
INSERT INTO `spec_param_sku` VALUES (10, 12);
INSERT INTO `spec_param_sku` VALUES (7, 1);
INSERT INTO `spec_param_sku` VALUES (4, 10);
INSERT INTO `spec_param_sku` VALUES (13, 11);
INSERT INTO `spec_param_sku` VALUES (11, 5);

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu`  (
  `spu_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品标题',
  `subhead` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '副标题',
  `cover` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品封面主图',
  `banner_images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品轮播图',
  `detail_img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情图',
  `origin_price` decimal(10, 2) NULL COMMENT '原价',
  `sell_price` decimal(10, 2) NULL COMMENT '售价',
  `discount` decimal(10, 2) NULL COMMENT '折扣',
  `collect` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '收藏数',
  `limit_num` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '限购数量',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位',
  `total_sales` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '总的销量',
  `month_sales` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '月销量',
  `praise_rate` decimal(5, 2) NULL DEFAULT 100.00 COMMENT '好评率',
  `special_spec` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '特有规格参数及可选值信息，json格式',
  `after_service` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '售后服务',
  `generic_spec` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通用规格参数',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态：0：下架，1：上架',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除；0:未删除，1：已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`spu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'SPU信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES (1, '啊克苏冰糖心苹果', '啊克苏冰糖心苹果', '0f6dff5c307c4354ac71a3863657b57e.jpeg', '6aff2e7016ce4ae7a82314de691dc68b.jpeg,c558f3913e4e40629ab02bd96bbce67e.jpeg,7b313003fb1e449fabe4dc91133b98cb.jpeg,ec1ce23e8f874c86b16481308a3d399f.jpeg', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 68.80, 0.00, 0.00, 0, NULL, '份', NULL, NULL, 100.00, NULL, NULL, NULL, '', 1, 0, '2020-03-14 01:12:24', '2020-06-08 16:28:54');
INSERT INTO `spu` VALUES (2, '冰淇淋西瓜', '冰淇淋西瓜', 'fafba0e84850440aa54de8337e4673cc.jpeg', 'c9496407020841798b517d9adf641411.jpeg', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 20.00, 0.00, 0.00, 0, NULL, '份', NULL, NULL, 100.00, NULL, NULL, NULL, '', 1, 0, '2020-03-14 01:16:35', '2020-06-08 16:29:24');
INSERT INTO `spu` VALUES (7, '锐澳鸡尾酒人气套装', '锐澳鸡尾酒人气套装', '93299f7978264598881553460a38c0a4.jpeg', '', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 239.00, 0.00, 0.00, 0, NULL, '份', NULL, NULL, 100.00, NULL, NULL, NULL, '', 1, 0, '2020-03-14 14:12:42', '2020-06-08 17:20:48');
INSERT INTO `spu` VALUES (8, '香辣牛肉酱', '香辣牛肉酱', 'a7a444bcc0fe4475bfa814942a07717c.jpeg', '467103ec8e2c46c794f9cc8e6af6915f.jpeg,eaf7beb591de4629b537082327ca3e50.jpeg', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 18.00, 0.00, 0.00, 0, NULL, '瓶', NULL, NULL, 100.00, NULL, NULL, NULL, '', 1, 0, '2020-03-15 16:21:55', '2020-06-08 17:21:34');
INSERT INTO `spu` VALUES (9, '新鲜现摘蓝莓', '新鲜现摘蓝莓', 'd29f47f24c8047fd83c427a9dd9b4bff.jpeg', 'c0355769ab184fa3a8518c002f3722c4.jpeg', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 12.80, 0.00, 0.00, 0, NULL, '份', NULL, NULL, 100.00, NULL, NULL, NULL, NULL, 1, 0, '2020-04-02 14:06:19', '2020-06-05 18:48:22');
INSERT INTO `spu` VALUES (10, '蓝鳍金枪鱼大脂', '蓝鳍金枪鱼大脂', 'e168c117b1914ca8bce4f2eb58b3a59c.jpeg', 'c191fc5a94c64aaaaa5636f47b6f5657.jpeg', '', 127.00, 0.00, 0.00, 0, NULL, '盒', NULL, NULL, 100.00, NULL, NULL, NULL, '', 0, 0, '2020-04-02 14:07:38', '2020-05-25 18:49:08');
INSERT INTO `spu` VALUES (11, '越南火龙果', '越南火龙果', '5231f20fb8554fbf890a3c284d008e8d.jpeg', '', '', 12.00, 0.00, 0.00, 0, NULL, '份', NULL, NULL, 100.00, NULL, NULL, NULL, '', 0, 0, '2020-04-03 00:22:51', '2020-05-25 18:49:19');
INSERT INTO `spu` VALUES (20, '我以为天理，何必了？你觉得我应该怎么办，我要杀戮！', '饼干', '4f405d762195425f835c6de08cc11349.png', '85b3678034d54b2b86fae18a11454a6e.jpeg,9436e886fcbb49d69e8561d5a1b2bcf9.png', 'df26fe6dd3c34ea1a80494b4a7959b5c.jpeg', 0.00, 0.00, 0.00, 0, NULL, '瓶', NULL, NULL, 100.00, NULL, NULL, NULL, '', 1, 0, '2020-05-01 15:47:03', '2020-06-08 17:03:30');
INSERT INTO `spu` VALUES (21, '23434', '234324', NULL, NULL, NULL, 0.00, 0.00, 0.00, 0, NULL, '瓶', NULL, NULL, 100.00, NULL, NULL, NULL, NULL, 1, 0, '2020-05-20 22:20:24', '2020-06-27 15:37:40');
INSERT INTO `spu` VALUES (22, '123', '123', NULL, NULL, NULL, 0.00, 0.00, 0.00, 0, NULL, '盒', NULL, NULL, 100.00, NULL, NULL, NULL, NULL, 1, 1, '2020-06-08 16:58:42', '2020-06-08 17:21:40');

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu`  (
  `menu_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '上级菜单id,父类别id当id=0时说明是根节点,一级类别',
  `menu_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单/按钮名称',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '类型 0-目录,1-菜单,2-按钮',
  `sort_order` int(4) NULL DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `descpt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10148 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES (10000, 0, '系统管理', 'layui-icon-setting', NULL, NULL, 0, 1, '', '2019-12-24 14:35:59', '2020-02-25 14:34:04');
INSERT INTO `system_menu` VALUES (10001, 0, '系统监控', 'layui-icon-alert', NULL, NULL, 0, 5, '', '2019-12-24 14:35:59', '2020-05-12 00:08:13');
INSERT INTO `system_menu` VALUES (10002, 10000, '用户管理', 'layui-icon-user', '/system/user', 'user:view', 1, 1, '', '2019-12-24 14:35:59', '2020-02-24 19:56:07');
INSERT INTO `system_menu` VALUES (10003, 10000, '角色管理', 'layui-icon-rolePermission', '/system/role', 'role:view', 1, 2, '', '2019-12-24 14:35:59', '2020-02-25 16:47:56');
INSERT INTO `system_menu` VALUES (10004, 10000, '菜单管理', 'layui-icon-menu1', '/system/menu', 'menu:view', 1, 3, '', '2019-12-24 14:35:59', '2020-02-25 16:48:54');
INSERT INTO `system_menu` VALUES (10007, 10001, '操作日志', 'layui-icon-logOption1', '/monitor/operationLog', 'operationLog:view', 1, 1, '', '2019-12-24 14:35:59', '2020-03-30 23:03:36');
INSERT INTO `system_menu` VALUES (10008, 10002, '新增用户', NULL, NULL, 'user:add', 2, NULL, NULL, '2020-02-14 14:10:05', '2020-02-14 14:11:31');
INSERT INTO `system_menu` VALUES (10009, 10002, '修改用户', NULL, NULL, 'user:update', 2, NULL, NULL, '2020-02-14 14:10:05', '2020-02-14 14:11:33');
INSERT INTO `system_menu` VALUES (10010, 10002, '删除用户', NULL, NULL, 'user:delete', 2, NULL, NULL, '2020-02-14 14:10:05', '2020-02-14 14:11:34');
INSERT INTO `system_menu` VALUES (10011, 0, '业务管理', 'layui-icon-areachart', NULL, NULL, 0, 2, '', '2020-02-25 17:51:30', '2020-04-21 13:24:16');
INSERT INTO `system_menu` VALUES (10012, 10001, 'druid监控', 'layui-icon-fire', '/druid', 'druid:view', 1, 2, '', '2020-02-26 00:22:30', '2020-02-26 00:23:35');
INSERT INTO `system_menu` VALUES (10013, 10011, '店铺管理', 'layui-icon-shop', '/business/shopInfo', 'shopInfo:view', 1, 1, '', '2020-02-26 13:29:37', '2020-02-26 13:29:37');
INSERT INTO `system_menu` VALUES (10014, 0, '分类管理', 'layui-icon-align-center', NULL, NULL, 0, 4, '', '2020-02-26 21:35:34', '2020-05-22 17:09:24');
INSERT INTO `system_menu` VALUES (10015, 10014, '商品分类', 'layui-icon-piechart', '/category/goods', 'goodsCategory:view', 1, 1, '', '2020-03-08 17:45:36', '2020-06-08 16:32:18');
INSERT INTO `system_menu` VALUES (10016, 0, '规格管理', 'layui-icon-radarchart', NULL, NULL, 0, 6, '', '2020-03-11 00:34:23', '2020-05-12 00:08:20');
INSERT INTO `system_menu` VALUES (10017, 10016, '规格组管理', 'layui-icon-pushpin', '/spec/group', 'specGroup:view', 1, 1, '', '2020-03-11 00:36:02', '2020-03-19 18:06:01');
INSERT INTO `system_menu` VALUES (10018, 10011, 'SPU管理', 'layui-icon-commodity', '/business/spu', 'spu:view', 1, 2, '', '2020-03-16 00:48:11', '2020-03-16 00:49:50');
INSERT INTO `system_menu` VALUES (10019, 10011, 'SKU管理', 'layui-icon-flag', '/business/sku', 'sku:view', 1, 3, '', '2020-03-16 01:03:25', '2020-03-16 01:03:25');
INSERT INTO `system_menu` VALUES (10020, 10003, '新增角色', NULL, NULL, 'role:add', 2, NULL, '', '2020-03-19 16:53:33', '2020-03-19 16:53:33');
INSERT INTO `system_menu` VALUES (10021, 10003, '修改角色', NULL, NULL, 'role:update', 2, NULL, '', '2020-03-19 16:54:22', '2020-03-19 16:54:22');
INSERT INTO `system_menu` VALUES (10022, 10003, '删除角色', NULL, NULL, 'role:delete', 2, NULL, '', '2020-03-19 16:54:51', '2020-03-19 16:54:51');
INSERT INTO `system_menu` VALUES (10023, 10004, '新增菜单', NULL, NULL, 'menu:add', 2, NULL, NULL, '2020-03-19 16:55:47', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10024, 10004, '修改菜单', NULL, NULL, 'menu:update', 2, NULL, NULL, '2020-03-19 16:55:47', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10025, 10004, '删除菜单', NULL, NULL, 'menu:delete', 2, NULL, NULL, '2020-03-19 16:55:47', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10026, 10013, '新增店铺', NULL, NULL, 'shopInfo:add', 2, NULL, NULL, '2020-03-19 17:00:02', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10027, 10013, '修改店铺', NULL, NULL, 'shopInfo:update', 2, NULL, NULL, '2020-03-19 17:00:03', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10028, 10013, '删除店铺', NULL, NULL, 'shopInfo:delete', 2, NULL, NULL, '2020-03-19 17:00:03', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10029, 10018, '新增SPU', NULL, NULL, 'spu:add', 2, NULL, NULL, '2020-03-19 17:01:18', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10030, 10018, '修改SPU', NULL, NULL, 'spu:update', 2, NULL, NULL, '2020-03-19 17:01:19', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10031, 10018, '删除SPU', NULL, NULL, 'spu:delete', 2, NULL, NULL, '2020-03-19 17:01:19', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10032, 10019, '新增SKU', NULL, NULL, 'sku:add', 2, NULL, NULL, '2020-03-19 17:02:30', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10033, 10019, '修改SKU', NULL, NULL, 'sku:update', 2, NULL, NULL, '2020-03-19 17:03:13', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10034, 10019, '删除SKU', NULL, NULL, 'sku:delete', 2, NULL, NULL, '2020-03-19 17:03:57', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10035, 10015, '新增分类', NULL, NULL, 'goodsCategory:add', 2, NULL, '', '2020-03-19 17:04:37', '2020-06-08 16:32:27');
INSERT INTO `system_menu` VALUES (10036, 10015, '修改分类', NULL, NULL, 'goodsCategory:update', 2, NULL, '', '2020-03-19 17:04:53', '2020-06-08 16:32:40');
INSERT INTO `system_menu` VALUES (10037, 10015, '删除分类', NULL, NULL, 'goodsCategory:delete', 2, NULL, '', '2020-03-19 17:04:53', '2020-06-08 16:32:51');
INSERT INTO `system_menu` VALUES (10038, 10017, '新增规格组', NULL, NULL, 'specGroup:add', 2, NULL, NULL, '2020-03-19 17:04:53', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10039, 10017, '修改规格组', NULL, NULL, 'specGroup:update', 2, NULL, NULL, '2020-03-19 18:04:45', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10040, 10017, '删除规格组', NULL, NULL, 'specGroup:delete', 2, NULL, NULL, '2020-03-19 18:05:16', '2020-03-19 19:08:09');
INSERT INTO `system_menu` VALUES (10041, 10017, '新增规格', NULL, NULL, 'specParam:add', 2, NULL, '', '2020-03-19 19:15:22', '2020-03-19 19:15:22');
INSERT INTO `system_menu` VALUES (10042, 10017, '修改规格', NULL, NULL, 'specParam:update', 2, NULL, '', '2020-03-19 19:15:59', '2020-03-19 19:15:59');
INSERT INTO `system_menu` VALUES (10043, 10017, '删除规格', NULL, NULL, 'specParam:delete', 2, NULL, '', '2020-03-19 19:17:54', '2020-03-19 19:17:54');
INSERT INTO `system_menu` VALUES (10044, 10007, '删除日志', NULL, NULL, 'operationLog:delete', 2, NULL, '', '2020-03-29 13:31:18', '2020-03-29 13:31:18');
INSERT INTO `system_menu` VALUES (10045, 10002, '重置密码', NULL, NULL, 'user:password:reset', 2, NULL, '', '2020-04-01 01:18:01', '2020-04-01 01:18:01');
INSERT INTO `system_menu` VALUES (10128, 0, 'C端数据', 'layui-icon-barchart', NULL, NULL, 0, 3, '', '2020-05-12 00:08:00', '2020-05-12 00:08:00');
INSERT INTO `system_menu` VALUES (10129, 10128, '订单管理', 'layui-icon-piechart', '/order', 'order:view', 1, 2, '', '2020-05-12 00:09:01', '2020-05-12 16:50:01');
INSERT INTO `system_menu` VALUES (10130, 10128, 'C端用户', 'layui-icon-userGroup', '/third_user', 'third_user:view', 1, 1, '', '2020-05-12 16:49:54', '2020-05-12 17:04:45');
INSERT INTO `system_menu` VALUES (10131, 10129, '删除订单', NULL, NULL, 'order:delete', 2, NULL, '', '2020-05-18 16:05:35', '2020-05-18 16:05:35');
INSERT INTO `system_menu` VALUES (10132, 0, '代码生成', 'layui-icon-interspace', NULL, NULL, 0, 4, '', '2020-05-22 00:33:58', '2020-05-22 17:15:25');
INSERT INTO `system_menu` VALUES (10133, 10132, '生成配置', 'layui-icon-wrench', '/generator/configure', 'generator:configure:view', 1, 1, '', '2020-05-22 00:37:21', '2020-05-22 16:58:26');
INSERT INTO `system_menu` VALUES (10134, 10132, '代码生成', 'layui-icon-similar-product', '/generator/generate', 'generator:view', 1, 2, '', '2020-05-22 17:26:16', '2020-05-23 00:15:01');
INSERT INTO `system_menu` VALUES (10135, 10133, '更新生成配置', NULL, NULL, 'generator:configure:update', 2, NULL, '', '2020-05-22 22:50:16', '2020-05-22 22:50:16');
INSERT INTO `system_menu` VALUES (10136, 10134, '生成代码', NULL, NULL, 'generator:generate', 2, NULL, '', '2020-05-23 00:14:47', '2020-05-23 00:14:47');
INSERT INTO `system_menu` VALUES (10137, 10001, '登录日志', 'layui-icon-solution', '/monitor/logLogin', 'logLogin:view', 1, 2, '', '2020-05-25 22:04:41', '2020-05-25 22:05:40');
INSERT INTO `system_menu` VALUES (10138, 10137, '删除登录日志', NULL, NULL, 'logLogin:delete', 2, NULL, '', '2020-05-25 22:14:24', '2020-05-25 22:14:24');
INSERT INTO `system_menu` VALUES (10139, 10001, 'swagger文档', 'layui-icon-commodity', '/doc.html', 'swagger:view', 1, 4, '', '2020-05-26 13:33:46', '2020-05-26 14:32:20');
INSERT INTO `system_menu` VALUES (10140, 0, 'CMS管理', 'layui-icon-read', NULL, NULL, 0, 5, '', '2020-05-28 17:02:48', '2020-05-28 17:02:48');
INSERT INTO `system_menu` VALUES (10141, 10140, 'Banner管理', 'layui-icon-desktop', '/content/banner', 'banner:view', 1, 1, '', '2020-05-28 17:04:39', '2020-05-28 17:04:39');
INSERT INTO `system_menu` VALUES (10142, 10141, '新增Banner', NULL, NULL, 'banner:add', 2, NULL, '', '2020-05-28 21:37:11', '2020-05-28 21:37:11');
INSERT INTO `system_menu` VALUES (10143, 10141, '删除Banner', NULL, NULL, 'banner:delete', 2, NULL, '', '2020-05-28 21:37:58', '2020-05-28 21:37:58');
INSERT INTO `system_menu` VALUES (10144, 10141, '修改banner', NULL, NULL, 'banner:update', 2, NULL, '', '2020-05-28 21:38:26', '2020-05-28 21:38:26');
INSERT INTO `system_menu` VALUES (10145, 10141, '新增BannerItem', NULL, NULL, 'bannerItem:add', 2, NULL, '', '2020-06-27 22:32:53', '2020-06-27 22:32:53');
INSERT INTO `system_menu` VALUES (10146, 10141, '修改BannerItem', NULL, NULL, 'bannerItem:update', 2, NULL, '', '2020-06-27 22:33:18', '2020-06-27 22:33:31');
INSERT INTO `system_menu` VALUES (10147, 10141, '删除BannerItem', NULL, NULL, 'bannerItem:delete', 2, NULL, '', '2020-06-27 22:33:53', '2020-06-27 22:33:53');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role`  (
  `role_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '1：有效 0：无效',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES (1, '超级管理员', '系统管理员，拥有所有操作权限 ^_^', 1, '2019-12-23 22:46:54', '2020-04-25 18:01:29');
INSERT INTO `system_role` VALUES (2, '注册账户', '注册账户，拥有查看，新增权限（新增用户除外）', 1, '2019-12-23 22:46:54', '2020-05-18 15:35:02');
INSERT INTO `system_role` VALUES (3, '业务员', '负责业务、分类、规格模块（包含增删改查）', 1, '2019-12-23 22:46:54', '2020-05-18 15:37:07');
INSERT INTO `system_role` VALUES (4, '系统监控员', '负责整个系统监控模块', 1, '2019-12-23 22:46:54', '2019-12-23 22:46:54');
INSERT INTO `system_role` VALUES (6, '演示角色', '演示角色，拥有查看、修改权限、删除权限、新增权限（系统模块除外）', 1, '2019-12-23 22:46:54', '2020-05-18 15:41:50');

-- ----------------------------
-- Table structure for system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu`;
CREATE TABLE `system_role_menu`  (
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) UNSIGNED NOT NULL COMMENT '菜单/按钮ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_role_menu
-- ----------------------------
INSERT INTO `system_role_menu` VALUES (3, 10011);
INSERT INTO `system_role_menu` VALUES (3, 10013);
INSERT INTO `system_role_menu` VALUES (3, 10026);
INSERT INTO `system_role_menu` VALUES (3, 10027);
INSERT INTO `system_role_menu` VALUES (3, 10028);
INSERT INTO `system_role_menu` VALUES (3, 10018);
INSERT INTO `system_role_menu` VALUES (3, 10029);
INSERT INTO `system_role_menu` VALUES (3, 10030);
INSERT INTO `system_role_menu` VALUES (3, 10031);
INSERT INTO `system_role_menu` VALUES (3, 10019);
INSERT INTO `system_role_menu` VALUES (3, 10032);
INSERT INTO `system_role_menu` VALUES (3, 10033);
INSERT INTO `system_role_menu` VALUES (3, 10034);
INSERT INTO `system_role_menu` VALUES (3, 10014);
INSERT INTO `system_role_menu` VALUES (3, 10015);
INSERT INTO `system_role_menu` VALUES (3, 10035);
INSERT INTO `system_role_menu` VALUES (3, 10036);
INSERT INTO `system_role_menu` VALUES (3, 10016);
INSERT INTO `system_role_menu` VALUES (3, 10017);
INSERT INTO `system_role_menu` VALUES (4, 10001);
INSERT INTO `system_role_menu` VALUES (4, 10007);
INSERT INTO `system_role_menu` VALUES (4, 10044);
INSERT INTO `system_role_menu` VALUES (4, 10012);
INSERT INTO `system_role_menu` VALUES (2, 10000);
INSERT INTO `system_role_menu` VALUES (2, 10002);
INSERT INTO `system_role_menu` VALUES (2, 10003);
INSERT INTO `system_role_menu` VALUES (2, 10020);
INSERT INTO `system_role_menu` VALUES (2, 10004);
INSERT INTO `system_role_menu` VALUES (2, 10023);
INSERT INTO `system_role_menu` VALUES (2, 10011);
INSERT INTO `system_role_menu` VALUES (2, 10013);
INSERT INTO `system_role_menu` VALUES (2, 10026);
INSERT INTO `system_role_menu` VALUES (2, 10018);
INSERT INTO `system_role_menu` VALUES (2, 10029);
INSERT INTO `system_role_menu` VALUES (2, 10019);
INSERT INTO `system_role_menu` VALUES (2, 10032);
INSERT INTO `system_role_menu` VALUES (2, 10128);
INSERT INTO `system_role_menu` VALUES (2, 10130);
INSERT INTO `system_role_menu` VALUES (2, 10129);
INSERT INTO `system_role_menu` VALUES (2, 10014);
INSERT INTO `system_role_menu` VALUES (2, 10015);
INSERT INTO `system_role_menu` VALUES (2, 10035);
INSERT INTO `system_role_menu` VALUES (2, 10001);
INSERT INTO `system_role_menu` VALUES (2, 10007);
INSERT INTO `system_role_menu` VALUES (2, 10016);
INSERT INTO `system_role_menu` VALUES (2, 10017);
INSERT INTO `system_role_menu` VALUES (2, 10038);
INSERT INTO `system_role_menu` VALUES (2, 10041);
INSERT INTO `system_role_menu` VALUES (6, 10000);
INSERT INTO `system_role_menu` VALUES (6, 10002);
INSERT INTO `system_role_menu` VALUES (6, 10003);
INSERT INTO `system_role_menu` VALUES (6, 10004);
INSERT INTO `system_role_menu` VALUES (6, 10023);
INSERT INTO `system_role_menu` VALUES (6, 10011);
INSERT INTO `system_role_menu` VALUES (6, 10013);
INSERT INTO `system_role_menu` VALUES (6, 10026);
INSERT INTO `system_role_menu` VALUES (6, 10027);
INSERT INTO `system_role_menu` VALUES (6, 10028);
INSERT INTO `system_role_menu` VALUES (6, 10018);
INSERT INTO `system_role_menu` VALUES (6, 10029);
INSERT INTO `system_role_menu` VALUES (6, 10030);
INSERT INTO `system_role_menu` VALUES (6, 10031);
INSERT INTO `system_role_menu` VALUES (6, 10019);
INSERT INTO `system_role_menu` VALUES (6, 10032);
INSERT INTO `system_role_menu` VALUES (6, 10033);
INSERT INTO `system_role_menu` VALUES (6, 10034);
INSERT INTO `system_role_menu` VALUES (6, 10128);
INSERT INTO `system_role_menu` VALUES (6, 10130);
INSERT INTO `system_role_menu` VALUES (6, 10129);
INSERT INTO `system_role_menu` VALUES (6, 10014);
INSERT INTO `system_role_menu` VALUES (6, 10015);
INSERT INTO `system_role_menu` VALUES (6, 10035);
INSERT INTO `system_role_menu` VALUES (6, 10036);
INSERT INTO `system_role_menu` VALUES (6, 10037);
INSERT INTO `system_role_menu` VALUES (6, 10001);
INSERT INTO `system_role_menu` VALUES (6, 10007);
INSERT INTO `system_role_menu` VALUES (6, 10044);
INSERT INTO `system_role_menu` VALUES (6, 10012);
INSERT INTO `system_role_menu` VALUES (6, 10016);
INSERT INTO `system_role_menu` VALUES (6, 10017);
INSERT INTO `system_role_menu` VALUES (6, 10038);
INSERT INTO `system_role_menu` VALUES (6, 10039);
INSERT INTO `system_role_menu` VALUES (6, 10040);
INSERT INTO `system_role_menu` VALUES (6, 10041);
INSERT INTO `system_role_menu` VALUES (6, 10042);
INSERT INTO `system_role_menu` VALUES (6, 10043);
INSERT INTO `system_role_menu` VALUES (1, 10000);
INSERT INTO `system_role_menu` VALUES (1, 10002);
INSERT INTO `system_role_menu` VALUES (1, 10008);
INSERT INTO `system_role_menu` VALUES (1, 10009);
INSERT INTO `system_role_menu` VALUES (1, 10010);
INSERT INTO `system_role_menu` VALUES (1, 10045);
INSERT INTO `system_role_menu` VALUES (1, 10003);
INSERT INTO `system_role_menu` VALUES (1, 10020);
INSERT INTO `system_role_menu` VALUES (1, 10021);
INSERT INTO `system_role_menu` VALUES (1, 10022);
INSERT INTO `system_role_menu` VALUES (1, 10004);
INSERT INTO `system_role_menu` VALUES (1, 10023);
INSERT INTO `system_role_menu` VALUES (1, 10024);
INSERT INTO `system_role_menu` VALUES (1, 10025);
INSERT INTO `system_role_menu` VALUES (1, 10011);
INSERT INTO `system_role_menu` VALUES (1, 10013);
INSERT INTO `system_role_menu` VALUES (1, 10026);
INSERT INTO `system_role_menu` VALUES (1, 10027);
INSERT INTO `system_role_menu` VALUES (1, 10028);
INSERT INTO `system_role_menu` VALUES (1, 10018);
INSERT INTO `system_role_menu` VALUES (1, 10029);
INSERT INTO `system_role_menu` VALUES (1, 10030);
INSERT INTO `system_role_menu` VALUES (1, 10031);
INSERT INTO `system_role_menu` VALUES (1, 10019);
INSERT INTO `system_role_menu` VALUES (1, 10032);
INSERT INTO `system_role_menu` VALUES (1, 10033);
INSERT INTO `system_role_menu` VALUES (1, 10034);
INSERT INTO `system_role_menu` VALUES (1, 10128);
INSERT INTO `system_role_menu` VALUES (1, 10130);
INSERT INTO `system_role_menu` VALUES (1, 10129);
INSERT INTO `system_role_menu` VALUES (1, 10131);
INSERT INTO `system_role_menu` VALUES (1, 10014);
INSERT INTO `system_role_menu` VALUES (1, 10015);
INSERT INTO `system_role_menu` VALUES (1, 10035);
INSERT INTO `system_role_menu` VALUES (1, 10036);
INSERT INTO `system_role_menu` VALUES (1, 10037);
INSERT INTO `system_role_menu` VALUES (1, 10132);
INSERT INTO `system_role_menu` VALUES (1, 10133);
INSERT INTO `system_role_menu` VALUES (1, 10135);
INSERT INTO `system_role_menu` VALUES (1, 10134);
INSERT INTO `system_role_menu` VALUES (1, 10136);
INSERT INTO `system_role_menu` VALUES (1, 10001);
INSERT INTO `system_role_menu` VALUES (1, 10007);
INSERT INTO `system_role_menu` VALUES (1, 10044);
INSERT INTO `system_role_menu` VALUES (1, 10012);
INSERT INTO `system_role_menu` VALUES (1, 10137);
INSERT INTO `system_role_menu` VALUES (1, 10138);
INSERT INTO `system_role_menu` VALUES (1, 10139);
INSERT INTO `system_role_menu` VALUES (1, 10140);
INSERT INTO `system_role_menu` VALUES (1, 10141);
INSERT INTO `system_role_menu` VALUES (1, 10142);
INSERT INTO `system_role_menu` VALUES (1, 10143);
INSERT INTO `system_role_menu` VALUES (1, 10144);
INSERT INTO `system_role_menu` VALUES (1, 10145);
INSERT INTO `system_role_menu` VALUES (1, 10146);
INSERT INTO `system_role_menu` VALUES (1, 10147);
INSERT INTO `system_role_menu` VALUES (1, 10016);
INSERT INTO `system_role_menu` VALUES (1, 10017);
INSERT INTO `system_role_menu` VALUES (1, 10038);
INSERT INTO `system_role_menu` VALUES (1, 10039);
INSERT INTO `system_role_menu` VALUES (1, 10040);
INSERT INTO `system_role_menu` VALUES (1, 10041);
INSERT INTO `system_role_menu` VALUES (1, 10042);
INSERT INTO `system_role_menu` VALUES (1, 10043);

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user`  (
  `system_user_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `true_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '性别;0：未知，1:男，2：女',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '类别状态1-正常,0-锁定',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`system_user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES (1, 'admin', '5a87bbc3dd52851f547669031c0215b9', '啊呆', 0, '13800138000', '2dd7a2d09fa94bf8b5c52e5318868b4df.jpg', '这是超级管理员账户', 1, '2020-06-30 00:31:33', '2020-02-03 18:30:29', '2020-06-30 00:31:33');
INSERT INTO `system_user` VALUES (2, 'test', 'c76fd2ed78e8644fc1a10959e5ec376b', '超毅', 0, '', 'default.jpg', '', 1, '2020-05-01 15:38:45', '2020-02-03 19:50:12', '2020-05-01 15:38:45');
INSERT INTO `system_user` VALUES (3, 'Janc', 'd3965faa3f5faced719f52002a07fbbd', '张三', 1, '12345678902', 'default.jpg', '', 1, '2020-06-11 19:11:39', '2020-02-05 20:40:41', '2020-06-11 19:11:38');
INSERT INTO `system_user` VALUES (5, 'jecc', 'bbdb642e67bedab157d0742d51691ca5', '李四', 2, '', 'default.jpg', '', 0, NULL, '2020-02-05 20:40:47', '2020-05-18 15:28:00');
INSERT INTO `system_user` VALUES (8, 'black', '7ce19267f3ad645556ccedd1aba5c01e', '赵六', 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-05 20:40:56', '2020-04-01 14:11:40');
INSERT INTO `system_user` VALUES (10, 'yyyq', 'c9899ced1247f9587926dc78bd637e32', '刘七', 0, NULL, 'default.jpg', NULL, 0, NULL, '2020-02-05 20:41:11', '2020-04-25 19:09:03');
INSERT INTO `system_user` VALUES (12, 'ffffe', '6b62459212199b83c9e560e06134135d', '陈九', 0, '', 'default.jpg', '', 1, NULL, '2020-02-05 20:41:19', '2020-04-25 18:40:08');
INSERT INTO `system_user` VALUES (56, 'asdsad', '4aa99f7b8c14c26baa8a2b0bd7c720e5', '', 0, '', 'default.jpg', '		                ', 1, NULL, '2020-02-12 21:40:43', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (57, 'adsdsa', 'c59ee946a9890170c56cd8e95e9ea74a', '', 0, '', 'default.jpg', '		                ', 1, NULL, '2020-02-12 21:45:54', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (60, 'adsasad', '3e3ca625d9332c4e7b08a569f90d67ba', '', 0, '', 'default.jpg', '		                ', 1, NULL, '2020-02-12 21:54:20', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (61, 'asdasdas', 'b5ecc0522603b68c8cb8f4c6df64b11e', '', 0, '', 'default.jpg', '		                ', 1, NULL, '2020-02-12 21:55:57', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (62, 'asdasdasd', '017a4a23dd70b6eb30e488c880c617f7', '', 0, '', 'default.jpg', '		                ', 1, NULL, '2020-02-12 21:59:41', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (65, '12432', '1b26dc8f9cf8e3a707a9e11969498e7e', '', 0, NULL, 'default.jpg', '		                ', 1, NULL, '2020-02-21 14:37:19', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (79, '54353', 'cf3de11d658696758425f0b85c57b7f0', NULL, 1, NULL, 'default.jpg', NULL, 0, NULL, '2020-02-23 21:08:06', '2020-02-23 21:41:03');
INSERT INTO `system_user` VALUES (80, '12356', 'a39c67c054c243151f26d147c095ed0d', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-23 21:39:41', '2020-02-23 21:39:41');
INSERT INTO `system_user` VALUES (81, '123777', '053fa344329ed999f7981941f4f51c0e', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-23 21:41:19', '2020-02-23 21:41:19');
INSERT INTO `system_user` VALUES (83, '123435', '35afc1a6fd7c3b3cb482527452aa4a7d', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-26 18:03:47', '2020-02-26 18:03:47');
INSERT INTO `system_user` VALUES (84, '12334', '51888798d2b364edfc9115cd220a3aa6', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-26 18:04:12', '2020-02-26 18:04:12');
INSERT INTO `system_user` VALUES (85, '234234', 'b2ea17a96453218acd817f9294d851b8', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-02-26 18:05:23', '2020-02-26 18:05:23');
INSERT INTO `system_user` VALUES (86, '123345   ', '1a91b0d9c7d75e31f8dfaf8896884a1b', NULL, 0, NULL, 'default.jpg', '123123', 1, NULL, '2020-02-29 14:46:18', '2020-05-01 15:18:42');
INSERT INTO `system_user` VALUES (108, '12335', '0bbd8dca7120d2d3bbf03e62db2a8274', NULL, 0, NULL, 'default.jpg', NULL, 1, NULL, '2020-05-25 16:55:43', '2020-05-25 16:55:43');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role`  (
  `system_user_id` bigint(20) UNSIGNED NOT NULL COMMENT '系统用户ID',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES (4, 2);
INSERT INTO `system_user_role` VALUES (6, 2);
INSERT INTO `system_user_role` VALUES (9, 2);
INSERT INTO `system_user_role` VALUES (11, 2);
INSERT INTO `system_user_role` VALUES (2, 2);
INSERT INTO `system_user_role` VALUES (12, 3);
INSERT INTO `system_user_role` VALUES (10, 2);
INSERT INTO `system_user_role` VALUES (1, 1);
INSERT INTO `system_user_role` VALUES (3, 6);
INSERT INTO `system_user_role` VALUES (5, 3);
INSERT INTO `system_user_role` VALUES (8, 3);
INSERT INTO `system_user_role` VALUES (108, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `nickName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信昵称',
  `open_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信头像',
  `country` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信国家',
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信省名',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信城市',
  `wallet` decimal(10, 2) NULL COMMENT '钱包',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `true_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `sex` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别;0：未知，1:男，2：女',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '类别状态1-正常,0-锁定',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除；0:否，1：是',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '加入时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, NULL, NULL, '。sx', 'o-y8S5eT4nNyxVnM3c1O6Oioa4Mc', 'https://wx.qlogo.cn/mmopen/vi_32/gwVXwVRiczoUyVsW0S7vPpAV2Kgniaj0lWxjRTlGu9lsacLdQBOOU35aF6piavH6PDMVS6mpkooe3IDGDpxNvMeIA/132', '中国', '广东', '茂名', 0.00, NULL, NULL, 1, 1, 0, '2020-06-17 14:31:20', '2020-06-17 14:31:20');

SET FOREIGN_KEY_CHECKS = 1;
