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

import com.ui.dao.BeneficiaryTypeDAO;
import com.ui.model.BeneficiaryType;

public class BeneficiaryTypeDAOImpl implements BeneficiaryTypeDAO{
	private DataSource dataSource;	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}	
	private static final Logger logger = LoggerFactory.getLogger(BeneficiaryTypeDAOImpl.class);
	
	public List<BeneficiaryType> getAllBeneficiaryType()
	{
		logger.info("+++++ GET ALL BENEFICIARY TYPE IMPL +++++");		
		List<BeneficiaryType> BeneficiaryType = new ArrayList<BeneficiaryType>();		
		String s = "y";		
		String sql = "select beneficiary_type_id, beneficiary_type_title from beneficiary_type where status=? order by beneficiary_type_title";		
		Connection conn = null;
		
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);
			BeneficiaryType beneficiarytype = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next())
			{
				beneficiarytype = new BeneficiaryType(
		                rs.getInt("beneficiary_type_id"),
		                rs.getString("beneficiary_type_title")
				);
				BeneficiaryType.add(beneficiarytype);
           }
           rs.close();
           ps.close();          
           return BeneficiaryType;
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
	
	public void addBeneficiaryType(BeneficiaryType bt)
	{
		logger.info("********** INSIDE ADD BENEFICIARY TYPE IMPL **********");		
		String sql = "insert into beneficiary_type (beneficiary_type_title, status, created_by, ip_address) values (?,?,?,?)";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, bt.getBeneficiaryTypeTitle());						
			ps.setString(2, bt.getStatus());
			ps.setInt(3, bt.getCreatedBy());
			ps.setString(4, bt.getIpAddress());			
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
	
	public void editBeneficiaryType(BeneficiaryType bt)
	{		
		logger.info("********** INSIDE EDIT BENEFICIARY TYPE IMPL **********");
		String sql = "update beneficiary_type set beneficiary_type_title=?, created_by=?, ip_address=?  where beneficiary_type_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			ps.setString(1, bt.getBeneficiaryTypeTitle());			
			ps.setInt(2, bt.getCreatedBy());			
			ps.setString(3, bt.getIpAddress());
			ps.setInt(4, bt.getBeneficiaryTypeId());
			
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
	
	public void deleteBeneficiaryType(int id)
	{
		logger.info("********** INSIDE DELETE BENEFICIARY TYPE ***********");
		String status="n";
		String sql = "update beneficiary_type set status=? where beneficiary_type_id=?";
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
