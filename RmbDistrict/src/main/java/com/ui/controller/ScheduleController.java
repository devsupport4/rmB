package com.ui.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ui.dao.MemberDAO;
import com.ui.dao.MemberReferralDAO;
import com.ui.model.MemberReferral;
import com.ui.model.Members;
import com.ui.model.MembersFamily;

@Configuration
@EnableScheduling
public class ScheduleController {
	
	@Autowired	
	MemberDAO memberdao;
	@Autowired
	MemberReferralDAO memberReferralDao;
	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Scheduled(cron="0 30 20 * * *")
    public void SendGreetingMails()
    {
		logger.info("********** Inside Send Greeting Mails **********");
		
		List<Members> members =  memberdao.getAllMembers();
		
		Date date = new Date();
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
	    
	    String CurrentDate = simpleDateFormat.format(date);
	    
		for(int i=0; i<members.size();i++)
		{	
			int memberid = members.get(i).getMemberId();
			String memberemail = members.get(i).getMemberEmail();
			String memberFirstName = members.get(i).getMemberFirstName();
			String memberLastName = members.get(i).getMemberLastName();
			String memberDOB = members.get(i).getMemberDateOfBirth();
			String memberAD = members.get(i).getMemberAnniversaryDate();
			String memberMobileNo = members.get(i).getMemberMobileNumber();
			
			MembersFamily memberfamily = (MembersFamily) memberdao.getSpouseData(memberid); 
			String SpouseFirstName = memberfamily.getMemberFamilyFirstName();
			String SpouseLastName  = memberfamily.getMemberFamilyLastName();
			String SpouseDOB = memberfamily.getMemberFamilyDateOfBirth();
			String SpouseEmail = memberfamily.getMemberFamilyEmail();
			
			String DOB = memberDOB.substring(5);
			String AD = memberAD.substring(5);
			String SDOB = SpouseDOB.substring(5);
			
			
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
			
			if(DOB.equals(CurrentDate))
			{
				System.out.println("**************"+memberemail);

		        try
		        {		        	
		        	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");           	            	
		           	Message message = new MimeMessage(session1);
		           	message.setFrom(new InternetAddress("admin@rmbbangalore.org","RMBFB")); 
		        	message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(memberemail));
		        	message.setRecipients(Message.RecipientType.BCC,myBccList);
		        	message.setSubject("Birthday Greetings from RMBFB");
		        	StringBuilder sb = new StringBuilder(); 
		        	sb.append("<table style='background-color:#BDBCC1;border-collapse:collapse;border-spacing:0;border-collapse:collapse;border-spacing:0' width='100%'><tbody><tr><td align='center'>"
		        			+ "<table style='border-collapse:collapse;border-spacing:0' width='660'><tbody><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr>"
		        			+ "<tr><td bgcolor='#ffffff'>&nbsp;</td></tr><tr><td align='center' bgcolor='#ffffff'><a href='http://www.rmbbangalore.org/' target='_blank'><img src='' ></a></td></tr><tr><td height='10' bgcolor='#ffffff'></td></tr>"
		        			+ "<tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr><tr><td bgcolor='#ffffff'><table style='display: inline-table;' border='0' cellpadding='0' cellspacing='0' width='660'>"
		        			+ "<tr><td height='5' colspan='2' bgcolor='#ffffff'></td></tr><tr><td width='440' valign='top'><table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td><img src='http://www.rcbs.co.in/resources/front/images/birthday-top-img.jpg'/></td></tr><tr><td>"
		        			+ "<table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td height='10' width='25'></td><td height='10' width='400'></td><td height='10' width='15'></td></tr><tr><td>&nbsp;</td>"
		        			+ "<td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'> Dear, <strong> "+memberFirstName+" "+memberLastName+"</strong></p></td><td>&nbsp;</td></tr><tr><td height='10'></td><td height='10'></td><td height='10'></td></tr>"
		        			+ "<tr><td>&nbsp;</td><td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'>May this special day bring...  <br>All the joy and success,<br>you ever wished for! <br>"
		        			+ "Wishing you a very <span style='color:#005DAA; font-weight:600;' >HAPPY BIRTHDAY!!</span></p></td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr><tr><td></td></tr></table></td><td width='220' valign='top'> "
		        			+ "<img src='http://www.rmbfmadurai.com/resources/front/images/birthday-right-img.jpg'/></td></tr><tr><td>&nbsp;</td><td valign='top'>&nbsp;</td></tr></table></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr></tbody></table></td></tr></tbody></table>");     	
		        	
		        	message.setContent(sb.toString(), "text/html");

		        	Transport.send(message);

		        	System.out.println("E-Mail Send Suceessfully...Using JSP.........");
		        }
		        catch (Exception msg)
		        {
		        	System.out.println("Not send mail "+msg);
		        }
		        
			}
			
			if(SDOB.equals(CurrentDate))
			{
				System.out.println("**************"+SpouseEmail);

		        try
		        {		        	
		        	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");        	
		        	Message message = new MimeMessage(session1);
		        	message.setFrom(new InternetAddress("emailrelay@astartechnologies.net","RMBFB")); 
		        	message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(SpouseEmail));
		        	message.setRecipients(Message.RecipientType.BCC,myBccList);
		        	message.setSubject("Birthday Greetings from RMBFB");
		        	StringBuilder sb = new StringBuilder(); 
		        	sb.append("<table style='background-color:#BDBCC1;border-collapse:collapse;border-spacing:0;border-collapse:collapse;border-spacing:0' width='100%'><tbody><tr><td align='center'>"
		        			+ "<table style='border-collapse:collapse;border-spacing:0' width='660'><tbody><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr>"
		        			+ "<tr><td bgcolor='#ffffff'>&nbsp;</td></tr><tr><td align='center' bgcolor='#ffffff'><a href='http://www.rmbbangalore.org/' target='_blank'><img src='' ></a></td></tr><tr><td height='10' bgcolor='#ffffff'></td></tr>"
		        			+ "<tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr><tr><td bgcolor='#ffffff'><table style='display: inline-table;' border='0' cellpadding='0' cellspacing='0' width='660'>"
		        			+ "<tr><td height='5' colspan='2' bgcolor='#ffffff'></td></tr><tr><td width='440' valign='top'><table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td><img src='http://www.rcbs.co.in/resources/front/images/birthday-top-img.jpg'/></td></tr><tr><td>"
		        			+ "<table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td height='10' width='25'></td><td height='10' width='400'></td><td height='10' width='15'></td></tr><tr><td>&nbsp;</td>"
		        			+ "<td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'> Dear, <strong> "+SpouseFirstName+" "+SpouseLastName+"</strong></p></td><td>&nbsp;</td></tr><tr><td height='10'></td><td height='10'></td><td height='10'></td></tr>"
		        			+ "<tr><td>&nbsp;</td><td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'>May this special day bring...  <br>All the joy and success,<br>you ever wished for! <br>"
		        			+ "Wishing you a very <span style='color:#005DAA; font-weight:600;' >HAPPY BIRTHDAY!!</span></p></td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr><tr><td></td></tr></table></td><td width='220' valign='top'> "
		        			+ "<img src='http://www.rcbs.co.in/resources/front/images/birthday-right-img.jpg'/></td></tr><tr><td>&nbsp;</td><td valign='top'>&nbsp;</td></tr></table></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr></tbody></table></td></tr></tbody></table>");     	
		        	
		        	message.setContent(sb.toString(), "text/html");

		        	Transport.send(message);

		        	System.out.println("E-Mail Send Suceessfully...Using JSP.........");
		        }
		        catch (Exception msg)
		        {
		        	System.out.println("Not send mail "+msg);
		        }
		        
			}
		        
