package com.ui.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ui.dao.EventDAO;
import com.ui.model.Event;
import com.ui.model.EventAgenda;
import com.ui.model.EventCharges;
import com.ui.model.EventImage;
import com.ui.model.EventRegistration;
import com.ui.model.EventType;
import com.ui.model.EventUrl;
import com.ui.model.MemberResponse;

public class EventDAOImpl implements EventDAO {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);
	
	 //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate now = LocalDate.now();
     String current_date = now.toString();

	public List<EventType> getAllEventType() {
		logger.info("********** Inside Get All EventTypes **********");
		List<EventType> EventType = new ArrayList<EventType>();
		String s = "y";
		String sql = "select event_type_id, event_type_title, event_type_description, status, created_by, created_date, created_time, ip_address from event_type where status=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			EventType eventtype = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				eventtype = new EventType(rs.getInt("event_type_id"), rs.getString("event_type_title"),
						rs.getString("event_type_description"), rs.getString("status"), rs.getInt("created_by"),
						rs.getString("created_date"), rs.getString("created_time"), rs.getString("ip_address"));

				EventType.add(eventtype);
			}
			rs.close();
			ps.close();

			return EventType;
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

	public List<Event> getAllEvents() {
		logger.info("********** Inside Get All Events **********");
		List<Event> Event = new ArrayList<Event>();
		String s = "y";
		String sql = "select event_id, event_type_id, event_title, event_subtitle, event_venue, event_map_location, event_date, event_time, event_description, registration, paid, rmbfb_member, rotarian, others, status, created_by, created_date, created_time, ip_address from event where status=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Event event = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getString("event_title"),
						rs.getString("event_subtitle"), rs.getString("event_venue"), rs.getString("event_map_location"),
						rs.getString("event_date"), rs.getString("event_time"), rs.getString("event_description"), rs.getString("registration"), rs.getString("paid"), 
						rs.getFloat("rmbfb_member"), rs.getFloat("rotarian"), rs.getFloat("others"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("created_time"), rs.getString("ip_address"));

				Event.add(event);
			}
			rs.close();
			ps.close();

			return Event;
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
	public List<EventRegistration> getAllEventRegistrationDetails(String eventtype) {
		logger.info("********** GET EVENT REGISTERED MEMBERS DETAILS **********");
		
String sql = null;
		
		System.out.println(eventtype);
		
		if(eventtype.equals("Online")) {
			sql ="";
		}
		
		if(eventtype.equals("Free")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.member_type=ec.registration_for and er.payment_reference_number IS NULL order by er.event_registration_id desc";
		}
		
		if(eventtype.equals("NEFT")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.member_type=ec.registration_for and er.payment_reference_number!='' order by er.event_registration_id desc";
		}
		
		//String sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er,event_charges ec,currency c,event ed where er.event_id=ec.event_id and er.event_id=ed.event_id and ec.currency_id=c.currency_id and er.member_type=ec.registration_for order by er.event_registration_id desc";
		Connection conn = null;
		List<EventRegistration> eventRegistrationlist=new ArrayList<EventRegistration>();;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

		
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventDate(rs.getString("event_date"));
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setCurrencyName(rs.getString("currency_name"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setPaymentStatu(rs.getString("payment_status"));
				eventRegistrationlist.add(er);
			}
			rs.close();
			ps.close();

			
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

		
				return eventRegistrationlist;
	}
	
	
	@Override
	public void updateStatus(int id,String newst) {
		logger.info("********** GET LAST STATUS AND UPDATE IT **********");
		Connection conn = null;
		/*String sql = "update event_registration set payment_status=? where event_registration_id=?";*/
		String sql = "update orders set payment_status=? where order_detail_id=?";
		try {
			
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, newst);
			ps.setInt(2, id);

			ps.executeUpdate();
		}

		catch (SQLException e) {
			throw new RuntimeException(e);
		} finally         {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {              }
			}
		}
	}
	
	@Override
	public List<EventRegistration> searchEventRegisteredMembers(String keyword, String eventtype) {
		logger.info("+++++ SEARCH EVENT REG MEMBERS +++++");
		List<EventRegistration> sta = new ArrayList<EventRegistration>();
		String sql = null;
		if(eventtype.equals("Online")) {
			sql ="";
		}
		
		if(eventtype.equals("Free")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.payment_reference_number IS NULL and er.member_type=ec.registration_for and Concat(er.first_name, '', er.last_name, '', last_name, '', ed.event_title, '',ed.event_date,'',er.payment_status,'') like ? order by er.event_registration_id desc";
		}
		
		if(eventtype.equals("NEFT")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.payment_reference_number!='' and er.member_type=ec.registration_for and Concat(er.first_name, '', er.last_name, '', last_name, '', ed.event_title, '',ed.event_date,'',er.payment_status,'') like ? order by er.event_registration_id desc";
		}
	
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, s);
			ps.setString(1, '%' + keyword + '%');
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				er = new EventRegistration();
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventDate(rs.getString("event_date"));
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setCurrencyName(rs.getString("currency_name"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setPaymentStatu(rs.getString("payment_status"));
				sta.add(er);
			}
			rs.close();
			ps.close();

			return sta;
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
	public EventRegistration getEventRegistrationDetailsById(int eventid) {
		logger.info("********** GET EVENT REGISTERED MEMBERS DETAILS BY ID**********");
		String sql = "select er.first_name,er.last_name,ed.event_title,ed.event_venue,DATE_FORMAT(ed.event_date,'dd/MM/yyyy') as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status,er.gender,er.email_id,er.mobile_no,er.rotarian,er.rotary_member_id,er.rmb_club_name,er.rmb_chap_name,er.comp_name,er.comp_website,er.comp_country,er.comp_short_desc,er.comp_business from event_registration er,event_charges ec,currency c,event ed,members ms where er.event_id=ec.event_id and ec.currency_id=c.currency_id and ms.member_id=er.member_id and er.member_type=ec.registration_for and er.event_registration_id=?";
		Connection conn = null;
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, eventid);
	
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				
				er.setEventTitle(rs.getString("event_title"));
				er.setEventVenue(rs.getString("event_venue"));
				er.setEventDate(rs.getString("event_date"));
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setCurrencyName(rs.getString("currency_name"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setPaymentStatu(rs.getString("payment_status"));
				er.setGender(rs.getString("gender"));
				er.setEmailId(rs.getString("email_id"));
				er.setMobileNo(rs.getString("mobile_no"));
				er.setRotarian(rs.getString("rotarian"));
				er.setRotaryMemberId(rs.getString("rotary_member_id"));
				er.setRotaryClubName(rs.getString("rmb_club_name"));
				er.setRotaryChapName(rs.getString("rmb_chap_name"));
				er.setCompName(rs.getString("comp_name"));
				er.setCompWebsite(rs.getString("comp_website"));
				er.setCompCountryId(rs.getInt("comp_country"));
				er.setCompShortDesc(rs.getString("comp_short_desc"));
				er.setCompBusiness(rs.getString("comp_business"));
			}
			rs.close();
			ps.close();

			return er;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}

		
			
	}
	
	@Override
	public List<EventRegistration> getEventRegistrationDetailsByPage(int pagesize, int startindex, String eventtype) {
		logger.info("********** GET EVENT REGISTERED MEMBERS DETAILS BY PAGE**********");
	
		String sql = null;
		
		System.out.println(eventtype);
		
		if(eventtype.equals("Online")) {
			sql ="";
		}
		
		if(eventtype.equals("Free")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.member_type=ec.registration_for and er.payment_reference_number IS NULL order by er.event_registration_id desc limit ?,?";
		}
		
		if(eventtype.equals("NEFT")) {
			sql = "select er.first_name,er.last_name,ed.event_title,DATE_FORMAT(ed.event_date,'%d/%m/%Y')as event_date,er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id left join event ed on er.event_id=ed.event_id where er.member_type=ec.registration_for and er.payment_reference_number!='' order by er.event_registration_id desc limit ?,?";
		}
						
		Connection conn = null;
		List<EventRegistration> eventRegistrationlist=new ArrayList<EventRegistration>();;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, startindex);
			ps.setInt(2, pagesize);
		
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventDate(rs.getString("event_date"));
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setCurrencyName(rs.getString("currency_name"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setPaymentStatu(rs.getString("payment_status"));
				eventRegistrationlist.add(er);
			}
			rs.close();
			ps.close();

			
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

		
				return eventRegistrationlist;

	}

	public List<Event> getAllEventsByDate(String todaydate) {
		logger.info("********** INSIDE GET EVENTS BY DATE **********");
		List<Event> Event = new ArrayList<Event>();
		String s = "y";
		String sql = "select event_id, event_type_id, event_title, event_subtitle, event_venue, event_map_location, event_date, event_time, event_description, registration, paid, rmbfb_member, rotarian, others, status, created_by, created_date, created_time, ip_address from event where status=? and event_date >= ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);
			ps.setString(2, todaydate);

			Event event = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getString("event_title"),
						rs.getString("event_subtitle"), rs.getString("event_venue"), rs.getString("event_map_location"),
						rs.getString("event_date"), rs.getString("event_time"), rs.getString("event_description"), rs.getString("registration"), rs.getString("paid"), 
						rs.getFloat("rmbfb_member"), rs.getFloat("rotarian"), rs.getFloat("others"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("created_time"), rs.getString("ip_address"));

				Event.add(event);
			}
			rs.close();
			ps.close();

			return Event;
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

	public List<Event> getLastThreeEventsByDate(String todaydate) {
		logger.info("+++++ INSIDE GET EVENTS BY DATE +++++");
		List<Event> Event = new ArrayList<Event>();
		String s = "y";
		String sql = "select event_id, event_title, event_date, event_time, event_description from event where status=? and event_date >= ? order by event_date desc limit 0,3";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ps.setString(2, todaydate);
			Event event = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getString("event_title"), rs.getString("event_date"),
						rs.getString("event_time"), rs.getString("event_description"));

				Event.add(event);
			}
			rs.close();
			ps.close();
			return Event;
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

	public List<EventAgenda> getRelatedEventAgenda(int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT AGENDA IMPL **********");
		List<EventAgenda> EventAgenda = new ArrayList<EventAgenda>();
		String sql = "select ea.event_agenda_title, ea.event_agenda_sequence, ea.event_agenda_conclusion from event e, event_agenda ea where e.event_id = ea.event_id and e.event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

			EventAgenda eventagenda = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				eventagenda = new EventAgenda(rs.getString("event_agenda_title"), rs.getInt("event_agenda_sequence"),
						rs.getString("event_agenda_conclusion"));

				EventAgenda.add(eventagenda);
			}
			rs.close();
			ps.close();

			return EventAgenda;
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

	public List<EventImage> getRelatedEventImages(int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT IMAGE IMPL **********");
		List<EventImage> EventImage = new ArrayList<EventImage>();
		String sql = "select ei.image, ei.event_image_title, ei.event_image_description from event e, event_image ei where e.event_id = ei.event_id and e.event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

			EventImage eventimages = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				eventimages = new EventImage(rs.getString("image"), rs.getString("event_image_title"),
						rs.getString("event_image_description"));

				EventImage.add(eventimages);
			}
			rs.close();
			ps.close();

			return EventImage;
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

	public List<EventUrl> getRelatedEventUrl(int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT URL IMPL **********");
		List<EventUrl> EventUrl = new ArrayList<EventUrl>();
		String sql = "select eu.event_url_title, eu.event_url, eu.event_url_sequence from event e, event_url eu where e.event_id = eu.event_id and e.event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

			EventUrl eventurl = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				eventurl = new EventUrl(rs.getString("event_url_title"), rs.getString("event_url"),
						rs.getInt("event_url_sequence"));

				EventUrl.add(eventurl);
			}
			rs.close();
			ps.close();

			return EventUrl;
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

	public int getLastEventId() {
		String sql = "select event_id from event order by event_id desc limit 0,1";
		int id = 0;
		Connection conn = null;
		logger.info("********** Inside get last Event Id Impl **********");
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("event_id");
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

	public void addEvent(Event e) {
		logger.info("********** INSIDE ADD EVENT IMPL **********");

		String sql = "insert into event (event_type_id,event_title,event_subtitle,event_venue,event_map_location,event_date,event_time,event_description, registration, paid, rmbfb_member, rotarian, others, status,created_by,created_date,created_time,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getEventTypeId());
			ps.setString(2, e.getEventTitle());
			ps.setString(3, e.getEventSubtitle());
			ps.setString(4, e.getEventVenue());
			ps.setString(5, e.getEventMapLocation());
			ps.setString(6, e.getEventDate());
			ps.setString(7, e.getEventTime());
			ps.setString(8, e.getEventDescription());
			ps.setString(9, e.getRegistration());
			ps.setString(10, e.getPaid());
			ps.setFloat(11, e.getRmbfbMember());
			ps.setFloat(12, e.getRotarian());
			ps.setFloat(13, e.getOthers());
			ps.setString(14, e.getStatus());
			ps.setInt(15, e.getCreatedBy());
			ps.setString(16, e.getCreatedDate());
			ps.setString(17, e.getCreatedTime());
			ps.setString(18, e.getIpAddress());

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

	public void addEventAgenda(EventAgenda ea) {
		logger.info("********** INSIDE ADD EVENT AGENDA IMPL **********");

		String sql = "insert into event_agenda (event_id,event_agenda_title,event_agenda_sequence,event_agenda_conclusion,created_by,created_date,created_time,ip_address) values (?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, ea.getEventId());
			ps.setString(2, ea.getEventAgendaTitle());
			ps.setInt(3, ea.getEventAgendaSequence());
			ps.setString(4, ea.getEventAgendaConclusion());
			ps.setInt(5, ea.getCreatedBy());
			ps.setString(6, ea.getCreatedDate());
			ps.setString(7, ea.getCreatedTime());
			ps.setString(8, ea.getIpAddress());

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

	public void addEventImages(EventImage ei) {
		logger.info("********** INSIDE ADD EVENT IMAGE IMPL **********");

		String sql = "insert into event_image (event_id,image,event_image_title,event_image_description,created_by,created_date,created_time,ip_address) values (?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ei.getEventId());
			ps.setString(2, ei.getImage());
			ps.setString(3, ei.getEventImageTitle());
			ps.setString(4, ei.getEventImageDescription());
			ps.setInt(5, ei.getCreatedBy());
			ps.setString(6, ei.getCreatedDate());
			ps.setString(7, ei.getCreatedTime());
			ps.setString(8, ei.getIpAddress());

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

	public void addEventUrl(EventUrl eu) {
		logger.info("********** INSIDE ADD EVENT URL IMPL **********");

		String sql = "insert into event_url (event_id,event_url_title,event_url,event_url_sequence,created_by,created_date,created_time,ip_address) values (?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eu.getEventId());
			ps.setString(2, eu.getEventUrlTitle());
			ps.setString(3, eu.getEventUrl());
			ps.setInt(4, eu.getEventUrlSequence());
			ps.setInt(5, eu.getCreatedBy());
			ps.setString(6, eu.getCreatedDate());
			ps.setString(7, eu.getCreatedTime());
			ps.setString(8, eu.getIpAddress());

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

	public void deleteEvent(int id) {
		logger.info("********** INSIDE DELETE EVENT ***********");
		String status = "n";
		String sql = "update event set status=? where event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, id);

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

	public void deleteSelectedEventAgenda(int eventid) {
		logger.info("********** INSIDE DELETE SELECTED EVENT AGENDA IMPL **********");
		String sql = "delete from event_agenda where event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

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

	public void deleteSelectedEventImages(int eventid) {
		logger.info("********** INSIDE DELETE SELETCED EVENT IMAGES IMPL ***********");
		String sql = "delete from event_image where event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

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

	public void deleteSelectedEventUrl(int eventid) {
		logger.info("********** INSIDE DELETE SELECTED EVENT URL IMPL **********");
		String sql = "delete from event_url where event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

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

	public void editEvent(Event e) {
		logger.info("********** INSIDE EDIT EVENT IMPL **********");
		String sql = "update event set event_type_id=?, event_title=?, event_subtitle=?, event_venue=?, event_map_location=?, event_date=?, event_time=?, event_description=?, registration=?, paid=?, rmbfb_member=?, rotarian=?, others=?, created_by=?, created_date=?, created_time=?, ip_address=?  where event_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, e.getEventTypeId());
			ps.setString(2, e.getEventTitle());
			ps.setString(3, e.getEventSubtitle());
			ps.setString(4, e.getEventVenue());
			ps.setString(5, e.getEventMapLocation());
			ps.setString(6, e.getEventDate());
			ps.setString(7, e.getEventTime());
			ps.setString(8, e.getEventDescription());
			ps.setString(9, e.getRegistration());
			ps.setString(10, e.getPaid());
			ps.setFloat(11, e.getRmbfbMember());
			ps.setFloat(12, e.getRotarian());
			ps.setFloat(13, e.getOthers());
			ps.setInt(14, e.getCreatedBy());
			ps.setString(15, e.getCreatedDate());
			ps.setString(16, e.getCreatedTime());
			ps.setString(17, e.getIpAddress());
			ps.setInt(18, e.getEventId());

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

	@Override
	public Event getLastEventDetail() {
		logger.info("********** INSIDE GET LAST EVENT DETAIL IMPL **********");
		
		String s = "y";
		String sql = "select event_id, event_type_id, event_title, event_subtitle, event_venue, event_map_location, event_date, event_time, event_description, registration, paid, rmbfb_member, rotarian, others, status, created_by, created_date, created_time, ip_address from event where status=? order by event_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Event event = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getString("event_title"),
						rs.getString("event_subtitle"), rs.getString("event_venue"), rs.getString("event_map_location"),
						rs.getString("event_date"), rs.getString("event_time"), rs.getString("event_description"), rs.getString("registration"), rs.getString("paid"), 
						rs.getFloat("rmbfb_member"), rs.getFloat("rotarian"), rs.getFloat("others"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("created_time"), rs.getString("ip_address"));
			}
			rs.close();
			ps.close();

			return event;
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

	public List<MemberResponse> getAllEventResponse() {
		logger.info("********** INSIDE GET ALL EVENT RESPONSE IMPL **********");
		List<MemberResponse> MemberResponse = new ArrayList<MemberResponse>();

		String sql = "select er.member_response_id, er.member_id, m.member_first_name, m.member_middle_name, m.member_last_name, er.event_id, er.join_self, er.join_spouse, er.join_annet, er.no_of_annets_join, er.not_coming_reason, er.comment, er.coming, er.notcoming, er.created_date, er.ip_address from member_response er, members m where m.member_id = er.member_id ";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			MemberResponse memberresponse = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberresponse = new MemberResponse(rs.getInt("member_response_id"), rs.getInt("member_id"),
						rs.getString("member_first_name"), rs.getString("member_middle_name"),
						rs.getString("member_last_name"), rs.getInt("event_id"), rs.getString("join_self"),
						rs.getString("join_spouse"), rs.getString("join_annet"), rs.getInt("no_of_annets_join"),
						rs.getString("not_coming_reason"), rs.getString("comment"), rs.getString("coming"),
						rs.getString("notcoming"), rs.getString("created_date"), rs.getString("ip_address"));

				MemberResponse.add(memberresponse);
			}
			rs.close();
			ps.close();

			return MemberResponse;
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

	public List<MemberResponse> getAllEventResponseCounts() {
		logger.info("********** INSIDE GET ALL EVENT COUNTS RESPONSE IMPL **********");
		List<MemberResponse> MemberResponse = new ArrayList<MemberResponse>();

		String sql = "select mr.event_id, e.event_title, count(mr.coming) as comming, count(mr.notcoming) as notcomming from member_response mr, event e where e.event_id=mr.event_id group by mr.event_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			MemberResponse memberresponse = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberresponse = new MemberResponse(rs.getInt("event_id"), rs.getString("event_title"),
						rs.getInt("comming"), rs.getInt("notcomming"));

				MemberResponse.add(memberresponse);
			}
			rs.close();
			ps.close();

			return MemberResponse;
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

	public Event getEventDetailById(int id) {
		logger.info("********** INSIDE GET EVENT DETAIL BY ID IMPL **********");
		// String s = "y";
		String sql = "select event_id, event_type_id, event_title, event_subtitle, event_venue, event_map_location, event_date, event_time, event_description, registration, paid from event where event_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

			Event event = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getString("event_title"),
						rs.getString("event_subtitle"), rs.getString("event_venue"), rs.getString("event_map_location"),
						rs.getString("event_date"), rs.getString("event_time"), rs.getString("event_description"), rs.getString("registration"), rs.getString("paid"));
			}
			rs.close();
			ps.close();

			return event;
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

	public void AddCommingResponse(MemberResponse mr) {
		logger.info("********** INSIDE ADD MEMBER COMING RESPONSE IMPL **********");

		String sql = "insert into member_response (member_id,event_id,join_self,join_spouse,join_annet,no_of_annets_join,comment,coming,notcoming,ip_address) values (?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, mr.getMemberId());
			ps.setInt(2, mr.getEventId());
			ps.setString(3, mr.getJoinSelf());
			ps.setString(4, mr.getJoinSpouse());
			ps.setString(5, mr.getJoinAnnet());
			ps.setInt(6, mr.getNoOfAnnetsJoin());
			ps.setString(7, mr.getComment());
			ps.setString(8, mr.getComing());
			ps.setString(9, mr.getNotcoming());
			ps.setString(10, mr.getIpAddress());
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

	public void AddNotComingResponse(MemberResponse mr) {
		logger.info("********** INSIDE ADD MEMBER NOT COMING RESPONSE IMPL **********");

		String sql = "insert into member_response (member_id,event_id,not_coming_reason,coming,notcoming,ip_address) values (?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, mr.getMemberId());
			ps.setInt(2, mr.getEventId());
			ps.setString(3, mr.getNotComingReason());
			ps.setString(4, mr.getComing());
			ps.setString(5, mr.getNotcoming());
			ps.setString(6, mr.getIpAddress());
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

	public MemberResponse getMemberEventResponse(int id, int memberid) {
		logger.info("********** INSIDE GET MEMBER EVENT RESPONSE BY ID IMPL **********");
		// String s = "y";
		String sql = "select coming, notcoming from member_response where event_id=? and member_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ps.setInt(2, memberid);

			MemberResponse memberresponse = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				memberresponse = new MemberResponse(rs.getString("coming"), rs.getString("notcoming"));
			}
			rs.close();
			ps.close();

			return memberresponse;
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
	
	
	public void addRegistrationCharges(EventCharges ec) {
		logger.info("********** INSIDE ADD EVENT CHARGES IMPL **********");

		String sql = "insert into event_charges (event_id,registration_for,currency_id,amount, status, created_by, ip_address) values (?,?,?,?,?,?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, ec.getEventId());
			ps.setString(2, ec.getRegistrationFor());
			ps.setInt(3, ec.getCurrencyId());
			ps.setFloat(4, ec.getAmount());
			ps.setString(5, ec.getStatus());
			ps.setInt(6, ec.getCreatedBy());
			ps.setString(7, ec.getIpAddress());
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
	
	public List<EventCharges> getEventChargesList(int eventid) {
		logger.info("********** INSIDE GET EVENT CHARGES IMPL **********");
		List<EventCharges> ea = new ArrayList<EventCharges>();
		String sql = "select ec.event_charges_id, ec.event_id, ec.registration_for, ec.currency_id, ec.amount, c.currency_name, c.currency_code from event_charges ec left join currency c on ec.currency_id=c.currency_id, event e where e.event_id = ec.event_id and ec.event_id=? order by registration_for desc";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);

			EventCharges ec = null;

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ec = new EventCharges();
				
				ec.setEventChargeId(rs.getInt("event_charges_id"));
				ec.setEventId(rs.getInt("event_id"));
				ec.setRegistrationFor(rs.getString("registration_for"));
				ec.setCurrencyId(rs.getInt("currency_id"));
				ec.setAmount(rs.getFloat("amount"));
				ec.setCurrencyName(rs.getString("currency_name"));
				ec.setCurrencyCode(rs.getString("currency_code"));
				ea.add(ec);
			}
			rs.close();
			ps.close();

			return ea;
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
	
	
	public void deleteEventCharge(int id) {
		logger.info("********** INSIDE DELETE EVENT CHARGES IMPL **********");
		String sql = "delete from event_charges where event_charges_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);

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
	
	
	@Override
	public String addRotaryUser(EventRegistration r) {
		logger.info("+++++ ADD FRONT ROTARY USER +++++");
		String sql = "insert into members (user_type_id,membership_number,member_type_id,member_first_name, member_last_name, member_gender, member_email,member_mobile_number,member_company_name,member_website_name,member_company_description,business_goals, sequence,status, created_by, ip_address,member_password, member_type, rotary_member_id, rmb_club_name, rmb_chap_name, member_country_id_citizenship) values (2,?,?,?,?,?,?,?,?,?,?,?,?,'i',?,?,?,?,?,?,?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, r.getMemberShipNumber());
			ps.setInt(2, 0);
			ps.setString(3, r.getFirstName());
			ps.setString(4, r.getLastName());
			ps.setString(5, r.getGender());
			ps.setString(6, r.getEmailId());
			ps.setString(7, r.getMobileNo());
			ps.setString(8, r.getCompName());
			ps.setString(9, r.getCompWebsite());
			ps.setString(10, r.getCompShortDesc());
			ps.setString(11, r.getCompBusiness());
			ps.setInt(12, r.getSequence());
			//ps.setString(13, r.getStatus());
			ps.setInt(13, r.getCreatedBy());
			ps.setString(14, r.getIpAddress());
			ps.setString(15, r.getPassword());
			ps.setString(16, r.getMemberType());
			ps.setString(17, r.getRotaryMemberId());
			ps.setString(18, r.getRotaryClubName());
			ps.setString(19, r.getRotaryChapName());
			ps.setInt(20, r.getCompCountryId());
			ps.executeUpdate();

			return "Success";
		} catch (SQLException e) {
			return e.getMessage();
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
	public String eventRegistration(EventRegistration r) {
		logger.info("+++++ EVENT REGISTRATION +++++");
		String sql = "insert into event_registration (member_type, event_id,member_id, first_name, last_name, gender, email_id,mobile_no,rotarian,rotary_member_id,rmb_club_name,rmb_chap_name,comp_name,comp_website,comp_country,comp_short_desc,comp_business,status, created_by, ip_address,payment_status,event_registrationDate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Unpaid',?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, r.getMemberType());
			ps.setInt(2, r.getEventId());
			ps.setInt(3, r.getMemberId());
			ps.setString(4, r.getFirstName());
			ps.setString(5, r.getLastName());
			ps.setString(6, r.getGender());
			ps.setString(7, r.getEmailId());
			ps.setString(8, r.getMobileNo());
			ps.setString(9, r.getRotarian());
			ps.setString(10, r.getRotaryMemberId());
			ps.setString(11, r.getRotaryClubName());
			ps.setString(12, r.getRotaryChapName());
			ps.setString(13, r.getCompName());
			ps.setString(14, r.getCompWebsite());
			ps.setInt(15, r.getCompCountryId());
			ps.setString(16, r.getCompShortDesc());
			ps.setString(17, r.getCompBusiness());
			ps.setString(18, r.getStatus());
			ps.setInt(19, r.getCreatedBy());
			ps.setString(20, r.getIpAddress());
			ps.setString(21, current_date);
			ps.executeUpdate();
			System.out.println("everything done perfectly"+current_date);
			
			return "Success";
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	public EventRegistration getEventRegistrationDetails(int eid,int mid) {
		logger.info("********** INSIDE GET EVENT REGISTRATION DETAIL BY ID IMPL **********");
		// String s = "y";
		String sql = "select er.event_registration_id, er.member_type, ec.amount, c.currency_code, c.currency_name, er.payment_reference_number, er.payment_status from event_registration er left join event_charges ec on er.event_id=ec.event_id left join currency c on ec.currency_id=c.currency_id where er.member_type=ec.registration_for and er.event_id=? and er.member_id=? group by er.member_type";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eid);
			ps.setInt(2, mid);

			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setCurrencyName(rs.getString("currency_name"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setPaymentStatu(rs.getString("payment_status"));
			}
			rs.close();
			ps.close();

			return er;
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
	
	public void updateEventPayment(EventRegistration e) {
		logger.info("********** INSIDE UPDATE EVENT BY PAYMENT DETAILS IMPL **********");
		String sql = null;
		sql = "update event_registration set payment_reference_number=?, payment_status='Pending', amount=?, paystat=? where event_id=? and member_id=?";
		

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, e.getPaymentRefNo());
			ps.setFloat(2, e.getAmount());
			ps.setString(3, e.getPayStat());
			ps.setInt(4, e.getEventId());
			ps.setInt(5, e.getMemberId());
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
	
	public void updateOrdersPayment(EventRegistration e) {
		logger.info("********** INSIDE UPDATE EVENT BY PAYMENT DETAILS IMPL **********");
		String sql = null;
		
		sql = "update orders set bank_reference_number=?, payment_status='Pending',total_amount=? where event_id=? and member_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, e.getPaymentRefNo());
			ps.setFloat(2, e.getAmount());
			ps.setInt(3, e.getEventId());
			ps.setInt(4, e.getMemberId());
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
	
	public void updateEventRegistrationPayment(EventRegistration e) {
		logger.info("********** INSIDE UPDATE EVENT BY PAYMENT DETAILS IMPL **********");
		String sql = "update event_registration set payment_status='Paid', amount=? where event_id=? and member_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setFloat(1, e.getAmount());
			ps.setInt(2, e.getEventId());
			ps.setInt(3, e.getMemberId());
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
	
	public void updateOrdersPaidPayment(EventRegistration e) {
		logger.info("********** INSIDE UPDATE ORDERS BY PAYMENT DETAILS IMPL **********");
		String sql = "update orders set payment_status='Free Event', total_amount=? where event_id=? and member_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setFloat(1, e.getAmount());
			ps.setInt(2, e.getEventId());
			ps.setInt(3, e.getMemberId());
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
	
	
	public Event getLastEventDetailForFront() {
		logger.info("********** INSIDE GET LAST EVENT DETAIL IMPL **********");
		String s = "y";
		String sql = "select event_id, event_type_id, event_title, event_subtitle, event_venue, event_map_location, event_date, event_time, event_description, registration, paid, rmbfb_member, rotarian, others, status, created_by, created_date, created_time, ip_address from event where status=? order by event_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, s);

			Event event = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				event = new Event(rs.getInt("event_id"), rs.getInt("event_type_id"), rs.getString("event_title"),
						rs.getString("event_subtitle"), rs.getString("event_venue"), rs.getString("event_map_location"),
						rs.getString("event_date"), rs.getString("event_time"), rs.getString("event_description"), rs.getString("registration"), rs.getString("paid"), 
						rs.getFloat("rmbfb_member"), rs.getFloat("rotarian"), rs.getFloat("others"),
						rs.getString("status"), rs.getInt("created_by"), rs.getString("created_date"),
						rs.getString("created_time"), rs.getString("ip_address"));
			}
			rs.close();
			ps.close();

			return event;
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
	public void updateRegistrationCharges(EventCharges eventCharges) {
		logger.info("********** INSIDE UPDATE EVENT CHARGES IMPL **********");

		String sql = "update event_charges set event_id=?,registration_for=?,currency_id=?,amount=?, status=?, created_by=?, ip_address=? where event_charges_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventCharges.getEventId());
			ps.setString(2, eventCharges.getRegistrationFor());
			ps.setInt(3, eventCharges.getCurrencyId());
			ps.setFloat(4, eventCharges.getAmount());
			ps.setString(5, eventCharges.getStatus());
			ps.setInt(6, eventCharges.getCreatedBy());
			ps.setString(7, eventCharges.getIpAddress());
			ps.setInt(8, eventCharges.getEventChargeId());
			
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

	@Override
	public EventRegistration getMemberEventPaymentStatus(int id, int memberid) {
		logger.info("********** INSIDE GET MEMBER EVENT PAYMENT STATUS**********");
		
		String sql = "select event_id, member_id, order_status, bank_reference_number, failure_message, payment_status from orders where event_id=? and member_id=?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ps.setInt(2, memberid);

			EventRegistration Er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Er = new EventRegistration();
				Er.setEventId(rs.getInt("event_id"));
				Er.setMemberId(rs.getInt("member_id"));
				Er.setOrderStatus(rs.getString("order_status"));
				Er.setPaymentRefNo(rs.getString("bank_reference_number"));
				Er.setFailureMsg(rs.getString("failure_message"));
				Er.setPaymentStatu(rs.getString("payment_status"));
			}
			rs.close();
			ps.close();

			return Er;
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
	public EventRegistration getMemberEventRegistrationStatus(int id, int memberid) {
logger.info("********** INSIDE GET MEMBER EVENT REGISTRATION STATUS **********");
		
		String sql = "select event_registration_id, event_id, member_id, payment_status from event_registration where event_id=? and member_id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ps.setInt(2, memberid);

			EventRegistration Er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Er = new EventRegistration();
				Er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				Er.setEventId(rs.getInt("event_id"));
				Er.setMemberId(rs.getInt("member_id"));
				Er.setPaymentStatu(rs.getString("payment_status"));
			}
			rs.close();
			ps.close();

			return Er;
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
	public List<EventRegistration> getAllRegisterdMembers(int eventid) {
		logger.info("********** GET EVENT REGISTERED MEMBERS DETAILS BY ID**********");
		List<EventRegistration> erl = new ArrayList<EventRegistration>();
		String sql = "SELECT er.event_registration_id,m.status,m.member_profile_picture,m.business_category_id,bc.business_category_name, er.event_id, er.member_type, DATE_FORMAT(er.event_registrationDate,'%d/%m/%Y')as event_registrationDate, er.payment_reference_number, er.member_id, er.first_name, er.last_name, er.gender, er.comp_name, er.email_id, er.mobile_no, er.payment_status as registration_pay,er.paystat, ec.amount, ec.currency_id, c.currency_name, c.currency_code, ev.event_title, ev.event_venue, DATE_FORMAT(ev.event_date, '%d/%m/%Y') as event_date, ev.registration, ev.paid FROM event_registration er left join event_charges ec on er.event_id = ec.event_id left join currency c on ec.currency_id = c.currency_id left join event ev on ev.event_id = er.event_id left join members m on er.member_id=m.member_id left join business_category bc on bc.business_category_id = m.business_category_id where ec.registration_for = er.member_type and er.event_id=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, eventid);
	
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setEventId(rs.getInt("event_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setRegistrationDate(rs.getString("event_registrationDate"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setMemberId(rs.getInt("member_id"));
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setGender(rs.getString("gender"));
				er.setCompName(rs.getString("comp_name"));
				er.setEmailId(rs.getString("email_id"));
				er.setMobileNo(rs.getString("mobile_no"));
				er.setPaymentStatu(rs.getString("registration_pay"));
				er.setPayStat(rs.getString("paystat"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventVenue(rs.getString("event_venue"));
				er.setEventDate(rs.getString("event_date"));
				er.setRegReq(rs.getString("registration"));
				er.setPayReq(rs.getString("paid"));
				er.setMemberActiveInactive(rs.getString("status"));
				er.setMemberProfilePicture(rs.getString("member_profile_picture"));
				er.setBusinessCategoryName(rs.getString("business_category_name"));
				
				
				
				erl.add(er);
			}
			rs.close();
			ps.close();

			return erl;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public List<EventRegistration> getAllEventRegisterdMembers() {
		logger.info("********** GET EVENT REGISTERED MEMBERS**********");
		List<EventRegistration> erl = new ArrayList<EventRegistration>();
		String sql = "SELECT er.event_registration_id, er.event_id, m.status, er.member_type, er.member_id, er.first_name, er.last_name, er.gender, er.email_id, er.mobile_no, er.payment_status as registration_pay,er.payment_reference_number,er.paystat, DATE_FORMAT(er.event_registrationDate,'%d/%m/%Y')as event_registrationDate, ec.amount, ec.currency_id, c.currency_name, c.currency_code, ev.event_title, ev.event_venue, DATE_FORMAT(ev.event_date, '%d/%m/%Y') as event_date, ev.registration, ev.paid FROM event_registration er left join event_charges ec on er.event_id = ec.event_id left join currency c on ec.currency_id = c.currency_id left join event ev on ev.event_id = er.event_id left join members m on er.member_id=m.member_id where ec.registration_for = er.member_type";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
	
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setEventId(rs.getInt("event_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setMemberId(rs.getInt("member_id"));
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setGender(rs.getString("gender"));
				er.setEmailId(rs.getString("email_id"));
				er.setMobileNo(rs.getString("mobile_no"));
				er.setPaymentStatu(rs.getString("registration_pay"));
				er.setPayStat(rs.getString("paystat"));
				er.setRegistrationDate(rs.getString("event_registrationDate"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventVenue(rs.getString("event_venue"));
				er.setEventDate(rs.getString("event_date"));
				er.setRegReq(rs.getString("registration"));
				er.setPayReq(rs.getString("paid"));
				er.setMemberActiveInactive(rs.getString("status"));
				
				
				erl.add(er);
			}
			rs.close();
			ps.close();

			return erl;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}
	}
	
	
	@Override
	public List<EventRegistration> getAllEventRegisterdMembersForResult(String fromdate, String todate, int pagesize, int startindex) {
		logger.info("********** GET EVENT REGISTERED MEMBERS FOR RESULT**********");
		List<EventRegistration> erl = new ArrayList<EventRegistration>();
		String sql = "SELECT er.event_registration_id,m.status, er.event_id, er.member_type, er.member_id, er.first_name, er.last_name, er.gender, er.email_id, er.mobile_no,er.paystat, er.payment_status as registration_pay,er.payment_reference_number, DATE_FORMAT(er.event_registrationDate,'%d/%m/%Y')as event_registrationDate, ec.amount, ec.currency_id, c.currency_name, c.currency_code, ev.event_title, ev.event_venue, DATE_FORMAT(ev.event_date, '%d/%m/%Y') as event_date, ev.registration, ev.paid FROM event_registration er left join event_charges ec on er.event_id = ec.event_id left join currency c on ec.currency_id = c.currency_id left join event ev on ev.event_id = er.event_id left join members m on er.member_id=m.member_id where ec.registration_for = er.member_type and er.event_registrationDate between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y') order by er.event_registration_id desc limit ?,?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(fromdate+", "+todate);
			ps.setString(1, fromdate);
			ps.setString(2, todate);
			ps.setInt(3, startindex);
			ps.setInt(4, pagesize);
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setEventId(rs.getInt("event_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setMemberId(rs.getInt("member_id"));
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setGender(rs.getString("gender"));
				er.setEmailId(rs.getString("email_id"));
				er.setMobileNo(rs.getString("mobile_no"));
				er.setPaymentStatu(rs.getString("registration_pay"));
				er.setPayStat(rs.getString("paystat"));
				er.setRegistrationDate(rs.getString("event_registrationDate"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventVenue(rs.getString("event_venue"));
				er.setEventDate(rs.getString("event_date"));
				er.setRegReq(rs.getString("registration"));
				er.setPayReq(rs.getString("paid"));
				er.setMemberActiveInactive(rs.getString("status"));
				
				
				
				erl.add(er);
			}
			rs.close();
			ps.close();

			return erl;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public EventRegistration getRegisteredMemberDetailByeventregistrationid(int eventregistrationid) {
		logger.info("********** GET EVENT REGISTERED MEMBER by eventregistration id**********");
		String sql = "SELECT er.event_registration_id, er.event_id,m.status, er.member_type, er.member_id, er.first_name, er.last_name, er.gender, er.email_id, er.mobile_no,er.paystat, er.payment_status as registration_pay,er.payment_reference_number, DATE_FORMAT(er.event_registrationDate,'%d/%m/%Y')as event_registrationDate, ec.amount, ec.currency_id, c.currency_name, c.currency_code, ev.event_title, ev.event_venue, DATE_FORMAT(ev.event_date, '%d/%m/%Y') as event_date, ev.registration, ev.paid FROM event_registration er left join event_charges ec on er.event_id = ec.event_id left join currency c on ec.currency_id = c.currency_id left join event ev on ev.event_id = er.event_id left join members m on er.member_id=m.member_id where ec.registration_for = er.member_type and er.event_registration_id=?";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eventregistrationid);
			EventRegistration er = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				er = new EventRegistration();
				er.setEventRegsitrationId(rs.getInt("event_registration_id"));
				er.setEventId(rs.getInt("event_id"));
				er.setMemberType(rs.getString("member_type"));
				er.setMemberId(rs.getInt("member_id"));
				er.setFirstName(rs.getString("first_name"));
				er.setLastName(rs.getString("last_name"));
				er.setGender(rs.getString("gender"));
				er.setEmailId(rs.getString("email_id"));
				er.setMobileNo(rs.getString("mobile_no"));
				er.setPaymentStatu(rs.getString("registration_pay"));
				er.setPayStat(rs.getString("paystat"));
				er.setRegistrationDate(rs.getString("event_registrationDate"));
				er.setPaymentRefNo(rs.getString("payment_reference_number"));
				er.setAmount(rs.getFloat("amount"));
				er.setCurrencyCode(rs.getString("currency_code"));
				er.setEventTitle(rs.getString("event_title"));
				er.setEventVenue(rs.getString("event_venue"));
				er.setEventDate(rs.getString("event_date"));
				er.setRegReq(rs.getString("registration"));
				er.setPayReq(rs.getString("paid"));
				er.setMemberActiveInactive(rs.getString("status"));
			}
			rs.close();
			ps.close();
			return er;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void updateEventRegistrationStatus(int eventregistrationID, String status) {
		logger.info("********** INSIDE UPDATE EVENT REGISTRATION STATUS **********");

		String sql = "update event_registration set payment_status=? where event_registration_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			

			ps.setString(1, status);
			ps.setInt(2, eventregistrationID);
			
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
} //end
