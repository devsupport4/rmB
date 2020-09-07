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

import com.ui.dao.ReferenceDAO;
import com.ui.model.Reference;

@RestController
public class ReferenceController {
	@Autowired
	ReferenceDAO referencedao;
	Reference reference;
	
	private static final Logger logger = LoggerFactory.getLogger(ReferenceController.class);
	
	@RequestMapping(value="/addReference ",method=RequestMethod.POST)
	public String addReference(int referencemembersid, String firstname, String lastname, String email, String mobilenumber, String address1, String address2, int statename, String cityname, String pincode, String companyname, String occupation, HttpServletRequest request, HttpSession session)
	{
		logger.info("***** ADD REFERENCE *****");
		System.out.println("Inside Add Reference Controller");
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		int membersid = Integer.parseInt(session.getAttribute("memberid").toString());
	
		reference = new Reference(referencemembersid, firstname,lastname, email, mobilenumber, address1, address2, statename, cityname, pincode, companyname, occupation, membersid, s, 1, ipAddress);
		referencedao.addReference(reference);		
		
		return "";
	}
	
	@RequestMapping(value="/getreference",method=RequestMethod.GET,produces="application/json")
	public List<Reference>getreference(int memberid, HttpServletRequest request)
	{
		List<Reference> reference = referencedao.getReference(memberid);
		
		return reference;
	}	
	
	
	@RequestMapping(value="/deleteReference",method=RequestMethod.DELETE)
	public void deleteReference(int referenceid)
	{
		referencedao.deleteReference(referenceid);
	}
	
	@RequestMapping(value="/editReference",method=RequestMethod.POST)
	public void editReference(int referenceid, int referencemembersid, String firstname, String lastname, String email, String mobilenumber, String address1, String address2, int statename, String cityname, String pincode, String companyname, String occupation, String createddate, String createdtime, HttpServletRequest request)
	{
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if(ipAddress == null)
		{
			ipAddress = request.getRemoteAddr();
		}		
		Reference reference = new Reference(referenceid, referencemembersid, firstname,lastname, email, mobilenumber, address1, address2, statename, cityname, pincode, companyname, occupation, 1, ipAddress);
		referencedao.editReference(reference);
	}
}
