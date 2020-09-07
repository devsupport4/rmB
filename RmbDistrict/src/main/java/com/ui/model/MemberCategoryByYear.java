package com.ui.model;

public class MemberCategoryByYear {
	public MemberCategoryByYear(int memberCategoryId) {
		super();
		this.memberCategoryId = memberCategoryId;
	}
	public MemberCategoryByYear(int rotaryYearId, int createdBy, String ipAddress) {
      super();
      this.rotaryYearId = rotaryYearId;     
      this.createdBy = createdBy;
      this.ipAddress = ipAddress;
  }
	public MemberCategoryByYear(int rotaryYearId, int memberId,
			int memberCategoryId, int createdBy, String ipAddress) {
		super();
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int memberCategoryByYearId;
	private int rotaryYearId;
	private int memberId;
	private int memberCategoryId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getMemberCategoryByYearId() {
		return memberCategoryByYearId;
	}
	public int getRotaryYearId() {
		return rotaryYearId;
	}
	public int getMemberId() {
		return memberId;
	}
	public int getMemberCategoryId() {
		return memberCategoryId;
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
