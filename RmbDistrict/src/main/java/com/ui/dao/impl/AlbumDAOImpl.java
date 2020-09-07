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

import com.ui.dao.AlbumDAO;
import com.ui.model.Album;
import com.ui.model.AlbumImage;

public class AlbumDAOImpl implements AlbumDAO
{
	@Autowired
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(AlbumDAOImpl.class);
	
	@Override
	public List<Album> getAllAlbum()
	{
		logger.info("********** INSIDE ALL ALBUM IMPL **********");
		
		List<Album> sta = new ArrayList<Album>();
		
		String s = "y";
		
		String sql = "select album_id, album_title, album_subtitle, album_date, description, service_project_id, status, created_date from album where status=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Album album = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				album = new Album(
						rs.getInt("album_id"),
						rs.getString("album_title"),
						rs.getString("album_subtitle"),
						rs.getString("album_date"),
						rs.getString("description"),
						rs.getInt("service_project_id"),						
						rs.getString("status"),
						rs.getString("created_date")
						);
				sta.add(album);
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
	public List<AlbumImage> getAlbumOneImage()
	{
		logger.info("Inside Get All Album One Image Impl");
		
		List<AlbumImage> sta = new ArrayList<AlbumImage>();	
		
		String sql = "select min(ai.album_image_id) as album_image_id, ai.image_title, ai.description, ai.image, a.album_id, a.album_title, a.description from album a, album_image ai where ai.album_id=a.album_id group by ai.album_id desc";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			AlbumImage albumimage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				albumimage = new AlbumImage(
						rs.getInt("album_image_id"),
						rs.getString("image_title"),
						rs.getString("description"),
						rs.getString("image"),
						rs.getInt("album_id"),
						rs.getString("album_title"),
						rs.getString("description")
						);
				sta.add(albumimage);
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
	
	public List<AlbumImage> getAllAlbumImages()
	{
		logger.info("Inside Get All Album Images Impl");
		
		List<AlbumImage> sta = new ArrayList<AlbumImage>();	
		
		String sql = "select album_image_id, image_title, description, image, album_id from album_image order by album_image_id";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			AlbumImage albumimage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				albumimage = new AlbumImage(
						rs.getInt("album_image_id"),
						rs.getString("image_title"),
						rs.getString("description"),
						rs.getString("image"),
						rs.getInt("album_id")
						);
				sta.add(albumimage);
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
	
	
	public List<AlbumImage> getAlbumImage(int albumid)
	{
		logger.info("Inside Get Album Image By Album Id Impl");
		
		List<AlbumImage> sta = new ArrayList<AlbumImage>();
		
		String sql = "select album_image_id, image_title, description, image, album_id from album_image where album_id=? order by album_image_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, albumid);

			AlbumImage albumimage = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				albumimage = new AlbumImage(
                rs.getInt("album_image_id"),
                rs.getString("image_title"),
                rs.getString("description"),
                rs.getString("image"),
                rs.getInt("album_id")
				);
				
				sta.add(albumimage);
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
	
	public void addAlbum(Album a)
	{
		logger.info("Inside Add Album Impl");
		
		String sql = "insert into album(album_title, album_subtitle, album_date, description, service_project_id, status, created_by, ip_address) values(?,?,?,?,?,?,?,?)";
		
		String status = "y";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, a.getAlbumTitle());
			ps.setString(2, a.getAlbumSubtitle());
			ps.setString(3, a.getAlbumDate());
			ps.setString(4, a.getDescription());
			ps.setInt(5, a.getServiceProjectId());			
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
	
	public int getLastAlbumId()
	{
		logger.info("Inside get last album id Impl");
		
		String sql = "select album_id from album order by album_id desc limit 0,1 ";
		
		int id=0;
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("album_id");
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
	
	public void addAlbumImage(AlbumImage p)
	{
		logger.info("Inside Add Album Images Impl");
		
		String sql = "insert into album_image (image_title, description, image, album_id, created_by, ip_address) values (?,?,?,?,?,?)";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getImageTitle());
			ps.setString(2, p.getDescription());
			ps.setString(3, p.getImage());
			ps.setInt(4, p.getAlbumId());
			ps.setInt(5, p.getCreatedBy());			
			ps.setString(6, p.getIpAddress());
			
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
	
	
	public void deleteSelectedAlbumImage(int albumid)
	{
		logger.info("Inside Delete Selected Album Image impl");
		
		String sql = "delete from album_image where album_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, albumid);
			
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
	
	
	public void editAlbum(Album p)
	{		
		logger.info("Inside Edit Album Impl");
		
		String sql = "update album set album_title=?, album_subtitle=?, album_date=?, description=?, service_project_id=?, created_by=?, ip_address=? where album_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getAlbumTitle());
			ps.setString(2, p.getAlbumSubtitle());
			ps.setString(3, p.getAlbumDate());
			ps.setString(4, p.getDescription());
			ps.setInt(5, p.getServiceProjectId());			
			ps.setInt(6, p.getCreatedBy());			
			ps.setString(7, p.getIpAddress());
			ps.setInt(8, p.getAlbumId());
			
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
	
	public void deleteAlbum(int albumid)
	{
		logger.info("Inside Delete Album impl");
		
		String status="n";
		
		String sql = "update album set status=? where album_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			ps.setInt(2, albumid);
			
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
	
	public List<AlbumImage> getPhotoAlbumByServiceProjectId(int id)
	{
	    logger.info("********** INSIDE GET ALBUM BY SERVICE PROJECT ID IMPL **********");
	    
	    List<AlbumImage> sta = new ArrayList();
	    

	    String sql = "select min(ai.album_image_id) as album_image_id, ai.image_title, ai.description, ai.image, a.album_id, a.album_title, a.description from album a, album_image ai where a.album_id=ai.album_id and service_project_id=? and status='y' group by a.album_id desc";
	    Connection conn = null;
	    try
	    {
	      conn = dataSource.getConnection();
	      PreparedStatement ps = conn.prepareStatement(sql);
	      
	      ps.setInt(1, id);
	      
	      AlbumImage albumimage = null;
	      ResultSet rs = ps.executeQuery();
	      
	      while (rs.next())
	      {
	        albumimage = new AlbumImage(
	          rs.getInt("album_image_id"), 
	          rs.getString("image_title"), 
	          rs.getString("description"), 
	          rs.getString("image"), 
	          rs.getInt("album_id"), 
	          rs.getString("album_title"), 
	          rs.getString("description"));
	        

	        sta.add(albumimage);
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
	        catch (SQLException localSQLException2) {}
	      }
	    }
	  }
}