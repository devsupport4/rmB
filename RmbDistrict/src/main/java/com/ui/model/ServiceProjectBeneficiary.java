package com.ui.model;

public class ServiceProjectBeneficiary {
	

	public ServiceProjectBeneficiary(int serviceProjectBeneficiaryId, int serviceProjectId, int beneficiaryTypeId,
			int beneficiaryId, int donorId, String startDate, String endDate, String status, int createdBy,
			String createdDate, String ipAddress, String serviceProjectTitle, String benificiaryTypeTitle,
			String benificiaryFirstName, String benificiaryMiddleName, String benificiaryLastName,
			String donorFirstName, String donorMiddleName, String donorLastName) {
		super();
		this.serviceProjectBeneficiaryId = serviceProjectBeneficiaryId;
		this.serviceProjectId = serviceProjectId;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryId = beneficiaryId;
		this.donorId = donorId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.serviceProjectTitle = serviceProjectTitle;
		this.beneficiaryTypeTitle = benificiaryTypeTitle;
		this.benificiaryFirstName = benificiaryFirstName;
		this.benificiaryMiddleName = benificiaryMiddleName;
		this.benificiaryLastName = benificiaryLastName;
		this.donorFirstName = donorFirstName;
		this.donorMiddleName = donorMiddleName;
		this.donorLastName = donorLastName;
	}
	public ServiceProjectBeneficiary(int serviceProjectBeneficiaryId, int serviceProjectId, int beneficiaryTypeId, int beneficiaryId, int donorId,
			String startDate, String endDate, int createdBy, String ipAddress) {
		super();
		this.serviceProjectBeneficiaryId = serviceProjectBeneficiaryId;
		this.serviceProjectId = serviceProjectId;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryId = beneficiaryId;
		this.donorId = donorId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public ServiceProjectBeneficiary(int serviceProjectId, int beneficiaryTypeId, int beneficiaryId, int donorId,
			String startDate, String endDate, int createdBy, String ipAddress) {
		super();
		this.serviceProjectId = serviceProjectId;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryId = beneficiaryId;
		this.donorId = donorId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int serviceProjectBeneficiaryId;
	private int serviceProjectId;
	private int beneficiaryTypeId;
	private int beneficiaryId;
	private int donorId;
	private String startDate;
	private String endDate;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String serviceProjectTitle;
	private String beneficiaryTypeTitle;
	private String benificiaryFirstName;
	private String benificiaryMiddleName;
	private String benificiaryLastName;
	private String donorFirstName;
	private String donorMiddleName;
	private String donorLastName;
	
	public int getServiceProjectBeneficiaryId() {
		return serviceProjectBeneficiaryId;
	}
	public int getServiceProjectId() {
		return serviceProjectId;
	}
	public int getBeneficiaryTypeId() {
		return beneficiaryTypeId;
	}
	public int getBeneficiaryId() {
		return beneficiaryId;
	}
	public int getDonorId() {
		return donorId;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
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
	public String getServiceProjectTitle() {
		return serviceProjectTitle;
	}
	public String getBeneficiaryTypeTitle() {
		return beneficiaryTypeTitle;
	}
	public String getBenificiaryFirstName() {
		return benificiaryFirstName;
	}
	public String getBenificiaryMiddleName() {
		return benificiaryMiddleName;
	}
	public String getBenificiaryLastName() {
		return benificiaryLastName;
	}
	public String getDonorFirstName() {
		return donorFirstName;
	}
	public String getDonorMiddleName() {
		return donorMiddleName;
	}
	public String getDonorLastName() {
		return donorLastName;
	}
	


}
