package org.unibl.etf.virtualbankui.entities;

public class CardEntity {

	private String firstName;
	private String lastName;
	private String cardNumber;
	private String cardType;
	private String expirationDate;
	private String cvv;
	private Double balance;
	private Boolean isEnabled;
	
	public CardEntity(String firstName, String lastName, String cardNumber, String cardType, String expirationDate, String cvv, Double balance, Boolean isEnabled) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
		this.balance = balance;
		this.isEnabled = isEnabled;
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
