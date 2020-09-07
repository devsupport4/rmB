package com.ui.dao;

import java.util.List;

import com.ui.model.State;

public interface StateDAO {
	List<State> getAllState();
	State getStateDetailById(int stateid);

}
