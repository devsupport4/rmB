package com.ui.model;

public class MemberPost {
	public MemberPost(int memberPostId, String memberPost, int createdBy, String createdDate,
			String memberFirstName, String memberLastName, int commentCount) {
		super();
		this.memberPostId = memberPostId;
		this.memberPost = memberPost;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.commentCount = commentCount;
	}
	public MemberPost(String memberPost, String status, int createdBy,
			String ipAddress) {
		super();
		this.memberPost = memberPost;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	private int memberPostId;
	private String memberPost;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String memberFirstName;	
	private String memberLastName;
	private int commentCount;
	
	public int getMemberPostId() {
		return memberPostId;
	}
	public String getMemberPost() {
		return memberPost;
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
	public String getMemberLastName() {
		return memberLastName;
	}
	public int getCommentCount() {
		return commentCount;
	}
}
