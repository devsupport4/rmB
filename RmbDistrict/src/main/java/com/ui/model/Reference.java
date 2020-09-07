package com.ui.model;

public class Reference {

	public Reference(int referenceId, int referenceMembersId, String referenceFirstName,
			String referenceLastName, String referenceEmail,
			String referenceMobileNumber, String referenceAddress1,
			String referenceAddress2, int referenceStateId,
			String referenceCityName, String referencePincode, 
			String referenceOccupation,
			int memberId) {
		super();
		this.referenceId = referenceId;
		this.referenceMembersId = referenceMembersId;
		this.referenceFirstName = referenceFirstName;
		this.referenceLastName = referenceLastName;
		this.referenceEmail = referenceEmail;
		this.referenceMobileNumber = referenceMobileNumber;
		this.referenceAddress1 = referenceAddress1;
		this.referenceAddress2 = referenceAddress2;
		this.referenceStateId = referenceStateId;
		this.referenceCityName = referenceCityName;
		this.referencePincode = referencePincode;		
		this.referenceOccupation = referenceOccupation;
		this.memberId = memberId;
	}
	
	public Reference(int referenceId, int referenceMembersId, String referenceFirstName,
			String referenceLastName, String referenceEmail,
			String referenceMobileNumber, String referenceAddress1,
			String referenceAddress2, int referenceStateId,
			String referenceCityName, String referencePincode,
			String referenceCompanyName, String referenceOccupation,
			int memberId) {
		super();
		this.referenceId = referenceId;
		this.referenceMembersId = referenceMembersId;
		this.referenceFirstName = referenceFirstName;
		this.referenceLastName = referenceLastName;
		this.referenceEmail = referenceEmail;
		this.referenceMobileNumber = referenceMobileNumber;
		this.referenceAddress1 = referenceAddress1;
		this.referenceAddress2 = referenceAddress2;
		this.referenceStateId = referenceStateId;
		this.referenceCityName = referenceCityName;
		this.referencePincode = referencePincode;
		this.referenceCompanyName = referenceCompanyName;
		this.referenceOccupation = referenceOccupation;
		this.memberId = memberId;		
	}
	
	public Reference(int referenceId, int referenceMembersId, String referenceFirstName,
			String referenceLastName, String referenceEmail,
			String referenceMobileNumber, String referenceAddress1,
			String referenceAddress2, int referenceStateId,
			String referenceCityName, String referencePincode,
			String referenceCompanyName, String referenceOccupation,
			int createdBy, String ipAddress) {
		super();
		this.referenceId = referenceId;
		this.referenceMembersId = referenceMembersId;
		this.referenceFirstName = referenceFirstName;
		this.referenceLastName = referenceLastName;
		this.referenceEmail = referenceEmail;
		this.referenceMobileNumber = referenceMobileNumber;
		this.referenceAddress1 = referenceAddress1;
		this.referenceAddress2 = referenceAddress2;
		this.referenceStateId = referenceStateId;
		this.referenceCityName = referenceCityName;
		this.referencePincode = referencePincode;
		this.referenceCompanyName = referenceCompanyName;
		this.referenceOccupation = referenceOccupation;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Reference(int referenceMembersId, String referenceFirstName, String referenceLastName,
			String referenceEmail, String referenceMobileNumber,
			String referenceAddress1, String referenceAddress2,
			int referenceStateId, String referenceCityName,
			String referencePincode, String referenceCompanyName,
			String referenceOccupation, int memberId, String status, int createdBy,
			String ipAddress) {
		super();
		this.referenceMembersId = referenceMembersId;
		this.referenceFirstName = referenceFirstName;
		this.referenceLastName = referenceLastName;
		this.referenceEmail = referenceEmail;
		this.referenceMobileNumber = referenceMobileNumber;
		this.referenceAddress1 = referenceAddress1;
		this.referenceAddress2 = referenceAddress2;
		this.referenceStateId = referenceStateId;
		this.referenceCityName = referenceCityName;
		this.referencePincode = referencePincode;
		this.referenceCompanyName = referenceCompanyName;
		this.referenceOccupation = referenceOccupation;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;				
		this.ipAddress = ipAddress;
	}
	public int referenceId;
	public int referenceMembersId;
	public String referenceFirstName;
	public String referenceLastName;
	public String referenceEmail;
	public String referenceMobileNumber;
	public String referenceAddress1;
	public String referenceAddress2;
	public int referenceStateId;
	public String referenceCityName;
	public String referencePincode;
	public String referenceCompanyName;
	public String referenceOccupation;
	public int memberId;	
	public String status;
	public int createdBy;
	public String createdDate;	
	public String ipAddress;
	
	
	public int getReferenceId() {
		return referenceId;
	}
	public int getReferenceMembersId() {
		return referenceMembersId;
	}
	public String getReferenceFirstName() {
		return referenceFirstName;
	}
	public String getReferenceLastName() {
		return referenceLastName;
	}
	public String getReferenceEmail() {
		return referenceEmail;
	}
	public String getReferenceMobileNumber() {
		return referenceMobileNumber;
	}
	public String getReferenceAddress1() {
		return referenceAddress1;
	}
	public String getReferenceAddress2() {
		return referenceAddress2;
	}
	public int getReferenceStateId() {
		return referenceStateId;
	}
	public String getReferenceCityName() {
		return referenceCityName;
	}
	public String getReferencePincode() {
		return referencePincode;
	}
	public String getReferenceCompanyName() {
		return referenceCompanyName;
	}
	public String getReferenceOccupation() {
		return referenceOccupation;
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
	public int getMemberId() {
		return memberId;
	}	
}
