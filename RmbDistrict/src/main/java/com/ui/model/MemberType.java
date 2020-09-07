package com.ui.model;

public class MemberType {
	
	
	
	public MemberType(int memberTypeId, String memberTypeName) {
		super();
		this.memberTypeId = memberTypeId;
		this.memberTypeName = memberTypeName;
	}
	public int memberTypeId;
	public String memberTypeName;
	public String status;
	public String createdBy;
	public String createdDate;
	public String ipAddress;
	
	
	public int getMemberTypeId() {
		return memberTypeId;
	}
	public String getMemberTypeName() {
		return memberTypeName;
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
