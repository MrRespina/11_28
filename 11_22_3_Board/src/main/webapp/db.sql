CREATE TABLE nov22_member(
	m_id varchar2(20 char) PRIMARY KEY,
	m_pw varchar2(24 char) not null,
	m_name varchar2(10 char) not null,
	m_phone varchar2(13 char) not null,
	m_birth Date not null,
	m_photo varchar2(256 char) not null
);

DROP TABLE nov22_member cascade constraint purge;

truncate table nov22_member;

select * from nov22_member;

UPDATE nov22_member set m_pw='1234',m_name='구구',m_phone='삼삼',m_birth=to_date('19990903','YYYYMMDD') WHERE m_id='asd';

INSERT INTO nov22_member VALUES('Respina','sdj7524','Ji','010-7151-7524',to_date('19950222','YYYYMMDD'),'1.png');

SELECT * FROM nov22_member WHERE m_id=?

DELETE FROM NOV22_MEMBER WHERE m_id='c';

CREATE SEQUENCE nov27_board_seq;
DROP SEQUENCE nov27_board_seq;

CREATE TABLE nov27_board(

	b_no number(3) primary key,
	b_writer varchar2(10 char) not null,
	b_when date not null,
	b_text varchar2(200 char) not null,
	CONSTRAINT fk_board FOREIGN KEY(b_writer) REFERENCES nov22_member(m_id) ON DELETE cascade

);

select * from nov27_board;

UPDATE nov27_board SET B_WHEN = sysdate,B_TEXT = '화요일!' WHERE b_no = 3;

DROP TABLE nov27_board CASCADE CONSTRAINT purge;

INSERT INTO nov27_board VALUES(nov27_board_seq.nextval,'Respina',sysdate,'123456');
INSERT INTO nov27_board VALUES(nov27_board_seq.nextval,'Respina',sysdate,'안녕!');
INSERT INTO nov27_board VALUES(nov27_board_seq.nextval,'Respina',sysdate,'월요일');
INSERT INTO nov27_board VALUES(nov27_board_seq.nextval,'Respina',sysdate,'HI.');

SELECT COUNT(b_no) FROM nov27_board;

SELECT * FROM nov22_member,nov27_board WHERE b_writer = m_id; 

SELECT COUNT(*) FROM nov22_member,nov27_board WHERE b_writer = m_id and b_text like '%123%';

SELECT * FROM nov27_board WHERE b_no = (SELECT b_no FROM nov27_board WHERE b_text like '%안녕%') order by b_when desc;

SELECT * FROM nov27_board order by b_when DESC;

SELECT * FROM 
(SELECT rownum as rn, b_no,b_writer,b_when,b_text,m_photo FROM 
nov22_member, nov27_board WHERE m_id = b_writer and b_text like '%안녕%' ORDER BY b_when DESC) WHERE rn >=1 and rn <=2;

SELECT * FROM nov22_member, nov27_board WHERE m_id = b_writer and b_text like '%안녕%' ORDER BY b_when DESC;

DELETE FROM NOV27_BOARD WHERE b_no =