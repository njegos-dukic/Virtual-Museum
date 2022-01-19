package org.unibl.etf.virtualbankui.entities;

public class TransactionEntity {
	
	private String firstName;
	private String lastName;
	private String museumName;
	private String tourName;
	private Double price;
	private String info;
	
	public TransactionEntity(String firstName, String lastName, String museumName, String tourName, Double price, String info) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.museumName = museumName;
		this.tourName = tourName;
		this.price = price;
		this.info = info;
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

	public String getMuseumName() {
		return museumName;
	}

	public void setMuseumName(String museumName) {
		this.museumName = museumName;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " - " + museumName
				+ " - " + tourName + " - " + price + " EUR - " + info;
	}
	
	
}
