package com.ui.model;

public class BeneficiaryType {
	public BeneficiaryType(String beneficiaryTypeTitle, String status,
			int createdBy, String ipAddress) {
		super();
		this.beneficiaryTypeTitle = beneficiaryTypeTitle;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public BeneficiaryType(int beneficiaryTypeId, String beneficiaryTypeTitle) {
		super();
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryTypeTitle = beneficiaryTypeTitle;
	}
	public BeneficiaryType( String beneficiaryTypeTitle,
			int createdBy, String ipAddress, int beneficiaryTypeId) {
		super();		
		this.beneficiaryTypeTitle = beneficiaryTypeTitle;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.beneficiaryTypeId = beneficiaryTypeId;
	}
	int beneficiaryTypeId;
	String beneficiaryTypeTitle;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	public int getBeneficiaryTypeId() {
		return beneficiaryTypeId;
	}
	public String getBeneficiaryTypeTitle() {
		return beneficiaryTypeTitle;
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
