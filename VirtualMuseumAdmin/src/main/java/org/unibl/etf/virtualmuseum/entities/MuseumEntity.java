package org.unibl.etf.virtualmuseum.entities;

public class MuseumEntity {

	private int id;
	private String name;
	private String address;
	private String phone;
	private String city;
	private String country;
	private String type;
	private String maps;
	
	public MuseumEntity(int id, String name, String address, String phone, String city, String country, String type, String maps) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.country = country;
		this.type = type;
		this.maps = maps;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMaps() {
		return maps;
	}
	
	public void setMaps(String maps) {
		this.maps = maps;
	}

	@Override
	public String toString() {
		return "MuseumEntity [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", city="
				+ city + ", country=" + country + ", type=" + type + ", maps=" + maps + "]";
	}
}
