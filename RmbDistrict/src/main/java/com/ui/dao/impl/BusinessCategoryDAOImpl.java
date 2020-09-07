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

import com.ui.dao.BusinessCategoryDAO;
import com.ui.model.BusinessCategory;

public class BusinessCategoryDAOImpl implements BusinessCategoryDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BusinessCategoryDAOImpl.class);

	public List<BusinessCategory> getAllBusinessCategories() {
		logger.info("++++++++++ GET ALL BUSINESS CATEGORIES IMPL +++++++++++");
		List<BusinessCategory> BusinessCategory = new ArrayList<BusinessCategory>();
		String s = "y";
		String sql = "select business_category_id, business_category_name, business_category_description from business_category where status=? order by business_category_name";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			BusinessCategory businesscategory = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				businesscategory = new BusinessCategory(rs.getInt("business_category_id"),
						rs.getString("business_category_name"), rs.getString("business_category_description"));
				BusinessCategory.add(businesscategory);
			}
			rs.close();
			ps.close();

			return BusinessCategory;
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

	public List<BusinessCategory> getLastTenCategoriesForHome() {
		logger.info("+++++ GET LAST TEN BUSINESS CATEGORIES FOR HOME ++++++");
		List<BusinessCategory> BusinessCategory = new ArrayList<BusinessCategory>();
		String s = "y";
		String sql = "select business_category_id, business_category_name from business_category where status=? order by business_category_id desc limit 0,10";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			BusinessCategory businesscategory = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				businesscategory = new BusinessCategory(rs.getInt("business_category_id"),
						rs.getString("business_category_name"));
				BusinessCategory.add(businesscategory);
			}
			rs.close();
			ps.close();

			return BusinessCategory;
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

	public List<BusinessCategory> getBusinessCategoryByPage(int pagesize, int startindex) {
		logger.info("++++++++++ GET BUSINESS TYPE BY PAGE IMPL +++++++++++");
		List<BusinessCategory> BusinessCategory = new ArrayList<BusinessCategory>();
		String s = "y";
		String sql = "select business_category_id, business_category_name, business_category_description from business_category where status=? order by business_category_id desc limit ?,?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			BusinessCategory businesscategory = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				businesscategory = new BusinessCategory(rs.getInt("business_category_id"),
						rs.getString("business_category_name"), rs.getString("business_category_description"));
				BusinessCategory.add(businesscategory);
			}
			rs.close();
			ps.close();

			return BusinessCategory;
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

	public List<BusinessCategory> searchBusinessCategory(String keyword) {
		logger.info("++++++++++ SEARCH BUSINESS CATEGORY IMPL ++++++++++");
		List<BusinessCategory> BusinessCategory = new ArrayList<BusinessCategory>();
		String s = "y";
		String sql = "select business_category_id, business_category_name, business_category_description from business_category where status=? and  Concat(business_category_name) like ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			BusinessCategory businesscategory = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				businesscategory = new BusinessCategory(rs.getInt("business_category_id"),
						rs.getString("business_category_name"), rs.getString("business_category_description"));
				BusinessCategory.add(businesscategory);
			}
			rs.close();
			ps.close();

			return BusinessCategory;
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

	public void addBusinessCategory(BusinessCategory bc) {
		logger.info("++++++++++ ADD BUSINESS CATEGORY IMPL ++++++++++");
		String sql = "insert into business_category (business_category_name, business_category_description, status, created_by, ip_address) values (?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, bc.getBusinessCategoryName());
			ps.setString(2, bc.getBusinessCategoryDescription());
			ps.setString(3, bc.getStatus());
			ps.setInt(4, bc.getCreatedBy());
			ps.setString(5, bc.getIpAddress());

			ps.executeUpdate();
		} catch (SQLException e1) {
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

	public void editBusinessCategory(BusinessCategory bc) {
		logger.info("++++++++++ EDIT BUSINESS CATEGORY IMPL ++++++++++");
		String sql = "update business_category set business_category_name=?, business_category_description=?, created_by=?, ip_address=? where business_category_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, bc.getBusinessCategoryName());
			ps.setString(2, bc.getBusinessCategoryDescription());
			ps.setInt(3, bc.getCreatedBy());
			ps.setString(4, bc.getIpAddress());
			ps.setInt(5, bc.getBusinessCategoryId());

			ps.executeUpdate();
		} catch (SQLException e1) {
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

	public void deleteBusinessCategory(int id) {
		logger.info("++++++++++ DELETE BUSINESS CATEGORY IMPL ++++++++++");
		String status = "n";
		String sql = "update business_category set status=? where business_category_id=?";
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
}
