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

import com.ui.dao.MembersCommitteeDAO;
import com.ui.model.MembersCommittee;

@RestController
public class MembersCommitteeController {
	private static final Logger logger = LoggerFactory.getLogger(MembersCommitteeController.class);
	@Autowired
	MembersCommitteeDAO memberscommitteedao;
	MembersCommittee memberscommittee;
	
	@RequestMapping(value="/MembersCommittee", method= RequestMethod.GET, produces = "application/json")
	public List<MembersCommittee> MembersCommittee(HttpServletRequest request)
	{
		logger.info("********** INSIDE ALL MEMBERS COMMITTEE **********");		
		List<MembersCommittee> MembersCommittee = memberscommitteedao.getAllMembersCommittee();		
		return MembersCommittee;
	}
	
	@RequestMapping(value="/getMembersCommitteeByRotaryYear", method= RequestMethod.GET, produces = "application/json")
	public List<MembersCommittee> getMembersCommitteeByRotaryYear(HttpServletRequest request, int rotaryyearid)
	{
		logger.info("********** INSIDE ALL MEMBERS COMMITTEE BY ROTARY YEAR **********");		
		List<MembersCommittee> MembersCommittee = memberscommitteedao.getAllMembersCommitteeByRotaryYear(rotaryyearid);		
		return MembersCommittee;
	}
	
	@RequestMapping(value="/addMembersCommittee",method= RequestMethod.POST)
	public String addMembersCommittee(HttpServletRequest request, HttpSession session, int rotaryyearid, float sequence, String designation, int memberid)
	{
		logger.info("********** INSIDE ADD MEMBERS COMMITTEE **********");		
		
		String s="y";
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		int fellowship_id = Integer.parseInt(session.getAttribute("fellowshipId").toString()); 
		int role_id = Integer.parseInt(session.getAttribute("roleId").toString()); 
		
		System.out.println("++++++++++++fellowship_id++++++++++++++"+fellowship_id);
		System.out.println("+++++++++++++++role_id+++++++++++"+role_id);
		
		memberscommittee = new MembersCommittee(rotaryyearid,sequence,designation,memberid,s,createdby,IpAddress,fellowship_id,role_id);		
		memberscommitteedao.addMembersCommittee(memberscommittee);	
	
		return "";
	}
	
	@RequestMapping(value="/editMembersCommittee",method= RequestMethod.POST)
	public String editMembersCommittee(HttpServletRequest request, HttpSession session, int id, int rotaryyearid, float sequence, String designation, int memberid,int fellowship_id)
	{
		logger.info("********** INSIDE EDIT MEMBERS COMMITTEE **********");	
		
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		memberscommittee = new MembersCommittee(rotaryyearid,sequence,designation,memberid,createdby,IpAddress,id,fellowship_id);		
		memberscommitteedao.editMembersCommittee(memberscommittee);	
	
		return "";
	}
	
	@RequestMapping(value="/deleteMembersCommittee",method= RequestMethod.DELETE)
	public void delete(int id)
	{
		logger.info("********** INSIDE DELETE MEMBERS COMMITTEE ********** ");
		
		memberscommitteedao.deleteMembersCommittee(id);
	}
	
}
