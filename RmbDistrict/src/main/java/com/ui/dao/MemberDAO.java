package com.ui.dao;

import java.util.List;

import com.ui.model.MemberCategoryByYear;
import com.ui.model.MemberLandlinePhoneNumber;
import com.ui.model.Members;
import com.ui.model.MembersFamily;

public interface MemberDAO {
	
	public List<Members> getLastMemberSequenceByCategory();
	public void addMemberDetail(Members members);
	public List<Members> getAllMembers();
	public List<Members> getLastEightMembersPics();
	public void addContactDetail(Members members);
	public int getLastMemberId();
	public void addMemberLandlinePhoneNumber(MemberLandlinePhoneNumber memberlandlinephonenumber);
	public Members getMemberByMemberId(int memberid);
	public Members getMemberDetailByMemberId(int memberid);
	public List<MembersFamily> getSpouseSequence(int membersid);
	public List<MembersFamily> getSpouseData(int membersid);
	public void addSpouseDetail(MembersFamily membersfamily);
	public int getLastMembersFamilyId();
	public List<MemberLandlinePhoneNumber> getFamilyResidentialLandline(int memberfamilyid);
	public List<MemberLandlinePhoneNumber> getFamilyWorkLandline(int memberfamilyid);
	public List<MemberLandlinePhoneNumber> getMemberResidentialLandline(int memberid);
	public List<MemberLandlinePhoneNumber> getMemberWorkLandline(int memberid);
	public void deleteFamilyResidentialLandline(int memberfamilyid);
	public void deleteFamilyWorkLandline(int memberfamilyid);
	public void editSpouseDetail(MembersFamily membersfamily);
	public void editSpouseDetail1(MembersFamily membersfamily);
	
	
	
	public List<Members> getMembersByPage(int pagesize, int startindex,int fellowship_id);
	
	public List<Members> getMembersByPage(int pagesize, int startindex);
	
	
	public List<Members> getMemberForMembersDirectoryByPage(int pagesize, int startindex);
	public void deleteMember(int membersid);
	public List<Members> getAllBirthdays(String birthmonth);
	public List<Members> getAllAnniversaries(String anniversarymonth);
	public List<Members> getAllBirthdaysByDate(String currentdate);
	public List<Members> getFistFourBirthdaysByDate(String currentdate);
	public List<Members> getAllAnniversariesByDate(String currentdate);
	public void editMemberDetail1(Members members);
	public void deleteMemberResidentialLandline(int memberid);
	public void deleteMemberWorkLandline(int memberid);
	public void deleteFamilyMember(int membersfamilyid);
	public List<Members> searchMembers(String keyword);
	public List<Members> searchMembersForMembersDirectory(String keyword);
	public List<Members> getAllMemberAndMemberFamily();
	public void addMemberCategoryByRotaryYear(MemberCategoryByYear mcby);
	public MemberCategoryByYear getMemberCurrentCategoryId(int memberid, int rotaryyearid);
	public List<Members> getChapterSummaryByDate(String fromdate, String todate);
	public MembersFamily getSpouseDetailById(int memberfamilyid);
	public List<Members> getMemberFullName();
	Members checkCurrentPassword(int memberid, String password);
	public void changePassword(Members m);
	public void resetPassword(Members m);
	public List<Members> getAllMembersDirectory();
	public void editmemberpersonaldetail1(Members members);
	public void editContactDetailForMobile(Members members);
	public void editWorkDetail(Members members);
	public void editProfilePicture1(Members members);
	
	public String checkemailaddress(String mobilenumber, String email);
	
	public String getMemberByEmailAndNumber(String mobilenumber, String email);
	/*public int getMemberByEmailAndNumber(String mobilenumber, String email);*/
	public void setMemberActiveOrInA(Members m);
	public List<Members> getAllMembersPics();
	public List<Members> getAllMemberContactInfoForMailer();
	
	public void addVocation(String title, String desc, String ipAddress, int id);
	public List<Members> getallVocation();
	public void deleteVocation(int vocationid);
	
	public int getLastMemberByEmail(String email, String mobileno);
  public void addMemberWorkDetails(MemberLandlinePhoneNumber memberlandlinephonenumber);
  
}
