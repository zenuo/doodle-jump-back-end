# 涂鸦跳跃网络版后端

## 1 数据库
### 1.1 建库
```
CREATE DATABASE DOODLEJUMP CHARACTER SET utf8mb4;
```

### 1.2 建立玩家表
```
CREATE TABLE `PLAYER` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(100) NOT NULL,
  `bio` char(20) DEFAULT NULL,
  `email` char(50) NOT NULL,
  `password` char(96) NOT NULL,
  `record` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
```

## 2 API
### 2.1 注册
HTTP方法：POST

URL：http://127.0.0.1:8080/auth/signin

表单：
```
name:yz
bio:你好，世界！
email:yz@email.com
password:123456
```

返回字符串：
* 0：表示注册成功
* 1：表示用户名已被注册

### 2.2 登入
HTTP方法：POST

URL：http://127.0.0.1:8080/auth/signin

表单：
```
name:yz
password:123456
```

返回字符串：

* 长度为32的字符串，例如“a809c2b3ed764d0792899edfeb34c0c0”，登录成功
* 1：表示用户名于密码不匹配
* 2：表示该用户已经登入

### 2.3 获取玩家信息
HTTP方法：GET

URL：http://127.0.0.1:8080/auth/info?session={session}

说明：“session”是登入成功后获得的字符串，起到标识用户的作用

返回Json：
```
{
    "id": 1,
    "name": "yz",
    "bio": "hello",
    "email": "email",
    "password": null,
    "record": 0
}
```

### 2.4 发送信息
HTTP方法：POST

URL:http://127.0.0.1:8080/message/send

报文：
```
{"id":null,"scope":0,"source":1,"target":1,"createTime":1497611475646,"sendTime":null,"content":"Hello World!"}
```

返回字符串：
* 0：表示发送成功