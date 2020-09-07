package com.ui.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ui.dao.MemberDAO;
import com.ui.dao.PaymentDAO;
import com.ui.model.Mailer;
import com.ui.model.Members;
import com.ui.model.Order;

@RestController
public class EmailController {
	@Autowired
	MemberDAO memberdao;
	@Autowired
	PaymentDAO paymentdao;

	Members members;

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

	@RequestMapping(value = "sendUserMessage", method = RequestMethod.POST)
	public String sendUserMessage(int memberid, String firstname, String lastname, String email, String mobileno,
			String usermessage, HttpSession session, HttpServletRequest request) {

		logger.info("********** SEND USER MESSAGE **********");
		
		Members members = memberdao.getMemberByMemberId(memberid);
		
		String memberemail = members.getMemberEmail();
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
           	message.setFrom(new InternetAddress("admin@rmbbangalore.org","RMBFB")); 
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(memberemail));
           	message.setRecipients(Message.RecipientType.BCC,myBccList);
           	message.setSubject("User message for you from RMBFB");
           	StringBuilder sb = new StringBuilder();
           	sb.append("<table style='border:solid 1px #bcc2cf; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'><tbody><tr>"
           			+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:10px;' align='left' valign='top'>"
           			+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
           			+ "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:15px;color:#0072c6; border-bottom:solid 1px #dbdfe6' align='left' valign='top' height='25'> You Have New Message From RMBFB </td>"
           			+ "</tr><tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr><tr><td align='left' valign='top' width='412'><table width='100%' cellspacing='0' cellpadding='4' border='0'><tbody><tr>"
           			+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top' width='12%'><strong> Full Name: </strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top' width='77%'> "+firstname+" "+lastname+" </td></tr><tr>"
           			+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top' width='12%'><strong> Email:</strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top'> "+email+" </td></tr><tr>"
           			+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top' width='12%'><strong> Mobile: </strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top'> "+mobileno+" </td></tr><tr>"
           			+ "<td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top' width='12%'><strong> Message : </strong></td><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#373737' align='left' valign='top'> "+usermessage+" </td></tr><tr>"
           			+ "</tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>");        	
           	
            message.setContent(sb.toString(), "text/html");

