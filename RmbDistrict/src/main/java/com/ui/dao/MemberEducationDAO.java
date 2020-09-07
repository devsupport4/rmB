package com.ui.dao;

import java.util.List;

import com.ui.model.MemberEducation;

public interface MemberEducationDAO
{
	public List<MemberEducation> getMemberEducationDetail(int memberid);
	public List<MemberEducation> getFamilyEducationDetail(int membersfamilyid);
	public int getLastMemberId();
	public void addMemberEducation(MemberEducation m);
	public int getLastMembersFamilyId();
	public void deleteFamilyEducation(int membersfamilyid);
	public String deleteMemberEducation(int memberid);
	public String deleteMemberEducationFront(int memberid);
}
