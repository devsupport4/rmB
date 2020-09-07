package com.ui.model;

public class AlbumImage 
{	
	public AlbumImage(int albumImageId, String imageTitle, String description,
			String image, int albumId) {
		super();
		this.albumImageId = albumImageId;
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.albumId = albumId;
	}
	public AlbumImage(int albumImageId, String imageTitle, String description,
			String image, int albumId, String albumTitle,
			String albumDescription) {
		super();
		this.albumImageId = albumImageId;
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.albumId = albumId;
		this.albumTitle = albumTitle;
		this.albumDescription = albumDescription;
	}
	public AlbumImage(String imageTitle, String description,
			String image, int albumId, int createdBy, String ipAddress) {
		super();		
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.albumId = albumId;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int albumImageId;
	private String imageTitle;
	private String description;
	private String image;
	private int albumId;
	private int createdBy;
	private String createdDate;	
	private String ipAddress;
	
	private String albumTitle;
	private String albumDescription;
	
	
	public int getAlbumImageId() {
		return albumImageId;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public String getDescription() {
		return description;
	}
	public String getImage() {
		return image;
	}
	public int getAlbumId() {
		return albumId;
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
	public String getAlbumTitle() {
		return albumTitle;
	}
	public String getAlbumDescription() {
		return albumDescription;
	}
}
