/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : shooping_mall

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 21/12/2019 17:15:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shooping_mall_category
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_category`;
CREATE TABLE `shooping_mall_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父类别id',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类别名字',
  `category_level` tinyint(4) NULL DEFAULT NULL COMMENT '类别级别',
  `category_rank` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  INDEX `category_id`(`category_id`, `category_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_category
-- ----------------------------
INSERT INTO `shooping_mall_category` VALUES (1, 0, '奶粉辅食', 1, 1, '2019-11-27 14:23:21', '2019-11-27 14:23:22');
INSERT INTO `shooping_mall_category` VALUES (2, 0, '儿童用品', 1, 2, '2019-11-27 14:23:30', '2019-11-27 14:23:37');
INSERT INTO `shooping_mall_category` VALUES (3, 0, '儿童早教', 1, 3, '2019-11-27 14:23:44', '2019-11-27 14:23:45');
INSERT INTO `shooping_mall_category` VALUES (4, 0, '儿童服饰', 1, 4, '2019-11-27 14:23:57', '2019-11-27 14:23:51');
INSERT INTO `shooping_mall_category` VALUES (5, 0, '孕妈专区', 1, 5, '2019-11-27 14:24:36', '2019-11-27 14:24:37');
INSERT INTO `shooping_mall_category` VALUES (6, 1, '进口奶粉', 2, 1, '2019-11-27 14:24:57', '2019-11-27 14:25:11');
INSERT INTO `shooping_mall_category` VALUES (7, 1, '宝宝辅食', 2, 2, '2019-11-27 14:25:22', '2019-11-27 14:25:16');
INSERT INTO `shooping_mall_category` VALUES (8, 1, '营养品', 2, 3, '2019-11-27 14:25:42', '2019-11-27 14:25:43');
INSERT INTO `shooping_mall_category` VALUES (9, 2, '纸尿裤', 2, 1, '2019-11-27 14:25:59', '2019-11-27 14:26:06');
INSERT INTO `shooping_mall_category` VALUES (10, 2, '婴儿湿巾', 2, 2, '2019-11-27 14:26:36', '2019-11-27 14:26:37');
INSERT INTO `shooping_mall_category` VALUES (11, 2, '婴儿车', 2, 3, '2019-11-27 14:26:49', '2019-11-27 14:26:48');
INSERT INTO `shooping_mall_category` VALUES (12, 2, '婴儿床', 2, 4, '2019-11-27 14:27:03', '2019-11-27 14:27:05');
INSERT INTO `shooping_mall_category` VALUES (13, 2, '儿童安全座椅', 2, 5, '2019-11-27 14:27:24', '2019-11-27 14:27:26');
INSERT INTO `shooping_mall_category` VALUES (14, 3, '儿童玩具', 2, 1, '2019-11-27 14:27:40', '2019-11-27 14:27:41');
INSERT INTO `shooping_mall_category` VALUES (15, 3, '早教书籍', 2, 2, '2019-11-27 14:28:04', '2019-11-27 14:28:00');
INSERT INTO `shooping_mall_category` VALUES (16, 3, '孕产育儿书', 2, 3, '2019-11-27 14:28:14', '2019-11-27 14:28:15');
INSERT INTO `shooping_mall_category` VALUES (17, 4, '童装', 2, 1, '2019-11-27 14:28:49', '2019-11-27 14:28:51');
INSERT INTO `shooping_mall_category` VALUES (18, 4, '童鞋', 2, 2, '2019-11-27 14:29:05', '2019-11-27 14:29:06');
INSERT INTO `shooping_mall_category` VALUES (19, 4, '宝宝配饰', 2, 3, '2019-11-27 14:29:16', '2019-11-27 14:29:17');
INSERT INTO `shooping_mall_category` VALUES (20, 5, '孕妇装', 2, 1, '2019-11-27 14:29:34', '2019-11-27 14:29:35');
INSERT INTO `shooping_mall_category` VALUES (21, 5, '孕妇护肤', 2, 2, '2019-11-27 14:29:49', '2019-11-27 14:29:51');
INSERT INTO `shooping_mall_category` VALUES (22, 5, '孕妇用品', 2, 3, '2019-11-27 14:30:09', '2019-11-27 14:30:10');

-- ----------------------------
-- Table structure for shooping_mall_comment
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_comment`;
CREATE TABLE `shooping_mall_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `commodity_id` int(11) NULL DEFAULT NULL COMMENT '关联商品id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联用户id',
  `comment_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_comment
-- ----------------------------
INSERT INTO `shooping_mall_comment` VALUES (1, 1, 1, '测试评论', '2019-12-01 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (2, 1, 1, '测试评论1', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (3, 1, 1, '测试评论2', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (4, 1, 1, '测试评论3', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (5, 1, 1, '测试评论4', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (6, 1, 1, '测试评论5', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (7, 1, 1, '测试评论6', '2019-11-30 20:40:47', '2019-11-30 20:40:48');
INSERT INTO `shooping_mall_comment` VALUES (8, 2, 1, '测试评论功能1', '2019-12-01 15:07:03', '2019-12-01 23:07:03');
INSERT INTO `shooping_mall_comment` VALUES (9, 4, 1, '测试评论刷新', '2019-12-02 02:49:56', '2019-12-02 10:49:55');
INSERT INTO `shooping_mall_comment` VALUES (10, 2, 1, '这件商品还不错哦！值得入手', '2019-12-02 14:47:52', '2019-12-02 22:47:51');
INSERT INTO `shooping_mall_comment` VALUES (11, 1, 1, '这个也不错哦', '2019-12-02 14:48:13', '2019-12-02 22:48:13');
INSERT INTO `shooping_mall_comment` VALUES (14, 4, 11, '测试评论1', '2019-12-06 13:16:41', '2019-12-06 21:16:40');

-- ----------------------------
-- Table structure for shooping_mall_commodity
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_commodity`;
CREATE TABLE `shooping_mall_commodity`  (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `category_id` int(11) NULL DEFAULT NULL COMMENT '关联类别id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联类别名称',
  `cover_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `show_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示图片',
  `commodity_intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品简介',
  `sales_volume` int(11) NULL DEFAULT NULL COMMENT '销售数量',
  `original_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `selling_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品实际售价',
  `detail_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品详情',
  `commodity_status` tinyint(4) NULL DEFAULT NULL COMMENT '上架状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`commodity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_commodity
-- ----------------------------
INSERT INTO `shooping_mall_commodity` VALUES (1, '森系小清新四件套', 17, '童装', 'http://localhost:8080/commodityImg/paging_img1.jpg', 'http://localhost:8080/commodityImg/paging_img1.jpg', '森系小清新四件套简介', 1266, 402.00, 100.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/details_imgbig.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (2, '冠琪亮 婴儿连体衣保暖6-12个月宝宝加厚冠琪亮 婴儿连体衣保暖6-12个月宝宝加厚冠琪亮 婴儿连体衣保暖6-12个月宝宝加厚', 17, '童装', 'http://localhost:8080/commodityImg/b15550d49f2a72dc.jpg', 'http://localhost:8080/commodityImg/b15550d49f2a72dc.jpg', '商品名称：冠琪亮 婴儿连体衣保暖6-12个月宝宝加厚哈衣爬爬服新生儿衣服夹棉冬季 密档蓝白 80cm【建议10-16个月，18-22斤】商品编号：52603450410店铺： 冠琪亮宝贝旗舰店商品毛重：140.00g商品产地：中国大陆货号：J002衣门襟：单排扣内外穿：内着面料：纯棉袖长：长袖适用性别：男女通用适用年龄：0-3个月，3-6个月，1-3岁，6-12个月分类：连体衣适用季节：冬季填充物：聚酯纤维安全等级：A类上市时间：2018冬季', 1272, 100.00, 59.90, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/67390191d7062062.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/5c9e0b4c5a621e49.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/f2ce8bfe63cdbb93.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (3, '英氏婴儿衣服新生儿连体衣儿童内衣宝宝婴幼儿居家爬爬服连体服哈衣服装连体睡衣早产儿纯棉四季春秋0-1岁', 17, '童装', 'http://localhost:8080/commodityImg/dde8ae61569790ed.jpg', 'http://localhost:8080/commodityImg/dde8ae61569790ed.jpg', '商品名称：英氏婴儿衣服新生儿连体衣儿童内衣宝宝婴幼儿居家爬爬服连体服哈衣服装连体睡衣早产儿纯棉四季春秋0-1岁 189Ayh7476-襟哈 80cm(建议9-18月)商品编号：40964739002店铺： 英氏YeeHoO旗舰店商品毛重：100.00g商品产地：中国大陆货号：189Ayh7475风格：日韩风，原创设计厚度：适中内外穿：内着面料：纯棉袖长：长袖衣门襟：斜襟系带', 1271, 203.00, 103.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/e397b8c5e2ba57b6.png\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/5ef0d576c4b8867d.png\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/660aa1372a9c1a6c.png\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (4, '优奇 婴儿衣服冬装珊瑚绒婴儿连体衣秋冬外出服男女宝宝加厚外套', 17, '童装', 'http://localhost:8080/commodityImg/00d4c53ae3f567c9.jpg', 'http://localhost:8080/commodityImg/00d4c53ae3f567c9.jpg', '商品名称：优奇 婴儿衣服冬装珊瑚绒婴儿连体衣秋冬外出服男女宝宝加厚外套 【夹棉厚款】粉蓝 80cm(推荐身高76-82cm)商品编号：17863939163店铺： 优奇官方旗舰店商品毛重：500.00g货号：FWFH001厚度：加绒适用性别：男女通用是否带帽子：有帽不可拆卸袖长：长袖衣门襟：拉链适用年龄：3-6个月，6-12个月，1-3岁安全等级：A类适用季节：冬季分类：连体衣面料：珊瑚绒上市时间：2017冬季', 1263, 200.00, 75.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/f12cc382d73542e3.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/b969320cd642a028.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/494158607151dcbf.jpg\">', 1, '2019-11-27 20:11:34', '2019-12-03 16:05:54');
INSERT INTO `shooping_mall_commodity` VALUES (5, '森系小清新四件套', 17, '童装', 'http://localhost:8080/commodityImg/paging_img1.jpg', 'http://localhost:8080/commodityImg/paging_img1.jpg', '森系小清新四件套简介', 1264, 200.00, 120.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (6, '森系小清新四件套', 17, '童装', 'http://localhost:8080/commodityImg/paging_img1.jpg', 'http://localhost:8080/commodityImg/paging_img1.jpg', '森系小清新四件套简介', 1265, 200.00, 150.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (7, '森系小清新四件套', 17, '童装', 'http://localhost:8080/commodityImg/paging_img1.jpg', 'http://localhost:8080/commodityImg/paging_img1.jpg', '森系小清新四件套简介', 1267, 200.00, 160.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (8, 'gb好孩子 多功能婴儿床环保实木拼接床摇篮婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/aa58db8777e05517.jpg', 'http://localhost:8080/commodityImg/aa58db8777e05517.jpg', '商品名称：好孩子MC306-J311商品编号：100006007836商品毛重：18.87kg商品产地：江苏昆山货号：MC306-J311材质：松木分类：多功能床适用年龄：全阶段（0-3岁）功能：可调高低档', 865, 156.00, 150.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/d391a501788df043.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/5b5a8e97N1d1eaf04.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/5b5a9185Nb0cedb6b.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (9, 'gb好孩子婴儿床拼接大床欧式实木宝宝多功能儿童床新生儿BB婴幼儿床边床', 12, '婴儿床', 'http://localhost:8080/commodityImg/dca15def7a4b738f.jpg', 'http://localhost:8080/commodityImg/dca15def7a4b738f.jpg', '商品名称：gb好孩子婴儿床拼接大床欧式实木宝宝多功能儿童床新生儿BB婴幼儿床边床 白色（赠五件套+棕垫+蚊帐+防啃条）商品编号：56079348468店铺： 卓妮母婴旗舰店商品毛重：13.0kg货号：MC295材质：松木适用年龄：婴幼儿（6个月-3岁）分类：多功能床功能：游戏床，可拼接颜色：原木色', 866, 157.00, 130.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/08900b324a974388.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/a041b133e90d8da2.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/5630647e0b9e0d6f.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (10, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 861, 158.00, 144.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (11, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 864, 159.00, 144.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (12, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 864, 155.00, 144.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (13, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 864, 155.00, 44.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (14, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 866, 155.00, 144.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (15, '原木婴儿床', 12, '婴儿床', 'http://localhost:8080/commodityImg/paging_img2.jpg', 'http://localhost:8080/commodityImg/paging_img2.jpg', '原木婴儿床简介', 867, 155.00, 144.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (16, '9i9久爱久儿童帽秋冬款宝宝帽子围脖套装小老虎款', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/565ca0a7c7c6a6f7.jpg', 'http://localhost:8080/commodityImg/565ca0a7c7c6a6f7.jpg', '商品名称：久爱久帽子 围脖商品编号：100000452053商品毛重：300.00g商品产地：中国北京货号：171017适用性别：通用适用年龄：1-3岁，6-12个月适用季节：冬季分类：其它上市时间：2018秋季', 1331, 175.00, 122.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/20191130201005.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (17, 'kk树 kocotree 儿童防水袖套男孩女孩秋冬季小孩宝宝可爱防脏套袖', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/985e5cd6476efe3f.jpg', 'http://localhost:8080/commodityImg/985e5cd6476efe3f.jpg', '商品名称：kocotreeKQ19328商品编号：100008499950商品毛重：160.00g商品产地：浙江省货号：KQ19328适用性别：通用适用年龄：3-6岁，6-9岁，9-12岁适用季节：四季通用分类：其它上市时间：2019年秋季', 1332, 176.00, 122.00, '<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/b414db1c6d5ae093.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/472b3cf07fc83f72.jpg\">\r\n<img class=\"datails-content-img\" src=\"http://localhost:8080/detailImg/f3eb9f07e692eaba.jpg\">', 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (18, '粉红色小帽子', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/paging_img3.jpg', 'http://localhost:8080/commodityImg/paging_img3.jpg', '粉红色小帽子简介', 1333, 177.00, 123.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (19, '粉红色小帽子', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/paging_img3.jpg', 'http://localhost:8080/commodityImg/paging_img3.jpg', '粉红色小帽子简介', 1334, 178.00, 102.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (20, '粉红色小帽子', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/paging_img3.jpg', 'http://localhost:8080/commodityImg/paging_img3.jpg', '粉红色小帽子简介', 1335, 179.00, 22.00, NULL, 1, '2019-11-27 20:11:34', '2019-11-27 20:11:36');
INSERT INTO `shooping_mall_commodity` VALUES (21, '粉红色小帽子', 19, '宝宝配饰', 'http://localhost:8080/commodityImg/paging_img3.jpg', 'http://localhost:8080/commodityImg/paging_img3.jpg', '粉红色小帽子简介', 1336, 175.00, 112.00, NULL, 1, '2019-11-06 20:11:34', '2019-11-27 20:11:36');

-- ----------------------------
-- Table structure for shooping_mall_order
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_order`;
CREATE TABLE `shooping_mall_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '关联用户id',
  `order_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '10' COMMENT '订单状态\r\n            10：待付款\r\n            20：已付款',
  `order_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `user_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `paid_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `uk_order_num`(`order_num`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_order
-- ----------------------------
INSERT INTO `shooping_mall_order` VALUES (1, 11, '201912071919434960', '20', 75.00, '湖南省邵阳市隆回县', '2019-12-07 11:19:44', '2019-12-07 11:22:58');

-- ----------------------------
-- Table structure for shooping_mall_order_flow
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_order_flow`;
CREATE TABLE `shooping_mall_order_flow`  (
  `flow_id` int(11) NOT NULL AUTO_INCREMENT,
  `flow_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流水号',
  `order_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `paid_amount` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付金额',
  `paid_method` int(11) NULL DEFAULT NULL COMMENT '支付方式\r\n            1：支付宝\r\n            2：微信',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`flow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_order_flow
-- ----------------------------
INSERT INTO `shooping_mall_order_flow` VALUES (1, '2019120722001466241000054324', '201912071919434960', '75.00', 1, '2019-12-07 11:22:58');

-- ----------------------------
-- Table structure for shooping_mall_order_item
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_order_item`;
CREATE TABLE `shooping_mall_order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `commodity_id` int(11) NULL DEFAULT NULL COMMENT '关联商品id',
  `buy_counts` int(11) NULL DEFAULT NULL COMMENT '购买数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_order_item
-- ----------------------------
INSERT INTO `shooping_mall_order_item` VALUES (1, 1, 4, 1, '2019-12-07 11:19:44', '2019-12-07 19:19:43');

-- ----------------------------
-- Table structure for shooping_mall_shopp_cart
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_shopp_cart`;
CREATE TABLE `shooping_mall_shopp_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `commodity_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `commodity_count` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_shopp_cart
-- ----------------------------
INSERT INTO `shooping_mall_shopp_cart` VALUES (1, 1, 1, 5, '2019-11-29 15:18:49', '2019-12-04 11:15:06');

-- ----------------------------
-- Table structure for shooping_mall_user
-- ----------------------------
DROP TABLE IF EXISTS `shooping_mall_user`;
CREATE TABLE `shooping_mall_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `user_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_user_phone`(`user_phone`) USING BTREE COMMENT '电话号码唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shooping_mall_user
-- ----------------------------
INSERT INTO `shooping_mall_user` VALUES (1, '朱林', '17673169855', 'http://localhost:8080/userImg/20191130_12345131.png', '670b14728ad9902aecba32e22fa4f6bd', '', '2019-11-26 16:39:52', '2019-12-21 17:14:51');
INSERT INTO `shooping_mall_user` VALUES (7, 'df13202914711', '13202914711', '/userImg/', '670b14728ad9902aecba32e22fa4f6bd', NULL, '2019-11-27 05:16:30', '2019-11-27 13:16:30');
INSERT INTO `shooping_mall_user` VALUES (8, 'df13202914751', '13202914751', '/userImg/', '670b14728ad9902aecba32e22fa4f6bd', NULL, '2019-11-28 09:02:10', '2019-11-28 17:02:10');
INSERT INTO `shooping_mall_user` VALUES (11, '测试账号', '18274344991', 'http://localhost:8080/userImg/20191206_21000536.jpg', '670b14728ad9902aecba32e22fa4f6bd', '', '2019-12-06 07:41:44', '2019-12-21 17:14:56');

SET FOREIGN_KEY_CHECKS = 1;
