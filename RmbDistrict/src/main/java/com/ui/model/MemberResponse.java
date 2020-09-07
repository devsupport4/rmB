package com.ui.model;

public class MemberResponse {
	
	public MemberResponse(String coming, String notcoming) {
		super();
		this.coming = coming;
		this.notcoming = notcoming;
	}
	public MemberResponse(int memberId, int eventId, String notComingReason,
			String coming, String notcoming, String ipAddress) {
		super();
		this.memberId = memberId;
		this.eventId = eventId;
		this.notComingReason = notComingReason;
		this.coming = coming;
		this.notcoming = notcoming;
		this.ipAddress = ipAddress;
	}
	public MemberResponse(int memberId, int eventId, String joinSelf,
			String joinSpouse, String joinAnnet, int noOfAnnetsJoin,
			String comment, String coming, String notcoming, String ipAddress) {
		super();
		this.memberId = memberId;
		this.eventId = eventId;
		this.joinSelf = joinSelf;
		this.joinSpouse = joinSpouse;
		this.joinAnnet = joinAnnet;
		this.noOfAnnetsJoin = noOfAnnetsJoin;
		this.comment = comment;	
		this.coming = coming;
		this.notcoming = notcoming;
		this.ipAddress = ipAddress;
	}
	public MemberResponse(int eventId, String eventTitle, int comming, int notcomming) {
		super();
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.comming = comming;
		this.notcomming = notcomming;
	}
	public MemberResponse(int memberResponseId, int memberId, String firstName, String middleName,
			String lastName, int eventId, String joinSelf,
			String joinSpouse, String joinAnnet, int noOfAnnetsJoin, String notComingReason, String comment,
			String coming, String notcoming, String createdDate, String ipAddress) {
		super();
		this.memberResponseId = memberResponseId;
		this.memberId = memberId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;		
		this.eventId = eventId;
		this.joinSelf = joinSelf;
		this.joinSpouse = joinSpouse;
		this.joinAnnet = joinAnnet;
		this.noOfAnnetsJoin = noOfAnnetsJoin;
		this.notComingReason = notComingReason;
		this.comment = comment;
		this.coming = coming;
		this.notcoming = notcoming;
		this.createdDate = createdDate;		
		this.ipAddress = ipAddress;
	}
	
	public int memberResponseId;
	public int memberId;
	public String firstName;
	public String middleName;
	public String lastName;	
	public int eventId;
	public String joinSelf;
	public String joinSpouse;
	public String joinAnnet;
	public int noOfAnnetsJoin;
	public String notComingReason;
	public String comment;
	public String coming;
	public String notcoming;
	public String createdDate;	
	public String ipAddress;
	public int comming;
	public int notcomming;
	public String eventTitle;
	
	public int getMemberResponseId() {
		return memberResponseId;
	}
	public int getMemberId() {
		return memberId;
	}	
	public int getEventId() {
		return eventId;
	}
	public String getJoinSelf() {
		return joinSelf;
	}
	public String getJoinSpouse() {
		return joinSpouse;
	}
	public String getJoinAnnet() {
		return joinAnnet;
	}
	public int getNoOfAnnetsJoin() {
		return noOfAnnetsJoin;
	}
	public String getNotComingReason() {
		return notComingReason;
	}
	public String getComment() {
		return comment;
	}	
	public String getCreatedDate() {
		return createdDate;
	}	
	public String getIpAddress() {
		return ipAddress;
	}
	public String getComing() {
		return coming;
	}
	public String getNotcoming() {
		return notcoming;
	}
	public int getComming() {
		return comming;
	}
	public int getNotcomming() {
		return notcomming;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}

}
