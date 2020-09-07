package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.MemberCategoryDAO;
import com.ui.model.MemberCategory;
@RestController
public class MemberCategoryController {
	@Autowired
	MemberCategoryDAO membercategorydao;
	private static final Logger logger = LoggerFactory.getLogger(MemberCategoryController.class);
	
	@RequestMapping(value="/getMemberCategory", method= RequestMethod.GET, produces = "application/json")
	public List<MemberCategory> getMemberCategory(HttpServletRequest request)
	{
		logger.info("Inside Get All Member Category");
		
		List<MemberCategory> MemberCategory = membercategorydao.getAllMemberCategory();
		
		return MemberCategory;
	}
	

	
}
