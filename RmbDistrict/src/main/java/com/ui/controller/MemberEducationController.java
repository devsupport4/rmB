package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.MemberEducationDAO;
import com.ui.model.MemberEducation;
@RestController
public class MemberEducationController {
	@Autowired
	MemberEducationDAO membereducationdao;
	MemberEducation membereducation;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberEducationController.class);
	
	@RequestMapping(value="/getMemberEducationDetail", method= RequestMethod.GET, produces = "application/json")
	public List<MemberEducation> getMemberEducationDetail(int memberid, HttpServletRequest request)
	{
		logger.info("Inside Get Member Education Sequence");
		
		List<MemberEducation> education = membereducationdao.getMemberEducationDetail(memberid);
		
		return education;
	}
	
	@RequestMapping(value="/getFamilyEducationDetail", method= RequestMethod.GET, produces = "application/json")
	public List<MemberEducation> getFamilyEducationDetail(int membersfamilyid, HttpServletRequest request)
	{
		logger.info("Inside Get Family Member Education Sequence");
		
		List<MemberEducation> education = membereducationdao.getFamilyEducationDetail(membersfamilyid);
		
		return education;
	}
	
	@RequestMapping(value="/addMemberEducation",method=RequestMethod.POST)
	public String addMemberEducation(String degreename, String passingyear, String grade, String institutename, HttpServletRequest request)
	{
		logger.info("Inside Add Member Education Controller");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		int memberid = membereducationdao.getLastMemberId();
		
		membereducation = new MemberEducation(degreename, passingyear, grade, institutename, memberid, 0, 1, IpAddress);
		membereducationdao.addMemberEducation(membereducation);
		return "";
	}
	
	@RequestMapping(value="/editMemberEducation",method=RequestMethod.POST)
	public String editMemberEducation(String degreename, String passingyear, String grade, String institutename, int memberid, HttpServletRequest request)
	{
		logger.info("********** INSIDE EDIT MEMBER EDUCATION CONTROLLER **********");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		membereducation = new MemberEducation(degreename, passingyear, grade, institutename, memberid, 0, 1, IpAddress);
		membereducationdao.addMemberEducation(membereducation);
		return "Success";
	}
	
	@RequestMapping(value="/addMemberFamilyEducation",method=RequestMethod.POST)
	public String addMemberFamilyEducation(String degreename, String passingyear, String grade, String institutename, HttpServletRequest request)
	{
		logger.info("Inside Add Member Family Education Controller");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		int membersfamilyid = membereducationdao.getLastMembersFamilyId();
		
		membereducation = new MemberEducation(degreename, passingyear, grade, institutename, 0, membersfamilyid, 1, IpAddress);
		membereducationdao.addMemberEducation(membereducation);
		return "";
	}
	
	@RequestMapping(value="/editMemberFamilyEducation",method=RequestMethod.POST)
	public String editMemberFamilyEducation(int membersfamilyid, String degreename, String passingyear, String grade, String institutename, String createddate, HttpServletRequest request)
	{
		logger.info("Inside Edit Member Family Education Controller");
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		membereducation = new MemberEducation(degreename, passingyear, grade, institutename, 0, membersfamilyid, 1, IpAddress);
		membereducationdao.addMemberEducation(membereducation);
		return "";
	}
	
	@RequestMapping(value="/deleteFamilyEducation", method=RequestMethod.DELETE)
	public String deleteFamilyEducation(int membersfamilyid)
	{
		logger.info("Inside Delete Family Education Controller");
		
		membereducationdao.deleteFamilyEducation(membersfamilyid);
		
		return "Delete successful";
	}
	
	@RequestMapping(value="/deleteMemberEducation", method=RequestMethod.DELETE)
	public String deleteMemberEducation(int membereducationid)
	{
		logger.info("*********** INSIDE DELETE MEMBER EDUCATION CONTROLLER **********");
		String S = "";
		S=membereducationdao.deleteMemberEducation(membereducationid);
		
		return S;
	}
	
	@RequestMapping(value="/deleteMemberEducationFront", method=RequestMethod.DELETE)
	public String deleteMemberEducationFront(int memberid)
	{
		logger.info("*********** INSIDE DELETE MEMBER EDUCATION CONTROLLER **********");
		String S = "";
		S=membereducationdao.deleteMemberEducationFront(memberid);
		
		return S;
	}
	
}
