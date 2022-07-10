-- rapcrew.tb_user definition

CREATE TABLE `tb_user` (
  `userNo` bigint NOT NULL AUTO_INCREMENT,
  `userId` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `status` int NOT NULL DEFAULT '1' COMMENT '1: 활성 2: 비활성 3: 정지',
  `createAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rapcrew.tb_card definition

CREATE TABLE `tb_card` (
  `cardId` bigint NOT NULL AUTO_INCREMENT,
  `userNo` bigint NOT NULL,
  `title` varchar(256) NOT NULL,
  `content` text,
  `createAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  `recruitAt` timestamp NULL DEFAULT NULL,
  `recruitNum` int DEFAULT NULL,
  PRIMARY KEY (`cardId`),
  KEY `tb_card_FK` (`userNo`),
  CONSTRAINT `tb_card_FK` FOREIGN KEY (`userNo`) REFERENCES `tb_user` (`userNo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rapcrew.tb_reply definition

CREATE TABLE `tb_reply` (
  `replyId` bigint NOT NULL AUTO_INCREMENT,
  `cardId` bigint NOT NULL,
  `userNo` bigint NOT NULL,
  `content` text NOT NULL,
  `parentId` bigint DEFAULT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  `createAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`replyId`),
  KEY `tb_reply_FK` (`cardId`),
  KEY `tb_reply_FK_1` (`userNo`),
  CONSTRAINT `tb_reply_FK` FOREIGN KEY (`cardId`) REFERENCES `tb_card` (`cardId`),
  CONSTRAINT `tb_reply_FK_1` FOREIGN KEY (`userNo`) REFERENCES `tb_user` (`userNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- rapcrew.tb_like definition

CREATE TABLE `tb_like` (
  `likeId` bigint NOT NULL AUTO_INCREMENT,
  `likeType` int NOT NULL DEFAULT '0' COMMENT '1: 게시글 2: 댓글',
  `userNo` bigint NOT NULL,
  `cardId` bigint DEFAULT NULL,
  `replyId` bigint DEFAULT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`likeId`),
  KEY `tb_like_FK` (`userNo`),
  KEY `tb_like_FK_1` (`cardId`),
  KEY `tb_like_FK_2` (`replyId`),
  CONSTRAINT `tb_like_FK` FOREIGN KEY (`userNo`) REFERENCES `tb_user` (`userNo`),
  CONSTRAINT `tb_like_FK_1` FOREIGN KEY (`cardId`) REFERENCES `tb_card` (`cardId`),
  CONSTRAINT `tb_like_FK_2` FOREIGN KEY (`replyId`) REFERENCES `tb_reply` (`replyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;