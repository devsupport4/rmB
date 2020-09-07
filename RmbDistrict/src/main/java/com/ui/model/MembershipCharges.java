package com.ui.model;

public class MembershipCharges {
	public MembershipCharges(int membershipChargeId, int memberId,
			int rotaryYearId, int memberCategoryId, float billingAmount,
			int currencyId) {
		super();
		this.membershipChargeId = membershipChargeId;
		this.memberId = memberId;
		this.rotaryYearId = rotaryYearId;
		this.memberCategoryId = memberCategoryId;
		this.billingAmount = billingAmount;
		this.currencyId = currencyId;
	}
	public MembershipCharges(int rotaryYearId, int memberCategoryId, float billingAmount,
			int currencyId, String status, int createdBy, String ipAddress) {
		super();	
		this.rotaryYearId = rotaryYearId;
		this.memberCategoryId = memberCategoryId;
		this.billingAmount = billingAmount;
		this.currencyId = currencyId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int membershipChargeId;
	private int memberId;
	private int rotaryYearId;
	private int memberCategoryId;
	private float billingAmount;
	private int currencyId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	public int getMembershipChargeId() {
		return membershipChargeId;
	}
	public int getMemberId() {
		return memberId;
	}
	public int getRotaryYearId() {
		return rotaryYearId;
	}
	public int getMemberCategoryId() {
		return memberCategoryId;
	}
	public float getBillingAmount() {
		return billingAmount;
	}
	public int getCurrencyId() {
		return currencyId;
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
