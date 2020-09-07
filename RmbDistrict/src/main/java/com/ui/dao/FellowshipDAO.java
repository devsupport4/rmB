package com.ui.dao;



import java.util.List;

import com.ui.model.Fellowship;

public interface FellowshipDAO {
	
	
	public String addFellowship(Fellowship m);
	 List<Fellowship> getAllFellowship();
	 Fellowship getFellowshipById(int fellowship_id);
	 String editFellowship(Fellowship m);
	  void deleteFellowship(int fellowship_id);
	  List<Fellowship> getFellowshipByPage(int pagesize, int startindex);

}
