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

import com.ui.dao.ClubDAO;
/*import com.ui.model.BusinessCategory;*/
import com.ui.model.Club;

public class ClubDAOImpl implements ClubDAO{
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(ClubDAOImpl.class);
	
	public List<Club> getAllClubs() {
		logger.info("++++++++++ GET ALL CLUB IMPL +++++++++++");		
		List<Club> Club = new ArrayList<Club>();		
		String s = "y";			
		String sql = "select club_id, club_name, club_description from club where status=? order by club_name";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);		
			Club club = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				club = new Club(
						rs.getInt("club_id"),
		                rs.getString("club_name"), 
		                rs.getString("club_description")
				);
				Club.add(club);
           }
           rs.close();
           ps.close();
          
           return Club;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<Club> getClubsByPage(int pagesize, int startindex) {
		logger.info("++++++++++ GET CLUBS BY PAGE IMPL +++++++++++");		
		List<Club> Club = new ArrayList<Club>();		
		String s = "y";			
		String sql = "select club_id, club_name, club_description from club where status=? order by club_id desc limit ?,?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);			
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Club club = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				club = new Club(
						rs.getInt("club_id"),
		                rs.getString("club_name"), 
		                rs.getString("club_description")
				);
				Club.add(club);
           }
           rs.close();
           ps.close();
          
           return Club;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<Club> searchClub(String keyword) {
		logger.info("++++++++++ SEARCH CLUB IMPL ++++++++++");		
		List<Club> Club = new ArrayList<Club>();		
		String s = "y";	
		String sql = "select club_id, club_name, club_description from club where status=? and  Concat(club_name) like ?";		
		Connection conn = null;		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);
			ps.setString(2, '%'+keyword+'%');
			Club club = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				club = new Club(
						rs.getInt("club_id"),
		                rs.getString("club_name"),
		                rs.getString("club_description")
				);
				Club.add(club);
           }
           rs.close();
           ps.close();
          
           return Club;
        }
		catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
        	if (conn != null) {
            	try {
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public void addClub(Club bc)	{
		logger.info("++++++++++ ADD CLUB IMPL ++++++++++");		
		String sql = "insert into club (club_name, club_description, status, created_by, ip_address) values (?,?,?,?,?)";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			
			ps.setString(1, bc.getClubName());
			ps.setString(2, bc.getClubDescription());
			ps.setString(3, bc.getStatus());
			ps.setInt(4, bc.getCreatedBy());
			ps.setString(5, bc.getIpAddress());	
			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
	
	public void editClub(Club bc)	{
		logger.info("++++++++++ EDIT CLUB IMPL ++++++++++");		
		String sql = "update club set club_name=?, club_description=?, created_by=?, ip_address=? where club_id=?";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			
			ps.setString(1, bc.getClubName());
			ps.setString(2, bc.getClubDescription());			
			ps.setInt(3, bc.getCreatedBy());
			ps.setString(4, bc.getIpAddress());
			ps.setInt(5, bc.getClubId());
			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
	
	public void deleteClub(int id) {
		logger.info("++++++++++ DELETE CLUB IMPL ++++++++++");
		String status="n";
		String sql = "update club set status=? where club_id=?";
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
                }
            	catch (SQLException e) {}
            }
        }
	}
}
