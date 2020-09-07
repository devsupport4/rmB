package com.ui.model;

public class Payment {
	
	public Payment(int paymentId, String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String transactionNumber, String contactNumber,
			String comments, float amountCharges, String status, int createdBy,
			String ipAddress) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.transactionNumber = transactionNumber;
		this.contactNumber = contactNumber;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	
	public Payment(int paymentId, String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String transactionNumber, String contactNumber,
			String paymentPlace, String comments, float amountCharges,
			String status, int createdBy, String ipAddress) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.transactionNumber = transactionNumber;
		this.contactNumber = contactNumber;
		this.paymentPlace = paymentPlace;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	
	public Payment(int paymentId, String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String bankName, String branchName,
			String accountNumber, String transactionNumber, String comments,
			float amountCharges, String status, int createdBy, String ipAddress) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.transactionNumber = transactionNumber;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	
	public Payment(int paymentId, String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String bankName, String branchName,
			String accountNumber, String chequeNumber, String chequeDate,
			String comments, float amountCharges, String status, int createdBy, String ipAddress) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.chequeNumber = chequeNumber;
		this.chequeDate = chequeDate;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	
	public Payment(int paymentId, String transactionDate, float transactionAmount, int currencyIdTransactionAmount,
			float transactionCharges, int currencyIdTransactionCharges, String paidToWhom, String paymentType,
			String bankName, String branchName, String accountNumber,
			String chequeNumber, String chequeDate, String demandDraftNumber,
			String demandDraftDate, String transactionNumber,
			String contactNumber, String paymentPlace, String comments,
			float amountCharges) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.chequeNumber = chequeNumber;
		this.chequeDate = chequeDate;
		this.demandDraftNumber = demandDraftNumber;
		this.demandDraftDate = demandDraftDate;
		this.transactionNumber = transactionNumber;
		this.contactNumber = contactNumber;
		this.paymentPlace = paymentPlace;
		this.comments = comments;
		this.amountCharges = amountCharges;
	}
	public Payment(int paymentId, String transactionDate,
			float transactionAmount, float transactionCharges, String comments,
			int memberId) {
		super();
		this.paymentId = paymentId;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.transactionCharges = transactionCharges;
		this.comments = comments;
		this.memberId = memberId;
	}
	public Payment(String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String transactionNumber, String contactNumber,
			String comments, float amountCharges, int memberId, String status, int createdBy,
			String ipAddress) {
		super();
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.transactionNumber = transactionNumber;
		this.contactNumber = contactNumber;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Payment(String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String transactionNumber, String contactNumber,
			String paymentPlace, String comments, float amountCharges,
			int memberId, String status, int createdBy, String ipAddress) {
		super();
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.transactionNumber = transactionNumber;
		this.contactNumber = contactNumber;
		this.paymentPlace = paymentPlace;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Payment(String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String bankName, String branchName,
			String accountNumber, String transactionNumber, String comments,
			float amountCharges, int memberId, String status, int createdBy,
			String ipAddress) {
		super();
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.transactionNumber = transactionNumber;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	public Payment(String transactionDate, float transactionAmount,
			int currencyIdTransactionAmount, float transactionCharges,
			int currencyIdTransactionCharges, String paidToWhom,
			String paymentType, String bankName, String branchName,
			String accountNumber, String chequeNumber, String chequeDate,
			String comments, float amountCharges, int memberId,
			String status, int createdBy, String ipAddress) {
		super();
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.currencyIdTransactionAmount = currencyIdTransactionAmount;
		this.transactionCharges = transactionCharges;
		this.currencyIdTransactionCharges = currencyIdTransactionCharges;
		this.paidToWhom = paidToWhom;
		this.paymentType = paymentType;
		this.bankName = bankName;
		this.branchName = branchName;
		this.accountNumber = accountNumber;
		this.chequeNumber = chequeNumber;
		this.chequeDate = chequeDate;
		this.comments = comments;
		this.amountCharges = amountCharges;
		this.memberId = memberId;		
		this.status = status;
		this.createdBy = createdBy;		
		this.ipAddress = ipAddress;
	}
	private int paymentId;
	private String transactionDate;
	private float transactionAmount;
	private int currencyIdTransactionAmount;
	private float transactionCharges;
	private int currencyIdTransactionCharges;
	private String paidToWhom;
	private String paymentType;	
	private String bankName;
	private String branchName;
	private String accountNumber;
	private String chequeNumber;
	private String chequeDate;
	private String demandDraftNumber;
	private String demandDraftDate;
	private String transactionNumber;
	private String contactNumber;
	private String paymentPlace;
	private String comments;
	private float amountCharges;
	private int memberId;	
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	
	public int getPaymentId() {
		return paymentId;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public float getTransactionAmount() {
		return transactionAmount;
	}
	public int getCurrencyIdTransactionAmount() {
		return currencyIdTransactionAmount;
	}
	public float getTransactionCharges() {
		return transactionCharges;
	}
	public int getCurrencyIdTransactionCharges() {
		return currencyIdTransactionCharges;
	}
	public String getPaidToWhom() {
		return paidToWhom;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public String getBankName() {
		return bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getChequeNumber() {
		return chequeNumber;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public String getDemandDraftNumber() {
		return demandDraftNumber;
	}
	public String getDemandDraftDate() {
		return demandDraftDate;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getPaymentPlace() {
		return paymentPlace;
	}
	public String getComments() {
		return comments;
	}
	public float getAmountCharges() {
		return amountCharges;
	}
	public int getMemberId() {
		return memberId;
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
	public String getStatus() {
		return status;
	}
	
	
	
			
	

}
