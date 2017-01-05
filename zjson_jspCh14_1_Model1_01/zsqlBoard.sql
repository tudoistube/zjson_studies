select * from ZJSP_STUDENT;

create table zjsp_board(
	board_num number,
	board_name varchar2(20),
	board_pass varchar2(15),
	board_subject varchar2(50),
	board_content varchar2(2000),
	board_file varchar2(50),
	board_re_ref number,
	board_re_lev number,
	board_re_seq number,
	board_readcount number,
	board_date date,
	primary key(board_num)
);

select * from ZJSP_BOARD;

drop table zjsp_board;


//drop table zjson_book;
create table zjson_book(
    book_num number,
	book_isbn varchar2(120),
	book_title varchar2(120),
	book_author varchar2(20),
	book_img varchar2(2000),
	book_sale_price number,
	book_description varchar2(2000),
	primary key(book_num)
);

select * from zjson_book;