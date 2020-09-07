package com.ui.dao;

import java.util.List;

import com.ui.model.ManageAdmin;

public interface ManageAdminDAO {

	
	public String addManageAdmin( ManageAdmin m);
	 List< ManageAdmin> getAllManageAdmin();
	 ManageAdmin getManageAdminById(int user_id);
	 String editManageAdmin( ManageAdmin m);
	  void deleteManageAdmin(int member_id);
	  List< ManageAdmin> getManageAdminByPage(int pagesize, int startindex);
	  
	  List<ManageAdmin> getAllMember();
	  List<ManageAdmin> getAllAdmin();
	// ManageAdmin  getAllMemberUserPaswword(int member_id ,int fellowship_id);
	  ManageAdmin  getAllMemberUserPaswword();
}
