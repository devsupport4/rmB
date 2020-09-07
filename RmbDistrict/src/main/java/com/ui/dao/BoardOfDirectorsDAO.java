package com.ui.dao;

import java.util.List;

import com.ui.model.BoardOfDirectors;
public interface BoardOfDirectorsDAO {	
	public List<BoardOfDirectors> getAllBoardOfDirectors();
	public List<BoardOfDirectors> getAllgetBoardOfDirectorsByRotaryYear(int rotaryyearid);
	public void addBoardOfDirectors(BoardOfDirectors b);
	public void editBoardOfDirectors(BoardOfDirectors b);
	public void deleteBoardOfDirectors(int id);
}
