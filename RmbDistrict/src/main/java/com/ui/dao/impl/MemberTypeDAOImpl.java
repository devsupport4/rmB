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

import com.ui.dao.MemberTypeDAO;
import com.ui.model.MemberType;

public class MemberTypeDAOImpl implements MemberTypeDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MemberTypeDAOImpl.class);
	
	public List<MemberType> getAllMemberType()
	{
		logger.info("Inside Get All Member Type Impl");
		
		List<MemberType> MemberType = new ArrayList<MemberType>();
		
		String s = "y";
		
		String sql = "select member_type_id, member_type_name from member_type where status=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			MemberType membertype = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				membertype = new MemberType(
		                rs.getInt("member_type_id"),
		                rs.getString("member_type_name")
				);
				MemberType.add(membertype);
           }
           rs.close();
           ps.close();
          
           return MemberType;
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