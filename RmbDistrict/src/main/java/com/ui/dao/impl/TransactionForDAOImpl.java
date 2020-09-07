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

import com.ui.dao.TransactionForDAO;
import com.ui.model.TransactionFor;

public class TransactionForDAOImpl implements TransactionForDAO {

	@Autowired	
	private DataSource dataSource;	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}	
	private static final Logger logger = LoggerFactory.getLogger(TransactionForDAOImpl.class);
	
	public List<TransactionFor> getAllTransactionFor(){
		logger.info("++++++++++ GET ALL TRANSACTION FOR ++++++++++");		
		List<TransactionFor> sta = new ArrayList<TransactionFor>();	
		String sql = "select transaction_for_id, transaction_for_name, transaction_for_detail from transaction_for where status='y' order by transaction_for_name";		
		Connection conn = null;
		try	{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			TransactionFor transactionFor = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				transactionFor = new TransactionFor(
				rs.getInt("transaction_for_id"),
                rs.getString("transaction_for_name"),
                rs.getString("transaction_for_detail")
				);				
				sta.add(transactionFor);
           }
           rs.close();
           ps.close();
          
           return sta;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
}
