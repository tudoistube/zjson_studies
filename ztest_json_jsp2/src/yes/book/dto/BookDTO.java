package yes.book.dto;

import java.util.Date;

public class BookDTO {
	
	int    book_num;
	String book_isbn;
	String book_title;
	String book_author;
	String book_img;
	String book_sale_price;
	String book_description;
	String book_pub_date_string;
	Date   book_pub_date;
	
	public BookDTO() {
		super();
	}	
	
	public BookDTO(String book_isbn, String book_title, String book_author, String book_img, String book_sale_price,
			String book_description, String book_pub_date_string) {
		super();
		this.book_isbn = book_isbn;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_img = book_img;
		this.book_sale_price = book_sale_price;
		this.book_description = book_description;
		this.book_pub_date_string = book_pub_date_string;
	}

	public BookDTO(int book_num, String book_isbn, String book_title, String book_author, String book_img, String book_sale_price,
			String book_description, Date book_pub_date) {
		super();
		this.book_num = book_num;
		this.book_isbn = book_isbn;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_img = book_img;
		this.book_sale_price = book_sale_price;
		this.book_description = book_description;
		this.book_pub_date = book_pub_date;
	}
	
	public int getBook_num() {
		return book_num;
	}

	public void setBook_num(int book_num) {
		this.book_num = book_num;
	}	
	
	public String getBook_isbn() {
		return book_isbn;
	}

	public void setBook_isbn(String book_isbn) {
		this.book_isbn = book_isbn;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_img() {
		return book_img;
	}

	public void setBook_img(String book_img) {
		this.book_img = book_img;
	}

	public String getBook_sale_price() {
		return book_sale_price;
	}

	public void setBook_sale_price(String book_sale_price) {
		this.book_sale_price = book_sale_price;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}

	public String getBook_pub_date_string() {
		return book_pub_date_string;
	}

	public void setBook_pub_date_string(String book_pub_date_string) {
		this.book_pub_date_string = book_pub_date_string;
	}

	public Date getBook_pub_date() {
		return book_pub_date;
	}

	public void setBook_pub_date(Date book_pub_date) {
		this.book_pub_date = book_pub_date;
	}

	@Override
	public String toString() {
		return "BookDTO [book_num=" + book_num + ", book_isbn=" + book_isbn + ", book_title=" + book_title
				+ ", book_author=" + book_author + ", book_img=" + book_img + ", book_sale_price=" + book_sale_price
				+ ", book_description=" + book_description + ", book_pub_date_string=" + book_pub_date_string
				+ ", book_pub_date=" + book_pub_date + "]";
	}	
	
		
}



