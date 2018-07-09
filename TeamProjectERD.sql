SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Views */

DROP VIEW IF EXISTS transactionhistory;



/* Drop Tables */

DROP TABLE IF EXISTS Alert;
DROP TABLE IF EXISTS Board;
DROP TABLE IF EXISTS Building;
DROP TABLE IF EXISTS Favorite;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Reserve;
DROP TABLE IF EXISTS Room;




/* Create Tables */

CREATE TABLE Alert
(
   -- 알람관리고유번호
   ANo int NOT NULL COMMENT '알람관리고유번호',
   -- 회원 ID
   ID varchar(30) COMMENT '회원 ID',
   -- 알림 내용
   AContent varchar(50) COMMENT '알림 내용',
   -- 알림이 등록된 시간
   RegDate datetime COMMENT '알림이 등록된 시간',
   -- 0 = 읽지않음
   -- 1 = 읽음
   AStat int COMMENT '0 = 읽지않음
1 = 읽음',
   PRIMARY KEY (ANo)
);


CREATE TABLE Board
(
   -- Review&상품문의 게시글 관리번호
   BNo int NOT NULL COMMENT 'Review&상품문의 게시글 관리번호',
   -- 건물관리고유번호
   SNo int COMMENT '건물관리고유번호',
   -- 회원 ID
   ID varchar(30) COMMENT '회원 ID',
   -- 회원 Email
   Email varchar(40) COMMENT '회원 Email',
   -- 게시글제목작성(1:1문의용에 사용)
   Subject varchar(1200) COMMENT '게시글제목작성(1:1문의용에 사용)',
   -- 게시글의 내용
   Content varchar(1200) COMMENT '게시글의 내용',
   -- 문의 성격을 표시
   QType varchar(40) COMMENT '문의 성격을 표시',
   -- 게시글 작성 시간
   RegDate datetime NOT NULL COMMENT '게시글 작성 시간',
   -- 원글 댓글 구분용 게시글원번호
   Ref int COMMENT '원글 댓글 구분용 게시글원번호',
   -- 원글 댓글 구분용 게시글 단계번호
   RefLevel int COMMENT '원글 댓글 구분용 게시글 단계번호',
   -- 1~5로 평점
   Score int COMMENT '1~5로 평점',
   -- 리뷰용 사진
   Img1 varchar(50) COMMENT '리뷰용 사진',
   -- 리뷰용 사진
   Img2 varchar(50) COMMENT '리뷰용 사진',
   -- 리뷰용 사진
   Img3 varchar(50) COMMENT '리뷰용 사진',
   -- 리뷰용 사진
   Img4 varchar(50) COMMENT '리뷰용 사진',
   -- 0 : Review
   -- 1 : 상품문의 (Guest -> Host)
   -- 2 : 관리자문의 (User -> Admin)
   Kind int COMMENT '0 : Review
1 : 상품문의 (Guest -> Host)
2 : 관리자문의 (User -> Admin)',
   PRIMARY KEY (BNo)
);


CREATE TABLE Building
(
   -- 건물관리고유번호
   SNo int NOT NULL COMMENT '건물관리고유번호',
   -- 회원 ID
   ID varchar(30) NOT NULL COMMENT '회원 ID',
   -- 공간의 이름
   SName varchar(50) COMMENT '공간의 이름',
   -- 공간 한 줄 소개
   SPreview varchar(80) COMMENT '공간 한 줄 소개',
   -- 공간에 대한 세부 설명
   SContent varchar(1200) COMMENT '공간에 대한 세부 설명',
   -- 제공하는 서비스 종류
   --  ex) 회의실, 세미나실, 연습실 등등
   SType varchar(1200) COMMENT '제공하는 서비스 종류
 ex) 회의실, 세미나실, 연습실 등등',
   -- 공간 꼬리표 / 검색에 사용
   STag varchar(30) COMMENT '공간 꼬리표 / 검색에 사용',
   -- 추가적으로 기입하는 시설 안내
   SInfoSub varchar(1200) COMMENT '추가적으로 기입하는 시설 안내',
   -- 공간 이용 시 주의사항 기입
   SRule varchar(1200) COMMENT '공간 이용 시 주의사항 기입',
   -- 공간 업무 운영 시간
   -- ex) 0024 : 00시 부터 24시까지
   SBHour varchar(130) COMMENT '공간 업무 운영 시간
ex) 0024 : 00시 부터 24시까지',
   -- 사업주 휴무요일
   -- ex) 토일월
   SHDay varchar(15) COMMENT '사업주 휴무요일
ex) 토일월',
   -- 공간 대표 커버 이미지
   SImg1 varchar(30) COMMENT '공간 대표 커버 이미지',
   -- \t로 구분자 사용
   -- ex ) img.png\timg2.jpg\t
   SImg2 varchar(1200) COMMENT '\t로 구분자 사용
