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

import com.ui.dao.PaymentDAO;
import com.ui.model.Members;
import com.ui.model.MembershipCharges;
import com.ui.model.Order;
import com.ui.model.Payment;
import com.ui.model.PaymentDetail;
import com.ui.model.PaymentImage;

public class PaymentDAOImpl implements PaymentDAO
{
	@Autowired
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentDAOImpl.class);
	
		
	public List<Order> getOrderDetailByOrderNumber(int memberid)
	{
		logger.info("Inside Get Members Payment Impl");
		
		List<Order> sta = new ArrayList<Order>();

		String sql = "select order_detail_id, sequence, order_number, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, subtotal, total_amount, member_id, order_status, payment_status, delivery_type, tracking_id, bank_reference_number, failure_message, payment_mode, card_name, bank_name, status_code, currency, DATE_FORMAT(order_date, '%d/%m/%Y %h:%i %p') as order_date, created_by, created_date, ip_address, order_by from orders where member_id = ? order by order_detail_id";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, memberid);
			
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	@Override
	public List<Order> searchOrders(String keyword) {
		logger.info("********** SEARCH ORDER DETAILS **********");
		String sql = "SELECT ord.order_number,ord.customer_name,ord.customer_mobileno, ord.payment_status,ord.total_amount,DATE_FORMAT(ord.order_date,'%d/%m/%y') as order_date FROM orders ord where Concat(ord.customer_name, '', ord.order_number, '', ord.customer_mobileno, '', ord.total_amount, '',ord.order_date,'',ord.order_status,'',ord.payment_status,'') like ?"; 
			
		Connection conn = null;
		List<Order> orlist=new ArrayList<Order>();

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, '%' + keyword + '%');
			Order or = null;
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				or = new Order();
				or.setOrderNumber(rs.getString("order_number"));
				or.setCustomerName(rs.getString("customer_name"));
				or.setCustomerMobileno(rs.getString("customer_mobileno"));
				or.setTotalAmount(rs.getInt("total_amount"));
				or.setOrderDate(rs.getString("order_date"));
				or.setPaymentStatus(rs.getString("payment_status"));
				orlist.add(or);
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

		
				return orlist;

	}
	
	public List<Order> getMemberOrdersById(int memberid, String fromdate, String todate)
	{
		logger.info("Inside Get Members Payment List By ID And Date Impl");
		
		List<Order> sta = new ArrayList<Order>();

		String sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where member_id = ? and order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc";
		String datetime = (todate+" "+"23:59:59");
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, memberid);
			ps.setString(2, fromdate);
			ps.setString(3, datetime);
			
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
                od.setEventTitle(rs.getString("event_title"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	
	public List<Order> getOrdersByDate(String fromdate, String todate, int pagesize, int startindex, String eventtype)
	{
		logger.info("Inside Get Payment List By ID And Date Impl");
		
		List<Order> sta = new ArrayList<Order>();
		String sql = null;
		
		if(eventtype.equals("All")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Online")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.tracking_id !='' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Bank")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.bank_reference_number !='' and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Free")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where (o.bank_reference_number='' OR o.bank_reference_number is null) and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc limit ?,?";
		}
		
		String datetime = (todate+" "+"23:59:59");
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, fromdate);
			ps.setString(2, datetime);
			ps.setInt(3, startindex);
			ps.setInt(4, pagesize);
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
                od.setEventTitle(rs.getString("event_title"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<Order> getMemberOrdersByDateAndPage(int memberid,String fromdate, String todate, int pagesize, int startindex, String eventtype)
	{
		logger.info("Inside Get Members Payment List By ID And Date Impl");
		
		List<Order> sta = new ArrayList<Order>();
		String sql = null;
		
		if(eventtype.equals("All")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Online")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.tracking_id !='' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Bank")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.bank_reference_number !='' and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc limit ?,?";
		}
		if(eventtype.equals("Free")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where (o.bank_reference_number='' OR o.bank_reference_number is null) and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc limit ?,?";
		}
		
		String datetime = (todate+" "+"23:59:59");
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, fromdate);
			ps.setString(2, datetime);
			ps.setInt(3, memberid);
			ps.setInt(4, startindex);
			ps.setInt(5, pagesize);
			
			
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
                od.setEventTitle(rs.getString("event_title"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	
	public List<Order> getAllOrderDetails(String fromdate, String todate, String eventtype)
	{
		logger.info("Inside Get Members Payment List By ID And Date Impl");
		
		List<Order> sta = new ArrayList<Order>();
		String sql = null;
		
		if(eventtype.equals("All")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc";
		}
		if(eventtype.equals("Online")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.tracking_id !='' order by o.order_detail_id desc";
		}
		if(eventtype.equals("Bank")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.bank_reference_number !='' and o.payment_mode='Offline' order by o.order_detail_id desc";
		}
		if(eventtype.equals("Free")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where (o.bank_reference_number='' OR o.bank_reference_number is null) and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') order by o.order_detail_id desc";
		}
				
		String datetime = (todate+" "+"23:59:59");
				
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, fromdate);
			ps.setString(2, datetime);					
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
                od.setEventTitle(rs.getString("event_title"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	
	public List<Order> getAllMembersOrderDetails(int memberid,String fromdate, String todate, String eventtype)
	{
		logger.info("Inside Get Members Payment List By ID And Date Impl");
		
		List<Order> sta = new ArrayList<Order>();
		String sql = null;
		
		if(eventtype.equals("All")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc";
		}
		if(eventtype.equals("Online")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.tracking_id !='' and o.member_id=? order by o.order_detail_id desc";
		}
		if(eventtype.equals("Bank")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.bank_reference_number !='' and o.payment_mode='Offline' and o.member_id=? order by o.order_detail_id desc";
		}
		if(eventtype.equals("Free")) {
			sql = "select o.order_detail_id, o.sequence, o.order_number, o.customer_name, o.customer_emailid, o.customer_mobileno, o.biller_name, o.biller_emailid, o.biller_address1, o.biller_address2, o.biller_country_name, o.biller_state_name, o.biller_city_name, o.biller_pincode, o.biller_mobileno, o.subtotal, o.total_amount, o.member_id, o.order_status, o.payment_status, o.delivery_type, o.tracking_id, o.bank_reference_number, o.failure_message, o.payment_mode, o.card_name, o.bank_name, o.status_code, o.currency, DATE_FORMAT(o.order_date, '%d/%m/%Y %h:%i %p') as order_date, o.created_by, o.created_date, o.ip_address, o.order_by, e.event_title from orders o left join event e on o.event_id=e.event_id where (o.bank_reference_number='' OR o.bank_reference_number is null) and o.payment_mode='Offline' and o.order_date between STR_TO_DATE(?,'%d/%m/%Y') and STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') and o.member_id=? order by o.order_detail_id desc";
		}
				
		String datetime = (todate+" "+"23:59:59");
				
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, fromdate);
			ps.setString(2, datetime);
			ps.setInt(3, memberid);
			Order od = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				od = new Order();

                od.setOrderDetailId(rs.getInt("order_detail_id"));
                od.setSequence(rs.getInt("sequence"));
                od.setOrderNumber(rs.getString("order_number"));
                od.setCustomerName(rs.getString("customer_name"));
                od.setCustomerEmailid(rs.getString("customer_emailid"));
                od.setCustomerMobileno(rs.getString("customer_mobileno"));
                od.setBillerName(rs.getString("biller_name"));
                od.setBillerEmailid(rs.getString("biller_emailid"));
                od.setBillerAddress1(rs.getString("biller_address1"));
                od.setBillerAddress2(rs.getString("biller_address2"));
                od.setBillerCountryName(rs.getString("biller_country_name"));
                od.setBillerStateName(rs.getString("biller_state_name"));
                od.setBillerCityName(rs.getString("biller_city_name"));
                od.setBillerPincode(rs.getString("biller_pincode"));
                od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
                od.setTotalAmount(rs.getFloat("total_amount"));
                od.setMemberId(rs.getInt("member_id"));
                od.setOrderStatus(rs.getString("order_status"));
                od.setPaymentStatus(rs.getString("payment_status"));
                od.setDeliveryType(rs.getString("delivery_type"));
                od.setTrackingId(rs.getString("tracking_id"));
                od.setBankReferenceNumber(rs.getString("bank_reference_number"));
                od.setFailureMessage(rs.getString("failure_message"));
                od.setPaymentMode(rs.getString("payment_mode"));
                od.setCardName(rs.getString("card_name"));
                od.setBankName(rs.getString("bank_name"));
                od.setStatusCode(rs.getInt("status_code"));
                od.setCurrency(rs.getString("currency"));
                od.setOrderDate(rs.getString("order_date"));
                od.setCreatedBy(rs.getInt("created_by"));
                od.setCreatedDate(rs.getString("created_date"));
                od.setIpAddress(rs.getString("ip_address"));
                od.setEventTitle(rs.getString("event_title"));
				
				sta.add(od);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	
	@Override
	public String addOrder(Order orderDetail) {
		logger.info("+++++ GET ADD ORDER DETAIL +++++");

		String sql = "insert into orders (sequence, order_number, event_id, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, subtotal, total_amount, member_id, order_status, payment_status, delivery_type, payment_mode, order_date, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP(),?,?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, orderDetail.getSequence());
			ps.setString(2, orderDetail.getOrderNumber());
			ps.setInt(3, orderDetail.getEventId());
			ps.setString(4, orderDetail.getCustomerName());
			ps.setString(5, orderDetail.getCustomerEmailid());			
			ps.setString(6, orderDetail.getCustomerMobileno());
			ps.setString(7, orderDetail.getBillerName());
			ps.setString(8, orderDetail.getBillerEmailid());
			ps.setString(9, orderDetail.getBillerAddress1());
			ps.setString(10, orderDetail.getBillerAddress2());
			ps.setString(11, orderDetail.getBillerCountryName());
			ps.setString(12, orderDetail.getBillerStateName());
			ps.setString(13, orderDetail.getBillerCityName());
			ps.setString(14, orderDetail.getBillerPincode());
			ps.setString(15, orderDetail.getBillerMobileno());
            ps.setFloat(16, orderDetail.getSubTotal());
            ps.setFloat(17, orderDetail.getTotalAmount());
			ps.setInt(18, orderDetail.getMemberId());
			ps.setString(19, orderDetail.getOrderStatus());
			ps.setString(20, orderDetail.getPaymentStatus());
			ps.setString(21, orderDetail.getDeliveryType());
			ps.setString(22, orderDetail.getPaymentMode());
			//ps.setString(23, orderDetail.getOrderDate());
			ps.setInt(23, orderDetail.getCreatedBy());
			ps.setString(24, orderDetail.getIpAddress());
			ps.executeUpdate();
			return "Success";
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
	public String addCcavenueResponse(Order orderDetail) {
		logger.info("+++++ UPDATE CCAVENUE RESPONSE +++++");

		
		
		String sql = "update orders set order_status=?, payment_status=?, tracking_id=?, bank_reference_number=?, failure_message=?, payment_mode=?, card_name=?, bank_name=?, status_code=?, currency=? where order_number=?";
		//String sql = "insert into order_detail (sequence, order_number, quotation_id, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, delivery_name, delivery_emailid, delivery_address1, delivery_address2, delivery_country_name, delivery_state_name, delivery_city_name, delivery_pincode, delivery_mobileno, subtotal, total_amount, user_id, order_status, payment_status, delivery_type, tracking_id, bank_reference_number, failure_message, payment_mode, card_name, bank_name, status_code, currency, order_date, created_by, ip_address, order_by) select sequence, order_number, quotation_id, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, delivery_name, delivery_emailid, delivery_address1, delivery_address2, delivery_country_name, delivery_state_name, delivery_city_name, delivery_pincode, delivery_mobileno, subtotal, total_amount, user_id, ?, ?, delivery_type, ?, ?, ?, ?, ?, ?, ?, ?, order_date, created_by, ip_address, order_by from order_detail where order_number=? order by order_detail_id desc limit 0,1";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, orderDetail.getOrderStatus());
			ps.setString(2, orderDetail.getPaymentStatus());
			ps.setString(3, orderDetail.getTrackingId());
			ps.setString(4, orderDetail.getBankReferenceNumber());
			ps.setString(5, orderDetail.getFailureMessage());
			ps.setString(6, orderDetail.getPaymentMode());
			ps.setString(7, orderDetail.getCardName());
			ps.setString(8, orderDetail.getBankName());
			ps.setInt(9, orderDetail.getStatusCode());
			ps.setString(10, orderDetail.getCurrency());
			ps.setString(11, orderDetail.getOrderNumber());

			ps.executeUpdate();
			
			return "Success";
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
	public Order getOrderDetailByOrderNumber(String ordernumber) {
		logger.info("+++++ GET LAST ORDER DETAIL +++++");
		Order od = null;
		String sql = "select order_detail_id, sequence, order_number, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, subtotal, total_amount, member_id, order_status, payment_status, delivery_type, tracking_id, bank_reference_number, failure_message, payment_mode, card_name, bank_name, status_code, currency, DATE_FORMAT(order_date, '%d/%m/%Y %h:%i %p') as order_date, created_by, created_date, ip_address, order_by from orders where order_number = ?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ordernumber);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				od = new Order();

				od.setOrderDetailId(rs.getInt("order_detail_id"));
				od.setSequence(rs.getInt("sequence"));
				od.setOrderNumber(rs.getString("order_number"));
				od.setCustomerName(rs.getString("customer_name"));
				od.setCustomerEmailid(rs.getString("customer_emailid"));
				od.setCustomerMobileno(rs.getString("customer_mobileno"));
				od.setBillerName(rs.getString("biller_name"));
				od.setBillerEmailid(rs.getString("biller_emailid"));
				od.setBillerAddress1(rs.getString("biller_address1"));
				od.setBillerAddress2(rs.getString("biller_address2"));
				od.setBillerCountryName(rs.getString("biller_country_name"));
				od.setBillerStateName(rs.getString("biller_state_name"));
				od.setBillerCityName(rs.getString("biller_city_name"));
				od.setBillerPincode(rs.getString("biller_pincode"));
				od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
				od.setTotalAmount(rs.getFloat("total_amount"));
				od.setMemberId(rs.getInt("member_id"));
				od.setOrderStatus(rs.getString("order_status"));
				od.setPaymentStatus(rs.getString("payment_status"));
				od.setDeliveryType(rs.getString("delivery_type"));
				od.setTrackingId(rs.getString("tracking_id"));
				od.setBankReferenceNumber(rs.getString("bank_reference_number"));
				od.setFailureMessage(rs.getString("failure_message"));
				od.setPaymentMode(rs.getString("payment_mode"));
				od.setCardName(rs.getString("card_name"));
				od.setBankName(rs.getString("bank_name"));
				od.setStatusCode(rs.getInt("status_code"));
				od.setCurrency(rs.getString("currency"));
				od.setOrderDate(rs.getString("order_date"));
				od.setCreatedBy(rs.getInt("created_by"));
				od.setCreatedDate(rs.getString("created_date"));
				od.setIpAddress(rs.getString("ip_address"));				
			}
			rs.close();
			ps.close();
			return od;
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
	public Order getLastOrderDetail() {
		logger.info("+++++ GET LAST ORDER DETAIL +++++");
		Order od = null;
		String sql = "select order_detail_id,sequence, order_number from orders order by order_detail_id desc limit 0,1";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				od = new Order();

				od.setOrderDetailId(rs.getInt("order_detail_id"));
				od.setSequence(rs.getInt("sequence"));
				od.setOrderNumber(rs.getString("order_number"));
			}
			rs.close();
			ps.close();
			return od;
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
	
	
	
	public List<Payment> getAllInstallment()
	{
		logger.info("Inside Get All Members Installment Impl");		
		List<Payment> sta = new ArrayList<Payment>();
		String sql = "payment_id, transaction_date, transaction_amount, transaction_charges, comments, member_id from payment order by member_id";		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			Payment payment = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				payment = new Payment(
				rs.getInt("payment_id"),
                rs.getString("transaction_date"),
                rs.getFloat("transaction_amount"),
                rs.getFloat("transaction_charges"),
                rs.getString("comments"),
                rs.getInt("member_id")
				);
				
				sta.add(payment);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }


	}
	
	public List<Payment> getPayment(int memberid)
	{
		logger.info("Inside Get Members Payment Impl");
		
		List<Payment> sta = new ArrayList<Payment>();

		String sql = "select payment_id, transaction_date, transaction_amount, currency_id_transaction_amount, transaction_charges, currency_id_transaction_charges, paid_to_whom, payment_type, bank_name, branch_name, account_number, cheque_number, cheque_date, demand_draft_number, demand_draft_date, transaction_number, contact_number, payment_place, comments, amount_charges from payment where member_id=? and status='y'";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, memberid);
			
			Payment payment = null;
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next())
			{
				payment = new Payment(
				rs.getInt("payment_id"),
				rs.getString("transaction_date"),
				rs.getFloat("transaction_amount"),
				rs.getInt("currency_id_transaction_amount"),
                rs.getFloat("transaction_charges"),
                rs.getInt("currency_id_transaction_charges"),
                rs.getString("paid_to_whom"),
                rs.getString("payment_type"),
                rs.getString("bank_name"),
                rs.getString("branch_name"),
                rs.getString("account_number"),
                rs.getString("cheque_number"),
                rs.getString("cheque_date"),
                rs.getString("demand_draft_number"),
                rs.getString("demand_draft_date"),
                rs.getString("transaction_number"),
                rs.getString("contact_number"),
                rs.getString("payment_place"),
                rs.getString("comments"),
                rs.getFloat("amount_charges")
				);
				
				sta.add(payment);
           }
           rs.close();
           ps.close();
          
           return sta;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentCheque(Payment p)
	{
		logger.info("Inside Add Payment Cheque Impl");
		
		String sql = "insert into payment(transaction_date,transaction_amount,currency_id_transaction_amount,transaction_charges,currency_id_transaction_charges,paid_to_whom,payment_type,bank_name,branch_name,account_number,cheque_number,cheque_date,comments,amount_charges,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getChequeNumber());
			ps.setString(12, p.getChequeDate());
			ps.setString(13, p.getComments());
			ps.setFloat(14, p.getAmountCharges());
			ps.setInt(15, p.getMemberId());			
			ps.setString(16, p.getStatus());
			ps.setInt(17, p.getCreatedBy());			
			ps.setString(18, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentDemandDraft(Payment p)
	{
		logger.info("Inside Add Payment Demand Draft Impl");
		
		String sql = "insert into payment(transaction_date,transaction_amount,currency_id_transaction_amount,transaction_charges,currency_id_transaction_charges,paid_to_whom,payment_type,bank_name,branch_name,account_number,demand_draft_number,demand_draft_date,comments,amount_charges,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getChequeNumber());
			ps.setString(12, p.getChequeDate());
			ps.setString(13, p.getComments());
			ps.setFloat(14, p.getAmountCharges());
			ps.setInt(15, p.getMemberId());			
			ps.setString(16, p.getStatus());
			ps.setInt(17, p.getCreatedBy());			
			ps.setString(18, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentNeft(Payment p)
	{
		logger.info("Inside Add Payment NEFT Impl");
		
		String sql = "insert into payment(transaction_date,transaction_amount,currency_id_transaction_amount,transaction_charges,currency_id_transaction_charges,paid_to_whom,payment_type,bank_name,branch_name,account_number,transaction_number,comments,amount_charges,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getTransactionNumber());
			ps.setString(12, p.getComments());
			ps.setFloat(13, p.getAmountCharges());
			ps.setInt(14, p.getMemberId());			
			ps.setString(15, p.getStatus());
			ps.setInt(16, p.getCreatedBy());			
			ps.setString(17, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentCash(Payment p)
	{
		logger.info("Inside Add Payment Cash Impl");
		
		String sql = "insert into payment(transaction_date,transaction_amount,currency_id_transaction_amount,transaction_charges,currency_id_transaction_charges,paid_to_whom,payment_type,transaction_number,contact_number,payment_place,comments,amount_charges,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getTransactionNumber());
			ps.setString(9, p.getContactNumber());
			ps.setString(10, p.getPaymentPlace());
			ps.setString(11, p.getComments());
			ps.setFloat(12, p.getAmountCharges());
			ps.setInt(13, p.getMemberId());			
			ps.setString(14, p.getStatus());
			ps.setInt(15, p.getCreatedBy());			
			ps.setString(16, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentOther(Payment p)
	{
		logger.info("Inside Add Payment Other Impl");
		
		String sql = "insert into payment (transaction_date,transaction_amount,currency_id_transaction_amount,transaction_charges,currency_id_transaction_charges,paid_to_whom,payment_type,transaction_number,contact_number,comments,amount_charges,member_id,status,created_by,ip_address) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getTransactionNumber());
			ps.setString(9, p.getContactNumber());
			ps.setString(10, p.getComments());
			ps.setFloat(11, p.getAmountCharges());
			ps.setInt(12, p.getMemberId());			
			ps.setString(13, p.getStatus());
			ps.setInt(14, p.getCreatedBy());			
			ps.setString(15, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public int getLastPaymentId()
	{
		logger.info("Inside get last payment id Impl");
		
		String sql = "select payment_id from payment order by payment_id desc limit 0,1 ";
		int id=0;
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				id= rs.getInt("payment_id");
			}
            rs.close();
            ps.close();
            return id;
        }
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentImage(PaymentImage p)
	{
		logger.info("Inside Add Payment Images Impl"); 
		
		String sql = "insert into payment_image (image,payment_id,created_by,ip_address) values (?,?,?,?)";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getImage());
			ps.setInt(2, p.getPaymentId());
			ps.setInt(3, p.getCreatedBy());			
			ps.setString(4, p.getIpAddress());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deletePaymentImage(int paymentid)
	{
		logger.info("Inside Delete Payment Image Impl");
		
		String sql1 = "delete from payment_image where payment_id =?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, paymentid);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {
            	}
            }
        }
	}
	
	public void editPaymentCheque(Payment p)
	{
		logger.info("Inside Edit Payment Cheque Impl");
		
		String sql = "update payment set transaction_date=?, transaction_amount=?, currency_id_transaction_amount=?, transaction_charges=?, currency_id_transaction_charges=?, paid_to_whom=?, payment_type=?, bank_name=?, branch_name=?, account_number=?, cheque_number=?, cheque_date=?, comments=?, amount_charges=?, created_by=?, ip_address=? where payment_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getChequeNumber());
			ps.setString(12, p.getChequeDate());
			ps.setString(13, p.getComments());
			ps.setFloat(14, p.getAmountCharges());
			ps.setInt(15, p.getCreatedBy());			
			ps.setString(16, p.getIpAddress());
			ps.setInt(17, p.getPaymentId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editPaymentDemandDraft(Payment p)
	{
		logger.info("Inside Edit Payment Demand Draft Impl");
		
		String sql = "update payment set transaction_date=?, transaction_amount=?, currency_id_transaction_amount=?, transaction_charges=?, currency_id_transaction_charges=?, paid_to_whom=?, payment_type=?, bank_name=?, branch_name=?, account_number=?, demand_draft_number=?, demand_draft_date=?, comments=?, amount_charges=?, created_by=?, ip_address=? where payment_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getChequeNumber());
			ps.setString(12, p.getChequeDate());
			ps.setString(13, p.getComments());
			ps.setFloat(14, p.getAmountCharges());
			ps.setInt(15, p.getCreatedBy());			
			ps.setString(16, p.getIpAddress());
			ps.setInt(17, p.getPaymentId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editPaymentNeft(Payment p)
	{
		logger.info("Inside Edit Payment NEFT Impl");
		
		String sql = "update payment set transaction_date=?, transaction_amount=?, currency_id_transaction_amount=?, transaction_charges=?, currency_id_transaction_charges=?, paid_to_whom=?, payment_type=?, bank_name=?, branch_name=?, account_number=?, transaction_number=?, comments=?, amount_charges=?, created_by=?, ip_address=? where payment_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getBankName());
			ps.setString(9, p.getBranchName());
			ps.setString(10, p.getAccountNumber());
			ps.setString(11, p.getTransactionNumber());
			ps.setString(12, p.getComments());
			ps.setFloat(13, p.getAmountCharges());
			ps.setInt(14, p.getCreatedBy());			
			ps.setString(15, p.getIpAddress());
			ps.setInt(16, p.getPaymentId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editPaymentCash(Payment p)
	{
		logger.info("Inside Edit Payment Cash Impl");
		
		String sql = "update payment set transaction_date=?, transaction_amount=?, currency_id_transaction_amount=?, transaction_charges=?, currency_id_transaction_charges=?, paid_to_whom=?, payment_type=?, transaction_number=?, contact_number=?, payment_place=?, comments=?, amount_charges=?, created_by=?, ip_address=? where payment_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getTransactionNumber());
			ps.setString(9, p.getContactNumber());
			ps.setString(10, p.getPaymentPlace());
			ps.setString(11, p.getComments());
			ps.setFloat(12, p.getAmountCharges());
			ps.setInt(13, p.getCreatedBy());			
			ps.setString(14, p.getIpAddress());
			ps.setInt(15, p.getPaymentId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void editPaymentOther(Payment p)
	{
		logger.info("Inside Edit Payment Other Impl");
		
		String sql = "update payment set transaction_date=?, transaction_amount=?, currency_id_transaction_amount=?, transaction_charges=?, currency_id_transaction_charges=?, paid_to_whom=?, payment_type=?, transaction_number=?, contact_number=?, comments=?, amount_charges=?, created_by=?, ip_address=? where payment_id=?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getTransactionDate());
			ps.setFloat(2, p.getTransactionAmount());
			ps.setInt(3, p.getCurrencyIdTransactionAmount());
			ps.setFloat(4, p.getTransactionCharges());
			ps.setInt(5, p.getCurrencyIdTransactionCharges());
			ps.setString(6, p.getPaidToWhom());
			ps.setString(7, p.getPaymentType());
			ps.setString(8, p.getTransactionNumber());
			ps.setString(9, p.getContactNumber());
			ps.setString(10, p.getComments());
			ps.setFloat(11, p.getAmountCharges());
			ps.setInt(12, p.getCreatedBy());			
			ps.setString(13, p.getIpAddress());
			ps.setInt(14, p.getPaymentId());
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
        }
		finally
		{
			if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void deletePayment(int paymentid)
	{
		logger.info("Inside Delete Payment Impl");
		
		String sql1 = "update payment set status = 'n' where payment_id =?";
		
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setInt(1, paymentid);
			
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
        }
		finally
		{
            if (conn != null)
            {
            	try
            	{
            		conn.close();
                }
            	catch (SQLException e) {
            	}
            }
        }
	}
	
	public void addMembershipCharges(MembershipCharges mc)	{
		logger.info("++++++++++ ADD MEMBERSHIP CHARGES ++++++++++");		
		String sql = "insert into membership_charges(rotary_year_id,member_category_id,billing_amount,currency_id,status,created_by,ip_address) values (?,?,?,?,?,?,?)";		
		Connection conn = null;
		try	{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			ps.setInt(1, mc.getRotaryYearId());
			ps.setInt(2, mc.getMemberCategoryId());
			ps.setFloat(3, mc.getBillingAmount());
			ps.setInt(4, mc.getCurrencyId());
			ps.setString(5, mc.getStatus());
			ps.setInt(6, mc.getCreatedBy());
			ps.setString(7, mc.getIpAddress());		
			ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<MembershipCharges> getMembershipCharges(int rotaryyearid) {
		logger.info("++++++++++ GET MEMBERSHIP CHARGES ++++++++++");		
		List<MembershipCharges> sta = new ArrayList<MembershipCharges>();	
		String sql = "select membership_charge_id, member_id, rotary_year_id, member_category_id, billing_amount, currency_id from membership_charges where status = 'y' and rotary_year_id = ? order by member_category_id";
		
		Connection conn = null;
		try	{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);	
			ps.setInt(1, rotaryyearid);
			MembershipCharges membershipCharges = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				membershipCharges = new MembershipCharges(
				rs.getInt("membership_charge_id"),
                rs.getInt("member_id"),
                rs.getInt("rotary_year_id"),
                rs.getInt("member_category_id"),
                rs.getFloat("billing_amount"),
                rs.getInt("currency_id")
				);				
				sta.add(membershipCharges);
           }
           rs.close();
           ps.close();
          
           return sta;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public List<Members> getMembershipChargesByMember() {
		logger.info("++++++++++ GET MEMBERSHIP CHARGES BY MEMBER ++++++++++");		
		List<Members> sta = new ArrayList<Members>();	
		String sql = "select m.member_id, m.member_first_name, m.member_last_name, ry.rotary_year_id, ry.rotary_year_title, mc.member_category_id, mc.member_category_name, mcs.billing_amount, c.currency_id, c.currency_code from members m, member_category_by_year mcby, rotary_year ry, member_category mc, membership_charges mcs, currency c where mcby.member_id=m.member_id and mcby.member_category_id = mc.member_category_id and mcs.rotary_year_id=ry.rotary_year_id and mcs.member_category_id=mc.member_category_id and mcs.currency_id=c.currency_id and m.status='y' group by m.member_id";		
		Connection conn = null;
		try	{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			Members member = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				member = new Members(
				rs.getInt("member_id"),
                rs.getString("member_first_name"),
                rs.getString("member_last_name"),
                rs.getInt("rotary_year_id"),
                rs.getString("rotary_year_title"),
                rs.getInt("member_category_id"),
                rs.getString("member_category_name"),
                rs.getFloat("billing_amount"),
                rs.getInt("currency_id"),
                rs.getString("currency_code")
				);				
				sta.add(member);
           }
           rs.close();
           ps.close();
          
           return sta;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public PaymentDetail getLastPaymentDetail(){
		logger.info("********** GET LAST PAYMENT DETAIL IMPL **********");		
		String sql = "select payment_detail_id, sequence, payment_number, transaction_for_id, rotary_year_id, member_id, member_category_id, payment_amount, currency_id, payment_status, payment_method, DATE_FORMAT(created_date, '%d/%m/%Y') as 'created_date' from payment_detail where status = 'y' order by payment_detail_id desc limit 0,1";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);						
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
		                rs.getInt("payment_detail_id"),
		                rs.getInt("sequence"),
		                rs.getString("payment_number"),
		                rs.getInt("transaction_for_id"),
		                rs.getInt("rotary_year_id"), 
		                rs.getInt("member_id"),
		                rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("payment_status"),
		                rs.getString("payment_method"),
		                rs.getString("created_date")
		                );		
           }
           rs.close();
           ps.close();
          
           return paymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public void addMembershipPaymentTransaction(PaymentDetail pd)	{
		logger.info("++++++++++ ADD MEMBERSHIP PAYMENT TRANSACTION IMPL ++++++++++");		
		String sql = "insert into payment_detail(transaction_for_id, rotary_year_id, member_id, member_category_id, payment_amount, currency_id, payment_status, status, created_by, ip_address) values (?,?,?,?,?,?,?,?,?,?)";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			
			ps.setInt(1, pd.getTransactionForId());
			ps.setInt(2, pd.getRotaryYearId());
			ps.setInt(3, pd.getMemberId());
			ps.setInt(4, pd.getMemberCategoryId());
			ps.setFloat(5, pd.getPaymentAmount());
			ps.setInt(6, pd.getCurrencyId());
			ps.setString(7, pd.getPaymentStatus());						
			ps.setString(8, pd.getStatus());
			ps.setInt(9, pd.getCreatedBy());
			ps.setString(10, pd.getIpAddress());			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
	
	public List<PaymentDetail> getPendingPaymentsByPage(int pagesize, int startindex) {
		logger.info("++++++++++ GET PENDING PAYMENTS BY PAGE IMPL +++++++++++");		
		List<PaymentDetail> PaymentDetail = new ArrayList<PaymentDetail>();		
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.payment_status = 'Unpaid' and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id order by payment_detail_id desc limit ?,?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setInt(1, startindex);
			ps.setInt(2, pagesize);
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
				);
				PaymentDetail.add(paymentDetail);
           }
           rs.close();
           ps.close();
          
           return PaymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<PaymentDetail> getAllPendingPayments() {
		logger.info("++++++++++ GET ALL PENDING PAYMENTS IMPL +++++++++++");		
		List<PaymentDetail> PaymentDetail = new ArrayList<PaymentDetail>();		
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.payment_status = 'Unpaid' and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id order by payment_detail_id desc";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
				);
				PaymentDetail.add(paymentDetail);
           }
           rs.close();
           ps.close();
          
           return PaymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<PaymentDetail> searchPendingPayments(String keyword) {
		logger.info("++++++++++ SEARCH PENDING PAYMENTS IMPL ++++++++++");		
		List<PaymentDetail> PaymentDetail = new ArrayList<PaymentDetail>();	
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.payment_status = 'Unpaid' and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id and Concat(m.member_first_name,'', m.member_middle_name,'', m.member_last_name) like ?";		
		Connection conn = null;		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ps.setString(1, '%'+keyword+'%');
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
				);
				PaymentDetail.add(paymentDetail);
           }
           rs.close();
           ps.close();
          
           return PaymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public PaymentDetail getPendingPaymentDetailByMemberId(int memberid){
		logger.info("********** GET PAYMENT DETAIL BY MEMBER ID IMPL **********");		
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, pd.payment_status, pd.payment_method, pd.bank_name, pd.branch_name, pd.cheque_no, pd.paid_amount, DATE_FORMAT(pd.transaction_date,'%d/%m/%Y') as transaction_date, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.member_id = ? and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberid);
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("payment_status"),
		                rs.getString("payment_method"),
		                rs.getString("bank_name"),
		                rs.getString("branch_name"),
		                rs.getString("cheque_no"),
		                rs.getFloat("paid_amount"),
		                rs.getString("transaction_date"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
		                );		
           }
           rs.close();
           ps.close();
          
           return paymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                }
            	catch (SQLException e) {}
            }
        }
	}
	
	public int getLastSequence() {
		logger.info("++++++++++ GET LAST SEQUENCE ++++++++++");		
		int id = 0;		
		String sql = "select sequence from payment_detail order by sequence desc limit 0,1";		
		Connection conn = null;		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				id= rs.getInt("sequence");
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
                } catch (SQLException e) {}
            }
        }
	}
	
	public String getLastPaymentNo() {
		logger.info("++++++++++ GET LAST PAYMENT NO ++++++++++");		
		String no = "";		
		String sql = "select payment_number from payment_detail order by payment_detail_id desc limit 0,1";		
		Connection conn = null;		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				no= rs.getString("payment_number");
			}
			rs.close();
			ps.close();          
			return no;
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        } finally {
        	if (conn != null) {
        		try {
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public void addPaymentInfo(PaymentDetail pd)	{
		logger.info("++++++++++ ADD PAYMENT INFO IMPL ++++++++++");		
		String sql = "update payment_detail set sequence=?, payment_number=?, payment_status=?, payment_method=?, bank_name=?, branch_name=?, cheque_no=?, paid_amount=?, transaction_date = STR_TO_DATE(?,'%d/%m/%Y'), created_by=?, ip_address=? where payment_detail_id=?";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			ps.setInt(1, pd.getSequence());
			ps.setString(2, pd.getPaymentNumber());
			ps.setString(3, pd.getPaymentStatus());
			ps.setString(4, pd.getPaymentMethod());
			ps.setString(5, pd.getBankName());
			ps.setString(6, pd.getBranchName());
			ps.setString(7, pd.getChequeNo());
			ps.setFloat(8, pd.getPaidAmount());
			ps.setString(9, pd.getTransactionDate());
			ps.setInt(10, pd.getCreatedBy());			
			ps.setString(11, pd.getIpAddress());
			ps.setInt(12, pd.getPaymentDetailId());
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}
	
	public List<PaymentDetail> getPaymentHistoryByPage(int rotaryyearid, int pagesize, int startindex) {
		logger.info("++++++++++ GET PAYMENT HISTORY BY PAGE IMPL +++++++++++");		
		List<PaymentDetail> PaymentDetail = new ArrayList<PaymentDetail>();		
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, pd.payment_status, pd.payment_method, pd.bank_name, pd.branch_name, pd.cheque_no, pd.paid_amount, DATE_FORMAT(pd.transaction_date,'%d/%m/%Y') as transaction_date, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.payment_status = 'Paid' and pd.rotary_year_id=? and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id order by payment_detail_id desc limit ?,?";
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rotaryyearid);
			ps.setInt(2, startindex);
			ps.setInt(3, pagesize);
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("payment_status"),
		                rs.getString("payment_method"),
		                rs.getString("bank_name"),
		                rs.getString("branch_name"),
		                rs.getString("cheque_no"),
		                rs.getFloat("paid_amount"),
		                rs.getString("transaction_date"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
				);
				PaymentDetail.add(paymentDetail);
           }
           rs.close();
           ps.close();
          
           return PaymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public List<PaymentDetail> getAllPaymentHistory(int rotaryyearid) {
		logger.info("++++++++++ GET ALL PAYMENT HISTORY IMPL +++++++++++");		
		List<PaymentDetail> PaymentDetail = new ArrayList<PaymentDetail>();		
		String sql = "select pd.payment_detail_id, pd.transaction_for_id, pd.rotary_year_id, pd.member_id, pd.member_category_id, pd.payment_amount, pd.currency_id, pd.payment_status, pd.payment_method, pd.bank_name, pd.branch_name, pd.cheque_no, pd.paid_amount, DATE_FORMAT(pd.transaction_date,'%d/%m/%Y') as transaction_date, t.transaction_for_name, ry.rotary_year_title, mc.member_category_name, m.member_name_title, m.member_first_name, m.member_middle_name, m.member_last_name, c.currency_code from payment_detail pd, transaction_for t, members m, rotary_year ry, member_category mc, currency c where pd.status = 'y' and pd.payment_status = 'Paid' and pd.rotary_year_id=? and t.transaction_for_id = pd.transaction_for_id and ry.rotary_year_id = pd.rotary_year_id and mc.member_category_id = pd.member_category_id and m.member_id = pd.member_id and c.currency_id = pd.currency_id order by payment_detail_id desc";		
		Connection conn = null;		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rotaryyearid);			
			PaymentDetail paymentDetail = null;			
			ResultSet rs = ps.executeQuery();			
			while (rs.next()) {
				paymentDetail = new PaymentDetail(
						rs.getInt("payment_detail_id"),
						rs.getInt("transaction_for_id"),
						rs.getInt("rotary_year_id"),
						rs.getInt("member_id"),
						rs.getInt("member_category_id"),
		                rs.getFloat("payment_amount"),
		                rs.getInt("currency_id"),
		                rs.getString("payment_status"),
		                rs.getString("payment_method"),
		                rs.getString("bank_name"),
		                rs.getString("branch_name"),
		                rs.getString("cheque_no"),
		                rs.getFloat("paid_amount"),
		                rs.getString("transaction_date"),
		                rs.getString("transaction_for_name"),
		                rs.getString("rotary_year_title"),
		                rs.getString("member_category_name"),
		                rs.getString("member_name_title"),
		                rs.getString("member_first_name"),
		                rs.getString("member_middle_name"),
		                rs.getString("member_last_name"),
		                rs.getString("currency_code")
				);
				PaymentDetail.add(paymentDetail);
           }
           rs.close();
           ps.close();
          
           return PaymentDetail;
        } catch (SQLException e) {
			throw new RuntimeException(e);
        } finally {
            if(conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e) {}
            }
        }
	}
	
	public void addPayment(PaymentDetail pd)	{
		logger.info("++++++++++ ADD PAYMENT IMPL ++++++++++");		
		String sql = "insert into payment_detail (sequence, payment_number, transaction_for_id, rotary_year_id, member_id, member_category_id, currency_id, payment_status, payment_method, bank_name, branch_name, cheque_no, paid_amount, transaction_date, status, created_by, ip_address) value (?,?,1,?,?,?,1,?,?,?,?,?,?,STR_TO_DATE(?,'%d/%m/%Y'),'y',?,?)";		
		
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);		
			ps.setInt(1, pd.getSequence());
			ps.setString(2, pd.getPaymentNumber());			
			ps.setInt(3, pd.getRotaryYearId());
			ps.setInt(4, pd.getMemberId());
			ps.setInt(5, pd.getMemberCategoryId());
			ps.setString(6, pd.getPaymentStatus());
			ps.setString(7, pd.getPaymentMethod());
			ps.setString(8, pd.getBankName());
			ps.setString(9, pd.getBranchName());
			ps.setString(10, pd.getChequeNo());
			ps.setFloat(11, pd.getPaidAmount());
			ps.setString(12, pd.getTransactionDate());			
			ps.setInt(13, pd.getCreatedBy());			
			ps.setString(14, pd.getIpAddress());			
			ps.executeUpdate();					
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
        } finally {
            if (conn != null) {
            	try	{
            		conn.close();
                } catch (SQLException e1) {}
            }
        }
	}

	@Override
	public Order getOrderDetailByEventIdandMemberId(int eventId, int memberId) {
		logger.info("+++++ GET ORDER DETAIL BY EVENT ID AND MEMBERID+++++");
		Order od = null;
		String sql = "select order_detail_id, sequence, order_number, customer_name, customer_emailid, customer_mobileno, biller_name, biller_emailid, biller_address1, biller_address2, biller_country_name, biller_state_name, biller_city_name, biller_pincode, biller_mobileno, subtotal, total_amount, member_id, order_status, payment_status, delivery_type, tracking_id, bank_reference_number, failure_message, payment_mode, card_name, bank_name, status_code, currency, DATE_FORMAT(order_date, '%d/%m/%Y %h:%i %p') as order_date, created_by, created_date, ip_address, order_by from orders where  member_id=? and event_id=?";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, memberId);
			ps.setInt(2, eventId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				od = new Order();

				od.setOrderDetailId(rs.getInt("order_detail_id"));
				od.setSequence(rs.getInt("sequence"));
				od.setOrderNumber(rs.getString("order_number"));
				od.setCustomerName(rs.getString("customer_name"));
				od.setCustomerEmailid(rs.getString("customer_emailid"));
				od.setCustomerMobileno(rs.getString("customer_mobileno"));
				od.setBillerName(rs.getString("biller_name"));
				od.setBillerEmailid(rs.getString("biller_emailid"));
				od.setBillerAddress1(rs.getString("biller_address1"));
				od.setBillerAddress2(rs.getString("biller_address2"));
				od.setBillerCountryName(rs.getString("biller_country_name"));
				od.setBillerStateName(rs.getString("biller_state_name"));
				od.setBillerCityName(rs.getString("biller_city_name"));
				od.setBillerPincode(rs.getString("biller_pincode"));
				od.setBillerMobileno(rs.getString("biller_mobileno"));
                od.setSubTotal(rs.getFloat("subtotal"));
				od.setTotalAmount(rs.getFloat("total_amount"));
				od.setMemberId(rs.getInt("member_id"));
				od.setOrderStatus(rs.getString("order_status"));
				od.setPaymentStatus(rs.getString("payment_status"));
				od.setDeliveryType(rs.getString("delivery_type"));
				od.setTrackingId(rs.getString("tracking_id"));
				od.setBankReferenceNumber(rs.getString("bank_reference_number"));
				od.setFailureMessage(rs.getString("failure_message"));
				od.setPaymentMode(rs.getString("payment_mode"));
				od.setCardName(rs.getString("card_name"));
				od.setBankName(rs.getString("bank_name"));
				od.setStatusCode(rs.getInt("status_code"));
				od.setCurrency(rs.getString("currency"));
				od.setOrderDate(rs.getString("order_date"));
				od.setCreatedBy(rs.getInt("created_by"));
				od.setCreatedDate(rs.getString("created_date"));
				od.setIpAddress(rs.getString("ip_address"));				
			}
			rs.close();
			ps.close();
			return od;
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
}