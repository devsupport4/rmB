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

import com.ui.dao.FellowshipDAO;
import com.ui.model.Fellowship;

public class FellowshipDAOImpl implements FellowshipDAO {

	@Autowired
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(FellowshipDAOImpl.class);
    

	@Override
	public String addFellowship(Fellowship m) {

		 logger.info("***** Add Fellowship DAO Impl*****");
      String sql = "insert into fellowship (fellowship_name,status,fellowship_city) values (?,?,?)";

      Connection conn = null;
      
      try
      {
  
      	conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
         
        
          ps.setString(1, m.getFellowship_name());  
        
			ps.setString(2, m.getStatus());
			 ps.setString(3, m.getFellowship_city());
			/*
			 * ps.setInt(6, m.getCreatedBy()); ps.setString(7, m.getIpAddress());
			 */
				

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

	@Override
	public List<Fellowship> getAllFellowship() {
		  logger.info("+++++ GET Fellowship NAME +++++");
		    List<Fellowship> mmp = new ArrayList<Fellowship>();
		    String s = "y";
		    String sql = "select fellowship_id ,fellowship_name,fellowship_city from fellowship where status=? ";
		    Connection conn = null;
		    try {
		        conn = dataSource.getConnection();
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, s);
		        Fellowship m = null;
		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            m = new Fellowship();
		            m.setFellowship_id(rs.getInt("fellowship_id"));
		            m.setFellowship_name(rs.getString("fellowship_name"));
		            m.setFellowship_city(rs.getString("fellowship_city"));
		      
		       
		            mmp.add(m);
		        }
		        rs.close();
		        ps.close();
		        return mmp;
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		            }
		        }
		    }
		  }
	@Override
	public Fellowship getFellowshipById(int fellowship_id) {
		Fellowship m = null;
		String sql = "SELECT fellowship_id,fellowship_name,fellowship_city FROM fellowship where fellowship_id=?";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, fellowship_id);
			ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			m = new Fellowship();
			 m.setFellowship_id(rs.getInt("fellowship_id"));
	            m.setFellowship_name(rs.getString("fellowship_name"));
	            m.setFellowship_city(rs.getString("fellowship_city"));
		}
		rs.close();
		ps.close();
		
		return m;
	} 
		catch (SQLException e) {
		
			throw new RuntimeException(e);
		} 
			finally {
		if (conn != null) {
			try {
					conn.close();
				} catch (SQLException e) {
			}
		  }
		}

	}
	@Override
	public String editFellowship(Fellowship m) {
		  logger.info("+++++ EDIT Fellowship +++++");
		    String sql = "update fellowship set fellowship_name=?, status=?,fellowship_city=?  where fellowship_id=?";
		    Connection conn = null;
		    try {
		        conn = dataSource.getConnection();
		        PreparedStatement ps = conn.prepareStatement(sql);
		        


		          ps.setString(1, m.getFellowship_name());  
		        
					ps.setString(2, m.getStatus());
					 ps.setString(3, m.getFellowship_city());
						 ps.setInt(4, m.getFellowship_id()); 
					

		        ps.executeUpdate();
		        return "Success";
		    } catch (SQLException e) {
		        return "Data not updated!";
		    } finally {
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		            }
		        }
		    }
		  }
	
	@Override
	public void deleteFellowship(int fellowship_id) {
		 logger.info("+++++ DELETE fellowship_id +++++");
		    String status = "n";
		    String sql = "update fellowship set status=? where fellowship_id=?";
		    Connection conn = null;
		    try {
		        conn = dataSource.getConnection();
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, status);
		        ps.setInt(2, fellowship_id);
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    } finally {
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		            }
		        }
		    }
		    
		  }
	@Override
	public List<Fellowship> getFellowshipByPage(int pagesize, int startindex) {
		// TODO Auto-generated method stub
		return null;
	}

}
