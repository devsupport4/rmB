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

import com.ui.dao.BusinessCategoryDAO;
import com.ui.model.BusinessCategory;

@RestController
public class BusinessCategoryController {
	@Autowired
	BusinessCategoryDAO businessCategoryDao;
	
	BusinessCategory businessCategory;
	private static final Logger logger = LoggerFactory.getLogger(BusinessCategoryController.class);
	
	@RequestMapping(value="/getAllBusinessCategories", method= RequestMethod.GET, produces = "application/json")
	public List<BusinessCategory> getAllBusinessCategories(HttpServletRequest request) {
		logger.info("********** GET ALL BUSINESS CATEGORY CONTROLLER **********");		
		List<BusinessCategory> BusinessCategory = businessCategoryDao.getAllBusinessCategories();		
		return BusinessCategory;
	}
	
	@RequestMapping(value="/getLastTenCategoriesForHome", method= RequestMethod.GET, produces = "application/json")
	public List<BusinessCategory> getLastTenCategoriesForHome(HttpServletRequest request) {
		logger.info("***** GET LAST TEN BUSINESS CATEGORIES FOR HOME *****");		
		List<BusinessCategory> BusinessCategory = businessCategoryDao.getLastTenCategoriesForHome();		
		return BusinessCategory;
	}
	
	@RequestMapping(value="/getBusinessCategoryByPage", method= RequestMethod.GET, produces = "application/json")
	public List<BusinessCategory> getBusinessCategoryByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("********** GET BUSINESS CATEGORY BY PAGE CONTROLLER **********");		
		List<BusinessCategory> BusinessCategory = businessCategoryDao.getBusinessCategoryByPage(pagesize, startindex);		
		return BusinessCategory;
	}
	
	@RequestMapping(value="/searchBusinessCategory", method= RequestMethod.GET, produces = "application/json")
	public List<BusinessCategory> searchBusinessCategory(String keyword, HttpServletRequest request) {
		logger.info("********** SEARCH BUSINESS CATEGORY **********");			
		List<BusinessCategory> BusinessCategory = businessCategoryDao.searchBusinessCategory(keyword);		
		return BusinessCategory;
	}
	
	@RequestMapping(value="/addBusinessCategory", method=RequestMethod.POST)
	public String addBusinessCategory(String businesscategorytitle, String businesscategorydescription, HttpServletRequest request, HttpSession session){
		logger.info("********** ADD BUSINESS CATEGORY CONTROLLER **********");
		
		String bct = businesscategorytitle.replace("$","&");
		String bct1 = bct.replace("~","#");
		String bct2 = bct1.replace("!","%");
		
		String status = "y";
		int id  = (Integer) session.getAttribute("adminid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		businessCategory = new BusinessCategory(bct2,businesscategorydescription,status,id,IpAddress);
		businessCategoryDao.addBusinessCategory(businessCategory);
		
		return "Success";
	}
	
	@RequestMapping(value="/editBusinessCategory", method=RequestMethod.POST)
	public String editBusinessCategory(int businesscategoryid, String businesscategorytitle, String businesscategorydescription, HttpServletRequest request, HttpSession session){
		logger.info("********** EDIT BUSINESS CATEGORY CONTROLLER **********");		
		
		String bct = businesscategorytitle.replace("$","&");
		String bct1 = bct.replace("~","#");
		String bct2 = bct1.replace("!","%");
		
		int id  = (Integer) session.getAttribute("adminid");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		businessCategory = new BusinessCategory(businesscategoryid,bct2,businesscategorydescription,id,IpAddress);
		businessCategoryDao.editBusinessCategory(businessCategory);
		
		return "Success";
	}
	
	@RequestMapping(value="/deleteBusinessCategory",method= RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("********** DELETE BUSINESS CATEGORY ********** ");		
		businessCategoryDao.deleteBusinessCategory(id);
	}

}
