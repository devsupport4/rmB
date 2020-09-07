package com.ui.model;

public class MemberPostComments {
	public MemberPostComments(String postComment, int memberPostId,
			String status, int createdBy, String ipAddress) {
		super();
		this.postComment = postComment;
		this.memberPostId = memberPostId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}
	public MemberPostComments(int memberPostCommentId, String postComment,
			int memberPostId, int createdBy, String createdDate,
			String memberFirstName, String memberLastName) {
		super();
		this.memberPostCommentId = memberPostCommentId;
		this.postComment = postComment;
		this.memberPostId = memberPostId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
	}
	private int memberPostCommentId;
	private String postComment;
	private int memberPostId;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	public String memberFirstName;	
	public String memberLastName;
	public int getMemberPostCommentId() {
		return memberPostCommentId;
	}
	public String getPostComment() {
		return postComment;
	}
	public int getMemberPostId() {
		return memberPostId;
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
}
