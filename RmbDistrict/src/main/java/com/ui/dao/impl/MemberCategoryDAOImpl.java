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

import com.ui.dao.MemberCategoryDAO;
import com.ui.model.MemberCategory;

public class MemberCategoryDAOImpl implements MemberCategoryDAO {
private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MemberCategoryDAOImpl.class);
	
	public List<MemberCategory> getAllMemberCategory()
	{
		logger.info("Inside Get All Member Category Impl");
		
		List<MemberCategory> MemberCategory = new ArrayList<MemberCategory>();
		
		String s = "y";
		
		String sql = "select member_category_id, member_category_name from member_category where status=?";
		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			MemberCategory membercategory = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				membercategory = new MemberCategory(
		                rs.getInt("member_category_id"),
		                rs.getString("member_category_name")
				);
				MemberCategory.add(membercategory);
           }
           rs.close();
           ps.close();
          
           return MemberCategory;
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