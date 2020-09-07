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

import com.ui.dao.RotaryYearDAO;
import com.ui.model.RotaryYear;

@RestController
public class RotaryYearController {

	private static final Logger logger = LoggerFactory.getLogger(RotaryYearController.class);

	@Autowired
	RotaryYearDAO rotaryyeardao;
	RotaryYear rotaryyear;

	@RequestMapping(value = "/RotaryYear", method = RequestMethod.GET, produces = "application/json")
	public List<RotaryYear> RotaryYear(HttpServletRequest request) {
		logger.info("********** Inside Rotary Year Controller **********");
		List<RotaryYear> RotaryYear = rotaryyeardao.getAllRotaryYear();
		return RotaryYear;
	}
	
	@RequestMapping(value = "/getCurrentRotaryYear", method = RequestMethod.GET, produces = "application/json")
	public RotaryYear getCurrentRotaryYear(HttpServletRequest request) {
		logger.info("***** GET CURRENT ROTARY YEAR *****");
		RotaryYear rotaryYear = rotaryyeardao.getCurrentRotaryYear();
		return rotaryYear;
	}

	@RequestMapping(value = "/addRotaryYear", method = RequestMethod.POST)
	public String addRotaryYear(HttpServletRequest request, HttpSession session, String yeartitle, String yearstartdate,
			String yearenddate) {
		logger.info("********** INSIDE ADD ROTERY YEAR **********");

		String s = "y";
		int createdby = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		rotaryyear = new RotaryYear(yeartitle, yearstartdate, yearenddate, s, createdby, IpAddress);
		rotaryyeardao.addRotaryYear(rotaryyear);

		return "";
	}

	@RequestMapping(value = "/changeDefaultYear", method = RequestMethod.POST)
	public String changeDefaultYear(HttpServletRequest request, HttpSession session, int id, String defaultyear) {
		logger.info("********** INSIDE CHANGE DEFAULT YEAR **********");
		rotaryyear = new RotaryYear(id, defaultyear);
		rotaryyeardao.changeDefaultYear(rotaryyear);
		return "";
	}

	@RequestMapping(value = "/editRotaryYear", method = RequestMethod.POST)
	public String editRotaryYear(HttpServletRequest request, HttpSession session, int id, String yeartitle,
			String yearstartdate, String yearenddate) {
		logger.info("********** INSIDE EDIT ROTERY YEAR **********");
		int createdby = 1;
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		rotaryyear = new RotaryYear(yeartitle, yearstartdate, yearenddate, createdby, IpAddress, id);
		rotaryyeardao.editRotaryYear(rotaryyear);

		return "";
	}

	@RequestMapping(value = "/deleteRotaryYear", method = RequestMethod.DELETE)
	public void delete(int id) {
		logger.info("********** INSIDE DELETE ROTARY YEAR ********** ");
		rotaryyeardao.deleteRotaryYear(id);
	}

}
