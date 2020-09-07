package com.ui.model;

public class MemberOneToOne {
	public MemberOneToOne(int oneToOneId, int metMemberId, String memberFirstName, String memberMiddleName,
			String memberLastName, int invitedByMemberId, String inviteeFirstName, String inviteeMiddleName,
			String inviteeLastName, String meetingDate, String location, String conversationTopic) {
		super();
		this.oneToOneId = oneToOneId;
		this.metMemberId = metMemberId;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.invitedByMemberId = invitedByMemberId;
		this.inviteeFirstName = inviteeFirstName;
		this.inviteeMiddleName = inviteeMiddleName;
		this.inviteeLastName = inviteeLastName;
		this.meetingDate = meetingDate;
		this.location = location;
		this.conversationTopic = conversationTopic;
	}

	public MemberOneToOne(int metMemberId, int invitedByMemberId, String meetingDate, String location,
			String conversationTopic, String status, int createdBy, String ipAddress) {
		super();
		this.metMemberId = metMemberId;
		this.invitedByMemberId = invitedByMemberId;
		this.meetingDate = meetingDate;
		this.location = location;
		this.conversationTopic = conversationTopic;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	private int oneToOneId;
	private int metMemberId;
	private int invitedByMemberId;
	private String meetingDate;
	private String location;
	private String conversationTopic;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String inviteeFirstName;
	private String inviteeMiddleName;
	private String inviteeLastName;

	public int getOneToOneId() {
		return oneToOneId;
	}

	public int getMetMemberId() {
		return metMemberId;
	}

	public int getInvitedByMemberId() {
		return invitedByMemberId;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public String getLocation() {
		return location;
	}

	public String getConversationTopic() {
		return conversationTopic;
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

	public String getMemberFirstName() {
		return memberFirstName;
	}

	public String getMemberMiddleName() {
		return memberMiddleName;
	}

	public String getMemberLastName() {
		return memberLastName;
	}

	public String getInviteeFirstName() {
		return inviteeFirstName;
	}

	public String getInviteeMiddleName() {
		return inviteeMiddleName;
	}

	public String getInviteeLastName() {
		return inviteeLastName;
	}
}
