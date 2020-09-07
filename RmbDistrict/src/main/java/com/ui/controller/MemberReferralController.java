package com.ui.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.MemberDAO;
import com.ui.dao.MemberReferralDAO;
import com.ui.model.MemberOneToOne;
import com.ui.model.MemberReferral;
import com.ui.model.MemberThankYouSlip;
import com.ui.model.Members;


@RestController
public class MemberReferralController {
	@Autowired
	MemberReferralDAO memberReferralDao;
	MemberReferral memberReferral;
	MemberThankYouSlip memberThankYouSlip;
	MemberOneToOne memberOneToOne; 
	@Autowired
	MemberDAO memberDao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberReferralController.class);
	
	
	
	@RequestMapping(value = "/getMemberMeetingsById", method = RequestMethod.GET, produces = "application/json")
	public List<MemberOneToOne> getMemberMeetingsById(int memberid, String fromdate, String todate, HttpServletRequest request) {
		logger.info("***** GET MEMBER MEETINGS BY ID *****");
		List<MemberOneToOne> memberOneToOne = memberReferralDao.getMemberMeetingsById(memberid, fromdate, todate);
		return memberOneToOne;
	}
	
	@RequestMapping(value = "/getMemberBusinessById", method = RequestMethod.GET, produces = "application/json")
	public List<MemberThankYouSlip> getMemberBusinessById(int memberid, String fromdate, String todate, HttpServletRequest request) {
		logger.info("***** GET MEMBER BUSINESS BY ID *****");
		List<MemberThankYouSlip> memberThankYouSlip = memberReferralDao.getMemberBusinessById(memberid, fromdate, todate);
		return memberThankYouSlip;
	}
	
	@RequestMapping(value = "/getMemberBusinessForChapterSummaryById", method = RequestMethod.GET, produces = "application/json")
    public List<MemberThankYouSlip> getMemberBusinessForChapterSummaryById(int memberid, String fromdate, String todate, HttpServletRequest request) {
        logger.info("***** GET MEMBER BUSINESS BY ID FOR CHAPTER SUMMARY *****");
        List<MemberThankYouSlip> memberThankYouSlip = memberReferralDao.getMemberBusinessForChapterSummaryById(memberid, fromdate, todate);
        return memberThankYouSlip;
    }
	
	
	@RequestMapping(value = "/getMemberReferencesById", method = RequestMethod.GET, produces = "application/json")
	public List<MemberReferral> getMemberReferencesById(int memberid, String fromdate, String todate, HttpServletRequest request) {
		logger.info("***** GET MEMBER REFERENCES BY ID *****");
		List<MemberReferral> memberReferences = memberReferralDao.getMemberReferencesById(memberid, fromdate, todate);
		return memberReferences;
	}
	
	@RequestMapping(value = "/getMemberReferralsById", method = RequestMethod.GET, produces = "application/json")
	public List<MemberReferral> getMemberReferralsById(int memberid, String fromdate, String todate, HttpServletRequest request) {
		logger.info("***** GET MEMBER REFERENCES BY ID *****");
		List<MemberReferral> memberReferral = memberReferralDao.getMemberReferralsById(memberid, fromdate, todate);
		return memberReferral;
	}
	
	@RequestMapping(value = "/getMemberReferencesAndBusinessAndMeetingById", method = RequestMethod.GET, produces = "application/json")
    public String getMemberReferencesAndBusinessAndMeetingById(int memberid, String fromdate, String todate, HttpServletRequest request, HttpSession session) {
        logger.info("***** GET MEMBER REFERENCES AND MEETING BY ID *****");
        
        
        /*System.out.print(fromdate);
        System.out.print(todate);*/
        session.setAttribute("fromdate", fromdate);
        session.setAttribute("todate", todate);
        
       /* System.out.println(session.getAttribute("fromdate"));
        System.out.println(session.getAttribute("todate"));*/
        
        int noofreference = 0, noofmeeting = 0;
        float businessamount = 0;
        
        List<MemberReferral> memberReferral = memberReferralDao.getMemberReferencesById(memberid, fromdate, todate);
        for(MemberReferral mr : memberReferral) {
          noofreference = noofreference + 1;
        }
        
        List<MemberThankYouSlip> memberThankYouSlip = memberReferralDao.getMemberBusinessById(memberid, fromdate, todate);
        for(MemberThankYouSlip mtys : memberThankYouSlip) {
          businessamount = businessamount + mtys.getAmount();
        }
        
        List<MemberOneToOne> memberOneToOne = memberReferralDao.getMemberMeetingsById(memberid, fromdate, todate);
        for(MemberOneToOne moto : memberOneToOne) {
          noofmeeting = noofmeeting + 1;
        }
        
        String returnstring;
        
        returnstring = noofreference + "-" + businessamount + "-" + noofmeeting;
        
        return returnstring;
    }
	
	@RequestMapping(value="/saveReferral",method= RequestMethod.POST)
	public String saveReferral(HttpServletRequest request, HttpSession session, int tomemberid, String referralname, String referdate, String closedate, String referraltype, String card, String call, String referralemail,String referalstatus, String apprvalue, String referralcontactno, String referraladdress, String comment)
	{
		logger.info("********** SAVE REFERRAL **********");		
			
		
		String s="y";
		int createdby  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		memberReferral = new MemberReferral();
		memberReferral.setToMemberId(tomemberid);
		memberReferral.setReferralName(referralname);
		memberReferral.setReferDate(referdate);
		memberReferral.setReferralType(referraltype);
		memberReferral.setReferralStatus1(card);
		memberReferral.setReferralStatus2(call);
		memberReferral.setAddress(referraladdress);
		memberReferral.setContactNumber(referralcontactno);
		memberReferral.setEmail(referralemail);
		memberReferral.setComments(comment);
		memberReferral.setStatus(s);
		memberReferral.setCreatedBy(createdby);
		memberReferral.setIpAddress(IpAddress);
		memberReferral.setCloseDate(closedate);
		memberReferral.setApprValue(apprvalue);
		memberReferral.setReferralStatus(referalstatus);
				
		memberReferralDao.saveReferral(memberReferral);	
		
		Members member = memberDao.getMemberDetailByMemberId(tomemberid);
		
		String email = member.getMemberEmail();	
		String Phone = member.getMemberMobileNumber();

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
	        	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
	           	Message message = new MimeMessage(session1);
	           	message.setFrom(new InternetAddress(username,"RMBFB")); 
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	            message.setRecipients(Message.RecipientType.BCC, myBccList);
	            message.setSubject("Reference Confirmation");
	            StringBuilder sb = new StringBuilder();
	            sb.append("<html><body>"
	                  + "<table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
	                  + "<tbody>"
	                  + "<tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737;' align='center' valign='middle'> <a href='#'> <img   border='0' src=''/></a> </td></tr>"
	                  + "<tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
	                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
	                  + "<tbody>"
	                  + "<tr>"
	                  + "<td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
	                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
	                  + "<tbody>"
	                  + "<tr>"
	                  + "<td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:17px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> Congratulations! You have received a new referral from  "
	                  + session.getAttribute("membername")
	                  +" "
	                  + session.getAttribute("memberlastname")
	                  + "!</td>"
	                  + "</tr>"
	                  + "<tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr>"
	                  + "<tr><td align='left' valign='top' width='412'>"
	                  + "<table width='100%' cellspacing='0' cellpadding='4' border='0'>"
	                  + "<tbody>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Date Referral Given:</strong> "
	                  + referdate
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Referral Type:</strong> "
	                  + referraltype
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Connection:</strong> "
	                  + card 
	                  +", "
	                  + call
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'> <strong>Referral Name:</strong> "
	                  + referralname
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'> <strong>Address:</strong> "
	                  + referraladdress
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Phone:</strong> "
	                  + referralcontactno
	                  + " <br></td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>E-mail:</strong> "
	                  + referralemail
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' colspan='5'> <strong> Comments:</strong> "
	                  +  comment
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' colspan='5'><br> For additional information and to follow up with your referral partner, be sure to contact "
	                  + session.getAttribute("membername")
	                  +" "
	                  + session.getAttribute("memberlastname")
	                  + "."
	                  + "</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Phone:</strong> "
	                  + session.getAttribute("membermobilenumber") 
	                  +"</td></tr>"
	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>E-mail:</strong> "
	                  + session.getAttribute("memberemail") 
	                  +"</td></tr>"
	                  + "</tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

	            message.setContent(sb.toString(), "text/html");
	            Transport.send(message);
	            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
	        } catch (Exception msg) {
	            System.out.println("Not send mail " + msg);
	        }
	        
	        logger.info("***** Refrence Create sms *****");
	        String Content = "Congratulations! You have received a new referral from  "+session.getAttribute("membername")+" "+ session.getAttribute("memberlastname")+ "! Please Check your mail for more details";
	        try {
	            String url = "http://sms.astartechnologies.net/sendsms.aspx";
	            URL obj = new URL(url);
	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	            String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to=" + Phone
	            + "&msg="+ Content;
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(urlParameters);
	            wr.flush();
	            wr.close();
	            int responseCode = con.getResponseCode();
	            if (responseCode == 200) {
	                con.connect();
	                BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	                StringBuffer buffer = new StringBuffer();
	                String line;
	                while ((line = rd.readLine()) != null) {
	                    buffer.append(line).append("n");
	                }
	                System.out.println(buffer.toString());
	                rd.close();
	                con.disconnect();
	                System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());

	            } else {
	                System.out.println("http response code error: " + responseCode + "\n");
	            }
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		return "Success";
	}
	
		
	
	@RequestMapping(value="/saveThankYouSlip",method= RequestMethod.POST)
	public String saveThankYouSlip(HttpServletRequest request, HttpSession session, int tomemberid, float amount, String slipdate, String businesstype, String referraltype, String comment)
	{
		logger.info("********** SAVE THANK YOU SLIP **********");		
		String s="y";
		int createdby  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		memberThankYouSlip = new MemberThankYouSlip(tomemberid, amount, slipdate, businesstype, referraltype, comment, s, createdby, IpAddress);		
		memberReferralDao.saveThankYouSlip(memberThankYouSlip);
		
		Members member = memberDao.getMemberDetailByMemberId(tomemberid);
        
        String email = member.getMemberEmail(); 
        String Phone = member.getMemberPhoneNumber();
		
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
        	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
           	Message message = new MimeMessage(session1);
           	message.setFrom(new InternetAddress(username,"RMBFB")); 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.BCC, myBccList);
            message.setSubject("Business Confirmation");
            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>"
                  + "<table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737;' align='center' valign='middle'> <a href='#'> <img   border='0' src=''/></a> </td></tr>"
                  + "<tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr>"
                  + "<td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr>"
                  + "<td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:17px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> Congratulations! You have received a new Business Transaction from "
                  + session.getAttribute("membername")
                  +" "
                  + session.getAttribute("memberlastname")
                  + "!</td>"
                  + "</tr>"
                  + "<tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr>"
                  + "<tr><td align='left' valign='top' width='412'>"
                  + "<table width='100%' cellspacing='0' cellpadding='4' border='0'>"
                  + "<tbody>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Amount:</strong> "
                  + amount
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Date:</strong> "
                  + slipdate
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Business Type:</strong> "
                  + businesstype
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'> <strong>Referral Type*:</strong> "
                  + referraltype
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'> <strong>Comments:</strong> "
                  + comment
                  + " </td></tr>"                 
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' colspan='5'><br> For additional information and to follow up with your Business partner, be sure to contact "
                  + session.getAttribute("membername")
                  +" "
                  + session.getAttribute("memberlastname")
                  + ".</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Phone:</strong> "
                  + session.getAttribute("membermobilenumber")
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>E-mail:</strong> "
                  + session.getAttribute("memberemail")
                  + "</td></tr>"
                  + "</tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

            message.setContent(sb.toString(), "text/html");
            Transport.send(message);
            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
        } catch (Exception msg) {
            System.out.println("Not send mail " + msg);
        }
        
        logger.info("***** Send Thank You slip sms *****");
        String Content = "Congratulations! You have received a new Business Transaction from  "+session.getAttribute("membername")+" "+ session.getAttribute("memberlastname")+ "! Please Check your mail for more details";
        try {
            String url = "http://sms.astartechnologies.net/sendsms.aspx";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to=" + Phone
            + "&msg="+ Content;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                con.connect();
                BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    buffer.append(line).append("n");
                }
                System.out.println(buffer.toString());
                rd.close();
                con.disconnect();
                System.out.println("responseCode ->" + responseCode + " --- " + con.getContent().toString());

            } else {
                System.out.println("http response code error: " + responseCode + "\n");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
	
		return "Success";
	}
	
	
	
	@RequestMapping(value="/saveOnetoOne",method= RequestMethod.POST)
	public String saveOnetoOne(HttpServletRequest request, HttpSession session, int metmemberid, int invitedbymemberid, String meetingdate, String location, String topic)
	{
		logger.info("********** SAVE THANK YOU SLIP **********");		
		String s="y";
		int createdby  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		
		memberOneToOne = new MemberOneToOne(metmemberid, invitedbymemberid, meetingdate, location, topic, s, createdby, IpAddress);		
		memberReferralDao.saveOnetoOne(memberOneToOne);	
	
		Members member = memberDao.getMemberDetailByMemberId(metmemberid);
        
        String email = member.getMemberEmail();
        String memberfirstname = member.getMemberFirstName();
        String memberlastname = member.getMemberLastName();
       
		
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
        	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net, "+email);           	            	
           	Message message = new MimeMessage(session1);
           	message.setFrom(new InternetAddress(username,"RMBFB")); 
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.BCC, myBccList);
            message.setSubject("Meeting Confirmation");
            StringBuilder sb = new StringBuilder();
            sb.append("<html><body>"
                  + "<table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737;' align='center' valign='middle'> <a href='#'> <img   border='0' src=''/></a> </td></tr>"
                  + "<tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr>"
                  + "<td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
                  + "<table width='100%' cellspacing='0' cellpadding='0' border='0'>"
                  + "<tbody>"
                  + "<tr>"
                  + "<td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:17px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> You have received a meeting Invitation from "
                  + session.getAttribute("membername")
                  +" "
                  + session.getAttribute("memberlastname")
                  + "!</td>"
                  + "</tr>"
                  + "<tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr>"
                  + "<tr><td align='left' valign='top' width='412'>"
                  + "<table width='100%' cellspacing='0' cellpadding='4' border='0'>"
                  + "<tbody>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Date Referral Given:</strong> "
                  + meetingdate
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong> Met With :</strong> "
                  + memberfirstname 
                  + " "
                  + memberlastname
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Address :</strong> "
                  + location
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'> <strong>Topics of conversation:</strong> "
                  + topic
                  + "</td></tr>"                  
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' colspan='5'><br> For additional information and to follow up with your Meeting partner, be sure to contact "
                  + session.getAttribute("membername")
                  +" "
                  + session.getAttribute("memberlastname")
                  + ".</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Phone:</strong> "
                  + session.getAttribute("membermobilenumber")
                  + "</td></tr>"
                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>E-mail:</strong> "
                  + session.getAttribute("memberemail")
                  + "</td></tr>"
                  + "</tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

            message.setContent(sb.toString(), "text/html");
            Transport.send(message);
            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
        } catch (Exception msg) {
            System.out.println("Not send mail " + msg);
        }
        
        
		return "Success";
	}
		
	@RequestMapping(value = "/business", method = RequestMethod.GET, produces = "application/json")
    public List<MemberThankYouSlip> Business(HttpServletRequest request) {
        logger.info("********** Inside News Controller **********");
        List<MemberThankYouSlip> memberThankYouSlip = memberReferralDao.getAllBusiness();
        return memberThankYouSlip;
    }  
	
	
	@RequestMapping(value = "/deleteBusiness", method = RequestMethod.DELETE)
    public void delete(int id) {
        logger.info("********** INSIDE DELETE NEWS ********** ");

        memberReferralDao.deleteBusiness(id);
    }
	
	@RequestMapping(value = "/getBusinessDetailById", method = RequestMethod.GET, produces = "application/json")
    public MemberThankYouSlip getBusinessDetailById(int thankyouslipid, HttpServletRequest request) {
        logger.info("***** GET MEMBER BUSINESS BY ID *****");
        MemberThankYouSlip memberThankYouSlip = memberReferralDao.getBusinessDetailById(thankyouslipid);
        return memberThankYouSlip;
    }
	
	
	@RequestMapping(value = "/meeting", method = RequestMethod.GET, produces = "application/json")
    public List<MemberOneToOne> Meeting(HttpServletRequest request) {
        logger.info("********** Inside Referral Controller **********");
        List<MemberOneToOne> memberOneToOne = memberReferralDao.getAllMeeting();
        return memberOneToOne;
    }
	
	@RequestMapping(value = "/deleteMeeting", method = RequestMethod.DELETE)
    public void deletes(int meetingid) {
        logger.info("********** INSIDE DELETE MEETING ********** ");

        memberReferralDao.deleteMeeting(meetingid);
    }
	
	@RequestMapping(value = "/getMeetingDetailById", method = RequestMethod.GET, produces = "application/json")
    public MemberOneToOne getMeetingDetailById(int onetooneid, HttpServletRequest request) {
        logger.info("***** GET MEMBER Meeting BY ID *****");
        MemberOneToOne memberOneToOne = memberReferralDao.getMeetingDetailById(onetooneid);
        return memberOneToOne;
    }
	
	@RequestMapping(value = "/reference", method = RequestMethod.GET, produces = "application/json")
    public List<MemberReferral> reference(HttpServletRequest request) {
        logger.info("********** Inside Referral Controller **********");
        List<MemberReferral> memberReferral = memberReferralDao.getAllReference();
        return memberReferral;
    }
	
	@RequestMapping(value = "/deleteRef", method = RequestMethod.DELETE)
    public void deletereference(int referenceid) {
        logger.info("********** INSIDE DELETE ADMIN REFERENCE ********** ");

        memberReferralDao.deleteRef(referenceid);
    }
		
	@RequestMapping(value = "/getReferenceDetailById", method = RequestMethod.GET, produces = "application/json")
    public MemberReferral getReferenceDetailById(int memberreferralid, HttpServletRequest request) {
        logger.info("***** GET MEMBER REFERENCE BY ID *****");
        MemberReferral memberReferral = memberReferralDao.getReferenceDetailById(memberreferralid);
        return memberReferral;
    }
	
	@RequestMapping(value="/ChangeRefStatus",method= RequestMethod.POST)
	public String ChangeRefStatus(int memberreferralid, String status)
	{	logger.info("***** UPDATE MEMBER REFERRAL Status *****");
	memberReferralDao.ChangeRefStatus(memberreferralid,status);
		return "Success";
	}
	
	@RequestMapping(value="/CloseRef",method= RequestMethod.POST)
	public String CloseRef(HttpServletRequest request, HttpSession session, int memberreferralid, String closeComment, String closeReason)
	{	logger.info("***** CLOSE MEMBER REFERRAL *****");
	MemberReferral memberReferral = new MemberReferral();
	memberReferral.setMemberReferralId(memberreferralid);
	memberReferral.setCloseComment(closeComment);
	memberReferral.setCloseReason(closeReason);
	memberReferral.setReferralStatus("C");
	memberReferralDao.CloseRef(memberReferral);
		return "Success";
	}
	
	@RequestMapping(value="/updateReferral",method= RequestMethod.POST)
	public String updateReferral(HttpServletRequest request, HttpSession session,int Referralid, int tomemberid, String referralname, String referdate, String closedate, String referraltype, String card, String call, String referralemail,String referalstatus, String apprvalue, String referralcontactno, String referraladdress, String comment,String closeComment, String closeReason)
	{
		logger.info("********** UPDATE REFERRAL **********");		
			
		String resp="";
		
		memberReferral = new MemberReferral();
		memberReferral.setMemberReferralId(Referralid);
		memberReferral.setToMemberId(tomemberid);
		memberReferral.setReferralName(referralname);
		memberReferral.setReferDate(referdate);
		memberReferral.setReferralType(referraltype);
		memberReferral.setReferralStatus1(card);
		memberReferral.setReferralStatus2(call);
		memberReferral.setAddress(referraladdress);
		memberReferral.setContactNumber(referralcontactno);
		memberReferral.setEmail(referralemail);
		memberReferral.setComments(comment);
		memberReferral.setCloseDate(closedate);
		memberReferral.setApprValue(apprvalue);
		memberReferral.setReferralStatus(referalstatus);
		memberReferral.setCloseComment(closeComment);
		memberReferral.setCloseReason(closeReason);
				
		resp = memberReferralDao.updateReferral(memberReferral);	
		
		return resp;
		
	}
	
}
