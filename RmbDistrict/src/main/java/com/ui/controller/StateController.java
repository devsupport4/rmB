package com.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.StateDAO;
import com.ui.model.State;

@RestController
public class StateController {

	@Autowired
	StateDAO statedao;

	private static final Logger logger = LoggerFactory.getLogger(StateController.class);

	@RequestMapping(value = "/getState", method = RequestMethod.GET, produces = "application/json")
	public List<State> State(HttpServletRequest request) {
		logger.info("Inside Get All State Controller");
		List<State> state = statedao.getAllState();
		return state;
	}
	
	@RequestMapping(value = "/getStateDetailById", method = RequestMethod.GET, produces = "application/json")
	public State getStateDetailById(int stateid, HttpServletRequest request) {
		logger.info("***** GET STATE DETAIL BY ID *****");
		State state = statedao.getStateDetailById(stateid);
		return state;
	}

}
