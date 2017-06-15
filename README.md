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