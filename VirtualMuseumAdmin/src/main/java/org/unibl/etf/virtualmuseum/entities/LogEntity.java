package org.unibl.etf.virtualmuseum.entities;

public class LogEntity {
	
	private String type;
	private String info;
	private String dateTime;
	
	public LogEntity(String type, String info, String dateTime) {
		super();
		this.type = type;
		this.info = info;
		this.dateTime = dateTime;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public String toString() {
		return "[" + this.type + "] at " + this.dateTime + " -- " + this.info;
	}
}
