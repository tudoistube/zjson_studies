create table zjsp_ch14_2_member(
member_id varchar2(15),
member_pw varchar2(13),
member_name varchar2(20),
member_age number,
member_gender varchar2(5),
member_email varchar2(30),
primary key(member_id)
);

CREATE TABLE zjsp_ch14_2_memberboard(
	BOARD_NUM NUMBER,
	BOARD_ID VARCHAR2(20),
	BOARD_SUBJECT VARCHAR2(50),
	BOARD_CONTENT VARCHAR2(2000),
	BOARD_FILE VARCHAR2(50),
	BOARD_RE_REF NUMBER,
	BOARD_RE_LEV NUMBER,
	BOARD_RE_SEQ NUMBER,
	BOARD_READCOUNT NUMBER,
	BOARD_DATE DATE,
	PRIMARY KEY(BOARD_NUM)
);

alter table zjsp_ch14_2_memberboard
	add constraint pk_board_id foreign key(board_id)
	references zjsp_ch14_2_member(member_id);
	
alter table zjsp_ch14_board rename to zjsp_ch14_memberboard;
	
create table zjsp_ch15_users(
	id varchar2(12) primary key,
	passwd varchar2(12),
	addr varchar2(50),
	job varchar2(30),
	salary number,
	name varchar2(12)
);

insert into zjsp_ch15_users values('java', 'java', 'Suwon', 'Programmer', 5000000, '순두부');