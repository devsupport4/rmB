package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ui.dao.StateDAO;
import com.ui.model.State;

public class StateDAOImpl implements StateDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private static final Logger logger = LoggerFactory.getLogger(StateDAOImpl.class);

	public List<State> getAllState() {
		logger.info("Inside Get All State Impl");

		List<State> State = new ArrayList<State>();

		String s = "y";

		String sql = "select state_id, state_name, country_id from state where status=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			State state = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getInt("country_id"));
				State.add(state);
			}
			rs.close();
			ps.close();

			return State;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public State getStateDetailById(int stateid) {
		logger.info("+++++ GET STATE DETAIL BY ID +++++");		
		String sql = "select state_id, state_name, country_id from state where state_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, stateid);
			State state = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getInt("country_id"));				
			}
			rs.close();
			ps.close();
			return state;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
