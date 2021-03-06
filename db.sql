#데이터베이스 생성
DROP DATABASE IF EXISTS untact;
CREATE DATABASE untact;
USE untact;

#게시물 테이블 생성

CREATE TABLE article(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title CHAR(100) NOT NULL,
`body` TEXT NOT NULL
);

#게시물, 테스트 데이터 생성

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1 입니다.",
`body` = "내용1 입니다.";

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목2 입니다.",
`body` = "내용2 입니다.";


INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = "제목3 입니다.",
`body` = "내용3 입니다.";



UPDATE article
	SET updateDate = NOW(),
	title = "제목3 입니다New",
	`body` = "내용3 입니다New"
	WHERE id = 9;
	

DELETE FROM article
WHERE id = 3;
	
SELECT * FROM article;


# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(30) NOT NULL,
    loginPw VARCHAR(100) NOT NULL,
    `name` CHAR(30) NOT NULL,
    `nickname` CHAR(30) NOT NULL,
    `email` CHAR(100) NOT NULL,
    `cellphoneNo` CHAR(20) NOT NULL
);

# 로그인 ID로 검색했을 때
ALTER TABLE `member` ADD UNIQUE INDEX (`loginId`);

# 회원, 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = "user",
loginPw = "user1",
`name` = "user1",
nickname = "user1",
cellphoneNo = "01012341234",
email = "popol91@naver.com";


# 게시물 테이블에 회원번호 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 게시물의 작성자 회원1로 저장
UPDATE article
SET memberId = 1;
WHERE memberId = 0;

SELECT * FROM `member`;


SELECT * FROM MEMBER WHERE loginId = 'user1';

SELECT * FROM article;


/*
insert into article
(regDate, updateDate, memberId, title, `body`)
SELECT NOW(), NOW(), FLOOR(RAND() * 2) + 1, CONCAT('제목_', FLOOR(RAND() * 1000) + 1), CONCAT('내용_', FLOOR(RAND() * 1000) + 1)
from article;
*/

SELECT COUNT(*) FROM article; 


SELECT NOW(),NOW(),FLOOR(RAND()*2),CONCAT('제목_', FLOOR(RAND()*100)+1),CONCAT('내용_', FLOOR(RAND()*100)+1)
FROM article;


SELECT CONCAT('제목_', FLOOR(RAND()*100)+1);

# 게시판 테이블 추가 
CREATE TABLE board(
id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
regDage DATETIME NOT NULL,
upDateDate DATETIME NOT NULL,
`code` CHAR(20) UNIQUE NOT NULL,
`name` CHAR(20) UNIQUE NOT NULL
);

#공지사항 게시판 추가
INSERT INTO board
SET regDage = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';


#자유 게시판 추가
INSERT INTO board
SET regDage = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유게시판';

# 게시물 테이블에 게시판 번호 칼럼 추가, updateDate 칼럼 뒤
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 데이터 랜덤하게 게시판 지정

UPDATE article
SET boardId = FLOOR(RAND() * 2) + 1
WHERE boardId = 0;
