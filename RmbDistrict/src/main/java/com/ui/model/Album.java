package com.ui.model;

public class Album 
{
	
	public Album(int albumId, String albumTitle, String albumSubtitle,
			String albumDate, String description, int serviceProjectId) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumSubtitle = albumSubtitle;
		this.albumDate = albumDate;
		this.description = description;
		this.serviceProjectId = serviceProjectId;
	}
	public Album(int albumId, String albumTitle, String albumSubtitle,
			String albumDate, String description, int serviceProjectId,
			int createdBy, String ipAddress) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumSubtitle = albumSubtitle;
		this.albumDate = albumDate;
		this.description = description;
		this.serviceProjectId = serviceProjectId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Album(int albumId, String albumTitle, String albumSubtitle,
			String albumDate, String description, int serviceProjectId,
			String status, String createdDate) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumSubtitle = albumSubtitle;
		this.albumDate = albumDate;
		this.description = description;
		this.serviceProjectId = serviceProjectId;
		this.status = status;
		this.createdDate = createdDate;
	}
	public Album(int albumId, String albumTitle, String albumSubtitle,
			String albumDate, String description, int serviceProjectId,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumSubtitle = albumSubtitle;
		this.albumDate = albumDate;
		this.description = description;
		this.serviceProjectId = serviceProjectId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public Album(String albumTitle, String albumSubtitle, String albumDate,
			String description, int serviceProjectId, String status,
			int createdBy, String ipAddress) {
		super();
		this.albumTitle = albumTitle;
		this.albumSubtitle = albumSubtitle;
		this.albumDate = albumDate;
		this.description = description;
		this.serviceProjectId = serviceProjectId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int albumId;
	private String albumTitle;
	private String albumSubtitle;
	private String albumDate;
	private String description;
	private int serviceProjectId;	
	private String status;
	private int createdBy;
	private String createdDate;	
	private String ipAddress;
	
	
	public int getAlbumId() {
		return albumId;
	}
	public String getAlbumTitle() {
		return albumTitle;
	}
	public String getDescription() {
		return description;
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
	public String getAlbumSubtitle() {
		return albumSubtitle;
	}
	public String getAlbumDate() {
		return albumDate;
	}
	public int getServiceProjectId() {
		return serviceProjectId;
	}		

}
