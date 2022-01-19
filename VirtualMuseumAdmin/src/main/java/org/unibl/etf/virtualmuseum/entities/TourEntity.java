package org.unibl.etf.virtualmuseum.entities;

import java.sql.Timestamp;

public class TourEntity {
	
	private int id;
	private int museumId;
	private String name;
	private String museumName;
	private Timestamp startDateTime;
	private double duration;
	private double price;
	
	public TourEntity() { }
	
	public TourEntity(int id, int museumId, String name, Timestamp startDateTime, double duration, double price) {
		super();
		this.id = id;
		this.museumId = museumId;
		this.name = name;
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.price = price;
	}
	
	public TourEntity(int id, int museumId, String name, String museumName, Timestamp startDateTime, double duration, double price) {
		super();
		this.id = id;
		this.museumId = museumId;
		this.name = name;
		this.museumName = museumName;
		this.startDateTime = startDateTime;
		this.duration = duration;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMuseumId() {
		return museumId;
	}
	public void setMuseumId(int museumId) {
		this.museumId = museumId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMuseumName() {
		return museumName;
	}
	public void setMuseumName(String museumName) {
		this.museumName = museumName;
	}
	public Timestamp getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
