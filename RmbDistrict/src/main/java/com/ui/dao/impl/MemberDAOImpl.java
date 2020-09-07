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

import com.ui.dao.MemberDAO;
import com.ui.model.MemberCategoryByYear;
import com.ui.model.MemberLandlinePhoneNumber;
import com.ui.model.Members;
import com.ui.model.MembersFamily;

public class MemberDAOImpl implements MemberDAO {

  
	@Autowired
	private DataSource dataSource;
	Connection conn = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);

	public List<Members> getAllMembers() {
		logger.info("Inside Get All Members Impl");
		List<Members> Members = new ArrayList<Members>();
		String s = "n";
		String sql = "select m.fellowship_id,m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name from members m left join business_category bc on m.business_category_id = bc.business_category_id where m.status!=? and m.member_type='RMBFB Member' order by m.member_first_name";
		// String sql = "select m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name from members m, business_category bc where m.status=? and m.business_category_id = bc.business_category_id order by m.member_first_name";
	
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getInt("user_type_id"),
						rs.getString("membership_number"), rs.getInt("club_id"), rs.getInt("member_category_id"),
						rs.getInt("member_type_id"), rs.getInt("business_category_id"), rs.getString("tenure_plan"),
						rs.getString("joining_date"), rs.getString("member_name_title"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getString("member_gender"),
						rs.getString("member_date_of_birth"), rs.getString("member_anniversary_date"),
						rs.getString("member_blood_group"), rs.getString("member_aadhar_number"),
						rs.getInt("member_country_id_citizenship"), rs.getString("member_passport_number"),
						rs.getString("member_pan_number"), rs.getString("member_profile_picture"),
						rs.getString("member_email"), rs.getString("member_address1"), rs.getString("member_address2"),
						rs.getString("member_address3"), rs.getInt("member_state_id"), rs.getString("member_city_name"),
						rs.getString("member_pincode"), rs.getString("member_mobile_number"),
						rs.getString("member_phone_number"), rs.getString("member_barcode"),
						rs.getString("member_qrcode"), rs.getString("company_logo"), rs.getString("member_occupation"),
						rs.getString("member_designation"), rs.getString("member_company_name"),
						rs.getString("member_business_nature"), rs.getString("member_company_mobile_number"),
						rs.getString("member_company_phone_number"), rs.getString("member_fax_number"),
						rs.getString("member_company_email"), rs.getString("member_website_name"),
						rs.getString("member_company_description"), rs.getString("member_company_keywords"),
						rs.getString("member_company_address1"), rs.getString("member_company_address2"),
						rs.getString("member_company_address3"), rs.getInt("member_company_state_id"),
						rs.getString("member_company_city_name"), rs.getString("member_company_pincode"),
						rs.getString("member_communication_address"), rs.getString("business_goals"),
						rs.getString("accomplishments"), rs.getString("interests"), rs.getString("networks"),
						rs.getString("skills"), rs.getString("ideal_referral"), rs.getString("top_product"),
						rs.getString("top_problem_solved"), rs.getInt("sequence"), rs.getString("status"),
						rs.getString("business_category_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	
	public void resetPassword(Members m) {
      logger.info("+++++ RESET PASSWORD IMPL +++++");
      String sql = "update members set member_password=?, ip_address=? where member_email=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, m.getMemberPassword());
          ps.setString(2, m.getIpAddress());
          ps.setString(3, m.getMemberEmail());
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

	
	
	public List<Members> getAllMembersDirectory() {
      logger.info("Inside Get All Members Directory");

      List<Members> Members = new ArrayList<Members>();

      String s = "y";

      String sql = "select member_id, member_first_name, member_last_name, member_profile_picture from members where status=? and member_type='RMBFB Member' order by member_first_name ";

      Connection conn = null;

      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);

          ps.setString(1, s);          

          Members members = null;

          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              members = new Members(rs.getInt("member_id"), rs.getString("member_first_name"), rs.getString("member_last_name"), rs.getString("member_profile_picture"));
              Members.add(members);
          }
          rs.close();
          ps.close();

          return Members;
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
	
	public void changePassword(Members m) {
      logger.info("+++++ CHANGE PASSWORD IMPL +++++");
      String sql = "update members set member_password=?, ip_address=? where member_id=?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setString(1, m.getMemberPassword());
          ps.setString(2, m.getIpAddress());
          ps.setInt(3, m.getMemberId());
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
  public Members checkCurrentPassword(int memberid, String password) {
      logger.info("+++++ CHECK CURRENT PASSWORD IMPL +++++");
      String sql = "select member_first_name from members where member_id = ? and member_password = ?";
      Connection conn = null;
      try {
          conn = dataSource.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql);
          ps.setInt(1, memberid);
          ps.setString(2, password);
          Members members = null;
          ResultSet rs = ps.executeQuery();
          if (rs.next()) {
              members = new Members();
              members.setMemberFirstName(rs.getString("member_first_name"));
          }
          rs.close();
          ps.close();
          return members;
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

	
	
	
	
	public List<Members> getLastEightMembersPics() {
		logger.info("+++++ GET LAST EIGHT MEMBER PICS +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select member_id, member_first_name, member_middle_name, member_last_name, member_profile_picture from members where status=? order by member_id desc limit 0,8";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getString("member_first_name"),
						rs.getString("member_middle_name"), rs.getString("member_last_name"),
						rs.getString("member_profile_picture"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getAllMemberAndMemberFamily() {
		logger.info("Inside Get All Members And Member's Family Impl");

		List<Members> Members = new ArrayList<Members>();

		String sql = "(select concat(member_id,'.','m') as id, member_first_name as first_name, member_last_name as last_name from members where status='y' order by member_first_name) union (select concat(members_family_id,'.','f') as id, member_family_first_name as first_name, member_family_last_name as last_name from members_family where member_family_type_of_relation = 'Spouse' and member_category_id != 0 order by member_family_first_name) order by first_name";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getAllBirthdays(String birthmonth) {
		logger.info("********** INSIDE GET ALL BIRTHDAYS **********");
		List<Members> Members = new ArrayList<Members>();
		String sql = "(select member_id as id, member_name_title as title, member_first_name as fname, member_last_name as lname, member_date_of_birth as dob, member_profile_picture  as profile_picture, member_company_name as company_name from members where status='y' and date_format(member_date_of_birth, '%m') = ?) union (select members_family_id as id, member_family_title_name as title, member_family_first_name as fname, member_family_last_name as lname, member_family_date_of_birth as dob, member_family_profile_picture as profile_picture, member_family_company_name as company_name from members_family where status='y' and date_format(member_family_date_of_birth, '%m') = ?) order by date_format(dob, '%m-%d')";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, birthmonth);
			ps.setString(2, birthmonth);

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getInt("id"), rs.getString("title"), rs.getString("fname"),
						rs.getString("lname"), rs.getString("dob"), rs.getString("profile_picture"),
						rs.getString("company_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getAllBirthdaysByDate(String currentdate) {
		logger.info("********** INSIDE GET ALL BIRTHDAYS BY DATE IMPL **********");
		List<Members> Members = new ArrayList<Members>();
		String sql = "(select member_id as id, member_name_title as title, member_first_name as fname, member_last_name as lname, member_date_of_birth as dob, member_profile_picture  as profile_picture, member_company_name as company_name from members where status='y' and date_format(member_date_of_birth, '%m-%d') >= ?) union (select members_family_id as id, member_family_title_name as title, member_family_first_name as fname, member_family_last_name as lname, member_family_date_of_birth as dob, member_family_profile_picture as profile_picture, member_family_company_name as company_name from members_family where status='y' and date_format(member_family_date_of_birth, '%m-%d') >= ?) order by date_format(dob, '%m-%d')";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, currentdate);
			ps.setString(2, currentdate);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("id"), rs.getString("title"), rs.getString("fname"),
						rs.getString("lname"), rs.getString("dob"), rs.getString("profile_picture"),
						rs.getString("company_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getFistFourBirthdaysByDate(String currentdate) {
		logger.info("+++++ GET FIRST FOUR BIRTHDAYS BY DAT +++++");
		List<Members> Members = new ArrayList<Members>();
		String sql = "(select member_id as id, member_name_title as title, member_first_name as fname, member_last_name as lname, member_date_of_birth as dob, member_profile_picture  as profile_picture, member_company_name as company_name from members where status='y' and date_format(member_date_of_birth, '%m-%d') >= ?) union (select members_family_id as id, member_family_title_name as title, member_family_first_name as fname, member_family_last_name as lname, member_family_date_of_birth as dob, member_family_profile_picture as profile_picture, member_family_company_name as company_name from members_family where status='y' and date_format(member_family_date_of_birth, '%m-%d') >= ?) order by date_format(dob, '%m-%d') limit 0,4";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, currentdate);
			ps.setString(2, currentdate);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("id"), rs.getString("title"), rs.getString("fname"),
						rs.getString("lname"), rs.getString("dob"), rs.getString("profile_picture"),
						rs.getString("company_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getAllAnniversaries(String anniversarymonth) {
		logger.info("********** INSIDE GET ALL ANNIVERSARIES **********");
		List<Members> Members = new ArrayList<Members>();
		String sql = "select m.member_name_title, m.member_first_name, m.member_last_name, m.member_anniversary_date, mf.member_family_title_name, mf.member_family_first_name, mf.member_family_last_name from members m, members_family mf where m.status='y' and date_format(m.member_anniversary_date, '%m') = ? and mf.member_family_type_of_relation = 'Spouse' and mf.status = 'y' and mf.member_id = m.member_id order by member_anniversary_date";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, anniversarymonth);

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getString("member_name_title"), rs.getString("member_first_name"),
						rs.getString("member_last_name"), rs.getString("member_anniversary_date"),
						rs.getString("member_family_title_name"), rs.getString("member_family_first_name"),
						rs.getString("member_family_last_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getAllAnniversariesByDate(String currentdate) {
		logger.info("********** INSIDE GET ALL ANNIVERSARIES BY DATE IMPL **********");
		List<Members> Members = new ArrayList<Members>();
		String sql = "select m.member_name_title, m.member_first_name, m.member_last_name, m.member_anniversary_date, mf.member_family_title_name, mf.member_family_first_name, mf.member_family_last_name from members m, members_family mf where m.status='y' and date_format(m.member_anniversary_date, '%m-%d') >= ? and mf.member_family_type_of_relation = 'Spouse' and mf.status = 'y' and mf.member_id = m.member_id order by member_anniversary_date";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, currentdate);

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getString("member_name_title"), rs.getString("member_first_name"),
						rs.getString("member_last_name"), rs.getString("member_anniversary_date"),
						rs.getString("member_family_title_name"), rs.getString("member_family_first_name"),
						rs.getString("member_family_last_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> getLastMemberSequenceByCategory() {
		logger.info("Inside Get Last Member Sequence By Category Impl");

		List<Members> Members = new ArrayList<Members>();

		String sql = "select MAX(sequence) as sequence from members";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getInt("sequence"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public void addMemberDetail(Members member) {
		logger.info("Inside Add Member Detail Impl");
		String sql = "insert into members(user_type_id, membership_number, club_id, member_category_id, member_type_id, member_type, business_category_id, tenure_plan, joining_date, member_name_title, member_first_name, member_middle_name, member_last_name, member_gender, member_date_of_birth, member_anniversary_date, member_blood_group, member_aadhar_number, member_country_id_citizenship, member_passport_number, member_pan_number, member_profile_picture, member_mobile_number, member_email, member_password, member_barcode, member_qrcode, sequence, vocation_id, status, created_by, ip_address,fellowship_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {		
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, member.getUserTypeId());
			ps.setString(2, member.getMembershipNumber());
			ps.setInt(3, member.getClubId());
			ps.setInt(4, member.getMemberCategoryId());
			ps.setInt(5, member.getMemberTypeId());
			ps.setString(6, member.getMemberType());
			ps.setInt(7, member.getBusinessCategoryId());
			ps.setString(8, member.getTenurePlan());
			ps.setString(9, member.getJoiningDate());
			ps.setString(10, member.getMemberNameTitle());
			ps.setString(11, member.getMemberFirstName());
			ps.setString(12, member.getMemberMiddleName());
			ps.setString(13, member.getMemberLastName());
			ps.setString(14, member.getMemberGender());
			ps.setString(15, member.getMemberDateOfBirth());
			ps.setString(16, member.getMemberAnniversaryDate());
			ps.setString(17, member.getMemberBloodGroup());
			ps.setString(18, member.getMemberAadharNumber());
			ps.setInt(19, member.getMemberCountryIdCitizenship());
			ps.setString(20, member.getMemberPassportNumber());
			ps.setString(21, member.getMemberPanNumber());
			ps.setString(22, member.getMemberProfilePicture());
			ps.setString(23, member.getMemberMobileNumber());
			ps.setString(24, member.getMemberEmail());
			ps.setString(25, member.getMemberPassword());
			ps.setString(26, member.getMemberBarcode());
			ps.setString(27, member.getMemberQrcode());
			ps.setInt(28, member.getSequence());
			ps.setInt(29, member.getVocationId());
			ps.setString(30, member.getStatus());
			ps.setInt(31, member.getCreatedBy());
			ps.setString(32, member.getIpAddress());
			ps.setInt(33, member.getFellowship_id());
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

	public int getLastMemberId() {
		logger.info("Inside Get Last Member Id Impl");

		int id = 0;

		String sql = "select member_id from members order by member_id desc limit 0,1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("member_id");
			}
			rs.close();
			ps.close();

			return id;
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

	public void addContactDetail(Members m) {
		logger.info("Inside Add Contact Detail Impl");
		System.out.println("Inside Add Contact Detail Impl");

		String query = "update members set member_address1=?, member_address2=?, member_address3=?, member_state_id=?, member_city_name=?, member_pincode=?, member_mobile_number=?, company_logo=?, member_occupation=?,member_designation=?,member_company_name=?,member_business_nature=?,member_company_mobile_number=?,member_fax_number=?,member_company_email=?,member_website_name=?, member_company_description = ?, member_company_keywords = ?, member_company_address1=?,member_company_address2=?,member_company_address3=?, member_company_state_id=?, member_company_city_name=?,member_company_pincode=?,member_communication_address=?, business_goals=?, accomplishments=?, interests=?, networks=?, skills=?, ideal_referral=?, top_product=?, top_problem_solved=?, ip_address=? where member_id=?";
				
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, m.getMemberAddress1());
			ps.setString(2, m.getMemberAddress2());
			ps.setString(3, m.getMemberAddress3());
			ps.setInt(4, m.getMemberStateId());
			ps.setString(5, m.getMemberCityName());
			ps.setString(6, m.getMemberPincode());
			ps.setString(7, m.getMemberMobileNumber());
			ps.setString(8, m.getCompanyLogo());
			ps.setString(9, m.getMemberOccupation());
			ps.setString(10, m.getMemberDesignation());
			ps.setString(11, m.getMemberCompanyName());
			ps.setString(12, m.getMemberBusinessNature());
			ps.setString(13, m.getMemberCompanyMobileNumber());
			ps.setString(14, m.getMemberFaxNumber());
			ps.setString(15, m.getMemberCompanyEmail());
			ps.setString(16, m.getMemberWebsiteName());
			ps.setString(17, m.getMemberCompanyDescription());
			ps.setString(18, m.getMemberCompanyKeywords());
			ps.setString(19, m.getMemberCompanyAddress1());
			ps.setString(20, m.getMemberCompanyAddress2());
			ps.setString(21, m.getMemberCompanyAddress3());
			ps.setInt(22, m.getMemberCompanyStateId());
			ps.setString(23, m.getMemberCompanyCityName());
			ps.setString(24, m.getMemberCompanyPincode());
			ps.setString(25, m.getMemberCommunicationAddress());
			ps.setString(26, m.getBusinessGoals());
			ps.setString(27, m.getAccomplishments());
			ps.setString(28, m.getInterests());
			ps.setString(29, m.getNetworks());
			ps.setString(30, m.getSkills());
			ps.setString(31, m.getIdealReferral());
			ps.setString(32, m.getTopProduct());
			ps.setString(33, m.getTopProblemSolved());
			ps.setString(34, m.getIpAddress());
			ps.setInt(35, m.getMemberId());

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

	public void addMemberLandlinePhoneNumber(MemberLandlinePhoneNumber memberlandlinephonenumber) {
		logger.info("Inside Add Member Landline Phone Number Impl");

		String sql = "insert into member_business(landline_phone_number,location,member_id,member_family_id, created_by, ip_address) values(?,?,?,?,?,?)";

		// Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, memberlandlinephonenumber.getLandlinePhoneNumber());
			ps.setString(2, memberlandlinephonenumber.getLocation());
			ps.setInt(3, memberlandlinephonenumber.getMemberId());
			ps.setInt(4, memberlandlinephonenumber.getMemberFamilyId());
			ps.setInt(5, memberlandlinephonenumber.getCreatedBy());
			ps.setString(6, memberlandlinephonenumber.getIpAddress());

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
	  public void addMemberWorkDetails(MemberLandlinePhoneNumber memberlandlinephonenumber) {
	  logger.info("Inside Add Member Work Details");

      String sql = "insert into member_business(landline_phone_number,location,member_id,member_family_id, created_by, ip_address, member_designation, member_company_name, member_company_mobile_number, member_company_email, member_company_address1, member_company_address2, member_company_address3, member_company_city_name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

      // Connection conn = null;

      try {
          conn = dataSource.getConnection();

          PreparedStatement ps = conn.prepareStatement(sql);
          
          ps.setString(1, memberlandlinephonenumber.getLandlinePhoneNumber());
          ps.setString(2, memberlandlinephonenumber.getLocation());
          ps.setInt(3, memberlandlinephonenumber.getMemberId());
          ps.setInt(4, memberlandlinephonenumber.getMemberFamilyId());
          ps.setInt(5, memberlandlinephonenumber.getCreatedBy());
          ps.setString(6, memberlandlinephonenumber.getIpAddress());
          ps.setString(7, memberlandlinephonenumber.getMemberDesignation());
          ps.setString(8, memberlandlinephonenumber.getMemberCompanyName());
          ps.setString(9, memberlandlinephonenumber.getMemberComapnyMobileNumber());
          ps.setString(10, memberlandlinephonenumber.getMemberComapnyEmail());
          ps.setString(11, memberlandlinephonenumber.getMemberComapnyAddress1());
          ps.setString(12, memberlandlinephonenumber.getMemberComapnyAddress2());
          ps.setString(13, memberlandlinephonenumber.getMemberComapnyAddress3());
          ps.setString(14, memberlandlinephonenumber.getMemberCompanyCity());

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

	public Members getMemberByMemberId(int memberid) {
		logger.info("+++++ GET MEMBER BY MEMBER ID +++++");
		Members members = null;
		String s = "y";
		String sql = "select m.member_first_name, m.member_last_name, m.member_id, m.member_profile_picture, m.member_email, m.member_mobile_number, m.member_type, bc.business_category_name, m.fellowship_id from members m, business_category bc where m.status=? and m.business_category_id = bc.business_category_id and m.member_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, memberid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getString("member_first_name"), rs.getString("member_last_name"),
						rs.getInt("member_id"), rs.getString("member_profile_picture"), rs.getString("member_email"),
						rs.getString("member_mobile_number"), rs.getString("business_category_name"), rs.getString("member_type"));

			}
			rs.close();
			ps.close();
			return members;
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

	public Members getMemberDetailByMemberId(int memberid) {
		logger.info("***** GET MEMBER DETAIL BY MEMBER ID *****");
		Members members = null;
		String s = "y";
		String sql = "select m.member_id, m.user_type_id, m.membership_number, m.vocation_id, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, DATE_FORMAT(m.joining_date, '%d/%m/%Y') as joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, DATE_FORMAT(m.member_date_of_birth, '%d/%m/%Y') as member_date_of_birth, DATE_FORMAT(m.member_anniversary_date, '%d/%m/%Y') as member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name, m.member_type, m.rotary_member_id, m.rmb_club_name, m.rmb_chap_name, c.club_name , m.fellowship_id from members m left join business_category bc on  m.business_category_id = bc.business_category_id left join club c on m.club_id=c.club_id where m.member_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			//ps.setString(1, s);
			ps.setInt(1, memberid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getInt("user_type_id"),
						rs.getString("membership_number"), rs.getInt("vocation_id"), rs.getInt("club_id"), rs.getInt("member_category_id"),
						rs.getInt("member_type_id"), rs.getInt("business_category_id"), rs.getString("tenure_plan"),
						rs.getString("joining_date"), rs.getString("member_name_title"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getString("member_gender"),
						rs.getString("member_date_of_birth"), rs.getString("member_anniversary_date"),
						rs.getString("member_blood_group"), rs.getString("member_aadhar_number"),
						rs.getInt("member_country_id_citizenship"), rs.getString("member_passport_number"),
						rs.getString("member_pan_number"), rs.getString("member_profile_picture"),
						rs.getString("member_email"), rs.getString("member_address1"), rs.getString("member_address2"),
						rs.getString("member_address3"), rs.getInt("member_state_id"), rs.getString("member_city_name"),
						rs.getString("member_pincode"), rs.getString("member_mobile_number"),
						rs.getString("member_phone_number"), rs.getString("member_barcode"),
						rs.getString("member_qrcode"), rs.getString("company_logo"), rs.getString("member_occupation"),
						rs.getString("member_designation"), rs.getString("member_company_name"),
						rs.getString("member_business_nature"), rs.getString("member_company_mobile_number"),
						rs.getString("member_company_phone_number"), rs.getString("member_fax_number"),
						rs.getString("member_company_email"), rs.getString("member_website_name"),
						rs.getString("member_company_description"), rs.getString("member_company_keywords"),
						rs.getString("member_company_address1"), rs.getString("member_company_address2"),
						rs.getString("member_company_address3"), rs.getInt("member_company_state_id"),
						rs.getString("member_company_city_name"), rs.getString("member_company_pincode"),
						rs.getString("member_communication_address"), rs.getString("business_goals"),
						rs.getString("accomplishments"), rs.getString("interests"), rs.getString("networks"),
						rs.getString("skills"), rs.getString("ideal_referral"), rs.getString("top_product"),
						rs.getString("top_problem_solved"), rs.getInt("sequence"), rs.getString("status"),
						rs.getString("business_category_name"), rs.getString("member_type"), rs.getString("rotary_member_id"), 
						rs.getString("rmb_club_name"), rs.getString("rmb_chap_name"), rs.getString("club_name"),rs.getInt("fellowship_id"));
			}
			rs.close();
			ps.close();
			return members;
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

	public List<MembersFamily> getSpouseSequence(int memberid) {
		logger.info("Inside Get Spouse Sequence Impl");

		List<MembersFamily> MembersFamily = new ArrayList<MembersFamily>();

		String sql = "select sequence from members_family where member_id=? and sequence != 0 order by members_family_id desc limit 0,1";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberid);

			MembersFamily membersfamily = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				membersfamily = new MembersFamily(rs.getInt("sequence"));
				MembersFamily.add(membersfamily);
			}
			rs.close();
			ps.close();

			return MembersFamily;
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

	public List<MembersFamily> getSpouseData(int memberid) {
		logger.info("Inside Get Spouse Data Impl");

		List<MembersFamily> MembersFamily = new ArrayList<MembersFamily>();

		String s = "y";

		String sql = "select members_family_id, membership_number, member_family_title_name, member_family_first_name, member_family_middle_name, member_family_last_name, member_family_gender, member_family_type_of_relation, member_family_date_of_birth, member_family_blood_group, member_family_aadhar_number, member_family_passport_number, member_family_pan_number, member_family_profile_picture, member_family_email, member_family_password, member_family_address1, member_family_address2, member_family_address3, member_family_state_id, member_family_city_name, member_family_pincode, member_family_mobile_number, member_family_phone_number, member_family_barcode, member_family_qrcode, member_family_occupation, member_family_designation, member_family_company_name, member_family_business_nature, member_family_company_mobile_number, member_family_company_phone_number, member_family_fax_number, member_family_company_email, member_family_website_name, member_family_company_address1, member_family_company_address1, member_family_company_address2, member_family_company_address3, member_family_company_state_id, member_family_company_city_name, member_family_company_pincode, member_family_communication_address, sequence, member_id, member_category_id, status from members_family where member_id=? and status=?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberid);
			ps.setString(2, s);

			MembersFamily membersfamily = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				membersfamily = new MembersFamily(rs.getInt("members_family_id"), rs.getString("membership_number"),
						rs.getString("member_family_title_name"), rs.getString("member_family_first_name"),
						rs.getString("member_family_middle_name"), rs.getString("member_family_last_name"),
						rs.getString("member_family_gender"), rs.getString("member_family_type_of_relation"),
						rs.getString("member_family_date_of_birth"), rs.getString("member_family_blood_group"),
						rs.getString("member_family_aadhar_number"), rs.getString("member_family_passport_number"),
						rs.getString("member_family_pan_number"), rs.getString("member_family_profile_picture"),
						rs.getString("member_family_email"), rs.getString("member_family_password"),
						rs.getString("member_family_address1"), rs.getString("member_family_address2"),
						rs.getString("member_family_address3"), rs.getInt("member_family_state_id"),
						rs.getString("member_family_city_name"), rs.getString("member_family_pincode"),
						rs.getString("member_family_mobile_number"), rs.getString("member_family_phone_number"),
						rs.getString("member_family_barcode"), rs.getString("member_family_qrcode"),
						rs.getString("member_family_occupation"), rs.getString("member_family_designation"),
						rs.getString("member_family_company_name"), rs.getString("member_family_business_nature"),
						rs.getString("member_family_company_mobile_number"),
						rs.getString("member_family_company_phone_number"), rs.getString("member_family_fax_number"),
						rs.getString("member_family_company_email"), rs.getString("member_family_website_name"),
						rs.getString("member_family_company_address1"), rs.getString("member_family_company_address2"),
						rs.getString("member_family_company_address3"), rs.getInt("member_family_company_state_id"),
						rs.getString("member_family_company_city_name"), rs.getString("member_family_company_pincode"),
						rs.getString("member_family_communication_address"), rs.getInt("sequence"),
						rs.getInt("member_id"), rs.getInt("member_category_id"), rs.getString("status"));
				MembersFamily.add(membersfamily);
			}
			rs.close();
			ps.close();

			return MembersFamily;
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

	public void addSpouseDetail(MembersFamily m) {
		logger.info("Inside Add Spouse Detail Impl");
		System.out.println("Inside Add Spouse Detail Impl");

		String sql = "insert into members_family(membership_number,member_family_title_name,member_family_first_name,member_family_middle_name,member_family_last_name,member_family_gender,member_family_type_of_relation,member_family_date_of_birth,member_family_blood_group,member_family_aadhar_number,member_family_passport_number,member_family_pan_number,member_family_profile_picture,member_family_email,member_family_password,member_family_address1,member_family_address2,member_family_address3, member_family_state_id, member_family_city_name,member_family_pincode,member_family_mobile_number,member_family_barcode,member_family_qrcode,member_family_occupation,member_family_designation,member_family_company_name,member_family_business_nature,member_family_company_mobile_number,member_family_fax_number,member_family_company_email,member_family_website_name,member_family_company_address1,member_family_company_address2,member_family_company_address3, member_family_company_state_id, member_family_company_city_name,member_family_company_pincode,member_family_communication_address,sequence,member_id, member_category_id, status, created_by, ip_address) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, m.getMembershipNumber());
			ps.setString(2, m.getMemberFamilyTitleName());
			ps.setString(3, m.getMemberFamilyFirstName());
			ps.setString(4, m.getMemberFamilyMiddleName());
			ps.setString(5, m.getMemberFamilyLastName());
			ps.setString(6, m.getMemberFamilyGender());
			ps.setString(7, m.getMemberFamilyTypeOfRelation());
			ps.setString(8, m.getMemberFamilyDateOfBirth());
			ps.setString(9, m.getMemberFamilyBloodGroup());
			ps.setString(10, m.getMemberFamilyAadharNumber());
			ps.setString(11, m.getMemberFamilyPassportNumber());
			ps.setString(12, m.getMemberFamilyPanNumber());
			ps.setString(13, m.getMemberFamilyProfilePicture());
			ps.setString(14, m.getMemberFamilyEmail());
			ps.setString(15, m.getMemberFamilyPassword());
			ps.setString(16, m.getMemberFamilyAddress1());
			ps.setString(17, m.getMemberFamilyAddress2());
			ps.setString(18, m.getMemberFamilyAddress3());
			ps.setInt(19, m.getMemberFamilyStateId());
			ps.setString(20, m.getMemberFamilyCityName());
			ps.setString(21, m.getMemberFamilyPincode());
			ps.setString(22, m.getMemberFamilyMobileNumber());
			ps.setString(23, m.getMemberFamilyBarcode());
			ps.setString(24, m.getMemberFamilyQrcode());
			ps.setString(25, m.getMemberFamilyOccupation());
			ps.setString(26, m.getMemberFamilyDesignation());
			ps.setString(27, m.getMemberFamilyCompanyName());
			ps.setString(28, m.getMemberFamilyBusinessNature());
			ps.setString(29, m.getMemberFamilyCompanyMobileNumber());
			ps.setString(30, m.getMemberFamilyFaxNumber());
			ps.setString(31, m.getMemberFamilyCompanyEmail());
			ps.setString(32, m.getMemberFamilyWebsiteName());
			ps.setString(33, m.getMemberFamilyCompanyAddress1());
			ps.setString(34, m.getMemberFamilyCompanyAddress2());
			ps.setString(35, m.getMemberFamilyCompanyAddress3());
			ps.setInt(36, m.getMemberFamilyCompanyStateId());
			ps.setString(37, m.getMemberFamilyCompanyCityName());
			ps.setString(38, m.getMemberFamilyCompanyPincode());
			ps.setString(39, m.getMemberFamilyCommunicationAddress());
			ps.setInt(40, m.getSequence());
			ps.setInt(41, m.getMemberId());
			ps.setInt(42, m.getMemberCategoryId());
			ps.setString(43, m.getStatus());
			ps.setInt(44, m.getCreatedBy());
			ps.setString(45, m.getIpAddress());

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

	public int getLastMembersFamilyId() {
		logger.info("Inside Get Last Members Family Id Impl");

		int id = 0;

		String sql = "select members_family_id from members_family order by members_family_id desc limit 0,1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("members_family_id");
			}
			rs.close();
			ps.close();

			return id;
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

	public List<MemberLandlinePhoneNumber> getFamilyResidentialLandline(int memberfamilyid) {
		logger.info("Inside Get Member Family Residential Landline Impl");

		List<MemberLandlinePhoneNumber> Members = new ArrayList<MemberLandlinePhoneNumber>();

		String sql = "select member_business_id, landline_phone_number, location, member_id, member_family_id from member_business where member_family_id=? and location = 'R'";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, memberfamilyid);

			MemberLandlinePhoneNumber memberBusiness = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			  
			  memberBusiness = new MemberLandlinePhoneNumber();
              
              memberBusiness.setMemberBusinessId(rs.getInt("member_business_id"));
              memberBusiness.setLandlinePhoneNumber(rs.getString("landline_phone_number"));
              memberBusiness.setLocation(rs.getString("location"));
              memberBusiness.setMemberId(rs.getInt("member_id"));
              memberBusiness.setMemberFamilyId(rs.getInt("member_family_id"));
                    
              Members.add(memberBusiness);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<MemberLandlinePhoneNumber> getFamilyWorkLandline(int memberfamilyid) {
		logger.info("Inside Get Member Family Work Landline Impl");

		List<MemberLandlinePhoneNumber> Members = new ArrayList<MemberLandlinePhoneNumber>();

		String sql = "select member_business_id, landline_phone_number, location, member_id, member_family_id from member_business where member_family_id=? and location = 'W'";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, memberfamilyid);

			MemberLandlinePhoneNumber memberBusiness = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			  
			  memberBusiness = new MemberLandlinePhoneNumber();
              
              memberBusiness.setMemberBusinessId(rs.getInt("member_business_id"));
              memberBusiness.setLandlinePhoneNumber(rs.getString("landline_phone_number"));
              memberBusiness.setLocation(rs.getString("location"));
              memberBusiness.setMemberId(rs.getInt("member_id"));
              memberBusiness.setMemberFamilyId(rs.getInt("member_family_id"));
                    
              Members.add(memberBusiness);
			  
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<MemberLandlinePhoneNumber> getMemberResidentialLandline(int memberid) {
		logger.info("Inside Get Member Residential Landline Impl");

		List<MemberLandlinePhoneNumber> Members = new ArrayList<MemberLandlinePhoneNumber>();

		String sql = "select member_business_id, landline_phone_number, location, member_id, member_family_id from member_business where member_id=? and location = 'R'";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, memberid);

			MemberLandlinePhoneNumber memberBusiness = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			  
			  memberBusiness = new MemberLandlinePhoneNumber();
              
              memberBusiness.setMemberBusinessId(rs.getInt("member_business_id"));
              memberBusiness.setLandlinePhoneNumber(rs.getString("landline_phone_number"));
              memberBusiness.setLocation(rs.getString("location"));
              memberBusiness.setMemberId(rs.getInt("member_id"));
              memberBusiness.setMemberFamilyId(rs.getInt("member_family_id"));
                    
              Members.add(memberBusiness);
			  
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<MemberLandlinePhoneNumber> getMemberWorkLandline(int memberid) {
		logger.info("Inside Get Member Work details Impl");

		List<MemberLandlinePhoneNumber> Members = new ArrayList<MemberLandlinePhoneNumber>();

		String sql = "select member_business_id, landline_phone_number, location, member_id, member_family_id, company_logo, member_occupation, member_designation, member_company_name, member_business_nature, member_company_mobile_number, member_company_phone_number, member_company_email, member_company_address1, member_company_address2, member_company_address3, member_company_state_id, member_company_city_name, member_company_pincode from member_business where member_id=? and location = 'W'";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, memberid);

			MemberLandlinePhoneNumber memberBusiness = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			  memberBusiness = new MemberLandlinePhoneNumber();
				
			  memberBusiness.setMemberBusinessId(rs.getInt("member_business_id"));
			  memberBusiness.setLandlinePhoneNumber(rs.getString("landline_phone_number"));
			  memberBusiness.setLocation(rs.getString("location"));
			  memberBusiness.setMemberId(rs.getInt("member_id"));
			  memberBusiness.setMemberFamilyId(rs.getInt("member_family_id"));
			  memberBusiness.setCompanyLogo(rs.getString("company_logo"));
			  memberBusiness.setMemberOccupation(rs.getString("member_occupation"));
			  memberBusiness.setMemberDesignation(rs.getString("member_designation"));
			  memberBusiness.setMemberCompanyName(rs.getString("member_company_name"));
			  memberBusiness.setMemberBusinessNature(rs.getString("member_business_nature"));
			  memberBusiness.setMemberComapnyMobileNumber(rs.getString("member_company_mobile_number"));
			  memberBusiness.setMemberComapnyPhoneNumber(rs.getString("member_company_phone_number"));
			  memberBusiness.setMemberComapnyEmail(rs.getString("member_company_email"));
			  memberBusiness.setMemberComapnyAddress1(rs.getString("member_company_address1"));
			  memberBusiness.setMemberComapnyAddress2(rs.getString("member_company_address2"));
			  memberBusiness.setMemberComapnyAddress3(rs.getString("member_company_address3"));
			  memberBusiness.setMemberCompanyStateId(rs.getInt("member_company_state_id"));
			  memberBusiness.setMemberCompanyCity(rs.getString("member_company_city_name"));
			  memberBusiness.setMemberCompanyPincode(rs.getString("member_company_pincode"));
			  
					
				Members.add(memberBusiness);
			}
			rs.close();
			ps.close();

			return Members;
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

	public void deleteFamilyResidentialLandline(int membersfamilyid) {
		logger.info("Inside Delete Family Residential Landline Implementation");

		String query = "delete from member_business where member_family_id=? and location = 'R'";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, membersfamilyid);

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

	public void deleteFamilyWorkLandline(int membersfamilyid) {
		logger.info("Inside Delete Family Work Landline Implementation");

		String query = "delete from member_business where member_family_id=? and location = 'W'";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, membersfamilyid);

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

	public void editSpouseDetail1(MembersFamily m) {
		logger.info("Inside Edit Spouse Detail1 Impl");

		String sql = "update members_family set membership_number=?, member_family_title_name=?, member_family_first_name=?, member_family_middle_name=? ,member_family_last_name=?, member_family_gender=?, member_family_type_of_relation=?, member_family_date_of_birth=?, member_family_blood_group=?, member_family_aadhar_number=?, member_family_passport_number=?, member_family_pan_number=?, member_family_profile_picture=?, member_family_email=?, member_family_password=?, member_family_address1=?, member_family_address2=?, member_family_address3=?, member_family_state_id=?, member_family_city_name=?, member_family_pincode=?, member_family_mobile_number=?, member_family_barcode=?, member_family_qrcode=?, member_family_occupation=?, member_family_designation=?, member_family_company_name=?, member_family_business_nature=?, member_family_company_mobile_number=?, member_family_fax_number=?, member_family_company_email=?, member_family_website_name=?, member_family_company_address1=?, member_family_company_address2=?, member_family_company_address3=?, member_family_company_state_id=?, member_family_company_city_name=?, member_family_company_pincode=?, member_family_communication_address=?, member_category_id=?, sequence=?, created_by=?, ip_address=? where members_family_id=?";

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, m.getMembershipNumber());
			ps.setString(2, m.getMemberFamilyTitleName());
			ps.setString(3, m.getMemberFamilyFirstName());
			ps.setString(4, m.getMemberFamilyMiddleName());
			ps.setString(5, m.getMemberFamilyLastName());
			ps.setString(6, m.getMemberFamilyGender());
			ps.setString(7, m.getMemberFamilyTypeOfRelation());
			ps.setString(8, m.getMemberFamilyDateOfBirth());
			ps.setString(9, m.getMemberFamilyBloodGroup());
			ps.setString(10, m.getMemberFamilyAadharNumber());
			ps.setString(11, m.getMemberFamilyPassportNumber());
			ps.setString(12, m.getMemberFamilyPanNumber());
			ps.setString(13, m.getMemberFamilyProfilePicture());
			ps.setString(14, m.getMemberFamilyEmail());
			ps.setString(15, m.getMemberFamilyPassword());
			ps.setString(16, m.getMemberFamilyAddress1());
			ps.setString(17, m.getMemberFamilyAddress2());
			ps.setString(18, m.getMemberFamilyAddress3());
			ps.setInt(19, m.getMemberFamilyStateId());
			ps.setString(20, m.getMemberFamilyCityName());
			ps.setString(21, m.getMemberFamilyPincode());
			ps.setString(22, m.getMemberFamilyMobileNumber());
			ps.setString(23, m.getMemberFamilyBarcode());
			ps.setString(24, m.getMemberFamilyQrcode());
			ps.setString(25, m.getMemberFamilyOccupation());
			ps.setString(26, m.getMemberFamilyDesignation());
			ps.setString(27, m.getMemberFamilyCompanyName());
			ps.setString(28, m.getMemberFamilyBusinessNature());
			ps.setString(29, m.getMemberFamilyCompanyMobileNumber());
			ps.setString(30, m.getMemberFamilyFaxNumber());
			ps.setString(31, m.getMemberFamilyCompanyEmail());
			ps.setString(32, m.getMemberFamilyWebsiteName());
			ps.setString(33, m.getMemberFamilyCompanyAddress1());
			ps.setString(34, m.getMemberFamilyCompanyAddress2());
			ps.setString(35, m.getMemberFamilyCompanyAddress3());
			ps.setInt(36, m.getMemberFamilyCompanyStateId());
			ps.setString(37, m.getMemberFamilyCompanyCityName());
			ps.setString(38, m.getMemberFamilyCompanyPincode());
			ps.setString(39, m.getMemberFamilyCommunicationAddress());
			ps.setInt(40, m.getMemberCategoryId());
			ps.setInt(41, m.getSequence());
			ps.setInt(42, m.getCreatedBy());
			ps.setString(43, m.getCreatedDate());
			ps.setString(44, m.getIpAddress());
			ps.setInt(45, m.getMembersFamilyId());

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

	public void editSpouseDetail(MembersFamily m) {
		logger.info("Inside Edit Spouse Detail Impl");
		System.out.println("Inside Edit Spouse Detail Impl");

		String sql = "update members_family set member_family_title_name=?, member_family_first_name=?, member_family_middle_name=? ,member_family_last_name=?, member_family_gender=?, member_family_type_of_relation=?, member_family_date_of_birth=?, member_family_blood_group=?, member_family_aadhar_number=?, member_family_passport_number=?, member_family_pan_number=?, member_family_profile_picture=?, member_family_email=?, member_family_password=?, member_family_address1=?, member_family_address2=?, member_family_address3=?, member_family_state_id=?, member_family_city_name=?, member_family_pincode=?, member_family_mobile_number=?, member_family_occupation=?, member_family_designation=?, member_family_company_name=?, member_family_business_nature=?, member_family_company_mobile_number=?, member_family_fax_number=?, member_family_company_email=?, member_family_website_name=?, member_family_company_address1=?, member_family_company_address2=?, member_family_company_address3=?, member_family_company_state_id=?, member_family_company_city_name=?, member_family_company_pincode=?, member_family_communication_address=?, member_category_id=?, created_by=?, ip_address=? where members_family_id=?";

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, m.getMemberFamilyTitleName());
			ps.setString(2, m.getMemberFamilyFirstName());
			ps.setString(3, m.getMemberFamilyMiddleName());
			ps.setString(4, m.getMemberFamilyLastName());
			ps.setString(5, m.getMemberFamilyGender());
			ps.setString(6, m.getMemberFamilyTypeOfRelation());
			ps.setString(7, m.getMemberFamilyDateOfBirth());
			ps.setString(8, m.getMemberFamilyBloodGroup());
			ps.setString(9, m.getMemberFamilyAadharNumber());
			ps.setString(10, m.getMemberFamilyPassportNumber());
			ps.setString(11, m.getMemberFamilyPanNumber());
			ps.setString(12, m.getMemberFamilyProfilePicture());
			ps.setString(13, m.getMemberFamilyEmail());
			ps.setString(14, m.getMemberFamilyPassword());
			ps.setString(15, m.getMemberFamilyAddress1());
			ps.setString(16, m.getMemberFamilyAddress2());
			ps.setString(17, m.getMemberFamilyAddress3());
			ps.setInt(18, m.getMemberFamilyStateId());
			ps.setString(19, m.getMemberFamilyCityName());
			ps.setString(20, m.getMemberFamilyPincode());
			ps.setString(21, m.getMemberFamilyMobileNumber());
			ps.setString(22, m.getMemberFamilyOccupation());
			ps.setString(23, m.getMemberFamilyDesignation());
			ps.setString(24, m.getMemberFamilyCompanyName());
			ps.setString(25, m.getMemberFamilyBusinessNature());
			ps.setString(26, m.getMemberFamilyCompanyMobileNumber());
			ps.setString(27, m.getMemberFamilyFaxNumber());
			ps.setString(28, m.getMemberFamilyCompanyEmail());
			ps.setString(29, m.getMemberFamilyWebsiteName());
			ps.setString(30, m.getMemberFamilyCompanyAddress1());
			ps.setString(31, m.getMemberFamilyCompanyAddress2());
			ps.setString(32, m.getMemberFamilyCompanyAddress3());
			ps.setInt(33, m.getMemberFamilyCompanyStateId());
			ps.setString(34, m.getMemberFamilyCompanyCityName());
			ps.setString(35, m.getMemberFamilyCompanyPincode());
			ps.setString(36, m.getMemberFamilyCommunicationAddress());
			ps.setInt(37, m.getMemberCategoryId());
			ps.setInt(38, m.getCreatedBy());
			ps.setString(39, m.getIpAddress());
			ps.setInt(40, m.getMembersFamilyId());

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

	//public List<Members> getMembersByPage(int pagesize, int startindex, int fellowship_id) {
	public List<Members> getMembersByPage(int pagesize, int startindex) {
		logger.info("+++++ GET MEMBERS BY PAGE +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
	//	String sql = "select  m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name,cc.club_name from members m left join business_category bc on m.business_category_id = bc.business_category_id left join club cc on m.club_id =cc.club_id where m.status=? and m.member_type='RMBFB Member' and m.fellowship_id =? order by m.member_first_name limit ?,?";
	
		//String sql = "select m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name,cc.club_name  from members m left join business_category bc on m.business_category_id = bc.business_category_id  left join club cc on m.club_id =cc.club_id  where m.status=? and m.member_type='RMBFB Member' order by m.member_first_name limit ?,?";
		
		String sql="select cc.club_name,m.member_id,m.fellowship_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status,bc.business_category_name ,ff.fellowship_name from members m left join business_category bc on m.business_category_id = bc.business_category_id left join club cc on m.club_id =cc.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.status=? and m.member_type='RMBFB Member' order by m.member_first_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
		//	ps.setInt(2, fellowship_id);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
		
			//ps.setInt(parameterIndex, x);
	
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getInt("user_type_id"),
						rs.getString("membership_number"), rs.getInt("club_id"), rs.getInt("member_category_id"),
						rs.getInt("member_type_id"), rs.getInt("business_category_id"), rs.getString("tenure_plan"),
						rs.getString("joining_date"), rs.getString("member_name_title"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getString("member_gender"),
						rs.getString("member_date_of_birth"), rs.getString("member_anniversary_date"),
						rs.getString("member_blood_group"), rs.getString("member_aadhar_number"),
						rs.getInt("member_country_id_citizenship"), rs.getString("member_passport_number"),
						rs.getString("member_pan_number"), rs.getString("member_profile_picture"),
						rs.getString("member_email"), rs.getString("member_address1"), rs.getString("member_address2"),
						rs.getString("member_address3"), rs.getInt("member_state_id"), rs.getString("member_city_name"),
						rs.getString("member_pincode"), rs.getString("member_mobile_number"),
						rs.getString("member_phone_number"), rs.getString("member_barcode"),
						rs.getString("member_qrcode"), rs.getString("company_logo"), rs.getString("member_occupation"),
						rs.getString("member_designation"), rs.getString("member_company_name"),
						rs.getString("member_business_nature"), rs.getString("member_company_mobile_number"),
						rs.getString("member_company_phone_number"), rs.getString("member_fax_number"),
						rs.getString("member_company_email"), rs.getString("member_website_name"),
						rs.getString("member_company_description"), rs.getString("member_company_keywords"),
						rs.getString("member_company_address1"), rs.getString("member_company_address2"),
						rs.getString("member_company_address3"), rs.getInt("member_company_state_id"),
						rs.getString("member_company_city_name"), rs.getString("member_company_pincode"),
						rs.getString("member_communication_address"), rs.getString("business_goals"),
						rs.getString("accomplishments"), rs.getString("interests"), rs.getString("networks"),
						rs.getString("skills"), rs.getString("ideal_referral"), rs.getString("top_product"),
						rs.getString("top_problem_solved"), rs.getInt("sequence"), rs.getString("status"),
						rs.getString("business_category_name"),rs.getString("club_name"),rs.getString("fellowship_name"),rs.getInt("fellowship_id"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
		} catch (SQLException e) {
			System.out.println("?????????????????????????????????"+e);
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

	public List<Members> getMembersByPage(int pagesize, int startindex, int fellowship_id) {
		logger.info("+++++ GET MEMBERS BY PAGE +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select  m.member_id,m.fellowship_id as fellowship_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name,cc.club_name , ff.fellowship_name from members m left join business_category bc on m.business_category_id = bc.business_category_id left join club cc on m.club_id =cc.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.status=? and m.member_type='RMBFB Member' and m.fellowship_id =? order by m.member_first_name limit ?,?";
	
		//String sql = "select m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status, bc.business_category_name,cc.club_name  from members m left join business_category bc on m.business_category_id = bc.business_category_id  left join club cc on m.club_id =cc.club_id  where m.status=? and m.member_type='RMBFB Member' order by m.member_first_name limit ?,?";
		
	//	String sql="select cc.club_name,m.member_id, m.user_type_id, m.membership_number, m.club_id, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, m.sequence, m.status,bc.business_category_name ,ff.fellowship_name from members m left join business_category bc on m.business_category_id = bc.business_category_id left join club cc on m.club_id =cc.club_id left join fellowship ff on ff.fellowship_id = m.fellowship_id where m.status=? and m.member_type='RMBFB Member' order by m.member_first_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, fellowship_id);
			ps.setInt(3, startindex);
			ps.setInt(4, pagesize);
		
			//ps.setInt(parameterIndex, x);
	
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getInt("user_type_id"),
						rs.getString("membership_number"), rs.getInt("club_id"), rs.getInt("member_category_id"),
						rs.getInt("member_type_id"), rs.getInt("business_category_id"), rs.getString("tenure_plan"),
						rs.getString("joining_date"), rs.getString("member_name_title"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getString("member_gender"),
						rs.getString("member_date_of_birth"), rs.getString("member_anniversary_date"),
						rs.getString("member_blood_group"), rs.getString("member_aadhar_number"),
						rs.getInt("member_country_id_citizenship"), rs.getString("member_passport_number"),
						rs.getString("member_pan_number"), rs.getString("member_profile_picture"),
						rs.getString("member_email"), rs.getString("member_address1"), rs.getString("member_address2"),
						rs.getString("member_address3"), rs.getInt("member_state_id"), rs.getString("member_city_name"),
						rs.getString("member_pincode"), rs.getString("member_mobile_number"),
						rs.getString("member_phone_number"), rs.getString("member_barcode"),
						rs.getString("member_qrcode"), rs.getString("company_logo"), rs.getString("member_occupation"),
						rs.getString("member_designation"), rs.getString("member_company_name"),
						rs.getString("member_business_nature"), rs.getString("member_company_mobile_number"),
						rs.getString("member_company_phone_number"), rs.getString("member_fax_number"),
						rs.getString("member_company_email"), rs.getString("member_website_name"),
						rs.getString("member_company_description"), rs.getString("member_company_keywords"),
						rs.getString("member_company_address1"), rs.getString("member_company_address2"),
						rs.getString("member_company_address3"), rs.getInt("member_company_state_id"),
						rs.getString("member_company_city_name"), rs.getString("member_company_pincode"),
						rs.getString("member_communication_address"), rs.getString("business_goals"),
						rs.getString("accomplishments"), rs.getString("interests"), rs.getString("networks"),
						rs.getString("skills"), rs.getString("ideal_referral"), rs.getString("top_product"),
						rs.getString("top_problem_solved"), rs.getInt("sequence"), rs.getString("status"),
						rs.getString("business_category_name"),rs.getString("club_name"),rs.getString("fellowship_name"),rs.getInt("fellowship_id") );
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
		} catch (SQLException e) {
			System.out.println("?????????????????????????????????"+e);
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

	public List<Members> getMemberForMembersDirectoryByPage(int pagesize, int startindex) {
		logger.info("+++++ GET MEMBERS FRO MEMBER DIRECTORY BY PAGE +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select m.member_first_name, m.member_last_name, m.member_id, m.member_profile_picture, m.member_email, m.member_mobile_number, m.member_type, bc.business_category_name from members m, business_category bc where m.status=? and m.business_category_id = bc.business_category_id and m.member_type='RMBFB Member' order by m.member_first_name limit ?,?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getString("member_first_name"), rs.getString("member_last_name"),
						rs.getInt("member_id"), rs.getString("member_profile_picture"), rs.getString("member_email"),
						rs.getString("member_mobile_number"), rs.getString("business_category_name"),rs.getString("member_type"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public void deleteMember(int memberid) {
		logger.info("Inside Delete Member impl");

		String status = "n";

		String sql = "update members set status=? where member_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, memberid);

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

	public void editMemberDetail1(Members member) {
		logger.info("+++++ EDIT MEMBER DETAIL +++++");
		String sql = "update members set membership_number=?, club_id=?, member_category_id=?, member_type_id=?, member_type=?, business_category_id=?, tenure_plan=?, joining_date = STR_TO_DATE(?,'%d/%m/%Y'), member_name_title=?, member_first_name=?, member_middle_name=?, member_last_name=?, member_gender=?, member_date_of_birth=STR_TO_DATE(?,'%d/%m/%Y'), member_anniversary_date=STR_TO_DATE(?,'%d/%m/%Y'), member_blood_group=?, member_aadhar_number=?, member_country_id_citizenship=?, member_passport_number=?, member_pan_number=?, member_profile_picture=?, member_email=?, member_barcode=?, member_qrcode=?, sequence=?, vocation_id=?, ip_address=?,fellowship_id=? where member_id=?";
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, member.getMembershipNumber());
			ps.setInt(2, member.getClubId());
			ps.setInt(3, member.getMemberCategoryId());
			ps.setInt(4, member.getMemberTypeId());
			ps.setString(5, member.getMemberType());
			ps.setInt(6, member.getBusinessCategoryId());
			ps.setString(7, member.getTenurePlan());
			ps.setString(8, member.getJoiningDate());
			ps.setString(9, member.getMemberNameTitle());
			ps.setString(10, member.getMemberFirstName());
			ps.setString(11, member.getMemberMiddleName());
			ps.setString(12, member.getMemberLastName());
			ps.setString(13, member.getMemberGender());
			ps.setString(14, member.getMemberDateOfBirth());
			ps.setString(15, member.getMemberAnniversaryDate());
			ps.setString(16, member.getMemberBloodGroup());
			ps.setString(17, member.getMemberAadharNumber());
			ps.setInt(18, member.getMemberCountryIdCitizenship());
			ps.setString(19, member.getMemberPassportNumber());
			ps.setString(20, member.getMemberPanNumber());
			ps.setString(21, member.getMemberProfilePicture());
			ps.setString(22, member.getMemberEmail());
			ps.setString(23, member.getMemberBarcode());
			ps.setString(24, member.getMemberQrcode());
			ps.setInt(25, member.getSequence());
			ps.setInt(26, member.getVocationId());
			ps.setString(27, member.getIpAddress());
			ps.setInt(28, member.getFellowship_id());
			System.out.println("///////////////////////DaoImpl/////////////"+member.getFellowship_id());
			ps.setInt(29, member.getMemberId());
	
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

	
	public void editmemberpersonaldetail1(Members member) {
      logger.info("+++++ EDIT MEMBER DETAIL +++++");
      String sql = "update members set member_name_title=?, member_first_name=?, member_middle_name=?, member_last_name=?, member_gender=?, member_date_of_birth=STR_TO_DATE(?,'%d/%m/%Y'), member_anniversary_date=STR_TO_DATE(?,'%d/%m/%Y'), member_blood_group=?, member_aadhar_number=?, member_country_id_citizenship=?, member_passport_number=?, member_pan_number=?, member_email=?, sequence=?, ip_address=? where member_id=?";
      try {
          conn = dataSource.getConnection();

          PreparedStatement ps = conn.prepareStatement(sql);

          
          ps.setString(1, member.getMemberNameTitle());
          ps.setString(2, member.getMemberFirstName());
          ps.setString(3, member.getMemberMiddleName());
          ps.setString(4, member.getMemberLastName());
          ps.setString(5, member.getMemberGender());
          ps.setString(6, member.getMemberDateOfBirth());
          ps.setString(7, member.getMemberAnniversaryDate());
          ps.setString(8, member.getMemberBloodGroup());
          ps.setString(9, member.getMemberAadharNumber());
          ps.setInt(10, member.getMemberCountryIdCitizenship());
          ps.setString(11, member.getMemberPassportNumber());
          ps.setString(12, member.getMemberPanNumber());          
          ps.setString(13, member.getMemberEmail());        
          ps.setInt(14, member.getSequence());
          ps.setString(15, member.getIpAddress());
          ps.setInt(16, member.getMemberId());
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
	
	public void deleteMemberResidentialLandline(int memberid) {
		logger.info("++++++++++ INSIDE DELETE MEMBER RESIDENTIAL LANDLINE NUMBER IMPL ++++++++++");

		String query = "delete from member_business where member_id=? and location = 'R'";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, memberid);
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

	public void deleteMemberWorkLandline(int memberid) {
		logger.info("++++++++++ INSIDE DELETE MEMBER WORK LANDLINE NUMBER IMPL ++++++++++");

		String query = "delete from member_business where member_id=? and location = 'W'";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, memberid);
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

	public void deleteFamilyMember(int membersfamilyid) {
		logger.info("++++++++++ INSIDE DELETE FAMILY MEMBER IMPL ++++++++++");
		String status = "n";
		String sql = "update members_family set status=? where members_family_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, membersfamilyid);

			ps.executeUpdate();
		}

		catch (SQLException e) {
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

	public List<Members> searchMembers(String keyword) {
		logger.info("+++++ SEARCH MEMBERS +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "n";
		String sql = "select m.member_id, m.user_type_id, m.membership_number, m.member_category_id, m.member_type_id, m.business_category_id, m.tenure_plan, m.joining_date, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, m.member_gender, m.member_date_of_birth, m.member_anniversary_date, m.member_blood_group, m.member_aadhar_number, m.member_country_id_citizenship, m.member_passport_number, m.member_pan_number, m.member_profile_picture, m.member_email, m.member_password, m.member_address1, m.member_address2, m.member_address3, m.member_state_id, m.member_city_name, m.member_pincode, m.member_mobile_number, m.member_phone_number, m.member_barcode, m.member_qrcode, m.company_logo, m.member_occupation, m.member_designation, m.member_company_name, m.member_business_nature, m.member_company_mobile_number, m.member_company_phone_number, m.member_fax_number, m.member_company_email, m.member_website_name, m.member_company_description, m.member_company_keywords, m.member_company_address1, m.member_company_address2, m.member_company_address3, m.member_company_state_id, m.member_company_city_name, m.member_company_pincode, m.member_communication_address, m.business_goals, m.accomplishments, m.interests, m.networks, m.skills, m.ideal_referral, m.top_product, m.top_problem_solved, bc.business_category_name from members m left join business_category bc on m.business_category_id = bc.business_category_id where m.status!=? and m.member_type='RMBFB Member' and  Concat(m.member_first_name, '', m.member_last_name, '', m.member_email, '', m.membership_number, '', bc.business_category_name) like ?";

		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getInt("user_type_id"),
						rs.getString("membership_number"), rs.getInt("member_category_id"), rs.getInt("member_type_id"),
						rs.getInt("business_category_id"), rs.getString("tenure_plan"), rs.getString("joining_date"),
						rs.getString("member_name_title"), rs.getString("member_first_name"),
						rs.getString("member_middle_name"), rs.getString("member_last_name"),
						rs.getString("member_gender"), rs.getString("member_date_of_birth"),
						rs.getString("member_anniversary_date"), rs.getString("member_blood_group"),
						rs.getString("member_aadhar_number"), rs.getInt("member_country_id_citizenship"),
						rs.getString("member_passport_number"), rs.getString("member_pan_number"),
						rs.getString("member_profile_picture"), rs.getString("member_email"),
						rs.getString("member_password"), rs.getString("member_address1"),
						rs.getString("member_address2"), rs.getString("member_address3"), rs.getInt("member_state_id"),
						rs.getString("member_city_name"), rs.getString("member_pincode"),
						rs.getString("member_mobile_number"), rs.getString("member_phone_number"),
						rs.getString("member_barcode"), rs.getString("member_qrcode"), rs.getString("company_logo"),
						rs.getString("member_occupation"), rs.getString("member_designation"),
						rs.getString("member_company_name"), rs.getString("member_business_nature"),
						rs.getString("member_company_mobile_number"), rs.getString("member_company_phone_number"),
						rs.getString("member_fax_number"), rs.getString("member_company_email"),
						rs.getString("member_website_name"), rs.getString("member_company_description"),
						rs.getString("member_company_keywords"), rs.getString("member_company_address1"),
						rs.getString("member_company_address2"), rs.getString("member_company_address3"),
						rs.getInt("member_company_state_id"), rs.getString("member_company_city_name"),
						rs.getString("member_company_pincode"), rs.getString("member_communication_address"),
						rs.getString("business_goals"), rs.getString("accomplishments"), rs.getString("interests"),
						rs.getString("networks"), rs.getString("skills"), rs.getString("ideal_referral"),
						rs.getString("top_product"), rs.getString("top_problem_solved"),
						rs.getString("business_category_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public List<Members> searchMembersForMembersDirectory(String keyword) {
		logger.info("+++++ SEARCH MEMBERS FOR MEMBERS DIRECTORY +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select m.member_first_name, m.member_last_name, m.member_id, m.member_profile_picture, m.member_email, m.member_mobile_number, m.member_type, bc.business_category_name from members m, business_category bc where m.status=? and m.business_category_id = bc.business_category_id and m.member_type = 'RMBFB Member' and concat(m.member_first_name, '', m.member_last_name, '', m.member_email, '', m.membership_number, '', bc.business_category_name, '',m.member_company_keywords) like ? order by m.member_first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, '%' + keyword + '%');
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getString("member_first_name"), rs.getString("member_last_name"),
						rs.getInt("member_id"), rs.getString("member_profile_picture"), rs.getString("member_email"),
						rs.getString("member_mobile_number"), rs.getString("business_category_name"),rs.getString("member_type"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public void addMemberCategoryByRotaryYear(MemberCategoryByYear mcby) {
		logger.info("+++++ ADD MEMBER CATEGORY BY ROTARY YEAR +++++");
		String sql = "insert into member_category_by_year(rotary_year_id, member_id, member_category_id, created_by, ip_address) values(?,?,?,?,?)";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, mcby.getRotaryYearId());
			ps.setInt(2, mcby.getMemberId());
			ps.setInt(3, mcby.getMemberCategoryId());
			ps.setInt(4, mcby.getCreatedBy());
			ps.setString(5, mcby.getIpAddress());

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

	public MemberCategoryByYear getMemberCurrentCategoryId(int memberid, int rotaryyearid) {
		logger.info("+++++ GET MEMBER CURRENT CATEGORY +++++");
		MemberCategoryByYear memberCategoryByYear = null;
		String sql = "select member_category_id from member_category_by_year where member_id = ? and rotary_year_id = ? order by member_category_by_year_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberid);
			ps.setInt(2, rotaryyearid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				memberCategoryByYear = new MemberCategoryByYear(rs.getInt("member_category_id"));
			}
			rs.close();
			ps.close();

			return memberCategoryByYear;
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

	public List<Members> getChapterSummaryByDate(String fromdate, String todate) {
		logger.info("+++++ GET CHAPTER SUMMARY BY DATE +++++");
		List<Members> Members = new ArrayList<Members>();
		//String sql = "select q1.member_id, q1.member_first_name, q1.member_middle_name, q1.member_last_name, q1.reference_given_inside, q2.reference_given_outside, q3.reference_received_inside,  q4.reference_received_outside, q5.member_meeting_count, q6.member_business_transaction_count from (select m.member_id, m.member_first_name, m.member_middle_name, m.member_last_name, count(mr.member_referral_id) as reference_given_inside from members m left join member_referral mr on m.member_id = mr.created_by and mr.referral_type='Inside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q1, (select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_given_outside from members m left join member_referral mr on m.member_id = mr.created_by and mr.referral_type='Outside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q2, (select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_received_inside from members m left join member_referral mr on m.member_id = mr.to_member_id and mr.referral_type='Inside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q3, (select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_received_outside from members m left join member_referral mr on m.member_id = mr.to_member_id and mr.referral_type='Outside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q4, (select m.member_id, m.member_first_name, m.member_last_name, count(o.one_to_one_id) as member_meeting_count from members m left join member_one_to_one o on m.member_id = o.created_by and o.meeting_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q5, (select m.member_id, m.member_first_name, m.member_last_name, IFNULL(sum(t.amount), 0) as member_business_transaction_count from members m left join member_thank_you_slip t on m.member_id = t.to_member_id and t.slip_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q6 where q1.member_id = q2.member_id and q1.member_id = q3.member_id and q1.member_id = q4.member_id and q1.member_id = q5.member_id and q1.member_id = q6.member_id order by q1.member_first_name";
		String sql = "select q1.member_id, q1.member_first_name, q1.member_middle_name, q1.member_last_name, q1.reference_given_inside, q2.reference_given_outside, q3.reference_received_inside,  q4.reference_received_outside, q5.member_meeting_count, q6.member_business_transaction_count from(select m.member_id, m.member_first_name, m.member_middle_name, m.member_last_name, count(mr.member_referral_id) as reference_given_inside from members m left join member_referral mr on m.member_id = mr.created_by and mr.referral_type='Inside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') and member_type='RMBFB Member' group by m.member_id) as q1,(select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_given_outside from members m left join member_referral mr on m.member_id = mr.created_by and mr.referral_type='Outside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q2,(select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_received_inside from members m left join member_referral mr on m.member_id = mr.to_member_id and mr.referral_type='Inside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q3,(select m.member_id, m.member_first_name, m.member_last_name, count(mr.member_referral_id) as reference_received_outside from members m left join member_referral mr on m.member_id = mr.to_member_id and mr.referral_type='Outside' and mr.refer_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q4,(select m.member_id, m.member_first_name, m.member_last_name, count(o.one_to_one_id) as member_meeting_count from members m left join member_one_to_one o on m.member_id = o.created_by and o.meeting_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q5,(select m.member_id, m.member_first_name, m.member_last_name, IFNULL(sum(t.amount), 0) as member_business_transaction_count from members m left join member_thank_you_slip t on m.member_id = t.to_member_id and t.slip_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') group by m.member_id) as q6 where q1.member_id = q2.member_id and q1.member_id = q3.member_id and q1.member_id = q4.member_id and q1.member_id = q5.member_id and q1.member_id = q6.member_id order by q1.member_first_name";
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, fromdate);
			ps.setString(2, todate);
			ps.setString(3, fromdate);
			ps.setString(4, todate);
			ps.setString(5, fromdate);
			ps.setString(6, todate);
			ps.setString(7, fromdate);
			ps.setString(8, todate);
			ps.setString(9, fromdate);
			ps.setString(10, todate);
			ps.setString(11, fromdate);
			ps.setString(12, todate);

			Members members = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getString("member_first_name"),
						rs.getString("member_middle_name"), rs.getString("member_last_name"),
						rs.getInt("reference_given_inside"), rs.getInt("reference_given_outside"),
						rs.getInt("reference_received_inside"), rs.getInt("reference_received_outside"),
						rs.getInt("member_meeting_count"), rs.getInt("member_business_transaction_count"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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

	public MembersFamily getSpouseDetailById(int memberfamilyid) {
		logger.info("+++++ GET SPOUSE DETAIL BY ID +++++");
		MembersFamily membersfamily = null;
		String sql = "select members_family_id, membership_number, member_family_title_name, member_family_first_name, member_family_middle_name, member_family_last_name, member_family_gender, member_family_type_of_relation, member_family_date_of_birth, member_family_blood_group, member_family_aadhar_number, member_family_passport_number, member_family_pan_number, member_family_profile_picture, member_family_email, member_family_password, member_family_address1, member_family_address2, member_family_address3, member_family_state_id, member_family_city_name, member_family_pincode, member_family_mobile_number, member_family_phone_number, member_family_barcode, member_family_qrcode, member_family_occupation, member_family_designation, member_family_company_name, member_family_business_nature, member_family_company_mobile_number, member_family_company_phone_number, member_family_fax_number, member_family_company_email, member_family_website_name, member_family_company_address1, member_family_company_address1, member_family_company_address2, member_family_company_address3, member_family_company_state_id, member_family_company_city_name, member_family_company_pincode, member_family_communication_address, sequence, member_id, member_category_id, status from members_family where members_family_id=?";

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberfamilyid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				membersfamily = new MembersFamily(rs.getInt("members_family_id"), rs.getString("membership_number"),
						rs.getString("member_family_title_name"), rs.getString("member_family_first_name"),
						rs.getString("member_family_middle_name"), rs.getString("member_family_last_name"),
						rs.getString("member_family_gender"), rs.getString("member_family_type_of_relation"),
						rs.getString("member_family_date_of_birth"), rs.getString("member_family_blood_group"),
						rs.getString("member_family_aadhar_number"), rs.getString("member_family_passport_number"),
						rs.getString("member_family_pan_number"), rs.getString("member_family_profile_picture"),
						rs.getString("member_family_email"), rs.getString("member_family_password"),
						rs.getString("member_family_address1"), rs.getString("member_family_address2"),
						rs.getString("member_family_address3"), rs.getInt("member_family_state_id"),
						rs.getString("member_family_city_name"), rs.getString("member_family_pincode"),
						rs.getString("member_family_mobile_number"), rs.getString("member_family_phone_number"),
						rs.getString("member_family_barcode"), rs.getString("member_family_qrcode"),
						rs.getString("member_family_occupation"), rs.getString("member_family_designation"),
						rs.getString("member_family_company_name"), rs.getString("member_family_business_nature"),
						rs.getString("member_family_company_mobile_number"),
						rs.getString("member_family_company_phone_number"), rs.getString("member_family_fax_number"),
						rs.getString("member_family_company_email"), rs.getString("member_family_website_name"),
						rs.getString("member_family_company_address1"), rs.getString("member_family_company_address2"),
						rs.getString("member_family_company_address3"), rs.getInt("member_family_company_state_id"),
						rs.getString("member_family_company_city_name"), rs.getString("member_family_company_pincode"),
						rs.getString("member_family_communication_address"), rs.getInt("sequence"),
						rs.getInt("member_id"), rs.getInt("member_category_id"), rs.getString("status"));
			}
			rs.close();
			ps.close();

			return membersfamily;
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

	public List<Members> getMemberFullName() {
		logger.info("+++++ GET MEMBER FULL NAME +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select member_id, member_first_name, member_middle_name, member_last_name  from members where status=? order by member_first_name";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getString("member_first_name"),
						rs.getString("member_middle_name"), rs.getString("member_last_name"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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
	
	
	public void editContactDetailForMobile(Members m) {
      logger.info("Inside Add Contact Detail For Mobile Page Impl");
      System.out.println("Inside Add Contact Detail Impl");

      String query = "update members set member_address1=?, member_address2=?, member_address3=?, member_state_id=?, member_city_name=?, member_pincode=?, member_mobile_number=?, ip_address=? where member_id=?";

      try {
          conn = dataSource.getConnection();

          PreparedStatement ps = conn.prepareStatement(query);

          ps.setString(1, m.getMemberAddress1());
          ps.setString(2, m.getMemberAddress2());
          ps.setString(3, m.getMemberAddress3());
          ps.setInt(4, m.getMemberStateId());
          ps.setString(5, m.getMemberCityName());
          ps.setString(6, m.getMemberPincode());
          ps.setString(7, m.getMemberMobileNumber());          
          ps.setString(8, m.getIpAddress());
          ps.setInt(9, m.getMemberId());

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
	
	public void editWorkDetail(Members m) {
      logger.info("Inside Add Work Detail Impl");
      System.out.println("Inside Add Contact Detail Impl");

      String query = "update members set member_occupation=?,member_designation=?,member_company_name=?,member_business_nature=?,member_company_mobile_number=?,member_fax_number=?,member_company_email=?,member_website_name=?, member_company_description = ?, member_company_keywords = ?, member_company_address1=?,member_company_address2=?,member_company_address3=?, member_company_state_id=?, member_company_city_name=?,member_company_pincode=?,member_communication_address=?, business_goals=?, accomplishments=?, interests=?, networks=?, skills=?, ideal_referral=?, top_product=?, top_problem_solved=?, ip_address=? where member_id=?";

      try {
          conn = dataSource.getConnection();

          PreparedStatement ps = conn.prepareStatement(query);
                  
          ps.setString(1, m.getMemberOccupation());
          ps.setString(2, m.getMemberDesignation());
          ps.setString(3, m.getMemberCompanyName());
          ps.setString(4, m.getMemberBusinessNature());
          ps.setString(5, m.getMemberCompanyMobileNumber());
          ps.setString(6, m.getMemberFaxNumber());
          ps.setString(7, m.getMemberCompanyEmail());
          ps.setString(8, m.getMemberWebsiteName());
          ps.setString(9, m.getMemberCompanyDescription());
          ps.setString(10, m.getMemberCompanyKeywords());
          ps.setString(11, m.getMemberCompanyAddress1());
          ps.setString(12, m.getMemberCompanyAddress2());
          ps.setString(13, m.getMemberCompanyAddress3());
          ps.setInt(14, m.getMemberCompanyStateId());
          ps.setString(15, m.getMemberCompanyCityName());
          ps.setString(16, m.getMemberCompanyPincode());
          ps.setString(17, m.getMemberCommunicationAddress());
          ps.setString(18, m.getBusinessGoals());
          ps.setString(19, m.getAccomplishments());
          ps.setString(20, m.getInterests());
          ps.setString(21, m.getNetworks());
          ps.setString(22, m.getSkills());
          ps.setString(23, m.getIdealReferral());
          ps.setString(24, m.getTopProduct());
          ps.setString(25, m.getTopProblemSolved());
          ps.setString(26, m.getIpAddress());
          ps.setInt(27, m.getMemberId());

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
	
	public void editProfilePicture1(Members member) {
      logger.info("+++++ EDIT MEMBER PROFILE IMAGE +++++");
      String sql = "update members set member_profile_picture=?, ip_address=? where member_id=?";
      try {
          conn = dataSource.getConnection();

          PreparedStatement ps = conn.prepareStatement(sql);

         
          ps.setString(1, member.getMemberPassword());         
          ps.setString(2, member.getIpAddress());
          ps.setInt(3, member.getMemberId());
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
	public String checkemailaddress(String mobilenumber, String email) {
		logger.info("+++++ CHECK EMAIL AND MOBILE+++++");
		  String result = null;
	      String sql = "select * from members where status='y' and member_mobile_number=? OR member_email=?";
	      Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, mobilenumber);
				ps.setString(2, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = "Success";
				}
				rs.close();
				ps.close();

				return result;
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
	public String getMemberByEmailAndNumber(String mobilenumber, String email) {
		logger.info("+++++ CHECK INACTIVE EMAIL AND MOBILE +++++");
		  String result = null;
	      String sql = "select * from members where status!='n' and member_mobile_number=? OR member_email=?";
	      Connection conn = null;
			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, mobilenumber);
				ps.setString(2, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = "Success";
				}
				rs.close();
				ps.close();

				return result;
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
	
	
	public void setMemberActiveOrInA(Members m) {
	      logger.info("+++++ SET ACTIVE OR INACTIVE IMPL +++++");
	      String sql = "update members set status=?, ip_address=? where member_id=?";
	      Connection conn = null;
	      try {
	          conn = dataSource.getConnection();
	          PreparedStatement ps = conn.prepareStatement(sql);
	          ps.setString(1, m.getStatus());
	          ps.setString(2, m.getIpAddress());
	          ps.setInt(3, m.getMemberId());
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
	
	
	public List<Members> getAllMembersPics() {
		logger.info("+++++ GET ALL MEMBER PICS +++++");
		List<Members> Members = new ArrayList<Members>();
		String s = "y";
		String sql = "select member_id, member_first_name, member_middle_name, member_last_name, member_profile_picture from members where status=? and member_profile_picture!='' and member_type='RMBFB Member' order by member_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members(rs.getInt("member_id"), rs.getString("member_first_name"),
						rs.getString("member_middle_name"), rs.getString("member_last_name"),
						rs.getString("member_profile_picture"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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
	public List<Members> getAllMemberContactInfoForMailer() {
		logger.info("Inside Get All Members Contact Info for Mailer");
		List<Members> Members = new ArrayList<Members>();
		String sql = "Select member_id,membership_number,member_first_name,member_last_name,member_email,member_mobile_number,member_type,status from members";
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Members members = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				members = new Members();
				members.setMemberId(rs.getInt("member_id"));
				members.setMembershipNumber(rs.getString("membership_number"));
				members.setMemberFirstName(rs.getString("member_first_name"));
				members.setMemberLastName(rs.getString("member_last_name"));
				members.setMemberEmail(rs.getString("member_email"));
				members.setMemberMobileNumber(rs.getString("member_mobile_number"));
				members.setMemberType(rs.getString("member_type"));
				members.setStatus(rs.getString("status"));
				Members.add(members);
			}
			rs.close();
			ps.close();

			return Members;
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
	public void addVocation(String title, String desc, String ipAddress, int id) {
		logger.info("Inside Add Vocation Impl");
		String s="y";
		String sql = "insert into vocation_master(vocation_title,vocation_desc,status,created_by,ip_address) values(?,?,?,?,?)";

		// Connection conn = null;

		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, title);
			ps.setString(2, desc);
			ps.setString(3, s);
			ps.setInt(4, id);
			ps.setString(5, ipAddress);

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
	public List<Members> getallVocation() {
		logger.info("Inside GET ALL VOCATIONS");
		List<Members> mb = new ArrayList<Members>();
		String sql = "select vocation_id, vocation_title, vocation_desc from vocation_master order by vocation_title";

		Connection conn = null;
		Members m;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				m = new Members();
				m.setVocationId(rs.getInt("vocation_id"));
				m.setVocationTitle(rs.getString("vocation_title"));
				m.setVocationDesc(rs.getString("vocation_desc"));
				
				mb.add(m);
			}
			rs.close();
			ps.close();

			return mb;
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
	public void deleteVocation(int vocationid) {
		logger.info("Inside Delete Vocation");

		String query = "delete from vocation_master where vocation_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(query);

			ps.setInt(1, vocationid);

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
	public int getLastMemberByEmail(String email, String mobileno) {
			logger.info("Inside Get Last Member Id by email");

			int id = 0;

			String sql = "select member_id from members where member_email=? and member_mobile_number=? order by member_id desc limit 0,1";

			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, email);
				ps.setString(2, mobileno);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					id = rs.getInt("member_id");
				}
				rs.close();
				ps.close();

				return id;
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
