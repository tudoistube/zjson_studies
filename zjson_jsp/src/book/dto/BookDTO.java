package book.dto;

public class BookDTO {
	
	String book_isbn;
	String book_title;
	String book_author;
	String book_img;
	String book_sale_price;
	String book_description;
	
	public BookDTO(String book_isbn, String book_title, String book_author, String book_img, String book_sale_price,
			String book_description) {
		super();
		this.book_isbn = book_isbn;
		this.book_title = book_title;
		this.book_author = book_author;
		this.book_img = book_img;
		this.book_sale_price = book_sale_price;
		this.book_description = book_description;
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
	
	
		
}
