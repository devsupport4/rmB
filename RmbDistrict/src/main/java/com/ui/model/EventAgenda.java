package com.ui.model;

public class EventAgenda {
	
	public EventAgenda(String eventAgendaTitle, int eventAgendaSequence, String eventAgendaConclusion) {
		super();
		this.eventAgendaTitle = eventAgendaTitle;
		this.eventAgendaSequence = eventAgendaSequence;
		this.eventAgendaConclusion = eventAgendaConclusion;
	}
	public EventAgenda(int eventId, String eventAgendaTitle, int eventAgendaSequence, String eventAgendaConclusion, 
			int createdBy, String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventId = eventId;
		this.eventAgendaTitle = eventAgendaTitle;
		this.eventAgendaSequence = eventAgendaSequence;
		this.eventAgendaConclusion = eventAgendaConclusion;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public EventAgenda(int eventAgendaId, int eventId, String eventAgendaTitle, int createdBy,
			String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventAgendaId = eventAgendaId;
		this.eventId = eventId;
		this.eventAgendaTitle = eventAgendaTitle;		
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	
	public int eventAgendaId;
	public int eventId;
	public String eventAgendaTitle;	
	public int eventAgendaSequence;
	public String eventAgendaConclusion;
	public int createdBy;
	public String createdDate;
	public String createdTime;
	public String ipAddress;
	
	public int getEventAgendaId() {
		return eventAgendaId;
	}
	public int getEventId() {
		return eventId;
	}
	public String getEventAgendaTitle() {
		return eventAgendaTitle;
	}
	public int getEventAgendaSequence() {
		return eventAgendaSequence;
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
	public String getEventAgendaConclusion() {
		return eventAgendaConclusion;
	}
	

}
