package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.ReadExcelDAO;
import com.ui.model.ReadExcel;

public class ReadExcelDAOImpl implements ReadExcelDAO {

	@Autowired
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    JdbcTemplate jdbcTemplate;

	@Override
	public String readExcelData(ReadExcel re) {
		  String sql = "insert into readExcel (number,name) values (?,?)";
		    
	        Connection conn = null;
	        
	        try
	        {
	    
	        	conn = dataSource.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql);
	            System.out.print("test");
	         
	          
	            ps.setInt(1, re.getNumber());
	            ps.setString(2, re.getName());
	          

	            System.out.println("Controller");
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
	        return "Success";
	    }

	}
