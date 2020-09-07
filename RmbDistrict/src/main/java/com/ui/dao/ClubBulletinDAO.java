package com.ui.dao;

import java.util.List;

import com.ui.model.ClubBulletin;

public interface ClubBulletinDAO {

	public void addClubBulletin(ClubBulletin cb);
	public void editClubBulletinWithoutFile(ClubBulletin cb);
	public void editClubBulletinWithFile(ClubBulletin cb);
	public List<ClubBulletin> getAllClubBulletins();
	public void deleteClubBulletin(int id);
	public List<ClubBulletin> getClubBulletinDetailByRotaryYear(int rotaryyearid);
}
