package com.ui.model;

public class BoardOfDirectors {

	public BoardOfDirectors(int boardOfDirectorsId, float sequence,
			String designation, int rotaryYearId, String rotaryYearTitle, int id, String title, String firstName,
			String lastName, String image) {
		super();
		this.boardOfDirectorsId = boardOfDirectorsId;
		this.sequence = sequence;
		this.designation = designation;	
		this.rotaryYearId = rotaryYearId;
		this.rotaryYearTitle = rotaryYearTitle;
		this.id = id;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
	}
	public BoardOfDirectors(int boardOfDirectorsId, float sequence,
			String designation, int memberId, int memberFamilyId, int rotaryYearId,  
			String memberFirstName, String memberLastName, String rotaryYearTitle, String memberProfilePicture,
			String memberNameTitle) {
		super();
		this.boardOfDirectorsId = boardOfDirectorsId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;
		this.memberFamilyId = memberFamilyId;
		this.rotaryYearId = rotaryYearId;		
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberProfilePicture = memberProfilePicture;
		this.memberNameTitle = memberNameTitle;
	}
	public BoardOfDirectors(int rotaryYearId, float sequence,
			String designation, int memberId, int memberFamilyId, String status,
			int createdBy, String ipAddress) {
		super();		
		this.rotaryYearId = rotaryYearId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;
		this.memberFamilyId = memberFamilyId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public BoardOfDirectors(int rotaryYearId, float sequence,
			String designation, int memberId, int memberFamilyId, int createdBy, 
			String ipAddress, int boardOfDirectorsId) {
		super();		
		this.rotaryYearId = rotaryYearId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;	
		this.memberFamilyId = memberFamilyId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.boardOfDirectorsId = boardOfDirectorsId;
	}
	public BoardOfDirectors() {
		// TODO Auto-generated constructor stub
	}
	int boardOfDirectorsId;
	float sequence;
	String designation;
	int memberId;
	int memberFamilyId;
	int rotaryYearId;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	String rotaryYearTitle;
	String memberFirstName;
	String memberLastName;
	String memberProfilePicture;
	String memberNameTitle;
	int id;
	String title;
	String firstName;
	String lastName;
	String image;
	
	public int getBoardOfDirectorsId() {
		return boardOfDirectorsId;
	}
	public float getSequence() {
		return sequence;
	}
	public String getDesignation() {
		return designation;
	}
	public int getMemberId() {
		return memberId;
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
	public String getRotaryYearTitle() {
		return rotaryYearTitle;
	}
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public String getMemberProfilePicture() {
		return memberProfilePicture;
	}
	public String getMemberNameTitle() {
		return memberNameTitle;
	}
	public int getMemberFamilyId() {
		return memberFamilyId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public void setMemberFamilyId(int memberFamilyId) {
		this.memberFamilyId = memberFamilyId;
	}
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getImage() {
		return image;
	}
	public String getTitle() {
		return title;
	}
	
}
