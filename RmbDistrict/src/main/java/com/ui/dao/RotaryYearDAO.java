package com.ui.dao;

import java.util.List;

import com.ui.model.RotaryYear;

public interface RotaryYearDAO {

	public List<RotaryYear> getAllRotaryYear();
	public void addRotaryYear(RotaryYear ry);
	public void editRotaryYear(RotaryYear ry);
	public void deleteRotaryYear(int id);	
	public void changeDefaultYear(RotaryYear ry);
	public String getCurrentRotaryYearCode();
	public RotaryYear getCurrentRotaryYear();
}
