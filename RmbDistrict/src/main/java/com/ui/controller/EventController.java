package com.ui.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.CountryDAO;
import com.ui.dao.EventDAO;
import com.ui.dao.LoginDAO;
import com.ui.dao.MemberDAO;
import com.ui.dao.PaymentDAO;
import com.ui.dao.RotaryYearDAO;
import com.ui.dao.StateDAO;
import com.ui.model.Country;
import com.ui.model.Event;
import com.ui.model.EventAgenda;
import com.ui.model.EventAgendaConclusion;
import com.ui.model.EventCharges;
import com.ui.model.EventImage;
import com.ui.model.EventRegistration;
import com.ui.model.EventType;
import com.ui.model.EventUrl;
import com.ui.model.MemberCategoryByYear;
import com.ui.model.MemberResponse;
import com.ui.model.Members;
import com.ui.model.Order;
import com.ui.model.RotaryYear;

@RestController
public class EventController {

	@Autowired
	EventDAO eventdao;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	LoginDAO loginDAO;
	@Autowired
	CountryDAO countryDAO;
	@Autowired
	StateDAO stateDAO;
	@Autowired
	PaymentDAO paymentdao;
	@Autowired
	RotaryYearDAO rotaryyeardao;
	
	EventType eventtype;
	Event event;
	EventAgenda eventagenda;
	EventImage eventimages;
	EventUrl eventurl;
	EventAgendaConclusion eventagendaconclusion;
	MemberResponse memberresponse;
	EventCharges eventCharges;
	EventRegistration eventRegistration;
	RotaryYear rotaryyear;
	MemberCategoryByYear memberCategoryByYear;

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@RequestMapping(value = "/EventType", method = RequestMethod.GET, produces = "application/json")
	public List<EventType> EventType(HttpServletRequest request) {
		logger.info("********** Inside EventType Controller **********");

		List<EventType> EventType = eventdao.getAllEventType();

		return EventType;
	}
	
	@RequestMapping(value = "/getEventRegistrationDetails", method = RequestMethod.GET, produces = "application/json")
	public EventRegistration getEventRegistrationDetails(int eventid, int mid, HttpServletRequest request) {
		logger.info("********** Inside Event REGISTRATION Controller **********");

		EventRegistration er = eventdao.getEventRegistrationDetails(eventid, mid);

		return er;
	}
	
