package com.ui.model;

public class MemberReferral {
	
	public MemberReferral() {}
	
	public MemberReferral(int memberReferralId, int toMemberId, String memberFirstName, String memberMiddleName, String memberLastName, String referralName, String fromFirstName, String fromMiddleName,
        String fromLastName, String referDate, String referralType, String referralStatus1, String referralStatus2, String address, String contactNumber, String email, String comments) {
    super();
    this.memberReferralId = memberReferralId;
    this.toMemberId = toMemberId;
    this.memberFirstName = memberFirstName;
    this.memberMiddleName = memberMiddleName;
    this.memberLastName = memberLastName;
    this.referralName = referralName;
    this.fromFirstName = fromFirstName;
    this.fromMiddleName = fromMiddleName;
    this.fromLastName = fromLastName;
    this.referDate = referDate;
    this.referralType = referralType;
    this.referralStatus1 = referralStatus1;
    this.referralStatus2 = referralStatus2;
    this.address = address;
    this.contactNumber = contactNumber;
    this.email = email;
    this.comments = comments;
  }
  public MemberReferral(int memberReferralId, int toMemberId, String memberFirstName, String memberMiddleName,
			String memberLastName, String referralName, String referDate, String referralType, String referralStatus1,
			String referralStatus2, String address, String contactNumber, String email, String comments) {
		super();
		this.memberReferralId = memberReferralId;
		this.toMemberId = toMemberId;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.referralName = referralName;
		this.referDate = referDate;
		this.referralType = referralType;
		this.referralStatus1 = referralStatus1;
		this.referralStatus2 = referralStatus2;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.comments = comments;
	}
	public MemberReferral(int toMemberId, String referralName, String referDate, String referralType, String referralStatus1,
			String referralStatus2, String address, String contactNumber, String email, String comments, String status,
			int createdBy, String ipAddress) {
		super();
		this.toMemberId = toMemberId;
		this.referralName = referralName;
		this.referDate = referDate;
		this.referralType = referralType;
		this.referralStatus1 = referralStatus1;
		this.referralStatus2 = referralStatus2;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.comments = comments;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int memberReferralId;
	private int toMemberId;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String referralName;
	private String fromFirstName;
    private String fromMiddleName;
    private String fromLastName;
	private String referDate;	
	private String referralType;
	private String referralStatus1;
	private String referralStatus2;
	private String address;
	private String contactNumber;
	private String email;
	private String comments;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	
	private String closeDate;
	private String referralStatus;
	private String apprValue;
	
	private String CloseComment;
	private String CloseReason;
	
	public int getMemberReferralId() {
		return memberReferralId;
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
	public int getToMemberId() {
		return toMemberId;
	}
	public String getReferralName() {
		return referralName;
	}
	public String getReferDate() {
		return referDate;
	}
	public String getReferralType() {
		return referralType;
	}
	public String getReferralStatus1() {
		return referralStatus1;
	}
	public String getReferralStatus2() {
		return referralStatus2;
	}
	public String getAddress() {
		return address;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getComments() {
		return comments;
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
  public String getFromFirstName() {
    return fromFirstName;
  }
  public void setFromFirstName(String fromFirstName) {
    this.fromFirstName = fromFirstName;
  }
  public String getFromMiddleName() {
    return fromMiddleName;
  }
  public void setFromMiddleName(String fromMiddleName) {
    this.fromMiddleName = fromMiddleName;
  }
  public String getFromLastName() {
    return fromLastName;
  }
  public void setFromLastName(String fromLastName) {
    this.fromLastName = fromLastName;
  }
public String getCloseDate() {
	return closeDate;
}
public void setCloseDate(String closeDate) {
	this.closeDate = closeDate;
}
public String getReferralStatus() {
	return referralStatus;
}
public void setReferralStatus(String referralStatus) {
	this.referralStatus = referralStatus;
}
public String getApprValue() {
	return apprValue;
}
public void setApprValue(String apprValue) {
	this.apprValue = apprValue;
}
public void setMemberReferralId(int memberReferralId) {
	this.memberReferralId = memberReferralId;
}
public void setToMemberId(int toMemberId) {
	this.toMemberId = toMemberId;
}
public void setMemberFirstName(String memberFirstName) {
	this.memberFirstName = memberFirstName;
}
public void setMemberMiddleName(String memberMiddleName) {
	this.memberMiddleName = memberMiddleName;
}
public void setMemberLastName(String memberLastName) {
	this.memberLastName = memberLastName;
}
public void setReferralName(String referralName) {
	this.referralName = referralName;
}
public void setReferDate(String referDate) {
	this.referDate = referDate;
}
public void setReferralType(String referralType) {
	this.referralType = referralType;
}
public void setReferralStatus1(String referralStatus1) {
	this.referralStatus1 = referralStatus1;
}
public void setReferralStatus2(String referralStatus2) {
	this.referralStatus2 = referralStatus2;
}
public void setAddress(String address) {
	this.address = address;
}
public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
}
public void setEmail(String email) {
	this.email = email;
}
public void setComments(String comments) {
	this.comments = comments;
}
public void setStatus(String status) {
	this.status = status;
}
public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
}
public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}
public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}

public String getCloseComment() {
	return CloseComment;
}

public void setCloseComment(String closeComment) {
	CloseComment = closeComment;
}

public String getCloseReason() {
	return CloseReason;
}

public void setCloseReason(String closeReason) {
	CloseReason = closeReason;
}


  
}
