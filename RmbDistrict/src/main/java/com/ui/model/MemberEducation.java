package com.ui.model;

public class MemberEducation {
	
	
	public MemberEducation(int memberEducationId, String degreeName,
			String passingYear, String grade, String instituteName) {
		super();
		this.memberEducationId = memberEducationId;
		this.degreeName = degreeName;
		this.passingYear = passingYear;
		this.grade = grade;
		this.instituteName = instituteName;
	}
	public MemberEducation(String degreeName, String passingYear, String grade,
			String instituteName, int memberId, int memberFamilyId,
			int createdBy, String ipAddress) {
		super();
		this.degreeName = degreeName;
		this.passingYear = passingYear;
		this.grade = grade;
		this.instituteName = instituteName;
		this.memberId = memberId;
		this.memberFamilyId = memberFamilyId;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public int memberEducationId;
	public String degreeName;
	public String passingYear;
	public String grade;
	public String instituteName;
	public int memberId;
	public int memberFamilyId;
	public int createdBy;
	public String createdDate;
	public String ipAddress;
	public int getMemberEducationId() {
		return memberEducationId;
	}
	public String getDegreeName() {
		return degreeName;
	}
	public String getPassingYear() {
		return passingYear;
	}
	public String getGrade() {
		return grade;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public int getMemberId() {
		return memberId;
	}
	public int getMemberFamilyId() {
		return memberFamilyId;
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