ex ) img.png\timg2.jpg\t',
	-- 공간(건물) 주소
	SAddress varchar(50) COMMENT '공간(건물) 주소',
	PRIMARY KEY (SNo)

);


CREATE TABLE Favorite
(
   -- 회원 ID
   ID varchar(30) NOT NULL COMMENT '회원 ID',
   -- 건물관리고유번호
   SNo int NOT NULL COMMENT '건물관리고유번호'
);


CREATE TABLE Member
(
   -- Guest 회원 ID
   ID varchar(30) NOT NULL COMMENT 'Guest 회원 ID',
   -- 회원 패스워드
   PW varchar(30) NOT NULL COMMENT '회원 패스워드',
   -- 회원 이름
   Name varchar(30) NOT NULL COMMENT '회원 이름',
   -- 회원 Email
   Email varchar(40) NOT NULL COMMENT '회원 Email',
   -- 회원 휴대폰 번호
   Mob varchar(20) COMMENT '회원 휴대폰 번호',
   -- 회원 가입한 일자
   RegDate datetime NOT NULL COMMENT '회원 가입한 일자',
   -- Guest = 0
   -- Host = 1
   -- Admin = 2
   MemType int COMMENT 'Guest = 0
Host = 1
Admin = 2',
<<<<<<< HEAD
   -- Host계정 전환 시 사용 될 사업주명
   HostName varchar(200) COMMENT 'Host계정 전환 시 사용 될 사업주명',
   -- 사업자등록번호
   HostRegNo varchar(200) COMMENT '사업자등록번호',
   -- 사업자등록 소재지
   Address varchar(1200) COMMENT '사업자등록 소재지',
   -- Host계정 전환 시 대표번호로 사용
   Tel varchar(200) COMMENT 'Host계정 전환 시 대표번호로 사용',
   -- 은행명 + \t + 계좌번호 + \t + 예금주
   AccountNo varchar(200) COMMENT '은행명 + \t + 계좌번호 + \t + 예금주',
   regStatus int(1),
   -- 호스트 계정 전환용 사업자 등록증 파일이름
   PictureUrl varchar(130) COMMENT '호스트 계정 전환용 사업자 등록증 파일이름',
   PRIMARY KEY (ID),
   UNIQUE (Email)
=======
	-- Host계정 전환 시 사용 될 사업주명
	HostName varchar(200) COMMENT 'Host계정 전환 시 사용 될 사업주명',
	-- 사업자등록번호
	HostRegNo varchar(200) COMMENT '사업자등록번호',
	-- 사업자등록 소재지
	Address varchar(1200) COMMENT '사업자등록 소재지',
	-- Host계정 전환 시 대표번호로 사용
	Tel varchar(200) COMMENT 'Host계정 전환 시 대표번호로 사용',
	-- 은행명 + \t + 계좌번호 + \t + 예금주
	AccountNo varchar(200) COMMENT '은행명 + \t + 계좌번호 + \t + 예금주',
	regStatus int(1),
	-- 호스트 계정 전환용 사업자 등록증 파일이름
	PictureUrl varchar(130) COMMENT '호스트 계정 전환용 사업자 등록증 파일이름',
	PRIMARY KEY (ID),
	UNIQUE (Email)
>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git
);


CREATE TABLE Reserve
(
   -- 한 거래당 발생하는 거래관리고유번호
   ReNo int NOT NULL COMMENT '한 거래당 발생하는 거래관리고유번호',
   -- 회원 ID
   ID varchar(30) COMMENT '회원 ID',
   -- 건물관리고유번호
   SNo int COMMENT '건물관리고유번호',
   -- 세부공간관리번호
   SRNo int COMMENT '세부공간관리번호',
   -- 상품의 예약 날짜
   ReDate datetime NOT NULL COMMENT '상품의 예약 날짜',
   -- 상품 예약을 등록한 일시
   -- 
   RegDate datetime NOT NULL COMMENT '상품 예약을 등록한 일시
',
   -- 상품의 가격 * 수량
   --  = 총 지불 해야 할(한) 금액
   TotPrice int COMMENT '상품의 가격 * 수량
 = 총 지불 해야 할(한) 금액',
   -- 상품을 예약/구매한 숫자
   ReCnt int COMMENT '상품을 예약/구매한 숫자',
   -- 0 = 예약 / 결제대기
   -- 1 = 결제완료
   -- 2 = 취소/환불
   -- 3 = 환불완료
   ReStat int COMMENT '0 = 예약 / 결제대기
1 = 결제완료
2 = 취소/환불
3 = 환불완료',
   PRIMARY KEY (ReNo)
);


CREATE TABLE Room
(
   -- 건물관리고유번호
   SNo int NOT NULL COMMENT '건물관리고유번호',
   -- 세부공간관리번호
   SRNo int NOT NULL COMMENT '세부공간관리번호',
   -- 세부 공간 이름
   SRName varchar(30) COMMENT '세부 공간 이름',
   -- 제공하는 서비스 종류
   -- ex)세미나실, 회의실, 연습실 등등
   SRType varchar(50) COMMENT '제공하는 서비스 종류
