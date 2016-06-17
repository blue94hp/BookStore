package bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_roles")
public class UserRoles {

	@Field
	@Id
	private String id;
	
	@Field
	private String userId;
	
	@Field
	private String role;

	public UserRoles() {
		super();
	}

	public UserRoles(String userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
