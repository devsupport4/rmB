package com.ui.model;

public class MembersCommittee {
	
	public MembersCommittee(int membersCommitteeId, float sequence,
			String designation, int memberId, int rotaryYearId,  
			String memberFirstName, String memberLastName, String rotaryYearTitle, String memberProfilePicture,
			String memberNameTitle,int fellowship_id) {
		super();
		this.membersCommitteeId = membersCommitteeId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;
		this.rotaryYearId = rotaryYearId;		
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberProfilePicture = memberProfilePicture;
		this.memberNameTitle = memberNameTitle;
		this.fellowship_id=fellowship_id;
		
	}
	public MembersCommittee(int membersCommitteeId, float sequence,
			String designation, int memberId, int rotaryYearId,  
			String memberFirstName, String memberLastName, String rotaryYearTitle, String memberProfilePicture,
			String memberNameTitle) {
		super();
		this.membersCommitteeId = membersCommitteeId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;
		this.rotaryYearId = rotaryYearId;		
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberProfilePicture = memberProfilePicture;
		this.memberNameTitle = memberNameTitle;
		
		
	}
	public MembersCommittee(int rotaryYearId, float sequence,
			String designation, int memberId, String status,
			int createdBy, String ipAddress,int fellowship_id,int user_role_id) {
		super();		
		this.rotaryYearId = rotaryYearId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.fellowship_id = fellowship_id;
		this.user_role_id = user_role_id;
	}
	public MembersCommittee(int rotaryYearId, float sequence,
			String designation, int memberId, int createdBy, String ipAddress, int membersCommitteeId) {
		super();		
		this.rotaryYearId = rotaryYearId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;	
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.membersCommitteeId = membersCommitteeId;
	}
	
	public MembersCommittee(int rotaryYearId, float sequence,
			String designation, int memberId, int createdBy, String ipAddress, int membersCommitteeId,int fellowship_id) {
		super();		
		this.rotaryYearId = rotaryYearId;
		this.sequence = sequence;
		this.designation = designation;
		this.memberId = memberId;	
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.membersCommitteeId = membersCommitteeId;
		this.fellowship_id=fellowship_id;
	}
	int membersCommitteeId;
	float sequence;
	String designation;
	int memberId;
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
	
	private int fellowship_id;
	private String fellowship_name;
	
	private int user_role_id;
	
	
	
	public int getFellowship_id() {
		return fellowship_id;
	}
	public void setFellowship_id(int fellowship_id) {
		this.fellowship_id = fellowship_id;
	}
	public String getFellowship_name() {
		return fellowship_name;
	}
	public void setFellowship_name(String fellowship_name) {
		this.fellowship_name = fellowship_name;
	}
	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public void setMembersCommitteeId(int membersCommitteeId) {
		this.membersCommitteeId = membersCommitteeId;
	}
	public void setSequence(float sequence) {
		this.sequence = sequence;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public void setRotaryYearId(int rotaryYearId) {
		this.rotaryYearId = rotaryYearId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void setRotaryYearTitle(String rotaryYearTitle) {
		this.rotaryYearTitle = rotaryYearTitle;
	}
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}
	public void setMemberProfilePicture(String memberProfilePicture) {
		this.memberProfilePicture = memberProfilePicture;
	}
	public void setMemberNameTitle(String memberNameTitle) {
		this.memberNameTitle = memberNameTitle;
	}
	public int getMembersCommitteeId() {
		return membersCommitteeId;
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

}
