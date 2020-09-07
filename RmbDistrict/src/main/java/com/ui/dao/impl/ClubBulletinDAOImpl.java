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

import com.ui.dao.ClubBulletinDAO;
import com.ui.model.ClubBulletin;

public class ClubBulletinDAOImpl implements ClubBulletinDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(ClubBulletinDAOImpl.class);

	public void addClubBulletin(ClubBulletin cb) {
		logger.info("********** INSIDE ADD CLUB BULETINS IMPL **********");

		String sql = "insert into club_bulletin (issue_no,bulletin_title,bulletin_date,file_path,thumbnail_path,rotary_year_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, cb.getIssueNo());
			ps.setString(2, cb.getBulletinTitle());
			ps.setString(3, cb.getBulletinDate());
			ps.setString(4, cb.getFilePath());
			ps.setString(5, cb.getThumbnailPath());
			ps.setInt(6, cb.getRotaryYearId());
			ps.setString(7, cb.getStatus());
			ps.setInt(8, cb.getCreatedBy());
			ps.setString(9, cb.getIpAddress());

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

	public List<ClubBulletin> getAllClubBulletins() {
		logger.info("********** Inside Get All Club Bulletin **********");
		List<ClubBulletin> ClubBulletin = new ArrayList<ClubBulletin>();
		String s = "y";
		String sql = "select bulletin_id, issue_no, bulletin_title, bulletin_date, file_path, thumbnail_path, rotary_year_id, status, created_by, created_date, ip_address from club_bulletin where status=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			ClubBulletin clubbulletin = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				clubbulletin = new ClubBulletin(rs.getInt("bulletin_id"), rs.getInt("issue_no"),
						rs.getString("bulletin_title"), rs.getString("bulletin_date"), rs.getString("file_path"),
						rs.getString("thumbnail_path"), rs.getInt("rotary_year_id"), rs.getString("status"),
						rs.getInt("created_by"), rs.getString("created_date"), rs.getString("ip_address"));

				ClubBulletin.add(clubbulletin);
			}
			rs.close();
			ps.close();

			return ClubBulletin;
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

	public void editClubBulletinWithoutFile(ClubBulletin cb) {
		logger.info("********** INSIDE EDIT CLUB BULLETIN IMPL **********");
		String sql = "update club_bulletin set issue_no=?, bulletin_title=?, bulletin_date=?, rotary_year_id=?, ip_address=?  where bulletin_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, cb.getIssueNo());
			ps.setString(2, cb.getBulletinTitle());
			ps.setString(3, cb.getBulletinDate());
			ps.setInt(4, cb.getRotaryYearId());
			ps.setString(5, cb.getIpAddress());
			ps.setInt(6, cb.getBulletinId());

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

	public void editClubBulletinWithFile(ClubBulletin cb) {
		logger.info("********** INSIDE EDIT CLUB BULLETIN WITH FILE IMPL **********");
		String sql = "update club_bulletin set issue_no=?, bulletin_title=?, bulletin_date=?, file_path=?, thumbnail_path=?, rotary_year_id=?, ip_address=?  where bulletin_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, cb.getIssueNo());
			ps.setString(2, cb.getBulletinTitle());
			ps.setString(3, cb.getBulletinDate());
			ps.setString(4, cb.getFilePath());
			ps.setString(5, cb.getThumbnailPath());
			ps.setInt(6, cb.getRotaryYearId());
			ps.setString(7, cb.getIpAddress());
			ps.setInt(8, cb.getBulletinId());

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

	public void deleteClubBulletin(int id) {
		logger.info("********** INSIDE DELETE CLUB BULLETIN IMPL ***********");
		String status = "n";
		String sql = "update club_bulletin set status=? where bulletin_id=?";
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

	public List<ClubBulletin> getClubBulletinDetailByRotaryYear(int rotaryyearid) {
		logger.info("+++++ GET CLUB BULLETINS OF CURRENT YEAR +++++");
		List<ClubBulletin> ClubBulletin = new ArrayList<ClubBulletin>();		
		String sql = "select bulletin_id, issue_no, bulletin_title, bulletin_date, file_path, thumbnail_path from club_bulletin where rotary_year_id = ? and status='y' order by bulletin_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rotaryyearid);
			ClubBulletin clubbulletin = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clubbulletin = new ClubBulletin(rs.getInt("bulletin_id"), rs.getInt("issue_no"),
						rs.getString("bulletin_title"), rs.getString("bulletin_date"), rs.getString("file_path"),
						rs.getString("thumbnail_path"));
				ClubBulletin.add(clubbulletin);
			}
			rs.close();
			ps.close();

			return ClubBulletin;
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
