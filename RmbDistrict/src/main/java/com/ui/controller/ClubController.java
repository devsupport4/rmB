package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.ClubDAO;
import com.ui.model.Club;

@RestController
public class ClubController {
	@Autowired
	ClubDAO clubDao;	
	Club club;
	
	private static final Logger logger = LoggerFactory.getLogger(ClubController.class);
	
	@RequestMapping(value="/getAllClubs", method= RequestMethod.GET, produces = "application/json")
	public List<Club> getAllClubs(HttpServletRequest request) {
		logger.info("********** GET ALL CLUBS CONTROLLER **********");		
		List<Club> Club = clubDao.getAllClubs();		
		return Club;
	}
	
	@RequestMapping(value="/getClubsByPage", method= RequestMethod.GET, produces = "application/json")
	public List<Club> getClubsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("********** GET CLUBS BY PAGE CONTROLLER **********");		
		List<Club> Club = clubDao.getClubsByPage(pagesize, startindex);		
		return Club;
	}
	
	@RequestMapping(value="/searchClub", method= RequestMethod.GET, produces = "application/json")
	public List<Club> searchClub(String keyword, HttpServletRequest request) {
		logger.info("********** SEARCH CLUB **********");			
		List<Club> Club = clubDao.searchClub(keyword);		
		return Club;
	}
	
	@RequestMapping(value="/addClub", method=RequestMethod.POST)
	public String addClub(String clubname, String clubdescription, HttpServletRequest request, HttpSession session){
		logger.info("********** ADD CLUB CONTROLLER **********");
		
		String status = "y";
		int id=1;
		//int id  = (Integer) session.getAttribute("adminid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		club = new Club(clubname,clubdescription,status,id,IpAddress);
		clubDao.addClub(club);
		
		return "Success";
	}
	
	@RequestMapping(value="/editClub", method=RequestMethod.POST)
	public String editClub(int clubid, String clubname, String clubdescription, HttpServletRequest request, HttpSession session){
		logger.info("********** EDIT CLUB CONTROLLER **********");		
		
		int id  = (Integer) session.getAttribute("adminid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		club = new Club(clubid,clubname,clubdescription,id,IpAddress);
		clubDao.editClub(club);
		
		return "Success";
	}
	
	@RequestMapping(value="/deleteClub",method= RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("********** DELETE CLUB ********** ");		
		clubDao.deleteClub(id);
	}
}
