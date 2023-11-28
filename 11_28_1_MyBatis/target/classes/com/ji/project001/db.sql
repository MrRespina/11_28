CREATE TABLE nov28_fruit(
	f_name varchar2(10 char) PRIMARY KEY,
	f_price number(5) not null
);

INSERT INTO nov28_fruit VALUES('사과',2800);
INSERT INTO nov28_fruit VALUES('바나나',1900);
INSERT INTO nov28_fruit VALUES('메론',12900);

SELECT * FROM nov28_fruit;

DELETE FROM nov28_fruit WHERE f_name = '사과';