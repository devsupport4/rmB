package com.ui.dao;

import com.ui.model.BoardOfDirectors;
import com.ui.model.Members;

public interface LoginDAO 
{
	Members checkLogin(String member_email, String member_password);
	
	  Members frontLogin(String userName, String password);
	  BoardOfDirectors getBoardOfDirectorsInfo(int memberid);
	 
}

