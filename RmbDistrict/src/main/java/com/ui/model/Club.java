package com.ui.model;

public class Club {
	public Club(int clubId,
			String clubName, String clubDescription,
			int createdBy, String ipAddress) {
		super();
		this.clubId = clubId;
		this.clubName = clubName;
		this.clubDescription = clubDescription;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public Club(int clubId,
			String clubName, String clubDescription) {
		super();
		this.clubId = clubId;
		this.clubName = clubName;
		this.clubDescription = clubDescription;
	}
	public Club(String clubName,
			String clubDescription, String status, int createdBy,
			String ipAddress) {
		super();
		this.clubName = clubName;
		this.clubDescription = clubDescription;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int clubId;
	private String clubName;
	private String clubDescription;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getClubId() {
		return clubId;
	}
	public String getClubName() {
		return clubName;
	}
	public String getClubDescription() {
		return clubDescription;
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
