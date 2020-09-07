package com.ui.model;

public class EventAgendaConclusion {
	
	public EventAgendaConclusion(int eventAgendaId, String eventAgendaConclusion, int createdBy, String createdDate,
			String createdTime, String ipAddress) {
		super();
		this.eventAgendaId = eventAgendaId;
		this.eventAgendaConclusion = eventAgendaConclusion;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public EventAgendaConclusion(int eventAgendaConclusionId, int eventAgendaId, String eventAgendaConclusion,
			int createdBy, String createdDate, String createdTime, String ipAddress) {
		super();
		this.eventAgendaConclusionId = eventAgendaConclusionId;
		this.eventAgendaId = eventAgendaId;
		this.eventAgendaConclusion = eventAgendaConclusion;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
		this.ipAddress = ipAddress;
	}
	public int eventAgendaConclusionId;
	public int eventAgendaId;
	public String eventAgendaConclusion;
	public int createdBy;
	public String createdDate;
	public String createdTime;
	public String ipAddress;
	public int getEventAgendaConclusionId() {
		return eventAgendaConclusionId;
	}
	public int getEventAgendaId() {
		return eventAgendaId;
	}
	public String getEventAgendaConclusion() {
		return eventAgendaConclusion;
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
