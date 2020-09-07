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

import com.ui.dao.SocialMediaDAO;
import com.ui.model.SocialMediaLink;

public class SocialMediaDAOImpl implements SocialMediaDAO{

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(SocialMediaDAO.class);
	
	public List<SocialMediaLink> getAllSocialMediaLinks()
	{
		logger.info("********** INSIDE GET ALL SOCIAL MEDIA LINKS IMPL **********");
		List<SocialMediaLink> SocialMediaLink = new ArrayList<SocialMediaLink>();
		String s = "y";
		String sql = "select social_media_link_id, social_media_platform_title, social_media_url, status, created_by, created_date, ip_address from social_media_link where status=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			SocialMediaLink socialmedialink = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				socialmedialink = new SocialMediaLink(		
						rs.getInt("social_media_link_id"),
						rs.getString("social_media_platform_title"),
						rs.getString("social_media_url"),
		                rs.getString("status"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("ip_address")
		                );
				
				SocialMediaLink.add(socialmedialink);
           }
           rs.close();
           ps.close();
          
           return SocialMediaLink;
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
	
	public void addSocialMediaLink(SocialMediaLink s)
	{
		logger.info("********** INSIDE ADD SOCIAL MEDIA LINK IMPL **********");
		
		String sql = "insert into social_media_link (social_media_platform_title, social_media_url, status, created_by, ip_address) values (?,?,?,?,?)";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			
			ps.setString(1, s.getSocialMediaPlatformTitle());
			ps.setString(2, s.getSocialMediaUrl());			
			ps.setString(3, s.getStatus());
			ps.setInt(4, s.getCreatedBy());
			ps.setString(5, s.getIpAddress());	
			
			ps.executeUpdate();					
		}
		
		catch (SQLException e1)
		{
			throw new RuntimeException(e1);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e1) {}
            }
        }
	}
	
	public void editSocialMediaLink(SocialMediaLink s)
	{
		logger.info("********** INSIDE EDIT SOCIAL MEDIA LINK IMPL **********");
		
		String sql = "update social_media_link set social_media_platform_title=?, social_media_url=?, created_by=?, ip_address=? where social_media_link_id=?";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s.getSocialMediaPlatformTitle());
			ps.setString(2, s.getSocialMediaUrl());			
			ps.setInt(3, s.getCreatedBy());
			ps.setString(4, s.getIpAddress());
			ps.setInt(5, s.getSocialMediaLinkId());
			
			ps.executeUpdate();					
		}
		
		catch (SQLException e1)
		{
			throw new RuntimeException(e1);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e1) {}
            }
        }
	}
	
	public void deleteSocialMediaLink(int id)
	{
		logger.info("********** INSIDE DELETE SOCIAL MEDIA LINK ***********");
		String status="n";
		String sql = "update social_media_link set status=? where social_media_link_id=?";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, id);
			
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
}
