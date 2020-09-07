package com.ui.model;

public class Awards {
	public Awards(String awardTitle, String awardSubtitle,
			String awardDescription, String awardDate, int rotaryYearId) {
		super();
		this.awardTitle = awardTitle;
		this.awardSubtitle = awardSubtitle;
		this.awardDescription = awardDescription;
		this.awardDate = awardDate;
		this.rotaryYearId = rotaryYearId;		
	}
	public Awards(int awardId, String awardTitle, String awardSubtitle,
			String awardDescription, String awardDate, int rotaryYearId,
			int createdBy, String ipAddress) {
		super();
		this.awardId = awardId;
		this.awardTitle = awardTitle;
		this.awardSubtitle = awardSubtitle;
		this.awardDescription = awardDescription;
		this.awardDate = awardDate;
		this.rotaryYearId = rotaryYearId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Awards(int awardId, String awardTitle, String awardSubtitle, String awardDate,
			String awardDescription, int rotaryYearId,
			String status, String createdDate) {
		super();
		this.awardId = awardId;
		this.awardTitle = awardTitle;
		this.awardSubtitle = awardSubtitle;
		this.awardDate = awardDate;
		this.awardDescription = awardDescription;		
		this.rotaryYearId = rotaryYearId;
		this.status = status;
		this.createdDate = createdDate;
	}
	public Awards(String awardTitle, String awardSubtitle,
			String awardDescription, String awardDate, int rotaryYearId,
			String status, int createdBy, String ipAddress) {
		super();
		this.awardTitle = awardTitle;
		this.awardSubtitle = awardSubtitle;
		this.awardDescription = awardDescription;
		this.awardDate = awardDate;
		this.rotaryYearId = rotaryYearId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	int awardId;
	String awardTitle;
	String awardSubtitle;
	String awardDescription;
	String awardDate;
	int rotaryYearId;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	
	public int getAwardId() {
		return awardId;
	}
	public String getAwardTitle() {
		return awardTitle;
	}
	public String getAwardSubtitle() {
		return awardSubtitle;
	}
	public String getAwardDescription() {
		return awardDescription;
	}
	public int getRotaryYearId() {
		return rotaryYearId;
	}
	public String getStatus() {
		return status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getAwardDate() {
		return awardDate;
	}
}
