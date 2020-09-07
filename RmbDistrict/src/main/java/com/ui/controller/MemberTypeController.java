package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.MemberTypeDAO;
import com.ui.model.MemberType;
@RestController
public class MemberTypeController {
	@Autowired
	MemberTypeDAO membertypedao;
	private static final Logger logger = LoggerFactory.getLogger(MemberTypeController.class);
	
	@RequestMapping(value="/getMemberType", method= RequestMethod.GET, produces = "application/json")
	public List<MemberType> getMemberType(HttpServletRequest request)
	{
		logger.info("Inside Get All Member Type");
		
		List<MemberType> MemberType = membertypedao.getAllMemberType();
		
		return MemberType;
	}
	

	
}
