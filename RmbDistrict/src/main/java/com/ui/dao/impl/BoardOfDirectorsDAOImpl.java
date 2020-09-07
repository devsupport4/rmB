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

import com.ui.dao.BoardOfDirectorsDAO;
import com.ui.model.BoardOfDirectors;

public class BoardOfDirectorsDAOImpl implements BoardOfDirectorsDAO{
	
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(BoardOfDirectorsDAO.class);
	
	public List<BoardOfDirectors> getAllBoardOfDirectors()
	{
		logger.info("********** INSIDE GET ALL BOARD OF DIRECTS IMPL **********");
		List<BoardOfDirectors> BoardOfDirectors = new ArrayList<BoardOfDirectors>();		
		/*String sql = "select b.board_of_directors_id, b.sequence, b.designation, b.member_id, b.member_family_id, b.rotary_year_id, m.member_first_name, m.member_last_name, r.rotary_year_title, m.member_profile_picture, m.member_name_title from board_of_directors b, rotary_year r, members m where m.member_id = b.member_id and r.rotary_year_id = b.rotary_year_id and b.status=? order by b.sequence";*/
		String sql = "(select b.board_of_directors_id, b.sequence, b.designation, b.rotary_year_id, r.rotary_year_title, concat(b.member_id,'.','m') as id, m.member_name_title as title, m.member_first_name as first_name, m.member_last_name as last_name, m.member_profile_picture as image from board_of_directors b, rotary_year r, members m where b.member_id != 0 and r.rotary_year_id = b.rotary_year_id and m.member_id = b.member_id and b.status='y') union (select b.board_of_directors_id, b.sequence, b.designation, b.rotary_year_id, r.rotary_year_title, concat(b.member_family_id,'.','f') as id, mf.member_family_title_name as title, mf.member_family_first_name as first_name, mf.member_family_last_name as last_name, mf.member_family_profile_picture as image from board_of_directors b, rotary_year r, members_family mf where b.member_family_id != 0 and r.rotary_year_id = b.rotary_year_id and mf.members_family_id = b.member_family_id and b.status='y') order by sequence";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			BoardOfDirectors boardofdirectors = null;			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				boardofdirectors = new BoardOfDirectors(
						rs.getInt("board_of_directors_id"),
						rs.getFloat("sequence"),
		                rs.getString("designation"),
		                rs.getInt("rotary_year_id"),
		                rs.getString("rotary_year_title"),
		                rs.getInt("id"),
		                rs.getString("title"),
		                rs.getString("first_name"), 
		                rs.getString("last_name"),
		                rs.getString("image")
		                );
				
				BoardOfDirectors.add(boardofdirectors);
           }
           rs.close();
           ps.close();
          
           return BoardOfDirectors;
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
	
	public List<BoardOfDirectors> getAllgetBoardOfDirectorsByRotaryYear(int rotaryyearid)
	{
		logger.info("********** INSIDE GET ALL BOARD OF DIRECTS BY ROTARY YEAR IMPL **********");
		List<BoardOfDirectors> BoardOfDirectors = new ArrayList<BoardOfDirectors>();
		String s = "y";
		/*String sql = "select b.board_of_directors_id, b.sequence, b.designation, b.member_id, b.member_family_id, b.rotary_year_id, m.member_first_name, m.member_last_name, r.rotary_year_title, m.member_profile_picture, m.member_name_title from board_of_directors b, rotary_year r, members m where m.member_id = b.member_id and r.rotary_year_id = b.rotary_year_id and b.status=? and b.rotary_year_id =? order by b.sequence";*/
		String sql = "(select b.board_of_directors_id, b.sequence, b.designation, b.rotary_year_id, r.rotary_year_title, concat(b.member_id,'.','m') as id, m.member_name_title as title, m.member_first_name as first_name, m.member_last_name as last_name, m.member_profile_picture as image from board_of_directors b, rotary_year r, members m where b.member_id != 0 and r.rotary_year_id = b.rotary_year_id and m.member_id = b.member_id and b.status='y' and b.rotary_year_id =?) union (select b.board_of_directors_id, b.sequence, b.designation, b.rotary_year_id, r.rotary_year_title, concat(b.member_family_id,'.','f') as id, mf.member_family_title_name as title, mf.member_family_first_name as first_name, mf.member_family_last_name as last_name, mf.member_family_profile_picture as image from board_of_directors b, rotary_year r, members_family mf where b.member_family_id != 0 and r.rotary_year_id = b.rotary_year_id and mf.members_family_id = b.member_family_id and b.status='y' and b.rotary_year_id =?) order by sequence";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			ps.setInt(1, rotaryyearid);
			ps.setInt(2, rotaryyearid);
			BoardOfDirectors boardofdirectors = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				boardofdirectors = new BoardOfDirectors(		
						rs.getInt("board_of_directors_id"),
						rs.getFloat("sequence"),
		                rs.getString("designation"),
		                rs.getInt("rotary_year_id"),
		                rs.getString("rotary_year_title"),
		                rs.getInt("id"),
		                rs.getString("title"),
		                rs.getString("first_name"), 
		                rs.getString("last_name"),
		                rs.getString("image")
		                );
				
				BoardOfDirectors.add(boardofdirectors);
           }
           rs.close();
           ps.close();
          
           return BoardOfDirectors;
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
	
	public void addBoardOfDirectors(BoardOfDirectors b)
	{
		logger.info("********** INSIDE ADD BOARD OF DIRECTOS IMPL **********");
		
		String sql = "insert into board_of_directors (rotary_year_id, sequence, designation, member_id, member_family_id, status, created_by, ip_address) values (?,?,?,?,?,?,?,?)";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, b.getRotaryYearId());
			ps.setFloat(2, b.getSequence());
			ps.setString(3, b.getDesignation());
			ps.setInt(4, b.getMemberId());
			ps.setInt(5, b.getMemberFamilyId());
			ps.setString(6, b.getStatus());
			ps.setInt(7, b.getCreatedBy());
			ps.setString(8, b.getIpAddress());	
			
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
	
	public void editBoardOfDirectors(BoardOfDirectors b)
	{
		logger.info("********** INSIDE EDIT BOARD OF DIRECTOS IMPL **********");
		
		String sql = "update board_of_directors set rotary_year_id=?, sequence=?, designation=?, member_id=?, member_family_id=?, created_by=?, ip_address=? where board_of_directors_id=?";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, b.getRotaryYearId());
			ps.setFloat(2, b.getSequence());
			ps.setString(3, b.getDesignation());
			ps.setInt(4, b.getMemberId());
			ps.setInt(5, b.getMemberFamilyId());
			ps.setInt(6, b.getCreatedBy());
			ps.setString(7, b.getIpAddress());
			ps.setInt(8, b.getBoardOfDirectorsId());
			
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
	
	public void deleteBoardOfDirectors(int id)
	{
		logger.info("********** INSIDE DELETE DIRECTORS ***********");
		String status="n";
		String sql = "update board_of_directors set status=? where board_of_directors_id=?";
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
