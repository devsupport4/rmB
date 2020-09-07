package com.ui.model;

public class EventImage {
	
	public EventImage(String image, String eventImageTitle, String eventImageDescription) {
		super();
		this.image = image;
		this.eventImageTitle = eventImageTitle;
		this.eventImageDescription = eventImageDescription;
	}
	public EventImage(int eventId, String image, String eventImageTitle, String eventImageDescription, int createdBy,
			String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventId = eventId;
		this.image = image;
		this.eventImageTitle = eventImageTitle;
		this.eventImageDescription = eventImageDescription;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public EventImage(int eventImageId, int eventId, String image, String eventImageTitle, String eventImageDescription,
			int createdBy, String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventImageId = eventImageId;
		this.eventId = eventId;
		this.image = image;
		this.eventImageTitle = eventImageTitle;
		this.eventImageDescription = eventImageDescription;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public int eventImageId;
	public int eventId;
	public String image;
	public String eventImageTitle;
	public String eventImageDescription;
	public int createdBy;
	public String createdDate;
	public String createdTime;
	public String ipAddress;
	
	public int getEventImageId() {
		return eventImageId;
	}
	public int getEventId() {
		return eventId;
	}
	public String getImage() {
		return image;
	}
	public String getEventImageTitle() {
		return eventImageTitle;
	}
	public String getEventImageDescription() {
		return eventImageDescription;
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
