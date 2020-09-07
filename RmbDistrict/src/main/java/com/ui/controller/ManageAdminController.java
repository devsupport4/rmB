package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.ManageAdminDAO;
import com.ui.model.ManageAdmin;

@RestController
public class ManageAdminController {
	
	@Autowired
	ManageAdminDAO manageAdminDAO;
	 private static final Logger logger = LoggerFactory.getLogger(ManageAdminController.class);
	

	 
		@PostMapping("addManageAdmin")
	    public String addMasterPlan(@RequestBody ManageAdmin manageAdmin, HttpServletRequest request, HttpSession session)
	 
	    {
			 logger.info("***** Add ManageAdmin *****");
	        String result = "";
	        int  id = Integer.parseInt(session.getAttribute("adminid").toString()); 
	       
	        int member_id = manageAdmin.getMember_id();
	        int fellowship_id = manageAdmin.getFellowship_id();
	       System.out.println("member_id"+member_id+"//fellowship_id="+fellowship_id);
	       /*   
	     manageAdmin = manageAdminDAO.getAllMemberUserPaswword(member_id, fellowship_id);
	     String username= manageAdmin.getUser_name();
	     String password = manageAdmin.getPassword();*/
	        System.out.println("============================================================="+id);
	        String ip = request.getHeader("X-FORWARDED-FOR");
	        if (ip == null) {
	            ip = request.getRemoteAddr();
	        }
		/* manageAdmin = new ManageAdmin(); */
	        
	        String s = "y";
	      int roll =2;
		/*
		 * manageAdmin.setUser_name(username); manageAdmin.setPassword(password);
		 */
	        manageAdmin.setCreatedBy(id);
	        manageAdmin.setIpAddress(ip);
	        manageAdmin.setStatus(s);
	       manageAdmin.setUser_role_id(roll);
	       

	        result = manageAdminDAO.editManageAdmin(manageAdmin);
	        		
	  
	        return result;
	    }
		
		@GetMapping(value = "getAllManageAdminName")
		  public List<ManageAdmin> getAllManageAdminName(HttpServletRequest request) {
		      logger.info("*****getAllManageAdminName*****");
		      return manageAdminDAO.getAllManageAdmin();
		  }
		@GetMapping(value = "getAllMemberNameList")
		  public List<ManageAdmin> getAllMemberNameList(HttpServletRequest request) {
		      logger.info("*****getAllMemberNameList*****");
		    //  return manageAdminDAO.getAllMemberByFellowshipId(fellowship_id);
		      return manageAdminDAO.getAllMember();
		  }
		@GetMapping(value = "getAllAdminList")
		  public List<ManageAdmin> getAllAdminList(HttpServletRequest request) {
		      logger.info("*****getAllMemberNameList*****");
		    //  return manageAdminDAO.getAllMemberByFellowshipId(fellowship_id);
		      return manageAdminDAO.getAllAdmin();
		  }
		@DeleteMapping(value = "deleteAdmin")
		  public void delete(int member_id) {
		      logger.info("*****  deleteAdmin Name *****");
		      manageAdminDAO.deleteManageAdmin(member_id);		  }
}
