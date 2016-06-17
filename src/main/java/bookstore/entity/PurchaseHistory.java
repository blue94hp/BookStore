package bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="purchaseHistory")
public class PurchaseHistory {

	@Field
	@Id
	private String id;
	
	@Field
	private String username;
	
	@Field
	private String book;
	
	@Field
	private int quantity;
	
	@Field
	private Double price;
	
	@Field
	private Double amount;
	
	@Field
	private String purchaseDate;

	public PurchaseHistory(String username, String book, int quantity, Double price, Double amount,
			String purchaseDate) {
		super();
		this.username = username;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.purchaseDate = purchaseDate;
	}

	public PurchaseHistory() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
