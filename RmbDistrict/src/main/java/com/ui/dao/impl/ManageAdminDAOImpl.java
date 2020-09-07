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

import com.ui.dao.ManageAdminDAO;
import com.ui.model.ManageAdmin;



public class ManageAdminDAOImpl implements  ManageAdminDAO {
	

	@Autowired
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ManageAdminDAOImpl.class);
    


	@Override
	public String addManageAdmin(ManageAdmin m) {
		 logger.info("***** Add Fellowship DAO Impl*****");
	     // String sql = "insert into user (member_id,fellowship_id,user_name,password,status,created_by, ip_address,user_role_id) values (?,?,?,?,?,?,?,?)";

		 String sql ="update members set user_role_id=? where member_id";
	      
	     
	      Connection conn = null;
	      
	      try
	      {
	  
	      	conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql);
	         
	        //
	        ps.setInt(1, m.getMember_id());
	        ps.setInt(2, m.getFellowship_id());
	        ps.setString(3, m.getUser_name());  
	        ps.setString(4, m.getPassword());
	        ps.setString(5, m.getStatus());
			ps.setInt(6, m.getCreatedBy());
			
			ps.setString(7, m.getIpAddress());
			ps.setInt(8, m.getUser_role_id());
	
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
	public List<ManageAdmin> getAllManageAdmin() {
	
		logger.info("+++++ GET Location NAME +++++");
		  
	    List<ManageAdmin> mmp = new ArrayList<ManageAdmin>();
	    String s = "y";
	    String sql = "select user_id,member_id,fellowship_id from user where status=? ";
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, s);
	        ManageAdmin m1 = null;
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            m1 = new ManageAdmin();
	            
	            m1.setMember_id(rs.getInt("member_id"));
				
				m1.setFellowship_id(rs.getInt("fellowship_id"));
			
	       
	            mmp.add(m1);
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
	public ManageAdmin getManageAdminById(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editManageAdmin(ManageAdmin m) {
		logger.info("+++++ EDIT Location +++++");
	 
	    String sql ="update members set user_role_id=?,fellowship_id=? ,status=?, created_by=?, ip_address=? where member_id=?";
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        

	        ps.setInt(1, m.getUser_role_id());  
	           ps.setInt(2, m.getFellowship_id());
					ps.setString(3, m.getStatus());
					ps.setInt(4, m.getCreatedBy());
					ps.setString(5, m.getIpAddress());
					ps.setInt(6, m.getMember_id());
					

	        ps.executeUpdate();
	        return "Success";
	    } catch (SQLException e) {
	    	System.out.println("///////////////////////////////"+e);
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
	public void deleteManageAdmin(int member_id) {
		  logger.info("+++++ deleteManageAdmin +++++");
	
		    String sql = "update members set user_role_id=3 where member_id=?";
		    Connection conn = null;
		    try {
		        conn = dataSource.getConnection();
		        PreparedStatement ps = conn.prepareStatement(sql);
		       
		        ps.setInt(1, member_id);
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
	public List<ManageAdmin> getManageAdminByPage(int pagesize, int startindex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ManageAdmin> getAllMember() {
		logger.info("+++++ GET getAllMember NAME +++++");
		  
	    List<ManageAdmin> mmp = new ArrayList<ManageAdmin>();
	    String s = "y";
	  String sql = "select member_id,member_first_name,member_last_name,member_email,fellowship_id from members where status=? ";
	   // String sql="select m.member_id,m.member_first_name,m.member_last_name,m.club_id,m.fellowship_id,m.member_mobile_number,m.member_email,cc.club_name as club_name,ff.fellowship_name as fellowship_name from members m left join club cc on cc.club_id=m.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.user_role_id=2";
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	       ps.setString(1, s);
	     //ps.setInt(2,fellowship_id);
	      
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	 ManageAdmin m1 = new ManageAdmin();
	            
				m1.setMember_id(rs.getInt("member_id"));
				m1.setFirstName(rs.getString("member_first_name"));
				m1.setLastName(rs.getString("member_last_name"));
				m1.setUser_name(rs.getString("member_email"));
				m1.setFellowship_id(rs.getInt("fellowship_id"));
			
		
	          
	       
	            mmp.add(m1);
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
	public ManageAdmin getAllMemberUserPaswword() {

	    logger.info("+++++ GET MasterMember By Type +++++");
	   
	    String s = "y";
	 //   String sql = "select member_id,member_email,member_password from members where status=? and member_id=? and fellowship_id=? ";
	  String sql="select m.member_id,m.club_id,m.fellowship_id,m.member_mobile_number,m.member_email,cc.club_name,ff.fellowship_name from members m left join club cc on cc.club_id=m.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.user_role_id=2;";
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	       
	       
			/* ps.setInt(, user_role_id); */
	        ManageAdmin m = null;
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	   m = new ManageAdmin();
	        	  
					m.setUser_name(rs.getString("member_email"));
					m.setPassword(rs.getString("member_password"));

					System.out.println("///////////////////////////////"+rs.getString("member_email"));
					System.out.println("?????????????????????????????"+rs.getString("member_password"));
		        
	        	
	        }
	        rs.close();
	        ps.close();
	        return m;
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
	public List<ManageAdmin> getAllAdmin() {
		logger.info("+++++ GET getAllMember NAME +++++");
		  
	    List<ManageAdmin> mmp = new ArrayList<ManageAdmin>();
	    String s = "y";
	 //   String sql = "select member_id,member_first_name,member_last_name,member_email,fellowship_id from members where status=? ";
	    String sql="select m.member_id,m.member_first_name,m.member_last_name,m.club_id,m.fellowship_id,m.member_mobile_number,m.member_email,cc.club_name as club_name,ff.fellowship_name as fellowship_name from members m left join club cc on cc.club_id=m.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.user_role_id=2";
	    Connection conn = null;
	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	      //  ps.setString(1, s);
	      //  ps.setInt(2,fellowship_id);
	      
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	        	 ManageAdmin m1 = new ManageAdmin();
	            
				m1.setMember_id(rs.getInt("member_id"));
				m1.setFirstName(rs.getString("member_first_name"));
				m1.setLastName(rs.getString("member_last_name"));
				m1.setUser_name(rs.getString("member_email"));
				m1.setFellowship_id(rs.getInt("fellowship_id"));
			    m1.setClub_name(rs.getString("club_name"));
			    m1.setFellowship_name(rs.getString("fellowship_name"));
			    m1.setMobile_number(rs.getString("member_mobile_number"));
		
	          
	       
	            mmp.add(m1);
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
	
}
