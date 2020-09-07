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

import com.ui.dao.MemberEducationDAO;
import com.ui.model.MemberEducation;

public class MemberEducationDAOImpl implements MemberEducationDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MemberEducationDAOImpl.class);
	
	
	public List<MemberEducation> getMemberEducationDetail(int memberid)
	{
		logger.info("Inside Get Family Education Impl");
		
		List<MemberEducation> MembersEducation = new ArrayList<MemberEducation>();
		
		String sql = "select member_education_id, degree_name, passing_year, grade, institute_name from member_education where member_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberid);
			MemberEducation membereducation = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				membereducation = new MemberEducation(
		                rs.getInt("member_education_id"),
		                rs.getString("degree_name"),
		                rs.getString("passing_year"),
		                rs.getString("grade"),
		                rs.getString("institute_name")
				);
				MembersEducation.add(membereducation);
           }
           rs.close();
           ps.close();
          
           return MembersEducation;
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
	
	
	public List<MemberEducation> getFamilyEducationDetail(int membersfamilyid)
	{
		logger.info("Inside Get Family Education Impl");
		
		List<MemberEducation> MembersEducation = new ArrayList<MemberEducation>();
		
		String sql = "select member_education_id, degree_name, passing_year, grade, institute_name from member_education where member_family_id=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, membersfamilyid);
			MemberEducation membereducation = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				membereducation = new MemberEducation(
		                rs.getInt("member_education_id"),
		                rs.getString("degree_name"),
		                rs.getString("passing_year"),
		                rs.getString("grade"),
		                rs.getString("institute_name")
				);
				MembersEducation.add(membereducation);
           }
           rs.close();
           ps.close();
          
           return MembersEducation;
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
	
	public int getLastMemberId()
	{
		logger.info("Inside Get Last Members Id Impl");
		
		int id = 0;
		
		String sql = "select member_id from members order by member_id desc limit 0,1";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				id= rs.getInt("member_id");
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
	
	public int getLastMembersFamilyId()
	{
		logger.info("Inside Get Last Members Family Id Impl");
		
		int id = 0;
		
		String sql = "select members_family_id from members_family order by members_family_id desc limit 0,1";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				id= rs.getInt("members_family_id");
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
	
	public void addMemberEducation(MemberEducation m)
	{
		logger.info("Inside Add Member Education Impl");
		
		String sql = "insert into member_education(degree_name, passing_year, grade, institute_name, member_id, member_family_id, created_by, created_date, ip_address) values (?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		
		try {
			conn=dataSource.getConnection();
		
			PreparedStatement ps= conn.prepareStatement(sql);

			ps.setString(1,m.getDegreeName());
			ps.setString(2,m.getPassingYear());
			ps.setString(3,m.getGrade());
			ps.setString(4,m.getInstituteName());
			ps.setInt(5,m.getMemberId());
			ps.setInt(6,m.getMemberFamilyId());
			ps.setInt(7, m.getCreatedBy());
			ps.setString(8,m.getCreatedDate());
			ps.setString(9,m.getIpAddress());

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
	
	public void deleteFamilyEducation(int membersfamilyid)
	{
		logger.info("++++++++++++++Inside Delete Family Educaton Implementation++++++++++++");
		
		String query = "delete from member_education where member_family_id=?";
		
		Connection conn = null;
		try{
				conn=dataSource.getConnection();
				
				PreparedStatement ps= conn.prepareStatement(query);
					
				ps.setInt(1, membersfamilyid);
				
				ps.executeUpdate();
				
			} catch (SQLException e){
				
				throw new RuntimeException(e);
				
			} finally {
				
				if(conn != null)
				{
					try {
						    conn.close();
						    
					} catch (SQLException e) {}
					
				}
		
			}		
	}
	
	public String deleteMemberEducation(int membereducationid)
	{
		logger.info("Inside Delete Member Educaton Implementation");
		
		String query = "delete from member_education where member_education_id=?";
		
		Connection conn = null;
		try{
				conn=dataSource.getConnection();
				
				PreparedStatement ps= conn.prepareStatement(query);
					
				ps.setInt(1, membereducationid);
				
				ps.executeUpdate();
				
				return "Success";
				
			} catch (SQLException e){
				
				throw new RuntimeException(e);
				
			} finally {
				
				if(conn != null)
				{
					try {
						    conn.close();
						    
					} catch (SQLException e) {}
					
				}
		
			}		
	}
	
	public String deleteMemberEducationFront(int memberid)
	{
		logger.info("Inside Delete Member Educaton Implementation");
		
		String query = "delete from member_education where member_id=?";
		
		Connection conn = null;
		try{
				conn=dataSource.getConnection();
				
				PreparedStatement ps= conn.prepareStatement(query);
					
				ps.setInt(1, memberid);
				
				ps.executeUpdate();
				
				return "Success";
				
			} catch (SQLException e){
				
				throw new RuntimeException(e);
				
			} finally {
				
				if(conn != null)
				{
					try {
						    conn.close();
						    
					} catch (SQLException e) {}
					
				}
		
			}		
	}

		
}
	



	
	
	

	