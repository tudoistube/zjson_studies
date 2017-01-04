package book.dto;

public class BookDTO {
	
	String author;
	String book_img;
	String book_sale_price;
	String book_description;
	
	public BookDTO(String author, String book_img, String book_sale_price, String book_description) {
		super();
		this.author = author;
		this.book_img = book_img;
		this.book_sale_price = book_sale_price;
		this.book_description = book_description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
