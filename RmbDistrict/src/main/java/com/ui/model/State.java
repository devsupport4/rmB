package com.ui.model;

public class State {
	
	
	public State(int stateId, String stateName, int countryId) {
		super();
		this.stateId = stateId;
		this.stateName = stateName;
		this.countryId = countryId;
	}
	public int stateId;
	public String stateName;
	public String stateCode;	
	public int countryId;
	public String status;
	public int createdBy;
	public String createdDate;
	public String ipAddress;
	
	
	public int getStateId() {
		return stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public String getStateCode() {
		return stateCode;
	}	
	public int getCountryId() {
		return countryId;
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