	@RequestMapping(value = "/getAllEventRegistrationDetails", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> getEventRegistrationDetails(HttpServletRequest request,String eventtype) {
		logger.info("********** Inside Event REGISTRATION Controller **********");

		List<EventRegistration> erlist = eventdao.getAllEventRegistrationDetails(eventtype);

		return erlist;
	}
	
	@RequestMapping(value = "/searchEventRegisteredMembers", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> searchEventRegisteredMembers(HttpServletRequest request,String keyword, String eventtype) {
		logger.info("********** Inside Event REGISTRATION Controller **********");

		List<EventRegistration> erlist = eventdao.searchEventRegisteredMembers(keyword,eventtype);

		return erlist;
	}
	
	@RequestMapping(value = "/getEventRegistrationDetailsByPage", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> getEventRegistrationDetailsByPage(HttpServletRequest request,int pagesize,int startindex, String eventtype) {
		logger.info("********** Inside Event REGISTRATION Controller **********");

		List<EventRegistration> erlist = eventdao.getEventRegistrationDetailsByPage(pagesize,startindex,eventtype);

		return erlist;
	}

	@RequestMapping(value = "/Events", method = RequestMethod.GET, produces = "application/json")
	public List<Event> Event(HttpServletRequest request) {
		logger.info("********** Inside Events Controller **********");

		List<Event> Event = eventdao.getAllEvents();

		return Event;
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public String updateStatus(HttpServletRequest request,int id,String status) {
		logger.info("********** UPDATE STATUS********** ");		
		eventdao.updateStatus(id,status);
		return "Success";
	}
	
	@RequestMapping(value = "/updateEventRegistrationStatus", method = RequestMethod.POST)
	public String updateEventRegistrationStatus(HttpServletRequest request,int eventregistrationID,String status) {
		logger.info("********** UPDATE STATUS EVENT REGISTRATION********** ");		
		eventdao.updateEventRegistrationStatus(eventregistrationID,status);
		return "Success";
	}
	
	@RequestMapping(value = "/getLastEventDetailForFront", method = RequestMethod.GET, produces = "application/json")
	public Event getLastEventDetailForFront(HttpServletRequest request) {
		logger.info("********** Inside Get Last Event Details Controller **********");

		Event event = eventdao.getLastEventDetailForFront();

		return event;
	}
	
	@RequestMapping(value = "/getLastEventDetail", method = RequestMethod.GET, produces = "application/json")
	public Event getLastEventDetail(HttpServletRequest request) {
		logger.info("********** Inside Get Last Event Details Controller **********");

		Event event = eventdao.getLastEventDetail();

		return event;
	}

	@RequestMapping(value = "/EventsByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Event> EventsByDate(HttpServletRequest request, String todaydate) {
		logger.info("***** EVENTS BY DATE *****");
		List<Event> Event = eventdao.getAllEventsByDate(todaydate);
		return Event;
	}
	
	@RequestMapping(value = "/getEventRegistrationDetailsById", method = RequestMethod.GET, produces = "application/json")
	public EventRegistration getEventRegistrationDetailsById(int eventid,HttpServletRequest request) {
		logger.info("********** Inside Event REGISTRATION Controller **********");

		EventRegistration er = eventdao.getEventRegistrationDetailsById(eventid);

		return er;
	}
	
	@RequestMapping(value = "/getRegisteredMemberDetailByeventregistrationid", method = RequestMethod.GET, produces = "application/json")
	public EventRegistration getRegisteredMemberDetailByeventregistrationid(HttpServletRequest request, int eventregistrationid) {
		logger.info("********** INSIDE GET REGISTERD MEMBERS by registration id**********");
		EventRegistration er = eventdao.getRegisteredMemberDetailByeventregistrationid(eventregistrationid);
		
		Order od = paymentdao.getOrderDetailByEventIdandMemberId(er.getEventId(),er.getMemberId());
		if(od != null) {
			er.setOrderDetails(od);
		}
		
		EventRegistration erOrd;
		
			 if(er.getPaymentStatu() != "Paid") {
				erOrd = eventdao.getMemberEventPaymentStatus(er.getEventId(), er.getMemberId());
				if(erOrd != null)
				{   
					er.setPayStat("Online");
					er.setPaymentStatu(erOrd.getPaymentStatu());
					er.setPaymentRefNo(erOrd.getPaymentRefNo());
					er.setFailureMsg(erOrd.getFailureMsg());
				}
			}
		

		return er;
	}
	
	@RequestMapping(value = "/getAllRegisteredMembersByEventID", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> getAllRegisteredMembersByEventID(HttpServletRequest request, int eventid) {
		logger.info("********** INSIDE GET REGISTERD MEMBERS **********");
		EventRegistration erOrd;
		List<EventRegistration> er = eventdao.getAllRegisterdMembers(eventid);
		
		for(EventRegistration evre:er) {
			 if(evre.getPaymentStatu() != "Paid") {
				erOrd = eventdao.getMemberEventPaymentStatus(evre.getEventId(), evre.getMemberId());
				if(erOrd != null)
				{   
					evre.setPayStat("Online");
					evre.setPaymentStatu(erOrd.getPaymentStatu());
					evre.setPaymentRefNo(erOrd.getPaymentRefNo());
					evre.setFailureMsg(erOrd.getFailureMsg());
				}
			}
		}

		return er;
	}
	
	@RequestMapping(value = "/getAllRegisteredMembers", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> getAllRegisteredMembers(HttpServletRequest request) {
		logger.info("********** INSIDE GET REGISTERD MEMBERS **********");
		EventRegistration erOrd;
		List<EventRegistration> er = eventdao.getAllEventRegisterdMembers();
		
		for(EventRegistration evre:er) {
			 if(evre.getPaymentStatu() != "Paid") {
				erOrd = eventdao.getMemberEventPaymentStatus(evre.getEventId(), evre.getMemberId());
				if(erOrd != null)
				{   
					evre.setPayStat("Online");
					evre.setPaymentStatu(erOrd.getPaymentStatu());
					evre.setPaymentRefNo(erOrd.getPaymentRefNo());
					evre.setFailureMsg(erOrd.getFailureMsg());
				}
			}
		}

		return er;
	}
	
	@RequestMapping(value = "/getAllRegisteredMembersForResult", method = RequestMethod.GET, produces = "application/json")
	public List<EventRegistration> getAllRegisteredMembers(String fromdate, String todate,int pagesize,int startindex, HttpServletRequest request) {
		logger.info("********** INSIDE GET REGISTERD MEMBERS **********");
		EventRegistration erOrd;
		List<EventRegistration> er = eventdao.getAllEventRegisterdMembersForResult(fromdate, todate, pagesize, startindex);
		
		for(EventRegistration evre:er) {
			 if(evre.getPaymentStatu() != "Paid") {
				erOrd = eventdao.getMemberEventPaymentStatus(evre.getEventId(), evre.getMemberId());
				if(erOrd != null)
				{   
					evre.setPayStat("Online");
					evre.setPaymentStatu(erOrd.getPaymentStatu());
					evre.setPaymentRefNo(erOrd.getPaymentRefNo());
					evre.setFailureMsg(erOrd.getFailureMsg());
				}
			}
		}

		return er;
	}
	
	@RequestMapping(value = "/getLastThreeEventsByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Event> getLastThreeEventsByDate(HttpServletRequest request, String todaydate) {
		logger.info("***** GET LAST THREE EVENTS BY DATE *****");
		List<Event> Event = eventdao.getLastThreeEventsByDate(todaydate);
		return Event;
	}

	@RequestMapping(value = "/EventResponse", method = RequestMethod.GET, produces = "application/json")
	public List<MemberResponse> EventResponse(HttpServletRequest request) {
		logger.info("********** INSIDE EVENT RESPONSE CONTROLLER **********");

		List<MemberResponse> MemberResponse = eventdao.getAllEventResponse();

		return MemberResponse;
	}

	@RequestMapping(value = "/EventResponseCounts", method = RequestMethod.GET, produces = "application/json")
	public List<MemberResponse> EventResponseCounts(HttpServletRequest request) {
		logger.info("********** INSIDE EVENT RESPONSE COUNTS CONTROLLER **********");

		List<MemberResponse> MemberResponse = eventdao.getAllEventResponseCounts();

		return MemberResponse;
	}

	@RequestMapping(value = "/getRelatedEventAgenda", method = RequestMethod.GET, produces = "application/json")
	public List<EventAgenda> getRelatedEventAgenda(HttpServletRequest request, int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT AGENDA **********");

		List<EventAgenda> EventAgenda = eventdao.getRelatedEventAgenda(eventid);

		return EventAgenda;
	}
	
	@RequestMapping(value = "/getEventChargesList", method = RequestMethod.GET, produces = "application/json")
	public List<EventCharges> getEventChargesList(HttpServletRequest request, int eventid) {
		logger.info("********** INSIDE GET EVENT CHARGES **********");

		List<EventCharges> ec = eventdao.getEventChargesList(eventid);

		return ec;
	}

	@RequestMapping(value = "/getRelatedEventImages", method = RequestMethod.GET, produces = "application/json")
	public List<EventImage> getRelatedEventImages(HttpServletRequest request, int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT IMAGES **********");

		List<EventImage> EventImage = eventdao.getRelatedEventImages(eventid);

		return EventImage;
	}
	

	@RequestMapping(value = "/getRelatedEventUrl", method = RequestMethod.GET, produces = "application/json")
	public List<EventUrl> getRelatedEventUrl(HttpServletRequest request, int eventid) {
		logger.info("********** INSIDE GET RELATED EVENT URL **********");

		List<EventUrl> EventUrl = eventdao.getRelatedEventUrl(eventid);

		return EventUrl;
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.POST)
	public String addEvent(HttpServletRequest request, HttpSession session, int eventtype, String eventtitle,
			String eventsubtitle, String eventdate, String eventtime, String eventvenue, String eventmaplocation,
			String eventdescription, String createddate, String createdtime, String notifyviaemail,
			String notifyviasms, String registration, String paid, float rmbfbmember, float rotarian, float others) {
		logger.info("********** INSIDE ADD EVENT **********");

		String s = "y";
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		try {
		event = new Event(eventtype, eventtitle, eventsubtitle, eventvenue, eventmaplocation, eventdate, eventtime,
				URLDecoder.decode(eventdescription, "UTF-8"), registration, paid, rmbfbmember, rotarian, others, s, 1, createddate, createdtime, IpAddress);
		eventdao.addEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("notifyviaemail", notifyviaemail);
		session.setAttribute("notifyviasms", notifyviasms);

		return "";
	}

	@RequestMapping(value = "/addEventAgenda", method = RequestMethod.POST)
	public String addEventAgenda(HttpServletRequest request, int eventagendasequence, String eventagendatitle,
			String eventagendaconclusion, String createddate, String createdtime) {
		logger.info("********** INSIDE ADD EVENT AGENDA **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		int eventid = eventdao.getLastEventId();

		eventagenda = new EventAgenda(eventid, eventagendatitle, eventagendasequence, eventagendaconclusion, 1,
				createddate, createdtime, IpAddress);

		eventdao.addEventAgenda(eventagenda);

		return "";
	}
	
	@RequestMapping(value = "/addRegistrationCharges", method = RequestMethod.POST)
	public String addRegistrationCharges(HttpServletRequest request, String registrationfor, int currencyid, float amount,
			String createddate, String createdtime) {
		logger.info("********** INSIDE ADD EVENT CHARGES **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		int eventid = eventdao.getLastEventId();

		eventCharges = new EventCharges();
		eventCharges.setEventId(eventid);
		eventCharges.setRegistrationFor(registrationfor);
		eventCharges.setCurrencyId(currencyid);
		eventCharges.setAmount(amount);
		eventCharges.setCreatedBy(1);
		eventCharges.setStatus("y");
		eventCharges.setIpAddress(IpAddress);
		
		eventdao.addRegistrationCharges(eventCharges);

		return "";
	}
	

	@RequestMapping(value = "/addEventImages", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String addEventImages(@RequestParam(value = "eventimage", required = false) MultipartFile[] eventimage,
			HttpServletRequest request, HttpSession session, String eventimagetitle[], String eventimagedescription[],
			String createddate, String createdtime) {
		logger.info("********** INSIDE ADD EVENT IMAGE **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		int eventid = eventdao.getLastEventId();
		String image = null;
		try {
			for (int i = 0; i < eventimage.length; i++) {
				if (eventimage[i].getOriginalFilename() != null && eventimage[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = eventimage[i].getBytes();

						File dir = new File(
								request.getRealPath("") + "/resources/admin/images/" + File.separator + "event");
						if (!dir.exists())
							dir.mkdirs();
						String path = request.getRealPath("/resources/admin/images/event/");
						File uploadfile = new File(path + File.separator + eventimage[i].getOriginalFilename());

						/********* Today Start **********/

						int height = 416, width = 833;

						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							/*
							 * BufferedImage img = ImageIO.read(in);
							 * 
							 * int original_width = img.getWidth(); int
							 * original_height = img.getHeight(); int
							 * bound_width = 700; int bound_height = 350;
							 * 
							 * if (original_height/bound_height >
							 * original_width/bound_width) { bound_width = (int)
							 * (bound_height * original_width /
							 * original_height); } else { bound_height = (int)
							 * (bound_width * original_height / original_width);
							 * }
							 * 
							 * Image scaledImage =
							 * img.getScaledInstance(bound_width, bound_height,
							 * Image.SCALE_SMOOTH);
							 * 
							 * 
							 * BufferedImage imageBuff = new
							 * BufferedImage(width, height,
							 * BufferedImage.TYPE_INT_RGB);
							 * 
							 * Graphics2D drawer = imageBuff.createGraphics() ;
							 * drawer.setBackground(Color.WHITE);
							 * drawer.clearRect(0,0,width,height);
							 * 
							 * imageBuff.getGraphics().drawImage(scaledImage,
							 * (width-bound_width)/2, (height-bound_height)/2,
							 * new Color(0,0,0), null);
							 * 
							 * ByteArrayOutputStream buffer = new
							 * ByteArrayOutputStream();
							 * 
							 * ImageIO.write(imageBuff, "jpg", buffer);
							 * 
							 * bytes = buffer.toByteArray();
							 */
							BufferedImage img = ImageIO.read(in);

							Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_FAST);

							BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

							Graphics2D drawer = imageBuff.createGraphics();
							drawer.drawImage(scaledImage, 0, 0, null);
							drawer.dispose();

							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(imageBuff, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {
							// throw new ApplicationException("IOException in
							// scale");
						}

						/********* Today End **********/

						System.out.println("*********************Path" + path);

						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();
						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}
						// image = request.getScheme() + "://" +
						// request.getServerName() + ":" +
						// request.getServerPort()+"/rmbv/resources/admin/images/event/"+eventimage[i].getOriginalFilename();
						image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/resources/admin/images/event/" + eventimage[i].getOriginalFilename();

						eventimages = new EventImage(eventid, image, eventimagetitle[i + 1],
								eventimagedescription[i + 1], 1, createddate, createdtime, IpAddress);
						eventdao.addEventImages(eventimages);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/addEventUrl", method = RequestMethod.POST)
	public String addEventUrl(HttpServletRequest request, int eventurlsequence, String eventurltitle, String url,
			String createddate, String createdtime) {
		logger.info("********** INSIDE ADD EVENT URL **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		int eventid = eventdao.getLastEventId();

		eventurl = new EventUrl(eventid, eventurltitle, url, eventurlsequence, 1, createddate, createdtime, IpAddress);

		eventdao.addEventUrl(eventurl);

		return "";
	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.DELETE)
	public void delete(String eventid) {
		logger.info("********** INSIDE DELETE EVENT ********** ");
		int id = Integer.parseInt(eventid);
		eventdao.deleteEvent(id);
	}

	@RequestMapping(value = "/deleteSelectedEventAgenda", method = RequestMethod.DELETE)
	public void delete(int eventid) {
		logger.info("********** INSIDE DETELE SELECTED EVENT AGENDA **********");

		eventdao.deleteSelectedEventAgenda(eventid);
	}

	@RequestMapping(value = "/deleteSelectedEventImages", method = RequestMethod.DELETE)
	public void deleteimage(int eventid) {
		logger.info("********** INSIDE DELETE DELECTED EVENT IMAGES **********");

		eventdao.deleteSelectedEventImages(eventid);
	}

	@RequestMapping(value = "/deleteSelectedEventUrl", method = RequestMethod.DELETE)
	public void deleteurl(int eventid) {
		logger.info("********** INSIDE DETELE SELECTED EVENT URL **********");

		eventdao.deleteSelectedEventUrl(eventid);
	}

	@RequestMapping(value = "editEvent", method = RequestMethod.POST)
	public String editEvent(int eventid, int eventtype, String eventtitle, String eventsubtitle, String eventdate,
			String eventtime, String eventvenue, String eventmaplocation, String eventdescription, String registration, String paid, float rmbfbmember, float rotarian, float others, String createddate,
			String createdtime, HttpServletRequest request, HttpSession session) {
		logger.info("********** INSIDE EDIT EVENT **********");

		String s = "y";
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		try {
			event = new Event(eventid, eventtype, eventtitle, eventsubtitle, eventvenue, eventmaplocation, eventdate,
					eventtime, URLDecoder.decode(eventdescription, "UTF-8"), registration, paid, rmbfbmember, rotarian, others, s, 1, createddate, createdtime, IpAddress);
			eventdao.editEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/editEventAgenda", method = RequestMethod.POST)
	public String editEventAgenda(HttpServletRequest request, int eventid, int eventagendasequence,
			String eventagendatitle, String eventagendaconclusion, String createddate, String createdtime) {
		logger.info("********** INSIDE EDIT EVENT AGENDA **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		eventagenda = new EventAgenda(eventid, eventagendatitle, eventagendasequence, eventagendaconclusion, 1,
				createddate, createdtime, IpAddress);

		eventdao.addEventAgenda(eventagenda);

		return "";
	}

	@RequestMapping(value = "/editEventImages", method = RequestMethod.POST)
	public String editEventImages(@RequestParam(value = "eventimage", required = false) MultipartFile[] eventimage,
			HttpServletRequest request, HttpSession session, int eventid, String eventimagetitle[],
			String eventimagedescription[], String createddate, String createdtime) {
		logger.info("********** INSIDE EDIT EVENT IMAGE **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		String image = null;

		try {
			for (int i = 0; i < eventimage.length; i++) {
				if (eventimage[i].getOriginalFilename() != null && eventimage[i].getOriginalFilename() != "") {
					try {
						byte[] bytes = eventimage[i].getBytes();

						File dir = new File(
								request.getRealPath("") + "/resources/admin/images/" + File.separator + "event");
						if (!dir.exists())
							dir.mkdirs();

						String path = request.getRealPath("/resources/admin/images/event/");
						File uploadfile = new File(path + File.separator + eventimage[i].getOriginalFilename());

						/********* Today Start **********/

						int height = 416, width = 833;

						ByteArrayInputStream in = new ByteArrayInputStream(bytes);
						try {
							/*
							 * BufferedImage img = ImageIO.read(in);
							 * 
							 * int original_width = img.getWidth(); int
							 * original_height = img.getHeight(); int
							 * bound_width = 700; int bound_height = 350;
							 * 
							 * if (original_height/bound_height >
							 * original_width/bound_width) { bound_width = (int)
							 * (bound_height * original_width /
							 * original_height); } else { bound_height = (int)
							 * (bound_width * original_height / original_width);
							 * }
							 * 
							 * Image scaledImage =
							 * img.getScaledInstance(bound_width, bound_height,
							 * Image.SCALE_SMOOTH);
							 * 
							 * 
							 * BufferedImage imageBuff = new
							 * BufferedImage(width, height,
							 * BufferedImage.TYPE_INT_RGB);
							 * 
							 * Graphics2D drawer = imageBuff.createGraphics() ;
							 * drawer.setBackground(Color.WHITE);
							 * drawer.clearRect(0,0,width,height);
							 * 
							 * imageBuff.getGraphics().drawImage(scaledImage,
							 * (width-bound_width)/2, (height-bound_height)/2,
							 * new Color(0,0,0), null);
							 * 
							 * ByteArrayOutputStream buffer = new
							 * ByteArrayOutputStream();
							 * 
							 * ImageIO.write(imageBuff, "jpg", buffer);
							 * 
							 * bytes = buffer.toByteArray();
							 */

							BufferedImage img = ImageIO.read(in);

							Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_FAST);

							BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

							Graphics2D drawer = imageBuff.createGraphics();
							drawer.drawImage(scaledImage, 0, 0, null);
							drawer.dispose();

							ByteArrayOutputStream buffer = new ByteArrayOutputStream();
							ImageIO.write(imageBuff, "jpg", buffer);
							bytes = buffer.toByteArray();
						} catch (IOException e) {
							// throw new ApplicationException("IOException in
							// scale");
						}

						/********* Today End **********/

						System.out.println("*********************Path" + path);

						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
								new FileOutputStream(uploadfile));
						bufferedOutputStream.write(bytes);
						bufferedOutputStream.close();

						File f = new File(path);
						File files[] = f.listFiles();

						for (int j = 0; j < files.length; j++) {
							if (files[j].isFile()) {
								System.out.println("File " + files[j].getName() + " " + files[j].length());
							}
						}
						// image = request.getScheme() + "://" +
						// request.getServerName() + ":" +
						// request.getServerPort()+"/rmbv/resources/admin/images/event/"+eventimage[i].getOriginalFilename();
						image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
								+ "/resources/admin/images/event/" + eventimage[i].getOriginalFilename();

						eventimages = new EventImage(eventid, image, eventimagetitle[i + 1],
								eventimagedescription[i + 1], 1, createddate, createdtime, IpAddress);

						eventdao.addEventImages(eventimages);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/editEventUrl", method = RequestMethod.POST)
	public String editEventUrl(HttpServletRequest request, int eventid, String eventurltitle, String url,
			int eventurlsequence, String createddate, String createdtime) {
		logger.info("********** INSIDE EDIT EVENT URL **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		eventurl = new EventUrl(eventid, eventurltitle, url, eventurlsequence, 1, createddate, createdtime, IpAddress);

		eventdao.addEventUrl(eventurl);

		return "";
	}

	@RequestMapping(value = "/editEventImagesNew", method = RequestMethod.POST)
	public String editEventImagesNew(HttpServletRequest request, HttpSession session, int eventid,
			String eventimagetitle, String eventimagedescription, String image, String createddate,
			String createdtime) {
		logger.info("********** INSIDE EDIT EVENT IMAGES NEW **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		eventimages = new EventImage(eventid, image, eventimagetitle, eventimagedescription, 1, createddate,
				createdtime, IpAddress);
		eventdao.addEventImages(eventimages);

		return "";
	}

	@RequestMapping(value = "/getEventDetailById", method = RequestMethod.GET, produces = "application/json")
	public Event getEventDetailById(int id, HttpServletRequest request) {
		logger.info("********** INSIDE GET EVENT DETAIL BY ID **********");

		Event event = eventdao.getEventDetailById(id);

		return event;
	}

	@RequestMapping(value = "/AddCommingResponse", method = RequestMethod.POST)
	public String AddCommingResponse(HttpServletRequest request, int id, int memberid, String joinself,
			String joinspouse, String joinannet, int noofannetjoin, String comment) {
		logger.info("********** INSIDE ADD MEMBER COMMING RESPONSE **********");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String coming = "Y";
		String notcoming = "N";
		memberresponse = new MemberResponse(memberid, id, joinself, joinspouse, joinannet, noofannetjoin, comment,
				coming, notcoming, IpAddress);

		eventdao.AddCommingResponse(memberresponse);
		return "";
	}

	@RequestMapping(value = "/AddNotComingResponse", method = RequestMethod.POST)
	public String AddNotComingResponse(HttpServletRequest request, int id, int memberid, String notcomingreason) {
		logger.info("********** INSIDE ADD MEMBER NOT COMMING RESPONSE **********");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String coming = "N";
		String notcoming = "Y";
		memberresponse = new MemberResponse(memberid, id, notcomingreason, coming, notcoming, IpAddress);

		eventdao.AddNotComingResponse(memberresponse);
		return "";
	}

	@RequestMapping(value = "/getMemberEventPaymentStatus", method = RequestMethod.GET, produces = "application/json")
	public EventRegistration getMemberEventPaymentStatus(int id, int memberid, HttpServletRequest request) {
		logger.info("********** INSIDE GET MEMBER EVENT PAYMENT STATUS **********");

		EventRegistration eventregistration = eventdao.getMemberEventPaymentStatus(id, memberid);

		return eventregistration;
	}
	
	@RequestMapping(value = "/getMemberEventRegistrationStatus", method = RequestMethod.GET, produces = "application/json")
	public EventRegistration getMemberEventRegistrationStatus(int id, int memberid, HttpServletRequest request) {
		logger.info("********** INSIDE GET MEMBER EVENT REGISTRATION STATUS **********");

		EventRegistration eventregistration = eventdao.getMemberEventRegistrationStatus(id, memberid);

		return eventregistration;
	}

	
	@RequestMapping(value = "/editRegistrationCharges", method = RequestMethod.POST)
	public String editRegistrationCharges(HttpServletRequest request, int id, String registrationfor, int currencyid, float amount) {
		logger.info("********** INSIDE EDIT EVENT CHARGES **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		/*int eventid = eventdao.getLastEventId();*/

		eventCharges = new EventCharges();
		eventCharges.setEventId(id);
		eventCharges.setRegistrationFor(registrationfor);
		eventCharges.setCurrencyId(currencyid);
		eventCharges.setAmount(amount);
		eventCharges.setCreatedBy(1);
		eventCharges.setStatus("y");
		eventCharges.setIpAddress(IpAddress);
		
		eventdao.addRegistrationCharges(eventCharges);

		return "";
	}
	
	@RequestMapping(value = "/updateRegistrationCharges", method = RequestMethod.POST)
	public String updateRegistrationCharges(HttpServletRequest request, int id, int event_id, String registrationfor, int currencyid, float amount) {
		logger.info("********** INSIDE update EVENT CHARGES **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}

		/*int eventid = eventdao.getLastEventId();*/

		eventCharges = new EventCharges();
		eventCharges.setEventChargeId(id);
		eventCharges.setEventId(event_id);
		eventCharges.setRegistrationFor(registrationfor);
		eventCharges.setCurrencyId(currencyid);
		eventCharges.setAmount(amount);
		eventCharges.setCreatedBy(1);
		eventCharges.setStatus("y");
		eventCharges.setIpAddress(IpAddress);
		
		eventdao.updateRegistrationCharges(eventCharges);

		return "";
	}
	
	@RequestMapping(value = "/deleteEventCharge", method = RequestMethod.DELETE)
	public void deleteEventCharge(int id) {
		logger.info("********** INSIDE DELETE EVENT CHARGES ********** ");		
		eventdao.deleteEventCharge(id);
	}
	
	//Event registration 
	
	
	
	@RequestMapping(value = "addRotaryUser", method = RequestMethod.POST)
	public String addRotaryUser(String membertype, String firstname,String lastname, String gender, String email,String mobileno,String rotarian,String rmemberid,String rclubname,String rchapname,String cmpname,String cmpwebsite, int countryid, String cmpshortdesc, String cmpbusiness,
			String password, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD ROTARY NEW  MEMBERS *****");
		String result=null;
		//int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
							
		eventRegistration=new EventRegistration();
		eventRegistration.setFirstName(firstname);
		eventRegistration.setLastName(lastname);
		eventRegistration.setGender(gender);
		eventRegistration.setEmailId(email);
		eventRegistration.setMobileNo(mobileno);
		eventRegistration.setRotarian(rotarian);
		eventRegistration.setRotaryMemberId(rmemberid);
		eventRegistration.setRotaryClubName(rclubname);
		eventRegistration.setRotaryChapName(rchapname);
		eventRegistration.setCompName(cmpname);
		eventRegistration.setCompWebsite(cmpwebsite);
		eventRegistration.setCompCountryId(countryid);
		eventRegistration.setCompShortDesc(cmpshortdesc);
		eventRegistration.setCompBusiness(cmpbusiness);
		eventRegistration.setStatus(s);
		eventRegistration.setCreatedBy(0);
		eventRegistration.setIpAddress(IpAddress);
		eventRegistration.setMemberType(membertype);
		eventRegistration.setPassword(password);
						
		String checkemail = memberDAO.getMemberByEmailAndNumber(mobileno, email);
		
		if(checkemail=="Success") {
			String msg = "Email Or Mobile Already Registerd with Us.";
			return msg;
		} else {
			
			int mid = memberDAO.getLastMemberId();
			Members mem = memberDAO.getMemberDetailByMemberId(mid);
									
			int seq = mem.getSequence() + 1;
						
		   String newmembershipno="";
			
			String typemem = "";
			String family = "";
			//typemem = "RMBF";
									
			if(membertype.equals("Others")) {
										
				typemem = "OTHR";
			} else {
				typemem = "RMBF";
			}
			
			if(seq >= 0 && seq <= 9) {
				family = typemem + "0000" + seq;
				newmembershipno = family;
			} else if(seq >= 10 && seq <= 99) {
				family = typemem+ "000" + seq;
				newmembershipno = family;
			} else if(seq >= 100 && seq <= 999) {
				family = typemem+ "00" + seq;
				newmembershipno = family;
			} else if(seq >= 1000 && seq <= 9999) {
				family = typemem+ "0" + seq;
				newmembershipno = family;
			} else if(seq >= 10000 && seq <= 99999) {
				family = typemem + seq;
				newmembershipno = family;
			}			
						
			eventRegistration.setSequence(seq);
			eventRegistration.setMemberShipNumber(newmembershipno);
			result=eventdao.addRotaryUser(eventRegistration);
		}
								
		return result;
	}
	
	
	@RequestMapping(value = "eventRegistration", method = RequestMethod.POST)
	public String eventRegistration(int eventid,String membertype, String firstname,String lastname, String gender, String email,String mobileno,String rotarian,String rmemberid,String rclubname,String rchapname,String cmpname,String cmpwebsite, int countryid, String cmpshortdesc, String cmpbusiness,
			int memberid, String password, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		logger.info("*****  EVENT REGISTRATION *****");
		String result=null;
		//int id = Integer.parseInt(session.getAttribute("useridadmin").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
							
		eventRegistration=new EventRegistration();
		eventRegistration.setFirstName(firstname);
		eventRegistration.setLastName(lastname);
		eventRegistration.setGender(gender);
		eventRegistration.setEmailId(email);
		eventRegistration.setMobileNo(mobileno);
		eventRegistration.setRotarian(rotarian);
		eventRegistration.setRotaryMemberId(rmemberid);
		eventRegistration.setRotaryClubName(rclubname);
		eventRegistration.setRotaryChapName(rchapname);
		eventRegistration.setCompName(cmpname);
		eventRegistration.setCompWebsite(cmpwebsite);
		eventRegistration.setCompCountryId(countryid);
		eventRegistration.setCompShortDesc(cmpshortdesc);
		eventRegistration.setCompBusiness(cmpbusiness);
		eventRegistration.setStatus(s);
		eventRegistration.setCreatedBy(0);
		eventRegistration.setIpAddress(IpAddress);
		eventRegistration.setMemberType(membertype);
		eventRegistration.setMemberId(memberid);
		eventRegistration.setEventId(eventid);
		eventRegistration.setPassword(password);
		
		if(memberid==0) {
			String checkemail = memberDAO.getMemberByEmailAndNumber(mobileno, email);
			
			if(checkemail=="Success") {
				String msg = "Unsuccess";
				return msg;
			} else {
				
				int mid = memberDAO.getLastMemberId();
				Members mem = memberDAO.getMemberDetailByMemberId(mid);
										
				int seq = mem.getSequence() + 1;
							
			   String newmembershipno="";
				
				String typemem = "";
				String family = "";
				//typemem = "RMBF";
										
				if(membertype.equals("RMBFB Member")) {
					typemem = "RMBF";
				} else {
					typemem = "OTHR";
					
				}
				
				
				if(seq >= 0 && seq <= 9) {
					family = typemem + "0000" + seq;
					newmembershipno = family;
				} else if(seq >= 10 && seq <= 99) {
					family = typemem+ "000" + seq;
					newmembershipno = family;
				} else if(seq >= 100 && seq <= 999) {
					family = typemem+ "00" + seq;
					newmembershipno = family;
				} else if(seq >= 1000 && seq <= 9999) {
					family = typemem+ "0" + seq;
					newmembershipno = family;
				} else if(seq >= 10000 && seq <= 99999) {
					family = typemem + seq;
					newmembershipno = family;
				}			
							
				eventRegistration.setSequence(seq);
				eventRegistration.setMemberShipNumber(newmembershipno);
			
				result=eventdao.addRotaryUser(eventRegistration);
				
				memberid = memberDAO.getLastMemberId();
				
				RotaryYear rotaryYear = rotaryyeardao.getCurrentRotaryYear();
				memberCategoryByYear = new MemberCategoryByYear(rotaryYear.getRotaryYearId(), memberid, 7, 1,
						IpAddress);
				memberDAO.addMemberCategoryByRotaryYear(memberCategoryByYear);
				
				if(result.equals("Success")) {
					int mid2 = memberDAO.getLastMemberId();
					eventRegistration.setMemberId(mid2);
					
					frontLoginForRegistration(session, email,password,response);
					eventdao.eventRegistration(eventRegistration);
					//payPaymentOffile(eventid,mid2,firstname,lastname,email,mobileno,countryid,request,session);
					//sendEventRegistrationEmail(firstname,lastname,email,mobileno,session, request);
				}
				
			}
				
		} else {
			/*String membertyp = session.getAttribute("membertypestatus");
			eventRegistration.setMemberType();*/
			result =eventdao.eventRegistration(eventRegistration);
			//payPaymentOffile(eventid,memberid,firstname,lastname,email,mobileno,countryid,request,session);
			//sendEventRegistrationEmail(firstname,lastname,email,mobileno,session, request);
		}
		return result;
	}
	
	
	@RequestMapping(value = "payPaymentOffile", method = RequestMethod.POST)
	public String payPaymentOffile(int eventid, int memberid , String firstname, String lastname, String email, String mobileno, int countryname, HttpServletRequest request,
			HttpSession session) {
		logger.info("***** ADD ORDER *****");
		
		//int id = Integer.parseInt(session.getAttribute("userid").toString());
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		String result = null;
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		String orderno = null;
		String ordernumber = null;

		int sequence = 0;

		Members m = memberDAO.getMemberDetailByMemberId(memberid);
		Country c = countryDAO.getCountryDetailById(countryname);

		Order od = paymentdao.getLastOrderDetail();
		
		
		if (od == null) {
			sequence = 1;
			orderno = "RMBFB-" + "ORD" + "-00001";
			
		} else {
			

			sequence = od.getSequence();

			if (sequence == 0) {
				sequence = 1;
				orderno = "RMBFB-" + "ORD" + "-00001";
			} else {
			   				
				if (sequence >= 1 && sequence < 10) {
					orderno = "RMBFB-" + "ORD"+ "-0000" + Integer.toString(sequence);
				} else if (sequence >= 10 && sequence < 100) {
					orderno = "RMBFB-" + "ORD"+ "-000" + Integer.toString(sequence);
				} else if (sequence >= 100 && sequence < 1000) {
					orderno = "RMBFB-" + "ORD" + "-00" + Integer.toString(sequence);
				} else if (sequence >= 1000 && sequence < 10000) {
					orderno = "RMBFB-" + "ORD" + "-0" + Integer.toString(sequence);
				} else if (sequence >= 10000 && sequence < 100000) {
					orderno = "RMBFB-" + "ORD" + Integer.toString(sequence);
				}
			}
		}
		Order order = new Order();
	    
		order.setSequence(sequence + 1);
		order.setOrderNumber(orderno);
		
		order.setEventId(eventid);
		order.setCustomerName(m.getMemberFirstName() + " " + m.getMemberLastName());
		order.setCustomerEmailid(m.getMemberEmail());
		order.setCustomerMobileno(m.getMemberMobileNumber());

		order.setBillerName(firstname + " " + lastname);
		order.setBillerEmailid(email);
		order.setBillerMobileno(mobileno);
		order.setBillerAddress1(" ");
		order.setBillerAddress2(" ");
		order.setBillerCountryName(c.countryName);
		order.setBillerStateName("");
		order.setBillerCityName("");
		order.setBillerPincode("");
		
		//order.setOrderDate(q.getCreatedDate());
				
		order.setTotalAmount(0);
		order.setSubTotal(0);

		order.setMemberId(memberid);
		order.setOrderStatus("New");
		order.setPaymentStatus("Unpaid");
		order.setPaymentMode("Offline");
		//order.setOrderDate(q.getCreatedDate());
		order.setCreatedBy(memberid);
		order.setIpAddress(IpAddress);

		session.setAttribute("totalamount", order.getTotalAmount());
		
		session.setAttribute("billingname", order.getBillerName());
		session.setAttribute("billingemail", order.getBillerEmailid());
		session.setAttribute("billingaddress1", order.getBillerAddress1());
		session.setAttribute("billingaddress2", order.getBillerAddress2());
		session.setAttribute("billingcountryname", order.getBillerCountryName());
		session.setAttribute("billingstatename", order.getBillerStateName());
		session.setAttribute("billingcityname", order.getBillerCityName());
		session.setAttribute("billingpincode", order.getBillerPincode());
		session.setAttribute("billingmobileno", order.getBillerMobileno());
				
		paymentdao.addOrder(order);

		ordernumber = paymentdao.getLastOrderDetail().getOrderNumber();

		session.setAttribute("ordernumber", ordernumber);

		return result;
	}
	
	
	@RequestMapping(value = "frontLoginForRegistration", method = RequestMethod.POST)
	public String frontLoginForRegistration(HttpSession session, String email, String password, HttpServletResponse response) {

		logger.info("Inside Front User Login For Registration");

		
		Members members = loginDAO.frontLogin(email, password);
		
		if (members != null) {
			session.setMaxInactiveInterval(30 * 60); // Set Session Time Out
			session.setAttribute("loginid", members.getMemberId());
			session.setAttribute("memberid", String.valueOf(members.getMemberId()));
			session.setAttribute("membershipnumber", members.getMembershipNumber());
			session.setAttribute("membername", members.getMemberFirstName());
			session.setAttribute("memberlastname", members.getMemberLastName());
			session.setAttribute("membermobilenumber", members.getMemberMobileNumber());
			session.setAttribute("memberemail", members.getMemberEmail());
			
			session.setAttribute("memberstatus", members.getStatus());
			
			
			return "Successfully Login";
		} else {
			return null;	
		}		
	}
	
	@RequestMapping(value = "sendEventRegistrationEmail", method = RequestMethod.POST)
	public String sendEventRegistrationEmail(String firstname, String lastname, String email, String mobileno,
			HttpSession session, HttpServletRequest request) {

		logger.info("********** SEND EVENT REGISTRATION MESSAGE **********");
		final String username = "admin@rmbbangalore.org";
	    final String password = "m@n@g4$3RMBB";
	    
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");        
        props.put("mail.smtp.host", "mail.rmbbangalore.org");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        javax.mail.Session session1 = javax.mail.Session.getInstance(props,
        		new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication(username, password);
        			}
        		});

        try {
        	
       		InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
           	Message message = new MimeMessage(session1);
           	message.setFrom(new InternetAddress(username,"RMBFB")); 
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
           	message.setRecipients(Message.RecipientType.BCC,myBccList);
           	message.setSubject("Event Registration from RMBFB");
           	StringBuilder sb = new StringBuilder();
           	sb.append("<br><h3> \n Dear "+firstname+" "+lastname+",</h3>");
	          sb.append("<br/><h2> Thank you for Registration. We have received your request about event registration.</h2>");
	          sb.append("<br/><h2> For Any Queries, you may contact us<br/><br/></h2>");
	          sb.append("<br/><br/><h2> With Regards,</h2>");
	          sb.append("<h2> RMBF, Bangalore</h2>");        	
           	
            message.setContent(sb.toString(), "text/html");

            Transport.send(message);

            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");	        	
        	
        } catch (Exception msg) {
        	System.out.println("Not send mail "+msg);
        }
        
        try {
			String url = "http://sms.astartechnologies.net/sendsms.aspx";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			System.out.println("Member Mobile No--->" + mobileno);

			String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to="
					+ mobileno
					+ "&msg=Thank you for Registration. We have received your message about event registration and will get back to you soon, Thank you.";

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "Success";
	}
	
	
	@RequestMapping(value = "/updateEventPayment", method = RequestMethod.POST)
	public String updateEventPayment(HttpServletRequest request, int memberid, int eid, String paymentrefno, float amount) {
		logger.info("********** INSIDE UPDATE EVENT PAYMENT **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		eventRegistration=new EventRegistration();
		eventRegistration.setMemberId(memberid);
		eventRegistration.setEventId(eid);
		eventRegistration.setPaymentRefNo(paymentrefno);
		eventRegistration.setAmount(amount);
		eventRegistration.setPayStat("Bank");

		eventdao.updateEventPayment(eventRegistration);
		eventdao.updateOrdersPayment(eventRegistration);
		sendEventPaymentEmail(memberid,request);
		return "";
	}
	
	@RequestMapping(value = "/updateEventRegistrationPayment", method = RequestMethod.POST)
	public String updateEventRegistrationPayment(HttpServletRequest request, int memberid, int eid) {
		logger.info("********** INSIDE UPDATE EVENT REGISTRATION PAYMENT **********");

		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		eventRegistration=new EventRegistration();
		eventRegistration.setMemberId(memberid);
		eventRegistration.setEventId(eid);
		eventRegistration.setAmount(0);

		eventdao.updateOrdersPaidPayment(eventRegistration);
		eventdao.updateEventRegistrationPayment(eventRegistration);
		
		sendEventregistrationEmail(memberid,request);
		return "";
	}
	
	@RequestMapping(value = "sendEventregistrationEmail", method = RequestMethod.POST)
	public String sendEventregistrationEmail(int memberid, HttpServletRequest request) {

		logger.info("********** SEND EVENT PAYMENT MESSAGE **********");
		
		
		Members m = memberDAO.getMemberDetailByMemberId(memberid);
		String mobileno = m.getMemberMobileNumber();
		String email = m.getMemberEmail();
		String firstname = m.getMemberFirstName();
		String lastname = m.getMemberLastName();
		final String username = "admin@rmbbangalore.org";
	    final String password = "m@n@g4$3RMBB";
	    
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");        
        props.put("mail.smtp.host", "mail.rmbbangalore.org");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        javax.mail.Session session1 = javax.mail.Session.getInstance(props,
        		new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication(username, password);
        			}
        		});

        try {
        	
       		InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
           	Message message = new MimeMessage(session1);
           	message.setFrom(new InternetAddress(username,"RMBFB")); 
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
           	message.setRecipients(Message.RecipientType.BCC,myBccList);
           	message.setSubject("Event Registration from RMBFB");
           	StringBuilder sb = new StringBuilder();
           	sb.append("<br><h3> \n Dear "+firstname+" "+lastname+",</h3>");
	          sb.append("<br/><h2> You're successfully registered for RMBFB event.<br>Thank You</h2>");
	          sb.append("<br/><h2> For Any Queries, you may contact us<br/></h2>");
	          sb.append("<br/><h2> With Regards,</h2>");
	          sb.append("<h2> RMBF, Bangalore</h2>");        	
           	
            message.setContent(sb.toString(), "text/html");

            Transport.send(message);

            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");	        	
        	
        } catch (Exception msg) {
        	System.out.println("Not send mail "+msg);
        }
        
        try {
			String url = "http://sms.astartechnologies.net/sendsms.aspx";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			System.out.println("Member Mobile No--->" + mobileno);

			String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to="
					+ mobileno
					+ "&msg=You're successfully registered for RMBFB event.Thank you , RMBF Bangalore.";

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "Success";
	}
	
	
	@RequestMapping(value = "sendEventPaymentEmail", method = RequestMethod.POST)
	public String sendEventPaymentEmail(int memberid, HttpServletRequest request) {

		logger.info("********** SEND EVENT PAYMENT MESSAGE **********");
		
		
		Members m = memberDAO.getMemberDetailByMemberId(memberid);
		String mobileno = m.getMemberMobileNumber();
		String email = m.getMemberEmail();
		String firstname = m.getMemberFirstName();
		String lastname = m.getMemberLastName();
		final String username = "admin@rmbbangalore.org";
	    final String password = "m@n@g4$3RMBB";
	    
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");        
        props.put("mail.smtp.host", "mail.rmbbangalore.org");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        
        
        javax.mail.Session session1 = javax.mail.Session.getInstance(props,
        		new javax.mail.Authenticator() {
        		protected PasswordAuthentication getPasswordAuthentication() {
        			return new PasswordAuthentication(username, password);
        			}
        		});

        try {
        	
       		InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
           	Message message = new MimeMessage(session1);
           	message.setFrom(new InternetAddress(username,"RMBFB")); 
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
           	message.setRecipients(Message.RecipientType.BCC,myBccList);
           	message.setSubject("Event Registration Payment from RMBFB");
           	StringBuilder sb = new StringBuilder();
           	sb.append("<br><h3> \n Dear "+firstname+" "+lastname+",</h3>");
	          sb.append("<br/><h2> Thank you for payment request for RMBF event.We'll get back to you after your payment verification.  </h2>");
	          sb.append("<br/><h2> For Any Queries, you may contact us<br/></h2>");
	          sb.append("<br/><h2> With Regards,</h2>");
	          sb.append("<h2> RMBF, Bangalore</h2>");        	
           	
            message.setContent(sb.toString(), "text/html");

            Transport.send(message);

            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");	        	
        	
        } catch (Exception msg) {
        	System.out.println("Not send mail "+msg);
        }
        
        try {
			String url = "http://sms.astartechnologies.net/sendsms.aspx";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			System.out.println("Member Mobile No--->" + mobileno);

			String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to="
					+ mobileno
					+ "&msg=Thank you for payment request for RMBF event. RMBF Bangalore, Thank you.";

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "Success";
	}	
	
	@RequestMapping(value = "/getLastMemberbyEmail", method = RequestMethod.GET, produces = "application/json")
	public int getEventDetailById(String email,String mobileno, HttpServletRequest request) {
		logger.info("********** INSIDE GET LAST MEMBER ID BY EMAIL **********");
		int id=0;
		id = memberDAO.getLastMemberByEmail(email,mobileno);

		return id;
	}
	
} //end
