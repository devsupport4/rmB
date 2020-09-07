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

import com.ui.dao.CountryDAO;
import com.ui.model.ClubInfo;
import com.ui.model.Country;
import com.ui.model.State;

public class CountryDAOImpl implements CountryDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	CountryDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(CountryDAOImpl.class);

	public List<Country> getAllCountry() {
		logger.info("Inside Get All Country Impl");
		List<Country> Country = new ArrayList<Country>();
		String s = "y";
		String sql = "select country_id, country_name, country_code from country where status=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Country country = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
						rs.getString("country_code"));
				Country.add(country);
			}
			rs.close();
			ps.close();

			return Country;
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

	public Country getCountryDetailById(int countryid) {
		logger.info("+++++ GET STATE DETAIL BY ID +++++");		
		String sql = "select country_id, country_name, country_code from country where country_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, countryid);
			Country country = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				country = new Country(rs.getInt("country_id"), rs.getString("country_name"),
						rs.getString("country_code"));				
			}
			rs.close();
			ps.close();
			return country;
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
	public List<State> getRelatedState(long countryId) {
		logger.info("Inside Get Related State Impl");

		List<State> State = new ArrayList<State>();

		String s = "y";

		String sql = "select state_id, state_name, country_id from state where status=? and country_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setLong(2, countryId);

			State state = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				state = new State(rs.getInt("state_id"), rs.getString("state_name"), rs.getInt("country_id"));

				State.add(state);
			}
			rs.close();
			ps.close();

			return State;
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

	public ClubInfo getClubInfo() {
		logger.info("+++++ GET CLUB INFO IMPL +++++");
		ClubInfo clubinfo = null;
		String sql = "select club_info_id, club_title, club_short_title, club_logo, club_no, district_no, zone_no, meeting_address1, meeting_address2, meeting_day, meeting_time, map_link, contact_person_name, contact_email, contact_telephone_no, contact_mobile_no, contact_address from club_info";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clubinfo = new ClubInfo(rs.getInt("club_info_id"), rs.getString("club_title"),
						rs.getString("club_short_title"), rs.getString("club_logo"), rs.getString("club_no"),
						rs.getString("district_no"), rs.getString("zone_no"), rs.getString("meeting_address1"),
						rs.getString("meeting_address2"), rs.getString("meeting_day"), rs.getString("meeting_time"),
						rs.getString("map_link"), rs.getString("contact_person_name"), rs.getString("contact_email"),
						rs.getString("contact_telephone_no"), rs.getString("contact_mobile_no"),
						rs.getString("contact_address"));				
			}
			rs.close();
			ps.close();
			return clubinfo;
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
	
	/*public int getLastClubInfoId() {
		logger.info("********** INSIDE GET LAST CLUB INFO ID IMPL **********");
		int id = 0;
		
		String sql = "select club_info_id from club_info order by club_info_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("club_info_id");
			}
			rs.close();
			ps.close();
			return id;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
	} */

	public void updateClubInfo(ClubInfo c) {
		logger.info("********** INSIDE UPDATE CLUB INFO IMPL **********");
		
		/*int clubinfoid = 0;
						
		String sql = null;
		try
        { 
			
			System.out.println("-----------Inside Try-------");
			
			clubinfoid = dao.getLastClubInfoId();
			if(clubinfoid !=0) {
				System.out.println("last Club id if cond-------"+clubinfoid);
				sql = "update club_info set club_title=?, club_short_title=?, club_logo=?, club_no=?, district_no=?, zone_no=?, meeting_address1=?, meeting_address2=?, meeting_day=?, meeting_time=?, map_link=?, contact_person_name=?, contact_email=?, contact_telephone_no=?, contact_mobile_no=?, contact_address=?, created_by=?, ip_address=?  where club_info_id=1";
			}
        } 
        catch(NullPointerException e) 
        { 
        	System.out.println("-----------Inside Catch-------");
        	clubinfoid = 0;
        	sql = "insert into club_info (club_title, club_short_title, club_logo, club_no, district_no, zone_no, meeting_address1, meeting_address2, meeting_day, meeting_time, map_link, contact_person_name, contact_email, contact_telephone_no, contact_mobile_no, contact_address, created_by, ip_address) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }*/
		
		/*if(clubinfoid==0) {
			sql = "insert into club_info (club_title, club_short_title, club_logo, club_no, district_no, zone_no, meeting_address1, meeting_address2, meeting_day, meeting_time, map_link, contact_person_name, contact_email, contact_telephone_no, contact_mobile_no, contact_address, created_by, ip_address) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		
		else {
			sql = "update club_info set club_title=?, club_short_title=?, club_logo=?, club_no=?, district_no=?, zone_no=?, meeting_address1=?, meeting_address2=?, meeting_day=?, meeting_time=?, map_link=?, contact_person_name=?, contact_email=?, contact_telephone_no=?, contact_mobile_no=?, contact_address=?, created_by=?, ip_address=?  where club_info_id=1";
		}*/
		
		String sql = "update club_info set club_title=?, club_short_title=?, club_logo=?, club_no=?, district_no=?, zone_no=?, meeting_address1=?, meeting_address2=?, meeting_day=?, meeting_time=?, map_link=?, contact_person_name=?, contact_email=?, contact_telephone_no=?, contact_mobile_no=?, contact_address=?, created_by=?, ip_address=?  where club_info_id=1";
		

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getClubTitle());
			ps.setString(2, c.getClubShortitle());
			ps.setString(3, c.getClubLogo());
			ps.setString(4, c.getClubNo());
			ps.setString(5, c.getDistrictNo());
			ps.setString(6, c.getZoneNo());
			ps.setString(7, c.getMeetingAddress1());
			ps.setString(8, c.getMeetingAddress2());
			ps.setString(9, c.getMeetingDay());
			ps.setString(10, c.getMeetingTime());
			ps.setString(11, c.getMapLink());
			ps.setString(12, c.getContactPersonName());
			ps.setString(13, c.getContactEmail());
			ps.setString(14, c.getContactTelephoneNo());
			ps.setString(15, c.getContactMobileNo());
			ps.setString(16, c.getContactAddress());
			ps.setInt(17, c.getCreatedBy());
			ps.setString(18, c.getIpAddress());
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
	
	
	public void addClubInfo(ClubInfo c) {
		logger.info("********** INSIDE ADD CLUB INFO IMPL **********");
		
		
		String	sql = "insert into club_info (club_title, club_short_title, club_logo, club_no, district_no, zone_no, meeting_address1, meeting_address2, meeting_day, meeting_time, map_link, contact_person_name, contact_email, contact_telephone_no, contact_mobile_no, contact_address, created_by, ip_address) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, c.getClubTitle());
			ps.setString(2, c.getClubShortitle());
			ps.setString(3, c.getClubLogo());
			ps.setString(4, c.getClubNo());
			ps.setString(5, c.getDistrictNo());
			ps.setString(6, c.getZoneNo());
			ps.setString(7, c.getMeetingAddress1());
			ps.setString(8, c.getMeetingAddress2());
			ps.setString(9, c.getMeetingDay());
			ps.setString(10, c.getMeetingTime());
			ps.setString(11, c.getMapLink());
			ps.setString(12, c.getContactPersonName());
			ps.setString(13, c.getContactEmail());
			ps.setString(14, c.getContactTelephoneNo());
			ps.setString(15, c.getContactMobileNo());
			ps.setString(16, c.getContactAddress());
			ps.setInt(17, c.getCreatedBy());
			ps.setString(18, c.getIpAddress());
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

}
