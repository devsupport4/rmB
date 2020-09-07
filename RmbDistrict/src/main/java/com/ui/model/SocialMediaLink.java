package com.ui.model;

public class SocialMediaLink {
	public SocialMediaLink(int socialMediaLinkId,
			String socialMediaPlatformTitle, String socialMediaUrl,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.socialMediaLinkId = socialMediaLinkId;
		this.socialMediaPlatformTitle = socialMediaPlatformTitle;
		this.socialMediaUrl = socialMediaUrl;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public SocialMediaLink(String socialMediaPlatformTitle,
			String socialMediaUrl, String status, int createdBy,
			String ipAddress) {
		super();
		this.socialMediaPlatformTitle = socialMediaPlatformTitle;
		this.socialMediaUrl = socialMediaUrl;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public SocialMediaLink(String socialMediaPlatformTitle, String socialMediaUrl, 
			int createdBy, String ipAddress, int socialMediaLinkId) {
		super();
		this.socialMediaPlatformTitle = socialMediaPlatformTitle;
		this.socialMediaUrl = socialMediaUrl;		
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
		this.socialMediaLinkId = socialMediaLinkId;
	}
	int socialMediaLinkId;
	String socialMediaPlatformTitle;
	String socialMediaUrl;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	public int getSocialMediaLinkId() {
		return socialMediaLinkId;
	}
	public String getSocialMediaPlatformTitle() {
		return socialMediaPlatformTitle;
	}
	public String getSocialMediaUrl() {
		return socialMediaUrl;
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
}
