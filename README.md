# 迷你论坛-演示项目

该项目为JavaWeb课程实验项目，应用了自己编写的框架[myframework](https://github.com/838239178/myframework)，是该框架的第一次应用。

## 项目功能

- 登录校验
- 发布帖子、修改帖子、删除帖子
- 回复、修改回复、删除回复

## 框架配置文件

用作范例

**myframework.yml**

```yml
controller:
  - cn.jmu.assgin.controller.LoginController
  - cn.jmu.assgin.controller.MessageController
  - cn.jmu.assgin.controller.RevertController
interceptor: [] #如果没有自定义可以不写
jdbcConfig:
  driver: com.mysql.cj.jdbc.Driver #数据库驱动需手动引入
  url: jdbc:mysql://xxxxx.xx:3306/xxxxx?serverTimezone=UTC&allowMultiQueries=true&characterEncoding=utf-8
  username: xxxx
  password: xxxx
```

