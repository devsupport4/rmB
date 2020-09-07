package com.ui.model;

public class User {

	public User(){
		
	}
	/*public User(int userId, String userName, String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}*/

	private int userId;
	private String userName;
	private String password;
	
	private int userRoleId;
	private String memberName;
	private String assignedRoles;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private int fellowship_id;
	
	
	
	
  public int getFellowship_id() {
		return fellowship_id;
	}
	public void setFellowship_id(int fellowship_id) {
		this.fellowship_id = fellowship_id;
	}
public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public int getUserRoleId() {
    return userRoleId;
  }
  public void setUserRoleId(int userRoleId) {
    this.userRoleId = userRoleId;
  }
  public String getMemberName() {
    return memberName;
  }
  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }
  public String getAssignedRoles() {
    return assignedRoles;
  }
  public void setAssignedRoles(String assignedRoles) {
    this.assignedRoles = assignedRoles;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
  public int getCreatedBy() {
    return createdBy;
  }
  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  

	
	
	

}