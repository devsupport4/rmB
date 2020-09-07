package com.ui.model;

public class AwardImage {	
	public AwardImage(int awardImageId, String imageTitle, String description, String image,
			int awardId, String awardTitle, String awardSubtitle,
			String awardDescription, String awardDate) {
		super();
		this.awardImageId = awardImageId;
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.awardId = awardId;
		this.awardTitle = awardTitle;
		this.awardSubtitle = awardSubtitle;
		this.awardDescription = awardDescription;
		this.awardDate = awardDate;
	}
	public AwardImage(int awardImageId, String imageTitle, String description,
			String image, int awardId) {
		super();
		this.awardImageId = awardImageId;
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.awardId = awardId;
	}
	public AwardImage(String imageTitle, String description, String image,
			int awardId, String ipAddress) {
		super();
		this.imageTitle = imageTitle;
		this.description = description;
		this.image = image;
		this.awardId = awardId;
		this.ipAddress = ipAddress;
	}
	int awardImageId;
	String imageTitle;
	String description;
	String image;
	int awardId;
	String createdDate;
	String ipAddress;
	
	String awardTitle;
	String awardSubtitle;
	String awardDescription;
	String awardDate;
	
	public int getAwardImageId() {
		return awardImageId;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public String getDescription() {
		return description;
	}
	public int getAwardId() {
		return awardId;
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
	public String getAwardTitle() {
		return awardTitle;
	}
	public String getAwardSubtitle() {
		return awardSubtitle;
	}
	public String getAwardDescription() {
		return awardDescription;
	}
	public String getAwardDate() {
		return awardDate;
	}
}
