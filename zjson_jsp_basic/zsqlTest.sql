create table zjsp_student(
num number,
name varchar2(10),
primary key(num));

alter table zjsp_student modify(
name varchar2(20));

insert into zjsp_student (num, name) values (1, '한예슬');
insert into zjsp_student (num, name) values (2, '이영애');
insert into zjsp_student (num, name) values (3, '김희애');
insert into zjsp_student (num, name) values (4, '김희선');
insert into zjsp_student (num, name) values (5, '신세경');

select * from zjsp_student order by num desc;

create table zjsp_member(
id varchar2(15),
password varchar2(10),
name varchar2(20),
age number,
gender varchar2(5),
email varchar2(30),
primary key(id)
);

create table zjsp_clobtable(num number, content clob);

create table zjsp_jstlsql(
	num number,
	name varchar2(10),
	primary key(num)
);