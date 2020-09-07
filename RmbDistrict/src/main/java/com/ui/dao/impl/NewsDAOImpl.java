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

import com.ui.dao.NewsDAO;
import com.ui.model.News;

public class NewsDAOImpl implements NewsDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(NewsDAOImpl.class);

	public void addNews(News n) {
		logger.info("********** INSIDE ADD NEWS IMPL **********");

		String sql = "insert into news (news_title,news_subtitle,beneficiary_type_id,beneficiary_no,news_date,image,image_title,news_desc,rotary_year_id,service_project_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, n.getNewsTitle());
			ps.setString(2, n.getNewsSubtitle());
			ps.setInt(3, n.getBeneficiaryTypeId());
			ps.setString(4, n.getBeneficiaryNo());
			ps.setString(5, n.getNewsDate());
			ps.setString(6, n.getImage());
			ps.setString(7, n.getImageTitle());
			ps.setString(8, n.getNewsDesc());
			ps.setInt(9, n.getRotaryYearId());
			ps.setInt(10, n.getServiceProjectId());
			ps.setString(11, n.getStatus());
			ps.setInt(12, n.getCreatedBy());
			ps.setString(13, n.getIpAddress());

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

	public List<News> getAllNews() {
		logger.info("********** Inside Get All News **********");
		List<News> News = new ArrayList<News>();
		// String s = "y";
		String sql = "select news_id, news_title, news_subtitle, beneficiary_type_id, beneficiary_no, news_date, image, image_title, news_desc, rotary_year_id, service_project_id from news where status = 'y' order by news_id desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			News news = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				news = new News(rs.getInt("news_id"), rs.getString("news_title"), rs.getString("news_subtitle"),
						rs.getInt("beneficiary_type_id"), rs.getString("beneficiary_no"), rs.getString("news_date"),
						rs.getString("image"), rs.getString("image_title"), rs.getString("news_desc"),
						rs.getInt("rotary_year_id"), rs.getInt("service_project_id"));

				News.add(news);
			}
			rs.close();
			ps.close();

			return News;
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

	public void editNews(News n) {
		logger.info("********** INSIDE EDIT NEWS IMPL **********");
		String sql = "update news set news_title=?, news_subtitle=?, beneficiary_type_id=?, beneficiary_no=?, news_date=?, image=?, image_title=?, news_desc=?, rotary_year_id=?, service_project_id=? where news_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, n.getNewsTitle());
			ps.setString(2, n.getNewsSubtitle());
			ps.setInt(3, n.getBeneficiaryTypeId());
			ps.setString(4, n.getBeneficiaryNo());
			ps.setString(5, n.getNewsDate());
			ps.setString(6, n.getImage());
			ps.setString(7, n.getImageTitle());
			ps.setString(8, n.getNewsDesc());
			ps.setInt(9, n.getRotaryYearId());
			ps.setInt(10, n.getServiceProjectId());
			ps.setInt(11, n.getNewsId());

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

	public void editNewsWithoutImage(News n) {
		logger.info("********** INSIDE EDIT NEWS WITHOUT IMAGE IMPL **********");
		String sql = "update news set news_title=?, news_subtitle=?, beneficiary_type_id=?, beneficiary_no=?, news_date=?, image_title=?, news_desc=?, rotary_year_id=?, service_project_id=? where news_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, n.getNewsTitle());
			ps.setString(2, n.getNewsSubtitle());
			ps.setInt(3, n.getBeneficiaryTypeId());
			ps.setString(4, n.getBeneficiaryNo());
			ps.setString(5, n.getNewsDate());
			ps.setString(6, n.getImageTitle());
			ps.setString(7, n.getNewsDesc());
			ps.setInt(8, n.getRotaryYearId());
			ps.setInt(9, n.getServiceProjectId());
			ps.setInt(10, n.getNewsId());

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

	public void deleteNews(int id) {
		logger.info("********** NISIDE DELETE NEWS ************");

		String status = "n";

		String sql = "update news set status=? where news_id=?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, id);

			ps.executeUpdate();
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

	public News getNewsDetailById(int id) {
		logger.info("********** INSIDE GET NEWS DETAIL BY ID IMPL **********");
		// String s = "y";
		String sql = "select news_title, news_subtitle, news_date, image, image_title, service_project_id, news_desc, rotary_year_id from news where news_id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			News news = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				news = new News(rs.getString("news_title"), rs.getString("news_subtitle"), rs.getString("news_date"),
						rs.getString("image"), rs.getString("image_title"), rs.getInt("service_project_id"),
						rs.getString("news_desc"), rs.getInt("rotary_year_id"));
			}
			rs.close();
			ps.close();

			return news;
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

	public List<News> getNewsDetailByRotaryYear(int rotaryyearid) {
		logger.info("+++++ GET NEWS OF CURRENT YEAR +++++");
		List<News> News = new ArrayList<News>();
		String sql = "select news_id, service_project_id, news_title, news_date, image, news_desc from news where rotary_year_id = ? and status = 'y' order by news_date desc limit 0,10";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rotaryyearid);
			News news = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				news = new News(rs.getInt("news_id"), rs.getInt("service_project_id"), rs.getString("news_title"),
						rs.getString("news_date"), rs.getString("image"), rs.getString("news_desc"));

				News.add(news);
			}
			rs.close();
			ps.close();

			return News;
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

	public List<News> getLastThreeNewsForHome() {
		logger.info("+++++ GET LAST THREE NEWS FOR HOME +++++");
		List<News> News = new ArrayList<News>();
		String sql = "select news_id, news_title, news_date, image, news_desc, service_project_id from news where status = 'y' order by news_id desc limit 0,3";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			News news = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				news = new News(rs.getInt("news_id"), rs.getString("news_title"), rs.getString("news_date"),
						rs.getString("image"), rs.getString("news_desc"), rs.getInt("service_project_id"));

				News.add(news);
			}
			rs.close();
			ps.close();

			return News;
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
