package com.ui.model;

public class ClubBulletin {
	
	public ClubBulletin(){}

	public ClubBulletin(int bulletinId, int issueNo, String bulletinTitle,
			String bulletinDate, String filePath, String thumbnailPath) {
		super();
		this.bulletinId = bulletinId;
		this.issueNo = issueNo;
		this.bulletinTitle = bulletinTitle;
		this.bulletinDate = bulletinDate;
		this.filePath = filePath;
		this.thumbnailPath = thumbnailPath;
	}
	public ClubBulletin(int issueNo, String bulletinTitle,
			String bulletinDate, int rotaryYearId, String ipAddress, int bulletinId) {
		super();
		
		this.issueNo = issueNo;
		this.bulletinTitle = bulletinTitle;
		this.bulletinDate = bulletinDate;
		this.rotaryYearId = rotaryYearId;
		this.ipAddress = ipAddress;
		this.bulletinId = bulletinId;
	}
	public ClubBulletin(int issueNo, String bulletinTitle,
			String bulletinDate, String filePath, String thumbnailPath, int rotaryYearId, String ipAddress, int bulletinId) {
		super();
		
		this.issueNo = issueNo;
		this.bulletinTitle = bulletinTitle;
		this.bulletinDate = bulletinDate;
		this.filePath = filePath;
		this.thumbnailPath = thumbnailPath;
		this.rotaryYearId = rotaryYearId;
		this.ipAddress = ipAddress;
		this.bulletinId = bulletinId;
	}
	public ClubBulletin(int bulletinId, int issueNo, String bulletinTitle,
			String bulletinDate, String filePath, String thumbnailPath, int rotaryYearId,
			String status, int createdBy, String createdDate, String ipAddress) {
		super();
		this.bulletinId = bulletinId;
		this.issueNo = issueNo;
		this.bulletinTitle = bulletinTitle;
		this.bulletinDate = bulletinDate;
		this.filePath = filePath;
		this.thumbnailPath = thumbnailPath;
		this.rotaryYearId = rotaryYearId;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.ipAddress = ipAddress;
	}
	public ClubBulletin(int issueNo, String bulletinTitle, String bulletinDate,
			String filePath, String thumbnailPath, int rotaryYearId, String status, int createdBy,
			String ipAddress) {
		super();
		this.issueNo = issueNo;
		this.bulletinTitle = bulletinTitle;
		this.bulletinDate = bulletinDate;
		this.filePath = filePath;
		this.thumbnailPath = thumbnailPath;
		this.rotaryYearId = rotaryYearId;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	int bulletinId;
	int issueNo;
	String bulletinTitle;
	String bulletinDate;
	String filePath;
	String thumbnailPath;
	int rotaryYearId;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	
	public int getBulletinId() {
		return bulletinId;
	}
	public int getIssueNo() {
		return issueNo;
	}
	public String getBulletinTitle() {
		return bulletinTitle;
	}
	public String getBulletinDate() {
		return bulletinDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public int getRotaryYearId() {
		return rotaryYearId;
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

	public String getThumbnailPath() {
		return thumbnailPath;
	}
}
