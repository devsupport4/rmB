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

import com.ui.dao.SliderDAO;
import com.ui.model.Slider;
public class SliderDAOImpl implements SliderDAO
{
	@Autowired
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(SliderDAOImpl.class);
	
	
	@Override
	public List<Slider> getAllSliders()
	{
		logger.info("Inside Get All Slider Impl");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select slider_id, slider_title, image, redirect_url, target, active, status, created_by, created_date, ip_address from slider where status=? order by slider_id ASC";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				slider = new Slider();
				slider.setSliderId(rs.getInt("slider_id"));
				slider.setSliderTitle(  rs.getString("slider_title"));
				slider.setImage(rs.getString("image"));
				slider.setRedirectUrl( rs.getString("redirect_url"));
				slider.setTarget( rs.getString("target"));
				slider.setActive(rs.getString("active"));
				slider.setStatus( rs.getString("status"));
				slider.setCreatedBy(rs.getInt("created_by"));
				slider.setCreatedDate( rs.getString("created_date"));
               slider.setIpAddress(  rs.getString("ip_address"));
                
        
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
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
	public List<Slider> getAllSlidersByPage(int pagesize, int startindex)
	{
		logger.info("Inside Get All Slider By Page Impl");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select slider_id, slider_title, image, redirect_url, target, active, status, created_by, created_date, ip_address from slider where status=? order by slider_id desc limit ?,?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				slider = new Slider();
					
					slider.setSliderId(rs.getInt("slider_id"));
					slider.setSliderTitle( rs.getString("slider_title"));
					slider.setImage(rs.getString("image"));
					slider.setRedirectUrl( rs.getString("redirect_url"));
					slider.setTarget( rs.getString("target"));
					slider.setActive(rs.getString("active"));
					slider.setStatus( rs.getString("status"));
					slider.setCreatedBy(rs.getInt("created_by"));
					slider.setCreatedDate(rs.getString("created_date"));
		            slider.setIpAddress( rs.getString("ip_address"));
				
				
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
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
	public List<Slider> getActiveSliders()
	{
		logger.info("======================Inside Get Active Sliders Impl=================================");
		
		List<Slider> Slider = new ArrayList<Slider>();
		
		String s = "y";
		
		String sql = "select slider_id,slider_title, image, redirect_url, target from slider where status=? and active=? order by slider_id ";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setString(2, s);

			Slider slider = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
			
				slider = new Slider();	
				slider.setSliderTitle( rs.getString("slider_title"));
				slider.setImage(rs.getString("image"));
				slider.setRedirectUrl( rs.getString("redirect_url"));
				slider.setTarget(rs.getString("target"));
				slider.setSliderId(rs.getInt("slider_id"));
				Slider.add(slider);
           }
           rs.close();
           ps.close();
          
           return Slider;
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
	public void addSlider(Slider s)
	{
		logger.info("Inside Add Slider Impl");
		
		String sql = "insert into slider (slider_title, image, redirect_url, target, active, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getSliderTitle());
			ps.setString(2, s.getImage());
			ps.setString(3, s.getRedirectUrl());
			ps.setString(4, s.getTarget());
			ps.setString(5, s.getActive());
			ps.setString(6, s.getStatus());
			ps.setInt(7, s.getCreatedBy());
			ps.setString(8, s.getIpAddress());
			
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
	
	@Override
	public void editSlider(Slider s)
	{
		logger.info("Inside Edit Slider Impl");
		
		String sql = "update slider set slider_title=?, image=?, redirect_url=?, target=?, active=?, status=?, created_by=?, ip_address=? where slider_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getSliderTitle());
			ps.setString(2, s.getImage());
			ps.setString(3, s.getRedirectUrl());
			ps.setString(4, s.getTarget());
			ps.setString(5, s.getActive());
			ps.setString(6, s.getStatus());
			ps.setInt(7, s.getCreatedBy());
			ps.setString(8, s.getIpAddress());
			ps.setInt(9, s.getSliderId());
			
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
	
	@Override
	public void deleteSlider(int sliderid)
	{
		logger.info("Inside Delete Slider Impl");
		String status="n";
		String sql = "update slider set status=? where slider_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, sliderid);
			
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
	
	public void setActiveOrInActiveSlider(Slider s)
	{		
		logger.info("Inside Set Active Or InActive Slider Impl");
		
		String sql = "update slider set active=?, created_by=?, ip_address=? where slider_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getActive());
			ps.setInt(2, s.getCreatedBy());
			ps.setString(3, s.getIpAddress());
			ps.setInt(4, s.getSliderId());
			
			ps.executeUpdate();
		}
		catch (SQLException ex)
		{
			throw new RuntimeException(ex);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException ex) {}
            }
        }
	}
	
	
	
}