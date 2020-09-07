package com.ui.model;

public class RotaryYear {
	public RotaryYear(int rotaryYearId, String rotaryYearTitle) {
		super();
		this.rotaryYearId = rotaryYearId;
		this.rotaryYearTitle = rotaryYearTitle;
	}
	public RotaryYear(String rotaryYearTitle, String yearStartDate, String yearEndDate, int createdBy, 
			String ipAddress, int rotaryYearId) {
		super();	
		this.rotaryYearTitle =rotaryYearTitle;
		this.yearStartDate = yearStartDate;
		this.yearEndDate = yearEndDate;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.rotaryYearId = rotaryYearId;
	}
	public RotaryYear(String rotaryYearTitle, String yearStartDate, String yearEndDate, String status,
			int createdBy, String ipAddress) {
		super();
		this.rotaryYearTitle = rotaryYearTitle;
		this.yearStartDate = yearStartDate;
		this.yearEndDate = yearEndDate;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public RotaryYear(int rotaryYearId, String rotaryYearTitle, String yearStartDate,
			String yearEndDate, String defaultYear) {
		super();
		this.rotaryYearId = rotaryYearId;
		this.rotaryYearTitle = rotaryYearTitle;
		this.yearStartDate = yearStartDate;
		this.yearEndDate = yearEndDate;
		this.defaultYear = defaultYear;
	}
	int rotaryYearId;
	String rotaryYearTitle;
	String yearStartDate;
	String yearEndDate;
	String defaultYear;
	String status;
	int createdBy;
	String ipAddress;	
	
	public int getRotaryYearId() {
		return rotaryYearId;
	}
	public String getRotaryYearTitle() {
		return rotaryYearTitle;
	}
	public String getYearStartDate() {
		return yearStartDate;
	}
	public String getYearEndDate() {
		return yearEndDate;
	}
	public String getDefaultYear() {
		return defaultYear;
	}
	public String getStatus() {
		return status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getIpAddress() {
		return ipAddress;
	}
}
