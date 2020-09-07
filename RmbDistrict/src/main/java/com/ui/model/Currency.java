package com.ui.model;

public class Currency {
	public Currency(int currencyId, String currencyName, String currencyCode, String currencySymbol,
			String description, String status, int createdBy, String ipAddress) {
		super();
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Currency(String currencyName, String currencyCode,
			String currencySymbol, String description, String status,
			int createdBy, String ipAddress) {
		super();
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Currency(int currencyId, String currencyName, String currencyCode,
			String description, String status, int createdBy, String ipAddress) {
		super();
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Currency(int currencyId, String currencyName, String currencyCode,
			String currencySymbol, String description, String status,
			int createdBy) {
		super();
		this.currencyId = currencyId;
		this.currencyName = currencyName;
		this.currencyCode = currencyCode;
		this.currencySymbol = currencySymbol;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;		
	}
	private int currencyId;
	private String currencyName;
	private String currencyCode;
	private String currencySymbol;
	private String description;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getCurrencyId() {
		return currencyId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public String getCurrencySymbol() {
		return currencySymbol;
	}
	public String getDescription() {
		return description;
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
	
		

}
