package bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "book")
public class Book {

	@Field
	@Id
	private String id;

	@Field
	private String title;

	@Field
	private String type;

	@Field
	private String author;

	@Field
	private String publisher;

	@Field
	private String coverType;

	@Field
	private String format;

	@Field
	private Integer numberOfPage;

	@Field
	private Double price;

	@Field
	private String description;

	@Field
	private String imageUrl;
	
	public Book() {
		super();
	}

	public Book(String title, String type, String author, String publisher, String coverType, String format,
			Integer numberOfPage, Double price, String description, String imageUrl) {
		super();
		this.title = title;
		this.type = type;
		this.author = author;
		this.publisher = publisher;
		this.coverType = coverType;
		this.format = format;
		this.numberOfPage = numberOfPage;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(Integer numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
