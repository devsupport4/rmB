package com.ui.controller;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import java.util.Hashtable;
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

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ui.dao.EventDAO;
import com.ui.dao.MemberDAO;
import com.ui.model.Event;
import com.ui.model.MemberCategoryByYear;
import com.ui.model.MemberLandlinePhoneNumber;
import com.ui.model.Members;
import com.ui.model.MembersFamily;

@RestController
public class MemberController {

	@Autowired
	MemberDAO memberdao;
	@Autowired
	EventDAO eventdao;

	Members members;
	MembersFamily membersfamily;
	MemberLandlinePhoneNumber memberlandlinephonenumber;
	MemberCategoryByYear memberCategoryByYear;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "/getLastMemberSequenceByCategory", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getLastMemberSequenceByCategory(HttpServletRequest request) {
		logger.info("Inside Get Last Member Sequence By Category");

		List<Members> sequence = memberdao.getLastMemberSequenceByCategory();

		return sequence;
	}

	@RequestMapping(value = "/getMember", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMember(HttpServletRequest request) {
		logger.info("Inside Get All Members");
		List<Members> members = memberdao.getAllMembers();
		return members;
	}
	
	@RequestMapping(value = "/getAllMembersPics", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllMembersPics(HttpServletRequest request) {
		logger.info("Inside Get All Members Pics");
		List<Members> members = memberdao.getAllMembersPics();
		return members;
	}
	
	@RequestMapping(value = "/getAllMembersDirectory", method = RequestMethod.GET, produces = "application/json")
    public List<Members> getAllMembersDirectory(HttpServletRequest request) {
        logger.info("Inside Get All Members");
        List<Members> members = memberdao.getAllMembersDirectory();
        return members;
    }

	@RequestMapping(value = "/getLastEightMembersPics", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getLastEightMembersPics(HttpServletRequest request) {
		logger.info("***** GET LAST EIGHT MEMBERS PICS *****");
		List<Members> members = memberdao.getLastEightMembersPics();
		return members;
	}

	/*@RequestMapping(value = "/getAllMembersDirectory", method = RequestMethod.GET, produces = "application/json")
    public List<Members> getAllMembersDirectory(HttpServletRequest request) {
        logger.info("Inside Get All Members");
        List<Members> members = memberdao.getAllMembersDirectory();
        return members;
    }*/
	
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
    public String resetPassword(String email, String newpassword, HttpServletRequest request, HttpSession session) {
        logger.info("********** INSIDE RESET PASSWORD CONTROLLER **********");
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        int id = 0;
        members = new Members(email, newpassword, IpAddress, id);
        memberdao.resetPassword(members);

        return "";
    }
	
	@RequestMapping(value = "/getMemberAndMemberFamily", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMemberAndMemberFamily(HttpServletRequest request) {
		logger.info("Inside Get All Members and Members Family");
		List<Members> members = memberdao.getAllMemberAndMemberFamily();
		return members;
	}

	@RequestMapping(value = "/getAllBirthdays", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllBirthdays(HttpServletRequest request, String birthmonth) {
		logger.info("********** INSIDE GET ALL MEMBERS BIRTHDAYS **********");

		List<Members> Members = memberdao.getAllBirthdays(birthmonth);

		return Members;
	}

	@RequestMapping(value = "/getFistFourBirthdaysByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getFistFourBirthdaysByDate(HttpServletRequest request, String currentdate) {
		logger.info("***** GET FIRST FOUR BIRTHDAYS BY DATE *****");
		List<Members> Members = memberdao.getFistFourBirthdaysByDate(currentdate);
		return Members;
	}

	@RequestMapping(value = "/getAllBirthdaysByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllBirthdaysByDate(HttpServletRequest request, String currentdate) {
		logger.info("********** INSIDE GET ALL MEMBERS BIRTHDAYS BY DATE**********");
		List<Members> Members = memberdao.getAllBirthdaysByDate(currentdate);
		return Members;
	}

	@RequestMapping(value = "/getAllAnniversaries", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllAnniversaries(HttpServletRequest request, String anniversarymonth) {
		logger.info("********** INSIDE GET ALL MEMBERS ANNIVERSARIES **********");
		List<Members> Members = memberdao.getAllAnniversaries(anniversarymonth);
		return Members;
	}

	@RequestMapping(value = "/getAllAnniversariesByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllAnniversariesByDate(HttpServletRequest request, String currentdate) {
		logger.info("********** INSIDE GET ALL MEMBERS ANNIVERSARIES BY DATE **********");

		List<Members> Members = memberdao.getAllAnniversariesByDate(currentdate);

		return Members;
	}
	
	@RequestMapping(value = "/getLastEvent", method = RequestMethod.GET, produces = "application/json")
	public Event getLastEvent(HttpServletRequest request) {
		logger.info("********** INSIDE GET LAST EVENT **********");

		Event Event = eventdao.getLastEventDetail();

		return Event;
	}

	/*@RequestMapping(value = "/getLastEvent", method = RequestMethod.GET, produces = "application/json")
	public List<Event> getLastEvent(HttpServletRequest request) {
		logger.info("********** INSIDE GET LAST EVENT **********");

		List<Event> Event = eventdao.getLastEventDetail();

		return Event;
	}*/

	@RequestMapping(value = "/getspousesequence", method = RequestMethod.GET, produces = {
			"application/json; charset=UTF-8" })
	public List<MembersFamily> getspousesequence(HttpServletRequest request, int memberid) {
		List<MembersFamily> getspousesequence = memberdao.getSpouseSequence(memberid);
		return getspousesequence;
	}

	@RequestMapping(value = "/getspousedata", method = RequestMethod.GET, produces = {
			"application/json; charset=UTF-8" })
	public List<MembersFamily> getspousedata(HttpServletRequest request, int memberid) {
		List<MembersFamily> getspousedata = memberdao.getSpouseData(memberid);
		return getspousedata;
	}

	@RequestMapping(value = "/getMemberByMemberId", method = RequestMethod.GET, produces = "application/json")
	public Members getMemberByMemberId(int memberid, HttpServletRequest request) {
		logger.info("***** GET MEMBER BY MEMBER ID *****");
		Members members = memberdao.getMemberByMemberId(memberid);
		return members;
	}
	
	/*@RequestMapping(value = "/getMemberDetailByMemberId", method = RequestMethod.GET, produces = "application/json")
    public Members getMemberDetailByMemberId(int memberid, HttpServletRequest request) {
        logger.info("***** GET MEMBER DETAIL BY MEMBER ID *****");
        Members members = memberdao.getMemberDetailByMemberId(memberid);
        return members;
    }*/
	
	
	@RequestMapping(value = "/getMemberDetailByMemberId", method = RequestMethod.GET, produces = "application/json")
	public Members getMemberDetailByMemberId(int memberid, HttpServletRequest request) {
		logger.info("***** GET MEMBER DETAIL BY MEMBER ID *****");
		Members members = memberdao.getMemberDetailByMemberId(memberid);
		return members;
	}

	@RequestMapping(value = "/getMemberByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMemberByPage(int pagesize, int startindex, HttpServletRequest request,HttpSession session) {
		logger.info("***** GET MEMBERS BY PAGE *****");

		int fellowship_id = Integer.parseInt(session.getAttribute("fellowshipId").toString()); 
		int role_id = Integer.parseInt(session.getAttribute("roleId").toString()); 
		System.out.println("++++++++++++fellowship_id++++++++++++++"+fellowship_id);
		System.out.println("+++++++++++++++role_id+++++++++++"+role_id);
		List<Members> members;
		if(role_id == 1) {
	   members = memberdao.getMembersByPage(pagesize, startindex);
		}
		else {
		 members = memberdao.getMembersByPage(pagesize, startindex, fellowship_id);
		}
		return members;
	}

/*	@RequestMapping(value = "/getMemberByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMemberByPage1(int pagesize, int startindex, HttpServletRequest request,HttpSession session) {
		logger.info("***** GET MEMBERS BY PAGE *****");
		
		int fellowship_id = Integer.parseInt(session.getAttribute("fellowshipId").toString()); 
	//	System.out.println("++++++++++++++++++++++++++"+fellowship_id);
		List<Members> members = memberdao.getMembersByPage(pagesize, startindex, fellowship_id);
		return members;
	}
	*/
	
	@RequestMapping(value = "/getMemberForMembersDirectoryByPage", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMemberForMembersDirectoryByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("***** GET MEMBER FOR MEMBERS DIRECTORY BY PAGE *****");
		List<Members> members = memberdao.getMemberForMembersDirectoryByPage(pagesize, startindex);
		return members;
	}
	
	@RequestMapping(value = "/getAllMemberContactInfoForMailer", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllMemberContactInfoForMailer(HttpServletRequest request) {
		logger.info("***** GET ALL MEMBER FOR MAILER *****");
		List<Members> members = memberdao.getAllMemberContactInfoForMailer();
		return members;
	}
	
	@RequestMapping(value = "checkCurrentPassword", method = RequestMethod.POST)
    public String checkCurrentPassword(int memberid, String password, HttpServletRequest request, HttpSession session) {
        String pw = password.replace("$", "&");
        String pw1 = pw.replace("~", "#");
        String pw2 = pw1.replace("!", "%");
        Members members = memberdao.checkCurrentPassword(memberid, pw2);
        if (members != null) {
            return "Password Matched";
        } else {
            return null;
        }
    }
	
	
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String changePassword(int memberid, String password, HttpServletRequest request, HttpSession session) {
        String pw = password.replace("$", "&");
        String pw1 = pw.replace("~", "#");
        String pw2 = pw1.replace("!", "%");
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        members = new Members(memberid, pw2, IpAddress);
        memberdao.changePassword(members);
        return "";
    }
	

	@RequestMapping(value = "sendNotificationToMembers", method = RequestMethod.POST)
	public String sendNotificationToMembers(HttpSession session, HttpServletRequest request, HttpServletResponse res) {
		logger.info("********** INSIDE SEND NOTIFICATION TO MEMBERS **********");

		String notifyviaemail = (String) session.getAttribute("notifyviaemail");
		String notifyviasms = (String) session.getAttribute("notifyviasms");

		session.setAttribute("notifyviaemail", "No");
		session.setAttribute("notifyviasms", "No");

		System.out.println("Send Emails---->" + notifyviaemail);
		System.out.println("Send SMS------>" + notifyviasms);

		/*List<Event> event = eventdao.getLastEventDetail();
		int eventid = event.get(0).getEventId();
		String eventtitle = event.get(0).getEventTitle();
		String eventsubtitle = event.get(0).getEventSubtitle();
		String eventdate = event.get(0).getEventDate();
		String eventtime = event.get(0).getEventTime();
		String eventvenue = event.get(0).getEventVenue();
		String eventmaplocation = event.get(0).getEventMapLocation();
		String eventdescription = event.get(0).getEventDescription();*/
		Event event = eventdao.getLastEventDetail();
		int eventid = event.getEventId();
		String eventtitle = event.getEventTitle();
		String eventsubtitle = event.getEventSubtitle();
		String eventdate = event.getEventDate();
		String eventtime = event.getEventTime();
		String eventvenue = event.getEventVenue();
		String eventmaplocation = event.getEventMapLocation();
		String eventdescription = event.getEventDescription();

		List<Members> members = memberdao.getAllMembers();

		for (int i = 0; i < members.size(); i++) {

			String email = members.get(i).getMemberEmail();
			String memberFirstName = members.get(i).getMemberFirstName();
			String memberLastName = members.get(i).getMemberLastName();

			if (notifyviaemail.equals("Yes")) {
				logger.info("********** INSIDE IF **********");
				final String username = "admin@rmbbangalore.org";
			    final String password = "m@n@g4$3RMBB";
			    
		        Properties props = new Properties();
		        props.put("mail.smtp.auth", "true");        
		        props.put("mail.smtp.host", "mail.rmbbangalore.org");
		        props.put("mail.smtp.port", "587");
		        props.put("mail.smtp.starttls.enable", "true");
				javax.mail.Session session1 = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {

					InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");
					Message message = new MimeMessage(session1);
					message.setFrom(new InternetAddress(username, "RMBFB"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
					message.setRecipients(Message.RecipientType.BCC, myBccList);
					message.setSubject(eventtitle);
					StringBuilder sb = new StringBuilder();
					sb.append(
							"<table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
									+ "<tbody><tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737; ' align='center' valign='middle'> <a href='http://www.rmbbangalore.org/'> <img border='0' src=''/></a></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td>"
									+ "</tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear <strong> "
									+ memberFirstName + " " + memberLastName
									+ "</strong></p><table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
									+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:18px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> "
									+ eventtitle
									+ "</td></tr><tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr><tr><td align='left' valign='top' width='412'><table width='100%' cellspacing='0' cellpadding='4' border='0'><tbody>"
									+ "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'><strong> Date: </strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='77%'> "
									+ eventdate
									+ " </td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'><strong> Time: </strong></td>"
									+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top'> "
									+ eventtime
									+ " </td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'><strong> Location:</strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top'> "
									+ eventvenue + "</td>"
									+ "</tr><tr><td colspan='2' style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737;    line-height: 22px;' align='left' valign='top'>"
									+ "" + eventdescription + ""
									+ "</td></tr><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:14px;color:#0072c6; ' align='left' valign='top' height='15'>"
									+ "</td></tr><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:14px;     color: #373737; border-top:solid 1px #dbdfe6' align='left' valign='top' ><b> Please <a style='    color: #0072c6;' target='_blank'  href='http://www.ultrasmartsolutions.com/rmbfbangalore/event_detail?id="
									+ eventid
									+ "'> CLICK HERE  </a> to let the club know whether you are attending to it or not.</b></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>");

					message.setContent(sb.toString(), "text/html");

					Transport.send(message);

					System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");

				} catch (Exception msg) {
					System.out.println("Not send mail " + msg);
				}
			}
			if (notifyviasms.equals("Yes")) {
				String memberMobileNumber = members.get(i).getMemberMobileNumber();

				try {
					String url = "http://sms.astartechnologies.net/sendsms.aspx";
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("POST");
					con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

					System.out.println("Member Mobile No--->" + memberMobileNumber);

					String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to="
							+ memberMobileNumber
							+ "&msg=This is RMBFB Event Notificaton, Please visit RMBFB Official Website for more details, Thank you.";

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
			}
		}

		return "";
	}

	@RequestMapping(value = "/addmemberdetail", method = RequestMethod.POST)
	public String addmemberdetail(@RequestParam(value = "profile", required = false) MultipartFile profile,
			int rotaryyearid, String membershipid, int memberclubname, int membercategoryname, int membertypename,
			int businesscategoryid, String tenureplan, String joiningdate, String startdate, String enddate,
			String membertitle, String firstname, String middlename, String lastname, String gender, String dateofbirth,
			String bloodgroup, String anniversarydate, String aadharnumber, int countrynamecitizenship,
			String passportnumber, String pannumber, String mobile, String email, String password, int sequence, int vocation, int valuex,
			int valuey, int valuew, int valueh,int fellowship_id, HttpSession session, HttpServletRequest request) throws IOException {
		logger.info("*****************Inside Add Member Detail Controller**********************");

		int memberid = 0;

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (joiningdate == "") {
			joiningdate = null;
		}
		if (startdate == "") {
			startdate = null;
		}
		if (dateofbirth == "") {
			dateofbirth = null;
		}
		if (anniversarydate == "") {
			anniversarydate = null;
		}
		if(email == "") {
			email = null;
		}
		if(mobile == "") {
			mobile = null;
		}
		
		String memberType="Others";
		
		String FirstCH = membershipid.substring(0,4);
		System.out.println("Firstch2 = "+ FirstCH);
		if(FirstCH.equals("RMBF")) {memberType="RMBFB Member"; System.out.println("Firstch2 = "+ FirstCH);}
		if(FirstCH.equals("OTHR")) {memberType="Others"; System.out.println("Firstch3 = "+ FirstCH);}
		
		String status = "y";
		String profileimage = "";
		String qrcodeimage = "";

		/* Generate BarCode Start */

		String barcodeImage = "";
		String barcodevalue = membershipid;

		Code128Bean bar = new Code128Bean();
		final int dpi = 128;
		bar.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
		bar.setFontSize(2.0);
		bar.doQuietZone(false);

		File dir1 = new File(request.getRealPath("") + "/resources/admin/images/barcode/");

		if (!dir1.exists()) {
			dir1.mkdirs();
		}
		String path1 = request.getRealPath("/resources/admin/images/barcode/");
		File uploadfile1 = new File(path1 + File.separator + membershipid + ".png");

		BufferedOutputStream bufferedoutput1 = null;
		bufferedoutput1 = new BufferedOutputStream(new FileOutputStream(uploadfile1));
		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(bufferedoutput1, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			bar.generateBarcode(canvasProvider, barcodevalue);

			canvasProvider.finish();

			File ff = new File(path1);
			File files[] = ff.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {

				}
			}
			// barcodeImage = request.getScheme() + "://"
			// +request.getServerName() + ":" + request.getServerPort()
			// +"/rmbv/resources/admin/images/barcode/" + membershipid + ".png";
			barcodeImage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/resources/admin/images/barcode/" + membershipid + ".png";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			bufferedoutput1.close();
		}

		/* Generate BarCode End */

		/* Generate QRCode Start */

		File dir2 = new File(request.getRealPath("") + "/resources/admin/images/qrcode/");
		if (!dir2.exists())
			dir2.mkdirs();

		String path2 = request.getRealPath("/resources/admin/images/qrcode/");

		int size = 125;
		String fileType = "png";

		Hashtable hintMap = new Hashtable();

		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(membershipid, BarcodeFormat.QR_CODE, size, size, hintMap);
		} catch (WriterException e1) {
			e1.printStackTrace();
		}

		int matrixWidth = byteMatrix.getWidth();

		BufferedImage image1 = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image1.createGraphics();

		Graphics2D graphics = (Graphics2D) image1.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		File qrFile = new File(path2 + File.separator + membershipid + ".png");
		ImageIO.write(image1, fileType, qrFile);

		// qrcodeimage = request.getScheme() + "://" + request.getServerName()
		// +":" + request.getServerPort()
		// +"/rmbv/resources/admin/images/qrcode/" + membershipid + ".png";
		qrcodeimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/resources/admin/images/qrcode/" + membershipid + ".png";

		/* Generate QRCode End */

		try {
			if (profile.getOriginalFilename() != "") {
				try {
					byte[] bytes = profile.getBytes();

					File dir = new File(request.getRealPath("") + "/resources/admin/images/" + File.separator + "user");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/user/");
					File uploadfile = new File(path + File.separator + profile.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
						bytes = buffer.toByteArray();
					} catch (IOException e) {

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

					// profileimage = request.getScheme() + "://"
					// +request.getServerName() + ":" + request.getServerPort()
					// +"/rmbv/resources/admin/images/user/"
					// +profile.getOriginalFilename();
					profileimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
							+ "/resources/admin/images/user/" + profile.getOriginalFilename();

					session.setAttribute("membershipnumber", membershipid);
					
					members = new Members();
					
					members.setUserTypeId(2);
					members.setMembershipNumber(membershipid);
					members.setClubId(memberclubname);
					members.setMemberCategoryId(membercategoryname);
					members.setMemberTypeId(membertypename);
					members.setMemberType(memberType);
					members.setBusinessCategoryId(businesscategoryid);
					members.setTenurePlan(tenureplan);
					members.setJoiningDate(joiningdate);
					members.setMemberNameTitle(membertitle);
					members.setMemberFirstName(firstname);
					members.setMemberMiddleName(middlename);
					members.setMemberLastName(lastname);
					members.setMemberGender(gender);
					members.setMemberDateOfBirth(dateofbirth);
					members.setMemberAnniversaryDate(anniversarydate);
					members.setMemberBloodGroup(bloodgroup);
					members.setMemberAadharNumber(aadharnumber);
					members.setMemberCountryIdCitizenship(countrynamecitizenship);
					members.setMemberPassportNumber(passportnumber);
					members.setMemberPanNumber(pannumber);
					members.setMemberProfilePicture(profileimage);
					members.setMemberMobileNumber(mobile);
					members.setMemberEmail(email);
					members.setMemberPassword(password);
					members.setMemberBarcode(barcodeImage);
					members.setMemberQrcode(qrcodeimage);
					members.setSequence(sequence);
					members.setVocationId(vocation);
					members.setStatus(status);
					members.setCreatedBy(1);
					members.setIpAddress(ipAddress);
					members.setFellowship_id(fellowship_id);
					System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++"+fellowship_id);
					memberdao.addMemberDetail(members);
					memberid = memberdao.getLastMemberId();
					memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryname, 1,
							ipAddress);
					memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			session.setAttribute("membershipnumber", membershipid);

			members = new Members();
			
			members.setUserTypeId(2);
			members.setMembershipNumber(membershipid);
			members.setClubId(memberclubname);
			members.setMemberCategoryId(membercategoryname);
			members.setMemberTypeId(membertypename);
			members.setMemberType(memberType);
			members.setBusinessCategoryId(businesscategoryid);
			members.setTenurePlan(tenureplan);
			members.setJoiningDate(joiningdate);
			members.setMemberNameTitle(membertitle);
			members.setMemberFirstName(firstname);
			members.setMemberMiddleName(middlename);
			members.setMemberLastName(lastname);
			members.setMemberGender(gender);
			members.setMemberDateOfBirth(dateofbirth);
			members.setMemberAnniversaryDate(anniversarydate);
			members.setMemberBloodGroup(bloodgroup);
			members.setMemberAadharNumber(aadharnumber);
			members.setMemberCountryIdCitizenship(countrynamecitizenship);
			members.setMemberPassportNumber(passportnumber);
			members.setMemberPanNumber(pannumber);
			members.setMemberProfilePicture(profileimage);
			members.setMemberMobileNumber(mobile);
			members.setMemberEmail(email);
			members.setMemberPassword(password);
			members.setMemberBarcode(barcodeImage);
			members.setMemberQrcode(qrcodeimage);
			members.setSequence(sequence);
			members.setStatus(status);
			members.setCreatedBy(1);
			members.setIpAddress(ipAddress);
			members.setFellowship_id(fellowship_id);
			memberdao.addMemberDetail(members);

			memberid = memberdao.getLastMemberId();
			memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryname, 1, ipAddress);
			memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);

			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/addContactDetail", method = RequestMethod.POST)
	public String addContactDetail(@RequestParam(value = "companylogo", required = false) MultipartFile companylogo,
			String address1, String address2, String address3, int statename, String cityname, String pincode,
			String memberemail, String occupation, String designation, String companyname, String businessnature,
			String faxnumber, String website, String aboutbusiness, String businesskeywords, String email,
			String address1work, String address2work, String address3work, int statenamework, String citynamework,
			String pincodework, String mobilenumberwork, String communicationaddress, String businessgoals,
			String accomplishments, String interests, String networks, String skills, String idealreferral,
			String topproduct, String topproblemsolved, int valuex, int valuey, int valuew, int valueh,
			HttpServletRequest request, HttpSession session) throws IOException {
		logger.info("Inside Add Member Contact Detail Controller");
		System.out.println("Inside Add Member Contact Detail Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		String companylogoimage = "";

		int memberid = memberdao.getLastMemberId();

		session.setAttribute("memberid", String.valueOf(memberid));

		try {
			if (companylogo.getOriginalFilename() != "") {
				try {
					byte[] bytes = companylogo.getBytes();

					File dir = new File(
							request.getRealPath("") + "/resources/admin/images/" + File.separator + "companylogo");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/companylogo/");
					File uploadfile = new File(path + File.separator + companylogo.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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
					// companylogoimage = request.getScheme() + "://"
					// +request.getServerName() + ":" + request.getServerPort()
					// +"/rmbv/resources/admin/images/companylogo/"
					// +companylogo.getOriginalFilename();
					companylogoimage = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + "/resources/admin/images/companylogo/"
							+ companylogo.getOriginalFilename();
					
					members = new Members();
					members.setMemberId(memberid);
					members.setMemberAddress1(address1);
					members.setMemberAddress2(address2);
					members.setMemberAddress3(address3);
					members.setMemberStateId(statename);
					members.setMemberCityName(cityname);
					members.setMemberPincode(pincode);
					members.setMemberEmail(memberemail);
					members.setCompanyLogo(companylogoimage);
					members.setMemberOccupation(occupation);
					members.setMemberDesignation(designation);
					members.setMemberCompanyName(companyname);
					members.setMemberBusinessNature(businessnature);
					members.setMemberCompanyMobileNumber(mobilenumberwork);
					members.setMemberFaxNumber(faxnumber);
					members.setMemberCompanyEmail(email);
					members.setMemberWebsiteName(website);
					members.setMemberCompanyDescription(aboutbusiness);
					members.setMemberCompanyKeywords(businesskeywords);
					members.setMemberCompanyAddress1(address1work);
					members.setMemberCompanyAddress2(address2work);
					members.setMemberCompanyAddress3(address3work);
					members.setMemberCompanyStateId(statenamework);
					members.setMemberCompanyCityName(citynamework);
					members.setMemberCompanyPincode(pincodework);
					members.setMemberCommunicationAddress(communicationaddress);
					members.setBusinessGoals(businessgoals);
					members.setAccomplishments(accomplishments);
					members.setInterests(interests);
					members.setNetworks(networks);
					members.setSkills(skills);
					members.setIdealReferral(idealreferral);
					members.setTopProduct(topproduct);
					members.setTopProblemSolved(topproblemsolved);
					members.setIpAddress(ipAddress);
					
					memberdao.addContactDetail(members);

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			members = new Members();
			members.setMemberId(memberid);
			members.setMemberAddress1(address1);
			members.setMemberAddress2(address2);
			members.setMemberAddress3(address3);
			members.setMemberStateId(statename);
			members.setMemberCityName(cityname);
			members.setMemberPincode(pincode);
			members.setMemberEmail(memberemail);
			members.setCompanyLogo(companylogoimage);
			members.setMemberOccupation(occupation);
			members.setMemberDesignation(designation);
			members.setMemberCompanyName(companyname);
			members.setMemberBusinessNature(businessnature);
			members.setMemberCompanyMobileNumber(mobilenumberwork);
			members.setMemberFaxNumber(faxnumber);
			members.setMemberCompanyEmail(email);
			members.setMemberWebsiteName(website);
			members.setMemberCompanyDescription(aboutbusiness);
			members.setMemberCompanyKeywords(businesskeywords);
			members.setMemberCompanyAddress1(address1work);
			members.setMemberCompanyAddress2(address2work);
			members.setMemberCompanyAddress3(address3work);
			members.setMemberCompanyStateId(statenamework);
			members.setMemberCompanyCityName(citynamework);
			members.setMemberCompanyPincode(pincodework);
			members.setMemberCommunicationAddress(communicationaddress);
			members.setBusinessGoals(businessgoals);
			members.setAccomplishments(accomplishments);
			members.setInterests(interests);
			members.setNetworks(networks);
			members.setSkills(skills);
			members.setIdealReferral(idealreferral);
			members.setTopProduct(topproduct);
			members.setTopProblemSolved(topproblemsolved);
			members.setIpAddress(ipAddress);
			
			memberdao.addContactDetail(members);

			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/addMemberLandlinePhoneNumber", method = RequestMethod.POST)
	public String addMemberLandlinePhoneNumber(String landlinephonenumber, String location, HttpServletRequest request,
			HttpSession session) {
		logger.info("Inside Add Member Landline Phone Number Controller");
		System.out.println("Inside Add Member Landline Phone Number Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		int memberid = memberdao.getLastMemberId();
		
		memberlandlinephonenumber = new MemberLandlinePhoneNumber();
        memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(memberid);
        memberlandlinephonenumber.setMemberFamilyId(0);
        memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
		memberdao.addMemberLandlinePhoneNumber(memberlandlinephonenumber);
		return "";
	}
	

	
	@RequestMapping(value = "/addMemberWorkDetails", method = RequestMethod.POST)
    public String addMemberWorkDetails(String landlinephonenumber, String location,
          String memberCompanyName, String memberDesignation, String memberComapnyAddress1, String memberComapnyAddress2, String memberComapnyAddress3,
          String memberCompanyCity, String memberComapnyMobileNumber, String memberComapnyEmail,
            HttpServletRequest request, HttpSession session) {
        logger.info("Inside Add Member Landline Phone Number Controller");
        System.out.println("Inside Add Member Landline Phone Number Controller");

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        int memberid = memberdao.getLastMemberId();
        
        memberlandlinephonenumber = new MemberLandlinePhoneNumber();
        
        memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(memberid);
        memberlandlinephonenumber.setMemberFamilyId(0);
        memberlandlinephonenumber.setMemberDesignation(memberDesignation);
        memberlandlinephonenumber.setMemberCompanyName(memberCompanyName);
        memberlandlinephonenumber.setMemberComapnyMobileNumber(memberComapnyMobileNumber);
        memberlandlinephonenumber.setMemberComapnyEmail(memberComapnyEmail);
        memberlandlinephonenumber.setMemberComapnyAddress1(memberComapnyAddress1);
        memberlandlinephonenumber.setMemberComapnyAddress2(memberComapnyAddress2);
        memberlandlinephonenumber.setMemberComapnyAddress3(memberComapnyAddress3);
        memberlandlinephonenumber.setMemberCompanyCity(memberCompanyCity);
        memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
        memberdao.addMemberWorkDetails(memberlandlinephonenumber);
        return "";
    }

	@RequestMapping(value = "/addSpouseDetail", method = RequestMethod.POST)
	public String addSpouseDetail(@RequestParam(value = "profile", required = false) MultipartFile profile,
			String membershipno, String relation, int membercategoryid, String memberfamilytitle, String firstname,
			String middlename, String lastname, String gender, String dateofbirth, String bloodgroup,
			String aadharnumber, String passportnumber, String pannumber, String email, String password,
			String address1, String address2, String address3, int statename, String cityname, String pincode,
			String mobilenumber, String occupation, String designation, String companyname, String businessnature,
			String faxnumber, String website, String emailwork, String address1work, String address2work,
			String address3work, int statenamework, String citynamework, String pincodework, String mobilenumberwork,
			String communication, int sequence, int valuex, int valuey, int valuew, int valueh, HttpSession session,
			HttpServletRequest request) throws IOException {
		logger.info("Inside Add Spouse Detail Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (dateofbirth.equals("--")) {
			dateofbirth = null;
		}

		int memberid = Integer.parseInt(session.getAttribute("memberid").toString());

		String status = "y";

		String profileimage = "";

		/* Generate Barcode Start */

		String barcodeImage;
		String qrcodeimage;
		String barcodevalue = membershipno;

		Code128Bean bar = new Code128Bean();
		final int dpi = 128;
		bar.setModuleWidth(UnitConv.in2mm(5.0f / dpi));
		bar.setFontSize(2.0);
		bar.doQuietZone(false);

		File dir1 = new File(request.getRealPath("") + "/resources/admin/images/barcode/");

		if (!dir1.exists()) {
			dir1.mkdirs();
		}
		String path1 = request.getRealPath("/resources/admin/images/barcode/");
		File uploadfile1 = new File(path1 + membershipno + ".png");
		BufferedOutputStream bufferedoutput1 = new BufferedOutputStream(new FileOutputStream(uploadfile1));
		// OutputStream out = new FileOutputStream(outputFile);
		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(bufferedoutput1, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			bar.generateBarcode(canvasProvider, barcodevalue);

			canvasProvider.finish();

			File ff = new File(path1);
			File files[] = ff.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// System.out.println("File " + files[i].getName()+"
					// "+files[i].length());
				}
			}

			// barcodeImage = request.getScheme() +
			// "://"+request.getServerName() + ":" +
			// request.getServerPort()+"/rmbv/resources/admin/images/barcode/" +
			// membershipno + ".png";
			barcodeImage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/resources/admin/images/barcode/" + membershipno + ".png";
		} finally {
			bufferedoutput1.close();
		}

		/* Generate Barcode End */

		/* Generate QRCode Start */

		File dir2 = new File(request.getRealPath("") + "/resources/admin/images/qrcode/");
		if (!dir2.exists())
			dir2.mkdirs();

		String path2 = request.getRealPath("/resources/admin/images/qrcode/");

		int size = 125;
		String fileType = "png";

		Hashtable hintMap = new Hashtable();

		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(membershipno, BarcodeFormat.QR_CODE, size, size, hintMap);
		} catch (WriterException e1) {
			e1.printStackTrace();
		}

		int matrixWidth = byteMatrix.getWidth();

		BufferedImage image1 = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image1.createGraphics();

		Graphics2D graphics = (Graphics2D) image1.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		File qrFile = new File(path2 + File.separator + membershipno + ".png");
		ImageIO.write(image1, fileType, qrFile);

		// qrcodeimage = request.getScheme() + "://" +
		// request.getServerName()+":" +
		// request.getServerPort()+"/rmbv/resources/admin/images/qrcode/" +
		// membershipno + ".png";
		qrcodeimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/resources/admin/images/qrcode/" + membershipno + ".png";

		/* Generate QRCode End */

		try {
			if (profile.getOriginalFilename() != "" || profile.getOriginalFilename() != null) {
				try {
					byte[] bytes = profile.getBytes();

					File dir = new File(request.getRealPath("") + "/resources/admin/images/" + File.separator + "user");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/user/");
					File uploadfile = new File(path + File.separator + profile.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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

					//profileimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						//	+ "/rmbv/resources/admin/images/user/" + profile.getOriginalFilename();
					 profileimage =
					 request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/resources/admin/images/user/"
					 + profile.getOriginalFilename();

					membersfamily = new MembersFamily(membershipno, memberfamilytitle, firstname, middlename, lastname,
							gender, relation, dateofbirth, bloodgroup, aadharnumber, passportnumber, pannumber,
							profileimage, email, password, address1, address2, address3, statename, cityname, pincode,
							mobilenumber, barcodeImage, qrcodeimage, occupation, designation, companyname,
							businessnature, mobilenumberwork, faxnumber, emailwork, website, address1work, address2work,
							address3work, statenamework, citynamework, pincodework, communication, sequence, memberid,
							membercategoryid, status, 1, ipAddress);
					memberdao.addSpouseDetail(membersfamily);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			membersfamily = new MembersFamily(membershipno, memberfamilytitle, firstname, middlename, lastname, gender,
					relation, dateofbirth, bloodgroup, aadharnumber, passportnumber, pannumber, profileimage, email,
					password, address1, address2, address3, statename, cityname, pincode, mobilenumber, barcodeImage,
					qrcodeimage, occupation, designation, companyname, businessnature, mobilenumberwork, faxnumber,
					emailwork, website, address1work, address2work, address3work, statenamework, citynamework,
					pincodework, communication, sequence, memberid, membercategoryid, status, 1, ipAddress);
			memberdao.addSpouseDetail(membersfamily);
			e.printStackTrace();
		}

		return "";
	}

	@RequestMapping(value = "/addFamilyMemberLandlinePhoneNumber", method = RequestMethod.POST)
	public String addFamilyMemberLandlinePhoneNumber(String landlinephonenumber, String location,
			HttpServletRequest request, HttpSession session) {
		logger.info("Inside Add FAmily Member Landline Phone Number Controller");
		System.out.println("Inside Add Family Member Landline Phone Number Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		int membersfamilyid = memberdao.getLastMembersFamilyId();

		
		memberlandlinephonenumber = new MemberLandlinePhoneNumber();
        memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(0);
        memberlandlinephonenumber.setMemberFamilyId(membersfamilyid);
        memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
		memberdao.addMemberLandlinePhoneNumber(memberlandlinephonenumber);
		return "";
	}

	@RequestMapping(value = "/getFamilyResidentialLandline", method = RequestMethod.GET, produces = "application/json")
	public List<MemberLandlinePhoneNumber> getFamilyResidentialLandline(int membersfamilyid,
			HttpServletRequest request) {
		logger.info("Inside Get Family Member Residential Landline Phone Number");

		List<MemberLandlinePhoneNumber> education = memberdao.getFamilyResidentialLandline(membersfamilyid);

		return education;
	}

	@RequestMapping(value = "/getFamilyWorkLandline", method = RequestMethod.GET, produces = "application/json")
	public List<MemberLandlinePhoneNumber> getFamilyWorkLandline(int membersfamilyid, HttpServletRequest request) {
		logger.info("Inside Get Family Member Work Landline Phone Number");

		List<MemberLandlinePhoneNumber> education = memberdao.getFamilyWorkLandline(membersfamilyid);

		return education;
	}

	@RequestMapping(value = "/getMemberResidentialLandline", method = RequestMethod.GET, produces = "application/json")
	public List<MemberLandlinePhoneNumber> getMemberResidentialLandline(int memberid, HttpServletRequest request) {
		logger.info("Inside Get Member Residential Landline Phone Number");

		List<MemberLandlinePhoneNumber> education = memberdao.getMemberResidentialLandline(memberid);

		return education;
	}

	@RequestMapping(value = "/getMemberWorkLandline", method = RequestMethod.GET, produces = "application/json")
	public List<MemberLandlinePhoneNumber> getMemberWorkLandline(int memberid, HttpServletRequest request) {
		logger.info("Inside Get Member Work Landline Phone Number");

		List<MemberLandlinePhoneNumber> education = memberdao.getMemberWorkLandline(memberid);

		return education;
	}

	@RequestMapping(value = "/deleteFamilyResidentialLandline", method = RequestMethod.DELETE)
	public String deleteFamilyResidentialLandline(int membersfamilyid) {
		logger.info("Inside Delete Family Residential Landline Controller");

		memberdao.deleteFamilyResidentialLandline(membersfamilyid);

		return "Delete successful";
	}

	@RequestMapping(value = "/deleteFamilyWorkLandline", method = RequestMethod.DELETE)
	public String deleteFamilyWorkLandline(int membersfamilyid) {
		logger.info("Inside Delete Family Work Landline Controller");

		memberdao.deleteFamilyWorkLandline(membersfamilyid);

		return "Delete successful";
	}

	@RequestMapping(value = "/editSpouseDetail1", method = RequestMethod.POST)
	public String editSpouseDetail1(@RequestParam(value = "profile", required = false) MultipartFile profile,
			int membersfamilyid, String relation, int membercategoryid, String memberfamilytitle, String firstname,
			String middlename, String lastname, String gender, String dateofbirth, String bloodgroup,
			String aadharnumber, String passportnumber, String pannumber, String profilepicture, String email,
			String password, String address1, String address2, String address3, int statename, String cityname,
			String pincode, String mobilenumber, String occupation, String designation, String companyname,
			String businessnature, String faxnumber, String website, String emailwork, String address1work,
			String address2work, String address3work, int statenamework, String citynamework, String pincodework,
			String mobilenumberwork, String communication, String spouseid, int sequence, int valuex, int valuey,
			int valuew, int valueh, HttpSession session, HttpServletRequest request) throws IOException {
		logger.info("Inside Edit Spouse Detail Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (dateofbirth.equals("--")) {
			dateofbirth = null;
		}

		/* Generate Barcode Start */

		String barcodeImage = "";
		String qrcodeimage = "";
		String barcodevalue = spouseid;

		Code128Bean bar = new Code128Bean();
		bar.setCodeset(Code128Constants.CODESET_B);
		final int dpi = 100;
		bar.setModuleWidth(UnitConv.in2mm(5.0f / dpi));
		bar.doQuietZone(false);

		File dir1 = new File(request.getRealPath("") + "/resources/admin/images/barcode/");

		if (!dir1.exists()) {
			dir1.mkdirs();
		}
		String path1 = request.getRealPath("/resources/admin/images/barcode/");
		File uploadfile1 = new File(path1 + File.separator + spouseid + ".png");

		BufferedOutputStream bufferedoutput1 = null;
		bufferedoutput1 = new BufferedOutputStream(new FileOutputStream(uploadfile1));

		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(bufferedoutput1, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			bar.generateBarcode(canvasProvider, barcodevalue);

			canvasProvider.finish();

			File ff = new File(path1);
			File files[] = ff.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// System.out.println("File " + files[i].getName()+"
					// "+files[i].length());
				}
			}
			// barcodeImage = request.getScheme() +
			// "://"+request.getServerName() + ":" +
			// request.getServerPort()+"/rmbv/resources/admin/images/barcode/" +
			// spouseid + ".png";
			barcodeImage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/resources/admin/images/barcode/" + spouseid + ".png";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			bufferedoutput1.close();
		}

		/* Generate Barcode End */

		/* Generate QRCode Start */

		File dir2 = new File(request.getRealPath("") + "/resources/admin/images/qrcode/");
		if (!dir2.exists())
			dir2.mkdirs();

		String path2 = request.getRealPath("/resources/admin/images/qrcode/");

		int size = 125;
		String fileType = "png";

		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();

		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(spouseid, BarcodeFormat.QR_CODE, size, size, hintMap);
		} catch (WriterException e1) {
			e1.printStackTrace();
		}

		int matrixWidth = byteMatrix.getWidth();

		BufferedImage image1 = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image1.createGraphics();

		Graphics2D graphics = (Graphics2D) image1.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);

		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		File qrFile = new File(path2 + File.separator + spouseid + ".png");
		ImageIO.write(image1, fileType, qrFile);

		// qrcodeimage = request.getScheme() + "://" +
		// request.getServerName()+":" +
		// request.getServerPort()+"/rmbv/resources/admin/images/qrcode/" +
		// spouseid + ".png";
		qrcodeimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/resources/admin/images/qrcode/" + spouseid + ".png";

		/* Generate QRCode End */

		try {
			if (profile.getOriginalFilename() != "") {
				try {
					byte[] bytes = profile.getBytes();

					File dir = new File(request.getRealPath("") + "/resources/admin/images/" + File.separator + "user");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/user/");
					File uploadfile = new File(path + File.separator + profile.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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

					// String profileimage = request.getScheme() +
					// "://"+request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/user/"+profile.getOriginalFilename();
					String profileimage = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + "/resources/admin/images/user/" + profile.getOriginalFilename();

					membersfamily = new MembersFamily(membersfamilyid, spouseid, memberfamilytitle, firstname,
							middlename, lastname, gender, relation, dateofbirth, bloodgroup, aadharnumber,
							passportnumber, pannumber, profileimage, email, password, address1, address2, address3,
							statename, cityname, pincode, mobilenumber, barcodeImage, qrcodeimage, occupation,
							designation, companyname, businessnature, mobilenumberwork, faxnumber, emailwork, website,
							address1work, address2work, address3work, statenamework, citynamework, pincodework,
							communication, membercategoryid, sequence, 1, ipAddress);
					memberdao.editSpouseDetail1(membersfamily);
				} catch (Exception e) {
					System.out.println("-----" + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			membersfamily = new MembersFamily(membersfamilyid, spouseid, memberfamilytitle, firstname, middlename,
					lastname, gender, relation, dateofbirth, bloodgroup, aadharnumber, passportnumber, pannumber,
					profilepicture, email, password, address1, address2, address3, statename, cityname, pincode,
					mobilenumber, barcodeImage, qrcodeimage, occupation, designation, companyname, businessnature,
					mobilenumberwork, faxnumber, emailwork, website, address1work, address2work, address3work,
					statenamework, citynamework, pincodework, communication, membercategoryid, sequence, 1, ipAddress);
			memberdao.editSpouseDetail1(membersfamily);
			return "";
		}

		return "";
	}

	@RequestMapping(value = "/editSpouseDetail", method = RequestMethod.POST)
	public String editSpouseDetail(@RequestParam(value = "profile", required = false) MultipartFile profile,
			int membersfamilyid, String relation, int membercategoryid, String memberfamilytitle, String firstname,
			String middlename, String lastname, String gender, String dateofbirth, String bloodgroup,
			String aadharnumber, String passportnumber, String pannumber, String profilepicture, String email,
			String password, String address1, String address2, String address3, int statename, String cityname,
			String pincode, String mobilenumber, String occupation, String designation, String companyname,
			String businessnature, String faxnumber, String website, String emailwork, String address1work,
			String address2work, String address3work, int statenamework, String citynamework, String pincodework,
			String mobilenumberwork, String communication, int valuex, int valuey, int valuew, int valueh,
			HttpSession session, HttpServletRequest request) throws IOException {
		logger.info("Inside Edit Spouse Detail Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (dateofbirth.equals("--")) {
			dateofbirth = null;
		}

		try {
			if (profile.getOriginalFilename() != "") {
				try {
					byte[] bytes = profile.getBytes();

					File dir = new File(request.getRealPath("") + "/resources/admin/images/" + File.separator + "user");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/user/");
					File uploadfile = new File(path + File.separator + profile.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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

					// String profileimage = request.getScheme() +
					// "://"+request.getServerName() + ":" +
					// request.getServerPort()+"/rmbv/resources/admin/images/user/"+profile.getOriginalFilename();
					      String profileimage = request.getScheme() + "://" + request.getServerName() + ":"
					      	+ request.getServerPort() + "/resources/admin/images/user/" + profile.getOriginalFilename();

					membersfamily = new MembersFamily(membersfamilyid, memberfamilytitle, firstname, middlename,
							lastname, gender, relation, dateofbirth, bloodgroup, aadharnumber, passportnumber,
							pannumber, profileimage, email, password, address1, address2, address3, statename, cityname,
							pincode, mobilenumber, occupation, designation, companyname, businessnature,
							mobilenumberwork, faxnumber, emailwork, website, address1work, address2work, address3work,
							statenamework, citynamework, pincodework, communication, membercategoryid, 1, ipAddress);
					memberdao.editSpouseDetail(membersfamily);
				} catch (Exception e) {
					System.out.println("-----" + e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			membersfamily = new MembersFamily(membersfamilyid, memberfamilytitle, firstname, middlename, lastname,
					gender, relation, dateofbirth, bloodgroup, aadharnumber, passportnumber, pannumber, profilepicture,
					email, password, address1, address2, address3, statename, cityname, pincode, mobilenumber,
					occupation, designation, companyname, businessnature, mobilenumberwork, faxnumber, emailwork,
					website, address1work, address2work, address3work, statenamework, citynamework, pincodework,
					communication, membercategoryid, 1, ipAddress);
			memberdao.editSpouseDetail(membersfamily);
			return "";
		}

		return "";
	}

	@RequestMapping(value = "/deleteMember", method = RequestMethod.DELETE)
	public void deleteMember(int memberid) {
		logger.info("Inside Delete Member...");

		memberdao.deleteMember(memberid);
	}

	@RequestMapping(value = "/editmemberdetailll", method = RequestMethod.POST)
	public String editmemberdetail1(@RequestParam(value = "profile", required = false) MultipartFile profile,
			int rotaryyearid, int memberid, String membershipid, int memberclubname, int membercategoryname,
			int membertypename, int businesscategoryid, String tenureplan, String joiningdate, String membertitle,
			String firstname, String middlename, String lastname, String gender, String dateofbirth, String bloodgroup,
			String anniversarydate, String aadharnumber, int countrynamecitizenship, String passportnumber,
			String pannumber, String profilepicture, String email, int sequence,int vocation, int valuex, int valuey, int valuew,
			int valueh,int fellowship_id, HttpSession session, HttpServletRequest request) throws IOException {
		logger.info("****************** Inside Edit Member Detail Controller****************");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		
		
	//	fellowship_id = (Integer) session.getAttribute("fellowshipId");
	//	System.out.println("+++++++++++++++++=EditController+++++++++++++++++++++"+fellowship_id);
		
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		if (joiningdate == "") {
			joiningdate = null;
		}
		if (dateofbirth == "") {
			dateofbirth = null;
		}
		if (anniversarydate == "") {
			anniversarydate = null;
		}

		String profileimage = profilepicture;

		String memberType="Others"; 
		
		String FirstCH = membershipid.substring(0,4);
		
		System.out.println("Firstch2 = "+ FirstCH);
		if(FirstCH.equals("RMBF")) {memberType="RMBFB Member"; System.out.println("Firstch2 = "+ FirstCH);}
		if(FirstCH.equals("OTHR")) {memberType="Others"; System.out.println("Firstch3 = "+ FirstCH);}
		/* Generate Barcode Start */

		String barcodeImage = "";
		String qrcodeimage = "";
		String barcodevalue = membershipid;

		Code128Bean bar = new Code128Bean();
		bar.setCodeset(Code128Constants.CODESET_B);
		final int dpi = 100;
		bar.setModuleWidth(UnitConv.in2mm(5.0f / dpi));
		bar.doQuietZone(false);

		File dir1 = new File(request.getRealPath("") + "/resources/admin/images/barcode/");

		if (!dir1.exists()) {
			dir1.mkdirs();
		}
		String path1 = request.getRealPath("/resources/admin/images/barcode/");
		File uploadfile1 = new File(path1 + File.separator + membershipid + ".png");

		BufferedOutputStream bufferedoutput1 = null;
		bufferedoutput1 = new BufferedOutputStream(new FileOutputStream(uploadfile1));

		try {
			BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(bufferedoutput1, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			bar.generateBarcode(canvasProvider, barcodevalue);

			canvasProvider.finish();

			File ff = new File(path1);
			File files[] = ff.listFiles();

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					// System.out.println("File " + files[i].getName()+"
					// "+files[i].length());
				}
			}
			// barcodeImage = request.getScheme() +
			// "://"+request.getServerName() + ":" +
			// request.getServerPort()+"/rmbv/resources/admin/images/barcode/" +
			// membershipid + ".png";
			barcodeImage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/RmbDistrict/resources/admin/images/barcode/" + membershipid + ".png";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			bufferedoutput1.close();
		}

		/* Generate Barcode End */

		/* Generate QRCode Start */

		File dir2 = new File(request.getRealPath("") + "/resources/admin/images/qrcode/");
		if (!dir2.exists())
			dir2.mkdirs();

		String path2 = request.getRealPath("/resources/admin/images/qrcode/");

		int size = 125;
		String fileType = "png";

		Hashtable hintMap = new Hashtable();

		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix byteMatrix = null;
		try {
			byteMatrix = qrCodeWriter.encode(membershipid, BarcodeFormat.QR_CODE, size, size, hintMap);
		} catch (WriterException e1) {
			e1.printStackTrace();
		}

		int matrixWidth = byteMatrix.getWidth();

		BufferedImage image1 = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image1.createGraphics();

		Graphics2D graphics = (Graphics2D) image1.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);

		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}

		File qrFile = new File(path2 + File.separator + membershipid + ".png");
		ImageIO.write(image1, fileType, qrFile);

		// qrcodeimage = request.getScheme() + "://" +
		// request.getServerName()+":" +
		// request.getServerPort()+"/rmbv/resources/admin/images/qrcode/" +
		// membershipid + ".png";
		qrcodeimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ "/RmbDistrict/resources/admin/images/qrcode/" + membershipid + ".png";
		/* Generate QRCode End */

		try {
			if (profile.getOriginalFilename() != null && profile.getOriginalFilename() != "") {
				try {
					byte[] bytes = profile.getBytes();

					File dir = new File(request.getRealPath("") + "/resources/admin/images/" + File.separator + "user");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/user/");
					File uploadfile = new File(path + File.separator + profile.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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

					 //profileimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +"/rmbv/resources/admin/images/user/"+profile.getOriginalFilename();
					profileimage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "RmbDistrict/resources/admin/images/user/" + profile.getOriginalFilename();

					memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryname, 1,
							ipAddress);
					memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);
					
					members = new Members();
					
					members.setMemberId(memberid);
					members.setMembershipNumber(membershipid);
					members.setClubId(memberclubname);
					members.setMemberCategoryId(membercategoryname);
					members.setMemberTypeId(membertypename);
					members.setMemberType(memberType);
					members.setBusinessCategoryId(businesscategoryid);
					members.setTenurePlan(tenureplan);
					members.setJoiningDate(joiningdate);
					members.setMemberNameTitle(membertitle);
					members.setMemberFirstName(firstname);
					members.setMemberMiddleName(middlename);
					members.setMemberLastName(lastname);
					members.setMemberGender(gender);
					members.setMemberDateOfBirth(dateofbirth);
					members.setMemberAnniversaryDate(anniversarydate);
					members.setMemberBloodGroup(bloodgroup);
					members.setMemberAadharNumber(aadharnumber);
					members.setMemberCountryIdCitizenship(countrynamecitizenship);
					members.setMemberPassportNumber(passportnumber);
					members.setMemberPanNumber(pannumber);
					members.setMemberProfilePicture(profileimage);
					members.setMemberEmail(email);
					members.setMemberBarcode(barcodeImage);
					members.setMemberQrcode(qrcodeimage);
					members.setSequence(sequence);
					members.setVocationId(vocation);
					members.setIpAddress(ipAddress);
					members.setFellowship_id(fellowship_id);
					System.out.println("??????????????fellowship_id1???????????????????"+fellowship_id);
					memberdao.editMemberDetail1(members);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println("Error Message------------->" + e.getMessage());

			memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryname, 1, ipAddress);
			memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);

			members = new Members();
			
			members.setMemberId(memberid);
			members.setMembershipNumber(membershipid);
			members.setClubId(memberclubname);
			members.setMemberCategoryId(membercategoryname);
			members.setMemberTypeId(membertypename);
			members.setMemberType(memberType);
			members.setBusinessCategoryId(businesscategoryid);
			members.setTenurePlan(tenureplan);
			members.setJoiningDate(joiningdate);
			members.setMemberNameTitle(membertitle);
			members.setMemberFirstName(firstname);
			members.setMemberMiddleName(middlename);
			members.setMemberLastName(lastname);
			members.setMemberGender(gender);
			members.setMemberDateOfBirth(dateofbirth);
			members.setMemberAnniversaryDate(anniversarydate);
			members.setMemberBloodGroup(bloodgroup);
			members.setMemberAadharNumber(aadharnumber);
			members.setMemberCountryIdCitizenship(countrynamecitizenship);
			members.setMemberPassportNumber(passportnumber);
			members.setMemberPanNumber(pannumber);
			members.setMemberProfilePicture(profileimage);
			members.setMemberEmail(email);
			members.setMemberBarcode(barcodeImage);
			members.setMemberQrcode(qrcodeimage);
			members.setSequence(sequence);
			members.setVocationId(vocation);
			members.setIpAddress(ipAddress);

			members.setFellowship_id(fellowship_id);
			System.out.println("??????????????fellowship_id2???????????????????"+fellowship_id);
			
			memberdao.editMemberDetail1(members);

		}

		return "";
	}
	
	
	
	@RequestMapping(value = "/editmemberpersonaldetail1", method = RequestMethod.POST)
    public String editmemberpersonaldetail1(int rotaryyearid, int memberid, String membertitle, String firstname, String middlename, String lastname, String gender, String dateofbirth, String bloodgroup,
          String anniversarydate, String aadharnumber, int countrynamecitizenship, String passportnumber,
          String pannumber, String email, int sequence, HttpSession session, HttpServletRequest request) {
	  logger.info("Inside Edit Member Personal Detail Controller");
        
	
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, 1, ipAddress);
        memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);

        members = new Members(memberid, membertitle, firstname, middlename, lastname, gender,
                dateofbirth, anniversarydate, bloodgroup, aadharnumber, countrynamecitizenship, passportnumber,
                pannumber, email, sequence, ipAddress);
        memberdao.editmemberpersonaldetail1(members);
        return "";
    }

	@RequestMapping(value = "/deleteMemberResidentialLandline", method = RequestMethod.DELETE)
	public String deleteMemberResidentialLandline(int memberid) {
		logger.info("********** INSIDE DELETE MEMBER RESIDENTIAL LANDLINE NUMBER CONTROLLER **********");
		memberdao.deleteMemberResidentialLandline(memberid);
		return "Delete successful";
	}

	@RequestMapping(value = "/deleteMemberWorkLandline", method = RequestMethod.DELETE)
	public String deleteMemberWorkLandline(int memberid) {
		logger.info("********** INSIDE DELETE MEMBER WORK LANDLINE NUMBER CONTROLLER **********");
		memberdao.deleteMemberWorkLandline(memberid);
		return "Delete successful";
	}

	
	@RequestMapping(value = "/editContactDetailForMobile", method = RequestMethod.POST)
    public String editContactDetailForMobile(int memberid, String address1, String address2, String address3, int statename, String cityname,
          String pincode, String mobilenumber, HttpSession session, HttpServletRequest request) {
      logger.info("Inside Edit Member Personal Detail Controller");
        
    
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        
        members = new Members(memberid, address1, address2, address3, statename, cityname, pincode,
              mobilenumber, ipAddress);
        memberdao.editContactDetailForMobile(members);
        return "";
    }	
	
	@RequestMapping(value = "/editContactDetail", method = RequestMethod.POST)
	public String editContactDetail(@RequestParam(value = "companylogo", required = false) MultipartFile companylogo,
			int memberid, String address1, String address2, String address3, int statename, String cityname,
			String pincode, String mobilenumber, String logoimage, String occupation, String designation,
			String companyname, String businessnature, String faxnumber, String website, String aboutbusiness,
			String businesskeywords, String email, String address1work, String address2work, String address3work,
			int statenamework, String citynamework, String pincodework, String mobilenumberwork,
			String communicationaddress, String businessgoals, String accomplishments, String interests,
			String networks, String skills, String idealreferral, String topproduct, String topproblemsolved,
			int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session)
			throws IOException {
		logger.info("Inside Edit Member Contact Detail Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		session.setAttribute("memberid", String.valueOf(memberid));

		String companylogoimage = "";
		try {
			if (companylogo.getOriginalFilename() != null && companylogo.getOriginalFilename() != "") {
				try {
					byte[] bytes = companylogo.getBytes();

					File dir = new File(
							request.getRealPath("") + "/resources/admin/images/" + File.separator + "companylogo");
					if (!dir.exists())
						dir.mkdirs();
					String path = request.getRealPath("/resources/admin/images/companylogo/");
					File uploadfile = new File(path + File.separator + companylogo.getOriginalFilename());
					ByteArrayInputStream in = new ByteArrayInputStream(bytes);
					BufferedImage img = ImageIO.read(in);
					try {
						Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
						BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
						Graphics2D drawer = SubImgage.createGraphics();
						drawer.setComposite(AlphaComposite.Src);
						drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
								RenderingHints.VALUE_INTERPOLATION_BILINEAR);
						drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						drawer.drawImage(scaledImage, valuew, valueh, null);
						drawer.dispose();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						ImageIO.write(SubImgage, "jpg", buffer);
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

					// companylogoimage = request.getScheme() +
					// "://"+request.getServerName()+":"
					// +request.getServerPort()+"/rmbv/resources/admin/images/companylogo/"+companylogo.getOriginalFilename();
					companylogoimage = request.getScheme() + "://" + request.getServerName() + ":"
							+ request.getServerPort() + "/resources/admin/images/companylogo/"
							+ companylogo.getOriginalFilename();

					members = new Members(memberid, address1, address2, address3, statename, cityname, pincode,
							mobilenumber, companylogoimage, occupation, designation, companyname, businessnature,
							mobilenumberwork, faxnumber, email, website, aboutbusiness, businesskeywords, address1work,
							address2work, address3work, statenamework, citynamework, pincodework, communicationaddress,
							businessgoals, accomplishments, interests, networks, skills, idealreferral, topproduct,
							topproblemsolved, ipAddress);
					memberdao.addContactDetail(members);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

			members = new Members(memberid, address1, address2, address3, statename, cityname, pincode, mobilenumber,
					logoimage, occupation, designation, companyname, businessnature, mobilenumberwork, faxnumber, email,
					website, aboutbusiness, businesskeywords, address1work, address2work, address3work, statenamework,
					citynamework, pincodework, communicationaddress, businessgoals, accomplishments, interests,
					networks, skills, idealreferral, topproduct, topproblemsolved, ipAddress);
			memberdao.addContactDetail(members);

		}

		return "";
	}

	@RequestMapping(value = "/editWorkDetail", method = RequestMethod.POST)
    public String editWorkDetail(int memberid, String occupation, String designation,
          String companyname, String businessnature, String faxnumber, String website, String aboutbusiness,
          String businesskeywords, String email, String address1work, String address2work, String address3work,
          int statenamework, String citynamework, String pincodework, String mobilenumberwork,
          String communicationaddress, String businessgoals, String accomplishments, String interests,
          String networks, String skills, String idealreferral, String topproduct, String topproblemsolved,
          HttpServletRequest request, HttpSession session) {
      logger.info("Inside Edit Member Personal Detail Controller");
        
    
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        
        members = new Members(memberid, occupation, designation, companyname, businessnature,
              mobilenumberwork, faxnumber, email, website, aboutbusiness, businesskeywords, address1work,
              address2work, address3work, statenamework, citynamework, pincodework, communicationaddress,
              businessgoals, accomplishments, interests, networks, skills, idealreferral, topproduct,
              topproblemsolved, ipAddress);
        memberdao.editWorkDetail(members);
        return "";
    }

	
	
	@RequestMapping(value = "/editMemberLandlinePhoneNumber", method = RequestMethod.POST)
	public String editMemberLandlinePhoneNumber(int memberid, String landlinephonenumber, String location,
			HttpServletRequest request, HttpSession session) {
		logger.info("Inside Edit Member Landline Phone Number Controller");
		System.out.println("Inside Edit Member Landline Phone Number Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		memberlandlinephonenumber = new MemberLandlinePhoneNumber();
		memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(memberid);
        memberlandlinephonenumber.setMemberFamilyId(0);
		memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
		memberdao.addMemberLandlinePhoneNumber(memberlandlinephonenumber);
		return "";
	}
	
	@RequestMapping(value = "/editMemberWorkDetails", method = RequestMethod.POST)
    public String editMemberWorkDetails(int memberid, String landlinephonenumber, String location,
          String memberCompanyName, String memberDesignation, String memberComapnyAddress1, String memberComapnyAddress2, String memberComapnyAddress3,
          String memberCompanyCity, String memberComapnyMobileNumber, String memberComapnyEmail,
            HttpServletRequest request, HttpSession session) {
        logger.info("Inside Edit Member WORK DETAILS Controller");
        
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        memberlandlinephonenumber = new MemberLandlinePhoneNumber();
        
        memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(memberid);
        memberlandlinephonenumber.setMemberFamilyId(0);
        memberlandlinephonenumber.setMemberDesignation(memberDesignation);
        memberlandlinephonenumber.setMemberCompanyName(memberCompanyName);
        memberlandlinephonenumber.setMemberComapnyMobileNumber(memberComapnyMobileNumber);
        memberlandlinephonenumber.setMemberComapnyEmail(memberComapnyEmail);
        memberlandlinephonenumber.setMemberComapnyAddress1(memberComapnyAddress1);
        memberlandlinephonenumber.setMemberComapnyAddress2(memberComapnyAddress2);
        memberlandlinephonenumber.setMemberComapnyAddress3(memberComapnyAddress3);
        memberlandlinephonenumber.setMemberCompanyCity(memberCompanyCity);
        memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
        
        
        memberdao.addMemberWorkDetails(memberlandlinephonenumber);
        return "";
    }

	@RequestMapping(value = "setSessionMemberId", method = RequestMethod.POST)
	public String setSessionMemberId(String memberid, String membershipnumber, HttpSession session) {
		logger.info("Inside Set Session Of MemberId");

		session.setMaxInactiveInterval(10 * 60); // Set Session Time Out time is
													// 10 Minutes

		session.setAttribute("memberid", memberid);
		session.setAttribute("membershipnumber", membershipnumber);

		return "";
	}

	@RequestMapping(value = "/editFamilyMemberLandlinePhoneNumber", method = RequestMethod.POST)
	public String editFamilyMemberLandlinePhoneNumber(int membersfamilyid, String landlinephonenumber, String location,
			HttpServletRequest request, HttpSession session) {
		logger.info("Inside Edit Family Member Landline Phone Number Controller");
		System.out.println("Inside Edit Family Member Landline Phone Number Controller");

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		
		
		memberlandlinephonenumber = new MemberLandlinePhoneNumber();
        memberlandlinephonenumber.setLandlinePhoneNumber(landlinephonenumber);
        memberlandlinephonenumber.setLocation(location);
        memberlandlinephonenumber.setMemberId(0);
        memberlandlinephonenumber.setMemberFamilyId(membersfamilyid);
        memberlandlinephonenumber.setIpAddress(ipAddress);
        memberlandlinephonenumber.setCreatedBy(0);
        
		memberdao.addMemberLandlinePhoneNumber(memberlandlinephonenumber);
		return "";
	}

	@RequestMapping(value = "/deleteFamilyMember", method = RequestMethod.DELETE)
	public void delete(int membersfamilyid) {
		logger.info("Inside Delete Family Member...");

		memberdao.deleteFamilyMember(membersfamilyid);
	}

	@RequestMapping(value = "/searchMembers", method = RequestMethod.GET, produces = "application/json")
	public List<Members> searchMembers(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH MEMBER *****");
		List<Members> members = memberdao.searchMembers(keyword);
		return members;
	}

	@RequestMapping(value = "/searchMembersForMembersDirectory", method = RequestMethod.GET, produces = "application/json")
	public List<Members> searchMembersForMembersDirectory(String keyword, HttpServletRequest request) {
		logger.info("***** SEARCH MEMBER FOR MEMBERS DIRECTORY *****");
		List<Members> members = memberdao.searchMembersForMembersDirectory(keyword);
		return members;
	}

	@RequestMapping(value = "/getMemberCurrentCategoryId", method = RequestMethod.GET, produces = "application/json")
	public MemberCategoryByYear getMemberCurrentCategoryId(int memberid, int rotaryyearid, HttpServletRequest request) {
		logger.info("********** GET MEMBER CURRENT CATEGORY ID **********");
		MemberCategoryByYear memberCategoryByYear = memberdao.getMemberCurrentCategoryId(memberid, rotaryyearid);
		return memberCategoryByYear;
	}

	@RequestMapping(value = "/getChapterSummaryByDate", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getChapterSummaryByDate(String fromdate, String todate, HttpServletRequest request) {
		logger.info("***** GET CHAPTER SUMMARY BY DATE *****");
		List<Members> members = memberdao.getChapterSummaryByDate(fromdate, todate);
		return members;
	}

	@RequestMapping(value = "/getSpouseDetailById", method = RequestMethod.GET, produces = "application/json")
	public MembersFamily getSpouseDetailById(int membersfamilyid, HttpServletRequest request) {
		logger.info("***** GET SPOUSE DETAIL BY ID *****");
		MembersFamily membersFamily = memberdao.getSpouseDetailById(membersfamilyid);
		return membersFamily;
	}
	
	@RequestMapping(value = "/getMemberFullName", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getMemberFullName(HttpServletRequest request) {
		logger.info("***** GET MEMBER FULL NAME *****");
		List<Members> members = memberdao.getMemberFullName();
		return members;
	}
	
	
	
	
	@RequestMapping(value = "/editProfilePicture1", method = RequestMethod.POST)
    public String editProfilePicture1(@RequestParam(value = "profilepicture", required = false) MultipartFile profilepicture,
            int memberid, String profileimage, int valuex, int valuey, int valuew, int valueh, HttpServletRequest request, HttpSession session)
            throws IOException {
        logger.info("Inside Edit Member Contact Detail Controller");

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        session.setAttribute("memberid", String.valueOf(memberid));

        String memberprofileimage = "";

        try {
            if (profilepicture.getOriginalFilename() != null && profilepicture.getOriginalFilename() != "") {
                try {
                    byte[] bytes = profilepicture.getBytes();

                    File dir = new File(
                            request.getRealPath("") + "/resources/admin/images/" + File.separator + "profilepicture");
                    if (!dir.exists())
                        dir.mkdirs();
                    String path = request.getRealPath("/resources/admin/images/companylogo/");
                    File uploadfile = new File(path + File.separator + profilepicture.getOriginalFilename());
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    BufferedImage img = ImageIO.read(in);
                    try {
                        Image scaledImage = img.getScaledInstance(valuew, valueh, Image.SCALE_SMOOTH);
                        BufferedImage SubImgage = img.getSubimage(valuex, valuey, valuew, valueh);
                        Graphics2D drawer = SubImgage.createGraphics();
                        drawer.setComposite(AlphaComposite.Src);
                        drawer.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        drawer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        drawer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        drawer.drawImage(scaledImage, valuew, valueh, null);
                        drawer.dispose();
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        ImageIO.write(SubImgage, "jpg", buffer);
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

                   // memberprofileimage = request.getScheme() +
                   //  "://"+request.getServerName()+":"
                   //  +request.getServerPort()+"/rmbv/resources/admin/images/companylogo/"+profilepicture.getOriginalFilename();
                    memberprofileimage = request.getScheme() + "://" + request.getServerName() + ":"
                          + request.getServerPort() + "/resources/admin/images/profilepicture/"
                           + profilepicture.getOriginalFilename();

                    members = new Members(memberid, memberprofileimage, ipAddress);
                    memberdao.editProfilePicture1(members);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());

            members = new Members(memberid, profileimage,  ipAddress);
            memberdao.editProfilePicture1(members);

        }

        return "";
    }
	
	@RequestMapping(value = "/checkmobilenumber", method = RequestMethod.GET, produces = "application/json")
	public String checkmobilenumber(String mobilenumber, String email, HttpServletRequest request) {
		logger.info("***** CHECK MOBILE NUMBER *****");
		String members = memberdao.checkemailaddress(mobilenumber, email);
		return members;
	}
	
	@RequestMapping(value = "setMemberActiveOrInA", method = RequestMethod.POST)
    public String setMemberActiveOrInA(int memberid,String memberstatus, HttpServletRequest request, HttpSession session) {
        logger.info("********** INSIDE SET MEMBER ACTIVE OR INACTIVE CONTROLLER **********");
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        int id = 1;
        members = new Members(memberid, memberstatus, id, IpAddress);
        memberdao.setMemberActiveOrInA(members);

        return "";
    }
	
	@RequestMapping(value = "AddVocation", method = RequestMethod.POST)
    public String AddVocation(String title, String desc, HttpServletRequest request, HttpSession session) {
        logger.info("********** INSIDE ADD VOCATION**********");
        String IpAddress = request.getHeader("X-FORWARDED-FOR");
        if (IpAddress == null) {
            IpAddress = request.getRemoteAddr();
        }
        int id = 0;
        memberdao.addVocation(title, desc, IpAddress, id);

        return "Success";
    }
	
	@RequestMapping(value = "/getAllVocation", method = RequestMethod.GET, produces = "application/json")
	public List<Members> getAllVocation(HttpServletRequest request) {
		logger.info("***** GET ALL VOCATION *****");
		List<Members> members = memberdao.getallVocation();
		return members;
	}
	
	@RequestMapping(value = "/deleteVocation", method = RequestMethod.DELETE)
	public String deleteVocation(int vocationid) {
		logger.info("Inside Delete VOCATION CONTROLLER");

		memberdao.deleteVocation(vocationid);

		return "Delete successful";
	}
	
}
