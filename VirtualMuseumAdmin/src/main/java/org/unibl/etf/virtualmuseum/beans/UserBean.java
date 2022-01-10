package org.unibl.etf.virtualmuseum.beans;

public class UserBean {
	private String username;
	private String password;
	private String firstName;
	private String lastname;
	private String email;
	private boolean isAdmin;
	private String adminToken;
	private boolean isApproved;
	private boolean isBlocked;
	private boolean isPasswordReset;
	private boolean loggedIn;
	
	public UserBean() {	}
	
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public void logOut() {
		loggedIn = false;
	}
	public boolean login() {
		if(username.contains("etf") && password.equals("student"))
			loggedIn = true;
		else 
			loggedIn = false;
		return loggedIn;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void logout(){
		loggedIn = false;
	}
}
