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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.RotaryYearDAO;
import com.ui.model.RotaryYear;

public class RotaryYearDAOImpl implements RotaryYearDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(RotaryYearDAO.class);

	public List<RotaryYear> getAllRotaryYear() {
		logger.info("********** Inside Get All Rotary Year **********");
		List<RotaryYear> RotaryYear = new ArrayList<RotaryYear>();
		String s = "y";
		String sql = "select rotary_year_title, rotary_year_id, year_start_date, year_end_date, default_year from rotary_year where status=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			RotaryYear rotaryyear = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				rotaryyear = new RotaryYear(rs.getInt("rotary_year_id"), rs.getString("rotary_year_title"),
						rs.getString("year_start_date"), rs.getString("year_end_date"), rs.getString("default_year"));

				RotaryYear.add(rotaryyear);
			}
			rs.close();
			ps.close();

			return RotaryYear;
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

	public void addRotaryYear(RotaryYear ry) {
		logger.info("********** INSIDE ADD ROTARY YEAR IMPL **********");

		String sql = "insert into rotary_year (rotary_year_title, year_start_date,year_end_date,status,created_by,ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, ry.getRotaryYearTitle());
			ps.setString(2, ry.getYearStartDate());
			ps.setString(3, ry.getYearEndDate());
			ps.setString(4, ry.getStatus());
			ps.setInt(5, ry.getCreatedBy());
			ps.setString(6, ry.getIpAddress());

			ps.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void editRotaryYear(RotaryYear ry) {
		logger.info("********** INSIDE EDIT ROTARY YEAR IMPL **********");
		String sql = "update rotary_year set rotary_year_title=?, year_start_date=?, year_end_date=?, created_by=?, ip_address=?  where rotary_year_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, ry.getRotaryYearTitle());
			ps.setString(2, ry.getYearStartDate());
			ps.setString(3, ry.getYearEndDate());
			ps.setInt(4, ry.getCreatedBy());
			ps.setString(5, ry.getIpAddress());
			ps.setInt(6, ry.getRotaryYearId());

			ps.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void changeDefaultYear(RotaryYear ry) {
		logger.info("********** INSIDE CHANGE DEFAULT ROTARY YEAR IMPL **********");
		String sql = "update rotary_year set default_year='n' where default_year='y'";

		String sql1 = "update rotary_year set default_year='y' where rotary_year_id=?";

		Connection conn = null;
		Connection conn1 = null;
		try {
			conn = dataSource.getConnection();
			conn1 = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			PreparedStatement ps1 = conn1.prepareStatement(sql1);

			//ps1.setString(1, ry.getDefaultYear());
			ps1.setInt(1, ry.getRotaryYearId());

			ps.executeUpdate();
			ps1.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null || conn1 != null) {
				try {
					conn.close();
					conn1.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void deleteRotaryYear(int id) {
		logger.info("********** INSIDE DELETE ROTARY YEAR ***********");
		String status = "n";
		String sql = "update rotary_year set status=? where rotary_year_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, id);

			ps.executeUpdate();
		}

		catch (SQLException e) {
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

	public String getCurrentRotaryYearCode() {
		logger.info("++++++++++ GET CURRENT ROTARY YEAR IMPL +++++++++++");
		String s = "y";
		String sql = "select rotary_year_title from rotary_year where status = ? and default_year = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s);
			String f = "";
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f = rs.getString("rotary_year_title");
			}
			rs.close();
			ps.close();

			return f;
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
	
	public RotaryYear getCurrentRotaryYear() {
		logger.info("+++++ GET CURRENT ROTARY YEAR +++++");
		RotaryYear rotaryYear = null;
		String s = "y";
		String sql = "select rotary_year_id, rotary_year_title from rotary_year where status=? and default_year=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				rotaryYear = new RotaryYear(rs.getInt("rotary_year_id"), rs.getString("rotary_year_title"));		
			}
			rs.close();
			ps.close();

			return rotaryYear;
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
