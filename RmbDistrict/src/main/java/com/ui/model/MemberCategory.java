package com.ui.model;

public class MemberCategory {
	
	
	public MemberCategory(int memberCategoryId, String memberCategoryName) {
		super();
		this.memberCategoryId = memberCategoryId;
		this.memberCategoryName = memberCategoryName;
	}
	public int memberCategoryId;
	public String memberCategoryName;
	public String status;
	public int createdBy;
	public String createdDate;
	public String ipAddress;
	
	
	public int getMemberCategoryId() {
		return memberCategoryId;
	}
	public String getMemberCategoryName() {
		return memberCategoryName;
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
