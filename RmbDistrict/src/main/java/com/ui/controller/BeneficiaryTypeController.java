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

import com.ui.dao.BeneficiaryTypeDAO;
import com.ui.model.BeneficiaryType;
@RestController
public class BeneficiaryTypeController {
	@Autowired
	BeneficiaryTypeDAO beneficiarytypedao;
	BeneficiaryType beneficiarytype;
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryTypeController.class);
	
	@RequestMapping(value="/BeneficiaryType", method= RequestMethod.GET, produces = "application/json")
	public List<BeneficiaryType> BeneficiaryType(HttpServletRequest request)
	{
		logger.info("***** GET ALL BENEFICIARY TYPE *****");	
		List<BeneficiaryType> BeneficiaryType = beneficiarytypedao.getAllBeneficiaryType();		
		return BeneficiaryType;
	}
	
	@RequestMapping(value="/addBeneficiaryType",method= RequestMethod.POST)
	public String addBeneficiaryType(HttpServletRequest request, HttpSession session, String title)
	{
		logger.info("********** INSIDE ADD BENEFICIARY TYPE **********");	
		String s="y";
		int id = Integer.parseInt(session.getAttribute("adminid").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		beneficiarytype = new BeneficiaryType(title,s,id,IpAddress);		
		beneficiarytypedao.addBeneficiaryType(beneficiarytype);	
		return "";
	}
	
	@RequestMapping(value="/editBeneficiaryType",method= RequestMethod.POST)
	public String editBeneficiaryType(HttpServletRequest request, HttpSession session, int id, String title)
	{
		logger.info("********** INSIDE EDIT BENEFICIARY TYPE **********");		
		int id1 = Integer.parseInt(session.getAttribute("adminid").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}			
		beneficiarytype = new BeneficiaryType(title,id1,IpAddress,id);		
		beneficiarytypedao.editBeneficiaryType(beneficiarytype);	
	
		return "";
	}
	
	@RequestMapping(value="/deleteBeneficiaryType",method= RequestMethod.DELETE)
	public void beneficiarydelete(int id)
	{
		logger.info("********** INSIDE DELETE BENEFICIARY TYPE ********** ");		
		beneficiarytypedao.deleteBeneficiaryType(id);
	}	
}
