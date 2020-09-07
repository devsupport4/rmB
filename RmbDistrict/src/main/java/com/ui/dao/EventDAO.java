package com.ui.dao;

import java.util.List;

import com.ui.model.Event;
import com.ui.model.EventAgenda;
import com.ui.model.EventCharges;
import com.ui.model.EventImage;
import com.ui.model.EventRegistration;
import com.ui.model.EventType;
import com.ui.model.EventUrl;
import com.ui.model.MemberResponse;

public interface EventDAO {
	
	public List<EventType> getAllEventType();
	public List<Event> getAllEvents();
	public List<Event> getAllEventsByDate(String todaydate);
	public List<Event> getLastThreeEventsByDate(String todaydate);
	public List<EventAgenda> getRelatedEventAgenda(int eventid);
	List<EventImage> getRelatedEventImages(int eventid);
	public List<EventUrl> getRelatedEventUrl(int eventid);
	public int getLastEventId();
	public void addEvent(Event e);
	public void addEventAgenda(EventAgenda ea);
	public void addEventImages(EventImage ei);
	public void addEventUrl(EventUrl eu);
	public void deleteEvent(int id);
	public void deleteSelectedEventAgenda(int eventid);
	public void deleteSelectedEventImages(int eventid);
	public void deleteSelectedEventUrl(int eventid);
	public void editEvent(Event e);
	public Event getLastEventDetail();
	public List<MemberResponse> getAllEventResponse();
	public List<MemberResponse> getAllEventResponseCounts();
	public Event getEventDetailById(int id);
	public void AddCommingResponse(MemberResponse mr);
	public void AddNotComingResponse(MemberResponse mr);
	public MemberResponse getMemberEventResponse(int id, int memberid);
	public void addRegistrationCharges(EventCharges ec);
	public List<EventCharges> getEventChargesList(int eventid);
	public void deleteEventCharge(int id);
	public String addRotaryUser(EventRegistration r);
	public String eventRegistration(EventRegistration r);
	public EventRegistration getEventRegistrationDetails(int eid,int mid); 
	public void updateEventPayment(EventRegistration e);
	public void updateOrdersPayment(EventRegistration e);
	
	public Event getLastEventDetailForFront();
	public List<EventRegistration> getAllEventRegistrationDetails(String eventtype);
	public EventRegistration getEventRegistrationDetailsById(int eventid);
	public List<EventRegistration> searchEventRegisteredMembers(String keyword, String eventtype);
	public List<EventRegistration> getEventRegistrationDetailsByPage(int pagesize,int startindex,String eventtype);
	public void updateStatus(int id,String newst);
	public void updateEventRegistrationPayment(EventRegistration e);
	public void updateOrdersPaidPayment(EventRegistration e);
	public void updateRegistrationCharges(EventCharges eventCharges);
	public EventRegistration getMemberEventPaymentStatus(int id, int memberid);
	public EventRegistration getMemberEventRegistrationStatus(int id, int memberid);
	
	public List<EventRegistration> getAllRegisterdMembers(int eventid);
	public List<EventRegistration> getAllEventRegisterdMembersForResult(String fromdate, String todate, int pagesize, int startindex);
	public List<EventRegistration> getAllEventRegisterdMembers();
	public EventRegistration getRegisteredMemberDetailByeventregistrationid(int eventregistrationid);
	public void updateEventRegistrationStatus(int eventregistrationID, String status);
}
