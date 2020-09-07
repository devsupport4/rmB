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

import com.ui.dao.MemberPostDAO;
import com.ui.model.MemberPost;
import com.ui.model.MemberPostComments;

public class MemberPostDAOImpl implements MemberPostDAO{
	@Autowired	
	private DataSource dataSource;	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}	
	JdbcTemplate jdbcTemplate;	
	private static final Logger logger = LoggerFactory.getLogger(MemberPostDAOImpl.class);
	
	public List<MemberPost> getAllMemberPost() {
		logger.info("++++++++++ GET ALL MEMBER POST IMPL +++++++++++");		
		List<MemberPost> MemberPost = new ArrayList<MemberPost>();		
		String s = "y";			
		String sql = "select mp.member_post_id, mp.member_post, mp.created_by, DATE_FORMAT(mp.created_date, '%d/%m/%Y %h:%i %p') as 'created_date', m.member_first_name, m.member_last_name, count(mpc.member_post_comment_id) as comment_count from  members m, member_post mp left join `member_post_comments` mpc on mp.member_post_id = mpc.member_post_id where mp.status=? and m.member_id = mp.created_by group by member_post_id order by mp.member_post_id desc limit 0,10";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);		
			MemberPost memberPost = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				memberPost = new MemberPost(
						rs.getInt("member_post_id"),
		                rs.getString("member_post"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("member_first_name"),
		                rs.getString("member_last_name"),
		                rs.getInt("comment_count")
				);
				MemberPost.add(memberPost);
           }
           rs.close();
           ps.close();
          
           return MemberPost;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<MemberPost> getLastThreeMemberPost() {
		logger.info("++++++++++ GET ALL MEMBER POST IMPL +++++++++++");		
		List<MemberPost> MemberPost = new ArrayList<MemberPost>();		
		String s = "y";			
		String sql = "select mp.member_post_id, mp.member_post, mp.created_by, DATE_FORMAT(mp.created_date, '%d/%m/%Y %h:%i %p') as 'created_date', m.member_first_name, m.member_last_name, count(mpc.member_post_comment_id) as comment_count from  members m, member_post mp left join `member_post_comments` mpc on mp.member_post_id = mpc.member_post_id where mp.status=? and m.member_id = mp.created_by group by member_post_id order by mp.member_post_id desc limit 0,3";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);		
			MemberPost memberPost = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				memberPost = new MemberPost(
						rs.getInt("member_post_id"),
		                rs.getString("member_post"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("member_first_name"),
		                rs.getString("member_last_name"),
		                rs.getInt("comment_count")
				);
				MemberPost.add(memberPost);
           }
           rs.close();
           ps.close();
          
           return MemberPost;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public void addNewMemberPost(MemberPost mp)	{
		logger.info("++++++++++ ADD NEW MEMBER POST IMPL ++++++++++");		
		String sql = "insert into member_post (member_post, status, created_by, ip_address) values (?,?,?,?)";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, mp.getMemberPost());			
			ps.setString(2, mp.getStatus());
			ps.setInt(3, mp.getCreatedBy());
			ps.setString(4, mp.getIpAddress());			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
	
	public List<MemberPostComments> getPostCommentsById(int postid) {
		logger.info("++++++++++ GET POST ALL COMMENTS IMPL +++++++++++");		
		List<MemberPostComments> MemberPostComments = new ArrayList<MemberPostComments>();		
		String s = "y";			
		String sql = "select mpc.member_post_comment_id, mpc.post_comment, mpc.member_post_id, mpc.created_by, DATE_FORMAT(mpc.created_date, '%d/%m/%Y %h:%i %p') as 'created_date', m.member_first_name, m.member_last_name from member_post_comments mpc, member_post mp, members m where mpc.status=? and mp.member_post_id=? and mp.member_post_id = mpc.member_post_id and m.member_id = mpc.created_by order by member_post_id limit 0,10";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, s);
			ps.setInt(2, postid);
			MemberPostComments memberPostComments = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				memberPostComments = new MemberPostComments(
						rs.getInt("member_post_comment_id"),
		                rs.getString("post_comment"),
		                rs.getInt("member_post_id"),
		                rs.getInt("created_by"),
		                rs.getString("created_date"),
		                rs.getString("member_first_name"),
		                rs.getString("member_last_name")
				);
				MemberPostComments.add(memberPostComments);
           }
           rs.close();
           ps.close();
          
           return MemberPostComments;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public void addComment(MemberPostComments mpc)	{
		logger.info("++++++++++ ADD NEW COMMENT IMPL ++++++++++");		
		String sql = "insert into member_post_comments (post_comment, member_post_id, status, created_by, ip_address) values (?,?,?,?,?)";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, mpc.getPostComment());			
			ps.setInt(2, mpc.getMemberPostId());
			ps.setString(3, mpc.getStatus());
			ps.setInt(4, mpc.getCreatedBy());
			ps.setString(5, mpc.getIpAddress());			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
}
