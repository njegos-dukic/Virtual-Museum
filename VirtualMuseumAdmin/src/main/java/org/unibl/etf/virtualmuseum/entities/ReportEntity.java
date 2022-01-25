package org.unibl.etf.virtualmuseum.entities;

public class ReportEntity {

	private String date;
	private String hour;
	private String count;
	
	public ReportEntity(String date, String hour, String count) {
		super();
		this.date = date;
		this.hour = hour;
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
