package com.ui.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.FellowshipDAO;
import com.ui.model.Fellowship;

@RestController
public class FellowshipController {

	

	@Autowired
	FellowshipDAO fellowshipDAO;
	 private static final Logger logger = LoggerFactory.getLogger(FellowshipController.class);
	 
	 @PostMapping("addfellowship")
	    public String addfellowship(@RequestBody Fellowship fellowship, HttpServletRequest request, HttpSession session)
	 
	    {
			 logger.info("***** Add Fellowship *****");
	        String result = "";
	        int  id = Integer.parseInt(session.getAttribute("adminid").toString());    
	        System.out.println("============================================================="+id);
	        String ip = request.getHeader("X-FORWARDED-FOR");
	        if (ip == null) {
	            ip = request.getRemoteAddr();
	        }

	        String s = "y";
	        System.out.println("-------------"+fellowship.getFellowship_name());
	        
			/*
			 * fellowship.setCreatedBy(id); fellowship.setIpAddress(ip);
			 */
	        fellowship.setStatus(s);

	        result = fellowshipDAO.addFellowship(fellowship);
	  
	        return result;
	    }
		
	 	@GetMapping(value = "getAllFellowshipName")
		  public List<Fellowship> getAllFellowshipName(HttpServletRequest request) {
		      logger.info("*****getAllFellowship*****");
		      return fellowshipDAO.getAllFellowship();
		  }
		 
		 @GetMapping(value = "/getFellowshipById")
		  public Fellowship getBatchTimingsById(int fellowship_id, HttpServletRequest request) {
		      logger.info("***** GET getfellowshipById *****");
		      return fellowshipDAO.getFellowshipById(fellowship_id);
		  }
		 
		 
		 @PostMapping(value = "editFellowshipName")
		  public String editBatch(@RequestBody Fellowship fellowship, HttpSession session, HttpServletRequest request,
		          HttpServletResponse response) {
		      logger.info("***** EDIT Fellowship *****");
		      String result = "";

		      int id = Integer.parseInt(session.getAttribute("adminid").toString());
		      String ipaddress = request.getHeader("X-FORWARDED-FOR");
		      
		      if (ipaddress == null) {
		          ipaddress = request.getRemoteAddr();
		      }
		      String s = "y";
				/*
				 * fellowship.setCreatedBy(id); fellowship.setIpAddress(ipaddress);
				 */
		      fellowship.setStatus(s);
		      
		        result = fellowshipDAO.editFellowship(fellowship);
		      
		      return result;
		  }
		 @DeleteMapping(value = "deleteLocation")
		  public void delete(int fellowship_id) {
		      logger.info("***** DELETE Fellowship Name *****");
		      fellowshipDAO.deleteFellowship(fellowship_id);
		  }
		 
	
}
