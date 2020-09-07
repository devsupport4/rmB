package com.ui.dao;

import java.util.List;

import com.ui.model.Members;
import com.ui.model.MembershipCharges;
import com.ui.model.Order;
import com.ui.model.Payment;
import com.ui.model.PaymentDetail;
import com.ui.model.PaymentImage;

public interface PaymentDAO
{
	public List<Payment> getAllInstallment();
	public List<Payment> getPayment(int memberid);		
	public void addPaymentCheque(Payment p);
	public void addPaymentDemandDraft(Payment p);
	public void addPaymentNeft(Payment p);
	public void addPaymentCash(Payment p);
	public void addPaymentOther(Payment p);
	public int getLastPaymentId();
	public void addPaymentImage(PaymentImage p);
	public void deletePaymentImage(int paymentid);
	public void editPaymentCheque(Payment p);
	public void editPaymentDemandDraft(Payment p);
	public void editPaymentNeft(Payment p);
	public void editPaymentCash(Payment p);
	public void editPaymentOther(Payment p);
	public void deletePayment(int paymentid);
	public void addMembershipCharges(MembershipCharges mc);
	public List<MembershipCharges> getMembershipCharges(int rotaryyearid);
	public List<Members> getMembershipChargesByMember();
	public PaymentDetail getLastPaymentDetail();
	public void addMembershipPaymentTransaction(PaymentDetail pd);
	public List<PaymentDetail> getPendingPaymentsByPage(int pagesize, int startindex);
	public List<PaymentDetail> getAllPendingPayments();
	public List<PaymentDetail> searchPendingPayments(String keyword);
	public PaymentDetail getPendingPaymentDetailByMemberId(int memberid);
	public String getLastPaymentNo();
	public int getLastSequence();
	public void addPaymentInfo(PaymentDetail pd);
	public List<PaymentDetail> getPaymentHistoryByPage(int rotaryyearid, int pagesize, int startindex);
	public List<PaymentDetail> getAllPaymentHistory(int rotaryyearid);
	public void addPayment(PaymentDetail pd);
	public Order getLastOrderDetail();
	public String addOrder(Order od);
	public String addCcavenueResponse(Order orderDetail);
	public Order getOrderDetailByOrderNumber(String ordernumber);
	public List<Order> getOrderDetailByOrderNumber(int memberid);
	public List<Order> getMemberOrdersById(int memberid, String fromdate, String todate);
	public List<Order> getOrdersByDate(String fromdate, String todate, int pagesize, int startindex, String eventtype);
	public List<Order> searchOrders(String keyword);
	public List<Order> getAllOrderDetails(String fromdate, String todate, String eventtype);
	public List<Order> getMemberOrdersByDateAndPage(int memberid,String fromdate, String todate, int pagesize, int startindex, String eventtype);
	public List<Order> getAllMembersOrderDetails(int memberid,String fromdate, String todate, String eventtype);
	public Order getOrderDetailByEventIdandMemberId(int eventId, int memberId);
}
