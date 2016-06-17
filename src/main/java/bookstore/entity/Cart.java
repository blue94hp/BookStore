package bookstore.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="cart")
public class Cart {

	@Field
	@Id
	private String id;
	
	@Field
	private List<Book> books;
	
	
	public Cart() {
		super();
	}

	public Cart(List<Book> books) {
		super();
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
