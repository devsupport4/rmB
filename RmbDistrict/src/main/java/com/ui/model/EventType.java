package com.ui.model;

public class EventType {
	
	public EventType(int eventTypeId, String eventTypeTitle, String eventTypeDescription, String status, int createdBy,
			String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventTypeId = eventTypeId;
		this.eventTypeTitle = eventTypeTitle;
		this.eventTypeDescription = eventTypeDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	
	public int eventTypeId;
	public String eventTypeTitle;
	public String eventTypeDescription;
	public String status;
	public int createdBy;
	public String createdDate;
	public String createdTime;
	public String ipAddress;
	
	public int getEventTypeId() {
		return eventTypeId;
	}
	public String getEventTypeTitle() {
		return eventTypeTitle;
	}
	public String getEventTypeDescription() {
		return eventTypeDescription;
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
	public String getCreatedTime() {
		return createdTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}	
}