		    if(AD.equals(CurrentDate))
			{
		    	System.out.println("**************"+memberemail);			

			    try
			    {		        	
			      	InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");        	
			       	Message message = new MimeMessage(session1);
			       	message.setFrom(new InternetAddress("emailrelay@astartechnologies.net","RMBFB")); 
			       	message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(memberemail));
			       	message.setRecipients(Message.RecipientType.BCC,myBccList);
			       	message.setSubject("Anniversary Greetings from RMBFB");
			       	StringBuilder sb = new StringBuilder(); 
			       	sb.append("<table style='background-color:#BDBCC1;border-collapse:collapse;border-spacing:0;border-collapse:collapse;border-spacing:0' width='100%'><tbody><tr><td align='center'><table style='border-collapse:collapse;border-spacing:0' width='660'><tbody><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr><tr><td bgcolor='#ffffff'>&nbsp;</td></tr><tr><td align='center' bgcolor='#ffffff'>"
			       			+ "<a href='http://www.rmbbangalore.org/' target='_blank'><img src='' ></a></td></tr><tr><td height='10' bgcolor='#ffffff'></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td></tr><tr><td bgcolor='#ffffff'>"
			       			+ "<table style='display: inline-table;' border='0' cellpadding='0' cellspacing='0' width='660'><tr><td height='5' colspan='2' bgcolor='#ffffff'></td></tr><tr><td width='440' valign='top'><table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td> <img src='http://www.rcbs.co.in/resources/front/images/anniversary-top-img.jpg'/> </td></tr><tr><td><table width='440' border='0' cellspacing='0' cellpadding='0'><tr><td height='10' width='25'></td><td height='10' width='400'></td><td height='10' width='15'></td></tr><tr><td>&nbsp;</td><td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'> Dear "+memberFirstName+" "+memberLastName+" & "+SpouseFirstName+" "+SpouseLastName+",</p></td><td>&nbsp;</td></tr><tr><td height='10'></td><td height='10'></td><td height='10'></td></tr><tr><td>&nbsp;</td><td><p style='margin:0;color:#005DAA;font-size:16px;line-height:24px;font-family:Arial,Helvetica,sans-serif;text-align:left'>Wishing you both a Long, Happy Married Life!</p></td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr><tr><td></td></tr></table></td><td width='220' valign='top'> <img src='http://www.rcbs.co.in/resources/front/images/anniversary-right-img.jpg'/></td></tr><tr><td>&nbsp;</td><td valign='top'>&nbsp;</td></tr></table></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left' height='18'>&nbsp;</td></tr></tbody></table></td></tr></tbody></table>");     	
			       	
			       	message.setContent(sb.toString(), "text/html");
			        	Transport.send(message);
			        	System.out.println("E-Mail Send Suceessfully...Using JSP.........");
		        }
		        catch (Exception msg)
		        {
		        	System.out.println("Not send mail "+msg);
		        }
	        
