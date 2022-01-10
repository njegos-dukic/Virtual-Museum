package org.unibl.etf.virtualmuseum.entities;

public class UserEntity {

	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private boolean isAdmin;
	private String adminToken;
	private boolean isApproved;
	private boolean isBlocked;
	private boolean isPasswordReset;
	
	public UserEntity(int id, String username, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public UserEntity(int id, String username, String password, String firstName, String lastName, String email, boolean isAdmin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isAdmin = isAdmin;
	}
	
	public UserEntity(int id, String username, String firstName, String lastName, String email, boolean isAdmin, boolean isApproved, boolean isBlocked, boolean isPasswordReset) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isApproved = isApproved;
		this.isBlocked = isBlocked;
		this.isPasswordReset = isPasswordReset;
	}
	
	public UserEntity(int id, String username, String password, String firstName, String lastName, String email, boolean isAdmin, boolean isApproved, boolean isBlocked, boolean isPasswordReset) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.isAdmin = isAdmin;
		this.isApproved = isApproved;
		this.isBlocked = isBlocked;
		this.isPasswordReset = isPasswordReset;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAdminToken() {
		return adminToken;
	}

	public void setAdminToken(String adminToken) {
		this.adminToken = adminToken;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isPasswordReset() {
		return isPasswordReset;
	}

	public void setPasswordReset(boolean isPasswordReset) {
		this.isPasswordReset = isPasswordReset;
	}
}
