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

import com.ui.dao.AwardDAO;
import com.ui.model.AwardImage;
import com.ui.model.Awards;

public class AwardDAOImpl implements AwardDAO{
	@Autowired
	
	private DataSource dataSource;	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}	
	JdbcTemplate jdbcTemplate;	
	private static final Logger logger = LoggerFactory.getLogger(AwardDAOImpl.class);
	
	@Override
	public List<Awards> getAllAwards()
	{
		logger.info("+++++ INSIDE ALL AWARDS IMPL +++++");		
		List<Awards> sta = new ArrayList<Awards>();		
		String s = "y";		
		String sql = "select award_id, award_title, award_subtitle, award_date, award_description, rotary_year_id, status, created_date from awards where status=? order by award_id desc";		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);
			Awards awards = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next())
			{
				awards = new Awards(
						rs.getInt("award_id"),
						rs.getString("award_title"),
						rs.getString("award_subtitle"),
						rs.getString("award_date"),
						rs.getString("award_description"),
						rs.getInt("rotary_year_id"),						
						rs.getString("status"),
						rs.getString("created_date")
						);
				sta.add(awards);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<AwardImage> getAwardDetailByRotaryYearWithOneImage(int rotaryyearid)
	{
		logger.info("+++++ GET AWARD DEAIL BY ROTARY YEAR ID WITH ONE IMAGE +++++");		
		List<AwardImage> sta = new ArrayList<AwardImage>();		
		String sql = "select min(ai.award_image_id) as award_image_id, ai.image_title, ai.description, ai.image, a.award_id, a.award_title, a.award_subtitle, a.award_description, a.award_date from awards a, award_image ai where ai.award_id=a.award_id and a.rotary_year_id = ? group by ai.award_id desc";		
		Connection conn = null;		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rotaryyearid);
			AwardImage awardimage = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next())
			{
				awardimage = new AwardImage(
						rs.getInt("award_image_id"),
						rs.getString("image_title"),
						rs.getString("description"),
						rs.getString("image"),
						rs.getInt("award_id"),
						rs.getString("award_title"),
						rs.getString("award_subtitle"),
						rs.getString("award_description"),
						rs.getString("award_date")
						);
				sta.add(awardimage);
           }
           rs.close();
           ps.close();          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addAward(Awards a)
	{
		logger.info("+++++ INSIDE ADD AWARD IMPL +++++");		
		String sql = "insert into awards(award_title, award_subtitle, award_date, award_description, rotary_year_id, status, created_by, ip_address) values(?,?,?,?,?,?,?,?)";		
		String status = "y";		
		Connection conn = null;		
		try
		{
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, a.getAwardTitle());
			ps.setString(2, a.getAwardSubtitle());
			ps.setString(3, a.getAwardDate());
			ps.setString(4, a.getAwardDescription());
			ps.setInt(5, a.getRotaryYearId());			
			ps.setString(6, status);
			ps.setInt(7, a.getCreatedBy());			
			ps.setString(8, a.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e.getMessage());
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public int getLastAwardId()
	{
		logger.info("+++++ INSIDE GET LAST AWARD ID IMPL +++++");
		
		String sql = "select award_id from awards order by award_id desc limit 0,1 ";
		
		int id=0;
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("award_id");
			}
            rs.close();
            ps.close();
            return id;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addAwardImage(AwardImage p)
	{
		logger.info("+++++ INSIDE ADD AWARD IMAGES IMPL +++++");
		
		String sql = "insert into award_image (image_title, description, image, award_id, ip_address) values (?,?,?,?,?)";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getImageTitle());
			ps.setString(2, p.getDescription());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getAwardId());						
			ps.setString(5, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<AwardImage> getAwardImage(int awardid)
	{
		logger.info("+++++ GET AWARD IMAGE BY AWARD ID IMPL +++++");		
		List<AwardImage> sta = new ArrayList<AwardImage>();		
		String sql = "select award_image_id, image_title, description, image, award_id from award_image where award_id=? order by award_image_id";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, awardid);
			AwardImage awardimage = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next())
			{
				awardimage = new AwardImage(
                rs.getInt("award_image_id"),
                rs.getString("image_title"),
                rs.getString("description"),
                rs.getString("image"),
                rs.getInt("award_id")
				);
				
				sta.add(awardimage);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deleteSelectedAwardImage(int awardid)
	{
		logger.info("+++++ DELETE SELECTED AWARD IMAGE IMPL +++++");		
		String sql = "delete from award_image where award_id=?";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, awardid);			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editAward(Awards p)
	{		
		logger.info("+++++ EDIT AWARD IMAPL +++++");		
		String sql = "update awards set award_title=?, award_subtitle=?, award_date=?, award_description=?, rotary_year_id=?, created_by=?, ip_address=? where award_id=?";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, p.getAwardTitle());
			ps.setString(2, p.getAwardSubtitle());
			ps.setString(3, p.getAwardDate());
			ps.setString(4, p.getAwardDescription());
			ps.setInt(5, p.getRotaryYearId());			
			ps.setInt(6, p.getCreatedBy());			
			ps.setString(7, p.getIpAddress());
			ps.setInt(8, p.getAwardId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deleteAward(int awardid)
	{
		logger.info("+++++ DELETE AWARD IMPL +++++");		
		String status="n";		
		String sql = "update awards set status=? where award_id=?";		
		Connection conn = null;		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, status);
			ps.setInt(2, awardid);			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public Awards getAwardDetailById(int awardid)
	{
		logger.info("********** INSIDE GET AWARD DETAIL BY ID IMPL **********");		
		String sql = "select award_title, award_subtitle, award_description, award_date, rotary_year_id from awards where award_id = ?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, awardid);			
			Awards awards = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next())
			{
				awards = new Awards(
						rs.getString("award_title"),
		                rs.getString("award_subtitle"),
		                rs.getString("award_description"),
		                rs.getString("award_date"),
		                rs.getInt("rotary_year_id")
		                );		
           }
           rs.close();
           ps.close();
          
           return awards;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
}
