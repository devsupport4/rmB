package com.ui.model;

public class Country {
	
	public Country(int countryId, String countryName, String countryCode) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCode = countryCode;	
	}
	public int countryId;
	public String countryName;
	public String countryCode;	
	public String status;
	public String createdBy;
	public String createdDate;
	public String ipAddress;
	
	
	public int getCountryId() {
		return countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}	
	public String getStatus() {
		return status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	

	
	
	
}
