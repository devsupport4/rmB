package com.ui.model;

public class EventCharges {
	
	private int eventChargeId;
	private int eventId;
	private String registrationFor;
	private int currencyId;
	private float amount;
	private String status;
	private int createdBy;
	private String createdDate;	
	private String ipAddress;
	private String currencyName;
	private String currencyCode;
	public int getEventChargeId() {
		return eventChargeId;
	}
	public void setEventChargeId(int eventChargeId) {
		this.eventChargeId = eventChargeId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getRegistrationFor() {
		return registrationFor;
	}
	public void setRegistrationFor(String registrationFor) {
		this.registrationFor = registrationFor;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	


}
