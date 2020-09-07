package com.ui.dao;

import java.util.List;

import com.ui.model.MemberOneToOne;
import com.ui.model.MemberReferral;
import com.ui.model.MemberThankYouSlip;

public interface MemberReferralDAO {
	public List<MemberOneToOne> getMemberMeetingsById(int memberid, String fromdate, String todate);
	public List<MemberThankYouSlip> getMemberBusinessById(int memberid, String fromdate, String todate);
	public List<MemberReferral> getMemberReferencesById(int memberid, String fromdate, String todate);
	public void saveReferral(MemberReferral mr);
	public void saveThankYouSlip(MemberThankYouSlip mr);
	public void saveOnetoOne(MemberOneToOne mr);
	public List<MemberThankYouSlip> getAllBusiness();
	public void deleteBusiness(int id);
	public MemberThankYouSlip getBusinessDetailById(int thankyouslipid);
	public List<MemberOneToOne> getAllMeeting();
	public void deleteMeeting(int meetingid);
	public MemberOneToOne getMeetingDetailById(int onetooneid);
	public List<MemberReferral> getAllReference();
	public void deleteRef(int referenceid);
	public MemberReferral getReferenceDetailById(int memberreferralid);
	public List<MemberThankYouSlip> getMemberBusinessForChapterSummaryById(int memberid, String fromdate, String todate);
	public List<MemberReferral> getMemberReferralsById(int memberid, String fromdate, String todate);
	public void ChangeRefStatus(int memberreferralid, String status);
	public String updateReferral(MemberReferral memberReferral);
	public void CloseRef(MemberReferral memberReferral);
}
