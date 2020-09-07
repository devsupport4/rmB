package com.ui.model;

public class News {	
	
	public News(int newsId, int serviceProjectId, String newsTitle, String newsDate, String image, String newsDesc) {
		super();
		this.newsId = newsId;
		this.serviceProjectId = serviceProjectId;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.image = image;
		this.newsDesc = newsDesc;		
	}
	public News(int newsId, String newsTitle, String newsDate, String image, String newsDesc, int serviceProjectId) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.image = image;
		this.newsDesc = newsDesc;
		this.serviceProjectId = serviceProjectId;
	}
	public News(String newsTitle, String newsSubtitle, int beneficiaryTypeId, String beneficiaryNo, String newsDate,
			String image, String imageTitle, String newsDesc, int rotaryYearId, int serviceProjectId,
			String status, int createdBy, String ipAddress) {
		super();
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryNo = beneficiaryNo;
		this.newsDate = newsDate;
		this.image = image;
		this.imageTitle = imageTitle;
		this.newsDesc = newsDesc;
		this.rotaryYearId = rotaryYearId;
		this.serviceProjectId = serviceProjectId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public News(String newsTitle, String newsSubtitle, String newsDate,
			String image, String imageTitle, int serviceProjectId, String newsDesc, int rotaryYearId) {
		super();
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDate = newsDate;
		this.image = image;
		this.imageTitle = imageTitle;
		this.serviceProjectId = serviceProjectId;
		this.newsDesc = newsDesc;
		this.rotaryYearId = rotaryYearId;		
	}
	public News(int newsId, String newsTitle, String newsSubtitle, int beneficiaryTypeId, String beneficiaryNo,
			String newsDate, String image, String imageTitle, String newsDesc,
			int rotaryYearId, int serviceProjectId) {
		super();
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryNo = beneficiaryNo;
		this.newsDate = newsDate;
		this.image = image;
		this.imageTitle = imageTitle;
		this.newsDesc = newsDesc;
		this.rotaryYearId = rotaryYearId;
		this.serviceProjectId = serviceProjectId;
	}
	public News(int newsId, int serviceProjectId, String newsTitle, String newsSubtitle, 
			String newsDate, String image, String imageTitle, String newsDesc) {
		super();
		this.newsId = newsId;
		this.serviceProjectId = serviceProjectId;
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.newsDate = newsDate;
		this.image = image;
		this.imageTitle = imageTitle;
		this.newsDesc = newsDesc;				
	}
	public News(String newsTitle, String newsSubtitle, int beneficiaryTypeId, String beneficiaryNo,
			String newsDate, String image, String imageTitle, String newsDesc,
			int rotaryYearId, int serviceProjectId, int newsId) {
		super();		
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryNo = beneficiaryNo;
		this.newsDate = newsDate;
		this.image = image;
		this.imageTitle = imageTitle;
		this.newsDesc = newsDesc;
		this.serviceProjectId = serviceProjectId;
		this.rotaryYearId = rotaryYearId;
		this.newsId = newsId;
	}
	public News(String newsTitle, String newsSubtitle, int beneficiaryTypeId, String beneficiaryNo,
			String newsDate, String imageTitle, String newsDesc,
			int rotaryYearId, int serviceProjectId, int newsId) {
		super();		
		this.newsTitle = newsTitle;
		this.newsSubtitle = newsSubtitle;
		this.beneficiaryTypeId = beneficiaryTypeId;
		this.beneficiaryNo = beneficiaryNo;
		this.newsDate = newsDate;		
		this.imageTitle = imageTitle;
		this.newsDesc = newsDesc;
		this.rotaryYearId = rotaryYearId;
		this.serviceProjectId = serviceProjectId;
		this.newsId = newsId;
	}
	int newsId;
	String newsTitle;
	String newsSubtitle;
	String newsDate;
	String image;
	String imageTitle;
	String newsDesc;
	int rotaryYearId;
	int serviceProjectId;
	String status;
	int createdBy;
	String createdDate;
	String ipAddress;
	int beneficiaryTypeId;
	String beneficiaryNo;
	
	public int getNewsId() {
		return newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public String getNewsSubtitle() {
		return newsSubtitle;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public String getImage() {
		return image;
	}
	public String getImageTitle() {
		return imageTitle;
	}
	public String getNewsDesc() {
		return newsDesc;
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
	public int getServiceProjectId() {
		return serviceProjectId;
	}
	public int getBeneficiaryTypeId() {
		return beneficiaryTypeId;
	}
	public String getBeneficiaryNo() {
		return beneficiaryNo;
	}

}
