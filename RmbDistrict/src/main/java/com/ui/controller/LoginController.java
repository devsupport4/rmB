package com.ui.controller;

import java.util.Properties;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.LoginDAO;
import com.ui.model.Members;
/*import com.ui.dao.MemberDAO;
import com.ui.model.BoardOfDirectors;
import com.ui.model.Members;*/
import com.ui.model.User;

@RestController
public class LoginController {
	@Autowired
	LoginDAO logindao;
	/*
	 * @Autowired MemberDAO memberDAO;
	 */
	
	
	User user;	
	/*
	 * Members members; BoardOfDirectors boardOfDirectors;
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(HttpSession session) {
		session.setAttribute("adminid", null);
		session.setAttribute("adminshowname", null);
						
		return "Logout Successfully";
	}
	
	@RequestMapping(value = "/getMemberDetailsByLogin", method = RequestMethod.GET, produces = "application/json")
	public Members getLastMemberSequenceByCategory(HttpServletRequest request, String userName,String password,HttpSession session) {
		logger.info("Inside Get Member Details By Email OR PASSWORD");
		
		Members m = logindao.frontLogin(userName, password);
		session.setAttribute("fellowshipId",Integer.valueOf(m.getFellowship_id()));
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+m.getFellowship_id());
		return m;
	}
	
	


	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpSession session, String member_email, String member_password) {

		logger.info("Inside Admin Login");

		Members user = logindao.checkLogin(member_email, member_password);

		if (user != null) {
			session.setMaxInactiveInterval(30 * 60); // Set Session Time Out
			session.setAttribute("adminid", String.valueOf(user.getMemberId()));
			session.setAttribute("roleId",String.valueOf(user.getUser_role_id()));
			session.setAttribute("fellowshipId",Integer.valueOf(user.getFellowship_id()));
			//session.setAttribute("loginid", user.getUserId());
			session.setAttribute("adminshowname", user.getMemberEmail());
			System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQ"+user.getFellowship_id());
			return "Successfully Login";
		}else{
			return null;	
		}
	}

	@RequestMapping(value = "frontLogin", method = RequestMethod.POST)
	public String frontLogin(HttpSession session, String userName, String password, String loggedin, HttpServletResponse response) {

		logger.info("Inside Front User Login");

		Members members = logindao.frontLogin(userName, password);
		
		session.setAttribute("fellowshipId",members.getFellowship_id());
		System.out.println("??????????????????????????????????"+members.getFellowship_id());
		/*
		 * BoardOfDirectors boardOfDirectors =
		 * logindao.getBoardOfDirectorsInfo(members.getMemberId()); if (boardOfDirectors
		 * != null) { session.setMaxInactiveInterval(30 * 60); // Set Session Time Out
		 * session.setAttribute("boardmemberid",
		 * String.valueOf(boardOfDirectors.getMemberId()));
		 * 
		 * session.setAttribute("boardmemberfamilyid",
		 * String.valueOf(boardOfDirectors.getMemberFamilyId()));
		 * session.setAttribute("loginid", boardOfDirectors.getMemberId()); } else {
		 * session.setAttribute("boardmemberid", "0");
		 * session.setAttribute("boardmemberfamilyid", "0"); }
		 */
		
		if (members != null) {
			session.setMaxInactiveInterval(30 * 60); // Set Session Time Out
			session.setAttribute("loginid", members.getMemberId());
			session.setAttribute("memberid", String.valueOf(members.getMemberId()));
			session.setAttribute("membershipnumber", members.getMembershipNumber());
			session.setAttribute("membername", members.getMemberFirstName());
			session.setAttribute("memberlastname", members.getMemberLastName());
			session.setAttribute("memberprofilepicture", members.getMemberProfilePicture());
			session.setAttribute("membermobilenumber", members.getMemberMobileNumber());
			session.setAttribute("memberemail", members.getMemberEmail());
			session.setAttribute("fellowshipId",Integer.valueOf(members.getFellowship_id()));
			session.setAttribute("memberstatus", members.getStatus());
			session.setAttribute("membertypestatus", String.valueOf(members.getStatus()));
			
			
			
			
			
			if(loggedin.equalsIgnoreCase("y"))
            {
                /*Create Cookie Start
                Cookie ck1 = new Cookie("rmbvemail", members.getMemberEmail());
                Cookie ck2 = new Cookie("rmbvpassword", members.getMemberPassword());
                ck1.setMaxAge(60 * 60 * 24 * 365 * 10); //10 Years
                ck2.setMaxAge(60 * 60 * 24 * 365 * 10); //10 Years
                response.addCookie(ck1);
                response.addCookie(ck2);
                //Create Cookie End     */       
            }
		
			return "Successfully Login";
		} else {
			return "Unsuccessful";	
		}		
	}
	
	@RequestMapping(value="submitRequest", method= RequestMethod.POST)
	public String submitRequest(HttpSession session, HttpServletRequest request, HttpServletResponse res, String fname, String lname, String email, String mobileno, String requestmsg)
	{
		logger.info("********** INSIDE SUBMIT REQUEST OR QUERY **********");
		
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
			message.setFrom(new InternetAddress("emailrelay@rmbbangalore.com","RMBFB"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setRecipients(Message.RecipientType.BCC,myBccList);
			message.setSubject("Message from contact us");
			StringBuilder sb = new StringBuilder();
			sb.append("<!DOCTYPE html><html><head><title> Contact Us </title><meta charset='UTF-8'><meta name='description' content=''>"
					+ "<meta name='keywords' content=''></head><body><table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
					+ "<tbody><tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737; ' align='center' valign='middle'> <a href='http://www.rmbbangalore.org/'> "
					+ "<img border='0' src=''/></a></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'>"
					+ "</td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
					+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
					+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:18px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'>Request / Query From User</td></tr>"
					+ "<tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr><tr><td align='left' valign='top' width='412'>"
					+ "<table width='100%' cellspacing='0' cellpadding='4' border='0'><tbody><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='15%'><strong> Name: </strong></td>"
					+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='85%'> "+fname+" "+lname+"</td></tr>"
					+ "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='15%'><strong> Email: </strong></td>"
					+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='25%'> "+email+" </td></tr>"
					+ "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='15%'><strong> Mobile No.: </strong></td>"
					+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='85%'> "+mobileno+" </td></tr>"
					+ "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'><strong> Message:</strong></td>"
					+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' colspan='5'> "+requestmsg+" </td></tr></tbody></table>"
					+ "</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");        	
			           	
			message.setContent(sb.toString(), "text/html");
			Transport.send(message);
			System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
		} catch (Exception msg) {
			System.out.println("Not send mail "+msg);
		}    
        		
		return "";
	}
}