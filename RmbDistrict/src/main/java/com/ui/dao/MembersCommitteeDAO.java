package com.ui.dao;

import java.util.List;

import com.ui.model.MembersCommittee;

public interface MembersCommitteeDAO {
	
	public List<MembersCommittee> getAllMembersCommittee();
	public List<MembersCommittee> getAllMembersCommitteeByRotaryYear(int rotaryyearid);
	public void addMembersCommittee(MembersCommittee b);
	public void editMembersCommittee(MembersCommittee b);
	public void deleteMembersCommittee(int id);

}
