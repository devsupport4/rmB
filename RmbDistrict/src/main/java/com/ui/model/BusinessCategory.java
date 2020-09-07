package com.ui.model;

public class BusinessCategory {
	public BusinessCategory(int businessCategoryId, String businessCategoryName) {
		super();
		this.businessCategoryId = businessCategoryId;
		this.businessCategoryName = businessCategoryName;
	}
	public BusinessCategory(int businessCategoryId,
			String businessCategoryName, String businessCategoryDescription,
			int createdBy, String ipAddress) {
		super();
		this.businessCategoryId = businessCategoryId;
		this.businessCategoryName = businessCategoryName;
		this.businessCategoryDescription = businessCategoryDescription;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public BusinessCategory(int businessCategoryId,
			String businessCategoryName, String businessCategoryDescription) {
		super();
		this.businessCategoryId = businessCategoryId;
		this.businessCategoryName = businessCategoryName;
		this.businessCategoryDescription = businessCategoryDescription;
	}
	public BusinessCategory(String businessCategoryName,
			String businessCategoryDescription, String status, int createdBy,
			String ipAddress) {
		super();
		this.businessCategoryName = businessCategoryName;
		this.businessCategoryDescription = businessCategoryDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int businessCategoryId;
	private String businessCategoryName;
	private String businessCategoryDescription;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getBusinessCategoryId() {
		return businessCategoryId;
	}
	public String getBusinessCategoryName() {
		return businessCategoryName;
	}
	public String getBusinessCategoryDescription() {
		return businessCategoryDescription;
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
