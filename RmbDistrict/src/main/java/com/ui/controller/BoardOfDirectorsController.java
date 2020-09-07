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

import com.ui.dao.BoardOfDirectorsDAO;
import com.ui.model.BoardOfDirectors;

@RestController
public class BoardOfDirectorsController {

private static final Logger logger = LoggerFactory.getLogger(BoardOfDirectorsController.class);
	
	@Autowired
	BoardOfDirectorsDAO boardofdirectorsdao;
	BoardOfDirectors boardofdirectors;
	
	@RequestMapping(value="/BoardOfDirectors", method= RequestMethod.GET, produces = "application/json")
	public List<BoardOfDirectors> BoardOfDirectors(HttpServletRequest request)
	{
		logger.info("********** INSIDE ALL BOARD OF DIRECTORS **********");		
		List<BoardOfDirectors> BoardOfDirectors = boardofdirectorsdao.getAllBoardOfDirectors();		
		return BoardOfDirectors;
	}
	
	@RequestMapping(value="/getBoardOfDirectorsByRotaryYear", method= RequestMethod.GET, produces = "application/json")
	public List<BoardOfDirectors> getBoardOfDirectorsByRotaryYear(HttpServletRequest request, int rotaryyearid)
	{
		logger.info("********** INSIDE ALL BOARD OF DIRECTORS BY ROTARY YEAR **********");		
		List<BoardOfDirectors> BoardOfDirectors = boardofdirectorsdao.getAllgetBoardOfDirectorsByRotaryYear(rotaryyearid);		
		return BoardOfDirectors;
	}
	
	@RequestMapping(value="/addBoardOfDirectors",method= RequestMethod.POST)
	public String addBoardOfDirectors(HttpServletRequest request, HttpSession session, int rotaryyearid, float sequence, String designation, int memberid, int memberfamilyid)
	{
		logger.info("********** INSIDE ADD BOARD OF DIRECTOS **********");		
		
		String s="y";
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		boardofdirectors = new BoardOfDirectors(rotaryyearid,sequence,designation,memberid,memberfamilyid,s,createdby,IpAddress);		
		boardofdirectorsdao.addBoardOfDirectors(boardofdirectors);	
	
		return "";
	}
	
	@RequestMapping(value="/editBoardOfDirectors",method= RequestMethod.POST)
	public String editBoardOfDirectors(HttpServletRequest request, HttpSession session, int id, int rotaryyearid, float sequence, String designation, int memberid, int memberfamilyid)
	{
		logger.info("********** INSIDE EDIT BOARD OF DIRECTOS **********");	
		
		int createdby  = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		boardofdirectors = new BoardOfDirectors(rotaryyearid,sequence,designation,memberid,memberfamilyid,createdby,IpAddress,id);		
		boardofdirectorsdao.editBoardOfDirectors(boardofdirectors);	
	
		return "";
	}
	
	@RequestMapping(value="/deleteBoardOfDirectors",method= RequestMethod.DELETE)
	public void delete(int id)
	{
		logger.info("********** INSIDE DELETE BoardOfDirectors ********** ");
		
		boardofdirectorsdao.deleteBoardOfDirectors(id);
	}
}