ex)세미나실, 회의실, 연습실 등등',
   -- 세부공간에 대한 설명
   SRContent varchar(1200) COMMENT '세부공간에 대한 설명',
   -- 세부 공간의 시설 안내
   SRInfo varchar(1200) COMMENT '세부 공간의 시설 안내',
   -- 0 = 시간 단위
   -- 1 = 일자 단위
   SResType int COMMENT '0 = 시간 단위
1 = 일자 단위',
   -- 공간을 이용하는 이용객 제한 설정
   SRPersonLimit varchar(30) COMMENT '공간을 이용하는 이용객 제한 설정',
   -- 단위 당(시간or일자) 가격 설정
   SPrice int COMMENT '단위 당(시간or일자) 가격 설정',
   -- 세부공간을 표현하는 이미지 설정
   SRImg varchar(1200) COMMENT '세부공간을 표현하는 이미지 설정',
   PRIMARY KEY (SNo, SRNo)
);



/* Create Views */

<<<<<<< HEAD
CREATE VIEW transactionhistory AS select `bu`.`ID` AS `id`,`bu`.`SNo` AS `sNo`,`re`.`reDate` AS `reDate`,`re`.`regDate` AS `regDate`,`re`.`totPrice` AS `totPrice`,`re`.`cnt` AS `cnt` from `bigdb`.`building` `bu` join (select `bigdb`.`reserve`.`SNo` AS `sNo`,`bigdb`.`reserve`.`ReDate` AS `reDate`,`bigdb`.`reserve`.`RegDate` AS `regDate`,sum(`bigdb`.`reserve`.`TotPrice`) AS `totPrice`,count(0) AS `cnt`,`bigdb`.`reserve`.`ReStat` AS `reStat` from `bigdb`.`reserve` group by `bigdb`.`reserve`.`SNo` having `bigdb`.`reserve`.`ReStat` = 1 and `bigdb`.`reserve`.`ReDate` < current_timestamp()) `re` where `bu`.`SNo` = `re`.`sNo`;
=======
CREATE VIEW transactionhistory AS select `t`.`host` AS `host`,`t`.`guest` AS `guest`,`m`.`HostName` AS `hostName`,`m`.`Email` AS `Email`,`m`.`Address` AS `address`,`m`.`RegDate` AS `regDate`,`m`.`Tel` AS `tel`,`m`.`AccountNo` AS `accountNo`,`t`.`SNo` AS `sNo`,`t`.`SName` AS `sName`,`t`.`srNo` AS `sRNo`,`t`.`srName` AS `sRName`,`t`.`reNo` AS `reNo`,`t`.`reDate` AS `reDate`,`t`.`totPrice` AS `totPrice` from `bigdb`.`member` `m` join (select `bu`.`ID` AS `host`,`bu`.`SNo` AS `SNo`,`bu`.`SName` AS `SName`,`rr`.`reDate` AS `reDate`,`rr`.`regDate` AS `regDate`,`rr`.`totPrice` AS `totPrice`,`rr`.`reNo` AS `reNo`,`rr`.`srName` AS `srName`,`rr`.`srNo` AS `srNo`,`rr`.`guest` AS `guest` from (select `re`.`sNo` AS `sNo`,`ro`.`SRNo` AS `srNo`,`ro`.`SRName` AS `srName`,`re`.`reDate` AS `reDate`,`re`.`totPrice` AS `totPrice`,`re`.`reNo` AS `reNo`,`re`.`regDate` AS `regDate`,`re`.`id` AS `guest` from (select `bigdb`.`reserve`.`SNo` AS `sNo`,`bigdb`.`reserve`.`SRNo` AS `srNo`,`bigdb`.`reserve`.`ReDate` AS `reDate`,`bigdb`.`reserve`.`RegDate` AS `regDate`,`bigdb`.`reserve`.`TotPrice` AS `totPrice`,`bigdb`.`reserve`.`ReStat` AS `reStat`,`bigdb`.`reserve`.`ReNo` AS `reNo`,`bigdb`.`reserve`.`ID` AS `id` from `bigdb`.`reserve` where `bigdb`.`reserve`.`ReStat` = 1 and `bigdb`.`reserve`.`ReDate` < curdate()) `re` join `bigdb`.`room` `ro` where `re`.`srNo` = `ro`.`SRNo` and `re`.`sNo` = `ro`.`SNo` group by `re`.`reNo`) `rr` join `bigdb`.`building` `bu` where `rr`.`sNo` = `bu`.`SNo` group by `rr`.`reNo`) `t` where `t`.`host` = `m`.`ID` group by `t`.`reNo`;

>>>>>>> branch 'master' of https://github.com/Choiguevara88/SMS.git


