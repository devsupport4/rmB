package com.ui.model;

public class ServiceProjectImage {
	
	public ServiceProjectImage(int serviceProjectImageId, int serviceProjectId,
			String sequence, String imageTitle, String image) {
		super();
		this.serviceProjectImageId = serviceProjectImageId;
		this.serviceProjectId = serviceProjectId;
		this.sequence = sequence;
		this.imageTitle = imageTitle;
		this.image = image;
	}
	public ServiceProjectImage(String sequence, String imageTitle, String image) {
		super();
		this.sequence = sequence;
		this.imageTitle = imageTitle;
		this.image = image;
	}
	public ServiceProjectImage(int serviceProjectId, String sequence,
			String imageTitle, String image, int createdBy, String ipAddress) {
		super();
		this.serviceProjectId = serviceProjectId;
		this.sequence = sequence;
		this.imageTitle = imageTitle;
		this.image = image;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	int serviceProjectImageId;
	int serviceProjectId;
	String sequence;
	String imageTitle;
	String image;
	int createdBy;
	String createdDate;
	String ipAddress;
	
	public int getServiceProjectImageId() {
		return serviceProjectImageId;
	}
	public int getServiceProjectId() {
		return serviceProjectId;
	}
	public String getSequence() {
		return sequence;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public String getImage() {
		return image;
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
