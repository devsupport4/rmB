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

import com.ui.dao.CurrencyDAO;
import com.ui.model.Currency;

public class CurrencyDAOImpl implements CurrencyDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	private static final Logger logger = LoggerFactory.getLogger(CurrencyDAOImpl.class);
	
	public List<Currency> getAllCurrency()
	{
		logger.info("Inside Get All Currency Impl");
		List<Currency> sta = new ArrayList<Currency>();
		String s = "y";
		String sql = "select currency_id, currency_name, currency_code, currency_symbol, description, status, created_by, ip_address  from currency where status=? order by currency_name";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, s);

			Currency currency = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				currency = new Currency(
                rs.getInt("currency_id"),
                rs.getString("currency_name"),
                rs.getString("currency_code"),
                rs.getString("currency_symbol"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getInt("created_by"),                
                rs.getString("ip_address")
				);
				
				sta.add(currency);
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
	
	/*public void addCurrency(Currency c)
	{
		logger.info("Inside Add currency Impl");
		
		String sql = "insert into currency (currency_name,currency_code,currency_symbol,description,status,created_by,created_date,ip_address) values (?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getCurrencyName());
			ps.setString(2, c.getCurrencyCode());
			ps.setString(3, c.getCurrencySymbol());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getStatus());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getCreatedDate());
			ps.setString(8, c.getIpAddress());
			
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
	
	public void editCurrency(Currency c)
	{		
		logger.info("Inside Edit Currency Impl");
		String sql = "update currency set currency_name=?,currency_code=?,currency_symbol=?,description=?,status=?,created_by=?,created_date=?,ip_address=? where currency_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getCurrencyName());
			ps.setString(2, c.getCurrencyCode());
			ps.setString(3, c.getCurrencySymbol());
			ps.setString(4, c.getDescription());
			ps.setString(5, c.getStatus());
			ps.setInt(6, c.getCreatedBy());
			ps.setString(7, c.getCreatedDate());
			ps.setString(8, c.getIpAddress());
			ps.setInt(9, c.getCurrencyId());
			
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
	
	public void deleteCurrency(int id)
	{
		logger.info("Inside Delete Currency impl");
		String status="n";
		String sql = "update currency set status=? where currency_id=?";
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
	}*/
}