	        /*try
			 {
				 String url = "http://sms.astartechnologies.net/sendsms.aspx";
				 URL obj = new URL(url);
				 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				 
				 //add reuqest header
				 con.setRequestMethod("POST");
				 con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				 
				 System.out.println("custPhoneNumber--->"+memberMobileNo);
				 
				 //Test url
				 //String urlParameters = "mobile=9824508568&pass=KDXWM&senderid=PURBTZ&to="+ custPhoneNumber +"&msg=We have received your order ORD"+orderId+" amounting to Rs."+ totalAmount +" and it is being processed. Thank You for placing an order with Pure Bitez!!";
				 
				 //Live url
				 String urlParameters = "mobile=9726721110&pass=CWCFQ&senderid=GHARIS&to="+ memberMobileNo +"&msg=Happy Birthday!";

				 // Send post request 
				 con.setDoOutput(true);
				 DataOutputStream wr = new DataOutputStream(con.getOutputStream()); 
				 wr.writeBytes(urlParameters);
				 wr.flush();
				 wr.close();
	  			
				 int responseCode = con.getResponseCode();
				 System.out.println("responseCode ->"+responseCode +" --- "+con.getContent().toString());				
					 
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}*/
	        		
		}	
	}
}
	
	@Scheduled(cron="0 2 0 * * *")
	public void updateProgramFeaturedStatus() {
		logger.info("***** CHECK REFRENCE EXPIRY *****");
		List<MemberReferral> memberReferral = memberReferralDao.getAllReference();
		/* int i=0; */
        for(MemberReferral MR : memberReferral)
		{
			/*
			 * i++; System.out.println("===  "+i);
			 */
	        MemberReferral memberReferrall = memberReferralDao.getReferenceDetailById(MR.getMemberReferralId());
	        Members memberData = memberdao.getMemberByMemberId(MR.getToMemberId());
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate now = LocalDate.now();
	        String current_date = dtf.format(now);
	        String close_date = memberReferrall.getCloseDate();
			/*
			 * System.out.println("/////////////////////////  "+memberReferrall.
			 * getReferralStatus());
			 */
	        if(current_date.compareTo(close_date)>0 && memberReferrall.getReferralStatus().equals("O")) {
				/* System.out.println("date Expired"); */
	        	memberReferrall = new MemberReferral();
	        	memberReferrall.setMemberReferralId(MR.getMemberReferralId());
	        	memberReferrall.setCloseComment("CLOSED DUE TO EXPIRATION");
	        	memberReferrall.setCloseReason("");
	        	memberReferrall.setReferralStatus("C");
	        	System.out.println("closed refrence of "+MR.getMemberReferralId());
	        	memberReferralDao.CloseRef(memberReferrall);
	        	
	        	String email = memberData.getMemberEmail();
	        	String Phone  = memberData.getMemberMobileNumber();
	        	String fromfirstName = MR.getFromFirstName();
	        	String fromlastName = MR.getFromLastName();
	        	String referdate = MR.getReferDate();
	        	String referraltype = MR.getReferralType();
	        	String card =MR.getReferralStatus1();
	        	String call = MR.getReferralStatus2();
	        	String referralname = MR.getReferralName();
	        	String referraladdress = MR.getAddress();
	        	String referralcontactno = MR.getContactNumber();
	        	String referralemail = MR.getEmail();
	        	String comment = MR.getComments();
	        	
	        	
	        	
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
	    	           	message.setFrom(new InternetAddress("admin@rmbbangalore.org","RMBFB")); 
	    	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	    	            message.setRecipients(Message.RecipientType.BCC, myBccList);
	    	            message.setSubject("Reference Expiration");
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
	    	                  + "<td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:17px;color:#005DAA; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> Sorry! the referral received from  "
	    	                  + fromfirstName
	    	                  +" "
	    	                  + fromlastName
	    	                  + "&nbsp; is expired as it was the last date to respond today</td>"
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
	    	                  + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='100%'><strong>Referral Status:</strong> "
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
	    	                  + "</tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

	    	            message.setContent(sb.toString(), "text/html");
	    	            Transport.send(message);
	    	            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
	    	        } catch (Exception msg) {
	    	            System.out.println("Not send mail " + msg);
	    	        }
	    	        
	    	        
	    	        logger.info("***** Refrence close expiration by date sms *****");
	    	        String Content = "Sorry! the referral received from  "+fromfirstName+" "+ fromlastName+ "is expired as it was the last date to respond today! Please Check your mail for more details";
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
	        	
	        }
		}
		
	}

}
