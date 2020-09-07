package com.ui.model;

public class PaymentDetail {
	public PaymentDetail(int paymentDetailId, int transactionForId,
			int rotaryYearId, int memberId, int memberCategoryId,
			float paymentAmount, int currencyId, String transactionForName,
			String rotaryYearTitle, String memberCategoryName,
			String memberNameTitle, String memberFirstName,
			String memberMiddleName, String memberLastName, String currencyCode) {
		super();
		this.paymentDetailId = paymentDetailId;
		this.transactionForId = transactionForId;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.paymentAmount = paymentAmount;
		this.currencyId = currencyId;
		this.transactionForName = transactionForName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberCategoryName = memberCategoryName;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.currencyCode = currencyCode;
	}
	
	public PaymentDetail(int paymentDetailId, int sequence,
			String paymentNumber, String paymentStatus, String paymentMethod,
			String bankName, String branchName, String chequeNo, 
			String transactionDate, float paidAmount, int createdBy, String ipAddress) {
		super();
		this.paymentDetailId = paymentDetailId;
		this.sequence = sequence;
		this.paymentNumber = paymentNumber;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.bankName = bankName;
		this.branchName = branchName;
		this.chequeNo = chequeNo;
		this.transactionDate = transactionDate;
		this.paidAmount = paidAmount;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;		
	}
	
	public PaymentDetail(int sequence,
			String paymentNumber, int rotaryYearId, int memberId, int memberCategoryId,
			String paymentStatus, String paymentMethod,
			String bankName, String branchName, String chequeNo, 
			String transactionDate, float paidAmount, int createdBy, String ipAddress) {
		super();		
		this.sequence = sequence;
		this.paymentNumber = paymentNumber;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;		
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.bankName = bankName;
		this.branchName = branchName;
		this.chequeNo = chequeNo;
		this.transactionDate = transactionDate;
		this.paidAmount = paidAmount;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;		
	}
	
	public PaymentDetail(int paymentDetailId, int transactionForId,
			int rotaryYearId, int memberId, int memberCategoryId,
			float paymentAmount, int currencyId, String paymentStatus, 
			String paymentMethod, String bankName, String branchName, String chequeNo, 
			float paidAmount, String transactionDate, String transactionForName,
			String rotaryYearTitle, String memberCategoryName,
			String memberNameTitle, String memberFirstName,
			String memberMiddleName, String memberLastName, String currencyCode) {
		super();
		this.paymentDetailId = paymentDetailId;
		this.transactionForId = transactionForId;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.paymentAmount = paymentAmount;
		this.currencyId = currencyId;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;	
		this.bankName = bankName;
		this.branchName = branchName;
		this.chequeNo = chequeNo;		
		this.paidAmount = paidAmount;
		this.transactionDate = transactionDate;
		this.transactionForName = transactionForName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberCategoryName = memberCategoryName;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.currencyCode = currencyCode;
	}
	
	public PaymentDetail(int paymentDetailId, int transactionForId,
			int rotaryYearId, int memberId, int memberCategoryId,
			float paymentAmount, int currencyId, String paymentStatus, 
			String paymentMethod, String transactionForName,
			String rotaryYearTitle, String memberCategoryName,
			String memberNameTitle, String memberFirstName,
			String memberMiddleName, String memberLastName, String currencyCode) {
		super();
		this.paymentDetailId = paymentDetailId;
		this.transactionForId = transactionForId;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.paymentAmount = paymentAmount;
		this.currencyId = currencyId;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;		
		this.transactionForName = transactionForName;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberCategoryName = memberCategoryName;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.currencyCode = currencyCode;
	}
	public PaymentDetail(int transactionForId, int rotaryYearId, int memberId,
			int memberCategoryId, float paymentAmount, int currencyId,
			String paymentStatus, String status,
			int createdBy, String ipAddress) {
		super();		
		this.transactionForId = transactionForId;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.paymentAmount = paymentAmount;
		this.currencyId = currencyId;
		this.paymentStatus = paymentStatus;		
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public PaymentDetail(int paymentDetailId, int sequence,
			String paymentNumber, int transactionForId, int rotaryYearId,
			int memberId, int memberCategoryId, float paymentAmount,
			int currencyId, String paymentStatus, String paymentMethod,
			String createdDate) {
		super();
		this.paymentDetailId = paymentDetailId;
		this.sequence = sequence;
		this.paymentNumber = paymentNumber;
		this.transactionForId = transactionForId;
		this.rotaryYearId = rotaryYearId;
		this.memberId = memberId;
		this.memberCategoryId = memberCategoryId;
		this.paymentAmount = paymentAmount;
		this.currencyId = currencyId;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.createdDate = createdDate;
	}
	private int paymentDetailId;
	private int sequence;
	private String paymentNumber;
	private int transactionForId;
	private int rotaryYearId;
	private int memberId;
	private int memberCategoryId;
	private float paymentAmount;
	private int currencyId;
	private String paymentStatus;
	private String paymentMethod;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String transactionForName;
	private String rotaryYearTitle;
	private String memberCategoryName;
	private String memberNameTitle;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String currencyCode;
	private String bankName;
	private String branchName;
	private String chequeNo;
	private float paidAmount;
	private String transactionDate;
	public int getPaymentDetailId() {
		return paymentDetailId;
	}
	public int getSequence() {
		return sequence;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public int getTransactionForId() {
		return transactionForId;
	}
	public int getRotaryYearId() {
		return rotaryYearId;
	}
	public int getMemberId() {
		return memberId;
	}
	public int getMemberCategoryId() {
		return memberCategoryId;
	}
	public float getPaymentAmount() {
		return paymentAmount;
	}
	public int getCurrencyId() {
		return currencyId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public String getPaymentMethod() {
		return paymentMethod;
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
	public String getTransactionForName() {
		return transactionForName;
	}
	public String getRotaryYearTitle() {
		return rotaryYearTitle;
	}
	public String getMemberCategoryName() {
		return memberCategoryName;
	}
	public String getMemberNameTitle() {
		return memberNameTitle;
	}
	public String getMemberFirstName() {
		return memberFirstName;
	}
	public String getMemberMiddleName() {
		return memberMiddleName;
	}
	public String getMemberLastName() {
		return memberLastName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public float getPaidAmount() {
		return paidAmount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}	
}
