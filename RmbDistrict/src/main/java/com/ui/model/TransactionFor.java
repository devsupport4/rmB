package com.ui.model;

public class TransactionFor {
	public TransactionFor(int transactionForId, String transactionForName,
			String transactionForDetail) {
		super();
		this.transactionForId = transactionForId;
		this.transactionForName = transactionForName;
		this.transactionForDetail = transactionForDetail;
	}
	private int transactionForId;
	private String transactionForName;
	private String transactionForDetail;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public int getTransactionForId() {
		return transactionForId;
	}
	public String getTransactionForName() {
		return transactionForName;
	}
	public String getTransactionForDetail() {
		return transactionForDetail;
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
