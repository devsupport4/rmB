package com.ui.model;

import java.util.List;

public class Mailer {

	private List<Members> members;
	private String Message;
	
	
	
	public List<Members> getMembers() {
		return members;
	}
	public void setMembers(List<Members> members) {
		this.members = members;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
	
}
