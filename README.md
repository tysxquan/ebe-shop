# ebe-shop

**记得安装lombok插件**

![https://img.shields.io/badge/springboot-2.2.5-red.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.5-red.svg?style=flat-square) ![https://img.shields.io/badge/shiro-1.4.2-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/shiro-1.4.2-blue.svg?longCache=true&style=flat-square) ![https://img.shields.io/badge/layui-2.5.6-brightgreen.svg?longCache=true&style=flat-square](https://img.shields.io/badge/layui-2.5.6-green.svg?longCache=true&style=flat-square)  ![https://img.shields.io/badge/license-Apache%202.0-4EB1BA.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-4EB1BA.svg?longCache=true&style=flat-square)

ebe-shop是一款简单美观高效的商城系统，使用SpringBoot、Shiro架构，数据持久化层为Mybatis+Mybatis-Plus构建及Layui+Thymeleaf模板引擎搭建的前端视图模型，适合新零售、商店、连锁商店等系统使用。

####  技术选型

- 核心框架：Spring Boot
- 权限框架：Apache Shiro
- 模板引擎：Thymeleaf
- 持久层框架：MyBatis-plus
- 数据库连接池：Alibaba Druid
- 缓存框架： Redis、Shiro-Redis
- 前端框架：Layui、WebUploader文件上传、ECharts图表、formSelects 4.x 多选框、eleTree 树组件

#### 说明

> 后台系统演示地址：[http://47.97.213.152](http://47.97.213.152)

> 国内镜像：[Gitee](https://gitee.com/sxquan/ebe-shop)

> 如果项目有帮助到你，可以点 "Star" 支持一下 谢谢！

> 如有问题请直接在 Issues 中提，或者您发现问题并有非常好的解决方案!

> 个人开发者不易维护，如有问题或建议可以加我QQ:1042951771

> 开发环境：IDE：IntelliJ IDEA、DB：Mysql 5.7.28、JDK：JAVA8

> 部署教程CSDN博客：https://blog.csdn.net/sx_4567/article/details/106225757

**账户：**

| 账户  | 密码                       | 说明                                                     |
| ----- | -------------------------- | -------------------------------------------------------- |
| Janc  | qweasd                     | 演示角色，拥有查看、修改、删除、新增权限（系统模块除外） |
| test  | qweasd                     | 注册账户，拥有查看，新增权限（新增用户除外）             |
| black | qweasd                     | 业务角色，负责业务、分类、规格模块（包含增删改查）       |
| admin | qweasd（演示环境暂不开放） | 系统管理员，拥有所有操作权限 ^_^                         |

#### 系统结构

- module-core 核心模块
- module-manage 后台系统模块（基本完善）
- module-moblie 移动端模块（正在进行）

#### 目标功能

后台系统：

- [x] 系统管理模块  --  完成
- [x] 业务管理模块  --  完成
- [x] 系统监控模块  --  完成
- [x] 分类规格模块  --  完成
- [x] C端数据模块  --  完成
- [x] CMS内容管理 -- 完成
- [x] 图片上传 --  完成
- [x] 权限管理 --  完成
- [x] 代码生成 --  完成
- [x] 登录日志记录 -- 完成
- [ ] 导出导入 --  进行
- [ ] 接口限流  --  未开始

小程序(进行中)：

- [ ] 小程序授权登录

- [ ] IP定位
- [ ] 下单 
- [ ] 订单信息 
- [ ] 购物车功能
- [ ]  用户信息
- [ ] 添加、删除、修改收货地址
- [ ] 商品详情 
- [ ] 商家详情 
- [ ] 测量距离

#### 系统截图

![ebe_home.png](https://github.com/tysxquan/ebe-shop/blob/master/screenshots/ebe_home.png)

![ebe_user.png](https://github.com/tysxquan/ebe-shop/blob/master/screenshots/ebe_user.png)

![ebe_update.png](https://github.com/tysxquan/ebe-shop/blob/master/screenshots/ebe_update.png)

![ebe_personal.png](https://github.com/tysxquan/ebe-shop/blob/master/screenshots/ebe_personal.png)

#### 

#### 注意

layui的element.js模块修改了源码（替换版本注意）

#### 鸣谢

该项目的业务功能和部分功能代码参考复用了[FEBS-Shiro](https://github.com/wuyouzhuguli/FEBS-Shiro)、[layuimini](https://github.com/zhongshaofa/layuimini)

