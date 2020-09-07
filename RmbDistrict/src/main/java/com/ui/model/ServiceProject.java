package com.ui.model;

public class ServiceProject {

	public ServiceProject(int serviceProjectId, String serviceProjectTitle,
			String serviceProjectSubtitle, String serviceProjectDate,
			int serviceSequence, String serviceProjectDescription,
			String status, int createdBy, String createdDate, String ipAddress, String image) {
		super();
		this.serviceProjectId = serviceProjectId;
		this.serviceProjectTitle = serviceProjectTitle;
		this.serviceProjectSubtitle = serviceProjectSubtitle;
		this.serviceProjectDate = serviceProjectDate;
		this.serviceSequence = serviceSequence;
		this.serviceProjectDescription = serviceProjectDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
		this.image = image;
	}
	public ServiceProject(int serviceProjectId, String serviceProjectTitle,
			String serviceProjectSubtitle, String serviceProjectDate,
			int serviceSequence, String serviceProjectDescription,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.serviceProjectId = serviceProjectId;
		this.serviceProjectTitle = serviceProjectTitle;
		this.serviceProjectSubtitle = serviceProjectSubtitle;
		this.serviceProjectDate = serviceProjectDate;
		this.serviceSequence = serviceSequence;
		this.serviceProjectDescription = serviceProjectDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;		
	}
	public ServiceProject(String serviceProjectTitle,
			String serviceProjectSubtitle, String serviceProjectDate,
			int serviceSequence, String serviceProjectDescription,
			String status, int createdBy, String ipAddress) {
		super();
		this.serviceProjectTitle = serviceProjectTitle;
		this.serviceProjectSubtitle = serviceProjectSubtitle;
		this.serviceProjectDate = serviceProjectDate;
		this.serviceSequence = serviceSequence;
		this.serviceProjectDescription = serviceProjectDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		
	}
	public ServiceProject(String serviceProjectTitle,
			String serviceProjectSubtitle, String serviceProjectDate,
			int serviceSequence, String serviceProjectDescription,
			String status, int createdBy, String ipAddress, int serviceProjectId) {
		super();
		this.serviceProjectId = serviceProjectId;
		this.serviceProjectTitle = serviceProjectTitle;
		this.serviceProjectSubtitle = serviceProjectSubtitle;
		this.serviceProjectDate = serviceProjectDate;
		this.serviceSequence = serviceSequence;
		this.serviceProjectDescription = serviceProjectDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	
	int serviceProjectId;
	String serviceProjectTitle;
	String serviceProjectSubtitle;
	String serviceProjectDate;
	int serviceSequence;
	String serviceProjectDescription;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	String image;
	
	public int getServiceProjectId() {
		return serviceProjectId;
	}
	public String getServiceProjectTitle() {
		return serviceProjectTitle;
	}
	public String getServiceProjectSubtitle() {
		return serviceProjectSubtitle;
	}
	public String getServiceProjectDate() {
		return serviceProjectDate;
	}
	public int getServiceSequence() {
		return serviceSequence;
	}
	public String getServiceProjectDescription() {
		return serviceProjectDescription;
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
	public String getImage() {
		return image;
	}
	
}
