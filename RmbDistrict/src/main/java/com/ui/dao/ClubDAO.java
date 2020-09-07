package com.ui.dao;

import java.util.List;

import com.ui.model.Club;

public interface ClubDAO {
	public List<Club> getAllClubs();
	public List<Club> getClubsByPage(int pagesize, int startindex);
	public List<Club> searchClub(String keyword);
	public void addClub(Club bc);
	public void editClub(Club bc);
	public void deleteClub(int id);
}