            Transport.send(message);

            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");	        	
        	
        } catch (Exception msg) {
        	System.out.println("Not send mail "+msg);
        }

		return "Success";
	}
	
	@RequestMapping(value={"sendEmailForForgetPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public String sendEmailForForgetPassword(String firstname, String lastname, String email, HttpSession session, HttpServletRequest request, HttpServletResponse res) { 
	      logger.info("********** INSIDE SEND EMAIL FOR FORGET PASSWORD **********");
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
	          message.setFrom(new InternetAddress("admin@rmbbangalore.org", "RMBFB"));
	          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	          message.setRecipients(Message.RecipientType.BCC, myBccList);            
	          message.setSubject("RMBFB - Password Recovery");
	          StringBuilder sb = new StringBuilder();
	          sb.append("<br><h3> \n Dear "+firstname+" "+lastname+",</h3>");
	          sb.append("<br/><h2> Please visit this link to reset your password: <a href='http://www.rmbbangalore.org/reset_password'>Click here...</a></h2>");
	          sb.append("<br/><h2> For Any Queries, you may contact us<br/><br/></h2>");
	          sb.append("<br/><br/><h2> With Regards,</h2>");
	          sb.append("<h2> RMBF, Bangalore</h2>");
	          message.setContent(sb.toString(), "text/html");          
	          Transport.send(message);          
	          System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
	        } catch (Exception msg) {
	          System.out.println("Not send mail " + msg);
	        }
	    return "";
	  }
	
	@RequestMapping(value={"joinRmbf"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	/*@RequestMapping(value = "joinRmbf", method = RequestMethod.POST)*/
    public String joinRmbf(String firstname, String lastname, String mobilenumber, String email, String rotarian, String description, HttpSession session, HttpServletRequest request, HttpServletResponse res) { 
        logger.info("********** INSIDE SEND EMAIL FOR JOIN RMBFB **********");
        
        //final String username = "emailrelay@rmbbangalore.com";       
        //final String password = "RMBB5463s@ndEsh";
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
            message.setFrom(new InternetAddress("admin@rmbbangalore.org", "RMBFB"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.BCC, myBccList);            
            message.setSubject("RMBFB - Join RMBFB");
            StringBuilder sb = new StringBuilder();
            sb.append(
                  "<table style='border:solid 1px #fec736; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
                          + "<tbody><tr><td style='padding:15px 0px;  font-size: 14px;  color: #373737; ' align='center' valign='middle'> <a href='http://www.rmbbangalore.org/'> <img border='0' src=''/></a></td></tr><tr><td style='line-height:0;font-size:0;vertical-align:top;padding:0px;text-align:left;border-bottom:4px solid #005DAA'></td>"
                          + "</tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737'> Dear <strong> "
                          + firstname + " " + lastname+ ","
                          + "</strong></p><br>"
                          + "Thank you for showing interest in joining RMBF Bangalore!.One of our members will be in touch with you soon.<br>"
                          +"<table style='margin-top:10px;' width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td style='background-color:#fff; padding:15px; border:solid 1px #dbdfe6' align='left' valign='top'>"
                          + "<table width='100%' cellspacing='0' cellpadding='0' border='0'><tbody><tr><td colspan='3' align='left' valign='top'>&nbsp;</td></tr><tr><td align='left' valign='top' width='412'><table width='100%' cellspacing='0' cellpadding='4' border='0'><tbody>"
                          + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'>Mobile No.:</td><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='77%'> "
                          + mobilenumber
                          + " </td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'> Email: </td>"
                          + "<td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top'> "
                          + email
                          + " </td></tr><tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top' width='12%'> Rotarian:</td><td style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737' align='left' valign='top'> "
                          + rotarian + "</td>"
                          + "</tr><tr><td colspan='2' style='font-family:Arial,Helvetica,sans-serif;font-size:14px;color:#373737;    line-height: 22px;' align='left' valign='top'>"
                          + "" + description + ""
                          + "</td></tr><tr><td colspan='3' style='font-family:Arial,Helvetica,sans-serif; font-size:14px;color:#0072c6; ' align='left' valign='top' height='15'>"
                          + "</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>");

          message.setContent(sb.toString(), "text/html");          
            Transport.send(message);          
            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
          } catch (Exception msg) {
            System.out.println("Not send mail " + msg);
          }
      return "Success";
    }
	
	
	@RequestMapping(value = "sendOrderConfirmationMail", method = RequestMethod.POST)
    public String sendOrderConfirmationMail(String ordernumber, HttpSession session, HttpServletRequest request,
            HttpServletResponse res) {

        logger.info("***** SEND ORDER CONFIRMATION EMAIL *****");

        String result = null;
        int id = Integer.parseInt(session.getAttribute("memberid").toString());
                       
        Order od = paymentdao.getOrderDetailByOrderNumber(ordernumber);
        
        Members u = memberdao.getMemberDetailByMemberId(id);
       
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
        
        System.out.println("demo----"+u.getMemberEmail());
        
        try {
            InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");
            Message message = new MimeMessage(session1);
            message.setFrom(new InternetAddress(username, "RMBFB"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getMemberEmail()));
            message.setRecipients(Message.RecipientType.BCC, myBccList);
            message.setSubject("Payment Confirmation- RMBFB");
            StringBuilder sb = new StringBuilder();
            sb.append(
                    "<table style='border:solid 1px #bcc2cf; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
                            + "<tbody>"
                            + "<tr><td style='padding:15px 0px;  font-size: 16px;  color: #373737; background-color: #f7f7f7;' align='center' valign='middle'><a href='http://rmbbangalore.org'> "
                            + "<img border='0' src=''/></a></td></tr>"
                            + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'><p> Dear "
                            + u.getMemberFirstName() + " " + u.getMemberLastName() + ",</p>" + "Your payment of Rs. "
                            + od.getTotalAmount() + " transaction is successful.<br><br>"
                            + "With warm regards and best of wishes,<br>" + "<strong> RMBF Bangalore"
                            + "</strong><br></td></tr></tbody></table></td></tr></tbody>"
                            + "</table></td></tr></tbody></table>");

            message.setContent(sb.toString(), "text/html");
            Transport.send(message);
            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
            result = "Success";
        } catch (Exception msg) {
            result = "Fail";
            System.out.println("Not send mail " + msg);
        }

        return result;
    }

    @RequestMapping(value = "sendOrderConfirmationSMS", method = RequestMethod.POST)
    public String sendOrderConfirmationSMS(String ordernumber, HttpSession session, HttpServletRequest request,
            HttpServletResponse res) {

        logger.info("***** SEND ORDER CONFIRMATION SMS *****");

        String result = null;

        int id = Integer.parseInt(session.getAttribute("memberid").toString());

        Order od = paymentdao.getOrderDetailByOrderNumber(ordernumber);
        
        Members u = memberdao.getMemberDetailByMemberId(id);

        System.out.println("User Mobile Number-------------------------->" + u.getMemberMobileNumber());

        try {
            String url = "http://sms.astartechnologies.net/sendsms.aspx";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to=" + u.getMemberMobileNumber()
                    + "&msg= Dear " + u.getMemberFirstName() + " " + u.getMemberLastName() + ", your payment of Rs. "
                    + od.getTotalAmount() + "  has been successful.";

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

                result = "Success";
            } else {
                result = "Fail";
                System.out.println("http response code error: " + responseCode + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "Fail";
        }

        return result;
    }
    
    
    
    @RequestMapping(value = "/emailSmsBlast", method = RequestMethod.POST)
    public String sendEmailSmsBlast(@RequestBody final Mailer mai, final boolean phoneCheck,final boolean emailCheck, HttpSession session, HttpServletRequest request,
            HttpServletResponse res) {
    	new Thread(new Runnable() {
            public void run(){
            	sendEmailSmsBlaster(mai,phoneCheck,emailCheck);
            }
        }).start();
    	return "Successful";
    }

	    public String sendEmailSmsBlaster(Mailer mai, boolean phoneCheck, boolean emailCheck) {
		 	
	    	
	    	List<Members> members = mai.getMembers();
	    	String Content = mai.getMessage();
		 	Content = Content.replace('$', '&');
		 	Content = Content.replace('~', '#');
		 	Content = Content.replace('!', '%');
	        logger.info("***** SEND BULK EMAIL *****");
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
	        if(emailCheck) {
	        	for(Members m : members)
	        	{
			        try {
			        	
			        	String email1 = m.getMemberEmail();
			            InternetAddress[] myBccList = InternetAddress.parse("webmaster@ultrainfotech.net");
			            Message message = new MimeMessage(session1);
			            message.setFrom(new InternetAddress(username, "RMBFB"));
			            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email1));
			            message.setRecipients(Message.RecipientType.BCC, myBccList);
			            message.setSubject("Communication from RMBFB");
			            StringBuilder sb = new StringBuilder();
			            sb.append(
			                    "<table style='border:solid 1px #bcc2cf; font-family: Arial,Helvetica,sans-serif;' align='center' width='750' cellspacing='0' cellpadding='0' border='0'>"
			                            + "<tbody>"
			                            + "<tr><td style='padding:15px 0px;  font-size: 16px;  color: #373737; background-color: #f7f7f7;' align='center' valign='middle'><a href='http://rmbbangalore.org'> "
			                            + "<img border='0' src=''/></a></td></tr>"
			                            + "<tr><td style='font-family:Arial,Helvetica,sans-serif;font-size:12px; color:#373737; background-color:#fff; padding:20px;' align='left' valign='top'>"
			                            + Content + "<br><br>"
			                            + "With warm regards and best of wishes,<br>" + "<strong> RMBF Bangalore"
			                            + "</strong><br></td></tr></tbody></table></td></tr></tbody>"
			                            + "</table></td></tr></tbody></table>");
		
			            message.setContent(sb.toString(), "text/html");
			            Transport.send(message);
			            System.out.println("E-Mail Send Suceessfully For Delivery...Using JSP.........");
			        } catch (Exception msg) {
			            System.out.println("Not send mail " + msg);
			        }
	        	}
	        }
	        
	        
	        logger.info("***** SEND ORDER CONFIRMATION SMS *****");
	        Content = Content.replaceAll("\\<.*?\\>", "");
	        Content = Content.replace("&nbsp;", " ");
	        Content = Content.replace("&amp;", "&");
	        Content = Content.replaceAll("&.*?;", "");
	        if(phoneCheck) {
	        	for(Members m : members)
	        	{
			        try {
			        	
			        	String phone = m.getMemberMobileNumber();
			            String url = "http://sms.astartechnologies.net/sendsms.aspx";
			            URL obj = new URL(url);
			            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			            con.setRequestMethod("POST");
			            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			            String msg2 = URLEncoder.encode(Content, "UTF-8");
			            String urlParameters = "mobile=9845326217&pass=Superrmb#1&senderid=RMBBLR&to=" + phone
			            + "&msg="+ msg2;
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

	        return "Success";
	 }
	
	
}//end
