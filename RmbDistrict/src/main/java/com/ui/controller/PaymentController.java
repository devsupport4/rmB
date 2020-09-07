package com.ui.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ui.dao.CountryDAO;
import com.ui.dao.MemberDAO;
import com.ui.dao.PaymentDAO;
import com.ui.dao.RotaryYearDAO;
import com.ui.dao.StateDAO;
import com.ui.model.Country;
import com.ui.model.MemberCategoryByYear;
import com.ui.model.Members;
import com.ui.model.MembershipCharges;
import com.ui.model.Order;
import com.ui.model.Payment;
import com.ui.model.PaymentDetail;
import com.ui.model.PaymentImage;
import com.ui.model.State;

@RestController
public class PaymentController
{
	@Autowired	
	PaymentDAO paymentdao;
	@Autowired
	MemberDAO memberdao;
	@Autowired
	RotaryYearDAO rotaryyeardao;
	@Autowired
	CountryDAO countryDAO;
	@Autowired
	StateDAO stateDAO;
	
	Payment payment;
	PaymentImage paymentimage;
	MembershipCharges membershipCharges;
	PaymentDetail paymentDetail;
	MemberCategoryByYear memberCategoryByYear;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	
	@RequestMapping(value = "/getOrderDetailByOrderNumber", method = RequestMethod.GET, produces = "application/json")
    public Order getOrderDetailByOrderNumber(String ordernumber, HttpServletRequest request) {
        logger.info("***** GET ORDER DETAIL *****");
        Order od = paymentdao.getOrderDetailByOrderNumber(ordernumber);
        /*od.setOrderProductDetails(orderDao.getOrderProductDetailByOrderNumber(ordernumber));
        od.setProgramRegistration(programDao.getProgramRegistrationDetailById(
                orderDao.getOrderProductDetailByOrderNumber(ordernumber).getRegistrationId()));
        od.setOrderTaxList(orderDao.getOrderTaxByOrderNumber(ordernumber));*/

        return od;
    }
	
	
	
