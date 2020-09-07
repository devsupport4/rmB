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

import com.ui.dao.MemberReferralDAO;
import com.ui.model.MemberOneToOne;
import com.ui.model.MemberReferral;
import com.ui.model.MemberThankYouSlip;

public class MemberReferralDAOImpl implements MemberReferralDAO {
	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MemberReferralDAOImpl.class);

	public List<MemberOneToOne> getMemberMeetingsById(int memberid, String fromdate, String todate) {
		logger.info("+++++ GET MEMBER MEETING BY ID +++++");
		List<MemberOneToOne> MemberOneToOne = new ArrayList<MemberOneToOne>();
		String s = "y";
		String sql = "select o.one_to_one_id, o.met_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, o.invited_by_member_id, mm.member_first_name as invitee_first_name, mm.member_middle_name as invitee_middle_name, mm.member_last_name as invitee_last_name, DATE_FORMAT(o.meeting_date, '%d/%m/%Y') as meeting_date, o.location, o.conversation_topic from member_one_to_one o, members m, members mm where o.status=? and o.created_by=? and o.meeting_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and m.member_id = o.met_member_id and mm.member_id = o.invited_by_member_id order by o.meeting_date";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, memberid);
			ps.setString(3, fromdate);
			ps.setString(4, todate);
			MemberOneToOne memberOneToOne = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberOneToOne = new MemberOneToOne(rs.getInt("one_to_one_id"), rs.getInt("met_member_id"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getInt("invited_by_member_id"),
						rs.getString("invitee_first_name"), rs.getString("invitee_middle_name"),
						rs.getString("invitee_last_name"), rs.getString("meeting_date"), rs.getString("location"),
						rs.getString("conversation_topic"));
				MemberOneToOne.add(memberOneToOne);
			}
			rs.close();
			ps.close();

			return MemberOneToOne;
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

	public List<MemberThankYouSlip> getMemberBusinessById(int memberid, String fromdate, String todate) {
		logger.info("+++++ GET MEMBER BUSINESS BY ID +++++");
		List<MemberThankYouSlip> MemberThankYouSlip = new ArrayList<MemberThankYouSlip>();
		String s = "y";
		String sql = "select t.thank_you_slip_id, t.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, t.amount, DATE_FORMAT(t.slip_date, '%d/%m/%Y') as slip_date, t.business_type, t.referral_type, t.comments from member_thank_you_slip t, members m where t.status=? and t.created_by=? and t.slip_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and m.member_id = t.to_member_id order by t.slip_date";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, memberid);
			ps.setString(3, fromdate);
			ps.setString(4, todate);
			MemberThankYouSlip memberThankYouSlip = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberThankYouSlip = new MemberThankYouSlip(rs.getInt("thank_you_slip_id"), rs.getInt("to_member_id"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getFloat("amount"), rs.getString("slip_date"),
						rs.getString("business_type"), rs.getString("referral_type"), rs.getString("comments"));
				MemberThankYouSlip.add(memberThankYouSlip);
			}
			rs.close();
			ps.close();

			return MemberThankYouSlip;
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
	
	public List<MemberReferral> getMemberReferencesById(int memberid, String fromdate, String todate) {
		logger.info("+++++ GET MEMBER MEETING BY ID +++++");
		List<MemberReferral> MemberReferral = new ArrayList<MemberReferral>();
		String s = "y";
		String sql = "select r.member_referral_id, r.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, r.referral_name, DATE_FORMAT(r.refer_date, '%d/%m/%Y') as refer_date, r.referral_type, r.referral_status1, r.referral_status2, r.address, r.contact_number, r.email, r.comments, r.member_referral_status, r.referal_close_date, r.appr_value from member_referral r, members m where r.status=? and r.created_by=? and r.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and m.member_id = r.to_member_id order by r.refer_date";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, memberid);
			ps.setString(3, fromdate);
			ps.setString(4, todate);
			MemberReferral memberReferral = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				memberReferral = new MemberReferral();
				
				memberReferral = new MemberReferral();
				memberReferral.setMemberReferralId(rs.getInt("member_referral_id"));
				memberReferral.setToMemberId(rs.getInt("to_member_id"));
				memberReferral.setMemberFirstName(rs.getString("member_first_name"));
				memberReferral.setMemberMiddleName(rs.getString("member_middle_name"));
				memberReferral.setMemberLastName(rs.getString("member_last_name"));
				memberReferral.setReferralName(rs.getString("referral_name"));
				memberReferral.setReferDate(rs.getString("refer_date"));
				memberReferral.setReferralType(rs.getString("referral_type"));
				memberReferral.setReferralStatus1(rs.getString("referral_status1"));
				memberReferral.setReferralStatus2(rs.getString("referral_status2"));
				memberReferral.setAddress(rs.getString("address"));
				memberReferral.setContactNumber(rs.getString("contact_number"));
				memberReferral.setEmail(rs.getString("email"));
				memberReferral.setComments(rs.getString("comments"));
				memberReferral.setCloseDate(rs.getString("referal_close_date"));
				memberReferral.setApprValue(rs.getString("appr_value"));
				memberReferral.setReferralStatus(rs.getString("member_referral_status"));
				MemberReferral.add(memberReferral);
			}
			rs.close();
			ps.close();

			return MemberReferral;
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

	public void saveReferral(MemberReferral mr) {
		logger.info("+++++ SAVE REFERRAL +++++");
		String sql = "insert into member_referral (to_member_id, referral_name, refer_date, referral_type, referral_status1, referral_status2, address, contact_number, email, comments, member_referral_status, referal_close_date, appr_value, status, created_by, ip_address) values (?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mr.getToMemberId());
			ps.setString(2, mr.getReferralName());
			ps.setString(3, mr.getReferDate());
			ps.setString(4, mr.getReferralType());
			ps.setString(5, mr.getReferralStatus1());
			ps.setString(6, mr.getReferralStatus2());
			ps.setString(7, mr.getAddress());
			ps.setString(8, mr.getContactNumber());
			ps.setString(9, mr.getEmail());
			ps.setString(10, mr.getComments());
			ps.setString(11, mr.getReferralStatus());
			ps.setString(12, mr.getCloseDate());
			ps.setString(13, mr.getApprValue());
			ps.setString(14, mr.getStatus());
			ps.setInt(15, mr.getCreatedBy());
			ps.setString(16, mr.getIpAddress());

			ps.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void saveThankYouSlip(MemberThankYouSlip mr) {
		logger.info("+++++ SAVE THANK YOU SLIP +++++");
		String sql = "insert into member_thank_you_slip (to_member_id, amount, slip_date, business_type, referral_type, comments, status, created_by, ip_address) values (?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mr.getToMemberId());
			ps.setFloat(2, mr.getAmount());
			ps.setString(3, mr.getSlipDate());
			ps.setString(4, mr.getBusinessType());
			ps.setString(5, mr.getReferralType());
			ps.setString(6, mr.getComments());
			ps.setString(7, mr.getStatus());
			ps.setInt(8, mr.getCreatedBy());
			ps.setString(9, mr.getIpAddress());
			ps.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void saveOnetoOne(MemberOneToOne mr) {
		logger.info("+++++ SAVE ONE TO ONE +++++");
		String sql = "insert into member_one_to_one (met_member_id, invited_by_member_id, meeting_date, location, conversation_topic, status, created_by, ip_address) values (?,?,STR_TO_DATE(?,'%d/%m/%Y'),?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mr.getMetMemberId());
			ps.setInt(2, mr.getInvitedByMemberId());
			ps.setString(3, mr.getMeetingDate());
			ps.setString(4, mr.getLocation());
			ps.setString(5, mr.getConversationTopic());
			ps.setString(6, mr.getStatus());
			ps.setInt(7, mr.getCreatedBy());
			ps.setString(8, mr.getIpAddress());
			ps.executeUpdate();
		}

		catch (SQLException e1) {
			throw new RuntimeException(e1);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}
	
	
	public List<MemberThankYouSlip> getAllBusiness() {
      logger.info("+++++ GET MEMBER BUSINESS BY ID +++++");
      List<MemberThankYouSlip> MemberThankYouSlip = new ArrayList<MemberThankYouSlip>();
      String s = "y";
      String sql = "select t.thank_you_slip_id, t.to_member_id, m.member_first_name as to_first_name, m.member_middle_name as to_middle_name, m.member_last_name as to_last_name, f.member_first_name as from_first_name, f.member_last_name as from_last_name, t.amount, DATE_FORMAT(t.slip_date, '%d/%m/%Y') as slip_date, t.business_type, t.referral_type, t.comments, t.created_by from member_thank_you_slip t, members m , members f where t.status=? and t.slip_date and m.member_id = t.to_member_id and f.member_id = t.created_by order by t.slip_date desc";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);          
          MemberThankYouSlip memberThankYouSlip = null;
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              memberThankYouSlip = new MemberThankYouSlip(rs.getInt("thank_you_slip_id"), rs.getInt("to_member_id"),
                      rs.getString("to_first_name"), rs.getString("to_middle_name"), rs.getString("to_last_name"), rs.getString("from_first_name"), rs.getString("from_last_name"), rs.getFloat("amount"), rs.getString("slip_date"),
                      rs.getString("business_type"), rs.getString("referral_type"), rs.getString("comments"), rs.getInt("created_by"));
              MemberThankYouSlip.add(memberThankYouSlip);
          }
          rs.close();
          ps.close();

          return MemberThankYouSlip;
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
	
	public void deleteBusiness(int id) {
      logger.info("********** NISIDE DELETE Business ************");

      String status = "n";

      String sql = "update member_thank_you_slip set status=? where thank_you_slip_id=?";

      Connection conn = null;

      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);

          ps.setString(1, status);
          ps.setInt(2, id);

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
	
	public MemberThankYouSlip getBusinessDetailById(int thankyouslipid) {
      logger.info("+++++ GET MEMBER BUSINESS BY ID +++++");      
      String sql = "select t.thank_you_slip_id, t.to_member_id, m.member_first_name as to_first_name, m.member_middle_name as to_middle_name, m.member_last_name as to_last_name, f.member_first_name as from_first_name, f.member_last_name as from_last_name, t.amount, DATE_FORMAT(t.slip_date, '%d/%m/%Y') as slip_date, t.business_type, t.referral_type, t.comments, t.created_by from member_thank_you_slip t, members m , members f where t.thank_you_slip_id=? and t.slip_date and m.member_id = t.to_member_id and f.member_id = t.created_by order by t.slip_date";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setInt(1, thankyouslipid);          
          MemberThankYouSlip memberThankYouSlip = null;
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
            memberThankYouSlip = new MemberThankYouSlip(rs.getInt("thank_you_slip_id"), rs.getInt("to_member_id"),
                    rs.getString("to_first_name"), rs.getString("to_middle_name"), rs.getString("to_last_name"), rs.getString("from_first_name"), rs.getString("from_last_name"), rs.getFloat("amount"), rs.getString("slip_date"),
                    rs.getString("business_type"), rs.getString("referral_type"), rs.getString("comments"), rs.getInt("created_by"));            
        }
          rs.close();
          ps.close();

          return memberThankYouSlip;
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
	
	public List<MemberOneToOne> getAllMeeting() {
      logger.info("+++++ GET MEMBER MEETING BY ID +++++");
      List<MemberOneToOne> MemberOneToOne = new ArrayList<MemberOneToOne>();
      String s = "y";
      String sql = "select o.one_to_one_id, o.met_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, o.invited_by_member_id, mm.member_first_name as invitee_first_name, mm.member_middle_name as invitee_middle_name, mm.member_last_name as invitee_last_name, DATE_FORMAT(o.meeting_date, '%d/%m/%Y') as meeting_date, o.location, o.conversation_topic from member_one_to_one o, members m, members mm where o.status=? and o.meeting_date and m.member_id = o.met_member_id and mm.member_id = o.invited_by_member_id order by o.meeting_date desc";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);         
          MemberOneToOne memberOneToOne = null;
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              memberOneToOne = new MemberOneToOne(rs.getInt("one_to_one_id"), rs.getInt("met_member_id"),
                      rs.getString("member_first_name"), rs.getString("member_middle_name"),
                      rs.getString("member_last_name"), rs.getInt("invited_by_member_id"),
                      rs.getString("invitee_first_name"), rs.getString("invitee_middle_name"),
                      rs.getString("invitee_last_name"), rs.getString("meeting_date"), rs.getString("location"),
                      rs.getString("conversation_topic"));
              MemberOneToOne.add(memberOneToOne);
          }
          rs.close();
          ps.close();

          return MemberOneToOne;
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
	
	public void deleteMeeting(int meetingid) {
      logger.info("********** NISIDE DELETE Business ************");

      String status = "n";

      String sql = "update member_one_to_one set status=? where one_to_one_id=?";

      Connection conn = null;

      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);

          ps.setString(1, status);
          ps.setInt(2, meetingid);

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
	
	public MemberOneToOne getMeetingDetailById(int onetooneid) {
      logger.info("+++++ GET MEMBER MEETING BY ID +++++");     
      String sql = "select o.one_to_one_id, o.met_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, o.invited_by_member_id, mm.member_first_name as invitee_first_name, mm.member_middle_name as invitee_middle_name, mm.member_last_name as invitee_last_name, DATE_FORMAT(o.meeting_date, '%d/%m/%Y') as meeting_date, o.location, o.conversation_topic from member_one_to_one o, members m, members mm where o.one_to_one_id=? and o.meeting_date and m.member_id = o.met_member_id and mm.member_id = o.invited_by_member_id order by o.meeting_date";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);          
          ps.setInt(1, onetooneid);         
          MemberOneToOne memberOneToOne = null;
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              memberOneToOne = new MemberOneToOne(rs.getInt("one_to_one_id"), rs.getInt("met_member_id"),
                      rs.getString("member_first_name"), rs.getString("member_middle_name"),
                      rs.getString("member_last_name"), rs.getInt("invited_by_member_id"),
                      rs.getString("invitee_first_name"), rs.getString("invitee_middle_name"),
                      rs.getString("invitee_last_name"), rs.getString("meeting_date"), rs.getString("location"),
                      rs.getString("conversation_topic"));              
          }
          rs.close();
          ps.close();

          return memberOneToOne;
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
	
	
	public List<MemberReferral> getAllReference() {
      logger.info("+++++ GET MEMBER MEETING BY ID +++++");
      List<MemberReferral> MemberReferral = new ArrayList<MemberReferral>();
      String s = "y";
      String sql = "select r.member_referral_id, r.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, r.referral_name, mm.member_first_name as from_first_name, mm.member_middle_name as from_middle_name, mm.member_last_name as from_last_name, DATE_FORMAT(r.refer_date, '%d/%m/%Y') as refer_date, r.referral_type, r.referral_status1, r.referral_status2, r.address, r.contact_number, r.email, r.comments from member_referral r, members m, members mm where r.status=? and m.member_id = r.to_member_id and mm.member_id = r.created_by order by r.refer_date desc";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, s);          
          MemberReferral memberReferral = null;
          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              memberReferral = new MemberReferral(rs.getInt("member_referral_id"), rs.getInt("to_member_id"),
                      rs.getString("member_first_name"), rs.getString("member_middle_name"),
                      rs.getString("member_last_name"), rs.getString("referral_name"),
                      rs.getString("from_first_name"), rs.getString("from_middle_name"),
                      rs.getString("from_last_name"),
                      rs.getString("refer_date"), rs.getString("referral_type"),
                      rs.getString("referral_status1"), rs.getString("referral_status2"), rs.getString("address"),
                      rs.getString("contact_number"), rs.getString("email"), rs.getString("comments"));
              MemberReferral.add(memberReferral);
          }
          rs.close();
          ps.close();

          return MemberReferral;
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
	
	public void deleteRef(int referenceid) {
      logger.info("********** NISIDE DELETE Business ************");

      String status = "n";

      String sql = "update member_referral set status=? where member_referral_id=?";

      Connection conn = null;

      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);

          ps.setString(1, status);
          ps.setInt(2, referenceid);

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
	
	 public MemberReferral getReferenceDetailById(int memberreferralid) {
       logger.info("+++++ GET MEMBER MEETING BY ID +++++");
               
       String sql = "select r.member_referral_id, r.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, r.referral_name, mm.member_first_name as from_first_name, mm.member_middle_name as from_middle_name, mm.member_last_name as from_last_name, DATE_FORMAT(r.refer_date, '%d/%m/%Y') as refer_date, r.referral_type, r.referral_status1, r.referral_status2, r.address, r.contact_number, r.email, r.comments, r.member_referral_status,DATE_FORMAT(r.referal_close_date, '%d/%m/%Y') as referal_close_date,r.appr_value, r.close_reasson, r.close_comment from member_referral r, members m, members mm where r.member_referral_id=? and r.refer_date and m.member_id = r.to_member_id and mm.member_id = r.created_by order by r.refer_date";
       Connection conn = null;
       try {
           conn = dataSource.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);            
           ps.setInt(1, memberreferralid);            
           MemberReferral memberReferral = null;
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {   
             
             memberReferral = new MemberReferral();
				
				memberReferral = new MemberReferral();
				memberReferral.setMemberReferralId(rs.getInt("member_referral_id"));
				memberReferral.setToMemberId(rs.getInt("to_member_id"));
				memberReferral.setMemberFirstName(rs.getString("member_first_name"));
				memberReferral.setMemberMiddleName(rs.getString("member_middle_name"));
				memberReferral.setMemberLastName(rs.getString("member_last_name"));
				memberReferral.setReferralName(rs.getString("referral_name"));
				memberReferral.setFromFirstName(rs.getString("from_first_name"));
				memberReferral.setFromMiddleName(rs.getString("from_middle_name"));
				memberReferral.setFromLastName(rs.getString("from_last_name"));
				memberReferral.setReferDate(rs.getString("refer_date"));
				memberReferral.setReferralType(rs.getString("referral_type"));
				memberReferral.setReferralStatus1(rs.getString("referral_status1"));
				memberReferral.setReferralStatus2(rs.getString("referral_status2"));
				memberReferral.setAddress(rs.getString("address"));
				memberReferral.setContactNumber(rs.getString("contact_number"));
				memberReferral.setEmail(rs.getString("email"));
				memberReferral.setComments(rs.getString("comments"));
				memberReferral.setCloseDate(rs.getString("referal_close_date"));
				memberReferral.setApprValue(rs.getString("appr_value"));
				memberReferral.setCloseComment(rs.getString("close_comment"));
				memberReferral.setCloseReason(rs.getString("close_reasson"));
				memberReferral.setReferralStatus(rs.getString("member_referral_status"));
           }
           rs.close();
           ps.close();

           return memberReferral;
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
	 
	 
	 public List<MemberThankYouSlip> getMemberBusinessForChapterSummaryById(int memberid, String fromdate, String todate) {
       logger.info("+++++ GET MEMBER BUSINESS BY ID FOR CHAPTER SUMMARY +++++");
       List<MemberThankYouSlip> MemberThankYouSlip = new ArrayList<MemberThankYouSlip>();
       String s = "y";
       String sql = "select t.thank_you_slip_id, t.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, t.amount, DATE_FORMAT(t.slip_date, '%d/%m/%Y') as slip_date, t.business_type, t.referral_type, t.comments, mm.member_first_name as from_first_name, mm.member_middle_name as from_middle_name, mm.member_last_name as from_last_name from member_thank_you_slip t, members m, members mm where t.status=? and t.to_member_id=? and t.slip_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and m.member_id = t.to_member_id and mm.member_id = t.created_by order by t.slip_date";
       Connection conn = null;
       try {
           conn = dataSource.getConnection();
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, s);
           ps.setInt(2, memberid);
           ps.setString(3, fromdate);
           ps.setString(4, todate);
           MemberThankYouSlip memberThankYouSlip = null;
           ResultSet rs = ps.executeQuery();

           while (rs.next()) {
               memberThankYouSlip = new MemberThankYouSlip(rs.getInt("thank_you_slip_id"), rs.getInt("to_member_id"),
                       rs.getString("member_first_name"), rs.getString("member_middle_name"),
                       rs.getString("member_last_name"), rs.getFloat("amount"), rs.getString("slip_date"),
                       rs.getString("business_type"), rs.getString("referral_type"), rs.getString("comments"), rs.getString("from_first_name"), rs.getString("from_middle_name"),
                       rs.getString("from_last_name"));
               MemberThankYouSlip.add(memberThankYouSlip);
           }
           rs.close();
           ps.close();

           return MemberThankYouSlip;
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
	public List<MemberReferral> getMemberReferralsById(int memberid, String fromdate, String todate) {
		logger.info("+++++ GET MEMBER REFERRALS BY ID +++++");
		List<MemberReferral> MemberReferral = new ArrayList<MemberReferral>();
		String s = "y";
		String sql = "select r.member_referral_id, r.to_member_id, m.member_first_name, m.member_middle_name, m.member_last_name, r.referral_name, DATE_FORMAT(r.refer_date, '%d/%m/%Y') as refer_date, r.referral_type, r.referral_status1, r.referral_status2, r.address, r.contact_number, r.email, r.comments, r.member_referral_status, r.referal_close_date, r.appr_value from member_referral r, members m where r.status=? and r.to_member_id=? and r.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and m.member_id = r.created_by order by r.refer_date";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, memberid);
			ps.setString(3, fromdate);
			ps.setString(4, todate);
			MemberReferral memberReferral = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberReferral = new MemberReferral();
				
				memberReferral = new MemberReferral();
				memberReferral.setMemberReferralId(rs.getInt("member_referral_id"));
				memberReferral.setToMemberId(rs.getInt("to_member_id"));
				memberReferral.setMemberFirstName(rs.getString("member_first_name"));
				memberReferral.setMemberMiddleName(rs.getString("member_middle_name"));
				memberReferral.setMemberLastName(rs.getString("member_last_name"));
				memberReferral.setReferralName(rs.getString("referral_name"));
				memberReferral.setReferDate(rs.getString("refer_date"));
				memberReferral.setReferralType(rs.getString("referral_type"));
				memberReferral.setReferralStatus1(rs.getString("referral_status1"));
				memberReferral.setReferralStatus2(rs.getString("referral_status2"));
				memberReferral.setAddress(rs.getString("address"));
				memberReferral.setContactNumber(rs.getString("contact_number"));
				memberReferral.setEmail(rs.getString("email"));
				memberReferral.setComments(rs.getString("comments"));
				memberReferral.setCloseDate(rs.getString("referal_close_date"));
				memberReferral.setApprValue(rs.getString("appr_value"));
				memberReferral.setReferralStatus(rs.getString("member_referral_status"));
				MemberReferral.add(memberReferral);
			}
			rs.close();
			ps.close();

			return MemberReferral;
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
	public void ChangeRefStatus(int memberreferralid, String status) {

		logger.info("+++++ CHANGE REFERENCE STATUS +++++");
	      String sql = "update member_referral set member_referral_status=? where member_referral_id=?";

	      Connection conn = null;

	      try {
	          conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql);

	          ps.setString(1, status);
	          ps.setInt(2, memberreferralid);

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
	public String updateReferral(MemberReferral memberReferral) {
		
		logger.info("+++++ UPDATE MEMBER REFERRAL +++++");
	      String sql = "update member_referral set to_member_id=?, referral_name=?, refer_date=STR_TO_DATE(?,'%d/%m/%Y'), referral_type=?, referral_status1=?, referral_status2=?, address=?, contact_number=?, email=?, comments=?, member_referral_status=?, referal_close_date=STR_TO_DATE(?,'%d/%m/%Y'), appr_value=?, close_comment=?, close_reasson=? where member_referral_id=?";

	      Connection conn = null;

	      try {
	          conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql);
	          
				ps.setInt(1, memberReferral.getToMemberId());
				ps.setString(2, memberReferral.getReferralName());
				ps.setString(3, memberReferral.getReferDate());
				ps.setString(4, memberReferral.getReferralType());
				ps.setString(5, memberReferral.getReferralStatus1());
				ps.setString(6, memberReferral.getReferralStatus2());
				ps.setString(7, memberReferral.getAddress());
				ps.setString(8, memberReferral.getContactNumber());
				ps.setString(9, memberReferral.getEmail());
				ps.setString(10, memberReferral.getComments());
				ps.setString(11, memberReferral.getReferralStatus());
				ps.setString(12, memberReferral.getCloseDate());
				ps.setString(13, memberReferral.getApprValue());
				ps.setString(14, memberReferral.getCloseComment());
		        ps.setString(15, memberReferral.getCloseReason());
				ps.setInt(16, memberReferral.getMemberReferralId());
				
				ps.executeUpdate();
				return "Success";
	      } catch (SQLException e) {
	          throw new RuntimeException(e);
	      } finally {
	          if (conn != null) {
	              try {
	                  conn.close();
	              } catch (SQLException e) {
	            	  return e.toString();
	              }
	          }
	      }
	}

	@Override
	public void CloseRef(MemberReferral mr) {
		logger.info("+++++ CLOSE REFERENCE +++++");
	      String sql = "update member_referral set member_referral_status=?, close_comment=?, close_reasson=? where member_referral_id=?";

	      Connection conn = null;

	      try {
	          conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql);

	          ps.setString(1, mr.getReferralStatus());
	          ps.setString(2, mr.getCloseComment());
	          ps.setString(3, mr.getCloseReason());
	          ps.setInt(4, mr.getMemberReferralId());

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

}
