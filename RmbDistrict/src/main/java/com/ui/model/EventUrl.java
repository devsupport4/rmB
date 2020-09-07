package com.ui.model;

public class EventUrl {
	
	public EventUrl(String eventUrlTitle, String eventUrl, int eventUrlSequence) {
		super();
		this.eventUrlTitle = eventUrlTitle;
		this.eventUrl = eventUrl;
		this.eventUrlSequence = eventUrlSequence;
	}
	public EventUrl(int eventId, String eventUrlTitle, String eventUrl, int eventUrlSequence, int createdBy,
			String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventId = eventId;
		this.eventUrlTitle = eventUrlTitle;
		this.eventUrl = eventUrl;
		this.eventUrlSequence = eventUrlSequence;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public int eventUrlId;
	public int eventId;
	public String eventUrlTitle;
	public String eventUrl;
	public int eventUrlSequence;
	public int createdBy;
	public String createdDate;
	public String createdTime;
	public String ipAddress;
	
	public int getEventUrlId() {
		return eventUrlId;
	}
	public int getEventId() {
		return eventId;
	}
	public String getEventUrlTitle() {
		return eventUrlTitle;
	}
	public String getEventUrl() {
		return eventUrl;
	}
	public int getEventUrlSequence() {
		return eventUrlSequence;
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
