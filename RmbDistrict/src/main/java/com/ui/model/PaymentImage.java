package com.ui.model;

public class PaymentImage {
	
	public PaymentImage(String image, int paymentId, int createdBy, String ipAddress) {
		super();
		this.image = image;
		this.paymentId = paymentId;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	
	
	private int paymentImageId;
	private String image;
	private int paymentId;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getPaymentImageId() {
		return paymentImageId;
	}
	public String getImage() {
		return image;
	}
	public int getPaymentId() {
		return paymentId;
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
