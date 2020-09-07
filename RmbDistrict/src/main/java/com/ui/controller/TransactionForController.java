package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.TransactionForDAO;
import com.ui.model.TransactionFor;

@RestController
public class TransactionForController {
	@Autowired	
	TransactionForDAO transactionForDao;
	
	TransactionFor transactionFor;
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionForController.class);
	
	@RequestMapping(value="/getAllTransactionFor", method= RequestMethod.GET, produces = "application/json")
	public List<TransactionFor> getAllTransactionFor(HttpServletRequest request) {
		logger.info("***** GET MEMBERSHIP CHARGES BY MEMBER *****");		
		List<TransactionFor> transactionFor = transactionForDao.getAllTransactionFor();		
		return transactionFor;
	}
}
