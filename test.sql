-- phpMyAdmin SQL Dump
-- version phpStudy 2014
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2017 年 11 月 06 日 09:03
-- 服务器版本: 5.5.53
-- PHP 版本: 5.4.45

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `test`
--

-- --------------------------------------------------------

--
-- 表的结构 `lx_article`
--

CREATE TABLE IF NOT EXISTS `lx_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `content` text,
  `createTime` int(11) NOT NULL DEFAULT '0',
  `updateTime` int(11) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '0.无效,1.有效',
  `isDelete` int(1) NOT NULL DEFAULT '0' COMMENT '0.正常,1.删除',
  `viewCount` int(11) NOT NULL DEFAULT '0',
  `commentCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `title` (`title`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `lx_article`
--

INSERT INTO `lx_article` (`id`, `userId`, `title`, `content`, `createTime`, `updateTime`, `status`, `isDelete`, `viewCount`, `commentCount`) VALUES
(1, 92, '是大锅饭时代是的公司的', '<p>胜多负少<img src="http://img.baidu.com/hi/jx2/j_0001.gif"/>胜多<strong>负</strong>少<strong>手动</strong>阀</p>', 1509939441, 1509939441, 1, 0, 0, 0),
(2, 92, '如果问任何人', '<p>胜多负少<img src="http://img.baidu.com/hi/jx2/j_0001.gif"/>胜多<strong>负</strong>少<strong>手动</strong>阀<img src="http://img.baidu.com/hi/jx2/j_0023.gif"/><img src="http://img.baidu.com/hi/jx2/j_0056.gif"/><img src="http://img.baidu.com/hi/jx2/j_0026.gif"/></p>', 1509939485, 1509939485, 1, 0, 0, 0),
(3, 92, '对于澳大利亚少年Aaron Pajich来', '<p class="f_center" style="margin-top: 0px; margin-bottom: 0px; padding: 0px 0px 10px; text-indent: 2em; color: rgb(82, 82, 82); font-family: &#39;microsoft yahei&#39;; line-height: 29.6px; white-space: normal;">016年6月14日，对于澳大利亚少年Aaron Pajich来说，是很普通的一天。因为有孤独症，所以Aaron是个内向而脆弱的人，被他成为“朋友”的人屈指可数，但都获得了他极度的信任与依赖。</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px 0px 10px; text-indent: 2em; color: rgb(82, 82, 82); font-family: &#39;microsoft yahei&#39;; line-height: 29.6px; white-space: normal;">这天，他独自从购物中心回家，路过了其中一个朋友的家。朋友的母亲TrudiLenon正站在门外，亲切对他打了声招呼。“听说你很擅长计算机，能不能帮我们下载几个电脑软件?”Trudi热情的对他说，请求他来自家帮个小忙。</p><p style="margin-top: 0px; margin-bottom: 0px; padding: 0px 0px 10px; text-indent: 2em; color: rgb(82, 82, 82); font-family: &#39;microsoft yahei&#39;; line-height: 29.6px; white-space: normal;">Aaron害羞的点点头，毫不犹豫的答应了，像往常一样走进了朋友的家门，此时的他并不知道，这个屋子里会怎样可怕的事情在等待他……朋友暂时不在家，家里只有Trudi，和一个叫做Jemma Lilley年轻女住客。</p><p><br/></p>', 1509939528, 1509939528, 1, 0, 1, 0),
(4, 92, '的粉红色的士大夫敢死队', '<p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"><strong>腾讯数码讯</strong>（水蓝）小米6此前已经增加了低配版本，而现在看起来似乎还有后续升级版推出。根据网友在微博上的爆料称，小米6应该会有后续版本与我们见面，预计或将是传说中的小米6c，主要特色是配有5.65英寸全面屏，并搭载新款10纳米工艺的澎湃处理器，据传将于12月份正式登场。</p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"><img src="http://inews.gtimg.com/newsapp_bt/0/2253430264/641" style="text-align: center; max-width: 640px; display: block;"/></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"><strong>将推全面屏版</strong></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;">随着全面屏手机开始成为业内新品的标配，小米不仅准备在红米系列中引入全面屏设计，而且小米6似乎也有推出全面屏版本的计划。根据网友@红尘小叶子在微博上的爆料称，传说中的小米6s将会配备5.65英寸全面屏和搭载骁龙835处理器。</p><p><br/></p>', 1509939581, 1509939581, 1, 0, 11, 1),
(5, 92, '的粉红色的士大夫敢死队234234', '<p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"><strong>腾讯数码讯</strong>（水蓝）小米6此前已经增加了低配版本，而现在看起来似乎还有后续升级版推出。根据网友在微博上的爆料称，小米6应该会有后续版本与我们见面，预计或将是传说中的小米6c，主要特色是配有5.65英寸全面屏，并搭载新款10纳米工艺的澎湃处理器，据传将于12月份正式登场。</p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"><img src="http://inews.gtimg.com/newsapp_bt/0/2253430264/641" style="text-align: center; max-width: 640px; display: block;"/></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;"><strong>将推全面屏版</strong></p><p style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal;"></p><p class="text" style="margin-top: 0px; margin-bottom: 28px; padding: 0px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; line-height: 28px; white-space: normal; text-indent: 2em;">随着全面屏手机开始成为业内新品的标配，小米不仅准备在红米系列中引入全面屏设计，而且小米6似乎也有推出全面屏版本的计划。根据网友@红尘小叶子在微博上的爆料称，传说中的小米6s将会配备5.65英寸全面屏和搭载骁龙835处理器。</p><p><br/></p>', 1509939600, 1509939600, 1, 0, 3, 0),
(6, 92, '测试啦啦啦啦', '<p>啊是的发生啊手动阀<span style="text-decoration:underline;"><img src="http://img.baidu.com/hi/jx2/j_0003.gif"/>啊是的发生啊手动阀</span></p>', 1509943236, 1509943236, 1, 0, 7, 2);

-- --------------------------------------------------------

--
-- 表的结构 `lx_comment`
--

CREATE TABLE IF NOT EXISTS `lx_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` int(11) NOT NULL DEFAULT '0',
  `parentId` int(11) NOT NULL DEFAULT '0' COMMENT '评论的父ID',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '0.无效,1.有效',
  PRIMARY KEY (`id`),
  KEY `articleId` (`articleId`,`userId`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `lx_comment`
--

INSERT INTO `lx_comment` (`id`, `articleId`, `userId`, `content`, `createTime`, `parentId`, `status`) VALUES
(1, 4, 92, '测试内容加快速度和法国手动阀', 1509941024, 0, 1),
(2, 5, 92, 'rhdrtftssafsdfbsdfv', 1509946085, 0, 1),
(3, 6, 92, '啊手动阀啊手动阀啊手动阀啊手动阀', 1509947453, 0, 1),
(4, 6, 92, 'rher看哈撒地方是的空间回复胜多负少', 1509949429, 0, 1),
(5, 4, 92, '随着全面屏手机开始成为业内新品的标配，小米不仅准备在红米系列中引入全面屏设计，而且小米6似乎也有推出全面屏版本的计划。根据网友@红尘小叶子在微博上的爆料称，传说中的小米6s将会配备5.65英寸全面屏和搭载骁龙835处理器。', 1509949442, 0, 1);

-- --------------------------------------------------------

--
-- 表的结构 `lx_user`
--

CREATE TABLE IF NOT EXISTS `lx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `loginPassword` varchar(32) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `realName` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `sex` varchar(50) DEFAULT 'UNSPECIFIED',
  `age` int(3) DEFAULT '0',
  `createTime` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=100 ;

--
-- 转存表中的数据 `lx_user`
--

INSERT INTO `lx_user` (`id`, `username`, `loginPassword`, `nickname`, `realName`, `email`, `mobile`, `sex`, `age`, `createTime`) VALUES
(1, 'a001', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '', '', 'MALE', 0, 1508771190),
(4, 'a004', '297199ff236f82aa92440ced541e89a6', NULL, NULL, '', '', 'MALE', 0, 1508771190),
(5, 'a005', '63de53a7b6e6a8ac0ce867217993a770', NULL, NULL, '', '13308811112', 'MALE', 0, 1508771190),
(88, 'a088', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵称', '小三', NULL, NULL, NULL, 18, 1508871190),
(89, 'a089', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵称1', '小四', '341455770@qq.com', '18580021590', NULL, 19, 1509599352),
(90, 'a090', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵称2', '小五', '341455771@qq.com', '18580021591', 'UNSPECIFIED', 20, 1509599442),
(91, 'a091', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵333', '小6', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509602225),
(92, NULL, NULL, '我的昵333', '小6', NULL, NULL, 'UNSPECIFIED', 21, 1509602668),
(94, 'a094', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵333', '小6', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509603021),
(95, 'a094', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵333', '小6', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509603149),
(96, 'a094', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵333', '小6', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509603287),
(97, 'a096', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵396', '小6', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509603683),
(98, 'a098', 'dc483e80a7a0bd9ef71d8cf973673924', '我的昵098', '小98', '341455772@qq.com', '18580021591', 'UNSPECIFIED', 21, 1509603700),
(99, '小AAAA', 'e10adc3949ba59abbe56e057f20f883e', '小AAAA', NULL, 'luoziping1225@126.com', '18580021590', 'UNSPECIFIED', NULL, 1509886250);

-- --------------------------------------------------------

--
-- 表的结构 `lx_user_log`
--

CREATE TABLE IF NOT EXISTS `lx_user_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `logType` varchar(50) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '0.无效,1.有效',
  `isDelete` int(1) NOT NULL DEFAULT '0' COMMENT '0.正常,1.删除',
  `createTime` int(11) NOT NULL DEFAULT '0',
  `ipAddr` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=34 ;

--
-- 转存表中的数据 `lx_user_log`
--

INSERT INTO `lx_user_log` (`id`, `userId`, `content`, `logType`, `status`, `isDelete`, `createTime`, `ipAddr`) VALUES
(1, 1, '谷歌地图的风格和肉体', '1', 1, 0, 0, NULL),
(3, 4, '上课就好地方阿三地方开会', '1', 1, 0, 0, NULL),
(4, 94, '注册成功', 'registered', 1, 0, 1509603656, '192.168.0.205'),
(5, 97, '注册成功', 'registered', 1, 0, 1509603683, '192.168.0.205'),
(6, 98, '注册成功', 'registered', 1, 0, 1509603700, '192.168.0.205'),
(7, 91, '用户登录', 'registered', 1, 0, 1509607895, '192.168.0.205'),
(8, 91, '用户登录', 'registered', 1, 0, 1509608046, '192.168.0.205'),
(9, 91, '用户登录', 'login', 1, 0, 1509608186, '192.168.0.205'),
(10, 91, '用户登录', 'logined', 1, 0, 1509608399, '192.168.0.205'),
(11, 91, '用户登录', 'logined', 1, 0, 1509608430, '192.168.0.205'),
(12, 91, '用户登录', 'logined', 1, 0, 1509608648, '192.168.0.205'),
(13, 91, '用户登录', 'logined', 1, 0, 1509608656, '192.168.0.205'),
(14, 91, '用户登录', 'logined', 1, 0, 1509680936, '192.168.0.205'),
(15, 92, '用户登录', 'logined', 1, 0, 1509681022, '192.168.0.205'),
(16, 92, '发表文章', 'addArticle', 1, 0, 1509685069, '192.168.0.205'),
(17, 92, '发表评论', 'addComment', 1, 0, 1509686448, '192.168.0.205'),
(18, 92, '发表评论', 'addComment', 1, 0, 1509686496, '192.168.0.205'),
(19, 92, '用户登录', 'logined', 1, 0, 1509885283, '0:0:0:0:0:0:0:1'),
(20, 99, '注册成功', 'registered', 1, 0, 1509886250, '0:0:0:0:0:0:0:1'),
(21, 92, '登录成功', 'logined', 1, 0, 1509939096, '0:0:0:0:0:0:0:1'),
(22, 92, '发表文章', 'addArticle', 1, 0, 1509939441, '0:0:0:0:0:0:0:1'),
(23, 92, '发表文章', 'addArticle', 1, 0, 1509939485, '0:0:0:0:0:0:0:1'),
(24, 92, '发表文章', 'addArticle', 1, 0, 1509939528, '0:0:0:0:0:0:0:1'),
(25, 92, '发表文章', 'addArticle', 1, 0, 1509939583, '0:0:0:0:0:0:0:1'),
(26, 92, '发表文章', 'addArticle', 1, 0, 1509939600, '0:0:0:0:0:0:0:1'),
(27, 92, '发表评论', 'addComment', 1, 0, 1509941024, '0:0:0:0:0:0:0:1'),
(28, 92, '发表文章', 'addArticle', 1, 0, 1509943237, '0:0:0:0:0:0:0:1'),
(29, 92, '发表评论', 'addComment', 1, 0, 1509946085, '0:0:0:0:0:0:0:1'),
(30, 92, '发表评论', 'addComment', 1, 0, 1509947453, '0:0:0:0:0:0:0:1'),
(31, 92, '发表评论', 'addComment', 1, 0, 1509949429, '0:0:0:0:0:0:0:1'),
(32, 92, '发表评论', 'addComment', 1, 0, 1509949442, '0:0:0:0:0:0:0:1'),
(33, 1, '登录成功', 'logined', 1, 0, 1509957145, '0:0:0:0:0:0:0:1');

-- --------------------------------------------------------

--
-- 表的结构 `lx_user_token`
--

CREATE TABLE IF NOT EXISTS `lx_user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `token` varchar(50) NOT NULL,
  `expireIn` int(11) NOT NULL DEFAULT '0',
  `lat` double NOT NULL DEFAULT '0',
  `lnt` double NOT NULL DEFAULT '0',
  `loginTime` int(11) NOT NULL DEFAULT '0',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '0.无效,1.有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=5 ;

--
-- 转存表中的数据 `lx_user_token`
--

INSERT INTO `lx_user_token` (`id`, `userId`, `token`, `expireIn`, `lat`, `lnt`, `loginTime`, `status`) VALUES
(2, 91, 'fbf715f9e7a6a505c10f953a7e04f979', 1514864936, 0, 0, 1509680936, 1),
(3, 92, '15792891770f675fa798f00a14a6430a', 1515123096, 0, 0, 1509939096, 1),
(4, 1, 'ce95659b2fb5bbf068fe6ccfdedc6afe', 1515141145, 0, 0, 1509957145, 1);

--
-- 限制导出的表
--

--
-- 限制表 `lx_article`
--
ALTER TABLE `lx_article`
  ADD CONSTRAINT `lx_article_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `lx_user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `lx_comment`
--
ALTER TABLE `lx_comment`
  ADD CONSTRAINT `lx_comment_ibfk_1` FOREIGN KEY (`articleId`) REFERENCES `lx_article` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `lx_comment_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `lx_user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `lx_user_log`
--
ALTER TABLE `lx_user_log`
  ADD CONSTRAINT `lx_user_log_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `lx_user` (`id`) ON DELETE CASCADE;

--
-- 限制表 `lx_user_token`
--
ALTER TABLE `lx_user_token`
  ADD CONSTRAINT `lx_user_token_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `lx_user` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
