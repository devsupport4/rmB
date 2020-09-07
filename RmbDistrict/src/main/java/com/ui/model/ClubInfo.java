package com.ui.model;

public class ClubInfo {
	public ClubInfo(int clubInfoId, String clubTitle, String clubShortitle,
			String clubLogo, String clubNo, String districtNo, String zoneNo, String meetingAddress1, String meetingAddress2,
			String meetingDay, String meetingTime, String mapLink, String contactPersonName,
			String contactEmail, String contactTelephoneNo, String contactMobileNo, String contactAddress) {
		super();
		this.clubInfoId = clubInfoId;
		this.clubTitle = clubTitle;
		this.clubShortitle = clubShortitle;
		this.clubLogo = clubLogo;
		this.clubNo = clubNo;
		this.districtNo = districtNo;
		this.zoneNo = zoneNo;
		this.meetingAddress1 = meetingAddress1;
		this.meetingAddress2 = meetingAddress2;
		this.meetingDay = meetingDay;
		this.meetingTime = meetingTime;
		this.mapLink = mapLink;
		this.contactPersonName = contactPersonName;
		this.contactEmail = contactEmail;
		this.contactTelephoneNo = contactTelephoneNo;
		this.contactMobileNo = contactMobileNo;
		this.contactAddress = contactAddress;
	}
	public ClubInfo(int clubInfoId, String clubTitle, String clubShortitle,
			String clubLogo, String clubNo, String districtNo, String zoneNo, String meetingAddress1, 
			String meetingAddress2, String meetingDay, String meetingTime, String mapLink, 
			String contactPersonName, String contactEmail, String contactTelephoneNo, 
			String contactMobileNo, String contactAddress, int createdBy, String ipAddress) {
		super();
		this.clubInfoId = clubInfoId;
		this.clubTitle = clubTitle;
		this.clubShortitle = clubShortitle;
		this.clubLogo = clubLogo;
		this.clubNo = clubNo;
		this.districtNo = districtNo;
		this.zoneNo = zoneNo;
		this.meetingAddress1 = meetingAddress1;
		this.meetingAddress2 = meetingAddress2;
		this.meetingDay = meetingDay;
		this.meetingTime = meetingTime;
		this.mapLink = mapLink;
		this.contactPersonName = contactPersonName;
		this.contactEmail = contactEmail;
		this.contactTelephoneNo = contactTelephoneNo;
		this.contactMobileNo = contactMobileNo;
		this.contactAddress = contactAddress;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	int clubInfoId;
	String clubTitle;
	String clubShortitle;
	String clubLogo;
	String clubNo;
	String districtNo;
	String zoneNo;
	String meetingAddress1;
	String meetingAddress2;
	String meetingDay;
	String meetingTime;
	String mapLink;
	String contactPersonName;
	String contactEmail;
	String contactTelephoneNo;
	String contactMobileNo;
	String contactAddress;
	int createdBy;
	String createdDate;
	String ipAddress;
	public int getClubInfoId() {
		return clubInfoId;
	}
	public String getClubTitle() {
		return clubTitle;
	}
	public String getClubShortitle() {
		return clubShortitle;
	}
	public String getClubLogo() {
		return clubLogo;
	}
	public String getMeetingAddress1() {
		return meetingAddress1;
	}
	public String getMeetingAddress2() {
		return meetingAddress2;
	}
	public String getMeetingDay() {
		return meetingDay;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public String getMapLink() {
		return mapLink;
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
	public String getContactPersonName() {
		return contactPersonName;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public String getContactTelephoneNo() {
		return contactTelephoneNo;
	}
	public String getContactMobileNo() {
		return contactMobileNo;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public String getClubNo() {
		return clubNo;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public String getZoneNo() {
		return zoneNo;
	}
}