	@RequestMapping(value = "/getOrdersByDateAndPage", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getOrdersByDate(String fromdate, String todate, int pagesize, int startindex, String eventtype, HttpServletRequest request) {
        logger.info("***** GET ORDERS By DATE *****");
        List<Order> od = paymentdao.getOrdersByDate(fromdate, todate,pagesize,startindex,eventtype);

        return od;
    }
	
	@RequestMapping(value = "/getMemberOrdersByDateAndPage", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getMemberOrdersByDateAndPage(int memberid,String fromdate, String todate, int pagesize, int startindex, String eventtype, HttpServletRequest request) {
        logger.info("***** GET ORDERS By DATE *****");
        List<Order> od = paymentdao.getMemberOrdersByDateAndPage(memberid,fromdate, todate,pagesize,startindex,eventtype);

        return od;
    }
	
	@RequestMapping(value = "/getAllMembersOrderDetails", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getAllMembersOrderDetails(int memberid,String fromdate, String todate, String eventtype, HttpServletRequest request) {
        logger.info("***** GET ORDERS By DATE *****");
        List<Order> od = paymentdao.getAllMembersOrderDetails(memberid,fromdate,todate,eventtype);

        return od;
    }
	
	@RequestMapping(value = "/getAllOrderDetails", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getAllOrderDetails(String fromdate, String todate, String eventtype, HttpServletRequest request) {
        logger.info("***** GET ORDERS By DATE *****");
        List<Order> od = paymentdao.getAllOrderDetails(fromdate,todate,eventtype);

        return od;
    }
	
	@RequestMapping(value = "/searchOrderMembers", method = RequestMethod.GET, produces = "application/json")
	public List<Order> searchOrderMembers(HttpServletRequest request,String keyword) {
		logger.info("********** Inside paymnet Controller **********");

		List<Order> orlist = paymentdao.searchOrders(keyword);

		return orlist;
	}
	
	@RequestMapping(value = "/getMemberOrdersById", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getMemberOrdersById(int memberid, String fromdate, String todate, HttpServletRequest request) {
        logger.info("***** GET ORDERS By MEMBERID AND DATE *****");
        List<Order> od = paymentdao.getMemberOrdersById(memberid, fromdate, todate);

        return od;
    }
	
	@RequestMapping(value = "/getOrderListByMemberId", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getOrderListByMemberId(int memberid, HttpServletRequest request) {
        logger.info("***** GET ORDER DETAIL *****");
        List<Order> od = paymentdao.getOrderDetailByOrderNumber(memberid);

        return od;
    }
	
	@RequestMapping(value = "payPaymentOnline", method = RequestMethod.POST)
	public String payPaymentOnline(int eventid, int memberid , String firstname, String lastname, String email, String mobileno, String address1, String address2, int countryname, int statename, String cityname, String pincode, float amount, HttpServletRequest request,
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

		Members m = memberdao.getMemberDetailByMemberId(memberid);
		Country c = countryDAO.getCountryDetailById(countryname);
		State s = stateDAO.getStateDetailById(statename);

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
		order.setBillerAddress1(address1);
		order.setBillerAddress2(address2);
		order.setBillerCountryName(c.countryName);
		order.setBillerStateName(s.getStateName());
		order.setBillerCityName(cityname);
		order.setBillerPincode(pincode);
		
		//order.setOrderDate(q.getCreatedDate());
				
		order.setTotalAmount(amount);
		order.setSubTotal(amount);

		order.setMemberId(memberid);
		order.setOrderStatus("Incomplete");
		order.setPaymentStatus("Unpaid");
		order.setPaymentMode("To Be Selected");
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
	
	
	@RequestMapping(value = "ccavenueResponse", method = RequestMethod.POST)
	public String ccavenueResponse(String orderid, String trackingid, String bankrefnumber, String orderstatus,
			String failuremessage, String paymentmode, String cardname, String statuscode, String statusmessage,
			String currency, String createddate, String createdtime, HttpSession session, HttpServletRequest request,
			HttpServletResponse res) {
		logger.info("***** ADD CCAVENUE RESPONSE *****");

		trackingid = trackingid.equalsIgnoreCase("null") ? "" : trackingid;
		bankrefnumber = bankrefnumber.equalsIgnoreCase("null") ? "" : bankrefnumber;
		orderstatus = orderstatus.equalsIgnoreCase("null") ? "" : orderstatus;
		failuremessage = failuremessage.equalsIgnoreCase("null") ? "" : failuremessage;
		paymentmode = paymentmode.equalsIgnoreCase("null") ? "" : paymentmode;
		cardname = cardname.equalsIgnoreCase("null") ? "" : cardname;
		statuscode = statuscode.equalsIgnoreCase("null") ? "" : statuscode;
		currency = currency.equalsIgnoreCase("null") ? "" : currency;

		Order orderDetail = new Order();
		orderDetail.setOrderNumber(orderid);
		orderDetail.setTrackingId(trackingid.isEmpty() ? "" : trackingid);
		orderDetail.setBankReferenceNumber(bankrefnumber.isEmpty() ? "" : bankrefnumber);
		orderDetail.setOrderStatus("New");
		orderDetail.setFailureMessage(failuremessage.isEmpty() ? "" : failuremessage);
		orderDetail.setPaymentStatus(orderstatus.isEmpty() ? "" : orderstatus);
		orderDetail.setPaymentMode(paymentmode.isEmpty() ? "" : paymentmode);
		orderDetail.setCardName(cardname.isEmpty() ? "" : cardname);
		orderDetail.setBankName(cardname.isEmpty() ? "" : cardname);
		orderDetail.setStatusCode(statuscode.isEmpty() ? 0 : Integer.parseInt(statuscode));
		orderDetail.setCurrency(currency.isEmpty() ? "" : currency);

		session.setAttribute("orderStatus", orderstatus);
		session.setAttribute("trackingid", trackingid);

		paymentdao.addCcavenueResponse(orderDetail);

		return "Success";

	}
	
	
	
	@RequestMapping(value="/getAllInstallment", method= RequestMethod.GET, produces = "application/json")
	public List<Payment> getAllInstallment(HttpServletRequest request)
	{
		logger.info("Inside Get All Installment");
		
		List<Payment> installment = paymentdao.getAllInstallment();
		
		return installment;
	}
	
	@RequestMapping(value="/getpayment", method= RequestMethod.GET, produces = "application/json")
	public List<Payment> getpayment(int memberid, HttpServletRequest request)
	{
		logger.info("Inside Get Payment");		
		List<Payment> payment = paymentdao.getPayment(memberid);		
		return payment;
	}
	
	
	@RequestMapping(value="addPaymentCheque", method= RequestMethod.POST)
	public String addPaymentCheque(String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String chequenumber, String chequedate, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Member Payment Cheque");	
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		payment = new Payment(transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,chequenumber,chequedate,comments,amountcharges,Integer.parseInt(session.getAttribute("memberid").toString()),s,1,IpAddress);		
		paymentdao.addPaymentCheque(payment);
	
		return "";
	}
	
	@RequestMapping(value="addPaymentDemandDraft", method= RequestMethod.POST)
	public String addPaymentDemandDraft(String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String demanddraftnumber, String demanddraftdate, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Member Payment Demand Draft");	
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		payment = new Payment(transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,demanddraftnumber,demanddraftdate,comments,amountcharges,Integer.parseInt(session.getAttribute("memberid").toString()),s,1,IpAddress);	
		paymentdao.addPaymentDemandDraft(payment);
	
		return "";
	}
	
	@RequestMapping(value="addPaymentNeft", method= RequestMethod.POST)
	public String addPaymentNeft(String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String transactionnumber, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Member Payment NEFT");	
		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";

		payment = new Payment(transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,transactionnumber,comments,amountcharges,Integer.parseInt(session.getAttribute("memberid").toString()),s,1,IpAddress);
		
		paymentdao.addPaymentNeft(payment);		
	
		return "";
	}
	
	@RequestMapping(value="addPaymentCash", method= RequestMethod.POST)
	public String addPaymentCash(String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String transactionnumber, String contactnumber, String paymentplace, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Member Payment Cash");			
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}		
		String s = "y";		
		payment = new Payment(transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,transactionnumber,contactnumber,paymentplace,comments,amountcharges,Integer.parseInt(session.getAttribute("memberid").toString()),s,1,IpAddress);
		paymentdao.addPaymentCash(payment);		
	
		return "";
	}
	
	@RequestMapping(value="addPaymentOther", method= RequestMethod.POST)
	public String addPaymentOther(String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String transactionnumber, String contactnumber, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Member Payment Other");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";		
		payment = new Payment(transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,transactionnumber,contactnumber,comments,amountcharges,Integer.parseInt(session.getAttribute("memberid").toString()),s,1,IpAddress);
		paymentdao.addPaymentOther(payment);
		
		return "";
	}
	
	@RequestMapping(value="addPaymentImage", method= RequestMethod.POST)
	public String addPaymentImage(@RequestParam(value="file", required=false) MultipartFile[] file,HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Add Product Sourcing Payment Image");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		int paymentid = paymentdao.getLastPaymentId();
		
		String image = null;

		try
		{
			if (file != null && file.length > 0)
			{
				try
				{
					for(int i=0; i<file.length; i++)
					{
						byte[] bytes =  file[i].getBytes();
						
						File dir = new File(request.getRealPath("")+"/resources/admin/images/" + File.separator + "payment");
		    			if (!dir.exists()) 
		    				dir.mkdirs();
						
		    			String path = request.getRealPath("/resources/admin/images/payment/");
			            File uploadfile = new File(path+File.separator+file[i].getOriginalFilename());
			            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			            bufferedOutputStream.write(bytes);
			            bufferedOutputStream.close();
			            
			            //image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/rcbsEvent/resources/admin/images/payment/"+file[i].getOriginalFilename();
			            image = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/resources/admin/images/payment/"+file[i].getOriginalFilename();
			            
			            paymentimage = new PaymentImage(image,paymentid,1,IpAddress);			    		
			    		paymentdao.addPaymentImage(paymentimage);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		
		return "";
	}
	
	@RequestMapping(value="/deletePaymentImage",method=RequestMethod.DELETE)
	public void deletePaymentImage(int paymentid)
	{
		paymentdao.deletePaymentImage(paymentid);
	}
	
	@RequestMapping(value="editPaymentCheque", method= RequestMethod.POST)
	public String editPaymentCheque(int paymentid, String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String chequenumber, String chequedate, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Payment Cheque");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		payment = new Payment(paymentid, transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,chequenumber,chequedate,comments,amountcharges,s,1,IpAddress);		
		paymentdao.editPaymentCheque(payment);
	
		return "";
	}
	
	@RequestMapping(value="editPaymentDemandDraft", method= RequestMethod.POST)
	public String editPaymentDemandDraft(int paymentid, String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String demanddraftnumber, String demanddraftdate, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Payment Demand Draft");	
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		payment = new Payment(paymentid, transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,demanddraftnumber,demanddraftdate,comments,amountcharges,s,1,IpAddress);
		paymentdao.editPaymentDemandDraft(payment);
	
		return "";
	}
	
	@RequestMapping(value="editPaymentNeft", method= RequestMethod.POST)
	public String editPaymentNeft(int paymentid, String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String bankname, String branchname, String accountnumber, String transactionnumber, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Payment NEFT");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";

		payment = new Payment(paymentid, transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,bankname,branchname,accountnumber,transactionnumber,comments,amountcharges,s,1,IpAddress);
		paymentdao.editPaymentNeft(payment);		
	
		return "";
	}
	
	@RequestMapping(value="editPaymentCash", method= RequestMethod.POST)
	public String editPaymentCash(int paymentid, String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String transactionnumber, String contactnumber, String paymentplace, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Payment Cash");	
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		
		String s = "y";
		
		payment = new Payment(paymentid, transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,transactionnumber,contactnumber,paymentplace,comments,amountcharges,s,1,IpAddress);
		paymentdao.editPaymentCash(payment);		
	
		return "";
	}
	
	@RequestMapping(value="editPaymentOther", method= RequestMethod.POST)
	public String editPaymentOther(int paymentid, String transactiondate, float transactionamount, int currencyidtransactionamount, float transactioncharges, int currencyidtransactioncharges, String paidtowhom, String paymenttype, String transactionnumber, String contactnumber, String comments, float amountcharges, HttpServletRequest request, HttpSession session)
	{
		logger.info("Inside Edit Payment Other");		
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null)
		{
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		payment = new Payment(paymentid, transactiondate,transactionamount,currencyidtransactionamount,transactioncharges,currencyidtransactioncharges,paidtowhom,paymenttype,transactionnumber,contactnumber,comments,amountcharges,s,1,IpAddress);
		paymentdao.editPaymentOther(payment);
		
		return "";
	}
	
	@RequestMapping(value="/deletePayment",method=RequestMethod.DELETE)
	public void deletePayment(int paymentid)
	{
		paymentdao.deletePayment(paymentid);
	}
	
	@RequestMapping(value="addMembershipCharges", method= RequestMethod.POST)
	public String addMembershipCharges(int rotaryyearid, int membercategoryid, float billingamount, int currencyid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD MEMBERSHIP CHARGES *****");	
		int id  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";		
		membershipCharges = new MembershipCharges(rotaryyearid,membercategoryid,billingamount,currencyid,s,id,IpAddress);
		paymentdao.addMembershipCharges(membershipCharges);	
		
		return "Success";
	}
	
	@RequestMapping(value="/getMembershipCharges", method= RequestMethod.GET, produces = "application/json")
	public List<MembershipCharges> getMembershipCharges(int rotaryyearid, HttpServletRequest request) {
		logger.info("***** GET MEMBERSHIP CHARGES *****");		
		List<MembershipCharges> membershipCharges = paymentdao.getMembershipCharges(rotaryyearid);		
		return membershipCharges;
	}
	
	@RequestMapping(value="/getMembershipChargesByMember", method= RequestMethod.GET, produces = "application/json")
	public List<Members> getMembershipChargesByMember(HttpServletRequest request) {
		logger.info("***** GET MEMBERSHIP CHARGES BY MEMBER *****");		
		List<Members> member = paymentdao.getMembershipChargesByMember();		
		return member;
	}
	
	@RequestMapping(value="addMembershipPaymentTransaction", method= RequestMethod.POST)
	public String addMembershipPaymentTransaction(int memberid, int rotaryyearid, int membercategoryid, float billingamount, int currencyid, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD MEMBERSHIP PAYMENT TRASACTION *****");	
		int id  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}
		String s = "y";
		
		/*memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryid, id, IpAddress);
		memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);*/
		
		paymentDetail = new PaymentDetail(1,rotaryyearid,memberid,membercategoryid,billingamount,currencyid,"Unpaid",s,id,IpAddress);
		paymentdao.addMembershipPaymentTransaction(paymentDetail);
		
		memberCategoryByYear = new MemberCategoryByYear(rotaryyearid, memberid, membercategoryid, id, IpAddress);
		memberdao.addMemberCategoryByRotaryYear(memberCategoryByYear);
		
		return "Success";	
	}
	
	@RequestMapping(value="/getPendingPaymentsByPage", method= RequestMethod.GET, produces = "application/json")
	public List<PaymentDetail> getPendingPaymentsByPage(int pagesize, int startindex, HttpServletRequest request) {
		logger.info("********** GET PENDING PAYMENTS BY PAGE **********");		
		List<PaymentDetail> paymentDetail = paymentdao.getPendingPaymentsByPage(pagesize, startindex);		
		return paymentDetail;
	}
	
	@RequestMapping(value="/getAllPendingPayments", method= RequestMethod.GET, produces = "application/json")
	public List<PaymentDetail> getAllPendingPayments(HttpServletRequest request) {
		logger.info("********** GET ALL PENDING PAYMENTS **********");		
		List<PaymentDetail> paymentDetail = paymentdao.getAllPendingPayments();		
		return paymentDetail;
	}
	
	@RequestMapping(value="/searchPendingPayments", method= RequestMethod.GET, produces = "application/json")
	public List<PaymentDetail> searchAdmittedPatient(String keyword, HttpServletRequest request) {
		logger.info("********** SEARCH PENDING PAYMENTS **********");			
		List<PaymentDetail> PaymentDetail = paymentdao.searchPendingPayments(keyword);		
		return PaymentDetail;
	}
	
	@RequestMapping(value="/getPendingPaymentDetailByMemberId", method= RequestMethod.GET, produces = "application/json")
	public PaymentDetail getPendingPaymentDetailByMemberId(int memberid, HttpServletRequest request) {
		logger.info("********** GET PAYMENT DETAIL BY MEMBER ID **********");			
		PaymentDetail PaymentDetail = paymentdao.getPendingPaymentDetailByMemberId(memberid);		
		return PaymentDetail;
	}
	
	@RequestMapping(value="addPaymentInfo", method= RequestMethod.POST)
	public String addPaymentInfo(int id, String paymentmethod, String bankname, String branchname, String chequeno, String paymentdate, float paidamount, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PAYMENT INFO *****");	
		int cid  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		
		String paymentno = null;
		int sequence = 0;
		
		String currentYear = rotaryyeardao.getCurrentRotaryYearCode();
		String currentyearcode1 = currentYear.substring(14, 16);
		String currentyearcode2 = currentYear.substring(17, 19);
		String currentyearcode = currentyearcode1+currentyearcode2;
		
		paymentno = paymentdao.getLastPaymentNo();
		
		if(paymentno == null) {
			sequence = 1;
			paymentno = "RCBS" + currentyearcode + "00001";
			paymentDetail = new PaymentDetail(id,sequence,paymentno,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
			paymentdao.addPaymentInfo(paymentDetail);
			return "Success";
		} else {
			String pn = paymentno;			
			String rotaryyearcode = pn.substring(4, 8);			
			sequence = paymentdao.getLastSequence();
			
			if(sequence == 0) {
				sequence = 1;
				paymentno = "RCBS" + currentyearcode + "00001";
				paymentDetail = new PaymentDetail(id,sequence,paymentno,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
				paymentdao.addPaymentInfo(paymentDetail);
				return "Success";
			} else {
				if(currentyearcode.equals(rotaryyearcode)) {					
					sequence = sequence + 1;					
				} else {
					sequence = 1;					
				}
				
				if(sequence >= 1 && sequence < 10) {
					paymentno = "RCBS" + currentyearcode + "0000" + Integer.toString(sequence);
				}
				else if(sequence >= 10 && sequence < 100)
				{
					paymentno = "RCBS" + currentyearcode + "000" + Integer.toString(sequence);
				}
				else if(sequence >= 100 && sequence < 1000)
				{
					paymentno = "RCBS" + currentyearcode + "00" + Integer.toString(sequence);
				}
				else if(sequence >= 1000 && sequence < 10000)
				{
					paymentno = "RCBS" + currentyearcode + "0" + Integer.toString(sequence);
				}
				else if(sequence >= 10000 && sequence < 100000)
				{
					paymentno = "RCBS" + currentyearcode + Integer.toString(sequence);
				}
				
				paymentDetail = new PaymentDetail(id,sequence,paymentno,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
				paymentdao.addPaymentInfo(paymentDetail);
				return "Success";
			}
		}	
	}
	
	@RequestMapping(value="/getPaymentHistoryByPage", method= RequestMethod.GET, produces = "application/json")
	public List<PaymentDetail> getPaymentHistoryByPage(int rotaryyearid, int pagesize, int startindex, HttpServletRequest request) {
		logger.info("********** GET PAYMENT HISTORY BY PAGE **********");		
		List<PaymentDetail> paymentDetail = paymentdao.getPaymentHistoryByPage(rotaryyearid, pagesize, startindex);		
		return paymentDetail;
	}
	
	@RequestMapping(value="/getAllPaymentHistory", method= RequestMethod.GET, produces = "application/json")
	public List<PaymentDetail> getAllPaymentHistory(int rotaryyearid, HttpServletRequest request) {
		logger.info("********** GET ALL PAYMENT HISTORY **********");		
		List<PaymentDetail> paymentDetail = paymentdao.getAllPaymentHistory(rotaryyearid);		
		return paymentDetail;
	}
	
	@RequestMapping(value="addPayment", method= RequestMethod.POST)
	public String addPayment(int memberid, int rotaryyearid, int membercategoryid, String paymentmethod, String bankname, String branchname, String chequeno, String paymentdate, float paidamount, HttpServletRequest request, HttpSession session) {
		logger.info("***** ADD PAYMENT *****");	
		int cid  = (Integer) session.getAttribute("loginid");
		String IpAddress = request.getHeader("X-FORWARDED-FOR");
		if (IpAddress == null) {
			IpAddress = request.getRemoteAddr();
		}		
		
		String paymentno = null;
		int sequence = 0;
		
		String currentYear = rotaryyeardao.getCurrentRotaryYearCode();
		String currentyearcode1 = currentYear.substring(14, 16);
		String currentyearcode2 = currentYear.substring(17, 19);
		String currentyearcode = currentyearcode1+currentyearcode2;
		
		paymentno = paymentdao.getLastPaymentNo();
		
		if(paymentno == null) {
			sequence = 1;
			paymentno = "RCBS" + currentyearcode + "00001";
			paymentDetail = new PaymentDetail(sequence,paymentno,rotaryyearid,memberid,membercategoryid,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
			paymentdao.addPayment(paymentDetail);
			return "Success";
		} else {
			String pn = paymentno;			
			String rotaryyearcode = pn.substring(4, 8);			
			sequence = paymentdao.getLastSequence();
			
			if(sequence == 0) {
				sequence = 1;
				paymentno = "RCBS" + currentyearcode + "00001";
				paymentDetail = new PaymentDetail(sequence,paymentno,rotaryyearid,memberid,membercategoryid,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
				paymentdao.addPayment(paymentDetail);
				return "Success";
			} else {
				if(currentyearcode.equals(rotaryyearcode)) {					
					sequence = sequence + 1;					
				} else {
					sequence = 1;					
				}
				
				if(sequence >= 1 && sequence < 10) {
					paymentno = "RCBS" + currentyearcode + "0000" + Integer.toString(sequence);
				}
				else if(sequence >= 10 && sequence < 100)
				{
					paymentno = "RCBS" + currentyearcode + "000" + Integer.toString(sequence);
				}
				else if(sequence >= 100 && sequence < 1000)
				{
					paymentno = "RCBS" + currentyearcode + "00" + Integer.toString(sequence);
				}
				else if(sequence >= 1000 && sequence < 10000)
				{
					paymentno = "RCBS" + currentyearcode + "0" + Integer.toString(sequence);
				}
				else if(sequence >= 10000 && sequence < 100000)
				{
					paymentno = "RCBS" + currentyearcode + Integer.toString(sequence);
				}
				
				paymentDetail = new PaymentDetail(sequence,paymentno,rotaryyearid,memberid,membercategoryid,"Paid",paymentmethod,bankname,branchname,chequeno,paymentdate,paidamount,cid,IpAddress);
				paymentdao.addPayment(paymentDetail);
				return "Success";
			}
		}	
	}
}