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

import com.ui.dao.MembersCommitteeDAO;
import com.ui.model.MembersCommittee;

public class MembersCommitteeDAOImpl implements MembersCommitteeDAO {

	@Autowired
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MembersCommitteeDAO.class);
	
	public List<MembersCommittee> getAllMembersCommittee()
	{
		logger.info("********** INSIDE GET ALL MEMBERS COMMITTEE IMPL **********");
		List<MembersCommittee> MembersCommittee = new ArrayList<MembersCommittee>();
		String s = "y";
		String sql = "select mc.fellowship_id, mc.members_committee_id, mc.sequence, mc.designation, mc.member_id, mc.rotary_year_id, m.member_first_name, m.member_last_name, r.rotary_year_title, m.member_profile_picture, m.member_name_title from members_committee mc, rotary_year r, members m where m.member_id = mc.member_id and r.rotary_year_id = mc.rotary_year_id and mc.status=? order by mc.sequence";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			MembersCommittee memberscommittee = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				memberscommittee = new MembersCommittee(		
						rs.getInt("members_committee_id"),
						rs.getFloat("sequence"),
		                rs.getString("designation"),
		                rs.getInt("member_id"),	
		                rs.getInt("rotary_year_id"),
		                rs.getString("member_first_name"),
		                rs.getString("member_last_name"),		               
		                rs.getString("rotary_year_title"),
		                rs.getString("member_profile_picture"),
		                rs.getString("member_name_title"),
		                rs.getInt("fellowship_id")
		                );
				
				MembersCommittee.add(memberscommittee);
           }
           rs.close();
           ps.close();
          
           return MembersCommittee;
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
	
	public List<MembersCommittee> getAllMembersCommitteeByRotaryYear(int rotaryyearid)
	{
		logger.info("********** INSIDE GET ALL MEMBERS COMMITTEE BY ROTARY YEAR IMPL **********");
		List<MembersCommittee> MembersCommittee = new ArrayList<MembersCommittee>();
		String s = "y";
		String sql = "select mc.fellowship_id, mc.members_committee_id, mc.sequence, mc.designation, mc.member_id, mc.rotary_year_id, m.member_first_name, m.member_last_name, r.rotary_year_title, m.member_profile_picture, m.member_name_title from members_committee mc, rotary_year r, members m where m.member_id = mc.member_id and r.rotary_year_id = mc.rotary_year_id and mc.status=? and mc.rotary_year_id = ? order by mc.sequence";
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);
			ps.setInt(2, rotaryyearid);

			MembersCommittee memberscommittee = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				memberscommittee = new MembersCommittee(	
						
						rs.getInt("members_committee_id"),
						rs.getFloat("sequence"),
		                rs.getString("designation"),
		                rs.getInt("member_id"),	
		                rs.getInt("rotary_year_id"),
		                rs.getString("member_first_name"),
		                rs.getString("member_last_name"),		               
		                rs.getString("rotary_year_title"),
		                rs.getString("member_profile_picture"),
		                rs.getString("member_name_title")
		               
		                );
				
				MembersCommittee.add(memberscommittee);
           }
           rs.close();
           ps.close();
          
           return MembersCommittee;
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
	
	public void addMembersCommittee(MembersCommittee b)
	{
		logger.info("********** INSIDE ADD MEMBERS DIRECTORY IMPL **********");
		
		String sql = "insert into members_committee (rotary_year_id, sequence, designation, member_id, status, created_by, ip_address,fellowship_id,user_role_id) values (?,?,?,?,?,?,?,?,?)";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, b.getRotaryYearId());
			ps.setFloat(2, b.getSequence());
			ps.setString(3, b.getDesignation());
			ps.setInt(4, b.getMemberId());
			ps.setString(5, b.getStatus());
			ps.setInt(6, b.getCreatedBy());
			ps.setString(7, b.getIpAddress());	
			ps.setInt(8, b.getFellowship_id());
			ps.setInt(9, b.getUser_role_id());
			
			ps.executeUpdate();					
		}
		
		catch (SQLException e1)
		{
			System.out.println("++++++++++++++++=;+e1");
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
	
	public void editMembersCommittee(MembersCommittee b)
	{
		logger.info("********** INSIDE EDIT MEMBERS COMMITTEE IMPL **********");
		
		String sql = "update members_committee set rotary_year_id=?, sequence=?, designation=?, member_id=?, created_by=?, ip_address=?,fellowship_id=? where members_committee_id=?";		
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, b.getRotaryYearId());
			ps.setFloat(2, b.getSequence());
			ps.setString(3, b.getDesignation());
			ps.setInt(4, b.getMemberId());			
			ps.setInt(5, b.getCreatedBy());
			ps.setString(6, b.getIpAddress());
			ps.setInt(7, b.getFellowship_id());
			
			ps.setInt(8, b.getMembersCommitteeId());
			
			
			
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
	
	public void deleteMembersCommittee(int id)
	{
		logger.info("********** INSIDE DELETE MEMBERS COMMITTEE ***********");
		String status="n";
		String sql = "update members_committee set status=? where members_committee_id=?";
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
